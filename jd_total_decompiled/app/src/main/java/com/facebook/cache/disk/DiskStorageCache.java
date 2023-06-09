package com.facebook.cache.disk;

import com.facebook.binaryresource.BinaryResource;
import com.facebook.cache.common.CacheErrorLogger;
import com.facebook.cache.common.CacheEventListener;
import com.facebook.cache.common.CacheKey;
import com.facebook.cache.common.CacheKeyUtil;
import com.facebook.cache.common.WriterCallback;
import com.facebook.cache.disk.DiskStorage;
import com.facebook.common.disk.DiskTrimmable;
import com.facebook.common.disk.DiskTrimmableRegistry;
import com.facebook.common.internal.VisibleForTesting;
import com.facebook.common.logging.FLog;
import com.facebook.common.statfs.StatFsHelper;
import com.facebook.common.time.Clock;
import com.facebook.common.time.SystemClock;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
/* loaded from: classes.dex */
public class DiskStorageCache implements FileCache, DiskTrimmable {
    public static final int START_OF_VERSIONING = 1;
    private static final double TRIMMING_LOWER_BOUND = 0.02d;
    private static final long UNINITIALIZED = -1;
    private final CacheErrorLogger mCacheErrorLogger;
    private final CacheEventListener mCacheEventListener;
    private long mCacheSizeLastUpdateTime;
    private long mCacheSizeLimit;
    private final long mCacheSizeLimitMinimum;
    private final CacheStats mCacheStats;
    private final Clock mClock;
    private final CountDownLatch mCountDownLatch;
    private final long mDefaultCacheSizeLimit;
    private final EntryEvictionComparatorSupplier mEntryEvictionComparatorSupplier;
    private final boolean mIndexPopulateAtStartupEnabled;
    private boolean mIndexReady;
    private final Object mLock = new Object();
    private final long mLowDiskSpaceCacheSizeLimit;
    @VisibleForTesting
    @GuardedBy("mLock")
    final Set<String> mResourceIndex;
    private final StatFsHelper mStatFsHelper;
    private final DiskStorage mStorage;
    private static final Class<?> TAG = DiskStorageCache.class;
    private static final long FUTURE_TIMESTAMP_THRESHOLD_MS = TimeUnit.HOURS.toMillis(2);
    private static final long FILECACHE_SIZE_UPDATE_PERIOD_MS = TimeUnit.MINUTES.toMillis(30);

    /* JADX INFO: Access modifiers changed from: package-private */
    @VisibleForTesting
    /* loaded from: classes.dex */
    public static class CacheStats {
        private boolean mInitialized = false;
        private long mSize = -1;
        private long mCount = -1;

        CacheStats() {
        }

        public synchronized long getCount() {
            return this.mCount;
        }

        public synchronized long getSize() {
            return this.mSize;
        }

        public synchronized void increment(long j2, long j3) {
            if (this.mInitialized) {
                this.mSize += j2;
                this.mCount += j3;
            }
        }

        public synchronized boolean isInitialized() {
            return this.mInitialized;
        }

        public synchronized void reset() {
            this.mInitialized = false;
            this.mCount = -1L;
            this.mSize = -1L;
        }

        public synchronized void set(long j2, long j3) {
            this.mCount = j3;
            this.mSize = j2;
            this.mInitialized = true;
        }
    }

    /* loaded from: classes.dex */
    public static class Params {
        public final long mCacheSizeLimitMinimum;
        public final long mDefaultCacheSizeLimit;
        public final long mLowDiskSpaceCacheSizeLimit;

        public Params(long j2, long j3, long j4) {
            this.mCacheSizeLimitMinimum = j2;
            this.mLowDiskSpaceCacheSizeLimit = j3;
            this.mDefaultCacheSizeLimit = j4;
        }
    }

    public DiskStorageCache(DiskStorage diskStorage, EntryEvictionComparatorSupplier entryEvictionComparatorSupplier, Params params, CacheEventListener cacheEventListener, CacheErrorLogger cacheErrorLogger, @Nullable DiskTrimmableRegistry diskTrimmableRegistry, Executor executor, boolean z) {
        this.mLowDiskSpaceCacheSizeLimit = params.mLowDiskSpaceCacheSizeLimit;
        long j2 = params.mDefaultCacheSizeLimit;
        this.mDefaultCacheSizeLimit = j2;
        this.mCacheSizeLimit = j2;
        this.mStatFsHelper = StatFsHelper.getInstance();
        this.mStorage = diskStorage;
        this.mEntryEvictionComparatorSupplier = entryEvictionComparatorSupplier;
        this.mCacheSizeLastUpdateTime = -1L;
        this.mCacheEventListener = cacheEventListener;
        this.mCacheSizeLimitMinimum = params.mCacheSizeLimitMinimum;
        this.mCacheErrorLogger = cacheErrorLogger;
        this.mCacheStats = new CacheStats();
        this.mClock = SystemClock.get();
        this.mIndexPopulateAtStartupEnabled = z;
        this.mResourceIndex = new HashSet();
        if (diskTrimmableRegistry != null) {
            diskTrimmableRegistry.registerDiskTrimmable(this);
        }
        if (!z) {
            this.mCountDownLatch = new CountDownLatch(0);
            return;
        }
        this.mCountDownLatch = new CountDownLatch(1);
        executor.execute(new Runnable() { // from class: com.facebook.cache.disk.DiskStorageCache.1
            @Override // java.lang.Runnable
            public void run() {
                synchronized (DiskStorageCache.this.mLock) {
                    DiskStorageCache.this.maybeUpdateFileCacheSize();
                }
                DiskStorageCache.this.mIndexReady = true;
                DiskStorageCache.this.mCountDownLatch.countDown();
            }
        });
    }

    private BinaryResource endInsert(DiskStorage.Inserter inserter, CacheKey cacheKey, String str) {
        BinaryResource commit;
        synchronized (this.mLock) {
            commit = inserter.commit(cacheKey);
            this.mResourceIndex.add(str);
            this.mCacheStats.increment(commit.size(), 1L);
        }
        return commit;
    }

    @GuardedBy("mLock")
    private void evictAboveSize(long j2, CacheEventListener.EvictionReason evictionReason) {
        try {
            Collection<DiskStorage.Entry> sortedEntries = getSortedEntries(this.mStorage.getEntries());
            long size = this.mCacheStats.getSize();
            long j3 = size - j2;
            int i2 = 0;
            long j4 = 0;
            for (DiskStorage.Entry entry : sortedEntries) {
                if (j4 > j3) {
                    break;
                }
                long remove = this.mStorage.remove(entry);
                this.mResourceIndex.remove(entry.getId());
                if (remove > 0) {
                    i2++;
                    j4 += remove;
                    SettableCacheEvent cacheLimit = SettableCacheEvent.obtain().setResourceId(entry.getId()).setEvictionReason(evictionReason).setItemSize(remove).setCacheSize(size - j4).setCacheLimit(j2);
                    this.mCacheEventListener.onEviction(cacheLimit);
                    cacheLimit.recycle();
                }
            }
            this.mCacheStats.increment(-j4, -i2);
            this.mStorage.purgeUnexpectedResources();
        } catch (IOException e2) {
            this.mCacheErrorLogger.logError(CacheErrorLogger.CacheErrorCategory.EVICTION, TAG, "evictAboveSize: " + e2.getMessage(), e2);
            throw e2;
        }
    }

    private Collection<DiskStorage.Entry> getSortedEntries(Collection<DiskStorage.Entry> collection) {
        long now = this.mClock.now() + FUTURE_TIMESTAMP_THRESHOLD_MS;
        ArrayList arrayList = new ArrayList(collection.size());
        ArrayList arrayList2 = new ArrayList(collection.size());
        for (DiskStorage.Entry entry : collection) {
            if (entry.getTimestamp() > now) {
                arrayList.add(entry);
            } else {
                arrayList2.add(entry);
            }
        }
        Collections.sort(arrayList2, this.mEntryEvictionComparatorSupplier.get());
        arrayList.addAll(arrayList2);
        return arrayList;
    }

    private void maybeEvictFilesInCacheDir() {
        synchronized (this.mLock) {
            boolean maybeUpdateFileCacheSize = maybeUpdateFileCacheSize();
            updateFileCacheSizeLimit();
            long size = this.mCacheStats.getSize();
            if (size > this.mCacheSizeLimit && !maybeUpdateFileCacheSize) {
                this.mCacheStats.reset();
                maybeUpdateFileCacheSize();
            }
            long j2 = this.mCacheSizeLimit;
            if (size > j2) {
                evictAboveSize((j2 * 9) / 10, CacheEventListener.EvictionReason.CACHE_FULL);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    @GuardedBy("mLock")
    public boolean maybeUpdateFileCacheSize() {
        long now = this.mClock.now();
        if (this.mCacheStats.isInitialized()) {
            long j2 = this.mCacheSizeLastUpdateTime;
            if (j2 != -1 && now - j2 <= FILECACHE_SIZE_UPDATE_PERIOD_MS) {
                return false;
            }
        }
        return maybeUpdateFileCacheSizeAndIndex();
    }

    @GuardedBy("mLock")
    private boolean maybeUpdateFileCacheSizeAndIndex() {
        Set<String> set;
        long now = this.mClock.now();
        long j2 = FUTURE_TIMESTAMP_THRESHOLD_MS + now;
        Set<String> hashSet = (this.mIndexPopulateAtStartupEnabled && this.mResourceIndex.isEmpty()) ? this.mResourceIndex : this.mIndexPopulateAtStartupEnabled ? new HashSet<>() : null;
        try {
            long j3 = 0;
            long j4 = -1;
            int i2 = 0;
            boolean z = false;
            int i3 = 0;
            int i4 = 0;
            for (DiskStorage.Entry entry : this.mStorage.getEntries()) {
                i3++;
                j3 += entry.getSize();
                if (entry.getTimestamp() > j2) {
                    i4 = (int) (i4 + entry.getSize());
                    j4 = Math.max(entry.getTimestamp() - now, j4);
                    i2++;
                    z = true;
                } else if (this.mIndexPopulateAtStartupEnabled) {
                    hashSet.add(entry.getId());
                }
            }
            if (z) {
                this.mCacheErrorLogger.logError(CacheErrorLogger.CacheErrorCategory.READ_INVALID_ENTRY, TAG, "Future timestamp found in " + i2 + " files , with a total size of " + i4 + " bytes, and a maximum time delta of " + j4 + "ms", null);
            }
            long j5 = i3;
            if (this.mCacheStats.getCount() != j5 || this.mCacheStats.getSize() != j3) {
                if (this.mIndexPopulateAtStartupEnabled && (set = this.mResourceIndex) != hashSet) {
                    set.clear();
                    this.mResourceIndex.addAll(hashSet);
                }
                this.mCacheStats.set(j3, j5);
            }
            this.mCacheSizeLastUpdateTime = now;
            return true;
        } catch (IOException e2) {
            this.mCacheErrorLogger.logError(CacheErrorLogger.CacheErrorCategory.GENERIC_IO, TAG, "calcFileCacheSize: " + e2.getMessage(), e2);
            return false;
        }
    }

    private DiskStorage.Inserter startInsert(String str, CacheKey cacheKey) {
        maybeEvictFilesInCacheDir();
        return this.mStorage.insert(str, cacheKey);
    }

    private void trimBy(double d) {
        synchronized (this.mLock) {
            try {
                this.mCacheStats.reset();
                maybeUpdateFileCacheSize();
                long size = this.mCacheStats.getSize();
                double d2 = size;
                Double.isNaN(d2);
                evictAboveSize(size - ((long) (d * d2)), CacheEventListener.EvictionReason.CACHE_MANAGER_TRIMMED);
            } catch (IOException e2) {
                this.mCacheErrorLogger.logError(CacheErrorLogger.CacheErrorCategory.EVICTION, TAG, "trimBy: " + e2.getMessage(), e2);
            }
        }
    }

    @GuardedBy("mLock")
    private void updateFileCacheSizeLimit() {
        this.mCacheSizeLimit = this.mStatFsHelper.testLowDiskSpace(this.mStorage.isExternal() ? StatFsHelper.StorageType.EXTERNAL : StatFsHelper.StorageType.INTERNAL, this.mDefaultCacheSizeLimit - this.mCacheStats.getSize()) ? this.mLowDiskSpaceCacheSizeLimit : this.mDefaultCacheSizeLimit;
    }

    @VisibleForTesting
    protected void awaitIndex() {
        try {
            this.mCountDownLatch.await();
        } catch (InterruptedException unused) {
            FLog.e(TAG, "Memory Index is not ready yet. ");
        }
    }

    @Override // com.facebook.cache.disk.FileCache
    public void clearAll() {
        synchronized (this.mLock) {
            try {
                this.mStorage.clearAll();
                this.mResourceIndex.clear();
                this.mCacheEventListener.onCleared();
            } catch (IOException | NullPointerException e2) {
                this.mCacheErrorLogger.logError(CacheErrorLogger.CacheErrorCategory.EVICTION, TAG, "clearAll: " + e2.getMessage(), e2);
            }
            this.mCacheStats.reset();
        }
    }

    @Override // com.facebook.cache.disk.FileCache
    public long clearOldEntries(long j2) {
        long j3;
        long j4;
        synchronized (this.mLock) {
            try {
                long now = this.mClock.now();
                Collection<DiskStorage.Entry> entries = this.mStorage.getEntries();
                long size = this.mCacheStats.getSize();
                int i2 = 0;
                long j5 = 0;
                j4 = 0;
                for (DiskStorage.Entry entry : entries) {
                    try {
                        long j6 = now;
                        long max = Math.max(1L, Math.abs(now - entry.getTimestamp()));
                        if (max >= j2) {
                            long remove = this.mStorage.remove(entry);
                            this.mResourceIndex.remove(entry.getId());
                            if (remove > 0) {
                                i2++;
                                j5 += remove;
                                SettableCacheEvent cacheSize = SettableCacheEvent.obtain().setResourceId(entry.getId()).setEvictionReason(CacheEventListener.EvictionReason.CONTENT_STALE).setItemSize(remove).setCacheSize(size - j5);
                                this.mCacheEventListener.onEviction(cacheSize);
                                cacheSize.recycle();
                            }
                        } else {
                            j4 = Math.max(j4, max);
                        }
                        now = j6;
                    } catch (IOException e2) {
                        e = e2;
                        j3 = j4;
                        this.mCacheErrorLogger.logError(CacheErrorLogger.CacheErrorCategory.EVICTION, TAG, "clearOldEntries: " + e.getMessage(), e);
                        j4 = j3;
                        return j4;
                    }
                }
                this.mStorage.purgeUnexpectedResources();
                if (i2 > 0) {
                    maybeUpdateFileCacheSize();
                    this.mCacheStats.increment(-j5, -i2);
                }
            } catch (IOException e3) {
                e = e3;
                j3 = 0;
            }
        }
        return j4;
    }

    @Override // com.facebook.cache.disk.FileCache
    public long getCount() {
        return this.mCacheStats.getCount();
    }

    @Override // com.facebook.cache.disk.FileCache
    public DiskStorage.DiskDumpInfo getDumpInfo() {
        return this.mStorage.getDumpInfo();
    }

    @Override // com.facebook.cache.disk.FileCache
    @Nullable
    public BinaryResource getResource(CacheKey cacheKey) {
        BinaryResource binaryResource;
        SettableCacheEvent cacheKey2 = SettableCacheEvent.obtain().setCacheKey(cacheKey);
        try {
            synchronized (this.mLock) {
                List<String> resourceIds = CacheKeyUtil.getResourceIds(cacheKey);
                String str = null;
                binaryResource = null;
                for (int i2 = 0; i2 < resourceIds.size(); i2++) {
                    str = resourceIds.get(i2);
                    cacheKey2.setResourceId(str);
                    binaryResource = this.mStorage.getResource(str, cacheKey);
                    if (binaryResource != null) {
                        break;
                    }
                }
                if (binaryResource == null) {
                    this.mCacheEventListener.onMiss(cacheKey2);
                    this.mResourceIndex.remove(str);
                } else {
                    this.mCacheEventListener.onHit(cacheKey2);
                    this.mResourceIndex.add(str);
                }
            }
            return binaryResource;
        } catch (IOException e2) {
            this.mCacheErrorLogger.logError(CacheErrorLogger.CacheErrorCategory.GENERIC_IO, TAG, "getResource", e2);
            cacheKey2.setException(e2);
            this.mCacheEventListener.onReadException(cacheKey2);
            return null;
        } finally {
            cacheKey2.recycle();
        }
    }

    @Override // com.facebook.cache.disk.FileCache
    public long getSize() {
        return this.mCacheStats.getSize();
    }

    @Override // com.facebook.cache.disk.FileCache
    public boolean hasKey(CacheKey cacheKey) {
        synchronized (this.mLock) {
            if (hasKeySync(cacheKey)) {
                return true;
            }
            try {
                List<String> resourceIds = CacheKeyUtil.getResourceIds(cacheKey);
                for (int i2 = 0; i2 < resourceIds.size(); i2++) {
                    String str = resourceIds.get(i2);
                    if (this.mStorage.contains(str, cacheKey)) {
                        this.mResourceIndex.add(str);
                        return true;
                    }
                }
                return false;
            } catch (IOException unused) {
                return false;
            }
        }
    }

    @Override // com.facebook.cache.disk.FileCache
    public boolean hasKeySync(CacheKey cacheKey) {
        synchronized (this.mLock) {
            List<String> resourceIds = CacheKeyUtil.getResourceIds(cacheKey);
            for (int i2 = 0; i2 < resourceIds.size(); i2++) {
                if (this.mResourceIndex.contains(resourceIds.get(i2))) {
                    return true;
                }
            }
            return false;
        }
    }

    @Override // com.facebook.cache.disk.FileCache
    public BinaryResource insert(CacheKey cacheKey, WriterCallback writerCallback) {
        String firstResourceId;
        SettableCacheEvent cacheKey2 = SettableCacheEvent.obtain().setCacheKey(cacheKey);
        this.mCacheEventListener.onWriteAttempt(cacheKey2);
        synchronized (this.mLock) {
            firstResourceId = CacheKeyUtil.getFirstResourceId(cacheKey);
        }
        cacheKey2.setResourceId(firstResourceId);
        try {
            try {
                DiskStorage.Inserter startInsert = startInsert(firstResourceId, cacheKey);
                try {
                    startInsert.writeData(writerCallback, cacheKey);
                    BinaryResource endInsert = endInsert(startInsert, cacheKey, firstResourceId);
                    cacheKey2.setItemSize(endInsert.size()).setCacheSize(this.mCacheStats.getSize());
                    this.mCacheEventListener.onWriteSuccess(cacheKey2);
                    return endInsert;
                } finally {
                    if (!startInsert.cleanUp()) {
                        FLog.e(TAG, "Failed to delete temp file");
                    }
                }
            } catch (IOException e2) {
                cacheKey2.setException(e2);
                this.mCacheEventListener.onWriteException(cacheKey2);
                FLog.e(TAG, "Failed inserting a file into the cache", e2);
                throw e2;
            }
        } finally {
            cacheKey2.recycle();
        }
    }

    @Override // com.facebook.cache.disk.FileCache
    public boolean isEnabled() {
        return this.mStorage.isEnabled();
    }

    public boolean isIndexReady() {
        return this.mIndexReady || !this.mIndexPopulateAtStartupEnabled;
    }

    @Override // com.facebook.cache.disk.FileCache
    public boolean probe(CacheKey cacheKey) {
        String str;
        IOException e2;
        String str2 = null;
        try {
            try {
                synchronized (this.mLock) {
                    try {
                        List<String> resourceIds = CacheKeyUtil.getResourceIds(cacheKey);
                        int i2 = 0;
                        while (i2 < resourceIds.size()) {
                            String str3 = resourceIds.get(i2);
                            if (this.mStorage.touch(str3, cacheKey)) {
                                this.mResourceIndex.add(str3);
                                return true;
                            }
                            i2++;
                            str2 = str3;
                        }
                        return false;
                    } catch (Throwable th) {
                        str = str2;
                        th = th;
                        try {
                            throw th;
                        } catch (IOException e3) {
                            e2 = e3;
                            SettableCacheEvent exception = SettableCacheEvent.obtain().setCacheKey(cacheKey).setResourceId(str).setException(e2);
                            this.mCacheEventListener.onReadException(exception);
                            exception.recycle();
                            return false;
                        }
                    }
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (IOException e4) {
            str = null;
            e2 = e4;
        }
    }

    @Override // com.facebook.cache.disk.FileCache
    public void remove(CacheKey cacheKey) {
        synchronized (this.mLock) {
            try {
                List<String> resourceIds = CacheKeyUtil.getResourceIds(cacheKey);
                for (int i2 = 0; i2 < resourceIds.size(); i2++) {
                    String str = resourceIds.get(i2);
                    this.mStorage.remove(str);
                    this.mResourceIndex.remove(str);
                }
            } catch (IOException e2) {
                this.mCacheErrorLogger.logError(CacheErrorLogger.CacheErrorCategory.DELETE_FILE, TAG, "delete: " + e2.getMessage(), e2);
            }
        }
    }

    @Override // com.facebook.common.disk.DiskTrimmable
    public void trimToMinimum() {
        synchronized (this.mLock) {
            maybeUpdateFileCacheSize();
            long size = this.mCacheStats.getSize();
            long j2 = this.mCacheSizeLimitMinimum;
            if (j2 <= 0 || size <= 0 || size < j2) {
                return;
            }
            double d = j2;
            double d2 = size;
            Double.isNaN(d);
            Double.isNaN(d2);
            double d3 = 1.0d - (d / d2);
            if (d3 > TRIMMING_LOWER_BOUND) {
                trimBy(d3);
            }
        }
    }

    @Override // com.facebook.common.disk.DiskTrimmable
    public void trimToNothing() {
        clearAll();
    }
}

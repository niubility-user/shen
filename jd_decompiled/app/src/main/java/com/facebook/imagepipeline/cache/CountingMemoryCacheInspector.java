package com.facebook.imagepipeline.cache;

import android.graphics.Bitmap;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.references.CloseableReference;
import com.facebook.imagepipeline.cache.CountingMemoryCache;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public class CountingMemoryCacheInspector<K, V> {
    private final CountingMemoryCache<K, V> mCountingBitmapCache;

    /* loaded from: classes.dex */
    public static class DumpInfo<K, V> {
        public final int lruSize;
        public final int maxEntriesCount;
        public final int maxEntrySize;
        public final int maxSize;
        public final int size;
        public final List<DumpInfoEntry<K, V>> lruEntries = new ArrayList();
        public final List<DumpInfoEntry<K, V>> sharedEntries = new ArrayList();
        public final Map<Bitmap, Object> otherEntries = new HashMap();

        public DumpInfo(int i2, int i3, MemoryCacheParams memoryCacheParams) {
            this.maxSize = memoryCacheParams.maxCacheSize;
            this.maxEntriesCount = memoryCacheParams.maxCacheEntries;
            this.maxEntrySize = memoryCacheParams.maxCacheEntrySize;
            this.size = i2;
            this.lruSize = i3;
        }

        public void release() {
            Iterator<DumpInfoEntry<K, V>> it = this.lruEntries.iterator();
            while (it.hasNext()) {
                it.next().release();
            }
            Iterator<DumpInfoEntry<K, V>> it2 = this.sharedEntries.iterator();
            while (it2.hasNext()) {
                it2.next().release();
            }
        }
    }

    /* loaded from: classes.dex */
    public static class DumpInfoEntry<K, V> {
        public final K key;
        public final CloseableReference<V> value;

        public DumpInfoEntry(K k2, CloseableReference<V> closeableReference) {
            this.key = (K) Preconditions.checkNotNull(k2);
            this.value = CloseableReference.cloneOrNull(closeableReference);
        }

        public void release() {
            CloseableReference.closeSafely((CloseableReference<?>) this.value);
        }
    }

    public CountingMemoryCacheInspector(CountingMemoryCache<K, V> countingMemoryCache) {
        this.mCountingBitmapCache = countingMemoryCache;
    }

    public DumpInfo dumpCacheContent() {
        DumpInfo dumpInfo;
        synchronized (this.mCountingBitmapCache) {
            dumpInfo = new DumpInfo(this.mCountingBitmapCache.getSizeInBytes(), this.mCountingBitmapCache.getEvictionQueueSizeInBytes(), this.mCountingBitmapCache.mMemoryCacheParams);
            Iterator<Map.Entry<K, CountingMemoryCache.Entry<K, V>>> it = this.mCountingBitmapCache.mCachedEntries.getMatchingEntries(null).iterator();
            while (it.hasNext()) {
                CountingMemoryCache.Entry<K, V> value = it.next().getValue();
                (value.clientCount > 0 ? dumpInfo.sharedEntries : dumpInfo.lruEntries).add(new DumpInfoEntry<>(value.key, value.valueRef));
            }
            for (Map.Entry<Bitmap, Object> entry : this.mCountingBitmapCache.mOtherEntries.entrySet()) {
                if (entry != null && !entry.getKey().isRecycled()) {
                    dumpInfo.otherEntries.put(entry.getKey(), entry.getValue());
                }
            }
        }
        return dumpInfo;
    }
}

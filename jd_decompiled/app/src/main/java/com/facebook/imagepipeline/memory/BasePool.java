package com.facebook.imagepipeline.memory;

import android.annotation.SuppressLint;
import android.util.SparseArray;
import android.util.SparseIntArray;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.Sets;
import com.facebook.common.internal.Throwables;
import com.facebook.common.internal.VisibleForTesting;
import com.facebook.common.logging.FLog;
import com.facebook.common.memory.MemoryTrimType;
import com.facebook.common.memory.MemoryTrimmableRegistry;
import com.facebook.common.memory.Pool;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.NotThreadSafe;

/* loaded from: classes.dex */
public abstract class BasePool<V> implements Pool<V> {
    private final Class<?> TAG;
    private boolean mAllowNewBuckets;
    @VisibleForTesting
    final SparseArray<Bucket<V>> mBuckets;
    @VisibleForTesting
    @GuardedBy("this")
    final Counter mFree;
    private boolean mIgnoreHardCap;
    @VisibleForTesting
    final Set<V> mInUseValues;
    final MemoryTrimmableRegistry mMemoryTrimmableRegistry;
    final PoolParams mPoolParams;
    private final PoolStatsTracker mPoolStatsTracker;
    @VisibleForTesting
    @GuardedBy("this")
    final Counter mUsed;

    /* JADX INFO: Access modifiers changed from: package-private */
    @VisibleForTesting
    @NotThreadSafe
    /* loaded from: classes.dex */
    public static class Counter {
        private static final String TAG = "com.facebook.imagepipeline.memory.BasePool.Counter";
        int mCount;
        int mNumBytes;

        Counter() {
        }

        public void decrement(int i2) {
            int i3;
            int i4 = this.mNumBytes;
            if (i4 < i2 || (i3 = this.mCount) <= 0) {
                FLog.wtf(TAG, "Unexpected decrement of %d. Current numBytes = %d, count = %d", Integer.valueOf(i2), Integer.valueOf(this.mNumBytes), Integer.valueOf(this.mCount));
                return;
            }
            this.mCount = i3 - 1;
            this.mNumBytes = i4 - i2;
        }

        public void increment(int i2) {
            this.mCount++;
            this.mNumBytes += i2;
        }

        public void reset() {
            this.mCount = 0;
            this.mNumBytes = 0;
        }
    }

    /* loaded from: classes.dex */
    public static class InvalidSizeException extends RuntimeException {
        public InvalidSizeException(Object obj) {
            super("Invalid size: " + obj.toString());
        }
    }

    /* loaded from: classes.dex */
    public static class InvalidValueException extends RuntimeException {
        public InvalidValueException(Object obj) {
            super("Invalid value: " + obj.toString());
        }
    }

    /* loaded from: classes.dex */
    public static class PoolSizeViolationException extends RuntimeException {
        public PoolSizeViolationException(int i2, int i3, int i4, int i5) {
            super("Pool hard cap violation? Hard cap = " + i2 + " Used size = " + i3 + " Free size = " + i4 + " Request size = " + i5);
        }
    }

    /* loaded from: classes.dex */
    public static class SizeTooLargeException extends InvalidSizeException {
        public SizeTooLargeException(Object obj) {
            super(obj);
        }
    }

    public BasePool(MemoryTrimmableRegistry memoryTrimmableRegistry, PoolParams poolParams, PoolStatsTracker poolStatsTracker) {
        this.TAG = getClass();
        this.mMemoryTrimmableRegistry = (MemoryTrimmableRegistry) Preconditions.checkNotNull(memoryTrimmableRegistry);
        PoolParams poolParams2 = (PoolParams) Preconditions.checkNotNull(poolParams);
        this.mPoolParams = poolParams2;
        this.mPoolStatsTracker = (PoolStatsTracker) Preconditions.checkNotNull(poolStatsTracker);
        this.mBuckets = new SparseArray<>();
        if (poolParams2.fixBucketsReinitialization) {
            initBuckets();
        } else {
            legacyInitBuckets(new SparseIntArray(0));
        }
        this.mInUseValues = Sets.newIdentityHashSet();
        this.mFree = new Counter();
        this.mUsed = new Counter();
    }

    public BasePool(MemoryTrimmableRegistry memoryTrimmableRegistry, PoolParams poolParams, PoolStatsTracker poolStatsTracker, boolean z) {
        this(memoryTrimmableRegistry, poolParams, poolStatsTracker);
        this.mIgnoreHardCap = z;
    }

    private synchronized void ensurePoolSizeInvariant() {
        boolean z;
        if (isMaxSizeSoftCapExceeded() && this.mFree.mNumBytes != 0) {
            z = false;
            Preconditions.checkState(z);
        }
        z = true;
        Preconditions.checkState(z);
    }

    private void fillBuckets(SparseIntArray sparseIntArray) {
        this.mBuckets.clear();
        for (int i2 = 0; i2 < sparseIntArray.size(); i2++) {
            int keyAt = sparseIntArray.keyAt(i2);
            this.mBuckets.put(keyAt, new Bucket<>(getSizeInBytes(keyAt), sparseIntArray.valueAt(i2), 0, this.mPoolParams.fixBucketsReinitialization));
        }
    }

    private synchronized Bucket<V> getBucketIfPresent(int i2) {
        return this.mBuckets.get(i2);
    }

    private synchronized void initBuckets() {
        SparseIntArray sparseIntArray = this.mPoolParams.bucketSizes;
        if (sparseIntArray != null) {
            fillBuckets(sparseIntArray);
            this.mAllowNewBuckets = false;
        } else {
            this.mAllowNewBuckets = true;
        }
    }

    private synchronized void legacyInitBuckets(SparseIntArray sparseIntArray) {
        Preconditions.checkNotNull(sparseIntArray);
        this.mBuckets.clear();
        SparseIntArray sparseIntArray2 = this.mPoolParams.bucketSizes;
        if (sparseIntArray2 != null) {
            for (int i2 = 0; i2 < sparseIntArray2.size(); i2++) {
                int keyAt = sparseIntArray2.keyAt(i2);
                this.mBuckets.put(keyAt, new Bucket<>(getSizeInBytes(keyAt), sparseIntArray2.valueAt(i2), sparseIntArray.get(keyAt, 0), this.mPoolParams.fixBucketsReinitialization));
            }
            this.mAllowNewBuckets = false;
        } else {
            this.mAllowNewBuckets = true;
        }
    }

    @SuppressLint({"InvalidAccessToGuardedField"})
    private void logStats() {
        if (FLog.isLoggable(2)) {
            FLog.v(this.TAG, "Used = (%d, %d); Free = (%d, %d)", Integer.valueOf(this.mUsed.mCount), Integer.valueOf(this.mUsed.mNumBytes), Integer.valueOf(this.mFree.mCount), Integer.valueOf(this.mFree.mNumBytes));
        }
    }

    private List<Bucket<V>> refillBuckets() {
        ArrayList arrayList = new ArrayList(this.mBuckets.size());
        int size = this.mBuckets.size();
        for (int i2 = 0; i2 < size; i2++) {
            Bucket<V> valueAt = this.mBuckets.valueAt(i2);
            int i3 = valueAt.mItemSize;
            int i4 = valueAt.mMaxLength;
            int inUseCount = valueAt.getInUseCount();
            if (valueAt.getFreeListSize() > 0) {
                arrayList.add(valueAt);
            }
            this.mBuckets.setValueAt(i2, new Bucket<>(getSizeInBytes(i3), i4, inUseCount, this.mPoolParams.fixBucketsReinitialization));
        }
        return arrayList;
    }

    protected abstract V alloc(int i2);

    @VisibleForTesting
    synchronized boolean canAllocate(int i2) {
        if (this.mIgnoreHardCap) {
            return true;
        }
        PoolParams poolParams = this.mPoolParams;
        int i3 = poolParams.maxSizeHardCap;
        int i4 = this.mUsed.mNumBytes;
        if (i2 > i3 - i4) {
            this.mPoolStatsTracker.onHardCapReached();
            return false;
        }
        int i5 = poolParams.maxSizeSoftCap;
        if (i2 > i5 - (i4 + this.mFree.mNumBytes)) {
            trimToSize(i5 - i2);
        }
        if (i2 > i3 - (this.mUsed.mNumBytes + this.mFree.mNumBytes)) {
            this.mPoolStatsTracker.onHardCapReached();
            return false;
        }
        return true;
    }

    @VisibleForTesting
    protected abstract void free(V v);

    @Override // com.facebook.common.memory.Pool
    public V get(int i2) {
        V value;
        ensurePoolSizeInvariant();
        int bucketedSize = getBucketedSize(i2);
        synchronized (this) {
            Bucket<V> bucket = getBucket(bucketedSize);
            if (bucket != null && (value = getValue(bucket)) != null) {
                Preconditions.checkState(this.mInUseValues.add(value));
                int bucketedSizeForValue = getBucketedSizeForValue(value);
                int sizeInBytes = getSizeInBytes(bucketedSizeForValue);
                this.mUsed.increment(sizeInBytes);
                this.mFree.decrement(sizeInBytes);
                this.mPoolStatsTracker.onValueReuse(sizeInBytes);
                logStats();
                if (FLog.isLoggable(2)) {
                    FLog.v(this.TAG, "get (reuse) (object, size) = (%x, %s)", Integer.valueOf(System.identityHashCode(value)), Integer.valueOf(bucketedSizeForValue));
                }
                return value;
            }
            int sizeInBytes2 = getSizeInBytes(bucketedSize);
            if (canAllocate(sizeInBytes2)) {
                this.mUsed.increment(sizeInBytes2);
                if (bucket != null) {
                    bucket.incrementInUseCount();
                }
                V v = null;
                try {
                    v = alloc(bucketedSize);
                } catch (Throwable th) {
                    synchronized (this) {
                        this.mUsed.decrement(sizeInBytes2);
                        Bucket<V> bucket2 = getBucket(bucketedSize);
                        if (bucket2 != null) {
                            bucket2.decrementInUseCount();
                        }
                        Throwables.propagateIfPossible(th);
                    }
                }
                synchronized (this) {
                    Preconditions.checkState(this.mInUseValues.add(v));
                    trimToSoftCap();
                    this.mPoolStatsTracker.onAlloc(sizeInBytes2);
                    logStats();
                    if (FLog.isLoggable(2)) {
                        FLog.v(this.TAG, "get (alloc) (object, size) = (%x, %s)", Integer.valueOf(System.identityHashCode(v)), Integer.valueOf(bucketedSize));
                    }
                }
                return v;
            }
            throw new PoolSizeViolationException(this.mPoolParams.maxSizeHardCap, this.mUsed.mNumBytes, this.mFree.mNumBytes, sizeInBytes2);
        }
    }

    @VisibleForTesting
    synchronized Bucket<V> getBucket(int i2) {
        Bucket<V> bucket = this.mBuckets.get(i2);
        if (bucket == null && this.mAllowNewBuckets) {
            if (FLog.isLoggable(2)) {
                FLog.v(this.TAG, "creating new bucket %s", Integer.valueOf(i2));
            }
            Bucket<V> newBucket = newBucket(i2);
            this.mBuckets.put(i2, newBucket);
            return newBucket;
        }
        return bucket;
    }

    protected abstract int getBucketedSize(int i2);

    protected abstract int getBucketedSizeForValue(V v);

    protected abstract int getSizeInBytes(int i2);

    public synchronized Map<String, Integer> getStats() {
        HashMap hashMap;
        hashMap = new HashMap();
        for (int i2 = 0; i2 < this.mBuckets.size(); i2++) {
            hashMap.put(PoolStatsTracker.BUCKETS_USED_PREFIX + getSizeInBytes(this.mBuckets.keyAt(i2)), Integer.valueOf(this.mBuckets.valueAt(i2).getInUseCount()));
        }
        hashMap.put(PoolStatsTracker.SOFT_CAP, Integer.valueOf(this.mPoolParams.maxSizeSoftCap));
        hashMap.put(PoolStatsTracker.HARD_CAP, Integer.valueOf(this.mPoolParams.maxSizeHardCap));
        hashMap.put(PoolStatsTracker.USED_COUNT, Integer.valueOf(this.mUsed.mCount));
        hashMap.put(PoolStatsTracker.USED_BYTES, Integer.valueOf(this.mUsed.mNumBytes));
        hashMap.put(PoolStatsTracker.FREE_COUNT, Integer.valueOf(this.mFree.mCount));
        hashMap.put(PoolStatsTracker.FREE_BYTES, Integer.valueOf(this.mFree.mNumBytes));
        return hashMap;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Nullable
    public synchronized V getValue(Bucket<V> bucket) {
        return bucket.get();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void initialize() {
        this.mMemoryTrimmableRegistry.registerMemoryTrimmable(this);
        this.mPoolStatsTracker.setBasePool(this);
    }

    @VisibleForTesting
    synchronized boolean isMaxSizeSoftCapExceeded() {
        boolean z;
        z = this.mUsed.mNumBytes + this.mFree.mNumBytes > this.mPoolParams.maxSizeSoftCap;
        if (z) {
            this.mPoolStatsTracker.onSoftCapReached();
        }
        return z;
    }

    protected boolean isReusable(V v) {
        Preconditions.checkNotNull(v);
        return true;
    }

    Bucket<V> newBucket(int i2) {
        return new Bucket<>(getSizeInBytes(i2), Integer.MAX_VALUE, 0, this.mPoolParams.fixBucketsReinitialization);
    }

    protected void onParamsChanged() {
    }

    /* JADX WARN: Code restructure failed: missing block: B:24:0x007f, code lost:
        r2.decrementInUseCount();
     */
    @Override // com.facebook.common.memory.Pool, com.facebook.common.references.ResourceReleaser
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void release(V r8) {
        /*
            r7 = this;
            com.facebook.common.internal.Preconditions.checkNotNull(r8)
            int r0 = r7.getBucketedSizeForValue(r8)
            int r1 = r7.getSizeInBytes(r0)
            monitor-enter(r7)
            com.facebook.imagepipeline.memory.Bucket r2 = r7.getBucketIfPresent(r0)     // Catch: java.lang.Throwable -> Lad
            java.util.Set<V> r3 = r7.mInUseValues     // Catch: java.lang.Throwable -> Lad
            boolean r3 = r3.remove(r8)     // Catch: java.lang.Throwable -> Lad
            r4 = 2
            if (r3 != 0) goto L3b
            java.lang.Class<?> r2 = r7.TAG     // Catch: java.lang.Throwable -> Lad
            java.lang.String r3 = "release (free, value unrecognized) (object, size) = (%x, %s)"
            java.lang.Object[] r4 = new java.lang.Object[r4]     // Catch: java.lang.Throwable -> Lad
            r5 = 0
            int r6 = java.lang.System.identityHashCode(r8)     // Catch: java.lang.Throwable -> Lad
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)     // Catch: java.lang.Throwable -> Lad
            r4[r5] = r6     // Catch: java.lang.Throwable -> Lad
            r5 = 1
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)     // Catch: java.lang.Throwable -> Lad
            r4[r5] = r0     // Catch: java.lang.Throwable -> Lad
            com.facebook.common.logging.FLog.e(r2, r3, r4)     // Catch: java.lang.Throwable -> Lad
            r7.free(r8)     // Catch: java.lang.Throwable -> Lad
        L38:
            com.facebook.imagepipeline.memory.PoolStatsTracker r8 = r7.mPoolStatsTracker     // Catch: java.lang.Throwable -> Lad
            goto La5
        L3b:
            if (r2 == 0) goto L7d
            boolean r3 = r2.isMaxLengthExceeded()     // Catch: java.lang.Throwable -> Lad
            if (r3 != 0) goto L7d
            boolean r3 = r7.isMaxSizeSoftCapExceeded()     // Catch: java.lang.Throwable -> Lad
            if (r3 != 0) goto L7d
            boolean r3 = r7.isReusable(r8)     // Catch: java.lang.Throwable -> Lad
            if (r3 != 0) goto L50
            goto L7d
        L50:
            r2.release(r8)     // Catch: java.lang.Throwable -> Lad
            com.facebook.imagepipeline.memory.BasePool$Counter r2 = r7.mFree     // Catch: java.lang.Throwable -> Lad
            r2.increment(r1)     // Catch: java.lang.Throwable -> Lad
            com.facebook.imagepipeline.memory.BasePool$Counter r2 = r7.mUsed     // Catch: java.lang.Throwable -> Lad
            r2.decrement(r1)     // Catch: java.lang.Throwable -> Lad
            com.facebook.imagepipeline.memory.PoolStatsTracker r2 = r7.mPoolStatsTracker     // Catch: java.lang.Throwable -> Lad
            r2.onValueRelease(r1)     // Catch: java.lang.Throwable -> Lad
            boolean r1 = com.facebook.common.logging.FLog.isLoggable(r4)     // Catch: java.lang.Throwable -> Lad
            if (r1 == 0) goto La8
            java.lang.Class<?> r1 = r7.TAG     // Catch: java.lang.Throwable -> Lad
            java.lang.String r2 = "release (reuse) (object, size) = (%x, %s)"
            int r8 = java.lang.System.identityHashCode(r8)     // Catch: java.lang.Throwable -> Lad
            java.lang.Integer r8 = java.lang.Integer.valueOf(r8)     // Catch: java.lang.Throwable -> Lad
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)     // Catch: java.lang.Throwable -> Lad
            com.facebook.common.logging.FLog.v(r1, r2, r8, r0)     // Catch: java.lang.Throwable -> Lad
            goto La8
        L7d:
            if (r2 == 0) goto L82
            r2.decrementInUseCount()     // Catch: java.lang.Throwable -> Lad
        L82:
            boolean r2 = com.facebook.common.logging.FLog.isLoggable(r4)     // Catch: java.lang.Throwable -> Lad
            if (r2 == 0) goto L9c
            java.lang.Class<?> r2 = r7.TAG     // Catch: java.lang.Throwable -> Lad
            java.lang.String r3 = "release (free) (object, size) = (%x, %s)"
            int r4 = java.lang.System.identityHashCode(r8)     // Catch: java.lang.Throwable -> Lad
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)     // Catch: java.lang.Throwable -> Lad
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)     // Catch: java.lang.Throwable -> Lad
            com.facebook.common.logging.FLog.v(r2, r3, r4, r0)     // Catch: java.lang.Throwable -> Lad
        L9c:
            r7.free(r8)     // Catch: java.lang.Throwable -> Lad
            com.facebook.imagepipeline.memory.BasePool$Counter r8 = r7.mUsed     // Catch: java.lang.Throwable -> Lad
            r8.decrement(r1)     // Catch: java.lang.Throwable -> Lad
            goto L38
        La5:
            r8.onFree(r1)     // Catch: java.lang.Throwable -> Lad
        La8:
            r7.logStats()     // Catch: java.lang.Throwable -> Lad
            monitor-exit(r7)     // Catch: java.lang.Throwable -> Lad
            return
        Lad:
            r8 = move-exception
            monitor-exit(r7)     // Catch: java.lang.Throwable -> Lad
            goto Lb1
        Lb0:
            throw r8
        Lb1:
            goto Lb0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.imagepipeline.memory.BasePool.release(java.lang.Object):void");
    }

    @Override // com.facebook.common.memory.MemoryTrimmable
    public void trim(MemoryTrimType memoryTrimType) {
        trimToNothing();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @VisibleForTesting
    void trimToNothing() {
        int i2;
        List arrayList;
        synchronized (this) {
            if (this.mPoolParams.fixBucketsReinitialization) {
                arrayList = refillBuckets();
            } else {
                arrayList = new ArrayList(this.mBuckets.size());
                SparseIntArray sparseIntArray = new SparseIntArray();
                for (int i3 = 0; i3 < this.mBuckets.size(); i3++) {
                    Bucket<V> valueAt = this.mBuckets.valueAt(i3);
                    if (valueAt.getFreeListSize() > 0) {
                        arrayList.add(valueAt);
                    }
                    sparseIntArray.put(this.mBuckets.keyAt(i3), valueAt.getInUseCount());
                }
                legacyInitBuckets(sparseIntArray);
            }
            this.mFree.reset();
            logStats();
        }
        onParamsChanged();
        for (i2 = 0; i2 < arrayList.size(); i2++) {
            Bucket bucket = (Bucket) arrayList.get(i2);
            while (true) {
                Object pop = bucket.pop();
                if (pop == null) {
                    break;
                }
                free(pop);
            }
        }
    }

    @VisibleForTesting
    synchronized void trimToSize(int i2) {
        int i3 = this.mUsed.mNumBytes;
        int i4 = this.mFree.mNumBytes;
        int min = Math.min((i3 + i4) - i2, i4);
        if (min <= 0) {
            return;
        }
        if (FLog.isLoggable(2)) {
            FLog.v(this.TAG, "trimToSize: TargetSize = %d; Initial Size = %d; Bytes to free = %d", Integer.valueOf(i2), Integer.valueOf(this.mUsed.mNumBytes + this.mFree.mNumBytes), Integer.valueOf(min));
        }
        logStats();
        for (int i5 = 0; i5 < this.mBuckets.size() && min > 0; i5++) {
            Bucket<V> valueAt = this.mBuckets.valueAt(i5);
            while (min > 0) {
                V pop = valueAt.pop();
                if (pop == null) {
                    break;
                }
                free(pop);
                int i6 = valueAt.mItemSize;
                min -= i6;
                this.mFree.decrement(i6);
            }
        }
        logStats();
        if (FLog.isLoggable(2)) {
            FLog.v(this.TAG, "trimToSize: TargetSize = %d; Final Size = %d", Integer.valueOf(i2), Integer.valueOf(this.mUsed.mNumBytes + this.mFree.mNumBytes));
        }
    }

    @VisibleForTesting
    synchronized void trimToSoftCap() {
        if (isMaxSizeSoftCapExceeded()) {
            trimToSize(this.mPoolParams.maxSizeSoftCap);
        }
    }
}

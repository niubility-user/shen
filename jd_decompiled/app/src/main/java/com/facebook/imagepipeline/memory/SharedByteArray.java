package com.facebook.imagepipeline.memory;

import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.Throwables;
import com.facebook.common.internal.VisibleForTesting;
import com.facebook.common.memory.MemoryTrimType;
import com.facebook.common.memory.MemoryTrimmable;
import com.facebook.common.memory.MemoryTrimmableRegistry;
import com.facebook.common.references.CloseableReference;
import com.facebook.common.references.OOMSoftReference;
import com.facebook.common.references.ResourceReleaser;
import java.util.concurrent.Semaphore;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
/* loaded from: classes.dex */
public class SharedByteArray implements MemoryTrimmable {
    @VisibleForTesting
    final OOMSoftReference<byte[]> mByteArraySoftRef;
    @VisibleForTesting
    final int mMaxByteArraySize;
    @VisibleForTesting
    final int mMinByteArraySize;
    private final ResourceReleaser<byte[]> mResourceReleaser;
    @VisibleForTesting
    final Semaphore mSemaphore;

    public SharedByteArray(MemoryTrimmableRegistry memoryTrimmableRegistry, PoolParams poolParams) {
        Preconditions.checkNotNull(memoryTrimmableRegistry);
        Preconditions.checkArgument(poolParams.minBucketSize > 0);
        Preconditions.checkArgument(poolParams.maxBucketSize >= poolParams.minBucketSize);
        this.mMaxByteArraySize = poolParams.maxBucketSize;
        this.mMinByteArraySize = poolParams.minBucketSize;
        this.mByteArraySoftRef = new OOMSoftReference<>();
        this.mSemaphore = new Semaphore(1);
        this.mResourceReleaser = new ResourceReleaser<byte[]>() { // from class: com.facebook.imagepipeline.memory.SharedByteArray.1
            @Override // com.facebook.common.references.ResourceReleaser
            public void release(byte[] bArr) {
                SharedByteArray.this.mSemaphore.release();
            }
        };
        memoryTrimmableRegistry.registerMemoryTrimmable(this);
    }

    private synchronized byte[] allocateByteArray(int i2) {
        byte[] bArr;
        this.mByteArraySoftRef.clear();
        bArr = new byte[i2];
        this.mByteArraySoftRef.set(bArr);
        return bArr;
    }

    private byte[] getByteArray(int i2) {
        int bucketedSize = getBucketedSize(i2);
        byte[] bArr = this.mByteArraySoftRef.get();
        return (bArr == null || bArr.length < bucketedSize) ? allocateByteArray(bucketedSize) : bArr;
    }

    public CloseableReference<byte[]> get(int i2) {
        Preconditions.checkArgument(i2 > 0, "Size must be greater than zero");
        Preconditions.checkArgument(i2 <= this.mMaxByteArraySize, "Requested size is too big");
        this.mSemaphore.acquireUninterruptibly();
        try {
            return CloseableReference.of(getByteArray(i2), this.mResourceReleaser);
        } catch (Throwable th) {
            this.mSemaphore.release();
            throw Throwables.propagate(th);
        }
    }

    @VisibleForTesting
    int getBucketedSize(int i2) {
        return Integer.highestOneBit(Math.max(i2, this.mMinByteArraySize) - 1) * 2;
    }

    @Override // com.facebook.common.memory.MemoryTrimmable
    public void trim(MemoryTrimType memoryTrimType) {
        if (this.mSemaphore.tryAcquire()) {
            try {
                this.mByteArraySoftRef.clear();
            } finally {
                this.mSemaphore.release();
            }
        }
    }
}

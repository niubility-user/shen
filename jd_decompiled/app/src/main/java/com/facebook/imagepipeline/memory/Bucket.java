package com.facebook.imagepipeline.memory;

import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.VisibleForTesting;
import com.facebook.common.logging.FLog;
import java.util.LinkedList;
import java.util.Queue;
import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;

@VisibleForTesting
@NotThreadSafe
/* loaded from: classes.dex */
class Bucket<V> {
    private static final String TAG = "BUCKET";
    private final boolean mFixBucketsReinitialization;
    final Queue mFreeList;
    private int mInUseLength;
    public final int mItemSize;
    public final int mMaxLength;

    public Bucket(int i2, int i3, int i4, boolean z) {
        Preconditions.checkState(i2 > 0);
        Preconditions.checkState(i3 >= 0);
        Preconditions.checkState(i4 >= 0);
        this.mItemSize = i2;
        this.mMaxLength = i3;
        this.mFreeList = new LinkedList();
        this.mInUseLength = i4;
        this.mFixBucketsReinitialization = z;
    }

    void addToFreeList(V v) {
        this.mFreeList.add(v);
    }

    public void decrementInUseCount() {
        Preconditions.checkState(this.mInUseLength > 0);
        this.mInUseLength--;
    }

    @Nullable
    @Deprecated
    public V get() {
        V pop = pop();
        if (pop != null) {
            this.mInUseLength++;
        }
        return pop;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getFreeListSize() {
        return this.mFreeList.size();
    }

    public int getInUseCount() {
        return this.mInUseLength;
    }

    public void incrementInUseCount() {
        this.mInUseLength++;
    }

    public boolean isMaxLengthExceeded() {
        return this.mInUseLength + getFreeListSize() > this.mMaxLength;
    }

    @Nullable
    public V pop() {
        return (V) this.mFreeList.poll();
    }

    public void release(V v) {
        int i2;
        Preconditions.checkNotNull(v);
        if (this.mFixBucketsReinitialization) {
            Preconditions.checkState(this.mInUseLength > 0);
            i2 = this.mInUseLength;
        } else {
            i2 = this.mInUseLength;
            if (i2 <= 0) {
                FLog.e(TAG, "Tried to release value %s from an empty bucket!", v);
                return;
            }
        }
        this.mInUseLength = i2 - 1;
        addToFreeList(v);
    }
}

package com.facebook.react.common;

import androidx.core.util.Pools;

/* loaded from: classes.dex */
public class ClearableSynchronizedPool<T> implements Pools.Pool<T> {
    private final Object[] mPool;
    private int mSize = 0;

    public ClearableSynchronizedPool(int i2) {
        this.mPool = new Object[i2];
    }

    @Override // androidx.core.util.Pools.Pool
    public synchronized T acquire() {
        int i2 = this.mSize;
        if (i2 == 0) {
            return null;
        }
        int i3 = i2 - 1;
        this.mSize = i3;
        Object[] objArr = this.mPool;
        T t = (T) objArr[i3];
        objArr[i3] = null;
        return t;
    }

    public synchronized void clear() {
        for (int i2 = 0; i2 < this.mSize; i2++) {
            this.mPool[i2] = null;
        }
        this.mSize = 0;
    }

    @Override // androidx.core.util.Pools.Pool
    public synchronized boolean release(T t) {
        int i2 = this.mSize;
        Object[] objArr = this.mPool;
        if (i2 == objArr.length) {
            return false;
        }
        objArr[i2] = t;
        this.mSize = i2 + 1;
        return true;
    }
}

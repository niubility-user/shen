package com.jingdong.sdk.threadpool.common;

import java.util.concurrent.Future;

/* loaded from: classes.dex */
public class Disposable<T> {
    private Future<T> mFuture;

    public Disposable() {
    }

    public void dispose() {
        Future<T> future = this.mFuture;
        if (future != null) {
            future.cancel(true);
        }
    }

    public Future<T> getFuture() {
        return this.mFuture;
    }

    public void setFuture(Future<T> future) {
        this.mFuture = future;
    }

    public Disposable(Future<T> future) {
        this.mFuture = future;
    }
}

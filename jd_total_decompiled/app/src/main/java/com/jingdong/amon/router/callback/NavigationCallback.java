package com.jingdong.amon.router.callback;

/* loaded from: classes18.dex */
public interface NavigationCallback {

    /* loaded from: classes18.dex */
    public interface OnCompleteCallback<T> {
        void onComplete(T t);
    }

    /* loaded from: classes18.dex */
    public interface OnErrorCallback<T> {
        void onError(T t, Exception exc);
    }

    /* loaded from: classes18.dex */
    public interface OnFoundCallback<T> {
        void onFound(T t);
    }

    /* loaded from: classes18.dex */
    public interface OnInterruptCallback<T> {
        void onInterrupt(T t);
    }

    /* loaded from: classes18.dex */
    public interface OnLostCallBack<T> {
        void onLost(T t);
    }
}

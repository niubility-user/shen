package com.jingdong.sdk.baseinfo;

/* loaded from: classes.dex */
public interface ResultCallback<T> {
    void onFailed(Throwable th);

    void onSucceed(T t);
}

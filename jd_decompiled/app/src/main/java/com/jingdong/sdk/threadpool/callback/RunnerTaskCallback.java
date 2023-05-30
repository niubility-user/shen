package com.jingdong.sdk.threadpool.callback;

/* loaded from: classes10.dex */
public interface RunnerTaskCallback<T> {
    void onFailed(String str, Throwable th);

    void onStart(String str);

    void onSuccess(String str, T t);
}

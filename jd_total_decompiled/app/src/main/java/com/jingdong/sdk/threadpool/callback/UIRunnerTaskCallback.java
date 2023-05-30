package com.jingdong.sdk.threadpool.callback;

/* loaded from: classes10.dex */
public abstract class UIRunnerTaskCallback<T> implements RunnerTaskCallback<T>, a {
    @Override // com.jingdong.sdk.threadpool.callback.RunnerTaskCallback
    public void onFailed(String str, Throwable th) {
    }

    @Override // com.jingdong.sdk.threadpool.callback.RunnerTaskCallback
    public void onStart(String str) {
    }

    @Override // com.jingdong.sdk.threadpool.callback.RunnerTaskCallback
    public void onSuccess(String str, T t) {
    }
}

package com.jingdong.aura.sdk.update.downloader;

/* loaded from: classes4.dex */
public interface IDownloadCallback {
    void onCanceled();

    void onError(Throwable th);

    void onFinish();

    void onProgress(long j2);

    void onStart();
}

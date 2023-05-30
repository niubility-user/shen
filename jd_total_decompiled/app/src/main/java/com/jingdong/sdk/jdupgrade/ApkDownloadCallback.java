package com.jingdong.sdk.jdupgrade;

/* loaded from: classes7.dex */
public interface ApkDownloadCallback {
    void onError();

    void onProgress(int i2, long j2, long j3);

    void onStart();

    void onSuccess(String str);
}

package com.jingdong.aura.provided.api;

/* loaded from: classes4.dex */
public interface IBundleDownloadListener {
    void onDownloadFailure(Exception exc);

    void onDownloadFinish();

    void onDownloadProgressChanged(int i2, int i3);

    void onInstallFinish(boolean z);

    void onInstallStart();

    void onPause(boolean z);

    void onStart();
}

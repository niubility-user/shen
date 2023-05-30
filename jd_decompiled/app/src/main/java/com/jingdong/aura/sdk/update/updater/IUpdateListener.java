package com.jingdong.aura.sdk.update.updater;

import com.jingdong.aura.sdk.update.AuraBundleResult;

/* loaded from: classes4.dex */
public interface IUpdateListener {
    void onDownloadFailure(Exception exc);

    void onDownloadFinish(AuraBundleResult auraBundleResult);

    void onDownloadPause(boolean z);

    void onDownloadProgress(long j2, long j3);

    void onDownloadStart();

    void onInstallFinish(boolean z);

    void onInstallStart();
}

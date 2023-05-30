package com.jingdong.sdk.jdupgrade;

/* loaded from: classes7.dex */
public interface VersionInfoCallback {
    void onError();

    void onStart();

    void onSuccess(VersionInfo versionInfo);
}

package com.jingdong.service.callback;

/* loaded from: classes10.dex */
public interface PermissionListener {
    void onCanceled();

    void onDenied();

    void onGranted();

    void onIgnored();

    void onOpenSetting();
}

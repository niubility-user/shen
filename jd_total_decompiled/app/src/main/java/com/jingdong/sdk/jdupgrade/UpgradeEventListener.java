package com.jingdong.sdk.jdupgrade;

/* loaded from: classes7.dex */
public interface UpgradeEventListener {
    void onCloseRemindDialog(RemindType remindType, UpgradeType upgradeType);

    void onDownloadFinish(boolean z);

    void onDownloadStart(boolean z);

    void onMessage(String str, String str2);

    void onShowRemindDialog(RemindType remindType, UpgradeType upgradeType);
}

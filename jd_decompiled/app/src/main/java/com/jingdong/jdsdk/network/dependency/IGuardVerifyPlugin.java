package com.jingdong.jdsdk.network.dependency;

import android.app.Activity;

/* loaded from: classes.dex */
public interface IGuardVerifyPlugin {
    String getLmtValue();

    String getVerifyToken();

    boolean isVerifyWindowShowing();

    void onActivityDestroyed(Activity activity);

    boolean onLineSwitchOpen();

    void onLogout();

    void onTriggerVerifyCheck(String str, String str2, String str3);
}

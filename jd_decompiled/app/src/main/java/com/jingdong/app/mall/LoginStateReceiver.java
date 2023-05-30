package com.jingdong.app.mall;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.jingdong.app.mall.bundle.mobileConfig.JDMobileConfig;
import com.jingdong.common.utils.RtcSdkConfig;
import com.jingdong.jdsdk.login.LoginUserHelper;
import com.jingdong.sdk.jdcrashreport.JdCrashReport;

/* loaded from: classes19.dex */
public class LoginStateReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (TextUtils.isEmpty(action)) {
            return;
        }
        if (action.equals("com.jingdong.action.user.login.out")) {
            com.jingdong.app.mall.utils.d.m(false);
            com.jingdong.app.mall.utils.d.t();
            JdCrashReport.updateUserId("");
            JDMobileConfig.getInstance().updateUserId("");
            RtcSdkConfig.getInstance().unInit();
        } else if (action.equals("com.jingdong.action.user.login.in")) {
            com.jingdong.app.mall.utils.d.m(true);
            JdCrashReport.updateUserId(LoginUserHelper.getInstance().getLoginUser().getLoginUserName());
            JDMobileConfig.getInstance().updateUserId(LoginUserHelper.getInstance().getLoginUser().getLoginUserName());
            RtcSdkConfig.getInstance().update();
        }
    }
}

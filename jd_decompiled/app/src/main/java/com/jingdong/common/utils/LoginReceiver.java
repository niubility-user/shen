package com.jingdong.common.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.jingdong.common.login.LoginConstans;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes6.dex */
public class LoginReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (TextUtils.isEmpty(action) || !action.equals("com.jingdong.action.user.login.in")) {
            return;
        }
        if (intent.getIntExtra("type", 0) == 0) {
            FireEyeUtils.reportFireEyeEvent(LoginConstans.FREGMENT_LOGIN_FLAG, FireEyeUtils.isFromOpenAPP);
        } else {
            FireEyeUtils.reportFireEyeEvent("register", FireEyeUtils.isFromOpenAPP);
        }
    }
}

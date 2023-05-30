package com.jd.manto;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.jingdong.common.login.LoginUserBase;
import com.jingdong.sdk.oklog.OKLog;

/* loaded from: classes17.dex */
public class MantoRipper extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (TextUtils.isEmpty(action)) {
            return;
        }
        OKLog.e("MantoRipper", action);
        if (action.equals("com.jingdong.action.user.login.out")) {
            com.jingdong.a.q();
        } else if (action.equals("com.jingdong.action.user.login.in")) {
            com.jingdong.a.C(LoginUserBase.getUserPin());
            com.jd.manto.login.a.m();
        }
    }
}

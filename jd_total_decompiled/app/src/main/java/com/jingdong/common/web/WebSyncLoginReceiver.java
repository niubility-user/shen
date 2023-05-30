package com.jingdong.common.web;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

/* loaded from: classes6.dex */
public class WebSyncLoginReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String action = intent != null ? intent.getAction() : null;
        if (TextUtils.isEmpty(action)) {
            return;
        }
        if (action.equalsIgnoreCase("com.jingdong.action.user.login.out")) {
            WebLoginHelper.addBroadCastLogoutCount();
        } else if (action.equalsIgnoreCase("com.jingdong.action.user.login.in")) {
            WebLoginHelper.addBroadCastLoginCount();
        }
    }
}

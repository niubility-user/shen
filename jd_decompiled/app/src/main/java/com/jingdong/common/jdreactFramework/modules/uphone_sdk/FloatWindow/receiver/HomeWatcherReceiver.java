package com.jingdong.common.jdreactFramework.modules.uphone_sdk.FloatWindow.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/* loaded from: classes5.dex */
public class HomeWatcherReceiver extends BroadcastReceiver {
    private static final String SYSTEM_DIALOG_FROM_HOME_KEY = "homekey";
    private static final String SYSTEM_DIALOG_FROM_KEY = "reason";
    private static final String SYSTEM_DIALOG_FROM_LOCK = "lock";
    private static final String SYSTEM_DIALOG_FROM_RECENT_APPS = "recentapps";
    private static final String TAG = "HomeWatcherReceiver";

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        String str = "onReceive: action: " + action;
        if (action.equals("android.intent.action.CLOSE_SYSTEM_DIALOGS")) {
            String stringExtra = intent.getStringExtra(SYSTEM_DIALOG_FROM_KEY);
            String str2 = "from: " + stringExtra;
            if (SYSTEM_DIALOG_FROM_HOME_KEY.equals(stringExtra) || SYSTEM_DIALOG_FROM_RECENT_APPS.equals(stringExtra)) {
                return;
            }
            "lock".equals(stringExtra);
        }
    }
}

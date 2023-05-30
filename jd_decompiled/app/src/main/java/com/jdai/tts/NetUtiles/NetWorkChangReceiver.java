package com.jdai.tts.NetUtiles;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import g.g.a.f;

/* loaded from: classes18.dex */
public class NetWorkChangReceiver extends BroadcastReceiver {
    private static String a = "NetWork";

    public NetWorkChangReceiver(Context context) {
        a.a(context);
    }

    public int a(b bVar) {
        return 0;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        f.c(a, "onReceive:");
        if ("android.net.wifi.WIFI_STATE_CHANGED".equals(intent.getAction())) {
            int intExtra = intent.getIntExtra("wifi_state", 0);
            f.c(a, "wifiState:" + intExtra);
        }
    }
}

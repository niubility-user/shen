package com.xiaomi.push.service.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.xiaomi.push.i4;
import com.xiaomi.push.service.XMPushService;
import com.xiaomi.push.service.m0;
import g.j.a.a.a.c;

/* loaded from: classes11.dex */
public class PingReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        c.B(intent.getPackage() + " is the package name");
        if (XMPushService.m145e()) {
            return;
        }
        if (!m0.o.equals(intent.getAction())) {
            c.o("cancel the old ping timer");
            i4.a();
        } else if (TextUtils.equals(context.getPackageName(), intent.getPackage())) {
            c.B("Ping XMChannelService on timer");
            try {
                Intent intent2 = new Intent(context, XMPushService.class);
                intent2.putExtra("time_stamp", System.currentTimeMillis());
                intent2.setAction("com.xiaomi.push.timer");
                com.xiaomi.push.service.a.h(context).i(intent2);
            } catch (Exception e2) {
                c.s(e2);
            }
        }
    }
}

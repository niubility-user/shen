package com.xiaomi.mipush.sdk;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/* loaded from: classes11.dex */
class v extends BroadcastReceiver {
    final /* synthetic */ NotificationClickedActivity a;

    public v(NotificationClickedActivity notificationClickedActivity) {
        this.a = notificationClickedActivity;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        g.j.a.a.a.c.y("clicked activity finish by normal.");
        this.a.finish();
    }
}

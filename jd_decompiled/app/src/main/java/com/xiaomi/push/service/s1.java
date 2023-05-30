package com.xiaomi.push.service;

import android.content.ComponentName;
import android.content.pm.PackageManager;

/* loaded from: classes11.dex */
class s1 implements Runnable {

    /* renamed from: g  reason: collision with root package name */
    final /* synthetic */ XMPushService f19186g;

    /* JADX INFO: Access modifiers changed from: package-private */
    public s1(XMPushService xMPushService) {
        this.f19186g = xMPushService;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            PackageManager packageManager = this.f19186g.getApplicationContext().getPackageManager();
            ComponentName componentName = new ComponentName(this.f19186g.getApplicationContext(), "com.xiaomi.push.service.receivers.PingReceiver");
            if (packageManager.getComponentEnabledSetting(componentName) != 2) {
                packageManager.setComponentEnabledSetting(componentName, 2, 1);
            }
        } catch (Throwable th) {
            g.j.a.a.a.c.o("[Alarm] disable ping receiver may be failure. " + th);
        }
    }
}

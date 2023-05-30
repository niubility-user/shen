package com.xiaomi.push.service;

/* loaded from: classes11.dex */
class r1 implements Runnable {

    /* renamed from: g  reason: collision with root package name */
    final /* synthetic */ XMPushService f19176g;

    /* JADX INFO: Access modifiers changed from: package-private */
    public r1(XMPushService xMPushService) {
        this.f19176g = xMPushService;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.f19176g.f241a = true;
        try {
            g.j.a.a.a.c.o("try to trigger the wifi digest broadcast.");
            Object systemService = this.f19176g.getApplicationContext().getSystemService("MiuiWifiService");
            if (systemService != null) {
                com.xiaomi.push.k0.n(systemService, "sendCurrentWifiDigestInfo", new Object[0]);
            }
        } catch (Throwable unused) {
        }
    }
}

package com.xiaomi.push.service;

import com.xiaomi.push.a5;
import com.xiaomi.push.service.XMPushService;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes11.dex */
public class s0 {

    /* renamed from: f  reason: collision with root package name */
    private static int f19183f = 300000;
    private XMPushService a;
    private int d = 0;

    /* renamed from: e  reason: collision with root package name */
    private int f19185e = 0;
    private int b = 500;

    /* renamed from: c  reason: collision with root package name */
    private long f19184c = 0;

    public s0(XMPushService xMPushService) {
        this.a = xMPushService;
    }

    private int a() {
        if (this.d > 8) {
            return 300000;
        }
        double random = (Math.random() * 2.0d) + 1.0d;
        int i2 = this.d;
        if (i2 > 4) {
            return (int) (random * 60000.0d);
        }
        if (i2 > 1) {
            return (int) (random * 10000.0d);
        }
        if (this.f19184c == 0) {
            return 0;
        }
        if (System.currentTimeMillis() - this.f19184c >= 310000) {
            this.b = 1000;
            this.f19185e = 0;
            return 0;
        }
        int i3 = this.b;
        int i4 = f19183f;
        if (i3 >= i4) {
            return i3;
        }
        int i5 = this.f19185e + 1;
        this.f19185e = i5;
        if (i5 >= 4) {
            return i4;
        }
        double d = i3;
        Double.isNaN(d);
        this.b = (int) (d * 1.5d);
        return i3;
    }

    public void b() {
        this.f19184c = System.currentTimeMillis();
        this.a.a(1);
        this.d = 0;
    }

    public void c(boolean z) {
        if (!this.a.m154a()) {
            g.j.a.a.a.c.B("should not reconnect as no client or network.");
        } else if (z) {
            if (!this.a.m155a(1)) {
                this.d++;
            }
            this.a.a(1);
            g.j.a.a.a.c.p("ReconnectionManager", "-->tryReconnect(): exec ConnectJob");
            XMPushService xMPushService = this.a;
            xMPushService.getClass();
            xMPushService.a(new XMPushService.e());
        } else if (this.a.m155a(1)) {
        } else {
            int a = a();
            this.d++;
            g.j.a.a.a.c.o("schedule reconnect in " + a + "ms");
            XMPushService xMPushService2 = this.a;
            xMPushService2.getClass();
            xMPushService2.a(new XMPushService.e(), (long) a);
            if (this.d == 2 && a5.f().k()) {
                q.e();
            }
            if (this.d == 3) {
                q.b();
            }
        }
    }
}

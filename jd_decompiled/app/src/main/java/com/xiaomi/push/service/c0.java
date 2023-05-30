package com.xiaomi.push.service;

import com.xiaomi.push.c7;
import com.xiaomi.push.c8;
import com.xiaomi.push.i;
import com.xiaomi.push.m8;
import java.lang.ref.WeakReference;

/* loaded from: classes11.dex */
public class c0 extends i.a {

    /* renamed from: g  reason: collision with root package name */
    private c8 f19069g;

    /* renamed from: h  reason: collision with root package name */
    private WeakReference<XMPushService> f19070h;

    /* renamed from: i  reason: collision with root package name */
    private boolean f19071i;

    public c0(c8 c8Var, WeakReference<XMPushService> weakReference, boolean z) {
        this.f19071i = false;
        this.f19069g = c8Var;
        this.f19070h = weakReference;
        this.f19071i = z;
    }

    @Override // com.xiaomi.push.i.a
    public String b() {
        return "22";
    }

    @Override // java.lang.Runnable
    public void run() {
        XMPushService xMPushService;
        WeakReference<XMPushService> weakReference = this.f19070h;
        if (weakReference == null || this.f19069g == null || (xMPushService = weakReference.get()) == null) {
            return;
        }
        this.f19069g.a(f0.a());
        this.f19069g.a(false);
        g.j.a.a.a.c.B("MoleInfo aw_ping : send aw_Ping msg " + this.f19069g.m34a());
        try {
            String c2 = this.f19069g.c();
            xMPushService.a(c2, m8.f(k.d(c2, this.f19069g.b(), this.f19069g, c7.Notification)), this.f19071i);
        } catch (Exception e2) {
            g.j.a.a.a.c.D("MoleInfo aw_ping : send help app ping error" + e2.toString());
        }
    }
}

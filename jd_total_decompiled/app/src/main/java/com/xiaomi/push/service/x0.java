package com.xiaomi.push.service;

import com.xiaomi.push.a6;
import com.xiaomi.push.e5;
import com.xiaomi.push.service.XMPushService;

/* loaded from: classes11.dex */
public class x0 extends XMPushService.j {

    /* renamed from: h  reason: collision with root package name */
    private XMPushService f19199h;

    /* renamed from: i  reason: collision with root package name */
    private e5 f19200i;

    public x0(XMPushService xMPushService, e5 e5Var) {
        super(4);
        this.f19199h = null;
        this.f19199h = xMPushService;
        this.f19200i = e5Var;
    }

    @Override // com.xiaomi.push.service.XMPushService.j
    public void a() {
        try {
            e5 e5Var = this.f19200i;
            if (e5Var != null) {
                if (b2.a(e5Var)) {
                    this.f19200i.A(System.currentTimeMillis() - this.f19200i.b());
                }
                this.f19199h.a(this.f19200i);
            }
        } catch (a6 e2) {
            g.j.a.a.a.c.s(e2);
            this.f19199h.a(10, e2);
        }
    }

    @Override // com.xiaomi.push.service.XMPushService.j
    public String b() {
        return "send a message.";
    }
}

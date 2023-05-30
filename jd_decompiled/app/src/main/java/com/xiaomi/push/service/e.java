package com.xiaomi.push.service;

import com.xiaomi.push.a6;
import com.xiaomi.push.service.XMPushService;
import com.xiaomi.push.y7;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes11.dex */
public class e extends XMPushService.j {

    /* renamed from: h */
    final /* synthetic */ XMPushService f19078h;

    /* renamed from: i */
    final /* synthetic */ y7 f19079i;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public e(int i2, XMPushService xMPushService, y7 y7Var) {
        super(i2);
        this.f19078h = xMPushService;
        this.f19079i = y7Var;
    }

    @Override // com.xiaomi.push.service.XMPushService.j
    public void a() {
        try {
            y7 b = x2.b(this.f19078h, this.f19079i);
            b.m185a().a("message_obsleted", "1");
            k.i(this.f19078h, b);
        } catch (a6 e2) {
            g.j.a.a.a.c.s(e2);
            this.f19078h.a(10, e2);
        }
    }

    @Override // com.xiaomi.push.service.XMPushService.j
    public String b() {
        return "send ack message for obsleted message.";
    }
}

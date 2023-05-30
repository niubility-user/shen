package com.xiaomi.push.service;

import com.xiaomi.push.a6;
import com.xiaomi.push.service.XMPushService;
import com.xiaomi.push.y7;

/* loaded from: classes11.dex */
public class f extends XMPushService.j {

    /* renamed from: h */
    final /* synthetic */ XMPushService f19081h;

    /* renamed from: i */
    final /* synthetic */ y7 f19082i;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public f(int i2, XMPushService xMPushService, y7 y7Var) {
        super(i2);
        this.f19081h = xMPushService;
        this.f19082i = y7Var;
    }

    @Override // com.xiaomi.push.service.XMPushService.j
    public void a() {
        try {
            y7 b = x2.b(this.f19081h, this.f19082i);
            b.m185a().a("miui_message_unrecognized", "1");
            k.i(this.f19081h, b);
        } catch (a6 e2) {
            g.j.a.a.a.c.s(e2);
            this.f19081h.a(10, e2);
        }
    }

    @Override // com.xiaomi.push.service.XMPushService.j
    public String b() {
        return "send ack message for unrecognized new miui message.";
    }
}

package com.xiaomi.push.service;

import com.xiaomi.push.a6;
import com.xiaomi.push.c7;
import com.xiaomi.push.c8;
import com.xiaomi.push.m7;
import com.xiaomi.push.service.XMPushService;
import com.xiaomi.push.t7;
import com.xiaomi.push.y7;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes11.dex */
public class i extends XMPushService.j {

    /* renamed from: h */
    final /* synthetic */ c8 f19092h;

    /* renamed from: i */
    final /* synthetic */ y7 f19093i;

    /* renamed from: j */
    final /* synthetic */ XMPushService f19094j;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public i(int i2, c8 c8Var, y7 y7Var, XMPushService xMPushService) {
        super(i2);
        this.f19092h = c8Var;
        this.f19093i = y7Var;
        this.f19094j = xMPushService;
    }

    @Override // com.xiaomi.push.service.XMPushService.j
    public void a() {
        try {
            t7 t7Var = new t7();
            t7Var.c(m7.CancelPushMessageACK.f179a);
            t7Var.a(this.f19092h.m34a());
            t7Var.a(this.f19092h.a());
            t7Var.b(this.f19092h.b());
            t7Var.e(this.f19092h.c());
            t7Var.a(0L);
            t7Var.d("success clear push message.");
            k.i(this.f19094j, k.n(this.f19093i.b(), this.f19093i.m186a(), t7Var, c7.Notification));
        } catch (a6 e2) {
            g.j.a.a.a.c.D("clear push message. " + e2);
            this.f19094j.a(10, e2);
        }
    }

    @Override // com.xiaomi.push.service.XMPushService.j
    public String b() {
        return "send ack message for clear push message.";
    }
}

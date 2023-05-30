package com.xiaomi.push.service;

import com.xiaomi.push.a6;
import com.xiaomi.push.service.XMPushService;
import com.xiaomi.push.y7;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes11.dex */
public class g extends XMPushService.j {

    /* renamed from: h */
    final /* synthetic */ XMPushService f19083h;

    /* renamed from: i */
    final /* synthetic */ y7 f19084i;

    /* renamed from: j */
    final /* synthetic */ String f19085j;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public g(int i2, XMPushService xMPushService, y7 y7Var, String str) {
        super(i2);
        this.f19083h = xMPushService;
        this.f19084i = y7Var;
        this.f19085j = str;
    }

    @Override // com.xiaomi.push.service.XMPushService.j
    public void a() {
        try {
            y7 b = x2.b(this.f19083h, this.f19084i);
            b.m185a().a("absent_target_package", this.f19085j);
            k.i(this.f19083h, b);
        } catch (a6 e2) {
            g.j.a.a.a.c.s(e2);
            this.f19083h.a(10, e2);
        }
    }

    @Override // com.xiaomi.push.service.XMPushService.j
    public String b() {
        return "send app absent ack message for message.";
    }
}

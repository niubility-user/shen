package com.xiaomi.push.service;

import com.xiaomi.push.a6;
import com.xiaomi.push.service.XMPushService;
import com.xiaomi.push.y7;

/* loaded from: classes11.dex */
public class h extends XMPushService.j {

    /* renamed from: h */
    final /* synthetic */ XMPushService f19088h;

    /* renamed from: i */
    final /* synthetic */ y7 f19089i;

    /* renamed from: j */
    final /* synthetic */ String f19090j;

    /* renamed from: k */
    final /* synthetic */ String f19091k;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public h(int i2, XMPushService xMPushService, y7 y7Var, String str, String str2) {
        super(i2);
        this.f19088h = xMPushService;
        this.f19089i = y7Var;
        this.f19090j = str;
        this.f19091k = str2;
    }

    @Override // com.xiaomi.push.service.XMPushService.j
    public void a() {
        try {
            y7 b = x2.b(this.f19088h, this.f19089i);
            b.f263a.a("error", this.f19090j);
            b.f263a.a("reason", this.f19091k);
            k.i(this.f19088h, b);
        } catch (a6 e2) {
            g.j.a.a.a.c.s(e2);
            this.f19088h.a(10, e2);
        }
    }

    @Override // com.xiaomi.push.service.XMPushService.j
    public String b() {
        return "send wrong message ack for message.";
    }
}

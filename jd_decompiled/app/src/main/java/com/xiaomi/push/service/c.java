package com.xiaomi.push.service;

import com.xiaomi.push.a6;
import com.xiaomi.push.service.XMPushService;
import com.xiaomi.push.y7;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes11.dex */
public class c extends XMPushService.j {

    /* renamed from: h */
    final /* synthetic */ XMPushService f19067h;

    /* renamed from: i */
    final /* synthetic */ y7 f19068i;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public c(int i2, XMPushService xMPushService, y7 y7Var) {
        super(i2);
        this.f19067h = xMPushService;
        this.f19068i = y7Var;
    }

    @Override // com.xiaomi.push.service.XMPushService.j
    public void a() {
        try {
            k.i(this.f19067h, k.c(this.f19068i.b(), this.f19068i.m186a()));
        } catch (a6 e2) {
            g.j.a.a.a.c.s(e2);
            this.f19067h.a(10, e2);
        }
    }

    @Override // com.xiaomi.push.service.XMPushService.j
    public String b() {
        return "send app absent message.";
    }
}

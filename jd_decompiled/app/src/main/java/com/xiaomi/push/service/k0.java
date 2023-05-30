package com.xiaomi.push.service;

import com.xiaomi.push.service.XMPushService;
import com.xiaomi.push.service.i0;

/* loaded from: classes11.dex */
class k0 extends XMPushService.j {

    /* renamed from: h */
    final /* synthetic */ i0.b.c f19120h;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public k0(i0.b.c cVar, int i2) {
        super(i2);
        this.f19120h = cVar;
    }

    @Override // com.xiaomi.push.service.XMPushService.j
    public void a() {
        i0.b.c cVar = this.f19120h;
        if (cVar.b == cVar.a.r) {
            g.j.a.a.a.c.y("clean peer, chid = " + this.f19120h.a.f19100h);
            this.f19120h.a.r = null;
        }
    }

    @Override // com.xiaomi.push.service.XMPushService.j
    public String b() {
        return "clear peer job";
    }
}

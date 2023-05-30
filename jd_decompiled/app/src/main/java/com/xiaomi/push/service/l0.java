package com.xiaomi.push.service;

import com.xiaomi.push.service.XMPushService;
import com.xiaomi.push.service.i0;

/* loaded from: classes11.dex */
class l0 extends XMPushService.j {

    /* renamed from: h  reason: collision with root package name */
    final /* synthetic */ i0.b.c f19124h;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public l0(i0.b.c cVar, int i2) {
        super(i2);
        this.f19124h = cVar;
    }

    @Override // com.xiaomi.push.service.XMPushService.j
    public void a() {
        i0 c2 = i0.c();
        i0.b bVar = this.f19124h.a;
        if (c2.b(bVar.f19100h, bVar.b).r == null) {
            XMPushService xMPushService = i0.b.this.p;
            i0.b bVar2 = this.f19124h.a;
            xMPushService.a(bVar2.f19100h, bVar2.b, 2, null, null);
        }
    }

    @Override // com.xiaomi.push.service.XMPushService.j
    public String b() {
        return "check peer job";
    }
}

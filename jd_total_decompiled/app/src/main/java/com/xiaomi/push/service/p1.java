package com.xiaomi.push.service;

import com.xiaomi.push.service.XMPushService;

/* loaded from: classes11.dex */
class p1 extends XMPushService.j {

    /* renamed from: h  reason: collision with root package name */
    final /* synthetic */ XMPushService f19172h;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public p1(XMPushService xMPushService, int i2) {
        super(i2);
        this.f19172h = xMPushService;
    }

    @Override // com.xiaomi.push.service.XMPushService.j
    public void a() {
        if (this.f19172h.f226a != null) {
            this.f19172h.f226a.v(15, null);
            this.f19172h.f226a = null;
        }
    }

    @Override // com.xiaomi.push.service.XMPushService.j
    public String b() {
        return "disconnect for service destroy.";
    }
}

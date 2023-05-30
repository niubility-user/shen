package com.xiaomi.push.service;

import com.xiaomi.push.service.XMPushService;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes11.dex */
public class x1 extends XMPushService.j {

    /* renamed from: h  reason: collision with root package name */
    final /* synthetic */ XMPushService f19201h;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public x1(XMPushService xMPushService, int i2) {
        super(i2);
        this.f19201h = xMPushService;
    }

    @Override // com.xiaomi.push.service.XMPushService.j
    public void a() {
        k.h(this.f19201h);
        if (com.xiaomi.push.j0.p(this.f19201h)) {
            this.f19201h.a(true);
        }
    }

    @Override // com.xiaomi.push.service.XMPushService.j
    public String b() {
        return "prepare the mi push account.";
    }
}

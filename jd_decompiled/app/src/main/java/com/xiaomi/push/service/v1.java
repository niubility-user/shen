package com.xiaomi.push.service;

import com.xiaomi.push.service.XMPushService;
import com.xiaomi.push.service.i0;

/* loaded from: classes11.dex */
class v1 implements i0.a {
    final /* synthetic */ XMPushService a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public v1(XMPushService xMPushService) {
        this.a = xMPushService;
    }

    @Override // com.xiaomi.push.service.i0.a
    public void a() {
        this.a.e();
        if (i0.c().a() <= 0) {
            XMPushService xMPushService = this.a;
            xMPushService.a(new XMPushService.g(12, null));
        }
    }
}

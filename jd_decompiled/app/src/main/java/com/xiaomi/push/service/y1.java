package com.xiaomi.push.service;

import com.xiaomi.push.service.XMPushService;
import com.xiaomi.push.service.t2;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes11.dex */
public class y1 implements t2.a {
    final /* synthetic */ XMPushService.j a;
    final /* synthetic */ XMPushService b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public y1(XMPushService xMPushService, XMPushService.j jVar) {
        this.b = xMPushService;
        this.a = jVar;
    }

    @Override // com.xiaomi.push.service.t2.a
    public void a() {
        this.b.a(this.a);
    }
}

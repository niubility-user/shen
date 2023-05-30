package com.xiaomi.push.service;

import com.xiaomi.push.service.XMPushService;
import com.xiaomi.push.service.i0;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes11.dex */
public class j0 implements i0.b.a {
    final /* synthetic */ i0.b a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public j0(i0.b bVar) {
        this.a = bVar;
    }

    @Override // com.xiaomi.push.service.i0.b.a
    public void a(i0.c cVar, i0.c cVar2, int i2) {
        XMPushService.c cVar3;
        XMPushService.c cVar4;
        if (cVar2 == i0.c.binding) {
            XMPushService xMPushService = this.a.p;
            cVar4 = this.a.t;
            xMPushService.a(cVar4, 60000L);
            return;
        }
        XMPushService xMPushService2 = this.a.p;
        cVar3 = this.a.t;
        xMPushService2.b(cVar3);
    }
}

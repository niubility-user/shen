package com.xiaomi.push.service;

import com.xiaomi.push.service.i0;

/* loaded from: classes11.dex */
class m implements i0.b.a {
    final /* synthetic */ XMPushService a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public m(XMPushService xMPushService) {
        this.a = xMPushService;
    }

    @Override // com.xiaomi.push.service.i0.b.a
    public void a(i0.c cVar, i0.c cVar2, int i2) {
        if (cVar2 == i0.c.binded) {
            w2.d(this.a, true);
            w2.c(this.a);
        } else if (cVar2 == i0.c.unbind) {
            g.j.a.a.a.c.o("onChange unbind");
            w2.a(this.a, 70000001, " the push is not connected.");
        }
    }
}

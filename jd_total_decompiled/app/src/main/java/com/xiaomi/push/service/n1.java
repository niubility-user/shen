package com.xiaomi.push.service;

import android.os.SystemClock;
import com.xiaomi.push.e5;
import com.xiaomi.push.g6;
import com.xiaomi.push.service.XMPushService;
import com.xiaomi.push.t5;

/* loaded from: classes11.dex */
class n1 implements t5 {
    final /* synthetic */ XMPushService a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public n1(XMPushService xMPushService) {
        this.a = xMPushService;
    }

    @Override // com.xiaomi.push.t5
    public void a(g6 g6Var) {
        XMPushService xMPushService = this.a;
        xMPushService.a(new XMPushService.m(g6Var));
    }

    @Override // com.xiaomi.push.t5
    public void b(e5 e5Var) {
        if (b2.a(e5Var)) {
            t0.a().d(e5Var.D(), SystemClock.elapsedRealtime(), this.a.m150a());
        }
        XMPushService xMPushService = this.a;
        xMPushService.a(new XMPushService.d(e5Var));
    }
}

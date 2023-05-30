package com.xiaomi.push.service;

import android.database.ContentObserver;
import android.os.Handler;
import com.xiaomi.push.service.XMPushService;

/* loaded from: classes11.dex */
class w1 extends ContentObserver {
    final /* synthetic */ XMPushService a;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public w1(XMPushService xMPushService, Handler handler) {
        super(handler);
        this.a = xMPushService;
    }

    @Override // android.database.ContentObserver
    public void onChange(boolean z) {
        boolean m147g;
        super.onChange(z);
        m147g = this.a.m147g();
        g.j.a.a.a.c.o("SuperPowerMode:" + m147g);
        this.a.e();
        if (!m147g) {
            this.a.a(true);
            return;
        }
        XMPushService xMPushService = this.a;
        xMPushService.a(new XMPushService.g(24, null));
    }
}

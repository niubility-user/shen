package com.xiaomi.push.service;

import android.content.Context;
import com.xiaomi.push.c7;
import com.xiaomi.push.c8;
import com.xiaomi.push.m8;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes11.dex */
public class m1 implements Runnable {

    /* renamed from: g  reason: collision with root package name */
    final /* synthetic */ c8 f19137g;

    /* JADX INFO: Access modifiers changed from: package-private */
    public m1(c8 c8Var) {
        this.f19137g = c8Var;
    }

    @Override // java.lang.Runnable
    public void run() {
        Context context;
        Context context2;
        byte[] f2 = m8.f(k.d(this.f19137g.c(), this.f19137g.b(), this.f19137g, c7.Notification));
        context = l1.f19125c;
        if (!(context instanceof XMPushService)) {
            g.j.a.a.a.c.o("UNDatas UploadNotificationDatas failed because not xmsf");
            return;
        }
        context2 = l1.f19125c;
        ((XMPushService) context2).a(this.f19137g.c(), f2, true);
    }
}

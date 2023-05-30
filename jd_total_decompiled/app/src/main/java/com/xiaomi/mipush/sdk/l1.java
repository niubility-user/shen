package com.xiaomi.mipush.sdk;

import android.content.Context;
import android.content.Intent;

/* loaded from: classes11.dex */
class l1 implements Runnable {

    /* renamed from: g  reason: collision with root package name */
    final /* synthetic */ Context f18396g;

    /* renamed from: h  reason: collision with root package name */
    final /* synthetic */ Intent f18397h;

    /* JADX INFO: Access modifiers changed from: package-private */
    public l1(Context context, Intent intent) {
        this.f18396g = context;
        this.f18397h = intent;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            this.f18396g.startService(this.f18397h);
        } catch (Exception e2) {
            g.j.a.a.a.c.o(e2.getMessage());
        }
    }
}

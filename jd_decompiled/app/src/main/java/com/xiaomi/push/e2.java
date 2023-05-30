package com.xiaomi.push;

import android.content.Context;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes11.dex */
public class e2 implements Runnable {

    /* renamed from: g  reason: collision with root package name */
    final /* synthetic */ Context f18554g;

    /* renamed from: h  reason: collision with root package name */
    final /* synthetic */ long f18555h;

    /* renamed from: i  reason: collision with root package name */
    final /* synthetic */ boolean f18556i;

    /* JADX INFO: Access modifiers changed from: package-private */
    public e2(Context context, long j2, boolean z) {
        this.f18554g = context;
        this.f18555h = j2;
        this.f18556i = z;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            b2.t(this.f18554g, this.f18555h, this.f18556i);
        } catch (Exception e2) {
            g.j.a.a.a.c.o("PowerStatsSP onPing exception: " + e2.getMessage());
        }
    }
}

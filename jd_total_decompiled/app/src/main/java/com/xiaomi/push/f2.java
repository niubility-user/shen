package com.xiaomi.push;

import android.content.Context;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes11.dex */
public class f2 implements Runnable {

    /* renamed from: g  reason: collision with root package name */
    final /* synthetic */ Context f18609g;

    /* renamed from: h  reason: collision with root package name */
    final /* synthetic */ long f18610h;

    /* renamed from: i  reason: collision with root package name */
    final /* synthetic */ boolean f18611i;

    /* JADX INFO: Access modifiers changed from: package-private */
    public f2(Context context, long j2, boolean z) {
        this.f18609g = context;
        this.f18610h = j2;
        this.f18611i = z;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            b2.u(this.f18609g, this.f18610h, this.f18611i);
        } catch (Exception e2) {
            g.j.a.a.a.c.o("PowerStatsSP onPong exception: " + e2.getMessage());
        }
    }
}

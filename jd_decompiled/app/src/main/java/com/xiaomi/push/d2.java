package com.xiaomi.push;

import android.content.Context;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes11.dex */
public class d2 implements Runnable {

    /* renamed from: g  reason: collision with root package name */
    final /* synthetic */ Context f18526g;

    /* renamed from: h  reason: collision with root package name */
    final /* synthetic */ long f18527h;

    /* renamed from: i  reason: collision with root package name */
    final /* synthetic */ boolean f18528i;

    /* JADX INFO: Access modifiers changed from: package-private */
    public d2(Context context, long j2, boolean z) {
        this.f18526g = context;
        this.f18527h = j2;
        this.f18528i = z;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            b2.s(this.f18526g, this.f18527h, this.f18528i);
        } catch (Exception e2) {
            g.j.a.a.a.c.o("PowerStatsSP onReceiveMsg exception: " + e2.getMessage());
        }
    }
}

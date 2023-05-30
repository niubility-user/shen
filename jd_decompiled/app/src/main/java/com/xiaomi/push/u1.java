package com.xiaomi.push;

import android.content.Context;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes11.dex */
public class u1 implements Runnable {

    /* renamed from: g  reason: collision with root package name */
    final /* synthetic */ Context f19252g;

    /* renamed from: h  reason: collision with root package name */
    final /* synthetic */ long f19253h;

    /* JADX INFO: Access modifiers changed from: package-private */
    public u1(Context context, long j2) {
        this.f19252g = context;
        this.f19253h = j2;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            s1.p(this.f19252g, this.f19253h);
        } catch (Exception e2) {
            g.j.a.a.a.c.o("DisconnectStatsSP onReconnection exception: " + e2.getMessage());
        }
    }
}

package com.xiaomi.push;

import android.content.Context;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes11.dex */
public class c2 implements Runnable {

    /* renamed from: g  reason: collision with root package name */
    final /* synthetic */ Context f18493g;

    /* renamed from: h  reason: collision with root package name */
    final /* synthetic */ long f18494h;

    /* renamed from: i  reason: collision with root package name */
    final /* synthetic */ boolean f18495i;

    /* JADX INFO: Access modifiers changed from: package-private */
    public c2(Context context, long j2, boolean z) {
        this.f18493g = context;
        this.f18494h = j2;
        this.f18495i = z;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            b2.r(this.f18493g, this.f18494h, this.f18495i);
        } catch (Exception e2) {
            g.j.a.a.a.c.o("PowerStatsSP onSendMsg exception: " + e2.getMessage());
        }
    }
}

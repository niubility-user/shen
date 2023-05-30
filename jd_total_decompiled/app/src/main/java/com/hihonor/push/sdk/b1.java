package com.hihonor.push.sdk;

import com.hihonor.push.sdk.c1;

/* loaded from: classes12.dex */
public class b1 implements Runnable {

    /* renamed from: g  reason: collision with root package name */
    public final /* synthetic */ int f1078g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ c1.a f1079h;

    public b1(c1.a aVar, int i2) {
        this.f1079h = aVar;
        this.f1078g = i2;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.f1079h.c(com.hihonor.push.sdk.b0.a.fromCode(this.f1078g));
    }
}

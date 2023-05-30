package com.hihonor.push.sdk;

/* loaded from: classes12.dex */
public class s0 implements Runnable {

    /* renamed from: g */
    public final /* synthetic */ e f1115g;

    /* renamed from: h */
    public final /* synthetic */ u0 f1116h;

    public s0(u0 u0Var, e eVar) {
        this.f1116h = u0Var;
        this.f1115g = eVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        synchronized (this.f1116h.f1127c) {
            g0 g0Var = this.f1116h.b;
            if (g0Var != null) {
                this.f1115g.c();
                ((a1) g0Var).a.countDown();
            }
        }
    }
}

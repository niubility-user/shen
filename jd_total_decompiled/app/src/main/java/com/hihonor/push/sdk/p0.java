package com.hihonor.push.sdk;

/* loaded from: classes12.dex */
public class p0 implements Runnable {

    /* renamed from: g */
    public final /* synthetic */ e f1108g;

    /* renamed from: h */
    public final /* synthetic */ q0 f1109h;

    public p0(q0 q0Var, e eVar) {
        this.f1109h = q0Var;
        this.f1108g = eVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        synchronized (this.f1109h.f1110c) {
            e0<TResult> e0Var = this.f1109h.b;
            if (e0Var != 0) {
                e0Var.a(this.f1108g);
            }
        }
    }
}

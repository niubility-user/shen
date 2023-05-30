package com.hihonor.push.sdk;

/* loaded from: classes12.dex */
public class x implements Runnable {

    /* renamed from: g  reason: collision with root package name */
    public final /* synthetic */ a f1136g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ f0 f1137h;

    public x(f0 f0Var, a aVar) {
        this.f1137h = f0Var;
        this.f1136g = aVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        o0 o0Var = this.f1137h.d;
        o0Var.b(new k0(o0Var), this.f1136g);
    }
}

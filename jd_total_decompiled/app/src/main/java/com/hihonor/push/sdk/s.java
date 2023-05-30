package com.hihonor.push.sdk;

import java.lang.ref.WeakReference;

/* loaded from: classes12.dex */
public class s implements Runnable {

    /* renamed from: g  reason: collision with root package name */
    public final /* synthetic */ v0 f1113g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ f0 f1114h;

    public s(f0 f0Var, v0 v0Var) {
        this.f1114h = f0Var;
        this.f1113g = v0Var;
    }

    @Override // java.lang.Runnable
    public void run() {
        if (this.f1114h.b) {
            return;
        }
        this.f1114h.b = true;
        this.f1114h.getClass();
        this.f1114h.a = new WeakReference<>(this.f1113g.a);
        this.f1114h.f1092c = this.f1113g.b;
        this.f1114h.d = new o0(this.f1113g.a);
        if (this.f1114h.f1092c) {
            f0 f0Var = this.f1114h;
            f0Var.b(new v(f0Var, null, true), null);
        }
    }
}

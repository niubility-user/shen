package com.hihonor.push.sdk;

/* loaded from: classes12.dex */
public class v implements Runnable {

    /* renamed from: g  reason: collision with root package name */
    public final /* synthetic */ a f1128g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ boolean f1129h;

    /* renamed from: i  reason: collision with root package name */
    public final /* synthetic */ f0 f1130i;

    public v(f0 f0Var, a aVar, boolean z) {
        this.f1130i = f0Var;
        this.f1128g = aVar;
        this.f1129h = z;
    }

    @Override // java.lang.Runnable
    public void run() {
        o0 o0Var = this.f1130i.d;
        o0Var.b(new i0(o0Var, this.f1129h), this.f1128g);
    }
}

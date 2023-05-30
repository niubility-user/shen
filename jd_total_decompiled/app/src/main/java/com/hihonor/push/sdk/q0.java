package com.hihonor.push.sdk;

import java.util.concurrent.Executor;

/* loaded from: classes12.dex */
public final class q0<TResult> implements d0<TResult> {
    public Executor a;
    public e0<TResult> b;

    /* renamed from: c  reason: collision with root package name */
    public final Object f1110c = new Object();

    public q0(Executor executor, e0<TResult> e0Var) {
        this.b = e0Var;
        this.a = executor;
    }

    @Override // com.hihonor.push.sdk.d0
    public final void a(e<TResult> eVar) {
        this.a.execute(new p0(this, eVar));
    }
}

package com.hihonor.push.sdk;

import java.util.concurrent.Executor;

/* loaded from: classes12.dex */
public final class u0<TResult> implements d0<TResult> {
    public Executor a;
    public g0 b;

    /* renamed from: c  reason: collision with root package name */
    public final Object f1127c = new Object();

    public u0(Executor executor, g0 g0Var) {
        this.b = g0Var;
        this.a = executor;
    }

    @Override // com.hihonor.push.sdk.d0
    public final void a(e<TResult> eVar) {
        if (eVar.f()) {
            return;
        }
        eVar.e();
        this.a.execute(new s0(this, eVar));
    }
}

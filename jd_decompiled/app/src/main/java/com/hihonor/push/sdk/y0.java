package com.hihonor.push.sdk;

import java.util.concurrent.Executor;

/* loaded from: classes12.dex */
public final class y0<TResult> implements d0<TResult> {
    public Executor a;
    public h0<TResult> b;

    /* renamed from: c  reason: collision with root package name */
    public final Object f1138c = new Object();

    public y0(Executor executor, h0<TResult> h0Var) {
        this.b = h0Var;
        this.a = executor;
    }

    @Override // com.hihonor.push.sdk.d0
    public final void a(e<TResult> eVar) {
        if (eVar.f()) {
            eVar.e();
            this.a.execute(new w0(this, eVar));
        }
    }
}

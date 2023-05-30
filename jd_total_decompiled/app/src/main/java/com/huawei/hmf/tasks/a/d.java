package com.huawei.hmf.tasks.a;

import java.util.concurrent.Executor;

/* loaded from: classes12.dex */
public final class d<TResult> implements g.e.c.a.b<TResult> {
    private g.e.c.a.e<TResult> a;
    private Executor b;

    /* renamed from: c  reason: collision with root package name */
    private final Object f1163c = new Object();

    /* loaded from: classes12.dex */
    final class a implements Runnable {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ g.e.c.a.f f1164g;

        a(g.e.c.a.f fVar) {
            this.f1164g = fVar;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.lang.Runnable
        public final void run() {
            synchronized (d.this.f1163c) {
                if (d.this.a != null) {
                    d.this.a.onSuccess(this.f1164g.e());
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public d(Executor executor, g.e.c.a.e<TResult> eVar) {
        this.a = eVar;
        this.b = executor;
    }

    @Override // g.e.c.a.b
    public final void cancel() {
        synchronized (this.f1163c) {
            this.a = null;
        }
    }

    @Override // g.e.c.a.b
    public final void onComplete(g.e.c.a.f<TResult> fVar) {
        if (!fVar.h() || fVar.f()) {
            return;
        }
        this.b.execute(new a(fVar));
    }
}

package com.huawei.hmf.tasks.a;

import java.util.concurrent.Executor;

/* loaded from: classes12.dex */
public final class c<TResult> implements g.e.c.a.b<TResult> {
    private g.e.c.a.d a;
    private Executor b;

    /* renamed from: c  reason: collision with root package name */
    private final Object f1160c = new Object();

    /* loaded from: classes12.dex */
    final class a implements Runnable {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ g.e.c.a.f f1161g;

        a(g.e.c.a.f fVar) {
            this.f1161g = fVar;
        }

        @Override // java.lang.Runnable
        public final void run() {
            synchronized (c.this.f1160c) {
                if (c.this.a != null) {
                    c.this.a.onFailure(this.f1161g.d());
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public c(Executor executor, g.e.c.a.d dVar) {
        this.a = dVar;
        this.b = executor;
    }

    @Override // g.e.c.a.b
    public final void cancel() {
        synchronized (this.f1160c) {
            this.a = null;
        }
    }

    @Override // g.e.c.a.b
    public final void onComplete(g.e.c.a.f<TResult> fVar) {
        if (fVar.h() || fVar.f()) {
            return;
        }
        this.b.execute(new a(fVar));
    }
}

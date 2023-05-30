package com.huawei.hmf.tasks.a;

import java.util.concurrent.Executor;

/* loaded from: classes12.dex */
public final class b<TResult> implements g.e.c.a.b<TResult> {
    private g.e.c.a.c<TResult> a;
    Executor b;

    /* renamed from: c  reason: collision with root package name */
    private final Object f1157c = new Object();

    /* loaded from: classes12.dex */
    final class a implements Runnable {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ g.e.c.a.f f1158g;

        a(g.e.c.a.f fVar) {
            this.f1158g = fVar;
        }

        @Override // java.lang.Runnable
        public final void run() {
            synchronized (b.this.f1157c) {
                if (b.this.a != null) {
                    b.this.a.onComplete(this.f1158g);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public b(Executor executor, g.e.c.a.c<TResult> cVar) {
        this.a = cVar;
        this.b = executor;
    }

    @Override // g.e.c.a.b
    public final void cancel() {
        synchronized (this.f1157c) {
            this.a = null;
        }
    }

    @Override // g.e.c.a.b
    public final void onComplete(g.e.c.a.f<TResult> fVar) {
        this.b.execute(new a(fVar));
    }
}

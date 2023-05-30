package com.huawei.hmf.tasks.a;

import android.os.Looper;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;

/* loaded from: classes12.dex */
public final class f {

    /* loaded from: classes12.dex */
    final class a implements Runnable {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ g.e.c.a.g f1169g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ Callable f1170h;

        a(f fVar, g.e.c.a.g gVar, Callable callable) {
            this.f1169g = gVar;
            this.f1170h = callable;
        }

        @Override // java.lang.Runnable
        public final void run() {
            try {
                this.f1169g.d(this.f1170h.call());
            } catch (Exception e2) {
                this.f1169g.c(e2);
            }
        }
    }

    /* loaded from: classes12.dex */
    public static class b<TResult> implements Object, g.e.c.a.d {
        public final CountDownLatch a = new CountDownLatch(1);

        @Override // g.e.c.a.d
        public final void onFailure(Exception exc) {
            this.a.countDown();
        }

        public final void onSuccess(TResult tresult) {
            this.a.countDown();
        }
    }

    public static <TResult> TResult b(g.e.c.a.f<TResult> fVar) throws ExecutionException {
        if (fVar.h()) {
            return fVar.e();
        }
        throw new ExecutionException(fVar.d());
    }

    public static void c(String str) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            throw new IllegalStateException(str);
        }
    }

    public final <TResult> g.e.c.a.f<TResult> a(Executor executor, Callable<TResult> callable) {
        g.e.c.a.g gVar = new g.e.c.a.g();
        try {
            executor.execute(new a(this, gVar, callable));
        } catch (Exception e2) {
            gVar.c(e2);
        }
        return gVar.b();
    }
}

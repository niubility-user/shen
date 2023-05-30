package com.sina.weibo.sdk.a;

import com.sina.weibo.sdk.a.c;
import java.util.Comparator;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* loaded from: classes9.dex */
public final class a {
    private static final int E;
    private static final int F;
    private static final int G;
    private static final Comparator<Runnable> I;
    private ThreadPoolExecutor H;

    /* renamed from: com.sina.weibo.sdk.a.a$a  reason: collision with other inner class name */
    /* loaded from: classes9.dex */
    static class C0773a implements Comparator<Runnable> {
        C0773a() {
        }

        @Override // java.util.Comparator
        public final /* bridge */ /* synthetic */ int compare(Runnable runnable, Runnable runnable2) {
            return 0;
        }
    }

    static {
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        E = availableProcessors;
        F = availableProcessors + 1;
        G = (availableProcessors * 2) + 1;
        I = new C0773a();
    }

    public a() {
        if (this.H == null) {
            this.H = new ThreadPoolExecutor(F, G, 1L, TimeUnit.SECONDS, new PriorityBlockingQueue(5, I));
        }
    }

    public final void a(c cVar) {
        ThreadPoolExecutor threadPoolExecutor = this.H;
        if (cVar.L != c.b.U) {
            int i2 = c.e.a[cVar.L - 1];
            if (i2 == 1) {
                throw new IllegalStateException("Cannot execute task: the task is already running.");
            }
            if (i2 == 2) {
                throw new IllegalStateException("Cannot execute task: the task has already been executed (a task can be executed only once)");
            }
        }
        cVar.L = c.b.V;
        c.h<Params, Result> hVar = cVar.M;
        hVar.f16075g = cVar.P;
        hVar.f16076h = cVar.O;
        threadPoolExecutor.execute(cVar.N);
    }
}

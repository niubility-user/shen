package com.jingdong.sdk.jdcrashreport.b;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;

/* loaded from: classes7.dex */
public class w<TResult> {
    private ScheduledExecutorService a;
    private TResult b;

    /* renamed from: c  reason: collision with root package name */
    private Exception f14890c;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes7.dex */
    public class a implements Runnable {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ e f14891g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ d f14892h;

        a(e eVar, d dVar) {
            this.f14891g = eVar;
            this.f14892h = dVar;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.lang.Runnable
        public void run() {
            try {
                this.f14891g.c(this.f14892h.a(w.this.b));
            } catch (Exception e2) {
                this.f14891g.b(e2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes7.dex */
    public class b implements ThreadFactory {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ String f14894g;

        b(String str) {
            this.f14894g = str;
        }

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            Thread thread = new Thread(runnable);
            thread.setName(this.f14894g);
            return thread;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes7.dex */
    public class c implements Runnable {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ long f14895g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ e f14896h;

        /* renamed from: i  reason: collision with root package name */
        final /* synthetic */ Callable f14897i;

        c(long j2, e eVar, Callable callable) {
            this.f14895g = j2;
            this.f14896h = eVar;
            this.f14897i = callable;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.lang.Runnable
        public void run() {
            try {
                Thread.sleep(this.f14895g);
                this.f14896h.c(this.f14897i.call());
            } catch (Exception e2) {
                this.f14896h.b(e2);
            }
        }
    }

    /* loaded from: classes7.dex */
    public interface d<TTaskResult, TContinuationResult> {
        TContinuationResult a(TTaskResult ttaskresult);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes7.dex */
    public class e {
        private ScheduledExecutorService a;

        /* synthetic */ e(w wVar, ScheduledExecutorService scheduledExecutorService, a aVar) {
            this(scheduledExecutorService);
        }

        w<TResult> a() {
            return w.this;
        }

        void b(Exception exc) {
            w.this.f14890c = exc;
        }

        void c(TResult tresult) {
            w.this.b = tresult;
        }

        void d(Runnable runnable) {
            this.a.submit(runnable);
        }

        private e(ScheduledExecutorService scheduledExecutorService) {
            this.a = scheduledExecutorService;
        }
    }

    private w() {
    }

    private static <TResult> w<TResult>.e a(ScheduledExecutorService scheduledExecutorService) {
        w wVar = new w();
        wVar.a = scheduledExecutorService;
        return new e(wVar, scheduledExecutorService, null);
    }

    public static <TResult> w<TResult> c(Callable<TResult> callable, long j2) {
        return d(callable, "YY_THREAD", j2);
    }

    public static <TResult> w<TResult> d(Callable<TResult> callable, String str, long j2) {
        return i(callable, str, j2);
    }

    private static <TResult> w<TResult> i(Callable<TResult> callable, String str, long j2) {
        e a2 = a(Executors.newSingleThreadScheduledExecutor(new b(str)));
        a2.d(new c(j2, a2, callable));
        return a2.a();
    }

    public <TCResult> w<TCResult> b(d<TResult, TCResult> dVar) {
        e a2 = a(this.a);
        a2.d(new a(a2, dVar));
        return a2.a();
    }

    public void h() {
        ScheduledExecutorService scheduledExecutorService = this.a;
        if (scheduledExecutorService == null || scheduledExecutorService.isShutdown()) {
            return;
        }
        this.a.shutdown();
    }
}

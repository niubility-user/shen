package com.jd.dynamic.lib.utils;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import androidx.annotation.MainThread;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import java.util.ArrayDeque;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes13.dex */
public abstract class q<Params, Progress, Result> {
    public static final Executor SERIAL_EXECUTOR;
    public static final Executor THREAD_POOL_EXECUTOR;

    /* renamed from: g  reason: collision with root package name */
    private static final ThreadFactory f2277g;

    /* renamed from: h  reason: collision with root package name */
    private static ThreadPoolExecutor f2278h;

    /* renamed from: i  reason: collision with root package name */
    private static LinkedBlockingQueue<Runnable> f2279i;

    /* renamed from: j  reason: collision with root package name */
    private static final RejectedExecutionHandler f2280j;

    /* renamed from: k  reason: collision with root package name */
    private static volatile Executor f2281k;

    /* renamed from: l  reason: collision with root package name */
    private static g f2282l;
    private final j<Params, Result> a;
    private final FutureTask<Result> b;

    /* renamed from: c  reason: collision with root package name */
    private volatile i f2283c;
    private final AtomicBoolean d;

    /* renamed from: e  reason: collision with root package name */
    private final AtomicBoolean f2284e;

    /* renamed from: f  reason: collision with root package name */
    private final Handler f2285f;

    /* loaded from: classes13.dex */
    static class a implements ThreadFactory {

        /* renamed from: g  reason: collision with root package name */
        private final AtomicInteger f2286g = new AtomicInteger(1);

        a() {
        }

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            return new Thread(runnable, "DynAsyncTask #" + this.f2286g.getAndIncrement());
        }
    }

    /* loaded from: classes13.dex */
    static class b implements RejectedExecutionHandler {
        b() {
        }

        @Override // java.util.concurrent.RejectedExecutionHandler
        public void rejectedExecution(Runnable runnable, ThreadPoolExecutor threadPoolExecutor) {
            synchronized (this) {
                if (q.f2278h == null) {
                    LinkedBlockingQueue unused = q.f2279i = new LinkedBlockingQueue();
                    ThreadPoolExecutor unused2 = q.f2278h = new ThreadPoolExecutor(5, 5, 3L, TimeUnit.SECONDS, q.f2279i, q.f2277g);
                    q.f2278h.allowCoreThreadTimeOut(true);
                }
            }
            q.f2278h.execute(runnable);
        }
    }

    /* loaded from: classes13.dex */
    class c extends j<Params, Result> {
        c() {
            super(null);
        }

        /*  JADX ERROR: JadxRuntimeException in pass: InlineMethods
            jadx.core.utils.exceptions.JadxRuntimeException: Failed to process method for inline: com.jd.dynamic.lib.utils.q.a(com.jd.dynamic.lib.utils.q, java.lang.Object):java.lang.Object
            	at jadx.core.dex.visitors.InlineMethods.processInvokeInsn(InlineMethods.java:74)
            	at jadx.core.dex.visitors.InlineMethods.visit(InlineMethods.java:49)
            Caused by: java.lang.NullPointerException
            	at jadx.core.dex.instructions.args.RegisterArg.sameRegAndSVar(RegisterArg.java:173)
            	at jadx.core.dex.instructions.args.InsnArg.isSameVar(InsnArg.java:269)
            	at jadx.core.dex.visitors.MarkMethodsForInline.isSyntheticAccessPattern(MarkMethodsForInline.java:118)
            	at jadx.core.dex.visitors.MarkMethodsForInline.inlineMth(MarkMethodsForInline.java:86)
            	at jadx.core.dex.visitors.MarkMethodsForInline.process(MarkMethodsForInline.java:53)
            	at jadx.core.dex.visitors.InlineMethods.processInvokeInsn(InlineMethods.java:63)
            	... 1 more
            */
        @Override // java.util.concurrent.Callable
        public Result call() {
            /*
                r4 = this;
                com.jd.dynamic.lib.utils.q r0 = com.jd.dynamic.lib.utils.q.this
                java.util.concurrent.atomic.AtomicBoolean r0 = com.jd.dynamic.lib.utils.q.e(r0)
                r1 = 1
                r0.set(r1)
                r0 = 10
                r2 = 0
                android.os.Process.setThreadPriority(r0)     // Catch: java.lang.Throwable -> L21
                com.jd.dynamic.lib.utils.q r0 = com.jd.dynamic.lib.utils.q.this     // Catch: java.lang.Throwable -> L21
                Params[] r3 = r4.f2295g     // Catch: java.lang.Throwable -> L21
                java.lang.Object r2 = r0.b(r3)     // Catch: java.lang.Throwable -> L21
                android.os.Binder.flushPendingCommands()     // Catch: java.lang.Throwable -> L21
                com.jd.dynamic.lib.utils.q r0 = com.jd.dynamic.lib.utils.q.this
                com.jd.dynamic.lib.utils.q.a(r0, r2)
                return r2
            L21:
                r0 = move-exception
                com.jd.dynamic.lib.utils.q r3 = com.jd.dynamic.lib.utils.q.this     // Catch: java.lang.Throwable -> L2c
                java.util.concurrent.atomic.AtomicBoolean r3 = com.jd.dynamic.lib.utils.q.h(r3)     // Catch: java.lang.Throwable -> L2c
                r3.set(r1)     // Catch: java.lang.Throwable -> L2c
                throw r0     // Catch: java.lang.Throwable -> L2c
            L2c:
                r0 = move-exception
                com.jd.dynamic.lib.utils.q r1 = com.jd.dynamic.lib.utils.q.this
                com.jd.dynamic.lib.utils.q.a(r1, r2)
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.jd.dynamic.lib.utils.q.c.call():java.lang.Object");
        }
    }

    /* loaded from: classes13.dex */
    class d extends FutureTask<Result> {
        d(Callable callable) {
            super(callable);
        }

        @Override // java.util.concurrent.FutureTask
        protected void done() {
            try {
                q.this.o(get());
            } catch (InterruptedException unused) {
            } catch (CancellationException unused2) {
                q.this.o(null);
            } catch (ExecutionException e2) {
                throw new RuntimeException("An error occurred while executing doInBackground()", e2.getCause());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes13.dex */
    public static /* synthetic */ class e {
        static final /* synthetic */ int[] a;

        static {
            int[] iArr = new int[i.values().length];
            a = iArr;
            try {
                iArr[i.RUNNING.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[i.FINISHED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes13.dex */
    public static class f<Data> {
        final q a;
        final Data[] b;

        f(q qVar, Data... dataArr) {
            this.a = qVar;
            this.b = dataArr;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes13.dex */
    public static class g extends Handler {
        public g(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            f fVar = (f) message.obj;
            int i2 = message.what;
            if (i2 == 1) {
                fVar.a.s(fVar.b[0]);
            } else if (i2 != 2) {
            } else {
                fVar.a.l(fVar.b);
            }
        }
    }

    /* loaded from: classes13.dex */
    private static class h implements Executor {

        /* renamed from: g  reason: collision with root package name */
        final ArrayDeque<Runnable> f2289g;

        /* renamed from: h  reason: collision with root package name */
        Runnable f2290h;

        /* loaded from: classes13.dex */
        class a implements Runnable {

            /* renamed from: g  reason: collision with root package name */
            final /* synthetic */ Runnable f2291g;

            a(Runnable runnable) {
                this.f2291g = runnable;
            }

            @Override // java.lang.Runnable
            public void run() {
                try {
                    this.f2291g.run();
                } finally {
                    h.this.a();
                }
            }
        }

        private h() {
            this.f2289g = new ArrayDeque<>();
        }

        /* synthetic */ h(a aVar) {
            this();
        }

        protected synchronized void a() {
            Runnable poll = this.f2289g.poll();
            this.f2290h = poll;
            if (poll != null) {
                q.THREAD_POOL_EXECUTOR.execute(poll);
            }
        }

        @Override // java.util.concurrent.Executor
        public synchronized void execute(Runnable runnable) {
            this.f2289g.offer(new a(runnable));
            if (this.f2290h == null) {
                a();
            }
        }
    }

    /* loaded from: classes13.dex */
    public enum i {
        PENDING,
        RUNNING,
        FINISHED
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes13.dex */
    public static abstract class j<Params, Result> implements Callable<Result> {

        /* renamed from: g  reason: collision with root package name */
        Params[] f2295g;

        private j() {
        }

        /* synthetic */ j(a aVar) {
            this();
        }
    }

    static {
        a aVar = new a();
        f2277g = aVar;
        b bVar = new b();
        f2280j = bVar;
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 20, 3L, TimeUnit.SECONDS, new SynchronousQueue(), aVar);
        threadPoolExecutor.setRejectedExecutionHandler(bVar);
        THREAD_POOL_EXECUTOR = threadPoolExecutor;
        h hVar = new h(null);
        SERIAL_EXECUTOR = hVar;
        f2281k = hVar;
    }

    public q() {
        this((Looper) null);
    }

    public q(@Nullable Handler handler) {
        this(handler != null ? handler.getLooper() : null);
    }

    public q(@Nullable Looper looper) {
        this.f2283c = i.PENDING;
        this.d = new AtomicBoolean();
        this.f2284e = new AtomicBoolean();
        this.f2285f = (looper == null || looper == Looper.getMainLooper()) ? t() : new Handler(looper);
        c cVar = new c();
        this.a = cVar;
        this.b = new d(cVar);
    }

    /*  JADX ERROR: NullPointerException in pass: MarkMethodsForInline
        java.lang.NullPointerException
        	at jadx.core.dex.instructions.args.RegisterArg.sameRegAndSVar(RegisterArg.java:173)
        	at jadx.core.dex.instructions.args.InsnArg.isSameVar(InsnArg.java:269)
        	at jadx.core.dex.visitors.MarkMethodsForInline.isSyntheticAccessPattern(MarkMethodsForInline.java:118)
        	at jadx.core.dex.visitors.MarkMethodsForInline.inlineMth(MarkMethodsForInline.java:86)
        	at jadx.core.dex.visitors.MarkMethodsForInline.process(MarkMethodsForInline.java:53)
        	at jadx.core.dex.visitors.MarkMethodsForInline.visit(MarkMethodsForInline.java:37)
        */
    static /* synthetic */ java.lang.Object a(com.jd.dynamic.lib.utils.q r0, java.lang.Object r1) {
        /*
            r0.p(r1)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.dynamic.lib.utils.q.a(com.jd.dynamic.lib.utils.q, java.lang.Object):java.lang.Object");
    }

    @MainThread
    public static void execute(Runnable runnable) {
        f2281k.execute(runnable);
    }

    static /* synthetic */ AtomicBoolean h(q qVar) {
        return qVar.d;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void o(Result result) {
        if (this.f2284e.get()) {
            return;
        }
        p(result);
    }

    private Result p(Result result) {
        u().obtainMessage(1, new f(this, result)).sendToTarget();
        return result;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void s(Result result) {
        if (isCancelled()) {
            k(result);
        } else {
            g(result);
        }
        this.f2283c = i.FINISHED;
    }

    private static Handler t() {
        g gVar;
        synchronized (q.class) {
            if (f2282l == null) {
                f2282l = new g(Looper.getMainLooper());
            }
            gVar = f2282l;
        }
        return gVar;
    }

    private Handler u() {
        return this.f2285f;
    }

    @WorkerThread
    protected abstract Result b(Params... paramsArr);

    public final boolean cancel(boolean z) {
        this.d.set(true);
        return this.b.cancel(z);
    }

    @MainThread
    public final q<Params, Progress, Result> execute(Params... paramsArr) {
        return executeOnExecutor(f2281k, paramsArr);
    }

    @MainThread
    public final q<Params, Progress, Result> executeOnExecutor(Executor executor, Params... paramsArr) {
        if (this.f2283c != i.PENDING) {
            int i2 = e.a[this.f2283c.ordinal()];
            if (i2 == 1) {
                throw new IllegalStateException("Cannot execute task: the task is already running.");
            }
            if (i2 == 2) {
                throw new IllegalStateException("Cannot execute task: the task has already been executed (a task can be executed only once)");
            }
        }
        this.f2283c = i.RUNNING;
        f();
        this.a.f2295g = paramsArr;
        executor.execute(this.b);
        return this;
    }

    @MainThread
    protected void f() {
    }

    @MainThread
    protected void g(Result result) {
    }

    public final Result get() {
        return this.b.get();
    }

    public final Result get(long j2, TimeUnit timeUnit) {
        return this.b.get(j2, timeUnit);
    }

    public final i getStatus() {
        return this.f2283c;
    }

    @MainThread
    protected void i() {
    }

    public final boolean isCancelled() {
        return this.d.get();
    }

    @MainThread
    protected void k(Result result) {
        i();
    }

    @MainThread
    protected void l(Progress... progressArr) {
    }
}

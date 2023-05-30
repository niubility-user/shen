package com.jingdong.jdma.c;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* loaded from: classes12.dex */
public class d {

    /* renamed from: c  reason: collision with root package name */
    private static d f12673c;
    private ThreadPoolExecutor a;
    private ThreadPoolExecutor b;

    /* loaded from: classes12.dex */
    class a implements ThreadFactory {
        a(d dVar) {
        }

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            Thread thread = new Thread(runnable);
            thread.setName("JDMAHttpRequestBusinessThread");
            return thread;
        }
    }

    /* loaded from: classes12.dex */
    class b extends ThreadPoolExecutor.DiscardPolicy {
        b(d dVar) {
        }

        @Override // java.util.concurrent.ThreadPoolExecutor.DiscardPolicy, java.util.concurrent.RejectedExecutionHandler
        public void rejectedExecution(Runnable runnable, ThreadPoolExecutor threadPoolExecutor) {
            if (runnable instanceof e) {
                ((e) runnable).a();
            }
        }
    }

    /* loaded from: classes12.dex */
    class c implements ThreadFactory {
        c(d dVar) {
        }

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            Thread thread = new Thread(runnable);
            thread.setName("JDMAHttpRequestOtherThread");
            return thread;
        }
    }

    /* renamed from: com.jingdong.jdma.c.d$d  reason: collision with other inner class name */
    /* loaded from: classes12.dex */
    class C0489d extends ThreadPoolExecutor.DiscardPolicy {
        C0489d(d dVar) {
        }

        @Override // java.util.concurrent.ThreadPoolExecutor.DiscardPolicy, java.util.concurrent.RejectedExecutionHandler
        public void rejectedExecution(Runnable runnable, ThreadPoolExecutor threadPoolExecutor) {
            if (runnable instanceof e) {
                ((e) runnable).a();
            }
        }
    }

    /* loaded from: classes12.dex */
    public interface e extends Runnable {
        void a();
    }

    private d() {
        TimeUnit timeUnit = TimeUnit.SECONDS;
        this.a = new ThreadPoolExecutor(0, 4, 60L, timeUnit, new LinkedBlockingQueue(4), new a(this), new b(this));
        this.b = new ThreadPoolExecutor(0, 2, 60L, timeUnit, new LinkedBlockingQueue(5), new c(this), new C0489d(this));
    }

    public static d a() {
        if (f12673c == null) {
            synchronized (d.class) {
                if (f12673c == null) {
                    f12673c = new d();
                }
            }
        }
        return f12673c;
    }

    public void b() {
        try {
            this.a.shutdown();
            this.b.shutdown();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void a(int i2, Runnable runnable) {
        if (i2 >= 2) {
            return;
        }
        try {
            if (i2 == 0) {
                this.a.execute(runnable);
            } else if (i2 != 1) {
            } else {
                this.b.execute(runnable);
            }
        } catch (Throwable unused) {
        }
    }
}

package com.jingdong.sdk.dialingtest.c.d;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes7.dex */
public class a {

    /* renamed from: f  reason: collision with root package name */
    private static a f14746f;
    private ThreadPoolExecutor a;
    private ThreadPoolExecutor b;

    /* renamed from: c  reason: collision with root package name */
    private final int f14747c = Math.max(2, Math.min(Runtime.getRuntime().availableProcessors() - 1, 4));
    private final TimeUnit d = TimeUnit.MINUTES;

    /* renamed from: e  reason: collision with root package name */
    private List<Future<?>> f14748e = new ArrayList(20);

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: com.jingdong.sdk.dialingtest.c.d.a$a  reason: collision with other inner class name */
    /* loaded from: classes7.dex */
    public static class ThreadFactoryC0713a implements ThreadFactory {

        /* renamed from: g  reason: collision with root package name */
        private final AtomicInteger f14749g = new AtomicInteger(1);

        ThreadFactoryC0713a() {
        }

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            Thread thread = new Thread(runnable, "dialingTest-t" + this.f14749g.getAndIncrement());
            if (thread.isDaemon()) {
                thread.setDaemon(false);
            }
            return thread;
        }
    }

    private a() {
    }

    private void a() {
        this.a = new ThreadPoolExecutor(this.f14747c, Integer.MAX_VALUE, 1L, this.d, new LinkedBlockingDeque(256), new ThreadFactoryC0713a());
    }

    private void c() {
        this.b = new ThreadPoolExecutor(1, 1, 1L, this.d, new LinkedBlockingDeque(256), new ThreadFactoryC0713a());
    }

    public static a e() {
        if (f14746f == null) {
            synchronized (a.class) {
                if (f14746f == null) {
                    f14746f = new a();
                }
            }
        }
        return f14746f;
    }

    public void b(Runnable runnable) {
        if (runnable == null) {
            return;
        }
        try {
            ThreadPoolExecutor threadPoolExecutor = this.a;
            if (threadPoolExecutor == null || threadPoolExecutor.isShutdown()) {
                a();
            }
            Future<?> submit = this.a.submit(runnable);
            synchronized (this) {
                List<Future<?>> list = this.f14748e;
                if (list != null) {
                    list.add(submit);
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void d(Runnable runnable) {
        if (runnable == null) {
            return;
        }
        try {
            ThreadPoolExecutor threadPoolExecutor = this.b;
            if (threadPoolExecutor == null || threadPoolExecutor.isShutdown()) {
                c();
            }
            Future<?> submit = this.b.submit(runnable);
            synchronized (this) {
                List<Future<?>> list = this.f14748e;
                if (list != null) {
                    list.add(submit);
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public synchronized void f() {
        List<Future<?>> list = this.f14748e;
        if (list != null) {
            list.clear();
        }
    }

    public synchronized void g() {
        List<Future<?>> list = this.f14748e;
        if (list != null && list.size() > 0) {
            for (Future<?> future : this.f14748e) {
                if (future != null) {
                    try {
                        com.jingdong.sdk.dialingtest.c.e.a.a("ThreadPoolManager", "cancel " + future.toString() + ": " + future.cancel(false));
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
            }
            this.f14748e.clear();
        }
    }
}

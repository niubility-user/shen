package com.tencent.mapsdk.internal;

import android.text.TextUtils;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes9.dex */
public class g7 {
    private static final a a = new a("sw");
    private static final a b = new a("lw");

    /* renamed from: c  reason: collision with root package name */
    private static final a f16596c = new a("mlw");
    private static final a d = new a("qw");

    /* renamed from: e  reason: collision with root package name */
    private static final a f16597e = new a("mqw");

    /* renamed from: f  reason: collision with root package name */
    private static ScheduledThreadPoolExecutor f16598f;

    /* renamed from: g  reason: collision with root package name */
    private static ThreadPoolExecutor f16599g;

    /* renamed from: h  reason: collision with root package name */
    private static ThreadPoolExecutor f16600h;

    /* renamed from: i  reason: collision with root package name */
    private static ThreadPoolExecutor f16601i;

    /* renamed from: j  reason: collision with root package name */
    private static ThreadPoolExecutor f16602j;

    /* loaded from: classes9.dex */
    public static class a implements ThreadFactory {
        private String a;
        private final AtomicInteger b = new AtomicInteger(0);

        public a(String str) {
            this.a = str;
            if (TextUtils.isEmpty(str)) {
                this.a = "def";
            }
        }

        public a a() {
            this.b.set(0);
            return this;
        }

        public int b() {
            return this.b.get();
        }

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            Thread thread = new Thread(runnable, "tms-" + this.a + "-" + this.b.incrementAndGet());
            StringBuilder sb = new StringBuilder();
            sb.append("\u521b\u5efa\u7ebf\u7a0b\uff1a");
            sb.append(thread);
            ma.a(sb.toString());
            return thread;
        }
    }

    public static String a() {
        Thread currentThread = Thread.currentThread();
        return currentThread + ";" + currentThread.getState() + ";interrupted=" + Thread.interrupted() + "|" + currentThread.isInterrupted();
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x001c  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0028  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static synchronized boolean a(ExecutorService executorService) {
        boolean z;
        boolean z2;
        synchronized (g7.class) {
            boolean z3 = false;
            if (executorService != null) {
                try {
                    if (!executorService.isShutdown() && !executorService.isTerminated()) {
                        z = true;
                        if (executorService instanceof ThreadPoolExecutor) {
                            z3 = z;
                        } else if (z) {
                            if (!((ThreadPoolExecutor) executorService).isTerminating()) {
                                z3 = true;
                            }
                        }
                        z2 = !z3;
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
            z = false;
            if (executorService instanceof ThreadPoolExecutor) {
            }
            z2 = !z3;
        }
        return z2;
    }

    public static synchronized ThreadPoolExecutor b() {
        ThreadPoolExecutor threadPoolExecutor;
        synchronized (g7.class) {
            if (a(f16600h)) {
                int max = Math.max(4, Runtime.getRuntime().availableProcessors());
                f16600h = new ThreadPoolExecutor(max / 2, max, 2L, TimeUnit.SECONDS, new LinkedBlockingQueue(), f16597e.a(), new ThreadPoolExecutor.DiscardPolicy());
            }
            threadPoolExecutor = f16600h;
        }
        return threadPoolExecutor;
    }

    public static synchronized ThreadPoolExecutor c() {
        ThreadPoolExecutor threadPoolExecutor;
        synchronized (g7.class) {
            if (a(f16602j)) {
                f16602j = new ThreadPoolExecutor(0, Math.max(4, Runtime.getRuntime().availableProcessors()) / 2, 5L, TimeUnit.SECONDS, new LinkedBlockingQueue(), f16596c.a(), new ThreadPoolExecutor.DiscardPolicy());
            }
            threadPoolExecutor = f16602j;
        }
        return threadPoolExecutor;
    }

    public static synchronized ScheduledThreadPoolExecutor d() {
        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor;
        synchronized (g7.class) {
            if (a(f16598f)) {
                f16598f = new ScheduledThreadPoolExecutor(2, a.a(), new ThreadPoolExecutor.DiscardPolicy());
            }
            scheduledThreadPoolExecutor = f16598f;
        }
        return scheduledThreadPoolExecutor;
    }

    public static synchronized ThreadPoolExecutor e() {
        ThreadPoolExecutor threadPoolExecutor;
        synchronized (g7.class) {
            if (a(f16599g)) {
                f16599g = new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue(), d.a(), new ThreadPoolExecutor.DiscardPolicy());
            }
            threadPoolExecutor = f16599g;
        }
        return threadPoolExecutor;
    }

    public static synchronized ThreadPoolExecutor f() {
        ThreadPoolExecutor threadPoolExecutor;
        synchronized (g7.class) {
            if (a(f16601i)) {
                f16601i = new ThreadPoolExecutor(0, 1, 2L, TimeUnit.SECONDS, new LinkedBlockingQueue(), b.a(), new ThreadPoolExecutor.DiscardPolicy());
            }
            threadPoolExecutor = f16601i;
        }
        return threadPoolExecutor;
    }
}

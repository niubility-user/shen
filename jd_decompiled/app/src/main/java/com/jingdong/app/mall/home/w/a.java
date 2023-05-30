package com.jingdong.app.mall.home.w;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* loaded from: classes4.dex */
public class a {
    private static final ThreadFactory a;
    private static final ExecutorService b;

    /* renamed from: c  reason: collision with root package name */
    private static final ThreadFactory f11006c;
    private static final ExecutorService d;

    /* renamed from: com.jingdong.app.mall.home.w.a$a  reason: collision with other inner class name */
    /* loaded from: classes4.dex */
    class ThreadFactoryC0335a implements ThreadFactory {
        ThreadFactoryC0335a() {
        }

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            return new Thread(runnable, "HOME_NORMAL");
        }
    }

    /* loaded from: classes4.dex */
    class b implements ThreadFactory {
        b() {
        }

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            return new Thread(runnable, "HOME_LONG");
        }
    }

    static {
        ThreadFactoryC0335a threadFactoryC0335a = new ThreadFactoryC0335a();
        a = threadFactoryC0335a;
        b = new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue(), threadFactoryC0335a);
        b bVar = new b();
        f11006c = bVar;
        d = new ThreadPoolExecutor(1, 5, 30L, TimeUnit.SECONDS, new LinkedBlockingQueue(), bVar);
    }

    public static void a(com.jingdong.app.mall.home.o.a.b bVar) {
        b(bVar, true);
    }

    public static void b(com.jingdong.app.mall.home.o.a.b bVar, boolean z) {
        if (z) {
            d.submit(bVar);
        } else {
            b.submit(bVar);
        }
    }
}

package com.hihonor.push.sdk;

import android.os.Handler;
import android.os.Looper;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* loaded from: classes12.dex */
public final class h {
    public static final h d = new h();
    public volatile Executor a;
    public volatile ExecutorService b;

    /* renamed from: c  reason: collision with root package name */
    public final Object f1096c = new Object();

    /* loaded from: classes12.dex */
    public static class a implements Executor {
        @Override // java.util.concurrent.Executor
        public final void execute(Runnable runnable) {
            new Handler(Looper.getMainLooper()).post(runnable);
        }
    }

    public static Executor a() {
        h hVar = d;
        if (hVar.a == null) {
            synchronized (hVar.f1096c) {
                if (hVar.a == null) {
                    hVar.a = new a();
                }
            }
        }
        return hVar.a;
    }

    public static void b(Runnable runnable) {
        if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
            runnable.run();
        } else {
            a().execute(runnable);
        }
    }

    public static ExecutorService d() {
        return d.c();
    }

    public final ExecutorService c() {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 10, 1L, TimeUnit.SECONDS, new LinkedBlockingQueue());
        threadPoolExecutor.allowCoreThreadTimeOut(true);
        return threadPoolExecutor;
    }
}

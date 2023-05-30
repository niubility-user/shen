package com.huawei.hmf.tasks.a;

import android.os.Handler;
import android.os.Looper;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* loaded from: classes12.dex */
public final class a {
    private static final a b = new a();

    /* renamed from: c  reason: collision with root package name */
    private static final int f1155c;
    private static final int d;

    /* renamed from: e  reason: collision with root package name */
    private static final int f1156e;
    private final Executor a = new ExecutorC0059a(0);

    /* renamed from: com.huawei.hmf.tasks.a.a$a  reason: collision with other inner class name */
    /* loaded from: classes12.dex */
    static class ExecutorC0059a implements Executor {
        private ExecutorC0059a() {
        }

        /* synthetic */ ExecutorC0059a(byte b) {
            this();
        }

        @Override // java.util.concurrent.Executor
        public final void execute(Runnable runnable) {
            new Handler(Looper.getMainLooper()).post(runnable);
        }
    }

    static {
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        f1155c = availableProcessors;
        d = availableProcessors + 1;
        f1156e = (availableProcessors * 2) + 1;
    }

    public static ExecutorService a() {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(d, f1156e, 1L, TimeUnit.SECONDS, new LinkedBlockingQueue());
        threadPoolExecutor.allowCoreThreadTimeOut(true);
        return threadPoolExecutor;
    }

    public static Executor b() {
        return b.a;
    }
}

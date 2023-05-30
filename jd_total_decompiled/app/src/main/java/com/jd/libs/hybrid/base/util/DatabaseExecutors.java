package com.jd.libs.hybrid.base.util;

import android.os.Handler;
import android.os.Looper;
import java.util.concurrent.Executor;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* loaded from: classes16.dex */
public class DatabaseExecutors {
    private Executor a;
    private Executor b;

    /* loaded from: classes16.dex */
    private static class MainThreadExecutor implements Executor {

        /* renamed from: g  reason: collision with root package name */
        private Handler f5898g;

        private MainThreadExecutor() {
            this.f5898g = new Handler(Looper.getMainLooper());
        }

        @Override // java.util.concurrent.Executor
        public void execute(Runnable runnable) {
            this.f5898g.post(runnable);
        }
    }

    /* loaded from: classes16.dex */
    private static class SingleHolder {
        private static DatabaseExecutors a = new DatabaseExecutors();

        private SingleHolder() {
        }
    }

    private static ThreadFactory a(final String str, final boolean z) {
        return new ThreadFactory() { // from class: com.jd.libs.hybrid.base.util.DatabaseExecutors.1
            @Override // java.util.concurrent.ThreadFactory
            public Thread newThread(Runnable runnable) {
                Thread thread = new Thread(runnable, str);
                thread.setDaemon(z);
                return thread;
            }
        };
    }

    public static DatabaseExecutors getInstance() {
        return SingleHolder.a;
    }

    public static boolean isInIoThread() {
        return Thread.currentThread().getName().startsWith("JDHybridIoThread");
    }

    public Executor mainThread() {
        return this.b;
    }

    public void runOnIoThread(Runnable runnable) {
        if (runnable == null) {
            return;
        }
        if (isInIoThread()) {
            runnable.run();
        } else {
            this.a.execute(runnable);
        }
    }

    public Executor threadIO() {
        return this.a;
    }

    private DatabaseExecutors() {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, Integer.MAX_VALUE, 10L, TimeUnit.SECONDS, new SynchronousQueue(), a("JDHybridIoThread", false));
        threadPoolExecutor.allowCoreThreadTimeOut(true);
        this.a = threadPoolExecutor;
        this.b = new MainThreadExecutor();
    }
}

package com.jingdong.jdexreport.d;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes.dex */
public class a {
    private static volatile a b;
    private volatile ThreadPoolExecutor a = null;

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: com.jingdong.jdexreport.d.a$a  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public static class ThreadFactoryC0486a implements ThreadFactory {
        private final AtomicInteger a = new AtomicInteger(1);

        ThreadFactoryC0486a() {
        }

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            Thread thread = new Thread(runnable, "exceptionReporter-t" + this.a.getAndIncrement());
            if (thread.isDaemon()) {
                thread.setDaemon(false);
            }
            return thread;
        }
    }

    private a() {
        a();
    }

    public static a b() {
        if (b == null) {
            synchronized (a.class) {
                if (b == null) {
                    b = new a();
                }
            }
        }
        return b;
    }

    public boolean a(Runnable runnable) {
        if (runnable == null) {
            return false;
        }
        if (this.a == null || this.a.isShutdown()) {
            synchronized (this) {
                if (this.a == null || this.a.isShutdown()) {
                    a();
                }
            }
        }
        try {
            this.a.execute(runnable);
            return true;
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    private void a() {
        this.a = new ThreadPoolExecutor(0, 1, 3L, TimeUnit.MINUTES, new LinkedBlockingDeque(256), new ThreadFactoryC0486a(), new ThreadPoolExecutor.DiscardPolicy());
    }
}

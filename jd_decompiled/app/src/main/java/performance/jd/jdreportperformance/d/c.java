package performance.jd.jdreportperformance.d;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes.dex */
public class c {
    private static volatile c b;
    private volatile ThreadPoolExecutor a = null;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class a implements ThreadFactory {
        private final AtomicInteger a = new AtomicInteger(1);

        a() {
        }

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            int andIncrement = this.a.getAndIncrement();
            performance.jd.jdreportperformance.b.b.b.b("ThreadPoolManager", "new thread , index: " + andIncrement);
            Thread thread = new Thread(runnable, "PerformanceReporter-t" + andIncrement);
            if (thread.isDaemon()) {
                thread.setDaemon(false);
            }
            return thread;
        }
    }

    private c() {
        a();
    }

    public static c b() {
        if (b == null) {
            synchronized (c.class) {
                if (b == null) {
                    b = new c();
                }
            }
        }
        return b;
    }

    public boolean a(Runnable runnable) {
        if (runnable == null) {
            return false;
        }
        performance.jd.jdreportperformance.b.b.b.b("ThreadPoolManager", "execute runnable : " + runnable.toString());
        if (this.a == null || this.a.isShutdown()) {
            synchronized (this) {
                if (this.a == null || this.a.isShutdown()) {
                    a();
                }
            }
        }
        try {
            this.a.execute(runnable);
            c();
            return true;
        } catch (Throwable th) {
            th.printStackTrace();
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void c() {
        try {
            performance.jd.jdreportperformance.b.b.b.b("ThreadPoolManager", "activeCount " + this.a.getActiveCount());
            performance.jd.jdreportperformance.b.b.b.b("ThreadPoolManager", "queue size  " + this.a.getQueue().size());
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private void a() {
        this.a = new ThreadPoolExecutor(5, 10, 2L, TimeUnit.MINUTES, new LinkedBlockingDeque(256), new a(), new ThreadPoolExecutor.AbortPolicy());
    }
}

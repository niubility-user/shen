package com.jingdong.sdk.jdcrashreport.b;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/* loaded from: classes7.dex */
public class d {
    private static d b = a();
    private ScheduledExecutorService a;

    /* loaded from: classes7.dex */
    class a implements ThreadFactory {
        a(d dVar) {
        }

        @Override // java.util.concurrent.ThreadFactory
        public final Thread newThread(Runnable runnable) {
            Thread thread = new Thread(runnable);
            thread.setName("YY_THREAD");
            return thread;
        }
    }

    private d() {
        ScheduledExecutorService newScheduledThreadPool = Executors.newScheduledThreadPool(5, new a(this));
        this.a = newScheduledThreadPool;
        if (newScheduledThreadPool == null || newScheduledThreadPool.isShutdown()) {
            r.b("[AsyncTask]", "ScheduledExecutorService is not available!");
        }
    }

    private static synchronized d a() {
        d dVar;
        synchronized (d.class) {
            if (b == null) {
                b = new d();
            }
            dVar = b;
        }
        return dVar;
    }

    public static synchronized ScheduledFuture<?> b(Runnable runnable, long j2, long j3) {
        synchronized (d.class) {
            if (!d()) {
                r.b("[AsyncTask]", "Async handler was Closed, should not post task.");
                return null;
            } else if (runnable == null) {
                r.b("[AsyncTask]", "AsyncTask input is null.");
                return null;
            } else {
                long j4 = j2 > 0 ? j2 : 0L;
                long j5 = j3 > 0 ? j3 : 0L;
                r.d("[AsyncTask]", "Post At Fixed Rate(time: %dms %dms) task: %s", Long.valueOf(j4), Long.valueOf(j5), runnable.getClass().getName());
                try {
                    return a().a.scheduleAtFixedRate(runnable, j4, j5, TimeUnit.MILLISECONDS);
                } catch (Throwable unused) {
                    return null;
                }
            }
        }
    }

    public static synchronized boolean c(Runnable runnable) {
        synchronized (d.class) {
            if (!d()) {
                r.b("[AsyncTask]", "AsyncTask handler was Closed, should not post task.");
                return false;
            } else if (runnable == null) {
                r.b("[AsyncTask]", "AsyncTask input is null.");
                return false;
            } else {
                r.d("[AsyncTask]", "Post normal task: %s", runnable.getClass().getName());
                try {
                    a().a.execute(runnable);
                    return true;
                } catch (Throwable unused) {
                    return false;
                }
            }
        }
    }

    private static synchronized boolean d() {
        boolean z;
        synchronized (d.class) {
            if (a().a != null) {
                z = a().a.isShutdown() ? false : true;
            }
        }
        return z;
    }
}

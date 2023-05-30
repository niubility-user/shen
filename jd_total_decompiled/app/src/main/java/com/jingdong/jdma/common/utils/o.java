package com.jingdong.jdma.common.utils;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* loaded from: classes12.dex */
public class o {
    private static o b;
    private ThreadPoolExecutor a = new ThreadPoolExecutor(3, 5, 60, TimeUnit.SECONDS, new LinkedBlockingQueue(Integer.MAX_VALUE), new a(this), new b(this));

    /* loaded from: classes12.dex */
    class a implements ThreadFactory {
        a(o oVar) {
        }

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            Thread thread = new Thread(runnable);
            thread.setName("ThreadPoolManager");
            return thread;
        }
    }

    /* loaded from: classes12.dex */
    class b extends ThreadPoolExecutor.DiscardPolicy {
        b(o oVar) {
        }

        @Override // java.util.concurrent.ThreadPoolExecutor.DiscardPolicy, java.util.concurrent.RejectedExecutionHandler
        public void rejectedExecution(Runnable runnable, ThreadPoolExecutor threadPoolExecutor) {
        }
    }

    private o() {
    }

    public static o a() {
        if (b == null) {
            synchronized (o.class) {
                if (b == null) {
                    b = new o();
                }
            }
        }
        return b;
    }

    public void b() {
        try {
            this.a.shutdown();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void a(Runnable runnable) {
        try {
            this.a.execute(runnable);
        } catch (Throwable unused) {
        }
    }
}

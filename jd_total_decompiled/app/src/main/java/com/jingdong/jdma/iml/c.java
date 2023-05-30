package com.jingdong.jdma.iml;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* loaded from: classes12.dex */
public class c {
    private static c b;
    private ThreadPoolExecutor a = new ThreadPoolExecutor(4, 6, 60, TimeUnit.SECONDS, new LinkedBlockingQueue(100), new a(this), new b(this));

    /* loaded from: classes12.dex */
    class a implements ThreadFactory {
        a(c cVar) {
        }

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            Thread thread = new Thread(runnable);
            thread.setName("JDMARecordThread");
            return thread;
        }
    }

    /* loaded from: classes12.dex */
    class b extends ThreadPoolExecutor.DiscardPolicy {
        b(c cVar) {
        }

        @Override // java.util.concurrent.ThreadPoolExecutor.DiscardPolicy, java.util.concurrent.RejectedExecutionHandler
        public void rejectedExecution(Runnable runnable, ThreadPoolExecutor threadPoolExecutor) {
            super.rejectedExecution(runnable, threadPoolExecutor);
        }
    }

    private c() {
    }

    public static c a() {
        if (b == null) {
            synchronized (c.class) {
                if (b == null) {
                    b = new c();
                }
            }
        }
        return b;
    }

    public void a(Runnable runnable) {
        try {
            this.a.execute(runnable);
        } catch (Throwable unused) {
        }
    }
}

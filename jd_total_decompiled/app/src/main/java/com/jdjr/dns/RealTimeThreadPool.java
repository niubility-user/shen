package com.jdjr.dns;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes18.dex */
public class RealTimeThreadPool {
    private static ExecutorService executorService;
    private static final Object lock = new Object();
    private static RealTimeThreadPool mInstance;

    /* loaded from: classes18.dex */
    static class DefaultThreadFactory implements ThreadFactory {
        private static final AtomicInteger poolNumber = new AtomicInteger(1);
        private final ThreadGroup group;
        private final String namePrefix;
        private final AtomicInteger threadNumber = new AtomicInteger(1);

        DefaultThreadFactory() {
            SecurityManager securityManager = System.getSecurityManager();
            this.group = securityManager != null ? securityManager.getThreadGroup() : Thread.currentThread().getThreadGroup();
            this.namePrefix = "pool-" + poolNumber.getAndIncrement() + "-thread-";
        }

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            Thread thread = new Thread(this.group, runnable, this.namePrefix + this.threadNumber.getAndIncrement(), 0L);
            if (thread.isDaemon()) {
                thread.setDaemon(false);
            }
            if (thread.getPriority() != 5) {
                thread.setPriority(5);
            }
            return thread;
        }
    }

    public static RealTimeThreadPool getInstance() {
        if (mInstance == null) {
            synchronized (lock) {
                if (mInstance == null) {
                    mInstance = new RealTimeThreadPool();
                    executorService = new ThreadPoolExecutor(1, 15, 1L, TimeUnit.SECONDS, new SynchronousQueue(), new DefaultThreadFactory(), new ThreadPoolExecutor.DiscardPolicy());
                }
            }
        }
        return mInstance;
    }

    public void execute(Runnable runnable) {
        ExecutorService executorService2 = executorService;
        if (executorService2 != null && !executorService2.isShutdown()) {
            executorService.execute(runnable);
        } else {
            new Thread(runnable).start();
        }
    }
}

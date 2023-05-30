package com.jingdong.common.httpdns;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* loaded from: classes5.dex */
public class GlobalExecutorService {
    private static final int THREAD_NUM = 3;
    private static ExecutorService cachedThreadPool;
    private static ThreadPoolExecutor executorService;
    private static ExecutorService lightThreadExecutorService;

    public static synchronized ExecutorService executorService() {
        ThreadPoolExecutor threadPoolExecutor;
        synchronized (GlobalExecutorService.class) {
            if (executorService == null) {
                ThreadPoolExecutor threadPoolExecutor2 = new ThreadPoolExecutor(5, Integer.MAX_VALUE, 5L, TimeUnit.SECONDS, new PriorityBlockingQueue(), ThreadUtil.threadFactory("HttpGroupAdapter", false));
                executorService = threadPoolExecutor2;
                threadPoolExecutor2.allowsCoreThreadTimeOut();
            }
            threadPoolExecutor = executorService;
        }
        return threadPoolExecutor;
    }

    public static synchronized ExecutorService lightExecutorService() {
        ExecutorService executorService2;
        synchronized (GlobalExecutorService.class) {
            if (lightThreadExecutorService == null) {
                lightThreadExecutorService = Executors.newFixedThreadPool(3);
            }
            executorService2 = lightThreadExecutorService;
        }
        return executorService2;
    }

    public static synchronized ExecutorService newCachedThreadPool() {
        ExecutorService executorService2;
        synchronized (GlobalExecutorService.class) {
            if (cachedThreadPool == null) {
                cachedThreadPool = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, new SynchronousQueue());
            }
            executorService2 = cachedThreadPool;
        }
        return executorService2;
    }
}

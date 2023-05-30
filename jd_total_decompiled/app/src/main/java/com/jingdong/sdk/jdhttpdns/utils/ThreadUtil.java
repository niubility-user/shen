package com.jingdong.sdk.jdhttpdns.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* loaded from: classes7.dex */
public class ThreadUtil {
    private static final TimeUnit TIME_UNIT;
    private static final ExecutorService executorService;
    private static final ThreadFactory threadFactory;

    static {
        TimeUnit timeUnit = TimeUnit.SECONDS;
        TIME_UNIT = timeUnit;
        ThreadFactory threadFactory2 = new ThreadFactory() { // from class: com.jingdong.sdk.jdhttpdns.utils.ThreadUtil.1
            @Override // java.util.concurrent.ThreadFactory
            public Thread newThread(Runnable runnable) {
                Thread thread = new Thread(runnable);
                thread.setName("httpdns worker");
                thread.setDaemon(false);
                return thread;
            }
        };
        threadFactory = threadFactory2;
        executorService = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 1L, timeUnit, new SynchronousQueue(), threadFactory2);
    }

    public static ExecutorService getExecutorService() {
        return executorService;
    }
}

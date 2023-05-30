package com.jingdong.common.XView2.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* loaded from: classes5.dex */
public class XView2SubThreadCtrl {
    private static final ThreadFactory LONG_FACTORY;
    private static final ExecutorService LONG_TIME_THREAD_POOL;
    private static final ThreadFactory NORMAL_FACTORY;
    private static final ExecutorService SINGLE_THREAD_POOL;

    static {
        ThreadFactory threadFactory = new ThreadFactory() { // from class: com.jingdong.common.XView2.utils.XView2SubThreadCtrl.1
            @Override // java.util.concurrent.ThreadFactory
            public Thread newThread(Runnable runnable) {
                return new Thread(runnable, "XVIEW2_NORMAL");
            }
        };
        NORMAL_FACTORY = threadFactory;
        SINGLE_THREAD_POOL = new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue(), threadFactory);
        ThreadFactory threadFactory2 = new ThreadFactory() { // from class: com.jingdong.common.XView2.utils.XView2SubThreadCtrl.2
            @Override // java.util.concurrent.ThreadFactory
            public Thread newThread(Runnable runnable) {
                return new Thread(runnable, "XVIEW2_LONG");
            }
        };
        LONG_FACTORY = threadFactory2;
        LONG_TIME_THREAD_POOL = new ThreadPoolExecutor(1, 5, 30L, TimeUnit.SECONDS, new LinkedBlockingQueue(), threadFactory2);
    }

    public static void addLongTimeTask(BaseRunnable baseRunnable) {
        addTask(baseRunnable, true);
    }

    public static void addTask(BaseRunnable baseRunnable, boolean z) {
        if (z) {
            LONG_TIME_THREAD_POOL.submit(baseRunnable);
        } else {
            SINGLE_THREAD_POOL.submit(baseRunnable);
        }
    }
}

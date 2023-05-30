package com.jd.skin.lib.manager;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* loaded from: classes18.dex */
public class ThreadPoolManager {
    public static final String DEFAULT_SINGLE_POOL_NAME = "DEFAULT_SINGLE_POOL_NAME";
    private static ThreadPoolProxy mDownloadPool;
    private static ThreadPoolProxy mLongPool;
    private static ThreadPoolProxy mShortPool;
    private static Object mLongLock = new Object();
    private static Object mShortLock = new Object();
    private static Object mDownloadLock = new Object();
    private static Map<String, ThreadPoolProxy> mMap = new HashMap();
    private static Object mSingleLock = new Object();

    /* loaded from: classes18.dex */
    public static class ThreadPoolProxy {
        private int mCorePoolSize;
        private long mKeepAliveTime;
        private int mMaximumPoolSize;
        private ThreadPoolExecutor mPool;

        public synchronized void cancel(Runnable runnable) {
            ThreadPoolExecutor threadPoolExecutor = this.mPool;
            if (threadPoolExecutor != null && (!threadPoolExecutor.isShutdown() || this.mPool.isTerminating())) {
                this.mPool.getQueue().remove(runnable);
            }
        }

        public synchronized boolean contains(Runnable runnable) {
            ThreadPoolExecutor threadPoolExecutor = this.mPool;
            if (threadPoolExecutor == null || (threadPoolExecutor.isShutdown() && !this.mPool.isTerminating())) {
                return false;
            }
            return this.mPool.getQueue().contains(runnable);
        }

        public synchronized void execute(Runnable runnable) {
            if (runnable == null) {
                return;
            }
            ThreadPoolExecutor threadPoolExecutor = this.mPool;
            if (threadPoolExecutor == null || threadPoolExecutor.isShutdown()) {
                this.mPool = new ThreadPoolExecutor(this.mCorePoolSize, this.mMaximumPoolSize, this.mKeepAliveTime, TimeUnit.MILLISECONDS, new LinkedBlockingQueue(), Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());
            }
            this.mPool.getCorePoolSize();
            this.mPool.execute(runnable);
        }

        public synchronized void shutdown() {
            ThreadPoolExecutor threadPoolExecutor = this.mPool;
            if (threadPoolExecutor != null && (!threadPoolExecutor.isShutdown() || this.mPool.isTerminating())) {
                this.mPool.shutdownNow();
            }
        }

        public void stop() {
            ThreadPoolExecutor threadPoolExecutor = this.mPool;
            if (threadPoolExecutor != null) {
                if (!threadPoolExecutor.isShutdown() || this.mPool.isTerminating()) {
                    this.mPool.shutdownNow();
                }
            }
        }

        private ThreadPoolProxy(int i2, int i3, long j2) {
            this.mCorePoolSize = i2;
            this.mMaximumPoolSize = i3;
            this.mKeepAliveTime = j2;
        }
    }

    public static ThreadPoolProxy getDownloadPool() {
        ThreadPoolProxy threadPoolProxy;
        synchronized (mDownloadLock) {
            if (mDownloadPool == null) {
                mDownloadPool = new ThreadPoolProxy(3, 3, 5L);
            }
            threadPoolProxy = mDownloadPool;
        }
        return threadPoolProxy;
    }

    public static ThreadPoolProxy getLongPool() {
        ThreadPoolProxy threadPoolProxy;
        synchronized (mLongLock) {
            if (mLongPool == null) {
                mLongPool = new ThreadPoolProxy(5, 5, 5L);
            }
            threadPoolProxy = mLongPool;
        }
        return threadPoolProxy;
    }

    public static ThreadPoolProxy getShortPool() {
        ThreadPoolProxy threadPoolProxy;
        synchronized (mShortLock) {
            if (mShortPool == null) {
                mShortPool = new ThreadPoolProxy(2, 2, 5L);
            }
            threadPoolProxy = mShortPool;
        }
        return threadPoolProxy;
    }

    public static ThreadPoolProxy getSinglePool() {
        return getSinglePool(DEFAULT_SINGLE_POOL_NAME);
    }

    public static ThreadPoolProxy getSinglePool(String str) {
        ThreadPoolProxy threadPoolProxy;
        synchronized (mSingleLock) {
            threadPoolProxy = mMap.get(str);
            if (threadPoolProxy == null) {
                threadPoolProxy = new ThreadPoolProxy(1, 1, 5L);
                mMap.put(str, threadPoolProxy);
            }
        }
        return threadPoolProxy;
    }
}

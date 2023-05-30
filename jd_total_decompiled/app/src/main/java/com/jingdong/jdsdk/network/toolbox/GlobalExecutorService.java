package com.jingdong.jdsdk.network.toolbox;

import com.jingdong.jdsdk.utils.e;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* loaded from: classes14.dex */
public class GlobalExecutorService {

    /* loaded from: classes14.dex */
    private static class DeliveryExecutorServiceHolder {
        public static ThreadPoolExecutor deliveryExecutorService;

        static {
            ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(3, Integer.MAX_VALUE, 5L, TimeUnit.SECONDS, new LinkedBlockingDeque(), e.a("callback-delivery", false));
            deliveryExecutorService = threadPoolExecutor;
            threadPoolExecutor.allowCoreThreadTimeOut(true);
        }

        private DeliveryExecutorServiceHolder() {
        }
    }

    /* loaded from: classes14.dex */
    private static class DownloadExecutorServiceHolder {
        public static ThreadPoolExecutor downloadExecutorService;

        static {
            ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, Integer.MAX_VALUE, 15L, TimeUnit.SECONDS, new LinkedBlockingDeque(), e.a("JDFileDownloader", false));
            downloadExecutorService = threadPoolExecutor;
            threadPoolExecutor.allowCoreThreadTimeOut(true);
        }

        private DownloadExecutorServiceHolder() {
        }
    }

    /* loaded from: classes14.dex */
    private static class ExecutorServiceHolder {
        public static ThreadPoolExecutor executorService = new ThreadPoolExecutor(5, Integer.MAX_VALUE, 5, TimeUnit.SECONDS, new PriorityBlockingQueue(), e.a("HttpGroupAdapter", false));

        private ExecutorServiceHolder() {
        }
    }

    /* loaded from: classes14.dex */
    private static class LightExecutorServiceHolder {
        public static ExecutorService lightThreadExecutorService = Executors.newFixedThreadPool(5);

        private LightExecutorServiceHolder() {
        }
    }

    public static ExecutorService deliveryExecutorService() {
        return DeliveryExecutorServiceHolder.deliveryExecutorService;
    }

    public static ExecutorService downloadThreadPool() {
        return DownloadExecutorServiceHolder.downloadExecutorService;
    }

    public static ExecutorService executorService() {
        return ExecutorServiceHolder.executorService;
    }

    public static ExecutorService lightExecutorService() {
        return LightExecutorServiceHolder.lightThreadExecutorService;
    }
}

package com.facebook.imagepipeline.backends.okhttp3;

import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes.dex */
public class ImgNetStatisticTool {
    public static final String KEY_SUCCEED_IMG_BAK_DOMAIN_REQUEST_COUNT = "succImgBakDomainReqCount";
    public static final String KEY_SUCCEED_IMG_BAK_IP_REQUEST_COUNT = "succImgBakIpRequestCount";
    public static final String KEY_SUCCEED_IMG_DOMAIN_REQUEST_COUNT = "succImgDomainRequestCount";
    public static final String KEY_SUCCEED_IMG_REQUEST_COUNT = "succImgRequestCount";
    public static final String KEY_TOTAL_IMG_REQUEST_COUNT = "totalImgRequestCount";
    private static boolean isSendLastResult;
    private static AtomicInteger totalImgRequestCount = new AtomicInteger();
    private static AtomicInteger succImgRequestCount = new AtomicInteger();
    private static AtomicInteger succImgDomainRequestCount = new AtomicInteger();
    private static AtomicInteger succImgBakIpRequestCount = new AtomicInteger();
    private static AtomicInteger succImgBakDomainRequestCount = new AtomicInteger();

    public static synchronized int decreaseTotalCountAndGet() {
        int decrementAndGet;
        synchronized (ImgNetStatisticTool.class) {
            decrementAndGet = totalImgRequestCount.decrementAndGet();
        }
        return decrementAndGet;
    }

    public static synchronized int getDomainSuccessCount() {
        int i2;
        synchronized (ImgNetStatisticTool.class) {
            i2 = succImgDomainRequestCount.get();
        }
        return i2;
    }

    public static int getSuccImgBakDomainReqCnt() {
        return succImgBakDomainRequestCount.get();
    }

    public static int getSuccImgBakIpRequestCount() {
        return succImgBakIpRequestCount.get();
    }

    public static synchronized int getSuccessCount() {
        int i2;
        synchronized (ImgNetStatisticTool.class) {
            i2 = succImgRequestCount.get();
        }
        return i2;
    }

    public static synchronized int getTotalCount() {
        int i2;
        synchronized (ImgNetStatisticTool.class) {
            i2 = totalImgRequestCount.get();
        }
        return i2;
    }

    public static synchronized void incrBakDomainSuccCnt() {
        synchronized (ImgNetStatisticTool.class) {
            succImgBakDomainRequestCount.incrementAndGet();
        }
    }

    public static synchronized int incrementBakIpSuccessCountAndGet() {
        int incrementAndGet;
        synchronized (ImgNetStatisticTool.class) {
            incrementAndGet = succImgBakIpRequestCount.incrementAndGet();
        }
        return incrementAndGet;
    }

    public static synchronized int incrementDomainSuccessCountAndGet() {
        int incrementAndGet;
        synchronized (ImgNetStatisticTool.class) {
            incrementAndGet = succImgDomainRequestCount.incrementAndGet();
        }
        return incrementAndGet;
    }

    public static synchronized int incrementSuccessCountAndGet() {
        int incrementAndGet;
        synchronized (ImgNetStatisticTool.class) {
            incrementAndGet = succImgRequestCount.incrementAndGet();
        }
        return incrementAndGet;
    }

    public static synchronized int incrementTotalCountAndGet() {
        int incrementAndGet;
        synchronized (ImgNetStatisticTool.class) {
            incrementAndGet = totalImgRequestCount.incrementAndGet();
        }
        return incrementAndGet;
    }

    public static synchronized boolean isSendLastResult() {
        boolean z;
        synchronized (ImgNetStatisticTool.class) {
            z = isSendLastResult;
        }
        return z;
    }

    public static synchronized void setIsSendLastResult(boolean z) {
        synchronized (ImgNetStatisticTool.class) {
            isSendLastResult = z;
        }
    }
}

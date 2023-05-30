package com.jingdong.sdk.jdhttpdns.utils;

/* loaded from: classes.dex */
public class StatisticTool {
    private static boolean isSendLastResult;
    private static int succHttpDnsDomainRequestCount;
    private static int succHttpDnsRequestCount;
    private static int totalHttpDnsRequestCount;

    public static synchronized int getDomainSuccessCount() {
        int i2;
        synchronized (StatisticTool.class) {
            i2 = succHttpDnsDomainRequestCount;
        }
        return i2;
    }

    public static synchronized int getSuccessCount() {
        int i2;
        synchronized (StatisticTool.class) {
            i2 = succHttpDnsRequestCount;
        }
        return i2;
    }

    public static synchronized int getTotalCount() {
        int i2;
        synchronized (StatisticTool.class) {
            i2 = totalHttpDnsRequestCount;
        }
        return i2;
    }

    public static synchronized int incrementDomainSuccessCountAndGet() {
        int i2;
        synchronized (StatisticTool.class) {
            i2 = succHttpDnsDomainRequestCount + 1;
            succHttpDnsDomainRequestCount = i2;
        }
        return i2;
    }

    public static synchronized int incrementSuccessCountAndGet() {
        int i2;
        synchronized (StatisticTool.class) {
            i2 = succHttpDnsRequestCount + 1;
            succHttpDnsRequestCount = i2;
        }
        return i2;
    }

    public static synchronized int incrementTotalCountAndGet() {
        int i2;
        synchronized (StatisticTool.class) {
            i2 = totalHttpDnsRequestCount + 1;
            totalHttpDnsRequestCount = i2;
        }
        return i2;
    }

    public static synchronized boolean isSendLastResult() {
        boolean z;
        synchronized (StatisticTool.class) {
            z = isSendLastResult;
        }
        return z;
    }

    public static synchronized void setIsSendLastResult(boolean z) {
        synchronized (StatisticTool.class) {
            isSendLastResult = z;
        }
    }
}

package com.jd.libs.hybrid.offlineload.loader;

import java.util.HashSet;
import java.util.Set;

/* loaded from: classes16.dex */
public class RetryFailInfo {
    private static Set<String> a = new HashSet();

    /* loaded from: classes.dex */
    public interface RetryEntity {
        String getRetryKey();
    }

    public static void addToOverRetry(RetryEntity retryEntity) {
        a.add(retryEntity.getRetryKey());
    }

    public static boolean hasInOverRetry(RetryEntity retryEntity) {
        return a.contains(retryEntity.getRetryKey());
    }

    public static void removeOverRetry(RetryEntity retryEntity) {
        a.remove(retryEntity.getRetryKey());
    }
}

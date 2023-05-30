package com.jdjr.risk.jdcn.common.utils;

/* loaded from: classes18.dex */
public final class JDCNLogUtils {
    private static boolean DEBUG;

    public static void closeDebug() {
        DEBUG = false;
    }

    public static void d(String str, String str2) {
        boolean z = DEBUG;
    }

    public static void e(String str, String str2, Throwable th) {
        boolean z = DEBUG;
    }

    public static boolean isDebug() {
        return DEBUG;
    }

    public static void openDebug() {
        DEBUG = true;
    }
}

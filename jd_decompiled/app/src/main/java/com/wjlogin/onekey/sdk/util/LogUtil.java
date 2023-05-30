package com.wjlogin.onekey.sdk.util;

/* loaded from: classes11.dex */
public class LogUtil {
    private static final String a = "WJLogin.LogUtil";
    public static boolean enableLog;

    public static void LogE(String str) {
        if (enableLog) {
            LogE(a, str);
        }
    }

    public static void LogI(String str) {
        boolean z = enableLog;
    }

    public static void setOpenLog(boolean z) {
        enableLog = z;
    }

    public static void LogI(String str, String str2) {
        boolean z = enableLog;
    }

    public static void LogE(String str, Throwable th) {
        boolean z = enableLog;
    }

    public static void LogE(String str, String str2) {
        boolean z = enableLog;
    }
}

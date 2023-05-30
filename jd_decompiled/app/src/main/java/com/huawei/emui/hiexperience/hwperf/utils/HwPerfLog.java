package com.huawei.emui.hiexperience.hwperf.utils;

import android.util.Log;

/* loaded from: classes12.dex */
public final class HwPerfLog {
    private static final String TAG = "HwPerfLog";
    private static volatile boolean logEnabled = true;

    private HwPerfLog() {
    }

    public static void d(String str, String str2) {
        log(3, str, str2, null);
    }

    public static void disableLog() {
        logEnabled = false;
    }

    public static void e(Throwable th) {
        log(6, null, null, th);
    }

    public static void enableLog() {
        logEnabled = true;
    }

    public static void i(String str, String str2) {
        log(4, str, str2, null);
    }

    private static void log(int i2, String str, String str2, Throwable th) {
        if (logEnabled) {
            if (th != null) {
                if (str2 == null) {
                    str2 = th.getMessage();
                } else {
                    str2 = str2 + " : " + th.getMessage();
                }
            }
            if (str != null) {
                Log.println(i2, str, str2);
            }
        }
    }

    public static void w(String str, String str2) {
        log(5, str, str2, null);
    }

    public static void d(String str) {
        log(3, TAG, str, null);
    }

    public static void e(String str, String str2) {
        log(6, str, str2, null);
    }

    public static void i(String str) {
        log(4, TAG, str, null);
    }

    public static void w(String str) {
        log(5, TAG, str, null);
    }

    public static void e(String str) {
        log(6, TAG, str, null);
    }

    public static void e(String str, String str2, Throwable th) {
        log(6, str, str2, th);
    }
}

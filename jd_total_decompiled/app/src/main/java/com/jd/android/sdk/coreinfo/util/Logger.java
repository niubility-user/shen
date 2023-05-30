package com.jd.android.sdk.coreinfo.util;

import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;

/* loaded from: classes.dex */
public class Logger {
    public static boolean D;
    public static boolean E;
    public static boolean I;
    public static boolean W;
    public static boolean printStack;

    public static void d(String str, String str2) {
        boolean z = D;
    }

    public static void debugWithStackTrace(String str, String str2) {
        if (D && printStack) {
            getStackTrace();
        }
    }

    public static void e(String str, String str2) {
        boolean z = E;
    }

    public static void enableLogger(boolean z) {
        E = z;
        W = z;
        I = z;
        D = z;
    }

    public static void errorWithStackTrace(String str, String str2) {
        if (E && printStack) {
            getStackTrace();
        }
    }

    public static String getStackTrace() {
        try {
            StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
            StringBuilder sb = new StringBuilder("stacktrace: \n");
            for (StackTraceElement stackTraceElement : stackTrace) {
                sb.append(stackTraceElement.toString());
                sb.append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
            }
            return sb.toString();
        } catch (Throwable unused) {
            return "";
        }
    }

    public static void i(String str, String str2) {
        boolean z = I;
    }

    public static void infoWithStackTrace(String str, String str2) {
        if (I && printStack) {
            getStackTrace();
        }
    }

    public static void w(String str, String str2) {
        boolean z = W;
    }

    public static void warnWithStackTrace(String str, String str2) {
        if (W && printStack) {
            getStackTrace();
        }
    }

    public static void e(String str, String str2, Throwable th) {
        boolean z = E;
    }
}

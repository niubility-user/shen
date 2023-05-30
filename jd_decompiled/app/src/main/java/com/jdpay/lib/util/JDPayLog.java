package com.jdpay.lib.util;

/* loaded from: classes18.dex */
public class JDPayLog {
    public static boolean DEBUG;

    public static void d(String str) {
        if (DEBUG) {
            StackTraceElement invoker = getInvoker();
            String str2 = "\u3010" + invoker.getMethodName() + ":" + invoker.getLineNumber() + "\u3011" + str;
            invoker.getClassName();
        }
    }

    public static void e(String str) {
        if (DEBUG) {
            StackTraceElement invoker = getInvoker();
            invoker.getClassName();
            String str2 = "\u3010" + invoker.getMethodName() + ":" + invoker.getLineNumber() + "\u3011" + str;
        }
    }

    private static StackTraceElement getInvoker() {
        return Thread.currentThread().getStackTrace()[4];
    }

    public static void i(String str) {
        if (DEBUG) {
            StackTraceElement invoker = getInvoker();
            invoker.getClassName();
            String str2 = "\u3010" + invoker.getMethodName() + ":" + invoker.getLineNumber() + "\u3011" + str;
        }
    }

    public static void v(String str) {
        if (DEBUG) {
            StackTraceElement invoker = getInvoker();
            invoker.getClassName();
            String str2 = "\u3010" + invoker.getMethodName() + ":" + invoker.getLineNumber() + "\u3011" + str;
        }
    }

    public static void w(String str) {
        if (DEBUG) {
            StackTraceElement invoker = getInvoker();
            invoker.getClassName();
            String str2 = "\u3010" + invoker.getMethodName() + ":" + invoker.getLineNumber() + "\u3011" + str;
        }
    }

    public static void e(Throwable th) {
        if (th == null) {
            return;
        }
        th.printStackTrace();
    }
}

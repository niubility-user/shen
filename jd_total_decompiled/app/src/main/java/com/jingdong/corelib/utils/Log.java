package com.jingdong.corelib.utils;

import com.jingdong.sdk.oklog.OKLog;

/* loaded from: classes.dex */
public final class Log {
    public static boolean D;
    public static boolean E;
    public static boolean I;
    public static boolean V;
    public static boolean W;

    public static void d(String str, String str2) {
        OKLog.d(str, str2);
    }

    public static void e(String str, String str2) {
        OKLog.e(str, str2);
    }

    public static void i(String str, String str2) {
        OKLog.i(str, str2);
    }

    public static void init() {
        V = OKLog.V;
        D = OKLog.D;
        I = OKLog.I;
        W = OKLog.W;
        E = OKLog.E;
    }

    public static void json(String str, Object obj) {
        if (obj != null) {
            OKLog.json(str, obj.toString());
        }
    }

    public static void s(int i2) {
    }

    public static void s(String str) {
    }

    public static void s(String str, String str2) {
    }

    public static void s(String str, String str2, Throwable th) {
    }

    public static void v(String str, String str2) {
        OKLog.v(str, str2);
    }

    public static void w(String str, String str2) {
        OKLog.w(str, str2);
    }

    public static void d(String str, String str2, Throwable th) {
        OKLog.d(str, str2, th);
    }

    public static void e(String str, String str2, Throwable th) {
        OKLog.e(str, str2, th);
    }

    public static void i(String str, String str2, Throwable th) {
        OKLog.i(str, str2, th);
    }

    public static void v(String str, String str2, Throwable th) {
        OKLog.v(str, str2, th);
    }

    public static void w(String str, Throwable th) {
        OKLog.w(str, th);
    }

    public static void w(String str, String str2, Throwable th) {
        OKLog.w(str, str2, th);
    }
}

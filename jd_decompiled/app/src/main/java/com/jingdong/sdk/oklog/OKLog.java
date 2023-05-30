package com.jingdong.sdk.oklog;

/* loaded from: classes.dex */
public class OKLog {
    public static boolean D = false;
    public static final int DEBUG = 3;
    public static boolean E = false;
    public static final int ERROR = 6;
    public static boolean I = false;
    public static final int INFO = 4;
    public static boolean V = false;
    public static final int VERBOSE = 2;
    public static boolean W = false;
    public static final int WARN = 5;

    private OKLog() {
    }

    public static void d(String str, String str2) {
        OKLogConfig.d(str, str2);
    }

    public static void d(String str, String str2, Throwable th) {
        OKLogConfig.d(str, str2, th);
    }

    public static void d(String str, Throwable th) {
        OKLogConfig.d(str, th);
    }

    public static void d(String str, Object... objArr) {
        OKLogConfig.d(str, objArr);
    }

    public static void e(String str, String str2) {
        OKLogConfig.e(str, str2);
    }

    public static void e(String str, String str2, Throwable th) {
        OKLogConfig.e(str, str2, th);
    }

    public static void e(String str, Throwable th) {
        OKLogConfig.e(str, th);
    }

    public static void e(String str, Object... objArr) {
        OKLogConfig.e(str, objArr);
    }

    public static void i(String str, String str2) {
        OKLogConfig.i(str, str2);
    }

    public static void i(String str, String str2, Throwable th) {
        OKLogConfig.i(str, str2, th);
    }

    public static void i(String str, Throwable th) {
        OKLogConfig.i(str, th);
    }

    public static void i(String str, Object... objArr) {
        OKLogConfig.i(str, objArr);
    }

    public static void json(String str, String str2) {
        OKLogConfig.json(str, str2);
    }

    public static void v(String str, String str2) {
        OKLogConfig.v(str, str2);
    }

    public static void v(String str, String str2, Throwable th) {
        OKLogConfig.v(str, str2, th);
    }

    public static void v(String str, Throwable th) {
        OKLogConfig.v(str, th);
    }

    public static void v(String str, Object... objArr) {
        OKLogConfig.v(str, objArr);
    }

    public static void w(String str, String str2) {
        OKLogConfig.w(str, str2);
    }

    public static void w(String str, String str2, Throwable th) {
        OKLogConfig.w(str, str2, th);
    }

    public static void w(String str, Throwable th) {
        OKLogConfig.w(str, th);
    }

    public static void w(String str, Object... objArr) {
        OKLogConfig.w(str, objArr);
    }
}

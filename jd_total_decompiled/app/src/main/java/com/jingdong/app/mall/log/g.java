package com.jingdong.app.mall.log;

/* loaded from: classes4.dex */
public class g {
    public static void a(String str, String str2) {
        e(3, str, str2, null);
    }

    public static void b(String str, String str2) {
        e(6, str, str2, null);
    }

    public static void c(String str, String str2, Throwable th) {
        e(6, str, str2, th);
    }

    public static void d(String str, String str2) {
        e(4, str, str2, null);
    }

    public static void e(int i2, String str, String str2, Throwable th) {
        com.jingdong.sdk.talos.a.n(i2, "logx_" + str, str2, th);
    }

    public static void f(String str, String str2) {
        e(2, str, str2, null);
    }

    public static void g(String str, String str2) {
        e(5, str, str2, null);
    }

    public static void h(String str, String str2, Throwable th) {
        e(5, str, str2, th);
    }
}

package com.jd.verify.j;

/* loaded from: classes18.dex */
public class d {
    public static boolean a;

    public static void a(boolean z) {
        a = z;
    }

    public static void b(String str) {
        b("JDVerify", str);
    }

    private static void c(String str) {
        com.jd.verify.e.a(str);
    }

    public static void a(String str) {
        a("JDVerify", str);
    }

    public static void b(String str, String str2) {
        boolean z = a;
        c(str2);
    }

    public static void a(String str, String str2) {
        boolean z = a;
        c(str2);
    }

    public static void a(String str, Throwable th) {
        boolean z = a;
        c(str);
    }
}

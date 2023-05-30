package com.huawei.hms.hatool;

/* loaded from: classes12.dex */
public abstract class z {
    public static String a(String str, String str2) {
        j0 c2 = c(str, str2);
        return c2 != null ? c2.a() : "";
    }

    public static boolean b(String str, String str2) {
        j0 c2 = c(str, str2);
        return c2 != null && c2.e();
    }

    private static j0 c(String str, String str2) {
        s0 a;
        l1 a2 = s.c().a(str);
        if (a2 == null || (a = a2.a(str2)) == null) {
            return null;
        }
        return a.j();
    }

    public static String d(String str, String str2) {
        j0 c2 = c(str, str2);
        return c2 != null ? c2.b() : "";
    }

    public static boolean e(String str, String str2) {
        j0 c2 = c(str, str2);
        return c2 != null && c2.f();
    }

    public static boolean f(String str, String str2) {
        s0 a;
        l1 a2 = s.c().a(str);
        if (a2 == null || (a = a2.a(str2)) == null) {
            return false;
        }
        return a.c();
    }

    public static String g(String str, String str2) {
        j0 c2 = c(str, str2);
        return c2 != null ? c2.d() : "";
    }

    public static boolean h(String str, String str2) {
        j0 c2 = c(str, str2);
        return c2 != null && c2.g();
    }

    public static boolean i(String str, String str2) {
        s0 a;
        l1 a2 = s.c().a(str);
        if (a2 == null || (a = a2.a(str2)) == null) {
            return false;
        }
        return a.e();
    }

    public static String j(String str, String str2) {
        j0 c2 = c(str, str2);
        return c2 != null ? c2.c() : "";
    }

    public static boolean k(String str, String str2) {
        j0 c2 = c(str, str2);
        return c2 != null && c2.h();
    }
}

package jd.wjlogin_sdk.util;

/* loaded from: classes.dex */
public class p {
    private static final String a = "WJLogin.LogUtil";
    public static boolean b;

    public static void a(boolean z) {
        b = z;
    }

    public static void b(String str) {
        boolean z = b;
        c(str);
    }

    private static void c(String str) {
        k.a(str);
    }

    public static void a(String str) {
        if (b) {
            a(a, str);
        }
        c(str);
    }

    public static void b(String str, String str2) {
        boolean z = b;
        c(str2);
    }

    public static void a(String str, Throwable th) {
        boolean z = b;
        c(str);
    }

    public static void a(String str, String str2) {
        boolean z = b;
        c(str2);
    }
}

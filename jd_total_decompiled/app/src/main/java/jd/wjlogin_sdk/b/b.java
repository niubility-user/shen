package jd.wjlogin_sdk.b;

import jd.wjlogin_sdk.util.p;

/* loaded from: classes.dex */
public class b {
    private static final f a = new e();
    private static final String b = "WJLogin.AESEncryptor";

    @Deprecated
    public static String a() {
        return jd.wjlogin_sdk.util.g.a(jd.wjlogin_sdk.common.b.a()) + "@&ast";
    }

    public static String b(String str, String str2) {
        try {
            return a.a(a.a(str, str2));
        } catch (Throwable th) {
            th.printStackTrace();
            return "";
        }
    }

    public static String a(String str, String str2) {
        try {
            if (p.b) {
                p.b("Encryptor.key4 = " + str);
            }
            return a.a(str, a.a(str2));
        } catch (Throwable th) {
            th.printStackTrace();
            return "";
        }
    }

    @Deprecated
    public static String b(String str) {
        try {
            String c2 = c.c();
            if (p.b) {
                p.b("Encryptor.key1 = " + c2);
            }
            return a.a(a.a(c2, str));
        } catch (Throwable th) {
            th.printStackTrace();
            return "";
        }
    }

    @Deprecated
    public static String a(String str) {
        try {
            String b2 = c.b();
            if (p.b) {
                p.b("Encryptor.key2 = " + b2);
            }
            return a.a(b2, a.a(str));
        } catch (Throwable th) {
            th.printStackTrace();
            return "";
        }
    }
}

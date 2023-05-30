package jd.wjweblogin.b;

/* loaded from: classes11.dex */
public class b {
    private static final f a = new e();
    private static final String b = "WJWebLogin.AESEncryptor";

    public static String a(String str, String str2) {
        try {
            if (jd.wjweblogin.d.g.b) {
                jd.wjweblogin.d.g.b("Encryptor.key4 = " + str);
            }
            return a.a(str, a.a(str2));
        } catch (Throwable th) {
            th.printStackTrace();
            return "";
        }
    }

    public static String b(String str, String str2) {
        try {
            return a.a(a.a(str, str2));
        } catch (Throwable th) {
            th.printStackTrace();
            return "";
        }
    }

    public static String b(String str) {
        try {
            if (jd.wjweblogin.d.g.b) {
                jd.wjweblogin.d.g.b("encryptServerData.content = " + str);
            }
            return a.a(c.a(str));
        } catch (Throwable th) {
            th.printStackTrace();
            return "";
        }
    }

    public static String a(String str) {
        try {
            if (jd.wjweblogin.d.g.b) {
                jd.wjweblogin.d.g.b("decryptServerData.encrypted = " + str);
            }
            return c.a(a.a(str));
        } catch (Throwable th) {
            th.printStackTrace();
            return "";
        }
    }
}

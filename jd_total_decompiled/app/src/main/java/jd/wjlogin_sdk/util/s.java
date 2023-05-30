package jd.wjlogin_sdk.util;

/* loaded from: classes.dex */
public class s {
    private static String a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String a() {
        return a;
    }

    public static void a(String str) {
        a = str;
        p.b("RSAEncryptor", "publicStr = " + str);
    }
}

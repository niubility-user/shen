package jd.wjlogin_sdk.util;

/* loaded from: classes.dex */
public class b {
    public static String a(String str, int i2) {
        try {
            return a.c(str.getBytes(), i2);
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static byte[] b(String str) throws Exception {
        return a.a(str.getBytes(), 11);
    }

    public static String c(String str) {
        try {
            return new String(a.a(str, 0));
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static String d(String str) {
        try {
            return a.c(str.getBytes(), 0);
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static String b(byte[] bArr) throws Exception {
        return new String(a.b(bArr, 11));
    }

    public static byte[] a(String str) throws Exception {
        return a.a(str.getBytes(), 2);
    }

    public static String a(byte[] bArr) throws Exception {
        return new String(a.b(bArr, 2));
    }
}

package c.t.m.g;

import java.security.MessageDigest;

/* loaded from: classes.dex */
public class o1 {
    public static String a(String str, String str2) {
        byte[] bArr;
        try {
            bArr = str.getBytes("UTF-8");
        } catch (Throwable unused) {
            bArr = null;
        }
        return b(bArr, str2);
    }

    public static String b(byte[] bArr, String str) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(str);
            messageDigest.update(bArr);
            return i3.a(messageDigest.digest(), "");
        } catch (Throwable unused) {
            if (z0.e()) {
                StringBuilder sb = new StringBuilder("getMessageDigest[");
                sb.append(str);
                sb.append("] error.");
            }
            return "";
        }
    }
}

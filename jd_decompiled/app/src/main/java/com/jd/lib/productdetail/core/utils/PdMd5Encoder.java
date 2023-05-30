package com.jd.lib.productdetail.core.utils;

import com.jingdong.app.mall.e;
import com.jingdong.jdsdk.a.a;
import java.security.MessageDigest;

/* loaded from: classes15.dex */
public class PdMd5Encoder {
    private static final String[] HEX_DIGITS = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", a.a, "b", "c", "d", e.a, "f"};
    private static String algorithm = "MD5";

    public PdMd5Encoder(String str) {
    }

    private static String byteArrayToHexString(byte[] bArr) {
        StringBuffer stringBuffer = new StringBuffer();
        for (byte b : bArr) {
            stringBuffer.append(byteToHexString(b));
        }
        return stringBuffer.toString();
    }

    /* JADX WARN: Code restructure failed: missing block: B:0:?, code lost:
        r3 = r3;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static java.lang.String byteToHexString(byte r3) {
        /*
            if (r3 >= 0) goto L4
            int r3 = r3 + 256
        L4:
            int r0 = r3 / 16
            int r3 = r3 % 16
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String[] r2 = com.jd.lib.productdetail.core.utils.PdMd5Encoder.HEX_DIGITS
            r0 = r2[r0]
            r1.append(r0)
            r3 = r2[r3]
            r1.append(r3)
            java.lang.String r3 = r1.toString()
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.lib.productdetail.core.utils.PdMd5Encoder.byteToHexString(byte):java.lang.String");
    }

    public static String encode(String str, String str2) {
        try {
            return byteArrayToHexString(MessageDigest.getInstance(algorithm).digest(mergePasswordAndSalt(str, str2).getBytes("utf-8")));
        } catch (Exception unused) {
            return null;
        }
    }

    public static boolean isPasswordValid(String str, String str2, String str3) {
        return ("" + str).equals(encode(str2, str3));
    }

    private static String mergePasswordAndSalt(String str, String str2) {
        if (str == null) {
            str = "";
        }
        if (str2 == null || "".equals(str2)) {
            return str;
        }
        return str + str2.toString();
    }
}

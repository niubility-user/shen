package com.jd.lib.cashier.sdk.core.utils;

import java.security.MessageDigest;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;

/* loaded from: classes14.dex */
public class v {
    private static final String[] a = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", com.jingdong.jdsdk.a.a.a, "b", "c", "d", com.jingdong.app.mall.e.a, "f"};

    public static String a(String str, String str2) {
        String b;
        String str3 = null;
        try {
            String str4 = new String(str);
            try {
                MessageDigest messageDigest = MessageDigest.getInstance(MessageDigestAlgorithms.MD5);
                if (str2 != null && !"".equals(str2)) {
                    b = b(messageDigest.digest(str4.getBytes(str2)));
                    return b;
                }
                b = b(messageDigest.digest(str4.getBytes()));
                return b;
            } catch (Exception e2) {
                e = e2;
                str3 = str4;
                e.printStackTrace();
                return str3;
            }
        } catch (Exception e3) {
            e = e3;
        }
    }

    private static String b(byte[] bArr) {
        StringBuffer stringBuffer = new StringBuffer();
        for (byte b : bArr) {
            stringBuffer.append(c(b));
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
    private static java.lang.String c(byte r3) {
        /*
            if (r3 >= 0) goto L4
            int r3 = r3 + 256
        L4:
            int r0 = r3 / 16
            int r3 = r3 % 16
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String[] r2 = com.jd.lib.cashier.sdk.core.utils.v.a
            r0 = r2[r0]
            r1.append(r0)
            r3 = r2[r3]
            r1.append(r3)
            java.lang.String r3 = r1.toString()
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.lib.cashier.sdk.core.utils.v.c(byte):java.lang.String");
    }
}

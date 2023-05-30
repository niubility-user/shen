package com.jingdong.common.utils.pay;

import com.jingdong.app.mall.e;
import com.jingdong.jdsdk.a.a;
import java.security.MessageDigest;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;

/* loaded from: classes6.dex */
public class PayMD5Util {
    private static final String[] hexDigits = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", a.a, "b", "c", "d", e.a, "f"};

    public static String MD5Encode(String str, String str2) {
        String byteArrayToHexString;
        String str3 = null;
        try {
            String str4 = new String(str);
            try {
                MessageDigest messageDigest = MessageDigest.getInstance(MessageDigestAlgorithms.MD5);
                if (str2 != null && !"".equals(str2)) {
                    byteArrayToHexString = byteArrayToHexString(messageDigest.digest(str4.getBytes(str2)));
                    return byteArrayToHexString;
                }
                byteArrayToHexString = byteArrayToHexString(messageDigest.digest(str4.getBytes()));
                return byteArrayToHexString;
            } catch (Exception unused) {
                str3 = str4;
                return str3;
            }
        } catch (Exception unused2) {
        }
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
            java.lang.String[] r2 = com.jingdong.common.utils.pay.PayMD5Util.hexDigits
            r0 = r2[r0]
            r1.append(r0)
            r3 = r2[r3]
            r1.append(r3)
            java.lang.String r3 = r1.toString()
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.utils.pay.PayMD5Util.byteToHexString(byte):java.lang.String");
    }
}

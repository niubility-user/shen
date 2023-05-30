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
    */
    private static String c(byte b) {
        int i2;
        if (b < 0) {
            i2 = b + 256;
        }
        StringBuilder sb = new StringBuilder();
        String[] strArr = a;
        sb.append(strArr[i2 / 16]);
        sb.append(strArr[i2 % 16]);
        return sb.toString();
    }
}

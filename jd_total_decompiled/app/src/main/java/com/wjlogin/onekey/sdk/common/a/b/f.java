package com.wjlogin.onekey.sdk.common.a.b;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/* loaded from: classes11.dex */
public class f {
    private static final String a = "HmacSHA1";
    private static final char[] b = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    public static String a(String str, String str2, String str3) {
        if (str != null && !h.m(str2)) {
            if (h.m(str3)) {
                str3 = "UTF-8";
            }
            try {
                Mac mac = Mac.getInstance(a);
                mac.init(new SecretKeySpec(str2.getBytes(str3), a));
                StringBuffer stringBuffer = new StringBuffer();
                byte[] doFinal = mac.doFinal(str.getBytes(str3));
                for (byte b2 : doFinal) {
                    stringBuffer.append(a(b2));
                }
                return stringBuffer.toString();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return str;
    }

    public static String b(String str, String str2, String str3) {
        if (str != null && !h.m(str2)) {
            try {
                if (h.m(str3)) {
                    str3 = "UTF-8";
                }
                return a(str, str2, str3).toUpperCase();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return str;
    }

    private static String a(byte b2) {
        try {
            char[] cArr = b;
            return new String(new char[]{cArr[(b2 >>> 4) & 15], cArr[b2 & 15]});
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }
}

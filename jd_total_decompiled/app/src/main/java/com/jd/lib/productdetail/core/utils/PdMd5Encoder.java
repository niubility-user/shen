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
    */
    private static String byteToHexString(byte b) {
        int i2;
        if (b < 0) {
            i2 = b + 256;
        }
        StringBuilder sb = new StringBuilder();
        String[] strArr = HEX_DIGITS;
        sb.append(strArr[i2 / 16]);
        sb.append(strArr[i2 % 16]);
        return sb.toString();
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

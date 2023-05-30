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
    */
    private static String byteToHexString(byte b) {
        int i2;
        if (b < 0) {
            i2 = b + 256;
        }
        StringBuilder sb = new StringBuilder();
        String[] strArr = hexDigits;
        sb.append(strArr[i2 / 16]);
        sb.append(strArr[i2 % 16]);
        return sb.toString();
    }
}

package com.jingdong.sdk.jdupgrade.a.j;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;

/* loaded from: classes7.dex */
public class c {
    private static String a(int i2) {
        StringBuilder sb = new StringBuilder();
        String hexString = Integer.toHexString(i2 & 255);
        if (hexString.length() == 1) {
            sb.append('0');
        }
        sb.append(hexString);
        return sb.toString();
    }

    public static String a(String str) {
        StringBuilder sb = new StringBuilder("\uff96\uff8dC\uff8b$\uffd2\uffe7\uffca");
        for (int i2 = 0; str != null && i2 < str.length(); i2++) {
            String a = a(str.charAt(i2));
            if (a.length() == 1) {
                sb.append('0');
            }
            sb.append(a);
        }
        int length = sb.length();
        for (int i3 = 0; i3 < length; i3++) {
            String a2 = a(sb.charAt(i3));
            if (a2.length() == 1) {
                sb.append('0');
            }
            sb.append(a2);
        }
        sb.delete(0, 8).setLength(32);
        return sb.toString().toLowerCase();
    }

    public static String a(byte[] bArr) {
        try {
            return b(MessageDigest.getInstance(MessageDigestAlgorithms.MD5).digest(bArr));
        } catch (NoSuchAlgorithmException e2) {
            e2.printStackTrace();
            return "";
        }
    }

    public static String a(byte[] bArr, byte[] bArr2) {
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(bArr2, "HmacSHA256");
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(secretKeySpec);
            return b(mac.doFinal(bArr));
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }

    public static String b(String str) {
        try {
            return b(MessageDigest.getInstance(MessageDigestAlgorithms.MD5).digest(str.getBytes()));
        } catch (NoSuchAlgorithmException e2) {
            e2.printStackTrace();
            return "";
        }
    }

    private static String b(byte[] bArr) {
        StringBuilder sb = new StringBuilder();
        for (int i2 = 0; i2 < bArr.length; i2++) {
            sb.append("0123456789ABCDEF".charAt((bArr[i2] >>> 4) & 15));
            sb.append("0123456789ABCDEF".charAt(bArr[i2] & 15));
        }
        return sb.toString();
    }
}

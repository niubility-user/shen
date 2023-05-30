package com.jingdong.jdpush_new.j;

import android.text.TextUtils;
import com.jdpay.lib.crypto.AES;
import com.jingdong.common.utils.security.JDKeyStore;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* loaded from: classes12.dex */
public class m {
    private static IvParameterSpec a;
    private static byte[] b;

    static {
        try {
            a = new IvParameterSpec("jpushmobileiv123".getBytes("UTF-8"));
            b = "jpushmobilecsserverkey1234567890".getBytes("UTF-8");
        } catch (Exception e2) {
            g.g(e2);
        }
    }

    private static void a(StringBuffer stringBuffer, byte b2) {
        stringBuffer.append("0123456789ABCDEF".charAt((b2 >> 4) & 15));
        stringBuffer.append("0123456789ABCDEF".charAt(b2 & 15));
    }

    public static String b(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        try {
            return new String(c(b, g(str)), "UTF-8");
        } catch (Exception e2) {
            g.g(e2);
            return "";
        }
    }

    private static byte[] c(byte[] bArr, byte[] bArr2) throws Exception {
        SecretKeySpec secretKeySpec = new SecretKeySpec(bArr, JDKeyStore.KEY_TYPE_AES);
        Cipher cipher = Cipher.getInstance(AES.ALGORITHM);
        cipher.init(2, secretKeySpec, a);
        return cipher.doFinal(bArr2);
    }

    public static String d(String str) {
        byte[] bArr;
        try {
            bArr = e(b, str.getBytes("UTF-8"));
        } catch (Exception e2) {
            g.g(e2);
            bArr = null;
        }
        return h(bArr);
    }

    private static byte[] e(byte[] bArr, byte[] bArr2) throws Exception {
        SecretKeySpec secretKeySpec = new SecretKeySpec(bArr, JDKeyStore.KEY_TYPE_AES);
        Cipher cipher = Cipher.getInstance(AES.ALGORITHM);
        cipher.init(1, secretKeySpec, a);
        return cipher.doFinal(bArr2);
    }

    public static String f(String str) throws Exception {
        return new String(c(b, g(str)), "UTF-8");
    }

    public static byte[] g(String str) {
        int length = str.length() / 2;
        byte[] bArr = new byte[length];
        for (int i2 = 0; i2 < length; i2++) {
            int i3 = i2 * 2;
            bArr[i2] = Integer.valueOf(str.substring(i3, i3 + 2), 16).byteValue();
        }
        return bArr;
    }

    public static String h(byte[] bArr) {
        if (bArr == null) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer(bArr.length * 2);
        for (byte b2 : bArr) {
            a(stringBuffer, b2);
        }
        return stringBuffer.toString();
    }
}

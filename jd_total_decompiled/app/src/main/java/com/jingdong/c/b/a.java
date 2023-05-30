package com.jingdong.c.b;

import android.content.Context;
import android.text.TextUtils;
import com.jingdong.common.utils.security.JDKeyStore;
import com.jingdong.sdk.baseinfo.R;
import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

/* loaded from: classes10.dex */
public class a {
    private static String a;
    private static byte[] b;

    private static void a(StringBuffer stringBuffer, byte b2) {
        stringBuffer.append("0123456789ABCDEF".charAt((b2 >> 4) & 15));
        stringBuffer.append("0123456789ABCDEF".charAt(b2 & 15));
    }

    public static String b(Context context, String str) {
        try {
            f(context);
            return new String(c(g(context, a), h(str)));
        } catch (Throwable unused) {
            return "";
        }
    }

    private static byte[] c(byte[] bArr, byte[] bArr2) {
        IvParameterSpec ivParameterSpec = new IvParameterSpec(b);
        SecretKeySpec secretKeySpec = new SecretKeySpec(bArr, JDKeyStore.KEY_TYPE_AES);
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
        cipher.init(2, secretKeySpec, ivParameterSpec);
        return cipher.doFinal(bArr2);
    }

    public static String d(Context context, String str) {
        try {
            f(context);
            return i(e(g(context, a), str.getBytes()));
        } catch (Throwable unused) {
            return null;
        }
    }

    private static byte[] e(byte[] bArr, byte[] bArr2) {
        IvParameterSpec ivParameterSpec = new IvParameterSpec(b);
        SecretKeySpec secretKeySpec = new SecretKeySpec(bArr, JDKeyStore.KEY_TYPE_AES);
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
        cipher.init(1, secretKeySpec, ivParameterSpec);
        return cipher.doFinal(bArr2);
    }

    public static void f(Context context) {
        if (TextUtils.isEmpty(a)) {
            a = context.getString(R.string.aes_seed);
        }
    }

    private static byte[] g(Context context, String str) {
        if (context == null) {
            return new byte[1];
        }
        byte[] bArr = b;
        if (bArr != null) {
            return bArr;
        }
        byte[] bArr2 = new byte[16];
        byte[] bytes = "!q@w".getBytes();
        byte[] bytes2 = context.getResources().getString(R.string.privateKeyP2).getBytes();
        byte[] c2 = e.c("JXReeQ==");
        byte[] bArr3 = new byte[bytes.length];
        for (int i2 = 0; i2 < bytes.length; i2++) {
            bArr3[i2] = (byte) (((bytes[i2] + bytes2[i2]) + c2[i2]) / 3);
        }
        System.arraycopy(bytes, 0, bArr2, 0, 4);
        System.arraycopy(bytes2, 0, bArr2, 4, 4);
        System.arraycopy(c2, 0, bArr2, 8, 4);
        System.arraycopy(bArr3, 0, bArr2, 12, 4);
        byte[] encoded = new SecretKeySpec(SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1").generateSecret(new PBEKeySpec(str.toCharArray(), bArr2, 10, 128)).getEncoded(), JDKeyStore.KEY_TYPE_AES).getEncoded();
        b = encoded;
        return encoded;
    }

    private static byte[] h(String str) {
        int length = str.length() / 2;
        byte[] bArr = new byte[length];
        for (int i2 = 0; i2 < length; i2++) {
            int i3 = i2 * 2;
            bArr[i2] = Integer.valueOf(str.substring(i3, i3 + 2), 16).byteValue();
        }
        return bArr;
    }

    private static String i(byte[] bArr) {
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

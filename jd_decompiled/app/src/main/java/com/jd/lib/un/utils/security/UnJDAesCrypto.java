package com.jd.lib.un.utils.security;

import android.content.Context;
import com.jingdong.common.utils.security.JDKeyStore;
import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

/* loaded from: classes16.dex */
public class UnJDAesCrypto {
    private static final String CIPHERMODEPADDING = "AES/CBC/PKCS5Padding";
    private static final String HEX = "0123456789ABCDEF";
    private static final int ITERATION_COUNT = 10;
    private static final int KEY_LENGTH = 128;
    private static byte[] rawKey;

    private static void appendHex(StringBuffer stringBuffer, byte b) {
        stringBuffer.append(HEX.charAt((b >> 4) & 15));
        stringBuffer.append(HEX.charAt(b & 15));
    }

    private static IvParameterSpec createIv(Context context, String str) throws Exception {
        return new IvParameterSpec(getRawKey(context, str.substring(1, str.length() - 1) + str.substring(0, 1)));
    }

    public static String decryptCBC(Context context, String str, String str2) {
        try {
            return new String(decryptCBC(getRawKey(context, str), toByte(str2), createIv(context, str)));
        } catch (Throwable unused) {
            return "";
        }
    }

    public static String encryptCBC(Context context, String str, String str2) {
        try {
            return toHex(encryptCBC(getRawKey(context, str), str2.getBytes(), createIv(context, str)));
        } catch (Throwable unused) {
            return null;
        }
    }

    private static byte[] getRawKey(Context context, String str) throws Exception {
        byte[] bArr = rawKey;
        if (bArr != null) {
            return bArr;
        }
        byte[] encoded = new SecretKeySpec(SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1").generateSecret(new PBEKeySpec(str.toCharArray(), UnSaltGenerator.newInstance().getSalt(context), 10, 128)).getEncoded(), JDKeyStore.KEY_TYPE_AES).getEncoded();
        rawKey = encoded;
        return encoded;
    }

    public static byte[] toByte(String str) {
        int length = str.length() / 2;
        byte[] bArr = new byte[length];
        for (int i2 = 0; i2 < length; i2++) {
            int i3 = i2 * 2;
            bArr[i2] = Integer.valueOf(str.substring(i3, i3 + 2), 16).byteValue();
        }
        return bArr;
    }

    public static String toHex(byte[] bArr) {
        if (bArr == null) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer(bArr.length * 2);
        for (byte b : bArr) {
            appendHex(stringBuffer, b);
        }
        return stringBuffer.toString();
    }

    private static byte[] encryptCBC(byte[] bArr, byte[] bArr2, IvParameterSpec ivParameterSpec) throws Exception {
        SecretKeySpec secretKeySpec = new SecretKeySpec(bArr, JDKeyStore.KEY_TYPE_AES);
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(1, secretKeySpec, ivParameterSpec);
        return cipher.doFinal(bArr2);
    }

    private static byte[] decryptCBC(byte[] bArr, byte[] bArr2, IvParameterSpec ivParameterSpec) throws Exception {
        SecretKeySpec secretKeySpec = new SecretKeySpec(bArr, JDKeyStore.KEY_TYPE_AES);
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(2, secretKeySpec, ivParameterSpec);
        return cipher.doFinal(bArr2);
    }
}

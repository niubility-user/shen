package com.jingdong.common.utils.security;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

/* loaded from: classes.dex */
public class JDAesCrypto {
    private static final String HEX = "0123456789ABCDEF";
    private static final int ITERATION_COUNT = 10;
    private static final int KEY_LENGTH = 128;
    private static byte[] rawKey;

    private static void appendHex(StringBuffer stringBuffer, byte b) {
        stringBuffer.append(HEX.charAt((b >> 4) & 15));
        stringBuffer.append(HEX.charAt(b & 15));
    }

    public static String decrypt(String str, String str2) {
        try {
            return new String(decrypt(getRawKey(str), toByte(str2)));
        } catch (Throwable unused) {
            return "";
        }
    }

    public static String encrypt(String str, String str2) {
        try {
            return toHex(encrypt(getRawKey(str), str2.getBytes()));
        } catch (Throwable unused) {
            return null;
        }
    }

    public static byte[] getRawKey(String str) throws Exception {
        byte[] bArr = rawKey;
        if (bArr != null) {
            return bArr;
        }
        byte[] encoded = new SecretKeySpec(SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1").generateSecret(new PBEKeySpec(str.toCharArray(), SaltGenerator.newInstance().getSalt(), 10, 128)).getEncoded(), JDKeyStore.KEY_TYPE_AES).getEncoded();
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

    private static byte[] encrypt(byte[] bArr, byte[] bArr2) throws Exception {
        SecretKeySpec secretKeySpec = new SecretKeySpec(bArr, JDKeyStore.KEY_TYPE_AES);
        Cipher cipher = Cipher.getInstance(JDKeyStore.KEY_TYPE_AES);
        cipher.init(1, secretKeySpec);
        return cipher.doFinal(bArr2);
    }

    private static byte[] decrypt(byte[] bArr, byte[] bArr2) throws Exception {
        SecretKeySpec secretKeySpec = new SecretKeySpec(bArr, JDKeyStore.KEY_TYPE_AES);
        Cipher cipher = Cipher.getInstance(JDKeyStore.KEY_TYPE_AES);
        cipher.init(2, secretKeySpec);
        return cipher.doFinal(bArr2);
    }
}

package com.jd.jdsdk.security;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;

/* loaded from: classes13.dex */
public class DesCbcCrypto {
    private static final String DES = "DESede";
    public static final byte[] IV_BYTES = {0, 0, 0, 0, 0, 0, 0, 0};
    private static final String PADDING = "DESede/CBC/PKCS5Padding";

    public static byte[] decrypt(byte[] bArr, byte[] bArr2, byte[] bArr3) throws Exception {
        if (bArr3 == null) {
            bArr3 = IV_BYTES;
        }
        SecretKey generateSecret = SecretKeyFactory.getInstance(DES).generateSecret(new DESedeKeySpec(bArr2));
        Cipher cipher = Cipher.getInstance(PADDING);
        cipher.init(2, generateSecret, new IvParameterSpec(bArr3));
        return cipher.doFinal(bArr);
    }

    public static byte[] encrypt(byte[] bArr, byte[] bArr2, byte[] bArr3) throws Exception {
        if (bArr3 == null) {
            bArr3 = IV_BYTES;
        }
        SecretKey generateSecret = SecretKeyFactory.getInstance(DES).generateSecret(new DESedeKeySpec(bArr2));
        IvParameterSpec ivParameterSpec = new IvParameterSpec(bArr3);
        Cipher cipher = Cipher.getInstance(PADDING);
        cipher.init(1, generateSecret, ivParameterSpec);
        return cipher.doFinal(bArr);
    }

    public static final String decrypt(String str, String str2, byte[] bArr) {
        try {
            return new String(decrypt(a.d(str.getBytes()), str2.getBytes(), bArr));
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static final String encrypt(String str, String str2, byte[] bArr) {
        try {
            return a.i(encrypt(str.getBytes(), str2.getBytes(), bArr));
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static final String decrypt(String str, String str2, byte[] bArr, String str3) {
        try {
            return new String(decrypt(a.d(str.getBytes()), str2.getBytes(), bArr), str3);
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static final String encrypt(String str, String str2, byte[] bArr, String str3) {
        try {
            return a.i(encrypt(str.getBytes(str3), str2.getBytes(), bArr));
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }
}

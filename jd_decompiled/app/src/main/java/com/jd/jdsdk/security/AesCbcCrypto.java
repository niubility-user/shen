package com.jd.jdsdk.security;

import com.jingdong.common.utils.security.JDKeyStore;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* loaded from: classes.dex */
public class AesCbcCrypto {
    private static final byte[] IV_BYTES = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    private static final String PADDING = "AES/CBC/PKCS5Padding";

    public static byte[] decrypt(byte[] bArr, byte[] bArr2, byte[] bArr3) throws Exception {
        if (bArr3 == null) {
            bArr3 = IV_BYTES;
        }
        SecretKeySpec secretKeySpec = new SecretKeySpec(bArr2, JDKeyStore.KEY_TYPE_AES);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(bArr3);
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(2, secretKeySpec, ivParameterSpec);
        return cipher.doFinal(bArr);
    }

    public static byte[] encrypt(byte[] bArr, byte[] bArr2, byte[] bArr3) throws Exception {
        if (bArr3 == null) {
            bArr3 = IV_BYTES;
        }
        SecretKeySpec secretKeySpec = new SecretKeySpec(bArr2, JDKeyStore.KEY_TYPE_AES);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(bArr3);
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(1, secretKeySpec, ivParameterSpec);
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

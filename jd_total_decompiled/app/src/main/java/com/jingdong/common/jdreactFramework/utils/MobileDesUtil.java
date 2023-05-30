package com.jingdong.common.jdreactFramework.utils;

import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

/* loaded from: classes5.dex */
public class MobileDesUtil {
    private static final String DES = "DES";
    private static final String PADDING = "DES/ECB/PKCS5Padding";

    public static byte[] decrypt(byte[] bArr, byte[] bArr2) throws Exception {
        SecureRandom secureRandom = new SecureRandom();
        SecretKey generateSecret = SecretKeyFactory.getInstance(DES).generateSecret(new DESKeySpec(bArr2));
        Cipher cipher = Cipher.getInstance(PADDING);
        cipher.init(2, generateSecret, secureRandom);
        return cipher.doFinal(bArr);
    }

    public static byte[] encrypt(byte[] bArr, byte[] bArr2) throws Exception {
        SecureRandom secureRandom = new SecureRandom();
        SecretKey generateSecret = SecretKeyFactory.getInstance(DES).generateSecret(new DESKeySpec(bArr2));
        Cipher cipher = Cipher.getInstance(PADDING);
        cipher.init(1, generateSecret, secureRandom);
        return cipher.doFinal(bArr);
    }

    public static final String decrypt(String str, String str2) {
        try {
            return new String(decrypt(com.jingdong.jdsdk.secure.Base64.decode(str.getBytes()), str2.getBytes()), "utf-8");
        } catch (Exception unused) {
            return null;
        }
    }

    public static final String encrypt(String str, String str2) {
        try {
            return com.jingdong.jdsdk.secure.Base64.encodeBytes(encrypt(str.getBytes("utf-8"), str2.getBytes()));
        } catch (Exception unused) {
            return null;
        }
    }
}

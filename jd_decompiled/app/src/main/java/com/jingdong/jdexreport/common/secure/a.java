package com.jingdong.jdexreport.common.secure;

import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

/* loaded from: classes.dex */
public class a {
    public static byte[] a(byte[] bArr, byte[] bArr2) throws Exception {
        SecureRandom secureRandom = new SecureRandom();
        SecretKey generateSecret = SecretKeyFactory.getInstance("DES").generateSecret(new DESKeySpec(bArr2));
        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
        cipher.init(1, generateSecret, secureRandom);
        return cipher.doFinal(bArr);
    }

    public static final String a(String str, String str2) {
        try {
            return Base64.encodeBytes(a(str.getBytes("utf-8"), str2.getBytes()));
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }
}

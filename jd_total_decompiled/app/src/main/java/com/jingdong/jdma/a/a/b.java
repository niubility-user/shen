package com.jingdong.jdma.a.a;

import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

/* loaded from: classes12.dex */
public class b {
    public static byte[] a(byte[] bArr, byte[] bArr2) throws Exception {
        SecureRandom secureRandom = new SecureRandom();
        SecretKey generateSecret = SecretKeyFactory.getInstance("DES").generateSecret(new DESKeySpec(bArr2));
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        cipher.init(1, generateSecret, secureRandom);
        return cipher.doFinal(bArr);
    }

    public static final String a(String str, String str2) {
        try {
            return a.a(a(str.getBytes("utf-8"), str2.getBytes("utf-8")));
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }
}

package com.jingdong.manto.j;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;

/* loaded from: classes15.dex */
public class b {
    public static final byte[] a = {0, 0, 0, 0, 0, 0, 0, 0};

    public static final String a(String str, String str2, byte[] bArr) {
        try {
            return new String(a(a.a(str.getBytes()), str2.getBytes(), bArr));
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static byte[] a(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        if (bArr3 == null) {
            bArr3 = a;
        }
        SecretKey generateSecret = SecretKeyFactory.getInstance("DESede").generateSecret(new DESedeKeySpec(bArr2));
        Cipher cipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");
        cipher.init(2, generateSecret, new IvParameterSpec(bArr3));
        return cipher.doFinal(bArr);
    }

    public static final String b(String str, String str2, byte[] bArr) {
        try {
            return a.b(b(str.getBytes(), str2.getBytes(), bArr));
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static byte[] b(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        if (bArr3 == null) {
            bArr3 = a;
        }
        SecretKey generateSecret = SecretKeyFactory.getInstance("DESede").generateSecret(new DESedeKeySpec(bArr2));
        IvParameterSpec ivParameterSpec = new IvParameterSpec(bArr3);
        Cipher cipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");
        cipher.init(1, generateSecret, ivParameterSpec);
        return cipher.doFinal(bArr);
    }
}

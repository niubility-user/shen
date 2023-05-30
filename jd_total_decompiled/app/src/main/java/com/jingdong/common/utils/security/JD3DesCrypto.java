package com.jingdong.common.utils.security;

import android.text.TextUtils;
import com.jingdong.jdsdk.secure.Base64;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;

/* loaded from: classes6.dex */
public final class JD3DesCrypto {
    private static final String UNIFORM_SECRET_KEY = "abcdefghigklmnopqrstuvwx";

    public static byte[] decodeCBC(byte[] bArr, byte[] bArr2, byte[] bArr3) throws Exception {
        SecretKey generateSecret = SecretKeyFactory.getInstance("desede").generateSecret(new DESedeKeySpec(bArr));
        Cipher cipher = Cipher.getInstance("desede/CBC/PKCS5Padding");
        cipher.init(2, generateSecret, new IvParameterSpec(bArr2));
        return cipher.doFinal(bArr3);
    }

    public static String decodeECB(String str, String str2) throws Exception {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return "";
        }
        byte[] decode = Base64.decode(str.getBytes());
        SecretKey generateSecret = SecretKeyFactory.getInstance("DESede").generateSecret(new DESedeKeySpec(str2.getBytes("UTF-8")));
        Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
        cipher.init(2, generateSecret);
        return new String(cipher.doFinal(decode));
    }

    public static byte[] encodeCBC(byte[] bArr, byte[] bArr2, byte[] bArr3) throws Exception {
        SecretKey generateSecret = SecretKeyFactory.getInstance("desede").generateSecret(new DESedeKeySpec(bArr));
        Cipher cipher = Cipher.getInstance("desede/CBC/PKCS5Padding");
        cipher.init(1, generateSecret, new IvParameterSpec(bArr2));
        return cipher.doFinal(bArr3);
    }

    public static String encodeECB(String str, String str2) throws Exception {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return "";
        }
        SecretKey generateSecret = SecretKeyFactory.getInstance("DESede").generateSecret(new DESedeKeySpec(str2.getBytes("UTF-8")));
        Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
        cipher.init(1, generateSecret);
        return Base64.encodeBytes(cipher.doFinal(str.getBytes()));
    }

    public static String encodeECB(String str) throws Exception {
        return encodeECB(str, UNIFORM_SECRET_KEY);
    }

    public static String decodeECB(String str) throws Exception {
        return decodeECB(str, UNIFORM_SECRET_KEY);
    }
}

package com.jdjr.risk.tracker.util;

import com.jdpay.lib.crypto.AES;
import com.jingdong.common.jdreactFramework.utils.RSAUtils;
import com.jingdong.common.utils.security.JDKeyStore;
import java.security.Key;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import org.json.JSONObject;

/* loaded from: classes18.dex */
public class CryptoUtil {
    private static String a;
    private static String b;

    static {
        try {
            System.loadLibrary("jdjrrisktracker");
        } catch (Throwable unused) {
        }
        a = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA6pjY2EyNg/a9IYjpaCmS\nYaLbcdQoE03BolQEZp4cz0TM4o17BjLxGnQYczGhWeH9Y6UihFES8J6pIyaM27yL\naAjmJfP0p3JD+eue+fUfCgjoFY2GZC5qz4OFkV5lEH9xre2XnIWrXDwQ+QLSXNxR\nywyX7DnUcgkdTKvCyhHyjA0JKf7GlScDw/XjkONaeqL+2P8ySfExV7mAbhAyW0IS\ncx/0/X37k24rlVfghqBZ2Kd52WEd3qFS6EKsxBen6iN8nLH2GXHZqlcKCUgGSYBa\n7NN25RZao7waajDKg/XA6BT/G8PH5kk2COkDOOrUZKz5SHpgwM5MFGcIYbYmpQof\nhwIDAQAB\n";
        b = "0123456789abcdef";
    }

    public static String a(byte[] bArr) {
        byte[] a2;
        PublicKey a3;
        JSONObject jSONObject = new JSONObject();
        try {
            Key a4 = a(JDKeyStore.KEY_TYPE_AES, 128);
            if (a4 != null && (a2 = a(bArr, a4, b)) != null && (a3 = a(a)) != null) {
                jSONObject.put("visa", a.a(a((SecretKey) a4, a3)));
                jSONObject.put("data", a.a(a2));
            }
        } catch (Exception unused) {
        }
        return jSONObject.toString();
    }

    private static Key a(String str, int i2) {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance(str);
            keyGenerator.init(i2);
            return keyGenerator.generateKey();
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    private static PublicKey a(String str) {
        try {
            return KeyFactory.getInstance(RSAUtils.KEY_ALGORITHM).generatePublic(new X509EncodedKeySpec(a.a(str)));
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    private static byte[] a(SecretKey secretKey, PublicKey publicKey) {
        try {
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(1, publicKey);
            cipher.update(secretKey.getEncoded());
            return cipher.doFinal();
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    private static byte[] a(byte[] bArr, Key key, String str) {
        try {
            Cipher cipher = Cipher.getInstance(AES.ALGORITHM);
            cipher.init(1, key, new IvParameterSpec(str.getBytes()));
            return cipher.doFinal(bArr);
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }
}

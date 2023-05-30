package com.jingdong.common.jdreactFramework.utils;

import androidx.collection.ArrayMap;
import java.io.ByteArrayOutputStream;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Map;
import javax.crypto.Cipher;

/* loaded from: classes5.dex */
public class RSAUtils {
    public static final String KEY_ALGORITHM = "RSA";
    private static final int MAX_DECRYPT_BLOCK = 128;
    private static final int MAX_ENCRYPT_BLOCK = 117;
    private static final String PRIVATE_KEY = "RSAPrivateKey";
    private static final String PUBLIC_KEY = "RSAPublicKey";
    public static final String SIGNATURE_ALGORITHM = "MD5withRSA";

    public static byte[] decryptByPrivateKey(byte[] bArr, String str) throws Exception {
        byte[] doFinal;
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(Base64Utils.decode(str));
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        PrivateKey generatePrivate = keyFactory.generatePrivate(x509EncodedKeySpec);
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(2, generatePrivate);
        int length = bArr.length;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int i2 = 0;
        int i3 = 0;
        while (true) {
            int i4 = length - i2;
            if (i4 > 0) {
                if (i4 > 128) {
                    doFinal = cipher.doFinal(bArr, i2, 128);
                } else {
                    doFinal = cipher.doFinal(bArr, i2, i4);
                }
                byteArrayOutputStream.write(doFinal, 0, doFinal.length);
                i3++;
                i2 = i3 * 128;
            } else {
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                byteArrayOutputStream.close();
                return byteArray;
            }
        }
    }

    public static byte[] decryptByPublicKey(byte[] bArr, String str) throws Exception {
        byte[] doFinal;
        PublicKey generatePublic = KeyFactory.getInstance(KEY_ALGORITHM).generatePublic(new X509EncodedKeySpec(Base64Utils.decode(str)));
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(2, generatePublic);
        int length = bArr.length;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int i2 = 0;
        int i3 = 0;
        while (true) {
            int i4 = length - i2;
            if (i4 > 0) {
                if (i4 > 128) {
                    doFinal = cipher.doFinal(bArr, i2, 128);
                } else {
                    doFinal = cipher.doFinal(bArr, i2, i4);
                }
                byteArrayOutputStream.write(doFinal, 0, doFinal.length);
                i3++;
                i2 = i3 * 128;
            } else {
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                byteArrayOutputStream.close();
                return byteArray;
            }
        }
    }

    public static byte[] encryptByPrivateKey(byte[] bArr, String str) throws Exception {
        byte[] doFinal;
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(Base64Utils.decode(str));
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        PrivateKey generatePrivate = keyFactory.generatePrivate(x509EncodedKeySpec);
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(1, generatePrivate);
        int length = bArr.length;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int i2 = 0;
        int i3 = 0;
        while (true) {
            int i4 = length - i2;
            if (i4 > 0) {
                if (i4 > 117) {
                    doFinal = cipher.doFinal(bArr, i2, 117);
                } else {
                    doFinal = cipher.doFinal(bArr, i2, i4);
                }
                byteArrayOutputStream.write(doFinal, 0, doFinal.length);
                i3++;
                i2 = i3 * 117;
            } else {
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                byteArrayOutputStream.close();
                return byteArray;
            }
        }
    }

    public static byte[] encryptByPublicKey(byte[] bArr, String str) throws Exception {
        byte[] doFinal;
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(Base64Utils.decode(str));
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        PublicKey generatePublic = keyFactory.generatePublic(x509EncodedKeySpec);
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(1, generatePublic);
        int length = bArr.length;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int i2 = 0;
        int i3 = 0;
        while (true) {
            int i4 = length - i2;
            if (i4 > 0) {
                if (i4 > 117) {
                    doFinal = cipher.doFinal(bArr, i2, 117);
                } else {
                    doFinal = cipher.doFinal(bArr, i2, i4);
                }
                byteArrayOutputStream.write(doFinal, 0, doFinal.length);
                i3++;
                i2 = i3 * 117;
            } else {
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                byteArrayOutputStream.close();
                return byteArray;
            }
        }
    }

    public static Map<String, Object> genKeyPair() throws Exception {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(KEY_ALGORITHM);
        keyPairGenerator.initialize(1024);
        KeyPair generateKeyPair = keyPairGenerator.generateKeyPair();
        ArrayMap arrayMap = new ArrayMap(2);
        arrayMap.put(PUBLIC_KEY, (RSAPublicKey) generateKeyPair.getPublic());
        arrayMap.put(PRIVATE_KEY, (RSAPrivateKey) generateKeyPair.getPrivate());
        return arrayMap;
    }

    public static String getPrivateKey(Map<String, Object> map) throws Exception {
        return Base64Utils.encode(((Key) map.get(PRIVATE_KEY)).getEncoded());
    }

    public static String getPublicKey(Map<String, Object> map) throws Exception {
        return Base64Utils.encode(((Key) map.get(PUBLIC_KEY)).getEncoded());
    }

    public static String sign(byte[] bArr, String str) throws Exception {
        PrivateKey generatePrivate = KeyFactory.getInstance(KEY_ALGORITHM).generatePrivate(new X509EncodedKeySpec(Base64Utils.decode(str)));
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
        signature.initSign(generatePrivate);
        signature.update(bArr);
        return Base64Utils.encode(signature.sign());
    }

    public static boolean verify(byte[] bArr, String str, String str2) throws Exception {
        PublicKey generatePublic = KeyFactory.getInstance(KEY_ALGORITHM).generatePublic(new X509EncodedKeySpec(Base64Utils.decode(str)));
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
        signature.initVerify(generatePublic);
        signature.update(bArr);
        return signature.verify(Base64Utils.decode(str2));
    }
}

package com.laser.utils.common;

import android.util.Base64;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/* loaded from: classes13.dex */
public class RSAUtils {
    private static final String CHARSET = "UTF-8";
    private static final String KEY_CIPHER = "RSA/ECB/PKCS1Padding";
    private static final String KEY_FACTORY = "RSA";
    private static final int MAX_DECRYPT_BLOCK = 128;
    private static final int MAX_ENCRYPT_BLOCK = 117;
    private static final String SIGN_ALGORITHMS = "SHA256WithRSA";

    public static void genKeyPair() throws Exception {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(1024);
        KeyPair generateKeyPair = keyPairGenerator.generateKeyPair();
        PublicKey publicKey = generateKeyPair.getPublic();
        PrivateKey privateKey = generateKeyPair.getPrivate();
        Base64.encodeToString(publicKey.getEncoded(), 0);
        System.out.println("=================== Public Key =====================");
        Base64.encodeToString(publicKey.getEncoded(), 0);
        System.out.println("=================== Private Key =====================");
        Base64.encodeToString(privateKey.getEncoded(), 0);
    }

    private static PrivateKey getPrivateKey(String str) throws Exception {
        return KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(Base64.decode(str, 0)));
    }

    private static PublicKey getPublicKey(String str) throws Exception {
        return KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(Base64.decode(str, 0)));
    }

    public static String sign(String str, String str2) {
        try {
            PrivateKey generatePrivate = KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(Base64.decode(str2, 0)));
            Signature signature = Signature.getInstance(SIGN_ALGORITHMS);
            signature.initSign(generatePrivate);
            signature.update(str.getBytes("UTF-8"));
            return Base64.encodeToString(signature.sign(), 0);
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static boolean verify(String str, String str2, String str3) {
        try {
            PublicKey generatePublic = KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(Base64.decode(str3, 0)));
            Signature signature = Signature.getInstance(SIGN_ALGORITHMS);
            signature.initVerify(generatePublic);
            signature.update(str.getBytes("UTF-8"));
            return signature.verify(Base64.decode(str2, 0));
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }
}

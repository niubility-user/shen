package com.cmic.sso.sdk.e;

import android.content.Context;
import android.os.Build;
import android.security.KeyPairGeneratorSpec;
import android.security.keystore.KeyGenParameterSpec;
import android.text.TextUtils;
import android.util.Base64;
import com.jingdong.common.jdreactFramework.utils.RSAUtils;
import com.jingdong.common.utils.security.JDKeyStore;
import java.math.BigInteger;
import java.security.Key;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Calendar;
import java.util.HashMap;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.security.auth.x500.X500Principal;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;

/* loaded from: classes.dex */
public class b {
    private static byte[] a;

    public static boolean a(Context context, boolean z) {
        try {
            KeyStore keyStore = KeyStore.getInstance("AndroidKeyStore");
            keyStore.load(null);
            if (keyStore.getKey("CMCC_SDK_V1", null) != null) {
                return true;
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        if (z) {
            return a(context);
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String b(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        byte[] b = b(context);
        if (b != null) {
            return a.b(b, str, a);
        }
        a();
        return null;
    }

    private static String c() {
        return k.b("AES_IV", "");
    }

    private static synchronized byte[] b(Context context) {
        Cipher cipher;
        byte[] doFinal;
        Cipher cipher2;
        synchronized (b.class) {
            try {
                KeyStore keyStore = KeyStore.getInstance("AndroidKeyStore");
                keyStore.load(null);
                if (a(context, false)) {
                    String b = b();
                    if (TextUtils.isEmpty(b)) {
                        doFinal = q.a();
                        a = q.a();
                        Key key = keyStore.getKey("CMCC_SDK_V1", null);
                        if (key instanceof SecretKey) {
                            c.b("KeystoreUtil", "\u968f\u673a\u751f\u6210aes\u79d8\u94a5");
                            cipher2 = Cipher.getInstance("AES/CBC/PKCS7Padding");
                            cipher2.init(1, key, new IvParameterSpec(a));
                        } else if (!(key instanceof PrivateKey)) {
                            return null;
                        } else {
                            PublicKey publicKey = keyStore.getCertificate("CMCC_SDK_V1").getPublicKey();
                            Cipher cipher3 = Cipher.getInstance("RSA/ECB/OAEPWithSHA256AndMGF1Padding");
                            c.b("KeystoreUtil", "\u751f\u6210rsa\u5bc6");
                            cipher3.init(1, publicKey);
                            cipher2 = cipher3;
                        }
                        String encodeToString = Base64.encodeToString(cipher2.doFinal(doFinal), 0);
                        String encodeToString2 = Base64.encodeToString(a, 0);
                        HashMap hashMap = new HashMap();
                        hashMap.put("AES_IV", encodeToString2);
                        hashMap.put("AES_KEY", encodeToString);
                        k.a(hashMap);
                    } else {
                        a = Base64.decode(c(), 0);
                        byte[] decode = Base64.decode(b, 0);
                        Key key2 = keyStore.getKey("CMCC_SDK_V1", null);
                        if (key2 == null) {
                            return null;
                        }
                        if (key2 instanceof SecretKey) {
                            cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
                            cipher.init(2, key2, new IvParameterSpec(a));
                            c.b("KeystoreUtil", "\u4f7f\u7528aes");
                        } else if (!(key2 instanceof PrivateKey)) {
                            return null;
                        } else {
                            cipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA256AndMGF1Padding");
                            cipher.init(2, key2);
                            c.b("KeystoreUtil", "\u4f7f\u7528rsa");
                        }
                        doFinal = cipher.doFinal(decode);
                        StringBuilder sb = new StringBuilder();
                        sb.append("\u662f\u5426\u89e3\u5bc6\u51fa\u79d8\u94a5\uff1a");
                        sb.append(!TextUtils.isEmpty(Base64.encodeToString(doFinal, 0)));
                        c.b("KeystoreUtil", sb.toString());
                    }
                    return doFinal;
                }
                return null;
            } catch (Exception e2) {
                e2.printStackTrace();
                return null;
            }
        }
    }

    private static boolean a(Context context) {
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 23) {
            try {
                KeyGenerator keyGenerator = KeyGenerator.getInstance(JDKeyStore.KEY_TYPE_AES, "AndroidKeyStore");
                KeyGenParameterSpec.Builder builder = new KeyGenParameterSpec.Builder("CMCC_SDK_V1", 3);
                String[] strArr = new String[2];
                strArr[0] = MessageDigestAlgorithms.SHA_256;
                strArr[1] = MessageDigestAlgorithms.SHA_512;
                KeyGenParameterSpec.Builder digests = builder.setDigests(strArr);
                String[] strArr2 = new String[1];
                strArr2[0] = "CBC";
                KeyGenParameterSpec.Builder blockModes = digests.setBlockModes(strArr2);
                String[] strArr3 = new String[1];
                strArr3[0] = "PKCS7Padding";
                keyGenerator.init(blockModes.setEncryptionPaddings(strArr3).setRandomizedEncryptionRequired(false).setKeySize(256).build());
                Thread.sleep(1000L);
                keyGenerator.generateKey();
                return true;
            } catch (Exception e2) {
                c.a("KeystoreUtil", e2.getMessage());
                return false;
            }
        }
        Calendar calendar = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();
        calendar2.add(1, 30);
        if (i2 >= 18) {
            try {
                KeyPairGeneratorSpec build = new KeyPairGeneratorSpec.Builder(context).setAlias("CMCC_SDK_V1").setSubject(new X500Principal("CN=CMCC_SDK_V1")).setSerialNumber(BigInteger.TEN).setStartDate(calendar.getTime()).setEndDate(calendar2.getTime()).build();
                KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(RSAUtils.KEY_ALGORITHM, "AndroidKeyStore");
                keyPairGenerator.initialize(build);
                Thread.sleep(1000L);
                keyPairGenerator.generateKeyPair();
                return true;
            } catch (Exception e3) {
                c.a("KeystoreUtil", e3.getMessage());
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String a(Context context, String str) {
        a();
        byte[] b = b(context);
        if (b != null) {
            return a.a(b, str, a);
        }
        a();
        return null;
    }

    public static void a() {
        k.a("AES_KEY");
    }

    private static String b() {
        return k.b("AES_KEY", "");
    }
}

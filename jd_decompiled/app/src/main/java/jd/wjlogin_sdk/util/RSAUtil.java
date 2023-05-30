package jd.wjlogin_sdk.util;

import android.util.Base64;
import java.security.KeyFactory;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.Cipher;

/* loaded from: classes11.dex */
public class RSAUtil {
    private static final String a = "RSA";

    public static String encrypt(String str) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(1, (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(Base64.decode(Base64.decode(s.a(), 2), 2))));
        return Base64.encodeToString(cipher.doFinal(str.getBytes()), 2);
    }
}

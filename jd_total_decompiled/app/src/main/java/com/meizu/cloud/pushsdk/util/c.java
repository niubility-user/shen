package com.meizu.cloud.pushsdk.util;

import android.text.TextUtils;
import com.jingdong.common.jdreactFramework.utils.RSAUtils;
import com.meizu.cloud.pushinternal.DebugLogger;
import java.nio.charset.Charset;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.Cipher;

/* loaded from: classes14.dex */
public class c {
    private static final Charset a = Charset.forName("UTF-8");

    public static String a(String str, String str2) {
        if (str != null) {
            try {
                if (!TextUtils.isEmpty(str2)) {
                    return new String(c(d(str), b(str2)), a);
                }
            } catch (Exception e2) {
                DebugLogger.e("RSAUtils", "decrypt " + e2.getMessage());
            }
        }
        return null;
    }

    private static byte[] b(String str) {
        return com.meizu.cloud.pushsdk.e.h.a.a(str);
    }

    private static byte[] c(PublicKey publicKey, byte[] bArr) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(2, publicKey);
        return cipher.doFinal(bArr);
    }

    private static RSAPublicKey d(String str) {
        StringBuilder sb;
        String message;
        try {
            return (RSAPublicKey) KeyFactory.getInstance(RSAUtils.KEY_ALGORITHM).generatePublic(new X509EncodedKeySpec(b(str)));
        } catch (NoSuchAlgorithmException e2) {
            sb = new StringBuilder();
            sb.append("loadPublicKey NoSuchAlgorithmException ");
            message = e2.getMessage();
            sb.append(message);
            DebugLogger.e("RSAUtils", sb.toString());
            return null;
        } catch (InvalidKeySpecException e3) {
            sb = new StringBuilder();
            sb.append("loadPublicKey InvalidKeySpecException ");
            message = e3.getMessage();
            sb.append(message);
            DebugLogger.e("RSAUtils", sb.toString());
            return null;
        }
    }
}

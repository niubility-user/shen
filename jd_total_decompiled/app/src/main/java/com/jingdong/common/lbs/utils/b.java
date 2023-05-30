package com.jingdong.common.lbs.utils;

import android.util.Base64;
import com.jingdong.common.jdreactFramework.utils.RSAUtils;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.Cipher;

/* loaded from: classes5.dex */
public final class b {
    public static String a(String str) {
        try {
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(1, a());
            return new String(Base64.encode(cipher.doFinal(str.getBytes("UTF-8")), 11));
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    private static PublicKey a() {
        try {
            return KeyFactory.getInstance(RSAUtils.KEY_ALGORITHM).generatePublic(new X509EncodedKeySpec(Base64.decode("MIGdMA0GCSqGSIb3DQEBAQUAA4GLADCBhwKBgQDZAYtvIX8jTTIey6kAb4cCuBOF8LuGorBymNHs0P2RHLJBGWFN/QnfBogVNEXWJtVM3HPBesxuUdA70Ai0IshmNtRBnlrBRusQmgpUsJz1+PCGeXvK016CQPv4fVVlf0sjE4e4IVfVsaDYC/iKFdDrtvNRUn68nXsPp9OsLkKTIwIBAw==", 0)));
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }
}

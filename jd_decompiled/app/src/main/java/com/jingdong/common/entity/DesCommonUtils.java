package com.jingdong.common.entity;

import android.text.TextUtils;
import com.jingdong.common.utils.security.JDKeyStore;
import com.jingdong.sdk.utils.c.a;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;

/* loaded from: classes5.dex */
public class DesCommonUtils {
    public static final String KEY_CART_SHARE = "yc2JffcREheFQlYFIAY5f9sY7uflgBTo";
    public static String key;

    public static String decryptThreeDESECB(String str, String str2) throws Exception {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        if (TextUtils.isEmpty(str2)) {
            str2 = JDKeyStore.getInstance().generateKey(JDKeyStore.KEY_TYPE_3DES);
            key = str2;
            if (TextUtils.isEmpty(str2)) {
                return "";
            }
        }
        byte[] f2 = a.f(str.getBytes());
        SecretKey generateSecret = SecretKeyFactory.getInstance("DESede").generateSecret(new DESedeKeySpec(str2.getBytes("UTF-8")));
        Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
        cipher.init(2, generateSecret);
        return new String(cipher.doFinal(f2));
    }

    public static String encryptThreeDESECB(String str, String str2) throws Exception {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        if (TextUtils.isEmpty(str2)) {
            str2 = JDKeyStore.getInstance().generateKey(JDKeyStore.KEY_TYPE_3DES);
            key = str2;
            if (TextUtils.isEmpty(str2)) {
                return "";
            }
        }
        SecretKey generateSecret = SecretKeyFactory.getInstance("DESede").generateSecret(new DESedeKeySpec(str2.getBytes("UTF-8")));
        Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
        cipher.init(1, generateSecret);
        return a.k(cipher.doFinal(str.getBytes()));
    }
}

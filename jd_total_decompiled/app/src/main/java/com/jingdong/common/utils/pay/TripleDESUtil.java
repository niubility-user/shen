package com.jingdong.common.utils.pay;

import android.text.TextUtils;
import com.jingdong.jdsdk.secure.Base64;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;

/* loaded from: classes6.dex */
public class TripleDESUtil {
    public static final String KEY = "ST2@y15c$*4e9%b8ST2@y15c$*4e9%b8ST2@y15c$*4e9%b8ST2@y15c$*4e9%b8";

    public static String decryptThreeDESECB(String str, String str2) throws Exception {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return "";
        }
        byte[] decode = Base64.decode(str.getBytes());
        SecretKey generateSecret = SecretKeyFactory.getInstance("DESede").generateSecret(new DESedeKeySpec(str2.getBytes("UTF-8")));
        Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
        cipher.init(2, generateSecret);
        return new String(cipher.doFinal(decode));
    }

    public static String encryptThreeDESECB(String str, String str2) throws Exception {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return "";
        }
        SecretKey generateSecret = SecretKeyFactory.getInstance("DESede").generateSecret(new DESedeKeySpec(str2.getBytes("UTF-8")));
        Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
        cipher.init(1, generateSecret);
        return Base64.encodeBytes(cipher.doFinal(str.getBytes()));
    }
}

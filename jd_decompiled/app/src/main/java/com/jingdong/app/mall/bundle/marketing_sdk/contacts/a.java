package com.jingdong.app.mall.bundle.marketing_sdk.contacts;

import android.text.TextUtils;
import com.jdpay.lib.crypto.AES;
import com.jingdong.common.utils.security.JDKeyStore;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* loaded from: classes.dex */
public class a {
    public static String a(byte[] bArr, String str) throws Exception {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        SecretKeySpec secretKeySpec = new SecretKeySpec(bArr, JDKeyStore.KEY_TYPE_AES);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(bArr);
        Cipher cipher = Cipher.getInstance(AES.ALGORITHM);
        cipher.init(1, secretKeySpec, ivParameterSpec);
        byte[] doFinal = cipher.doFinal(str.getBytes("utf-8"));
        if (doFinal == null || doFinal.length <= 0) {
            return null;
        }
        String b = com.jingdong.app.mall.bundle.marketing_sdk.a.b(doFinal);
        if (TextUtils.isEmpty(b)) {
            return null;
        }
        return com.jingdong.app.mall.bundle.marketing_sdk.a.b(b.getBytes());
    }
}

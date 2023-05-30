package com.jingdong.sdk.baseinfo.c;

import com.jd.android.sdk.coreinfo.util.Logger;
import com.jdpay.lib.crypto.AES;
import com.jingdong.common.utils.security.JDKeyStore;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* loaded from: classes.dex */
public final class c {
    private static IvParameterSpec a;
    private static byte[] b;

    static {
        try {
            a = new IvParameterSpec("af8eec6097dba536".getBytes("UTF-8"));
            b = "scfdasg64reg1f3ds54gfd68v4fr4weq".getBytes("UTF-8");
        } catch (Throwable th) {
            Logger.e("BaseInfo", "", th);
        }
    }

    public static String a(String str) {
        try {
            byte[] bArr = b;
            byte[] bytes = str.getBytes("UTF-8");
            SecretKeySpec secretKeySpec = new SecretKeySpec(bArr, JDKeyStore.KEY_TYPE_AES);
            Cipher cipher = Cipher.getInstance(AES.ALGORITHM);
            cipher.init(1, secretKeySpec, a);
            return com.jingdong.c.b.e.a(cipher.doFinal(bytes));
        } catch (Throwable th) {
            Logger.e("BaseInfo", "", th);
            return str;
        }
    }

    private static byte[] a(byte[] bArr, byte[] bArr2) {
        SecretKeySpec secretKeySpec = new SecretKeySpec(bArr, JDKeyStore.KEY_TYPE_AES);
        Cipher cipher = Cipher.getInstance(AES.ALGORITHM);
        cipher.init(2, secretKeySpec, a);
        return cipher.doFinal(bArr2);
    }

    public static String b(String str) {
        try {
            return c(str);
        } catch (Throwable th) {
            Logger.e("BaseInfo", th.toString());
            return str;
        }
    }

    public static String c(String str) {
        return new String(a(b, com.jingdong.c.b.e.c(str)), "UTF-8");
    }
}

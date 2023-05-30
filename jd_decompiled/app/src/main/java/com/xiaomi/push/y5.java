package com.xiaomi.push;

import com.jdpay.lib.crypto.AES;
import com.jingdong.common.utils.security.JDKeyStore;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import jd.wjlogin_sdk.util.ReplyCode;

/* loaded from: classes11.dex */
public class y5 {
    private static final byte[] a = {ReplyCode.reply0x64, 23, 84, 114, 72, 0, 4, 97, 73, 97, 2, ReplyCode.reply0x34, 84, 102, 18, 32};

    private static Cipher a(byte[] bArr, int i2) {
        SecretKeySpec secretKeySpec = new SecretKeySpec(bArr, JDKeyStore.KEY_TYPE_AES);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(a);
        Cipher cipher = Cipher.getInstance(AES.ALGORITHM);
        cipher.init(i2, secretKeySpec, ivParameterSpec);
        return cipher;
    }

    public static byte[] b(byte[] bArr, byte[] bArr2) {
        return a(bArr, 2).doFinal(bArr2);
    }

    public static byte[] c(byte[] bArr, byte[] bArr2) {
        return a(bArr, 1).doFinal(bArr2);
    }
}

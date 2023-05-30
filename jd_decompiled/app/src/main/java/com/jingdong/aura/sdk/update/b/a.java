package com.jingdong.aura.sdk.update.b;

import com.jdpay.lib.crypto.AES;
import com.jingdong.common.utils.security.JDKeyStore;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* loaded from: classes4.dex */
public final class a {
    public static byte[] a(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        try {
            Cipher cipher = Cipher.getInstance(AES.ALGORITHM);
            cipher.init(1, new SecretKeySpec(bArr2, JDKeyStore.KEY_TYPE_AES), new IvParameterSpec(bArr3));
            return cipher.doFinal(bArr);
        } catch (Exception e2) {
            throw new RuntimeException("encrypt error", e2);
        }
    }
}

package com.jingdong.sdk.jdcrashreport.b;

import com.jdpay.lib.crypto.AES;
import com.jingdong.common.utils.security.JDKeyStore;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* loaded from: classes7.dex */
public class b {
    private static byte[] a(Cipher cipher, byte[] bArr, byte[] bArr2, byte[] bArr3) {
        cipher.init(2, new SecretKeySpec(bArr2, JDKeyStore.KEY_TYPE_AES), new IvParameterSpec(bArr3));
        return cipher.doFinal(bArr);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static byte[] b(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        try {
            return a(Cipher.getInstance(AES.ALGORITHM), bArr, bArr2, bArr3);
        } catch (Exception e2) {
            throw new RuntimeException("decrypt error", e2);
        }
    }
}

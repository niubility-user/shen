package com.unionpay.utils;

import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/* loaded from: classes11.dex */
public final class d {
    /* JADX WARN: Removed duplicated region for block: B:20:0x0038  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x004b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static byte[] a(int i2, byte[] bArr, byte[] bArr2) {
        if (bArr == null || !(bArr.length == 8 || bArr.length == 16 || bArr.length == 24)) {
            throw new IllegalArgumentException();
        }
        if (bArr2 != null) {
            Cipher cipher = Cipher.getInstance("DESede/ECB/NoPadding");
            byte[] bArr3 = new byte[24];
            if (bArr.length == 8) {
                System.arraycopy(bArr, 0, bArr3, 0, 8);
                System.arraycopy(bArr, 0, bArr3, 8, 8);
            } else if (bArr.length != 16) {
                System.arraycopy(bArr, 0, bArr3, 0, 24);
                if (bArr2.length % 8 != 0) {
                    int length = ((bArr2.length / 8) + 1) * 8;
                    byte[] bArr4 = new byte[length];
                    System.arraycopy(bArr2, 0, bArr4, 0, bArr2.length);
                    Arrays.fill(bArr4, bArr2.length, length, (byte) 0);
                    bArr2 = bArr4;
                }
                cipher.init(i2 == 0 ? 2 : 1, new SecretKeySpec(bArr3, "DESede"));
                return cipher.doFinal(bArr2);
            } else {
                System.arraycopy(bArr, 0, bArr3, 0, 16);
            }
            System.arraycopy(bArr, 0, bArr3, 16, 8);
            if (bArr2.length % 8 != 0) {
            }
            cipher.init(i2 == 0 ? 2 : 1, new SecretKeySpec(bArr3, "DESede"));
            return cipher.doFinal(bArr2);
        }
        throw new IllegalArgumentException();
    }

    public static byte[] a(byte[] bArr, byte[] bArr2) {
        return a(1, bArr, bArr2);
    }

    public static byte[] b(byte[] bArr, byte[] bArr2) {
        return a(0, bArr, bArr2);
    }
}

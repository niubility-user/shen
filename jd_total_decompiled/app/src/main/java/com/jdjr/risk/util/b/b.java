package com.jdjr.risk.util.b;

/* loaded from: classes18.dex */
public class b {
    public static String a(byte[] bArr, int i2, String str) {
        return new String(a(bArr, (byte) i2), str);
    }

    public static byte[] a(byte[] bArr, byte b) {
        if (bArr != null) {
            try {
                if (bArr.length > 0) {
                    byte[] bArr2 = new byte[bArr.length];
                    for (int i2 = 0; i2 < bArr.length; i2++) {
                        bArr2[i2] = (byte) (bArr[i2] ^ b);
                    }
                    return bArr2;
                }
                return null;
            } catch (Throwable unused) {
                return null;
            }
        }
        return null;
    }
}

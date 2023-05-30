package com.jingdong.aura.sdk.update.b;

/* loaded from: classes4.dex */
public final class e {
    public static String a(byte[] bArr) {
        int i2 = 0;
        for (int length = bArr.length - 1; i2 < length; length--) {
            byte b = bArr[i2];
            bArr[i2] = bArr[length];
            bArr[length] = b;
            i2++;
        }
        for (int i3 = 0; i3 < bArr.length; i3++) {
            bArr[i3] = (byte) (bArr[i3] ^ 91);
        }
        return new String(bArr);
    }
}

package com.jingdong.jdma.common.utils;

import java.io.UnsupportedEncodingException;

/* loaded from: classes12.dex */
public class b {
    public static int a(String str, int i2) {
        return Math.abs(a(str)) % i2;
    }

    public static int a(String str) {
        byte[] bArr = new byte[0];
        try {
            bArr = str.getBytes("utf-8");
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
        }
        return a(bArr, 0, bArr.length, 31);
    }

    public static int a(byte[] bArr, int i2, int i3, int i4) {
        int i5 = i4 ^ i3;
        int i6 = i3 >> 2;
        for (int i7 = 0; i7 < i6; i7++) {
            int i8 = (i7 << 2) + i2;
            int i9 = ((bArr[i8 + 0] & 255) | (((((bArr[i8 + 3] << 8) | (bArr[i8 + 2] & 255)) << 8) | (bArr[i8 + 1] & 255)) << 8)) * 1540483477;
            i5 = (i5 * 1540483477) ^ ((i9 ^ (i9 >>> 24)) * 1540483477);
        }
        int i10 = i6 << 2;
        int i11 = i3 - i10;
        int i12 = i10 + i2;
        if (i11 != 0) {
            if (i11 >= 3) {
                i5 ^= bArr[i12 + 2] << 16;
            }
            if (i11 >= 2) {
                i5 ^= bArr[i12 + 1] << 8;
            }
            if (i11 >= 1) {
                i5 ^= bArr[i12];
            }
            i5 *= 1540483477;
        }
        int i13 = ((i5 >>> 13) ^ i5) * 1540483477;
        return i13 ^ (i13 >>> 15);
    }
}

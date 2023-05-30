package com.jingdong.app.mall.bundle.mobileConfig.c;

import jd.wjlogin_sdk.util.ReplyCode;

/* loaded from: classes12.dex */
public class c {
    private static final byte[] a = {-30, 114, ReplyCode.reply0x31, -100, -40, -38, -127, -107, 8, -103, ReplyCode.reply0x25, ReplyCode.reply0x8c, 118, -98, ReplyCode.reply0xa6, -37, -53, -38, -73, ReplyCode.reply0x26, -12, ReplyCode.reply0xaa, -63, -43, ReplyCode.reply0xa6, -100, 42, 121, 97, -104, ReplyCode.reply0xed, 67};
    private static final byte[] b = {110, 4, 80, ReplyCode.reply0xa7, ReplyCode.reply0x73, -61, -106, ReplyCode.reply0xef, 26, -92, ReplyCode.reply0x68, ReplyCode.reply0x28, -36, -11, ReplyCode.reply0x34, ReplyCode.reply0xd0};

    /* renamed from: c  reason: collision with root package name */
    public static final byte[] f8236c = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

    public static String a() {
        byte[] a2 = a.a(a, b, f8236c);
        StringBuilder sb = new StringBuilder();
        int i2 = 0;
        int i3 = 0;
        while (true) {
            byte[] bArr = b;
            if (i3 >= bArr.length) {
                break;
            }
            sb.append(a(bArr[i3] ^ a2[i3]));
            i3 += 2;
        }
        while (true) {
            byte[] bArr2 = b;
            if (i2 >= bArr2.length) {
                return sb.toString();
            }
            sb.append(a(bArr2[i2] ^ a2[i2 + 1]));
            i2 += 2;
        }
    }

    private static String a(int i2) {
        StringBuilder sb = new StringBuilder();
        String hexString = Integer.toHexString(i2 & 255);
        if (hexString.length() == 1) {
            sb.append('0');
        }
        sb.append(hexString);
        return sb.toString();
    }

    public static String a(byte[] bArr) {
        int i2 = 0;
        for (int length = bArr.length - 1; i2 < length; length--) {
            byte b2 = bArr[i2];
            bArr[i2] = bArr[length];
            bArr[length] = b2;
            i2++;
        }
        for (int i3 = 0; i3 < bArr.length; i3++) {
            bArr[i3] = (byte) (bArr[i3] ^ 91);
        }
        return new String(bArr);
    }
}

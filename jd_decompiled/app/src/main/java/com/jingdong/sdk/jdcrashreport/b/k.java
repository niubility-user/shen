package com.jingdong.sdk.jdcrashreport.b;

import com.google.common.primitives.SignedBytes;
import jd.wjlogin_sdk.util.ReplyCode;

/* loaded from: classes7.dex */
public class k {
    private static final byte[] a = {25, 72, 89, 9, -103, -6, 3, -50, 11, -43, 76, ReplyCode.reply0xa6, ReplyCode.reply0xa8, 72, 26, -113, -73, ReplyCode.reply0xd2, 92, 70, ReplyCode.reply0xca, 105, 14, ReplyCode.reply0xca, 27, 27, 66, 17, ReplyCode.reply0x26, -108, -101, ReplyCode.reply0x73};
    private static final byte[] b = {-12, 21, -73, ReplyCode.reply0xa0, 109, -127, ReplyCode.reply0xb1, 29, 117, SignedBytes.MAX_POWER_OF_TWO, -97, -32, 83, 116, -113, 118};

    private static String a(int i2) {
        StringBuilder sb = new StringBuilder();
        String hexString = Integer.toHexString(i2 & 255);
        if (hexString.length() == 1) {
            sb.append('0');
        }
        sb.append(hexString);
        return sb.toString();
    }

    public static String b(long j2, long j3) {
        byte[] b2 = b.b(a, b, c(j2, j3));
        StringBuilder sb = new StringBuilder();
        int i2 = 0;
        int i3 = 0;
        while (true) {
            byte[] bArr = b;
            if (i3 >= bArr.length) {
                break;
            }
            sb.append(a(bArr[i3] ^ b2[i3]));
            i3 += 2;
        }
        while (true) {
            byte[] bArr2 = b;
            if (i2 < bArr2.length) {
                sb.append(a(bArr2[i2] ^ b2[i2 + 1]));
                i2 += 2;
            } else {
                return sb.toString();
            }
        }
    }

    private static byte[] c(long j2, long j3) {
        byte[] bArr = new byte[16];
        int i2 = 0;
        while (i2 < 8) {
            byte b2 = (byte) (j2 % 100);
            bArr[i2] = b2;
            bArr[i2 + 9] = b2;
            j2 /= 100;
            int i3 = i2 + 1;
            byte b3 = (byte) (j3 % 100);
            bArr[i3] = b3;
            bArr[i3 + 7] = b3;
            j3 /= 100;
            i2 = i3 + 1;
        }
        return bArr;
    }
}

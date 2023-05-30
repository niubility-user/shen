package com.jingdong.aura.sdk.update.b;

import com.google.common.primitives.SignedBytes;
import java.io.ByteArrayOutputStream;
import java.util.zip.GZIPOutputStream;

/* loaded from: classes4.dex */
public final class h {
    private static final char[] a = {'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/'};
    private static byte[] b = new byte[128];

    static {
        int i2 = 0;
        int i3 = 0;
        while (true) {
            byte[] bArr = b;
            if (i3 > bArr.length - 1) {
                break;
            }
            bArr[i3] = -1;
            i3++;
        }
        while (true) {
            char[] cArr = a;
            if (i2 > cArr.length - 1) {
                return;
            }
            b[cArr[i2]] = (byte) i2;
            i2++;
        }
    }

    public static String a(byte[] bArr) {
        StringBuilder sb = new StringBuilder();
        for (int i2 = 0; i2 <= bArr.length - 1; i2 += 3) {
            byte[] bArr2 = new byte[4];
            byte b2 = 0;
            for (int i3 = 0; i3 <= 2; i3++) {
                int i4 = i2 + i3;
                if (i4 <= bArr.length - 1) {
                    bArr2[i3] = (byte) (b2 | ((bArr[i4] & 255) >>> ((i3 * 2) + 2)));
                    b2 = (byte) ((((bArr[i4] & 255) << (((2 - i3) * 2) + 2)) & 255) >>> 2);
                } else {
                    bArr2[i3] = b2;
                    b2 = SignedBytes.MAX_POWER_OF_TWO;
                }
            }
            bArr2[3] = b2;
            for (int i5 = 0; i5 <= 3; i5++) {
                sb.append(bArr2[i5] <= 63 ? a[bArr2[i5]] : '=');
            }
        }
        return sb.toString();
    }

    public static byte[] b(byte[] bArr) {
        byte[] bArr2 = null;
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
            gZIPOutputStream.write(bArr);
            gZIPOutputStream.finish();
            gZIPOutputStream.close();
            bArr2 = byteArrayOutputStream.toByteArray();
            byteArrayOutputStream.close();
            return bArr2;
        } catch (Exception e2) {
            e2.printStackTrace();
            return bArr2;
        }
    }
}

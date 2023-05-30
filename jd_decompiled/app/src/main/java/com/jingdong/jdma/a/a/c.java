package com.jingdong.jdma.a.a;

import com.google.common.primitives.SignedBytes;
import java.io.ByteArrayOutputStream;
import java.util.zip.GZIPOutputStream;

/* loaded from: classes12.dex */
public class c {
    private static final char[] a = {'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/'};

    public static String a(byte[] bArr) throws Exception {
        StringBuilder sb = new StringBuilder();
        for (int i2 = 0; i2 <= bArr.length - 1; i2 += 3) {
            byte[] bArr2 = new byte[4];
            byte b = 0;
            for (int i3 = 0; i3 <= 2; i3++) {
                int i4 = i2 + i3;
                if (i4 <= bArr.length - 1) {
                    bArr2[i3] = (byte) (b | ((bArr[i4] & 255) >>> ((i3 * 2) + 2)));
                    b = (byte) ((((bArr[i4] & 255) << (((2 - i3) * 2) + 2)) & 255) >>> 2);
                } else {
                    bArr2[i3] = b;
                    b = SignedBytes.MAX_POWER_OF_TWO;
                }
            }
            bArr2[3] = b;
            for (int i5 = 0; i5 <= 3; i5++) {
                if (bArr2[i5] <= 63) {
                    sb.append(a[bArr2[i5]]);
                } else {
                    sb.append('=');
                }
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

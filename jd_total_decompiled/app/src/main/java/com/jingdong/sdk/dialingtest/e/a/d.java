package com.jingdong.sdk.dialingtest.e.a;

import com.google.common.primitives.SignedBytes;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/* loaded from: classes7.dex */
public class d {
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

    private static byte[] b() {
        byte[] bArr = new byte[128];
        int i2 = 0;
        for (int i3 = 0; i3 <= 127; i3++) {
            bArr[i3] = -1;
        }
        while (true) {
            char[] cArr = a;
            if (i2 > cArr.length - 1) {
                return bArr;
            }
            bArr[cArr[i2]] = (byte) i2;
            i2++;
        }
    }

    public static byte[] c(String str) throws Exception {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bytes = str.getBytes();
        int length = bytes.length;
        byte[] bArr = new byte[length];
        byte[] b = b();
        for (int i2 = 0; i2 <= bytes.length - 1; i2++) {
            bArr[i2] = b[bytes[i2]];
        }
        int i3 = 0;
        while (true) {
            int i4 = length - 1;
            if (i3 > i4) {
                return byteArrayOutputStream.toByteArray();
            }
            byte[] bArr2 = new byte[3];
            int i5 = 0;
            for (int i6 = 0; i6 <= 2; i6++) {
                int i7 = i3 + i6;
                int i8 = i7 + 1;
                if (i8 <= i4 && bArr[i8] >= 0) {
                    bArr2[i6] = (byte) ((((bArr[i7] & 255) << ((i6 * 2) + 2)) & 255) | ((byte) ((bArr[i8] & 255) >>> (((2 - (i6 + 1)) * 2) + 2))));
                    i5++;
                }
            }
            for (int i9 = 0; i9 <= i5 - 1; i9++) {
                byteArrayOutputStream.write(bArr2[i9]);
            }
            i3 += 4;
        }
    }

    public static byte[] d(byte[] bArr) {
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

    public static byte[] e(byte[] bArr) {
        byte[] bArr2 = null;
        try {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
            GZIPInputStream gZIPInputStream = new GZIPInputStream(byteArrayInputStream);
            byte[] bArr3 = new byte[1024];
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            while (true) {
                int read = gZIPInputStream.read(bArr3, 0, 1024);
                if (read != -1) {
                    byteArrayOutputStream.write(bArr3, 0, read);
                } else {
                    bArr2 = byteArrayOutputStream.toByteArray();
                    byteArrayOutputStream.flush();
                    byteArrayOutputStream.close();
                    gZIPInputStream.close();
                    byteArrayInputStream.close();
                    return bArr2;
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            return bArr2;
        }
    }
}

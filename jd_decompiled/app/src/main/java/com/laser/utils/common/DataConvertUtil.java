package com.laser.utils.common;

/* loaded from: classes13.dex */
public final class DataConvertUtil {
    private static final char[] HEX_DIGITS = "0123456789ABCDEF".toCharArray();

    public static String bytesToHexString(byte[] bArr) {
        StringBuilder sb = new StringBuilder("");
        if (bArr == null || bArr.length <= 0) {
            return null;
        }
        for (byte b : bArr) {
            String hexString = Integer.toHexString(b & 255);
            if (hexString.length() < 2) {
                sb.append(0);
            }
            sb.append(hexString);
        }
        return sb.toString();
    }

    private static byte charToByte(char c2) {
        return (byte) "0123456789ABCDEF".indexOf(c2);
    }

    public static byte[] hexStringToBytes(String str) {
        if (str == null || str.equals("")) {
            return null;
        }
        String upperCase = str.toUpperCase();
        int length = upperCase.length() / 2;
        char[] charArray = upperCase.toCharArray();
        byte[] bArr = new byte[length];
        for (int i2 = 0; i2 < length; i2++) {
            int i3 = i2 * 2;
            bArr[i2] = (byte) (charToByte(charArray[i3 + 1]) | (charToByte(charArray[i3]) << 4));
        }
        return bArr;
    }

    public static final String long2HexString(long j2, int i2) {
        int i3 = i2 << 1;
        char[] cArr = new char[i3];
        for (int i4 = i3 - 1; i4 >= 0; i4--) {
            cArr[i4] = HEX_DIGITS[(int) (15 & j2)];
            j2 >>>= 4;
        }
        return new String(cArr);
    }

    public static final byte[] padding(byte[] bArr, byte b, byte b2, boolean z) {
        int length = bArr.length % 8;
        int i2 = 1;
        if (length != 0) {
            int i3 = 8 - length;
            byte[] bArr2 = new byte[bArr.length + i3];
            System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
            bArr2[bArr.length] = b;
            while (i2 < i3) {
                bArr2[bArr.length + i2] = b2;
                i2++;
            }
            return bArr2;
        } else if (z) {
            byte[] bArr3 = new byte[bArr.length + 8];
            System.arraycopy(bArr, 0, bArr3, 0, bArr.length);
            bArr3[bArr.length] = b;
            while (i2 < 8) {
                bArr3[bArr.length + i2] = b2;
                i2++;
            }
            return bArr3;
        } else {
            return bArr;
        }
    }
}

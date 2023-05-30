package com.facebook.common.util;

import com.jingdong.common.utils.LangUtils;

/* loaded from: classes.dex */
public class Hex {
    private static final byte[] DIGITS;
    private static final char[] HEX_DIGITS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    private static final char[] FIRST_CHAR = new char[256];
    private static final char[] SECOND_CHAR = new char[256];

    static {
        for (int i2 = 0; i2 < 256; i2++) {
            char[] cArr = FIRST_CHAR;
            char[] cArr2 = HEX_DIGITS;
            cArr[i2] = cArr2[(i2 >> 4) & 15];
            SECOND_CHAR[i2] = cArr2[i2 & 15];
        }
        DIGITS = new byte[103];
        for (int i3 = 0; i3 <= 70; i3++) {
            DIGITS[i3] = -1;
        }
        for (byte b = 0; b < 10; b = (byte) (b + 1)) {
            DIGITS[b + 48] = b;
        }
        for (byte b2 = 0; b2 < 6; b2 = (byte) (b2 + 1)) {
            byte[] bArr = DIGITS;
            byte b3 = (byte) (b2 + 10);
            bArr[b2 + 65] = b3;
            bArr[b2 + 97] = b3;
        }
    }

    public static String byte2Hex(int i2) {
        if (i2 > 255 || i2 < 0) {
            throw new IllegalArgumentException("The int converting to hex should be in range 0~255");
        }
        return String.valueOf(FIRST_CHAR[i2]) + String.valueOf(SECOND_CHAR[i2]);
    }

    public static byte[] decodeHex(String str) {
        byte[] bArr;
        byte b;
        byte b2;
        int length = str.length();
        if ((length & 1) == 0) {
            byte[] bArr2 = new byte[length >> 1];
            boolean z = false;
            int i2 = 0;
            int i3 = 0;
            while (i2 < length) {
                int i4 = i2 + 1;
                char charAt = str.charAt(i2);
                if (charAt <= 'f' && (b = (bArr = DIGITS)[charAt]) != -1) {
                    int i5 = i4 + 1;
                    char charAt2 = str.charAt(i4);
                    if (charAt2 <= 'f' && (b2 = bArr[charAt2]) != -1) {
                        bArr2[i3] = (byte) ((b << 4) | b2);
                        i3++;
                        i2 = i5;
                    }
                }
                z = true;
            }
            if (z) {
                throw new IllegalArgumentException("Invalid hexadecimal digit: " + str);
            }
            return bArr2;
        }
        throw new IllegalArgumentException("Odd number of characters.");
    }

    public static String encodeHex(byte[] bArr, boolean z) {
        int i2;
        char[] cArr = new char[bArr.length * 2];
        int i3 = 0;
        for (int i4 = 0; i4 < bArr.length && ((i2 = bArr[i4] & 255) != 0 || !z); i4++) {
            int i5 = i3 + 1;
            cArr[i3] = FIRST_CHAR[i2];
            i3 = i5 + 1;
            cArr[i5] = SECOND_CHAR[i2];
        }
        return new String(cArr, 0, i3);
    }

    public static byte[] hexStringToByteArray(String str) {
        return decodeHex(str.replaceAll(LangUtils.SINGLE_SPACE, ""));
    }
}

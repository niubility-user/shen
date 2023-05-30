package com.xiaomi.push;

/* loaded from: classes11.dex */
public class m0 {
    private static char[] a;
    private static byte[] b;

    static {
        System.getProperty("line.separator");
        a = new char[64];
        char c2 = 'A';
        int i2 = 0;
        while (c2 <= 'Z') {
            a[i2] = c2;
            c2 = (char) (c2 + 1);
            i2++;
        }
        char c3 = 'a';
        while (c3 <= 'z') {
            a[i2] = c3;
            c3 = (char) (c3 + 1);
            i2++;
        }
        char c4 = '0';
        while (c4 <= '9') {
            a[i2] = c4;
            c4 = (char) (c4 + 1);
            i2++;
        }
        char[] cArr = a;
        cArr[i2] = '+';
        cArr[i2 + 1] = '/';
        b = new byte[128];
        int i3 = 0;
        while (true) {
            byte[] bArr = b;
            if (i3 >= bArr.length) {
                break;
            }
            bArr[i3] = -1;
            i3++;
        }
        for (int i4 = 0; i4 < 64; i4++) {
            b[a[i4]] = (byte) i4;
        }
    }

    public static String a(String str) {
        return new String(e(str.getBytes()));
    }

    public static byte[] b(String str) {
        return c(str.toCharArray());
    }

    public static byte[] c(char[] cArr) {
        return d(cArr, 0, cArr.length);
    }

    public static byte[] d(char[] cArr, int i2, int i3) {
        int i4;
        char c2;
        int i5;
        char c3;
        if (i3 % 4 == 0) {
            while (i3 > 0 && cArr[(i2 + i3) - 1] == '=') {
                i3--;
            }
            int i6 = (i3 * 3) / 4;
            byte[] bArr = new byte[i6];
            int i7 = i3 + i2;
            int i8 = 0;
            while (i2 < i7) {
                int i9 = i2 + 1;
                char c4 = cArr[i2];
                int i10 = i9 + 1;
                char c5 = cArr[i9];
                if (i10 < i7) {
                    i4 = i10 + 1;
                    c2 = cArr[i10];
                } else {
                    i4 = i10;
                    c2 = 'A';
                }
                if (i4 < i7) {
                    i5 = i4 + 1;
                    c3 = cArr[i4];
                } else {
                    i5 = i4;
                    c3 = 'A';
                }
                if (c4 > '\u007f' || c5 > '\u007f' || c2 > '\u007f' || c3 > '\u007f') {
                    throw new IllegalArgumentException("Illegal character in Base64 encoded data.");
                }
                byte[] bArr2 = b;
                byte b2 = bArr2[c4];
                byte b3 = bArr2[c5];
                byte b4 = bArr2[c2];
                byte b5 = bArr2[c3];
                if (b2 < 0 || b3 < 0 || b4 < 0 || b5 < 0) {
                    throw new IllegalArgumentException("Illegal character in Base64 encoded data.");
                }
                int i11 = (b2 << 2) | (b3 >>> 4);
                int i12 = ((b3 & 15) << 4) | (b4 >>> 2);
                int i13 = ((b4 & 3) << 6) | b5;
                int i14 = i8 + 1;
                bArr[i8] = (byte) i11;
                if (i14 < i6) {
                    bArr[i14] = (byte) i12;
                    i14++;
                }
                if (i14 < i6) {
                    bArr[i14] = (byte) i13;
                    i8 = i14 + 1;
                } else {
                    i8 = i14;
                }
                i2 = i5;
            }
            return bArr;
        }
        throw new IllegalArgumentException("Length of Base64 encoded input string is not a multiple of 4.");
    }

    public static char[] e(byte[] bArr) {
        return f(bArr, 0, bArr.length);
    }

    public static char[] f(byte[] bArr, int i2, int i3) {
        int i4;
        int i5;
        int i6;
        int i7 = ((i3 * 4) + 2) / 3;
        char[] cArr = new char[((i3 + 2) / 3) * 4];
        int i8 = i3 + i2;
        int i9 = 0;
        while (i2 < i8) {
            int i10 = i2 + 1;
            int i11 = bArr[i2] & 255;
            if (i10 < i8) {
                i4 = i10 + 1;
                i5 = bArr[i10] & 255;
            } else {
                i4 = i10;
                i5 = 0;
            }
            if (i4 < i8) {
                i6 = bArr[i4] & 255;
                i4++;
            } else {
                i6 = 0;
            }
            int i12 = i11 >>> 2;
            int i13 = ((i11 & 3) << 4) | (i5 >>> 4);
            int i14 = ((i5 & 15) << 2) | (i6 >>> 6);
            int i15 = i6 & 63;
            int i16 = i9 + 1;
            char[] cArr2 = a;
            cArr[i9] = cArr2[i12];
            int i17 = i16 + 1;
            cArr[i16] = cArr2[i13];
            char c2 = '=';
            cArr[i17] = i17 < i7 ? cArr2[i14] : '=';
            int i18 = i17 + 1;
            if (i18 < i7) {
                c2 = cArr2[i15];
            }
            cArr[i18] = c2;
            i9 = i18 + 1;
            i2 = i4;
        }
        return cArr;
    }

    public static String g(String str) {
        return new String(b(str));
    }
}

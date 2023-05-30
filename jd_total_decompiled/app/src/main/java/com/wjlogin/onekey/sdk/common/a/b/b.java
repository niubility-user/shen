package com.wjlogin.onekey.sdk.common.a.b;

import androidx.core.view.InputDeviceCompat;
import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
import com.jingdong.common.utils.LangUtils;

/* loaded from: classes11.dex */
public class b {
    private static final char[] a = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    private static final int b = 16;

    /* renamed from: c  reason: collision with root package name */
    private static final int f18337c = 3;
    private static final int d = 7;

    /* renamed from: e  reason: collision with root package name */
    private static final int f18338e = 11;

    public static final String a(byte[] bArr) {
        StringBuffer stringBuffer = new StringBuffer(bArr.length);
        for (byte b2 : bArr) {
            String hexString = Integer.toHexString(b2 & 255);
            if (hexString.length() < 2) {
                stringBuffer.append(0);
            }
            stringBuffer.append(hexString.toUpperCase());
        }
        return stringBuffer.toString();
    }

    public static String b(byte[] bArr) {
        return bArr == null ? "" : a(bArr, 0, bArr.length);
    }

    public static String c(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i2 = 0; i2 < bArr.length; i2++) {
            char[] cArr = a;
            sb.append(cArr[(bArr[i2] >> 4) & 15]);
            sb.append(cArr[bArr[i2] & 15]);
        }
        return sb.toString();
    }

    public static byte[] a(String str) {
        if (str == null) {
            return null;
        }
        char[] charArray = str.toCharArray();
        int length = charArray.length / 2;
        byte[] bArr = new byte[length];
        for (int i2 = 0; i2 < length; i2++) {
            int i3 = i2 * 2;
            int digit = Character.digit(charArray[i3 + 1], 16) | (Character.digit(charArray[i3], 16) << 4);
            if (digit > 127) {
                digit += InputDeviceCompat.SOURCE_ANY;
            }
            bArr[i2] = (byte) digit;
        }
        return bArr;
    }

    public static String a(byte[] bArr, int i2, int i3) {
        int i4;
        if (bArr == null || bArr.length == 0) {
            return "";
        }
        int length = i2 >= bArr.length ? bArr.length - 1 : i2;
        int length2 = i3 > bArr.length - length ? bArr.length - length : i3;
        StringBuffer stringBuffer = new StringBuffer();
        byte[] bArr2 = new byte[18];
        char[] cArr = new char[4];
        char[] cArr2 = new char[8];
        int i5 = length2 >> 4;
        int i6 = length2 & 15;
        int i7 = 0;
        while (i7 < i5) {
            int i8 = i7 * 16;
            char[] cArr3 = a;
            cArr2[0] = cArr3[(i8 >> 12) & 15];
            cArr2[1] = cArr3[(i8 >> 8) & 15];
            cArr2[2] = cArr3[(i8 >> 4) & 15];
            cArr2[3] = cArr3[i8 & 15];
            stringBuffer.append(new String(cArr2, 0, 4) + ": ");
            int i9 = 0;
            while (i9 < 16) {
                bArr2[i9] = bArr[length + i8 + i9];
                char[] cArr4 = a;
                cArr[0] = cArr4[(bArr2[i9] >> 4) & 15];
                cArr[1] = cArr4[bArr2[i9] & 15];
                stringBuffer.append(cArr[0]);
                stringBuffer.append(cArr[1]);
                stringBuffer.append(' ');
                if (i9 == 3 || i9 == 7 || i9 == 11) {
                    stringBuffer.append(' ');
                }
                if (bArr2[i9] < 32 || bArr2[i9] > 126) {
                    bArr2[i9] = 46;
                }
                i9++;
            }
            stringBuffer.append(" ; " + new String(bArr2, 0, i9) + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
            i7++;
        }
        if (i6 > 0) {
            int i10 = i7 * 16;
            char[] cArr5 = a;
            cArr2[0] = cArr5[(i10 >> 12) & 15];
            cArr2[1] = cArr5[(i10 >> 8) & 15];
            cArr2[2] = cArr5[(i10 >> 4) & 15];
            cArr2[3] = cArr5[i10 & 15];
            stringBuffer.append(new String(cArr2, 0, 4) + ": ");
            while (i4 < i6) {
                bArr2[i4] = bArr[length + i10 + i4];
                char[] cArr6 = a;
                cArr[0] = cArr6[(bArr2[i4] >> 4) & 15];
                cArr[1] = cArr6[bArr2[i4] & 15];
                stringBuffer.append(cArr[0]);
                stringBuffer.append(cArr[1]);
                stringBuffer.append(' ');
                if (i4 == 3 || i4 == 7 || i4 == 11) {
                    stringBuffer.append(' ');
                }
                i4 = (bArr2[i4] >= 32 && bArr2[i4] <= 126) ? i4 + 1 : 0;
                bArr2[i4] = 46;
            }
            while (i4 < 16) {
                bArr2[i4] = 32;
                stringBuffer.append("   ");
                if (i4 == 3 || i4 == 7 || i4 == 11) {
                    stringBuffer.append(LangUtils.SINGLE_SPACE);
                }
                i4++;
            }
            stringBuffer.append(" ; " + new String(bArr2, 0, i4) + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
        }
        return stringBuffer.toString();
    }

    public static void a(String[] strArr) {
        byte[] bArr = new byte[65536];
        for (int i2 = 0; i2 < 65536; i2++) {
            bArr[i2] = (byte) i2;
        }
    }
}

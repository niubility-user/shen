package cn.com.union.fido.util.asn1.util;

import com.jingdong.sdk.platform.business.personal.R2;
import java.io.ByteArrayOutputStream;
import java.util.Vector;
import jd.wjlogin_sdk.util.ReplyCode;

/* loaded from: classes.dex */
public final class Strings {
    public static String fromUTF8ByteArray(byte[] bArr) {
        char c2;
        int i2;
        byte b;
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        while (i4 < bArr.length) {
            i5++;
            if ((bArr[i4] & 240) == 240) {
                i5++;
                i4 += 4;
            } else {
                i4 = (bArr[i4] & 224) == 224 ? i4 + 3 : (bArr[i4] & 192) == 192 ? i4 + 2 : i4 + 1;
            }
        }
        char[] cArr = new char[i5];
        int i6 = 0;
        while (i3 < bArr.length) {
            if ((bArr[i3] & 240) == 240) {
                int i7 = (((((bArr[i3] & 3) << 18) | ((bArr[i3 + 1] & 63) << 12)) | ((bArr[i3 + 2] & 63) << 6)) | (bArr[i3 + 3] & 63)) - 65536;
                c2 = (char) ((i7 & R2.attr.icon_font_color) | 56320);
                cArr[i6] = (char) (55296 | (i7 >> 10));
                i3 += 4;
                i6++;
            } else if ((bArr[i3] & 224) == 224) {
                c2 = (char) (((bArr[i3] & 15) << 12) | ((bArr[i3 + 1] & 63) << 6) | (bArr[i3 + 2] & 63));
                i3 += 3;
            } else {
                if ((bArr[i3] & ReplyCode.reply0xd0) == 208) {
                    i2 = (bArr[i3] & 31) << 6;
                    b = bArr[i3 + 1];
                } else if ((bArr[i3] & 192) == 192) {
                    i2 = (bArr[i3] & 31) << 6;
                    b = bArr[i3 + 1];
                } else {
                    c2 = (char) (bArr[i3] & 255);
                    i3++;
                }
                c2 = (char) (i2 | (b & 63));
                i3 += 2;
            }
            cArr[i6] = c2;
            i6++;
        }
        return new String(cArr);
    }

    public static String[] split(String str, char c2) {
        Vector vector = new Vector();
        boolean z = true;
        while (z) {
            int indexOf = str.indexOf(c2);
            if (indexOf > 0) {
                vector.addElement(str.substring(0, indexOf));
                str = str.substring(indexOf + 1);
            } else {
                vector.addElement(str);
                z = false;
            }
        }
        int size = vector.size();
        String[] strArr = new String[size];
        for (int i2 = 0; i2 != size; i2++) {
            strArr[i2] = (String) vector.elementAt(i2);
        }
        return strArr;
    }

    public static byte[] toByteArray(String str) {
        int length = str.length();
        byte[] bArr = new byte[length];
        for (int i2 = 0; i2 != length; i2++) {
            bArr[i2] = (byte) str.charAt(i2);
        }
        return bArr;
    }

    public static String toLowerCase(String str) {
        char[] charArray = str.toCharArray();
        boolean z = false;
        for (int i2 = 0; i2 != charArray.length; i2++) {
            char c2 = charArray[i2];
            if ('A' <= c2 && 'Z' >= c2) {
                charArray[i2] = (char) ((c2 - 'A') + 97);
                z = true;
            }
        }
        return z ? new String(charArray) : str;
    }

    public static byte[] toUTF8ByteArray(String str) {
        return toUTF8ByteArray(str.toCharArray());
    }

    public static byte[] toUTF8ByteArray(char[] cArr) {
        int i2;
        char c2;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int i3 = 0;
        while (i3 < cArr.length) {
            char c3 = cArr[i3];
            char c4 = c3;
            if (c3 >= '\u0080') {
                if (c3 < '\u0800') {
                    i2 = (c3 >> 6) | 192;
                } else if (c3 < '\ud800' || c3 > '\udfff') {
                    byteArrayOutputStream.write((c3 >> '\f') | 224);
                    i2 = ((c3 >> 6) & 63) | 128;
                } else {
                    i3++;
                    if (i3 >= cArr.length) {
                        throw new IllegalStateException("invalid UTF-16 codepoint");
                    }
                    char c5 = cArr[i3];
                    if (c3 > '\udbff') {
                        throw new IllegalStateException("invalid UTF-16 codepoint");
                    }
                    int i4 = (((c3 & '\u03ff') << 10) | (c5 & '\u03ff')) + 65536;
                    byteArrayOutputStream.write((i4 >> 18) | 240);
                    byteArrayOutputStream.write(((i4 >> 12) & 63) | 128);
                    byteArrayOutputStream.write(((i4 >> 6) & 63) | 128);
                    c2 = i4;
                    c4 = (c2 & '?') | 128;
                }
                byteArrayOutputStream.write(i2);
                c2 = c3;
                c4 = (c2 & '?') | 128;
            }
            byteArrayOutputStream.write(c4);
            i3++;
        }
        return byteArrayOutputStream.toByteArray();
    }

    public static String toUpperCase(String str) {
        char[] charArray = str.toCharArray();
        boolean z = false;
        for (int i2 = 0; i2 != charArray.length; i2++) {
            char c2 = charArray[i2];
            if ('a' <= c2 && 'z' >= c2) {
                charArray[i2] = (char) ((c2 - 'a') + 65);
                z = true;
            }
        }
        return z ? new String(charArray) : str;
    }
}

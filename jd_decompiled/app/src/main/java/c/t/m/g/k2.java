package c.t.m.g;

import com.huawei.hms.framework.common.ContainerUtils;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/* loaded from: classes.dex */
public class k2 {
    public static final char[] a = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".toCharArray();

    public static int a(char c2) {
        int i2;
        if (c2 < 'A' || c2 > 'Z') {
            if (c2 >= 'a' && c2 <= 'z') {
                i2 = c2 - 'a';
            } else if (c2 < '0' || c2 > '9') {
                if (c2 != '+') {
                    if (c2 != '/') {
                        if (c2 == '=') {
                            return 0;
                        }
                        throw new RuntimeException("unexpected code: ".concat(String.valueOf(c2)));
                    }
                    return 63;
                }
                return 62;
            } else {
                i2 = (c2 - '0') + 26;
            }
            return i2 + 26;
        }
        return c2 - 'A';
    }

    public static String b(byte[] bArr) {
        String str;
        int length = bArr.length;
        StringBuffer stringBuffer = new StringBuffer((bArr.length * 3) / 2);
        int i2 = length - 3;
        int i3 = 0;
        loop0: while (true) {
            int i4 = 0;
            while (i3 <= i2) {
                int i5 = ((bArr[i3] & 255) << 16) | ((bArr[i3 + 1] & 255) << 8) | (bArr[i3 + 2] & 255);
                char[] cArr = a;
                stringBuffer.append(cArr[(i5 >> 18) & 63]);
                stringBuffer.append(cArr[(i5 >> 12) & 63]);
                stringBuffer.append(cArr[(i5 >> 6) & 63]);
                stringBuffer.append(cArr[i5 & 63]);
                i3 += 3;
                int i6 = i4 + 1;
                if (i4 >= 14) {
                    break;
                }
                i4 = i6;
            }
        }
        int i7 = length + 0;
        if (i3 != i7 - 2) {
            if (i3 == i7 - 1) {
                int i8 = (bArr[i3] & 255) << 16;
                char[] cArr2 = a;
                stringBuffer.append(cArr2[(i8 >> 18) & 63]);
                stringBuffer.append(cArr2[(i8 >> 12) & 63]);
                str = "==";
            }
            return stringBuffer.toString();
        }
        int i9 = ((bArr[i3 + 1] & 255) << 8) | ((bArr[i3] & 255) << 16);
        char[] cArr3 = a;
        stringBuffer.append(cArr3[(i9 >> 18) & 63]);
        stringBuffer.append(cArr3[(i9 >> 12) & 63]);
        stringBuffer.append(cArr3[(i9 >> 6) & 63]);
        str = ContainerUtils.KEY_VALUE_DELIMITER;
        stringBuffer.append(str);
        return stringBuffer.toString();
    }

    public static void c(String str, OutputStream outputStream) {
        int length = str.length();
        int i2 = 0;
        while (true) {
            if (i2 < length && str.charAt(i2) <= ' ') {
                i2++;
            } else if (i2 == length) {
                return;
            } else {
                int i3 = i2 + 2;
                int i4 = i2 + 3;
                int a2 = (a(str.charAt(i2)) << 18) + (a(str.charAt(i2 + 1)) << 12) + (a(str.charAt(i3)) << 6) + a(str.charAt(i4));
                outputStream.write((a2 >> 16) & 255);
                if (str.charAt(i3) == '=') {
                    return;
                }
                outputStream.write((a2 >> 8) & 255);
                if (str.charAt(i4) == '=') {
                    return;
                }
                outputStream.write(a2 & 255);
                i2 += 4;
            }
        }
    }

    public static byte[] d(String str) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            c(str, byteArrayOutputStream);
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            try {
                byteArrayOutputStream.close();
            } catch (IOException e2) {
                System.err.println("Error while decoding BASE64: " + e2.toString());
            }
            return byteArray;
        } catch (IOException unused) {
            throw new RuntimeException();
        }
    }
}

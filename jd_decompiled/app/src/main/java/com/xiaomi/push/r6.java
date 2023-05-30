package com.xiaomi.push;

import com.huawei.hms.framework.common.ContainerUtils;
import java.util.Random;

/* loaded from: classes11.dex */
public class r6 {
    private static final char[] a = "&quot;".toCharArray();
    private static final char[] b = "&apos;".toCharArray();

    /* renamed from: c  reason: collision with root package name */
    private static final char[] f18984c = "&amp;".toCharArray();
    private static final char[] d = "&lt;".toCharArray();

    /* renamed from: e  reason: collision with root package name */
    private static final char[] f18985e = "&gt;".toCharArray();

    /* renamed from: f  reason: collision with root package name */
    private static Random f18986f = new Random();

    /* renamed from: g  reason: collision with root package name */
    private static char[] f18987g = "0123456789abcdefghijklmnopqrstuvwxyz0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

    public static String a(int i2) {
        if (i2 < 1) {
            return null;
        }
        char[] cArr = new char[i2];
        for (int i3 = 0; i3 < i2; i3++) {
            cArr[i3] = f18987g[f18986f.nextInt(71)];
        }
        return new String(cArr);
    }

    public static String b(String str) {
        if (str == null) {
            return null;
        }
        char[] charArray = str.toCharArray();
        int length = charArray.length;
        double d2 = length;
        Double.isNaN(d2);
        StringBuilder sb = new StringBuilder((int) (d2 * 1.3d));
        int i2 = 0;
        int i3 = 0;
        while (i2 < length) {
            char c2 = charArray[i2];
            if (c2 <= '>') {
                if (c2 == '<') {
                    if (i2 > i3) {
                        sb.append(charArray, i3, i2 - i3);
                    }
                    i3 = i2 + 1;
                    sb.append(d);
                } else if (c2 == '>') {
                    if (i2 > i3) {
                        sb.append(charArray, i3, i2 - i3);
                    }
                    i3 = i2 + 1;
                    sb.append(f18985e);
                } else if (c2 == '&') {
                    if (i2 > i3) {
                        sb.append(charArray, i3, i2 - i3);
                    }
                    int i4 = i2 + 5;
                    if (length <= i4 || charArray[i2 + 1] != '#' || !Character.isDigit(charArray[i2 + 2]) || !Character.isDigit(charArray[i2 + 3]) || !Character.isDigit(charArray[i2 + 4]) || charArray[i4] != ';') {
                        i3 = i2 + 1;
                        sb.append(f18984c);
                    }
                } else if (c2 == '\"') {
                    if (i2 > i3) {
                        sb.append(charArray, i3, i2 - i3);
                    }
                    i3 = i2 + 1;
                    sb.append(a);
                } else if (c2 == '\'') {
                    if (i2 > i3) {
                        sb.append(charArray, i3, i2 - i3);
                    }
                    i3 = i2 + 1;
                    sb.append(b);
                }
            }
            i2++;
        }
        if (i3 == 0) {
            return str;
        }
        if (i2 > i3) {
            sb.append(charArray, i3, i2 - i3);
        }
        return sb.toString();
    }

    public static final String c(String str, String str2, String str3) {
        if (str == null) {
            return null;
        }
        int indexOf = str.indexOf(str2, 0);
        if (indexOf < 0) {
            return str;
        }
        char[] charArray = str.toCharArray();
        char[] charArray2 = str3.toCharArray();
        int length = str2.length();
        StringBuilder sb = new StringBuilder(charArray.length);
        sb.append(charArray, 0, indexOf);
        sb.append(charArray2);
        int i2 = indexOf + length;
        while (true) {
            int indexOf2 = str.indexOf(str2, i2);
            if (indexOf2 <= 0) {
                sb.append(charArray, i2, charArray.length - i2);
                return sb.toString();
            }
            sb.append(charArray, i2, indexOf2 - i2);
            sb.append(charArray2);
            i2 = indexOf2 + length;
        }
    }

    public static String d(byte[] bArr) {
        return String.valueOf(m0.e(bArr));
    }

    public static final String e(String str) {
        return c(c(c(c(c(str, "&lt;", "<"), "&gt;", ">"), "&quot;", "\""), "&apos;", "'"), "&amp;", ContainerUtils.FIELD_DELIMITER);
    }
}

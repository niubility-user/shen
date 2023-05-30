package com.huawei.secure.android.common.encrypt.d;

import android.annotation.SuppressLint;

/* loaded from: classes12.dex */
public class a {
    private static final String a = "BaseKeyUtil";

    private static int a(int i2, int i3, int i4) {
        if (i3 < i2) {
            i2 = i3;
        }
        return i4 < i2 ? i4 : i2;
    }

    private static boolean b(int i2) {
        return i2 >= 16;
    }

    private static boolean c(int i2, byte[] bArr) {
        return b(i2) & d(bArr);
    }

    private static boolean d(byte[] bArr) {
        return bArr.length >= 16;
    }

    public static byte[] e(String str, String str2, String str3, String str4, int i2, boolean z) {
        return g(str, str2, str3, c.b(str4), i2, z);
    }

    public static byte[] f(String str, String str2, String str3, byte[] bArr, int i2, int i3, boolean z) {
        byte[] b = c.b(str);
        byte[] b2 = c.b(str2);
        byte[] b3 = c.b(str3);
        int a2 = a(b.length, b2.length, b3.length);
        if (c(a2, bArr)) {
            char[] cArr = new char[a2];
            for (int i4 = 0; i4 < a2; i4++) {
                cArr[i4] = (char) ((b[i4] ^ b2[i4]) ^ b3[i4]);
            }
            if (!z) {
                f.d(a, "exportRootKey: sha1");
                return com.huawei.secure.android.common.encrypt.b.a.b(cArr, bArr, i2, i3 * 8);
            }
            f.d(a, "exportRootKey: sha256");
            return com.huawei.secure.android.common.encrypt.b.a.c(cArr, bArr, i2, i3 * 8);
        }
        throw new IllegalArgumentException("key length must be more than 128bit.");
    }

    @SuppressLint({"NewApi"})
    public static byte[] g(String str, String str2, String str3, byte[] bArr, int i2, boolean z) {
        return f(str, str2, str3, bArr, 10000, i2, z);
    }

    @SuppressLint({"NewApi"})
    public static byte[] h(String str, String str2, String str3, byte[] bArr, boolean z) {
        return g(str, str2, str3, bArr, 16, z);
    }
}

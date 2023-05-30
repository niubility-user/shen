package com.tencent.mapsdk.internal;

import java.io.InputStream;

/* loaded from: classes9.dex */
public class dc implements gc {
    private static long a(int i2, InputStream inputStream) {
        long j2 = 0;
        for (int i3 = i2 - 1; i3 >= 0; i3--) {
            j2 |= (b(inputStream) & 255) << (i3 * 8);
        }
        return j2;
    }

    public static boolean a(InputStream inputStream) {
        return b(inputStream) != 0;
    }

    public static byte[] a(InputStream inputStream, int i2) {
        byte[] bArr = new byte[i2];
        int i3 = 0;
        while (i3 < i2) {
            i3 += inputStream.read(bArr, i3, i2 - i3);
        }
        return bArr;
    }

    public static byte b(InputStream inputStream) {
        return (byte) inputStream.read();
    }

    public static char c(InputStream inputStream) {
        long j2 = 0;
        for (int i2 = 0; i2 < 2; i2++) {
            j2 |= (b(inputStream) & 255) << (i2 * 8);
        }
        return (char) j2;
    }

    public static int d(InputStream inputStream) {
        return (int) a(4, inputStream);
    }

    public static long e(InputStream inputStream) {
        return a(8, inputStream);
    }

    public static int f(InputStream inputStream) {
        return (int) a(2, inputStream);
    }

    public static String g(InputStream inputStream) {
        return new String(a(inputStream, f(inputStream)));
    }
}

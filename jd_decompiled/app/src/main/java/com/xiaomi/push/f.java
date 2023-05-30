package com.xiaomi.push;

import android.content.Context;

/* loaded from: classes11.dex */
public class f {
    static final char[] a = "0123456789ABCDEF".toCharArray();

    public static String a(byte[] bArr, int i2, int i3) {
        StringBuilder sb = new StringBuilder(i3 * 2);
        for (int i4 = 0; i4 < i3; i4++) {
            int i5 = bArr[i2 + i4] & 255;
            char[] cArr = a;
            sb.append(cArr[i5 >> 4]);
            sb.append(cArr[i5 & 15]);
        }
        return sb.toString();
    }

    public static boolean b(Context context) {
        return e.a;
    }
}

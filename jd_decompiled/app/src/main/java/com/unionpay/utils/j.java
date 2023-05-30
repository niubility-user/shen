package com.unionpay.utils;

import android.util.Log;

/* loaded from: classes11.dex */
public final class j {
    private static int a = Integer.MAX_VALUE;

    private static int a(int i2, String str, String str2) {
        if (str != null && str2 != null) {
            if (i2 == 2) {
                return Log.v(str, str2);
            }
            if (i2 == 3) {
                return Log.d(str, str2);
            }
            if (i2 == 4) {
                return Log.i(str, str2);
            }
            if (i2 == 5) {
                return Log.w(str, str2);
            }
            if (i2 == 6) {
                return Log.e(str, str2);
            }
        }
        return 0;
    }

    public static int a(String str, String str2) {
        if (a <= 3) {
            a(3, str, str2);
            return 0;
        }
        return 0;
    }

    public static int b(String str, String str2) {
        if (a <= 4) {
            a(4, str, str2);
            return 0;
        }
        return 0;
    }

    public static int c(String str, String str2) {
        if (a <= 6) {
            return a(6, str, str2);
        }
        return 0;
    }
}

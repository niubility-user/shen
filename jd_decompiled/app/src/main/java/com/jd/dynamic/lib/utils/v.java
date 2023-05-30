package com.jd.dynamic.lib.utils;

import android.content.SharedPreferences;
import com.jd.dynamic.base.DynamicSdk;

/* loaded from: classes13.dex */
public class v {
    private static SharedPreferences a;

    public static long a(String str, long j2) {
        return b().getLong(str, j2);
    }

    public static SharedPreferences b() {
        if (a == null) {
            synchronized (v.class) {
                if (a == null) {
                    a = DynamicSdk.getEngine().getContext().getSharedPreferences("dynamicSdk", 0);
                }
            }
        }
        return a;
    }

    public static void c(String str, boolean z) {
        b().edit().putBoolean(str, z).apply();
    }

    public static boolean d(String str, boolean z) {
        return b().getBoolean(str, z);
    }
}

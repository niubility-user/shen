package com.jingdong.sdk.dialingtest.c.e;

import android.content.SharedPreferences;

/* loaded from: classes7.dex */
public class b {
    private static SharedPreferences a;

    public static long a(String str, long j2) {
        return b().getLong(str, j2);
    }

    private static synchronized SharedPreferences b() {
        SharedPreferences sharedPreferences;
        synchronized (b.class) {
            if (a == null) {
                a = com.jingdong.sdk.dialingtest.b.k().j().getSharedPreferences("jdAndroidDialingTest_", 0);
            }
            sharedPreferences = a;
        }
        return sharedPreferences;
    }

    public static String c(String str, String str2) {
        return b().getString(str, str2);
    }

    public static boolean d(String str) {
        return b().contains(str);
    }

    public static void e(String str, long j2) {
        b().edit().putLong(str, j2).apply();
    }

    public static void f(String str, String str2) {
        b().edit().putString(str, str2).apply();
    }
}

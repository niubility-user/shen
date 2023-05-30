package com.jingdong.sdk.jdupgrade.a.j;

import android.content.SharedPreferences;

/* loaded from: classes7.dex */
public class k {
    private static SharedPreferences a;

    public static int a(String str, int i2) {
        if (a == null) {
            a = b();
        }
        return a.getInt(str, i2);
    }

    public static long a(String str, long j2) {
        if (a == null) {
            a = b();
        }
        return a.getLong(str, j2);
    }

    private static SharedPreferences.Editor a() {
        if (a == null) {
            a = b();
        }
        return a.edit();
    }

    public static String a(String str, String str2) {
        if (a == null) {
            a = b();
        }
        return a.getString(str, str2);
    }

    private static SharedPreferences b() {
        if (a == null) {
            a = com.jingdong.sdk.jdupgrade.a.c.j().getSharedPreferences("upgrade_sp", 0);
        }
        return a;
    }

    public static void b(String str, int i2) {
        a().putInt(str, i2).apply();
    }

    public static void b(String str, long j2) {
        a().putLong(str, j2).apply();
    }

    public static void b(String str, String str2) {
        if (str2 == null) {
            str2 = "";
        }
        a().putString(str, str2).apply();
    }
}

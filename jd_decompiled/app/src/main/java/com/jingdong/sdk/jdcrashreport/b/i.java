package com.jingdong.sdk.jdcrashreport.b;

import android.content.SharedPreferences;

/* loaded from: classes7.dex */
public class i {
    private static SharedPreferences a;

    public static int a(String str, int i2) {
        if (a == null) {
            a = i();
        }
        return a.getInt(str, i2);
    }

    public static long b(String str, long j2) {
        if (a == null) {
            a = i();
        }
        return a.getLong(str, j2);
    }

    public static void c() {
        i();
    }

    public static void d(String str, String str2) {
        if (str2 == null) {
            str2 = "";
        }
        e().putString(str, str2).commit();
    }

    public static SharedPreferences.Editor e() {
        if (a == null) {
            a = i();
        }
        return a.edit();
    }

    public static String f(String str, String str2) {
        if (a == null) {
            a = i();
        }
        return a.getString(str, str2);
    }

    public static void g(String str, int i2) {
        e().putInt(str, i2).commit();
    }

    public static void h(String str, long j2) {
        e().putLong(str, j2).commit();
    }

    private static synchronized SharedPreferences i() {
        SharedPreferences sharedPreferences;
        synchronized (i.class) {
            if (a == null) {
                a = com.jingdong.sdk.jdcrashreport.d.I().getSharedPreferences("crash_sp", 0);
            }
            sharedPreferences = a;
        }
        return sharedPreferences;
    }
}

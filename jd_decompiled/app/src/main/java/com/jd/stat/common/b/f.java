package com.jd.stat.common.b;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

/* loaded from: classes18.dex */
public class f {
    private static SharedPreferences a;

    public static synchronized SharedPreferences a(Context context) {
        SharedPreferences sharedPreferences;
        synchronized (f.class) {
            if (a == null) {
                a = context.getSharedPreferences("jma_sp_file", 0);
            }
            sharedPreferences = a;
        }
        return sharedPreferences;
    }

    public static boolean b(String str, boolean z) {
        Context context = com.jd.stat.security.c.a;
        return context == null ? z : a(context).getBoolean(str, z);
    }

    public static String c(String str, String str2) {
        String b = b(str, str2);
        return TextUtils.isEmpty(b) ? str2 : b;
    }

    public static int b(String str, int i2) {
        Context context = com.jd.stat.security.c.a;
        return context == null ? i2 : a(context).getInt(str, i2);
    }

    public static void a(String str, boolean z) {
        Context context = com.jd.stat.security.c.a;
        if (context != null) {
            a(context).edit().putBoolean(str, z).apply();
        }
    }

    public static long b(String str, long j2) {
        Context context = com.jd.stat.security.c.a;
        return context == null ? j2 : a(context).getLong(str, j2);
    }

    public static void a(String str, int i2) {
        Context context = com.jd.stat.security.c.a;
        if (context != null) {
            a(context).edit().putInt(str, i2).apply();
        }
    }

    public static String b(String str, String str2) {
        Context context = com.jd.stat.security.c.a;
        return context == null ? str2 : a(context).getString(str, str2);
    }

    public static void a(String str, long j2) {
        if (com.jd.stat.security.c.a != null) {
            b.b("JDMob.Security.SharedPreferencesUtil", "key = " + str + ",value = " + j2);
            a(com.jd.stat.security.c.a).edit().putLong(str, j2).apply();
        }
    }

    public static void a(String str, String str2) {
        Context context = com.jd.stat.security.c.a;
        if (context != null) {
            a(context).edit().putString(str, str2).apply();
        }
    }
}

package com.jd.fireeye.b;

import android.content.Context;
import android.content.SharedPreferences;

/* loaded from: classes13.dex */
public class o {
    private static SharedPreferences a;

    public static synchronized SharedPreferences a(Context context) {
        SharedPreferences sharedPreferences;
        synchronized (o.class) {
            if (a == null) {
                a = context.getSharedPreferences("jma_sp_file", 0);
            }
            sharedPreferences = a;
        }
        return sharedPreferences;
    }

    public static void b(String str, boolean z) {
        Context context = com.jd.fireeye.security.a.a;
        if (context != null) {
            a(context).edit().putBoolean(str, z).apply();
        }
    }

    public static void b(String str, String str2) {
        Context context = com.jd.fireeye.security.a.a;
        if (context != null) {
            a(context).edit().putString(str, str2).apply();
        }
    }

    public static boolean a(String str, boolean z) {
        Context context = com.jd.fireeye.security.a.a;
        return context == null ? z : a(context).getBoolean(str, z);
    }

    public static String a(String str, String str2) {
        Context context = com.jd.fireeye.security.a.a;
        return context == null ? str2 : a(context).getString(str, str2);
    }
}

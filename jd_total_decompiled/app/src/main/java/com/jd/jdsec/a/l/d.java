package com.jd.jdsec.a.l;

import android.content.Context;
import android.content.SharedPreferences;
import com.jd.jdsec.c.g;

/* loaded from: classes13.dex */
public class d {
    private static SharedPreferences a;

    public static long a(String str, long j2) {
        Context context = g.a;
        return context == null ? j2 : b(context).getLong(str, j2);
    }

    public static synchronized SharedPreferences b(Context context) {
        SharedPreferences sharedPreferences;
        synchronized (d.class) {
            if (a == null) {
                a = context.getSharedPreferences("jdsec_sp_file", 0);
            }
            sharedPreferences = a;
        }
        return sharedPreferences;
    }

    public static String c(String str, String str2) {
        Context context = g.a;
        return context == null ? str2 : b(context).getString(str, str2);
    }

    public static void d(String str, long j2) {
        if (g.a != null) {
            b.e("JDSec.Security.SharedPreferencesUtil", "key = " + str + ",value = " + j2);
            b(g.a).edit().putLong(str, j2).apply();
        }
    }

    public static void e(String str, String str2) {
        Context context = g.a;
        if (context != null) {
            b(context).edit().putString(str, str2).apply();
        }
    }
}

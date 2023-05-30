package com.jd.verify.j;

import android.content.Context;
import android.content.SharedPreferences;

/* loaded from: classes18.dex */
public class e {
    private static SharedPreferences a;

    private static String a(Context context, String str, String str2) {
        return context != null ? b(context).getString(str, str2) : "";
    }

    public static synchronized SharedPreferences b(Context context) {
        SharedPreferences sharedPreferences;
        synchronized (e.class) {
            if (a == null) {
                a = context.getSharedPreferences("verify_sp_file", 0);
            }
            sharedPreferences = a;
        }
        return sharedPreferences;
    }

    public static boolean a(Context context, String str, boolean z) {
        if (context != null) {
            return b(context).getBoolean(str, z);
        }
        return false;
    }

    public static String a(Context context) {
        return a(context, "verify_fp", "");
    }

    public static void a(Context context, String str) {
        b(context, "verify_fp", str);
    }

    public static void b(Context context, String str, String str2) {
        if (context != null) {
            b(context).edit().putString(str, str2).apply();
        }
    }

    public static void b(Context context, String str, boolean z) {
        if (context != null) {
            b(context).edit().putBoolean(str, z).apply();
        }
    }
}

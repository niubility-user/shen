package com.jingdong.c.b;

import android.content.Context;
import android.content.SharedPreferences;

/* loaded from: classes10.dex */
public final class k {
    static SharedPreferences a;
    static Context b;

    public static synchronized SharedPreferences a(Context context) {
        SharedPreferences sharedPreferences;
        synchronized (k.class) {
            b = context;
            if (a == null) {
                a = context.getSharedPreferences("JdAndroidUUID", 0);
            }
            sharedPreferences = a;
        }
        return sharedPreferences;
    }

    public static void b(String str, boolean z) {
        a.edit().putBoolean(str, z).apply();
    }

    public static boolean c(String str) {
        return a.getBoolean(str, false);
    }
}

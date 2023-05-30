package com.huawei.secure.android.common.ssl.g;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;

/* loaded from: classes12.dex */
public class h {
    private static SharedPreferences a;

    public static String a(String str, String str2, Context context) {
        return b(context).getString(str, str2);
    }

    public static synchronized SharedPreferences b(Context context) {
        SharedPreferences sharedPreferences;
        synchronized (h.class) {
            if (a == null) {
                if (Build.VERSION.SDK_INT >= 24) {
                    a = context.createDeviceProtectedStorageContext().getSharedPreferences("aegis", 0);
                } else {
                    a = context.getApplicationContext().getSharedPreferences("aegis", 0);
                }
            }
            sharedPreferences = a;
        }
        return sharedPreferences;
    }

    public static void c(String str, String str2, Context context) {
        b(context).edit().putString(str, str2).apply();
    }
}

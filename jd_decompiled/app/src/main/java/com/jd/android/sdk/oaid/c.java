package com.jd.android.sdk.oaid;

import android.content.Context;
import android.content.SharedPreferences;

/* loaded from: classes12.dex */
public class c {
    private static c a;
    private static SharedPreferences b;

    private c() {
    }

    private c(Context context) {
        if (b == null) {
            b = context.getSharedPreferences("sp-base-info-sdk", 0);
        }
    }

    public static c a(Context context) {
        if (a == null) {
            synchronized (c.class) {
                if (a == null) {
                    a = new c(context);
                }
            }
        }
        return a;
    }

    public static void a(String str, String str2) {
        b.edit().putString(str, str2).apply();
    }

    public static String b(String str, String str2) {
        return b.getString(str, str2);
    }
}

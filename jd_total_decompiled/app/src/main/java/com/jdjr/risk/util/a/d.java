package com.jdjr.risk.util.a;

import android.content.Context;
import android.content.SharedPreferences;

/* loaded from: classes18.dex */
public class d {
    private static volatile SharedPreferences a;

    public static SharedPreferences a(Context context) {
        if (a == null) {
            synchronized (d.class) {
                if (a == null) {
                    a = context.getSharedPreferences("BIOMETRIC_OBJECT", 0);
                }
            }
        }
        return a;
    }

    public static boolean a(Context context, String str, String str2) {
        try {
            a(context).edit().putString(str, str2).commit();
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    public static String b(Context context, String str, String str2) {
        SharedPreferences a2 = a(context);
        return a2 != null ? a2.getString(str, str2) : str2;
    }
}

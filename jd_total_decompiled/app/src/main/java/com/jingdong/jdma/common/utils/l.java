package com.jingdong.jdma.common.utils;

import android.content.Context;
import android.content.SharedPreferences;

/* loaded from: classes12.dex */
public class l {
    private static l b;
    private SharedPreferences a;

    private l(Context context) {
        if (context != null) {
            this.a = (context.getApplicationContext() != null ? context.getApplicationContext() : context).getSharedPreferences("jd_ma_sdk", 0);
        }
    }

    public static l a(Context context) {
        if (b == null) {
            synchronized (l.class) {
                if (b == null) {
                    b = new l(context);
                }
            }
        }
        return b;
    }

    public void b(String str, String str2) {
        SharedPreferences sharedPreferences = this.a;
        if (sharedPreferences != null) {
            sharedPreferences.edit().putString(str, str2).apply();
        }
    }

    public void b(String str) {
        SharedPreferences sharedPreferences = this.a;
        if (sharedPreferences != null) {
            sharedPreferences.edit().remove(str).apply();
        }
    }

    public String a(String str, String str2) {
        SharedPreferences sharedPreferences = this.a;
        return sharedPreferences != null ? sharedPreferences.getString(str, str2) : str2;
    }

    public boolean a(String str) {
        SharedPreferences sharedPreferences = this.a;
        return sharedPreferences != null && sharedPreferences.contains(str);
    }
}

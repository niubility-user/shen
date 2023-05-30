package com.jingdong.common.lbs.utils;

import android.content.SharedPreferences;

/* loaded from: classes5.dex */
public final class c {
    private static SharedPreferences a;

    public static synchronized SharedPreferences a() {
        SharedPreferences sharedPreferences;
        synchronized (c.class) {
            if (a == null) {
                a = com.jingdong.common.lbs.proxy.a.a.getSharedPreferences("CCF_CONFIG_LBS_REPORT", 0);
            }
            sharedPreferences = a;
        }
        return sharedPreferences;
    }

    public static void a(String str, String str2) {
        a().edit().putString(str, str2).apply();
    }
}

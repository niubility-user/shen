package com.jd.lib.cashier.sdk.core.utils;

import android.app.Activity;
import android.content.SharedPreferences;

/* loaded from: classes14.dex */
public class c0 {
    private static volatile SharedPreferences a;

    public static synchronized boolean a(Activity activity, String str) {
        synchronized (c0.class) {
            if (g0.a(activity)) {
                return c(activity).contains(str);
            }
            return false;
        }
    }

    public static synchronized boolean b(Activity activity, String str, boolean z) {
        synchronized (c0.class) {
            if (g0.a(activity)) {
                return c(activity).getBoolean(str, z);
            }
            return false;
        }
    }

    private static synchronized SharedPreferences c(Activity activity) {
        SharedPreferences sharedPreferences;
        synchronized (c0.class) {
            if (a == null) {
                a = activity.getSharedPreferences("jdAndroidClient_Cashier", 0);
            }
            sharedPreferences = a;
        }
        return sharedPreferences;
    }

    public static synchronized String d(Activity activity, String str, String str2) {
        synchronized (c0.class) {
            if (g0.a(activity)) {
                return c(activity).getString(str, str2);
            }
            return "";
        }
    }

    public static synchronized void e(Activity activity, String str, boolean z) {
        synchronized (c0.class) {
            if (g0.a(activity)) {
                c(activity).edit().putBoolean(str, z).apply();
            }
        }
    }

    public static synchronized void f(Activity activity, String str, String str2) {
        synchronized (c0.class) {
            if (g0.a(activity)) {
                c(activity).edit().putString(str, str2).apply();
            }
        }
    }
}

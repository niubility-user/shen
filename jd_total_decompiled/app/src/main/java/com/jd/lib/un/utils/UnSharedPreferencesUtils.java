package com.jd.lib.un.utils;

import android.content.Context;
import android.content.SharedPreferences;

/* loaded from: classes16.dex */
public class UnSharedPreferencesUtils {
    private static final String JD_SHARE_PREFERENCE = "jdAndroidClient";
    private static SharedPreferences mSharedPreferences;

    private UnSharedPreferencesUtils() {
    }

    public static boolean contains(Context context, String str) {
        return getSharedPreferences(context).contains(str);
    }

    public static boolean getBoolean(Context context, String str, boolean z) {
        return getSharedPreferences(context).getBoolean(str, z);
    }

    public static float getFloat(Context context, String str, float f2) {
        return getSharedPreferences(context).getFloat(str, f2);
    }

    public static int getInt(Context context, String str, int i2) {
        return getSharedPreferences(context).getInt(str, i2);
    }

    public static long getLong(Context context, String str, long j2) {
        return getSharedPreferences(context).getLong(str, j2);
    }

    public static synchronized SharedPreferences getSharedPreferences(Context context) {
        SharedPreferences sharedPreferences;
        synchronized (UnSharedPreferencesUtils.class) {
            if (mSharedPreferences == null) {
                mSharedPreferences = new UnJDSharedPreferences(context, JD_SHARE_PREFERENCE, 0);
            }
            sharedPreferences = mSharedPreferences;
        }
        return sharedPreferences;
    }

    public static String getString(Context context, String str, String str2) {
        return getSharedPreferences(context).getString(str, str2);
    }

    public static void putBoolean(Context context, String str, boolean z) {
        getSharedPreferences(context).edit().putBoolean(str, z).apply();
    }

    public static void putFloat(Context context, String str, float f2) {
        getSharedPreferences(context).edit().putFloat(str, f2).apply();
    }

    public static void putInt(Context context, String str, int i2) {
        getSharedPreferences(context).edit().putInt(str, i2).apply();
    }

    public static void putLong(Context context, String str, long j2) {
        getSharedPreferences(context).edit().putLong(str, j2).apply();
    }

    public static void putString(Context context, String str, String str2) {
        getSharedPreferences(context).edit().putString(str, str2).apply();
    }

    public static void remove(Context context, String str) {
        getSharedPreferences(context).edit().remove(str).apply();
    }
}

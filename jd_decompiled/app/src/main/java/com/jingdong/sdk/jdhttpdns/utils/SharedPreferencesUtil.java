package com.jingdong.sdk.jdhttpdns.utils;

import android.content.SharedPreferences;
import com.jingdong.sdk.jdhttpdns.core.HttpDnsImpl;

/* loaded from: classes7.dex */
public class SharedPreferencesUtil {
    private static final String JDHTTPDNS_SHARE_PREFERENCE = "jdhttpdns_shared_preference";
    private static SharedPreferences mSharedPreferences;

    private SharedPreferencesUtil() {
    }

    public static boolean contains(String str) {
        return getSharedPreferences().contains(str);
    }

    public static boolean getBoolean(String str, boolean z) {
        return getSharedPreferences().getBoolean(str, z);
    }

    public static SharedPreferences.Editor getEditor() {
        return getSharedPreferences().edit();
    }

    public static float getFloat(String str, float f2) {
        return getSharedPreferences().getFloat(str, f2);
    }

    public static int getInt(String str, int i2) {
        return getSharedPreferences().getInt(str, i2);
    }

    public static long getLong(String str, long j2) {
        return getSharedPreferences().getLong(str, j2);
    }

    public static synchronized SharedPreferences getSharedPreferences() {
        SharedPreferences sharedPreferences;
        synchronized (SharedPreferencesUtil.class) {
            if (mSharedPreferences == null) {
                mSharedPreferences = HttpDnsImpl.applicationContext.getSharedPreferences(JDHTTPDNS_SHARE_PREFERENCE, 0);
            }
            sharedPreferences = mSharedPreferences;
        }
        return sharedPreferences;
    }

    public static String getString(String str, String str2) {
        return getSharedPreferences().getString(str, str2);
    }

    public static void putBoolean(String str, boolean z) {
        getSharedPreferences().edit().putBoolean(str, z).apply();
    }

    public static void putFloat(String str, float f2) {
        getSharedPreferences().edit().putFloat(str, f2).apply();
    }

    public static void putInt(String str, int i2) {
        getSharedPreferences().edit().putInt(str, i2).apply();
    }

    public static void putLong(String str, long j2) {
        getSharedPreferences().edit().putLong(str, j2).apply();
    }

    public static void putString(String str, String str2) {
        getSharedPreferences().edit().putString(str, str2).apply();
    }

    public static void remove(String str) {
        getSharedPreferences().edit().remove(str).apply();
    }
}

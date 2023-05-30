package com.jingdong.jdsdk.network.utils;

import android.content.SharedPreferences;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.sdk.oklog.OKLog;
import java.util.HashMap;

/* loaded from: classes.dex */
public final class StatSharePreferenceUtil {
    private static final String NETWORK_STAT_SHARE_PREFERENCE = "NetworkStatSharedPref";
    private static SharedPreferences mSharedPreferences;

    private StatSharePreferenceUtil() {
    }

    public static boolean contains(String str) {
        return getSharedPreferences().contains(str);
    }

    public static boolean getBoolean(String str, boolean z) {
        return getSharedPreferences().getBoolean(str, z);
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
        synchronized (StatSharePreferenceUtil.class) {
            if (mSharedPreferences == null) {
                mSharedPreferences = JdSdk.getInstance().getApplication().getApplicationContext().getSharedPreferences(NETWORK_STAT_SHARE_PREFERENCE, 0);
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

    public static void putIntMap(HashMap<String, Integer> hashMap) {
        if (hashMap == null || hashMap.size() <= 0) {
            return;
        }
        SharedPreferences.Editor edit = getSharedPreferences().edit();
        for (String str : hashMap.keySet()) {
            edit.putInt(str, hashMap.get(str).intValue());
            if (OKLog.D) {
                OKLog.d("ExceptionReporter", "saveStatisticData ===> key : " + str + " and value : " + hashMap.get(str));
            }
        }
        edit.apply();
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

package com.jd.skin.lib.Utils;

import android.content.Context;
import android.content.SharedPreferences;

/* loaded from: classes18.dex */
public class JDSkinSharedPreferencesUtils {
    private static SharedPreferences mSharedPreferences;

    public static void clear(Context context) {
        getSharedPrefeerences(context).edit().clear();
    }

    public static boolean getBoolean(Context context, String str, boolean z) {
        return getSharedPrefeerences(context).getBoolean(str, z);
    }

    public static Long getLong(Context context, String str, long j2) {
        return Long.valueOf(getSharedPrefeerences(context).getLong(str, j2));
    }

    private static synchronized SharedPreferences getSharedPrefeerences(Context context) {
        SharedPreferences sharedPreferences;
        synchronized (JDSkinSharedPreferencesUtils.class) {
            if (mSharedPreferences == null && context != null) {
                mSharedPreferences = context.getSharedPreferences(ConstancesUtils.SP_NAME, 0);
            }
            sharedPreferences = mSharedPreferences;
        }
        return sharedPreferences;
    }

    public static String getString(Context context, String str, String str2) {
        return getSharedPrefeerences(context).getString(str, str2);
    }

    public static void putBoolean(Context context, String str, boolean z) {
        getSharedPrefeerences(context).edit().putBoolean(str, z).apply();
    }

    public static void putLong(Context context, String str, long j2) {
        getSharedPrefeerences(context).edit().putLong(str, j2).apply();
    }

    public static void putString(Context context, String str, String str2) {
        getSharedPrefeerences(context).edit().putString(str, str2).apply();
    }
}

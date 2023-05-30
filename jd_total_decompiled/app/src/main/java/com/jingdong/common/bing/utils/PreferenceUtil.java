package com.jingdong.common.bing.utils;

import android.content.Context;
import android.preference.PreferenceManager;

/* loaded from: classes5.dex */
public class PreferenceUtil {
    public static boolean contains(Context context, String str) {
        return PreferenceManager.getDefaultSharedPreferences(context).contains(str);
    }

    public static String get(Context context, String str) {
        return PreferenceManager.getDefaultSharedPreferences(context).getString(str, "");
    }

    public static void put(Context context, String str, String str2) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putString(str, str2).commit();
    }

    public static void put(Context context, String str, long j2) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putLong(str, j2).commit();
    }
}

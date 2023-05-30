package com.jingdong.manto.utils;

import android.content.Context;
import android.content.SharedPreferences;

/* loaded from: classes16.dex */
public class MantoSharedPrefrenceUtil {
    private static final String SP_NAME_MANTO = "manto";

    public static void clearPreference(Context context, SharedPreferences sharedPreferences) {
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.clear();
        edit.apply();
    }

    private static SharedPreferences getMantoPref(Context context) {
        return context.getSharedPreferences(SP_NAME_MANTO, 0);
    }

    public static boolean getPrefBoolean(Context context, String str, boolean z) {
        return getMantoPref(context).getBoolean(str, z);
    }

    public static float getPrefFloat(Context context, String str, float f2) {
        return getMantoPref(context).getFloat(str, f2);
    }

    public static int getPrefInt(Context context, String str, int i2) {
        return getMantoPref(context).getInt(str, i2);
    }

    public static long getPrefLong(Context context, String str, long j2) {
        return getMantoPref(context).getLong(str, j2);
    }

    public static String getPrefString(Context context, String str, String str2) {
        return getMantoPref(context).getString(str, str2);
    }

    public static boolean hasKey(Context context, String str) {
        return getMantoPref(context).contains(str);
    }

    public static void setAndApplyBoolean(Context context, String str, boolean z) {
        getMantoPref(context).edit().putBoolean(str, z).apply();
    }

    public static void setAndApplyFloat(Context context, String str, float f2) {
        getMantoPref(context).edit().putFloat(str, f2).apply();
    }

    public static void setAndApplyInt(Context context, String str, int i2) {
        getMantoPref(context).edit().putInt(str, i2).apply();
    }

    public static void setAndApplyLong(Context context, String str, long j2) {
        getMantoPref(context).edit().putLong(str, j2).apply();
    }

    public static void setAndApplyString(Context context, String str, String str2) {
        getMantoPref(context).edit().putString(str, str2).apply();
    }

    public static void setAndCommitBoolean(Context context, String str, boolean z) {
        getMantoPref(context).edit().putBoolean(str, z).commit();
    }

    public static void setAndCommitFloat(Context context, String str, float f2) {
        getMantoPref(context).edit().putFloat(str, f2).commit();
    }

    public static void setAndCommitInt(Context context, String str, int i2) {
        getMantoPref(context).edit().putInt(str, i2).commit();
    }

    public static void setAndCommitLong(Context context, String str, long j2) {
        getMantoPref(context).edit().putLong(str, j2).commit();
    }

    public static void setAndCommitString(Context context, String str, String str2) {
        getMantoPref(context).edit().putString(str, str2).commit();
    }
}

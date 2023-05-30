package com.jd.libs.hybrid.base.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import java.util.Set;

/* loaded from: classes16.dex */
public class SharedPreferenceUtils {
    @SuppressLint({"ApplySharedPref"})
    public static void apply(SharedPreferences sharedPreferences) {
        sharedPreferences.edit().apply();
    }

    @SuppressLint({"ApplySharedPref"})
    public static void commit(SharedPreferences sharedPreferences) {
        sharedPreferences.edit().commit();
    }

    @SuppressLint({"ApplySharedPref"})
    public static void commitBoolean(SharedPreferences sharedPreferences, String str, boolean z) {
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putBoolean(str, z);
        edit.commit();
    }

    @SuppressLint({"ApplySharedPref"})
    public static void commitFloat(SharedPreferences sharedPreferences, String str, float f2) {
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putFloat(str, f2);
        edit.commit();
    }

    @SuppressLint({"ApplySharedPref"})
    public static void commitInt(SharedPreferences sharedPreferences, String str, int i2) {
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putInt(str, i2);
        edit.commit();
    }

    @SuppressLint({"ApplySharedPref"})
    public static void commitLong(SharedPreferences sharedPreferences, String str, long j2) {
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putLong(str, j2);
        edit.commit();
    }

    @SuppressLint({"ApplySharedPref"})
    public static void commitString(SharedPreferences sharedPreferences, String str, String str2) {
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putString(str, str2);
        edit.commit();
    }

    @SuppressLint({"ApplySharedPref"})
    public static void commitStringSet(SharedPreferences sharedPreferences, String str, Set<String> set) {
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putStringSet(str, set);
        edit.commit();
    }

    public static SharedPreferences createPreference(Context context, String str) {
        if (context != null) {
            return context.getApplicationContext().getSharedPreferences(str, 0);
        }
        return null;
    }

    public static void putBoolean(SharedPreferences sharedPreferences, String str, boolean z) {
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putBoolean(str, z);
        edit.apply();
    }

    public static void putFloat(SharedPreferences sharedPreferences, String str, float f2) {
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putFloat(str, f2);
        edit.apply();
    }

    public static void putInt(SharedPreferences sharedPreferences, String str, int i2) {
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putInt(str, i2);
        edit.apply();
    }

    public static void putLong(SharedPreferences sharedPreferences, String str, long j2) {
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putLong(str, j2);
        edit.apply();
    }

    public static void putString(SharedPreferences sharedPreferences, String str, String str2) {
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putString(str, str2);
        edit.apply();
    }

    public static void putStringSet(SharedPreferences sharedPreferences, String str, Set<String> set) {
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putStringSet(str, set);
        edit.apply();
    }
}

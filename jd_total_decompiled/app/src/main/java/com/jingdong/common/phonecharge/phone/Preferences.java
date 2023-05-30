package com.jingdong.common.phonecharge.phone;

import android.content.Context;
import android.content.SharedPreferences;

/* loaded from: classes5.dex */
public final class Preferences {
    public static String PHONE_CHARGE_COMMON = "lib_phone_charge_main";
    private static Preferences instance;
    private SharedPreferences.Editor mEditor;
    private SharedPreferences mSharedPreferences;

    private Preferences(Context context, String str) {
        SharedPreferences sharedPreferences = context.getSharedPreferences((str == null || str.trim().length() == 0) ? context.getPackageName() : str, 0);
        this.mSharedPreferences = sharedPreferences;
        this.mEditor = sharedPreferences.edit();
    }

    public static synchronized Preferences getPrefer(Context context) {
        Preferences preferences;
        synchronized (Preferences.class) {
            if (instance == null) {
                instance = new Preferences(context, PHONE_CHARGE_COMMON);
            }
            preferences = instance;
        }
        return preferences;
    }

    public static void release() {
        instance = null;
    }

    public boolean exist(String str) {
        return this.mSharedPreferences.contains(str);
    }

    public boolean getBoolean(String str, boolean z) {
        return this.mSharedPreferences.getBoolean(str, z);
    }

    public float getFloat(String str, float f2) {
        return this.mSharedPreferences.getFloat(str, f2);
    }

    public int getInt(String str, int i2) {
        return this.mSharedPreferences.getInt(str, i2);
    }

    public long getLong(String str, long j2) {
        return this.mSharedPreferences.getLong(str, j2);
    }

    public String getString(String str, String str2) {
        return this.mSharedPreferences.getString(str, str2);
    }

    public void putBoolean(String str, boolean z) {
        this.mEditor.putBoolean(str, z);
        this.mEditor.commit();
    }

    public void putFloat(String str, float f2) {
        this.mEditor.putFloat(str, f2);
        this.mEditor.commit();
    }

    public void putInt(String str, int i2) {
        this.mEditor.putInt(str, i2);
        this.mEditor.commit();
    }

    public void putLong(String str, long j2) {
        this.mEditor.putLong(str, j2);
        this.mEditor.commit();
    }

    public void putString(String str, String str2) {
        this.mEditor.putString(str, str2);
        this.mEditor.commit();
    }

    public void remove(String str) {
        this.mEditor.remove(str);
        this.mEditor.commit();
    }

    public static synchronized Preferences getPrefer(Context context, String str) {
        Preferences preferences;
        synchronized (Preferences.class) {
            if (instance == null) {
                instance = new Preferences(context, str);
            }
            preferences = instance;
        }
        return preferences;
    }
}

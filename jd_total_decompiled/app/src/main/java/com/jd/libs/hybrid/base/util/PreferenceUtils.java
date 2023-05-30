package com.jd.libs.hybrid.base.util;

import android.content.Context;
import android.content.SharedPreferences;
import com.jd.libs.hybrid.offlineload.utils.OfflineFileUtils;
import java.util.Set;

/* loaded from: classes16.dex */
public class PreferenceUtils {
    private static volatile SharedPreferences a;

    private static SharedPreferences a(Context context) {
        if (a == null) {
            synchronized (PreferenceUtils.class) {
                if (a == null) {
                    a = SharedPreferenceUtils.createPreference(context, OfflineFileUtils.HYBRID_OFFLINE_ROOT_DIR);
                }
            }
        }
        return a;
    }

    public static boolean getBoolean(Context context, String str, boolean z) {
        return a(context).getBoolean(str, z);
    }

    public static float getFloat(Context context, String str, float f2) {
        return a(context).getFloat(str, f2);
    }

    public static int getInt(Context context, String str, int i2) {
        return a(context).getInt(str, i2);
    }

    public static long getLong(Context context, String str, long j2) {
        return a(context).getLong(str, j2);
    }

    public static String getString(Context context, String str, String str2) {
        return a(context).getString(str, str2);
    }

    public static Set<String> getStringSet(Context context, String str, Set<String> set) {
        return a(context).getStringSet(str, set);
    }

    public static void putBoolean(Context context, String str, boolean z) {
        SharedPreferenceUtils.putBoolean(a(context), str, z);
    }

    public static void putFloat(Context context, String str, float f2) {
        SharedPreferenceUtils.putFloat(a(context), str, f2);
    }

    public static void putInt(Context context, String str, int i2) {
        SharedPreferenceUtils.putInt(a(context), str, i2);
    }

    public static void putLong(Context context, String str, long j2) {
        SharedPreferenceUtils.putLong(a(context), str, j2);
    }

    public static void putString(Context context, String str, String str2) {
        SharedPreferenceUtils.putString(a(context), str, str2);
    }

    public static void putStringSet(Context context, String str, Set<String> set) {
        SharedPreferenceUtils.putStringSet(a(context), str, set);
    }
}

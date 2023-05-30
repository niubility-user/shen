package com.jingdong.common.utils;

import androidx.annotation.Nullable;

/* loaded from: classes6.dex */
public class UpdateHelper {
    private static final String KEY_LAST_VERSION = "personal_last_update_version_";

    private UpdateHelper() {
    }

    @Nullable
    public static String getListVersion(String str) {
        return CommonBase.getJdSharedPreferences().getString(KEY_LAST_VERSION + str, null);
    }

    public static void putListVersion(String str, String str2) {
        CommonBase.getJdSharedPreferences().edit().putString(KEY_LAST_VERSION + str, str2).apply();
    }
}

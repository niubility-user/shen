package com.jingdong.common.utils;

import com.jingdong.jdsdk.utils.SharedPreferencesUtil;

/* loaded from: classes6.dex */
public class AddressStorageUtil {
    public static String getCityIDFromSharedPreferences() {
        return SharedPreferencesUtil.getSharedPreferences().getString("globalCityID", null);
    }

    public static String getDistrictIdFromSharedPreferences() {
        return SharedPreferencesUtil.getSharedPreferences().getString("globalDistrictID", null);
    }

    public static String getProvinceIDFromSharedPreferences() {
        return SharedPreferencesUtil.getSharedPreferences().getString("globalProvinceID", null);
    }

    public static Boolean getRememberStateSharedPreferences() {
        return Boolean.valueOf(CommonBase.getJdSharedPreferences().getBoolean("rememberRegion", false));
    }

    public static String getTownIdFromSharedPreferences() {
        return SharedPreferencesUtil.getSharedPreferences().getString("globalTownID", null);
    }
}

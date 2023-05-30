package com.jingdong.common.messagecenter;

import com.jingdong.jdsdk.utils.SharedPreferencesUtil;

/* loaded from: classes5.dex */
public class BadgeCacheDataUtils {
    private static final String BADGE_AB_SWITCH = "badgeABSwitch";
    private static final String BADGE_HONOR_AB_SWITCH = "badgeHonorABSwitch";
    private static final String BADGE_HUAWEI_AB_SWITCH = "badgeHuaWeiABSwitch";
    private static final String BADGE_STATUS_TURN_ON = "1";
    private static final String BADGE_VIVO_AB_SWITCH = "badgeVIVOABSwitch";
    private static final String DEVICE_BRAND = "deviceBrand";

    public static boolean getBadgeABSwitch() {
        return SharedPreferencesUtil.getBoolean(BADGE_AB_SWITCH, false);
    }

    public static boolean getHonorBadgeABSwitch() {
        return SharedPreferencesUtil.getBoolean(BADGE_HONOR_AB_SWITCH, false);
    }

    public static boolean getHuaWeiBadgeABSwitch() {
        return SharedPreferencesUtil.getBoolean(BADGE_HUAWEI_AB_SWITCH, false);
    }

    public static boolean getVIVOBadgeABSwitch() {
        return SharedPreferencesUtil.getBoolean(BADGE_VIVO_AB_SWITCH, false);
    }

    public static void putBadgeABSwitch(String str) {
        SharedPreferencesUtil.putBoolean(BADGE_AB_SWITCH, "1".equals(str));
    }

    public static void putHonorBadgeABSwitch(String str) {
        SharedPreferencesUtil.putBoolean(BADGE_HONOR_AB_SWITCH, "1".equals(str));
    }

    public static void putHuaWeiBadgeABSwitch(String str) {
        SharedPreferencesUtil.putBoolean(BADGE_HUAWEI_AB_SWITCH, "1".equals(str));
    }

    public static void putVIVOBadgeABSwitch(String str) {
        SharedPreferencesUtil.putBoolean(BADGE_VIVO_AB_SWITCH, "1".equals(str));
    }
}

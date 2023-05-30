package com.jingdong.app.mall.home;

import com.jingdong.jdsdk.utils.SharedPreferencesUtil;

/* loaded from: classes4.dex */
public class HomeSettingBridge {
    private static boolean sShowTopLbsSetting;
    private static final String KEY_HOME_TOP_LBS_OPEN = "key_home_top_lbs_open";
    private static boolean sTopLbsOpen = SharedPreferencesUtil.getBoolean(KEY_HOME_TOP_LBS_OPEN, true);

    public static boolean isTopLbsOpen() {
        return sTopLbsOpen;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void setShowTopLbsSetting(boolean z) {
        sShowTopLbsSetting = z;
    }

    public static void setTopLbsOpen(boolean z) {
        sTopLbsOpen = z;
        SharedPreferencesUtil.putBoolean(KEY_HOME_TOP_LBS_OPEN, z);
    }

    public static boolean showTopLbsSetting() {
        return sShowTopLbsSetting;
    }
}

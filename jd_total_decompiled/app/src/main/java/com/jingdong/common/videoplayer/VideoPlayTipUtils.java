package com.jingdong.common.videoplayer;

import com.jingdong.common.utils.CommonBase;
import com.jingdong.jdsdk.utils.NetUtils;
import java.util.Calendar;

/* loaded from: classes6.dex */
public class VideoPlayTipUtils {
    private static final String NO_WIFI_TIP_TIME = "videoPlayNoWifiTip";
    private static final String WIFI_CHANGE_TO_4G_TIP_TIME = "videoPlayWifi4GTip";
    private static boolean isAlreadyShowNoWifi;
    private static boolean isAlreadyShowWifiChangeTo4G;
    private static long todayTime = getTodayTime();

    static {
        isAlreadyShowNoWifi = CommonBase.getJdSharedPreferences().getLong(NO_WIFI_TIP_TIME, 0L) == todayTime;
        isAlreadyShowWifiChangeTo4G = CommonBase.getJdSharedPreferences().getLong(WIFI_CHANGE_TO_4G_TIP_TIME, 0L) == todayTime;
    }

    private static long getTodayTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(14, 0);
        calendar.set(13, 0);
        calendar.set(12, 0);
        calendar.set(11, 0);
        return calendar.getTimeInMillis();
    }

    public static boolean needNoWifiTip() {
        if (isAlreadyShowNoWifi || !NetUtils.isNetworkAvailable() || NetUtils.isWifi()) {
            return false;
        }
        isAlreadyShowNoWifi = true;
        todayTime = getTodayTime();
        CommonBase.getJdSharedPreferences().edit().putLong(NO_WIFI_TIP_TIME, todayTime).apply();
        return true;
    }

    public static boolean needWifiChangeTo4GTip() {
        if (isAlreadyShowWifiChangeTo4G || !NetUtils.isNetworkAvailable() || NetUtils.isWifi()) {
            return false;
        }
        isAlreadyShowWifiChangeTo4G = true;
        isAlreadyShowNoWifi = true;
        todayTime = getTodayTime();
        CommonBase.getJdSharedPreferences().edit().putLong(NO_WIFI_TIP_TIME, todayTime).putLong(WIFI_CHANGE_TO_4G_TIP_TIME, todayTime).apply();
        return true;
    }
}

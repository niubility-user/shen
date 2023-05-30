package com.jingdong.common.messagecenter;

import android.text.TextUtils;
import com.jingdong.jdsdk.utils.SharedPreferencesUtil;

/* loaded from: classes5.dex */
public class StationCacheDataUtils {
    private static final String STATION_PV_SWITCH = "stationPVSwitch";
    private static final String STATION_PV_TURN_ON = "1";
    private static final String STATION_WHITE_PAGE = "stationWhitePage";
    public static boolean mStationPVSwitch;
    public static String mStationWhitePage;

    public static boolean getStationPVSwitch() {
        if (!mStationPVSwitch) {
            mStationPVSwitch = SharedPreferencesUtil.getBoolean(STATION_PV_SWITCH, false);
        }
        return mStationPVSwitch;
    }

    public static String getStationWhitePage() {
        if (TextUtils.isEmpty(mStationWhitePage)) {
            mStationWhitePage = SharedPreferencesUtil.getString(STATION_WHITE_PAGE, "");
        }
        return mStationWhitePage;
    }

    public static void putStationPVSwitch(String str) {
        boolean equals = "1".equals(str);
        mStationPVSwitch = equals;
        SharedPreferencesUtil.putBoolean(STATION_PV_SWITCH, equals);
    }

    public static void putStationWhitePage(String str) {
        mStationWhitePage = str;
        SharedPreferencesUtil.putString(STATION_WHITE_PAGE, str);
    }
}

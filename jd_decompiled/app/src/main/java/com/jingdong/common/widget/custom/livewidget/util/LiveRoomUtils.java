package com.jingdong.common.widget.custom.livewidget.util;

import com.jingdong.jdsdk.utils.SharedPreferencesUtil;

/* loaded from: classes12.dex */
public class LiveRoomUtils {
    public static final String OFF = "0";
    public static final String ON = "1";
    public static final String SP_LIVE_BG_PLAY_SWITCH = "SP_LIVE_BG_PLAY_SWITCH";

    public static String getLiveRoomBgPlayStatus() {
        return SharedPreferencesUtil.getString(SP_LIVE_BG_PLAY_SWITCH, "1");
    }

    public static void setLiveRoomBgPlayStatus(String str) {
        SharedPreferencesUtil.putString(SP_LIVE_BG_PLAY_SWITCH, str);
    }
}

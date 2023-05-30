package com.jingdong.common.messagecenter;

import android.text.TextUtils;
import com.jingdong.app.mall.bundle.mobileConfig.JDMobileConfig;

/* loaded from: classes5.dex */
public class MessageBasicConfigUtils {
    public static final String MSG_CONFIG_SWITCH = "msgConfigSwitch";
    public static final String MSG_RED_DOT_SWITCH = "msgRedDotSwitch";
    public static final String OTHER_RED_DOT_SWITCH = "otherRedDotSwitch";
    public static final String PUSH_GUIDE_SWITCH = "pushGuideSwitch";
    public static final String STATION_SWITCH = "stationSwitch";

    private static boolean getConfigResult(String str) {
        if (TextUtils.isEmpty(str)) {
            return true;
        }
        return !"1".equals(JDMobileConfig.getInstance().getConfig("JDLogin", "msg-switch", str));
    }

    public static boolean isDegradeForX(String str) {
        return false;
    }
}

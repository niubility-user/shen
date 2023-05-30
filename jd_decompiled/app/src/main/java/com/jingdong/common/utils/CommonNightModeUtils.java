package com.jingdong.common.utils;

import com.jingdong.jdsdk.constant.Constants;
import com.jingdong.sdk.oklog.OKLog;

/* loaded from: classes6.dex */
public class CommonNightModeUtils {
    private static final String TAG = "CommonNightModeUtils";

    public static void optionsDefaultMode() {
        try {
            Boolean bool = Boolean.FALSE;
            boolean booleanValue = CommonBase.getBooleanFromPreference(Constants.SHARED_PREFERENCES_NIGHT_MODE_SETTING_DEFAULT_FLAG, bool).booleanValue();
            if (OKLog.D) {
                OKLog.d(TAG, "nightFlagSettingFlag == " + booleanValue);
            }
            if (booleanValue) {
                return;
            }
            CommonBase.putBooleanToPreference(Constants.SHARED_PREFERENCES_NIGHT_MODE_SETTING_DEFAULT_FLAG, Boolean.TRUE);
            if (CommonBase.getBooleanFromPreference(Constants.SHARED_PREFERENCES_NIGHT_MODE_SWITCH, bool).booleanValue()) {
                CommonBase.putBooleanToPreference(Constants.SHARED_PREFERENCES_NIGHT_MODE_SWITCH, bool);
            }
        } catch (Exception e2) {
            OKLog.e(TAG, e2);
        }
    }
}

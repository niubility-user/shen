package com.jingdong.common.utils;

import android.text.TextUtils;
import com.jingdong.common.login.LoginUserBase;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.utils.Md5Encrypt;
import com.jingdong.jdsdk.utils.SharedPreferencesUtil;
import com.jingdong.sdk.utils.PackageInfoUtil;

/* loaded from: classes6.dex */
public class MSGWithDDUtil {
    private static final String ACCOUNT_LIST_ENABLE = "AccountListEnable";
    private static final String DD_SERVICE_ENABLE = "dd_service_enable";
    private static final String DD_STATION_WINDOW = "dd_station_window";
    private static final String FIRST_LEVEL_ABT_TEST_FLAG = "v900FirstLevelABTestFlag";
    private static final String HOME_PUSH_GUIDE_DIALOG_SHOW_VERSION = "home_push_guide_dialog_show_versionn";
    private static final String IS_PULL_STATION_CALL_BACK = "isPullStationCallback";
    private static final String IS_PULL_STATION_RECORD = "isPullStationRecord";
    private static final String IS_SHOW_PUSH_DIALOG = "isShowPushDialog";
    private static final String MSG_NOTICE_VERSION = "msgNoticeVersion";
    private static final String MSG_POP_MAIN_SWITCH = "popMainSwitch";
    private static final String MSG_POP_SWITCHES = "popSwitches";
    private static final String MSG_RING_ENABLE = "msg_ring_enable";
    private static final String MSG_SHAKE_SWITCH = "msg_shake_switch";
    private static final String MSG_SOUND_SWITCH = "msg_sound_switch";
    private static final String STATION_PULL_DELAY_TIME = "stationPullDelayTime";
    private static final String STATION_PULL_PERIOD = "stationPullPeriod";

    private static boolean accountListEnable(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return "1".equals(str);
    }

    public static boolean getAccountListEnable() {
        return SharedPreferencesUtil.getBoolean(ACCOUNT_LIST_ENABLE, false);
    }

    public static boolean getDDStationWindowKey() {
        return SharedPreferencesUtil.getBoolean(DD_STATION_WINDOW + getPinValue(), true);
    }

    public static String getFirstLevelABTestFlag() {
        return SharedPreferencesUtil.getString(FIRST_LEVEL_ABT_TEST_FLAG, "");
    }

    public static String getIsPullStationCallback() {
        return SharedPreferencesUtil.getString(IS_PULL_STATION_CALL_BACK, "");
    }

    public static String getIsPullStationRecord() {
        return SharedPreferencesUtil.getString(IS_PULL_STATION_RECORD, "");
    }

    public static boolean getMSGSOUND() {
        return SharedPreferencesUtil.getBoolean(MSG_SOUND_SWITCH + getPinValue(), true);
    }

    public static boolean getMsgShakeSwitch() {
        return SharedPreferencesUtil.getBoolean(MSG_SHAKE_SWITCH + getPinValue(), true);
    }

    public static String getMsgVersion() {
        return SharedPreferencesUtil.getString(MSG_NOTICE_VERSION, "");
    }

    public static String getPinValue() {
        String userPin = LoginUserBase.getUserPin();
        return TextUtils.isEmpty(userPin) ? "" : Md5Encrypt.md5(userPin);
    }

    public static boolean getPopMainSwitch() {
        return SharedPreferencesUtil.getBoolean(MSG_POP_MAIN_SWITCH, false);
    }

    public static String getPopSwitchs() {
        return SharedPreferencesUtil.getString(MSG_POP_SWITCHES, "");
    }

    public static String getStationPullDelayTime() {
        return SharedPreferencesUtil.getString(STATION_PULL_DELAY_TIME, "");
    }

    public static int getStationPullPeriod() {
        return SharedPreferencesUtil.getInt(STATION_PULL_PERIOD, 4);
    }

    public static void homePushGuideDialogShow() {
        SharedPreferencesUtil.putString(HOME_PUSH_GUIDE_DIALOG_SHOW_VERSION, PackageInfoUtil.getVersionName(JdSdk.getInstance().getApplication()));
    }

    public static boolean isHomePushGuiDialogShowed() {
        return SharedPreferencesUtil.getString(HOME_PUSH_GUIDE_DIALOG_SHOW_VERSION, "").equals(PackageInfoUtil.getVersionName(JdSdk.getInstance().getApplication()));
    }

    public static void putAccountListEnable(String str) {
        SharedPreferencesUtil.putBoolean(ACCOUNT_LIST_ENABLE, accountListEnable(str));
    }

    public static void putDDServiceEnable(int i2) {
        SharedPreferencesUtil.putInt(DD_SERVICE_ENABLE, i2);
    }

    public static void putDDStationWindowKey(boolean z) {
        SharedPreferencesUtil.putBoolean(DD_STATION_WINDOW + getPinValue(), z);
    }

    public static void putFirstLevelABTestFlag(String str) {
        SharedPreferencesUtil.putString(FIRST_LEVEL_ABT_TEST_FLAG, str);
    }

    public static void putIsPullStationCallback(String str) {
        SharedPreferencesUtil.putString(IS_PULL_STATION_CALL_BACK, str);
    }

    public static void putIsPullStationRecord(String str) {
        SharedPreferencesUtil.putString(IS_PULL_STATION_RECORD, str);
    }

    public static void putMSGRINGEnable(int i2) {
        SharedPreferencesUtil.putBoolean(MSG_RING_ENABLE, i2 == 1);
    }

    public static void putMSGSOUND(boolean z) {
        SharedPreferencesUtil.putBoolean(MSG_SOUND_SWITCH + getPinValue(), z);
    }

    public static void putMsgShakeSwith(boolean z) {
        SharedPreferencesUtil.putBoolean(MSG_SHAKE_SWITCH + getPinValue(), z);
    }

    public static void putMsgVersion(String str) {
        SharedPreferencesUtil.putString(MSG_NOTICE_VERSION, str);
    }

    public static void putPopMainSwitch(boolean z) {
        SharedPreferencesUtil.putBoolean(MSG_POP_MAIN_SWITCH, z);
    }

    public static void putPopSwitches(String str) {
        SharedPreferencesUtil.putString(MSG_POP_SWITCHES, str);
    }

    public static void putStationPullDelayTime(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        try {
            SharedPreferencesUtil.putString(STATION_PULL_DELAY_TIME, str);
        } catch (NumberFormatException e2) {
            e2.printStackTrace();
        }
    }

    public static void putStationPullPeriod(String str) {
        if (TextUtils.isEmpty(str)) {
            SharedPreferencesUtil.putInt(STATION_PULL_PERIOD, 4);
            return;
        }
        try {
            SharedPreferencesUtil.putInt(STATION_PULL_PERIOD, Integer.parseInt(str));
        } catch (NumberFormatException e2) {
            e2.printStackTrace();
        }
    }
}

package com.jingdong.common.utils;

import com.jingdong.common.entity.DesCommonUtils;
import com.jingdong.common.login.LoginUserBase;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;

/* loaded from: classes6.dex */
public class PersonalPreferenceUtil {
    private static final String JD_AMOUNT_VALUE_SHOW_FLAG = "personal_amount_value_show_flag_";
    public static final String JD_CHANGE_NOTITY_MODIFY_TIME = "JdChangeNotityModifyTime";
    public static final String JD_CHECK_CHANGE_RED_DOT_MODIFY_TIME = "JdCheckChangeRedDotNotifyTime";
    public static final String JD_HOME_CACHE_VERSION = "JdHomeCacheVersion";
    public static final String JD_MORE_CHANGE_NOTITY_MODIFY_TIME = "JdMoreChangeNotityModifyTime";
    public static final String JD_MORE_MODIFY_TIME = "myJdMoreModifyTime";
    public static final String JD_STATIC_CONFIG_RED_DOT_MODIFY_TIME = "JdStaticConfigRedDotNotifyTime";
    private static final String MY_JD_SET_BUSINESS_FORCE_UPDATE_TIME = "myJdSetBusinessForceUpdateTime";
    private static final String MY_JD_SET_BUSINESS_LOCAL_CACHE_UPDATE_TIME = "myJdSetBusinessLocalCacheUpdateTime";
    private static final String MY_JD_SET_BUSINESS_LOCAL_FORCE_UPDATE_TIME = "myJdSetBusinessLocalForceUpdateTime";
    private static final String MY_JD_SET_BUSINESS_REQUEST_INTERVAL = "myJdSetBusinessRequestInterval";
    private static final String MY_JD_USER_INFO_FLOOR_STYLE = "MyJdUserInfoFloorStyle";

    private static String getAmountFlagKey(boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append(JD_AMOUNT_VALUE_SHOW_FLAG);
        try {
            sb.append(PersonalDesCommonUtils.commonEncrypt(LoginUserBase.getUserPin(), z ? 2 : 1, DesCommonUtils.KEY_CART_SHARE));
        } catch (Exception e2) {
            ExceptionReporter.reportExceptionToBugly(e2);
        }
        return sb.toString();
    }

    public static boolean getAmountValueShowFlag() {
        try {
            updateAmountValueShowFlag();
            String amountFlagKey = getAmountFlagKey(true);
            if (Log.D) {
                Log.d("DYS", "get Flag" + amountFlagKey);
            }
            return CommonBase.getJdSharedPreferences().getBoolean(amountFlagKey, true);
        } catch (Exception e2) {
            ExceptionReporter.reportExceptionToBugly(e2);
            return true;
        }
    }

    public static long getJdChangeNotityModifyTime() {
        return CommonBase.getJdSharedPreferences().getLong(JD_CHANGE_NOTITY_MODIFY_TIME, 0L);
    }

    public static long getJdCheckChangeRedHotModifyTime() {
        return CommonBase.getJdSharedPreferences().getLong(JD_CHECK_CHANGE_RED_DOT_MODIFY_TIME, 0L);
    }

    public static String getJdHomeCacheVersion() {
        String string = CommonBase.getJdSharedPreferences().getString(JD_HOME_CACHE_VERSION, "");
        if (Log.D) {
            Log.d("JDHomeVersion", " getHomeCacheVersion " + string);
        }
        return string;
    }

    public static long getJdHomeModifyTime() {
        return CommonBase.getJdSharedPreferences().getLong("myJdHomeModifyTime", 0L);
    }

    public static long getJdMoreChangeNotityModifyTime() {
        return CommonBase.getJdSharedPreferences().getLong(JD_MORE_CHANGE_NOTITY_MODIFY_TIME, 0L);
    }

    public static long getJdMoreModifyTime() {
        return CommonBase.getJdSharedPreferences().getLong(JD_MORE_MODIFY_TIME, 0L);
    }

    public static long getJdStaticConfigRedHotModifyTime() {
        return CommonBase.getJdSharedPreferences().getLong(JD_STATIC_CONFIG_RED_DOT_MODIFY_TIME, 0L);
    }

    public static long getMyJdSetBusinessFourceUpdateTime() {
        try {
            return CommonBase.getJdSharedPreferences().getLong(MY_JD_SET_BUSINESS_FORCE_UPDATE_TIME, 0L);
        } catch (Exception e2) {
            ExceptionReporter.reportExceptionToBugly(e2);
            return 0L;
        }
    }

    public static long getMyJdSetBusinessLocalFourceUpdateTime() {
        try {
            return CommonBase.getJdSharedPreferences().getLong(MY_JD_SET_BUSINESS_LOCAL_FORCE_UPDATE_TIME, 0L);
        } catch (Exception e2) {
            ExceptionReporter.reportExceptionToBugly(e2);
            return 0L;
        }
    }

    public static long getMyJdSetBusinessLocalUpdateTimestamp() {
        try {
            return CommonBase.getJdSharedPreferences().getLong(MY_JD_SET_BUSINESS_LOCAL_CACHE_UPDATE_TIME, 0L);
        } catch (Exception e2) {
            ExceptionReporter.reportExceptionToBugly(e2);
            return 0L;
        }
    }

    public static long getMyJdSetBusinessRequestInterval() {
        try {
            return CommonBase.getJdSharedPreferences().getLong(MY_JD_SET_BUSINESS_REQUEST_INTERVAL, 0L);
        } catch (Exception e2) {
            ExceptionReporter.reportExceptionToBugly(e2);
            return 0L;
        }
    }

    public static boolean getMyJdUserInfoFloorStyle() {
        try {
            return CommonBase.getJdSharedPreferences().getBoolean(MY_JD_USER_INFO_FLOOR_STYLE, false);
        } catch (Exception e2) {
            ExceptionReporter.reportExceptionToBugly(new IllegalArgumentException("getMyJdUserInfoFloorStyle: " + e2));
            return false;
        }
    }

    public static int getNewNumber(String str) {
        return CommonBase.getJdSharedPreferences().getInt("personal_number_" + str, 0);
    }

    public static long getPersonalRedDotVersion(String str) {
        return CommonBase.getJdSharedPreferences().getLong("personal_redot_" + str, 0L);
    }

    public static long getRedDotLastVersion(String str) {
        return CommonBase.getJdSharedPreferences().getLong("personal_redot_" + str, 0L);
    }

    public static boolean getisFirstEnterMy618DNA() {
        return CommonBase.getJdSharedPreferences().getBoolean(LoginUserBase.getLoginUserName() + "_isFirstEnterMy618DNA", true);
    }

    public static void putDnaTitle(String str) {
        CommonBase.getJdSharedPreferences().edit().putString("personal_dna_title", str).apply();
    }

    public static void putIisFirstEnterMy618DNA(boolean z) {
        CommonBase.getJdSharedPreferences().edit().putBoolean(LoginUserBase.getLoginUserName() + "_isFirstEnterMy618DNA", z).apply();
    }

    public static void putJDMoreModifyTime(long j2) {
        CommonBase.getJdSharedPreferences().edit().putLong(JD_MORE_MODIFY_TIME, j2).apply();
    }

    public static void putJdChangeNotityModifyTime(long j2) {
        CommonBase.getJdSharedPreferences().edit().putLong(JD_CHANGE_NOTITY_MODIFY_TIME, j2).apply();
    }

    public static void putJdCheckChangeRedHotModifyTime(long j2) {
        CommonBase.getJdSharedPreferences().edit().putLong(JD_CHECK_CHANGE_RED_DOT_MODIFY_TIME, j2).apply();
    }

    public static void putJdHomeCacheVersion(String str) {
        if (Log.D) {
            Log.d("JDHomeVersion", "putHomeCacheVersion " + str);
        }
        CommonBase.getJdSharedPreferences().edit().putString(JD_HOME_CACHE_VERSION, str).apply();
    }

    public static void putJdHomeModifyTime(long j2) {
        CommonBase.getJdSharedPreferences().edit().putLong("myJdHomeModifyTime", j2).apply();
    }

    public static void putJdMoreChangeNotityModifyTime(long j2) {
        CommonBase.getJdSharedPreferences().edit().putLong(JD_MORE_CHANGE_NOTITY_MODIFY_TIME, j2).apply();
    }

    public static void putJdStaticConfigRedHotModifyTime(long j2) {
        CommonBase.getJdSharedPreferences().edit().putLong(JD_STATIC_CONFIG_RED_DOT_MODIFY_TIME, j2).apply();
    }

    public static void putMyJdSetBusinessFourceUpdateTime(long j2) {
        try {
            CommonBase.getJdSharedPreferences().edit().putLong(MY_JD_SET_BUSINESS_FORCE_UPDATE_TIME, j2).apply();
        } catch (Exception e2) {
            ExceptionReporter.reportExceptionToBugly(e2);
        }
    }

    public static void putMyJdSetBusinessLocalFourceUpdateTime(long j2) {
        try {
            CommonBase.getJdSharedPreferences().edit().putLong(MY_JD_SET_BUSINESS_LOCAL_FORCE_UPDATE_TIME, j2).apply();
        } catch (Exception e2) {
            ExceptionReporter.reportExceptionToBugly(e2);
        }
    }

    public static void putMyJdSetBusinessLocalUpdateTimestamp(long j2) {
        try {
            CommonBase.getJdSharedPreferences().edit().putLong(MY_JD_SET_BUSINESS_LOCAL_CACHE_UPDATE_TIME, j2).apply();
        } catch (Exception e2) {
            ExceptionReporter.reportExceptionToBugly(e2);
        }
    }

    public static void putMyJdSetBusinessRequestInterval(long j2) {
        try {
            CommonBase.getJdSharedPreferences().edit().putLong(MY_JD_SET_BUSINESS_REQUEST_INTERVAL, j2).apply();
        } catch (Exception e2) {
            ExceptionReporter.reportExceptionToBugly(e2);
        }
    }

    public static void putMyJdUserInfoFloorStyle(boolean z) {
        try {
            CommonBase.getJdSharedPreferences().edit().putBoolean(MY_JD_USER_INFO_FLOOR_STYLE, z).apply();
        } catch (Exception e2) {
            ExceptionReporter.reportExceptionToBugly(new IllegalArgumentException("putMyJdUserInfoFloorStyle: " + e2));
        }
    }

    public static void putNewNumber(String str, int i2) {
        CommonBase.getJdSharedPreferences().edit().putInt("personal_number_" + str, i2).apply();
    }

    public static void putRedDotLastVersion(String str, long j2) {
        CommonBase.getJdSharedPreferences().edit().putLong("personal_redot_" + str, j2).apply();
    }

    public static void setAmountValueShowFlag(boolean z) {
        try {
            updateAmountValueShowFlag();
            String amountFlagKey = getAmountFlagKey(true);
            if (Log.D) {
                Log.d("DYS", "put Flag" + amountFlagKey + "  " + z);
            }
            CommonBase.getJdSharedPreferences().edit().putBoolean(amountFlagKey, z).apply();
        } catch (Exception e2) {
            ExceptionReporter.reportExceptionToBugly(e2);
        }
    }

    private static void updateAmountValueShowFlag() {
        String amountFlagKey = getAmountFlagKey(true);
        String amountFlagKey2 = getAmountFlagKey(false);
        if (CommonBase.getJdSharedPreferences().contains(amountFlagKey)) {
            return;
        }
        if (CommonBase.getJdSharedPreferences().contains(amountFlagKey2)) {
            CommonBase.getJdSharedPreferences().edit().putBoolean(amountFlagKey, CommonBase.getJdSharedPreferences().getBoolean(amountFlagKey2, true)).apply();
            CommonBase.getJdSharedPreferences().edit().remove(amountFlagKey2).apply();
            return;
        }
        CommonBase.getJdSharedPreferences().edit().putBoolean(amountFlagKey2, true).apply();
    }
}

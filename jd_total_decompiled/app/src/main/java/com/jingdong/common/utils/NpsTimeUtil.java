package com.jingdong.common.utils;

import com.jingdong.common.login.LoginUserBase;
import com.jingdong.jdsdk.utils.Md5Encrypt;
import com.jingdong.sdk.oklog.OKLog;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/* loaded from: classes6.dex */
public class NpsTimeUtil {
    public static final String FUNCTION_FLOAT_CJH = "floatCJH";
    public static final String FUNCTION_FLOAT_NPS = "floatNPS";
    private static final String JD_NPS_CLOSE_TIME_FLAG = "personal_nps_close_time_flag";
    private static final String TAG = "NpsTimeUtil";

    public static long getCurrentLongTime() {
        try {
            return Long.parseLong(new SimpleDateFormat("yyyyMMdd", Locale.CHINA).format(new Date(System.currentTimeMillis())));
        } catch (Exception e2) {
            if (OKLog.D) {
                OKLog.d(TAG, "getCurrentLongTime Exception" + e2);
                return 0L;
            }
            return 0L;
        }
    }

    public static long getNpsCloseTime(String str) {
        updatedNpsCloseTime(str);
        return CommonBase.getJdSharedPreferences().getLong(JD_NPS_CLOSE_TIME_FLAG + str + Md5Encrypt.md5(LoginUserBase.getUserPin()), 0L);
    }

    public static boolean judgeShowNps(String str) {
        try {
            return getCurrentLongTime() > getNpsCloseTime(str);
        } catch (Exception e2) {
            if (OKLog.D) {
                OKLog.d(TAG, "JudgeShowNps Exception" + e2);
                return false;
            }
            return false;
        }
    }

    public static void putJdNpsCloseTime(String str) {
        updatedNpsCloseTime(str);
        CommonBase.getJdSharedPreferences().edit().putLong(JD_NPS_CLOSE_TIME_FLAG + str + Md5Encrypt.md5(LoginUserBase.getUserPin()), getCurrentLongTime()).apply();
    }

    public static void updatedNpsCloseTime(String str) {
        try {
            String str2 = JD_NPS_CLOSE_TIME_FLAG + str + Md5Encrypt.md5(LoginUserBase.getUserPin());
            String str3 = JD_NPS_CLOSE_TIME_FLAG + Md5Encrypt.md5(LoginUserBase.getUserPin());
            if (!CommonBase.getJdSharedPreferences().contains(str2) && CommonBase.getJdSharedPreferences().contains(str3)) {
                CommonBase.getJdSharedPreferences().edit().putLong(str2, CommonBase.getJdSharedPreferences().getLong(str3, 0L)).apply();
                CommonBase.getJdSharedPreferences().edit().remove(str3).apply();
            }
        } catch (Exception e2) {
            if (OKLog.D) {
                OKLog.d(TAG, String.format("updatedNpsCloseTime error:%s", e2));
            }
        }
    }
}

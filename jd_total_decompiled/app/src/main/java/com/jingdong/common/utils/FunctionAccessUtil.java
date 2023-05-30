package com.jingdong.common.utils;

import com.jingdong.jdsdk.config.Configuration;
import com.jingdong.sdk.oklog.OKLog;
import java.util.Calendar;

/* loaded from: classes6.dex */
public class FunctionAccessUtil {
    private static final long MS_OF_DAY = 86400000;
    private static final long MS_OF_HOUR = 3600000;
    private static final String TAG = "JDGlobalInit";

    public static boolean checkChannelReady(long j2, int i2, String str, String str2) {
        long j3 = CommonBase.getJdSharedPreferences().getLong(str + str2, -1L);
        if (j3 == -1) {
            CommonBase.putLongToPreference(str + str2, j2);
        }
        if (j3 == -1) {
            return false;
        }
        if (OKLog.D) {
            OKLog.d(TAG, "FunctionAccessUtil lastAccess-->> " + j3);
            OKLog.d(TAG, "FunctionAccessUtil now-->> " + j2);
        }
        return j3 + (((long) i2) * 3600000) <= j2;
    }

    public static boolean checkFunctionReady(String str, String str2) {
        long j2 = CommonBase.getJdSharedPreferences().getLong(str, -1L);
        if (j2 == -1) {
            return true;
        }
        long currentTimeMillis = System.currentTimeMillis();
        long longValue = Configuration.getLongProperty(str2, -1L).longValue();
        if (86400000 == longValue) {
            return true ^ isOfToday(j2);
        }
        return j2 + longValue < currentTimeMillis;
    }

    public static boolean checkNumReady(String str, String str2, String str3) {
        int i2 = CommonBase.getJdSharedPreferences().getInt(str + str2, 0);
        int i3 = CommonBase.getJdSharedPreferences().getInt(str + str3, -1);
        return i3 == -1 || i2 < i3;
    }

    public static boolean checkTimeReady(String str, String str2, String str3) {
        long j2 = CommonBase.getJdSharedPreferences().getLong(str + str2, -1L);
        int i2 = CommonBase.getJdSharedPreferences().getInt(str + str3, -1);
        if (j2 == -1 || i2 == -1) {
            return true;
        }
        long currentTimeMillis = System.currentTimeMillis();
        if (OKLog.D) {
            OKLog.d(TAG, "FunctionAccessUtil lastAccess-->> " + j2);
            OKLog.d(TAG, "FunctionAccessUtil interval-->> " + i2);
            OKLog.d(TAG, "FunctionAccessUtil now-->> " + currentTimeMillis);
        }
        return j2 + (((long) i2) * 3600000) <= currentTimeMillis;
    }

    static boolean isOfToday(long j2) {
        Calendar calendar = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTimeInMillis(j2);
        return calendar.get(1) == calendar2.get(1) && calendar.get(6) == calendar2.get(6);
    }

    public static void updateLastAccess(String str) {
        try {
            CommonBase.getJdSharedPreferences().edit().putLong(str, System.currentTimeMillis()).commit();
        } catch (Error unused) {
        }
    }
}

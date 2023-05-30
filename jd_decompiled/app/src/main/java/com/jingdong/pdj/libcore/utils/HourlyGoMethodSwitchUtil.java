package com.jingdong.pdj.libcore.utils;

import android.util.Log;
import com.jingdong.common.utils.SwitchQueryFetcher;
import com.jingdong.sdk.oklog.OKLog;

/* loaded from: classes7.dex */
public class HourlyGoMethodSwitchUtil {
    private static int CLEAR_GIF_CACHE = 0;
    private static final int TAG_RADIX = 2;

    static {
        try {
            CLEAR_GIF_CACHE = HourlyGoNumberUtil.parseInt("10", 2);
        } catch (Exception e2) {
            e2.printStackTrace();
            OKLog.e(HourlyGoLibConstant.HOURLY_GO_EXCEPTION, Log.getStackTraceString(e2));
        }
    }

    public static boolean clearGifCache() {
        return isMethodSwitch(CLEAR_GIF_CACHE);
    }

    private static boolean isMethodSwitch(int i2) {
        return i2 == (SwitchQueryFetcher.getSwitchIntValue(SwitchQueryFetcher.METHOD_SWITCH, 0) & i2);
    }
}

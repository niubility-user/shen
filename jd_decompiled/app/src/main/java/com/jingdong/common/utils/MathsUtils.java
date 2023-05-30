package com.jingdong.common.utils;

import com.jingdong.sdk.oklog.OKLog;

/* loaded from: classes6.dex */
public class MathsUtils {
    private static final String TAG = "MathsUtils";

    public static int parseInt(String str) {
        return parseInt(str, 0);
    }

    public static int parseInt(String str, int i2) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e2) {
            if (OKLog.E) {
                OKLog.e(TAG, e2);
            }
            return i2;
        }
    }
}

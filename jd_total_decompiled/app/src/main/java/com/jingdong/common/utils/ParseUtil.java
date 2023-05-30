package com.jingdong.common.utils;

import android.text.TextUtils;
import com.jingdong.sdk.oklog.OKLog;

/* loaded from: classes6.dex */
public class ParseUtil {
    private static final String TAG = "ParseUtil";

    public static boolean parseBoolean(String str) {
        return !TextUtils.isEmpty(str) && Boolean.parseBoolean(str);
    }

    public static double parseDouble(String str, double d) {
        try {
            if (!TextUtils.isEmpty(str)) {
                return Double.parseDouble(str);
            }
        } catch (NumberFormatException e2) {
            OKLog.e(TAG, e2);
        }
        return d;
    }

    public static float parseFloat(String str, float f2) {
        try {
            if (!TextUtils.isEmpty(str)) {
                return Float.parseFloat(str);
            }
        } catch (NumberFormatException e2) {
            OKLog.e(TAG, e2);
        }
        return f2;
    }

    public static int parseInt(String str, int i2) {
        try {
            if (!TextUtils.isEmpty(str)) {
                return Integer.parseInt(str);
            }
        } catch (NumberFormatException e2) {
            OKLog.e(TAG, e2);
        }
        return i2;
    }

    public static double parseDouble(String str) {
        return parseDouble(str, 0.0d);
    }

    public static float parseFloat(String str) {
        return parseFloat(str, 0.0f);
    }

    public static int parseInt(String str) {
        return parseInt(str, 0);
    }
}

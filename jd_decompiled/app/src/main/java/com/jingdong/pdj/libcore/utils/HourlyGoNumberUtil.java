package com.jingdong.pdj.libcore.utils;

import android.util.Log;
import com.jingdong.sdk.oklog.OKLog;

/* loaded from: classes7.dex */
public class HourlyGoNumberUtil {
    public static boolean isFloatEqual(float f2, float f3) {
        return Math.abs(f2 - f3) < 1.0E-6f;
    }

    public static double parseDouble(String str) {
        try {
            return Double.parseDouble(str);
        } catch (NumberFormatException e2) {
            e2.printStackTrace();
            OKLog.e(HourlyGoLibConstant.HOURLY_GO_EXCEPTION, Log.getStackTraceString(e2));
            return 0.0d;
        }
    }

    public static float parseFloat(String str) {
        try {
            return Float.parseFloat(str);
        } catch (NumberFormatException e2) {
            e2.printStackTrace();
            OKLog.e(HourlyGoLibConstant.HOURLY_GO_EXCEPTION, Log.getStackTraceString(e2));
            return 0.0f;
        }
    }

    public static int parseInt(String str) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e2) {
            e2.printStackTrace();
            OKLog.e(HourlyGoLibConstant.HOURLY_GO_EXCEPTION, Log.getStackTraceString(e2));
            return 0;
        }
    }

    public static long parseLong(String str) {
        try {
            return Long.parseLong(str);
        } catch (NumberFormatException e2) {
            e2.printStackTrace();
            OKLog.e(HourlyGoLibConstant.HOURLY_GO_EXCEPTION, Log.getStackTraceString(e2));
            return 0L;
        }
    }

    public static Double parseDouble(double d) {
        return Double.valueOf(d);
    }

    public static Float parseFloat(float f2) {
        return Float.valueOf(f2);
    }

    public static Integer parseInt(int i2) {
        return Integer.valueOf(i2);
    }

    public static long parseLong(String str, int i2) {
        try {
            return Long.parseLong(str, i2);
        } catch (NumberFormatException e2) {
            e2.printStackTrace();
            OKLog.e(HourlyGoLibConstant.HOURLY_GO_EXCEPTION, Log.getStackTraceString(e2));
            return 0L;
        }
    }

    public static int parseInt(String str, int i2) {
        try {
            return Integer.parseInt(str, i2);
        } catch (NumberFormatException e2) {
            e2.printStackTrace();
            OKLog.e(HourlyGoLibConstant.HOURLY_GO_EXCEPTION, Log.getStackTraceString(e2));
            return 0;
        }
    }

    public static Long parseLong(long j2) {
        return Long.valueOf(j2);
    }
}

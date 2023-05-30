package com.jingdong.common.XView2.utils;

import android.annotation.SuppressLint;

/* loaded from: classes5.dex */
public class TimeUtil {
    private TimeUtil() {
    }

    @SuppressLint({"DefaultLocale"})
    public static String ms2TimeString(long j2) {
        try {
            return String.format("%d", Long.valueOf(j2 / 1000));
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }
}

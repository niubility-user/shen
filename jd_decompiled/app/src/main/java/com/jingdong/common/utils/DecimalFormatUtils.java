package com.jingdong.common.utils;

import java.text.DecimalFormat;

/* loaded from: classes6.dex */
public class DecimalFormatUtils {
    public static String format(double d) {
        return new DecimalFormat("0.00").format(d);
    }

    public static String format(long j2) {
        return new DecimalFormat("0.00").format(j2);
    }

    public static String format(double d, String str) {
        return new DecimalFormat(str).format(d);
    }

    public static String format(long j2, String str) {
        return new DecimalFormat(str).format(j2);
    }
}

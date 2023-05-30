package com.jd.lib.un.utils;

import java.math.BigDecimal;

/* loaded from: classes16.dex */
public class UnMath {
    public static int compareToDouble(double d, double d2) {
        return BigDecimal.valueOf(d).compareTo(BigDecimal.valueOf(d2));
    }

    public static boolean equalsDouble(double d, double d2) {
        return compareToDouble(d, d2) == 0;
    }
}

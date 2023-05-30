package com.jingdong.common.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/* loaded from: classes6.dex */
public class CalculationUtil {
    public static double add(double d, double d2) {
        return new BigDecimal(Double.toString(d)).add(new BigDecimal(Double.toString(d2))).doubleValue();
    }

    public static double minus(double d, double d2) {
        return new BigDecimal(Double.toString(d)).subtract(new BigDecimal(Double.toString(d2))).doubleValue();
    }

    public static String resultDecimalFormat(double d) {
        return new DecimalFormat("#0.00").format(d);
    }
}

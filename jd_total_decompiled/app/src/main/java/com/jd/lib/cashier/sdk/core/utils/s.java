package com.jd.lib.cashier.sdk.core.utils;

import android.graphics.Color;
import android.text.TextUtils;

/* loaded from: classes14.dex */
public class s {
    public static int a(String str) {
        if (!TextUtils.isEmpty(str)) {
            try {
                return Color.parseColor(str);
            } catch (Exception e2) {
                r.d("CashierParseStrUtil", "parse string to color in exception\n" + e2.getMessage());
            }
        }
        return -1;
    }

    public static double b(String str) {
        if (TextUtils.isEmpty(str)) {
            return -1.0d;
        }
        try {
            return Double.parseDouble(str);
        } catch (Exception e2) {
            r.d("CashierParseStrUtil", "parse string to double in exception\n" + e2.getMessage());
            return -1.0d;
        }
    }

    public static int c(String str) {
        if (TextUtils.isEmpty(str)) {
            return -1;
        }
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e2) {
            r.d("CashierParseStrUtil", "parse string to int in exception\n" + e2.getMessage());
            return -1;
        }
    }
}

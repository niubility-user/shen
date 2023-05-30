package com.jingdong.common.widget.custom.contentutils;

import android.graphics.Color;
import android.text.TextUtils;
import android.widget.TextView;
import com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVUtil;
import com.jingdong.corelib.utils.Log;
import java.util.List;
import java.util.Locale;

/* loaded from: classes12.dex */
public class ContentCommonUtil {
    public static String cutMaxLengthText(String str, int i2, boolean z) {
        String str2 = z ? "\u2026" : "";
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        if (str.length() <= i2 || i2 <= 0) {
            return str;
        }
        return str.substring(0, i2) + str2;
    }

    public static String getFormatFollowNumString(String str) {
        long j2;
        try {
            j2 = Long.parseLong(str);
        } catch (NumberFormatException e2) {
            if (Log.D) {
                e2.printStackTrace();
            }
            j2 = 0;
        }
        if (j2 <= 0) {
            return "0";
        }
        double d = j2;
        if (d < 10000.0d) {
            return j2 + "";
        } else if (j2 <= 9999000) {
            Double.isNaN(d);
            String format = String.format("%.1f", Double.valueOf(d / 10000.0d));
            if (format.indexOf(OrderISVUtil.MONEY_DECIMAL) > 0) {
                format = format.replaceAll("0+?$", "").replaceAll("[.]$", "");
            }
            return format + "\u4e07";
        } else {
            return "999.9\u4e07+";
        }
    }

    public static String getFormatPVString(long j2) {
        if (j2 < 0) {
            return "0";
        }
        double d = j2;
        if (d < 10000.0d) {
            return j2 + "";
        }
        Double.isNaN(d);
        String format = String.format(Locale.getDefault(), "%.1f", Double.valueOf(d / 10000.0d));
        if (format.indexOf(OrderISVUtil.MONEY_DECIMAL) > 0) {
            format = format.replaceAll("0+?$", "").replaceAll("[.]$", "");
        }
        return format + "\u4e07";
    }

    public static long getLongID(String str) {
        try {
            return Long.parseLong(str);
        } catch (NumberFormatException e2) {
            if (Log.D) {
                e2.printStackTrace();
            }
            return -1L;
        }
    }

    public static boolean isListEmpty(List<?> list) {
        return list == null || list.size() == 0;
    }

    public static int parseColor(String str, int i2) {
        if (TextUtils.isEmpty(str)) {
            return i2;
        }
        try {
            return Color.parseColor(str);
        } catch (Exception e2) {
            e2.printStackTrace();
            return i2;
        }
    }

    public static void setViewVisible(TextView textView, CharSequence charSequence) {
        if (textView == null) {
            return;
        }
        if (!TextUtils.isEmpty(charSequence)) {
            textView.setVisibility(0);
            textView.setText(charSequence);
            return;
        }
        textView.setVisibility(8);
        textView.setText("");
    }

    public static String standardizingRMBPrice(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        if (str.contains("\u00a5")) {
            return str;
        }
        return "\u00a5" + str;
    }
}

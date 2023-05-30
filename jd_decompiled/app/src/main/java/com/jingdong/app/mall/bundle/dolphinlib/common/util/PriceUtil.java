package com.jingdong.app.mall.bundle.dolphinlib.common.util;

import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.RelativeSizeSpan;
import android.widget.TextView;
import com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVUtil;
import java.math.BigDecimal;
import java.text.DecimalFormat;

/* loaded from: classes19.dex */
public class PriceUtil {
    public static final float SIZE_BIG_RATIO = 0.8f;
    public static final float SIZE_SMALL_RATIO = 0.625f;

    public static String getBigDecimalToString(BigDecimal bigDecimal) {
        return new DecimalFormat("#####0.00").format(bigDecimal);
    }

    public static String getDoubleToString(double d) {
        return new DecimalFormat("#####0.00").format(d);
    }

    public static void setSpanViewDiscount(TextView textView, String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        String str2Discount = str2Discount(str);
        SpannableString spannableString = new SpannableString(str2Discount);
        spannableString.setSpan(new RelativeSizeSpan(0.625f), str2Discount.length() - 1, str2Discount.length(), 33);
        textView.setText(spannableString);
    }

    public static void setSpanViewPrice(TextView textView, BigDecimal bigDecimal) {
        if (bigDecimal == null) {
            return;
        }
        setSpanViewPrice(textView, getBigDecimalToString(bigDecimal));
    }

    public static void setSpanViewPriceWithPrefix(TextView textView, String str, String str2) {
        if (textView == null || TextUtils.isEmpty(str)) {
            return;
        }
        String str2Price = str2Price(str);
        String str3 = str2 + str2Price;
        SpannableString spannableString = new SpannableString(str3);
        spannableString.setSpan(new RelativeSizeSpan(0.625f), 0, str3.indexOf("\u00a5") + 1, 33);
        if (str2Price.contains(OrderISVUtil.MONEY_DECIMAL)) {
            spannableString.setSpan(new RelativeSizeSpan(0.625f), str3.indexOf(OrderISVUtil.MONEY_DECIMAL), str3.length(), 33);
        }
        textView.setText(spannableString);
    }

    public static void setStrikePrice(TextView textView, BigDecimal bigDecimal) {
        if (bigDecimal == null) {
            return;
        }
        setStrikePrice(textView, getBigDecimalToString(bigDecimal));
    }

    public static String str2Discount(String str) {
        if (TextUtils.isEmpty(str) || str.endsWith("\u6298")) {
            return str;
        }
        return str + "\u6298";
    }

    public static String str2Price(String str) {
        if (TextUtils.isEmpty(str) || str.startsWith("\u00a5")) {
            return str;
        }
        return "\u00a5" + str;
    }

    public static void setSpanViewPrice(TextView textView, double d) {
        setSpanViewPrice(textView, getDoubleToString(d));
    }

    public static void setStrikePrice(TextView textView, double d) {
        setStrikePrice(textView, getDoubleToString(d));
    }

    public static void setSpanViewPrice(TextView textView, String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        String str2Price = str2Price(str);
        SpannableString spannableString = new SpannableString(str2Price);
        spannableString.setSpan(new RelativeSizeSpan(0.625f), 0, 1, 33);
        if (str2Price.contains(OrderISVUtil.MONEY_DECIMAL)) {
            spannableString.setSpan(new RelativeSizeSpan(0.625f), str2Price.indexOf(OrderISVUtil.MONEY_DECIMAL), str2Price.length(), 33);
        }
        textView.setText(spannableString);
    }

    public static void setStrikePrice(TextView textView, String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        textView.setText(str2Price(str));
        textView.getPaint().setFlags(17);
    }
}

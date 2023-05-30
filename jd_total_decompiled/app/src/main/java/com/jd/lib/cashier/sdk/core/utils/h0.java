package com.jd.lib.cashier.sdk.core.utils;

import android.content.Context;
import android.graphics.Point;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.RelativeSizeSpan;
import android.util.DisplayMetrics;
import android.widget.TextView;
import com.jd.cashier.app.jdlibcutter.protocol.utils.DpiUtil;
import com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVUtil;

/* loaded from: classes14.dex */
public class h0 {
    public static CharSequence a(Context context, String str, String str2) {
        float f2;
        int indexOf;
        if (i.a(context, str)) {
            return "";
        }
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        String trim = str.trim();
        try {
            f2 = Float.parseFloat(trim);
        } catch (Exception unused) {
            f2 = 0.0f;
        }
        if (f2 < 0.0f) {
            return "";
        }
        String str3 = str2 + trim;
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(str3);
        try {
            indexOf = str3.indexOf(OrderISVUtil.MONEY_DECIMAL);
            if (indexOf == -1) {
                indexOf = spannableStringBuilder.length();
            }
        } catch (Exception e2) {
            r.d("CashierViewUtil", e2.getMessage());
        }
        if (indexOf == 1) {
            return str3;
        }
        spannableStringBuilder.setSpan(new RelativeSizeSpan(1.46f), 1, indexOf, 33);
        return spannableStringBuilder;
    }

    public static CharSequence b(Context context, String str) {
        float f2;
        int indexOf;
        if (i.a(context, str)) {
            return "";
        }
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        String trim = str.trim();
        try {
            f2 = Float.parseFloat(trim);
        } catch (Exception unused) {
            f2 = 0.0f;
        }
        if (f2 < 0.0f) {
            return "";
        }
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(trim);
        try {
            indexOf = trim.indexOf(OrderISVUtil.MONEY_DECIMAL);
            if (indexOf == -1) {
                indexOf = spannableStringBuilder.length();
            }
        } catch (Exception e2) {
            r.d("CashierViewUtil", e2.getMessage());
        }
        if (indexOf == 0) {
            return trim;
        }
        spannableStringBuilder.setSpan(new RelativeSizeSpan(0.68f), indexOf, trim.length(), 33);
        return spannableStringBuilder;
    }

    public static int c(Context context, int i2, int i3, float f2, int i4) {
        int i5;
        try {
            if (context == null) {
                return (((DpiUtil.dip2px(context, i4) - DpiUtil.dip2px(context, i2)) / 2) - DpiUtil.dip2px(context, f2)) - DpiUtil.dip2px(context, i3);
            }
            DisplayMetrics i6 = y.i();
            if (i6 == null) {
                i5 = DpiUtil.dip2px(context, i4);
            } else {
                i5 = i6.widthPixels;
            }
            return (((i5 - DpiUtil.dip2px(context, i2)) / 2) - DpiUtil.dip2px(context, f2)) - DpiUtil.dip2px(context, i3);
        } catch (Exception e2) {
            e2.printStackTrace();
            return (((DpiUtil.dip2px(context, i4) - DpiUtil.dip2px(context, i2)) / 2) - DpiUtil.dip2px(context, f2)) - DpiUtil.dip2px(context, i3);
        }
    }

    public static Point d(Context context, String str, float f2, int i2, int i3, boolean z) {
        if (context != null && str != null) {
            try {
                if (!str.isEmpty() && 0.0f != f2) {
                    TextView textView = new TextView(context);
                    textView.setText(str);
                    textView.setTextSize(f2);
                    if (-2 != i2 && -1 != i2) {
                        textView.setWidth(i2);
                    }
                    if (-2 != i3 && -1 != i3) {
                        textView.setHeight(i3);
                    }
                    textView.setSingleLine(z);
                    textView.measure(-2, -1);
                    int measuredWidth = textView.getMeasuredWidth();
                    int measuredHeight = textView.getMeasuredHeight();
                    Point point2 = new Point();
                    point2.x = measuredWidth;
                    point2.y = measuredHeight;
                    return point2;
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return null;
    }

    public static void e(TextView textView, String str) {
        if (textView != null) {
            textView.setText(str);
        }
    }

    public static void f(TextView textView, int i2, float f2) {
        if (textView != null) {
            textView.setTextSize(i2, f2);
        }
    }
}

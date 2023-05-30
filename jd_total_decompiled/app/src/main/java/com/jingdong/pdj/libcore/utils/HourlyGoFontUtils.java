package com.jingdong.pdj.libcore.utils;

import android.app.Application;
import android.content.Context;
import android.graphics.Typeface;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.util.Log;
import android.util.SparseArray;
import android.widget.TextView;
import com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVUtil;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.utils.FontsUtil;
import com.jingdong.pdj.libcore.R;
import com.jingdong.pdj.libcore.screen.HourlyGoDpi750;
import com.jingdong.sdk.oklog.OKLog;

/* loaded from: classes7.dex */
public class HourlyGoFontUtils {
    private static final SparseArray<Typeface> array = new SparseArray<>(3);

    public static void changeFont(TextView textView, int i2) {
        Typeface typeFace;
        if (textView == null || textView.getContext() == null || (typeFace = getTypeFace(textView.getContext(), i2)) == null) {
            return;
        }
        textView.setTypeface(typeFace);
    }

    public static Typeface getTypeFace(Context context, int i2) {
        SparseArray<Typeface> sparseArray = array;
        if (sparseArray.get(i2) == null) {
            Typeface typeFace = FontsUtil.getTypeFace(context, i2);
            if (typeFace != null) {
                sparseArray.put(i2, typeFace);
                return typeFace;
            }
            return null;
        }
        return sparseArray.get(i2);
    }

    public static void setPrice(TextView textView, String str, int i2, int i3, int i4) {
        if (textView == null || TextUtils.isEmpty(str)) {
            if (textView != null) {
                textView.setText("");
                return;
            }
            return;
        }
        try {
            changeFont(textView, 4099);
            Application application = JdSdk.getInstance().getApplication();
            int i5 = R.string.lib_hourly_core_price_unit;
            if (!str.startsWith(application.getString(i5))) {
                str = JdSdk.getInstance().getApplication().getString(i5) + str;
            }
            if (i2 == i3 && i2 == i4) {
                textView.setText(str);
                textView.setTextSize(0, HourlyGoDpi750.getSizeBy750(i2 * 2));
                return;
            }
            int indexOf = str.indexOf(OrderISVUtil.MONEY_DECIMAL);
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(str);
            if (indexOf != -1) {
                spannableStringBuilder.setSpan(new AbsoluteSizeSpan(HourlyGoDpi750.getSizeBy750(i2 * 2), false), 0, 1, 33);
                spannableStringBuilder.setSpan(new AbsoluteSizeSpan(HourlyGoDpi750.getSizeBy750(i3 * 2), false), 1, indexOf, 33);
                spannableStringBuilder.setSpan(new AbsoluteSizeSpan(HourlyGoDpi750.getSizeBy750(i4 * 2), false), indexOf, spannableStringBuilder.length(), 33);
            } else {
                spannableStringBuilder.setSpan(new AbsoluteSizeSpan(HourlyGoDpi750.getSizeBy750(i2 * 2), false), 0, 1, 33);
                spannableStringBuilder.setSpan(new AbsoluteSizeSpan(HourlyGoDpi750.getSizeBy750(i3 * 2), false), 1, spannableStringBuilder.length(), 33);
            }
            textView.setText(spannableStringBuilder);
        } catch (Exception e2) {
            OKLog.e(HourlyGoLibConstant.HOURLY_GO_EXCEPTION, Log.getStackTraceString(e2));
        }
    }
}

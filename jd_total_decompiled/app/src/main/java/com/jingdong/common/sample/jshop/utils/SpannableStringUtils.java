package com.jingdong.common.sample.jshop.utils;

import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import androidx.core.internal.view.SupportMenu;
import com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVUtil;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.res.StringUtil;
import com.jingdong.jdsdk.utils.DPIUtil;

/* loaded from: classes6.dex */
public class SpannableStringUtils {
    public static final String TAG = "SpannableStringUtils";

    public static Spannable getDetailCommentSpan(String str, String str2, int i2) {
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            SpannableString spannableString = new SpannableString(str);
            int indexOf = str.indexOf(str2);
            if (-1 != indexOf) {
                try {
                    spannableString.setSpan(new ForegroundColorSpan(i2), indexOf, str2.length() + indexOf, 33);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            return spannableString;
        }
        return new SpannableString(str);
    }

    private static int getEnd(String str) {
        int indexOf = str.indexOf(OrderISVUtil.MONEY_DECIMAL);
        return -1 == indexOf ? str.length() : indexOf;
    }

    public static Spannable getFormatSpanPrice(String str) {
        Spannable spannable;
        try {
            if (isValidPrice(str)) {
                spannable = getJDPriceSpan("\u00a5" + str, -1, DPIUtil.dip2px(17.0f));
            } else {
                if ("\u5f85\u53d1\u5e03".equals(str)) {
                    str = "\u00a5" + str;
                }
                spannable = getJDPriceSpan(str, -1, DPIUtil.dip2px(15.0f));
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            spannable = null;
        }
        if (spannable == null) {
            SpannableString spannableString = new SpannableString(StringUtil.no_price);
            spannableString.setSpan(new AbsoluteSizeSpan(DPIUtil.dip2px(15.0f)), 0, 4, 33);
            return spannableString;
        }
        return spannable;
    }

    public static Spannable getJDPriceSpan(String str, int i2, float f2) throws Exception {
        if (!TextUtils.isEmpty(str)) {
            SpannableString spannableString = new SpannableString(str);
            spannableString.setSpan(new AbsoluteSizeSpan(DPIUtil.dip2px(f2)), getStart(str), getEnd(str), 33);
            if (-1 != i2) {
                spannableString.setSpan(new ForegroundColorSpan(i2), getStart(str), getEnd(str), 33);
            }
            return spannableString;
        }
        return new SpannableString(str);
    }

    public static Spannable getJshopScoreSpan(String str, String str2) {
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            SpannableString spannableString = new SpannableString(str);
            int indexOf = str.indexOf(str2);
            if (-1 != indexOf) {
                try {
                    spannableString.setSpan(new ForegroundColorSpan((int) SupportMenu.CATEGORY_MASK), indexOf, str2.length() + indexOf, 33);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            return spannableString;
        }
        return new SpannableString(str);
    }

    private static int getStart(String str) {
        int indexOf = str.indexOf("\u00a5");
        Log.d(TAG, "getStart index = " + indexOf);
        if (-1 == indexOf) {
            return 0;
        }
        return indexOf + 1;
    }

    public static boolean isValidPrice(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    public static Spannable getJDPriceSpan(String str, int i2, int i3) throws Exception {
        if (!TextUtils.isEmpty(str)) {
            SpannableString spannableString = new SpannableString(str);
            spannableString.setSpan(new AbsoluteSizeSpan(i3), getStart(str), getEnd(str), 33);
            if (-1 != i2) {
                spannableString.setSpan(new ForegroundColorSpan(i2), getStart(str), getEnd(str), 33);
            }
            return spannableString;
        }
        return new SpannableString(str);
    }

    public static Spannable getJDPriceSpan(String str, float f2) throws Exception {
        return getJDPriceSpan(str, -1, f2);
    }
}

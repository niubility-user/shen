package com.jingdong.app.mall.mylib.CouponUnit;

import android.content.Context;
import android.graphics.Typeface;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.LeadingMarginSpan;
import android.text.style.RelativeSizeSpan;
import android.view.View;
import com.jd.lib.un.utils.UnTimeUtils;
import com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVUtil;
import com.jingdong.common.utils.LangUtils;
import com.jingdong.sdk.lib.couponunit.R;
import com.jingdong.sdk.utils.DPIUtil;
import java.util.ArrayList;

/* loaded from: classes4.dex */
public class CouponUtil {
    public static int baitiaoWidth;
    public static int familyWidth;
    public static Typeface jdBold;
    public static Typeface jdLight;
    public static Typeface jdRegular;
    public static int onlyPlusWidth;
    public static int sellWidth;
    public static int shareLayoutWidth;
    public static int shareOrOverLayWidth;

    public static SpannableString TransDiscountString(String str, float f2, float f3) {
        if (TextUtils.isEmpty(str)) {
            return new SpannableString("");
        }
        try {
            StringBuilder sb = new StringBuilder(str);
            sb.insert(str.length() - 1, LangUtils.SINGLE_SPACE);
            String sb2 = sb.toString();
            int length = sb2.length();
            SpannableString spannableString = new SpannableString(sb2);
            if (sb2.contains("-")) {
                int indexOf = sb2.indexOf("-");
                int i2 = indexOf + 1;
                String substring = sb2.substring(i2);
                int indexOf2 = sb2.substring(0, indexOf).indexOf(OrderISVUtil.MONEY_DECIMAL);
                int indexOf3 = substring.indexOf(OrderISVUtil.MONEY_DECIMAL);
                if (indexOf2 != -1) {
                    int i3 = indexOf2 + 1;
                    spannableString.setSpan(new RelativeSizeSpan(f2), 0, i3, 18);
                    spannableString.setSpan(new RelativeSizeSpan(f2), indexOf, i2, 18);
                    spannableString.setSpan(new RelativeSizeSpan(f3), i3, indexOf, 18);
                } else {
                    spannableString.setSpan(new RelativeSizeSpan(f2), 0, indexOf, 18);
                    spannableString.setSpan(new RelativeSizeSpan(f2), indexOf, i2, 18);
                }
                if (indexOf3 != -1) {
                    int i4 = indexOf + indexOf3 + 1 + 1;
                    spannableString.setSpan(new RelativeSizeSpan(f2), i2, i4, 18);
                    spannableString.setSpan(new RelativeSizeSpan(f3), i4, length - 2, 18);
                } else {
                    spannableString.setSpan(new RelativeSizeSpan(f2), i2, length - 2, 18);
                }
            } else {
                int indexOf4 = sb2.indexOf(OrderISVUtil.MONEY_DECIMAL);
                if (indexOf4 != -1) {
                    int i5 = indexOf4 + 1;
                    spannableString.setSpan(new RelativeSizeSpan(f2), 0, i5, 18);
                    spannableString.setSpan(new RelativeSizeSpan(f3), i5, length - 2, 18);
                } else {
                    spannableString.setSpan(new RelativeSizeSpan(f2), 0, length - 2, 18);
                }
            }
            return spannableString;
        } catch (Exception unused) {
            return new SpannableString(str);
        }
    }

    public static SpannableString TransFinaceString(String str, float f2) {
        if (TextUtils.isEmpty(str)) {
            return new SpannableString("");
        }
        try {
            SpannableString spannableString = new SpannableString(str);
            spannableString.setSpan(new RelativeSizeSpan(f2), 0, str.length() - 1, 18);
            return spannableString;
        } catch (Exception unused) {
            return new SpannableString("");
        }
    }

    public static SpannableString TransNativeNormalValue(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            return new SpannableString("");
        }
        try {
            int indexOf = str.indexOf(OrderISVUtil.MONEY_DECIMAL);
            if (str.length() >= 2 && "\u00a5".equals(str.substring(0, 1))) {
                SpannableString spannableString = new SpannableString(str);
                if (indexOf != -1) {
                    spannableString.setSpan(new RelativeSizeSpan(0.682f), indexOf, indexOf + 1, 33);
                }
                spannableString.setSpan(new RelativeSizeSpan(0.454f), 0, 1, 17);
                return spannableString;
            }
            return new SpannableString(str);
        } catch (Exception unused) {
            return new SpannableString(str);
        }
    }

    public static SpannableString formatCountDownLimitTime(long j2, Typeface typeface) {
        int i2;
        int i3;
        String formatTime = formatTime(j2);
        SpannableString spannableString = new SpannableString(formatTime);
        ArrayList<Integer> timeLength = getTimeLength(j2);
        if (!timeLength.isEmpty() && spannableString.length() > 0 && typeface != null) {
            int length = formatTime.length();
            int size = timeLength.size();
            int i4 = size - 1;
            if (i4 >= 0) {
                int intValue = timeLength.get(i4).intValue();
                int i5 = length - 4;
                int i6 = i5 - intValue;
                if (i6 >= 0) {
                    spannableString.setSpan(new RelativeSizeSpan(1.182f), i6, i5, 17);
                    spannableString.setSpan(new CommonTypefaceSpan(typeface), i6, i5, 17);
                }
                int i7 = size - 2;
                if (i7 >= 0) {
                    int intValue2 = timeLength.get(i7).intValue();
                    int i8 = length - intValue;
                    int i9 = i8 - 6;
                    if (intValue2 != 0) {
                        int i10 = i9 - intValue2;
                        if (i10 >= 0) {
                            spannableString.setSpan(new RelativeSizeSpan(1.182f), i10, i9, 17);
                            spannableString.setSpan(new CommonTypefaceSpan(typeface), i10, i9, 17);
                        }
                        if (size - 3 >= 0 && (i8 - intValue2) - 7 >= 0) {
                            spannableString.setSpan(new RelativeSizeSpan(1.182f), 0, i3, 17);
                            spannableString.setSpan(new CommonTypefaceSpan(typeface), 0, i3, 17);
                        }
                    } else if (size - 3 >= 0 && i8 - 5 >= 0) {
                        spannableString.setSpan(new RelativeSizeSpan(1.182f), 0, i2, 17);
                        spannableString.setSpan(new CommonTypefaceSpan(typeface), 0, i2, 17);
                    }
                }
            }
            return spannableString;
        }
        return new SpannableString(formatTime);
    }

    public static String formatTime(long j2) {
        long j3 = (long) UnTimeUtils.DAY;
        long j4 = j2 / j3;
        long j5 = j2 - (j3 * j4);
        long j6 = (long) UnTimeUtils.HOUR;
        long j7 = j5 / j6;
        long j8 = (j5 - (j6 * j7)) / 60000;
        if (j8 <= 0) {
            j8 = 1;
        }
        StringBuilder sb = new StringBuilder();
        if (j4 > 0) {
            sb.append(j4);
            sb.append("\u5929");
        }
        if (j7 > 0) {
            sb.append(j7);
            sb.append("\u5c0f\u65f6");
        }
        sb.append(j8);
        sb.append("\u5206\u540e\u53ef\u7528");
        return sb.toString();
    }

    public static int getDrawableIdWithCouponBgColorNum(String str) {
        if (TextUtils.isEmpty(str)) {
            return 0;
        }
        str.hashCode();
        char c2 = '\uffff';
        switch (str.hashCode()) {
            case 609894040:
                if (str.equals("coupon_001")) {
                    c2 = 0;
                    break;
                }
                break;
            case 609894041:
                if (str.equals("coupon_002")) {
                    c2 = 1;
                    break;
                }
                break;
            case 609894042:
                if (str.equals("coupon_003")) {
                    c2 = 2;
                    break;
                }
                break;
            case 609894043:
                if (str.equals("coupon_004")) {
                    c2 = 3;
                    break;
                }
                break;
            case 609894132:
                if (str.equals("coupon_030")) {
                    c2 = 4;
                    break;
                }
                break;
            case 609894133:
                if (str.equals("coupon_031")) {
                    c2 = 5;
                    break;
                }
                break;
            case 609894134:
                if (str.equals("coupon_032")) {
                    c2 = 6;
                    break;
                }
                break;
        }
        switch (c2) {
            case 0:
                return R.drawable.common_grey_value_background;
            case 1:
                return R.drawable.value_background_jing;
            case 2:
                return R.drawable.value_background_dong;
            case 3:
                return R.drawable.value_background_yun;
            case 4:
                return R.drawable.value_background_jing;
            case 5:
                return R.drawable.value_background_dong;
            case 6:
                return R.drawable.value_background_finance;
            default:
                return 0;
        }
    }

    public static SpannableString getIndentString(Context context, int i2, String str) {
        if (str == null) {
            str = "";
        }
        SpannableString spannableString = new SpannableString(str);
        spannableString.setSpan(new LeadingMarginSpan.Standard(i2 + DPIUtil.dip2px(context, 20.0f), 0), 0, str.length(), 17);
        return spannableString;
    }

    public static ArrayList<Integer> getTimeLength(long j2) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        long j3 = (long) UnTimeUtils.DAY;
        long j4 = j2 / j3;
        long j5 = j2 - (j3 * j4);
        long j6 = (long) UnTimeUtils.HOUR;
        long j7 = j5 / j6;
        long j8 = (j5 - (j6 * j7)) / 60000;
        if (j8 <= 0) {
            j8 = 1;
        }
        if (j4 > 9) {
            arrayList.add(2);
        } else if (j4 > 0) {
            arrayList.add(1);
        }
        if (j7 > 9) {
            arrayList.add(2);
        } else if (j7 > 0) {
            arrayList.add(1);
        } else {
            arrayList.add(0);
        }
        if (j8 > 9) {
            arrayList.add(2);
        } else {
            arrayList.add(1);
        }
        return arrayList;
    }

    public static int measureWidth(View view) {
        view.measure(View.MeasureSpec.makeMeasureSpec(0, 0), View.MeasureSpec.makeMeasureSpec(0, 0));
        return view.getMeasuredWidth();
    }
}

package com.jingdong.common.search.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StrikethroughSpan;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.jd.framework.json.JDJSONObject;
import com.jd.lib.cashier.sdk.core.utils.JDDarkUtil;
import com.jd.lib.un.utils.UnDisplayUtil;
import com.jingdong.app.mall.bundle.mobileConfig.JDMobileConfig;
import com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVUtil;
import com.jingdong.common.search.utils.ViewUtil;
import com.jingdong.common.unification.uniconfig.UnIconConfigHelper;
import com.jingdong.common.utils.DeepDarkChangeManager;
import com.jingdong.common.utils.text.TextScaleModeHelper;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.res.StringUtil;
import com.jingdong.jdsdk.utils.FontsUtil;
import com.jingdong.sdk.eldermode.util.JDElderModeUtils;
import com.jingdong.sdk.utils.DPIUtil;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

/* loaded from: classes6.dex */
public class PriceHelper {
    public static final String ONE_STRING = "1";
    public static final String PRODUCT_PRICE_LABEL_FULL = "\uffe5";
    public static final String TEXT_STYLE_BOLD = "bold";
    public static final String TEXT_STYLE_JDZH = "jdzh";
    public static final int THIRTY = 30;
    private static final int VALUE_TAG_FONT_REGULAR = 2;
    static Pattern p = Pattern.compile("^[0-9]+([.]{0,1}[0-9]+){0,1}$");
    private static Typeface typefaceRegular;

    private static void changeTextFontRegular(TextView textView) {
        if (textView == null || textView.getContext() == null) {
            return;
        }
        Object tag = textView.getTag();
        if (tag != null) {
            try {
                if (Integer.parseInt(tag.toString()) == 2) {
                    return;
                }
            } catch (Exception unused) {
                return;
            }
        }
        if (typefaceRegular == null) {
            typefaceRegular = FontsUtil.getTypeFace(textView.getContext(), 4099);
        }
        textView.setTypeface(typefaceRegular);
        textView.setTag(2);
    }

    private static CommonPriceEntity convertToCommonPrice(PriceInfoBean priceInfoBean) {
        float scaleSize;
        float scaleSize2;
        float scaleSize3;
        CommonPriceEntity commonPriceEntity = new CommonPriceEntity();
        if (priceInfoBean == null) {
            return commonPriceEntity;
        }
        commonPriceEntity.priceText = priceInfoBean.price;
        commonPriceEntity.priceColor = DeepDarkChangeManager.getInstance().getUIMode() == 1 ? priceInfoBean.colorDark : priceInfoBean.color;
        int parseStringToFloat = (int) parseStringToFloat(priceInfoBean.font);
        int parseStringToFloat2 = (int) parseStringToFloat(priceInfoBean.unitFont);
        int parseStringToFloat3 = (int) parseStringToFloat(priceInfoBean.decFont);
        float parseStringToFloat4 = parseStringToFloat(priceInfoBean.zoominRatio);
        if (parseStringToFloat <= 0) {
            parseStringToFloat = 12;
        } else if (parseStringToFloat > 30) {
            parseStringToFloat = 30;
        }
        if (parseStringToFloat2 <= 0) {
            parseStringToFloat2 = parseStringToFloat;
        } else if (parseStringToFloat2 > 30) {
            parseStringToFloat2 = 30;
        }
        if (parseStringToFloat3 <= 0) {
            parseStringToFloat3 = parseStringToFloat;
        } else if (parseStringToFloat3 > 30) {
            parseStringToFloat3 = 30;
        }
        if (JDElderModeUtils.isElderMode()) {
            scaleSize = JDElderModeUtils.getElderTextSize(parseStringToFloat2);
            scaleSize2 = JDElderModeUtils.getElderTextSize(parseStringToFloat);
            scaleSize3 = JDElderModeUtils.getElderTextSize(parseStringToFloat3);
        } else {
            TextScaleModeHelper.Companion companion = TextScaleModeHelper.INSTANCE;
            scaleSize = companion.getInstance().getScaleSize(JdSdk.getInstance().getApplicationContext(), parseStringToFloat2);
            scaleSize2 = companion.getInstance().getScaleSize(JdSdk.getInstance().getApplicationContext(), parseStringToFloat);
            scaleSize3 = companion.getInstance().getScaleSize(JdSdk.getInstance().getApplicationContext(), parseStringToFloat3);
        }
        commonPriceEntity.font = String.valueOf(scaleSize2);
        commonPriceEntity.unitFont = String.valueOf(scaleSize);
        commonPriceEntity.decFont = String.valueOf(scaleSize3);
        commonPriceEntity.origin = false;
        commonPriceEntity.hasUnit = true;
        commonPriceEntity.precision = 2;
        commonPriceEntity.deleteEndZero = false;
        commonPriceEntity.isAddUnderLine = TextUtils.equals("1", priceInfoBean.textLineFlag);
        commonPriceEntity.zoominRatio = parseStringToFloat4;
        return commonPriceEntity;
    }

    private static CharSequence dealPrice(Context context, OriginPriceBean originPriceBean, CommonPriceEntity commonPriceEntity) {
        if (context == null || originPriceBean == null || commonPriceEntity == null) {
            return null;
        }
        commonPriceEntity.origin = originPriceBean.isNewPriceStatus();
        commonPriceEntity.deleteEndZero = originPriceBean.isNewPriceStatus();
        return getCommonPrice(context, commonPriceEntity);
    }

    private static CharSequence getCommonPrice(Context context, CommonPriceEntity commonPriceEntity) {
        int i2;
        int i3;
        if (context == null || commonPriceEntity == null) {
            return "";
        }
        boolean z = commonPriceEntity.origin;
        if (TextUtils.isEmpty(commonPriceEntity.priceText)) {
            return "";
        }
        String trim = commonPriceEntity.priceText.trim();
        if (TextUtils.isEmpty(trim)) {
            return "";
        }
        int i4 = (trim.startsWith("\u00a5") || trim.startsWith(PRODUCT_PRICE_LABEL_FULL)) ? 1 : 0;
        if (i4 != 0) {
            trim = trim.substring(1);
        }
        if (!TextUtils.isEmpty(trim) && p.matcher(trim).matches()) {
            int indexOf = trim.indexOf(OrderISVUtil.MONEY_DECIMAL);
            int length = trim.length();
            if (!z) {
                int i5 = commonPriceEntity.precision;
                boolean z2 = commonPriceEntity.deleteEndZero;
                if (i5 < 0) {
                    i5 = 2;
                }
                StringBuilder sb = new StringBuilder();
                int i6 = length - 1;
                if (indexOf < i6) {
                    int i7 = indexOf == -1 ? 0 : i6 - indexOf;
                    if (i7 < i5) {
                        sb.append(trim);
                        if (indexOf == -1) {
                            sb.append(OrderISVUtil.MONEY_DECIMAL);
                        }
                        for (int i8 = 0; i8 < i5 - i7; i8++) {
                            sb.append("0");
                        }
                        trim = sb.toString();
                    }
                }
                if (indexOf != -1 && (i3 = indexOf + i5) < trim.length()) {
                    trim = trim.substring(0, i3 + 1);
                }
                if (z2) {
                    for (int i9 = 0; i9 < i5; i9++) {
                        if (trim.endsWith("0")) {
                            trim = trim.substring(0, trim.length() - 1);
                        }
                    }
                }
                if (trim.endsWith(OrderISVUtil.MONEY_DECIMAL)) {
                    trim = trim.substring(0, trim.length() - 1);
                }
                if (commonPriceEntity.hasUnit) {
                    i4 = 1;
                    trim = "\u00a5" + trim;
                } else {
                    i4 = 0;
                }
            } else if (i4 != 0) {
                trim = "\u00a5" + trim;
            }
            int parseStringToFloat = (int) parseStringToFloat(commonPriceEntity.font);
            int parseStringToFloat2 = (int) parseStringToFloat(commonPriceEntity.unitFont);
            int parseStringToFloat3 = (int) parseStringToFloat(commonPriceEntity.decFont);
            if (parseStringToFloat <= 0) {
                parseStringToFloat = 12;
            }
            if (parseStringToFloat2 <= 0) {
                parseStringToFloat2 = parseStringToFloat;
            }
            if (parseStringToFloat3 <= 0) {
                parseStringToFloat3 = parseStringToFloat;
            }
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(trim);
            int indexOf2 = trim.indexOf(OrderISVUtil.MONEY_DECIMAL);
            if (indexOf2 == -1) {
                indexOf2 = spannableStringBuilder.length();
            }
            int length2 = spannableStringBuilder.length();
            if (commonPriceEntity.zoominRatio >= 1.0f) {
                spannableStringBuilder.setSpan(new RelativeSizeSpan(commonPriceEntity.zoominRatio), i4, indexOf2, 33);
            } else {
                if (i4 != 0) {
                    spannableStringBuilder.setSpan(new AbsoluteSizeSpan(parseStringToFloat2, true), 0, 1, 17);
                    spannableStringBuilder.setSpan(new AbsoluteSizeSpan(parseStringToFloat, true), 1, indexOf2, 17);
                } else {
                    spannableStringBuilder.setSpan(new AbsoluteSizeSpan(parseStringToFloat, true), 0, indexOf2, 17);
                }
                spannableStringBuilder.setSpan(new AbsoluteSizeSpan(parseStringToFloat3, true), indexOf2, length2, 17);
                if (!TextUtils.isEmpty(commonPriceEntity.priceColor)) {
                    try {
                        i2 = ViewUtil.parseColor(commonPriceEntity.priceColor);
                    } catch (Exception unused) {
                        i2 = -1;
                    }
                    if (i2 != -1) {
                        spannableStringBuilder.setSpan(new ForegroundColorSpan(i2), 0, length2, 17);
                    }
                }
            }
            if (commonPriceEntity.isAddUnderLine) {
                spannableStringBuilder.setSpan(new StrikethroughSpan(), 0, spannableStringBuilder.length(), 33);
            }
            return spannableStringBuilder;
        }
        return "";
    }

    private static ImageView getImageViewByUnBitmapConfig(Context context, Bitmap bitmap) {
        if (context == null) {
            return null;
        }
        ImageView imageView = new ImageView(context);
        if (bitmap != null) {
            imageView.setImageBitmap(bitmap);
            return imageView;
        }
        return null;
    }

    private static void getPriceMta(List<Integer> list, OriginPriceBean originPriceBean, String str) {
        List<PriceInfoBean> list2;
        JDJSONObject jDJSONObject;
        Set<String> keySet;
        JDJSONObject jDJSONObject2 = new JDJSONObject();
        if (list == null || list.size() == 0 || originPriceBean == null || (list2 = originPriceBean.renewVersionPrice) == null || list2.size() == 0) {
            return;
        }
        try {
            Iterator<Integer> it = list.iterator();
            while (it.hasNext()) {
                PriceInfoBean priceInfoBean = originPriceBean.renewVersionPrice.get(it.next().intValue());
                if (priceInfoBean != null && (jDJSONObject = priceInfoBean.mta) != null && (keySet = jDJSONObject.keySet()) != null && keySet.iterator() != null) {
                    for (String str2 : keySet) {
                        if (!TextUtils.isEmpty(str2) && (priceInfoBean.mta.get(str2) instanceof String)) {
                            String str3 = (String) priceInfoBean.mta.get(str2);
                            boolean z = true;
                            if (!TextUtils.isEmpty(priceInfoBean.uniqueId) && TextUtils.equals(priceInfoBean.uniqueId.trim(), str)) {
                                z = false;
                            }
                            if (z) {
                                if (TextUtils.isEmpty(str3)) {
                                    str3 = "-100";
                                }
                                jDJSONObject2.put(str2, (Object) str3);
                            }
                        }
                    }
                }
            }
            originPriceBean.priceInfoMta = jDJSONObject2;
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:72:0x00d0 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:75:0x010f A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void handleNewPrice(@androidx.annotation.NonNull android.content.Context r18, com.jingdong.common.search.view.OriginPriceBean r19) {
        /*
            Method dump skipped, instructions count: 317
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.search.view.PriceHelper.handleNewPrice(android.content.Context, com.jingdong.common.search.view.OriginPriceBean):void");
    }

    private static double parseStringToDouble(String str) {
        if (str == null) {
            return -1.0d;
        }
        String trim = str.trim();
        if (TextUtils.isEmpty(trim)) {
            return -1.0d;
        }
        try {
            return Double.parseDouble(trim);
        } catch (Exception unused) {
            return -1.0d;
        }
    }

    private static float parseStringToFloat(String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                return -1.0f;
            }
            return Float.parseFloat(str);
        } catch (Exception unused) {
            return -1.0f;
        }
    }

    private static int showCommonPrice(Context context, PriceInfoBean priceInfoBean, OriginPriceBean originPriceBean, int i2) {
        if (context == null || priceInfoBean == null || originPriceBean.priceContainer == null) {
            return -1;
        }
        CharSequence dealPrice = dealPrice(context, originPriceBean, convertToCommonPrice(priceInfoBean));
        if (TextUtils.isEmpty(dealPrice)) {
            dealPrice = TextUtils.isEmpty(priceInfoBean.noPriceMsg) ? StringUtil.no_price : priceInfoBean.noPriceMsg;
            int parseStringToDouble = (int) parseStringToDouble(priceInfoBean.noPriceMsgFont);
            if (parseStringToDouble <= 0) {
                parseStringToDouble = 14;
            } else if (parseStringToDouble > 30) {
                parseStringToDouble = 30;
            }
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(dealPrice);
            if (!TextUtils.isEmpty(spannableStringBuilder)) {
                spannableStringBuilder.setSpan(new AbsoluteSizeSpan(parseStringToDouble, true), 0, dealPrice.length(), 17);
                dealPrice = spannableStringBuilder;
            }
        }
        TextView textView = new TextView(context);
        textView.setGravity(17);
        textView.setMaxLines(1);
        textView.setEllipsize(TextUtils.TruncateAt.END);
        textView.setIncludeFontPadding(false);
        textView.setText(dealPrice);
        changeTextFontRegular(textView);
        String str = TextUtils.isEmpty(priceInfoBean.colorDark) ? priceInfoBean.color : priceInfoBean.colorDark;
        if (DeepDarkChangeManager.getInstance().getUIMode() != 1) {
            str = priceInfoBean.color;
        }
        String str2 = DeepDarkChangeManager.getInstance().getUIMode() == 1 ? JDDarkUtil.COLOR_FF3826 : JDDarkUtil.COLOR_FA2C19;
        if (TextUtils.isEmpty(str)) {
            str = str2;
        }
        textView.setTextColor(ViewUtil.parseColor(str));
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        float parseStringToFloat = parseStringToFloat(priceInfoBean.leftMargin);
        if (i2 == 0 || parseStringToFloat < 0.0f) {
            parseStringToFloat = 0.0f;
        }
        int dip2px = UnDisplayUtil.dip2px(context, parseStringToFloat);
        layoutParams.leftMargin = dip2px;
        textView.measure(0, 0);
        String str3 = priceInfoBean.uniqueId;
        String trim = str3 == null ? "" : str3.trim();
        int measuredWidth = textView.getMeasuredWidth();
        if (measuredWidth + dip2px < originPriceBean.priceMaxWidth || i2 == 0) {
            textView.setTag(trim);
            textView.setLayoutParams(layoutParams);
            textView.setMaxWidth(originPriceBean.priceMaxWidth - dip2px);
            originPriceBean.priceContainer.addView(textView);
        } else {
            i2 = -1;
        }
        originPriceBean.priceMaxWidth = (originPriceBean.priceMaxWidth - measuredWidth) - dip2px;
        return i2;
    }

    private static int showCommonPriceByLegao(Context context, String str, String str2, String str3, String str4, LinearLayout linearLayout, OriginPriceBean originPriceBean, String str5, int i2) {
        Bitmap bitmap;
        ImageView imageViewByUnBitmapConfig;
        if (TextUtils.equals(JDMobileConfig.getInstance().getConfig("JDSearch", "legaoTagsNewApi", "value", "0"), "1")) {
            return showCommonPriceByLegaoNewApi(context, str, str2, str3, str4, linearLayout, originPriceBean, str5, i2);
        }
        if (linearLayout == null) {
            return -1;
        }
        if (context != null && !TextUtils.isEmpty(str4) && originPriceBean != null) {
            float parseStringToFloat = parseStringToFloat(str);
            if (parseStringToFloat < 0.0f) {
                parseStringToFloat = 0.0f;
            }
            int dip2px = DPIUtil.dip2px(parseStringToFloat);
            linearLayout.setVisibility(0);
            TextView textView = null;
            if (!TextUtils.isEmpty(str3)) {
                TextView textViewOrNull = UnIconConfigHelper.getTextViewOrNull(str4, str3, JDElderModeUtils.isElderMode());
                bitmap = null;
                textView = textViewOrNull;
                imageViewByUnBitmapConfig = null;
            } else {
                bitmap = UnIconConfigHelper.getBitmap(str4, JDElderModeUtils.isElderMode());
                imageViewByUnBitmapConfig = getImageViewByUnBitmapConfig(context, bitmap);
            }
            if (textView != null) {
                textView.setGravity(17);
                textView.setMaxLines(1);
                textView.setIncludeFontPadding(false);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, DPIUtil.dip2px(JDElderModeUtils.isElderMode() ? JDElderModeUtils.getElderTextSize(12.0f) : 12.0f));
                layoutParams.setMargins(dip2px, 0, 0, DPIUtil.dip2px(2.0f));
                textView.measure(0, 0);
                int measuredWidth = textView.getMeasuredWidth();
                if (dip2px + measuredWidth < originPriceBean.priceMaxWidth) {
                    textView.setTag(str2 != null ? str2.trim() : "");
                    linearLayout.addView(textView, layoutParams);
                } else {
                    i2 = -1;
                }
                originPriceBean.priceMaxWidth = (originPriceBean.priceMaxWidth - dip2px) - measuredWidth;
            } else if (imageViewByUnBitmapConfig == null || bitmap == null) {
                return -1;
            } else {
                int width = (int) (bitmap.getWidth() * (DPIUtil.dip2px(r2) / bitmap.getHeight()));
                LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(width, DPIUtil.dip2px(JDElderModeUtils.isElderMode() ? JDElderModeUtils.getElderTextSize(12.0f) : 12.0f));
                layoutParams2.setMargins(dip2px, 0, 0, DPIUtil.dip2px(3.0f));
                if (dip2px + width < originPriceBean.priceMaxWidth) {
                    imageViewByUnBitmapConfig.setTag(str2 != null ? str2.trim() : "");
                    if (!TextUtils.isEmpty(str5)) {
                        imageViewByUnBitmapConfig.setContentDescription(str5);
                    }
                    linearLayout.addView(imageViewByUnBitmapConfig, layoutParams2);
                } else {
                    i2 = -1;
                }
                originPriceBean.priceMaxWidth = (originPriceBean.priceMaxWidth - dip2px) - width;
            }
            return i2;
        }
        linearLayout.setVisibility(8);
        return -1;
    }

    private static int showCommonPriceByLegaoNewApi(Context context, String str, String str2, String str3, String str4, LinearLayout linearLayout, OriginPriceBean originPriceBean, String str5, int i2) {
        Bitmap bitmap;
        if (linearLayout == null) {
            return -1;
        }
        if (context != null && !TextUtils.isEmpty(str4) && originPriceBean != null) {
            float parseStringToFloat = parseStringToFloat(str);
            if (parseStringToFloat < 0.0f) {
                parseStringToFloat = 0.0f;
            }
            int dip2px = UnDisplayUtil.dip2px(context, parseStringToFloat);
            linearLayout.setVisibility(0);
            if (!TextUtils.isEmpty(str3)) {
                bitmap = UnIconConfigHelper.getTextBitmap(str4, str3, JDElderModeUtils.isElderMode());
            } else {
                bitmap = UnIconConfigHelper.getBitmap(str4, JDElderModeUtils.isElderMode());
            }
            ImageView imageViewByUnBitmapConfig = getImageViewByUnBitmapConfig(context, bitmap);
            if (imageViewByUnBitmapConfig == null || bitmap == null) {
                return -1;
            }
            int width = (int) (bitmap.getWidth() * (UnDisplayUtil.dip2px(context, r3) / bitmap.getHeight()));
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(width, UnDisplayUtil.dip2px(context, JDElderModeUtils.isElderMode() ? JDElderModeUtils.getElderTextSize(12.0f) : 12.0f));
            layoutParams.setMargins(dip2px, 0, 0, UnDisplayUtil.dip2px(context, 3.0f));
            if (dip2px + width < originPriceBean.priceMaxWidth) {
                imageViewByUnBitmapConfig.setTag(str2 == null ? "" : str2.trim());
                if (!TextUtils.isEmpty(str5)) {
                    imageViewByUnBitmapConfig.setContentDescription(str5);
                }
                linearLayout.addView(imageViewByUnBitmapConfig, layoutParams);
            } else {
                i2 = -1;
            }
            originPriceBean.priceMaxWidth = (originPriceBean.priceMaxWidth - dip2px) - width;
            return i2;
        }
        linearLayout.setVisibility(8);
        return -1;
    }

    private static int showPriceTextLabel(Context context, PriceInfoBean priceInfoBean, OriginPriceBean originPriceBean, int i2) {
        float scaleSize;
        if (originPriceBean == null || context == null || priceInfoBean == null || originPriceBean.priceContainer == null || TextUtils.isEmpty(priceInfoBean.text)) {
            return -1;
        }
        TextView textView = new TextView(context);
        textView.setGravity(17);
        textView.setMaxLines(1);
        textView.setEllipsize(TextUtils.TruncateAt.END);
        textView.setIncludeFontPadding(false);
        textView.setText(priceInfoBean.text);
        if (TextUtils.equals(TEXT_STYLE_JDZH, priceInfoBean.fontFamily)) {
            changeTextFontRegular(textView);
        } else if (TextUtils.equals("bold", priceInfoBean.fontFamily)) {
            textView.setTypeface(Typeface.defaultFromStyle(1));
        } else {
            textView.setTypeface(Typeface.defaultFromStyle(0));
        }
        String str = TextUtils.isEmpty(priceInfoBean.colorDark) ? priceInfoBean.color : priceInfoBean.colorDark;
        if (DeepDarkChangeManager.getInstance().getUIMode() != 1) {
            str = priceInfoBean.color;
        }
        String str2 = DeepDarkChangeManager.getInstance().getUIMode() == 1 ? JDDarkUtil.COLOR_FF3826 : JDDarkUtil.COLOR_FA2C19;
        if (TextUtils.isEmpty(str)) {
            str = str2;
        }
        textView.setTextColor(ViewUtil.parseColor(str));
        int parseStringToFloat = (int) parseStringToFloat(priceInfoBean.font);
        if (parseStringToFloat <= 0) {
            parseStringToFloat = 12;
        } else if (parseStringToFloat > 30) {
            parseStringToFloat = 30;
        }
        if (JDElderModeUtils.isElderMode()) {
            scaleSize = JDElderModeUtils.getElderTextSize(parseStringToFloat);
        } else {
            scaleSize = TextScaleModeHelper.INSTANCE.getInstance().getScaleSize(JdSdk.getInstance().getApplicationContext(), parseStringToFloat);
        }
        textView.setTextSize(scaleSize);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        float parseStringToFloat2 = parseStringToFloat(priceInfoBean.leftMargin);
        if (i2 == 0 || parseStringToFloat2 < 0.0f) {
            parseStringToFloat2 = 0.0f;
        }
        int dip2px = UnDisplayUtil.dip2px(context, parseStringToFloat2);
        layoutParams.setMargins(dip2px, 0, 0, UnDisplayUtil.dip2px(context, 1.0f));
        textView.measure(0, 0);
        String str3 = priceInfoBean.uniqueId;
        String trim = str3 == null ? "" : str3.trim();
        int measuredWidth = textView.getMeasuredWidth();
        boolean equals = TextUtils.equals("1", priceInfoBean.alwaysShow);
        if (measuredWidth + dip2px < originPriceBean.priceMaxWidth || equals || i2 == 0) {
            textView.setTag(trim);
            textView.setLayoutParams(layoutParams);
            textView.setMaxWidth(originPriceBean.priceMaxWidth - dip2px);
            originPriceBean.priceContainer.addView(textView);
        } else {
            i2 = -1;
        }
        originPriceBean.priceMaxWidth = (originPriceBean.priceMaxWidth - measuredWidth) - dip2px;
        return i2;
    }
}

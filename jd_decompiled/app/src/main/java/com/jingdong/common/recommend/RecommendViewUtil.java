package com.jingdong.common.recommend;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.ColorInt;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jd.lib.un.business.widget.a;
import com.jd.skin.lib.JDSkinSDK;
import com.jingdong.common.DpiUtil;
import com.jingdong.common.gray.JDGrayModelUtils;
import com.jingdong.common.recommend.entity.RecommendIcon;
import com.jingdong.common.recommend.entity.RecommendProduct;
import com.jingdong.common.recommend.ui.AutoWarpTextView;
import com.jingdong.common.recommend.ui.RecommendImageParser;
import com.jingdong.common.recommend.ui.RecommendImageSpan;
import com.jingdong.common.unification.uniconfig.UnIconConfigHelper;
import com.jingdong.common.utils.LangUtils;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.utils.DPIUtil;
import com.jingdong.sdk.eldermode.util.JDElderModeUtils;
import com.jingdong.sdk.oklog.OKLog;
import com.jingdong.sdk.platform.business.personal.R2;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes6.dex */
public class RecommendViewUtil {
    public static int homeTopSpace;
    public static boolean isHomeRecommendInScreen;

    public static void addView(ViewGroup viewGroup, View view, int i2, ViewGroup.LayoutParams layoutParams) {
        if (viewGroup == null || view == null || layoutParams == null) {
            return;
        }
        viewGroup.addView(view, i2, layoutParams);
    }

    public static void changeViewHeight(float f2, View view) {
        ViewGroup.LayoutParams layoutParams;
        if (f2 <= 0.0f || view == null || (layoutParams = view.getLayoutParams()) == null) {
            return;
        }
        layoutParams.height = (int) ((view.getWidth() > 0 ? view.getWidth() : getLineTwoItemWidth(view.getContext())) / f2);
        view.setLayoutParams(layoutParams);
        view.requestLayout();
    }

    public static int dealColor(String str, String str2) {
        try {
            if (!TextUtils.isEmpty(str)) {
                if (str.charAt(0) != '#') {
                    str = "#" + str;
                }
                return Color.parseColor(str);
            }
        } catch (Exception e2) {
            if (OKLog.D) {
                e2.printStackTrace();
            }
        }
        try {
            return Color.parseColor(str2);
        } catch (Exception unused) {
            return 0;
        }
    }

    public static <T extends View> T findViewById(View view, int i2) {
        if (view != null) {
            return (T) view.findViewById(i2);
        }
        return null;
    }

    public static int generateColor(String str, String str2) {
        try {
            if (!TextUtils.isEmpty(str)) {
                return Color.parseColor(str);
            }
        } catch (Exception e2) {
            if (OKLog.D) {
                e2.printStackTrace();
            }
        }
        try {
            return Color.parseColor(str2);
        } catch (Exception unused) {
            return 0;
        }
    }

    public static void generateGradientDrawable(View view, float f2, int[] iArr) {
        if (view == null) {
            return;
        }
        try {
            if (Build.VERSION.SDK_INT >= 16 && (view.getBackground() instanceof GradientDrawable)) {
                ((GradientDrawable) view.getBackground()).setColors(iArr);
                return;
            }
            GradientDrawable gradientDrawable = new GradientDrawable(GradientDrawable.Orientation.TL_BR, iArr);
            gradientDrawable.setCornerRadius(f2);
            view.setBackgroundDrawable(gradientDrawable);
        } catch (Exception e2) {
            if (OKLog.D) {
                e2.printStackTrace();
            }
        }
    }

    public static void generateGradientDrawableLR(View view, float f2, int[] iArr) {
        try {
            if (Build.VERSION.SDK_INT >= 16 && (view.getBackground() instanceof GradientDrawable)) {
                ((GradientDrawable) view.getBackground()).setOrientation(GradientDrawable.Orientation.LEFT_RIGHT);
                ((GradientDrawable) view.getBackground().mutate()).setColors(iArr);
                return;
            }
            GradientDrawable gradientDrawable = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, iArr);
            gradientDrawable.setCornerRadius(f2);
            view.setBackgroundDrawable(gradientDrawable);
        } catch (Exception e2) {
            if (OKLog.D) {
                e2.printStackTrace();
            }
        }
    }

    public static double getCurrentExpoPercent(View view) {
        if (view != null) {
            try {
                if (view.getVisibility() != 0) {
                    return 0.0d;
                }
                Rect rect = new Rect();
                if (view.getGlobalVisibleRect(rect)) {
                    double height = rect.height();
                    double measuredHeight = view.getMeasuredHeight();
                    if (height < 0.0d || measuredHeight <= 0.0d) {
                        return 0.0d;
                    }
                    Double.isNaN(height);
                    Double.isNaN(measuredHeight);
                    return height / measuredHeight;
                }
                return 0.0d;
            } catch (Exception unused) {
                return 0.0d;
            }
        }
        return 0.0d;
    }

    public static String getCurrentViewHeight(View view) {
        if (view != null) {
            try {
                if (view.getVisibility() != 0) {
                    return "0";
                }
                Rect rect = new Rect();
                if (view.getGlobalVisibleRect(rect)) {
                    float height = rect.height();
                    float measuredHeight = view.getMeasuredHeight();
                    if (height < 0.0f || measuredHeight <= 0.0f) {
                        return "0";
                    }
                    return ((int) ((height / measuredHeight) * 100.0f)) + "";
                }
                return "0";
            } catch (Exception unused) {
                return "0";
            }
        }
        return "0";
    }

    public static int getDrawableWidth(String str) {
        Drawable drawable;
        if (TextUtils.isEmpty(str) || (drawable = UnIconConfigHelper.getDrawable(str)) == null) {
            return 0;
        }
        return drawable.getIntrinsicWidth();
    }

    public static float getLabelScale(boolean z) {
        return (DpiUtil.getDensity(a.g().d()) * (z ? 1.3f : 1.0f)) / 3.0f;
    }

    public static int getLineTwoItemWidth(Context context) {
        int i2 = RecommendUtils.windowWidthSize;
        if (i2 > 0) {
            return ((i2 - (RecommendUtils.RECYCLERVIEW_LEFT_RIGHT_PADDING * 2)) - (RecommendUtils.RECYCLERVIEW_ITEM_MARGIN * 4)) / 2;
        }
        if (context instanceof Activity) {
            return ((DpiUtil.getAppWidth((Activity) context) - (RecommendUtils.RECYCLERVIEW_LEFT_RIGHT_PADDING * 2)) - (RecommendUtils.RECYCLERVIEW_ITEM_MARGIN * 4)) / 2;
        }
        return ((DpiUtil.getWidth(JdSdk.getInstance().getApplicationContext()) - (RecommendUtils.RECYCLERVIEW_LEFT_RIGHT_PADDING * 2)) - (RecommendUtils.RECYCLERVIEW_ITEM_MARGIN * 4)) / 2;
    }

    public static int getRightSize(int i2) {
        int i3 = RecommendUtils.windowWidthSize;
        if (i3 == 0) {
            return DPIUtil.getWidthByDesignValue750(i2);
        }
        return RecommendUtils.getWidthByDesignValue(i3, i2, R2.attr.decimalNumber);
    }

    public static float getTextSizeScale(float f2, float f3) {
        if (f3 <= 0.0f) {
            return 1.0f;
        }
        return Math.round((f2 / f3) * 100.0f) / 100.0f;
    }

    public static int getViewMeasureWidth(View view) {
        if (view != null) {
            view.measure(0, 0);
            return view.getMeasuredWidth();
        }
        return 0;
    }

    public static void gone(View view) {
        if (view instanceof ViewStub) {
            view.setVisibility(8);
        } else if (view == null || view.getVisibility() == 8) {
        } else {
            view.setVisibility(8);
        }
    }

    public static void inVisible(View... viewArr) {
        for (View view : viewArr) {
            invisible(view);
        }
    }

    public static void invisible(View view) {
        if (view instanceof ViewStub) {
            view.setVisibility(4);
        } else if (view == null || view.getVisibility() == 4) {
        } else {
            view.setVisibility(4);
        }
    }

    public static boolean isVisible(View view) {
        return view != null && view.getVisibility() == 0;
    }

    public static void setAspectRatio(SimpleDraweeView simpleDraweeView, float f2) {
        if (simpleDraweeView != null) {
            simpleDraweeView.setAspectRatio(f2);
        }
    }

    public static void setGrayView(View view, boolean z) {
        if (view != null) {
            try {
                int i2 = R.id.recommend_view_gray;
                if ((view.getTag(i2) instanceof Boolean ? ((Boolean) view.getTag(i2)).booleanValue() : false) != z) {
                    JDGrayModelUtils.getInstance().setViewGray(view, z);
                    view.setTag(i2, Boolean.valueOf(z));
                }
            } catch (Exception e2) {
                if (OKLog.D) {
                    e2.printStackTrace();
                }
            }
        }
    }

    public static void setImageResource(ImageView imageView, int i2) {
        if (imageView != null) {
            imageView.setImageResource(i2);
        }
    }

    public static void setOnClickListener(View view, View.OnClickListener onClickListener) {
        if (view != null) {
            view.setOnClickListener(onClickListener);
        }
    }

    public static String setRightSecondPrice(TextView textView, RecommendProduct recommendProduct, String str) {
        String str2;
        String str3;
        String str4;
        String str5 = null;
        if ("1".equals(recommendProduct.doublePriceUpMap.notSfStyle)) {
            RecommendProduct.DoublePriceUp doublePriceUp = recommendProduct.doublePriceUpMap;
            str5 = doublePriceUp.doublePrice;
            str2 = doublePriceUp.doublePriceColor;
        } else if (TextUtils.isEmpty(recommendProduct.doublePriceUpMap.jdPriceColor)) {
            str2 = null;
        } else {
            RecommendProduct.DoublePriceUp doublePriceUp2 = recommendProduct.doublePriceUpMap;
            String str6 = doublePriceUp2.jdPriceColor;
            if (!TextUtils.isEmpty(doublePriceUp2.secondPrice)) {
                str3 = recommendProduct.doublePriceUpMap.secondPrice;
            } else {
                str3 = recommendProduct.jdPrice;
            }
            str5 = str3;
            str2 = str6;
        }
        if (TextUtils.isEmpty(str5)) {
            return "-100";
        }
        String showPrice = RecommendUtils.getShowPrice(str5);
        if (RecommendUtils.getIllegalPrice().equals(showPrice)) {
            return "";
        }
        if (!"1".equals(recommendProduct.doublePriceUpMap.notSfStyle) || TextUtils.isEmpty(recommendProduct.doublePriceUpMap.doublePriceText)) {
            str4 = showPrice;
        } else {
            str4 = showPrice + LangUtils.SINGLE_SPACE + recommendProduct.doublePriceUpMap.doublePriceText;
        }
        textView.setText(str + str4);
        textView.setTextColor(generateColor(str2, "#8C8C8C"));
        textView.setVisibility(0);
        return showPrice;
    }

    public static void setRightTextSize(TextView textView, int i2, String str, int i3) {
        try {
            if (!TextUtils.isEmpty(str)) {
                JDSkinSDK.getInstance().setFontAndSize(str, textView);
                String fontSize = JDSkinSDK.getInstance().getFontSize(str);
                if (!TextUtils.isEmpty(fontSize)) {
                    i2 = Integer.parseInt(fontSize);
                }
            }
            textView.setTextSize(RecommendFontUtils.convertFontSizeByStandard(i2, i3));
        } catch (Exception e2) {
            OKLog.d("RecommendException", e2.getMessage());
        }
    }

    public static void setText(TextView textView, CharSequence charSequence) {
        if (textView != null) {
            textView.setText(charSequence);
        }
    }

    public static void setTextColor(TextView textView, @ColorInt int i2) {
        if (textView != null) {
            textView.setTextColor(i2);
        }
    }

    public static void setTextViewSpanString(List<RecommendIcon> list, String str, AutoWarpTextView autoWarpTextView, int i2) {
        if (TextUtils.isEmpty(str) || autoWarpTextView == null) {
            return;
        }
        if (list == null) {
            list = new ArrayList<>();
        }
        ArrayList<RecommendImageSpan> arrayList = new ArrayList<>();
        for (int i3 = 0; i3 < list.size(); i3++) {
            RecommendIcon recommendIcon = list.get(i3);
            if (recommendIcon != null) {
                RecommendImageSpan recommendImageSpan = null;
                if (!TextUtils.isEmpty(recommendIcon.resourceUrl)) {
                    int dip2px = DPIUtil.dip2px(RecommendUtils.isBAppType() ? recommendIcon.bHeight : recommendIcon.aHeight);
                    if (dip2px <= 0) {
                        dip2px = DPIUtil.dip2px(JDElderModeUtils.isElderMode() ? 16.0f : 12.0f);
                    }
                    recommendImageSpan = new RecommendImageSpan(new RecommendImageParser(autoWarpTextView, autoWarpTextView.getContext()).getDrawable(recommendIcon.resourceUrl, dip2px));
                } else if (!TextUtils.isEmpty(recommendIcon.resourceCode)) {
                    recommendImageSpan = new RecommendImageSpan(autoWarpTextView.getContext(), UnIconConfigHelper.getBitmap(recommendIcon.resourceCode, JDElderModeUtils.isElderMode()));
                }
                if (recommendImageSpan != null) {
                    arrayList.add(recommendImageSpan);
                }
            }
        }
        for (int i4 = 0; i4 < arrayList.size(); i4++) {
            int i5 = i4 * 2;
            arrayList.get(i4).setStartPo(i5, i5 + 1);
        }
        autoWarpTextView.setSpannedString(arrayList, str);
        autoWarpTextView.splitText(i2);
    }

    public static void setViewBackground(View view, int i2) {
        if (view != null) {
            view.setBackgroundResource(i2);
        }
    }

    public static void setViewBackgroundColor(View view, @ColorInt int i2) {
        if (view != null) {
            view.setBackgroundColor(i2);
        }
    }

    public static void showIcon(TextView textView, String str, String str2, boolean z) {
        if (textView == null) {
            return;
        }
        if (!TextUtils.isEmpty(str)) {
            if (!TextUtils.isEmpty(str2)) {
                textView.setText(str2);
            }
            UnIconConfigHelper.setTextViewProperties(str, textView, z);
            textView.setVisibility(0);
            return;
        }
        textView.setVisibility(8);
    }

    public static void showUnIcon(ImageView imageView, String str) {
        showUnIcon(imageView, str, null);
    }

    public static void visible(View view) {
        if (view instanceof ViewStub) {
            view.setVisibility(0);
        } else if (view == null || view.getVisibility() == 0) {
        } else {
            view.setVisibility(0);
        }
    }

    public static void showUnIcon(ImageView imageView, String str, String str2) {
        if (imageView == null) {
            return;
        }
        int i2 = 8;
        if (!TextUtils.isEmpty(str)) {
            if (!TextUtils.isEmpty(str2)) {
                Drawable textDrawable = UnIconConfigHelper.getTextDrawable(str, str2);
                if (textDrawable != null) {
                    imageView.setImageDrawable(textDrawable);
                    i2 = 0;
                }
            } else {
                Bitmap bitmap = UnIconConfigHelper.getBitmap(str);
                if (bitmap != null) {
                    imageView.setImageBitmap(bitmap);
                    i2 = 0;
                }
            }
        }
        imageView.setVisibility(i2);
    }

    public static void gone(View... viewArr) {
        for (View view : viewArr) {
            gone(view);
        }
    }

    public static void visible(View... viewArr) {
        for (View view : viewArr) {
            visible(view);
        }
    }
}

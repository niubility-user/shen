package com.jingdong.sdk.platform.business.utils;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.StateListDrawable;
import android.text.TextUtils;
import android.widget.TextView;
import com.jd.dynamic.DYConstants;
import com.jd.lib.cashier.sdk.core.utils.JDDarkUtil;
import com.jingdong.common.unification.customtheme.UnCustomThemeHelper;
import com.jingdong.jdsdk.utils.FontsUtil;
import com.jingdong.sdk.oklog.OKLog;
import com.jingdong.sdk.platform.floor.entity.BaseTemplateEntity;
import com.jingdong.sdk.platform.utils.PlatformTools;
import com.jingdong.sdk.utils.DPIUtil;

/* loaded from: classes10.dex */
public class BusinessTools {
    public static final String DETAIL_POP_LAYER = "DETAILPOPLAYER";
    private static final float[] corner;
    private static final float[] cornerDown;
    private static final float[] cornerUp;
    private static final int radius;

    static {
        int dip2px = dip2px(12.0f);
        radius = dip2px;
        corner = new float[]{dip2px, dip2px, dip2px, dip2px, dip2px, dip2px, dip2px, dip2px};
        cornerDown = new float[]{0.0f, 0.0f, 0.0f, 0.0f, dip2px, dip2px, dip2px, dip2px};
        cornerUp = new float[]{dip2px, dip2px, dip2px, dip2px, 0.0f, 0.0f, 0.0f, 0.0f};
    }

    public static ColorStateList createColorStateList(int i2, int i3) {
        return new ColorStateList(new int[][]{new int[]{16842919}, new int[0]}, new int[]{i2, i3});
    }

    public static int dip2px(float f2) {
        return DPIUtil.dip2px(f2);
    }

    public static Drawable getColorDrawable(String str, String str2, String str3, String str4) {
        if (TextUtils.isEmpty(str)) {
            str = str2;
        }
        if (TextUtils.isEmpty(str3)) {
            str3 = str4;
        }
        GradientDrawable gradientDrawable = getGradientDrawable(str);
        GradientDrawable gradientDrawable2 = getGradientDrawable(str3);
        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(new int[]{16842919}, gradientDrawable2);
        stateListDrawable.addState(new int[0], gradientDrawable);
        return stateListDrawable;
    }

    public static GradientDrawable getFloorBgDrawable(BaseTemplateEntity baseTemplateEntity, String str, int i2) {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(0);
        gradientDrawable.setColor(PlatformTools.getColorValue(str, i2));
        boolean z = baseTemplateEntity.isRoundDown;
        if (z && baseTemplateEntity.isRoundUp) {
            gradientDrawable.setCornerRadii(corner);
        } else if (z) {
            gradientDrawable.setCornerRadii(cornerDown);
        } else if (baseTemplateEntity.isRoundUp) {
            gradientDrawable.setCornerRadii(cornerUp);
        }
        return gradientDrawable;
    }

    private static GradientDrawable getGradientDrawable(String str) {
        String[] split;
        if (TextUtils.isEmpty(str) || (split = str.split(DYConstants.DY_REGEX_COMMA)) == null || split.length <= 0) {
            return null;
        }
        int[] iArr = new int[split.length];
        for (int i2 = 0; i2 < split.length; i2++) {
            iArr[i2] = parseColor(split[i2]);
        }
        return new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, iArr);
    }

    public static boolean getImageInfoEntity() {
        return UnCustomThemeHelper.getInstance().getTitleImageInfo("DETAILPOPLAYER") != null;
    }

    public static int parseColor(String str) {
        try {
            return Color.parseColor(str);
        } catch (Exception e2) {
            if (OKLog.D) {
                e2.printStackTrace();
            }
            return 0;
        }
    }

    public static void setTextBold(TextView textView, boolean z, boolean z2) {
        if (textView != null) {
            if (z) {
                textView.setTypeface(Typeface.DEFAULT_BOLD);
            } else if (z2) {
                FontsUtil.changeTextFont(textView, 4097);
            } else {
                FontsUtil.changeTextFont(textView, 4099);
            }
        }
    }

    public static LayerDrawable getFloorBgDrawable(BaseTemplateEntity baseTemplateEntity, String str, int i2, boolean z, boolean z2) {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(0);
        gradientDrawable.setColor(PlatformTools.getColorValue(str, i2));
        boolean z3 = baseTemplateEntity.isRoundDown;
        if (z3 && baseTemplateEntity.isRoundUp) {
            gradientDrawable.setCornerRadii(corner);
        } else if (z3) {
            gradientDrawable.setCornerRadii(cornerDown);
        } else if (baseTemplateEntity.isRoundUp) {
            gradientDrawable.setCornerRadii(cornerUp);
        }
        GradientDrawable gradientDrawable2 = new GradientDrawable();
        gradientDrawable2.setShape(0);
        gradientDrawable2.setColor(parseColor(z ? JDDarkUtil.COLOR_141212 : "#F2F2F2"));
        return new LayerDrawable(!z2 ? new GradientDrawable[]{gradientDrawable2, gradientDrawable} : new GradientDrawable[]{gradientDrawable});
    }

    public static GradientDrawable getFloorBgDrawable(BaseTemplateEntity baseTemplateEntity, int i2) {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(0);
        gradientDrawable.setColor(i2);
        boolean z = baseTemplateEntity.isRoundDown;
        if (z && baseTemplateEntity.isRoundUp) {
            gradientDrawable.setCornerRadii(corner);
        } else if (z) {
            gradientDrawable.setCornerRadii(cornerDown);
        } else if (baseTemplateEntity.isRoundUp) {
            gradientDrawable.setCornerRadii(cornerUp);
        }
        return gradientDrawable;
    }

    public static LayerDrawable getFloorBgDrawable(BaseTemplateEntity baseTemplateEntity, int i2, boolean z, boolean z2) {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(0);
        gradientDrawable.setColor(i2);
        boolean z3 = baseTemplateEntity.isRoundDown;
        if (z3 && baseTemplateEntity.isRoundUp) {
            gradientDrawable.setCornerRadii(corner);
        } else if (z3) {
            gradientDrawable.setCornerRadii(cornerDown);
        } else if (baseTemplateEntity.isRoundUp) {
            gradientDrawable.setCornerRadii(cornerUp);
        }
        GradientDrawable gradientDrawable2 = new GradientDrawable();
        gradientDrawable2.setShape(0);
        gradientDrawable2.setColor(parseColor(z ? JDDarkUtil.COLOR_141212 : "#F2F2F2"));
        return new LayerDrawable(!z2 ? new GradientDrawable[]{gradientDrawable2, gradientDrawable} : new GradientDrawable[]{gradientDrawable});
    }
}

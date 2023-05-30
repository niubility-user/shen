package com.jingdong.common.recommend;

import android.content.Context;
import android.graphics.Typeface;
import android.util.SparseArray;
import android.widget.TextView;
import com.jingdong.jdsdk.utils.FontsUtil;
import com.jingdong.sdk.eldermode.util.JDElderModeUtils;

/* loaded from: classes6.dex */
public class RecommendFontUtils {
    public static int RECOMMEND_BIG_MODE = 2;
    public static int RECOMMEND_ELDER_MODE = 1;
    public static int RECOMMEND_STANDARD_MODE;
    private static SparseArray<Typeface> array = new SparseArray<>(3);

    public static void changeFont(TextView textView, int i2) {
        Typeface typeFace;
        if (textView == null || textView.getContext() == null || (typeFace = getTypeFace(textView.getContext(), i2)) == null) {
            return;
        }
        textView.setTypeface(typeFace);
    }

    public static int convertFontSize(int i2) {
        if (i2 == 18 || i2 == 23) {
            return 22;
        }
        switch (i2) {
            case 11:
            case 12:
                return 14;
            case 13:
            case 14:
            case 15:
                return 16;
            default:
                return i2;
        }
    }

    public static int convertFontSize(int i2, int i3) {
        return i3 == RECOMMEND_ELDER_MODE ? convertFontSize(i2) : i2;
    }

    public static float convertFontSizeByStandard(int i2, int i3) {
        return i3 == RECOMMEND_ELDER_MODE ? JDElderModeUtils.getElderTextSize(i2) : i2;
    }

    public static float convertRecommendLabelFont(float f2, int i2) {
        if (i2 == RECOMMEND_ELDER_MODE) {
            double d = f2;
            Double.isNaN(d);
            return (float) (d * 1.3d);
        }
        return f2;
    }

    public static boolean enableFontRule(int i2) {
        return i2 == RECOMMEND_ELDER_MODE;
    }

    public static int getRecommendUIMode(RecommendConfig recommendConfig) {
        if (recommendConfig != null && recommendConfig.isElderEnable()) {
            return RECOMMEND_ELDER_MODE;
        }
        if (recommendConfig != null && recommendConfig.isBigEnable()) {
            return RECOMMEND_BIG_MODE;
        }
        return RECOMMEND_STANDARD_MODE;
    }

    public static Typeface getTypeFace(Context context, int i2) {
        if (array.get(i2) == null) {
            Typeface typeFace = FontsUtil.getTypeFace(context, i2);
            if (typeFace != null) {
                array.put(i2, typeFace);
                return typeFace;
            }
            return null;
        }
        return array.get(i2);
    }
}

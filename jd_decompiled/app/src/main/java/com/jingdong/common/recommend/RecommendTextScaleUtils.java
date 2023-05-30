package com.jingdong.common.recommend;

import android.text.TextPaint;
import android.text.TextUtils;
import android.widget.TextView;
import com.jingdong.common.utils.text.ScaleModeConstants;
import com.jingdong.common.utils.text.TextScaleModeHelper;

/* loaded from: classes6.dex */
public class RecommendTextScaleUtils {
    public static float getScaleTextSize(int i2) {
        if (ScaleModeConstants.TEXT_SCALE_MODE_LARGE.equals(TextScaleModeHelper.INSTANCE.getInstance().getTextSizeScaleMode())) {
            if (i2 != 20) {
                if (i2 != 30) {
                    switch (i2) {
                        case 8:
                            return 10.0f;
                        case 9:
                            return 11.0f;
                        case 10:
                            return 12.0f;
                        case 11:
                            return 13.0f;
                        case 12:
                            return 14.0f;
                        case 13:
                            return 16.0f;
                        case 14:
                            return 17.0f;
                        case 15:
                            return 18.0f;
                        case 16:
                            return 19.0f;
                        case 17:
                            return 20.0f;
                        case 18:
                            return 22.0f;
                    }
                }
                return 36.0f;
            }
            return 24.0f;
        }
        return i2;
    }

    public static String subStringForTextViewToDraw(String str, int i2, int i3, TextView textView) {
        TextPaint paint;
        int i4;
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        try {
            int length = str.length();
            if (length <= i2 || textView == null || (paint = textView.getPaint()) == null) {
                return str;
            }
            do {
                i4 = i2;
                i2++;
                if (i2 > length) {
                    break;
                }
            } while (paint.measureText(str, 0, i2) <= i3);
            return str.substring(0, i4);
        } catch (Exception unused) {
            return str;
        }
    }
}

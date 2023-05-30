package com.jingdong.common.search.utils;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.ColorInt;
import com.jd.lib.cashier.sdk.core.utils.JDDarkUtil;
import com.jingdong.common.utils.DeepDarkChangeManager;

/* loaded from: classes6.dex */
public class ViewUtil {
    public static String getShowLineCountForMta(TextView textView, int i2) {
        if (textView == null || i2 < 0) {
            return "-100";
        }
        textView.measure(View.MeasureSpec.makeMeasureSpec(i2, 1073741824), 0);
        return String.valueOf(textView.getLineCount() < 2 ? 1 : 2);
    }

    public static boolean isDarkMode() {
        return DeepDarkChangeManager.getInstance().getUIMode() == 1;
    }

    @ColorInt
    public static int parseColor(String str) {
        try {
            return Color.parseColor(str);
        } catch (Exception unused) {
            return Color.parseColor(isDarkMode() ? JDDarkUtil.COLOR_ECECEC : JDDarkUtil.COLOR_1A1A1A);
        }
    }
}

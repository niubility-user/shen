package com.jd.aips.verify.face.view;

import android.content.Context;
import android.graphics.Paint;
import android.view.View;
import com.jd.aips.common.utils.FsBaseInfoUtils;

/* loaded from: classes12.dex */
public class MiscUtil {
    public static int dipToPx(Context context, float f2) {
        return (int) ((FsBaseInfoUtils.getDensity(context) * f2) + ((f2 >= 0.0f ? 1 : -1) * 0.5f));
    }

    public static String getPrecisionFormat(int i2) {
        return "%." + i2 + "f";
    }

    public static int measure(int i2, int i3) {
        int mode = View.MeasureSpec.getMode(i2);
        int size = View.MeasureSpec.getSize(i2);
        return mode == 1073741824 ? size : mode == Integer.MIN_VALUE ? Math.min(i3, size) : i3;
    }

    public static float measureTextHeight(Paint paint) {
        Paint.FontMetrics fontMetrics = paint.getFontMetrics();
        return Math.abs(fontMetrics.ascent) - fontMetrics.descent;
    }
}

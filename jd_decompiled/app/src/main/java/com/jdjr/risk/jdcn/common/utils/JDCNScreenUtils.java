package com.jdjr.risk.jdcn.common.utils;

import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.view.Display;
import android.view.WindowManager;

/* loaded from: classes18.dex */
public class JDCNScreenUtils {
    public static float convertDpToPixel(float f2, Context context) {
        return f2 * (context.getResources().getDisplayMetrics().densityDpi / 160.0f);
    }

    public static int[] getRealScreenWidthHeight(Context context) {
        int i2;
        int i3;
        WindowManager windowManager = (WindowManager) context.getSystemService("window");
        if (windowManager != null) {
            Display defaultDisplay = windowManager.getDefaultDisplay();
            Point point2 = new Point();
            if (Build.VERSION.SDK_INT >= 17) {
                defaultDisplay.getRealSize(point2);
                i2 = point2.x;
                i3 = point2.y;
                if (i2 >= i3) {
                    i3 = i2;
                    i2 = i3;
                }
            } else {
                i3 = context.getResources().getDisplayMetrics().heightPixels;
                i2 = context.getResources().getDisplayMetrics().widthPixels;
            }
        } else {
            i2 = 0;
            i3 = 0;
        }
        return new int[]{i2, i3};
    }
}

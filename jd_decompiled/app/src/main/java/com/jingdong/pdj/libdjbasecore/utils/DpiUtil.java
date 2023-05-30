package com.jingdong.pdj.libdjbasecore.utils;

import android.content.Context;
import android.graphics.Point;
import android.view.Display;
import android.view.WindowManager;

/* loaded from: classes7.dex */
public class DpiUtil {
    private static Display defaultDisplay;
    private static Point outSize;

    public static Display getDefaultDisplay(Context context) {
        if (defaultDisplay == null) {
            Object systemService = context.getApplicationContext().getSystemService("window");
            if (systemService instanceof WindowManager) {
                defaultDisplay = ((WindowManager) systemService).getDefaultDisplay();
            }
        }
        return defaultDisplay;
    }

    public static int getHeight(Context context) {
        Display defaultDisplay2 = getDefaultDisplay(context);
        Point point2 = new Point();
        defaultDisplay2.getSize(point2);
        return point2.y;
    }

    public static void getPxSize(Context context) {
        Display defaultDisplay2 = getDefaultDisplay(context);
        Point point2 = new Point();
        outSize = point2;
        defaultDisplay2.getSize(point2);
    }

    public static int getWidth(Context context) {
        if (outSize == null) {
            synchronized (DpiUtil.class) {
                if (outSize == null) {
                    getPxSize(context);
                }
            }
        }
        return outSize.x;
    }
}

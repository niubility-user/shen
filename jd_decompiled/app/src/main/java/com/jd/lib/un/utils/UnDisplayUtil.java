package com.jd.lib.un.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import com.jd.lib.un.utils.config.UnDeviceInfo;

/* loaded from: classes16.dex */
public class UnDisplayUtil {
    private static final String TAG = "UnDisplayUtil";
    private static Display defaultDisplay;
    private static Point outSize;

    public static int dip2px(Context context, float f2) {
        double density = getDensity(context) * f2;
        Double.isNaN(density);
        return (int) (density + 0.5d);
    }

    public static int getAppWidth(Activity activity) {
        if (activity != null) {
            try {
                Point point2 = new Point();
                activity.getWindowManager().getDefaultDisplay().getSize(point2);
                return point2.x;
            } catch (Exception e2) {
                if (UnLog.D) {
                    e2.printStackTrace();
                }
            }
        }
        if (outSize == null) {
            synchronized (UnDisplayUtil.class) {
                if (outSize == null) {
                    getPxSize(activity);
                }
            }
        }
        return outSize.x;
    }

    public static int getContentHeight(Activity activity) {
        View contentView = getContentView(activity);
        if (contentView == null) {
            return 0;
        }
        return contentView.getHeight();
    }

    private static View getContentView(Activity activity) {
        if (activity == null) {
            return null;
        }
        return activity.getWindow().getDecorView().findViewById(16908290);
    }

    public static Display getDefaultDisplay(Context context) {
        if (defaultDisplay == null) {
            defaultDisplay = ((WindowManager) context.getApplicationContext().getSystemService("window")).getDefaultDisplay();
        }
        return defaultDisplay;
    }

    public static float getDensity(Context context) {
        return UnDeviceInfo.getDensity();
    }

    public static DisplayMetrics getDisplayMetrics(Context context) {
        if (context == null) {
            return null;
        }
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) context.getApplicationContext().getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics;
    }

    public static float getFontDensity(Context context) {
        return UnDeviceInfo.getScaledDensity();
    }

    public static int getHeight(Context context) {
        return UnDeviceInfo.getScreenHeight();
    }

    public static void getPxSize(Context context) {
        Display defaultDisplay2 = getDefaultDisplay(context);
        Point point2 = new Point();
        outSize = point2;
        defaultDisplay2.getSize(point2);
    }

    public static int getWidth(Context context) {
        return UnDeviceInfo.getScreenWidth();
    }

    public static int percentHeight(Context context, float f2) {
        return (int) (getHeight(context) * f2);
    }

    public static int percentWidth(Context context, float f2) {
        return (int) (getWidth(context) * f2);
    }

    public static int px2dip(Context context, float f2) {
        double density = f2 / getDensity(context);
        Double.isNaN(density);
        return (int) (density + 0.5d);
    }

    public static int px2sp(Context context, float f2) {
        double fontDensity = f2 / getFontDensity(context);
        Double.isNaN(fontDensity);
        return (int) (fontDensity + 0.5d);
    }

    public static int sp2px(Context context, float f2) {
        double fontDensity = getFontDensity(context) * f2;
        Double.isNaN(fontDensity);
        return (int) (fontDensity + 0.5d);
    }
}

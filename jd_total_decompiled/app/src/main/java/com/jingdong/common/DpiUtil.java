package com.jingdong.common;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import com.jd.lib.un.utils.config.UnDeviceInfo;
import com.jingdong.sdk.oklog.OKLog;

/* loaded from: classes5.dex */
public class DpiUtil {
    private static final String TAG = "DpiUtil";
    private static Display defaultDisplay;
    private static Point outSize;

    public static int dip2px(Context context, float f2) {
        float density = getDensity(context);
        String str = "density: " + density;
        double d = density * f2;
        Double.isNaN(d);
        return (int) (d + 0.5d);
    }

    public static int getAppHeight(Activity activity) {
        if (activity != null) {
            try {
                Point point2 = new Point();
                activity.getWindowManager().getDefaultDisplay().getSize(point2);
                int i2 = point2.y;
                return i2 == 0 ? getHeight() : i2;
            } catch (Exception e2) {
                if (OKLog.D) {
                    e2.printStackTrace();
                }
            }
        }
        if (outSize == null) {
            synchronized (DpiUtil.class) {
                if (outSize == null) {
                    getPxSize(activity);
                }
            }
        }
        int i3 = outSize.y;
        return i3 == 0 ? getHeight() : i3;
    }

    public static int getAppWidth(Activity activity) {
        if (activity != null) {
            try {
                Point point2 = new Point();
                activity.getWindowManager().getDefaultDisplay().getSize(point2);
                if (point2.x == 0) {
                    return getWidth(activity);
                }
            } catch (Exception e2) {
                if (UnLog.D) {
                    e2.printStackTrace();
                }
            }
        }
        if (outSize == null) {
            synchronized (DpiUtil.class) {
                if (outSize == null) {
                    getPxSize(activity);
                }
            }
        }
        int i2 = outSize.x;
        return i2 == 0 ? getWidth(activity) : i2;
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

    public static int getHeight() {
        return UnDeviceInfo.getScreenHeight();
    }
}

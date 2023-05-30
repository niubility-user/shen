package com.jd.dynamic.lib.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.view.Display;
import android.view.WindowManager;
import com.jingdong.sdk.baseinfo.BaseInfo;
import com.jingdong.sdk.platform.business.personal.R2;

/* loaded from: classes13.dex */
public class DPIUtil {
    private static float ScaledDensity = BaseInfo.getScaledDensity();
    private static Display defaultDisplay = null;
    private static float mDensity = 160.0f;
    private static Point outSize;

    public static int dip2px(float f2) {
        return (int) ((f2 * mDensity) + 0.5f);
    }

    public static int dip2px(Context context, float f2) {
        return (int) ((f2 * BaseInfo.getDensity()) + 0.5f);
    }

    public static float dip2pxF(Context context, float f2) {
        if (f2 == 0.0f) {
            return 0.0f;
        }
        return f2 * BaseInfo.getDensity();
    }

    public static int getAppHeight(Activity activity) {
        if (activity != null) {
            try {
                Point point2 = new Point();
                if (Build.VERSION.SDK_INT >= 17) {
                    activity.getWindowManager().getDefaultDisplay().getRealSize(point2);
                } else {
                    activity.getWindowManager().getDefaultDisplay().getSize(point2);
                }
                return point2.y;
            } catch (Exception unused) {
            }
        }
        if (outSize == null) {
            synchronized (DPIUtil.class) {
                if (outSize == null) {
                    getPxSize(activity.getApplicationContext());
                }
            }
        }
        return outSize.y;
    }

    public static int getAppWidth(Activity activity) {
        if (activity != null) {
            try {
                Point point2 = new Point();
                if (Build.VERSION.SDK_INT >= 17) {
                    activity.getWindowManager().getDefaultDisplay().getRealSize(point2);
                } else {
                    activity.getWindowManager().getDefaultDisplay().getSize(point2);
                }
                return point2.x;
            } catch (Exception unused) {
            }
        }
        if (outSize == null) {
            synchronized (DPIUtil.class) {
                if (outSize == null) {
                    getPxSize(activity.getApplicationContext());
                }
            }
        }
        return outSize.x;
    }

    public static Display getDefaultDisplay(Context context) {
        if (defaultDisplay == null) {
            defaultDisplay = ((WindowManager) context.getApplicationContext().getSystemService("window")).getDefaultDisplay();
        }
        return defaultDisplay;
    }

    public static float getDensity() {
        return mDensity;
    }

    public static int getHeight(Context context) {
        Display defaultDisplay2 = getDefaultDisplay(context);
        Point point2 = new Point();
        defaultDisplay2.getSize(point2);
        return point2.y;
    }

    public static int getHeightWithDp(Activity activity) {
        return px2dip(getAppHeight(activity));
    }

    public static void getPxSize(Context context) {
        Display defaultDisplay2 = getDefaultDisplay(context);
        Point point2 = new Point();
        outSize = point2;
        defaultDisplay2.getSize(point2);
    }

    public static double getStatusBarHeight(Context context) {
        return BaseInfo.getStatusBarHeight(context);
    }

    public static double getStatusBarHeightDP(Context context) {
        return px2dip((float) getStatusBarHeight(context));
    }

    public static int getWidth(Context context) {
        if (outSize == null) {
            synchronized (DPIUtil.class) {
                if (outSize == null) {
                    getPxSize(context);
                }
            }
        }
        return outSize.x;
    }

    public static int getWidthByDesignValue(Activity activity, int i2, int i3) {
        return (int) (((getAppWidth(activity) * i2) / i3) + 0.5f);
    }

    public static int getWidthByDesignValue(Context context, int i2, int i3) {
        return (int) (((getWidth(context) * i2) / i3) + 0.5f);
    }

    public static int getWidthByDesignValue720(Activity activity, int i2) {
        return getWidthByDesignValue(activity, i2, (int) R2.attr.counterOverflowTextColor);
    }

    public static int getWidthByDesignValue720(Context context, int i2) {
        return getWidthByDesignValue(context, i2, (int) R2.attr.counterOverflowTextColor);
    }

    public static int getWidthByDesignValue750(Activity activity, int i2) {
        return getWidthByDesignValue(activity, i2, (int) R2.attr.decimalNumber);
    }

    public static int getWidthByDesignValue750(Context context, int i2) {
        return getWidthByDesignValue(context, i2, (int) R2.attr.decimalNumber);
    }

    public static int getWidthWithDp(Activity activity) {
        return px2dip(getAppWidth(activity));
    }

    public static int percentHeight(Context context, float f2) {
        return (int) (getHeight(context) * f2);
    }

    public static int percentWidth(Context context, float f2) {
        return (int) (getWidth(context) * f2);
    }

    public static int px2dip(float f2) {
        return (int) ((f2 / mDensity) + 0.5f);
    }

    public static float px2dipF(float f2) {
        return f2 / mDensity;
    }

    public static int px2sp(Context context, float f2) {
        return (int) ((f2 / ScaledDensity) + 0.5f);
    }

    public static void setDensity(float f2) {
        mDensity = f2;
    }

    public static int sp2px(Context context, float f2) {
        return (int) ((f2 - 0.5f) * ScaledDensity);
    }
}

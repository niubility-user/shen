package com.jd.lib.babel.servicekit.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import com.jd.lib.babel.servicekit.iservice.IBaseInfo;
import com.jd.lib.babel.servicekit.iservice.IDpi;
import com.jingdong.sdk.platform.business.personal.R2;

/* loaded from: classes13.dex */
public class DPIUtil {
    public static int dip2px(float f2) {
        return (int) ((f2 * getDensity()) + 0.5f);
    }

    public static int getAppHeight(Activity activity) {
        IDpi iDpi = (IDpi) BabelServiceUtils.getService(IDpi.class);
        if (iDpi != null) {
            return iDpi.getAppHeight(activity);
        }
        if (activity != null) {
            try {
                Point point2 = new Point();
                activity.getWindowManager().getDefaultDisplay().getSize(point2);
                return point2.y;
            } catch (Exception unused) {
                return 0;
            }
        }
        return 0;
    }

    public static int getAppWidth(Activity activity) {
        IDpi iDpi = (IDpi) BabelServiceUtils.getService(IDpi.class);
        if (iDpi != null) {
            return iDpi.getAppWidth(activity);
        }
        if (activity != null) {
            try {
                Point point2 = new Point();
                activity.getWindowManager().getDefaultDisplay().getSize(point2);
                return point2.x;
            } catch (Exception unused) {
                return 0;
            }
        }
        return 0;
    }

    public static float getDensity() {
        IBaseInfo iBaseInfo = (IBaseInfo) BabelServiceUtils.getService(IBaseInfo.class);
        if (iBaseInfo != null) {
            return iBaseInfo.getDensity();
        }
        return 1.0f;
    }

    public static int getHeight(Context context) {
        IDpi iDpi = (IDpi) BabelServiceUtils.getService(IDpi.class);
        if (iDpi != null) {
            return iDpi.getHeight(context);
        }
        return 0;
    }

    public static float getScaledDensity() {
        IBaseInfo iBaseInfo = (IBaseInfo) BabelServiceUtils.getService(IBaseInfo.class);
        if (iBaseInfo != null) {
            return iBaseInfo.getScaledDensity();
        }
        return 1.0f;
    }

    public static int getWidth(Context context) {
        IDpi iDpi = (IDpi) BabelServiceUtils.getService(IDpi.class);
        if (iDpi != null) {
            return iDpi.getWidth(context);
        }
        return 0;
    }

    public static int getWidthByDesignValue(Context context, int i2, int i3) {
        return (int) (((getWidth(context) * i2) / i3) + 0.5f);
    }

    public static int getWidthByDesignValue1125(Context context, int i2) {
        return getWidthByDesignValue(context, i2, R2.attr.iv_id);
    }

    public static int getWidthByDesignValue720(Context context, int i2) {
        return getWidthByDesignValue(context, i2, R2.attr.counterOverflowTextColor);
    }

    public static int getWidthByDesignValue750(Context context, int i2) {
        return getWidthByDesignValue(context, i2, R2.attr.decimalNumber);
    }

    public static int px2dip(float f2) {
        return (int) ((f2 / getDensity()) + 0.5f);
    }

    public static int px2sp(Context context, float f2) {
        return (int) ((f2 / getScaledDensity()) + 0.5f);
    }

    public static int sp2px(Context context, float f2) {
        return (int) ((f2 - 0.5f) * getScaledDensity());
    }

    public static int dip2px(Context context, float f2) {
        return (int) ((f2 * getDensity()) + 0.5f);
    }
}

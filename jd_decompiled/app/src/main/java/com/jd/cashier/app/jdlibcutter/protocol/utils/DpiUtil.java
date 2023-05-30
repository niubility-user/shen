package com.jd.cashier.app.jdlibcutter.protocol.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.view.Display;
import android.view.WindowManager;
import com.jingdong.sdk.platform.business.personal.R2;

/* loaded from: classes13.dex */
public class DpiUtil {
    private static Display defaultDisplay = null;
    private static float mDensity = 160.0f;
    private static Point outSize;

    public static synchronized int dip2px(float f2) {
        int i2;
        synchronized (DpiUtil.class) {
            i2 = (int) ((f2 * mDensity) + 0.5f);
        }
        return i2;
    }

    public static synchronized int getAppHeight(Activity activity) {
        synchronized (DpiUtil.class) {
            if (activity != null) {
                try {
                    Point point2 = new Point();
                    activity.getWindowManager().getDefaultDisplay().getSize(point2);
                    return point2.y;
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            if (outSize == null && activity != null) {
                synchronized (DpiUtil.class) {
                    if (outSize == null) {
                        getPxSize(activity.getApplicationContext());
                    }
                }
            }
            Point point3 = outSize;
            if (point3 != null) {
                return point3.y;
            }
            return -1;
        }
    }

    public static synchronized int getAppWidth(Activity activity) {
        synchronized (DpiUtil.class) {
            if (activity != null) {
                try {
                    Point point2 = new Point();
                    activity.getWindowManager().getDefaultDisplay().getSize(point2);
                    return point2.x;
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            if (outSize == null && activity != null) {
                synchronized (DpiUtil.class) {
                    if (outSize == null) {
                        getPxSize(activity.getApplicationContext());
                    }
                }
            }
            Point point3 = outSize;
            if (point3 != null) {
                return point3.x;
            }
            return -1;
        }
    }

    public static synchronized Display getDefaultDisplay(Context context) {
        Display display;
        synchronized (DpiUtil.class) {
            if (defaultDisplay == null) {
                defaultDisplay = ((WindowManager) context.getApplicationContext().getSystemService("window")).getDefaultDisplay();
            }
            display = defaultDisplay;
        }
        return display;
    }

    public static synchronized float getDensity() {
        float f2;
        synchronized (DpiUtil.class) {
            f2 = mDensity;
        }
        return f2;
    }

    public static synchronized int getHeight(Context context) {
        int i2;
        synchronized (DpiUtil.class) {
            Display defaultDisplay2 = getDefaultDisplay(context);
            Point point2 = new Point();
            defaultDisplay2.getSize(point2);
            i2 = point2.y;
        }
        return i2;
    }

    public static synchronized void getPxSize(Context context) {
        synchronized (DpiUtil.class) {
            Display defaultDisplay2 = getDefaultDisplay(context);
            Point point2 = new Point();
            outSize = point2;
            defaultDisplay2.getSize(point2);
        }
    }

    public static synchronized int getWidth(Context context) {
        synchronized (DpiUtil.class) {
            if (outSize == null) {
                synchronized (DpiUtil.class) {
                    if (outSize == null) {
                        getPxSize(context);
                    }
                }
            }
            Point point2 = outSize;
            if (point2 != null) {
                return point2.x;
            }
            return -1;
        }
    }

    public static synchronized int getWidthByDesignValue(Context context, int i2, int i3) {
        int width;
        synchronized (DpiUtil.class) {
            width = (int) (((getWidth(context) * i2) / i3) + 0.5f);
        }
        return width;
    }

    public static synchronized int getWidthByDesignValue720(Context context, int i2) {
        int widthByDesignValue;
        synchronized (DpiUtil.class) {
            widthByDesignValue = getWidthByDesignValue(context, i2, (int) R2.attr.counterOverflowTextColor);
        }
        return widthByDesignValue;
    }

    public static synchronized int getWidthByDesignValue750(Context context, int i2) {
        int widthByDesignValue;
        synchronized (DpiUtil.class) {
            widthByDesignValue = getWidthByDesignValue(context, i2, (int) R2.attr.decimalNumber);
        }
        return widthByDesignValue;
    }

    public static synchronized int percentHeight(Context context, float f2) {
        int height;
        synchronized (DpiUtil.class) {
            height = (int) (getHeight(context) * f2);
        }
        return height;
    }

    public static synchronized int percentWidth(Context context, float f2) {
        int width;
        synchronized (DpiUtil.class) {
            width = (int) (getWidth(context) * f2);
        }
        return width;
    }

    public static synchronized int px2dip(float f2) {
        int i2;
        synchronized (DpiUtil.class) {
            i2 = (int) ((f2 / mDensity) + 0.5f);
        }
        return i2;
    }

    public static synchronized int px2sp(Context context, float f2) {
        int i2;
        synchronized (DpiUtil.class) {
            i2 = (int) ((f2 / context.getResources().getDisplayMetrics().scaledDensity) + 0.5f);
        }
        return i2;
    }

    public static synchronized void setDensity(float f2) {
        synchronized (DpiUtil.class) {
            mDensity = f2;
        }
    }

    public static synchronized int sp2px(Context context, float f2) {
        int i2;
        synchronized (DpiUtil.class) {
            i2 = (int) ((f2 - 0.5f) * context.getResources().getDisplayMetrics().scaledDensity);
        }
        return i2;
    }

    public static synchronized int dip2px(Context context, float f2) {
        int i2;
        synchronized (DpiUtil.class) {
            i2 = (int) ((f2 * context.getResources().getDisplayMetrics().density) + 0.5f);
        }
        return i2;
    }

    public static synchronized int getWidthByDesignValue(Activity activity, int i2, int i3) {
        int appWidth;
        synchronized (DpiUtil.class) {
            appWidth = (int) (((getAppWidth(activity) * i2) / i3) + 0.5f);
        }
        return appWidth;
    }

    public static synchronized int getWidthByDesignValue720(Activity activity, int i2) {
        int widthByDesignValue;
        synchronized (DpiUtil.class) {
            widthByDesignValue = getWidthByDesignValue(activity, i2, (int) R2.attr.counterOverflowTextColor);
        }
        return widthByDesignValue;
    }

    public static synchronized int getWidthByDesignValue750(Activity activity, int i2) {
        int widthByDesignValue;
        synchronized (DpiUtil.class) {
            widthByDesignValue = getWidthByDesignValue(activity, i2, (int) R2.attr.decimalNumber);
        }
        return widthByDesignValue;
    }
}

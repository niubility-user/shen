package com.jingdong.common.jdmiaosha.utils;

import android.content.Context;
import android.graphics.Point;
import android.view.WindowManager;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.sdk.utils.DPIUtil;

/* loaded from: classes5.dex */
public class UResize {
    public static int dip2px(Context context, int i2) {
        return DPIUtil.dip2px(context, i2);
    }

    static int get720SizeValue(int i2, int i3) {
        return (int) (((i3 * i2) / 720.0f) + 0.5f);
    }

    public static Point getScreen() {
        try {
            WindowManager windowManager = (WindowManager) JdSdk.getInstance().getApplication().getSystemService("window");
            if (windowManager != null) {
                Point point2 = new Point();
                windowManager.getDefaultDisplay().getSize(point2);
                return point2;
            }
        } catch (Exception unused) {
        }
        return new Point(com.jingdong.jdsdk.utils.DPIUtil.getWidth(), com.jingdong.jdsdk.utils.DPIUtil.getHeight());
    }

    public static int getWidth(Context context) {
        return getScreen().x;
    }

    public static int getWidthByDesignValue720(int i2) {
        return get720SizeValue(i2, getScreen().x);
    }

    public static int dip2px(float f2) {
        return com.jingdong.jdsdk.utils.DPIUtil.dip2px(f2);
    }

    public static int getWidth() {
        return getScreen().x;
    }

    public static int getWidthByDesignValue720(Context context, int i2) {
        return get720SizeValue(i2, getScreen().x);
    }
}

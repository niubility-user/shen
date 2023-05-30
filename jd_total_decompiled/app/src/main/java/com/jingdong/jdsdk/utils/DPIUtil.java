package com.jingdong.jdsdk.utils;

import android.content.Context;
import android.view.Display;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.sdk.oklog.OKLog;
import com.jingdong.sdk.platform.business.personal.R2;

@Deprecated
/* loaded from: classes.dex */
public class DPIUtil {
    public static int dip2px(float f2) {
        return com.jingdong.sdk.utils.DPIUtil.dip2px(f2);
    }

    public static Display getDefaultDisplay() {
        return com.jingdong.sdk.utils.DPIUtil.getDefaultDisplay(JdSdk.getInstance().getApplicationContext());
    }

    public static float getDensity() {
        return com.jingdong.sdk.utils.DPIUtil.getDensity();
    }

    public static int getHeight() {
        return com.jingdong.sdk.utils.DPIUtil.getHeight(JdSdk.getInstance().getApplicationContext());
    }

    public static void getPxSize() {
        com.jingdong.sdk.utils.DPIUtil.getPxSize(JdSdk.getInstance().getApplicationContext());
    }

    public static int getWidth() {
        return com.jingdong.sdk.utils.DPIUtil.getWidth(JdSdk.getInstance().getApplicationContext());
    }

    public static int getWidthByDesignValue(int i2, int i3) {
        return com.jingdong.sdk.utils.DPIUtil.getWidthByDesignValue(JdSdk.getInstance().getApplicationContext(), i2, i3);
    }

    public static int getWidthByDesignValue720(int i2) {
        return getWidthByDesignValue(i2, R2.attr.counterOverflowTextColor);
    }

    public static int getWidthByDesignValue750(int i2) {
        return getWidthByDesignValue(i2, R2.attr.decimalNumber);
    }

    public static int percentHeight(float f2) {
        return com.jingdong.sdk.utils.DPIUtil.percentHeight(JdSdk.getInstance().getApplicationContext(), f2);
    }

    public static int percentWidth(float f2) {
        return com.jingdong.sdk.utils.DPIUtil.percentWidth(JdSdk.getInstance().getApplicationContext(), f2);
    }

    public static int px2dip(float f2) {
        return com.jingdong.sdk.utils.DPIUtil.px2dip(f2);
    }

    public static int px2sp(Context context, float f2) {
        return com.jingdong.sdk.utils.DPIUtil.px2sp(context, f2);
    }

    public static void setDensity(float f2) {
        com.jingdong.sdk.utils.DPIUtil.setDensity(f2);
        if (OKLog.D) {
            OKLog.d("DPIUtil", " -->> density=" + f2);
        }
    }

    public static int sp2px(Context context, float f2) {
        return com.jingdong.sdk.utils.DPIUtil.sp2px(context, f2);
    }

    public static int dip2px(Context context, float f2) {
        return com.jingdong.sdk.utils.DPIUtil.dip2px(context, f2);
    }
}

package com.jingdong.jdreact.plugin.utils;

import android.app.Application;
import android.content.Context;
import android.view.Display;
import android.view.WindowManager;
import com.jingdong.common.jdreactFramework.JDReactHelper;

/* loaded from: classes14.dex */
public class DPIUtil {
    static final String TAG = "DPIUtil";
    private static Display defaultDisplay = null;
    private static float mDensity = 160.0f;

    public static int dip2px(float f2) {
        return (int) ((f2 * mDensity) + 0.5f);
    }

    public static Display getDefaultDisplay() {
        if (defaultDisplay == null) {
            defaultDisplay = ((WindowManager) JDReactHelper.newInstance().getApplicationContext().getSystemService("window")).getDefaultDisplay();
        }
        return defaultDisplay;
    }

    public static float getDensity() {
        return mDensity;
    }

    public static void init(Application application) {
        if (application == null) {
            return;
        }
        try {
            setDensity(JDReactHelper.newInstance().getDensity(application));
        } catch (Exception e2) {
            LogUtil.e(TAG, e2.toString());
        }
    }

    public static int px2dip(float f2) {
        return (int) ((f2 / mDensity) + 0.5f);
    }

    public static int px2sp(Context context, float f2) {
        return (int) ((f2 / JDReactHelper.newInstance().getScaledDensity(context)) + 0.5f);
    }

    public static void setDensity(float f2) {
        mDensity = f2;
    }

    public static int sp2px(Context context, float f2) {
        return (int) ((f2 - 0.5f) * JDReactHelper.newInstance().getScaledDensity(context));
    }

    public static int dip2px(Context context, float f2) {
        return (int) ((f2 * JDReactHelper.newInstance().getDensity(context)) + 0.5f);
    }
}

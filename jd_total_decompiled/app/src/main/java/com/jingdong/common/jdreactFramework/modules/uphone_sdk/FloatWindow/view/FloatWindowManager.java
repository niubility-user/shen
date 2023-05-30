package com.jingdong.common.jdreactFramework.modules.uphone_sdk.FloatWindow.view;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import com.jingdong.sdk.platform.business.personal.R2;

/* loaded from: classes5.dex */
public class FloatWindowManager {
    private static FloatLayout mFloatLayout;
    private static boolean mHasShown;
    private static WindowManager mWindowManager;
    private static WindowManager.LayoutParams wmParams;

    public static void createFloatWindow(Context context) {
        wmParams = new WindowManager.LayoutParams();
        WindowManager windowManager = getWindowManager(context);
        mFloatLayout = new FloatLayout(context);
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 26) {
            wmParams.type = R2.attr.textIsDisplayable;
        } else if (i2 >= 24) {
            wmParams.type = 2002;
        } else {
            String packageName = context.getPackageName();
            PackageManager packageManager = context.getPackageManager();
            context.getClass();
            if (packageManager.checkPermission("android.permission.SYSTEM_ALERT_WINDOW", packageName) == 0) {
                wmParams.type = 2002;
            } else {
                wmParams.type = 2005;
            }
        }
        WindowManager.LayoutParams layoutParams = wmParams;
        layoutParams.format = 1;
        layoutParams.flags = 8;
        layoutParams.gravity = 8388659;
        DisplayMetrics displayMetrics = new DisplayMetrics();
        mWindowManager.getDefaultDisplay().getMetrics(displayMetrics);
        int i3 = displayMetrics.widthPixels;
        int i4 = displayMetrics.heightPixels;
        WindowManager.LayoutParams layoutParams2 = wmParams;
        layoutParams2.x = i3;
        layoutParams2.y = i4;
        layoutParams2.width = -2;
        layoutParams2.height = -2;
        mFloatLayout.setParams(layoutParams2);
        windowManager.addView(mFloatLayout, wmParams);
        mHasShown = true;
    }

    private static WindowManager getWindowManager(Context context) {
        if (mWindowManager == null) {
            mWindowManager = (WindowManager) context.getSystemService("window");
        }
        return mWindowManager;
    }

    public static void hide() {
        if (mHasShown) {
            mWindowManager.removeViewImmediate(mFloatLayout);
        }
        mHasShown = false;
    }

    public static void removeFloatWindowManager() {
        WindowManager windowManager;
        boolean isAttachedToWindow = Build.VERSION.SDK_INT >= 19 ? mFloatLayout.isAttachedToWindow() : true;
        if (mHasShown && isAttachedToWindow && (windowManager = mWindowManager) != null) {
            mHasShown = false;
            windowManager.removeView(mFloatLayout);
        }
    }

    public static void setTime(int i2) {
        boolean isAttachedToWindow = Build.VERSION.SDK_INT >= 19 ? mFloatLayout.isAttachedToWindow() : true;
        if (mHasShown && isAttachedToWindow && mWindowManager != null) {
            mFloatLayout.setTime(i2);
        }
    }

    public static void show(int i2) {
        if (!mHasShown) {
            mWindowManager.addView(mFloatLayout, wmParams);
        }
        mHasShown = true;
        mFloatLayout.setTime(i2);
    }
}

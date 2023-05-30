package com.jingdong.manto.widget;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;

/* loaded from: classes16.dex */
public class MantoStatusBarUtil {
    private static boolean hasStatusBar;

    static {
        hasStatusBar = Build.VERSION.SDK_INT >= 21 && !hasSmartBar();
    }

    public static int getStatusBarHeight(Activity activity) {
        if (!hasStatusBar || activity == null) {
            return 0;
        }
        int i2 = f.a(activity).d;
        if (i2 <= 0) {
            try {
                return activity.getResources().getDimensionPixelSize(activity.getResources().getIdentifier("status_bar_height", "dimen", "android"));
            } catch (Exception unused) {
                return 0;
            }
        }
        return i2;
    }

    public static boolean hasSmartBar() {
        try {
            return ((Boolean) Class.forName("android.os.Build").getMethod("hasSmartBar", new Class[0]).invoke(null, new Object[0])).booleanValue();
        } catch (Exception unused) {
            return false;
        }
    }

    public static void setStatusBarColor(Activity activity, int i2, boolean z) {
        if (activity == null || activity.getWindow() == null || !hasStatusBar) {
            return;
        }
        if (i2 == -1 && Build.VERSION.SDK_INT < 23) {
            i2 = Color.argb(80, 0, 0, 0);
        }
        if (Build.VERSION.SDK_INT >= 21) {
            activity.getWindow().setStatusBarColor(i2);
        }
        com.jingdong.manto.ui.d.a(activity.getWindow(), z);
    }
}

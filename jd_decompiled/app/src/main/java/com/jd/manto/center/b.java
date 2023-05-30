package com.jd.manto.center;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.view.Window;
import com.jd.lib.cashier.sdk.core.utils.JDDarkUtil;

/* loaded from: classes17.dex */
public final class b {
    public static void a(Activity activity, int i2, boolean z) {
        if (i2 == -1 && Build.VERSION.SDK_INT < 23) {
            i2 = Color.parseColor(JDDarkUtil.COLOR_757575);
        }
        if (Build.VERSION.SDK_INT >= 21) {
            activity.getWindow().setStatusBarColor(i2);
        }
        b(activity.getWindow(), z);
    }

    public static boolean b(Window window, boolean z) {
        if (window == null || window.getDecorView() == null || Build.VERSION.SDK_INT < 23) {
            return false;
        }
        View decorView = window.getDecorView();
        int systemUiVisibility = decorView.getSystemUiVisibility();
        decorView.setSystemUiVisibility(z ? systemUiVisibility | 8192 : systemUiVisibility & (-8193));
        return true;
    }
}

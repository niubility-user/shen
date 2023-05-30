package com.jingdong.manto.utils;

import android.app.Activity;
import android.content.Context;
import android.os.IBinder;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import com.jingdong.sdk.platform.business.personal.R2;

/* loaded from: classes16.dex */
public final class e {
    private static int a = -1;
    private static int b = -1;

    /* renamed from: c  reason: collision with root package name */
    private static int f14312c = -1;

    public static int a(Activity activity) {
        if (c(activity)) {
            return d(activity);
        }
        return 0;
    }

    public static int a(Context context) {
        return a(context, -1);
    }

    private static int a(Context context, int i2) {
        int f2 = f(context);
        if (h(context)) {
            if (i2 <= 0) {
                i2 = a(context, true);
            }
            int c2 = c(context);
            return i2 > c2 ? c2 : i2 < f2 ? f2 : i2;
        }
        double d = f2;
        Double.isNaN(d);
        int i3 = (int) (d / 1.5d);
        int i4 = e(context)[0] / 2;
        return i3 > i4 ? i4 : i3;
    }

    public static int a(Context context, boolean z) {
        int i2 = a;
        return (i2 <= 0 || !z) ? b(context) : i2;
    }

    public static boolean a(View view) {
        InputMethodManager inputMethodManager;
        IBinder windowToken;
        if (view == null || (inputMethodManager = (InputMethodManager) view.getContext().getSystemService("input_method")) == null || (windowToken = view.getWindowToken()) == null) {
            return false;
        }
        try {
            return inputMethodManager.hideSoftInputFromWindow(windowToken, 0);
        } catch (IllegalArgumentException unused) {
            return false;
        }
    }

    public static int b(Context context) {
        if (g(context)) {
            int i2 = com.jingdong.manto.c.c().getInt("com.jd.vapp.compatible.util.keyboard.height", MantoDensityUtils.dip2pixel(context, 230));
            a = i2;
            return i2;
        }
        return com.jingdong.manto.c.c().getInt("com.jd.vapp.compatible.util.keyboard.height", 690);
    }

    public static void b(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) view.getContext().getSystemService("input_method");
        if (inputMethodManager == null || view == null || view.getWindowToken() == null) {
            return;
        }
        inputMethodManager.toggleSoftInput(0, 2);
    }

    public static boolean b(Activity activity) {
        InputMethodManager inputMethodManager;
        View currentFocus;
        IBinder windowToken;
        if (activity == null || (inputMethodManager = (InputMethodManager) activity.getSystemService("input_method")) == null || (currentFocus = activity.getCurrentFocus()) == null || (windowToken = currentFocus.getWindowToken()) == null) {
            return false;
        }
        try {
            return inputMethodManager.hideSoftInputFromWindow(windowToken, 0);
        } catch (IllegalArgumentException unused) {
            return false;
        }
    }

    public static boolean b(Context context, int i2) {
        if (a == i2) {
            return true;
        }
        if (g(context) && i2 >= 0) {
            a = i2;
            MantoLog.d("KeyBordUtil", String.format("save keyboard height: %d", Integer.valueOf(i2)));
            return com.jingdong.manto.c.c().edit().putInt("com.jd.vapp.compatible.util.keyboard.height", i2).commit();
        }
        return false;
    }

    public static int c(Context context) {
        int i2 = b;
        if (i2 > 0) {
            return i2;
        }
        if (g(context)) {
            int dip2pixel = MantoDensityUtils.dip2pixel(context, R2.attr.animSize);
            b = dip2pixel;
            return dip2pixel;
        }
        return R2.attr.jdPrice_margin;
    }

    public static boolean c(Activity activity) {
        int visibility;
        View findViewById = activity.findViewById(16908336);
        return (findViewById == null || (visibility = findViewById.getVisibility()) == 8 || visibility == 4) ? false : true;
    }

    public static int d(Context context) {
        int identifier = context.getResources().getIdentifier("navigation_bar_height", "dimen", "android");
        if (identifier > 0) {
            return context.getResources().getDimensionPixelSize(identifier);
        }
        return 0;
    }

    public static int[] e(Context context) {
        if (context == null) {
            context = com.jingdong.manto.c.a();
        }
        int[] iArr = new int[2];
        if (context instanceof Activity) {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            iArr[0] = displayMetrics.widthPixels;
            iArr[1] = displayMetrics.heightPixels;
        } else {
            Display defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
            iArr[0] = defaultDisplay.getWidth();
            iArr[1] = defaultDisplay.getHeight();
        }
        return iArr;
    }

    public static int f(Context context) {
        int i2 = f14312c;
        if (i2 > 0) {
            return i2;
        }
        if (g(context)) {
            int dip2pixel = MantoDensityUtils.dip2pixel(context, 230);
            f14312c = dip2pixel;
            return dip2pixel;
        }
        return f14312c * 3;
    }

    private static boolean g(Context context) {
        if (context == null) {
            context = com.jingdong.manto.c.a();
        }
        return context != null;
    }

    public static boolean h(Context context) {
        int[] e2 = e(context);
        int i2 = e2[0];
        int i3 = e2[1];
        return true;
    }
}

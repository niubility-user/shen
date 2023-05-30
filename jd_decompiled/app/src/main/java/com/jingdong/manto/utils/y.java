package com.jingdong.manto.utils;

import android.app.Activity;
import android.graphics.Rect;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.DisplayCutout;
import android.view.View;
import java.util.List;

/* loaded from: classes16.dex */
public class y {
    private static int a(Activity activity) {
        if (Build.VERSION.SDK_INT >= 17) {
            Rect rect = new Rect();
            activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
            return MantoDensityUtils.getRealDM(activity).heightPixels - (rect.top + rect.height());
        }
        return MantoDensityUtils.getStatusBarHeight(activity, MantoDensityUtils.dip2pixel(activity, 24));
    }

    private static int a(View view) {
        if (Build.VERSION.SDK_INT >= 28) {
            try {
                DisplayCutout displayCutout = view.getRootWindowInsets().getDisplayCutout();
                List<Rect> boundingRects = displayCutout.getBoundingRects();
                if (boundingRects != null && boundingRects.size() > 0) {
                    return MantoDensityUtils.pixel2dip(displayCutout.getSafeInsetTop());
                }
            } catch (Exception unused) {
            }
        }
        return 0;
    }

    public static Rect b(Activity activity) {
        Rect rect;
        DisplayMetrics realDM = MantoDensityUtils.getRealDM(activity);
        int pixel2dip = MantoDensityUtils.pixel2dip(MantoDensityUtils.getDMWidthPixels());
        int pixel2dip2 = MantoDensityUtils.pixel2dip(MantoDensityUtils.getDMHeightPixels());
        int a = a(activity);
        if (realDM != null) {
            pixel2dip = MantoDensityUtils.pixel2dip(realDM.widthPixels);
            pixel2dip2 = MantoDensityUtils.pixel2dip(realDM.heightPixels - a);
        }
        int rotation = activity.getWindowManager().getDefaultDisplay().getRotation();
        int a2 = a(activity.getWindow().getDecorView());
        if (rotation == 0) {
            rect = new Rect(0, a2, pixel2dip, pixel2dip2);
        } else if (rotation == 1) {
            rect = new Rect(a2, 0, pixel2dip, pixel2dip2);
        } else if (rotation == 2) {
            rect = new Rect(0, 0, pixel2dip, pixel2dip2 - a2);
        } else if (rotation != 3) {
            return new Rect(0, 0, pixel2dip, pixel2dip2);
        } else {
            rect = new Rect(0, 0, pixel2dip - a2, pixel2dip2);
        }
        return rect;
    }
}

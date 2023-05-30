package com.jingdong.common.utils;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.TypedArray;
import com.jingdong.sdk.threadpool.utils.LogUtil;

/* loaded from: classes6.dex */
public class CompatOThemeUtils {
    public static boolean isFixedOrientation(int i2) {
        return isFixedOrientationLandscape(i2) || isFixedOrientationPortrait(i2) || i2 == 14;
    }

    public static boolean isFixedOrientationLandscape(int i2) {
        return i2 == 0 || i2 == 6 || i2 == 8 || i2 == 11;
    }

    public static boolean isFixedOrientationPortrait(int i2) {
        return i2 == 1 || i2 == 7 || i2 == 9 || i2 == 12;
    }

    public static boolean isTranslucentOrFloating(Activity activity) {
        TypedArray obtainStyledAttributes = activity.getTheme().obtainStyledAttributes(new int[]{16842840, 16842839, 16843763});
        boolean z = obtainStyledAttributes.getBoolean(0, false);
        boolean z2 = obtainStyledAttributes.getBoolean(1, false);
        boolean z3 = !z && obtainStyledAttributes.getBoolean(2, false);
        LogUtil.i("isTranslucent::" + z, "isFloating::" + z2, "isSwipeToDismiss:" + z3);
        obtainStyledAttributes.recycle();
        return z2 || z || z3;
    }

    public static boolean printThemeAttr(Activity activity) {
        try {
            int i2 = activity.getPackageManager().getActivityInfo(activity.getComponentName(), 1).screenOrientation;
            boolean isFixedOrientation = isFixedOrientation(i2);
            ApplicationInfo applicationInfo = activity.getApplicationInfo();
            LogUtil.i("fixedOrientation::" + isFixedOrientation, "screenOrientation==" + i2, "requestedOrientation==" + activity.getRequestedOrientation(), "target sdk::" + applicationInfo.targetSdkVersion);
            TypedArray obtainStyledAttributes = activity.getTheme().obtainStyledAttributes(new int[]{16842840, 16842839, 16843763, 16843277});
            boolean isTranslucentOrFloating = isTranslucentOrFloating(obtainStyledAttributes);
            LogUtil.i("typedArray count::" + obtainStyledAttributes.getIndexCount(), "typedArray lenght::" + obtainStyledAttributes.length(), "fixedOrientation::" + isFixedOrientation, "isTranslucentOrFloating::" + isTranslucentOrFloating, "isFullScreen::" + obtainStyledAttributes.getBoolean(3, false));
            obtainStyledAttributes.recycle();
            return isTranslucentOrFloating;
        } catch (PackageManager.NameNotFoundException e2) {
            e2.printStackTrace();
            LogUtil.e(e2.toString());
            return false;
        }
    }

    public static boolean isFixedOrientation(Activity activity) {
        ActivityInfo activityInfo;
        try {
            activityInfo = activity.getPackageManager().getActivityInfo(activity.getComponentName(), 1);
        } catch (PackageManager.NameNotFoundException e2) {
            e2.printStackTrace();
            activityInfo = null;
        }
        boolean isFixedOrientation = isFixedOrientation(activityInfo != null ? activityInfo.screenOrientation : -1);
        LogUtil.i("isFixedOrientation::" + isFixedOrientation);
        return isFixedOrientation;
    }

    private static boolean isTranslucentOrFloating(TypedArray typedArray) {
        boolean z = typedArray.getBoolean(0, false);
        boolean z2 = typedArray.getBoolean(1, false);
        boolean z3 = !z && typedArray.getBoolean(2, false);
        LogUtil.i("isTranslucent::" + z, "isFloating::" + z2, "isSwipeToDismiss:" + z3);
        return z2 || z || z3;
    }
}

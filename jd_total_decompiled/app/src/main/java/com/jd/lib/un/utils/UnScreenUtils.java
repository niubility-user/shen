package com.jd.lib.un.utils;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.KeyCharacterMap;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.WindowManager;
import androidx.annotation.NonNull;
import com.jd.lib.un.utils.config.UnDeviceInfo;
import com.jingdong.manto.sdk.api.IMantoServerRequester;
import com.jingdong.sdk.platform.business.personal.R2;
import java.lang.reflect.Method;

/* loaded from: classes16.dex */
public class UnScreenUtils {
    private static int getContentViewInvisibleHeight(Activity activity) {
        View findViewById = activity.findViewById(16908290);
        Rect rect = new Rect();
        findViewById.getWindowVisibleDisplayFrame(rect);
        return findViewById.getBottom() - rect.bottom;
    }

    public static DisplayMetrics getDisplayMetrics(Context context) {
        WindowManager windowManager = getWindowManager(context);
        if (windowManager == null) {
            return null;
        }
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics;
    }

    public static int getNavBarHeight(Context context) {
        int identifier = context.getResources().getIdentifier("navigation_bar_height", "dimen", "android");
        if (identifier > 0) {
            return context.getResources().getDimensionPixelSize(identifier);
        }
        return 0;
    }

    private static String getNavBarOverride() {
        if (UnDeviceInfo.getSdkVersion() >= 19) {
            try {
                Method declaredMethod = Class.forName("android.os.SystemProperties").getDeclaredMethod(IMantoServerRequester.GET, String.class);
                declaredMethod.setAccessible(true);
                if (declaredMethod.invoke(null, "qemu.hw.mainkeys") instanceof String) {
                    return (String) declaredMethod.invoke(null, "qemu.hw.mainkeys");
                }
                return null;
            } catch (Throwable unused) {
                return null;
            }
        }
        return null;
    }

    public static float getScreenDensity(Context context) {
        return UnDeviceInfo.getDensity();
    }

    public static int getScreenDensityDpi(Context context) {
        return UnDeviceInfo.getDensityDpiInt();
    }

    public static int getScreenHWithoutVirtKeyboard(Context context) {
        DisplayMetrics displayMetrics = getDisplayMetrics(context);
        if (displayMetrics != null) {
            return displayMetrics.heightPixels;
        }
        return -1;
    }

    public static int getScreenHeightWithVirtKeyboard(Context context) {
        WindowManager windowManager = context.getApplicationContext().getSystemService("window") instanceof WindowManager ? (WindowManager) context.getApplicationContext().getSystemService("window") : null;
        if (windowManager == null) {
            return UnDeviceInfo.getScreenHeight();
        }
        Point point2 = new Point();
        if (UnDeviceInfo.getSdkVersion() >= 17) {
            windowManager.getDefaultDisplay().getRealSize(point2);
        } else {
            windowManager.getDefaultDisplay().getSize(point2);
        }
        return point2.y;
    }

    public static int getScreenW(Context context) {
        DisplayMetrics displayMetrics = getDisplayMetrics(context);
        if (displayMetrics != null) {
            return displayMetrics.widthPixels;
        }
        return -1;
    }

    public static int getScreenWidth(Context context) {
        WindowManager windowManager = context.getApplicationContext().getSystemService("window") instanceof WindowManager ? (WindowManager) context.getApplicationContext().getSystemService("window") : null;
        if (windowManager == null) {
            return UnDeviceInfo.getScreenWidth();
        }
        Point point2 = new Point();
        if (UnDeviceInfo.getSdkVersion() >= 17) {
            windowManager.getDefaultDisplay().getRealSize(point2);
        } else {
            windowManager.getDefaultDisplay().getSize(point2);
        }
        return point2.x;
    }

    public static WindowManager getWindowManager(Context context) {
        if (context.getSystemService("window") instanceof WindowManager) {
            return (WindowManager) context.getSystemService("window");
        }
        return null;
    }

    @TargetApi(14)
    public static boolean hasNavBar(Context context) {
        Resources resources = context.getResources();
        int identifier = resources.getIdentifier("config_showNavigationBar", "bool", "android");
        if (identifier != 0) {
            boolean z = resources.getBoolean(identifier);
            String navBarOverride = getNavBarOverride();
            if ("1".equals(navBarOverride)) {
                return false;
            }
            if ("0".equals(navBarOverride)) {
                return true;
            }
            return z;
        }
        return !ViewConfiguration.get(context).hasPermanentMenuKey();
    }

    public static boolean isLandscape(Context context) {
        return context.getApplicationContext().getResources().getConfiguration().orientation == 2;
    }

    public static boolean isPortrait(Context context) {
        return context.getApplicationContext().getResources().getConfiguration().orientation == 1;
    }

    public static boolean isShowNavBar(Context context) {
        if (hasNavBar(context)) {
            if (UnDeviceInfo.getSdkVersion() < 17) {
                return (ViewConfiguration.get(context).hasPermanentMenuKey() || KeyCharacterMap.deviceHasKey(4)) ? false : true;
            }
            WindowManager windowManager = getWindowManager(context);
            if (windowManager != null) {
                Display defaultDisplay = windowManager.getDefaultDisplay();
                Point point2 = new Point();
                Point point3 = new Point();
                defaultDisplay.getSize(point2);
                defaultDisplay.getRealSize(point3);
                return point3.y != point2.y;
            }
            return false;
        }
        return false;
    }

    public static boolean isTablet(Context context) {
        return (context.getApplicationContext().getResources().getConfiguration().screenLayout & 15) >= 3;
    }

    public static Bitmap screenShot(@NonNull Activity activity) {
        return screenShot(activity, false);
    }

    public static void setFullScreen(@NonNull Activity activity) {
        activity.getWindow().addFlags(R2.attr.placeholderImageScaleType);
    }

    public static void setLandscape(@NonNull Activity activity) {
        activity.setRequestedOrientation(0);
    }

    public static void setPortrait(@NonNull Activity activity) {
        activity.setRequestedOrientation(1);
    }

    public static Bitmap screenShot(@NonNull Activity activity, boolean z) {
        Bitmap createBitmap;
        View decorView = activity.getWindow().getDecorView();
        decorView.setDrawingCacheEnabled(true);
        decorView.buildDrawingCache();
        Bitmap drawingCache = decorView.getDrawingCache();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        if (z) {
            Resources resources = activity.getResources();
            int dimensionPixelSize = resources.getDimensionPixelSize(resources.getIdentifier("status_bar_height", "dimen", "android"));
            createBitmap = Bitmap.createBitmap(drawingCache, 0, dimensionPixelSize, displayMetrics.widthPixels, displayMetrics.heightPixels - dimensionPixelSize);
        } else {
            createBitmap = Bitmap.createBitmap(drawingCache, 0, 0, displayMetrics.widthPixels, displayMetrics.heightPixels);
        }
        decorView.destroyDrawingCache();
        return createBitmap;
    }
}

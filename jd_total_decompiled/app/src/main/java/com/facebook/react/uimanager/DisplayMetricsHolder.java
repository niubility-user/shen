package com.facebook.react.uimanager;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.bridge.WritableNativeMap;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nullable;

/* loaded from: classes12.dex */
public class DisplayMetricsHolder {
    @Nullable
    private static DisplayMetrics sScreenDisplayMetrics;
    @Nullable
    private static DisplayMetrics sWindowDisplayMetrics;

    public static Point getAppSize(Context context) {
        if (context != null) {
            try {
                Point point2 = new Point();
                try {
                    ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getSize(point2);
                } catch (Exception unused) {
                }
                return point2;
            } catch (Exception unused2) {
                return null;
            }
        }
        return null;
    }

    public static Map<String, Map<String, Object>> getDisplayMetricsMap(double d) {
        Assertions.assertNotNull(Boolean.valueOf((sWindowDisplayMetrics == null && sScreenDisplayMetrics == null) ? false : true), "DisplayMetricsHolder must be initialized with initDisplayMetricsIfNotInitialized or initDisplayMetrics");
        HashMap hashMap = new HashMap();
        hashMap.put("windowPhysicalPixels", getPhysicalPixelsMap(sWindowDisplayMetrics, d));
        hashMap.put("screenPhysicalPixels", getPhysicalPixelsMap(sScreenDisplayMetrics, d));
        return hashMap;
    }

    public static WritableNativeMap getDisplayMetricsNativeMap(double d) {
        Assertions.assertNotNull(Boolean.valueOf((sWindowDisplayMetrics == null && sScreenDisplayMetrics == null) ? false : true), "DisplayMetricsHolder must be initialized with initDisplayMetricsIfNotInitialized or initDisplayMetrics");
        WritableNativeMap writableNativeMap = new WritableNativeMap();
        writableNativeMap.putMap("windowPhysicalPixels", getPhysicalPixelsNativeMap(sWindowDisplayMetrics, d));
        writableNativeMap.putMap("screenPhysicalPixels", getPhysicalPixelsNativeMap(sScreenDisplayMetrics, d));
        return writableNativeMap;
    }

    private static Map<String, Object> getPhysicalPixelsMap(DisplayMetrics displayMetrics, double d) {
        HashMap hashMap = new HashMap();
        hashMap.put("width", Integer.valueOf(displayMetrics.widthPixels));
        hashMap.put("height", Integer.valueOf(displayMetrics.heightPixels));
        hashMap.put("scale", Float.valueOf(displayMetrics.density));
        hashMap.put("fontScale", Double.valueOf(d));
        hashMap.put("densityDpi", Integer.valueOf(displayMetrics.densityDpi));
        return hashMap;
    }

    private static WritableNativeMap getPhysicalPixelsNativeMap(DisplayMetrics displayMetrics, double d) {
        WritableNativeMap writableNativeMap = new WritableNativeMap();
        writableNativeMap.putInt("width", displayMetrics.widthPixels);
        writableNativeMap.putInt("height", displayMetrics.heightPixels);
        writableNativeMap.putDouble("scale", displayMetrics.density);
        writableNativeMap.putDouble("fontScale", d);
        writableNativeMap.putDouble("densityDpi", displayMetrics.densityDpi);
        return writableNativeMap;
    }

    public static DisplayMetrics getScreenDisplayMetrics() {
        return sScreenDisplayMetrics;
    }

    @Deprecated
    public static DisplayMetrics getWindowDisplayMetrics() {
        return sWindowDisplayMetrics;
    }

    @Deprecated
    public static void initDisplayMetrics(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        Point appSize = getAppSize(context);
        if (appSize != null) {
            displayMetrics.heightPixels = appSize.y;
            displayMetrics.widthPixels = appSize.x;
        }
        String str = "initDisplayMetrics\uff1acurrent width=" + appSize.x + "---- height=" + appSize.y;
        setWindowDisplayMetrics(displayMetrics);
        DisplayMetrics displayMetrics2 = new DisplayMetrics();
        displayMetrics2.setTo(displayMetrics);
        WindowManager windowManager = (WindowManager) context.getSystemService("window");
        Assertions.assertNotNull(windowManager, "WindowManager is null!");
        Display defaultDisplay = windowManager.getDefaultDisplay();
        if (Build.VERSION.SDK_INT >= 17) {
            defaultDisplay.getRealMetrics(displayMetrics2);
        } else {
            try {
                Method method = Display.class.getMethod("getRawHeight", new Class[0]);
                displayMetrics2.widthPixels = ((Integer) Display.class.getMethod("getRawWidth", new Class[0]).invoke(defaultDisplay, new Object[0])).intValue();
                displayMetrics2.heightPixels = ((Integer) method.invoke(defaultDisplay, new Object[0])).intValue();
            } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e2) {
                throw new RuntimeException("Error getting real dimensions for API level < 17", e2);
            }
        }
        setScreenDisplayMetrics(displayMetrics2);
        String str2 = "initDisplayMetrics: windowDisplayMetrics=" + displayMetrics;
        String str3 = "initDisplayMetrics: screenDisplayMetrics=" + displayMetrics2;
    }

    public static void initDisplayMetricsForActivity(Activity activity) {
        String str = "initDisplayMetricsForActivity --" + activity;
        initDisplayMetrics(activity);
    }

    @Deprecated
    public static void initDisplayMetricsIfNotInitialized(Context context) {
        String str = "initDisplayMetricsIfNotInitialized --" + context;
        if (getScreenDisplayMetrics() != null) {
            return;
        }
        initDisplayMetrics(context);
    }

    public static void initDisplayMetricsIfNotInitializedForActivity(Activity activity) {
        String str = "initDisplayMetricsIfNotInitializedForActivity --" + activity;
        if (getScreenDisplayMetrics() != null) {
            return;
        }
        initDisplayMetrics(activity);
    }

    public static void setScreenDisplayMetrics(DisplayMetrics displayMetrics) {
        sScreenDisplayMetrics = displayMetrics;
    }

    public static void setWindowDisplayMetrics(DisplayMetrics displayMetrics) {
        sWindowDisplayMetrics = displayMetrics;
    }
}

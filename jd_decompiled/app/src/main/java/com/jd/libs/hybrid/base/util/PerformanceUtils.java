package com.jd.libs.hybrid.base.util;

import android.text.TextUtils;
import android.view.View;
import android.view.ViewParent;
import com.jingdong.sdk.jdhttpdns.config.HttpDnsConfig;
import java.lang.reflect.Method;
import java.util.Map;

/* loaded from: classes16.dex */
public class PerformanceUtils {
    private static Class<?> a;
    private static Class<?> b;

    /* renamed from: c  reason: collision with root package name */
    private static Method f5900c;
    public static Method onHtmlPreDownloadChange;
    public static Method onMatchOffline;
    public static Method onMatchShared;
    public static Method onMonitor;
    public static Method onOfflineBingo;
    public static Method onPreloadStatusChange;
    public static Method onSharedOfflineBingo;
    public static Method onWebReload;

    static {
        try {
            Class<?> cls = Class.forName("com.jd.libs.hybrid.performance.WebPerfMonitor");
            Class<?> cls2 = Class.forName("com.jd.libs.hybrid.performance.PerformanceWebView");
            a = cls2;
            onMonitor = cls.getMethod("onMonitor", cls2, String.class, Map.class);
            onPreloadStatusChange = cls.getMethod("onPreloadStatusChange", a, String.class);
            onMatchOffline = cls.getMethod("onMatchOffline", a, String.class);
            onMatchShared = cls.getMethod("onMatchShared", a, String.class);
            onOfflineBingo = cls.getMethod("onOfflineBingo", a, String.class);
            onSharedOfflineBingo = cls.getMethod("onSharedOfflineBingo", a, String.class);
            onHtmlPreDownloadChange = cls.getMethod("onHtmlPreDownloadChange", a, String.class, Integer.TYPE, Object.class);
            onWebReload = cls.getMethod("onWebReload", a, String.class);
        } catch (ClassNotFoundException e2) {
            Log.e("PerformanceUtils", "If you don't need to upload performance data, you can ignore this. Error = " + e2);
        } catch (NoSuchMethodException e3) {
            Log.e("PerformanceUtils", "If you don't need to upload performance data, you can ignore this. Error = " + e3);
        }
        try {
            Class<?> cls3 = Class.forName("com.jingdong.common.web.ui.JDWebView");
            b = Class.forName("com.jd.libs.hybrid.base.HybridWebView");
            f5900c = cls3.getDeclaredMethod("appendPerformanceData", String.class, String.class);
        } catch (ClassNotFoundException | NoSuchMethodException unused) {
        }
    }

    public static void onHtmlPreDownloadChange(Object obj, String str, int i2, Object obj2) {
        Class<?> cls;
        if (onHtmlPreDownloadChange == null || obj == null || (cls = a) == null || !cls.isAssignableFrom(obj.getClass())) {
            return;
        }
        try {
            onHtmlPreDownloadChange.invoke(null, obj, str, Integer.valueOf(i2), obj2);
        } catch (Exception e2) {
            Log.e("PerformanceUtils", e2);
        }
    }

    public static void onMatchOffline(Object obj, String str) {
        Class<?> cls;
        if (onMatchOffline == null || obj == null || (cls = a) == null || !cls.isAssignableFrom(obj.getClass())) {
            return;
        }
        try {
            onMatchOffline.invoke(null, obj, str);
        } catch (Exception e2) {
            Log.e("PerformanceUtils", e2);
        }
    }

    public static void onMatchShared(Object obj, String str) {
        Class<?> cls;
        if (onMatchShared == null || obj == null || (cls = a) == null || !cls.isAssignableFrom(obj.getClass())) {
            return;
        }
        try {
            onMatchShared.invoke(null, obj, str);
        } catch (Exception e2) {
            Log.e("PerformanceUtils", e2);
        }
    }

    public static void onMonitor(Object obj, String str, Map<String, String> map) {
        Class<?> cls;
        Class<?> cls2;
        if (onMonitor != null && obj != null && (cls2 = a) != null && cls2.isAssignableFrom(obj.getClass())) {
            try {
                onMonitor.invoke(null, obj, str, map);
            } catch (Throwable unused) {
            }
        } else if (f5900c != null && obj != null && (cls = b) != null && cls.isAssignableFrom(obj.getClass()) && (obj instanceof View)) {
            ViewParent parent = ((View) obj).getParent();
            while (parent != null && !"com.jingdong.common.web.ui.JDWebView".equals(parent.getClass().getName())) {
                parent = parent.getParent();
            }
            if (parent == null || !HttpDnsConfig.PREDOWNLOAD_PARAMS.equals(str) || map == null || map.isEmpty()) {
                return;
            }
            for (Map.Entry<String, String> entry : map.entrySet()) {
                if (!TextUtils.isEmpty(entry.getKey()) && !TextUtils.isEmpty(entry.getValue())) {
                    f5900c.invoke(parent, entry.getKey(), entry.getValue());
                }
            }
        }
    }

    public static void onOfflineBingo(Object obj, String str) {
        Class<?> cls;
        if (onOfflineBingo == null || obj == null || (cls = a) == null || !cls.isAssignableFrom(obj.getClass())) {
            return;
        }
        try {
            onOfflineBingo.invoke(null, obj, str);
        } catch (Exception e2) {
            Log.e("PerformanceUtils", e2);
        }
    }

    public static void onPreloadStatusChange(Object obj, String str) {
        Class<?> cls;
        if (onPreloadStatusChange == null || obj == null || (cls = a) == null || !cls.isAssignableFrom(obj.getClass())) {
            return;
        }
        try {
            onPreloadStatusChange.invoke(null, obj, str);
        } catch (Exception e2) {
            Log.e("PerformanceUtils", e2);
        }
    }

    public static void onSharedOfflineBingo(Object obj, String str) {
        Class<?> cls;
        if (onSharedOfflineBingo == null || obj == null || (cls = a) == null || !cls.isAssignableFrom(obj.getClass())) {
            return;
        }
        try {
            onSharedOfflineBingo.invoke(null, obj, str);
        } catch (Exception e2) {
            Log.e("PerformanceUtils", e2);
        }
    }

    public static void onWebReload(Object obj, String str) {
        Class<?> cls;
        if (onWebReload == null || obj == null || (cls = a) == null || !cls.isAssignableFrom(obj.getClass())) {
            return;
        }
        try {
            onWebReload.invoke(null, obj, str);
        } catch (Exception e2) {
            Log.e("PerformanceUtils", e2);
        }
    }
}

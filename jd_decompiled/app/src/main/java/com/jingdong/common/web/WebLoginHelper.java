package com.jingdong.common.web;

import android.os.Handler;
import android.os.Looper;
import com.jingdong.common.utils.SwitchQueryFetcher;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import com.tencent.smtt.sdk.WebView;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes.dex */
public class WebLoginHelper {
    private static final String TAG = "WebLoginHelper";
    private static AtomicInteger apiLoginCount = new AtomicInteger(0);
    private static AtomicInteger apiLogoutCount = new AtomicInteger(0);
    private static AtomicInteger broadcastLoginCount = new AtomicInteger(0);
    private static AtomicInteger broadcastLogoutCount = new AtomicInteger(0);
    private static volatile long lastGentokenTime;

    public static void addApiLoginCount() {
        apiLoginCount.incrementAndGet();
    }

    public static void addApiLogoutCount() {
        apiLogoutCount.incrementAndGet();
    }

    public static void addBroadCastLoginCount() {
        broadcastLoginCount.incrementAndGet();
    }

    public static void addBroadCastLogoutCount() {
        broadcastLogoutCount.incrementAndGet();
    }

    public static void asyncWebCookie(int i2) {
        Log.d(TAG, "asyncWebCookie");
        try {
            Class.forName("com.jingdong.common.web.util.JDWebCookieHelper").getMethod("asyncWebCookie", Integer.TYPE).invoke(null, Integer.valueOf(i2));
        } catch (Exception e2) {
            ExceptionReporter.reportWebViewCommonError("async_cookie_error", "", "WebLoginHelper-asyncWebCookie", e2.getMessage());
        }
    }

    public static int getApiLoginCount() {
        return apiLoginCount.get();
    }

    public static int getApiLogoutCount() {
        return apiLogoutCount.get();
    }

    public static int getBroadcastLoginCount() {
        return broadcastLoginCount.get();
    }

    public static int getBroadcastLogoutCount() {
        return broadcastLogoutCount.get();
    }

    public static synchronized long getLastGentokenTime() {
        long j2;
        synchronized (WebLoginHelper.class) {
            j2 = lastGentokenTime;
        }
        return j2;
    }

    public static boolean isPreSyncH5LoginSuccess() {
        int switchIntValue = SwitchQueryFetcher.getSwitchIntValue(SwitchQueryFetcher.WEB_COOKIE_EXPIRE, 0);
        return switchIntValue > 0 && System.currentTimeMillis() - getLastGentokenTime() < ((long) switchIntValue);
    }

    public static synchronized void onGentokenFail() {
        synchronized (WebLoginHelper.class) {
            lastGentokenTime = 0L;
        }
    }

    public static synchronized void onGentokenSuccess() {
        synchronized (WebLoginHelper.class) {
            lastGentokenTime = System.currentTimeMillis();
        }
    }

    public static void onUserLoginChange(boolean z, int i2) {
        Log.d(TAG, "onUserLoginChange:" + z + "  type:" + i2);
        if (z) {
            addApiLoginCount();
        } else {
            addApiLogoutCount();
        }
        if (!z) {
            synchronized (WebLoginHelper.class) {
                lastGentokenTime = 0L;
            }
        }
        if (SwitchQueryFetcher.getSwitchIntValue(SwitchQueryFetcher.WEB_COOKIE_EXPIRE, 0) > 0) {
            asyncWebCookie(2);
        }
    }

    public static void preCreateWebView() {
        Log.d(TAG, "preCreateWebView");
        if (SwitchQueryFetcher.getSwitchIntValue(SwitchQueryFetcher.WEB_COOKIE_EXPIRE, 0) > 0) {
            asyncWebCookie(0);
            return;
        }
        Runnable runnable = new Runnable() { // from class: com.jingdong.common.web.WebLoginHelper.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    new WebView(JdSdk.getInstance().getApplication());
                } catch (Exception unused) {
                }
            }
        };
        if (Looper.myLooper() == Looper.getMainLooper()) {
            runnable.run();
        } else {
            new Handler(Looper.getMainLooper()).post(runnable);
        }
    }
}

package com.jingdong.common.web;

import android.content.Context;
import android.content.MutableContextWrapper;
import android.os.Handler;
import android.os.Looper;
import android.view.ViewGroup;
import com.jingdong.common.utils.SwitchQueryFetcher;
import com.jingdong.common.web.ui.X5WebView;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.sdk.baseinfo.BaseInfo;
import com.jingdong.sdk.oklog.OKLog;
import com.tencent.smtt.sdk.WebView;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

/* loaded from: classes6.dex */
public class WebViewPool {
    private static final int COUNT = 1;
    public static final String RECYCLE_URL = "file:///android_asset/jd.html";
    private static List<X5WebView> inUses = new LinkedList();
    private static List<X5WebView> waitings = new LinkedList();
    private static List<X5WebView> avlCaches = new LinkedList();

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void a(X5WebView x5WebView) {
        x5WebView.clearHistory();
        x5WebView.setCachedWebView(true);
        waitings.remove(x5WebView);
        avlCaches.add(x5WebView);
    }

    private static void destroy(X5WebView x5WebView) {
        x5WebView.destroy();
    }

    public static X5WebView getAvlWebView() {
        return getAvlWebView(null);
    }

    public static boolean isRecycleUrl(String str) {
        return RECYCLE_URL.equals(str);
    }

    public static void recycleWebView(X5WebView x5WebView) {
        if (x5WebView == null) {
            return;
        }
        if (!inUses.remove(x5WebView)) {
            if (OKLog.D) {
                Log.d("WebViewPool", "destroy webview(" + x5WebView.hashCode() + ") because it is not created for reuse.");
            }
            destroy(x5WebView);
        } else if (SwitchQueryFetcher.getSwitchBooleanValue("web_recycle", false) && BaseInfo.getAndroidSDKVersion() >= 29) {
            if (inUses.size() + waitings.size() + avlCaches.size() >= 1) {
                if (OKLog.D) {
                    Log.d("WebViewPool", String.format(Locale.getDefault(), "destroy webview(%d) because of exceeding max count (max=%d, using=%d, waiting=%d, available=%d)", Integer.valueOf(x5WebView.hashCode()), 1, Integer.valueOf(inUses.size()), Integer.valueOf(waitings.size()), Integer.valueOf(avlCaches.size())));
                }
                destroy(x5WebView);
                return;
            }
            resetWebView(x5WebView);
            if (OKLog.D) {
                Log.d("WebViewPool", String.format(Locale.getDefault(), "just reset webview(%d) for next use. (max=%d, using=%d, waiting=%d, available=%d)", Integer.valueOf(x5WebView.hashCode()), 1, Integer.valueOf(inUses.size()), Integer.valueOf(waitings.size()), Integer.valueOf(avlCaches.size())));
            }
        } else {
            if (OKLog.D) {
                Log.d("WebViewPool", "destroy webview(" + x5WebView.hashCode() + ") because the switch is off / Android version < Android10.");
            }
            destroy(x5WebView);
        }
    }

    private static void resetWebView(final X5WebView x5WebView) {
        waitings.add(x5WebView);
        if (x5WebView.getParent() != null) {
            ((ViewGroup) x5WebView.getParent()).removeView(x5WebView);
        }
        x5WebView.setBackgroundColor(0);
        x5WebView.stopLoading();
        if (x5WebView.getContext() instanceof MutableContextWrapper) {
            ((MutableContextWrapper) x5WebView.getContext()).setBaseContext(JdSdk.getInstance().getApplicationContext());
        } else if (OKLog.D) {
            throw new RuntimeException("WebViewPool: X5WebView's context is NOT a MutableContextWrapper!");
        }
        x5WebView.loadUrl(RECYCLE_URL);
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: com.jingdong.common.web.i
            @Override // java.lang.Runnable
            public final void run() {
                WebViewPool.a(X5WebView.this);
            }
        }, 500L);
    }

    public static X5WebView getAvlWebView(Context context) {
        X5WebView x5WebView;
        if (avlCaches.size() > 0 && BaseInfo.getAndroidSDKVersion() >= 29) {
            x5WebView = avlCaches.get(0);
            if (x5WebView.getContext() instanceof MutableContextWrapper) {
                MutableContextWrapper mutableContextWrapper = (MutableContextWrapper) x5WebView.getContext();
                if (context == null) {
                    context = JdSdk.getInstance().getApplicationContext();
                }
                mutableContextWrapper.setBaseContext(context);
            } else if (OKLog.D) {
                throw new RuntimeException("WebViewPool: X5WebView's context is NOT a MutableContextWrapper!");
            }
            avlCaches.remove(x5WebView);
            if (OKLog.D) {
                Log.d("WebViewPool", "reuse cached webview(" + x5WebView.hashCode() + ")");
            }
        } else {
            long currentTimeMillis = System.currentTimeMillis();
            boolean z = WebView.mWebViewCreated;
            if (context == null) {
                context = JdSdk.getInstance().getApplicationContext();
            }
            x5WebView = new X5WebView(currentTimeMillis, z, new MutableContextWrapper(context));
            if (OKLog.D) {
                Log.d("WebViewPool", "create new webview(" + x5WebView.hashCode() + ") for reuse");
            }
        }
        inUses.add(x5WebView);
        return x5WebView;
    }
}

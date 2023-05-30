package com.tencent.open.web;

import android.os.Build;
import android.webkit.WebSettings;
import android.webkit.WebView;
import com.tencent.open.log.SLog;

/* loaded from: classes9.dex */
public class a {
    public static void a(WebView webView) {
        if (webView == null) {
            return;
        }
        b(webView);
        WebSettings settings = webView.getSettings();
        if (settings != null) {
            a(settings);
            b(settings);
        }
    }

    private static void b(WebView webView) {
        if (Build.VERSION.SDK_INT >= 11) {
            webView.removeJavascriptInterface("searchBoxJavaBridge_");
            webView.removeJavascriptInterface("accessibility");
            webView.removeJavascriptInterface("accessibilityTraversal");
        }
    }

    private static void a(WebSettings webSettings) {
        try {
            webSettings.setSavePassword(false);
            webSettings.setAllowFileAccess(false);
            if (Build.VERSION.SDK_INT >= 16) {
                webSettings.setAllowFileAccessFromFileURLs(false);
                webSettings.setAllowUniversalAccessFromFileURLs(false);
            }
        } catch (Exception e2) {
            SLog.e("WebViewUtils", "Exception", e2);
        }
    }

    private static void b(WebSettings webSettings) {
        webSettings.setJavaScriptEnabled(true);
    }
}

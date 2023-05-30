package com.jd.lib.cashier.sdk.core.utils;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import com.jd.cashier.app.jdlibcutter.initialize.DependInitializer;
import com.jd.cashier.app.jdlibcutter.protocol.ui.webview.IWebView;
import com.jd.cashier.app.jdlibcutter.protocol.ui.webview.IWebViewInterceptor;
import com.jd.cashier.app.jdlibcutter.protocol.ui.webview.IWebViewScrollListener;
import com.jd.cashier.app.jdlibcutter.protocol.ui.webview.IWebViewTitleChangeListener;

/* loaded from: classes14.dex */
public class i0 {
    public static void a(View view, Object obj, String str) {
        IWebView webView = DependInitializer.getWebView();
        if (webView == null || obj == null || TextUtils.isEmpty(str)) {
            return;
        }
        webView.addJavascriptInterface(view, obj, str);
    }

    public static void b(View view, IWebViewScrollListener iWebViewScrollListener) {
        IWebView webView = DependInitializer.getWebView();
        if (webView == null || iWebViewScrollListener == null) {
            return;
        }
        webView.addWebViewScrollListener(view, iWebViewScrollListener);
    }

    public static void c(View view, IWebViewTitleChangeListener iWebViewTitleChangeListener) {
        IWebView webView = DependInitializer.getWebView();
        if (webView == null || iWebViewTitleChangeListener == null) {
            return;
        }
        webView.addWebViewTitleChangeListener(view, iWebViewTitleChangeListener);
    }

    public static void d(View view, IWebViewInterceptor iWebViewInterceptor) {
        IWebView webView = DependInitializer.getWebView();
        if (webView == null || iWebViewInterceptor == null) {
            return;
        }
        webView.addWebViewInterceptor(view, iWebViewInterceptor);
    }

    public static View e(Context context, ViewGroup.LayoutParams layoutParams) {
        IWebView webView = DependInitializer.getWebView();
        if (webView != null) {
            return webView.createWebView(context, layoutParams);
        }
        return null;
    }

    public static View f(Context context, ViewGroup.LayoutParams layoutParams) {
        IWebView webView = DependInitializer.getWebView();
        if (webView != null) {
            return webView.createWebViewWithoutDark(context, layoutParams);
        }
        return null;
    }

    public static void g(View view, String str) {
        IWebView webView = DependInitializer.getWebView();
        if (webView != null) {
            webView.executeJavaScript(view, str);
        }
    }

    public static void h(View view, String str) {
        IWebView webView = DependInitializer.getWebView();
        if (webView == null || TextUtils.isEmpty(str)) {
            return;
        }
        webView.loadUrl(view, str);
    }

    public static void i(View view) {
        IWebView webView = DependInitializer.getWebView();
        if (webView != null) {
            webView.onDestroy(view);
        }
    }
}

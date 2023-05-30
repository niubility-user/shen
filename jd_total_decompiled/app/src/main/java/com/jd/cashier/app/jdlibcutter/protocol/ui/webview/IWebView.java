package com.jd.cashier.app.jdlibcutter.protocol.ui.webview;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

/* loaded from: classes13.dex */
public interface IWebView {
    void addJavascriptInterface(View view, Object obj, String str);

    void addWebViewInterceptor(View view, IWebViewInterceptor iWebViewInterceptor);

    void addWebViewScrollListener(View view, IWebViewScrollListener iWebViewScrollListener);

    void addWebViewTitleChangeListener(View view, IWebViewTitleChangeListener iWebViewTitleChangeListener);

    View createWebView(Context context, ViewGroup.LayoutParams layoutParams);

    View createWebViewWithoutDark(Context context, ViewGroup.LayoutParams layoutParams);

    void executeJavaScript(View view, String str);

    void loadUrl(View view, String str);

    void onDestroy(View view);
}

package com.jd.cashier.app.jdlibcutter.impl.ui.webview;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import com.jd.cashier.app.jdlibcutter.protocol.ui.webview.IWebView;
import com.jd.cashier.app.jdlibcutter.protocol.ui.webview.IWebViewInterceptor;
import com.jd.cashier.app.jdlibcutter.protocol.ui.webview.IWebViewScrollListener;
import com.jd.cashier.app.jdlibcutter.protocol.ui.webview.IWebViewTitleChangeListener;
import com.jingdong.common.web.ui.JDWebView;
import com.jingdong.common.web.ui.JDWebViewBuilder;
import com.jingdong.common.web.ui.X5WebView;
import com.jingdong.common.web.uilistener.impl.WebViewUrlInterceptorImpl;
import com.tencent.smtt.export.external.extension.interfaces.IX5WebViewExtension;

/* loaded from: classes13.dex */
public class JDX5WebView implements IWebView {
    private void hideVerticalTrackDrawable(JDWebView jDWebView) {
        X5WebView webView;
        IX5WebViewExtension x5WebViewExtension;
        if (jDWebView == null || (webView = jDWebView.getWebView()) == null || (x5WebViewExtension = webView.getX5WebViewExtension()) == null) {
            return;
        }
        x5WebViewExtension.setVerticalTrackDrawable(null);
    }

    private JDWebView instanceWebView(Context context, ViewGroup.LayoutParams layoutParams) {
        JDWebView jDWebView = new JDWebView(context, true);
        setJdWebViewParam(context, layoutParams, jDWebView);
        return jDWebView;
    }

    private JDWebView instanceWebViewWithoutDark(Context context, ViewGroup.LayoutParams layoutParams) {
        JDWebView jDWebView = new JDWebView(context, new JDWebViewBuilder(context));
        setJdWebViewParam(context, layoutParams, jDWebView);
        return jDWebView;
    }

    private void setJdWebViewParam(Context context, ViewGroup.LayoutParams layoutParams, JDWebView jDWebView) {
        if (layoutParams != null) {
            jDWebView.setLayoutParams(layoutParams);
        }
        jDWebView.setUseCache(true);
        jDWebView.setScrollBarStyle(0);
        jDWebView.setVerticalScrollBarEnabled(false);
        jDWebView.setTopBarGone(true);
        jDWebView.setUseCloseBtn(false);
        jDWebView.setMoreBtnVisible(false);
        jDWebView.customListenDarkMode(false);
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.ui.webview.IWebView
    public void addJavascriptInterface(View view, Object obj, String str) {
        if (!(view instanceof JDWebView) || obj == null || TextUtils.isEmpty(str)) {
            return;
        }
        ((JDWebView) view).addJavascriptInterface(obj, str);
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.ui.webview.IWebView
    public void addWebViewInterceptor(View view, IWebViewInterceptor iWebViewInterceptor) {
        if (!(view instanceof JDWebView) || iWebViewInterceptor == null) {
            return;
        }
        JDWebViewInterceptor jDWebViewInterceptor = new JDWebViewInterceptor(iWebViewInterceptor);
        WebViewUrlInterceptorImpl webViewUrlInterceptorImpl = new WebViewUrlInterceptorImpl(null);
        webViewUrlInterceptorImpl.addUrlShouldOverrideLoading(jDWebViewInterceptor);
        ((JDWebView) view).setWebViewInterceptUrlListener(webViewUrlInterceptorImpl);
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.ui.webview.IWebView
    public void addWebViewScrollListener(View view, IWebViewScrollListener iWebViewScrollListener) {
        if (!(view instanceof JDWebView) || iWebViewScrollListener == null) {
            return;
        }
        ((JDWebView) view).addWebViewScrollListener(new JDWebViewScrollListener(iWebViewScrollListener));
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.ui.webview.IWebView
    public void addWebViewTitleChangeListener(View view, IWebViewTitleChangeListener iWebViewTitleChangeListener) {
        if (!(view instanceof JDWebView) || iWebViewTitleChangeListener == null) {
            return;
        }
        ((JDWebView) view).setTitleChangeListener(new JDWebViewTitleChangeListener(iWebViewTitleChangeListener));
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.ui.webview.IWebView
    public View createWebView(Context context, ViewGroup.LayoutParams layoutParams) {
        JDWebView instanceWebView = instanceWebView(context, layoutParams);
        hideVerticalTrackDrawable(instanceWebView);
        return instanceWebView;
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.ui.webview.IWebView
    public View createWebViewWithoutDark(Context context, ViewGroup.LayoutParams layoutParams) {
        JDWebView instanceWebViewWithoutDark = instanceWebViewWithoutDark(context, layoutParams);
        hideVerticalTrackDrawable(instanceWebViewWithoutDark);
        return instanceWebViewWithoutDark;
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.ui.webview.IWebView
    public void executeJavaScript(View view, String str) {
        try {
            if (!(view instanceof JDWebView) || TextUtils.isEmpty(str)) {
                return;
            }
            ((JDWebView) view).injectJs(str);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.ui.webview.IWebView
    public void loadUrl(View view, String str) {
        if (!(view instanceof JDWebView) || TextUtils.isEmpty(str)) {
            return;
        }
        ((JDWebView) view).loadUrl(str);
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.ui.webview.IWebView
    public void onDestroy(View view) {
        if (view instanceof JDWebView) {
            ((JDWebView) view).onDestory();
        }
    }
}

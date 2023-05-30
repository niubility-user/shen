package com.jingdong.sdk.jweb;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.DownloadListener;
import android.webkit.ValueCallback;
import android.webkit.WebView;
import android.widget.FrameLayout;
import androidx.core.app.NotificationCompat;
import com.jingdong.sdk.jweb.x5.e;
import com.tencent.smtt.export.external.extension.proxy.ProxyWebViewClientExtension;
import java.util.Map;

/* loaded from: classes7.dex */
public class JDWebView extends FrameLayout implements JWebView {
    private String requireMvType;
    private JWebView webCoreProxy;

    /* loaded from: classes7.dex */
    public static class HitTestResult {
        public String mExtra;
        public int mType = 0;
    }

    public JDWebView(Context context) {
        this(context, null);
    }

    public JDWebView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public JDWebView(Context context, AttributeSet attributeSet, int i2) {
        this(context, attributeSet, 0, "x5");
    }

    public JDWebView(Context context, AttributeSet attributeSet, int i2, String str) {
        super(context, attributeSet, i2);
        this.requireMvType = parseType(str);
        init();
    }

    private JWebView createWebCoreProxy() {
        String str = this.requireMvType;
        str.hashCode();
        if (!str.equals(NotificationCompat.CATEGORY_SYSTEM)) {
            try {
                JWebView a = e.a().a(this);
                return (a == null || a.getWebContentView() == null) ? com.jingdong.sdk.jweb.sys.c.a().a(this) : a;
            } catch (Throwable unused) {
            }
        }
        return com.jingdong.sdk.jweb.sys.c.a().a(this);
    }

    private void init() {
        JWebView createWebCoreProxy = createWebCoreProxy();
        this.webCoreProxy = createWebCoreProxy;
        try {
            addView(createWebCoreProxy.getWebContentView());
        } catch (Throwable th) {
            String str = "" + th.getMessage();
        }
    }

    private static String parseType(String str) {
        if (TextUtils.isEmpty(str)) {
            return "x5";
        }
        String lowerCase = str.toLowerCase();
        return (lowerCase.equals("x5") || lowerCase.equals(NotificationCompat.CATEGORY_SYSTEM)) ? lowerCase : "x5";
    }

    public void addJavascriptInterface(Object obj, String str) {
        this.webCoreProxy.addJavascriptInterface(obj, str);
    }

    @Override // com.jingdong.sdk.jweb.JWebView
    public boolean canGoBack() {
        return this.webCoreProxy.canGoBack();
    }

    @Override // com.jingdong.sdk.jweb.JWebView
    public void clearMatches() {
        this.webCoreProxy.clearMatches();
    }

    @Override // com.jingdong.sdk.jweb.JWebView
    public void clearSslPreferences() {
        this.webCoreProxy.clearSslPreferences();
    }

    @Override // com.jingdong.sdk.jweb.JWebView
    public void clearView() {
        this.webCoreProxy.clearView();
    }

    public void destroy() {
        this.webCoreProxy.destroy();
    }

    public void evaluateJavascript(String str, ValueCallback<String> valueCallback) {
        this.webCoreProxy.evaluateJavascript(str, valueCallback);
    }

    @Override // com.jingdong.sdk.jweb.JWebView
    public void findAllAsync(String str) {
        this.webCoreProxy.findAllAsync(str);
    }

    @Override // com.jingdong.sdk.jweb.JWebView
    public void findNext(boolean z) {
        this.webCoreProxy.findNext(z);
    }

    @Override // com.jingdong.sdk.jweb.JWebView
    public int getContentHeight() {
        return this.webCoreProxy.getContentHeight();
    }

    @Override // com.jingdong.sdk.jweb.JWebView
    public a getDefaultOpProvider() {
        return null;
    }

    @Override // com.jingdong.sdk.jweb.JWebView
    public HitTestResult getHitTestResult() {
        return this.webCoreProxy.getHitTestResult();
    }

    @Override // com.jingdong.sdk.jweb.JWebView
    public float getScale() {
        return this.webCoreProxy.getScale();
    }

    @Override // com.jingdong.sdk.jweb.JWebView
    public JWebSettings getSettings() {
        return this.webCoreProxy.getSettings();
    }

    @Override // com.jingdong.sdk.jweb.JWebView
    public String getTitle() {
        return this.webCoreProxy.getTitle();
    }

    @Override // com.jingdong.sdk.jweb.JWebView
    public String getUrl() {
        return this.webCoreProxy.getUrl();
    }

    @Override // com.jingdong.sdk.jweb.JWebView
    public View getView() {
        return this.webCoreProxy.getView();
    }

    @Override // com.jingdong.sdk.jweb.JWebView
    public JWebChromeClient getWebChromeClient() {
        return this.webCoreProxy.getWebChromeClient();
    }

    @Override // com.jingdong.sdk.jweb.JWebView
    public View getWebContentView() {
        return this.webCoreProxy.getWebContentView();
    }

    @Override // com.jingdong.sdk.jweb.JWebView
    public int getWebScrollX() {
        return this.webCoreProxy.getWebScrollX();
    }

    @Override // com.jingdong.sdk.jweb.JWebView
    public int getWebScrollY() {
        return this.webCoreProxy.getWebScrollY();
    }

    @Override // com.jingdong.sdk.jweb.JWebView
    public JWebType getWebType() {
        return this.webCoreProxy.getWebType();
    }

    @Override // com.jingdong.sdk.jweb.JWebView
    public JWebViewClient getWebViewClient() {
        return this.webCoreProxy.getWebViewClient();
    }

    @Override // com.jingdong.sdk.jweb.JWebView
    public Object getX5WebViewExtension() {
        return this.webCoreProxy.getX5WebViewExtension();
    }

    @Override // com.jingdong.sdk.jweb.JWebView
    public void goBack() {
        this.webCoreProxy.goBack();
    }

    @Override // com.jingdong.sdk.jweb.JWebView
    public boolean hasEnteredFullscreen() {
        return this.webCoreProxy.hasEnteredFullscreen();
    }

    @Override // com.jingdong.sdk.jweb.JWebView
    public boolean isOverScrollStart() {
        return this.webCoreProxy.isOverScrollStart();
    }

    @Override // com.jingdong.sdk.jweb.JWebView
    public void leaveFullscreen() {
        this.webCoreProxy.leaveFullscreen();
    }

    @Override // com.jingdong.sdk.jweb.JWebView
    public void loadData(String str, String str2, String str3) {
        this.webCoreProxy.loadData(str, str2, str3);
    }

    @Override // com.jingdong.sdk.jweb.JWebView
    public void loadDataWithBaseURL(String str, String str2, String str3, String str4, String str5) {
        this.webCoreProxy.loadDataWithBaseURL(str, str2, str3, str4, str5);
    }

    public void loadUrl(String str) {
        this.webCoreProxy.loadUrl(str);
    }

    @Override // com.jingdong.sdk.jweb.JWebView
    public void loadUrl(String str, Map<String, String> map) {
        this.webCoreProxy.loadUrl(str, map);
    }

    @Override // com.jingdong.sdk.jweb.JWebView
    public void onPause() {
        this.webCoreProxy.onPause();
    }

    @Override // com.jingdong.sdk.jweb.JWebView
    public void onResume() {
        this.webCoreProxy.onResume();
    }

    public void onWebViewScrollChanged(int i2, int i3, int i4, int i5) {
    }

    @Override // com.jingdong.sdk.jweb.JWebView
    public boolean overlayHorizontalScrollbar() {
        return this.webCoreProxy.overlayHorizontalScrollbar();
    }

    @Override // com.jingdong.sdk.jweb.JWebView
    public void reload() {
        this.webCoreProxy.reload();
    }

    @Override // com.jingdong.sdk.jweb.JWebView
    public void removeJavascriptInterface(String str) {
        this.webCoreProxy.removeJavascriptInterface(str);
    }

    @Override // com.jingdong.sdk.jweb.JWebView
    public void setDownloadListener(DownloadListener downloadListener) {
        this.webCoreProxy.setDownloadListener(downloadListener);
    }

    @Override // com.jingdong.sdk.jweb.JWebView
    public void setFindListener(WebView.FindListener findListener) {
        this.webCoreProxy.setFindListener(findListener);
    }

    @Override // android.view.View
    public void setOnLongClickListener(View.OnLongClickListener onLongClickListener) {
        JWebView jWebView = this.webCoreProxy;
        if (jWebView != null) {
            jWebView.getWebContentView().setOnLongClickListener(onLongClickListener);
        }
    }

    @Override // com.jingdong.sdk.jweb.JWebView
    public void setWebChromeClient(JWebChromeClient jWebChromeClient) {
        this.webCoreProxy.setWebChromeClient(jWebChromeClient);
    }

    @Override // com.jingdong.sdk.jweb.JWebView
    public void setWebViewCallbackClient(JWebViewCallbackClient jWebViewCallbackClient) {
        this.webCoreProxy.setWebViewCallbackClient(jWebViewCallbackClient);
    }

    @Override // com.jingdong.sdk.jweb.JWebView
    public void setWebViewClient(JWebViewClient jWebViewClient) {
        this.webCoreProxy.setWebViewClient(jWebViewClient);
    }

    @Override // com.jingdong.sdk.jweb.JWebView
    public void setWebViewClientExtension(ProxyWebViewClientExtension proxyWebViewClientExtension) {
        this.webCoreProxy.setWebViewClientExtension(proxyWebViewClientExtension);
    }

    @Override // com.jingdong.sdk.jweb.JWebView
    public void stopLoading() {
        this.webCoreProxy.stopLoading();
    }

    @Override // com.jingdong.sdk.jweb.JWebView
    public void super_computeScroll() {
        this.webCoreProxy.super_computeScroll();
    }

    @Override // com.jingdong.sdk.jweb.JWebView
    public boolean super_dispatchTouchEvent(MotionEvent motionEvent) {
        return this.webCoreProxy.super_dispatchTouchEvent(motionEvent);
    }

    @Override // com.jingdong.sdk.jweb.JWebView
    public boolean super_onInterceptTouchEvent(MotionEvent motionEvent) {
        return this.webCoreProxy.super_onInterceptTouchEvent(motionEvent);
    }

    @Override // com.jingdong.sdk.jweb.JWebView
    public void super_onOverScrolled(int i2, int i3, boolean z, boolean z2) {
        this.webCoreProxy.super_onOverScrolled(i2, i3, z, z2);
    }

    @Override // com.jingdong.sdk.jweb.JWebView
    public void super_onScrollChanged(int i2, int i3, int i4, int i5) {
        this.webCoreProxy.super_onScrollChanged(i2, i3, i4, i5);
    }

    @Override // com.jingdong.sdk.jweb.JWebView
    public boolean super_onTouchEvent(MotionEvent motionEvent) {
        return this.webCoreProxy.super_onTouchEvent(motionEvent);
    }

    @Override // com.jingdong.sdk.jweb.JWebView
    public boolean super_overScrollBy(int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, boolean z) {
        return this.webCoreProxy.super_overScrollBy(i2, i3, i4, i5, i6, i7, i8, i9, z);
    }

    @Override // com.jingdong.sdk.jweb.JWebView
    public boolean zoomIn() {
        return this.webCoreProxy.zoomIn();
    }

    @Override // com.jingdong.sdk.jweb.JWebView
    public boolean zoomOut() {
        return this.webCoreProxy.zoomOut();
    }
}

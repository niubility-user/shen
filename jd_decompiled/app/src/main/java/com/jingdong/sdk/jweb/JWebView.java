package com.jingdong.sdk.jweb;

import android.view.MotionEvent;
import android.view.View;
import android.webkit.DownloadListener;
import android.webkit.ValueCallback;
import android.webkit.WebView;
import com.jingdong.sdk.jweb.JDWebView;
import com.tencent.smtt.export.external.extension.proxy.ProxyWebViewClientExtension;
import java.util.Map;

/* loaded from: classes7.dex */
public interface JWebView {
    void addJavascriptInterface(Object obj, String str);

    boolean canGoBack();

    void clearMatches();

    void clearSslPreferences();

    void clearView();

    void destroy();

    void evaluateJavascript(String str, ValueCallback<String> valueCallback);

    void findAllAsync(String str);

    void findNext(boolean z);

    int getContentHeight();

    a getDefaultOpProvider();

    JDWebView.HitTestResult getHitTestResult();

    float getScale();

    JWebSettings getSettings();

    String getTitle();

    String getUrl();

    View getView();

    JWebChromeClient getWebChromeClient();

    View getWebContentView();

    int getWebScrollX();

    int getWebScrollY();

    JWebType getWebType();

    JWebViewClient getWebViewClient();

    Object getX5WebViewExtension();

    void goBack();

    boolean hasEnteredFullscreen();

    boolean isOverScrollStart();

    void leaveFullscreen();

    void loadData(String str, String str2, String str3);

    void loadDataWithBaseURL(String str, String str2, String str3, String str4, String str5);

    void loadUrl(String str);

    void loadUrl(String str, Map<String, String> map);

    void onPause();

    void onResume();

    boolean overlayHorizontalScrollbar();

    void reload();

    void removeJavascriptInterface(String str);

    void setDownloadListener(DownloadListener downloadListener);

    void setFindListener(WebView.FindListener findListener);

    void setWebChromeClient(JWebChromeClient jWebChromeClient);

    void setWebViewCallbackClient(JWebViewCallbackClient jWebViewCallbackClient);

    void setWebViewClient(JWebViewClient jWebViewClient);

    void setWebViewClientExtension(ProxyWebViewClientExtension proxyWebViewClientExtension);

    void stopLoading();

    void super_computeScroll();

    boolean super_dispatchTouchEvent(MotionEvent motionEvent);

    boolean super_onInterceptTouchEvent(MotionEvent motionEvent);

    void super_onOverScrolled(int i2, int i3, boolean z, boolean z2);

    void super_onScrollChanged(int i2, int i3, int i4, int i5);

    boolean super_onTouchEvent(MotionEvent motionEvent);

    boolean super_overScrollBy(int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, boolean z);

    boolean zoomIn();

    boolean zoomOut();
}

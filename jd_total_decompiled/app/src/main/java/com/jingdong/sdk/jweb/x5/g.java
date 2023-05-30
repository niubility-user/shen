package com.jingdong.sdk.jweb.x5;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.ConsoleMessage;
import android.webkit.DownloadListener;
import android.webkit.WebView;
import com.jingdong.sdk.jweb.JDWebView;
import com.jingdong.sdk.jweb.JWebChromeClient;
import com.jingdong.sdk.jweb.JWebPermissionRequest;
import com.jingdong.sdk.jweb.JWebSettings;
import com.jingdong.sdk.jweb.JWebType;
import com.jingdong.sdk.jweb.JWebView;
import com.jingdong.sdk.jweb.JWebViewCallbackClient;
import com.jingdong.sdk.jweb.JWebViewClient;
import com.jingdong.sdk.jweb.x5.a;
import com.tencent.smtt.export.external.extension.proxy.ProxyWebViewClientExtension;
import com.tencent.smtt.export.external.interfaces.ConsoleMessage;
import com.tencent.smtt.export.external.interfaces.GeolocationPermissionsCallback;
import com.tencent.smtt.export.external.interfaces.IX5WebChromeClient;
import com.tencent.smtt.export.external.interfaces.JsPromptResult;
import com.tencent.smtt.export.external.interfaces.JsResult;
import com.tencent.smtt.export.external.interfaces.PermissionRequest;
import com.tencent.smtt.export.external.interfaces.SslError;
import com.tencent.smtt.export.external.interfaces.SslErrorHandler;
import com.tencent.smtt.export.external.interfaces.WebResourceRequest;
import com.tencent.smtt.export.external.interfaces.WebResourceResponse;
import com.tencent.smtt.sdk.ValueCallback;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;
import java.util.Map;

/* loaded from: classes7.dex */
public class g implements JWebView {
    final JDWebView a;
    WebView b;

    /* renamed from: c  reason: collision with root package name */
    WebChromeClient f15166c = new a();
    WebViewClient d = new b();

    /* renamed from: e  reason: collision with root package name */
    private JWebChromeClient f15167e;

    /* renamed from: f  reason: collision with root package name */
    private JWebViewClient f15168f;

    /* renamed from: g  reason: collision with root package name */
    private JWebSettings f15169g;

    /* loaded from: classes7.dex */
    class a extends WebChromeClient {

        /* renamed from: com.jingdong.sdk.jweb.x5.g$a$a  reason: collision with other inner class name */
        /* loaded from: classes7.dex */
        class C0737a implements JWebPermissionRequest {
            final /* synthetic */ PermissionRequest a;

            C0737a(a aVar, PermissionRequest permissionRequest) {
                this.a = permissionRequest;
            }

            @Override // com.jingdong.sdk.jweb.JWebPermissionRequest
            public void deny() {
                this.a.deny();
            }

            @Override // com.jingdong.sdk.jweb.JWebPermissionRequest
            public Uri getOrigin() {
                return this.a.getOrigin();
            }

            @Override // com.jingdong.sdk.jweb.JWebPermissionRequest
            public String[] getResources() {
                return this.a.getResources();
            }

            @Override // com.jingdong.sdk.jweb.JWebPermissionRequest
            public void grant(String[] strArr) {
                this.a.grant(strArr);
            }
        }

        a() {
        }

        @Override // com.tencent.smtt.sdk.WebChromeClient
        public View getVideoLoadingProgressView() {
            return g.this.f15167e != null ? g.this.f15167e.getVideoLoadingProgressView() : super.getVideoLoadingProgressView();
        }

        @Override // com.tencent.smtt.sdk.WebChromeClient
        public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
            android.webkit.ConsoleMessage consoleMessage2;
            if (g.this.f15167e == null) {
                return super.onConsoleMessage(consoleMessage);
            }
            if (consoleMessage == null) {
                consoleMessage2 = null;
            } else {
                ConsoleMessage.MessageLevel messageLevel = ConsoleMessage.MessageLevel.DEBUG;
                int i2 = c.a[consoleMessage.messageLevel().ordinal()];
                if (i2 == 1) {
                    messageLevel = ConsoleMessage.MessageLevel.LOG;
                } else if (i2 == 2) {
                    messageLevel = ConsoleMessage.MessageLevel.TIP;
                } else if (i2 == 3) {
                    messageLevel = ConsoleMessage.MessageLevel.DEBUG;
                } else if (i2 == 4) {
                    messageLevel = ConsoleMessage.MessageLevel.ERROR;
                } else if (i2 == 5) {
                    messageLevel = ConsoleMessage.MessageLevel.WARNING;
                }
                consoleMessage2 = new android.webkit.ConsoleMessage(consoleMessage.message(), consoleMessage.sourceId(), consoleMessage.lineNumber(), messageLevel);
            }
            return g.this.f15167e.onConsoleMessage(consoleMessage2);
        }

        @Override // com.tencent.smtt.sdk.WebChromeClient
        public void onGeolocationPermissionsHidePrompt() {
            if (g.this.f15167e == null) {
                super.onGeolocationPermissionsHidePrompt();
            }
        }

        @Override // com.tencent.smtt.sdk.WebChromeClient
        public void onGeolocationPermissionsShowPrompt(String str, GeolocationPermissionsCallback geolocationPermissionsCallback) {
            if (g.this.f15167e != null) {
                g.this.f15167e.onGeolocationPermissionsShowPrompt(str, new a.e(geolocationPermissionsCallback));
            } else {
                super.onGeolocationPermissionsShowPrompt(str, geolocationPermissionsCallback);
            }
        }

        @Override // com.tencent.smtt.sdk.WebChromeClient
        public void onHideCustomView() {
            if (g.this.f15167e != null) {
                g.this.f15167e.onHideCustomView();
            } else {
                super.onHideCustomView();
            }
        }

        @Override // com.tencent.smtt.sdk.WebChromeClient
        public boolean onJsAlert(WebView webView, String str, String str2, JsResult jsResult) {
            return g.this.f15167e != null ? g.this.f15167e.onJsAlert(g.this.a, str, str2, new a.g(jsResult)) : super.onJsAlert(webView, str, str2, jsResult);
        }

        @Override // com.tencent.smtt.sdk.WebChromeClient
        public boolean onJsConfirm(WebView webView, String str, String str2, JsResult jsResult) {
            return g.this.f15167e != null ? g.this.f15167e.onJsConfirm(g.this.a, str, str2, new a.g(jsResult)) : super.onJsConfirm(webView, str, str2, jsResult);
        }

        @Override // com.tencent.smtt.sdk.WebChromeClient
        public boolean onJsPrompt(WebView webView, String str, String str2, String str3, JsPromptResult jsPromptResult) {
            return g.this.f15167e != null ? g.this.f15167e.onJsPrompt(g.this.a, str, str2, str3, new a.f(jsPromptResult)) : super.onJsPrompt(webView, str, str2, str3, jsPromptResult);
        }

        @Override // com.tencent.smtt.sdk.WebChromeClient
        public void onPermissionRequest(PermissionRequest permissionRequest) {
            if (g.this.f15167e != null) {
                g.this.f15167e.onPermissionRequest(new C0737a(this, permissionRequest));
            } else {
                super.onPermissionRequest(permissionRequest);
            }
        }

        @Override // com.tencent.smtt.sdk.WebChromeClient
        public void onProgressChanged(WebView webView, int i2) {
            if (g.this.f15167e != null) {
                g.this.f15167e.onProgressChanged(g.this.a, i2);
            }
            super.onProgressChanged(webView, i2);
        }

        @Override // com.tencent.smtt.sdk.WebChromeClient
        public void onReceivedTitle(WebView webView, String str) {
            if (g.this.f15167e != null) {
                g.this.f15167e.onReceivedTitle(g.this.a, str);
            } else {
                super.onReceivedTitle(webView, str);
            }
        }

        @Override // com.tencent.smtt.sdk.WebChromeClient
        public void onShowCustomView(View view, IX5WebChromeClient.CustomViewCallback customViewCallback) {
            if (g.this.f15167e != null) {
                g.this.f15167e.onShowCustomView(view, new a.C0734a(customViewCallback));
            } else {
                super.onShowCustomView(view, customViewCallback);
            }
        }

        @Override // com.tencent.smtt.sdk.WebChromeClient
        public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> valueCallback, WebChromeClient.FileChooserParams fileChooserParams) {
            return g.this.f15167e != null ? g.this.f15167e.onShowFileChooser(g.this.a, valueCallback, new a.c(fileChooserParams)) : super.onShowFileChooser(webView, valueCallback, fileChooserParams);
        }

        @Override // com.tencent.smtt.sdk.WebChromeClient
        public void openFileChooser(ValueCallback<Uri> valueCallback, String str, String str2) {
            if (g.this.f15167e != null) {
                g.this.f15167e.openFileChooser(valueCallback, str, str2);
            } else {
                super.openFileChooser(valueCallback, str, str2);
            }
        }
    }

    /* loaded from: classes7.dex */
    class b extends WebViewClient {
        b() {
        }

        @Override // com.tencent.smtt.sdk.WebViewClient
        public void doUpdateVisitedHistory(WebView webView, String str, boolean z) {
            if (g.this.f15168f == null) {
                super.doUpdateVisitedHistory(webView, str, z);
            } else {
                g.this.f15168f.doUpdateVisitedHistory(g.this.a, str, z);
            }
        }

        @Override // com.tencent.smtt.sdk.WebViewClient
        public void onLoadResource(WebView webView, String str) {
            if (g.this.f15168f != null) {
                g.this.f15168f.onLoadResource(g.this.a, str);
            } else {
                super.onLoadResource(webView, str);
            }
        }

        @Override // com.tencent.smtt.sdk.WebViewClient
        public void onPageFinished(WebView webView, String str) {
            if (g.this.f15168f != null) {
                g.this.f15168f.onPageFinished(g.this.a, str);
            } else {
                super.onPageFinished(webView, str);
            }
        }

        @Override // com.tencent.smtt.sdk.WebViewClient
        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            if (g.this.f15168f != null) {
                g.this.f15168f.onPageStarted(g.this.a, str, bitmap);
            } else {
                super.onPageStarted(webView, str, bitmap);
            }
        }

        @Override // com.tencent.smtt.sdk.WebViewClient
        public void onReceivedError(WebView webView, int i2, String str, String str2) {
            if (g.this.f15168f == null) {
                super.onReceivedError(webView, i2, str, str2);
            } else {
                g.this.f15168f.onReceivedError(g.this.a, i2, str, str2);
            }
        }

        @Override // com.tencent.smtt.sdk.WebViewClient
        public void onReceivedHttpError(WebView webView, WebResourceRequest webResourceRequest, WebResourceResponse webResourceResponse) {
            if (g.this.f15168f == null) {
                super.onReceivedHttpError(webView, webResourceRequest, webResourceResponse);
            } else {
                g.this.f15168f.onReceivedHttpError(g.this.a, new a.j(webResourceRequest), com.jingdong.sdk.jweb.x5.a.a(webResourceResponse));
            }
        }

        @Override // com.tencent.smtt.sdk.WebViewClient
        public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
            if (g.this.f15168f == null) {
                super.onReceivedSslError(webView, sslErrorHandler, sslError);
            } else {
                g.this.f15168f.onReceivedSslError(g.this.a, new a.h(sslErrorHandler), sslError == null ? null : new android.net.http.SslError(sslError.getPrimaryError(), sslError.getCertificate()));
            }
        }

        @Override // com.tencent.smtt.sdk.WebViewClient
        public void onScaleChanged(WebView webView, float f2, float f3) {
            if (g.this.f15168f == null) {
                super.onScaleChanged(webView, f2, f3);
            } else {
                g.this.f15168f.onScaleChanged(g.this.a, f2, f3);
            }
        }

        @Override // com.tencent.smtt.sdk.WebViewClient
        public WebResourceResponse shouldInterceptRequest(WebView webView, WebResourceRequest webResourceRequest) {
            return g.this.f15168f != null ? com.jingdong.sdk.jweb.x5.a.a(g.this.f15168f.shouldInterceptRequest(g.this.a, new a.j(webResourceRequest))) : super.shouldInterceptRequest(webView, webResourceRequest);
        }

        @Override // com.tencent.smtt.sdk.WebViewClient
        public WebResourceResponse shouldInterceptRequest(WebView webView, String str) {
            return g.this.f15168f != null ? com.jingdong.sdk.jweb.x5.a.a(g.this.f15168f.shouldInterceptRequest(g.this.a, str)) : super.shouldInterceptRequest(webView, str);
        }

        @Override // com.tencent.smtt.sdk.WebViewClient
        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            return g.this.f15168f == null ? super.shouldOverrideUrlLoading(webView, str) : g.this.f15168f.shouldOverrideUrlLoading(g.this.a, str);
        }
    }

    /* loaded from: classes7.dex */
    static /* synthetic */ class c {
        static final /* synthetic */ int[] a;

        static {
            int[] iArr = new int[ConsoleMessage.MessageLevel.values().length];
            a = iArr;
            try {
                iArr[ConsoleMessage.MessageLevel.LOG.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[ConsoleMessage.MessageLevel.TIP.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                a[ConsoleMessage.MessageLevel.DEBUG.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                a[ConsoleMessage.MessageLevel.ERROR.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                a[ConsoleMessage.MessageLevel.WARNING.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    /* loaded from: classes7.dex */
    private class d extends WebView {
        JDWebView a;

        public d(g gVar, JDWebView jDWebView, Context context) {
            super(context);
            this.a = jDWebView;
        }

        @Override // android.view.View
        protected void onScrollChanged(int i2, int i3, int i4, int i5) {
            super.onScrollChanged(i2, i3, i4, i5);
            JDWebView jDWebView = this.a;
            if (jDWebView != null) {
                jDWebView.onWebViewScrollChanged(i2, i3, i4, i5);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public g(JDWebView jDWebView) {
        this.a = jDWebView;
        try {
            d dVar = new d(this, jDWebView, jDWebView.getContext());
            this.b = dVar;
            dVar.setWebChromeClient(this.f15166c);
            this.b.setWebViewClient(this.d);
            this.b.setWebChromeClientExtension(new com.jingdong.sdk.jweb.x5.d());
        } catch (Throwable th) {
            String str = "" + th;
            throw th;
        }
    }

    @Override // com.jingdong.sdk.jweb.JWebView, com.jingdong.manto.jsengine.IMantoWebViewJS
    public void addJavascriptInterface(Object obj, String str) {
        this.b.addJavascriptInterface(obj, str);
    }

    @Override // com.jingdong.sdk.jweb.JWebView
    public boolean canGoBack() {
        return this.b.canGoBack();
    }

    @Override // com.jingdong.sdk.jweb.JWebView
    public void clearMatches() {
        this.b.clearMatches();
    }

    @Override // com.jingdong.sdk.jweb.JWebView
    public void clearSslPreferences() {
        this.b.clearSslPreferences();
    }

    @Override // com.jingdong.sdk.jweb.JWebView
    public void clearView() {
        this.b.clearView();
    }

    @Override // com.jingdong.sdk.jweb.JWebView, com.jingdong.manto.jsengine.IMantoWebViewJS
    public void destroy() {
        this.b.destroy();
    }

    @Override // com.jingdong.sdk.jweb.JWebView
    public void evaluateJavascript(String str, android.webkit.ValueCallback<String> valueCallback) {
        this.b.evaluateJavascript(str, new a.i(valueCallback));
    }

    @Override // com.jingdong.sdk.jweb.JWebView
    public void findAllAsync(String str) {
        this.b.findAllAsync(str);
    }

    @Override // com.jingdong.sdk.jweb.JWebView
    public void findNext(boolean z) {
        this.b.findNext(z);
    }

    @Override // com.jingdong.sdk.jweb.JWebView
    public int getContentHeight() {
        return this.b.getContentHeight();
    }

    @Override // com.jingdong.sdk.jweb.JWebView
    public com.jingdong.sdk.jweb.a getDefaultOpProvider() {
        return null;
    }

    @Override // com.jingdong.sdk.jweb.JWebView
    public JDWebView.HitTestResult getHitTestResult() {
        WebView.HitTestResult hitTestResult = this.b.getHitTestResult();
        JDWebView.HitTestResult hitTestResult2 = new JDWebView.HitTestResult();
        hitTestResult2.mType = hitTestResult.getType();
        hitTestResult2.mExtra = hitTestResult.getExtra();
        return hitTestResult2;
    }

    @Override // com.jingdong.sdk.jweb.JWebView
    public float getScale() {
        return this.b.getScale();
    }

    @Override // com.jingdong.sdk.jweb.JWebView
    public JWebSettings getSettings() {
        JWebSettings jWebSettings = this.f15169g;
        if (jWebSettings != null) {
            return jWebSettings;
        }
        f fVar = new f(this.b);
        this.f15169g = fVar;
        return fVar;
    }

    @Override // com.jingdong.sdk.jweb.JWebView
    public String getTitle() {
        return this.b.getTitle();
    }

    @Override // com.jingdong.sdk.jweb.JWebView
    public String getUrl() {
        return this.b.getUrl();
    }

    @Override // com.jingdong.sdk.jweb.JWebView
    public View getView() {
        return this.b.getView();
    }

    @Override // com.jingdong.sdk.jweb.JWebView
    public JWebChromeClient getWebChromeClient() {
        return this.f15167e;
    }

    @Override // com.jingdong.sdk.jweb.JWebView
    public View getWebContentView() {
        return this.b;
    }

    @Override // com.jingdong.sdk.jweb.JWebView
    public int getWebScrollX() {
        return this.b.getWebScrollX();
    }

    @Override // com.jingdong.sdk.jweb.JWebView
    public int getWebScrollY() {
        return this.b.getWebScrollY();
    }

    @Override // com.jingdong.sdk.jweb.JWebView
    public JWebType getWebType() {
        return this.b.getX5WebViewExtension() != null ? JWebType.MV_TYPE_X5_X5 : JWebType.MV_TYPE_X5_SYS;
    }

    @Override // com.jingdong.sdk.jweb.JWebView
    public JWebViewClient getWebViewClient() {
        return this.f15168f;
    }

    @Override // com.jingdong.sdk.jweb.JWebView
    public Object getX5WebViewExtension() {
        return this.b.getX5WebViewExtension();
    }

    @Override // com.jingdong.sdk.jweb.JWebView
    public void goBack() {
        this.b.goBack();
    }

    @Override // com.jingdong.sdk.jweb.JWebView
    public boolean hasEnteredFullscreen() {
        return false;
    }

    @Override // com.jingdong.sdk.jweb.JWebView
    public boolean isOverScrollStart() {
        return getView().getScrollY() == 0;
    }

    @Override // com.jingdong.sdk.jweb.JWebView
    public void leaveFullscreen() {
    }

    @Override // com.jingdong.sdk.jweb.JWebView
    public void loadData(String str, String str2, String str3) {
        try {
            this.b.loadData(str, str2, str3);
        } catch (Throwable unused) {
        }
    }

    @Override // com.jingdong.sdk.jweb.JWebView
    public void loadDataWithBaseURL(String str, String str2, String str3, String str4, String str5) {
        try {
            this.b.loadDataWithBaseURL(str, str2, str3, str4, str5);
        } catch (Throwable unused) {
        }
    }

    @Override // com.jingdong.sdk.jweb.JWebView
    public void loadUrl(String str) {
        try {
            this.b.loadUrl(str);
        } catch (Throwable unused) {
        }
    }

    @Override // com.jingdong.sdk.jweb.JWebView
    public void loadUrl(String str, Map<String, String> map) {
        try {
            this.b.loadUrl(str, map);
        } catch (Throwable unused) {
        }
    }

    @Override // com.jingdong.sdk.jweb.JWebView
    public void onPause() {
        this.b.onPause();
    }

    @Override // com.jingdong.sdk.jweb.JWebView
    public void onResume() {
        this.b.onResume();
    }

    @Override // com.jingdong.sdk.jweb.JWebView
    public boolean overlayHorizontalScrollbar() {
        return this.b.overlayHorizontalScrollbar();
    }

    @Override // com.jingdong.sdk.jweb.JWebView
    public void reload() {
        this.b.reload();
    }

    @Override // com.jingdong.sdk.jweb.JWebView
    public void removeJavascriptInterface(String str) {
        this.b.removeJavascriptInterface(str);
    }

    @Override // com.jingdong.sdk.jweb.JWebView
    public void setDownloadListener(DownloadListener downloadListener) {
        this.b.setDownloadListener(new a.b(downloadListener));
    }

    @Override // com.jingdong.sdk.jweb.JWebView
    public void setFindListener(WebView.FindListener findListener) {
        this.b.setFindListener(new a.d(findListener));
    }

    @Override // com.jingdong.sdk.jweb.JWebView
    public void setWebChromeClient(JWebChromeClient jWebChromeClient) {
        this.f15167e = jWebChromeClient;
    }

    @Override // com.jingdong.sdk.jweb.JWebView
    public void setWebViewCallbackClient(JWebViewCallbackClient jWebViewCallbackClient) {
        this.b.setWebViewCallbackClient(new a.k(jWebViewCallbackClient));
    }

    @Override // com.jingdong.sdk.jweb.JWebView
    public void setWebViewClient(JWebViewClient jWebViewClient) {
        this.f15168f = jWebViewClient;
    }

    @Override // com.jingdong.sdk.jweb.JWebView
    public void setWebViewClientExtension(ProxyWebViewClientExtension proxyWebViewClientExtension) {
        this.b.setWebViewClientExtension(proxyWebViewClientExtension);
    }

    @Override // com.jingdong.sdk.jweb.JWebView
    public void stopLoading() {
        this.b.stopLoading();
    }

    @Override // com.jingdong.sdk.jweb.JWebView
    public void super_computeScroll() {
        this.b.super_computeScroll();
    }

    @Override // com.jingdong.sdk.jweb.JWebView
    public boolean super_dispatchTouchEvent(MotionEvent motionEvent) {
        return this.b.super_dispatchTouchEvent(motionEvent);
    }

    @Override // com.jingdong.sdk.jweb.JWebView
    public boolean super_onInterceptTouchEvent(MotionEvent motionEvent) {
        return this.b.super_onInterceptTouchEvent(motionEvent);
    }

    @Override // com.jingdong.sdk.jweb.JWebView
    public void super_onOverScrolled(int i2, int i3, boolean z, boolean z2) {
        this.b.super_onOverScrolled(i2, i3, z, z2);
    }

    @Override // com.jingdong.sdk.jweb.JWebView
    public void super_onScrollChanged(int i2, int i3, int i4, int i5) {
        this.b.super_onScrollChanged(i2, i3, i4, i5);
    }

    @Override // com.jingdong.sdk.jweb.JWebView
    public boolean super_onTouchEvent(MotionEvent motionEvent) {
        return this.b.super_onTouchEvent(motionEvent);
    }

    @Override // com.jingdong.sdk.jweb.JWebView
    public boolean super_overScrollBy(int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, boolean z) {
        return this.b.super_overScrollBy(i2, i3, i4, i5, i6, i7, i8, i9, z);
    }

    @Override // com.jingdong.sdk.jweb.JWebView
    public boolean zoomIn() {
        return this.b.zoomIn();
    }

    @Override // com.jingdong.sdk.jweb.JWebView
    public boolean zoomOut() {
        return this.b.zoomOut();
    }
}

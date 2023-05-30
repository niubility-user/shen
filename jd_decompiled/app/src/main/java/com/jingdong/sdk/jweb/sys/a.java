package com.jingdong.sdk.jweb.sys;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.DownloadListener;
import android.webkit.JavascriptInterface;
import android.webkit.PermissionRequest;
import android.webkit.SslErrorHandler;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.annotation.RequiresApi;
import com.jingdong.sdk.jweb.JDWebView;
import com.jingdong.sdk.jweb.JWebChromeClient;
import com.jingdong.sdk.jweb.JWebPermissionRequest;
import com.jingdong.sdk.jweb.JWebSettings;
import com.jingdong.sdk.jweb.JWebType;
import com.jingdong.sdk.jweb.JWebView;
import com.jingdong.sdk.jweb.JWebViewCallbackClient;
import com.jingdong.sdk.jweb.JWebViewClient;
import com.jingdong.sdk.jweb.sys.Wrapper;
import com.tencent.smtt.export.external.extension.proxy.ProxyWebViewClientExtension;
import java.util.Map;

/* loaded from: classes7.dex */
public class a implements JWebView {
    final JDWebView a;
    WebView b;

    /* renamed from: c  reason: collision with root package name */
    private JWebChromeClient f15159c;
    private JWebViewClient d;

    /* renamed from: e  reason: collision with root package name */
    private JWebSettings f15160e;

    /* renamed from: f  reason: collision with root package name */
    WebChromeClient f15161f = new C0731a();

    /* renamed from: g  reason: collision with root package name */
    WebViewClient f15162g = new b();

    /* renamed from: com.jingdong.sdk.jweb.sys.a$a  reason: collision with other inner class name */
    /* loaded from: classes7.dex */
    class C0731a extends WebChromeClient {

        /* renamed from: com.jingdong.sdk.jweb.sys.a$a$a  reason: collision with other inner class name */
        /* loaded from: classes7.dex */
        class C0732a implements JWebPermissionRequest {
            final /* synthetic */ PermissionRequest a;

            C0732a(C0731a c0731a, PermissionRequest permissionRequest) {
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

        C0731a() {
        }

        @Override // android.webkit.WebChromeClient
        public View getVideoLoadingProgressView() {
            return a.this.f15159c != null ? a.this.f15159c.getVideoLoadingProgressView() : super.getVideoLoadingProgressView();
        }

        @Override // android.webkit.WebChromeClient
        @RequiresApi(api = 21)
        public void onPermissionRequest(PermissionRequest permissionRequest) {
            if (a.this.f15159c != null) {
                a.this.f15159c.onPermissionRequest(new C0732a(this, permissionRequest));
            } else {
                super.onPermissionRequest(permissionRequest);
            }
        }

        @Override // android.webkit.WebChromeClient
        public void onProgressChanged(WebView webView, int i2) {
            if (a.this.f15159c != null) {
                a.this.f15159c.onProgressChanged(a.this.a, i2);
            }
            super.onProgressChanged(webView, i2);
        }

        @Override // android.webkit.WebChromeClient
        public void onReceivedTitle(WebView webView, String str) {
            if (a.this.f15159c != null) {
                a.this.f15159c.onReceivedTitle(a.this.a, str);
            } else {
                super.onReceivedTitle(webView, str);
            }
        }
    }

    /* loaded from: classes7.dex */
    class b extends WebViewClient {
        b() {
        }

        @Override // android.webkit.WebViewClient
        public void doUpdateVisitedHistory(WebView webView, String str, boolean z) {
            if (a.this.d == null) {
                super.doUpdateVisitedHistory(webView, str, z);
            } else {
                a.this.d.doUpdateVisitedHistory(a.this.a, str, z);
            }
        }

        @Override // android.webkit.WebViewClient
        public void onLoadResource(WebView webView, String str) {
            if (a.this.d != null) {
                a.this.d.onLoadResource(a.this.a, str);
            } else {
                super.onLoadResource(webView, str);
            }
        }

        @Override // android.webkit.WebViewClient
        public void onPageFinished(WebView webView, String str) {
            if (a.this.d != null) {
                a.this.d.onPageFinished(a.this.a, str);
            } else {
                super.onPageFinished(webView, str);
            }
        }

        @Override // android.webkit.WebViewClient
        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            if (a.this.d != null) {
                a.this.d.onPageStarted(a.this.a, str, bitmap);
            } else {
                super.onPageStarted(webView, str, bitmap);
            }
        }

        @Override // android.webkit.WebViewClient
        public void onReceivedError(WebView webView, int i2, String str, String str2) {
            if (a.this.d == null) {
                super.onReceivedError(webView, i2, str, str2);
            } else {
                a.this.d.onReceivedError(a.this.a, i2, str, str2);
            }
        }

        @Override // android.webkit.WebViewClient
        public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
            if (a.this.d == null) {
                super.onReceivedSslError(webView, sslErrorHandler, sslError);
            }
        }

        @Override // android.webkit.WebViewClient
        public void onScaleChanged(WebView webView, float f2, float f3) {
            if (a.this.d == null) {
                super.onScaleChanged(webView, f2, f3);
            } else {
                a.this.d.onScaleChanged(a.this.a, f2, f3);
            }
        }

        @Override // android.webkit.WebViewClient
        public WebResourceResponse shouldInterceptRequest(WebView webView, WebResourceRequest webResourceRequest) {
            return (a.this.d == null || Build.VERSION.SDK_INT < 21) ? super.shouldInterceptRequest(webView, webResourceRequest) : Wrapper.convert(a.this.d.shouldInterceptRequest(a.this.a, new Wrapper.a(webResourceRequest)));
        }

        @Override // android.webkit.WebViewClient
        public WebResourceResponse shouldInterceptRequest(WebView webView, String str) {
            return a.this.d == null ? super.shouldInterceptRequest(webView, str) : Wrapper.convert(a.this.d.shouldInterceptRequest(a.this.a, str));
        }

        @Override // android.webkit.WebViewClient
        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            return a.this.d == null ? super.shouldOverrideUrlLoading(webView, str) : a.this.d.shouldOverrideUrlLoading(a.this.a, str);
        }
    }

    /* loaded from: classes7.dex */
    private class c extends WebView {
        JDWebView a;

        public c(a aVar, JDWebView jDWebView, Context context) {
            super(context);
            this.a = jDWebView;
        }

        @Override // android.webkit.WebView, android.view.View
        protected void onScrollChanged(int i2, int i3, int i4, int i5) {
            super.onScrollChanged(i2, i3, i4, i5);
            JDWebView jDWebView = this.a;
            if (jDWebView != null) {
                jDWebView.onWebViewScrollChanged(i2, i3, i4, i5);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public a(JDWebView jDWebView) {
        this.a = jDWebView;
        try {
            c cVar = new c(this, jDWebView, jDWebView.getContext());
            this.b = cVar;
            cVar.setWebViewClient(this.f15162g);
            this.b.setWebChromeClient(this.f15161f);
            this.b.setWebViewClient(this.f15162g);
            this.b.getSettings().setSavePassword(false);
        } catch (Exception e2) {
            String str = "" + e2;
        }
    }

    @Override // com.jingdong.sdk.jweb.JWebView, com.jingdong.manto.jsengine.IMantoWebViewJS
    @JavascriptInterface
    @SuppressLint({"JavascriptInterface"})
    public void addJavascriptInterface(Object obj, String str) {
        try {
            this.b.addJavascriptInterface(obj, str);
        } catch (Exception unused) {
        }
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
    public void evaluateJavascript(String str, ValueCallback<String> valueCallback) {
        if (Build.VERSION.SDK_INT >= 19) {
            this.b.evaluateJavascript(str, valueCallback);
        } else {
            this.b.loadUrl(str);
        }
    }

    @Override // com.jingdong.sdk.jweb.JWebView
    public void findAllAsync(String str) {
        if (Build.VERSION.SDK_INT >= 16) {
            this.b.findAllAsync(str);
        }
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
        return null;
    }

    @Override // com.jingdong.sdk.jweb.JWebView
    public float getScale() {
        return this.b.getScale();
    }

    @Override // com.jingdong.sdk.jweb.JWebView
    public JWebSettings getSettings() {
        JWebSettings jWebSettings = this.f15160e;
        if (jWebSettings != null) {
            return jWebSettings;
        }
        d dVar = new d(this.b);
        this.f15160e = dVar;
        return dVar;
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
        return this.b;
    }

    @Override // com.jingdong.sdk.jweb.JWebView
    public JWebChromeClient getWebChromeClient() {
        return this.f15159c;
    }

    @Override // com.jingdong.sdk.jweb.JWebView
    public View getWebContentView() {
        return this.b;
    }

    @Override // com.jingdong.sdk.jweb.JWebView
    public int getWebScrollX() {
        return this.b.getScrollX();
    }

    @Override // com.jingdong.sdk.jweb.JWebView
    public int getWebScrollY() {
        return this.b.getScrollY();
    }

    @Override // com.jingdong.sdk.jweb.JWebView
    public JWebType getWebType() {
        return JWebType.WV_TYPE_SYS;
    }

    @Override // com.jingdong.sdk.jweb.JWebView
    public JWebViewClient getWebViewClient() {
        return this.d;
    }

    @Override // com.jingdong.sdk.jweb.JWebView
    public Object getX5WebViewExtension() {
        return null;
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
        return false;
    }

    @Override // com.jingdong.sdk.jweb.JWebView
    public void leaveFullscreen() {
    }

    @Override // com.jingdong.sdk.jweb.JWebView
    public void loadData(String str, String str2, String str3) {
        this.b.loadData(str, str2, str3);
    }

    @Override // com.jingdong.sdk.jweb.JWebView
    public void loadDataWithBaseURL(String str, String str2, String str3, String str4, String str5) {
        this.b.loadDataWithBaseURL(str, str2, str3, str4, str5);
    }

    @Override // com.jingdong.sdk.jweb.JWebView
    public void loadUrl(String str) {
        this.b.loadUrl(str);
    }

    @Override // com.jingdong.sdk.jweb.JWebView
    public void loadUrl(String str, Map<String, String> map) {
        this.b.loadUrl(str, map);
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
        this.b.setDownloadListener(downloadListener);
    }

    @Override // com.jingdong.sdk.jweb.JWebView
    public void setFindListener(WebView.FindListener findListener) {
        if (Build.VERSION.SDK_INT >= 16) {
            this.b.setFindListener(findListener);
        }
    }

    @Override // com.jingdong.sdk.jweb.JWebView
    public void setWebChromeClient(JWebChromeClient jWebChromeClient) {
        this.f15159c = jWebChromeClient;
    }

    @Override // com.jingdong.sdk.jweb.JWebView
    public void setWebViewCallbackClient(JWebViewCallbackClient jWebViewCallbackClient) {
    }

    @Override // com.jingdong.sdk.jweb.JWebView
    public void setWebViewClient(JWebViewClient jWebViewClient) {
        this.d = jWebViewClient;
    }

    @Override // com.jingdong.sdk.jweb.JWebView
    public void setWebViewClientExtension(ProxyWebViewClientExtension proxyWebViewClientExtension) {
    }

    @Override // com.jingdong.sdk.jweb.JWebView
    public void stopLoading() {
        this.b.stopLoading();
    }

    @Override // com.jingdong.sdk.jweb.JWebView
    public void super_computeScroll() {
        this.b.computeScroll();
    }

    @Override // com.jingdong.sdk.jweb.JWebView
    public boolean super_dispatchTouchEvent(MotionEvent motionEvent) {
        return this.b.dispatchTouchEvent(motionEvent);
    }

    @Override // com.jingdong.sdk.jweb.JWebView
    public boolean super_onInterceptTouchEvent(MotionEvent motionEvent) {
        return this.b.onInterceptTouchEvent(motionEvent);
    }

    @Override // com.jingdong.sdk.jweb.JWebView
    public void super_onOverScrolled(int i2, int i3, boolean z, boolean z2) {
    }

    @Override // com.jingdong.sdk.jweb.JWebView
    public void super_onScrollChanged(int i2, int i3, int i4, int i5) {
    }

    @Override // com.jingdong.sdk.jweb.JWebView
    public boolean super_onTouchEvent(MotionEvent motionEvent) {
        return this.b.onTouchEvent(motionEvent);
    }

    @Override // com.jingdong.sdk.jweb.JWebView
    public boolean super_overScrollBy(int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, boolean z) {
        return false;
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

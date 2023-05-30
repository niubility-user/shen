package com.jingdong.sdk.jweb.x5;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.GeolocationPermissions;
import android.webkit.WebView;
import com.jingdong.sdk.jweb.JWebChromeClient;
import com.jingdong.sdk.jweb.JWebResourceRequest;
import com.jingdong.sdk.jweb.JWebResourceResponse;
import com.jingdong.sdk.jweb.JWebViewCallbackClient;
import com.tencent.smtt.export.external.interfaces.GeolocationPermissionsCallback;
import com.tencent.smtt.export.external.interfaces.IX5WebChromeClient;
import com.tencent.smtt.export.external.interfaces.IX5WebViewBase;
import com.tencent.smtt.export.external.interfaces.JsPromptResult;
import com.tencent.smtt.export.external.interfaces.JsResult;
import com.tencent.smtt.export.external.interfaces.SslErrorHandler;
import com.tencent.smtt.export.external.interfaces.WebResourceRequest;
import com.tencent.smtt.export.external.interfaces.WebResourceResponse;
import com.tencent.smtt.sdk.DownloadListener;
import com.tencent.smtt.sdk.ValueCallback;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebViewCallbackClient;
import java.util.Map;

/* loaded from: classes7.dex */
public class a {

    /* renamed from: com.jingdong.sdk.jweb.x5.a$a  reason: collision with other inner class name */
    /* loaded from: classes7.dex */
    static class C0734a implements JWebChromeClient.CustomViewCallback {
        IX5WebChromeClient.CustomViewCallback a;

        public C0734a(IX5WebChromeClient.CustomViewCallback customViewCallback) {
            this.a = customViewCallback;
        }

        @Override // com.jingdong.sdk.jweb.JWebChromeClient.CustomViewCallback
        public void onCustomViewHidden() {
            this.a.onCustomViewHidden();
        }
    }

    /* loaded from: classes7.dex */
    public static class b implements DownloadListener {
        android.webkit.DownloadListener a;

        public b(android.webkit.DownloadListener downloadListener) {
            this.a = downloadListener;
        }

        @Override // com.tencent.smtt.sdk.DownloadListener
        public void onDownloadStart(String str, String str2, String str3, String str4, long j2) {
            this.a.onDownloadStart(str, str2, str3, str4, j2);
        }
    }

    /* loaded from: classes7.dex */
    static class c extends JWebChromeClient.FileChooserParams {
        WebChromeClient.FileChooserParams a;

        public c(WebChromeClient.FileChooserParams fileChooserParams) {
            this.a = fileChooserParams;
        }

        @Override // com.jingdong.sdk.jweb.JWebChromeClient.FileChooserParams
        public Intent createIntent() {
            return this.a.createIntent();
        }

        @Override // com.jingdong.sdk.jweb.JWebChromeClient.FileChooserParams
        public String[] getAcceptTypes() {
            return this.a.getAcceptTypes();
        }

        @Override // com.jingdong.sdk.jweb.JWebChromeClient.FileChooserParams
        public String getFilenameHint() {
            return this.a.getFilenameHint();
        }

        @Override // com.jingdong.sdk.jweb.JWebChromeClient.FileChooserParams
        public int getMode() {
            return this.a.getMode();
        }

        @Override // com.jingdong.sdk.jweb.JWebChromeClient.FileChooserParams
        public CharSequence getTitle() {
            return this.a.getTitle();
        }

        @Override // com.jingdong.sdk.jweb.JWebChromeClient.FileChooserParams
        public boolean isCaptureEnabled() {
            return this.a.isCaptureEnabled();
        }
    }

    /* loaded from: classes7.dex */
    public static class d implements IX5WebViewBase.FindListener {
        WebView.FindListener a;

        public d(WebView.FindListener findListener) {
            this.a = findListener;
        }

        @Override // com.tencent.smtt.export.external.interfaces.IX5WebViewBase.FindListener
        public final void onFindResultReceived(int i2, int i3, boolean z) {
            WebView.FindListener findListener = this.a;
            if (findListener == null || Build.VERSION.SDK_INT < 16) {
                return;
            }
            findListener.onFindResultReceived(i2, i3, z);
        }
    }

    /* loaded from: classes7.dex */
    static class e implements GeolocationPermissions.Callback {
        GeolocationPermissionsCallback a;

        public e(GeolocationPermissionsCallback geolocationPermissionsCallback) {
            this.a = geolocationPermissionsCallback;
        }

        @Override // android.webkit.GeolocationPermissions.Callback
        public void invoke(String str, boolean z, boolean z2) {
            this.a.invoke(str, z, z2);
        }
    }

    /* loaded from: classes7.dex */
    static class f extends com.jingdong.sdk.jweb.c {
        public f(JsPromptResult jsPromptResult) {
        }
    }

    /* loaded from: classes7.dex */
    static class g extends com.jingdong.sdk.jweb.c {
        public g(JsResult jsResult) {
        }
    }

    /* loaded from: classes7.dex */
    public static class h extends com.jingdong.sdk.jweb.b {
        public h(SslErrorHandler sslErrorHandler) {
        }
    }

    /* loaded from: classes7.dex */
    public static class i<T> implements ValueCallback<T> {
        android.webkit.ValueCallback<T> a;

        public i(android.webkit.ValueCallback<T> valueCallback) {
            this.a = valueCallback;
        }

        @Override // com.tencent.smtt.sdk.ValueCallback, android.webkit.ValueCallback
        public final void onReceiveValue(T t) {
            android.webkit.ValueCallback<T> valueCallback = this.a;
            if (valueCallback != null) {
                valueCallback.onReceiveValue(t);
            }
        }
    }

    /* loaded from: classes7.dex */
    static class j implements JWebResourceRequest {
        WebResourceRequest a;

        public j(WebResourceRequest webResourceRequest) {
            this.a = webResourceRequest;
        }

        @Override // com.jingdong.sdk.jweb.JWebResourceRequest
        public String getMethod() {
            return this.a.getMethod();
        }

        @Override // com.jingdong.sdk.jweb.JWebResourceRequest
        public Map<String, String> getRequestHeaders() {
            return this.a.getRequestHeaders();
        }

        @Override // com.jingdong.sdk.jweb.JWebResourceRequest
        public Uri getUrl() {
            return this.a.getUrl();
        }

        @Override // com.jingdong.sdk.jweb.JWebResourceRequest
        public boolean hasGesture() {
            return this.a.hasGesture();
        }

        @Override // com.jingdong.sdk.jweb.JWebResourceRequest
        public boolean isForMainFrame() {
            return this.a.isForMainFrame();
        }

        @Override // com.jingdong.sdk.jweb.JWebResourceRequest
        public boolean isRedirect() {
            return this.a.isRedirect();
        }
    }

    /* loaded from: classes7.dex */
    public static class k implements WebViewCallbackClient {
        JWebViewCallbackClient a;

        public k(JWebViewCallbackClient jWebViewCallbackClient) {
            this.a = jWebViewCallbackClient;
        }

        @Override // com.tencent.smtt.sdk.WebViewCallbackClient
        public void computeScroll(View view) {
            this.a.computeScroll(view);
        }

        @Override // com.tencent.smtt.sdk.WebViewCallbackClient
        public boolean dispatchTouchEvent(MotionEvent motionEvent, View view) {
            return this.a.dispatchTouchEvent(motionEvent, view);
        }

        @Override // com.tencent.smtt.sdk.WebViewCallbackClient
        public void invalidate() {
            this.a.invalidate();
        }

        @Override // com.tencent.smtt.sdk.WebViewCallbackClient
        public boolean onInterceptTouchEvent(MotionEvent motionEvent, View view) {
            return this.a.onInterceptTouchEvent(motionEvent, view);
        }

        @Override // com.tencent.smtt.sdk.WebViewCallbackClient
        public void onOverScrolled(int i2, int i3, boolean z, boolean z2, View view) {
            this.a.onOverScrolled(i2, i3, z, z2, view);
        }

        @Override // com.tencent.smtt.sdk.WebViewCallbackClient
        public void onScrollChanged(int i2, int i3, int i4, int i5, View view) {
            this.a.onScrollChanged(i2, i3, i4, i5, view);
        }

        @Override // com.tencent.smtt.sdk.WebViewCallbackClient
        public boolean onTouchEvent(MotionEvent motionEvent, View view) {
            return this.a.onTouchEvent(motionEvent, view);
        }

        @Override // com.tencent.smtt.sdk.WebViewCallbackClient
        public boolean overScrollBy(int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, boolean z, View view) {
            return this.a.overScrollBy(i2, i3, i4, i5, i6, i7, i8, i9, z, view);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static JWebResourceResponse a(WebResourceResponse webResourceResponse) {
        if (webResourceResponse == null) {
            return null;
        }
        return new JWebResourceResponse(webResourceResponse.getMimeType(), webResourceResponse.getEncoding(), webResourceResponse.getStatusCode(), webResourceResponse.getReasonPhrase(), webResourceResponse.getResponseHeaders(), webResourceResponse.getData());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static WebResourceResponse a(JWebResourceResponse jWebResourceResponse) {
        if (jWebResourceResponse == null) {
            return null;
        }
        return new WebResourceResponse(jWebResourceResponse.getMimeType(), jWebResourceResponse.getEncoding(), jWebResourceResponse.getStatusCode(), jWebResourceResponse.getReasonPhrase(), jWebResourceResponse.getResponseHeaders(), jWebResourceResponse.getData());
    }
}

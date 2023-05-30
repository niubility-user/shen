package com.jingdong.sdk.jweb;

import android.graphics.Bitmap;
import android.net.http.SslError;
import android.webkit.WebResourceError;
import android.webkit.WebView;

/* loaded from: classes7.dex */
public class JWebViewClient {
    public static final int ERROR_AUTHENTICATION = -4;
    public static final int ERROR_BAD_URL = -12;
    public static final int ERROR_CONNECT = -6;
    public static final int ERROR_FAILED_SSL_HANDSHAKE = -11;
    public static final int ERROR_FILE = -13;
    public static final int ERROR_FILE_NOT_FOUND = -14;
    public static final int ERROR_HOST_LOOKUP = -2;
    public static final int ERROR_IO = -7;
    public static final int ERROR_PROXY_AUTHENTICATION = -5;
    public static final int ERROR_REDIRECT_LOOP = -9;
    public static final int ERROR_TIMEOUT = -8;
    public static final int ERROR_TOO_MANY_REQUESTS = -15;
    public static final int ERROR_UNKNOWN = -1;
    public static final int ERROR_UNSAFE_RESOURCE = -16;
    public static final int ERROR_UNSUPPORTED_AUTH_SCHEME = -3;
    public static final int ERROR_UNSUPPORTED_SCHEME = -10;

    public void doUpdateVisitedHistory(WebView webView, String str, boolean z) {
    }

    public void doUpdateVisitedHistory(JDWebView jDWebView, String str, boolean z) {
    }

    public void onLoadResource(JDWebView jDWebView, String str) {
    }

    public void onPageCommitVisible(JDWebView jDWebView, String str) {
    }

    public void onPageFinished(JDWebView jDWebView, String str) {
    }

    public void onPageStarted(JDWebView jDWebView, String str, Bitmap bitmap) {
    }

    public void onReceivedError(JDWebView jDWebView, int i2, String str, String str2) {
    }

    public void onReceivedError(JDWebView jDWebView, JWebResourceRequest jWebResourceRequest, WebResourceError webResourceError) {
    }

    public void onReceivedHttpError(JDWebView jDWebView, JWebResourceRequest jWebResourceRequest, JWebResourceResponse jWebResourceResponse) {
    }

    public void onReceivedSslError(JDWebView jDWebView, b bVar, SslError sslError) {
    }

    public void onScaleChanged(JDWebView jDWebView, float f2, float f3) {
    }

    public JWebResourceResponse shouldInterceptRequest(JDWebView jDWebView, JWebResourceRequest jWebResourceRequest) {
        return null;
    }

    public JWebResourceResponse shouldInterceptRequest(JDWebView jDWebView, String str) {
        return null;
    }

    public boolean shouldOverrideUrlLoading(JDWebView jDWebView, JWebResourceRequest jWebResourceRequest) {
        return false;
    }

    public boolean shouldOverrideUrlLoading(JDWebView jDWebView, String str) {
        return false;
    }
}

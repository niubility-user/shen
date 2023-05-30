package com.tencent.smtt.sdk;

import android.annotation.TargetApi;
import android.view.View;
import android.webkit.WebChromeClient;
import com.tencent.smtt.sdk.SystemWebChromeClient;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes9.dex */
public class c extends SystemWebChromeClient {
    /* JADX INFO: Access modifiers changed from: package-private */
    public c(WebView webView, WebChromeClient webChromeClient) {
        super(webView, webChromeClient);
    }

    @Override // android.webkit.WebChromeClient
    @TargetApi(7)
    public void onHideCustomView() {
        this.a.onHideCustomView();
    }

    @Override // android.webkit.WebChromeClient
    @TargetApi(14)
    @Deprecated
    public void onShowCustomView(View view, int i2, WebChromeClient.CustomViewCallback customViewCallback) {
        this.a.onShowCustomView(view, i2, new SystemWebChromeClient.b(customViewCallback));
    }

    @Override // android.webkit.WebChromeClient
    @TargetApi(7)
    public void onShowCustomView(View view, WebChromeClient.CustomViewCallback customViewCallback) {
        this.a.onShowCustomView(view, new SystemWebChromeClient.b(customViewCallback));
    }
}

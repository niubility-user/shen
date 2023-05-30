package com.unionpay;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes11.dex */
public final class w implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ WebViewJavascriptBridge b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public w(WebViewJavascriptBridge webViewJavascriptBridge, String str) {
        this.b = webViewJavascriptBridge;
        this.a = str;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.b.mWebView.loadUrl(this.a);
    }
}

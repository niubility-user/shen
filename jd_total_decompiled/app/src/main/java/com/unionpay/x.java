package com.unionpay;

/* loaded from: classes11.dex */
final class x implements ab {
    final /* synthetic */ WebViewJavascriptBridge a;
    private final String b;

    public x(WebViewJavascriptBridge webViewJavascriptBridge, String str) {
        this.a = webViewJavascriptBridge;
        this.b = str;
    }

    @Override // com.unionpay.ab
    public final void a(String str) {
        this.a._callbackJs(this.b, str);
    }
}

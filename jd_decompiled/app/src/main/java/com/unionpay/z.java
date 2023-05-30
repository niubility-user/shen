package com.unionpay;

import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/* loaded from: classes11.dex */
final class z extends WebViewClient {
    final /* synthetic */ WebViewJavascriptBridge a;

    private z(WebViewJavascriptBridge webViewJavascriptBridge) {
        this.a = webViewJavascriptBridge;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ z(WebViewJavascriptBridge webViewJavascriptBridge, byte b) {
        this(webViewJavascriptBridge);
    }

    @Override // android.webkit.WebViewClient
    public final void onPageFinished(WebView webView, String str) {
        com.unionpay.utils.j.a("test", "onPageFinished");
    }

    @Override // android.webkit.WebViewClient
    public final boolean shouldOverrideUrlLoading(WebView webView, String str) {
        com.unionpay.utils.j.a("uppay", "shouldOverrideUrlLoading\uff1a" + str);
        if (WebViewJavascriptBridge.access$200(this.a) && !TextUtils.isEmpty(str) && !str.startsWith("http")) {
            try {
                Intent intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
                intent.setData(Uri.parse(str));
                this.a.mContext.startActivity(intent);
                return true;
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return super.shouldOverrideUrlLoading(webView, str);
    }
}

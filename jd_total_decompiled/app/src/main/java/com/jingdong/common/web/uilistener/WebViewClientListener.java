package com.jingdong.common.web.uilistener;

import android.graphics.Bitmap;
import com.tencent.smtt.sdk.WebView;

/* loaded from: classes12.dex */
public interface WebViewClientListener {
    void onPageFinished(WebView webView, String str);

    void onPageStarted(WebView webView, String str, Bitmap bitmap);

    void onReceivedError(WebView webView, int i2, String str, String str2);

    void shouldOverrideUrlLoading(WebView webView, String str);
}

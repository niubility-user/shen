package com.jd.libs.jdmbridge.base.base;

import android.view.View;
import android.webkit.ValueCallback;

/* loaded from: classes16.dex */
public interface IBridgeWebView {
    void addJavascriptInterface(Object obj, String str);

    void evaluateJavascript(String str, ValueCallback<String> valueCallback);

    String getUrl();

    View getWebView();
}

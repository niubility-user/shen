package com.jd.libs.hybrid.base;

import android.view.View;
import android.webkit.ValueCallback;
import androidx.annotation.Keep;
import com.jd.jdcache.JDCacheWebView;

@Keep
/* loaded from: classes16.dex */
public interface HybridWebView extends JDCacheWebView {
    @Override // com.jd.jdcache.JDCacheWebView
    void addJavascriptInterface(Object obj, String str);

    @Override // com.jd.jdcache.JDCacheWebView
    void evaluateJavascript(String str, ValueCallback<String> valueCallback);

    String getUrl();

    @Override // com.jd.jdcache.JDCacheWebView
    View getView();

    void loadUrl(String str);

    @Override // com.jd.jdcache.JDCacheWebView
    void reload();

    void stopLoading();
}

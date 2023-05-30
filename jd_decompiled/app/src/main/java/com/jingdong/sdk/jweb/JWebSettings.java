package com.jingdong.sdk.jweb;

import android.webkit.WebSettings;

/* loaded from: classes7.dex */
public interface JWebSettings {

    /* loaded from: classes7.dex */
    public enum a {
        SMALLEST(50),
        SMALLER(75),
        NORMAL(100),
        LARGER(125),
        LARGEST(150);

        a(int i2) {
        }
    }

    void disableDisplayZoomControls();

    void enableCache();

    void enableMixedContent();

    String getUserAgentString();

    void setAllowFileAccess(boolean z);

    void setAppCacheEnable(boolean z);

    void setAppCacheMaxSize(long j2);

    void setAppCachePath(String str);

    void setBuiltInZoomControls(boolean z);

    void setDatabaseEnabled(boolean z);

    void setDatabasePath(String str);

    void setDefaultFontSize(int i2);

    void setDefaultTextEncodingName(String str);

    void setDoNotSaveFormData();

    void setDomStorageEnabled(boolean z);

    void setGeolocationEnabled(boolean z);

    void setJavaScriptCanOpenWindowsAutomatically(boolean z);

    void setJavaScriptEnabled(boolean z);

    void setLayoutAlgorithm(WebSettings.LayoutAlgorithm layoutAlgorithm);

    void setLoadWithOverviewMode(boolean z);

    void setLoadsImagesAutomatically(boolean z);

    void setMediaPlaybackRequiresUserGesture(boolean z);

    void setPluginsEnabled(boolean z);

    void setRenderPriority(WebSettings.RenderPriority renderPriority);

    void setSavePassword(boolean z);

    void setSupportZoom(boolean z);

    void setTextSize(a aVar);

    void setTextZoom(int i2);

    void setUseWideViewPort(boolean z);

    void setUserAgentString(String str);
}

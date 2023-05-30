package com.jingdong.sdk.jweb.sys;

import android.os.Build;
import android.webkit.WebSettings;
import android.webkit.WebView;
import com.jingdong.sdk.jweb.JWebSettings;

/* loaded from: classes7.dex */
public class d implements JWebSettings {
    private final WebView a;

    public d(WebView webView) {
        this.a = webView;
    }

    @Override // com.jingdong.sdk.jweb.JWebSettings
    public void disableDisplayZoomControls() {
        this.a.getSettings().setDisplayZoomControls(false);
    }

    @Override // com.jingdong.sdk.jweb.JWebSettings
    public void enableCache() {
    }

    @Override // com.jingdong.sdk.jweb.JWebSettings
    public void enableMixedContent() {
        if (Build.VERSION.SDK_INT >= 21) {
            this.a.getSettings().setMixedContentMode(0);
        }
    }

    @Override // com.jingdong.sdk.jweb.JWebSettings
    public String getUserAgentString() {
        return this.a.getSettings().getUserAgentString();
    }

    @Override // com.jingdong.sdk.jweb.JWebSettings
    public void setAllowFileAccess(boolean z) {
        this.a.getSettings().setAllowFileAccess(z);
    }

    @Override // com.jingdong.sdk.jweb.JWebSettings
    public void setAppCacheEnable(boolean z) {
        this.a.getSettings().setAppCacheEnabled(z);
    }

    @Override // com.jingdong.sdk.jweb.JWebSettings
    public void setAppCacheMaxSize(long j2) {
        this.a.getSettings().setAppCacheMaxSize(j2);
    }

    @Override // com.jingdong.sdk.jweb.JWebSettings
    public void setAppCachePath(String str) {
        this.a.getSettings().setAppCachePath(str);
    }

    @Override // com.jingdong.sdk.jweb.JWebSettings
    public void setBuiltInZoomControls(boolean z) {
        this.a.getSettings().setBuiltInZoomControls(z);
    }

    @Override // com.jingdong.sdk.jweb.JWebSettings
    public void setDatabaseEnabled(boolean z) {
        this.a.getSettings().setDatabaseEnabled(z);
    }

    @Override // com.jingdong.sdk.jweb.JWebSettings
    public void setDatabasePath(String str) {
        this.a.getSettings().setDatabasePath(str);
    }

    @Override // com.jingdong.sdk.jweb.JWebSettings
    public void setDefaultFontSize(int i2) {
        this.a.getSettings().setDefaultFontSize(i2);
    }

    @Override // com.jingdong.sdk.jweb.JWebSettings
    public void setDefaultTextEncodingName(String str) {
        this.a.getSettings().setDefaultTextEncodingName(str);
    }

    @Override // com.jingdong.sdk.jweb.JWebSettings
    public void setDoNotSaveFormData() {
        this.a.getSettings().setSaveFormData(false);
    }

    @Override // com.jingdong.sdk.jweb.JWebSettings
    public void setDomStorageEnabled(boolean z) {
        this.a.getSettings().setDomStorageEnabled(z);
    }

    @Override // com.jingdong.sdk.jweb.JWebSettings
    public void setGeolocationEnabled(boolean z) {
        this.a.getSettings().setGeolocationEnabled(z);
    }

    @Override // com.jingdong.sdk.jweb.JWebSettings
    public void setJavaScriptCanOpenWindowsAutomatically(boolean z) {
        this.a.getSettings().setJavaScriptCanOpenWindowsAutomatically(z);
    }

    @Override // com.jingdong.sdk.jweb.JWebSettings
    public void setJavaScriptEnabled(boolean z) {
        this.a.getSettings().setJavaScriptEnabled(true);
    }

    @Override // com.jingdong.sdk.jweb.JWebSettings
    public void setLayoutAlgorithm(WebSettings.LayoutAlgorithm layoutAlgorithm) {
    }

    @Override // com.jingdong.sdk.jweb.JWebSettings
    public void setLoadWithOverviewMode(boolean z) {
        this.a.getSettings().setLoadWithOverviewMode(z);
    }

    @Override // com.jingdong.sdk.jweb.JWebSettings
    public void setLoadsImagesAutomatically(boolean z) {
        this.a.getSettings().setLoadsImagesAutomatically(z);
    }

    @Override // com.jingdong.sdk.jweb.JWebSettings
    public void setMediaPlaybackRequiresUserGesture(boolean z) {
        if (Build.VERSION.SDK_INT >= 17) {
            this.a.getSettings().setMediaPlaybackRequiresUserGesture(z);
        }
    }

    @Override // com.jingdong.sdk.jweb.JWebSettings
    public void setPluginsEnabled(boolean z) {
    }

    @Override // com.jingdong.sdk.jweb.JWebSettings
    public void setRenderPriority(WebSettings.RenderPriority renderPriority) {
    }

    @Override // com.jingdong.sdk.jweb.JWebSettings
    public void setSavePassword(boolean z) {
        this.a.getSettings().setSavePassword(z);
    }

    @Override // com.jingdong.sdk.jweb.JWebSettings
    public void setSupportZoom(boolean z) {
        this.a.getSettings().setSupportZoom(z);
    }

    @Override // com.jingdong.sdk.jweb.JWebSettings
    public void setTextSize(JWebSettings.a aVar) {
    }

    @Override // com.jingdong.sdk.jweb.JWebSettings
    public void setTextZoom(int i2) {
        this.a.getSettings().setTextZoom(i2);
    }

    @Override // com.jingdong.sdk.jweb.JWebSettings
    public void setUseWideViewPort(boolean z) {
        this.a.getSettings().setUseWideViewPort(z);
    }

    @Override // com.jingdong.sdk.jweb.JWebSettings
    public void setUserAgentString(String str) {
        this.a.getSettings().setUserAgentString(str);
    }
}

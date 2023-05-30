package com.jd.libs.xdog.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import com.jd.xbridge.XBridgeInstaller;
import com.jd.xbridge.base.IBridgeWebView;
import java.util.Map;

/* loaded from: classes16.dex */
public class XDogWebView extends WebView implements IBridgeWebView {

    /* renamed from: g  reason: collision with root package name */
    private XBridgeInstaller f6223g;

    public XDogWebView(Context context) {
        super(context);
        a();
    }

    @SuppressLint({"SetJavaScriptEnabled"})
    public void a() {
        requestFocus();
        CookieManager.getInstance().setAcceptCookie(true);
        WebSettings settings = getSettings();
        if (Build.VERSION.SDK_INT >= 19) {
            WebView.setWebContentsDebuggingEnabled(true);
        }
        setScrollBarStyle(33554432);
        try {
            settings.setJavaScriptEnabled(true);
            settings.setJavaScriptCanOpenWindowsAutomatically(true);
            settings.setUseWideViewPort(true);
            settings.setLoadWithOverviewMode(true);
            settings.setAllowFileAccessFromFileURLs(false);
            settings.setAllowUniversalAccessFromFileURLs(false);
        } catch (Throwable th) {
            th.printStackTrace();
        }
        settings.setDomStorageEnabled(true);
        settings.setDatabaseEnabled(true);
        settings.setAppCacheEnabled(true);
        settings.setCacheMode(-1);
        XBridgeInstaller xBridgeInstaller = new XBridgeInstaller();
        this.f6223g = xBridgeInstaller;
        xBridgeInstaller.install(this);
    }

    @Override // com.jd.xbridge.base.IBridgeWebView
    public Map<String, com.jd.xbridge.base.d> getBridgeMap() {
        XBridgeInstaller xBridgeInstaller = this.f6223g;
        if (xBridgeInstaller != null) {
            return xBridgeInstaller.getBridgeMap();
        }
        return null;
    }

    @Override // com.jd.xbridge.base.IBridgeWebView
    public View getView() {
        return this;
    }

    @Override // com.jd.xbridge.base.IBridgeWebView
    public void onStart() {
    }

    @Override // com.jd.xbridge.base.IBridgeWebView
    public void onStop() {
    }
}

package com.jingdong.manto.sdk.api;

import android.view.View;
import com.jingdong.manto.MantoCore;
import com.jingdong.manto.jsengine.IMantoWebViewJS;
import com.jingdong.manto.sdk.IMantoSdkBase;
import com.jingdong.sdk.jweb.JDWebView;
import com.jingdong.sdk.jweb.JWebChromeClient;
import java.lang.ref.Reference;
import java.util.Map;

/* loaded from: classes16.dex */
public interface IWebview extends IMantoSdkBase {

    /* loaded from: classes16.dex */
    public interface IMantoWebViewCallBack extends IMantoSdkBase {
        boolean onLongClick(View view);

        void onPageFinished(View view, String str);

        void onProgressChanged(View view, int i2);

        void onReceivedTitle(View view, String str);

        boolean shouldOverrideUrlLoading(View view, String str);
    }

    /* loaded from: classes16.dex */
    public interface IMantoWebViewInterface extends IMantoWebViewJS {
        boolean canGoBack();

        JDWebView.HitTestResult getHitTestResult();

        String getTitle();

        String getUrl();

        View getView();

        JWebChromeClient getWebChromeClient();

        void goBack();

        void init(IMantoWebViewCallBack iMantoWebViewCallBack, String str, boolean z);

        boolean isSysWebView();

        void loadUrl(String str);
    }

    Map<String, Object> getHostJsInterfaces();

    String getHostUA();

    IMantoWebViewInterface getWebview(Reference<MantoCore> reference);
}

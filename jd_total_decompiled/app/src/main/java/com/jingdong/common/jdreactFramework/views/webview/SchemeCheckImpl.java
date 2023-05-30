package com.jingdong.common.jdreactFramework.views.webview;

import com.jingdong.common.jdreactFramework.listener.JDCallbackShouldOverriderUrlLoading;
import com.jingdong.common.web.BaseWebComponent;
import com.jingdong.common.web.uibinder.IWebUiBinder;
import com.jingdong.common.web.urlcheck.ICheckUrl;
import com.tencent.smtt.sdk.WebView;

/* loaded from: classes5.dex */
public class SchemeCheckImpl extends BaseWebComponent implements ICheckUrl {
    private JDCallbackShouldOverriderUrlLoading mWebViewClientListener;

    public SchemeCheckImpl(IWebUiBinder iWebUiBinder, JDCallbackShouldOverriderUrlLoading jDCallbackShouldOverriderUrlLoading) {
        super(iWebUiBinder);
        this.mWebViewClientListener = jDCallbackShouldOverriderUrlLoading;
    }

    @Override // com.jingdong.common.web.urlcheck.ICheckUrl
    public boolean checkReturn() {
        return true;
    }

    @Override // com.jingdong.common.web.urlcheck.ICheckUrl
    public boolean checkUrl(WebView webView, String str) {
        JDCallbackShouldOverriderUrlLoading jDCallbackShouldOverriderUrlLoading;
        if (!str.contains("xview:") || (jDCallbackShouldOverriderUrlLoading = this.mWebViewClientListener) == null) {
            return false;
        }
        jDCallbackShouldOverriderUrlLoading.OnCallbackShouldOverrideUrlLoading(webView, str);
        return false;
    }

    @Override // com.jingdong.common.web.urlcheck.ICheckUrl
    public String getKey() {
        return "checkScheme";
    }
}

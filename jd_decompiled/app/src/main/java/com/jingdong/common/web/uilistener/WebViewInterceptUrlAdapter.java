package com.jingdong.common.web.uilistener;

import com.jingdong.common.web.urlcheck.ICheckUrl;
import com.jingdong.common.web.urlcheck.IInterceptRequest;
import com.tencent.smtt.export.external.interfaces.WebResourceRequest;
import com.tencent.smtt.export.external.interfaces.WebResourceResponse;
import com.tencent.smtt.sdk.WebView;

/* loaded from: classes12.dex */
public class WebViewInterceptUrlAdapter implements IWebViewUrlInterceptor {
    @Override // com.jingdong.common.web.uilistener.IWebViewUrlInterceptor
    public void addUrlCheckOnCommitVisible(ICheckUrl iCheckUrl) {
    }

    @Override // com.jingdong.common.web.uilistener.IWebViewUrlInterceptor
    public void addUrlCheckOnPageFinish(ICheckUrl iCheckUrl) {
    }

    @Override // com.jingdong.common.web.uilistener.IWebViewUrlInterceptor
    public void addUrlCheckOnPageStart(ICheckUrl iCheckUrl) {
    }

    @Override // com.jingdong.common.web.uilistener.IWebViewUrlInterceptor
    public void addUrlCheckOnPageStartAfterDefaultNavi(ICheckUrl iCheckUrl) {
    }

    @Override // com.jingdong.common.web.uilistener.IWebViewUrlInterceptor
    public void addUrlShouldOverrideLoading(ICheckUrl iCheckUrl) {
    }

    @Override // com.jingdong.common.web.uilistener.IWebViewUrlInterceptor
    public <T extends ICheckUrl> T getUrlCheck(String str) {
        return null;
    }

    @Override // com.jingdong.common.web.uilistener.IWebViewUrlInterceptor
    public boolean interceptOnPageStart(WebView webView, String str) {
        return false;
    }

    @Override // com.jingdong.common.web.uilistener.IWebViewUrlInterceptor
    public void interceptOnPageStartAfterDefaultNavi(WebView webView, String str) {
    }

    @Override // com.jingdong.common.web.uilistener.IWebViewUrlInterceptor
    public WebResourceResponse interceptRequest(WebView webView, WebResourceRequest webResourceRequest) {
        return null;
    }

    @Override // com.jingdong.common.web.uilistener.IWebViewUrlInterceptor
    public WebResourceResponse interceptRequest(WebView webView, String str) {
        return null;
    }

    @Override // com.jingdong.common.web.uilistener.IWebViewUrlInterceptor
    public boolean interceptShouldOverrideLoading(WebView webView, String str) {
        return false;
    }

    @Override // com.jingdong.common.web.uilistener.IWebViewUrlInterceptor
    public void onPageCommitVisible(WebView webView, String str) {
    }

    @Override // com.jingdong.common.web.uilistener.IWebViewUrlInterceptor
    public void onPageFinished(WebView webView, String str) {
    }

    @Override // com.jingdong.common.web.uilistener.IWebViewUrlInterceptor
    public void setShouldInterceptRequest(IInterceptRequest iInterceptRequest) {
    }
}

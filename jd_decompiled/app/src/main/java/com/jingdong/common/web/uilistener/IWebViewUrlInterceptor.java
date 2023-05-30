package com.jingdong.common.web.uilistener;

import com.jingdong.common.web.urlcheck.ICheckUrl;
import com.jingdong.common.web.urlcheck.IInterceptRequest;
import com.tencent.smtt.export.external.interfaces.WebResourceRequest;
import com.tencent.smtt.export.external.interfaces.WebResourceResponse;
import com.tencent.smtt.sdk.WebView;

/* loaded from: classes12.dex */
public interface IWebViewUrlInterceptor {
    void addUrlCheckOnCommitVisible(ICheckUrl iCheckUrl);

    void addUrlCheckOnPageFinish(ICheckUrl iCheckUrl);

    void addUrlCheckOnPageStart(ICheckUrl iCheckUrl);

    void addUrlCheckOnPageStartAfterDefaultNavi(ICheckUrl iCheckUrl);

    void addUrlShouldOverrideLoading(ICheckUrl iCheckUrl);

    <T extends ICheckUrl> T getUrlCheck(String str);

    boolean interceptOnPageStart(WebView webView, String str);

    void interceptOnPageStartAfterDefaultNavi(WebView webView, String str);

    WebResourceResponse interceptRequest(WebView webView, WebResourceRequest webResourceRequest);

    WebResourceResponse interceptRequest(WebView webView, String str);

    boolean interceptShouldOverrideLoading(WebView webView, String str);

    void onPageCommitVisible(WebView webView, String str);

    void onPageFinished(WebView webView, String str);

    void setShouldInterceptRequest(IInterceptRequest iInterceptRequest);
}

package com.jingdong.common.web.urlcheck;

import com.tencent.smtt.export.external.interfaces.WebResourceRequest;
import com.tencent.smtt.export.external.interfaces.WebResourceResponse;
import com.tencent.smtt.sdk.WebView;

/* loaded from: classes12.dex */
public interface IInterceptRequest {
    WebResourceResponse interceptRequest(WebView webView, WebResourceRequest webResourceRequest);

    WebResourceResponse interceptRequest(WebView webView, String str);
}

package com.jingdong.common.web.javainterface.impl;

import android.webkit.JavascriptInterface;
import com.jingdong.common.web.BaseWebComponent;
import com.jingdong.common.web.javainterface.IJavaInterface;
import com.jingdong.common.web.uibinder.IWebUiBinder;
import com.jingdong.common.web.xrender.XRender;

/* loaded from: classes12.dex */
public class XRenderJS extends BaseWebComponent implements IJavaInterface {
    private String xRenderReadyParams = "";
    private String xRenderClickParams = "";

    @JavascriptInterface
    public String JDHybridXrenderClick() {
        XRender.Log("\u6267\u884c JDHybridXRenderClick JS\u6865");
        return this.xRenderClickParams;
    }

    @JavascriptInterface
    public String JDHybridXrenderReady() {
        XRender.Log("\u6267\u884c JDHybridXrenderReady JS\u6865");
        return this.xRenderReadyParams;
    }

    @Override // com.jingdong.common.web.javainterface.IJavaInterface
    public String getName() {
        return "XRenderJS";
    }

    @JavascriptInterface
    public Boolean isPreRender() {
        XRender.Log("\u6267\u884c XRenderJS isPreRender");
        IWebUiBinder iWebUiBinder = this.webUiBinder;
        if (iWebUiBinder != null && iWebUiBinder.getJdWebView() != null) {
            return Boolean.valueOf(this.webUiBinder.getJdWebView().isPreRender());
        }
        return Boolean.FALSE;
    }

    public void setxRenderClickParams(String str) {
        this.xRenderClickParams = str;
    }

    public void setxRenderReadyParams(String str) {
        this.xRenderReadyParams = str;
    }
}

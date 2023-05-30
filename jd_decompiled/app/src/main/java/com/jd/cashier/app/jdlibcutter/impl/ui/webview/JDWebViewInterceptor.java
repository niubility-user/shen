package com.jd.cashier.app.jdlibcutter.impl.ui.webview;

import android.text.TextUtils;
import com.jd.cashier.app.jdlibcutter.protocol.ui.webview.IWebViewInterceptor;
import com.jingdong.common.web.urlcheck.ICheckUrl;
import com.tencent.smtt.sdk.WebView;

/* loaded from: classes13.dex */
public class JDWebViewInterceptor implements ICheckUrl {
    private static final String CHECK_MODULE = "JDCashier";
    private String mCheckModule;
    private IWebViewInterceptor mInterceptor;

    public JDWebViewInterceptor(IWebViewInterceptor iWebViewInterceptor) {
        this.mInterceptor = iWebViewInterceptor;
    }

    @Override // com.jingdong.common.web.urlcheck.ICheckUrl
    public boolean checkReturn() {
        return true;
    }

    @Override // com.jingdong.common.web.urlcheck.ICheckUrl
    public boolean checkUrl(WebView webView, String str) {
        IWebViewInterceptor iWebViewInterceptor = this.mInterceptor;
        return iWebViewInterceptor != null && iWebViewInterceptor.onInterceptor(str);
    }

    @Override // com.jingdong.common.web.urlcheck.ICheckUrl
    public String getKey() {
        return TextUtils.isEmpty(this.mCheckModule) ? "JDCashier" : this.mCheckModule;
    }

    public JDWebViewInterceptor(String str, IWebViewInterceptor iWebViewInterceptor) {
        this(iWebViewInterceptor);
        this.mCheckModule = str;
    }
}

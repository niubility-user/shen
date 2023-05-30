package com.jd.cashier.app.jdlibcutter.impl.ui.webview;

import com.jd.cashier.app.jdlibcutter.protocol.ui.webview.IWebViewScrollListener;
import com.jingdong.common.web.uilistener.WebViewScrollListener;

/* loaded from: classes13.dex */
public class JDWebViewScrollListener implements WebViewScrollListener {
    private IWebViewScrollListener mWebViewScrollLister;

    public JDWebViewScrollListener(IWebViewScrollListener iWebViewScrollListener) {
        this.mWebViewScrollLister = iWebViewScrollListener;
    }

    @Override // com.jingdong.common.web.uilistener.WebViewScrollListener
    public void onScrollChanged(int i2, int i3, int i4, int i5) {
        IWebViewScrollListener iWebViewScrollListener = this.mWebViewScrollLister;
        if (iWebViewScrollListener != null) {
            iWebViewScrollListener.onScrollChanged(i2, i3, i4, i5);
        }
    }
}

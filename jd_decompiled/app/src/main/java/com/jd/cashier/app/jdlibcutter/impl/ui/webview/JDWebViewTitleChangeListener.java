package com.jd.cashier.app.jdlibcutter.impl.ui.webview;

import com.jd.cashier.app.jdlibcutter.protocol.ui.webview.IWebViewTitleChangeListener;
import com.jingdong.common.web.uilistener.TitleChangeListener;

/* loaded from: classes13.dex */
public class JDWebViewTitleChangeListener implements TitleChangeListener {
    private IWebViewTitleChangeListener mWebViewTitleChangeListener;

    public JDWebViewTitleChangeListener(IWebViewTitleChangeListener iWebViewTitleChangeListener) {
        this.mWebViewTitleChangeListener = iWebViewTitleChangeListener;
    }

    @Override // com.jingdong.common.web.uilistener.TitleChangeListener
    public void onTitleChange(String str) {
        IWebViewTitleChangeListener iWebViewTitleChangeListener = this.mWebViewTitleChangeListener;
        if (iWebViewTitleChangeListener != null) {
            iWebViewTitleChangeListener.onWebViewTitleChange(str);
        }
    }
}

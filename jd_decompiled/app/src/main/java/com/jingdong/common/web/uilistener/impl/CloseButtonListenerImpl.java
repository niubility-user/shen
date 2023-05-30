package com.jingdong.common.web.uilistener.impl;

import com.jingdong.common.web.BaseWebComponent;
import com.jingdong.common.web.ui.JDWebView;
import com.jingdong.common.web.uibinder.IWebUiBinder;
import com.jingdong.common.web.uilistener.CloseButtonListener;
import com.jingdong.common.web.uilistener.WebBackOrCloseListener;

/* loaded from: classes12.dex */
public class CloseButtonListenerImpl extends BaseWebComponent implements CloseButtonListener {
    private boolean isFromNF;

    public CloseButtonListenerImpl(IWebUiBinder iWebUiBinder) {
        super(iWebUiBinder);
        this.isFromNF = false;
    }

    private void onWebClose() {
        try {
            IWebUiBinder iWebUiBinder = this.webUiBinder;
            JDWebView jdWebView = iWebUiBinder != null ? iWebUiBinder.getJdWebView() : null;
            WebBackOrCloseListener webBackListener = jdWebView != null ? jdWebView.getWebBackListener() : null;
            if (webBackListener == null) {
                return;
            }
            webBackListener.onWebClose();
        } catch (Exception unused) {
        }
    }

    @Override // com.jingdong.common.web.uilistener.CloseButtonListener
    public void close() {
        this.webUiBinder.getJdWebView().stopLoading();
        onWebClose();
        if (this.isFromNF) {
            this.webUiBinder.getBaseActivity().onTitleBack();
        } else {
            this.webUiBinder.finishUi();
        }
    }
}

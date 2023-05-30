package com.jingdong.common.XView;

import com.jingdong.common.XView.javainterface.XViewJavaInterface;
import com.jingdong.common.XView.javainterface.XViewUnite;
import com.jingdong.common.web.javainterface.impl.Performance;
import com.jingdong.common.web.javainterface.impl.ShareHelper;
import com.jingdong.common.web.managers.WebPerfManager;
import com.jingdong.common.web.uibinder.BaseUiBinder;
import com.jingdong.common.web.uilistener.IWebViewUrlInterceptor;

/* loaded from: classes5.dex */
public class XViewUiBinder extends BaseUiBinder {
    public void bindJavaScriptInterface() {
        addJavascriptInterface(new ShareHelper(this, getWebEntity().isIgnoreShare, this.mHandler));
        addJavascriptInterface(new XViewUnite(this));
        addJavascriptInterface(new XViewJavaInterface(this));
        if (WebPerfManager.getInstance().isEnable()) {
            Performance performance2 = new Performance(this);
            addJavascriptInterface(performance2);
            if (getJdWebView() != null) {
                getJdWebView().setTimingReportEnable(true, performance2);
            }
        }
    }

    public void bindUiLintener() {
    }

    public void bindUrlIntercept() {
    }

    @Override // com.jingdong.common.web.uibinder.BaseUiBinder
    protected IWebViewUrlInterceptor newUrlInterceptor() {
        return null;
    }

    @Override // com.jingdong.common.web.uibinder.BaseUiBinder
    protected void onBindUi() {
        if (this.jdWebViewUi != null) {
            bindJavaScriptInterface();
        }
    }
}

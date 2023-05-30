package com.jingdong.common.web.javainterface.impl;

import android.webkit.JavascriptInterface;
import com.jingdong.common.web.BaseWebComponent;
import com.jingdong.common.web.WebUiConstans;
import com.jingdong.common.web.javainterface.IJavaInterface;
import com.jingdong.common.web.uibinder.IWebUiBinder;

/* loaded from: classes12.dex */
public class XWidget extends BaseWebComponent implements IJavaInterface {
    public XWidget(IWebUiBinder iWebUiBinder) {
        super(iWebUiBinder);
    }

    @JavascriptInterface
    public boolean canIUse(String str) {
        return this.webUiBinder.getJdWebView().canIUse(str);
    }

    @Override // com.jingdong.common.web.javainterface.IJavaInterface
    public String getName() {
        return WebUiConstans.JavaInterfaceNames.JDXWidget;
    }
}

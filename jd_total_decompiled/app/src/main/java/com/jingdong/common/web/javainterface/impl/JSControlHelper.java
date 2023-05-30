package com.jingdong.common.web.javainterface.impl;

import android.webkit.JavascriptInterface;
import com.jingdong.common.web.BaseWebComponent;
import com.jingdong.common.web.WebUiConstans;
import com.jingdong.common.web.javainterface.IJavaInterface;
import com.jingdong.common.web.uibinder.IWebUiBinder;
import com.jingdong.common.web.util.WebUnifiedMtaUtil;

/* loaded from: classes12.dex */
public class JSControlHelper extends BaseWebComponent implements IJavaInterface {
    private static final String TAG = "JSControlHelper";

    public JSControlHelper(IWebUiBinder iWebUiBinder) {
        super(iWebUiBinder);
    }

    @JavascriptInterface
    public void finishActivity() {
        WebUnifiedMtaUtil.functionReport(this.webUiBinder, "JSControlHelper_finishActivity");
        this.webUiBinder.finishUi();
    }

    @Override // com.jingdong.common.web.javainterface.IJavaInterface
    public String getName() {
        return WebUiConstans.JavaInterfaceNames.JS_CONTROL;
    }

    public JSControlHelper() {
    }
}

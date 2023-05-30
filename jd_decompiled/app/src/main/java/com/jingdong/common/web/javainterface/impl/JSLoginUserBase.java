package com.jingdong.common.web.javainterface.impl;

import android.webkit.JavascriptInterface;
import com.jingdong.common.login.LoginUserBase;
import com.jingdong.common.web.BaseWebComponent;
import com.jingdong.common.web.WebUiConstans;
import com.jingdong.common.web.javainterface.IJavaInterface;
import com.jingdong.common.web.uibinder.IWebUiBinder;
import com.jingdong.common.web.util.WebUnifiedMtaUtil;

/* loaded from: classes12.dex */
public class JSLoginUserBase extends BaseWebComponent implements IJavaInterface {
    private static final String TAG = "JSLoginUserBase";

    public JSLoginUserBase(IWebUiBinder iWebUiBinder) {
        super(iWebUiBinder);
    }

    @Override // com.jingdong.common.web.javainterface.IJavaInterface
    public String getName() {
        return WebUiConstans.JavaInterfaceNames.JS_LOGINUSERBASE;
    }

    @JavascriptInterface
    public boolean hasLogin() {
        WebUnifiedMtaUtil.functionReport(this.webUiBinder, "JSLoginUserBase_hasLogin");
        return LoginUserBase.hasLogin();
    }

    public JSLoginUserBase() {
    }
}

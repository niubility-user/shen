package com.jingdong.common.web.javainterface.impl;

import com.jingdong.common.web.BaseWebComponent;
import com.jingdong.common.web.WebUiConstans;
import com.jingdong.common.web.javainterface.IJavaInterface;
import com.jingdong.common.web.uibinder.IWebUiBinder;

/* loaded from: classes12.dex */
public class JSJDMtaUtils extends BaseWebComponent implements IJavaInterface {
    private static final String TAG = "JSJDMtaUtils";

    public JSJDMtaUtils(IWebUiBinder iWebUiBinder) {
        super(iWebUiBinder);
    }

    @Override // com.jingdong.common.web.javainterface.IJavaInterface
    public String getName() {
        return WebUiConstans.JavaInterfaceNames.JS_JDMTA;
    }
}

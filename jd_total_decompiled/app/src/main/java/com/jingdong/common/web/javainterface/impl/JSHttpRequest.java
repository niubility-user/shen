package com.jingdong.common.web.javainterface.impl;

import android.os.Handler;
import com.jingdong.common.web.BaseWebComponent;
import com.jingdong.common.web.WebUiConstans;
import com.jingdong.common.web.javainterface.IJavaInterface;
import com.jingdong.common.web.uibinder.IWebUiBinder;

/* loaded from: classes12.dex */
public final class JSHttpRequest extends BaseWebComponent implements IJavaInterface {
    private static final String TAG = "JSHttpRequest";
    private Handler handler;

    public JSHttpRequest(IWebUiBinder iWebUiBinder) {
        super(iWebUiBinder);
        this.handler = new Handler();
    }

    @Override // com.jingdong.common.web.javainterface.IJavaInterface
    public String getName() {
        return WebUiConstans.JavaInterfaceNames.JS_HTTPREQUEST;
    }
}

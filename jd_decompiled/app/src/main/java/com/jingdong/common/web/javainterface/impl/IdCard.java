package com.jingdong.common.web.javainterface.impl;

import android.webkit.JavascriptInterface;
import com.jingdong.common.web.BaseWebComponent;
import com.jingdong.common.web.WebUiConstans;
import com.jingdong.common.web.javainterface.IJavaInterface;
import com.jingdong.common.web.uibinder.IWebUiBinder;
import com.jingdong.common.web.util.WebUnifiedMtaUtil;

/* loaded from: classes12.dex */
public class IdCard extends BaseWebComponent implements IJavaInterface {
    private final String TAG;

    public IdCard(IWebUiBinder iWebUiBinder) {
        super(iWebUiBinder);
        this.TAG = IdCard.class.getSimpleName();
    }

    @Override // com.jingdong.common.web.javainterface.IJavaInterface
    public String getName() {
        return WebUiConstans.JavaInterfaceNames.IDCARD;
    }

    @JavascriptInterface
    public void onClose() {
        WebUnifiedMtaUtil.functionReport(this.webUiBinder, "IdCard_onClose");
        this.webUiBinder.finishUi();
    }

    public IdCard() {
        this.TAG = IdCard.class.getSimpleName();
    }
}

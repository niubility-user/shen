package com.jingdong.common.web.javainterface.impl;

import android.webkit.JavascriptInterface;
import com.jingdong.common.web.BaseWebComponent;
import com.jingdong.common.web.WebUiConstans;
import com.jingdong.common.web.entity.JsBridgeEntity;
import com.jingdong.common.web.javainterface.IJavaInterface;
import com.jingdong.common.web.uibinder.IWebUiBinder;
import com.jingdong.common.web.util.WebUnifiedMtaUtil;

/* loaded from: classes12.dex */
public final class MtaHelper extends BaseWebComponent implements IJavaInterface {
    private JsBridgeEntity jsBridgeEntity;

    public MtaHelper(IWebUiBinder iWebUiBinder) {
        super(iWebUiBinder);
        this.jsBridgeEntity = iWebUiBinder.getWebEntity().jsBridgeEntity;
    }

    @Override // com.jingdong.common.web.javainterface.IJavaInterface
    public String getName() {
        return WebUiConstans.JavaInterfaceNames.MTAHELPER;
    }

    @JavascriptInterface
    public void setMtaEventId(String str) {
        this.jsBridgeEntity.event_id = str;
        WebUnifiedMtaUtil.functionReport(this.webUiBinder, "MtaHelper_setMtaEventId");
    }

    public MtaHelper() {
    }
}

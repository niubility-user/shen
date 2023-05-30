package com.jingdong.common.web.javainterface.impl;

import android.webkit.JavascriptInterface;
import com.jingdong.common.utils.InteractionServicesUtil;
import com.jingdong.common.web.BaseWebComponent;
import com.jingdong.common.web.WebUiConstans;
import com.jingdong.common.web.javainterface.IJavaInterface;
import com.jingdong.common.web.uibinder.IWebUiBinder;
import com.jingdong.common.web.util.WebUnifiedMtaUtil;

/* loaded from: classes12.dex */
public final class AndroidSound extends BaseWebComponent implements IJavaInterface {
    private InteractionServicesUtil mInteractionServicesUtil;

    public AndroidSound(IWebUiBinder iWebUiBinder) {
        super(iWebUiBinder);
    }

    @Override // com.jingdong.common.web.javainterface.IJavaInterface
    public String getName() {
        return WebUiConstans.JavaInterfaceNames.ANDROID_SOUND;
    }

    @JavascriptInterface
    public double getSound() {
        WebUnifiedMtaUtil.permissionReport(this.webUiBinder, "AndroidSound-getSound");
        Object data = this.mInteractionServicesUtil.getData(1001);
        if (data != null) {
            return ((Double) data).doubleValue();
        }
        return 0.0d;
    }

    @JavascriptInterface
    public void startRecord(int i2) {
        InteractionServicesUtil interactionServicesUtil = InteractionServicesUtil.getInstance();
        this.mInteractionServicesUtil = interactionServicesUtil;
        interactionServicesUtil.startService(1001, i2);
        WebUnifiedMtaUtil.functionReport(this.webUiBinder, "AndroidSound-startRecord");
    }

    public void stopAndRelease() {
        InteractionServicesUtil interactionServicesUtil = this.mInteractionServicesUtil;
        if (interactionServicesUtil != null) {
            interactionServicesUtil.stopService(1001);
            this.mInteractionServicesUtil = null;
        }
    }

    @JavascriptInterface
    public void stopRecord() {
        WebUnifiedMtaUtil.functionReport(this.webUiBinder, "AndroidSound-stopRecord");
        this.mInteractionServicesUtil.stopService(1001);
    }

    public AndroidSound() {
    }
}

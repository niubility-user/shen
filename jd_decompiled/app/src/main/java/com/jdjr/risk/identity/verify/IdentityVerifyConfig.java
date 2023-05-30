package com.jdjr.risk.identity.verify;

import com.jd.aips.verify.config.FaceDazzleSdk;
import com.jd.aips.verify.config.FaceSdk;
import com.jd.aips.verify.config.IdCardNfcSdk;
import com.jd.aips.verify.config.IdCardOcrSdk;
import com.jd.aips.verify.config.VerifyConfig;

/* loaded from: classes18.dex */
public class IdentityVerifyConfig extends VerifyConfig {
    static final long serialVersionUID = -6741431211021270324L;
    public FaceDazzleSdk faceDazzleSdk;
    public FaceSdk faceSdk;
    public FaceSdk faceSdkDowngraded;
    public IdCardNfcSdk idcardNfcSdk;
    public IdCardOcrSdk idcardOcrSdk;
    public IdCardOcrSdk idcardOcrSdkDowngraded;
}

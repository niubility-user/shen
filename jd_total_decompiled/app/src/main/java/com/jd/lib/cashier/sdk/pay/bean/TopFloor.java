package com.jd.lib.cashier.sdk.pay.bean;

import com.jd.lib.cashier.sdk.core.model.ICheckNullObj;

/* loaded from: classes14.dex */
public class TopFloor implements ICheckNullObj {
    public DfPriceInfo dfPriceInfo;
    public HomeLetter homeLetter;
    public String triggerCountdownTime = "";
    public String moneyFlag = "";
    public String payprice = "";
    public String mobile = "";
    public String mobileTitle = "";
    public String countdownTime = "";
    public String subsidyCountDownTip = "";

    @Override // com.jd.lib.cashier.sdk.core.model.ICheckNullObj
    public void checkNullObjAndInit() {
        if (this.homeLetter == null) {
            this.homeLetter = new HomeLetter();
        }
        if (this.dfPriceInfo == null) {
            this.dfPriceInfo = new DfPriceInfo();
        }
        this.homeLetter.checkNullObjAndInit();
    }
}

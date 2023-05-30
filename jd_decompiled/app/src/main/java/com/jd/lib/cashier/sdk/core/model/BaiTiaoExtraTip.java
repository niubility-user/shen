package com.jd.lib.cashier.sdk.core.model;

/* loaded from: classes14.dex */
public class BaiTiaoExtraTip implements ICheckNullObj {
    public String extraTipStr = "";
    public CashierCommonPopConfig extraTipToast;

    @Override // com.jd.lib.cashier.sdk.core.model.ICheckNullObj
    public void checkNullObjAndInit() {
        if (this.extraTipToast == null) {
            this.extraTipToast = new CashierCommonPopConfig();
        }
        this.extraTipToast.checkNullObjAndInit();
    }
}

package com.jd.lib.cashier.sdk.pay.bean.baitiao;

import com.jd.lib.cashier.sdk.core.model.CashierCommonPopConfig;
import com.jd.lib.cashier.sdk.core.model.ICheckNullObj;

/* loaded from: classes14.dex */
public class PlanServiceMap implements ICheckNullObj {
    public String planServiceFeeStr;
    public CashierCommonPopConfig planServiceFeeToast;

    @Override // com.jd.lib.cashier.sdk.core.model.ICheckNullObj
    public void checkNullObjAndInit() {
        if (this.planServiceFeeToast == null) {
            this.planServiceFeeToast = new CashierCommonPopConfig();
        }
        this.planServiceFeeToast.checkNullObjAndInit();
    }
}

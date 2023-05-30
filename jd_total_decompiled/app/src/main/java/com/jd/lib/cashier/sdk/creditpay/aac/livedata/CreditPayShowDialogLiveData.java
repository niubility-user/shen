package com.jd.lib.cashier.sdk.creditpay.aac.livedata;

import androidx.lifecycle.LiveData;
import com.jd.lib.cashier.sdk.core.model.CashierCommonPopConfig;
import com.jd.lib.cashier.sdk.creditpay.bean.CreditPayEntity;
import com.jd.lib.cashier.sdk.h.a.b.c;

/* loaded from: classes14.dex */
public class CreditPayShowDialogLiveData extends LiveData<c> {
    public void a(CreditPayEntity creditPayEntity) {
        CashierCommonPopConfig cashierCommonPopConfig;
        if (creditPayEntity == null || (cashierCommonPopConfig = creditPayEntity.orderExceptionInfo) == null) {
            return;
        }
        c cVar = new c();
        cVar.a = cashierCommonPopConfig;
        postValue(cVar);
    }
}

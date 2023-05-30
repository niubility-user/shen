package com.jd.lib.cashier.sdk.pay.aac.livedata;

import android.text.TextUtils;
import androidx.lifecycle.LiveData;
import com.jd.lib.cashier.sdk.core.model.CashierCommonPopConfig;
import com.jd.lib.cashier.sdk.h.f.d;
import com.jd.lib.cashier.sdk.pay.aac.livedata.a.r;
import com.jd.lib.cashier.sdk.pay.bean.CashierPayEntity;
import com.jd.lib.cashier.sdk.pay.bean.TopFloor;

/* loaded from: classes14.dex */
public class PayTopFloorLiveData extends LiveData<r> {
    public void a(CashierPayEntity cashierPayEntity, d dVar, boolean z) {
        if (cashierPayEntity == null) {
            return;
        }
        r rVar = new r();
        TopFloor topFloor = cashierPayEntity.topFloor;
        if (topFloor != null) {
            rVar.a = topFloor;
        }
        CashierCommonPopConfig cashierCommonPopConfig = cashierPayEntity.countdownPopInfo;
        if (cashierCommonPopConfig != null) {
            rVar.f3792f = cashierCommonPopConfig;
        }
        if (dVar != null && !TextUtils.isEmpty(dVar.p)) {
            rVar.f3790c = dVar.p;
        }
        if (dVar != null && !TextUtils.isEmpty(dVar.q)) {
            rVar.d = dVar.q;
        }
        rVar.b = cashierPayEntity.graduallyPay;
        rVar.f3791e = cashierPayEntity.graduallyPayInfo;
        rVar.f3794h = z;
        rVar.f3793g = cashierPayEntity.tenBillionSubsidy;
        postValue(rVar);
    }
}

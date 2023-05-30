package com.jd.lib.cashier.sdk.quickpay.aac.livedata;

import androidx.lifecycle.LiveData;
import com.jd.lib.cashier.sdk.common.bean.CashierGetSuccessUrlEntity;
import com.jd.lib.cashier.sdk.i.a.b.b;

/* loaded from: classes14.dex */
public class CashierQuickPayJumpLiveData extends LiveData<b> {
    public void a() {
        b bVar = new b();
        bVar.a = "2";
        postValue(bVar);
    }

    public void b(CashierGetSuccessUrlEntity cashierGetSuccessUrlEntity, String str) {
        if (cashierGetSuccessUrlEntity != null) {
            b bVar = new b();
            bVar.b = cashierGetSuccessUrlEntity.payStatus;
            bVar.f3592c = cashierGetSuccessUrlEntity.successUrl;
            bVar.f3595g = cashierGetSuccessUrlEntity.xviewType;
            bVar.f3593e = cashierGetSuccessUrlEntity.successUrlType;
            bVar.f3594f = cashierGetSuccessUrlEntity.businessMapInfo;
            bVar.d = cashierGetSuccessUrlEntity.successToastText;
            bVar.a = "1";
            postValue(bVar);
        }
    }
}

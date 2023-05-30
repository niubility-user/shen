package com.jd.lib.cashier.sdk.freindpay.aac.livedata;

import androidx.lifecycle.LiveData;
import com.jd.lib.cashier.sdk.common.bean.CashierGetSuccessUrlEntity;
import com.jd.lib.cashier.sdk.f.a.b.c;

/* loaded from: classes14.dex */
public class CashierGetSuccessUrlLiveData extends LiveData<c> {
    public void a() {
        c cVar = new c();
        cVar.a = "-1";
        postValue(cVar);
    }

    public void b(String str, CashierGetSuccessUrlEntity cashierGetSuccessUrlEntity) {
        if (cashierGetSuccessUrlEntity != null) {
            c cVar = new c();
            cVar.a = cashierGetSuccessUrlEntity.code;
            cVar.f3344f = str;
            cVar.f3345g = cashierGetSuccessUrlEntity.xviewType;
            cVar.b = cashierGetSuccessUrlEntity.payStatus;
            cVar.f3342c = cashierGetSuccessUrlEntity.successUrl;
            cVar.f3343e = cashierGetSuccessUrlEntity.successUrlType;
            cVar.d = cashierGetSuccessUrlEntity.successToastText;
            postValue(cVar);
        }
    }
}

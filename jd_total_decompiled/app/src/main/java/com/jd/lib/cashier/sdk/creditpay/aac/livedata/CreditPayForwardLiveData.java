package com.jd.lib.cashier.sdk.creditpay.aac.livedata;

import androidx.lifecycle.LiveData;
import com.jd.lib.cashier.sdk.common.bean.CashierGetSuccessUrlEntity;
import com.jd.lib.cashier.sdk.e.a.b.b;

/* loaded from: classes14.dex */
public class CreditPayForwardLiveData extends LiveData<b> {
    public void a() {
        b bVar = new b();
        bVar.a = "2";
        postValue(bVar);
    }

    public void b(CashierGetSuccessUrlEntity cashierGetSuccessUrlEntity, String str, String str2) {
        if (cashierGetSuccessUrlEntity != null) {
            b bVar = new b();
            bVar.f3316f = cashierGetSuccessUrlEntity.xviewType;
            bVar.b = cashierGetSuccessUrlEntity.payStatus;
            bVar.f3314c = cashierGetSuccessUrlEntity.successUrl;
            bVar.f3315e = cashierGetSuccessUrlEntity.successUrlType;
            String str3 = cashierGetSuccessUrlEntity.businessMapInfo;
            bVar.d = cashierGetSuccessUrlEntity.successToastText;
            bVar.a = "1";
            postValue(bVar);
        }
    }
}

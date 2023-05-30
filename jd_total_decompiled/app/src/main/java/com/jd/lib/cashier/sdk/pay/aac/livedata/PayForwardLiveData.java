package com.jd.lib.cashier.sdk.pay.aac.livedata;

import androidx.lifecycle.LiveData;
import com.jd.lib.cashier.sdk.common.bean.CashierGetSuccessUrlEntity;
import com.jd.lib.cashier.sdk.pay.aac.livedata.a.h;

/* loaded from: classes14.dex */
public class PayForwardLiveData extends LiveData<h> {
    public void a(String str, String str2) {
        h hVar = new h();
        hVar.f3785h = str2;
        hVar.a = "3";
        postValue(hVar);
    }

    public void b() {
        h hVar = new h();
        hVar.a = "2";
        postValue(hVar);
    }

    public void c(CashierGetSuccessUrlEntity cashierGetSuccessUrlEntity, String str, String str2) {
        if (cashierGetSuccessUrlEntity != null) {
            h hVar = new h();
            hVar.f3783f = str;
            hVar.f3786i = cashierGetSuccessUrlEntity.xviewType;
            hVar.b = cashierGetSuccessUrlEntity.payStatus;
            hVar.f3781c = cashierGetSuccessUrlEntity.successUrl;
            hVar.f3782e = cashierGetSuccessUrlEntity.successUrlType;
            hVar.f3784g = cashierGetSuccessUrlEntity.businessMapInfo;
            hVar.d = cashierGetSuccessUrlEntity.successToastText;
            hVar.a = "1";
            postValue(hVar);
        }
    }
}

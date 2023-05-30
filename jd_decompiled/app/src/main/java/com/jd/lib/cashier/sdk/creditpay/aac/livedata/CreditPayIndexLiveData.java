package com.jd.lib.cashier.sdk.creditpay.aac.livedata;

import androidx.lifecycle.LiveData;
import com.jd.lib.cashier.sdk.creditpay.bean.CreditPayEntity;
import com.jd.lib.cashier.sdk.e.a.b.c;

/* loaded from: classes14.dex */
public class CreditPayIndexLiveData extends LiveData<c> {
    public void a(CreditPayEntity creditPayEntity) {
        c cVar = new c();
        cVar.a = 1024;
        cVar.b = creditPayEntity;
        postValue(cVar);
    }
}

package com.jd.lib.cashier.sdk.freindpay.aac.livedata;

import androidx.lifecycle.LiveData;
import com.jd.lib.cashier.sdk.core.model.CashierCommonPopConfig;
import com.jd.lib.cashier.sdk.f.a.b.a;

/* loaded from: classes14.dex */
public class CashierFriendPayFailLiveData extends LiveData<a> {
    public void a() {
        a aVar = new a();
        aVar.a = 8;
        postValue(aVar);
    }

    public void b() {
        a aVar = new a();
        aVar.a = 0;
        postValue(aVar);
    }

    public void c(CashierCommonPopConfig cashierCommonPopConfig) {
        if (cashierCommonPopConfig != null) {
            a aVar = new a();
            aVar.b = cashierCommonPopConfig;
            postValue(aVar);
        }
    }
}

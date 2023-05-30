package com.jd.lib.cashier.sdk.risk.aac.livedata;

import androidx.lifecycle.LiveData;
import com.jd.lib.cashier.sdk.j.a.b.a;

/* loaded from: classes14.dex */
public class CashierRiskLiveData extends LiveData<a> {
    public void a() {
        a aVar = new a();
        aVar.a = 2000;
        postValue(aVar);
    }

    public void b() {
        a aVar = new a();
        aVar.a = 3000;
        postValue(aVar);
    }

    public void c() {
        a aVar = new a();
        aVar.a = 1000;
        postValue(aVar);
    }
}

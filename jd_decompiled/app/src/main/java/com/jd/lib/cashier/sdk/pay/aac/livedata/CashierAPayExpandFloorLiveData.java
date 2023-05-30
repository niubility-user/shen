package com.jd.lib.cashier.sdk.pay.aac.livedata;

import androidx.lifecycle.LiveData;
import com.jd.lib.cashier.sdk.d.a.e.a;
import com.jd.lib.cashier.sdk.pay.aac.livedata.a.e;
import java.util.List;

/* loaded from: classes14.dex */
public class CashierAPayExpandFloorLiveData extends LiveData<e> {
    public void a(com.jd.lib.cashier.sdk.h.g.e eVar, List<a> list) {
        e eVar2 = new e();
        eVar2.b = eVar;
        eVar2.a = list;
        postValue(eVar2);
    }
}

package com.jd.lib.cashier.sdk.pay.aac.livedata;

import androidx.lifecycle.LiveData;
import com.jd.lib.cashier.sdk.d.a.e.a;
import com.jd.lib.cashier.sdk.h.g.q;
import com.jd.lib.cashier.sdk.pay.aac.livedata.a.f;
import com.jd.lib.cashier.sdk.pay.bean.Payment;
import java.util.List;

/* loaded from: classes14.dex */
public class CashierBPayDynamicExpandFloorLiveData extends LiveData<f> {
    public void a(q qVar, List<Payment> list, List<a> list2, List<a> list3) {
        f fVar = new f();
        fVar.a = list;
        fVar.d = qVar;
        fVar.b = list2;
        fVar.f3779c = list3;
        postValue(fVar);
    }
}

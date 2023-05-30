package com.jd.lib.cashier.sdk.pay.aac.livedata;

import androidx.lifecycle.LiveData;
import com.jd.lib.cashier.sdk.d.a.e.a;
import com.jd.lib.cashier.sdk.h.g.s;
import com.jd.lib.cashier.sdk.pay.aac.livedata.a.g;
import com.jd.lib.cashier.sdk.pay.bean.Payment;
import java.util.List;

/* loaded from: classes14.dex */
public class CashierBPayExpandFloorLiveData extends LiveData<g> {
    public void a(s sVar, List<Payment> list, List<a> list2, List<a> list3) {
        g gVar = new g();
        gVar.a = list;
        gVar.d = sVar;
        gVar.b = list2;
        gVar.f3780c = list3;
        postValue(gVar);
    }
}

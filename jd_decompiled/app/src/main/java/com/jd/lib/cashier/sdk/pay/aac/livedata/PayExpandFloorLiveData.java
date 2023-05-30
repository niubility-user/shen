package com.jd.lib.cashier.sdk.pay.aac.livedata;

import androidx.lifecycle.LiveData;
import com.jd.lib.cashier.sdk.d.a.e.a;
import com.jd.lib.cashier.sdk.h.g.z;
import com.jd.lib.cashier.sdk.pay.aac.livedata.a.q;
import java.util.List;

/* loaded from: classes14.dex */
public class PayExpandFloorLiveData extends LiveData<q> {
    public void a(z zVar, List<a> list) {
        q qVar = new q();
        qVar.b = zVar;
        qVar.a = list;
        postValue(qVar);
    }
}

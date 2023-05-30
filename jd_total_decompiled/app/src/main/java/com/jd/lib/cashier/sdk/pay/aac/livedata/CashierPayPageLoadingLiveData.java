package com.jd.lib.cashier.sdk.pay.aac.livedata;

import androidx.lifecycle.LiveData;
import com.jd.lib.cashier.sdk.h.a.b.b;

/* loaded from: classes14.dex */
public class CashierPayPageLoadingLiveData extends LiveData<b> {
    public void a() {
        b bVar = new b();
        bVar.a = 2;
        postValue(bVar);
    }

    public void b() {
        b bVar = new b();
        bVar.a = 1;
        postValue(bVar);
    }
}

package com.jd.lib.cashier.sdk.creditpay.aac.livedata;

import androidx.lifecycle.LiveData;
import com.jd.lib.cashier.sdk.e.a.b.d;

/* loaded from: classes14.dex */
public class CreditPayStateLiveData extends LiveData<d> {
    public void a(String str) {
        d dVar = new d();
        dVar.a = 2048;
        dVar.b = str;
        postValue(dVar);
    }

    public void b() {
        d dVar = new d();
        dVar.a = 1024;
        postValue(dVar);
    }

    public void c() {
        d dVar = new d();
        dVar.a = 4096;
        postValue(dVar);
    }
}

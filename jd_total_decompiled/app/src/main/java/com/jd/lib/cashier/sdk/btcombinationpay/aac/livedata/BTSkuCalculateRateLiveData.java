package com.jd.lib.cashier.sdk.btcombinationpay.aac.livedata;

import android.text.TextUtils;
import androidx.lifecycle.LiveData;
import com.jd.lib.cashier.sdk.a.a.b.a;
import com.jd.lib.cashier.sdk.btcombinationpay.bean.BTSkuCalculateEntity;

/* loaded from: classes14.dex */
public class BTSkuCalculateRateLiveData extends LiveData<a> {
    public void a() {
        a aVar = new a();
        aVar.a = 1003;
        postValue(aVar);
    }

    public void b(String str) {
        a aVar = new a();
        aVar.a = 1002;
        if (!TextUtils.isEmpty(str)) {
            aVar.b = str;
        }
        postValue(aVar);
    }

    public void c() {
        a aVar = new a();
        aVar.a = 1005;
        postValue(aVar);
    }

    public void d(BTSkuCalculateEntity bTSkuCalculateEntity) {
        a aVar = new a();
        aVar.a = 1000;
        aVar.f2790c = bTSkuCalculateEntity;
        postValue(aVar);
    }
}

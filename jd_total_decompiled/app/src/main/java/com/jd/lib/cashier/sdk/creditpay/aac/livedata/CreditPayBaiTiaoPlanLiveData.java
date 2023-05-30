package com.jd.lib.cashier.sdk.creditpay.aac.livedata;

import androidx.lifecycle.LiveData;
import com.jd.lib.cashier.sdk.e.a.b.a;
import com.jd.lib.cashier.sdk.pay.bean.BaiTiaoPayPlanResponse;

/* loaded from: classes14.dex */
public class CreditPayBaiTiaoPlanLiveData extends LiveData<a> {
    public synchronized void a() {
        a aVar = new a();
        aVar.a = 1024;
        postValue(aVar);
    }

    public synchronized void b(BaiTiaoPayPlanResponse baiTiaoPayPlanResponse) {
        if (baiTiaoPayPlanResponse != null) {
            a aVar = new a();
            aVar.a = 1024;
            aVar.b = "1312";
            postValue(aVar);
        }
    }

    public synchronized void c(BaiTiaoPayPlanResponse baiTiaoPayPlanResponse) {
        if (baiTiaoPayPlanResponse != null) {
            a aVar = new a();
            aVar.a = 2048;
            aVar.f3313c = baiTiaoPayPlanResponse;
            postValue(aVar);
        }
    }
}

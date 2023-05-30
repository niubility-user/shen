package com.jd.lib.cashier.sdk.quickpay.aac.livedata;

import androidx.lifecycle.LiveData;
import com.jd.lib.cashier.sdk.d.g.g.f;
import com.jd.lib.cashier.sdk.i.a.b.d;
import com.jd.lib.cashier.sdk.i.a.e.a;
import com.jd.lib.cashier.sdk.quickpay.bean.JDPayServiceEntity;
import com.jd.lib.cashier.sdk.quickpay.bean.WXPayServiceEntity;

/* loaded from: classes14.dex */
public class CashierQuickPayPayingLiveData extends LiveData<d> {
    public void a(a aVar, JDPayServiceEntity jDPayServiceEntity) {
        if (jDPayServiceEntity == null || aVar == null) {
            return;
        }
        d dVar = new d();
        dVar.d = aVar.r;
        dVar.a = f.JDPAY;
        dVar.f3597c = jDPayServiceEntity;
        postValue(dVar);
    }

    public void b(a aVar, WXPayServiceEntity wXPayServiceEntity) {
        if (wXPayServiceEntity == null || aVar == null) {
            return;
        }
        d dVar = new d();
        dVar.d = aVar.r;
        dVar.a = f.WEIXIN;
        dVar.b = wXPayServiceEntity.payInfo;
        postValue(dVar);
    }
}

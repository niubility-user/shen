package com.jd.lib.cashier.sdk.risk.aac.viewmodel;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.FragmentActivity;
import com.jd.lib.cashier.sdk.core.aac.AbsCashierViewModel;
import com.jd.lib.cashier.sdk.j.a.e.a;
import com.jd.lib.cashier.sdk.risk.aac.livedata.CashierRiskLiveData;

/* loaded from: classes14.dex */
public class CashierRiskViewModel extends AbsCashierViewModel<a> {
    private CashierRiskLiveData b;

    /* renamed from: c  reason: collision with root package name */
    private com.jd.lib.cashier.sdk.j.a.d.a f4199c;

    @Override // com.jd.lib.cashier.sdk.core.aac.AbsCashierViewModel
    /* renamed from: c  reason: merged with bridge method [inline-methods] */
    public a a() {
        return new a();
    }

    public void d(FragmentActivity fragmentActivity) {
        if (this.f4199c == null) {
            this.f4199c = new com.jd.lib.cashier.sdk.j.a.d.a();
        }
        this.f4199c.a(fragmentActivity, b());
    }

    public CashierRiskLiveData e() {
        if (this.b == null) {
            this.b = new CashierRiskLiveData();
        }
        return this.b;
    }

    public void f(Intent intent) {
        Bundle extras;
        if (intent == null || (extras = intent.getExtras()) == null) {
            return;
        }
        b().b = extras;
        b().f3633c = extras.getString("orderId");
        b().d = extras.getString("orderDate");
    }
}

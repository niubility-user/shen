package com.jd.lib.cashier.sdk.pay.handler;

import android.content.Intent;
import android.text.TextUtils;
import androidx.lifecycle.ViewModelProviders;
import com.jd.lib.cashier.sdk.core.utils.g0;
import com.jd.lib.cashier.sdk.pay.aac.viewmodel.CashierPayViewModel;
import com.jd.lib.cashier.sdk.pay.view.CashierPayActivity;

/* loaded from: classes14.dex */
public class b implements d {

    /* renamed from: g  reason: collision with root package name */
    private CashierPayActivity f4163g;

    public b(CashierPayActivity cashierPayActivity) {
        this.f4163g = cashierPayActivity;
    }

    private void a() {
        com.jd.lib.cashier.sdk.d.g.g.d d = com.jd.lib.cashier.sdk.d.g.g.e.c().d(com.jd.lib.cashier.sdk.d.g.g.f.CYBERMONEY);
        com.jd.lib.cashier.sdk.d.g.g.b a = d != null ? d.a() : null;
        if (!(a instanceof com.jd.lib.cashier.sdk.d.g.a.c.a) || TextUtils.isEmpty(((com.jd.lib.cashier.sdk.d.g.a.c.a) a).f3244c)) {
            return;
        }
        c();
    }

    private void c() {
        if (g0.a(this.f4163g)) {
            ((CashierPayViewModel) ViewModelProviders.of(this.f4163g).get(CashierPayViewModel.class)).j(this.f4163g, true, "cyberMoney");
        }
    }

    private void f() {
        com.jd.lib.cashier.sdk.b.d.a.i(this.f4163g, com.jd.lib.cashier.sdk.d.g.g.f.CYBERMONEY);
    }

    @Override // com.jd.lib.cashier.sdk.pay.handler.d
    public void j(int i2, int i3, Intent intent) {
        if (10001 == i2) {
            a();
        } else if (intent == null || !"com.jd.cyberMoneyPayFail".equals(intent.getAction())) {
        } else {
            f();
            com.jd.lib.cashier.sdk.d.h.a.a("cyberMoneyPayResultFunction", "PayResultException", "CyberMoneyPayResultProxy.onReceivePayResult()", "requestCode = " + i2 + "\tresultCode = " + i3);
        }
    }

    @Override // com.jd.lib.cashier.sdk.d.d.a
    public void onDestroy() {
        if (this.f4163g != null) {
            this.f4163g = null;
        }
    }
}

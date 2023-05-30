package com.jd.lib.cashier.sdk.pay.handler;

import android.content.Intent;
import android.text.TextUtils;
import androidx.lifecycle.ViewModelProviders;
import com.jd.lib.cashier.sdk.core.utils.g0;
import com.jd.lib.cashier.sdk.pay.aac.viewmodel.CashierPayViewModel;
import com.jd.lib.cashier.sdk.pay.view.CashierPayActivity;

/* loaded from: classes14.dex */
public class g implements d {

    /* renamed from: g  reason: collision with root package name */
    private CashierPayActivity f4167g;

    public g(CashierPayActivity cashierPayActivity) {
        this.f4167g = cashierPayActivity;
    }

    private void a() {
        com.jd.lib.cashier.sdk.d.g.g.d d = com.jd.lib.cashier.sdk.d.g.g.e.c().d(com.jd.lib.cashier.sdk.d.g.g.f.OCTOPUS);
        com.jd.lib.cashier.sdk.d.g.g.b a = d != null ? d.a() : null;
        if (!(a instanceof com.jd.lib.cashier.sdk.d.g.f.c.a) || TextUtils.isEmpty(((com.jd.lib.cashier.sdk.d.g.f.c.a) a).f3282c)) {
            return;
        }
        c();
    }

    private void c() {
        if (g0.a(this.f4167g)) {
            ((CashierPayViewModel) ViewModelProviders.of(this.f4167g).get(CashierPayViewModel.class)).j(this.f4167g, true, "octopusPay");
        }
    }

    private void f() {
        com.jd.lib.cashier.sdk.b.d.a.i(this.f4167g, com.jd.lib.cashier.sdk.d.g.g.f.OCTOPUS);
    }

    @Override // com.jd.lib.cashier.sdk.pay.handler.d
    public void j(int i2, int i3, Intent intent) {
        if (10000 == i2) {
            a();
        } else if (intent == null || !"com.jd.octopusPayFail".equals(intent.getAction())) {
        } else {
            f();
            com.jd.lib.cashier.sdk.d.h.a.a("OctopusPayResultFunction", "PayResultException", "OctopusPayResultProxy.onReceivePayResult()", "requestCode = " + i2 + "\tresultCode = " + i3);
        }
    }

    @Override // com.jd.lib.cashier.sdk.d.d.a
    public void onDestroy() {
        if (this.f4167g != null) {
            this.f4167g = null;
        }
    }
}

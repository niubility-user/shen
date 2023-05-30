package com.jd.lib.cashier.sdk.creditpay.aac.impl;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import com.jd.lib.cashier.sdk.core.model.CashierCommonPopConfig;
import com.jd.lib.cashier.sdk.core.utils.g0;
import com.jd.lib.cashier.sdk.creditpay.aac.viewmodel.CashierCreditPayViewModel;
import com.jd.lib.cashier.sdk.creditpay.view.CashierCreditPayActivity;

/* loaded from: classes14.dex */
public class CreditPayShowDialogImpl implements c, Observer<com.jd.lib.cashier.sdk.h.a.b.c> {

    /* renamed from: g  reason: collision with root package name */
    private CashierCreditPayActivity f3106g;

    public CreditPayShowDialogImpl(CashierCreditPayActivity cashierCreditPayActivity) {
        this.f3106g = cashierCreditPayActivity;
    }

    @Override // androidx.lifecycle.Observer
    /* renamed from: a  reason: merged with bridge method [inline-methods] */
    public void onChanged(com.jd.lib.cashier.sdk.h.a.b.c cVar) {
        if (cVar == null || !g0.a(this.f3106g)) {
            return;
        }
        CashierCommonPopConfig cashierCommonPopConfig = cVar.b;
        if (cashierCommonPopConfig != null) {
            com.jd.lib.cashier.sdk.creditpay.dialog.a.a(this.f3106g, cashierCommonPopConfig);
            return;
        }
        CashierCommonPopConfig cashierCommonPopConfig2 = cVar.a;
        if (cashierCommonPopConfig2 != null) {
            com.jd.lib.cashier.sdk.creditpay.dialog.a.d(this.f3106g, cashierCommonPopConfig2);
        }
    }

    @Override // com.jd.lib.cashier.sdk.core.aac.d
    public void f(FragmentActivity fragmentActivity) {
        ((CashierCreditPayViewModel) ViewModelProviders.of(fragmentActivity).get(CashierCreditPayViewModel.class)).k().observe(fragmentActivity, this);
    }

    @Override // com.jd.lib.cashier.sdk.d.d.a
    public void onDestroy() {
        if (this.f3106g != null) {
            this.f3106g = null;
        }
    }
}

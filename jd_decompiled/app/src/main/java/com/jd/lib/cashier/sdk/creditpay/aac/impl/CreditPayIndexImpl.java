package com.jd.lib.cashier.sdk.creditpay.aac.impl;

import android.view.View;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import com.jd.lib.cashier.sdk.core.utils.g0;
import com.jd.lib.cashier.sdk.creditpay.aac.viewmodel.CashierCreditPayViewModel;
import com.jd.lib.cashier.sdk.creditpay.bean.CreditPayEntity;
import com.jd.lib.cashier.sdk.creditpay.dialog.CashierCreditPayDialogProxy;
import com.jd.lib.cashier.sdk.creditpay.view.CashierCreditPayActivity;

/* loaded from: classes14.dex */
public class CreditPayIndexImpl implements b, Observer<com.jd.lib.cashier.sdk.e.a.b.c> {

    /* renamed from: g  reason: collision with root package name */
    private CashierCreditPayActivity f3103g;

    /* renamed from: h  reason: collision with root package name */
    private com.jd.lib.cashier.sdk.e.a.c.a f3104h;

    /* renamed from: i  reason: collision with root package name */
    private CashierCreditPayDialogProxy f3105i;

    public CreditPayIndexImpl(CashierCreditPayActivity cashierCreditPayActivity, com.jd.lib.cashier.sdk.e.a.c.a aVar, View view) {
        this.f3103g = cashierCreditPayActivity;
        this.f3104h = aVar;
        this.f3105i = new CashierCreditPayDialogProxy(cashierCreditPayActivity, view);
    }

    private void a() {
        if (this.f3104h != null && g0.a(this.f3103g)) {
            CashierCreditPayViewModel cashierCreditPayViewModel = (CashierCreditPayViewModel) ViewModelProviders.of(this.f3103g).get(CashierCreditPayViewModel.class);
            this.f3104h.d(this.f3103g, cashierCreditPayViewModel.b().p, cashierCreditPayViewModel.b().d);
        }
        CashierCreditPayActivity cashierCreditPayActivity = this.f3103g;
        if (cashierCreditPayActivity != null) {
            cashierCreditPayActivity.finish();
        }
    }

    private void h(CreditPayEntity creditPayEntity) {
        CashierCreditPayDialogProxy cashierCreditPayDialogProxy = this.f3105i;
        if (cashierCreditPayDialogProxy != null) {
            cashierCreditPayDialogProxy.H(creditPayEntity);
            this.f3105i.R();
            this.f3105i.C();
        }
    }

    @Override // androidx.lifecycle.Observer
    /* renamed from: c  reason: merged with bridge method [inline-methods] */
    public void onChanged(com.jd.lib.cashier.sdk.e.a.b.c cVar) {
        if (cVar != null && cVar.a == 1024) {
            h(cVar.b);
        } else {
            a();
        }
    }

    @Override // com.jd.lib.cashier.sdk.core.aac.d
    public void f(FragmentActivity fragmentActivity) {
        if (g0.a(fragmentActivity)) {
            ((CashierCreditPayViewModel) ViewModelProviders.of(fragmentActivity).get(CashierCreditPayViewModel.class)).i().observe(fragmentActivity, this);
        }
    }

    @Override // com.jd.lib.cashier.sdk.core.aac.e
    public void onChangeSkin() {
        CashierCreditPayDialogProxy cashierCreditPayDialogProxy = this.f3105i;
        if (cashierCreditPayDialogProxy != null) {
            cashierCreditPayDialogProxy.onChangeSkin();
        }
    }

    @Override // com.jd.lib.cashier.sdk.d.d.a
    public void onDestroy() {
        if (this.f3103g != null) {
            this.f3103g = null;
        }
        CashierCreditPayDialogProxy cashierCreditPayDialogProxy = this.f3105i;
        if (cashierCreditPayDialogProxy != null) {
            cashierCreditPayDialogProxy.onDestroy();
            this.f3105i = null;
        }
    }
}

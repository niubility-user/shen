package com.jd.lib.cashier.sdk.creditpay.aac.impl;

import android.text.TextUtils;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import com.jd.lib.cashier.sdk.R;
import com.jd.lib.cashier.sdk.core.utils.f0;
import com.jd.lib.cashier.sdk.core.utils.g0;
import com.jd.lib.cashier.sdk.core.utils.m;
import com.jd.lib.cashier.sdk.core.utils.p;
import com.jd.lib.cashier.sdk.creditpay.aac.viewmodel.CashierCreditPayViewModel;
import com.jd.lib.cashier.sdk.creditpay.view.CashierCreditPayActivity;
import java.util.HashMap;

/* loaded from: classes14.dex */
public class CreditPayForwardImpl implements a, Observer<com.jd.lib.cashier.sdk.e.a.b.b> {

    /* renamed from: g  reason: collision with root package name */
    private CashierCreditPayActivity f3101g;

    /* renamed from: h  reason: collision with root package name */
    private com.jd.lib.cashier.sdk.e.a.c.a f3102h;

    public CreditPayForwardImpl(CashierCreditPayActivity cashierCreditPayActivity, com.jd.lib.cashier.sdk.e.a.c.a aVar) {
        this.f3101g = cashierCreditPayActivity;
        this.f3102h = aVar;
    }

    private void a(com.jd.lib.cashier.sdk.e.a.b.b bVar) {
        if (bVar == null) {
            return;
        }
        if (TextUtils.equals(bVar.b, "1")) {
            if (TextUtils.isEmpty(bVar.d)) {
                bVar.d = this.f3101g.getString(R.string.lib_cashier_sdk_pay_status_suc_toast);
            }
            if (TextUtils.equals(bVar.f3315e, "0")) {
                h(bVar.f3314c, "1", bVar.f3316f);
            } else if (TextUtils.equals(bVar.f3315e, "2")) {
                h(bVar.f3314c, "0", bVar.f3316f);
            } else {
                m(bVar.f3314c);
            }
            f0.c(bVar.d);
            return;
        }
        l();
    }

    private void c() {
        CashierCreditPayActivity cashierCreditPayActivity = this.f3101g;
        if (cashierCreditPayActivity != null) {
            cashierCreditPayActivity.finish();
        }
    }

    private void h(String str, String str2, String str3) {
        if (TextUtils.isEmpty(str) || !g0.a(this.f3101g)) {
            return;
        }
        HashMap hashMap = new HashMap();
        hashMap.put("url", str);
        hashMap.put("supportXView", str3);
        hashMap.put("statusBarHint", str2);
        hashMap.put("appId", ((CashierCreditPayViewModel) ViewModelProviders.of(this.f3101g).get(CashierCreditPayViewModel.class)).b().b);
        this.f3102h.c(this.f3101g, hashMap);
    }

    private void l() {
        if (g0.a(this.f3101g)) {
            CashierCreditPayViewModel cashierCreditPayViewModel = (CashierCreditPayViewModel) ViewModelProviders.of(this.f3101g).get(CashierCreditPayViewModel.class);
            this.f3102h.d(this.f3101g, cashierCreditPayViewModel.b().p, cashierCreditPayViewModel.b().d);
        }
    }

    private void m(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        p.a(this.f3101g, str);
    }

    @Override // com.jd.lib.cashier.sdk.core.aac.d
    public void f(FragmentActivity fragmentActivity) {
        if (g0.a(fragmentActivity)) {
            ((CashierCreditPayViewModel) ViewModelProviders.of(fragmentActivity).get(CashierCreditPayViewModel.class)).h().observe(fragmentActivity, this);
        }
    }

    @Override // androidx.lifecycle.Observer
    /* renamed from: n  reason: merged with bridge method [inline-methods] */
    public void onChanged(com.jd.lib.cashier.sdk.e.a.b.b bVar) {
        try {
            try {
                if (bVar != null) {
                    if (TextUtils.equals(bVar.a, "1")) {
                        a(bVar);
                    } else {
                        l();
                    }
                } else {
                    l();
                }
            } catch (Exception unused) {
                l();
            }
        } finally {
            c();
            m.f().c();
        }
    }

    @Override // com.jd.lib.cashier.sdk.d.d.a
    public void onDestroy() {
        if (this.f3101g != null) {
            this.f3101g = null;
        }
        if (this.f3102h != null) {
            this.f3102h = null;
        }
    }
}

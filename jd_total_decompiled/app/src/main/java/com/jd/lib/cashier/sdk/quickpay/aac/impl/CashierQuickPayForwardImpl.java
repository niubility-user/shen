package com.jd.lib.cashier.sdk.quickpay.aac.impl;

import android.text.TextUtils;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import com.jd.lib.cashier.sdk.R;
import com.jd.lib.cashier.sdk.core.utils.f0;
import com.jd.lib.cashier.sdk.core.utils.g0;
import com.jd.lib.cashier.sdk.core.utils.m;
import com.jd.lib.cashier.sdk.core.utils.r;
import com.jd.lib.cashier.sdk.quickpay.aac.viewmodel.CashierQuickPayViewModel;
import com.jd.lib.cashier.sdk.quickpay.view.CashierQuickPayActivity;
import java.util.HashMap;

/* loaded from: classes14.dex */
public class CashierQuickPayForwardImpl implements a, Observer<com.jd.lib.cashier.sdk.i.a.b.b> {

    /* renamed from: g  reason: collision with root package name */
    private CashierQuickPayActivity f4185g;

    /* renamed from: h  reason: collision with root package name */
    private com.jd.lib.cashier.sdk.i.a.c.a f4186h;

    public CashierQuickPayForwardImpl(CashierQuickPayActivity cashierQuickPayActivity, com.jd.lib.cashier.sdk.i.a.c.a aVar) {
        this.f4185g = cashierQuickPayActivity;
        this.f4186h = aVar;
    }

    private void a(com.jd.lib.cashier.sdk.i.a.b.b bVar) {
        if (bVar != null) {
            if (TextUtils.equals(bVar.b, "1")) {
                o(bVar);
                p(bVar.f3594f);
                return;
            }
            m();
        }
    }

    private void c() {
        CashierQuickPayActivity cashierQuickPayActivity = this.f4185g;
        if (cashierQuickPayActivity != null) {
            cashierQuickPayActivity.finish();
        }
    }

    private void h(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.f4186h.c(this.f4185g, str);
    }

    private void l(String str, String str2, String str3) {
        if (this.f4186h == null || !g0.a(this.f4185g)) {
            return;
        }
        HashMap hashMap = new HashMap();
        hashMap.put("url", str);
        hashMap.put("supportXView", str3);
        hashMap.put("statusBarHint", str2);
        hashMap.put("appId", ((CashierQuickPayViewModel) ViewModelProviders.of(this.f4185g).get(CashierQuickPayViewModel.class)).b().b);
        this.f4186h.d(this.f4185g, hashMap);
    }

    private void m() {
        com.jd.lib.cashier.sdk.i.a.c.a aVar = this.f4186h;
        if (aVar != null) {
            aVar.b(this.f4185g);
        }
    }

    private void o(com.jd.lib.cashier.sdk.i.a.b.b bVar) {
        if (bVar == null) {
            return;
        }
        if (TextUtils.isEmpty(bVar.d)) {
            bVar.d = this.f4185g.getString(R.string.lib_cashier_sdk_pay_status_suc_toast);
        }
        if (TextUtils.equals(bVar.f3593e, "0")) {
            l(bVar.f3592c, "1", bVar.f3595g);
        } else if (TextUtils.equals(bVar.f3593e, "2")) {
            l(bVar.f3592c, "0", bVar.f3595g);
        } else {
            h(bVar.f3592c);
        }
        f0.c(bVar.d);
    }

    private void p(String str) {
        com.jd.lib.cashier.sdk.d.g.b.a.a().g(this.f4185g);
        com.jd.lib.cashier.sdk.d.g.b.a.a().h(this.f4185g);
        com.jd.lib.cashier.sdk.d.g.b.a.a().j(this.f4185g, str);
    }

    @Override // com.jd.lib.cashier.sdk.core.aac.d
    public void f(FragmentActivity fragmentActivity) {
        if (g0.a(fragmentActivity)) {
            ((CashierQuickPayViewModel) ViewModelProviders.of(fragmentActivity).get(CashierQuickPayViewModel.class)).e().observe(fragmentActivity, this);
        }
    }

    @Override // androidx.lifecycle.Observer
    /* renamed from: n  reason: merged with bridge method [inline-methods] */
    public void onChanged(com.jd.lib.cashier.sdk.i.a.b.b bVar) {
        try {
            try {
                if (bVar != null) {
                    if (TextUtils.equals(bVar.a, "1")) {
                        a(bVar);
                    } else {
                        m();
                    }
                } else {
                    m();
                }
            } catch (Exception e2) {
                m();
                r.d("CashierQuickPayForwardImpl", e2.getMessage());
            }
        } finally {
            c();
            m.f().c();
        }
    }

    @Override // com.jd.lib.cashier.sdk.d.d.a
    public void onDestroy() {
        if (this.f4185g != null) {
            this.f4185g = null;
        }
        if (this.f4186h != null) {
            this.f4186h = null;
        }
    }
}

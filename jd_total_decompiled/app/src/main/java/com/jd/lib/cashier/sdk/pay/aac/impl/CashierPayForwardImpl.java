package com.jd.lib.cashier.sdk.pay.aac.impl;

import android.text.TextUtils;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import com.jd.cashier.app.jdlibcutter.protocol.stackmanager.PayTaskStackManager;
import com.jd.lib.cashier.sdk.R;
import com.jd.lib.cashier.sdk.core.utils.f0;
import com.jd.lib.cashier.sdk.core.utils.g0;
import com.jd.lib.cashier.sdk.core.utils.m;
import com.jd.lib.cashier.sdk.core.utils.p;
import com.jd.lib.cashier.sdk.core.utils.r;
import com.jd.lib.cashier.sdk.d.g.g.e;
import com.jd.lib.cashier.sdk.pay.aac.impl.e.d;
import com.jd.lib.cashier.sdk.pay.aac.livedata.a.h;
import com.jd.lib.cashier.sdk.pay.aac.viewmodel.CashierPayViewModel;
import com.jd.lib.cashier.sdk.pay.view.CashierPayActivity;
import java.util.HashMap;

/* loaded from: classes14.dex */
public class CashierPayForwardImpl implements d, Observer<h> {

    /* renamed from: g  reason: collision with root package name */
    private CashierPayActivity f3641g;

    /* renamed from: h  reason: collision with root package name */
    private com.jd.lib.cashier.sdk.h.a.c.a f3642h;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public class a implements com.jd.lib.cashier.sdk.b.d.b.a {
        a() {
        }

        @Override // com.jd.lib.cashier.sdk.b.d.b.a
        public void a(String str) {
            CashierPayForwardImpl.this.r();
            CashierPayForwardImpl.this.o();
        }

        @Override // com.jd.lib.cashier.sdk.b.d.b.a
        public void b(String str) {
            CashierPayForwardImpl.this.A("octopusPay");
        }
    }

    public CashierPayForwardImpl(CashierPayActivity cashierPayActivity, com.jd.lib.cashier.sdk.h.a.c.a aVar) {
        this.f3641g = cashierPayActivity;
        this.f3642h = aVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void A(String str) {
        if (!g0.a(this.f3641g) || TextUtils.isEmpty(str)) {
            return;
        }
        ((CashierPayViewModel) ViewModelProviders.of(this.f3641g).get(CashierPayViewModel.class)).j(this.f3641g, true, str);
    }

    private void B(String str) {
        com.jd.lib.cashier.sdk.d.g.b.a.a().g(this.f3641g);
        com.jd.lib.cashier.sdk.d.g.b.a.a().h(this.f3641g);
        com.jd.lib.cashier.sdk.d.g.b.a.a().j(this.f3641g, str);
    }

    private void n(String str, String str2) {
        if (g0.a(this.f3641g)) {
            com.jd.lib.cashier.sdk.h.e.a.d().K(this.f3641g, str2, str, ((CashierPayViewModel) ViewModelProviders.of(this.f3641g).get(CashierPayViewModel.class)).b().f3511e, m.f().i());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void o() {
        CashierPayActivity cashierPayActivity = this.f3641g;
        if (cashierPayActivity != null) {
            cashierPayActivity.finish();
        }
    }

    private void p(String str, String str2, String str3) {
        if (!TextUtils.isEmpty(str) && g0.a(this.f3641g)) {
            HashMap hashMap = new HashMap();
            hashMap.put("url", str);
            hashMap.put("supportXView", str3);
            hashMap.put("statusBarHint", str2);
            hashMap.put("appId", ((CashierPayViewModel) ViewModelProviders.of(this.f3641g).get(CashierPayViewModel.class)).b().b);
            this.f3642h.f(this.f3641g, hashMap);
        }
        PayTaskStackManager.removeCashierFriendPayDialogTask();
        PayTaskStackManager.removeCashierFriendPayTask();
        PayTaskStackManager.removeCashierPayTask();
    }

    private void q(String str) {
        p.h(this.f3641g, str);
        PayTaskStackManager.removeAllCashierTask();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void r() {
        this.f3642h.b(this.f3641g);
        PayTaskStackManager.removeAllCashierTask();
    }

    private void s(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.f3642h.c(this.f3641g, str);
        }
        PayTaskStackManager.removeAllCashierTask();
    }

    private String t() {
        com.jd.lib.cashier.sdk.d.g.g.d e2 = e.c().e();
        return (e2 == null || e2.a() == null) ? "" : e2.a().a;
    }

    private void v(h hVar) {
        if (hVar == null) {
            return;
        }
        if (TextUtils.isEmpty(hVar.d)) {
            hVar.d = this.f3641g.getString(R.string.lib_cashier_sdk_pay_status_suc_toast);
        }
        f0.c(hVar.d);
        if (TextUtils.equals(hVar.f3782e, "0")) {
            p(hVar.f3781c, "1", hVar.f3786i);
            o();
        } else if (TextUtils.equals(hVar.f3782e, "2")) {
            p(hVar.f3781c, "0", hVar.f3786i);
            o();
        } else {
            s(hVar.f3781c);
            o();
        }
    }

    private void w() {
        if (g0.a(this.f3641g)) {
            ((CashierPayViewModel) ViewModelProviders.of(this.f3641g).get(CashierPayViewModel.class)).k(this.f3641g);
        }
    }

    private void x(h hVar) {
        if (hVar != null && TextUtils.equals(hVar.f3783f, "octopusPay")) {
            com.jd.lib.cashier.sdk.b.d.a.a(this.f3641g, new a());
        }
    }

    private void y(h hVar) {
        if (hVar != null && TextUtils.equals(hVar.f3783f, "octopusPay")) {
            f0.a(this.f3641g, R.string.lib_cashier_sdk_pay_octopus_failure);
        }
    }

    private void z() {
        A(t());
    }

    @Override // com.jd.lib.cashier.sdk.core.aac.d
    public void f(FragmentActivity fragmentActivity) {
        if (g0.a(fragmentActivity)) {
            ((CashierPayViewModel) ViewModelProviders.of(fragmentActivity).get(CashierPayViewModel.class)).F().observe(fragmentActivity, this);
        }
    }

    public void m(h hVar) {
        if (hVar != null) {
            n(hVar.b, hVar.f3783f);
            if (TextUtils.equals(hVar.b, "1")) {
                v(hVar);
                B(hVar.f3784g);
            } else if (TextUtils.equals(hVar.b, "2")) {
                y(hVar);
            } else if (TextUtils.equals(hVar.b, "3")) {
                x(hVar);
            } else if (TextUtils.equals(hVar.b, "4")) {
                w();
            } else {
                y(hVar);
            }
        }
    }

    @Override // com.jd.lib.cashier.sdk.d.d.a
    public void onDestroy() {
        if (this.f3641g != null) {
            this.f3641g = null;
        }
        if (this.f3642h != null) {
            this.f3642h = null;
        }
    }

    @Override // com.jd.lib.cashier.sdk.d.d.b
    public void onResume() {
        if (g0.a(this.f3641g)) {
            CashierPayViewModel cashierPayViewModel = (CashierPayViewModel) ViewModelProviders.of(this.f3641g).get(CashierPayViewModel.class);
            if (cashierPayViewModel.b().H) {
                z();
                cashierPayViewModel.b().H = false;
            }
        }
    }

    @Override // androidx.lifecycle.Observer
    /* renamed from: u  reason: merged with bridge method [inline-methods] */
    public void onChanged(@Nullable h hVar) {
        try {
            if (hVar != null) {
                if (TextUtils.equals(hVar.a, "3")) {
                    q(hVar.f3785h);
                    o();
                } else if (TextUtils.equals(hVar.a, "1")) {
                    m(hVar);
                } else {
                    r();
                    o();
                }
            } else {
                r();
                o();
            }
        } catch (Exception e2) {
            r();
            o();
            r.d("CashierPayForwardImpl", e2.getMessage());
        }
    }
}

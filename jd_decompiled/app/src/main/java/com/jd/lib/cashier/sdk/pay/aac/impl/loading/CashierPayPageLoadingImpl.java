package com.jd.lib.cashier.sdk.pay.aac.impl.loading;

import android.view.ViewGroup;
import android.view.Window;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import com.jd.lib.cashier.sdk.R;
import com.jd.lib.cashier.sdk.core.utils.g0;
import com.jd.lib.cashier.sdk.core.utils.q;
import com.jd.lib.cashier.sdk.d.d.a;
import com.jd.lib.cashier.sdk.h.a.b.b;
import com.jd.lib.cashier.sdk.pay.aac.viewmodel.CashierPayViewModel;
import com.jd.lib.cashier.sdk.pay.view.CashierPayActivity;

/* loaded from: classes14.dex */
public class CashierPayPageLoadingImpl implements Object, Observer<b>, a, Observer {

    /* renamed from: g  reason: collision with root package name */
    private ViewGroup f3765g;

    private void a() {
        ViewGroup viewGroup = this.f3765g;
        if (viewGroup != null) {
            q.b(viewGroup);
        }
    }

    private void l() {
        ViewGroup viewGroup = this.f3765g;
        if (viewGroup == null || viewGroup.getVisibility() != 8) {
            return;
        }
        q.c(this.f3765g);
    }

    @Override // androidx.lifecycle.Observer
    /* renamed from: c  reason: merged with bridge method [inline-methods] */
    public void onChanged(b bVar) {
        if (bVar != null) {
            int i2 = bVar.a;
            if (i2 == 1) {
                l();
                return;
            } else if (i2 != 2) {
                return;
            } else {
                a();
                return;
            }
        }
        a();
    }

    public void f(FragmentActivity fragmentActivity) {
        if (g0.a(fragmentActivity) && (fragmentActivity instanceof CashierPayActivity)) {
            ((CashierPayViewModel) ViewModelProviders.of(fragmentActivity).get(CashierPayViewModel.class)).w().observe(fragmentActivity, this);
        }
    }

    public void h(Window window) {
        if (window != null) {
            this.f3765g = (ViewGroup) window.findViewById(R.id.lib_cashier_pay_state_loading);
        }
    }

    @Override // com.jd.lib.cashier.sdk.d.d.a
    public void onDestroy() {
        q.a(this.f3765g);
    }
}

package com.jd.lib.cashier.sdk.freindpaydialog.aac.impl;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import com.jd.lib.cashier.sdk.core.model.CashierCommonPopConfig;
import com.jd.lib.cashier.sdk.core.utils.g0;
import com.jd.lib.cashier.sdk.core.utils.o0;
import com.jd.lib.cashier.sdk.freindpaydialog.aac.viewmodel.FriendPayDialogViewModel;
import com.jd.lib.cashier.sdk.freindpaydialog.view.FriendPayDialogActivity;
import com.jd.lib.cashier.sdk.h.a.b.c;
import com.jd.lib.cashier.sdk.pay.aac.impl.e.e;

/* loaded from: classes14.dex */
public class CashierFriendPayShowDialogImpl implements e, Observer<c> {

    /* renamed from: h */
    public static final String f3428h = "CashierFriendPayShowDialogImpl";

    /* renamed from: g */
    private FriendPayDialogActivity f3429g;

    public CashierFriendPayShowDialogImpl(FriendPayDialogActivity friendPayDialogActivity) {
        this.f3429g = friendPayDialogActivity;
    }

    private void c(CashierCommonPopConfig cashierCommonPopConfig) {
        if (o0.a(f3428h + "commonDialog") || cashierCommonPopConfig == null) {
            return;
        }
        com.jd.lib.cashier.sdk.b.d.a.c(this.f3429g, cashierCommonPopConfig);
    }

    @Override // androidx.lifecycle.Observer
    /* renamed from: a */
    public void onChanged(c cVar) {
        if (cVar != null) {
            h(cVar);
        }
    }

    @Override // com.jd.lib.cashier.sdk.core.aac.d
    public void f(FragmentActivity fragmentActivity) {
        if (g0.a(fragmentActivity)) {
            ((FriendPayDialogViewModel) ViewModelProviders.of(fragmentActivity).get(FriendPayDialogViewModel.class)).g().observe(fragmentActivity, this);
        }
    }

    public void h(c cVar) {
        CashierCommonPopConfig cashierCommonPopConfig;
        if (cVar == null || (cashierCommonPopConfig = cVar.d) == null) {
            return;
        }
        c(cashierCommonPopConfig);
    }

    @Override // com.jd.lib.cashier.sdk.d.d.a
    public void onDestroy() {
        if (this.f3429g != null) {
            this.f3429g = null;
        }
    }
}

package com.jd.lib.cashier.sdk.freindpaydialog.aac.impl;

import android.text.TextUtils;
import android.view.ViewStub;
import android.view.Window;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import com.jd.cashier.app.jdlibcutter.protocol.stackmanager.PayTaskStackManager;
import com.jd.lib.cashier.sdk.R;
import com.jd.lib.cashier.sdk.b.d.b.c;
import com.jd.lib.cashier.sdk.core.utils.g0;
import com.jd.lib.cashier.sdk.core.utils.j0;
import com.jd.lib.cashier.sdk.core.utils.p;
import com.jd.lib.cashier.sdk.creditpay.ui.CashierCreditPayErrorView;
import com.jd.lib.cashier.sdk.freindpaydialog.aac.viewmodel.FriendPayDialogViewModel;
import com.jd.lib.cashier.sdk.freindpaydialog.view.FriendPayDialogActivity;
import org.jetbrains.annotations.Nullable;

/* loaded from: classes14.dex */
public class CashierFriendPayDialogFailImpl implements com.jd.lib.cashier.sdk.freindpaydialog.aac.impl.a, Observer<com.jd.lib.cashier.sdk.g.a.b.a> {

    /* renamed from: g */
    private ViewStub f3416g;

    /* renamed from: h */
    private CashierCreditPayErrorView f3417h;

    /* renamed from: i */
    private FriendPayDialogActivity f3418i;

    /* loaded from: classes14.dex */
    public class a implements c {
        final /* synthetic */ FriendPayDialogViewModel a;

        a(FriendPayDialogViewModel friendPayDialogViewModel) {
            CashierFriendPayDialogFailImpl.this = r1;
            this.a = friendPayDialogViewModel;
        }

        @Override // com.jd.lib.cashier.sdk.b.d.b.c
        public void a(@Nullable String str, @Nullable String str2) {
            com.jd.lib.cashier.sdk.h.e.a.d().h(CashierFriendPayDialogFailImpl.this.f3418i, this.a.b().b, 1000);
            if (!TextUtils.isEmpty(str2)) {
                p.a(CashierFriendPayDialogFailImpl.this.f3418i, str2);
            } else {
                p.m(CashierFriendPayDialogFailImpl.this.f3418i);
            }
            PayTaskStackManager.removeAllCashierTask();
        }

        @Override // com.jd.lib.cashier.sdk.b.d.b.c
        public void b(@Nullable String str, @Nullable String str2) {
            com.jd.lib.cashier.sdk.h.e.a.d().f(CashierFriendPayDialogFailImpl.this.f3418i, this.a.b().b, 1000);
            p.a(CashierFriendPayDialogFailImpl.this.f3418i, str2);
            PayTaskStackManager.removeAllCashierTask();
        }
    }

    public CashierFriendPayDialogFailImpl(FriendPayDialogActivity friendPayDialogActivity) {
        this.f3418i = friendPayDialogActivity;
    }

    private void c(com.jd.lib.cashier.sdk.g.a.b.a aVar) {
        if (aVar == null || aVar.b == null) {
            return;
        }
        FriendPayDialogViewModel friendPayDialogViewModel = (FriendPayDialogViewModel) ViewModelProviders.of(this.f3418i).get(FriendPayDialogViewModel.class);
        com.jd.lib.cashier.sdk.b.d.a.k(this.f3418i, aVar.b, new a(friendPayDialogViewModel));
        com.jd.lib.cashier.sdk.h.e.a.d().g(this.f3418i, friendPayDialogViewModel.b().b, 1000);
    }

    private void l(com.jd.lib.cashier.sdk.g.a.b.a aVar) {
        if (aVar != null) {
            int i2 = aVar.a;
            if (i2 == 0) {
                r();
            } else if (i2 != 8) {
            } else {
                m();
            }
        }
    }

    private void m() {
        j0.b(this.f3417h);
    }

    private void n() {
        ViewStub viewStub = this.f3416g;
        if (viewStub == null || this.f3417h != null) {
            return;
        }
        this.f3417h = (CashierCreditPayErrorView) viewStub.inflate();
    }

    private void p() {
        CashierCreditPayErrorView cashierCreditPayErrorView = this.f3417h;
        if (cashierCreditPayErrorView != null) {
            cashierCreditPayErrorView.onHandSkinMode();
        }
    }

    private void r() {
        n();
        CashierCreditPayErrorView cashierCreditPayErrorView = this.f3417h;
        if (cashierCreditPayErrorView == null) {
            return;
        }
        j0.d(cashierCreditPayErrorView);
        p();
    }

    @Override // com.jd.lib.cashier.sdk.core.aac.d
    public void f(FragmentActivity fragmentActivity) {
        if (g0.a(fragmentActivity)) {
            ((FriendPayDialogViewModel) ViewModelProviders.of(fragmentActivity).get(FriendPayDialogViewModel.class)).f().observe(fragmentActivity, this);
        }
    }

    @Override // com.jd.lib.cashier.sdk.core.aac.c
    public void h(Window window) {
        if (window != null) {
            this.f3416g = (ViewStub) window.findViewById(R.id.lib_cashier_friend_pay_dialog_error_view_stub);
        }
    }

    @Override // androidx.lifecycle.Observer
    /* renamed from: o */
    public void onChanged(@androidx.annotation.Nullable com.jd.lib.cashier.sdk.g.a.b.a aVar) {
        q(aVar);
    }

    @Override // com.jd.lib.cashier.sdk.core.aac.e
    public void onChangeSkin() {
        p();
    }

    @Override // com.jd.lib.cashier.sdk.d.d.a
    public void onDestroy() {
        if (this.f3418i != null) {
            this.f3418i = null;
        }
    }

    public void q(com.jd.lib.cashier.sdk.g.a.b.a aVar) {
        if (aVar != null) {
            l(aVar);
            c(aVar);
        }
    }
}

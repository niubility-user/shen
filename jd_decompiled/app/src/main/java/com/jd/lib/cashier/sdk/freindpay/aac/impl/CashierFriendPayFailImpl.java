package com.jd.lib.cashier.sdk.freindpay.aac.impl;

import android.text.TextUtils;
import android.view.View;
import android.view.ViewStub;
import android.view.Window;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import com.jd.cashier.app.jdlibcutter.protocol.stackmanager.PayTaskStackManager;
import com.jd.lib.cashier.sdk.R;
import com.jd.lib.cashier.sdk.core.utils.g0;
import com.jd.lib.cashier.sdk.core.utils.j0;
import com.jd.lib.cashier.sdk.core.utils.p;
import com.jd.lib.cashier.sdk.freindpay.aac.viewmodel.FriendPayViewModel;
import com.jd.lib.cashier.sdk.freindpay.view.CashierErrorView;
import com.jd.lib.cashier.sdk.freindpay.view.FriendPayActivity;
import org.jetbrains.annotations.Nullable;

/* loaded from: classes14.dex */
public class CashierFriendPayFailImpl implements com.jd.lib.cashier.sdk.freindpay.aac.impl.a, Observer<com.jd.lib.cashier.sdk.f.a.b.a> {

    /* renamed from: g */
    private boolean f3359g = false;

    /* renamed from: h */
    private ViewStub f3360h;

    /* renamed from: i */
    private CashierErrorView f3361i;

    /* renamed from: j */
    private FriendPayActivity f3362j;

    /* loaded from: classes14.dex */
    public class a implements com.jd.lib.cashier.sdk.b.d.b.c {
        final /* synthetic */ FriendPayViewModel a;

        a(FriendPayViewModel friendPayViewModel) {
            CashierFriendPayFailImpl.this = r1;
            this.a = friendPayViewModel;
        }

        @Override // com.jd.lib.cashier.sdk.b.d.b.c
        public void a(@Nullable String str, @Nullable String str2) {
            com.jd.lib.cashier.sdk.h.e.a.d().h(CashierFriendPayFailImpl.this.f3362j, this.a.b().b, 3000);
            if (!TextUtils.isEmpty(str2)) {
                p.a(CashierFriendPayFailImpl.this.f3362j, str2);
            } else {
                p.m(CashierFriendPayFailImpl.this.f3362j);
            }
            PayTaskStackManager.removeAllCashierTask();
        }

        @Override // com.jd.lib.cashier.sdk.b.d.b.c
        public void b(@Nullable String str, @Nullable String str2) {
            com.jd.lib.cashier.sdk.h.e.a.d().f(CashierFriendPayFailImpl.this.f3362j, this.a.b().b, 3000);
            p.a(CashierFriendPayFailImpl.this.f3362j, str2);
            PayTaskStackManager.removeAllCashierTask();
        }
    }

    /* loaded from: classes14.dex */
    public class b implements View.OnClickListener {
        b() {
            CashierFriendPayFailImpl.this = r1;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            CashierFriendPayFailImpl.this.m();
        }
    }

    /* loaded from: classes14.dex */
    public class c implements View.OnClickListener {
        c() {
            CashierFriendPayFailImpl.this = r1;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            CashierFriendPayFailImpl.this.n();
        }
    }

    public CashierFriendPayFailImpl(FriendPayActivity friendPayActivity) {
        this.f3362j = friendPayActivity;
    }

    public void m() {
        if (!g0.a(this.f3362j) || this.f3359g) {
            return;
        }
        this.f3359g = true;
        ((FriendPayViewModel) ViewModelProviders.of(this.f3362j).get(FriendPayViewModel.class)).d(this.f3362j);
    }

    public void n() {
        try {
            if (g0.a(this.f3362j)) {
                this.f3362j.w().b(this.f3362j);
            }
            PayTaskStackManager.removeAllCashierTask();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private void o(com.jd.lib.cashier.sdk.f.a.b.a aVar) {
        if (aVar == null || aVar.b == null) {
            return;
        }
        FriendPayViewModel friendPayViewModel = (FriendPayViewModel) ViewModelProviders.of(this.f3362j).get(FriendPayViewModel.class);
        com.jd.lib.cashier.sdk.b.d.a.k(this.f3362j, aVar.b, new a(friendPayViewModel));
        com.jd.lib.cashier.sdk.h.e.a.d().g(this.f3362j, friendPayViewModel.b().b, 3000);
    }

    private void p(com.jd.lib.cashier.sdk.f.a.b.a aVar) {
        if (aVar != null) {
            int i2 = aVar.a;
            if (i2 == 0) {
                v();
            } else if (i2 != 8) {
            } else {
                q();
            }
        }
    }

    private void q() {
        j0.b(this.f3361i);
    }

    private void r() {
        ViewStub viewStub = this.f3360h;
        if (viewStub == null || this.f3361i != null) {
            return;
        }
        this.f3361i = (CashierErrorView) viewStub.inflate();
    }

    private void t() {
        CashierErrorView cashierErrorView = this.f3361i;
        if (cashierErrorView != null) {
            cashierErrorView.onHandModeSkin();
        }
    }

    private void v() {
        r();
        CashierErrorView cashierErrorView = this.f3361i;
        if (cashierErrorView == null) {
            return;
        }
        j0.d(cashierErrorView);
        this.f3361i.setErrorButtonListener(new b());
        this.f3361i.setErrorOrderListButtonListener(new c());
        t();
    }

    @Override // com.jd.lib.cashier.sdk.core.aac.d
    public void f(FragmentActivity fragmentActivity) {
        if (g0.a(fragmentActivity)) {
            ((FriendPayViewModel) ViewModelProviders.of(fragmentActivity).get(FriendPayViewModel.class)).f().observe(fragmentActivity, this);
        }
    }

    @Override // com.jd.lib.cashier.sdk.core.aac.c
    public void h(Window window) {
        if (window != null) {
            this.f3360h = (ViewStub) window.findViewById(R.id.lib_cashier_friend_pay_error_view_stub);
        }
    }

    @Override // com.jd.lib.cashier.sdk.core.aac.e
    public void onChangeSkin() {
        t();
    }

    @Override // com.jd.lib.cashier.sdk.d.d.a
    public void onDestroy() {
        if (this.f3362j != null) {
            this.f3362j = null;
        }
    }

    @Override // androidx.lifecycle.Observer
    /* renamed from: s */
    public void onChanged(@androidx.annotation.Nullable com.jd.lib.cashier.sdk.f.a.b.a aVar) {
        this.f3359g = false;
        u(aVar);
    }

    public void u(com.jd.lib.cashier.sdk.f.a.b.a aVar) {
        if (aVar != null) {
            p(aVar);
            o(aVar);
        }
    }
}

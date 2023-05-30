package com.jd.lib.cashier.sdk.pay.aac.impl.channel;

import android.os.Bundle;
import android.text.TextUtils;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import com.jd.cashier.app.jdlibcutter.initialize.DependInitializer;
import com.jd.cashier.app.jdlibcutter.protocol.login.ILogin;
import com.jd.cashier.app.jdlibcutter.protocol.stackmanager.PayTaskStackManager;
import com.jd.lib.cashier.sdk.core.model.CashierCommonPopConfig;
import com.jd.lib.cashier.sdk.core.utils.g0;
import com.jd.lib.cashier.sdk.core.utils.o0;
import com.jd.lib.cashier.sdk.pay.aac.viewmodel.CashierPayViewModel;
import com.jd.lib.cashier.sdk.pay.view.CashierPayActivity;
import org.jetbrains.annotations.Nullable;

/* loaded from: classes14.dex */
public class CashierPayShowDialogImpl implements com.jd.lib.cashier.sdk.pay.aac.impl.e.e, Observer<com.jd.lib.cashier.sdk.h.a.b.c> {

    /* renamed from: h  reason: collision with root package name */
    public static final String f3654h = "CashierPayShowDialogImpl";

    /* renamed from: g  reason: collision with root package name */
    private CashierPayActivity f3655g;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public class a implements com.jd.lib.cashier.sdk.b.d.b.c {
        a() {
        }

        @Override // com.jd.lib.cashier.sdk.b.d.b.c
        public void a(@Nullable String str, @Nullable String str2) {
            com.jd.lib.cashier.sdk.h.e.a.d().i0(CashierPayShowDialogImpl.this.f3655g);
            PayTaskStackManager.removeAllCashierTask();
        }

        @Override // com.jd.lib.cashier.sdk.b.d.b.c
        public void b(@Nullable String str, @Nullable String str2) {
            if (g0.a(CashierPayShowDialogImpl.this.f3655g)) {
                ((CashierPayViewModel) ViewModelProviders.of(CashierPayShowDialogImpl.this.f3655g).get(CashierPayViewModel.class)).k(CashierPayShowDialogImpl.this.f3655g);
            }
            com.jd.lib.cashier.sdk.h.e.a.d().j0(CashierPayShowDialogImpl.this.f3655g);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public class b implements com.jd.lib.cashier.sdk.b.d.b.c {

        /* loaded from: classes14.dex */
        class a implements Runnable {
            a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                if (g0.a(CashierPayShowDialogImpl.this.f3655g)) {
                    ((CashierPayViewModel) ViewModelProviders.of(CashierPayShowDialogImpl.this.f3655g).get(CashierPayViewModel.class)).k(CashierPayShowDialogImpl.this.f3655g);
                }
            }
        }

        /* renamed from: com.jd.lib.cashier.sdk.pay.aac.impl.channel.CashierPayShowDialogImpl$b$b  reason: collision with other inner class name */
        /* loaded from: classes14.dex */
        class RunnableC0124b implements Runnable {
            RunnableC0124b(b bVar) {
            }

            @Override // java.lang.Runnable
            public void run() {
            }
        }

        b() {
        }

        @Override // com.jd.lib.cashier.sdk.b.d.b.c
        public void a(@Nullable String str, @Nullable String str2) {
            ILogin login = DependInitializer.getLogin();
            if (login != null) {
                login.logout();
                login.doLogin(CashierPayShowDialogImpl.this.f3655g, new Bundle(), new a(), new RunnableC0124b(this));
            }
        }

        @Override // com.jd.lib.cashier.sdk.b.d.b.c
        public void b(@Nullable String str, @Nullable String str2) {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public class c implements com.jd.lib.cashier.sdk.b.d.b.c {
        final /* synthetic */ CashierPayViewModel a;

        c(CashierPayViewModel cashierPayViewModel) {
            this.a = cashierPayViewModel;
        }

        @Override // com.jd.lib.cashier.sdk.b.d.b.c
        public void a(@Nullable String str, @Nullable String str2) {
            com.jd.lib.cashier.sdk.h.e.a.d().h(CashierPayShowDialogImpl.this.f3655g, this.a.b().f3511e, 1000);
            if (!TextUtils.isEmpty(str2)) {
                com.jd.lib.cashier.sdk.core.utils.p.a(CashierPayShowDialogImpl.this.f3655g, str2);
            } else {
                com.jd.lib.cashier.sdk.core.utils.p.m(CashierPayShowDialogImpl.this.f3655g);
            }
            PayTaskStackManager.removeAllCashierTask();
        }

        @Override // com.jd.lib.cashier.sdk.b.d.b.c
        public void b(@Nullable String str, @Nullable String str2) {
            com.jd.lib.cashier.sdk.h.e.a.d().f(CashierPayShowDialogImpl.this.f3655g, this.a.b().f3511e, 1000);
            com.jd.lib.cashier.sdk.core.utils.p.a(CashierPayShowDialogImpl.this.f3655g, str2);
            PayTaskStackManager.removeAllCashierTask();
        }
    }

    public CashierPayShowDialogImpl(CashierPayActivity cashierPayActivity) {
        this.f3655g = cashierPayActivity;
    }

    private void h(CashierCommonPopConfig cashierCommonPopConfig) {
        if (o0.a(f3654h + "commonDialog") || cashierCommonPopConfig == null) {
            return;
        }
        com.jd.lib.cashier.sdk.b.d.a.c(this.f3655g, cashierCommonPopConfig);
    }

    private void m(CashierCommonPopConfig cashierCommonPopConfig) {
        if (cashierCommonPopConfig == null || !g0.a(this.f3655g)) {
            return;
        }
        CashierPayViewModel cashierPayViewModel = (CashierPayViewModel) ViewModelProviders.of(this.f3655g).get(CashierPayViewModel.class);
        com.jd.lib.cashier.sdk.b.d.a.k(this.f3655g, cashierCommonPopConfig, new c(cashierPayViewModel));
        com.jd.lib.cashier.sdk.h.e.a.d().g(this.f3655g, cashierPayViewModel.b().f3511e, 1000);
    }

    private void n(CashierCommonPopConfig cashierCommonPopConfig) {
        if (cashierCommonPopConfig == null || !g0.a(this.f3655g)) {
            return;
        }
        com.jd.lib.cashier.sdk.b.d.a.k(this.f3655g, cashierCommonPopConfig, new b());
    }

    private void o(CashierCommonPopConfig cashierCommonPopConfig) {
        if (cashierCommonPopConfig == null || !g0.a(this.f3655g)) {
            return;
        }
        com.jd.lib.cashier.sdk.b.d.a.k(this.f3655g, cashierCommonPopConfig, new a());
        com.jd.lib.cashier.sdk.h.e.a.d().k0(this.f3655g, cashierCommonPopConfig.businessMap);
    }

    @Override // androidx.lifecycle.Observer
    /* renamed from: c  reason: merged with bridge method [inline-methods] */
    public void onChanged(com.jd.lib.cashier.sdk.h.a.b.c cVar) {
        if (cVar != null) {
            l(cVar);
        }
    }

    @Override // com.jd.lib.cashier.sdk.core.aac.d
    public void f(FragmentActivity fragmentActivity) {
        if (g0.a(fragmentActivity)) {
            ((CashierPayViewModel) ViewModelProviders.of(fragmentActivity).get(CashierPayViewModel.class)).G().observe(fragmentActivity, this);
        }
    }

    public void l(com.jd.lib.cashier.sdk.h.a.b.c cVar) {
        if (cVar != null) {
            CashierCommonPopConfig cashierCommonPopConfig = cVar.f3509e;
            if (cashierCommonPopConfig != null) {
                o(cashierCommonPopConfig);
                return;
            }
            CashierCommonPopConfig cashierCommonPopConfig2 = cVar.f3508c;
            if (cashierCommonPopConfig2 != null) {
                n(cashierCommonPopConfig2);
                return;
            }
            CashierCommonPopConfig cashierCommonPopConfig3 = cVar.a;
            if (cashierCommonPopConfig3 != null) {
                m(cashierCommonPopConfig3);
                return;
            }
            CashierCommonPopConfig cashierCommonPopConfig4 = cVar.b;
            if (cashierCommonPopConfig4 != null) {
                h(cashierCommonPopConfig4);
                return;
            }
            CashierCommonPopConfig cashierCommonPopConfig5 = cVar.d;
            if (cashierCommonPopConfig5 != null) {
                h(cashierCommonPopConfig5);
            }
        }
    }

    @Override // com.jd.lib.cashier.sdk.d.d.a
    public void onDestroy() {
        if (this.f3655g != null) {
            this.f3655g = null;
        }
    }
}

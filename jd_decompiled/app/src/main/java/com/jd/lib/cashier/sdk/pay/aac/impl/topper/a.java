package com.jd.lib.cashier.sdk.pay.aac.impl.topper;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import com.jd.lib.cashier.sdk.common.bean.GraduallyPayPopup;
import com.jd.lib.cashier.sdk.core.aac.d;
import com.jd.lib.cashier.sdk.core.utils.p;
import com.jd.lib.cashier.sdk.pay.aac.livedata.LargePaymentLiveData;
import com.jd.lib.cashier.sdk.pay.aac.livedata.a.n;
import com.jd.lib.cashier.sdk.pay.aac.viewmodel.CashierPayViewModel;
import com.jd.lib.cashier.sdk.pay.view.CashierPayActivity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* loaded from: classes14.dex */
public final class a implements d, com.jd.lib.cashier.sdk.d.d.a {

    /* renamed from: com.jd.lib.cashier.sdk.pay.aac.impl.topper.a$a  reason: collision with other inner class name */
    /* loaded from: classes14.dex */
    static final class C0132a<T> implements Observer<n> {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ FragmentActivity f3777g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ CashierPayViewModel f3778h;

        /* renamed from: com.jd.lib.cashier.sdk.pay.aac.impl.topper.a$a$a  reason: collision with other inner class name */
        /* loaded from: classes14.dex */
        public static final class C0133a implements com.jd.lib.cashier.sdk.b.d.b.a {
            final /* synthetic */ GraduallyPayPopup b;

            C0133a(GraduallyPayPopup graduallyPayPopup) {
                this.b = graduallyPayPopup;
            }

            @Override // com.jd.lib.cashier.sdk.b.d.b.a
            public void a(@Nullable String str) {
                String str2;
                com.jd.lib.cashier.sdk.h.e.a d = com.jd.lib.cashier.sdk.h.e.a.d();
                FragmentActivity fragmentActivity = C0132a.this.f3777g;
                GraduallyPayPopup graduallyPayPopup = this.b;
                if (graduallyPayPopup == null || (str2 = graduallyPayPopup.payRemain) == null) {
                    str2 = "";
                }
                d.d0(fragmentActivity, str2);
                p.a(C0132a.this.f3777g, str);
            }

            @Override // com.jd.lib.cashier.sdk.b.d.b.a
            public void b(@Nullable String str) {
                String str2;
                com.jd.lib.cashier.sdk.h.e.a d = com.jd.lib.cashier.sdk.h.e.a.d();
                FragmentActivity fragmentActivity = C0132a.this.f3777g;
                GraduallyPayPopup graduallyPayPopup = this.b;
                if (graduallyPayPopup == null || (str2 = graduallyPayPopup.payRemain) == null) {
                    str2 = "";
                }
                d.c0(fragmentActivity, str2);
                C0132a c0132a = C0132a.this;
                CashierPayViewModel.m(c0132a.f3778h, c0132a.f3777g, "1", null, 4, null);
            }
        }

        C0132a(FragmentActivity fragmentActivity, CashierPayViewModel cashierPayViewModel) {
            this.f3777g = fragmentActivity;
            this.f3778h = cashierPayViewModel;
        }

        @Override // androidx.lifecycle.Observer
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public final void onChanged(n nVar) {
            com.jd.lib.cashier.sdk.h.e.a.d().e0(this.f3777g);
            GraduallyPayPopup a = nVar != null ? nVar.a() : null;
            com.jd.lib.cashier.sdk.b.d.a.e(this.f3777g, a, new C0133a(a));
        }
    }

    @Override // com.jd.lib.cashier.sdk.core.aac.d
    public void f(@NotNull FragmentActivity fragmentActivity) {
        LargePaymentLiveData A;
        CashierPayActivity cashierPayActivity = (CashierPayActivity) (!(fragmentActivity instanceof CashierPayActivity) ? null : fragmentActivity);
        CashierPayViewModel x = cashierPayActivity != null ? cashierPayActivity.x() : null;
        if (x == null || (A = x.A()) == null) {
            return;
        }
        A.observe(fragmentActivity, new C0132a(fragmentActivity, x));
    }
}

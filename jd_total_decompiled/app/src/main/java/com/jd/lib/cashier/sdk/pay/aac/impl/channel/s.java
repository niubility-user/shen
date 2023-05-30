package com.jd.lib.cashier.sdk.pay.aac.impl.channel;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import com.jd.lib.cashier.sdk.R;
import com.jd.lib.cashier.sdk.core.ui.widget.PaymentTriggerView;
import com.jd.lib.cashier.sdk.pay.aac.livedata.OctopusRateLiveData;
import com.jd.lib.cashier.sdk.pay.aac.viewmodel.CashierPayViewModel;
import com.jd.lib.cashier.sdk.pay.bean.OctopusRateResponse;
import com.jd.lib.cashier.sdk.pay.bean.Payment;
import com.jd.lib.cashier.sdk.pay.view.CashierPayActivity;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* loaded from: classes14.dex */
public final class s implements com.jd.lib.cashier.sdk.core.aac.d, com.jd.lib.cashier.sdk.d.d.a {

    /* renamed from: g  reason: collision with root package name */
    private Function0<Unit> f3739g;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public static final class a<T> implements Observer<com.jd.lib.cashier.sdk.pay.aac.livedata.a.o> {

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ FragmentActivity f3741h;

        /* JADX INFO: Access modifiers changed from: package-private */
        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0002\u0010\u0002\n\u0002\b\u0004\u0010\u0004\u001a\u00020\u0000H\n\u00a2\u0006\u0004\b\u0001\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"", "invoke", "()V", "com/jd/lib/cashier/sdk/pay/aac/impl/channel/OctopusRatePaymentProxy$subscribe$1$1$1", "<anonymous>"}, k = 3, mv = {1, 4, 0})
        /* renamed from: com.jd.lib.cashier.sdk.pay.aac.impl.channel.s$a$a  reason: collision with other inner class name */
        /* loaded from: classes14.dex */
        public static final class C0131a extends Lambda implements Function0<Unit> {
            C0131a() {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2() {
                CashierPayViewModel x;
                FragmentActivity fragmentActivity = a.this.f3741h;
                if (!(fragmentActivity instanceof CashierPayActivity)) {
                    fragmentActivity = null;
                }
                CashierPayActivity cashierPayActivity = (CashierPayActivity) fragmentActivity;
                if (cashierPayActivity == null || (x = cashierPayActivity.x()) == null) {
                    return;
                }
                x.i((CashierPayActivity) a.this.f3741h);
            }
        }

        a(FragmentActivity fragmentActivity) {
            this.f3741h = fragmentActivity;
        }

        @Override // androidx.lifecycle.Observer
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public final void onChanged(com.jd.lib.cashier.sdk.pay.aac.livedata.a.o oVar) {
            OctopusRateResponse a;
            String str;
            CashierPayViewModel x;
            com.jd.lib.cashier.sdk.h.c.a b;
            if (oVar == null || (a = oVar.a()) == null) {
                return;
            }
            FragmentActivity fragmentActivity = this.f3741h;
            if (!(fragmentActivity instanceof CashierPayActivity)) {
                fragmentActivity = null;
            }
            CashierPayActivity cashierPayActivity = (CashierPayActivity) fragmentActivity;
            Payment payment = (cashierPayActivity == null || (x = cashierPayActivity.x()) == null || (b = x.b()) == null) ? null : b.O;
            if (payment == null || (str = payment.code) == null) {
                str = "";
            }
            if (com.jd.lib.cashier.sdk.h.h.g.m(str)) {
                if (a.getOctopusResult() == OctopusRateResponse.Result.SUCCESS) {
                    PaymentTriggerView.l((PaymentTriggerView) ((CashierPayActivity) this.f3741h).getWindow().findViewById(R.id.lib_cashier_payment_trigger_view), new com.jd.lib.cashier.sdk.core.ui.widget.e(a.getOrderPrice(), a.getExBuyPrice()), null, 2, null);
                    return;
                }
                com.jd.lib.cashier.sdk.b.i.f.a(this.f3741h, new C0131a());
                Function0 function0 = s.this.f3739g;
                if (function0 != null) {
                    Unit unit = (Unit) function0.invoke();
                }
            }
        }
    }

    public final void c(@NotNull FragmentActivity fragmentActivity, @Nullable Function0<Unit> function0) {
        CashierPayViewModel x;
        this.f3739g = function0;
        CashierPayActivity cashierPayActivity = (CashierPayActivity) (!(fragmentActivity instanceof CashierPayActivity) ? null : fragmentActivity);
        if (cashierPayActivity == null || (x = cashierPayActivity.x()) == null) {
            return;
        }
        x.i((CashierPayActivity) fragmentActivity);
    }

    @Override // com.jd.lib.cashier.sdk.core.aac.d
    public void f(@NotNull FragmentActivity fragmentActivity) {
        CashierPayViewModel x;
        OctopusRateLiveData B;
        CashierPayActivity cashierPayActivity = (CashierPayActivity) (!(fragmentActivity instanceof CashierPayActivity) ? null : fragmentActivity);
        if (cashierPayActivity == null || (x = cashierPayActivity.x()) == null || (B = x.B()) == null) {
            return;
        }
        B.observe(fragmentActivity, new a(fragmentActivity));
    }
}

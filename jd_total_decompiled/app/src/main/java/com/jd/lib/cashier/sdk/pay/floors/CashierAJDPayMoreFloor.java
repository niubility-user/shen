package com.jd.lib.cashier.sdk.pay.floors;

import android.content.Context;
import android.view.View;
import androidx.lifecycle.ViewModelProviders;
import com.jd.lib.cashier.sdk.R;
import com.jd.lib.cashier.sdk.core.commonfloor.floor.AbstractFloor;
import com.jd.lib.cashier.sdk.core.ui.widget.CashierAMoreChannelView;
import com.jd.lib.cashier.sdk.d.b.a;
import com.jd.lib.cashier.sdk.h.g.x;
import com.jd.lib.cashier.sdk.pay.aac.viewmodel.CashierPayViewModel;
import com.jd.lib.cashier.sdk.pay.bean.Payment;
import com.jd.lib.cashier.sdk.pay.bean.syncevent.ClickOtherJDPayItemEvent;
import com.jd.lib.cashier.sdk.pay.view.CashierPayActivity;

/* loaded from: classes14.dex */
public class CashierAJDPayMoreFloor extends AbstractFloor<com.jd.lib.cashier.sdk.h.d.a, com.jd.lib.cashier.sdk.h.g.a> {

    /* renamed from: i  reason: collision with root package name */
    private CashierAMoreChannelView f3975i;

    /* renamed from: j  reason: collision with root package name */
    private x f3976j;

    /* renamed from: k  reason: collision with root package name */
    private final Runnable f3977k;

    /* loaded from: classes14.dex */
    class a extends com.jd.lib.cashier.sdk.core.utils.b {
        a(long j2) {
            super(j2);
        }

        @Override // com.jd.lib.cashier.sdk.core.utils.b
        public void b(View view) {
            if (CashierAJDPayMoreFloor.this.f3977k != null) {
                CashierAJDPayMoreFloor.this.f3977k.run();
            }
        }
    }

    /* loaded from: classes14.dex */
    class b implements Runnable {
        b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (CashierAJDPayMoreFloor.this.f3975i == null || CashierAJDPayMoreFloor.this.f3976j == null) {
                return;
            }
            Context context = CashierAJDPayMoreFloor.this.getConvertView().getContext();
            if ((context instanceof CashierPayActivity ? ((CashierPayViewModel) ViewModelProviders.of((CashierPayActivity) context).get(CashierPayViewModel.class)).b().O : null) == CashierAJDPayMoreFloor.this.f3976j.a()) {
                return;
            }
            com.jd.lib.cashier.sdk.b.i.e.f("CLICK_OTHER_JD_PAYMENT_CHANNEL_ITEM", new ClickOtherJDPayItemEvent());
        }
    }

    public CashierAJDPayMoreFloor(View view) {
        super(view);
        this.f3977k = new b();
    }

    private void f(Payment payment) {
        Context context = getConvertView().getContext();
        if (payment == null || payment.hasPaymentExpo || payment.isSourceFromDialogSelected) {
            return;
        }
        payment.hasPaymentExpo = true;
        com.jd.lib.cashier.sdk.h.e.a.d().N(context);
    }

    private void g() {
        CashierAMoreChannelView cashierAMoreChannelView = this.f3975i;
        if (cashierAMoreChannelView != null) {
            cashierAMoreChannelView.onChangeSkin();
        }
    }

    private void i() {
        CashierAMoreChannelView cashierAMoreChannelView = this.f3975i;
        if (cashierAMoreChannelView != null) {
            cashierAMoreChannelView.c(a.EnumC0116a.RIGHT_ARROW);
        }
    }

    private void j(String str) {
        CashierAMoreChannelView cashierAMoreChannelView = this.f3975i;
        if (cashierAMoreChannelView != null) {
            cashierAMoreChannelView.e(str);
        }
    }

    private void k() {
        CashierAMoreChannelView cashierAMoreChannelView = this.f3975i;
        if (cashierAMoreChannelView != null) {
            cashierAMoreChannelView.f(a.b.FLOOR_BOTTOM);
        }
    }

    private void l() {
        CashierAMoreChannelView cashierAMoreChannelView = this.f3975i;
        if (cashierAMoreChannelView != null) {
            cashierAMoreChannelView.setVisibility(0);
        }
    }

    @Override // com.jd.lib.cashier.sdk.core.commonfloor.floor.AbstractFloor
    /* renamed from: h  reason: merged with bridge method [inline-methods] */
    public void b(com.jd.lib.cashier.sdk.h.d.a aVar, com.jd.lib.cashier.sdk.h.g.a aVar2) {
        if (aVar2 != null) {
            this.f3976j = aVar2;
            Payment a2 = aVar2.a();
            j(a2.channelName);
            l();
            i();
            k();
            g();
            f(a2);
        }
    }

    @Override // com.jd.lib.cashier.sdk.core.commonfloor.floor.AbstractFloor
    public void initView(View view) {
        if (this.f3975i == null) {
            CashierAMoreChannelView cashierAMoreChannelView = (CashierAMoreChannelView) getView(R.id.lib_cashier_sdk_a_jd_pay_more_floor_view);
            this.f3975i = cashierAMoreChannelView;
            cashierAMoreChannelView.setOnClickListener(new a(1000L));
        }
    }
}

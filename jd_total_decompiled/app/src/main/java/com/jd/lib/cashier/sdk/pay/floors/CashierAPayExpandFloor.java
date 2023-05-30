package com.jd.lib.cashier.sdk.pay.floors;

import android.content.Context;
import android.view.View;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import com.jd.lib.cashier.sdk.R;
import com.jd.lib.cashier.sdk.core.commonfloor.floor.AbstractFloor;
import com.jd.lib.cashier.sdk.core.ui.widget.CashierAMoreChannelView;
import com.jd.lib.cashier.sdk.core.utils.j0;
import com.jd.lib.cashier.sdk.d.b.a;
import com.jd.lib.cashier.sdk.pay.aac.viewmodel.CashierPayViewModel;
import com.jd.lib.cashier.sdk.pay.bean.CashierPayEntity;
import com.jd.lib.cashier.sdk.pay.view.CashierPayActivity;

/* loaded from: classes14.dex */
public class CashierAPayExpandFloor extends AbstractFloor<com.jd.lib.cashier.sdk.h.d.a, com.jd.lib.cashier.sdk.h.g.e> {

    /* renamed from: i  reason: collision with root package name */
    private CashierAMoreChannelView f4000i;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public class a extends com.jd.lib.cashier.sdk.core.utils.b {

        /* renamed from: j  reason: collision with root package name */
        final /* synthetic */ com.jd.lib.cashier.sdk.h.g.e f4001j;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        a(long j2, com.jd.lib.cashier.sdk.h.g.e eVar) {
            super(j2);
            this.f4001j = eVar;
        }

        @Override // com.jd.lib.cashier.sdk.core.utils.b
        public void b(View view) {
            Context context = CashierAPayExpandFloor.this.getConvertView().getContext();
            if (context instanceof CashierPayActivity) {
                FragmentActivity fragmentActivity = (FragmentActivity) context;
                CashierPayViewModel cashierPayViewModel = (CashierPayViewModel) ViewModelProviders.of(fragmentActivity).get(CashierPayViewModel.class);
                cashierPayViewModel.t().a(this.f4001j, com.jd.lib.cashier.sdk.h.b.e.f().g(cashierPayViewModel.b().K));
                CashierPayEntity cashierPayEntity = cashierPayViewModel.b().K;
                if (cashierPayEntity != null) {
                    com.jd.lib.cashier.sdk.h.e.a.d().E(fragmentActivity, cashierPayEntity.orderId, cashierPayEntity.thirdPaychannelFoldStrategyId, cashierPayEntity.buttonStatus);
                }
            }
        }
    }

    public CashierAPayExpandFloor(View view) {
        super(view);
    }

    private void d() {
        if (j0.a(this.f4000i)) {
            this.f4000i.onChangeSkin();
        }
    }

    private void f() {
        if (j0.a(this.f4000i)) {
            this.f4000i.c(a.EnumC0116a.DOWN_ARROW);
        }
    }

    private void g(String str) {
        CashierAMoreChannelView cashierAMoreChannelView = this.f4000i;
        if (cashierAMoreChannelView != null) {
            cashierAMoreChannelView.e(str);
        }
    }

    private void h(com.jd.lib.cashier.sdk.h.g.e eVar) {
        CashierAMoreChannelView cashierAMoreChannelView = this.f4000i;
        if (cashierAMoreChannelView == null) {
            return;
        }
        if (!j0.a(cashierAMoreChannelView)) {
            this.f4000i.setOnClickListener(null);
        } else {
            this.f4000i.setOnClickListener(new a(1000L, eVar));
        }
    }

    private void i(boolean z) {
        if (j0.a(this.f4000i)) {
            if (z) {
                this.f4000i.f(a.b.FLOOR_TOP_AND_BOTTOM);
            } else {
                this.f4000i.f(a.b.FLOOR_BOTTOM);
            }
        }
    }

    private void j() {
        CashierAMoreChannelView cashierAMoreChannelView = this.f4000i;
        if (cashierAMoreChannelView != null && cashierAMoreChannelView.a()) {
            this.f4000i.setVisibility(0);
            return;
        }
        CashierAMoreChannelView cashierAMoreChannelView2 = this.f4000i;
        if (cashierAMoreChannelView2 != null) {
            cashierAMoreChannelView2.setVisibility(8);
        }
    }

    @Override // com.jd.lib.cashier.sdk.core.commonfloor.floor.AbstractFloor
    /* renamed from: e  reason: merged with bridge method [inline-methods] */
    public void b(com.jd.lib.cashier.sdk.h.d.a aVar, com.jd.lib.cashier.sdk.h.g.e eVar) {
        if (eVar != null) {
            g(eVar.b);
            j();
            f();
            i(eVar.a);
            d();
            h(eVar);
        }
    }

    @Override // com.jd.lib.cashier.sdk.core.commonfloor.floor.AbstractFloor
    public void initView(View view) {
        if (this.f4000i == null) {
            this.f4000i = (CashierAMoreChannelView) getView(R.id.lib_cashier_sdk_a_pay_expand_floor_root);
        }
    }
}

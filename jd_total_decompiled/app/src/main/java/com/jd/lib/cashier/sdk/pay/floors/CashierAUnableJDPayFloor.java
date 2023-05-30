package com.jd.lib.cashier.sdk.pay.floors;

import android.view.View;
import com.jd.lib.cashier.sdk.R;
import com.jd.lib.cashier.sdk.core.commonfloor.floor.AbstractFloor;
import com.jd.lib.cashier.sdk.core.ui.widget.CashierAMoreChannelView;
import com.jd.lib.cashier.sdk.d.b.a;

/* loaded from: classes14.dex */
public class CashierAUnableJDPayFloor extends AbstractFloor<com.jd.lib.cashier.sdk.h.d.a, com.jd.lib.cashier.sdk.h.g.f> {

    /* renamed from: i  reason: collision with root package name */
    private CashierAMoreChannelView f4004i;

    public CashierAUnableJDPayFloor(View view) {
        super(view);
    }

    private void d() {
        CashierAMoreChannelView cashierAMoreChannelView = this.f4004i;
        if (cashierAMoreChannelView != null) {
            cashierAMoreChannelView.onChangeSkin();
        }
    }

    private void f() {
        CashierAMoreChannelView cashierAMoreChannelView = this.f4004i;
        if (cashierAMoreChannelView != null) {
            cashierAMoreChannelView.c(a.EnumC0116a.NONE);
        }
    }

    private void g() {
        if (this.f4004i != null) {
            this.f4004i.e(getConvertView().getContext().getResources().getString(R.string.lib_cashier_sdk_pay_unable_use_text));
        }
    }

    private void h() {
        CashierAMoreChannelView cashierAMoreChannelView = this.f4004i;
        if (cashierAMoreChannelView != null) {
            cashierAMoreChannelView.f(a.b.FLOOR_TOP_AND_BOTTOM);
        }
    }

    private void i() {
        CashierAMoreChannelView cashierAMoreChannelView = this.f4004i;
        if (cashierAMoreChannelView != null) {
            cashierAMoreChannelView.setVisibility(0);
        }
    }

    @Override // com.jd.lib.cashier.sdk.core.commonfloor.floor.AbstractFloor
    /* renamed from: e  reason: merged with bridge method [inline-methods] */
    public void b(com.jd.lib.cashier.sdk.h.d.a aVar, com.jd.lib.cashier.sdk.h.g.f fVar) {
        if (fVar != null) {
            g();
            i();
            f();
            h();
            d();
        }
    }

    @Override // com.jd.lib.cashier.sdk.core.commonfloor.floor.AbstractFloor
    public void initView(View view) {
        this.f4004i = (CashierAMoreChannelView) getView(R.id.lib_cashier_a_unable_jd_pay_view);
    }
}

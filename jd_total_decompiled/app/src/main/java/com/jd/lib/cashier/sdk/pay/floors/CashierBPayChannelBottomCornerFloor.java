package com.jd.lib.cashier.sdk.pay.floors;

import android.view.View;
import com.jd.lib.cashier.sdk.R;
import com.jd.lib.cashier.sdk.core.commonfloor.floor.AbstractFloor;

/* loaded from: classes14.dex */
public class CashierBPayChannelBottomCornerFloor extends AbstractFloor<com.jd.lib.cashier.sdk.h.d.a, com.jd.lib.cashier.sdk.h.g.j> {

    /* renamed from: i  reason: collision with root package name */
    private View f4031i;

    public CashierBPayChannelBottomCornerFloor(View view) {
        super(view);
    }

    @Override // com.jd.lib.cashier.sdk.core.commonfloor.floor.AbstractFloor
    /* renamed from: c  reason: merged with bridge method [inline-methods] */
    public void b(com.jd.lib.cashier.sdk.h.d.a aVar, com.jd.lib.cashier.sdk.h.g.j jVar) {
    }

    @Override // com.jd.lib.cashier.sdk.core.commonfloor.floor.AbstractFloor
    public void initView(View view) {
        if (this.f4031i == null) {
            this.f4031i = getView(R.id.lib_cashier_b_pay_channel_bottom_corner_floor_root);
        }
    }
}

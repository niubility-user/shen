package com.jd.lib.cashier.sdk.pay.floors;

import android.view.View;
import com.jd.lib.cashier.sdk.R;
import com.jd.lib.cashier.sdk.core.commonfloor.floor.AbstractFloor;
import com.jd.lib.cashier.sdk.h.g.l;

/* loaded from: classes14.dex */
public class CashierBPayChannelTopCornerFloor extends AbstractFloor<com.jd.lib.cashier.sdk.h.d.a, l> {

    /* renamed from: i  reason: collision with root package name */
    private View f4059i;

    public CashierBPayChannelTopCornerFloor(View view) {
        super(view);
    }

    @Override // com.jd.lib.cashier.sdk.core.commonfloor.floor.AbstractFloor
    /* renamed from: c  reason: merged with bridge method [inline-methods] */
    public void b(com.jd.lib.cashier.sdk.h.d.a aVar, l lVar) {
    }

    @Override // com.jd.lib.cashier.sdk.core.commonfloor.floor.AbstractFloor
    public void initView(View view) {
        if (this.f4059i == null) {
            this.f4059i = getView(R.id.lib_cashier_b_pay_channel_top_corner_floor_root);
        }
    }
}

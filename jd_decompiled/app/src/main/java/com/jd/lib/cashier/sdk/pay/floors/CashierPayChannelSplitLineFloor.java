package com.jd.lib.cashier.sdk.pay.floors;

import android.view.View;
import android.view.ViewGroup;
import com.jd.cashier.app.jdlibcutter.protocol.utils.DpiUtil;
import com.jd.lib.cashier.sdk.R;
import com.jd.lib.cashier.sdk.core.commonfloor.floor.AbstractFloor;
import com.jd.lib.cashier.sdk.h.g.w;

/* loaded from: classes14.dex */
public class CashierPayChannelSplitLineFloor extends AbstractFloor<com.jd.lib.cashier.sdk.h.d.a, w> {

    /* renamed from: i  reason: collision with root package name */
    private View f4127i;

    public CashierPayChannelSplitLineFloor(View view) {
        super(view);
    }

    @Override // com.jd.lib.cashier.sdk.core.commonfloor.floor.AbstractFloor
    /* renamed from: c  reason: merged with bridge method [inline-methods] */
    public void b(com.jd.lib.cashier.sdk.h.d.a aVar, w wVar) {
        View view = this.f4127i;
        if (view == null || wVar == null) {
            return;
        }
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        int dip2px = DpiUtil.dip2px(getConvertView().getContext(), wVar.a);
        if (layoutParams.height != dip2px) {
            layoutParams.height = dip2px;
            this.f4127i.setLayoutParams(layoutParams);
        }
    }

    @Override // com.jd.lib.cashier.sdk.core.commonfloor.floor.AbstractFloor
    public void initView(View view) {
        if (this.f4127i == null) {
            this.f4127i = getView(R.id.lib_cashier_pay_split_line_floor_root);
        }
    }
}

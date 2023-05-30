package com.jd.lib.cashier.sdk.pay.floors;

import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.jd.cashier.app.jdlibcutter.protocol.utils.DpiUtil;
import com.jd.lib.cashier.sdk.R;
import com.jd.lib.cashier.sdk.core.commonfloor.floor.AbstractFloor;
import com.jd.lib.cashier.sdk.pay.bean.Payment;
import com.jd.lib.cashier.sdk.pay.bean.syncevent.ClickOtherJDPayItemEvent;

/* loaded from: classes14.dex */
public class CashierBPayChannelBindCardMoreFloor extends AbstractFloor<com.jd.lib.cashier.sdk.h.d.a, com.jd.lib.cashier.sdk.h.g.h> {

    /* renamed from: i  reason: collision with root package name */
    private LinearLayout f4025i;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public class a extends com.jd.lib.cashier.sdk.core.utils.b {
        a(CashierBPayChannelBindCardMoreFloor cashierBPayChannelBindCardMoreFloor, long j2) {
            super(j2);
        }

        @Override // com.jd.lib.cashier.sdk.core.utils.b
        public void b(View view) {
            com.jd.lib.cashier.sdk.b.i.e.f("CLICK_OTHER_JD_PAYMENT_CHANNEL_ITEM", new ClickOtherJDPayItemEvent());
        }
    }

    public CashierBPayChannelBindCardMoreFloor(View view) {
        super(view);
    }

    private void d() {
        if (getConvertView() != null) {
            getConvertView().setOnClickListener(new a(this, 1200L));
        }
    }

    private void e(com.jd.lib.cashier.sdk.h.d.a aVar) {
        ViewGroup.LayoutParams layoutParams = this.f4025i.getLayoutParams();
        if (aVar != null) {
            Payment payment = aVar.f3523e;
            if (payment != null && com.jd.lib.cashier.sdk.h.h.g.j(payment, aVar.b)) {
                this.f4025i.setVisibility(0);
                layoutParams.height = DpiUtil.dip2px(getConvertView().getContext(), 72.0f);
                layoutParams.width = -1;
            } else {
                this.f4025i.setVisibility(8);
                layoutParams.height = 0;
                layoutParams.width = 0;
            }
        } else {
            this.f4025i.setVisibility(8);
            layoutParams.height = 0;
            layoutParams.width = 0;
        }
        this.f4025i.setLayoutParams(layoutParams);
    }

    @Override // com.jd.lib.cashier.sdk.core.commonfloor.floor.AbstractFloor
    /* renamed from: c  reason: merged with bridge method [inline-methods] */
    public void b(com.jd.lib.cashier.sdk.h.d.a aVar, com.jd.lib.cashier.sdk.h.g.h hVar) {
        if (hVar != null) {
            e(aVar);
            d();
        }
    }

    @Override // com.jd.lib.cashier.sdk.core.commonfloor.floor.AbstractFloor
    public void initView(View view) {
        if (this.f4025i == null) {
            this.f4025i = (LinearLayout) view.findViewById(R.id.lib_cashier_b_pay_channel_more_floor_root);
        }
    }
}

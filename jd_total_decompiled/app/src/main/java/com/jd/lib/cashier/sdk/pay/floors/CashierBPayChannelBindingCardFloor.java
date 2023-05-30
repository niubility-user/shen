package com.jd.lib.cashier.sdk.pay.floors;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import androidx.lifecycle.ViewModelProviders;
import com.jd.cashier.app.jdlibcutter.protocol.utils.DpiUtil;
import com.jd.lib.cashier.sdk.R;
import com.jd.lib.cashier.sdk.core.commonfloor.floor.AbstractFloor;
import com.jd.lib.cashier.sdk.core.ui.CashierBNewCardItemView;
import com.jd.lib.cashier.sdk.h.g.x;
import com.jd.lib.cashier.sdk.pay.aac.viewmodel.CashierPayViewModel;
import com.jd.lib.cashier.sdk.pay.bean.Payment;
import com.jd.lib.cashier.sdk.pay.bean.TopPriceMtaObject;
import com.jd.lib.cashier.sdk.pay.bean.syncevent.ClickPayChannelItemEvent;
import com.jd.lib.cashier.sdk.pay.bean.syncevent.UpdateHeaderFloorInfo;
import com.jd.lib.cashier.sdk.pay.view.CashierPayActivity;

/* loaded from: classes14.dex */
public class CashierBPayChannelBindingCardFloor extends AbstractFloor<com.jd.lib.cashier.sdk.h.d.a, com.jd.lib.cashier.sdk.h.g.i> {

    /* renamed from: i  reason: collision with root package name */
    private CashierBNewCardItemView f4026i;

    /* renamed from: j  reason: collision with root package name */
    private x f4027j;

    /* renamed from: k  reason: collision with root package name */
    private final Runnable f4028k;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public class a implements View.OnClickListener {
        a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (CashierBPayChannelBindingCardFloor.this.f4028k != null) {
                CashierBPayChannelBindingCardFloor.this.f4028k.run();
            }
        }
    }

    /* loaded from: classes14.dex */
    class b implements Runnable {
        b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (CashierBPayChannelBindingCardFloor.this.f4026i == null || CashierBPayChannelBindingCardFloor.this.f4027j == null) {
                return;
            }
            Context context = CashierBPayChannelBindingCardFloor.this.getConvertView().getContext();
            Payment a = CashierBPayChannelBindingCardFloor.this.f4027j.a();
            if ((context instanceof CashierPayActivity ? ((CashierPayViewModel) ViewModelProviders.of((CashierPayActivity) context).get(CashierPayViewModel.class)).b().O : null) != a && com.jd.lib.cashier.sdk.h.h.g.g(a.status)) {
                com.jd.lib.cashier.sdk.b.i.e.f("cashier_item_click", new ClickPayChannelItemEvent(a));
            }
        }
    }

    public CashierBPayChannelBindingCardFloor(View view) {
        super(view);
        this.f4028k = new b();
    }

    private void f(com.jd.lib.cashier.sdk.h.g.i iVar) {
        Payment a2 = iVar.a();
        Context context = getConvertView().getContext();
        String str = a2.preferentiaNum;
        com.jd.lib.cashier.sdk.pay.dialog.e eVar = a2.selectedCouponEntity;
        if (eVar != null) {
            str = eVar.getTitleName();
        }
        if (!TextUtils.isEmpty(str) && !a2.hasCouponExpo && !a2.isSourceFromDialogSelected) {
            a2.hasCouponExpo = true;
            com.jd.lib.cashier.sdk.h.e.a.d().M(context, a2.code, com.jd.lib.cashier.sdk.h.h.e.b(a2));
        }
        this.f4026i.d(str);
    }

    private void g(com.jd.lib.cashier.sdk.h.g.i iVar) {
        CashierBNewCardItemView cashierBNewCardItemView = this.f4026i;
        if (cashierBNewCardItemView == null || iVar == null) {
            return;
        }
        if (iVar.b) {
            cashierBNewCardItemView.setVisibility(4);
            return;
        }
        cashierBNewCardItemView.setVisibility(0);
        l(iVar);
    }

    private void i() {
        this.f4026i.setOnClickListener(new a());
    }

    private void j(com.jd.lib.cashier.sdk.h.g.i iVar) {
        CashierBNewCardItemView cashierBNewCardItemView;
        if (iVar == null || (cashierBNewCardItemView = this.f4026i) == null) {
            return;
        }
        if (iVar.f3562c) {
            cashierBNewCardItemView.setPadding(DpiUtil.dip2px(getConvertView().getContext(), 8.0f), 0, DpiUtil.dip2px(getConvertView().getContext(), 4.0f), 0);
        } else {
            cashierBNewCardItemView.setPadding(DpiUtil.dip2px(getConvertView().getContext(), 12.0f), 0, 0, 0);
        }
    }

    private void k(Payment payment) {
        UpdateHeaderFloorInfo updateHeaderFloorInfo;
        if (payment == null || !payment.selected) {
            return;
        }
        TopPriceMtaObject topPriceMtaObject = new TopPriceMtaObject();
        com.jd.lib.cashier.sdk.pay.dialog.e eVar = payment.selectedCouponEntity;
        topPriceMtaObject.code = payment.code;
        topPriceMtaObject.uniqueChannelId = com.jd.lib.cashier.sdk.h.h.e.b(payment);
        if (eVar == null) {
            topPriceMtaObject.couponType = "0";
            updateHeaderFloorInfo = new UpdateHeaderFloorInfo(null, topPriceMtaObject);
        } else {
            topPriceMtaObject.couponType = eVar.getCutOffType();
            updateHeaderFloorInfo = new UpdateHeaderFloorInfo(eVar.getTopPriceAnimationInfo(), topPriceMtaObject);
        }
        com.jd.lib.cashier.sdk.b.i.e.f("update_top_floor_price_info", updateHeaderFloorInfo);
    }

    private void l(com.jd.lib.cashier.sdk.h.g.i iVar) {
        Payment a2 = iVar.a();
        CashierBNewCardItemView cashierBNewCardItemView = this.f4026i;
        if (cashierBNewCardItemView == null) {
            return;
        }
        cashierBNewCardItemView.setChecked(a2.selected);
        this.f4026i.g(a2.logo);
        if (!TextUtils.isEmpty(a2.channelDesc)) {
            this.f4026i.f(a2.channelDesc);
        } else {
            this.f4026i.f(a2.channelName);
        }
        f(iVar);
        k(a2);
        i();
    }

    private void m(com.jd.lib.cashier.sdk.h.d.a aVar, x xVar) {
        ViewGroup.LayoutParams layoutParams = this.f4026i.getLayoutParams();
        if (aVar != null && xVar != null) {
            Payment payment = aVar.f3523e;
            if (payment != null && com.jd.lib.cashier.sdk.h.h.g.j(payment, aVar.b)) {
                this.f4026i.setVisibility(0);
                layoutParams.height = -1;
                layoutParams.width = -1;
            } else {
                this.f4026i.setVisibility(8);
                layoutParams.height = 0;
                layoutParams.width = 0;
            }
        } else {
            this.f4026i.setVisibility(8);
            layoutParams.height = 0;
            layoutParams.width = 0;
        }
        this.f4026i.setLayoutParams(layoutParams);
    }

    @Override // com.jd.lib.cashier.sdk.core.commonfloor.floor.AbstractFloor
    /* renamed from: h  reason: merged with bridge method [inline-methods] */
    public void b(com.jd.lib.cashier.sdk.h.d.a aVar, com.jd.lib.cashier.sdk.h.g.i iVar) {
        if (iVar != null) {
            this.f4027j = iVar;
            m(aVar, iVar);
            j(iVar);
            g(iVar);
        }
    }

    @Override // com.jd.lib.cashier.sdk.core.commonfloor.floor.AbstractFloor
    public void initView(View view) {
        if (this.f4026i == null) {
            this.f4026i = (CashierBNewCardItemView) view.findViewById(R.id.lib_cashier_sdk_b_new_card_view);
        }
    }
}

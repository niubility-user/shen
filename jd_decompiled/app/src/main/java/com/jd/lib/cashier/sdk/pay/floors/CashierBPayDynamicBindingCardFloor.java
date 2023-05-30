package com.jd.lib.cashier.sdk.pay.floors;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.lifecycle.ViewModelProviders;
import com.jd.cashier.app.jdlibcutter.initialize.DependInitializer;
import com.jd.cashier.app.jdlibcutter.protocol.callback.DynamicEventListener;
import com.jd.cashier.app.jdlibcutter.protocol.dynamic.IDynamic;
import com.jd.lib.cashier.sdk.R;
import com.jd.lib.cashier.sdk.core.commonfloor.floor.AbstractFloor;
import com.jd.lib.cashier.sdk.core.utils.j0;
import com.jd.lib.cashier.sdk.core.utils.o0;
import com.jd.lib.cashier.sdk.h.g.o;
import com.jd.lib.cashier.sdk.pay.aac.viewmodel.CashierPayViewModel;
import com.jd.lib.cashier.sdk.pay.bean.Payment;
import com.jd.lib.cashier.sdk.pay.bean.TopPriceMtaObject;
import com.jd.lib.cashier.sdk.pay.bean.dynamic.paychannel.CashierBPayBindingCardData;
import com.jd.lib.cashier.sdk.pay.bean.dynamic.templatedata.CashierBPayBindingCardTemplateData;
import com.jd.lib.cashier.sdk.pay.bean.syncevent.ClickOtherJDPayItemEvent;
import com.jd.lib.cashier.sdk.pay.bean.syncevent.ClickPayChannelItemEvent;
import com.jd.lib.cashier.sdk.pay.bean.syncevent.UpdateHeaderFloorInfo;
import com.jd.lib.cashier.sdk.pay.view.CashierPayActivity;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

/* loaded from: classes14.dex */
public class CashierBPayDynamicBindingCardFloor extends AbstractFloor<com.jd.lib.cashier.sdk.h.d.a, o> {

    /* renamed from: i  reason: collision with root package name */
    private LinearLayout f4076i;

    /* renamed from: j  reason: collision with root package name */
    private boolean f4077j;

    /* renamed from: k  reason: collision with root package name */
    private LinearLayout f4078k;

    /* renamed from: l  reason: collision with root package name */
    private ViewGroup f4079l;

    /* renamed from: m  reason: collision with root package name */
    private o f4080m;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public class a implements DynamicEventListener<JSONObject> {
        a() {
        }

        @Override // com.jd.cashier.app.jdlibcutter.protocol.callback.DynamicEventListener
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public Object onEvent(JSONObject jSONObject) {
            if (CashierBPayDynamicBindingCardFloor.this.f4080m != null) {
                CashierBPayDynamicBindingCardFloor cashierBPayDynamicBindingCardFloor = CashierBPayDynamicBindingCardFloor.this;
                cashierBPayDynamicBindingCardFloor.f(cashierBPayDynamicBindingCardFloor.f4080m.a, jSONObject);
                return "";
            }
            return "";
        }
    }

    public CashierBPayDynamicBindingCardFloor(View view) {
        super(view);
    }

    private void e(o oVar) {
        try {
            CashierBPayBindingCardTemplateData cashierBPayBindingCardTemplateData = oVar.b;
            if (cashierBPayBindingCardTemplateData != null && this.f4078k != null) {
                IDynamic dynamic = DependInitializer.getDynamic();
                JSONObject jSONObject = new JSONObject(DependInitializer.getJsonParser().toJsonString(cashierBPayBindingCardTemplateData));
                ViewGroup viewGroup = this.f4079l;
                if (viewGroup == null && dynamic != null) {
                    this.f4079l = dynamic.createDynamicContainer(getConvertView().getContext(), "pay", "BPayJDPayNewCardView", null, new a());
                    this.f4078k.removeAllViews();
                    this.f4078k.addView(this.f4079l);
                    this.f4077j = dynamic.asyncLoad(this.f4079l, jSONObject);
                } else if (dynamic != null && this.f4077j) {
                    dynamic.bindData(viewGroup, jSONObject);
                } else if (dynamic != null) {
                    this.f4077j = dynamic.asyncLoad(viewGroup, jSONObject);
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f(List<Payment> list, JSONObject jSONObject) {
        if (list == null || jSONObject == null) {
            return;
        }
        String optString = jSONObject.optString("fun");
        if (!TextUtils.isEmpty(optString)) {
            optString.hashCode();
            char c2 = '\uffff';
            switch (optString.hashCode()) {
                case -1624713709:
                    if (optString.equals("rightText_onClick")) {
                        c2 = 0;
                        break;
                    }
                    break;
                case -970745261:
                    if (optString.equals("selectSecondChannel")) {
                        c2 = 1;
                        break;
                    }
                    break;
                case 1525336911:
                    if (optString.equals("selectFirstChannel")) {
                        c2 = 2;
                        break;
                    }
                    break;
            }
            switch (c2) {
                case 0:
                    l();
                    return;
                case 1:
                    if (list.size() > 1) {
                        i(list.get(1));
                        return;
                    }
                    return;
                case 2:
                    if (list.size() > 0) {
                        i(list.get(0));
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
        com.jd.lib.cashier.sdk.d.h.b.a("CashierBPayDynamicBindingCardFloor.dispatchPayChannelTemplateEvent()", "function is invalid =" + optString);
    }

    private void g(o oVar) {
        if (oVar != null) {
            List<Payment> list = oVar.a;
            CashierBPayBindingCardTemplateData cashierBPayBindingCardTemplateData = oVar.b;
            if (cashierBPayBindingCardTemplateData == null || list == null || list.isEmpty()) {
                return;
            }
            ArrayList arrayList = new ArrayList(3);
            for (int i2 = 0; i2 < list.size(); i2++) {
                Payment payment = list.get(i2);
                if (payment != null) {
                    CashierBPayBindingCardData cashierBPayBindingCardData = new CashierBPayBindingCardData();
                    cashierBPayBindingCardData.logo = payment.logo;
                    if (!TextUtils.isEmpty(payment.channelDesc)) {
                        cashierBPayBindingCardData.title = payment.channelDesc;
                    } else {
                        cashierBPayBindingCardData.title = payment.channelName;
                    }
                    cashierBPayBindingCardData.selected = payment.selected;
                    String str = payment.preferentiaNum;
                    com.jd.lib.cashier.sdk.pay.dialog.e eVar = payment.selectedCouponEntity;
                    if (eVar != null) {
                        str = eVar.getTitleName();
                    }
                    cashierBPayBindingCardData.couponText = str;
                    arrayList.add(cashierBPayBindingCardData);
                }
            }
            if (arrayList.size() == 1) {
                arrayList.add(new CashierBPayBindingCardData());
            }
            CashierBPayBindingCardData cashierBPayBindingCardData2 = new CashierBPayBindingCardData();
            cashierBPayBindingCardData2.title = getConvertView().getContext().getString(R.string.lib_cashier_sdk_b_pay_bind_card_more_text);
            arrayList.add(cashierBPayBindingCardData2);
            cashierBPayBindingCardTemplateData.jdPayChannelList = arrayList;
        }
    }

    private void h(List<Payment> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        for (int i2 = 0; i2 < list.size(); i2++) {
            Payment payment = list.get(i2);
            if (payment != null) {
                Context context = getConvertView().getContext();
                String str = payment.preferentiaNum;
                com.jd.lib.cashier.sdk.pay.dialog.e eVar = payment.selectedCouponEntity;
                if (eVar != null) {
                    str = eVar.getTitleName();
                }
                if (!TextUtils.isEmpty(str) && !payment.hasCouponExpo && !payment.isSourceFromDialogSelected) {
                    payment.hasCouponExpo = true;
                    com.jd.lib.cashier.sdk.h.e.a.d().M(context, payment.code, com.jd.lib.cashier.sdk.h.h.e.b(payment));
                }
            }
        }
    }

    private void i(Payment payment) {
        if (payment == null) {
            return;
        }
        Context context = getConvertView().getContext();
        if ((context instanceof CashierPayActivity ? ((CashierPayViewModel) ViewModelProviders.of((CashierPayActivity) context).get(CashierPayViewModel.class)).b().O : null) != payment && com.jd.lib.cashier.sdk.h.h.g.g(payment.status)) {
            com.jd.lib.cashier.sdk.b.i.e.f("cashier_item_click", new ClickPayChannelItemEvent(payment));
        }
    }

    private void k(o oVar) {
        UpdateHeaderFloorInfo updateHeaderFloorInfo;
        List<Payment> list = oVar.a;
        if (list == null || list.isEmpty()) {
            return;
        }
        for (int i2 = 0; i2 < list.size(); i2++) {
            Payment payment = list.get(i2);
            if (payment != null && payment.selected) {
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
                return;
            }
        }
    }

    private void l() {
        if (o0.a("showJDWholePaymentDialogCashierBPayDynamicBindingCardFloor")) {
            return;
        }
        com.jd.lib.cashier.sdk.b.i.e.f("CLICK_OTHER_JD_PAYMENT_CHANNEL_ITEM", new ClickOtherJDPayItemEvent());
    }

    private void m(com.jd.lib.cashier.sdk.h.d.a aVar) {
        LinearLayout linearLayout = this.f4076i;
        if (linearLayout != null) {
            ViewGroup.LayoutParams layoutParams = linearLayout.getLayoutParams();
            if (aVar != null) {
                Payment payment = aVar.f3523e;
                if (payment != null && com.jd.lib.cashier.sdk.h.h.g.j(payment, aVar.b)) {
                    this.f4076i.setVisibility(0);
                    layoutParams.height = -2;
                    layoutParams.width = -1;
                } else {
                    this.f4076i.setVisibility(8);
                    layoutParams.height = 0;
                    layoutParams.width = 0;
                }
            } else {
                this.f4076i.setVisibility(8);
                layoutParams.height = 0;
                layoutParams.width = 0;
            }
            this.f4076i.setLayoutParams(layoutParams);
        }
    }

    @Override // com.jd.lib.cashier.sdk.core.commonfloor.floor.AbstractFloor
    public void initView(View view) {
        if (this.f4078k == null) {
            this.f4078k = (LinearLayout) getView(R.id.lib_cashier_b_pay_dynamic_binding_card_floor_container);
        }
        if (this.f4076i == null) {
            this.f4076i = (LinearLayout) view.findViewById(R.id.lib_cashier_b_pay_dynamic_binding_card_floor_root);
        }
    }

    @Override // com.jd.lib.cashier.sdk.core.commonfloor.floor.AbstractFloor
    /* renamed from: j  reason: merged with bridge method [inline-methods] */
    public void b(com.jd.lib.cashier.sdk.h.d.a aVar, o oVar) {
        if (oVar != null) {
            this.f4080m = oVar;
            m(aVar);
            if (j0.a(this.f4076i)) {
                g(oVar);
                e(oVar);
                k(oVar);
                h(oVar.a);
            }
        }
    }
}

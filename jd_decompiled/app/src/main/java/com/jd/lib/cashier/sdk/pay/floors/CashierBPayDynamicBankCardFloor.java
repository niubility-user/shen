package com.jd.lib.cashier.sdk.pay.floors;

import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.jd.cashier.app.jdlibcutter.initialize.DependInitializer;
import com.jd.cashier.app.jdlibcutter.protocol.callback.DynamicEventListener;
import com.jd.cashier.app.jdlibcutter.protocol.dynamic.IDynamic;
import com.jd.cashier.app.jdlibcutter.protocol.utils.DpiUtil;
import com.jd.lib.cashier.sdk.R;
import com.jd.lib.cashier.sdk.core.utils.j0;
import com.jd.lib.cashier.sdk.core.utils.o0;
import com.jd.lib.cashier.sdk.h.g.n;
import com.jd.lib.cashier.sdk.h.g.x;
import com.jd.lib.cashier.sdk.pay.bean.Payment;
import com.jd.lib.cashier.sdk.pay.bean.dynamic.paychannel.CashierBPayChannelData;
import com.jd.lib.cashier.sdk.pay.bean.dynamic.payplan.CashierBPayPlanData;
import com.jd.lib.cashier.sdk.pay.bean.dynamic.templatedata.CashierBPayBankCardTemplateData;
import com.jd.lib.cashier.sdk.pay.bean.syncevent.ClickCreditCardBankItemEvent;
import com.jd.lib.cashier.sdk.pay.bean.syncevent.ClickOtherJDPayItemEvent;
import com.jingdong.common.cashiernative.CashierPayChannelCode;
import java.util.List;
import org.json.JSONObject;

/* loaded from: classes14.dex */
public class CashierBPayDynamicBankCardFloor extends AbstractCashierBPayDynamicChannelListFloor {

    /* renamed from: k  reason: collision with root package name */
    private LinearLayout f4072k;

    /* renamed from: l  reason: collision with root package name */
    private LinearLayout f4073l;

    /* renamed from: m  reason: collision with root package name */
    private boolean f4074m;

    /* renamed from: n  reason: collision with root package name */
    private boolean f4075n;
    private n o;
    private ViewGroup p;
    private ViewGroup q;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public class a implements DynamicEventListener<JSONObject> {
        a() {
        }

        @Override // com.jd.cashier.app.jdlibcutter.protocol.callback.DynamicEventListener
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public Object onEvent(JSONObject jSONObject) {
            if (CashierBPayDynamicBankCardFloor.this.o != null) {
                CashierBPayDynamicBankCardFloor cashierBPayDynamicBankCardFloor = CashierBPayDynamicBankCardFloor.this;
                cashierBPayDynamicBankCardFloor.D(cashierBPayDynamicBankCardFloor.o.a(), jSONObject);
                return "";
            }
            return "";
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public class b implements DynamicEventListener<JSONObject> {
        b() {
        }

        @Override // com.jd.cashier.app.jdlibcutter.protocol.callback.DynamicEventListener
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public Object onEvent(JSONObject jSONObject) {
            if (CashierBPayDynamicBankCardFloor.this.o != null) {
                CashierBPayDynamicBankCardFloor cashierBPayDynamicBankCardFloor = CashierBPayDynamicBankCardFloor.this;
                cashierBPayDynamicBankCardFloor.E(cashierBPayDynamicBankCardFloor.o.a(), jSONObject);
                return "";
            }
            return "";
        }
    }

    public CashierBPayDynamicBankCardFloor(View view) {
        super(view);
    }

    private void C(n nVar, boolean z) {
        LinearLayout linearLayout;
        CashierBPayBankCardTemplateData cashierBPayBankCardTemplateData = nVar.b;
        if (cashierBPayBankCardTemplateData != null) {
            try {
                if (cashierBPayBankCardTemplateData.payChannelData != null && this.f4073l != null) {
                    IDynamic dynamic = DependInitializer.getDynamic();
                    JSONObject jSONObject = new JSONObject(DependInitializer.getJsonParser().toJsonString(cashierBPayBankCardTemplateData.payChannelData));
                    ViewGroup viewGroup = this.p;
                    if (viewGroup == null && dynamic != null) {
                        this.p = dynamic.createDynamicContainer(getConvertView().getContext(), "pay", "BPayJDPayChannelView", null, new a());
                        this.f4073l.removeAllViews();
                        this.f4073l.addView(this.p);
                        this.f4074m = dynamic.asyncLoad(this.p, jSONObject);
                    } else if (dynamic != null && this.f4074m) {
                        dynamic.bindData(viewGroup, jSONObject);
                    } else if (dynamic != null) {
                        this.f4074m = dynamic.asyncLoad(viewGroup, jSONObject);
                    }
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        if (z && cashierBPayBankCardTemplateData != null) {
            try {
                if (cashierBPayBankCardTemplateData.payPlanData != null && this.f3952j != null) {
                    IDynamic dynamic2 = DependInitializer.getDynamic();
                    JSONObject jSONObject2 = new JSONObject(DependInitializer.getJsonParser().toJsonString(cashierBPayBankCardTemplateData.payPlanData));
                    if (this.q == null && dynamic2 != null) {
                        this.q = dynamic2.createDynamicContainer(getConvertView().getContext(), "pay", "BPayChannelExtView", null, new b());
                        this.f3952j.removeAllViews();
                        this.f3952j.addView(this.q);
                        this.f4075n = dynamic2.asyncLoad(this.q, jSONObject2);
                    } else if (dynamic2 != null && this.f4075n) {
                        this.f3952j.removeAllViews();
                        this.f3952j.addView(this.q);
                        dynamic2.bindData(this.q, jSONObject2);
                    } else if (dynamic2 != null) {
                        this.f3952j.removeAllViews();
                        this.f3952j.addView(this.q);
                        this.f4075n = dynamic2.asyncLoad(this.q, jSONObject2);
                    }
                }
            } catch (Exception e3) {
                e3.printStackTrace();
            }
        }
        if (z && (linearLayout = this.f3952j) != null && linearLayout.getVisibility() == 0) {
            getConvertView().setPadding(0, 0, 0, DpiUtil.dip2px(getConvertView().getContext(), 12.0f));
        } else {
            getConvertView().setPadding(0, 0, 0, 0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void D(Payment payment, JSONObject jSONObject) {
        if (payment == null || jSONObject == null) {
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
                case -425325038:
                    if (optString.equals("tipText_onClick")) {
                        c2 = 1;
                        break;
                    }
                    break;
                case 1315356499:
                    if (optString.equals("iconList_1_onClick")) {
                        c2 = 2;
                        break;
                    }
                    break;
            }
            switch (c2) {
                case 0:
                    K();
                    return;
                case 1:
                    t(payment);
                    return;
                case 2:
                    if (TextUtils.equals("GOUWUJIN", payment.code)) {
                        u(payment);
                        return;
                    } else if (TextUtils.equals(CashierPayChannelCode.JD_PAY_JXJ, payment.code)) {
                        w(payment);
                        return;
                    } else {
                        return;
                    }
                default:
                    return;
            }
        }
        com.jd.lib.cashier.sdk.d.h.b.a("CashierBPayDynamicBankCardFloor.dispatchPayChannelTemplateEvent()", "function is invalid =" + optString);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void E(Payment payment, JSONObject jSONObject) {
        if (payment == null || jSONObject == null) {
            return;
        }
        String optString = jSONObject.optString("fun");
        if (!TextUtils.isEmpty(optString)) {
            optString.hashCode();
            char c2 = '\uffff';
            switch (optString.hashCode()) {
                case 608854067:
                    if (optString.equals("onHeadTipIconClick")) {
                        c2 = 0;
                        break;
                    }
                    break;
                case 1163956933:
                    if (optString.equals("onBottomIconClick")) {
                        c2 = 1;
                        break;
                    }
                    break;
                case 1168863515:
                    if (optString.equals("onPlanViewClick")) {
                        c2 = 2;
                        break;
                    }
                    break;
                case 1529538243:
                    if (optString.equals("onSuperLinkTipClick")) {
                        c2 = 3;
                        break;
                    }
                    break;
                case 2096925462:
                    if (optString.equals("onItemClick")) {
                        c2 = 4;
                        break;
                    }
                    break;
            }
            switch (c2) {
                case 0:
                    s(payment);
                    return;
                case 1:
                    y(payment);
                    return;
                case 2:
                    l(jSONObject.optInt("clickPosition"), payment);
                    return;
                case 3:
                    r(payment);
                    return;
                case 4:
                    J(payment);
                    return;
                default:
                    return;
            }
        }
        com.jd.lib.cashier.sdk.d.h.b.a("CashierBPayDynamicBankCardFloor.dispatchPlanViewTemplateEvent()", "function is invalid =" + optString);
    }

    private synchronized boolean F(com.jd.lib.cashier.sdk.h.d.a aVar) {
        if (aVar != null) {
            List<Payment> list = aVar.d;
            if (list != null && !list.isEmpty()) {
                List<Payment> list2 = aVar.d;
                for (int i2 = 0; i2 < list2.size(); i2++) {
                    if (TextUtils.equals(list2.get(i2).code, "moreInfo")) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private void G(com.jd.lib.cashier.sdk.h.d.a aVar, n nVar, boolean z) {
        if (nVar == null || nVar.b == null) {
            return;
        }
        H(aVar, nVar);
        if (z) {
            I(nVar);
        }
    }

    private void H(com.jd.lib.cashier.sdk.h.d.a aVar, n nVar) {
        if (nVar == null || nVar.b == null) {
            return;
        }
        Payment a2 = nVar.a();
        boolean F = F(aVar);
        nVar.b.payChannelData = new CashierBPayChannelData();
        CashierBPayChannelData cashierBPayChannelData = nVar.b.payChannelData;
        cashierBPayChannelData.title = a2.channelName;
        d(nVar, cashierBPayChannelData);
        x(a2.moreInfoTip, nVar.b.payChannelData);
        if (F) {
            nVar.b.payChannelData.rightText = "\u66f4\u6362";
        } else {
            nVar.b.payChannelData.rightText = "";
        }
        e(nVar, nVar.b.payChannelData);
        if (TextUtils.equals("3", a2.status)) {
            CashierBPayChannelData cashierBPayChannelData2 = nVar.b.payChannelData;
            cashierBPayChannelData2.status = "0";
            p("0", "0", "", "", cashierBPayChannelData2);
            q("", "", "", "", nVar.b.payChannelData);
            return;
        }
        nVar.b.payChannelData.status = "1";
    }

    private void I(n nVar) {
        if (nVar == null || nVar.b == null) {
            return;
        }
        Payment a2 = nVar.a();
        nVar.b.payPlanData = new CashierBPayPlanData();
        g(a2, nVar.b.payPlanData);
    }

    private void J(Payment payment) {
        if (payment == null || o0.a("showCreditCardBankDialogCashierBPayDynamicBankCardFloor")) {
            return;
        }
        com.jd.lib.cashier.sdk.b.i.e.f("click_credit_card_bank_on_item", new ClickCreditCardBankItemEvent(payment));
    }

    private void K() {
        if (o0.a("showJDWholePaymentDialogCashierBPayDynamicBankCardFloor")) {
            return;
        }
        com.jd.lib.cashier.sdk.b.i.e.f("CLICK_OTHER_JD_PAYMENT_CHANNEL_ITEM", new ClickOtherJDPayItemEvent());
    }

    private void L(com.jd.lib.cashier.sdk.h.d.a aVar, n nVar) {
        if (this.f4073l == null || this.f3952j == null) {
            return;
        }
        if (aVar != null && nVar != null) {
            Payment payment = aVar.f3523e;
            if (payment != null && com.jd.lib.cashier.sdk.h.h.g.j(payment, aVar.b)) {
                this.f4072k.setVisibility(0);
                this.f4073l.setVisibility(0);
                this.f3952j.setVisibility(0);
                return;
            }
            this.f4072k.setVisibility(8);
            this.f4073l.setVisibility(8);
            this.f3952j.setVisibility(8);
            return;
        }
        this.f4072k.setVisibility(8);
        this.f4073l.setVisibility(8);
        this.f3952j.setVisibility(8);
    }

    @Override // com.jd.lib.cashier.sdk.pay.floors.AbstractCashierBPayDynamicChannelListFloor
    protected void i(View view) {
        if (this.f4072k == null) {
            this.f4072k = (LinearLayout) getView(R.id.lib_cashier_b_pay_dynamic_bank_card_floor_root);
        }
        if (this.f4073l == null) {
            this.f4073l = (LinearLayout) getView(R.id.lib_cashier_b_pay_dynamic_bank_card_channel_container);
        }
    }

    @Override // com.jd.lib.cashier.sdk.pay.floors.AbstractCashierBPayDynamicChannelListFloor
    public void n(com.jd.lib.cashier.sdk.h.d.a aVar, x xVar) {
        if (xVar instanceof n) {
            n nVar = (n) xVar;
            this.o = nVar;
            L(aVar, nVar);
            if (j0.a(this.f4072k)) {
                boolean f2 = f(xVar);
                G(aVar, nVar, f2);
                C(nVar, f2);
                v(xVar.a());
                m(xVar.a());
                j(xVar.a());
                h(xVar.a());
                c(xVar.a());
                if (f2) {
                    k(xVar);
                }
            }
        }
    }
}

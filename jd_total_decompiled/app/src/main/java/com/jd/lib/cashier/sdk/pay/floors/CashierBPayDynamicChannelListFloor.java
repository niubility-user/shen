package com.jd.lib.cashier.sdk.pay.floors;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import com.jd.cashier.app.jdlibcutter.initialize.DependInitializer;
import com.jd.cashier.app.jdlibcutter.protocol.callback.DynamicEventListener;
import com.jd.cashier.app.jdlibcutter.protocol.dynamic.IDynamic;
import com.jd.cashier.app.jdlibcutter.protocol.utils.DpiUtil;
import com.jd.lib.cashier.sdk.R;
import com.jd.lib.cashier.sdk.core.model.CashierCommonPopConfig;
import com.jd.lib.cashier.sdk.h.g.p;
import com.jd.lib.cashier.sdk.h.g.x;
import com.jd.lib.cashier.sdk.pay.aac.viewmodel.CashierPayViewModel;
import com.jd.lib.cashier.sdk.pay.bean.Payment;
import com.jd.lib.cashier.sdk.pay.bean.dynamic.paychannel.CashierBPayChannelListData;
import com.jd.lib.cashier.sdk.pay.bean.dynamic.payplan.CashierBPayPlanData;
import com.jd.lib.cashier.sdk.pay.bean.dynamic.templatedata.CashierBPayChannelListTemplateData;
import com.jd.lib.cashier.sdk.pay.bean.syncevent.ClickPayChannelItemEvent;
import com.jd.lib.cashier.sdk.pay.view.CashierPayActivity;
import com.jingdong.common.cashiernative.CashierPayChannelCode;
import org.json.JSONObject;

/* loaded from: classes14.dex */
public class CashierBPayDynamicChannelListFloor extends AbstractCashierBPayDynamicChannelListFloor {

    /* renamed from: k  reason: collision with root package name */
    private LinearLayout f4081k;

    /* renamed from: l  reason: collision with root package name */
    private LinearLayout f4082l;

    /* renamed from: m  reason: collision with root package name */
    private boolean f4083m;

    /* renamed from: n  reason: collision with root package name */
    private boolean f4084n;
    private p o;
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
            if (CashierBPayDynamicChannelListFloor.this.o != null) {
                CashierBPayDynamicChannelListFloor cashierBPayDynamicChannelListFloor = CashierBPayDynamicChannelListFloor.this;
                cashierBPayDynamicChannelListFloor.D(cashierBPayDynamicChannelListFloor.o.a(), jSONObject);
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
            if (CashierBPayDynamicChannelListFloor.this.o != null) {
                CashierBPayDynamicChannelListFloor cashierBPayDynamicChannelListFloor = CashierBPayDynamicChannelListFloor.this;
                cashierBPayDynamicChannelListFloor.E(cashierBPayDynamicChannelListFloor.o.a(), jSONObject);
                return "";
            }
            return "";
        }
    }

    public CashierBPayDynamicChannelListFloor(View view) {
        super(view);
    }

    private void C(p pVar, boolean z) {
        CashierBPayChannelListTemplateData cashierBPayChannelListTemplateData = pVar.b;
        if (cashierBPayChannelListTemplateData != null) {
            try {
                if (cashierBPayChannelListTemplateData.payChannelData != null && this.f4082l != null) {
                    IDynamic dynamic = DependInitializer.getDynamic();
                    JSONObject jSONObject = new JSONObject(DependInitializer.getJsonParser().toJsonString(cashierBPayChannelListTemplateData.payChannelData));
                    ViewGroup viewGroup = this.p;
                    if (viewGroup == null && dynamic != null) {
                        this.p = dynamic.createDynamicContainer(getConvertView().getContext(), "pay", "BPayChannelView", null, new a());
                        this.f4082l.removeAllViews();
                        this.f4082l.addView(this.p);
                        this.f4083m = dynamic.asyncLoad(this.p, jSONObject);
                    } else if (dynamic != null && this.f4083m) {
                        dynamic.bindData(viewGroup, jSONObject);
                    } else if (dynamic != null) {
                        this.f4083m = dynamic.asyncLoad(viewGroup, jSONObject);
                    }
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        if (z && cashierBPayChannelListTemplateData != null) {
            try {
                if (cashierBPayChannelListTemplateData.payPlanData != null && this.f3952j != null) {
                    IDynamic dynamic2 = DependInitializer.getDynamic();
                    JSONObject jSONObject2 = new JSONObject(DependInitializer.getJsonParser().toJsonString(cashierBPayChannelListTemplateData.payPlanData));
                    ViewGroup viewGroup2 = this.q;
                    if (viewGroup2 == null && dynamic2 != null) {
                        this.q = dynamic2.createDynamicContainer(getConvertView().getContext(), "pay", "BPayChannelExtView", null, new b());
                        this.f3952j.removeAllViews();
                        this.f3952j.addView(this.q);
                        this.f4084n = dynamic2.asyncLoad(this.q, jSONObject2);
                    } else if (dynamic2 != null && this.f4084n) {
                        dynamic2.bindData(viewGroup2, jSONObject2);
                    } else if (dynamic2 != null) {
                        this.f4084n = dynamic2.asyncLoad(viewGroup2, jSONObject2);
                    }
                }
            } catch (Exception e3) {
                e3.printStackTrace();
            }
        }
        if (z) {
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
                case -425325038:
                    if (optString.equals("tipText_onClick")) {
                        c2 = 0;
                        break;
                    }
                    break;
                case 1315356499:
                    if (optString.equals("iconList_1_onClick")) {
                        c2 = 1;
                        break;
                    }
                    break;
                case 1445764993:
                    if (optString.equals("channelViewOnClick")) {
                        c2 = 2;
                        break;
                    }
                    break;
                case 1554746029:
                    if (optString.equals("subTitleTipIconOnClick")) {
                        c2 = 3;
                        break;
                    }
                    break;
            }
            switch (c2) {
                case 0:
                    t(payment);
                    return;
                case 1:
                    if (TextUtils.equals("GOUWUJIN", payment.code)) {
                        u(payment);
                        return;
                    } else if (TextUtils.equals(CashierPayChannelCode.JD_PAY_JXJ, payment.code)) {
                        w(payment);
                        return;
                    } else {
                        return;
                    }
                case 2:
                    I(payment);
                    return;
                case 3:
                    J(payment);
                    return;
                default:
                    return;
            }
        }
        com.jd.lib.cashier.sdk.d.h.b.a("CashierBPayDynamicChannelListFloor.dispatchPayChannelTemplateEvent()", "function is invalid =" + optString);
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
                default:
                    return;
            }
        }
        com.jd.lib.cashier.sdk.d.h.b.a("CashierBPayDynamicChannelListFloor.dispatchPlanViewTemplateEvent()", "function is invalid =" + optString);
    }

    private void F(p pVar, boolean z) {
        if (pVar == null || pVar.b == null) {
            return;
        }
        G(pVar);
        if (z) {
            H(pVar);
        }
    }

    private void G(p pVar) {
        if (pVar == null || pVar.b == null) {
            return;
        }
        Payment a2 = pVar.a();
        pVar.b.payChannelData = new CashierBPayChannelListData();
        pVar.b.payChannelData.logo = a2.logo;
        if (!TextUtils.isEmpty(a2.channelDesc)) {
            pVar.b.payChannelData.title = a2.channelDesc;
        } else {
            pVar.b.payChannelData.title = a2.channelName;
        }
        pVar.b.payChannelData.subTitle = a2.tip;
        if (TextUtils.isEmpty(a2.extraInfo) && a2.toastModel == null) {
            pVar.b.payChannelData.subTitleTipIcon = "";
        } else {
            pVar.b.payChannelData.subTitleTipIcon = "1";
        }
        e(pVar, pVar.b.payChannelData);
        d(pVar, pVar.b.payChannelData);
        x(a2.moreInfoTip, pVar.b.payChannelData);
        if (TextUtils.equals("3", a2.status)) {
            CashierBPayChannelListData cashierBPayChannelListData = pVar.b.payChannelData;
            cashierBPayChannelListData.checkBoxType = "4";
            cashierBPayChannelListData.status = "0";
            p("0", "0", "", "", cashierBPayChannelListData);
            q("", "", "", "", pVar.b.payChannelData);
            return;
        }
        pVar.b.payChannelData.status = "1";
        if (TextUtils.equals("9", a2.status)) {
            pVar.b.payChannelData.checkBoxType = "";
        } else if (a2.selected) {
            pVar.b.payChannelData.checkBoxType = "2";
        } else {
            pVar.b.payChannelData.checkBoxType = "1";
        }
    }

    private void H(p pVar) {
        if (pVar == null || pVar.b == null) {
            return;
        }
        Payment a2 = pVar.a();
        pVar.b.payPlanData = new CashierBPayPlanData();
        g(a2, pVar.b.payPlanData);
    }

    private void I(Payment payment) {
        if (payment == null) {
            return;
        }
        Context context = getConvertView().getContext();
        if ((context instanceof CashierPayActivity ? ((CashierPayViewModel) ViewModelProviders.of((CashierPayActivity) context).get(CashierPayViewModel.class)).b().O : null) != payment && com.jd.lib.cashier.sdk.h.h.g.g(payment.status)) {
            com.jd.lib.cashier.sdk.b.i.e.f("cashier_item_click", new ClickPayChannelItemEvent(payment));
        }
    }

    private void J(Payment payment) {
        String str;
        if (payment == null || TextUtils.isEmpty(payment.tip)) {
            return;
        }
        String str2 = "";
        if (!TextUtils.isEmpty(payment.extraInfo)) {
            str2 = payment.extraInfo;
            str = "";
        } else {
            CashierCommonPopConfig cashierCommonPopConfig = payment.toastModel;
            if (cashierCommonPopConfig != null) {
                str2 = cashierCommonPopConfig.message;
                str = cashierCommonPopConfig.confirmBtn;
            } else {
                str = "";
            }
        }
        if (TextUtils.isEmpty(str2)) {
            return;
        }
        Context context = getConvertView().getContext();
        if (context instanceof FragmentActivity) {
            com.jd.lib.cashier.sdk.b.d.a.g((FragmentActivity) context, str2, str);
        }
    }

    @Override // com.jd.lib.cashier.sdk.pay.floors.AbstractCashierBPayDynamicChannelListFloor
    protected void i(View view) {
        if (this.f4081k == null) {
            this.f4081k = (LinearLayout) getView(R.id.lib_cashier_b_pay_dynamic_channel_list_floor_root);
        }
        if (this.f4082l == null) {
            this.f4082l = (LinearLayout) getView(R.id.lib_cashier_b_pay_dynamic_channel_list_channel_container);
        }
    }

    @Override // com.jd.lib.cashier.sdk.pay.floors.AbstractCashierBPayDynamicChannelListFloor
    public void n(com.jd.lib.cashier.sdk.h.d.a aVar, x xVar) {
        if (xVar instanceof p) {
            p pVar = (p) xVar;
            this.o = pVar;
            boolean f2 = f(xVar);
            F(pVar, f2);
            C(pVar, f2);
            v(xVar.a());
            m(xVar.a());
            h(xVar.a());
            c(xVar.a());
            j(xVar.a());
            if (f2) {
                k(xVar);
            }
        }
    }
}

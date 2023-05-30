package com.jd.lib.cashier.sdk.pay.floors;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.fragment.app.FragmentActivity;
import com.jd.cashier.app.jdlibcutter.initialize.DependInitializer;
import com.jd.cashier.app.jdlibcutter.protocol.callback.DynamicEventListener;
import com.jd.cashier.app.jdlibcutter.protocol.dynamic.IDynamic;
import com.jd.lib.cashier.sdk.R;
import com.jd.lib.cashier.sdk.core.commonfloor.floor.AbstractFloor;
import com.jd.lib.cashier.sdk.core.model.CashierCommonPopConfig;
import com.jd.lib.cashier.sdk.core.utils.f0;
import com.jd.lib.cashier.sdk.core.utils.o0;
import com.jd.lib.cashier.sdk.h.g.o;
import com.jd.lib.cashier.sdk.h.g.r;
import com.jd.lib.cashier.sdk.h.g.x;
import com.jd.lib.cashier.sdk.pay.bean.GouWuJinModel;
import com.jd.lib.cashier.sdk.pay.bean.GouWuJinWalletInfo;
import com.jd.lib.cashier.sdk.pay.bean.Payment;
import com.jd.lib.cashier.sdk.pay.bean.ProductInfo;
import com.jd.lib.cashier.sdk.pay.bean.dynamic.paychannel.CashierBPayChannelListData;
import com.jd.lib.cashier.sdk.pay.bean.syncevent.ClickBaiTiaoCouponItemEvent;
import com.jd.lib.cashier.sdk.pay.bean.syncevent.ClickBankCouponItemEvent;
import com.jd.lib.cashier.sdk.pay.bean.syncevent.ClickGWJIconItemEvent;
import com.jd.lib.cashier.sdk.pay.bean.syncevent.ClickJXJIconItemEvent;
import com.jd.lib.cashier.sdk.pay.bean.syncevent.ClickPayChannelItemEvent;
import com.jd.lib.cashier.sdk.pay.view.CashierPayActivity;
import com.jingdong.common.cashiernative.CashierPayChannelCode;
import java.util.List;
import org.json.JSONObject;

/* loaded from: classes14.dex */
public class CashierBPayDynamicVirtualJDPayFloor extends AbstractFloor<com.jd.lib.cashier.sdk.h.d.a, r> {

    /* renamed from: i  reason: collision with root package name */
    private boolean f4090i;

    /* renamed from: j  reason: collision with root package name */
    private LinearLayout f4091j;

    /* renamed from: k  reason: collision with root package name */
    private ViewGroup f4092k;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public class a implements DynamicEventListener<JSONObject> {
        final /* synthetic */ Payment a;
        final /* synthetic */ com.jd.lib.cashier.sdk.h.d.a b;

        a(Payment payment, com.jd.lib.cashier.sdk.h.d.a aVar) {
            this.a = payment;
            this.b = aVar;
        }

        @Override // com.jd.cashier.app.jdlibcutter.protocol.callback.DynamicEventListener
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public Object onEvent(JSONObject jSONObject) {
            CashierBPayDynamicVirtualJDPayFloor.this.e(this.a, this.b, jSONObject);
            return "";
        }
    }

    public CashierBPayDynamicVirtualJDPayFloor(View view) {
        super(view);
    }

    private void d(com.jd.lib.cashier.sdk.h.d.a aVar, r rVar) {
        if (rVar == null || rVar.b == null) {
            return;
        }
        try {
            Payment a2 = rVar.a();
            CashierBPayChannelListData cashierBPayChannelListData = rVar.b;
            if (this.f4091j != null) {
                IDynamic dynamic = DependInitializer.getDynamic();
                JSONObject jSONObject = new JSONObject(DependInitializer.getJsonParser().toJsonString(cashierBPayChannelListData));
                ViewGroup viewGroup = this.f4092k;
                if (viewGroup == null && dynamic != null) {
                    this.f4092k = dynamic.createDynamicContainer(getConvertView().getContext(), "pay", "BPayChannelView", null, new a(a2, aVar));
                    this.f4091j.removeAllViews();
                    this.f4091j.addView(this.f4092k);
                    this.f4090i = dynamic.asyncLoad(this.f4092k, jSONObject);
                } else if (dynamic != null && this.f4090i) {
                    dynamic.bindData(viewGroup, jSONObject);
                } else if (dynamic != null) {
                    this.f4090i = dynamic.asyncLoad(viewGroup, jSONObject);
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(Payment payment, com.jd.lib.cashier.sdk.h.d.a aVar, JSONObject jSONObject) {
        if (aVar == null || jSONObject == null) {
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
                    o(payment);
                    return;
                case 1:
                    if (TextUtils.equals("GOUWUJIN", payment.code)) {
                        p(payment);
                        return;
                    } else if (TextUtils.equals(CashierPayChannelCode.JD_PAY_JXJ, payment.code)) {
                        q(payment);
                        return;
                    } else {
                        return;
                    }
                case 2:
                    f(aVar);
                    return;
                case 3:
                    r(payment);
                    return;
                default:
                    return;
            }
        }
        com.jd.lib.cashier.sdk.d.h.b.a("CashierBPayDynamicVirtualJDPayFloor.dispatchPayChannelTemplateEvent()", "function is invalid =" + optString);
    }

    private void f(com.jd.lib.cashier.sdk.h.d.a aVar) {
        List<com.jd.lib.cashier.sdk.d.a.e.a> list;
        Payment payment;
        if (aVar != null && ((payment = aVar.f3523e) == null || !(payment == null || com.jd.lib.cashier.sdk.h.h.g.j(payment, aVar.b)))) {
            Payment payment2 = aVar.f3524f;
            if (payment2 == null && (list = aVar.f3522c) != null && list.size() > 0) {
                int i2 = 0;
                while (true) {
                    if (i2 >= aVar.f3522c.size()) {
                        break;
                    }
                    com.jd.lib.cashier.sdk.d.a.e.a aVar2 = aVar.f3522c.get(i2);
                    if (500002 == aVar2.getItemType() && (aVar2 instanceof o)) {
                        List<Payment> list2 = ((o) aVar2).a;
                        if (list2 != null && !list2.isEmpty()) {
                            int i3 = 0;
                            while (true) {
                                if (i3 >= list2.size()) {
                                    break;
                                }
                                Payment payment3 = list2.get(i3);
                                if (payment3.jdPay && com.jd.lib.cashier.sdk.h.h.e.g(payment3)) {
                                    payment2 = payment3;
                                    break;
                                }
                                i3++;
                            }
                        }
                    } else if (aVar2 instanceof x) {
                        x xVar = (x) aVar2;
                        if (xVar.a().jdPay && com.jd.lib.cashier.sdk.h.h.e.g(xVar.a())) {
                            payment2 = xVar.a();
                            break;
                        }
                    }
                    if (payment2 != null) {
                        break;
                    }
                    i2++;
                }
            }
            if (payment2 == null) {
                Context context = getConvertView().getContext();
                if (context instanceof CashierPayActivity) {
                    f0.c(context.getString(R.string.lib_cashier_sdk_credit_pay_no_way_pay_toast));
                    return;
                }
                return;
            }
            com.jd.lib.cashier.sdk.b.i.e.f("cashier_item_click", new ClickPayChannelItemEvent(payment2));
        }
    }

    private void g(r rVar, CashierBPayChannelListData cashierBPayChannelListData) {
        if (rVar == null || rVar.b == null) {
            return;
        }
        Payment a2 = rVar.a();
        getConvertView().getContext();
        if (!CashierPayChannelCode.JD_PAY_CREDIT.equals(a2.code) && (!"5".equals(a2.status) || !com.jd.lib.cashier.sdk.h.h.g.a(a2.code))) {
            if ((com.jd.lib.cashier.sdk.h.h.g.a(a2.code) || com.jd.lib.cashier.sdk.h.h.g.c(a2.code) || com.jd.lib.cashier.sdk.h.h.g.f(a2.code)) ? false : true) {
                String str = a2.preferentiaNum;
                m(TextUtils.isEmpty(str) ? "0" : "1", "0", str, "1", cashierBPayChannelListData);
                return;
            }
            String str2 = a2.preferentiaNum;
            com.jd.lib.cashier.sdk.pay.dialog.e eVar = a2.selectedCouponEntity;
            if (eVar != null) {
                str2 = eVar.getTitleName();
            }
            String str3 = str2;
            if (TextUtils.isEmpty(str3)) {
                m("0", "", "", "", cashierBPayChannelListData);
                return;
            } else {
                m("1", "1".equals(a2.canSelectCoupon) ? "1" : "0", str3, "1", cashierBPayChannelListData);
                return;
            }
        }
        m("0", "", "", "", cashierBPayChannelListData);
    }

    private void h(r rVar, CashierBPayChannelListData cashierBPayChannelListData) {
        String str;
        String str2;
        String str3;
        String str4;
        String str5;
        List<GouWuJinWalletInfo> list;
        String str6;
        List<ProductInfo> list2;
        if (rVar == null || cashierBPayChannelListData == null) {
            return;
        }
        Payment a2 = rVar.a();
        List<String> list3 = a2.iconList;
        String str7 = "";
        if (list3 == null || list3.isEmpty()) {
            str = "";
            str2 = str;
            str3 = str2;
            str4 = str3;
        } else {
            String str8 = "2";
            if (a2.iconList.size() >= 2) {
                str6 = a2.iconList.get(1);
                str7 = a2.iconList.get(0);
                str8 = "1";
            } else if (TextUtils.equals(CashierPayChannelCode.JD_PAY_JXJ, a2.code)) {
                if (a2.showSkuList && (list2 = a2.productInfos) != null && !list2.isEmpty()) {
                    str5 = a2.iconList.get(0);
                    str7 = str5;
                    str6 = "";
                }
                str8 = "1";
                str6 = "";
            } else {
                if (TextUtils.equals("GOUWUJIN", a2.code)) {
                    GouWuJinModel gouWuJinModel = a2.gouWuJinModel;
                    if (gouWuJinModel != null && (list = gouWuJinModel.walletInfos) != null && !list.isEmpty()) {
                        str5 = a2.iconList.get(0);
                    }
                    str8 = "1";
                    str6 = "";
                } else {
                    str5 = a2.iconList.get(0);
                    str8 = "1";
                }
                str7 = str5;
                str6 = "";
            }
            str3 = str6;
            str4 = "1";
            str = str7;
            str2 = str8;
        }
        n(str, str2, str3, str4, cashierBPayChannelListData);
    }

    private void i(com.jd.lib.cashier.sdk.h.d.a aVar, r rVar) {
        if (rVar != null) {
            rVar.b = new CashierBPayChannelListData();
            Payment a2 = rVar.a();
            Payment payment = aVar.f3523e;
            Payment payment2 = payment != null ? payment : a2;
            rVar.b.logo = a2.logo;
            if (!TextUtils.isEmpty(a2.channelDesc)) {
                rVar.b.title = a2.channelDesc;
            } else if (!TextUtils.isEmpty(a2.channelName)) {
                rVar.b.title = a2.channelName;
            }
            rVar.b.subTitle = a2.tip;
            if (TextUtils.isEmpty(a2.extraInfo) && a2.toastModel == null) {
                rVar.b.subTitleTipIcon = "";
            } else {
                rVar.b.subTitleTipIcon = "1";
            }
            h(rVar, rVar.b);
            g(rVar, rVar.b);
            if (!TextUtils.isEmpty(a2.moreInfoTip)) {
                m("1", "0", a2.moreInfoTip, "3", rVar.b);
            }
            if (TextUtils.equals("3", a2.status)) {
                CashierBPayChannelListData cashierBPayChannelListData = rVar.b;
                cashierBPayChannelListData.checkBoxType = "4";
                cashierBPayChannelListData.status = "0";
                m("0", "0", "", "", cashierBPayChannelListData);
                n("", "", "", "", rVar.b);
                return;
            }
            rVar.b.status = "1";
            if (com.jd.lib.cashier.sdk.h.h.g.j(payment2, aVar.b)) {
                rVar.b.checkBoxType = "2";
            } else {
                rVar.b.checkBoxType = "1";
            }
        }
    }

    private void j(Payment payment) {
        if (payment == null || CashierPayChannelCode.JD_PAY_CREDIT.equals(payment.code)) {
            return;
        }
        if ("5".equals(payment.status) && com.jd.lib.cashier.sdk.h.h.g.a(payment.code)) {
            return;
        }
        String str = payment.preferentiaNum;
        com.jd.lib.cashier.sdk.pay.dialog.e eVar = payment.selectedCouponEntity;
        if (eVar != null) {
            str = eVar.getTitleName();
        }
        if (TextUtils.isEmpty(str) || payment.hasCouponExpo || payment.isSourceFromDialogSelected) {
            return;
        }
        payment.hasCouponExpo = true;
        com.jd.lib.cashier.sdk.h.e.a.d().M(getConvertView().getContext(), payment.code, com.jd.lib.cashier.sdk.h.h.e.b(payment));
    }

    private void k(Payment payment) {
        GouWuJinModel gouWuJinModel;
        List<GouWuJinWalletInfo> list;
        if (payment == null) {
            return;
        }
        Context context = getConvertView().getContext();
        if (!TextUtils.equals("GOUWUJIN", payment.code) || (gouWuJinModel = payment.gouWuJinModel) == null || (list = gouWuJinModel.walletInfos) == null || list.isEmpty() || !(context instanceof CashierPayActivity)) {
            return;
        }
        com.jd.lib.cashier.sdk.h.e.a.d().S((CashierPayActivity) context);
    }

    private void m(String str, String str2, String str3, String str4, CashierBPayChannelListData cashierBPayChannelListData) {
        if (cashierBPayChannelListData != null) {
            cashierBPayChannelListData.showTipView = str;
            cashierBPayChannelListData.tipHasArrow = str2;
            cashierBPayChannelListData.tipText = str3;
            cashierBPayChannelListData.tipType = str4;
        }
    }

    private void n(String str, String str2, String str3, String str4, CashierBPayChannelListData cashierBPayChannelListData) {
        if (cashierBPayChannelListData != null) {
            cashierBPayChannelListData.iconList_1 = str;
            cashierBPayChannelListData.iconList_1_type = str2;
            cashierBPayChannelListData.iconList_2 = str3;
            cashierBPayChannelListData.iconList_2_type = str4;
        }
    }

    private void o(Payment payment) {
        if (payment == null || payment.selected || o0.a("showCouponDialogCashierBPayDynamicVirtualJDPayFloor") || !"1".equals(payment.canSelectCoupon)) {
            return;
        }
        if (com.jd.lib.cashier.sdk.h.h.g.c(payment.code)) {
            com.jd.lib.cashier.sdk.b.i.e.f("CLICK_BANK_COUPON_ON_PAYMENT_ITEM", new ClickBankCouponItemEvent(payment));
        } else if (com.jd.lib.cashier.sdk.h.h.g.a(payment.code)) {
            com.jd.lib.cashier.sdk.b.i.e.f("click_baitiao_COUPON_on_item_key", new ClickBaiTiaoCouponItemEvent(payment));
        }
    }

    private void p(Payment payment) {
        List<String> list;
        GouWuJinModel gouWuJinModel;
        List<GouWuJinWalletInfo> list2;
        if (payment == null || o0.a("showGWJDialogCashierBPayDynamicVirtualJDPayFloor") || (list = payment.iconList) == null || list.isEmpty() || payment.iconList.size() != 1 || !TextUtils.equals("GOUWUJIN", payment.code) || (gouWuJinModel = payment.gouWuJinModel) == null || (list2 = gouWuJinModel.walletInfos) == null || list2.isEmpty()) {
            return;
        }
        Context context = getConvertView().getContext();
        if (context instanceof CashierPayActivity) {
            com.jd.lib.cashier.sdk.h.e.a.d().R((CashierPayActivity) context);
        }
        com.jd.lib.cashier.sdk.b.i.e.f("CLICK_PAYMENT_ITEM_GWJ_ICON", new ClickGWJIconItemEvent(payment.gouWuJinModel));
    }

    private void q(Payment payment) {
        List<String> list;
        List<ProductInfo> list2;
        if (payment == null || o0.a("showJXJDialogCashierBPayDynamicVirtualJDPayFloor") || (list = payment.iconList) == null || list.isEmpty() || payment.iconList.size() != 1 || !TextUtils.equals(CashierPayChannelCode.JD_PAY_JXJ, payment.code) || !payment.showSkuList || (list2 = payment.productInfos) == null || list2.isEmpty()) {
            return;
        }
        com.jd.lib.cashier.sdk.b.i.e.f("CLICK_PAYMENT_ITEM_JXJ_ICON", new ClickJXJIconItemEvent(payment));
    }

    private void r(Payment payment) {
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

    @Override // com.jd.lib.cashier.sdk.core.commonfloor.floor.AbstractFloor
    public void initView(View view) {
        if (this.f4091j == null) {
            this.f4091j = (LinearLayout) getView(R.id.lib_cashier_b_pay_dynamic_virtual_jd_pay_container);
        }
    }

    @Override // com.jd.lib.cashier.sdk.core.commonfloor.floor.AbstractFloor
    /* renamed from: l  reason: merged with bridge method [inline-methods] */
    public void b(com.jd.lib.cashier.sdk.h.d.a aVar, r rVar) {
        if (rVar != null) {
            i(aVar, rVar);
            d(aVar, rVar);
            k(rVar.a());
            j(rVar.a());
        }
    }
}

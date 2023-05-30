package com.jd.lib.cashier.sdk.pay.floors;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import com.jd.lib.cashier.sdk.R;
import com.jd.lib.cashier.sdk.core.commonfloor.floor.AbstractFloor;
import com.jd.lib.cashier.sdk.core.model.BaiTiaoExtraTip;
import com.jd.lib.cashier.sdk.core.model.CashierCommonPopConfig;
import com.jd.lib.cashier.sdk.core.model.PlanRowEntity;
import com.jd.lib.cashier.sdk.core.model.PopBusinessMap;
import com.jd.lib.cashier.sdk.core.ui.widget.IPlanItemViewEntity;
import com.jd.lib.cashier.sdk.core.utils.o0;
import com.jd.lib.cashier.sdk.h.g.x;
import com.jd.lib.cashier.sdk.pay.bean.GouWuJinModel;
import com.jd.lib.cashier.sdk.pay.bean.GouWuJinWalletInfo;
import com.jd.lib.cashier.sdk.pay.bean.Payment;
import com.jd.lib.cashier.sdk.pay.bean.PlanFeeEntity;
import com.jd.lib.cashier.sdk.pay.bean.ProductInfo;
import com.jd.lib.cashier.sdk.pay.bean.TopPriceMtaObject;
import com.jd.lib.cashier.sdk.pay.bean.baitiao.AgreementServiceMapMap;
import com.jd.lib.cashier.sdk.pay.bean.baitiao.PlanServiceMap;
import com.jd.lib.cashier.sdk.pay.bean.creditcard.CreditCard;
import com.jd.lib.cashier.sdk.pay.bean.creditcard.CreditCardPlan;
import com.jd.lib.cashier.sdk.pay.bean.dynamic.paychannel.AbstractCashierBPayChannelListData;
import com.jd.lib.cashier.sdk.pay.bean.dynamic.payplan.CashierBPayPlanData;
import com.jd.lib.cashier.sdk.pay.bean.dynamic.payplan.PlanViewData;
import com.jd.lib.cashier.sdk.pay.bean.dynamic.payplan.PlanViewSelectedStyle;
import com.jd.lib.cashier.sdk.pay.bean.dynamic.payplan.PlanViewUnSelectedStyle;
import com.jd.lib.cashier.sdk.pay.bean.dynamic.payplan.PlanViewUncheckableStyle;
import com.jd.lib.cashier.sdk.pay.bean.dynamic.payplan.PlusPlanViewSelectedStyle;
import com.jd.lib.cashier.sdk.pay.bean.dynamic.payplan.PlusPlanViewUnSelectedStyle;
import com.jd.lib.cashier.sdk.pay.bean.syncevent.ClickBaiTiaoCouponItemEvent;
import com.jd.lib.cashier.sdk.pay.bean.syncevent.ClickBaiTiaoPayPlanItemEvent;
import com.jd.lib.cashier.sdk.pay.bean.syncevent.ClickBankCouponItemEvent;
import com.jd.lib.cashier.sdk.pay.bean.syncevent.ClickCreditCardPlanItemEvent;
import com.jd.lib.cashier.sdk.pay.bean.syncevent.ClickGWJIconItemEvent;
import com.jd.lib.cashier.sdk.pay.bean.syncevent.ClickJXJIconItemEvent;
import com.jd.lib.cashier.sdk.pay.bean.syncevent.UpdateHeaderFloorInfo;
import com.jd.lib.cashier.sdk.pay.view.CashierPayActivity;
import com.jingdong.common.cashiernative.CashierPayChannelCode;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes14.dex */
public abstract class AbstractCashierBPayDynamicChannelListFloor extends AbstractFloor<com.jd.lib.cashier.sdk.h.d.a, x> {

    /* renamed from: i  reason: collision with root package name */
    public IPlanItemViewEntity f3951i;

    /* renamed from: j  reason: collision with root package name */
    public LinearLayout f3952j;

    public AbstractCashierBPayDynamicChannelListFloor(View view) {
        super(view);
    }

    public void c(Payment payment) {
        if (payment != null) {
            Context context = getConvertView().getContext();
            if (payment.hasPaymentExpo || payment.isSourceFromDialogSelected) {
                return;
            }
            payment.hasPaymentExpo = true;
            if (context instanceof CashierPayActivity) {
                com.jd.lib.cashier.sdk.h.e.a.d().V((CashierPayActivity) context, payment.code, com.jd.lib.cashier.sdk.h.h.e.b(payment), payment.canUse(), payment.isCombine(), payment.defaultSelected, payment.openXjkLargePayFlag, payment.changetag, payment.hasCouponExpo ? "1" : "0");
            }
        }
    }

    public void d(x xVar, AbstractCashierBPayChannelListData abstractCashierBPayChannelListData) {
        if (xVar == null || abstractCashierBPayChannelListData == null) {
            return;
        }
        Payment a = xVar.a();
        Context context = getConvertView().getContext();
        if (!CashierPayChannelCode.JD_PAY_CREDIT.equals(a.code) && (!"5".equals(a.status) || !com.jd.lib.cashier.sdk.h.h.g.a(a.code))) {
            if ((com.jd.lib.cashier.sdk.h.h.g.a(a.code) || com.jd.lib.cashier.sdk.h.h.g.c(a.code) || com.jd.lib.cashier.sdk.h.h.g.f(a.code)) ? false : true) {
                String str = a.preferentiaNum;
                String str2 = TextUtils.isEmpty(str) ? "0" : "1";
                if (!TextUtils.isEmpty(str) && !a.hasCouponExpo && !a.isSourceFromDialogSelected) {
                    a.hasCouponExpo = true;
                    com.jd.lib.cashier.sdk.h.e.a.d().M(context, a.code, com.jd.lib.cashier.sdk.h.h.e.b(a));
                }
                p(str2, "0", str, "1", abstractCashierBPayChannelListData);
                return;
            }
            String str3 = a.preferentiaNum;
            com.jd.lib.cashier.sdk.pay.dialog.e eVar = a.selectedCouponEntity;
            if (eVar != null) {
                str3 = eVar.getTitleName();
            }
            if (!TextUtils.isEmpty(str3) && !a.hasCouponExpo && !a.isSourceFromDialogSelected) {
                a.hasCouponExpo = true;
                com.jd.lib.cashier.sdk.h.e.a.d().M(context, a.code, com.jd.lib.cashier.sdk.h.h.e.b(a));
            }
            if (TextUtils.isEmpty(str3)) {
                p("0", "", "", "", abstractCashierBPayChannelListData);
                return;
            } else {
                p("1", "1".equals(a.canSelectCoupon) ? "1" : "0", str3, "1", abstractCashierBPayChannelListData);
                return;
            }
        }
        p("0", "", "", "", abstractCashierBPayChannelListData);
    }

    public void e(x xVar, AbstractCashierBPayChannelListData abstractCashierBPayChannelListData) {
        String str;
        String str2;
        String str3;
        String str4;
        String str5;
        List<GouWuJinWalletInfo> list;
        String str6;
        List<ProductInfo> list2;
        if (xVar == null || abstractCashierBPayChannelListData == null) {
            return;
        }
        Payment a = xVar.a();
        Context context = getConvertView().getContext();
        List<String> list3 = a.iconList;
        String str7 = "";
        if (list3 == null || list3.isEmpty()) {
            str = "";
            str2 = str;
            str3 = str2;
            str4 = str3;
        } else {
            String str8 = "2";
            if (a.iconList.size() >= 2) {
                str6 = a.iconList.get(1);
                str7 = a.iconList.get(0);
                str8 = "1";
            } else if (TextUtils.equals(CashierPayChannelCode.JD_PAY_JXJ, a.code)) {
                if (a.showSkuList && (list2 = a.productInfos) != null && !list2.isEmpty()) {
                    str5 = a.iconList.get(0);
                    str7 = str5;
                    str6 = "";
                }
                str8 = "1";
                str6 = "";
            } else {
                if (TextUtils.equals("GOUWUJIN", a.code)) {
                    GouWuJinModel gouWuJinModel = a.gouWuJinModel;
                    if (gouWuJinModel != null && (list = gouWuJinModel.walletInfos) != null && !list.isEmpty()) {
                        if (context instanceof CashierPayActivity) {
                            com.jd.lib.cashier.sdk.h.e.a.d().S((CashierPayActivity) context);
                        }
                        str5 = a.iconList.get(0);
                    }
                    str8 = "1";
                    str6 = "";
                } else {
                    str5 = a.iconList.get(0);
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
        q(str, str2, str3, str4, abstractCashierBPayChannelListData);
    }

    public boolean f(@NonNull x xVar) {
        Payment a = xVar.a();
        if (a == null) {
            return false;
        }
        boolean z = a.selected;
        boolean a2 = com.jd.lib.cashier.sdk.h.h.g.a(a.code);
        boolean equals = "5".equals(a.status);
        boolean equals2 = CashierPayChannelCode.JD_PAY_CREDIT.equals(a.code);
        boolean equals3 = TextUtils.equals(a.status, "3");
        boolean z2 = a.baitiaoPlanInfo != null && a2;
        LinearLayout linearLayout = this.f3952j;
        if (linearLayout == null) {
            return false;
        }
        if (equals3) {
            linearLayout.setVisibility(8);
            return false;
        } else if (equals || !(z || z2)) {
            linearLayout.setVisibility(8);
            return false;
        } else if (equals2) {
            linearLayout.setVisibility(0);
            return true;
        } else if (a2) {
            List<IPlanItemViewEntity> list = a.planFeeEntityList;
            if (list != null && list.size() > 0) {
                this.f3952j.setVisibility(0);
                return true;
            }
            this.f3952j.setVisibility(8);
            return false;
        } else {
            linearLayout.setVisibility(8);
            return false;
        }
    }

    public void g(Payment payment, CashierBPayPlanData cashierBPayPlanData) {
        PopBusinessMap popBusinessMap;
        List<PlanRowEntity> list;
        if (payment == null || cashierBPayPlanData == null) {
            return;
        }
        CreditCard creditCard = payment.currentCreditCardBank;
        if (creditCard != null && !TextUtils.isEmpty(creditCard.bankNameShow)) {
            cashierBPayPlanData.title = payment.currentCreditCardBank.bankNameShow;
            cashierBPayPlanData.arrowIcon = true;
        }
        if (!TextUtils.isEmpty(payment.investorDoc)) {
            cashierBPayPlanData.subTitle = payment.investorDoc;
        }
        BaiTiaoExtraTip baiTiaoExtraTip = payment.baiTiaoExtraTip;
        if (baiTiaoExtraTip != null && !TextUtils.isEmpty(baiTiaoExtraTip.extraTipStr)) {
            cashierBPayPlanData.headTip = baiTiaoExtraTip.extraTipStr;
            CashierCommonPopConfig cashierCommonPopConfig = baiTiaoExtraTip.extraTipToast;
            if (cashierCommonPopConfig != null && (!TextUtils.isEmpty(cashierCommonPopConfig.cancelBtn) || !TextUtils.isEmpty(cashierCommonPopConfig.confirmBtn))) {
                cashierBPayPlanData.headTipIcon = true;
            }
        }
        List<IPlanItemViewEntity> list2 = payment.planFeeEntityList;
        if (list2 != null && !list2.isEmpty()) {
            cashierBPayPlanData.planFeeList = new ArrayList(6);
            for (int i2 = 0; i2 < list2.size(); i2++) {
                IPlanItemViewEntity iPlanItemViewEntity = list2.get(i2);
                if (iPlanItemViewEntity != null) {
                    PlanViewData planViewData = new PlanViewData();
                    planViewData.priceAndPlan = iPlanItemViewEntity.getTopText();
                    planViewData.planFeeInfo = iPlanItemViewEntity.getPlanFeeInfo();
                    planViewData.discountLogoText = iPlanItemViewEntity.getFlagText();
                    planViewData.selected = iPlanItemViewEntity.isChecked();
                    planViewData.selectable = iPlanItemViewEntity.isCheckable();
                    if (iPlanItemViewEntity.isCheckable()) {
                        if (iPlanItemViewEntity.getSelectorType() == 20000) {
                            if (iPlanItemViewEntity.isChecked()) {
                                planViewData.planItemViewStyle = new PlusPlanViewSelectedStyle();
                            } else {
                                planViewData.planItemViewStyle = new PlusPlanViewUnSelectedStyle(TextUtils.equals(payment.mianxiHighlight, "1"));
                            }
                        } else if (iPlanItemViewEntity.getSelectorType() == 30000) {
                            if (iPlanItemViewEntity.isChecked()) {
                                planViewData.planItemViewStyle = new PlanViewSelectedStyle();
                            } else {
                                planViewData.planItemViewStyle = new PlanViewUnSelectedStyle(TextUtils.equals(payment.mianxiHighlight, "1"));
                            }
                        } else if (iPlanItemViewEntity.isChecked()) {
                            planViewData.planItemViewStyle = new PlanViewSelectedStyle();
                        } else {
                            planViewData.planItemViewStyle = new PlanViewUnSelectedStyle(false);
                        }
                    } else {
                        planViewData.planItemViewStyle = new PlanViewUncheckableStyle();
                    }
                    cashierBPayPlanData.planFeeList.add(planViewData);
                }
            }
        }
        PlanServiceMap planServiceMap = payment.serviceMap;
        if (planServiceMap != null && !TextUtils.isEmpty(planServiceMap.planServiceFeeStr)) {
            cashierBPayPlanData.bottomTip = planServiceMap.planServiceFeeStr;
            CashierCommonPopConfig cashierCommonPopConfig2 = planServiceMap.planServiceFeeToast;
            cashierBPayPlanData.bottomTipIcon = (cashierCommonPopConfig2 == null || (popBusinessMap = cashierCommonPopConfig2.businessMap) == null || (list = popBusinessMap.table) == null || list.isEmpty()) ? false : true;
        }
        AgreementServiceMapMap agreementServiceMapMap = payment.agreementServiceMap;
        if (agreementServiceMapMap == null || TextUtils.isEmpty(agreementServiceMapMap.agreementName)) {
            return;
        }
        cashierBPayPlanData.superLinkTip = payment.agreementServiceMap.agreementName;
    }

    public void h(Payment payment) {
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

    protected abstract void i(View view);

    @Override // com.jd.lib.cashier.sdk.core.commonfloor.floor.AbstractFloor
    public void initView(View view) {
        if (this.f3952j == null) {
            this.f3952j = (LinearLayout) getView(R.id.lib_cashier_b_pay_dynamic_channel_list_plan_view_container);
        }
        i(view);
    }

    public void j(Payment payment) {
        CashierCommonPopConfig cashierCommonPopConfig;
        if (payment == null) {
            return;
        }
        Context context = getConvertView().getContext();
        BaiTiaoExtraTip baiTiaoExtraTip = payment.baiTiaoExtraTip;
        if (baiTiaoExtraTip == null || TextUtils.isEmpty(baiTiaoExtraTip.extraTipStr) || (cashierCommonPopConfig = baiTiaoExtraTip.extraTipToast) == null) {
            return;
        }
        if (!(TextUtils.isEmpty(cashierCommonPopConfig.cancelBtn) && TextUtils.isEmpty(cashierCommonPopConfig.confirmBtn)) && (context instanceof CashierPayActivity)) {
            com.jd.lib.cashier.sdk.h.e.a.d().m((CashierPayActivity) context);
        }
    }

    public void k(@NonNull x xVar) {
        String str;
        boolean z;
        try {
            Context context = getConvertView().getContext();
            for (IPlanItemViewEntity iPlanItemViewEntity : xVar.a().planFeeEntityList) {
                if (iPlanItemViewEntity instanceof PlanFeeEntity) {
                    str = ((PlanFeeEntity) iPlanItemViewEntity).getSkuSplitFlag() ? "1" : "0";
                    z = iPlanItemViewEntity.isChecked();
                } else {
                    str = "0";
                    z = false;
                }
                com.jd.lib.cashier.sdk.h.e.a.d().T(context, iPlanItemViewEntity.getPlanNum(), z, str, xVar.a().code);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void l(int i2, Payment payment) {
        if (payment == null || o0.b("onPlanViewClickAbstractCashierBPayDynamicChannelListFloor", 300L)) {
            return;
        }
        Context context = getConvertView().getContext();
        List<IPlanItemViewEntity> list = payment.planFeeEntityList;
        if (list == null || list.size() <= i2 || i2 < 0) {
            return;
        }
        IPlanItemViewEntity iPlanItemViewEntity = list.get(i2);
        if (this.f3951i == null) {
            this.f3951i = iPlanItemViewEntity;
        }
        if (iPlanItemViewEntity instanceof CreditCardPlan) {
            IPlanItemViewEntity iPlanItemViewEntity2 = this.f3951i;
            if (iPlanItemViewEntity2 instanceof CreditCardPlan) {
                CreditCardPlan creditCardPlan = (CreditCardPlan) iPlanItemViewEntity;
                com.jd.lib.cashier.sdk.h.e.a.d().F(context, payment.code, creditCardPlan.plan, "");
                com.jd.lib.cashier.sdk.b.i.e.g("CLICK_PAYMENT_CHANNEL_PAY_PLAN_ITEM", "credit_card_plan", new ClickCreditCardPlanItemEvent(creditCardPlan, (CreditCardPlan) iPlanItemViewEntity2, payment));
                this.f3951i = iPlanItemViewEntity;
            }
        }
        if (iPlanItemViewEntity instanceof PlanFeeEntity) {
            IPlanItemViewEntity iPlanItemViewEntity3 = this.f3951i;
            if (iPlanItemViewEntity3 instanceof PlanFeeEntity) {
                PlanFeeEntity planFeeEntity = (PlanFeeEntity) iPlanItemViewEntity;
                PlanFeeEntity planFeeEntity2 = (PlanFeeEntity) iPlanItemViewEntity3;
                com.jd.lib.cashier.sdk.h.e.a.d().F(context, payment.code, planFeeEntity.getPlan(), planFeeEntity.getSkuSplitFlag() ? "1" : "0");
                com.jd.lib.cashier.sdk.b.i.e.g("CLICK_PAYMENT_CHANNEL_PAY_PLAN_ITEM", "baitiao_plan", new ClickBaiTiaoPayPlanItemEvent(planFeeEntity, planFeeEntity2, payment));
            }
        }
        this.f3951i = iPlanItemViewEntity;
    }

    public void m(Payment payment) {
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

    public abstract void n(com.jd.lib.cashier.sdk.h.d.a aVar, x xVar);

    @Override // com.jd.lib.cashier.sdk.core.commonfloor.floor.AbstractFloor
    /* renamed from: o  reason: merged with bridge method [inline-methods] */
    public void b(com.jd.lib.cashier.sdk.h.d.a aVar, x xVar) {
        n(aVar, xVar);
    }

    public void p(String str, String str2, String str3, String str4, AbstractCashierBPayChannelListData abstractCashierBPayChannelListData) {
        if (abstractCashierBPayChannelListData != null) {
            abstractCashierBPayChannelListData.showTipView = str;
            abstractCashierBPayChannelListData.tipHasArrow = str2;
            abstractCashierBPayChannelListData.tipText = str3;
            abstractCashierBPayChannelListData.tipType = str4;
        }
    }

    public void q(String str, String str2, String str3, String str4, AbstractCashierBPayChannelListData abstractCashierBPayChannelListData) {
        if (abstractCashierBPayChannelListData != null) {
            abstractCashierBPayChannelListData.iconList_1 = str;
            abstractCashierBPayChannelListData.iconList_1_type = str2;
            abstractCashierBPayChannelListData.iconList_2 = str3;
            abstractCashierBPayChannelListData.iconList_2_type = str4;
        }
    }

    public void r(Payment payment) {
        if (payment == null || o0.a("showAgreementDialogAbstractCashierBPayDynamicChannelListFloor")) {
            return;
        }
        Context context = getConvertView().getContext();
        AgreementServiceMapMap agreementServiceMapMap = payment.agreementServiceMap;
        if (!(context instanceof CashierPayActivity) || agreementServiceMapMap == null) {
            return;
        }
        new com.jd.lib.cashier.sdk.pay.dialog.a().e((CashierPayActivity) context, agreementServiceMapMap.agreementUrl, agreementServiceMapMap.agreementTitle);
    }

    public void s(Payment payment) {
        CashierCommonPopConfig cashierCommonPopConfig;
        if (payment == null || o0.a("showBTGrowUpDialogAbstractCashierBPayDynamicChannelListFloor")) {
            return;
        }
        BaiTiaoExtraTip baiTiaoExtraTip = payment.baiTiaoExtraTip;
        Context context = getConvertView().getContext();
        if (baiTiaoExtraTip == null || (cashierCommonPopConfig = baiTiaoExtraTip.extraTipToast) == null) {
            return;
        }
        com.jd.lib.cashier.sdk.b.d.a.c(context, cashierCommonPopConfig);
        if (context instanceof CashierPayActivity) {
            com.jd.lib.cashier.sdk.h.e.a.d().l((CashierPayActivity) context, payment.code);
        }
    }

    public void t(Payment payment) {
        if (payment == null || o0.a("showCouponDialogAbstractCashierBPayDynamicChannelListFloor") || CashierPayChannelCode.JD_PAY_CREDIT.equals(payment.code)) {
            return;
        }
        if (!("5".equals(payment.status) && com.jd.lib.cashier.sdk.h.h.g.a(payment.code)) && "1".equals(payment.canSelectCoupon)) {
            if (com.jd.lib.cashier.sdk.h.h.g.c(payment.code)) {
                com.jd.lib.cashier.sdk.b.i.e.f("CLICK_BANK_COUPON_ON_PAYMENT_ITEM", new ClickBankCouponItemEvent(payment));
            } else if (com.jd.lib.cashier.sdk.h.h.g.a(payment.code)) {
                com.jd.lib.cashier.sdk.b.i.e.f("click_baitiao_COUPON_on_item_key", new ClickBaiTiaoCouponItemEvent(payment));
            }
        }
    }

    public void u(Payment payment) {
        List<String> list;
        GouWuJinModel gouWuJinModel;
        List<GouWuJinWalletInfo> list2;
        if (payment == null || o0.a("showGWJDialogAbstractCashierBPayDynamicChannelListFloor") || (list = payment.iconList) == null || list.isEmpty() || payment.iconList.size() != 1 || !TextUtils.equals("GOUWUJIN", payment.code) || (gouWuJinModel = payment.gouWuJinModel) == null || (list2 = gouWuJinModel.walletInfos) == null || list2.isEmpty()) {
            return;
        }
        Context context = getConvertView().getContext();
        if (context instanceof CashierPayActivity) {
            com.jd.lib.cashier.sdk.h.e.a.d().R((CashierPayActivity) context);
        }
        com.jd.lib.cashier.sdk.b.i.e.f("CLICK_PAYMENT_ITEM_GWJ_ICON", new ClickGWJIconItemEvent(payment.gouWuJinModel));
    }

    public void v(Payment payment) {
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

    public void w(Payment payment) {
        List<String> list;
        List<ProductInfo> list2;
        if (payment == null || o0.a("showJXJDialogAbstractCashierBPayDynamicChannelListFloor") || (list = payment.iconList) == null || list.isEmpty() || payment.iconList.size() != 1 || !TextUtils.equals(CashierPayChannelCode.JD_PAY_JXJ, payment.code) || !payment.showSkuList || (list2 = payment.productInfos) == null || list2.isEmpty()) {
            return;
        }
        com.jd.lib.cashier.sdk.b.i.e.f("CLICK_PAYMENT_ITEM_JXJ_ICON", new ClickJXJIconItemEvent(payment));
    }

    public void x(String str, AbstractCashierBPayChannelListData abstractCashierBPayChannelListData) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        p("1", "0", str, "3", abstractCashierBPayChannelListData);
    }

    public void y(Payment payment) {
        if (payment == null || o0.a("showPlanPaymentRateDialogAbstractCashierBPayDynamicChannelListFloor")) {
            return;
        }
        Context context = getConvertView().getContext();
        PlanServiceMap planServiceMap = payment.serviceMap;
        if (context instanceof CashierPayActivity) {
            CashierPayActivity cashierPayActivity = (CashierPayActivity) context;
            com.jd.lib.cashier.sdk.h.e.a.d().O(cashierPayActivity, payment.code, com.jd.lib.cashier.sdk.h.h.e.b(payment));
            if (planServiceMap != null) {
                com.jd.lib.cashier.sdk.b.d.a.j(cashierPayActivity, planServiceMap.planServiceFeeToast);
            }
        }
    }
}

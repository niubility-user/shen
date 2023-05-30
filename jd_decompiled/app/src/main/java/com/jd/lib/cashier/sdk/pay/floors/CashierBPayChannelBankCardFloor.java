package com.jd.lib.cashier.sdk.pay.floors;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.jd.cashier.app.jdlibcutter.protocol.utils.DpiUtil;
import com.jd.lib.cashier.sdk.R;
import com.jd.lib.cashier.sdk.core.commonfloor.floor.AbstractFloor;
import com.jd.lib.cashier.sdk.core.model.BaiTiaoExtraTip;
import com.jd.lib.cashier.sdk.core.model.CashierCommonPopConfig;
import com.jd.lib.cashier.sdk.core.model.PlanRowEntity;
import com.jd.lib.cashier.sdk.core.model.PopBusinessMap;
import com.jd.lib.cashier.sdk.core.ui.CashierBNoLogoItemView;
import com.jd.lib.cashier.sdk.core.ui.widget.AbsPayPlanView;
import com.jd.lib.cashier.sdk.core.ui.widget.BPayPlanView;
import com.jd.lib.cashier.sdk.core.ui.widget.IPlanItemViewEntity;
import com.jd.lib.cashier.sdk.core.ui.widget.OnPlanViewClickListener;
import com.jd.lib.cashier.sdk.core.utils.JDDarkUtil;
import com.jd.lib.cashier.sdk.core.utils.o0;
import com.jd.lib.cashier.sdk.core.utils.r;
import com.jd.lib.cashier.sdk.h.g.x;
import com.jd.lib.cashier.sdk.pay.bean.GouWuJinModel;
import com.jd.lib.cashier.sdk.pay.bean.GouWuJinWalletInfo;
import com.jd.lib.cashier.sdk.pay.bean.Payment;
import com.jd.lib.cashier.sdk.pay.bean.PlanFeeEntity;
import com.jd.lib.cashier.sdk.pay.bean.ProductInfo;
import com.jd.lib.cashier.sdk.pay.bean.TopPriceMtaObject;
import com.jd.lib.cashier.sdk.pay.bean.baitiao.AgreementServiceMapMap;
import com.jd.lib.cashier.sdk.pay.bean.baitiao.PlanServiceMap;
import com.jd.lib.cashier.sdk.pay.bean.coupon.CouponEntity;
import com.jd.lib.cashier.sdk.pay.bean.creditcard.CreditCardPlan;
import com.jd.lib.cashier.sdk.pay.bean.syncevent.ClickBaiTiaoCouponItemEvent;
import com.jd.lib.cashier.sdk.pay.bean.syncevent.ClickBaiTiaoPayPlanItemEvent;
import com.jd.lib.cashier.sdk.pay.bean.syncevent.ClickBankCouponItemEvent;
import com.jd.lib.cashier.sdk.pay.bean.syncevent.ClickCreditCardBankItemEvent;
import com.jd.lib.cashier.sdk.pay.bean.syncevent.ClickCreditCardCouponItemEvent;
import com.jd.lib.cashier.sdk.pay.bean.syncevent.ClickCreditCardPlanItemEvent;
import com.jd.lib.cashier.sdk.pay.bean.syncevent.ClickGWJIconItemEvent;
import com.jd.lib.cashier.sdk.pay.bean.syncevent.ClickJXJIconItemEvent;
import com.jd.lib.cashier.sdk.pay.bean.syncevent.ClickOtherJDPayItemEvent;
import com.jd.lib.cashier.sdk.pay.bean.syncevent.UpdateHeaderFloorInfo;
import com.jd.lib.cashier.sdk.pay.view.CashierPayActivity;
import com.jingdong.common.cashiernative.CashierPayChannelCode;
import java.util.List;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;

/* loaded from: classes14.dex */
public class CashierBPayChannelBankCardFloor extends AbstractFloor<com.jd.lib.cashier.sdk.h.d.a, com.jd.lib.cashier.sdk.h.g.g> {

    /* renamed from: i  reason: collision with root package name */
    private ViewStub f4005i;

    /* renamed from: j  reason: collision with root package name */
    public BPayPlanView f4006j;

    /* renamed from: k  reason: collision with root package name */
    private CashierBNoLogoItemView f4007k;

    /* renamed from: l  reason: collision with root package name */
    private LinearLayout f4008l;

    /* renamed from: m  reason: collision with root package name */
    private com.jd.lib.cashier.sdk.h.g.g f4009m;

    /* renamed from: n  reason: collision with root package name */
    private Payment f4010n;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public class a extends com.jd.lib.cashier.sdk.core.utils.b {

        /* renamed from: j  reason: collision with root package name */
        final /* synthetic */ Context f4011j;

        /* renamed from: k  reason: collision with root package name */
        final /* synthetic */ AgreementServiceMapMap f4012k;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        a(CashierBPayChannelBankCardFloor cashierBPayChannelBankCardFloor, long j2, Context context, AgreementServiceMapMap agreementServiceMapMap) {
            super(j2);
            this.f4011j = context;
            this.f4012k = agreementServiceMapMap;
        }

        @Override // com.jd.lib.cashier.sdk.core.utils.b
        public void b(View view) {
            Context context = this.f4011j;
            if (context instanceof CashierPayActivity) {
                AgreementServiceMapMap agreementServiceMapMap = this.f4012k;
                new com.jd.lib.cashier.sdk.pay.dialog.a().e((CashierPayActivity) context, agreementServiceMapMap.agreementUrl, agreementServiceMapMap.agreementTitle);
            }
        }
    }

    /* loaded from: classes14.dex */
    class b extends com.jd.lib.cashier.sdk.core.utils.b {
        b(CashierBPayChannelBankCardFloor cashierBPayChannelBankCardFloor, long j2) {
            super(j2);
        }

        @Override // com.jd.lib.cashier.sdk.core.utils.b
        public void b(View view) {
            com.jd.lib.cashier.sdk.b.i.e.f("CLICK_OTHER_JD_PAYMENT_CHANNEL_ITEM", new ClickOtherJDPayItemEvent());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public class c implements OnPlanViewClickListener {
        final /* synthetic */ Context a;

        c(Context context) {
            this.a = context;
        }

        @Override // com.jd.lib.cashier.sdk.core.ui.widget.OnPlanViewClickListener
        public void onClick(@NonNull IPlanItemViewEntity iPlanItemViewEntity, @Nullable IPlanItemViewEntity iPlanItemViewEntity2) {
            if ((iPlanItemViewEntity instanceof PlanFeeEntity) && (iPlanItemViewEntity2 instanceof PlanFeeEntity)) {
                PlanFeeEntity planFeeEntity = (PlanFeeEntity) iPlanItemViewEntity;
                PlanFeeEntity planFeeEntity2 = (PlanFeeEntity) iPlanItemViewEntity2;
                r.b("CashierBPayChannelBankCardFloor", "payPlanItemView initializePayPlanView payPlanView.setOnItemClickListener");
                Payment a = CashierBPayChannelBankCardFloor.this.f4009m.a();
                com.jd.lib.cashier.sdk.h.e.a.d().F(this.a, a.code, planFeeEntity.getPlan(), planFeeEntity.getSkuSplitFlag() ? "1" : "0");
                com.jd.lib.cashier.sdk.b.i.e.g("CLICK_PAYMENT_CHANNEL_PAY_PLAN_ITEM", "baitiao_plan", new ClickBaiTiaoPayPlanItemEvent(planFeeEntity, planFeeEntity2, a));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public class d implements OnPlanViewClickListener {
        final /* synthetic */ Context a;

        d(Context context) {
            this.a = context;
        }

        @Override // com.jd.lib.cashier.sdk.core.ui.widget.OnPlanViewClickListener
        public void onClick(@NonNull IPlanItemViewEntity iPlanItemViewEntity, @Nullable IPlanItemViewEntity iPlanItemViewEntity2) {
            if (iPlanItemViewEntity2 != null && (iPlanItemViewEntity instanceof CreditCardPlan) && (iPlanItemViewEntity2 instanceof CreditCardPlan)) {
                CreditCardPlan creditCardPlan = (CreditCardPlan) iPlanItemViewEntity;
                r.b("CashierBPayChannelBankCardFloor", "payPlanItemView initializePayPlanView payPlanView.setOnItemClickListener");
                Payment a = CashierBPayChannelBankCardFloor.this.f4009m.a();
                com.jd.lib.cashier.sdk.h.e.a.d().F(this.a, a.code, creditCardPlan.plan, "");
                com.jd.lib.cashier.sdk.b.i.e.g("CLICK_PAYMENT_CHANNEL_PAY_PLAN_ITEM", "credit_card_plan", new ClickCreditCardPlanItemEvent(creditCardPlan, (CreditCardPlan) iPlanItemViewEntity2, a));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public class e implements Function1<String, Unit> {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ com.jd.lib.cashier.sdk.pay.dialog.e f4013g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ x f4014h;

        e(CashierBPayChannelBankCardFloor cashierBPayChannelBankCardFloor, com.jd.lib.cashier.sdk.pay.dialog.e eVar, x xVar) {
            this.f4013g = eVar;
            this.f4014h = xVar;
        }

        @Override // kotlin.jvm.functions.Function1
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public Unit invoke(String str) {
            if (!o0.a("CashierBPayChannelBankCardFloor") && (this.f4013g instanceof CouponEntity)) {
                com.jd.lib.cashier.sdk.b.i.e.f("click_credit_card_COUPON_on_item_key", new ClickCreditCardCouponItemEvent(this.f4014h.a()));
            }
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public class f implements Function0<Unit> {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ Payment f4015g;

        f(CashierBPayChannelBankCardFloor cashierBPayChannelBankCardFloor, Payment payment) {
            this.f4015g = payment;
        }

        @Override // kotlin.jvm.functions.Function0
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public Unit invoke() {
            if (o0.a("CashierBPayChannelBankCardFloor")) {
                return null;
            }
            com.jd.lib.cashier.sdk.b.i.e.f("click_credit_card_bank_on_item", new ClickCreditCardBankItemEvent(this.f4015g));
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public class g implements View.OnClickListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ BaiTiaoExtraTip f4016g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ Context f4017h;

        g(BaiTiaoExtraTip baiTiaoExtraTip, Context context) {
            this.f4016g = baiTiaoExtraTip;
            this.f4017h = context;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            CashierCommonPopConfig cashierCommonPopConfig;
            BaiTiaoExtraTip baiTiaoExtraTip = this.f4016g;
            if (baiTiaoExtraTip == null || (cashierCommonPopConfig = baiTiaoExtraTip.extraTipToast) == null) {
                return;
            }
            com.jd.lib.cashier.sdk.b.d.a.c(this.f4017h, cashierCommonPopConfig);
            Payment a = CashierBPayChannelBankCardFloor.this.f4009m.a();
            if (this.f4017h instanceof CashierPayActivity) {
                com.jd.lib.cashier.sdk.h.e.a.d().l((CashierPayActivity) this.f4017h, a.code);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public class h implements Runnable {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ Context f4019g;

        h(CashierBPayChannelBankCardFloor cashierBPayChannelBankCardFloor, Context context) {
            this.f4019g = context;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.f4019g instanceof CashierPayActivity) {
                com.jd.lib.cashier.sdk.h.e.a.d().m((CashierPayActivity) this.f4019g);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public class i implements View.OnClickListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ Payment f4020g;

        i(Payment payment) {
            this.f4020g = payment;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            Payment payment = this.f4020g;
            if (payment != null && payment.equals(CashierBPayChannelBankCardFloor.this.f4010n) && o0.a("CashierBPayChannelBankCardFloorcoupon")) {
                return;
            }
            if (com.jd.lib.cashier.sdk.h.h.g.c(this.f4020g.code)) {
                com.jd.lib.cashier.sdk.b.i.e.f("CLICK_BANK_COUPON_ON_PAYMENT_ITEM", new ClickBankCouponItemEvent(this.f4020g));
            }
            if (com.jd.lib.cashier.sdk.h.h.g.a(this.f4020g.code)) {
                r.b("CashierBPayChannelBankCardFloor", "EventBusManager sendEvent CLICK_COUPON_ON_PAYMENT_CHANNEL");
                com.jd.lib.cashier.sdk.b.i.e.f("click_baitiao_COUPON_on_item_key", new ClickBaiTiaoCouponItemEvent(this.f4020g));
            }
            CashierBPayChannelBankCardFloor.this.f4010n = this.f4020g;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public class j implements View.OnClickListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ Context f4022g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ x f4023h;

        /* renamed from: i  reason: collision with root package name */
        final /* synthetic */ PlanServiceMap f4024i;

        j(CashierBPayChannelBankCardFloor cashierBPayChannelBankCardFloor, Context context, x xVar, PlanServiceMap planServiceMap) {
            this.f4022g = context;
            this.f4023h = xVar;
            this.f4024i = planServiceMap;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            Context context = this.f4022g;
            if (context instanceof CashierPayActivity) {
                CashierPayActivity cashierPayActivity = (CashierPayActivity) context;
                Payment a = this.f4023h.a();
                com.jd.lib.cashier.sdk.h.e.a.d().O(cashierPayActivity, a.code, com.jd.lib.cashier.sdk.h.h.e.b(a));
                com.jd.lib.cashier.sdk.b.d.a.j(cashierPayActivity, this.f4024i.planServiceFeeToast);
            }
        }
    }

    public CashierBPayChannelBankCardFloor(View view) {
        super(view);
    }

    private void g(List<IPlanItemViewEntity> list, String str) {
        Context context = getConvertView().getContext();
        BPayPlanView bPayPlanView = (BPayPlanView) getView(R.id.stub_pay_plan_view);
        this.f4006j = bPayPlanView;
        bPayPlanView.H(AbsPayPlanView.b.PLAN_BAITIAO);
        this.f4006j.x(list, str);
        this.f4006j.G(new c(context));
        BPayPlanView bPayPlanView2 = this.f4006j;
        if (bPayPlanView2 != null) {
            bPayPlanView2.onChangeSkin();
        }
    }

    private void h(x xVar) {
        Payment a2 = xVar.a();
        Context context = getConvertView().getContext();
        if (a2 == null) {
            return;
        }
        if (CashierPayChannelCode.JD_PAY_CREDIT.equals(a2.code)) {
            this.f4007k.k();
        } else if ("5".equals(a2.status) && com.jd.lib.cashier.sdk.h.h.g.a(a2.code)) {
            this.f4007k.k();
        } else {
            if ((com.jd.lib.cashier.sdk.h.h.g.a(a2.code) || com.jd.lib.cashier.sdk.h.h.g.c(a2.code) || com.jd.lib.cashier.sdk.h.h.g.f(a2.code)) ? false : true) {
                String str = a2.preferentiaNum;
                this.f4007k.j(str, false, null);
                if (TextUtils.isEmpty(str) || a2.hasCouponExpo || a2.isSourceFromDialogSelected) {
                    return;
                }
                a2.hasCouponExpo = true;
                com.jd.lib.cashier.sdk.h.e.a.d().M(context, a2.code, com.jd.lib.cashier.sdk.h.h.e.b(a2));
                return;
            }
            String str2 = a2.preferentiaNum;
            com.jd.lib.cashier.sdk.pay.dialog.e eVar = a2.selectedCouponEntity;
            if (eVar != null) {
                str2 = eVar.getTitleName();
            }
            if (!TextUtils.isEmpty(str2) && !a2.hasCouponExpo && !a2.isSourceFromDialogSelected) {
                a2.hasCouponExpo = true;
                com.jd.lib.cashier.sdk.h.e.a.d().M(context, a2.code, com.jd.lib.cashier.sdk.h.h.e.b(a2));
            }
            if ("1".equals(a2.canSelectCoupon)) {
                this.f4007k.j(str2, true, new i(a2));
            } else {
                this.f4007k.j(str2, false, null);
            }
        }
    }

    private void i(@NonNull x xVar) {
        Context context = getConvertView().getContext();
        BPayPlanView bPayPlanView = (BPayPlanView) getView(R.id.stub_pay_plan_view);
        this.f4006j = bPayPlanView;
        bPayPlanView.H(AbsPayPlanView.b.PLAN_CREDIT_CARD);
        this.f4006j.x(xVar.a().planFeeEntityList, xVar.a().mianxiHighlight);
        this.f4006j.G(new d(context));
        com.jd.lib.cashier.sdk.pay.dialog.e eVar = xVar.a().selectedCouponEntity;
        if (eVar != null && !TextUtils.isEmpty(eVar.getTitleName())) {
            this.f4006j.y(eVar.getTitleName(), true);
        } else {
            this.f4006j.y("", true);
        }
        this.f4006j.E(new e(this, eVar, xVar));
        if (xVar.a().currentCreditCardBank != null) {
            this.f4006j.J(xVar.a().currentCreditCardBank.bankNameShow);
        }
        this.f4006j.F(new f(this, xVar.a()));
        BPayPlanView bPayPlanView2 = this.f4006j;
        if (bPayPlanView2 != null) {
            bPayPlanView2.onChangeSkin();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void k(Payment payment, View view) {
        if (o0.a("jxjCashierBPayChannelBankCardFloor")) {
            return;
        }
        com.jd.lib.cashier.sdk.b.i.e.f("CLICK_PAYMENT_ITEM_JXJ_ICON", new ClickJXJIconItemEvent(payment));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void l(Context context, Payment payment, View view) {
        if (o0.a("gwjCashierBPayChannelBankCardFloor")) {
            return;
        }
        if (context instanceof CashierPayActivity) {
            com.jd.lib.cashier.sdk.h.e.a.d().R((CashierPayActivity) context);
        }
        com.jd.lib.cashier.sdk.b.i.e.f("CLICK_PAYMENT_ITEM_GWJ_ICON", new ClickGWJIconItemEvent(payment.gouWuJinModel));
    }

    private void m(@NonNull x xVar) {
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

    private void p(final Payment payment) {
        List<GouWuJinWalletInfo> list;
        List<ProductInfo> list2;
        final Context context = getConvertView().getContext();
        List<String> list3 = payment.iconList;
        if (list3 != null && !list3.isEmpty()) {
            int size = payment.iconList.size();
            this.f4007k.d(24, 20);
            if (size >= 2) {
                this.f4007k.l(payment.iconList.get(0));
                this.f4007k.q(payment.iconList.get(1));
                return;
            }
            this.f4007k.r();
            if (TextUtils.equals(CashierPayChannelCode.JD_PAY_JXJ, payment.code)) {
                if (payment.showSkuList && (list2 = payment.productInfos) != null && !list2.isEmpty()) {
                    this.f4007k.e();
                    this.f4007k.m(payment.iconList.get(0), "\u81a8\u80c0\u91d1\u8bf4\u660e");
                    this.f4007k.n(new View.OnClickListener() { // from class: com.jd.lib.cashier.sdk.pay.floors.f
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view) {
                            CashierBPayChannelBankCardFloor.k(Payment.this, view);
                        }
                    });
                    return;
                }
                this.f4007k.o();
                return;
            } else if (TextUtils.equals("GOUWUJIN", payment.code)) {
                GouWuJinModel gouWuJinModel = payment.gouWuJinModel;
                if (gouWuJinModel != null && (list = gouWuJinModel.walletInfos) != null && !list.isEmpty()) {
                    if (context instanceof CashierPayActivity) {
                        com.jd.lib.cashier.sdk.h.e.a.d().S((CashierPayActivity) context);
                    }
                    this.f4007k.e();
                    this.f4007k.m(payment.iconList.get(0), "\u8d2d\u7269\u91d1\u8bf4\u660e");
                    this.f4007k.n(new View.OnClickListener() { // from class: com.jd.lib.cashier.sdk.pay.floors.e
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view) {
                            CashierBPayChannelBankCardFloor.l(context, payment, view);
                        }
                    });
                    return;
                }
                this.f4007k.o();
                return;
            } else {
                this.f4007k.l(payment.iconList.get(0));
                return;
            }
        }
        this.f4007k.p();
    }

    private void r(com.jd.lib.cashier.sdk.h.g.g gVar) {
        BPayPlanView bPayPlanView;
        if (gVar == null || (bPayPlanView = this.f4006j) == null) {
            return;
        }
        bPayPlanView.L(gVar.a().investorDoc);
    }

    private void s(com.jd.lib.cashier.sdk.h.d.a aVar) {
        List<Payment> list;
        boolean z = false;
        if (aVar != null && (list = aVar.d) != null && list.size() > 0) {
            int i2 = 0;
            while (true) {
                if (i2 < aVar.d.size()) {
                    Payment payment = aVar.d.get(i2);
                    if (payment != null && com.jd.lib.cashier.sdk.h.h.g.l(payment.code)) {
                        z = true;
                        break;
                    }
                    i2++;
                } else {
                    break;
                }
            }
        }
        this.f4007k.u(z);
    }

    private void u(x xVar) {
        PopBusinessMap popBusinessMap;
        List<PlanRowEntity> list;
        Context context = getConvertView().getContext();
        TextView textView = (TextView) getView(R.id.lib_cashier_pay_channel_floor_tip);
        ViewGroup viewGroup = (ViewGroup) getView(R.id.lib_cashier_pay_channel_floor_bottom_root);
        ViewGroup viewGroup2 = (ViewGroup) getView(R.id.lib_cashier_pay_channel_floor_tip_container);
        ViewGroup viewGroup3 = (ViewGroup) getView(R.id.lib_cashier_pay_channel_floor_agreement_container);
        TextView textView2 = (TextView) getView(R.id.lib_cashier_pay_channel_floor_agreement_name);
        View view = getView(R.id.lib_cashier_pay_channel_floor_agreement_view);
        if (viewGroup == null || viewGroup2 == null || textView == null || xVar == null || viewGroup3 == null || textView2 == null) {
            return;
        }
        PlanServiceMap planServiceMap = xVar.a().serviceMap;
        AgreementServiceMapMap agreementServiceMapMap = xVar.a().agreementServiceMap;
        BPayPlanView bPayPlanView = this.f4006j;
        if (bPayPlanView == null || bPayPlanView.getVisibility() != 0) {
            viewGroup.setVisibility(8);
        } else if (planServiceMap == null && agreementServiceMapMap == null) {
            viewGroup.setVisibility(8);
        } else if (planServiceMap != null && TextUtils.isEmpty(planServiceMap.planServiceFeeStr) && agreementServiceMapMap != null && TextUtils.isEmpty(agreementServiceMapMap.agreementName)) {
            viewGroup.setVisibility(8);
        } else {
            viewGroup.setVisibility(0);
            if (planServiceMap != null && !TextUtils.isEmpty(planServiceMap.planServiceFeeStr)) {
                textView.setVisibility(0);
                textView.setText(planServiceMap.planServiceFeeStr);
                CashierCommonPopConfig cashierCommonPopConfig = planServiceMap.planServiceFeeToast;
                if ((cashierCommonPopConfig == null || (popBusinessMap = cashierCommonPopConfig.businessMap) == null || (list = popBusinessMap.table) == null || list.isEmpty()) ? false : true) {
                    textView.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, (Drawable) null, context.getResources().getDrawable(R.drawable.lib_cashier_sdk_icon_style_light), (Drawable) null);
                    textView.setCompoundDrawablePadding(DpiUtil.dip2px(context, 4.0f));
                    textView.setOnClickListener(new j(this, context, xVar, planServiceMap));
                } else {
                    textView.setText(planServiceMap.planServiceFeeStr);
                    textView.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, (Drawable) null, (Drawable) null, (Drawable) null);
                    textView.setOnClickListener(null);
                }
                textView.setTextColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_808080));
                com.jd.lib.cashier.sdk.h.e.a.d().n(context, planServiceMap.planServiceFeeStr);
            } else {
                textView.setVisibility(8);
            }
            if (agreementServiceMapMap != null && !TextUtils.isEmpty(agreementServiceMapMap.agreementName)) {
                viewGroup3.setVisibility(0);
                textView2.setText(agreementServiceMapMap.agreementName);
                textView2.setOnClickListener(new a(this, 3000L, context, agreementServiceMapMap));
            } else {
                viewGroup3.setVisibility(8);
            }
            if (textView.getVisibility() != 0 && viewGroup3.getVisibility() != 0) {
                viewGroup.setVisibility(8);
            }
            if (viewGroup3.getVisibility() == 0 && textView.getVisibility() == 0) {
                view.setVisibility(0);
            } else {
                view.setVisibility(8);
            }
            if (textView.getVisibility() == 0 || viewGroup3.getVisibility() == 0) {
                viewGroup.setBackgroundColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_FFFFFFF));
            }
            if (view.getVisibility() == 0) {
                view.setBackgroundColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_BFBFBF, JDDarkUtil.COLOR_404040));
            }
        }
    }

    private void w(com.jd.lib.cashier.sdk.h.d.a aVar, x xVar) {
        ViewGroup.LayoutParams layoutParams = this.f4008l.getLayoutParams();
        if (aVar != null && xVar != null) {
            Payment payment = aVar.f3523e;
            if (payment != null && com.jd.lib.cashier.sdk.h.h.g.j(payment, aVar.b)) {
                if (this.f4008l.getVisibility() == 0) {
                    return;
                }
                this.f4008l.setVisibility(0);
                layoutParams.height = -2;
                layoutParams.width = -2;
            } else if (this.f4008l.getVisibility() == 8) {
                return;
            } else {
                this.f4008l.setVisibility(8);
                layoutParams.height = 0;
                layoutParams.width = 0;
            }
        } else if (this.f4008l.getVisibility() == 8) {
            return;
        } else {
            this.f4008l.setVisibility(8);
            layoutParams.height = 0;
            layoutParams.width = 0;
        }
        this.f4008l.setLayoutParams(layoutParams);
    }

    protected void f(x xVar) {
        if (xVar != null) {
            Payment a2 = xVar.a();
            Context context = getConvertView().getContext();
            if (a2 == null || a2.hasPaymentExpo || a2.isSourceFromDialogSelected) {
                return;
            }
            a2.hasPaymentExpo = true;
            if (context instanceof CashierPayActivity) {
                com.jd.lib.cashier.sdk.h.e.a.d().V((CashierPayActivity) context, a2.code, com.jd.lib.cashier.sdk.h.h.e.b(a2), a2.canUse(), a2.isCombine(), a2.defaultSelected, a2.openXjkLargePayFlag, a2.changetag, a2.hasCouponExpo ? "1" : "0");
            }
        }
    }

    @Override // com.jd.lib.cashier.sdk.core.commonfloor.floor.AbstractFloor
    public void initView(View view) {
        if (this.f4007k == null) {
            this.f4005i = (ViewStub) view.findViewById(R.id.lib_cashier_pay_channel_floor_item_stub);
            this.f4008l = (LinearLayout) view.findViewById(R.id.lib_cashier_b_pay_channel_bank_card_floor_root);
            CashierBNoLogoItemView cashierBNoLogoItemView = (CashierBNoLogoItemView) view.findViewById(R.id.lib_cashier_pay_channel_floor_item);
            this.f4007k = cashierBNoLogoItemView;
            cashierBNoLogoItemView.s(new b(this, 3000L));
        }
    }

    protected boolean j(@NonNull x xVar) {
        Payment a2 = xVar.a();
        boolean z = a2.selected;
        boolean a3 = com.jd.lib.cashier.sdk.h.h.g.a(a2.code);
        boolean equals = "5".equals(a2.status);
        boolean equals2 = CashierPayChannelCode.JD_PAY_CREDIT.equals(a2.code);
        boolean equals3 = TextUtils.equals(a2.status, "3");
        boolean z2 = a2.baitiaoPlanInfo != null && a3;
        ViewStub viewStub = this.f4005i;
        if (viewStub == null) {
            return false;
        }
        if (equals3) {
            viewStub.setVisibility(8);
            return false;
        } else if (equals || !(z || z2)) {
            viewStub.setVisibility(8);
            return false;
        } else if (equals2) {
            viewStub.setVisibility(0);
            return true;
        } else if (a3) {
            List<IPlanItemViewEntity> list = a2.planFeeEntityList;
            if (list != null && list.size() > 0) {
                this.f4005i.setVisibility(0);
                return true;
            }
            this.f4005i.setVisibility(8);
            return false;
        } else {
            viewStub.setVisibility(8);
            return false;
        }
    }

    protected void n(@NonNull x xVar) {
        m(xVar);
        Payment a2 = xVar.a();
        List<IPlanItemViewEntity> list = a2.planFeeEntityList;
        if (com.jd.lib.cashier.sdk.h.h.g.a(a2.code)) {
            g(list, a2.mianxiHighlight);
        } else if (com.jd.lib.cashier.sdk.h.h.g.d(a2.code)) {
            i(xVar);
        }
    }

    @Override // com.jd.lib.cashier.sdk.core.commonfloor.floor.AbstractFloor
    /* renamed from: o  reason: merged with bridge method [inline-methods] */
    public void b(com.jd.lib.cashier.sdk.h.d.a aVar, com.jd.lib.cashier.sdk.h.g.g gVar) {
        this.f4009m = gVar;
        if (gVar != null) {
            w(aVar, gVar);
            if (j(gVar)) {
                getConvertView().setPadding(0, 0, 0, DpiUtil.dip2px(getConvertView().getContext(), 12.0f));
                n(gVar);
            } else {
                getConvertView().setPadding(0, 0, 0, 0);
            }
            t(gVar);
            u(gVar);
            s(aVar);
            r(gVar);
            v();
            q(gVar);
            f(gVar);
        }
    }

    protected void q(x xVar) {
        UpdateHeaderFloorInfo updateHeaderFloorInfo;
        if (xVar.a().selected) {
            TopPriceMtaObject topPriceMtaObject = new TopPriceMtaObject();
            com.jd.lib.cashier.sdk.pay.dialog.e eVar = xVar.a().selectedCouponEntity;
            topPriceMtaObject.code = xVar.a().code;
            topPriceMtaObject.uniqueChannelId = com.jd.lib.cashier.sdk.h.h.e.b(xVar.a());
            if (eVar == null) {
                topPriceMtaObject.couponType = "0";
                updateHeaderFloorInfo = new UpdateHeaderFloorInfo(null, topPriceMtaObject);
            } else {
                topPriceMtaObject.couponType = eVar.getCutOffType();
                updateHeaderFloorInfo = new UpdateHeaderFloorInfo(eVar.getTopPriceAnimationInfo(), topPriceMtaObject);
            }
            com.jd.lib.cashier.sdk.b.i.e.f("update_top_floor_price_info", updateHeaderFloorInfo);
        }
    }

    protected void t(x xVar) {
        Payment a2 = xVar.a();
        CashierBNoLogoItemView cashierBNoLogoItemView = this.f4007k;
        if (cashierBNoLogoItemView != null) {
            cashierBNoLogoItemView.i(a2.channelName);
            p(a2);
            h(xVar);
            this.f4007k.t();
            this.f4007k.h(a2.moreInfoTip);
            if (TextUtils.equals("3", a2.status)) {
                this.f4007k.b();
            }
            this.f4007k.a("3".equals(a2.status));
        }
    }

    protected void v() {
        if (this.f4009m == null || this.f4006j == null) {
            return;
        }
        Context context = getConvertView().getContext();
        if (this.f4006j.getVisibility() == 0) {
            BaiTiaoExtraTip baiTiaoExtraTip = this.f4009m.a().baiTiaoExtraTip;
            this.f4006j.D(new g(baiTiaoExtraTip, context));
            this.f4006j.K(baiTiaoExtraTip, new h(this, context));
        }
    }
}

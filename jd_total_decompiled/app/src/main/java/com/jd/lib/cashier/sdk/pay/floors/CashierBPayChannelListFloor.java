package com.jd.lib.cashier.sdk.pay.floors;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.view.ViewTreeObserver;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import com.jd.cashier.app.jdlibcutter.protocol.utils.DpiUtil;
import com.jd.lib.cashier.sdk.R;
import com.jd.lib.cashier.sdk.core.commonfloor.floor.AbstractFloor;
import com.jd.lib.cashier.sdk.core.model.BaiTiaoExtraTip;
import com.jd.lib.cashier.sdk.core.model.CashierCommonPopConfig;
import com.jd.lib.cashier.sdk.core.model.PlanRowEntity;
import com.jd.lib.cashier.sdk.core.model.PopBusinessMap;
import com.jd.lib.cashier.sdk.core.ui.CashierBItemView;
import com.jd.lib.cashier.sdk.core.ui.widget.AbsPayPlanView;
import com.jd.lib.cashier.sdk.core.ui.widget.BPayPlanView;
import com.jd.lib.cashier.sdk.core.ui.widget.IPlanItemViewEntity;
import com.jd.lib.cashier.sdk.core.ui.widget.OnPlanViewClickListener;
import com.jd.lib.cashier.sdk.core.utils.JDDarkUtil;
import com.jd.lib.cashier.sdk.core.utils.o0;
import com.jd.lib.cashier.sdk.core.utils.r;
import com.jd.lib.cashier.sdk.h.g.x;
import com.jd.lib.cashier.sdk.pay.aac.viewmodel.CashierPayViewModel;
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
import com.jd.lib.cashier.sdk.pay.bean.syncevent.ClickPayChannelItemEvent;
import com.jd.lib.cashier.sdk.pay.bean.syncevent.UpdateHeaderFloorInfo;
import com.jd.lib.cashier.sdk.pay.view.CashierPayActivity;
import com.jingdong.common.cashiernative.CashierPayChannelCode;
import java.util.List;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;

/* loaded from: classes14.dex */
public class CashierBPayChannelListFloor extends AbstractFloor<com.jd.lib.cashier.sdk.h.d.a, com.jd.lib.cashier.sdk.h.g.k> {

    /* renamed from: i  reason: collision with root package name */
    private ViewStub f4032i;

    /* renamed from: j  reason: collision with root package name */
    public BPayPlanView f4033j;

    /* renamed from: k  reason: collision with root package name */
    protected CashierBItemView f4034k;

    /* renamed from: l  reason: collision with root package name */
    private com.jd.lib.cashier.sdk.h.g.k f4035l;

    /* renamed from: m  reason: collision with root package name */
    private final Runnable f4036m;

    /* renamed from: n  reason: collision with root package name */
    private Payment f4037n;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public class a implements View.OnClickListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ Payment f4038g;

        a(Payment payment) {
            this.f4038g = payment;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            Payment payment = this.f4038g;
            if (payment != null && payment.equals(CashierBPayChannelListFloor.this.f4037n) && o0.a("CashierBPayChannelListFloorcoupon")) {
                return;
            }
            if (com.jd.lib.cashier.sdk.h.h.g.c(this.f4038g.code)) {
                com.jd.lib.cashier.sdk.b.i.e.f("CLICK_BANK_COUPON_ON_PAYMENT_ITEM", new ClickBankCouponItemEvent(this.f4038g));
            }
            if (com.jd.lib.cashier.sdk.h.h.g.a(this.f4038g.code)) {
                r.b("CashierBPayChannelListFloor", "EventBusManager sendEvent CLICK_COUPON_ON_PAYMENT_CHANNEL");
                com.jd.lib.cashier.sdk.b.i.e.f("click_baitiao_COUPON_on_item_key", new ClickBaiTiaoCouponItemEvent(this.f4038g));
            }
            CashierBPayChannelListFloor.this.f4037n = this.f4038g;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public class b implements View.OnClickListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ Context f4040g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ x f4041h;

        /* renamed from: i  reason: collision with root package name */
        final /* synthetic */ PlanServiceMap f4042i;

        b(CashierBPayChannelListFloor cashierBPayChannelListFloor, Context context, x xVar, PlanServiceMap planServiceMap) {
            this.f4040g = context;
            this.f4041h = xVar;
            this.f4042i = planServiceMap;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            Context context = this.f4040g;
            if (context instanceof CashierPayActivity) {
                CashierPayActivity cashierPayActivity = (CashierPayActivity) context;
                Payment a = this.f4041h.a();
                com.jd.lib.cashier.sdk.h.e.a.d().O(cashierPayActivity, a.code, com.jd.lib.cashier.sdk.h.h.e.b(a));
                com.jd.lib.cashier.sdk.b.d.a.j(cashierPayActivity, this.f4042i.planServiceFeeToast);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public class c extends com.jd.lib.cashier.sdk.core.utils.b {

        /* renamed from: j  reason: collision with root package name */
        final /* synthetic */ Context f4043j;

        /* renamed from: k  reason: collision with root package name */
        final /* synthetic */ AgreementServiceMapMap f4044k;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        c(CashierBPayChannelListFloor cashierBPayChannelListFloor, long j2, Context context, AgreementServiceMapMap agreementServiceMapMap) {
            super(j2);
            this.f4043j = context;
            this.f4044k = agreementServiceMapMap;
        }

        @Override // com.jd.lib.cashier.sdk.core.utils.b
        public void b(View view) {
            Context context = this.f4043j;
            if (context instanceof CashierPayActivity) {
                AgreementServiceMapMap agreementServiceMapMap = this.f4044k;
                new com.jd.lib.cashier.sdk.pay.dialog.a().e((CashierPayActivity) context, agreementServiceMapMap.agreementUrl, agreementServiceMapMap.agreementTitle);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public class d implements ViewTreeObserver.OnGlobalLayoutListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ x f4045g;

        d(x xVar) {
            this.f4045g = xVar;
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            x xVar;
            int i2 = Build.VERSION.SDK_INT;
            if (i2 >= 16) {
                CashierBPayChannelListFloor.this.f4034k.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            } else if (i2 >= 14) {
                CashierBPayChannelListFloor.this.f4034k.getViewTreeObserver().removeGlobalOnLayoutListener(this);
            }
            if (CashierBPayChannelListFloor.this.f4034k == null || (xVar = this.f4045g) == null || !xVar.a().selected || !this.f4045g.a().clickEvent) {
                return;
            }
            this.f4045g.a().clickEvent = false;
            CashierBPayChannelListFloor.this.f4034k.clearFocus();
            CashierBPayChannelListFloor.this.f4034k.sendAccessibilityEvent(65536);
            CashierBPayChannelListFloor.this.f4034k.sendAccessibilityEvent(4);
            CashierBPayChannelListFloor.this.f4034k.sendAccessibilityEvent(8);
            CashierBPayChannelListFloor.this.f4034k.sendAccessibilityEvent(32768);
        }
    }

    /* loaded from: classes14.dex */
    class e implements View.OnClickListener {
        e() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (CashierBPayChannelListFloor.this.f4036m != null) {
                CashierBPayChannelListFloor.this.f4036m.run();
            }
        }
    }

    /* loaded from: classes14.dex */
    class f implements Runnable {
        f() {
        }

        @Override // java.lang.Runnable
        public void run() {
            CashierBPayChannelListFloor cashierBPayChannelListFloor = CashierBPayChannelListFloor.this;
            if (cashierBPayChannelListFloor.f4034k != null) {
                Context context = cashierBPayChannelListFloor.getConvertView().getContext();
                Payment a = CashierBPayChannelListFloor.this.f4035l.a();
                if ((context instanceof CashierPayActivity ? ((CashierPayViewModel) ViewModelProviders.of((CashierPayActivity) context).get(CashierPayViewModel.class)).b().O : null) != a && com.jd.lib.cashier.sdk.h.h.g.g(a.status)) {
                    com.jd.lib.cashier.sdk.b.i.e.f("cashier_item_click", new ClickPayChannelItemEvent(a));
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public class g implements OnPlanViewClickListener {
        final /* synthetic */ Context a;

        g(Context context) {
            this.a = context;
        }

        @Override // com.jd.lib.cashier.sdk.core.ui.widget.OnPlanViewClickListener
        public void onClick(@NonNull IPlanItemViewEntity iPlanItemViewEntity, @Nullable IPlanItemViewEntity iPlanItemViewEntity2) {
            if ((iPlanItemViewEntity instanceof PlanFeeEntity) && (iPlanItemViewEntity2 instanceof PlanFeeEntity)) {
                PlanFeeEntity planFeeEntity = (PlanFeeEntity) iPlanItemViewEntity;
                PlanFeeEntity planFeeEntity2 = (PlanFeeEntity) iPlanItemViewEntity2;
                r.b("CashierBPayChannelListFloor", "payPlanItemView initializePayPlanView payPlanView.setOnItemClickListener");
                Payment a = CashierBPayChannelListFloor.this.f4035l.a();
                com.jd.lib.cashier.sdk.h.e.a.d().F(this.a, a.code, planFeeEntity.getPlan(), planFeeEntity.getSkuSplitFlag() ? "1" : "0");
                com.jd.lib.cashier.sdk.b.i.e.g("CLICK_PAYMENT_CHANNEL_PAY_PLAN_ITEM", "baitiao_plan", new ClickBaiTiaoPayPlanItemEvent(planFeeEntity, planFeeEntity2, a));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public class h implements OnPlanViewClickListener {
        final /* synthetic */ Context a;

        h(Context context) {
            this.a = context;
        }

        @Override // com.jd.lib.cashier.sdk.core.ui.widget.OnPlanViewClickListener
        public void onClick(@NonNull IPlanItemViewEntity iPlanItemViewEntity, @Nullable IPlanItemViewEntity iPlanItemViewEntity2) {
            if (iPlanItemViewEntity2 != null && (iPlanItemViewEntity instanceof CreditCardPlan) && (iPlanItemViewEntity2 instanceof CreditCardPlan)) {
                CreditCardPlan creditCardPlan = (CreditCardPlan) iPlanItemViewEntity;
                r.b("CashierBPayChannelListFloor", "payPlanItemView initializePayPlanView payPlanView.setOnItemClickListener");
                Payment a = CashierBPayChannelListFloor.this.f4035l.a();
                com.jd.lib.cashier.sdk.h.e.a.d().F(this.a, a.code, creditCardPlan.plan, "");
                com.jd.lib.cashier.sdk.b.i.e.g("CLICK_PAYMENT_CHANNEL_PAY_PLAN_ITEM", "credit_card_plan", new ClickCreditCardPlanItemEvent(creditCardPlan, (CreditCardPlan) iPlanItemViewEntity2, a));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public class i implements Function1<String, Unit> {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ com.jd.lib.cashier.sdk.pay.dialog.e f4049g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ x f4050h;

        i(CashierBPayChannelListFloor cashierBPayChannelListFloor, com.jd.lib.cashier.sdk.pay.dialog.e eVar, x xVar) {
            this.f4049g = eVar;
            this.f4050h = xVar;
        }

        @Override // kotlin.jvm.functions.Function1
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public Unit invoke(String str) {
            if (!o0.a("CashierBPayChannelListFloor") && (this.f4049g instanceof CouponEntity)) {
                com.jd.lib.cashier.sdk.b.i.e.f("click_credit_card_COUPON_on_item_key", new ClickCreditCardCouponItemEvent(this.f4050h.a()));
            }
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public class j implements Function0<Unit> {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ Payment f4051g;

        j(CashierBPayChannelListFloor cashierBPayChannelListFloor, Payment payment) {
            this.f4051g = payment;
        }

        @Override // kotlin.jvm.functions.Function0
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public Unit invoke() {
            if (o0.a("CashierBPayChannelListFloor")) {
                return null;
            }
            com.jd.lib.cashier.sdk.b.i.e.f("click_credit_card_bank_on_item", new ClickCreditCardBankItemEvent(this.f4051g));
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public class k extends com.jd.lib.cashier.sdk.core.utils.b {

        /* renamed from: j  reason: collision with root package name */
        final /* synthetic */ Context f4052j;

        /* renamed from: k  reason: collision with root package name */
        final /* synthetic */ String f4053k;

        /* renamed from: l  reason: collision with root package name */
        final /* synthetic */ String f4054l;

        k(CashierBPayChannelListFloor cashierBPayChannelListFloor, Context context, String str, String str2) {
            this.f4052j = context;
            this.f4053k = str;
            this.f4054l = str2;
        }

        @Override // com.jd.lib.cashier.sdk.core.utils.b
        public void b(View view) {
            Context context = this.f4052j;
            if (context instanceof FragmentActivity) {
                com.jd.lib.cashier.sdk.b.d.a.g((FragmentActivity) context, this.f4053k, this.f4054l);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public class l implements View.OnClickListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ BaiTiaoExtraTip f4055g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ Context f4056h;

        l(BaiTiaoExtraTip baiTiaoExtraTip, Context context) {
            this.f4055g = baiTiaoExtraTip;
            this.f4056h = context;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            CashierCommonPopConfig cashierCommonPopConfig;
            BaiTiaoExtraTip baiTiaoExtraTip = this.f4055g;
            if (baiTiaoExtraTip == null || (cashierCommonPopConfig = baiTiaoExtraTip.extraTipToast) == null) {
                return;
            }
            com.jd.lib.cashier.sdk.b.d.a.c(this.f4056h, cashierCommonPopConfig);
            Payment a = CashierBPayChannelListFloor.this.f4035l.a();
            if (this.f4056h instanceof CashierPayActivity) {
                com.jd.lib.cashier.sdk.h.e.a.d().l((CashierPayActivity) this.f4056h, a.code);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public class m implements Runnable {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ Context f4058g;

        m(CashierBPayChannelListFloor cashierBPayChannelListFloor, Context context) {
            this.f4058g = context;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.f4058g instanceof CashierPayActivity) {
                com.jd.lib.cashier.sdk.h.e.a.d().m((CashierPayActivity) this.f4058g);
            }
        }
    }

    public CashierBPayChannelListFloor(View view) {
        super(view);
        this.f4036m = new f();
    }

    private void h(List<IPlanItemViewEntity> list, String str) {
        Context context = getConvertView().getContext();
        BPayPlanView bPayPlanView = (BPayPlanView) getView(R.id.stub_pay_plan_view);
        this.f4033j = bPayPlanView;
        bPayPlanView.H(AbsPayPlanView.b.PLAN_BAITIAO);
        this.f4033j.x(list, str);
        this.f4033j.G(new g(context));
        BPayPlanView bPayPlanView2 = this.f4033j;
        if (bPayPlanView2 != null) {
            bPayPlanView2.onChangeSkin();
        }
    }

    private void i(x xVar) {
        Payment a2 = xVar.a();
        Context context = getConvertView().getContext();
        if (a2 == null) {
            return;
        }
        if (CashierPayChannelCode.JD_PAY_CREDIT.equals(a2.code)) {
            this.f4034k.n();
        } else if ("5".equals(a2.status) && com.jd.lib.cashier.sdk.h.h.g.a(a2.code)) {
            this.f4034k.n();
        } else {
            if ((com.jd.lib.cashier.sdk.h.h.g.a(a2.code) || com.jd.lib.cashier.sdk.h.h.g.c(a2.code) || com.jd.lib.cashier.sdk.h.h.g.f(a2.code)) ? false : true) {
                String str = a2.preferentiaNum;
                this.f4034k.m(str, false, null);
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
                this.f4034k.m(str2, true, new a(a2));
            } else {
                this.f4034k.m(str2, false, null);
            }
        }
    }

    private void j(@NonNull x xVar) {
        Context context = getConvertView().getContext();
        BPayPlanView bPayPlanView = (BPayPlanView) getView(R.id.stub_pay_plan_view);
        this.f4033j = bPayPlanView;
        bPayPlanView.H(AbsPayPlanView.b.PLAN_CREDIT_CARD);
        this.f4033j.x(xVar.a().planFeeEntityList, xVar.a().mianxiHighlight);
        this.f4033j.G(new h(context));
        com.jd.lib.cashier.sdk.pay.dialog.e eVar = xVar.a().selectedCouponEntity;
        if (eVar != null && !TextUtils.isEmpty(eVar.getTitleName())) {
            this.f4033j.y(eVar.getTitleName(), true);
        } else {
            this.f4033j.y("", true);
        }
        this.f4033j.E(new i(this, eVar, xVar));
        if (xVar.a().currentCreditCardBank != null) {
            this.f4033j.J(xVar.a().currentCreditCardBank.bankNameShow);
        }
        this.f4033j.F(new j(this, xVar.a()));
        BPayPlanView bPayPlanView2 = this.f4033j;
        if (bPayPlanView2 != null) {
            bPayPlanView2.onChangeSkin();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void l(Payment payment, View view) {
        if (o0.a("jxjCashierBPayChannelListFloor")) {
            return;
        }
        com.jd.lib.cashier.sdk.b.i.e.f("CLICK_PAYMENT_ITEM_JXJ_ICON", new ClickJXJIconItemEvent(payment));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void m(Context context, Payment payment, View view) {
        if (o0.a("gwjCashierBPayChannelListFloor")) {
            return;
        }
        if (context instanceof CashierPayActivity) {
            com.jd.lib.cashier.sdk.h.e.a.d().R((CashierPayActivity) context);
        }
        com.jd.lib.cashier.sdk.b.i.e.f("CLICK_PAYMENT_ITEM_GWJ_ICON", new ClickGWJIconItemEvent(payment.gouWuJinModel));
    }

    private void o(@NonNull x xVar) {
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

    private void r(final Payment payment) {
        List<GouWuJinWalletInfo> list;
        List<ProductInfo> list2;
        final Context context = getConvertView().getContext();
        List<String> list3 = payment.iconList;
        if (list3 != null && !list3.isEmpty()) {
            int size = payment.iconList.size();
            this.f4034k.f(24, 20);
            if (size >= 2) {
                this.f4034k.o(payment.iconList.get(0));
                this.f4034k.t(payment.iconList.get(1));
                return;
            }
            this.f4034k.u();
            if (TextUtils.equals(CashierPayChannelCode.JD_PAY_JXJ, payment.code)) {
                if (payment.showSkuList && (list2 = payment.productInfos) != null && !list2.isEmpty()) {
                    this.f4034k.g();
                    this.f4034k.p(payment.iconList.get(0), "\u81a8\u80c0\u91d1\u8bf4\u660e");
                    this.f4034k.q(new View.OnClickListener() { // from class: com.jd.lib.cashier.sdk.pay.floors.h
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view) {
                            CashierBPayChannelListFloor.l(Payment.this, view);
                        }
                    });
                    return;
                }
                this.f4034k.r();
                return;
            } else if (TextUtils.equals("GOUWUJIN", payment.code)) {
                GouWuJinModel gouWuJinModel = payment.gouWuJinModel;
                if (gouWuJinModel != null && (list = gouWuJinModel.walletInfos) != null && !list.isEmpty()) {
                    if (context instanceof CashierPayActivity) {
                        com.jd.lib.cashier.sdk.h.e.a.d().S((CashierPayActivity) context);
                    }
                    this.f4034k.g();
                    this.f4034k.p(payment.iconList.get(0), "\u8d2d\u7269\u91d1\u8bf4\u660e");
                    this.f4034k.q(new View.OnClickListener() { // from class: com.jd.lib.cashier.sdk.pay.floors.g
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view) {
                            CashierBPayChannelListFloor.m(context, payment, view);
                        }
                    });
                    return;
                }
                this.f4034k.r();
                return;
            } else {
                this.f4034k.o(payment.iconList.get(0));
                return;
            }
        }
        this.f4034k.s();
    }

    private void s(Payment payment, boolean z) {
        if ("9".equals(payment.status)) {
            this.f4034k.c();
            return;
        }
        this.f4034k.A();
        this.f4034k.e(z);
    }

    private void u(com.jd.lib.cashier.sdk.h.g.k kVar) {
        BPayPlanView bPayPlanView;
        if (kVar == null || (bPayPlanView = this.f4033j) == null) {
            return;
        }
        bPayPlanView.L(kVar.a().investorDoc);
    }

    private void w(x xVar) {
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
        BPayPlanView bPayPlanView = this.f4033j;
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
                    textView.setOnClickListener(new b(this, context, xVar, planServiceMap));
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
                textView2.setOnClickListener(new c(this, 3000L, context, agreementServiceMapMap));
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

    protected void g(x xVar) {
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
        if (this.f4034k == null) {
            this.f4032i = (ViewStub) view.findViewById(R.id.lib_cashier_pay_channel_floor_item_stub);
            CashierBItemView cashierBItemView = (CashierBItemView) view.findViewById(R.id.lib_cashier_pay_channel_floor_item);
            this.f4034k = cashierBItemView;
            cashierBItemView.k(new e());
        }
    }

    protected boolean k(@NonNull x xVar) {
        Payment a2 = xVar.a();
        if (a2 == null) {
            return false;
        }
        boolean z = xVar.a().selected;
        boolean a3 = com.jd.lib.cashier.sdk.h.h.g.a(a2.code);
        boolean equals = "5".equals(a2.status);
        boolean equals2 = CashierPayChannelCode.JD_PAY_CREDIT.equals(a2.code);
        boolean equals3 = TextUtils.equals(a2.status, "3");
        boolean z2 = a2.baitiaoPlanInfo != null && a3;
        ViewStub viewStub = this.f4032i;
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
            List<IPlanItemViewEntity> list = xVar.a().planFeeEntityList;
            if (list != null && list.size() > 0) {
                this.f4032i.setVisibility(0);
                return true;
            }
            this.f4032i.setVisibility(8);
            return false;
        } else {
            viewStub.setVisibility(8);
            return false;
        }
    }

    protected void n(x xVar) {
        try {
            if (this.f4034k == null || !com.jd.lib.cashier.sdk.core.utils.g.a(getConvertView().getContext())) {
                return;
            }
            this.f4034k.getViewTreeObserver().addOnGlobalLayoutListener(new d(xVar));
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    protected void p(@NonNull x xVar) {
        o(xVar);
        List<IPlanItemViewEntity> list = xVar.a().planFeeEntityList;
        Payment a2 = xVar.a();
        if (com.jd.lib.cashier.sdk.h.h.g.a(a2.code)) {
            h(list, xVar.a().mianxiHighlight);
        } else if (com.jd.lib.cashier.sdk.h.h.g.d(a2.code)) {
            j(xVar);
        }
    }

    @Override // com.jd.lib.cashier.sdk.core.commonfloor.floor.AbstractFloor
    /* renamed from: q  reason: merged with bridge method [inline-methods] */
    public void b(com.jd.lib.cashier.sdk.h.d.a aVar, com.jd.lib.cashier.sdk.h.g.k kVar) {
        this.f4035l = kVar;
        if (kVar != null) {
            if (k(kVar)) {
                getConvertView().setPadding(0, 0, 0, DpiUtil.dip2px(getConvertView().getContext(), 12.0f));
                p(kVar);
            } else {
                getConvertView().setPadding(0, 0, 0, 0);
            }
            v(kVar);
            w(kVar);
            u(kVar);
            x();
            t(kVar);
            g(kVar);
            n(kVar);
        }
    }

    protected void t(x xVar) {
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

    protected void v(x xVar) {
        String str;
        Context context = getConvertView().getContext();
        Payment a2 = xVar.a();
        CashierBItemView cashierBItemView = this.f4034k;
        if (cashierBItemView != null) {
            cashierBItemView.v(a2.logo);
            if (!TextUtils.isEmpty(a2.channelDesc)) {
                this.f4034k.l(a2.channelDesc);
            } else {
                this.f4034k.l(a2.channelName);
            }
            this.f4034k.x(a2.tip);
            String str2 = "";
            if (!TextUtils.isEmpty(a2.extraInfo)) {
                str2 = a2.extraInfo;
                str = "";
            } else {
                CashierCommonPopConfig cashierCommonPopConfig = a2.toastModel;
                if (cashierCommonPopConfig != null) {
                    str2 = cashierCommonPopConfig.message;
                    str = cashierCommonPopConfig.confirmBtn;
                } else {
                    str = "";
                }
            }
            this.f4034k.y(str2, new k(this, context, str2, str));
            this.f4034k.w(a2.statusDesc);
            r(a2);
            i(xVar);
            this.f4034k.z();
            this.f4034k.j(a2.moreInfoTip);
            if (TextUtils.equals("3", a2.status)) {
                this.f4034k.b();
            }
            s(a2, a2.selected);
            this.f4034k.a("3".equals(a2.status));
        }
    }

    protected void x() {
        if (this.f4035l == null || this.f4033j == null) {
            return;
        }
        Context context = getConvertView().getContext();
        if (this.f4033j.getVisibility() == 0) {
            BaiTiaoExtraTip baiTiaoExtraTip = this.f4035l.a().baiTiaoExtraTip;
            this.f4033j.D(new l(baiTiaoExtraTip, context));
            this.f4033j.K(baiTiaoExtraTip, new m(this, context));
        }
    }
}

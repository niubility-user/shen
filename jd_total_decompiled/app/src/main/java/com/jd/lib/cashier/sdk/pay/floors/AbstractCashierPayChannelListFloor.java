package com.jd.lib.cashier.sdk.pay.floors;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewStub;
import android.view.ViewTreeObserver;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import com.jd.lib.cashier.sdk.R;
import com.jd.lib.cashier.sdk.core.commonfloor.floor.AbstractFloor;
import com.jd.lib.cashier.sdk.core.model.BaiTiaoExtraTip;
import com.jd.lib.cashier.sdk.core.model.CashierCommonPopConfig;
import com.jd.lib.cashier.sdk.core.ui.CashierRegulatorItemView;
import com.jd.lib.cashier.sdk.core.ui.widget.AbsPayPlanView;
import com.jd.lib.cashier.sdk.core.ui.widget.IPlanItemViewEntity;
import com.jd.lib.cashier.sdk.core.ui.widget.OnPlanViewClickListener;
import com.jd.lib.cashier.sdk.core.ui.widget.PayPlanView;
import com.jd.lib.cashier.sdk.core.utils.JDDarkUtil;
import com.jd.lib.cashier.sdk.core.utils.o0;
import com.jd.lib.cashier.sdk.core.utils.r;
import com.jd.lib.cashier.sdk.d.b.a;
import com.jd.lib.cashier.sdk.h.g.x;
import com.jd.lib.cashier.sdk.pay.aac.viewmodel.CashierPayViewModel;
import com.jd.lib.cashier.sdk.pay.bean.GouWuJinModel;
import com.jd.lib.cashier.sdk.pay.bean.GouWuJinWalletInfo;
import com.jd.lib.cashier.sdk.pay.bean.Payment;
import com.jd.lib.cashier.sdk.pay.bean.PlanFeeEntity;
import com.jd.lib.cashier.sdk.pay.bean.ProductInfo;
import com.jd.lib.cashier.sdk.pay.bean.TopPriceMtaObject;
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
import com.jd.lib.cashier.sdk.pay.bean.syncevent.ClickPayChannelItemEvent;
import com.jd.lib.cashier.sdk.pay.bean.syncevent.UpdateHeaderFloorInfo;
import com.jd.lib.cashier.sdk.pay.view.CashierPayActivity;
import com.jingdong.common.cashiernative.CashierPayChannelCode;
import java.util.List;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;

/* loaded from: classes14.dex */
public abstract class AbstractCashierPayChannelListFloor extends AbstractFloor<com.jd.lib.cashier.sdk.h.d.a, x> {

    /* renamed from: i  reason: collision with root package name */
    private ViewStub f3953i;

    /* renamed from: j  reason: collision with root package name */
    public PayPlanView f3954j;

    /* renamed from: k  reason: collision with root package name */
    protected CashierRegulatorItemView f3955k;

    /* renamed from: l  reason: collision with root package name */
    private x f3956l;

    /* renamed from: m  reason: collision with root package name */
    private final Runnable f3957m;

    /* renamed from: n  reason: collision with root package name */
    private Payment f3958n;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public class a implements View.OnClickListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ Payment f3959g;

        a(Payment payment) {
            this.f3959g = payment;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            Payment payment = this.f3959g;
            if (payment != null && payment.equals(AbstractCashierPayChannelListFloor.this.f3958n) && o0.a("AbstractCashierPayChannelListFloorcoupon")) {
                return;
            }
            if (com.jd.lib.cashier.sdk.h.h.g.c(this.f3959g.code)) {
                com.jd.lib.cashier.sdk.b.i.e.f("CLICK_BANK_COUPON_ON_PAYMENT_ITEM", new ClickBankCouponItemEvent(this.f3959g));
            }
            if (com.jd.lib.cashier.sdk.h.h.g.a(this.f3959g.code)) {
                r.b("AbstractCashierPayChannelListFloor", "EventBusManager sendEvent CLICK_COUPON_ON_PAYMENT_CHANNEL");
                com.jd.lib.cashier.sdk.b.i.e.f("click_baitiao_COUPON_on_item_key", new ClickBaiTiaoCouponItemEvent(this.f3959g));
            }
            AbstractCashierPayChannelListFloor.this.f3958n = this.f3959g;
        }
    }

    /* loaded from: classes14.dex */
    class b implements ViewTreeObserver.OnGlobalLayoutListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ x f3961g;

        b(x xVar) {
            this.f3961g = xVar;
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            x xVar;
            int i2 = Build.VERSION.SDK_INT;
            if (i2 >= 16) {
                AbstractCashierPayChannelListFloor.this.f3955k.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            } else if (i2 >= 14) {
                AbstractCashierPayChannelListFloor.this.f3955k.getViewTreeObserver().removeGlobalOnLayoutListener(this);
            }
            if (AbstractCashierPayChannelListFloor.this.f3955k == null || (xVar = this.f3961g) == null || !xVar.a().selected || !this.f3961g.a().clickEvent) {
                return;
            }
            this.f3961g.a().clickEvent = false;
            AbstractCashierPayChannelListFloor.this.f3955k.clearFocus();
            AbstractCashierPayChannelListFloor.this.f3955k.sendAccessibilityEvent(65536);
            AbstractCashierPayChannelListFloor.this.f3955k.sendAccessibilityEvent(4);
            AbstractCashierPayChannelListFloor.this.f3955k.sendAccessibilityEvent(8);
            AbstractCashierPayChannelListFloor.this.f3955k.sendAccessibilityEvent(32768);
        }
    }

    /* loaded from: classes14.dex */
    class c implements View.OnClickListener {
        c() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (AbstractCashierPayChannelListFloor.this.f3957m != null) {
                AbstractCashierPayChannelListFloor.this.f3957m.run();
            }
        }
    }

    /* loaded from: classes14.dex */
    class d implements Runnable {
        d() {
        }

        @Override // java.lang.Runnable
        public void run() {
            AbstractCashierPayChannelListFloor abstractCashierPayChannelListFloor = AbstractCashierPayChannelListFloor.this;
            if (abstractCashierPayChannelListFloor.f3955k != null) {
                Context context = abstractCashierPayChannelListFloor.getConvertView().getContext();
                Payment a = AbstractCashierPayChannelListFloor.this.f3956l.a();
                if ((context instanceof CashierPayActivity ? ((CashierPayViewModel) ViewModelProviders.of((CashierPayActivity) context).get(CashierPayViewModel.class)).b().O : null) == a) {
                    return;
                }
                if ("moreInfo".equals(a.code)) {
                    com.jd.lib.cashier.sdk.b.i.e.f("CLICK_OTHER_JD_PAYMENT_CHANNEL_ITEM", new ClickOtherJDPayItemEvent());
                } else if (com.jd.lib.cashier.sdk.h.h.g.g(a.status)) {
                    com.jd.lib.cashier.sdk.b.i.e.f("cashier_item_click", new ClickPayChannelItemEvent(a));
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public class e implements OnPlanViewClickListener {
        final /* synthetic */ Context a;

        e(Context context) {
            this.a = context;
        }

        @Override // com.jd.lib.cashier.sdk.core.ui.widget.OnPlanViewClickListener
        public void onClick(@NonNull IPlanItemViewEntity iPlanItemViewEntity, @Nullable IPlanItemViewEntity iPlanItemViewEntity2) {
            if ((iPlanItemViewEntity instanceof PlanFeeEntity) && (iPlanItemViewEntity2 instanceof PlanFeeEntity)) {
                PlanFeeEntity planFeeEntity = (PlanFeeEntity) iPlanItemViewEntity;
                PlanFeeEntity planFeeEntity2 = (PlanFeeEntity) iPlanItemViewEntity2;
                r.b("AbstractCashierPayChannelListFloor", "payPlanItemView initializePayPlanView payPlanView.setOnItemClickListener");
                Payment a = AbstractCashierPayChannelListFloor.this.f3956l.a();
                com.jd.lib.cashier.sdk.h.e.a.d().F(this.a, a.code, planFeeEntity.getPlan(), planFeeEntity.getSkuSplitFlag() ? "1" : "0");
                com.jd.lib.cashier.sdk.b.i.e.g("CLICK_PAYMENT_CHANNEL_PAY_PLAN_ITEM", "baitiao_plan", new ClickBaiTiaoPayPlanItemEvent(planFeeEntity, planFeeEntity2, a));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public class f implements OnPlanViewClickListener {
        final /* synthetic */ Context a;

        f(Context context) {
            this.a = context;
        }

        @Override // com.jd.lib.cashier.sdk.core.ui.widget.OnPlanViewClickListener
        public void onClick(@NonNull IPlanItemViewEntity iPlanItemViewEntity, @Nullable IPlanItemViewEntity iPlanItemViewEntity2) {
            if (iPlanItemViewEntity2 != null && (iPlanItemViewEntity instanceof CreditCardPlan) && (iPlanItemViewEntity2 instanceof CreditCardPlan)) {
                CreditCardPlan creditCardPlan = (CreditCardPlan) iPlanItemViewEntity;
                r.b("AbstractCashierPayChannelListFloor", "payPlanItemView initializePayPlanView payPlanView.setOnItemClickListener");
                Payment a = AbstractCashierPayChannelListFloor.this.f3956l.a();
                com.jd.lib.cashier.sdk.h.e.a.d().F(this.a, a.code, creditCardPlan.plan, "");
                com.jd.lib.cashier.sdk.b.i.e.g("CLICK_PAYMENT_CHANNEL_PAY_PLAN_ITEM", "credit_card_plan", new ClickCreditCardPlanItemEvent(creditCardPlan, (CreditCardPlan) iPlanItemViewEntity2, a));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public class g implements Function1<String, Unit> {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ com.jd.lib.cashier.sdk.pay.dialog.e f3965g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ x f3966h;

        g(AbstractCashierPayChannelListFloor abstractCashierPayChannelListFloor, com.jd.lib.cashier.sdk.pay.dialog.e eVar, x xVar) {
            this.f3965g = eVar;
            this.f3966h = xVar;
        }

        @Override // kotlin.jvm.functions.Function1
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public Unit invoke(String str) {
            if (!o0.a("AbstractCashierPayChannelListFloor") && (this.f3965g instanceof CouponEntity)) {
                com.jd.lib.cashier.sdk.b.i.e.f("click_credit_card_COUPON_on_item_key", new ClickCreditCardCouponItemEvent(this.f3966h.a()));
            }
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public class h implements Function0<Unit> {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ Payment f3967g;

        h(AbstractCashierPayChannelListFloor abstractCashierPayChannelListFloor, Payment payment) {
            this.f3967g = payment;
        }

        @Override // kotlin.jvm.functions.Function0
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public Unit invoke() {
            if (o0.a("AbstractCashierPayChannelListFloor")) {
                return null;
            }
            com.jd.lib.cashier.sdk.b.i.e.f("click_credit_card_bank_on_item", new ClickCreditCardBankItemEvent(this.f3967g));
            return null;
        }
    }

    /* loaded from: classes14.dex */
    class i extends com.jd.lib.cashier.sdk.core.utils.b {

        /* renamed from: j  reason: collision with root package name */
        final /* synthetic */ Context f3968j;

        /* renamed from: k  reason: collision with root package name */
        final /* synthetic */ Payment f3969k;

        i(Context context, Payment payment) {
            this.f3968j = context;
            this.f3969k = payment;
        }

        @Override // com.jd.lib.cashier.sdk.core.utils.b
        public void b(View view) {
            if (AbstractCashierPayChannelListFloor.this.f3957m != null) {
                AbstractCashierPayChannelListFloor.this.f3957m.run();
            }
            Context context = this.f3968j;
            if (context instanceof FragmentActivity) {
                com.jd.lib.cashier.sdk.b.d.a.f((FragmentActivity) context, this.f3969k.extraInfo);
            }
        }
    }

    /* loaded from: classes14.dex */
    class j implements View.OnClickListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ BaiTiaoExtraTip f3971g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ Context f3972h;

        j(BaiTiaoExtraTip baiTiaoExtraTip, Context context) {
            this.f3971g = baiTiaoExtraTip;
            this.f3972h = context;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            CashierCommonPopConfig cashierCommonPopConfig;
            BaiTiaoExtraTip baiTiaoExtraTip = this.f3971g;
            if (baiTiaoExtraTip == null || (cashierCommonPopConfig = baiTiaoExtraTip.extraTipToast) == null) {
                return;
            }
            com.jd.lib.cashier.sdk.b.d.a.c(this.f3972h, cashierCommonPopConfig);
            Payment a = AbstractCashierPayChannelListFloor.this.f3956l.a();
            if (this.f3972h instanceof CashierPayActivity) {
                com.jd.lib.cashier.sdk.h.e.a.d().l((CashierPayActivity) this.f3972h, a.code);
            }
        }
    }

    /* loaded from: classes14.dex */
    class k implements Runnable {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ Context f3974g;

        k(AbstractCashierPayChannelListFloor abstractCashierPayChannelListFloor, Context context) {
            this.f3974g = context;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.f3974g instanceof CashierPayActivity) {
                com.jd.lib.cashier.sdk.h.e.a.d().m((CashierPayActivity) this.f3974g);
            }
        }
    }

    public AbstractCashierPayChannelListFloor(View view) {
        super(view);
        this.f3957m = new d();
    }

    private void h(List<IPlanItemViewEntity> list, String str) {
        Context context = getConvertView().getContext();
        PayPlanView payPlanView = (PayPlanView) getView(R.id.stub_pay_plan_view);
        this.f3954j = payPlanView;
        payPlanView.H(AbsPayPlanView.b.PLAN_BAITIAO);
        this.f3954j.x(list, str);
        this.f3954j.G(new e(context));
        PayPlanView payPlanView2 = this.f3954j;
        if (payPlanView2 != null) {
            payPlanView2.onChangeSkin();
        }
    }

    private void i(x xVar) {
        Payment a2 = xVar.a();
        Context context = getConvertView().getContext();
        if (a2 == null) {
            return;
        }
        if (CashierPayChannelCode.JD_PAY_CREDIT.equals(a2.code)) {
            this.f3955k.w();
        } else if ("5".equals(a2.status) && com.jd.lib.cashier.sdk.h.h.g.a(a2.code)) {
            this.f3955k.w();
        } else {
            if ((com.jd.lib.cashier.sdk.h.h.g.a(a2.code) || com.jd.lib.cashier.sdk.h.h.g.c(a2.code) || com.jd.lib.cashier.sdk.h.h.g.f(a2.code)) ? false : true) {
                String str = a2.preferentiaNum;
                this.f3955k.v(str, false, null);
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
                this.f3955k.v(str2, true, new a(a2));
            } else {
                this.f3955k.v(str2, false, null);
            }
        }
    }

    private void j(@NonNull x xVar) {
        Context context = getConvertView().getContext();
        PayPlanView payPlanView = (PayPlanView) getView(R.id.stub_pay_plan_view);
        this.f3954j = payPlanView;
        payPlanView.H(AbsPayPlanView.b.PLAN_CREDIT_CARD);
        this.f3954j.x(xVar.a().planFeeEntityList, xVar.a().mianxiHighlight);
        this.f3954j.G(new f(context));
        com.jd.lib.cashier.sdk.pay.dialog.e eVar = xVar.a().selectedCouponEntity;
        if (eVar != null && !TextUtils.isEmpty(eVar.getTitleName())) {
            this.f3954j.y(eVar.getTitleName(), true);
        } else {
            this.f3954j.y("", true);
        }
        this.f3954j.E(new g(this, eVar, xVar));
        if (xVar.a().currentCreditCardBank != null) {
            this.f3954j.J(xVar.a().currentCreditCardBank.bankNameShow);
        }
        this.f3954j.F(new h(this, xVar.a()));
        PayPlanView payPlanView2 = this.f3954j;
        if (payPlanView2 != null) {
            payPlanView2.onChangeSkin();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void l(Payment payment, View view) {
        if (o0.a("jxjAbstractCashierPayChannelListFloor")) {
            return;
        }
        com.jd.lib.cashier.sdk.b.i.e.f("CLICK_PAYMENT_ITEM_JXJ_ICON", new ClickJXJIconItemEvent(payment));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void m(Context context, Payment payment, View view) {
        if (o0.a("gwjAbstractCashierPayChannelListFloor")) {
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

    private void s(a.b bVar, boolean z) {
        if ((bVar == a.b.FLOOR_BOTTOM || bVar == a.b.FLOOR_TOP_AND_BOTTOM) && z) {
            if (bVar == a.b.FLOOR_TOP_AND_BOTTOM) {
                this.f3955k.T(a.b.FLOOR_TOP_AND_NORMAL);
                return;
            } else {
                this.f3955k.T(a.b.NORMAL);
                return;
            }
        }
        this.f3955k.T(bVar);
    }

    private void u(Payment payment, boolean z) {
        s(payment.splitLineType, z);
    }

    private void v(final Payment payment) {
        List<GouWuJinWalletInfo> list;
        List<ProductInfo> list2;
        final Context context = getConvertView().getContext();
        List<String> list3 = payment.iconList;
        if (list3 != null && !list3.isEmpty()) {
            int size = payment.iconList.size();
            this.f3955k.l(24, 20);
            if (size >= 2) {
                this.f3955k.x(payment.iconList.get(0));
                this.f3955k.C(payment.iconList.get(1));
                return;
            }
            this.f3955k.D();
            if (TextUtils.equals(CashierPayChannelCode.JD_PAY_JXJ, payment.code)) {
                if (payment.showSkuList && (list2 = payment.productInfos) != null && !list2.isEmpty()) {
                    this.f3955k.m();
                    this.f3955k.y(payment.iconList.get(0), "\u81a8\u80c0\u91d1\u8bf4\u660e");
                    this.f3955k.z(new View.OnClickListener() { // from class: com.jd.lib.cashier.sdk.pay.floors.b
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view) {
                            AbstractCashierPayChannelListFloor.l(Payment.this, view);
                        }
                    });
                    return;
                }
                this.f3955k.A();
                return;
            } else if (TextUtils.equals("GOUWUJIN", payment.code)) {
                GouWuJinModel gouWuJinModel = payment.gouWuJinModel;
                if (gouWuJinModel != null && (list = gouWuJinModel.walletInfos) != null && !list.isEmpty()) {
                    if (context instanceof CashierPayActivity) {
                        com.jd.lib.cashier.sdk.h.e.a.d().S((CashierPayActivity) context);
                    }
                    this.f3955k.m();
                    this.f3955k.y(payment.iconList.get(0), "\u8d2d\u7269\u91d1\u8bf4\u660e");
                    this.f3955k.z(new View.OnClickListener() { // from class: com.jd.lib.cashier.sdk.pay.floors.a
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view) {
                            AbstractCashierPayChannelListFloor.m(context, payment, view);
                        }
                    });
                    return;
                }
                this.f3955k.A();
                return;
            } else {
                this.f3955k.x(payment.iconList.get(0));
                return;
            }
        }
        this.f3955k.B();
    }

    private void w(Payment payment, boolean z) {
        if ("moreInfo".equals(payment.code)) {
            this.f3955k.g();
            this.f3955k.O();
        } else if ("9".equals(payment.status)) {
            this.f3955k.g();
            this.f3955k.f();
        } else {
            this.f3955k.P();
            this.f3955k.f();
            this.f3955k.k(z);
        }
    }

    private void x(Payment payment, boolean z) {
        a.b bVar = a.b.NORMAL;
        a.b bVar2 = payment.splitLineType;
        if (bVar == bVar2) {
            this.f3955k.L();
        } else if (a.b.FLOOR_BOTTOM == bVar2) {
            if (z) {
                this.f3955k.L();
            } else {
                this.f3955k.I();
            }
        } else if (a.b.FLOOR_TOP_AND_NORMAL == bVar2) {
            this.f3955k.N();
        } else if (a.b.FLOOR_TOP_AND_BOTTOM != bVar2) {
            this.f3955k.e();
        } else if (z) {
            this.f3955k.N();
        } else {
            this.f3955k.M();
        }
        if (com.jd.lib.cashier.sdk.h.h.g.a(payment.code) || CashierPayChannelCode.JD_PAY_CREDIT.equals(payment.code)) {
            if (z) {
                this.f3955k.i();
            } else {
                this.f3955k.R();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void A() {
        if (this.f3956l == null || this.f3954j == null) {
            return;
        }
        Context context = getConvertView().getContext();
        if (this.f3954j.getVisibility() == 0) {
            BaiTiaoExtraTip baiTiaoExtraTip = this.f3956l.a().baiTiaoExtraTip;
            this.f3954j.D(new j(baiTiaoExtraTip, context));
            this.f3954j.K(baiTiaoExtraTip, new k(this, context));
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void g(x xVar) {
        if (xVar != null) {
            Payment a2 = xVar.a();
            Context context = getConvertView().getContext();
            if (a2 == null || a2.hasPaymentExpo || a2.isSourceFromDialogSelected) {
                return;
            }
            a2.hasPaymentExpo = true;
            if (TextUtils.equals(a2.code, "moreInfo")) {
                com.jd.lib.cashier.sdk.h.e.a.d().N(context);
            } else if (context instanceof CashierPayActivity) {
                com.jd.lib.cashier.sdk.h.e.a.d().V((CashierPayActivity) context, a2.code, com.jd.lib.cashier.sdk.h.h.e.b(a2), a2.canUse(), a2.isCombine(), a2.defaultSelected, a2.openXjkLargePayFlag, a2.changetag, a2.hasCouponExpo ? "1" : "0");
            }
        }
    }

    @Override // com.jd.lib.cashier.sdk.core.commonfloor.floor.AbstractFloor
    public void initView(View view) {
        if (this.f3955k == null) {
            this.f3953i = (ViewStub) view.findViewById(R.id.lib_cashier_pay_channel_floor_item_stub);
            CashierRegulatorItemView cashierRegulatorItemView = (CashierRegulatorItemView) view.findViewById(R.id.lib_cashier_pay_channel_floor_item);
            this.f3955k = cashierRegulatorItemView;
            cashierRegulatorItemView.r(new c());
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean k(@NonNull x xVar) {
        Payment a2 = xVar.a();
        if (a2 == null) {
            return false;
        }
        boolean z = a2.selected;
        boolean a3 = com.jd.lib.cashier.sdk.h.h.g.a(a2.code);
        boolean equals = "5".equals(a2.status);
        boolean equals2 = CashierPayChannelCode.JD_PAY_CREDIT.equals(a2.code);
        boolean equals3 = TextUtils.equals(a2.status, "3");
        boolean z2 = a2.baitiaoPlanInfo != null && a3;
        ViewStub viewStub = this.f3953i;
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
                this.f3953i.setVisibility(0);
                return true;
            }
            this.f3953i.setVisibility(8);
            return false;
        } else {
            viewStub.setVisibility(8);
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void n(x xVar) {
        try {
            if (this.f3955k == null || !com.jd.lib.cashier.sdk.core.utils.g.a(getConvertView().getContext())) {
                return;
            }
            this.f3955k.getViewTreeObserver().addOnGlobalLayoutListener(new b(xVar));
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public abstract void p(x xVar);

    /* JADX INFO: Access modifiers changed from: protected */
    public void q(@NonNull x xVar) {
        o(xVar);
        List<IPlanItemViewEntity> list = xVar.a().planFeeEntityList;
        Payment a2 = xVar.a();
        if (com.jd.lib.cashier.sdk.h.h.g.a(a2.code)) {
            h(list, a2.mianxiHighlight);
        } else if (com.jd.lib.cashier.sdk.h.h.g.d(a2.code)) {
            j(xVar);
        }
    }

    @Override // com.jd.lib.cashier.sdk.core.commonfloor.floor.AbstractFloor
    /* renamed from: r  reason: merged with bridge method [inline-methods] */
    public void b(com.jd.lib.cashier.sdk.h.d.a aVar, x xVar) {
        this.f3956l = xVar;
        p(xVar);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void t(Payment payment) {
        if (payment == null) {
            return;
        }
        View view = getView(R.id.lib_cashier_pay_channel_list_new_floor_root);
        a.b bVar = payment.splitLineType;
        if ((a.b.FLOOR_TOP_AND_BOTTOM == bVar || a.b.FLOOR_BOTTOM == bVar || a.b.FLOOR_TOP_AND_NORMAL == bVar) && view != null) {
            view.setBackgroundColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_F2F2F2));
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void y(x xVar) {
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

    /* JADX INFO: Access modifiers changed from: protected */
    public void z(x xVar, boolean z) {
        Payment a2 = xVar.a();
        Context context = getConvertView().getContext();
        CashierRegulatorItemView cashierRegulatorItemView = this.f3955k;
        if (cashierRegulatorItemView != null) {
            cashierRegulatorItemView.E(a2.logo);
            if (CashierPayChannelCode.JD_PAY_HONEY.equals(a2.code)) {
                this.f3955k.s(a2.channelNamePre);
            } else {
                this.f3955k.s(a2.channelName);
            }
            this.f3955k.G(a2.tip);
            this.f3955k.H(a2.extraInfo, new i(context, a2));
            this.f3955k.F(a2.statusDesc);
            v(a2);
            this.f3955k.u(a2.channelNameTail);
            this.f3955k.t(a2.channelNameMiddle);
            i(xVar);
            this.f3955k.K();
            this.f3955k.p(a2.moreInfoTip);
            if (TextUtils.equals("3", a2.status)) {
                this.f3955k.d();
            }
            w(a2, a2.selected);
            CashierRegulatorItemView cashierRegulatorItemView2 = this.f3955k;
            cashierRegulatorItemView2.J(cashierRegulatorItemView2.c(a2.status, com.jd.lib.cashier.sdk.h.h.g.b(a2.code)));
            this.f3955k.a("3".equals(a2.status));
            x(a2, z);
            u(a2, z);
        }
    }
}

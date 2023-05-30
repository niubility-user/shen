package com.jd.lib.cashier.sdk.pay.aac.impl.channel;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import com.jd.lib.cashier.sdk.core.ui.widget.IPlanItemViewEntity;
import com.jd.lib.cashier.sdk.h.g.x;
import com.jd.lib.cashier.sdk.pay.aac.livedata.CreditCardPayPlanFailureLiveData;
import com.jd.lib.cashier.sdk.pay.aac.livedata.CreditCardPayPlanLiveData;
import com.jd.lib.cashier.sdk.pay.aac.viewmodel.CashierPayViewModel;
import com.jd.lib.cashier.sdk.pay.adapter.CashierPayAdapter;
import com.jd.lib.cashier.sdk.pay.bean.Payment;
import com.jd.lib.cashier.sdk.pay.bean.baitiao.AgreementServiceMapMap;
import com.jd.lib.cashier.sdk.pay.bean.baitiao.PlanServiceMap;
import com.jd.lib.cashier.sdk.pay.bean.coupon.CouponEntity;
import com.jd.lib.cashier.sdk.pay.bean.creditcard.CreditCard;
import com.jd.lib.cashier.sdk.pay.bean.creditcard.CreditCardPayPlanResponse;
import com.jd.lib.cashier.sdk.pay.bean.creditcard.CreditCardPlan;
import com.jd.lib.cashier.sdk.pay.bean.syncevent.ClickCreditCardBankItemEvent;
import com.jd.lib.cashier.sdk.pay.bean.syncevent.ClickCreditCardCouponItemEvent;
import com.jd.lib.cashier.sdk.pay.bean.syncevent.ClickCreditCardPlanItemEvent;
import com.jd.lib.cashier.sdk.pay.bean.syncevent.ClickCreditCardPlanToFooterEvent;
import com.jd.lib.cashier.sdk.pay.view.CashierPayActivity;
import com.jingdong.common.cashiernative.CashierPayChannelCode;
import com.jingdong.sdk.platform.business.personal.R2;
import com.meizu.cloud.pushsdk.notification.model.AdvanceSetting;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* loaded from: classes14.dex */
public final class p implements com.jd.lib.cashier.sdk.core.aac.d, com.jd.lib.cashier.sdk.d.d.a {

    /* renamed from: l  reason: collision with root package name */
    private static final String f3729l = "p";
    @Nullable

    /* renamed from: g  reason: collision with root package name */
    private CashierPayAdapter f3730g;

    /* renamed from: h  reason: collision with root package name */
    private final Lazy f3731h;

    /* renamed from: i  reason: collision with root package name */
    private final Lazy f3732i;

    /* renamed from: j  reason: collision with root package name */
    private String f3733j;

    /* renamed from: k  reason: collision with root package name */
    private final FragmentActivity f3734k;

    /* JADX INFO: Access modifiers changed from: package-private */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n\u00a2\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lcom/jd/lib/cashier/sdk/pay/bean/creditcard/CreditCard;", "creditCard", "", "invoke", "(Lcom/jd/lib/cashier/sdk/pay/bean/creditcard/CreditCard;)V", "<anonymous>"}, k = 3, mv = {1, 4, 0})
    /* loaded from: classes14.dex */
    public static final class a extends Lambda implements Function1<CreditCard, Unit> {
        a() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(CreditCard creditCard) {
            invoke2(creditCard);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(@NotNull CreditCard creditCard) {
            String str = creditCard.recommendPlanId;
            if (str == null) {
                str = "1";
            }
            p.this.E(creditCard, str);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0002\u0010\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\n\u00a2\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"", "invoke", "()V", "<anonymous>"}, k = 3, mv = {1, 4, 0})
    /* loaded from: classes14.dex */
    public static final class b extends Lambda implements Function0<Unit> {
        final /* synthetic */ Payment $payment;

        /* JADX INFO: Access modifiers changed from: package-private */
        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n\u00a2\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lcom/jd/lib/cashier/sdk/h/h/k;", "params", "", "invoke", "(Lcom/jd/lib/cashier/sdk/h/h/k;)V", "<anonymous>"}, k = 3, mv = {1, 4, 0})
        /* loaded from: classes14.dex */
        public static final class a extends Lambda implements Function1<com.jd.lib.cashier.sdk.h.h.k, Unit> {
            a() {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(com.jd.lib.cashier.sdk.h.h.k kVar) {
                invoke2(kVar);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull com.jd.lib.cashier.sdk.h.h.k kVar) {
                com.jd.lib.cashier.sdk.h.e.a.d().y(p.this.f3734k, kVar.b(), kVar.a());
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        b(Payment payment) {
            super(0);
            this.$payment = payment;
        }

        @Override // kotlin.jvm.functions.Function0
        public /* bridge */ /* synthetic */ Unit invoke() {
            invoke2();
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2() {
            com.jd.lib.cashier.sdk.h.h.c.d(this.$payment, new a());
            p.this.A();
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\n\u00a2\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lcom/jd/lib/cashier/sdk/pay/dialog/b;", "invoke", "()Lcom/jd/lib/cashier/sdk/pay/dialog/b;", "<anonymous>"}, k = 3, mv = {1, 4, 0})
    /* loaded from: classes14.dex */
    static final class c extends Lambda implements Function0<com.jd.lib.cashier.sdk.pay.dialog.b> {
        c() {
            super(0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        @NotNull
        public final com.jd.lib.cashier.sdk.pay.dialog.b invoke() {
            return new com.jd.lib.cashier.sdk.pay.dialog.b(p.this.f3734k);
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\n\u00a2\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lcom/jd/lib/cashier/sdk/pay/dialog/c;", "invoke", "()Lcom/jd/lib/cashier/sdk/pay/dialog/c;", "<anonymous>"}, k = 3, mv = {1, 4, 0})
    /* loaded from: classes14.dex */
    static final class d extends Lambda implements Function0<com.jd.lib.cashier.sdk.pay.dialog.c> {
        public static final d INSTANCE = new d();

        d() {
            super(0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        @NotNull
        public final com.jd.lib.cashier.sdk.pay.dialog.c invoke() {
            return new com.jd.lib.cashier.sdk.pay.dialog.c();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\b\u0010\u0001\u001a\u0004\u0018\u00010\u0000H\n\u00a2\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lcom/jd/lib/cashier/sdk/pay/dialog/e;", "currentCouponItemEntity", "", "invoke", "(Lcom/jd/lib/cashier/sdk/pay/dialog/e;)V", "<anonymous>"}, k = 3, mv = {1, 4, 0})
    /* loaded from: classes14.dex */
    public static final class e extends Lambda implements Function1<com.jd.lib.cashier.sdk.pay.dialog.e, Unit> {
        e() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(com.jd.lib.cashier.sdk.pay.dialog.e eVar) {
            invoke2(eVar);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(@Nullable com.jd.lib.cashier.sdk.pay.dialog.e eVar) {
            if (eVar instanceof CouponEntity) {
                com.jd.lib.cashier.sdk.core.utils.r.b(p.f3729l, "currentCouponItemEntity = " + eVar);
                p.this.z((CouponEntity) eVar);
            }
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n\u00a2\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lcom/jd/lib/cashier/sdk/pay/bean/syncevent/ClickCreditCardBankItemEvent;", AdvanceSetting.NETWORK_TYPE, "", "invoke", "(Lcom/jd/lib/cashier/sdk/pay/bean/syncevent/ClickCreditCardBankItemEvent;)V", "<anonymous>"}, k = 3, mv = {1, 4, 0})
    /* loaded from: classes14.dex */
    static final class f extends Lambda implements Function1<ClickCreditCardBankItemEvent, Unit> {
        f() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(ClickCreditCardBankItemEvent clickCreditCardBankItemEvent) {
            invoke2(clickCreditCardBankItemEvent);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(@NotNull ClickCreditCardBankItemEvent clickCreditCardBankItemEvent) {
            p.this.x();
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n\u00a2\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lcom/jd/lib/cashier/sdk/pay/bean/syncevent/ClickCreditCardPlanItemEvent;", AdvanceSetting.NETWORK_TYPE, "", "invoke", "(Lcom/jd/lib/cashier/sdk/pay/bean/syncevent/ClickCreditCardPlanItemEvent;)V", "<anonymous>"}, k = 3, mv = {1, 4, 0})
    /* loaded from: classes14.dex */
    static final class g extends Lambda implements Function1<ClickCreditCardPlanItemEvent, Unit> {
        g() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(ClickCreditCardPlanItemEvent clickCreditCardPlanItemEvent) {
            invoke2(clickCreditCardPlanItemEvent);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(@NotNull ClickCreditCardPlanItemEvent clickCreditCardPlanItemEvent) {
            String targetCreditCardPlan = clickCreditCardPlanItemEvent.getTargetCreditCardPlan().plan;
            p pVar = p.this;
            Intrinsics.checkExpressionValueIsNotNull(targetCreditCardPlan, "targetCreditCardPlan");
            pVar.D(targetCreditCardPlan);
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n\u00a2\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lcom/jd/lib/cashier/sdk/pay/bean/syncevent/ClickCreditCardCouponItemEvent;", AdvanceSetting.NETWORK_TYPE, "", "invoke", "(Lcom/jd/lib/cashier/sdk/pay/bean/syncevent/ClickCreditCardCouponItemEvent;)V", "<anonymous>"}, k = 3, mv = {1, 4, 0})
    /* loaded from: classes14.dex */
    static final class h extends Lambda implements Function1<ClickCreditCardCouponItemEvent, Unit> {
        h() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(ClickCreditCardCouponItemEvent clickCreditCardCouponItemEvent) {
            invoke2(clickCreditCardCouponItemEvent);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(@NotNull ClickCreditCardCouponItemEvent clickCreditCardCouponItemEvent) {
            p.this.y(clickCreditCardCouponItemEvent.getPayment());
        }
    }

    /* loaded from: classes14.dex */
    static final class i<T> implements Observer<com.jd.lib.cashier.sdk.pay.aac.livedata.a.j> {

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ FragmentActivity f3736h;

        i(FragmentActivity fragmentActivity) {
            this.f3736h = fragmentActivity;
        }

        @Override // androidx.lifecycle.Observer
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public final void onChanged(com.jd.lib.cashier.sdk.pay.aac.livedata.a.j jVar) {
            String str;
            String str2;
            CashierPayViewModel x;
            com.jd.lib.cashier.sdk.h.c.a b;
            if (jVar != null) {
                FragmentActivity fragmentActivity = this.f3736h;
                Payment payment = null;
                if (!(fragmentActivity instanceof CashierPayActivity)) {
                    fragmentActivity = null;
                }
                CashierPayActivity cashierPayActivity = (CashierPayActivity) fragmentActivity;
                if (cashierPayActivity != null && (x = cashierPayActivity.x()) != null && (b = x.b()) != null) {
                    payment = b.O;
                }
                if (payment == null || (str = payment.code) == null) {
                    str = "";
                }
                if (com.jd.lib.cashier.sdk.h.h.g.d(str)) {
                    CreditCardPayPlanResponse a = jVar.a();
                    com.jd.lib.cashier.sdk.pay.aac.livedata.a.l b2 = jVar.b();
                    if (b2 == null || (str2 = b2.a()) == null) {
                        str2 = "";
                    }
                    if (Intrinsics.areEqual("1312", a.errorCode)) {
                        p.this.J().e();
                        p.this.J().n(com.jd.lib.cashier.sdk.b.c.c.a());
                        com.jd.lib.cashier.sdk.h.h.m.q(this.f3736h, "", "");
                        p.this.S(com.jd.lib.cashier.sdk.b.c.c.a());
                    } else if (str2.hashCode() == 48 && str2.equals("0")) {
                        p.this.R();
                    }
                }
            }
        }
    }

    /* loaded from: classes14.dex */
    static final class j<T> implements Observer<com.jd.lib.cashier.sdk.pay.aac.livedata.a.k> {

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ FragmentActivity f3738h;

        j(FragmentActivity fragmentActivity) {
            this.f3738h = fragmentActivity;
        }

        @Override // androidx.lifecycle.Observer
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public final void onChanged(com.jd.lib.cashier.sdk.pay.aac.livedata.a.k kVar) {
            String str;
            CreditCardPlan creditCardPlan;
            String str2;
            String str3;
            String str4;
            String str5;
            String str6;
            T t;
            CashierPayViewModel x;
            com.jd.lib.cashier.sdk.h.c.a b;
            if (kVar != null) {
                FragmentActivity fragmentActivity = this.f3738h;
                if (!(fragmentActivity instanceof CashierPayActivity)) {
                    fragmentActivity = null;
                }
                CashierPayActivity cashierPayActivity = (CashierPayActivity) fragmentActivity;
                Payment payment = (cashierPayActivity == null || (x = cashierPayActivity.x()) == null || (b = x.b()) == null) ? null : b.O;
                if (payment == null || (str = payment.code) == null) {
                    str = "";
                }
                if (com.jd.lib.cashier.sdk.h.h.g.d(str)) {
                    CreditCardPayPlanResponse a = kVar.a();
                    com.jd.lib.cashier.sdk.pay.aac.livedata.a.l b2 = kVar.b();
                    if (Intrinsics.areEqual(b2 != null ? b2.a() : null, "5")) {
                        p.this.J().p(a.canUseCouponList, a.cantUseCouponList);
                        p.this.Q();
                        return;
                    }
                    if (Intrinsics.areEqual(b2 != null ? b2.a() : null, "3")) {
                        p.this.J().e();
                    }
                    p.this.J().r(a.selectedCoupon);
                    CouponEntity h2 = p.this.J().h(a.selectedCoupon);
                    p.this.J().n(h2);
                    p.this.J().q(h2, this.f3738h);
                    com.jd.lib.cashier.sdk.core.utils.r.b("PaymentParamsHelper", "creditCard proxy currentBaiTiaoCoupon = " + h2);
                    List<CreditCardPlan> list = a.planFeeList;
                    if (list != null) {
                        Iterator<T> it = list.iterator();
                        while (true) {
                            if (!it.hasNext()) {
                                t = null;
                                break;
                            }
                            t = it.next();
                            Boolean bool = ((CreditCardPlan) t).selected;
                            Intrinsics.checkExpressionValueIsNotNull(bool, "planFeeEntity.selected");
                            if (bool.booleanValue()) {
                                break;
                            }
                        }
                        creditCardPlan = (CreditCardPlan) t;
                    } else {
                        creditCardPlan = null;
                    }
                    p pVar = p.this;
                    if (creditCardPlan == null || (str2 = creditCardPlan.plan) == null) {
                        str2 = "";
                    }
                    pVar.f3733j = str2;
                    com.jd.lib.cashier.sdk.h.h.m.j(this.f3738h, (creditCardPlan == null || (str6 = creditCardPlan.plan) == null) ? "" : str6, (creditCardPlan == null || (str5 = creditCardPlan.planInfo) == null) ? "" : str5, (creditCardPlan == null || (str4 = creditCardPlan.bankPlanRate) == null) ? "" : str4, (creditCardPlan == null || (str3 = creditCardPlan.merchantFeeSubSideBy) == null) ? "" : str3, creditCardPlan != null ? creditCardPlan.tradeMap : null);
                    com.jd.lib.cashier.sdk.core.utils.r.b("PaymentParamsHelper", "creditCard proxy currentBaiTiaoPlanFee = " + creditCardPlan);
                    com.jd.lib.cashier.sdk.pay.aac.livedata.a.l b3 = kVar.b();
                    CreditCard b4 = b3 != null ? b3.b() : null;
                    p.this.T(h2, a, b4);
                    com.jd.lib.cashier.sdk.b.i.e.f("CLICK_CREDIT_CARD_PLAN_TO_FOOTER", new ClickCreditCardPlanToFooterEvent(creditCardPlan));
                    p.this.P(b4);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0010\u0006\u001a\u0012\u0012\u0004\u0012\u00020\u00010\u0000j\b\u0012\u0004\u0012\u00020\u0001`\u00022\u0016\u0010\u0003\u001a\u0012\u0012\u0004\u0012\u00020\u00010\u0000j\b\u0012\u0004\u0012\u00020\u0001`\u0002H\n\u00a2\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"Ljava/util/ArrayList;", "Lcom/jd/lib/cashier/sdk/d/a/e/a;", "Lkotlin/collections/ArrayList;", "baseTemplateEntityList", "invoke", "(Ljava/util/ArrayList;)Ljava/util/ArrayList;", "<anonymous>"}, k = 3, mv = {1, 4, 0})
    /* loaded from: classes14.dex */
    public static final class k extends Lambda implements Function1<ArrayList<com.jd.lib.cashier.sdk.d.a.e.a>, ArrayList<com.jd.lib.cashier.sdk.d.a.e.a>> {
        k() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        @NotNull
        public final ArrayList<com.jd.lib.cashier.sdk.d.a.e.a> invoke(@NotNull ArrayList<com.jd.lib.cashier.sdk.d.a.e.a> arrayList) {
            Collection data;
            ArrayList<com.jd.lib.cashier.sdk.d.a.e.a> arrayList2 = new ArrayList<>();
            if (arrayList.size() <= 0) {
                CashierPayAdapter I = p.this.I();
                if (I != null && (data = I.getData()) != null) {
                    Iterator it = data.iterator();
                    while (it.hasNext()) {
                        arrayList2.add((com.jd.lib.cashier.sdk.d.a.e.a) it.next());
                    }
                }
            } else {
                arrayList2.addAll(arrayList);
            }
            return arrayList2;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0010\u0006\u001a\u0012\u0012\u0004\u0012\u00020\u00010\u0000j\b\u0012\u0004\u0012\u00020\u0001`\u00022\u0016\u0010\u0003\u001a\u0012\u0012\u0004\u0012\u00020\u00010\u0000j\b\u0012\u0004\u0012\u00020\u0001`\u0002H\n\u00a2\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"Ljava/util/ArrayList;", "Lcom/jd/lib/cashier/sdk/d/a/e/a;", "Lkotlin/collections/ArrayList;", "baseTemplateEntityList", "invoke", "(Ljava/util/ArrayList;)Ljava/util/ArrayList;", "<anonymous>"}, k = 3, mv = {1, 4, 0})
    /* loaded from: classes14.dex */
    public static final class l extends Lambda implements Function1<ArrayList<com.jd.lib.cashier.sdk.d.a.e.a>, ArrayList<com.jd.lib.cashier.sdk.d.a.e.a>> {
        final /* synthetic */ CouponEntity $selectedCouponEntity;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        l(CouponEntity couponEntity) {
            super(1);
            this.$selectedCouponEntity = couponEntity;
        }

        @Override // kotlin.jvm.functions.Function1
        @NotNull
        public final ArrayList<com.jd.lib.cashier.sdk.d.a.e.a> invoke(@NotNull ArrayList<com.jd.lib.cashier.sdk.d.a.e.a> arrayList) {
            Collection data;
            ArrayList<com.jd.lib.cashier.sdk.d.a.e.a> arrayList2 = new ArrayList<>();
            if (arrayList.size() <= 0) {
                CashierPayAdapter I = p.this.I();
                if (I != null && (data = I.getData()) != null) {
                    Iterator it = data.iterator();
                    while (it.hasNext()) {
                        arrayList2.add((com.jd.lib.cashier.sdk.d.a.e.a) it.next());
                    }
                }
            } else {
                arrayList2.addAll(arrayList);
            }
            Iterator<com.jd.lib.cashier.sdk.d.a.e.a> it2 = arrayList2.iterator();
            while (true) {
                if (!it2.hasNext()) {
                    break;
                }
                com.jd.lib.cashier.sdk.d.a.e.a next = it2.next();
                if (next instanceof x) {
                    x xVar = (x) next;
                    if (Intrinsics.areEqual(CashierPayChannelCode.JD_PAY_CREDIT, xVar.a().code)) {
                        xVar.a().selectedCouponEntity = this.$selectedCouponEntity;
                        break;
                    }
                }
            }
            return arrayList2;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0010\u0006\u001a\u0012\u0012\u0004\u0012\u00020\u00010\u0000j\b\u0012\u0004\u0012\u00020\u0001`\u00022\u0016\u0010\u0003\u001a\u0012\u0012\u0004\u0012\u00020\u00010\u0000j\b\u0012\u0004\u0012\u00020\u0001`\u0002H\n\u00a2\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"Ljava/util/ArrayList;", "Lcom/jd/lib/cashier/sdk/d/a/e/a;", "Lkotlin/collections/ArrayList;", "baseTemplateEntityList", "invoke", "(Ljava/util/ArrayList;)Ljava/util/ArrayList;", "<anonymous>"}, k = 3, mv = {1, 4, 0})
    /* loaded from: classes14.dex */
    public static final class m extends Lambda implements Function1<ArrayList<com.jd.lib.cashier.sdk.d.a.e.a>, ArrayList<com.jd.lib.cashier.sdk.d.a.e.a>> {
        final /* synthetic */ AgreementServiceMapMap $agreementServiceMap;
        final /* synthetic */ CreditCard $creditCard;
        final /* synthetic */ List $planFeeEntityList;
        final /* synthetic */ CouponEntity $selectedCouponEntity;
        final /* synthetic */ PlanServiceMap $serviceMap;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        m(List list, PlanServiceMap planServiceMap, CreditCard creditCard, AgreementServiceMapMap agreementServiceMapMap, CouponEntity couponEntity) {
            super(1);
            this.$planFeeEntityList = list;
            this.$serviceMap = planServiceMap;
            this.$creditCard = creditCard;
            this.$agreementServiceMap = agreementServiceMapMap;
            this.$selectedCouponEntity = couponEntity;
        }

        @Override // kotlin.jvm.functions.Function1
        @NotNull
        public final ArrayList<com.jd.lib.cashier.sdk.d.a.e.a> invoke(@NotNull ArrayList<com.jd.lib.cashier.sdk.d.a.e.a> arrayList) {
            Collection data;
            ArrayList<com.jd.lib.cashier.sdk.d.a.e.a> arrayList2 = new ArrayList<>();
            if (arrayList.size() <= 0) {
                CashierPayAdapter I = p.this.I();
                if (I != null && (data = I.getData()) != null) {
                    Iterator it = data.iterator();
                    while (it.hasNext()) {
                        arrayList2.add((com.jd.lib.cashier.sdk.d.a.e.a) it.next());
                    }
                }
            } else {
                arrayList2.addAll(arrayList);
            }
            Iterator<com.jd.lib.cashier.sdk.d.a.e.a> it2 = arrayList2.iterator();
            while (true) {
                if (!it2.hasNext()) {
                    break;
                }
                com.jd.lib.cashier.sdk.d.a.e.a next = it2.next();
                if (next instanceof x) {
                    x xVar = (x) next;
                    if (Intrinsics.areEqual(CashierPayChannelCode.JD_PAY_CREDIT, xVar.a().code)) {
                        xVar.a().planFeeEntityList.clear();
                        List<IPlanItemViewEntity> list = xVar.a().planFeeEntityList;
                        Collection<? extends IPlanItemViewEntity> collection = this.$planFeeEntityList;
                        if (collection == null) {
                            collection = new ArrayList<>();
                        }
                        list.addAll(collection);
                        xVar.a().serviceMap = this.$serviceMap;
                        xVar.a().currentCreditCardBank = this.$creditCard;
                        xVar.a().agreementServiceMap = this.$agreementServiceMap;
                        xVar.a().selectedCouponEntity = this.$selectedCouponEntity;
                        Payment a = xVar.a();
                        AgreementServiceMapMap agreementServiceMapMap = this.$agreementServiceMap;
                        a.planButtonTitle = agreementServiceMapMap != null ? agreementServiceMapMap.agreementButtonTitle : null;
                    }
                }
            }
            return arrayList2;
        }
    }

    public p(@NotNull FragmentActivity fragmentActivity) {
        Lazy lazy;
        Lazy lazy2;
        this.f3734k = fragmentActivity;
        lazy = LazyKt__LazyJVMKt.lazy(new c());
        this.f3731h = lazy;
        lazy2 = LazyKt__LazyJVMKt.lazy(d.INSTANCE);
        this.f3732i = lazy2;
        this.f3733j = "";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void A() {
        H(this, "3", null, null, L(), 6, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void D(String str) {
        H(this, "2", null, str, L(), 2, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void E(CreditCard creditCard, String str) {
        if (creditCard == null) {
            return;
        }
        String str2 = creditCard.channelId;
        String str3 = str2 != null ? str2 : "";
        String str4 = creditCard.bankCode;
        com.jd.lib.cashier.sdk.h.f.f fVar = new com.jd.lib.cashier.sdk.h.f.f(str3, str4 != null ? str4 : "", "4", "", str, null, null, null, 224, null);
        FragmentActivity fragmentActivity = this.f3734k;
        if (!(fragmentActivity instanceof CashierPayActivity)) {
            fragmentActivity = null;
        }
        fVar.setActivity((CashierPayActivity) fragmentActivity);
        fVar.i(creditCard);
        ViewModel viewModel = ViewModelProviders.of(this.f3734k).get(CashierPayViewModel.class);
        Intrinsics.checkExpressionValueIsNotNull(viewModel, "ViewModelProviders.of(co\u2026PayViewModel::class.java)");
        ((CashierPayViewModel) viewModel).g(fVar);
    }

    private final void F(CreditCard creditCard, String str) {
        if (creditCard == null) {
            return;
        }
        String str2 = creditCard.channelId;
        String str3 = str2 != null ? str2 : "";
        String str4 = creditCard.bankCode;
        com.jd.lib.cashier.sdk.h.f.f fVar = new com.jd.lib.cashier.sdk.h.f.f(str3, str4 != null ? str4 : "", "0", str, null, null, null, null, 240, null);
        FragmentActivity fragmentActivity = this.f3734k;
        if (!(fragmentActivity instanceof CashierPayActivity)) {
            fragmentActivity = null;
        }
        fVar.setActivity((CashierPayActivity) fragmentActivity);
        fVar.i(creditCard);
        ViewModel viewModel = ViewModelProviders.of(this.f3734k).get(CashierPayViewModel.class);
        Intrinsics.checkExpressionValueIsNotNull(viewModel, "ViewModelProviders.of(co\u2026PayViewModel::class.java)");
        ((CashierPayViewModel) viewModel).g(fVar);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private final void G(String str, CouponEntity couponEntity, String str2, CreditCard creditCard) {
        com.jd.lib.cashier.sdk.h.f.f fVar;
        if (creditCard == null) {
            return;
        }
        String str3 = creditCard.channelId;
        String str4 = str3 != null ? str3 : "";
        String str5 = creditCard.bankCode;
        String str6 = str5 != null ? str5 : "";
        switch (str.hashCode()) {
            case 49:
                if (str.equals("1")) {
                    fVar = new com.jd.lib.cashier.sdk.h.f.f(str4, str6, str, this.f3733j, null, null, couponEntity != null ? couponEntity.convertToCreditPayCouponEntityRequest() : null, null, R2.anim.popup_center_enter, null);
                    break;
                }
                fVar = new com.jd.lib.cashier.sdk.h.f.f(str4, str6, str, null, null, null, null, null, 248, null);
                break;
            case 50:
                if (str.equals("2")) {
                    fVar = new com.jd.lib.cashier.sdk.h.f.f(str4, str6, str, null, str2, null, null, null, 232, null);
                    break;
                }
                fVar = new com.jd.lib.cashier.sdk.h.f.f(str4, str6, str, null, null, null, null, null, 248, null);
                break;
            case 51:
                if (str.equals("3")) {
                    fVar = new com.jd.lib.cashier.sdk.h.f.f(str4, str6, str, this.f3733j, null, null, null, null, 240, null);
                    break;
                }
                fVar = new com.jd.lib.cashier.sdk.h.f.f(str4, str6, str, null, null, null, null, null, 248, null);
                break;
            case 52:
            default:
                fVar = new com.jd.lib.cashier.sdk.h.f.f(str4, str6, str, null, null, null, null, null, 248, null);
                break;
            case 53:
                if (str.equals("5")) {
                    fVar = new com.jd.lib.cashier.sdk.h.f.f(str4, str6, str, null, null, null, null, null, 248, null);
                    break;
                }
                fVar = new com.jd.lib.cashier.sdk.h.f.f(str4, str6, str, null, null, null, null, null, 248, null);
                break;
        }
        FragmentActivity fragmentActivity = this.f3734k;
        fVar.setActivity(fragmentActivity instanceof CashierPayActivity ? fragmentActivity : null);
        fVar.i(creditCard);
        com.jd.lib.cashier.sdk.core.utils.r.b(f3729l, "EventBusManager creditCard doPayPlanRequest");
        ViewModel viewModel = ViewModelProviders.of(this.f3734k).get(CashierPayViewModel.class);
        Intrinsics.checkExpressionValueIsNotNull(viewModel, "ViewModelProviders.of(co\u2026PayViewModel::class.java)");
        ((CashierPayViewModel) viewModel).g(fVar);
    }

    static /* synthetic */ void H(p pVar, String str, CouponEntity couponEntity, String str2, CreditCard creditCard, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            couponEntity = null;
        }
        if ((i2 & 4) != 0) {
            str2 = "";
        }
        pVar.G(str, couponEntity, str2, creditCard);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final com.jd.lib.cashier.sdk.pay.dialog.b J() {
        return (com.jd.lib.cashier.sdk.pay.dialog.b) this.f3731h.getValue();
    }

    private final com.jd.lib.cashier.sdk.pay.dialog.c K() {
        return (com.jd.lib.cashier.sdk.pay.dialog.c) this.f3732i.getValue();
    }

    private final CreditCard L() {
        return K().a();
    }

    private final CreditCard M(Payment payment) {
        if (L() == null) {
            return N(payment);
        }
        return L();
    }

    private final CreditCard N(Payment payment) {
        List<CreditCard> list;
        Object obj = null;
        K().c(payment != null ? payment.bindingCardArray : null);
        if (payment == null || (list = payment.bindingCardArray) == null || (!list.isEmpty()) != true) {
            return null;
        }
        List<CreditCard> list2 = payment.bindingCardArray;
        Intrinsics.checkExpressionValueIsNotNull(list2, "currentPayment.bindingCardArray");
        Iterator<T> it = list2.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Object next = it.next();
            if (((CreditCard) next).selected) {
                obj = next;
                break;
            }
        }
        CreditCard creditCard = (CreditCard) obj;
        if (creditCard != null) {
            return creditCard;
        }
        CreditCard creditCard2 = payment.bindingCardArray.get(0);
        Intrinsics.checkExpressionValueIsNotNull(creditCard2, "currentPayment.bindingCardArray[0]");
        return creditCard2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void P(CreditCard creditCard) {
        if (creditCard == null) {
            com.jd.lib.cashier.sdk.h.h.m.h(this.f3734k, "", false, "");
        } else {
            FragmentActivity fragmentActivity = this.f3734k;
            String str = creditCard.bankCode;
            boolean z = creditCard.cardAd;
            String str2 = creditCard.channelId;
            Intrinsics.checkExpressionValueIsNotNull(str2, "creditCard.channelId");
            com.jd.lib.cashier.sdk.h.h.m.h(fragmentActivity, str, z, str2);
        }
        K().d(creditCard);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void Q() {
        com.jd.lib.cashier.sdk.pay.dialog.b J = J();
        com.jd.lib.cashier.sdk.pay.dialog.e i2 = J().i();
        if (!(i2 instanceof CouponEntity)) {
            i2 = null;
        }
        J.r((CouponEntity) i2);
        com.jd.lib.cashier.sdk.pay.dialog.b.t(J(), null, new e(), 1, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void R() {
        com.jd.lib.cashier.sdk.h.h.a.a(new k());
        com.jd.lib.cashier.sdk.h.h.a.c(this.f3730g);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void S(CouponEntity couponEntity) {
        com.jd.lib.cashier.sdk.h.h.a.a(new l(couponEntity));
        com.jd.lib.cashier.sdk.h.h.a.c(this.f3730g);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void T(CouponEntity couponEntity, CreditCardPayPlanResponse creditCardPayPlanResponse, CreditCard creditCard) {
        com.jd.lib.cashier.sdk.core.utils.r.b(f3729l, "updatePayPlanAndCouponData selectedCouponEntity = " + couponEntity);
        com.jd.lib.cashier.sdk.h.h.a.a(new m(creditCardPayPlanResponse != null ? creditCardPayPlanResponse.planFeeList : null, creditCardPayPlanResponse != null ? creditCardPayPlanResponse.serviceMap : null, creditCard, creditCardPayPlanResponse != null ? creditCardPayPlanResponse.agreementServiceMap : null, couponEntity));
        com.jd.lib.cashier.sdk.h.h.a.c(this.f3730g);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void x() {
        K().e(this.f3734k, new a());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void y(Payment payment) {
        J().o(new b(payment));
        com.jd.lib.cashier.sdk.h.e.a.d().C(this.f3734k, payment.code, payment.uniqueChannelId);
        H(this, "5", null, null, L(), 6, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void z(CouponEntity couponEntity) {
        H(this, "1", couponEntity, null, L(), 4, null);
    }

    public final void B(@Nullable Payment payment) {
        com.jd.lib.cashier.sdk.core.utils.r.b(f3729l, "EventBusManager handlePlanOnPaymentItem");
        if (L() == null) {
            C(payment);
        } else {
            F(M(payment), this.f3733j);
        }
    }

    public final synchronized void C(@Nullable Payment payment) {
        String str;
        com.jd.lib.cashier.sdk.core.utils.r.b(f3729l, "EventBusManager handlePlanOnPaymentItem");
        CreditCard N = N(payment);
        if (N == null || (str = N.recommendPlanId) == null) {
            str = "1";
        }
        F(N, str);
    }

    @Nullable
    public final CashierPayAdapter I() {
        return this.f3730g;
    }

    public final void O(@Nullable CashierPayAdapter cashierPayAdapter) {
        this.f3730g = cashierPayAdapter;
    }

    @Override // com.jd.lib.cashier.sdk.core.aac.d
    public void f(@Nullable FragmentActivity fragmentActivity) {
        CashierPayViewModel x;
        CreditCardPayPlanLiveData y;
        CashierPayViewModel x2;
        CreditCardPayPlanFailureLiveData x3;
        com.jd.lib.cashier.sdk.b.i.e.e("click_credit_card_bank_on_item", null, new f(), 2, null);
        com.jd.lib.cashier.sdk.b.i.e.d("CLICK_PAYMENT_CHANNEL_PAY_PLAN_ITEM", "credit_card_plan", new g());
        com.jd.lib.cashier.sdk.b.i.e.e("click_credit_card_COUPON_on_item_key", null, new h(), 2, null);
        boolean z = fragmentActivity instanceof CashierPayActivity;
        CashierPayActivity cashierPayActivity = (CashierPayActivity) (!z ? null : fragmentActivity);
        if (cashierPayActivity != null && (x2 = cashierPayActivity.x()) != null && (x3 = x2.x()) != null) {
            x3.observe(this.f3734k, new i(fragmentActivity));
        }
        CashierPayActivity cashierPayActivity2 = z ? fragmentActivity : null;
        if (cashierPayActivity2 == null || (x = cashierPayActivity2.x()) == null || (y = x.y()) == null) {
            return;
        }
        y.observe(this.f3734k, new j(fragmentActivity));
    }
}

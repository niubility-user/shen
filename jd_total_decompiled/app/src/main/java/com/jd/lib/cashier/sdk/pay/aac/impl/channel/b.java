package com.jd.lib.cashier.sdk.pay.aac.impl.channel;

import android.text.TextUtils;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import com.jd.lib.cashier.sdk.core.model.BaiTiaoExtraTip;
import com.jd.lib.cashier.sdk.core.ui.widget.IPlanItemViewEntity;
import com.jd.lib.cashier.sdk.core.utils.f0;
import com.jd.lib.cashier.sdk.pay.aac.viewmodel.CashierPayViewModel;
import com.jd.lib.cashier.sdk.pay.bean.BaiTiaoPayPlanBizData;
import com.jd.lib.cashier.sdk.pay.bean.BaiTiaoPayPlanResponse;
import com.jd.lib.cashier.sdk.pay.bean.Payment;
import com.jd.lib.cashier.sdk.pay.bean.PlanFeeEntity;
import com.jd.lib.cashier.sdk.pay.bean.baitiao.AgreementServiceMapMap;
import com.jd.lib.cashier.sdk.pay.bean.baitiao.BaiTiaoPlanInfo;
import com.jd.lib.cashier.sdk.pay.bean.baitiao.PlanServiceMap;
import com.jd.lib.cashier.sdk.pay.bean.coupon.CouponEntity;
import com.jd.lib.cashier.sdk.pay.bean.coupon.CouponEntityRequest;
import com.jd.lib.cashier.sdk.pay.bean.syncevent.ClickBaiTiaoCouponItemEvent;
import com.jd.lib.cashier.sdk.pay.bean.syncevent.ClickBaiTiaoPayPlanItemEvent;
import com.jd.lib.cashier.sdk.pay.bean.syncevent.ClickBaiTiaoPlanToFooterEvent;
import com.jd.lib.cashier.sdk.pay.bean.syncevent.MonitorClickBaiTiaoNoPlanEvent;
import com.jd.lib.cashier.sdk.pay.bean.syncevent.UpdatePaymentListEvent;
import com.jd.lib.cashier.sdk.pay.dialog.MultiCouponDialogHelper;
import com.jd.lib.cashier.sdk.pay.view.CashierPayActivity;
import com.meizu.cloud.pushsdk.notification.model.AdvanceSetting;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* loaded from: classes14.dex */
public final class b implements com.jd.lib.cashier.sdk.core.aac.d, com.jd.lib.cashier.sdk.d.d.a {
    private static final String o = "b";
    public static final a p = new a(null);

    /* renamed from: g  reason: collision with root package name */
    private final long f3687g = 1200;

    /* renamed from: h  reason: collision with root package name */
    private long f3688h;

    /* renamed from: i  reason: collision with root package name */
    private final Lazy f3689i;

    /* renamed from: j  reason: collision with root package name */
    private boolean f3690j;

    /* renamed from: k  reason: collision with root package name */
    private final String f3691k;

    /* renamed from: l  reason: collision with root package name */
    private final String f3692l;

    /* renamed from: m  reason: collision with root package name */
    private final FragmentActivity f3693m;
    @NotNull

    /* renamed from: n  reason: collision with root package name */
    private final Function1<Payment, Unit> f3694n;

    /* loaded from: classes14.dex */
    public static final class a {
        private a() {
        }

        public final String a() {
            return b.o;
        }

        public /* synthetic */ a(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\n\u00a2\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lcom/jd/lib/cashier/sdk/pay/dialog/MultiCouponDialogHelper;", "invoke", "()Lcom/jd/lib/cashier/sdk/pay/dialog/MultiCouponDialogHelper;", "<anonymous>"}, k = 3, mv = {1, 4, 0})
    /* renamed from: com.jd.lib.cashier.sdk.pay.aac.impl.channel.b$b  reason: collision with other inner class name */
    /* loaded from: classes14.dex */
    public static final class C0128b extends Lambda implements Function0<MultiCouponDialogHelper> {

        /* JADX INFO: Access modifiers changed from: package-private */
        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\u0010\u0006\u001a\u00020\u00032\b\u0010\u0001\u001a\u0004\u0018\u00010\u00002\b\u0010\u0002\u001a\u0004\u0018\u00010\u0000H\n\u00a2\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"Lcom/jd/lib/cashier/sdk/pay/bean/coupon/CouponEntity;", "currentCoupon", "targetCoupon", "", "invoke", "(Lcom/jd/lib/cashier/sdk/pay/bean/coupon/CouponEntity;Lcom/jd/lib/cashier/sdk/pay/bean/coupon/CouponEntity;)V", "<anonymous>"}, k = 3, mv = {1, 4, 0})
        /* renamed from: com.jd.lib.cashier.sdk.pay.aac.impl.channel.b$b$a */
        /* loaded from: classes14.dex */
        public static final class a extends Lambda implements Function2<CouponEntity, CouponEntity, Unit> {
            a() {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(CouponEntity couponEntity, CouponEntity couponEntity2) {
                invoke2(couponEntity, couponEntity2);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(@Nullable CouponEntity couponEntity, @Nullable CouponEntity couponEntity2) {
                a aVar = b.p;
                com.jd.lib.cashier.sdk.core.utils.r.b(aVar.a(), "currentCoupon = " + couponEntity);
                com.jd.lib.cashier.sdk.core.utils.r.b(aVar.a(), "targetCoupon = " + couponEntity2);
                b.this.L(couponEntity, couponEntity2);
            }
        }

        C0128b() {
            super(0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        @NotNull
        public final MultiCouponDialogHelper invoke() {
            MultiCouponDialogHelper multiCouponDialogHelper = new MultiCouponDialogHelper(b.this.f3691k, b.this.f3692l, b.this.f3693m, new a());
            multiCouponDialogHelper.c();
            return multiCouponDialogHelper;
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n\u00a2\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lcom/jd/lib/cashier/sdk/pay/bean/syncevent/ClickBaiTiaoPayPlanItemEvent;", AdvanceSetting.NETWORK_TYPE, "", "invoke", "(Lcom/jd/lib/cashier/sdk/pay/bean/syncevent/ClickBaiTiaoPayPlanItemEvent;)V", "<anonymous>"}, k = 3, mv = {1, 4, 0})
    /* loaded from: classes14.dex */
    static final class c extends Lambda implements Function1<ClickBaiTiaoPayPlanItemEvent, Unit> {
        final /* synthetic */ FragmentActivity $activity;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        c(FragmentActivity fragmentActivity) {
            super(1);
            this.$activity = fragmentActivity;
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(ClickBaiTiaoPayPlanItemEvent clickBaiTiaoPayPlanItemEvent) {
            invoke2(clickBaiTiaoPayPlanItemEvent);
            return Unit.INSTANCE;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r1v3, types: [androidx.fragment.app.FragmentActivity] */
        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(@NotNull ClickBaiTiaoPayPlanItemEvent clickBaiTiaoPayPlanItemEvent) {
            CouponEntity couponEntity;
            String str;
            String str2;
            String str3;
            String str4;
            CashierPayViewModel x;
            com.jd.lib.cashier.sdk.h.c.a b;
            com.jd.lib.cashier.sdk.h.a.c.a w;
            CashierPayViewModel x2;
            com.jd.lib.cashier.sdk.h.c.a b2;
            Payment currentPayment = clickBaiTiaoPayPlanItemEvent.getCurrentPayment();
            if (b.this.I(currentPayment)) {
                FragmentActivity fragmentActivity = this.$activity;
                if (!(fragmentActivity instanceof CashierPayActivity)) {
                    fragmentActivity = null;
                }
                CashierPayActivity cashierPayActivity = (CashierPayActivity) fragmentActivity;
                if (!b.this.I((cashierPayActivity == null || (x2 = cashierPayActivity.x()) == null || (b2 = x2.b()) == null) ? null : b2.O)) {
                    b.this.D().invoke(currentPayment);
                }
                if (clickBaiTiaoPayPlanItemEvent.getPlanFeeEntity().getSkuSplitFlag()) {
                    if (b.this.K()) {
                        return;
                    }
                    FragmentActivity fragmentActivity2 = this.$activity;
                    if (!(fragmentActivity2 instanceof CashierPayActivity)) {
                        fragmentActivity2 = null;
                    }
                    CashierPayActivity cashierPayActivity2 = (CashierPayActivity) fragmentActivity2;
                    if (cashierPayActivity2 != null && (x = cashierPayActivity2.x()) != null && (b = x.b()) != null) {
                        ?? r1 = this.$activity;
                        CashierPayActivity cashierPayActivity3 = (CashierPayActivity) (r1 instanceof CashierPayActivity ? r1 : null);
                        if (cashierPayActivity3 == null || (w = cashierPayActivity3.w()) == null) {
                            return;
                        }
                        w.e(this.$activity, b);
                        return;
                    }
                }
                String str5 = (currentPayment == null || (str4 = currentPayment.code) == null) ? "" : str4;
                String str6 = (currentPayment == null || (str3 = currentPayment.channelId) == null) ? "" : str3;
                String str7 = (currentPayment == null || (str2 = currentPayment.planRate) == null) ? "" : str2;
                String str8 = (currentPayment == null || (str = currentPayment.combineType) == null) ? "" : str;
                String plan = clickBaiTiaoPayPlanItemEvent.getLastPlanFeeEntity().getPlan();
                if (currentPayment != null && (couponEntity = currentPayment.baiTiaoPlanInfoCoupon) != null) {
                    r4 = couponEntity;
                } else if (currentPayment != null) {
                    r4 = currentPayment.defaultCoupon;
                }
                b bVar = b.this;
                CouponEntity B = bVar.B(bVar.A().getCurrentCoupon(), r4);
                String plan2 = clickBaiTiaoPayPlanItemEvent.getPlanFeeEntity().getPlan();
                CouponEntity recommendCoupon = clickBaiTiaoPayPlanItemEvent.getPlanFeeEntity().getRecommendCoupon();
                com.jd.lib.cashier.sdk.core.utils.r.b(b.p.a(), "payPlanItemView onEvent currentCoupon = " + B);
                b.z(b.this, str5, str6, str8, "2", str7, new Pair(plan, B), new Pair(plan2, recommendCoupon), false, 128, null);
            }
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\u001f\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0002H\u0016\u00a2\u0006\u0004\b\u0007\u0010\b\u00a8\u0006\t"}, d2 = {"com/jd/lib/cashier/sdk/pay/aac/impl/channel/b$d", "Lcom/jd/lib/cashier/sdk/b/i/g;", "Lcom/jd/lib/cashier/sdk/pay/bean/syncevent/ClickBaiTiaoCouponItemEvent;", "", "action", "eventData", "", "onEvent", "(Ljava/lang/String;Lcom/jd/lib/cashier/sdk/pay/bean/syncevent/ClickBaiTiaoCouponItemEvent;)V", "cashier_release"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes14.dex */
    public static final class d implements com.jd.lib.cashier.sdk.b.i.g<ClickBaiTiaoCouponItemEvent> {
        final /* synthetic */ FragmentActivity b;

        /* JADX INFO: Access modifiers changed from: package-private */
        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0002\u0010\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\n\u00a2\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"", "invoke", "()V", "<anonymous>"}, k = 3, mv = {1, 4, 0})
        /* loaded from: classes14.dex */
        public static final class a extends Lambda implements Function0<Unit> {
            final /* synthetic */ Payment $currentClickPayment;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            a(Payment payment) {
                super(0);
                this.$currentClickPayment = payment;
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2() {
                com.jd.lib.cashier.sdk.h.e.a d = com.jd.lib.cashier.sdk.h.e.a.d();
                FragmentActivity fragmentActivity = d.this.b;
                Payment payment = this.$currentClickPayment;
                d.y(fragmentActivity, payment.code, payment.uniqueChannelId);
                b bVar = b.this;
                Payment payment2 = this.$currentClickPayment;
                String str = payment2.code;
                if (str == null) {
                    str = "";
                }
                String str2 = payment2.channelId;
                if (str2 == null) {
                    str2 = "";
                }
                String str3 = payment2.combineType;
                if (str3 == null) {
                    str3 = "";
                }
                String str4 = payment2.planRate;
                if (str4 == null) {
                    str4 = "";
                }
                String str5 = payment2.currentSelectedPlan;
                if (str5 == null) {
                    str5 = "1";
                }
                b.z(bVar, str, str2, str3, "3", str4, new Pair(str5, null), new Pair(null, null), false, 128, null);
            }
        }

        d(FragmentActivity fragmentActivity) {
            this.b = fragmentActivity;
        }

        @Override // com.jd.lib.cashier.sdk.b.i.g
        public void onEvent(@NotNull String action, @NotNull ClickBaiTiaoCouponItemEvent eventData) {
            com.jd.lib.cashier.sdk.h.c.a b;
            com.jd.lib.cashier.sdk.core.utils.r.b(b.p.a(), "EventBusManager register onEvent CLICK_COUPON_ON_PAYMENT_CHANNEL");
            Payment payment = eventData.getPayment();
            if (b.this.I(payment)) {
                com.jd.lib.cashier.sdk.h.e.a.d().C(this.b, payment.code, payment.uniqueChannelId);
                b.this.A().u(new a(payment));
                FragmentActivity fragmentActivity = this.b;
                if (!(fragmentActivity instanceof CashierPayActivity)) {
                    fragmentActivity = null;
                }
                CashierPayActivity cashierPayActivity = (CashierPayActivity) fragmentActivity;
                CashierPayViewModel x = cashierPayActivity != null ? cashierPayActivity.x() : null;
                Payment payment2 = (x == null || (b = x.b()) == null) ? null : b.O;
                if (b.this.I(payment2) && b.this.A().o()) {
                    if (x != null) {
                        x.h(payment.combineType, payment.channelId, b.this.f3693m);
                    }
                } else if (b.this.I(payment2) && !b.this.A().o()) {
                    b.this.A().e(payment.combineType, b.this.A().getCurrentCoupon(), null, "1");
                } else {
                    b.this.D().invoke(payment);
                    b.this.E(payment, true);
                }
            }
        }
    }

    /* loaded from: classes14.dex */
    static final class e<T> implements Observer<com.jd.lib.cashier.sdk.pay.aac.livedata.a.b> {

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ FragmentActivity f3696h;

        e(FragmentActivity fragmentActivity) {
            this.f3696h = fragmentActivity;
        }

        @Override // androidx.lifecycle.Observer
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public final void onChanged(com.jd.lib.cashier.sdk.pay.aac.livedata.a.b bVar) {
            CashierPayViewModel x;
            com.jd.lib.cashier.sdk.h.c.a b;
            if (bVar != null) {
                com.jd.lib.cashier.sdk.pay.aac.livedata.a.p b2 = bVar.b();
                FragmentActivity fragmentActivity = this.f3696h;
                if (!(fragmentActivity instanceof CashierPayActivity)) {
                    fragmentActivity = null;
                }
                CashierPayActivity cashierPayActivity = (CashierPayActivity) fragmentActivity;
                Payment payment = (cashierPayActivity == null || (x = cashierPayActivity.x()) == null || (b = x.b()) == null) ? null : b.O;
                if (b.this.J(b2) && b.this.I(payment)) {
                    BaiTiaoPayPlanResponse a = bVar.a();
                    com.jd.lib.cashier.sdk.pay.aac.livedata.a.p b3 = bVar.b();
                    String c2 = b3 != null ? b3.c() : null;
                    if ((a != null ? Boolean.valueOf(a.clearCouponInfo) : null).booleanValue()) {
                        if (payment != null) {
                            payment.baiTiaoTip = "";
                        }
                        if (payment != null) {
                            payment.baiTiaoHighlightTip = "";
                        }
                    }
                    String str = a.errorCode;
                    if (str != null && str.hashCode() == 1510339 && str.equals("1312")) {
                        b.this.A().d();
                        b.this.A().t(com.jd.lib.cashier.sdk.b.c.c.a());
                        b.this.A().s(com.jd.lib.cashier.sdk.b.c.c.a());
                        com.jd.lib.cashier.sdk.h.h.m.q(this.f3696h, "", "");
                        if (payment != null) {
                            payment.selectedCouponEntity = com.jd.lib.cashier.sdk.b.c.c.a();
                        }
                        com.jd.lib.cashier.sdk.b.i.e.g("SEND_UPDATE_PAYMENT_LIST", "UPDATE_PAYMENT_LIST", new UpdatePaymentListEvent(null, false));
                        return;
                    }
                    if (!TextUtils.equals("0", c2)) {
                        if (!(a != null ? Boolean.valueOf(a.clearCouponInfo) : null).booleanValue()) {
                            return;
                        }
                    }
                    com.jd.lib.cashier.sdk.b.i.e.g("SEND_UPDATE_PAYMENT_LIST", "UPDATE_PAYMENT_LIST", new UpdatePaymentListEvent(null, false));
                }
            }
        }
    }

    /* loaded from: classes14.dex */
    static final class f<T> implements Observer<com.jd.lib.cashier.sdk.pay.aac.livedata.a.c> {

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ FragmentActivity f3698h;

        f(FragmentActivity fragmentActivity) {
            this.f3698h = fragmentActivity;
        }

        /* JADX WARN: Code restructure failed: missing block: B:48:0x00b1, code lost:
            if ((r6 == null || r6.isEmpty()) == false) goto L49;
         */
        /* JADX WARN: Removed duplicated region for block: B:52:0x00ef  */
        /* JADX WARN: Removed duplicated region for block: B:55:0x0112  */
        /* JADX WARN: Removed duplicated region for block: B:63:0x012e  */
        /* JADX WARN: Removed duplicated region for block: B:66:0x0133  */
        /* JADX WARN: Removed duplicated region for block: B:82:0x0154  */
        /* JADX WARN: Removed duplicated region for block: B:83:0x0159  */
        /* JADX WARN: Removed duplicated region for block: B:86:0x01a8  */
        /* JADX WARN: Removed duplicated region for block: B:87:0x01ad  */
        @Override // androidx.lifecycle.Observer
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public final void onChanged(@Nullable com.jd.lib.cashier.sdk.pay.aac.livedata.a.c cVar) {
            CouponEntity f2;
            List<PlanFeeEntity> list;
            PlanFeeEntity planFeeEntity;
            String str;
            String planInfo;
            String str2;
            T t;
            CashierPayViewModel x;
            com.jd.lib.cashier.sdk.h.c.a b;
            if (cVar != null) {
                com.jd.lib.cashier.sdk.pay.aac.livedata.a.p b2 = cVar.b();
                FragmentActivity fragmentActivity = this.f3698h;
                if (!(fragmentActivity instanceof CashierPayActivity)) {
                    fragmentActivity = null;
                }
                CashierPayActivity cashierPayActivity = (CashierPayActivity) fragmentActivity;
                Payment payment = (cashierPayActivity == null || (x = cashierPayActivity.x()) == null || (b = x.b()) == null) ? null : b.O;
                if (b.this.J(b2) && b.this.I(payment)) {
                    b.this.f3690j = false;
                    BaiTiaoPayPlanResponse a = cVar.a();
                    com.jd.lib.cashier.sdk.core.utils.r.b(b.p.a(), "\u8c03\u8bd5 \u767d\u6761\u8bd5\u7b97\u63a5\u53e3 payPlanItemView baiTiaoPayPlanResponseEntity = " + a);
                    if (Intrinsics.areEqual(b2 != null ? b2.c() : null, "3")) {
                        b.this.A().d();
                    }
                    String str3 = a.discountConflictTip;
                    if (!TextUtils.isEmpty(str3)) {
                        f0.b(this.f3698h, str3);
                    }
                    if (1 != a.isMarketRecommendModify) {
                        if (b.this.A().o()) {
                            List<CouponEntity> list2 = a.canUseCouponList;
                            if (list2 == null || list2.isEmpty()) {
                                List<CouponEntity> list3 = a.cantUseCouponList;
                            }
                        }
                        f2 = b.this.A().f(a.selectedCoupon, a.canUseCouponList, a.cantUseCouponList);
                        b.this.A().t(f2);
                        b.this.A().s(f2);
                        b.this.A().w(f2, this.f3698h);
                        if (f2 != null) {
                            f2.setTopPriceAnimationInfo(cVar.a().topPriceAnimationInfo);
                        }
                        com.jd.lib.cashier.sdk.core.utils.r.b("PaymentParamsHelper", "baiTiao proxy currentSelectedCoupon = " + f2);
                        list = a.planFeeList;
                        if (list == null) {
                            Iterator<T> it = list.iterator();
                            while (true) {
                                if (!it.hasNext()) {
                                    t = null;
                                    break;
                                }
                                t = it.next();
                                if (((PlanFeeEntity) t).getSelected()) {
                                    break;
                                }
                            }
                            planFeeEntity = (PlanFeeEntity) t;
                        } else {
                            planFeeEntity = null;
                        }
                        str = "";
                        if (payment != null) {
                            if (planFeeEntity == null || (str2 = planFeeEntity.getPlan()) == null) {
                                str2 = "";
                            }
                            payment.currentSelectedPlan = str2;
                        }
                        if (planFeeEntity != null || (r11 = planFeeEntity.getPlan()) == null) {
                            String str4 = "";
                        }
                        if (planFeeEntity != null && (planInfo = planFeeEntity.getPlanInfo()) != null) {
                            str = planInfo;
                        }
                        com.jd.lib.cashier.sdk.h.h.m.p(this.f3698h, str4, str, planFeeEntity == null ? planFeeEntity.getTradeMap() : null);
                        com.jd.lib.cashier.sdk.core.utils.r.b("PaymentParamsHelper", "baiTiao proxy currentPlanFeeEntity = " + planFeeEntity);
                        b.this.O(payment, f2, new BaiTiaoPayPlanBizData(a.agreementServiceMap, a.baiTiaoTip, a.baiTiaoHighlightTip, a.investorDoc, a.serviceMap, a.extraTip, a.mianxiHighlight, a.planFeeList), Boolean.TRUE);
                        com.jd.lib.cashier.sdk.b.i.e.g("SEND_UPDATE_PAYMENT_LIST", "UPDATE_PAYMENT_LIST", new UpdatePaymentListEvent(null, false));
                        com.jd.lib.cashier.sdk.b.i.e.f("CLICK_BAITIAO_PLAN_TO_UPDATE_FOOTER", new ClickBaiTiaoPlanToFooterEvent(planFeeEntity == null ? planFeeEntity.getRealPayAmount() : null));
                        if (b2 == null && b2.d()) {
                            b.this.A().e(payment != null ? payment.combineType : null, b.this.A().getCurrentCoupon(), null, "1");
                            return;
                        }
                    }
                    b.this.A().v(a.canUseCouponList, a.cantUseCouponList);
                    f2 = b.this.A().f(a.selectedCoupon, a.canUseCouponList, a.cantUseCouponList);
                    b.this.A().t(f2);
                    b.this.A().s(f2);
                    b.this.A().w(f2, this.f3698h);
                    if (f2 != null) {
                    }
                    com.jd.lib.cashier.sdk.core.utils.r.b("PaymentParamsHelper", "baiTiao proxy currentSelectedCoupon = " + f2);
                    list = a.planFeeList;
                    if (list == null) {
                    }
                    str = "";
                    if (payment != null) {
                    }
                    if (planFeeEntity != null) {
                    }
                    String str42 = "";
                    if (planFeeEntity != null) {
                        str = planInfo;
                    }
                    com.jd.lib.cashier.sdk.h.h.m.p(this.f3698h, str42, str, planFeeEntity == null ? planFeeEntity.getTradeMap() : null);
                    com.jd.lib.cashier.sdk.core.utils.r.b("PaymentParamsHelper", "baiTiao proxy currentPlanFeeEntity = " + planFeeEntity);
                    b.this.O(payment, f2, new BaiTiaoPayPlanBizData(a.agreementServiceMap, a.baiTiaoTip, a.baiTiaoHighlightTip, a.investorDoc, a.serviceMap, a.extraTip, a.mianxiHighlight, a.planFeeList), Boolean.TRUE);
                    com.jd.lib.cashier.sdk.b.i.e.g("SEND_UPDATE_PAYMENT_LIST", "UPDATE_PAYMENT_LIST", new UpdatePaymentListEvent(null, false));
                    com.jd.lib.cashier.sdk.b.i.e.f("CLICK_BAITIAO_PLAN_TO_UPDATE_FOOTER", new ClickBaiTiaoPlanToFooterEvent(planFeeEntity == null ? planFeeEntity.getRealPayAmount() : null));
                    if (b2 == null) {
                    }
                }
            }
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\u001f\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0002H\u0016\u00a2\u0006\u0004\b\u0007\u0010\b\u00a8\u0006\t"}, d2 = {"com/jd/lib/cashier/sdk/pay/aac/impl/channel/b$g", "Lcom/jd/lib/cashier/sdk/b/i/g;", "Lcom/jd/lib/cashier/sdk/pay/bean/syncevent/MonitorClickBaiTiaoNoPlanEvent;", "", "action", "eventData", "", "onEvent", "(Ljava/lang/String;Lcom/jd/lib/cashier/sdk/pay/bean/syncevent/MonitorClickBaiTiaoNoPlanEvent;)V", "cashier_release"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes14.dex */
    public static final class g implements com.jd.lib.cashier.sdk.b.i.g<MonitorClickBaiTiaoNoPlanEvent> {
        g() {
        }

        @Override // com.jd.lib.cashier.sdk.b.i.g
        public void onEvent(@NotNull String action, @NotNull MonitorClickBaiTiaoNoPlanEvent eventData) {
            if (TextUtils.equals(b.this.f3691k, eventData.getChannelType()) && TextUtils.equals(b.this.f3692l, eventData.getChannelId())) {
                b.this.x();
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public b(@NotNull String str, @NotNull String str2, @NotNull FragmentActivity fragmentActivity, @NotNull Function1<? super Payment, Unit> function1) {
        Lazy lazy;
        this.f3691k = str;
        this.f3692l = str2;
        this.f3693m = fragmentActivity;
        this.f3694n = function1;
        lazy = LazyKt__LazyJVMKt.lazy(new C0128b());
        this.f3689i = lazy;
        this.f3690j = true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final MultiCouponDialogHelper A() {
        return (MultiCouponDialogHelper) this.f3689i.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final CouponEntity B(com.jd.lib.cashier.sdk.pay.dialog.e eVar, CouponEntity couponEntity) {
        if (eVar == null) {
            return couponEntity;
        }
        if (!(eVar instanceof CouponEntity)) {
            eVar = null;
        }
        return (CouponEntity) eVar;
    }

    private final String C() {
        return (this.f3690j && A().o()) ? "1" : "";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void E(Payment payment, boolean z) {
        String str;
        CouponEntity couponEntity;
        PlanFeeEntity planFeeEntity;
        String str2;
        String str3;
        String str4;
        String str5;
        String str6 = o;
        com.jd.lib.cashier.sdk.core.utils.r.b(str6, "EventBusManager handleBaiTiaoPayPlanOnPaymentItem");
        String str7 = (payment == null || (str5 = payment.code) == null) ? "" : str5;
        String str8 = (payment == null || (str4 = payment.channelId) == null) ? "" : str4;
        String str9 = (payment == null || (str3 = payment.combineType) == null) ? "" : str3;
        String str10 = (payment == null || (str2 = payment.planRate) == null) ? "" : str2;
        String str11 = "1";
        if (!TextUtils.isEmpty(payment != null ? payment.currentSelectedPlan : null) ? !(payment == null || (str = payment.currentSelectedPlan) == null) : !(payment == null || (planFeeEntity = payment.defaultPlanFee) == null || (str = planFeeEntity.getPlan()) == null)) {
            str11 = str;
        }
        if (payment == null || (couponEntity = payment.baiTiaoPlanInfoCoupon) == null) {
            couponEntity = payment != null ? payment.defaultCoupon : null;
        }
        CouponEntity B = B(A().getCurrentCoupon(), couponEntity);
        com.jd.lib.cashier.sdk.core.utils.r.b(str6, "handleBaiTiaoPayPlanOnPaymentItem currentCouponEntity = " + B);
        y(str7, str8, str9, "0", str10, new Pair<>(str11, B), new Pair<>(str11, null), z);
    }

    private final void G(Payment payment, String str, CouponEntity couponEntity) {
        String str2;
        CouponEntity couponEntity2;
        PlanFeeEntity planFeeEntity;
        String str3;
        String str4;
        String str5;
        String str6;
        String str7 = o;
        com.jd.lib.cashier.sdk.core.utils.r.b(str7, "EventBusManager handleBaiTiaoPayPlanOnPaymentItem");
        String str8 = (payment == null || (str6 = payment.code) == null) ? "" : str6;
        String str9 = (payment == null || (str5 = payment.channelId) == null) ? "" : str5;
        String str10 = (payment == null || (str4 = payment.combineType) == null) ? "" : str4;
        String str11 = (payment == null || (str3 = payment.planRate) == null) ? "" : str3;
        CouponEntity couponEntity3 = null;
        String str12 = "1";
        if (!TextUtils.isEmpty(payment != null ? payment.currentSelectedPlan : null) ? !(payment == null || (str2 = payment.currentSelectedPlan) == null) : !(payment == null || (planFeeEntity = payment.defaultPlanFee) == null || (str2 = planFeeEntity.getPlan()) == null)) {
            str12 = str2;
        }
        if (payment != null && (couponEntity2 = payment.baiTiaoPlanInfoCoupon) != null) {
            couponEntity3 = couponEntity2;
        } else if (payment != null) {
            couponEntity3 = payment.defaultCoupon;
        }
        CouponEntity B = B(A().getCurrentCoupon(), couponEntity3);
        com.jd.lib.cashier.sdk.core.utils.r.b(str7, "handleBaiTiaoPayPlanOnPaymentItem currentCouponEntity = " + B);
        y(str8, str9, str10, "2", str11, new Pair<>(str12, B), new Pair<>(str, couponEntity), false);
    }

    private final void H(Payment payment) {
        PlanFeeEntity planFeeEntity;
        String str;
        String planInfo;
        String str2;
        List<PlanFeeEntity> list;
        Object obj;
        if (I(payment)) {
            BaiTiaoPlanInfo baiTiaoPlanInfo = payment != null ? payment.baitiaoPlanInfo : null;
            com.jd.lib.cashier.sdk.core.utils.r.b(o, "\u8c03\u8bd5 platPayChannel baitiaoPlanInfo = " + baiTiaoPlanInfo);
            CouponEntity couponEntity = baiTiaoPlanInfo != null ? baiTiaoPlanInfo.selectedCoupon : null;
            A().t(couponEntity);
            A().s(couponEntity);
            A().w(couponEntity, this.f3693m);
            if (couponEntity != null) {
                couponEntity.setTopPriceAnimationInfo(baiTiaoPlanInfo != null ? baiTiaoPlanInfo.topPriceAnimationInfo : null);
            }
            com.jd.lib.cashier.sdk.core.utils.r.b("PaymentParamsHelper", "baiTiao proxy currentSelectedCoupon = " + couponEntity);
            if (baiTiaoPlanInfo == null || (list = baiTiaoPlanInfo.planFeeList) == null) {
                planFeeEntity = null;
            } else {
                Iterator<T> it = list.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        obj = null;
                        break;
                    }
                    obj = it.next();
                    if (((PlanFeeEntity) obj).getSelected()) {
                        break;
                    }
                }
                planFeeEntity = (PlanFeeEntity) obj;
            }
            String str3 = "";
            if (payment != null) {
                if (planFeeEntity == null || (str2 = planFeeEntity.getPlan()) == null) {
                    str2 = "";
                }
                payment.currentSelectedPlan = str2;
            }
            if (planFeeEntity == null || (str = planFeeEntity.getPlan()) == null) {
                str = "";
            }
            if (planFeeEntity != null && (planInfo = planFeeEntity.getPlanInfo()) != null) {
                str3 = planInfo;
            }
            com.jd.lib.cashier.sdk.h.h.m.p(this.f3693m, str, str3, planFeeEntity != null ? planFeeEntity.getTradeMap() : null);
            com.jd.lib.cashier.sdk.core.utils.r.b("PaymentParamsHelper", "baiTiao proxy currentPlanFeeEntity = " + planFeeEntity);
            P(this, payment, couponEntity, new BaiTiaoPayPlanBizData(baiTiaoPlanInfo != null ? baiTiaoPlanInfo.agreementServiceMap : null, baiTiaoPlanInfo != null ? baiTiaoPlanInfo.serviceMap : null, baiTiaoPlanInfo != null ? baiTiaoPlanInfo.extraTip : null, baiTiaoPlanInfo != null ? baiTiaoPlanInfo.mianxiHighlight : null, baiTiaoPlanInfo != null ? baiTiaoPlanInfo.planFeeList : null), null, 8, null);
            com.jd.lib.cashier.sdk.b.i.e.g("SEND_UPDATE_PAYMENT_LIST", "UPDATE_PAYMENT_LIST", new UpdatePaymentListEvent(null, true));
            com.jd.lib.cashier.sdk.b.i.e.f("CLICK_BAITIAO_PLAN_TO_UPDATE_FOOTER", new ClickBaiTiaoPlanToFooterEvent(planFeeEntity != null ? planFeeEntity.getRealPayAmount() : null));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean I(Payment payment) {
        String str;
        if (payment == null || (str = com.jd.lib.cashier.sdk.h.h.e.a.d(payment)) == null) {
            str = "";
        }
        return TextUtils.equals(payment != null ? payment.code : null, this.f3691k) && TextUtils.equals(str, this.f3692l);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean J(com.jd.lib.cashier.sdk.pay.aac.livedata.a.p pVar) {
        if (TextUtils.equals(pVar != null ? pVar.b() : null, this.f3691k)) {
            if (TextUtils.equals(pVar != null ? pVar.a() : null, this.f3692l)) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean K() {
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - this.f3688h <= this.f3687g) {
            return true;
        }
        this.f3688h = currentTimeMillis;
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void L(CouponEntity couponEntity, CouponEntity couponEntity2) {
        String str;
        String str2;
        String str3;
        String str4;
        String str5;
        CashierPayViewModel x;
        com.jd.lib.cashier.sdk.h.c.a b;
        FragmentActivity fragmentActivity = this.f3693m;
        Payment payment = null;
        if (!(fragmentActivity instanceof CashierPayActivity)) {
            fragmentActivity = null;
        }
        CashierPayActivity cashierPayActivity = (CashierPayActivity) fragmentActivity;
        if (cashierPayActivity != null && (x = cashierPayActivity.x()) != null && (b = x.b()) != null) {
            payment = b.O;
        }
        if (payment == null || (str = payment.currentSelectedPlan) == null) {
            str = "1";
        }
        if (I(payment)) {
            if (payment == null || (str2 = payment.code) == null) {
                str2 = "";
            }
            if (payment == null || (str3 = payment.channelId) == null) {
                str3 = "";
            }
            if (payment == null || (str4 = payment.combineType) == null) {
                str4 = "";
            }
            z(this, str2, str3, str4, "1", (payment == null || (str5 = payment.planRate) == null) ? "" : str5, new Pair(str, couponEntity), new Pair("", couponEntity2), false, 128, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void O(Payment payment, CouponEntity couponEntity, BaiTiaoPayPlanBizData baiTiaoPayPlanBizData, Boolean bool) {
        List<IPlanItemViewEntity> list;
        List<IPlanItemViewEntity> list2;
        Collection<? extends IPlanItemViewEntity> collection = baiTiaoPayPlanBizData.planFeeList;
        PlanServiceMap planServiceMap = baiTiaoPayPlanBizData.serviceMap;
        AgreementServiceMapMap agreementServiceMapMap = baiTiaoPayPlanBizData.agreementServiceMap;
        BaiTiaoExtraTip baiTiaoExtraTip = baiTiaoPayPlanBizData.extraTip;
        String str = baiTiaoPayPlanBizData != null ? baiTiaoPayPlanBizData.mianxiHighlight : null;
        String str2 = baiTiaoPayPlanBizData != null ? baiTiaoPayPlanBizData.investorDoc : null;
        String str3 = baiTiaoPayPlanBizData != null ? baiTiaoPayPlanBizData.baiTiaoTip : null;
        String str4 = baiTiaoPayPlanBizData != null ? baiTiaoPayPlanBizData.baiTiaoHighlightTip : null;
        if (payment != null) {
            payment.serviceMap = planServiceMap;
        }
        if (payment != null) {
            payment.mianxiHighlight = str;
        }
        if (payment != null) {
            payment.agreementServiceMap = agreementServiceMapMap;
        }
        if (payment != null) {
            payment.selectedCouponEntity = couponEntity;
        }
        if (payment != null && (list2 = payment.planFeeEntityList) != null) {
            list2.clear();
        }
        if (payment != null && (list = payment.planFeeEntityList) != null) {
            if (collection == null) {
                collection = new ArrayList<>();
            }
            list.addAll(collection);
        }
        if (payment != null) {
            payment.baiTiaoExtraTip = baiTiaoExtraTip;
        }
        if ((baiTiaoPayPlanBizData != null ? Boolean.valueOf(baiTiaoPayPlanBizData.useInvestorDoc) : null).booleanValue() && payment != null) {
            payment.investorDoc = str2;
        }
        if (payment != null) {
            payment.planButtonTitle = agreementServiceMapMap != null ? agreementServiceMapMap.agreementButtonTitle : null;
        }
        if (Intrinsics.areEqual(bool, Boolean.TRUE)) {
            if (payment != null) {
                payment.baiTiaoTip = str3;
            }
            if (payment != null) {
                payment.baiTiaoHighlightTip = str4;
            }
        }
    }

    static /* synthetic */ void P(b bVar, Payment payment, CouponEntity couponEntity, BaiTiaoPayPlanBizData baiTiaoPayPlanBizData, Boolean bool, int i2, Object obj) {
        if ((i2 & 8) != 0) {
            bool = Boolean.FALSE;
        }
        bVar.O(payment, couponEntity, baiTiaoPayPlanBizData, bool);
    }

    public static /* synthetic */ void w(b bVar, Payment payment, boolean z, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            z = false;
        }
        bVar.v(payment, z);
    }

    private final void y(String str, String str2, String str3, String str4, String str5, Pair<String, CouponEntity> pair, Pair<String, CouponEntity> pair2, boolean z) {
        String str6;
        FragmentActivity fragmentActivity = this.f3693m;
        if (!(fragmentActivity instanceof CashierPayActivity)) {
            fragmentActivity = null;
        }
        CashierPayActivity cashierPayActivity = (CashierPayActivity) fragmentActivity;
        CashierPayViewModel x = cashierPayActivity != null ? cashierPayActivity.x() : null;
        com.jd.lib.cashier.sdk.h.c.a b = x != null ? x.b() : null;
        String component1 = pair.component1();
        CouponEntity component2 = pair.component2();
        String component12 = pair2.component1();
        CouponEntity component22 = pair2.component2();
        CouponEntityRequest convertToCouponEntityRequest = component2 != null ? component2.convertToCouponEntityRequest() : null;
        CouponEntityRequest convertToCouponEntityRequest2 = component22 != null ? component22.convertToCouponEntityRequest() : null;
        String C = C();
        if (b == null || (str6 = b.B) == null) {
            str6 = "";
        }
        com.jd.lib.cashier.sdk.h.f.b bVar = new com.jd.lib.cashier.sdk.h.f.b(str, str2, C, str4, component1, component12, convertToCouponEntityRequest, convertToCouponEntityRequest2, str5, str6, str3);
        bVar.setActivity(this.f3693m);
        bVar.t(z);
        com.jd.lib.cashier.sdk.core.utils.r.b(o, "doBaiTiaoPayPlanRequest requestParam value = " + bVar);
        if (x != null) {
            x.e(bVar);
        }
    }

    static /* synthetic */ void z(b bVar, String str, String str2, String str3, String str4, String str5, Pair pair, Pair pair2, boolean z, int i2, Object obj) {
        bVar.y(str, str2, str3, str4, str5, pair, pair2, (i2 & 128) != 0 ? false : z);
    }

    @NotNull
    public final Function1<Payment, Unit> D() {
        return this.f3694n;
    }

    public final void F(@Nullable Payment payment) {
        if ((payment != null ? payment.baitiaoPlanInfo : null) != null) {
            H(payment);
        }
    }

    public final void M() {
        A().r();
    }

    public final void N(@Nullable Payment payment) {
        if (I(payment)) {
            BaiTiaoPlanInfo baiTiaoPlanInfo = payment != null ? payment.baitiaoPlanInfo : null;
            A().t(baiTiaoPlanInfo != null ? baiTiaoPlanInfo.selectedCoupon : null);
            A().s(baiTiaoPlanInfo != null ? baiTiaoPlanInfo.selectedCoupon : null);
            A().w(baiTiaoPlanInfo != null ? baiTiaoPlanInfo.selectedCoupon : null, this.f3693m);
            P(this, payment, baiTiaoPlanInfo != null ? baiTiaoPlanInfo.selectedCoupon : null, new BaiTiaoPayPlanBizData(baiTiaoPlanInfo != null ? baiTiaoPlanInfo.agreementServiceMap : null, baiTiaoPlanInfo != null ? baiTiaoPlanInfo.serviceMap : null, baiTiaoPlanInfo != null ? baiTiaoPlanInfo.extraTip : null, baiTiaoPlanInfo != null ? baiTiaoPlanInfo.mianxiHighlight : null, baiTiaoPlanInfo != null ? baiTiaoPlanInfo.planFeeList : null), null, 8, null);
            com.jd.lib.cashier.sdk.b.i.e.g("SEND_UPDATE_PAYMENT_LIST", "UPDATE_PAYMENT_LIST", new UpdatePaymentListEvent(null, true));
        }
    }

    @Override // com.jd.lib.cashier.sdk.core.aac.d
    public void f(@NotNull FragmentActivity fragmentActivity) {
        com.jd.lib.cashier.sdk.core.utils.r.b(o, "EventBusManager subscribe");
        com.jd.lib.cashier.sdk.b.i.e.d("CLICK_PAYMENT_CHANNEL_PAY_PLAN_ITEM", "baitiao_plan", new c(fragmentActivity));
        com.jd.lib.cashier.sdk.b.i.e.c("click_baitiao_COUPON_on_item_key", new d(fragmentActivity));
        ViewModel viewModel = ViewModelProviders.of(fragmentActivity).get(CashierPayViewModel.class);
        Intrinsics.checkExpressionValueIsNotNull(viewModel, "ViewModelProviders.of(ac\u2026PayViewModel::class.java)");
        CashierPayViewModel cashierPayViewModel = (CashierPayViewModel) viewModel;
        cashierPayViewModel.q().observe(fragmentActivity, new e(fragmentActivity));
        cashierPayViewModel.r().observe(fragmentActivity, new f(fragmentActivity));
        com.jd.lib.cashier.sdk.b.i.e.c("MONITOR_CLICK_BAITIAO_CHANNEL_NO_PLAN_EVENT", new g());
    }

    public final void v(@Nullable Payment payment, boolean z) {
        if (!TextUtils.isEmpty(payment != null ? payment.recommendPlan : null)) {
            if ((payment != null ? payment.recommendCoupon : null) != null) {
                G(payment, payment.recommendPlan, payment.recommendCoupon);
                payment.recommendPlan = "";
                payment.recommendCoupon = null;
                return;
            }
        }
        E(payment, z);
    }

    public final void x() {
        String str;
        String str2;
        CouponEntity couponEntity;
        String str3;
        PlanFeeEntity planFeeEntity;
        String str4;
        String plan;
        String str5;
        CashierPayViewModel x;
        com.jd.lib.cashier.sdk.h.c.a b;
        FragmentActivity fragmentActivity = this.f3693m;
        if (!(fragmentActivity instanceof CashierPayActivity)) {
            fragmentActivity = null;
        }
        CashierPayActivity cashierPayActivity = (CashierPayActivity) fragmentActivity;
        Payment payment = (cashierPayActivity == null || (x = cashierPayActivity.x()) == null || (b = x.b()) == null) ? null : b.O;
        if (I(payment)) {
            List<IPlanItemViewEntity> list = payment != null ? payment.planFeeEntityList : null;
            String str6 = "";
            if (payment == null || (str = payment.code) == null) {
                str = "";
            }
            if (payment == null || (str2 = payment.channelId) == null) {
                str2 = "";
            }
            if (payment == null || (couponEntity = payment.baiTiaoPlanInfoCoupon) == null) {
                couponEntity = payment != null ? payment.defaultCoupon : null;
            }
            CouponEntity B = B(A().getCurrentCoupon(), couponEntity);
            if (payment == null || (str3 = payment.planRate) == null) {
                str3 = "";
            }
            String str7 = (payment == null || (str5 = payment.combineType) == null) ? "" : str5;
            if (list != null) {
                planFeeEntity = null;
                str4 = "";
                for (IPlanItemViewEntity iPlanItemViewEntity : list) {
                    if (iPlanItemViewEntity instanceof PlanFeeEntity) {
                        PlanFeeEntity planFeeEntity2 = (PlanFeeEntity) iPlanItemViewEntity;
                        if (TextUtils.equals("1", planFeeEntity2.getPlan())) {
                            planFeeEntity = planFeeEntity2;
                        }
                        if (planFeeEntity2.getSelected()) {
                            str4 = planFeeEntity2.getPlan();
                        }
                    }
                }
            } else {
                planFeeEntity = null;
                str4 = "";
            }
            if (planFeeEntity != null && (plan = planFeeEntity.getPlan()) != null) {
                str6 = plan;
            }
            CouponEntity recommendCoupon = planFeeEntity != null ? planFeeEntity.getRecommendCoupon() : null;
            com.jd.lib.cashier.sdk.core.utils.r.b(o, "payPlanItemView onEvent currentCoupon = " + B);
            z(this, str, str2, str7, "2", str3, new Pair(str4, B), new Pair(str6, recommendCoupon), false, 128, null);
        }
    }
}

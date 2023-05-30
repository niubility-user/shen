package com.jd.lib.cashier.sdk.pay.aac.impl;

import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import com.jd.lib.cashier.sdk.R;
import com.jd.lib.cashier.sdk.core.ui.widget.CashierNoticeView;
import com.jd.lib.cashier.sdk.core.ui.widget.PaymentTriggerView;
import com.jd.lib.cashier.sdk.core.utils.JDDarkUtil;
import com.jd.lib.cashier.sdk.core.utils.f0;
import com.jd.lib.cashier.sdk.core.utils.g0;
import com.jd.lib.cashier.sdk.core.utils.j0;
import com.jd.lib.cashier.sdk.core.utils.o0;
import com.jd.lib.cashier.sdk.core.utils.r;
import com.jd.lib.cashier.sdk.h.h.m;
import com.jd.lib.cashier.sdk.pay.aac.impl.channel.CyberMoneyPaymentProxy;
import com.jd.lib.cashier.sdk.pay.aac.impl.channel.s;
import com.jd.lib.cashier.sdk.pay.aac.viewmodel.CashierPayViewModel;
import com.jd.lib.cashier.sdk.pay.adapter.CashierPayAdapter;
import com.jd.lib.cashier.sdk.pay.bean.CashierPayEntity;
import com.jd.lib.cashier.sdk.pay.bean.Payment;
import com.jd.lib.cashier.sdk.pay.bean.TopFloor;
import com.jd.lib.cashier.sdk.pay.bean.convert.PaymentChoseHolder;
import com.jd.lib.cashier.sdk.pay.bean.digitalmoney.DigitalMoneyBankCard;
import com.jd.lib.cashier.sdk.pay.bean.digitalmoney.DigitalMoneyPayEntity;
import com.jd.lib.cashier.sdk.pay.bean.syncevent.ClickBaiTiaoPlanToFooterEvent;
import com.jd.lib.cashier.sdk.pay.bean.syncevent.ClickCommonCouponItemEvent;
import com.jd.lib.cashier.sdk.pay.bean.syncevent.ClickCreditCardPlanToFooterEvent;
import com.jd.lib.cashier.sdk.pay.view.CashierPayActivity;
import com.meizu.cloud.pushsdk.notification.model.AdvanceSetting;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Ref;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* loaded from: classes14.dex */
public final class b implements com.jd.lib.cashier.sdk.core.aac.d, com.jd.lib.cashier.sdk.core.aac.c, com.jd.lib.cashier.sdk.d.d.a, com.jd.lib.cashier.sdk.core.aac.e {
    private static final String p;
    @Nullable

    /* renamed from: g  reason: collision with root package name */
    private Payment f3643g;

    /* renamed from: h  reason: collision with root package name */
    private LinearLayout f3644h;

    /* renamed from: i  reason: collision with root package name */
    private LinearLayout f3645i;

    /* renamed from: j  reason: collision with root package name */
    private PaymentTriggerView f3646j;

    /* renamed from: k  reason: collision with root package name */
    private final Lazy f3647k;

    /* renamed from: l  reason: collision with root package name */
    private final Lazy f3648l;

    /* renamed from: m  reason: collision with root package name */
    private final Lazy f3649m;

    /* renamed from: n  reason: collision with root package name */
    private final Lazy f3650n;
    private FragmentActivity o;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\n\u00a2\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lcom/jd/lib/cashier/sdk/pay/handler/a;", "invoke", "()Lcom/jd/lib/cashier/sdk/pay/handler/a;", "<anonymous>"}, k = 3, mv = {1, 4, 0})
    /* loaded from: classes14.dex */
    static final class a extends Lambda implements Function0<com.jd.lib.cashier.sdk.pay.handler.a> {
        public static final a INSTANCE = new a();

        a() {
            super(0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        @NotNull
        public final com.jd.lib.cashier.sdk.pay.handler.a invoke() {
            return new com.jd.lib.cashier.sdk.pay.handler.a();
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\n\u00a2\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lcom/jd/lib/cashier/sdk/pay/aac/impl/channel/CyberMoneyPaymentProxy;", "invoke", "()Lcom/jd/lib/cashier/sdk/pay/aac/impl/channel/CyberMoneyPaymentProxy;", "<anonymous>"}, k = 3, mv = {1, 4, 0})
    /* renamed from: com.jd.lib.cashier.sdk.pay.aac.impl.b$b  reason: collision with other inner class name */
    /* loaded from: classes14.dex */
    static final class C0123b extends Lambda implements Function0<CyberMoneyPaymentProxy> {
        C0123b() {
            super(0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        @NotNull
        public final CyberMoneyPaymentProxy invoke() {
            return new CyberMoneyPaymentProxy(b.this.o);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public static final class c implements Runnable {
        c() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            b bVar = b.this;
            bVar.C(bVar.o);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n\u00a2\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Landroid/view/View;", AdvanceSetting.NETWORK_TYPE, "", "invoke", "(Landroid/view/View;)V", "<anonymous>"}, k = 3, mv = {1, 4, 0})
    /* loaded from: classes14.dex */
    public static final class d extends Lambda implements Function1<View, Unit> {
        d() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(View view) {
            invoke2(view);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(@NotNull View view) {
            CashierPayViewModel x;
            com.jd.lib.cashier.sdk.h.c.a b;
            CashierPayViewModel x2;
            com.jd.lib.cashier.sdk.h.c.a b2;
            FragmentActivity fragmentActivity = b.this.o;
            String str = null;
            if (!(fragmentActivity instanceof CashierPayActivity)) {
                fragmentActivity = null;
            }
            CashierPayActivity cashierPayActivity = (CashierPayActivity) fragmentActivity;
            if (b.this.r() == null) {
                if (!TextUtils.isEmpty((cashierPayActivity == null || (x2 = cashierPayActivity.x()) == null || (b2 = x2.b()) == null) ? null : b2.S)) {
                    if (cashierPayActivity != null && (x = cashierPayActivity.x()) != null && (b = x.b()) != null) {
                        str = b.S;
                    }
                    f0.c(str);
                }
            }
            Payment r = b.this.r();
            if (r != null) {
                b.this.v(r);
            }
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\n\u00a2\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lcom/jd/lib/cashier/sdk/pay/aac/impl/d/a;", "invoke", "()Lcom/jd/lib/cashier/sdk/pay/aac/impl/d/a;", "<anonymous>"}, k = 3, mv = {1, 4, 0})
    /* loaded from: classes14.dex */
    static final class e extends Lambda implements Function0<com.jd.lib.cashier.sdk.pay.aac.impl.d.a> {
        public static final e INSTANCE = new e();

        e() {
            super(0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        @NotNull
        public final com.jd.lib.cashier.sdk.pay.aac.impl.d.a invoke() {
            return new com.jd.lib.cashier.sdk.pay.aac.impl.d.a();
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\n\u00a2\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lcom/jd/lib/cashier/sdk/pay/aac/impl/channel/s;", "invoke", "()Lcom/jd/lib/cashier/sdk/pay/aac/impl/channel/s;", "<anonymous>"}, k = 3, mv = {1, 4, 0})
    /* loaded from: classes14.dex */
    static final class f extends Lambda implements Function0<s> {
        public static final f INSTANCE = new f();

        f() {
            super(0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        @NotNull
        public final s invoke() {
            return new s();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n\u00a2\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lcom/jd/lib/cashier/sdk/h/h/h;", AdvanceSetting.NETWORK_TYPE, "", "invoke", "(Lcom/jd/lib/cashier/sdk/h/h/h;)V", "<anonymous>"}, k = 3, mv = {1, 4, 0})
    /* loaded from: classes14.dex */
    public static final class g extends Lambda implements Function1<com.jd.lib.cashier.sdk.h.h.h, Unit> {
        final /* synthetic */ FragmentActivity $activity;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        g(FragmentActivity fragmentActivity) {
            super(1);
            this.$activity = fragmentActivity;
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(com.jd.lib.cashier.sdk.h.h.h hVar) {
            invoke2(hVar);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(@NotNull com.jd.lib.cashier.sdk.h.h.h hVar) {
            CashierPayViewModel x;
            com.jd.lib.cashier.sdk.h.c.a b;
            CashierPayEntity cashierPayEntity;
            CashierPayViewModel x2;
            com.jd.lib.cashier.sdk.h.c.a b2;
            CashierPayEntity cashierPayEntity2;
            CashierPayViewModel x3;
            com.jd.lib.cashier.sdk.h.c.a b3;
            CashierPayEntity cashierPayEntity3;
            TopFloor topFloor;
            FragmentActivity fragmentActivity = this.$activity;
            String str = null;
            if (!(fragmentActivity instanceof CashierPayActivity)) {
                fragmentActivity = null;
            }
            CashierPayActivity cashierPayActivity = (CashierPayActivity) fragmentActivity;
            String str2 = (cashierPayActivity == null || (x3 = cashierPayActivity.x()) == null || (b3 = x3.b()) == null || (cashierPayEntity3 = b3.K) == null || (topFloor = cashierPayEntity3.topFloor) == null) ? null : topFloor.payprice;
            FragmentActivity fragmentActivity2 = this.$activity;
            if (!(fragmentActivity2 instanceof CashierPayActivity)) {
                fragmentActivity2 = null;
            }
            CashierPayActivity cashierPayActivity2 = (CashierPayActivity) fragmentActivity2;
            String str3 = (cashierPayActivity2 == null || (x2 = cashierPayActivity2.x()) == null || (b2 = x2.b()) == null || (cashierPayEntity2 = b2.K) == null) ? null : cashierPayEntity2.paySource;
            FragmentActivity fragmentActivity3 = this.$activity;
            if (!(fragmentActivity3 instanceof CashierPayActivity)) {
                fragmentActivity3 = null;
            }
            CashierPayActivity cashierPayActivity3 = (CashierPayActivity) fragmentActivity3;
            if (cashierPayActivity3 != null && (x = cashierPayActivity3.x()) != null && (b = x.b()) != null && (cashierPayEntity = b.K) != null) {
                str = cashierPayEntity.checkIsNewUser();
            }
            String str4 = str;
            com.jd.lib.cashier.sdk.h.e.a d = com.jd.lib.cashier.sdk.h.e.a.d();
            FragmentActivity fragmentActivity4 = this.$activity;
            String c2 = hVar.c();
            String b4 = hVar.b();
            boolean h2 = hVar.h();
            if (str2 == null) {
                str2 = "";
            }
            d.D(fragmentActivity4, c2, b4, h2, str2, hVar.d(), hVar.g(), str3, str4, hVar.a(), hVar.f());
        }
    }

    /* loaded from: classes14.dex */
    static final class h<T> implements Observer<com.jd.lib.cashier.sdk.pay.aac.livedata.a.s> {
        h() {
        }

        @Override // androidx.lifecycle.Observer
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public final void onChanged(com.jd.lib.cashier.sdk.pay.aac.livedata.a.s sVar) {
            b.l(b.this).setVisibility(sVar.a() ? 4 : 0);
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n\u00a2\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lcom/jd/lib/cashier/sdk/pay/bean/syncevent/ClickBaiTiaoPlanToFooterEvent;", "dataEvent", "", "invoke", "(Lcom/jd/lib/cashier/sdk/pay/bean/syncevent/ClickBaiTiaoPlanToFooterEvent;)V", "<anonymous>"}, k = 3, mv = {1, 4, 0})
    /* loaded from: classes14.dex */
    static final class i extends Lambda implements Function1<ClickBaiTiaoPlanToFooterEvent, Unit> {
        final /* synthetic */ FragmentActivity $activity;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        i(FragmentActivity fragmentActivity) {
            super(1);
            this.$activity = fragmentActivity;
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(ClickBaiTiaoPlanToFooterEvent clickBaiTiaoPlanToFooterEvent) {
            invoke2(clickBaiTiaoPlanToFooterEvent);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(@NotNull ClickBaiTiaoPlanToFooterEvent clickBaiTiaoPlanToFooterEvent) {
            b.this.z(this.$activity, b.this.p());
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n\u00a2\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lcom/jd/lib/cashier/sdk/pay/bean/syncevent/ClickCreditCardPlanToFooterEvent;", "dataEvent", "", "invoke", "(Lcom/jd/lib/cashier/sdk/pay/bean/syncevent/ClickCreditCardPlanToFooterEvent;)V", "<anonymous>"}, k = 3, mv = {1, 4, 0})
    /* loaded from: classes14.dex */
    static final class j extends Lambda implements Function1<ClickCreditCardPlanToFooterEvent, Unit> {
        final /* synthetic */ FragmentActivity $activity;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        j(FragmentActivity fragmentActivity) {
            super(1);
            this.$activity = fragmentActivity;
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(ClickCreditCardPlanToFooterEvent clickCreditCardPlanToFooterEvent) {
            invoke2(clickCreditCardPlanToFooterEvent);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(@NotNull ClickCreditCardPlanToFooterEvent clickCreditCardPlanToFooterEvent) {
            b.this.z(this.$activity, b.this.p());
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n\u00a2\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lcom/jd/lib/cashier/sdk/pay/bean/syncevent/ClickCommonCouponItemEvent;", "eventData", "", "invoke", "(Lcom/jd/lib/cashier/sdk/pay/bean/syncevent/ClickCommonCouponItemEvent;)V", "<anonymous>"}, k = 3, mv = {1, 4, 0})
    /* loaded from: classes14.dex */
    static final class k extends Lambda implements Function1<ClickCommonCouponItemEvent, Unit> {
        final /* synthetic */ FragmentActivity $activity;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        k(FragmentActivity fragmentActivity) {
            super(1);
            this.$activity = fragmentActivity;
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(ClickCommonCouponItemEvent clickCommonCouponItemEvent) {
            invoke2(clickCommonCouponItemEvent);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(@NotNull ClickCommonCouponItemEvent clickCommonCouponItemEvent) {
            r.b(b.p, "CLICK_COMMON_COUPON_DIALOG_ITEM");
            b.this.z(this.$activity, b.this.p());
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0002\u0010\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\n\u00a2\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"", "invoke", "()V", "<anonymous>"}, k = 3, mv = {1, 4, 0})
    /* loaded from: classes14.dex */
    static final class l extends Lambda implements Function0<Unit> {
        final /* synthetic */ FragmentActivity $activity;
        final /* synthetic */ Ref.ObjectRef $buttonTitle;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        l(FragmentActivity fragmentActivity, Ref.ObjectRef objectRef) {
            super(0);
            this.$activity = fragmentActivity;
            this.$buttonTitle = objectRef;
        }

        @Override // kotlin.jvm.functions.Function0
        public /* bridge */ /* synthetic */ Unit invoke() {
            invoke2();
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2() {
            b.this.z(this.$activity, (String) this.$buttonTitle.element);
        }
    }

    static {
        String simpleName = b.class.getSimpleName();
        Intrinsics.checkExpressionValueIsNotNull(simpleName, "CashierPayFooterImpl::class.java.simpleName");
        p = simpleName;
    }

    public b(@NotNull FragmentActivity fragmentActivity) {
        Lazy lazy;
        Lazy lazy2;
        Lazy lazy3;
        Lazy lazy4;
        this.o = fragmentActivity;
        lazy = LazyKt__LazyJVMKt.lazy(a.INSTANCE);
        this.f3647k = lazy;
        lazy2 = LazyKt__LazyJVMKt.lazy(f.INSTANCE);
        this.f3648l = lazy2;
        lazy3 = LazyKt__LazyJVMKt.lazy(e.INSTANCE);
        this.f3649m = lazy3;
        lazy4 = LazyKt__LazyJVMKt.lazy(new C0123b());
        this.f3650n = lazy4;
    }

    private final void B() {
        PaymentTriggerView paymentTriggerView = this.f3646j;
        if (paymentTriggerView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("paymentTriggerView");
        }
        j0.d(paymentTriggerView);
        PaymentTriggerView paymentTriggerView2 = this.f3646j;
        if (paymentTriggerView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("paymentTriggerView");
        }
        paymentTriggerView2.k("\u8bf7\u9009\u62e9\u4ed8\u6b3e\u65b9\u5f0f");
    }

    public static final /* synthetic */ LinearLayout l(b bVar) {
        LinearLayout linearLayout = bVar.f3644h;
        if (linearLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("footerContainer");
        }
        return linearLayout;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String p() {
        String str;
        String str2;
        String str3 = "";
        if (g0.a(this.o) && (this.o instanceof CashierPayActivity)) {
            Payment payment = this.f3643g;
            if (payment == null || (str = payment.buttonTitle) == null) {
                str = "";
            }
            if (TextUtils.isEmpty(payment != null ? payment.planButtonTitle : null)) {
                return str;
            }
            Payment payment2 = this.f3643g;
            if (payment2 != null && (str2 = payment2.planButtonTitle) != null) {
                str3 = str2;
            }
            return str3;
        }
        return "";
    }

    private final com.jd.lib.cashier.sdk.pay.handler.a q() {
        return (com.jd.lib.cashier.sdk.pay.handler.a) this.f3647k.getValue();
    }

    private final CyberMoneyPaymentProxy s() {
        return (CyberMoneyPaymentProxy) this.f3650n.getValue();
    }

    private final com.jd.lib.cashier.sdk.pay.aac.impl.d.a t() {
        return (com.jd.lib.cashier.sdk.pay.aac.impl.d.a) this.f3649m.getValue();
    }

    private final s u() {
        return (s) this.f3648l.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void v(Payment payment) {
        com.jd.lib.cashier.sdk.h.c.a b;
        com.jd.lib.cashier.sdk.h.c.a b2;
        List<DigitalMoneyBankCard> list;
        String str = p;
        if (o0.a(str)) {
            return;
        }
        FragmentActivity fragmentActivity = this.o;
        if (!(fragmentActivity instanceof CashierPayActivity)) {
            fragmentActivity = null;
        }
        CashierPayActivity cashierPayActivity = (CashierPayActivity) fragmentActivity;
        if (cashierPayActivity != null) {
            if ((!Intrinsics.areEqual("1", payment.extendPayment) || TextUtils.isEmpty(payment.extendPaymentUrl) || payment.canUsePaymentAcc) ? false : true) {
                cashierPayActivity.w().h(cashierPayActivity, payment.extendPaymentUrl);
                return;
            }
            String str2 = payment.code;
            if (str2 == null) {
                str2 = "";
            }
            int hashCode = str2.hashCode();
            if (hashCode != -1899735100) {
                if (hashCode == -155096089 && str2.equals("cyberMoney")) {
                    DigitalMoneyPayEntity digitalMoneyPayEntity = payment.virtualPayModel;
                    String str3 = digitalMoneyPayEntity != null ? digitalMoneyPayEntity.jumpApp : null;
                    if (!TextUtils.equals(str3, "ecnyApp") && !TextUtils.equals(str3, "jdAppSdk")) {
                        DigitalMoneyPayEntity digitalMoneyPayEntity2 = payment.virtualPayModel;
                        if (digitalMoneyPayEntity2 != null && (list = digitalMoneyPayEntity2.bankCardList) != null && list.isEmpty()) {
                            f0.a(cashierPayActivity, R.string.lib_cashier_sdk_dialog_digital_money_unavailable_hint);
                            return;
                        } else {
                            s().x(payment.virtualPayModel, new c());
                            return;
                        }
                    }
                    C(this.o);
                    return;
                }
            } else if (str2.equals("weiXinDFPay")) {
                y(cashierPayActivity);
                CashierPayViewModel x = cashierPayActivity.x();
                if (x != null && (b2 = x.b()) != null) {
                    b2.H = false;
                }
                CashierPayViewModel x2 = cashierPayActivity.x();
                if (x2 == null || (b = x2.b()) == null) {
                    return;
                }
                r.b(str, "appId = " + b.b);
                com.jd.lib.cashier.sdk.h.a.c.a w = cashierPayActivity.w();
                FragmentActivity fragmentActivity2 = this.o;
                CashierPayViewModel x3 = cashierPayActivity.x();
                w.g(fragmentActivity2, x3 != null ? x3.b() : null);
                return;
            }
            C(this.o);
        }
    }

    private final void x() {
        if (this.f3644h != null) {
            com.jd.lib.cashier.sdk.pay.aac.impl.d.a t = t();
            LinearLayout linearLayout = this.f3644h;
            if (linearLayout == null) {
                Intrinsics.throwUninitializedPropertyAccessException("footerContainer");
            }
            t.b((CashierNoticeView) linearLayout.findViewById(R.id.lib_cashier_notice_view));
        }
    }

    private final void y(FragmentActivity fragmentActivity) {
        com.jd.lib.cashier.sdk.h.h.c.b(fragmentActivity, new g(fragmentActivity));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void z(FragmentActivity fragmentActivity, String str) {
        String str2;
        if (g0.a(fragmentActivity) && (fragmentActivity instanceof CashierPayActivity)) {
            Payment payment = this.f3643g;
            if (payment == null || (str2 = payment.buttonSubtitle) == null) {
                str2 = "";
            }
            if (!(str == null || str.length() == 0)) {
                if (!TextUtils.isEmpty(str2)) {
                    PaymentTriggerView paymentTriggerView = this.f3646j;
                    if (paymentTriggerView == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("paymentTriggerView");
                    }
                    PaymentTriggerView.l(paymentTriggerView, new com.jd.lib.cashier.sdk.core.ui.widget.e(str, str2), null, 2, null);
                    return;
                }
                PaymentTriggerView paymentTriggerView2 = this.f3646j;
                if (paymentTriggerView2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("paymentTriggerView");
                }
                PaymentTriggerView.l(paymentTriggerView2, new com.jd.lib.cashier.sdk.core.ui.widget.e(str, ""), null, 2, null);
                return;
            }
            com.jd.lib.cashier.sdk.core.ui.widget.e eVar = new com.jd.lib.cashier.sdk.core.ui.widget.e("", "");
            FragmentActivity fragmentActivity2 = this.o;
            String string = (fragmentActivity2 != null ? fragmentActivity2.getResources() : null).getString(R.string.lib_cashier_sdk_credit_pay_confirm_to_pay_btn_text);
            Intrinsics.checkExpressionValueIsNotNull(string, "cashierActivity?.resourc\u2026_confirm_to_pay_btn_text)");
            eVar.d(string);
            if (!TextUtils.isEmpty(str2)) {
                eVar.e(str2);
            } else {
                eVar.e("");
            }
            PaymentTriggerView paymentTriggerView3 = this.f3646j;
            if (paymentTriggerView3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("paymentTriggerView");
            }
            paymentTriggerView3.h(eVar, "");
        }
    }

    public final void A(@Nullable Payment payment) {
        this.f3643g = payment;
    }

    public final void C(@NotNull FragmentActivity fragmentActivity) {
        PaymentTriggerView paymentTriggerView = this.f3646j;
        if (paymentTriggerView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("paymentTriggerView");
        }
        paymentTriggerView.m(com.jd.lib.cashier.sdk.core.ui.widget.c.IN_PAYMENT);
        y(fragmentActivity);
        ViewModel viewModel = ViewModelProviders.of(fragmentActivity).get(CashierPayViewModel.class);
        Intrinsics.checkExpressionValueIsNotNull(viewModel, "ViewModelProviders.of(ac\u2026PayViewModel::class.java)");
        if (((CashierPayViewModel) viewModel).b() != null) {
            if (!(fragmentActivity instanceof CashierPayActivity)) {
                fragmentActivity = null;
            }
            CashierPayActivity cashierPayActivity = (CashierPayActivity) fragmentActivity;
            if (cashierPayActivity != null) {
                m.m(cashierPayActivity);
                PaymentChoseHolder b = m.b(cashierPayActivity);
                if (b != null) {
                    q().a(cashierPayActivity, b);
                }
            }
        }
    }

    public final void D(@Nullable FragmentActivity fragmentActivity, @Nullable CashierPayAdapter cashierPayAdapter, boolean z) {
        t().a(fragmentActivity, cashierPayAdapter, z);
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [T, java.lang.String] */
    public final void E(@Nullable Payment payment, @NotNull FragmentActivity fragmentActivity) {
        if (payment == null) {
            B();
            return;
        }
        PaymentTriggerView paymentTriggerView = this.f3646j;
        if (paymentTriggerView == null) {
            return;
        }
        if (paymentTriggerView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("paymentTriggerView");
        }
        j0.d(paymentTriggerView);
        Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = p();
        if (TextUtils.equals("octopusPay", payment.code)) {
            u().c(fragmentActivity, new l(fragmentActivity, objectRef));
        } else {
            z(fragmentActivity, (String) objectRef.element);
        }
    }

    @Override // com.jd.lib.cashier.sdk.core.aac.d
    public void f(@NotNull FragmentActivity fragmentActivity) {
        if (g0.a(fragmentActivity)) {
            ViewModel viewModel = ViewModelProviders.of(fragmentActivity).get(CashierPayViewModel.class);
            Intrinsics.checkExpressionValueIsNotNull(viewModel, "ViewModelProviders.of(ac\u2026PayViewModel::class.java)");
            ((CashierPayViewModel) viewModel).E().observe(fragmentActivity, new h());
        }
        com.jd.lib.cashier.sdk.b.i.e.e("CLICK_BAITIAO_PLAN_TO_UPDATE_FOOTER", null, new i(fragmentActivity), 2, null);
        com.jd.lib.cashier.sdk.b.i.e.e("CLICK_CREDIT_CARD_PLAN_TO_FOOTER", null, new j(fragmentActivity), 2, null);
        com.jd.lib.cashier.sdk.b.i.e.e("CLICK_COMMON_COUPON_DIALOG_ITEM", null, new k(fragmentActivity), 2, null);
        u().f(fragmentActivity);
        s().f(fragmentActivity);
    }

    @Override // com.jd.lib.cashier.sdk.core.aac.c
    public void h(@Nullable Window window) {
        LinearLayout linearLayout;
        if (window == null || (linearLayout = (LinearLayout) window.findViewById(R.id.lib_cashier_payment_action_root)) == null) {
            return;
        }
        this.f3644h = linearLayout;
        if (linearLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("footerContainer");
        }
        View findViewById = linearLayout.findViewById(R.id.lib_cashier_payment_action_container);
        Intrinsics.checkExpressionValueIsNotNull(findViewById, "footerContainer.findView\u2026payment_action_container)");
        this.f3645i = (LinearLayout) findViewById;
        LinearLayout linearLayout2 = this.f3644h;
        if (linearLayout2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("footerContainer");
        }
        View findViewById2 = linearLayout2.findViewById(R.id.lib_cashier_payment_trigger_view);
        Intrinsics.checkExpressionValueIsNotNull(findViewById2, "footerContainer.findView\u2026ier_payment_trigger_view)");
        PaymentTriggerView paymentTriggerView = (PaymentTriggerView) findViewById2;
        this.f3646j = paymentTriggerView;
        if (paymentTriggerView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("paymentTriggerView");
        }
        paymentTriggerView.f(new d());
        onChangeSkin();
    }

    @Override // com.jd.lib.cashier.sdk.core.aac.e
    public void onChangeSkin() {
        try {
            LinearLayout linearLayout = this.f3645i;
            if (linearLayout != null) {
                if (linearLayout == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("paymentTriggerViewContainer");
                }
                linearLayout.setBackgroundColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_F8F8F8, JDDarkUtil.COLOR_1D1B1B));
                PaymentTriggerView paymentTriggerView = this.f3646j;
                if (paymentTriggerView == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("paymentTriggerView");
                }
                paymentTriggerView.onChangeSkin();
            }
        } catch (Exception e2) {
            r.d(p, e2.getMessage());
        }
    }

    @Override // com.jd.lib.cashier.sdk.d.d.a
    public void onDestroy() {
        s().onDestroy();
    }

    public final void onPause() {
        PaymentTriggerView paymentTriggerView = this.f3646j;
        if (paymentTriggerView != null) {
            if (paymentTriggerView == null) {
                Intrinsics.throwUninitializedPropertyAccessException("paymentTriggerView");
            }
            paymentTriggerView.onPause();
        }
    }

    public final void onResume() {
        PaymentTriggerView paymentTriggerView = this.f3646j;
        if (paymentTriggerView != null) {
            if (paymentTriggerView == null) {
                Intrinsics.throwUninitializedPropertyAccessException("paymentTriggerView");
            }
            paymentTriggerView.onResume();
        }
    }

    @Nullable
    public final Payment r() {
        return this.f3643g;
    }

    public final void w(@Nullable Window window) {
        h(window);
        x();
    }
}

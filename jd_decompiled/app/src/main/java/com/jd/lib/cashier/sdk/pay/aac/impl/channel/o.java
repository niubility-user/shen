package com.jd.lib.cashier.sdk.pay.aac.impl.channel;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import com.jd.lib.cashier.sdk.h.g.x;
import com.jd.lib.cashier.sdk.pay.aac.livedata.BankCouponLiveData;
import com.jd.lib.cashier.sdk.pay.aac.viewmodel.CashierPayViewModel;
import com.jd.lib.cashier.sdk.pay.adapter.CashierPayAdapter;
import com.jd.lib.cashier.sdk.pay.bean.BankCouponResponse;
import com.jd.lib.cashier.sdk.pay.bean.Payment;
import com.jd.lib.cashier.sdk.pay.bean.coupon.CommonCouponEntity;
import com.jd.lib.cashier.sdk.pay.bean.syncevent.ClickBankCouponItemEvent;
import com.jd.lib.cashier.sdk.pay.bean.syncevent.ClickCommonCouponItemEvent;
import com.jd.lib.cashier.sdk.pay.view.CashierPayActivity;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* loaded from: classes14.dex */
public final class o {
    private static final String d = "o";
    @NotNull

    /* renamed from: e  reason: collision with root package name */
    private static final CommonCouponEntity f3724e;

    /* renamed from: f  reason: collision with root package name */
    public static final a f3725f = new a(null);
    @Nullable
    private CashierPayAdapter a;
    private final HashMap<String, String> b = new HashMap<>();
    @NotNull

    /* renamed from: c  reason: collision with root package name */
    private final Function1<Payment, Unit> f3726c;

    /* loaded from: classes14.dex */
    public static final class a {
        private a() {
        }

        @NotNull
        public final CommonCouponEntity a() {
            return o.f3724e;
        }

        public /* synthetic */ a(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\u001f\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0002H\u0016\u00a2\u0006\u0004\b\u0007\u0010\b\u00a8\u0006\t"}, d2 = {"com/jd/lib/cashier/sdk/pay/aac/impl/channel/o$b", "Lcom/jd/lib/cashier/sdk/b/i/g;", "Lcom/jd/lib/cashier/sdk/pay/bean/syncevent/ClickBankCouponItemEvent;", "", "action", "eventData", "", "onEvent", "(Ljava/lang/String;Lcom/jd/lib/cashier/sdk/pay/bean/syncevent/ClickBankCouponItemEvent;)V", "cashier_release"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes14.dex */
    public static final class b implements com.jd.lib.cashier.sdk.b.i.g<ClickBankCouponItemEvent> {
        final /* synthetic */ FragmentActivity b;

        b(FragmentActivity fragmentActivity) {
            this.b = fragmentActivity;
        }

        @Override // com.jd.lib.cashier.sdk.b.i.g
        public void onEvent(@NotNull String action, @NotNull ClickBankCouponItemEvent eventData) {
            String str;
            CashierPayViewModel x;
            com.jd.lib.cashier.sdk.pay.dialog.e eVar;
            CashierPayViewModel x2;
            com.jd.lib.cashier.sdk.h.c.a b;
            com.jd.lib.cashier.sdk.core.utils.r.b(o.d, "EventBusManager register onEvent");
            Payment payment = eventData.getPayment();
            FragmentActivity fragmentActivity = this.b;
            if (!(fragmentActivity instanceof CashierPayActivity)) {
                fragmentActivity = null;
            }
            CashierPayActivity cashierPayActivity = (CashierPayActivity) fragmentActivity;
            Payment payment2 = (cashierPayActivity == null || (x2 = cashierPayActivity.x()) == null || (b = x2.b()) == null) ? null : b.O;
            if (payment != null) {
                com.jd.lib.cashier.sdk.h.e.a.d().C(this.b, payment.code, payment.uniqueChannelId);
            }
            if ((!Intrinsics.areEqual(payment, payment2)) != false) {
                o.this.g().invoke(payment);
            }
            if (payment == null || (eVar = payment.selectedCouponEntity) == null || (str = eVar.getPayMarketingUUID()) == null) {
                str = payment != null ? payment.defaultCouponId : null;
            }
            o oVar = o.this;
            if (str == null) {
                str = "";
            }
            oVar.i(payment, str);
            FragmentActivity fragmentActivity2 = this.b;
            CashierPayActivity cashierPayActivity2 = fragmentActivity2 instanceof CashierPayActivity ? fragmentActivity2 : null;
            if (cashierPayActivity2 == null || (x = cashierPayActivity2.x()) == null) {
                return;
            }
            x.f((CashierPayActivity) this.b, payment);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public static final class c<T> implements Observer<com.jd.lib.cashier.sdk.pay.aac.livedata.a.d> {

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ FragmentActivity f3728h;

        /* JADX INFO: Access modifiers changed from: package-private */
        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n\u00a2\u0006\u0004\b\u0003\u0010\u0004\u00a8\u0006\u0005"}, d2 = {"Lcom/jd/lib/cashier/sdk/pay/bean/coupon/CommonCouponEntity;", "currentCouponEntity", "", "invoke", "(Lcom/jd/lib/cashier/sdk/pay/bean/coupon/CommonCouponEntity;)V", "com/jd/lib/cashier/sdk/pay/aac/impl/channel/CommonCouponProxy$subscribe$2$1$1", "reactionToClick"}, k = 3, mv = {1, 4, 0})
        /* loaded from: classes14.dex */
        public static final class a extends Lambda implements Function1<CommonCouponEntity, Unit> {
            final /* synthetic */ Payment $currentPayment$inlined;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            a(Payment payment) {
                super(1);
                this.$currentPayment$inlined = payment;
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(CommonCouponEntity commonCouponEntity) {
                invoke2(commonCouponEntity);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull CommonCouponEntity commonCouponEntity) {
                a aVar = o.f3725f;
                String payMarketingUUID = Intrinsics.areEqual(commonCouponEntity, aVar.a()) ? "" : commonCouponEntity.getPayMarketingUUID();
                com.jd.lib.cashier.sdk.h.h.m.e(c.this.f3728h, payMarketingUUID, Intrinsics.areEqual(commonCouponEntity, aVar.a()) ? "" : commonCouponEntity.getPrizeId());
                o.this.i(this.$currentPayment$inlined, payMarketingUUID);
                com.jd.lib.cashier.sdk.b.i.e.f("CLICK_COMMON_COUPON_DIALOG_ITEM", new ClickCommonCouponItemEvent(this.$currentPayment$inlined, commonCouponEntity));
                o.this.k(commonCouponEntity, this.$currentPayment$inlined);
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0002\u0010\u0002\n\u0002\b\u0004\u0010\u0004\u001a\u00020\u0000H\n\u00a2\u0006\u0004\b\u0001\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"", "invoke", "()V", "com/jd/lib/cashier/sdk/pay/aac/impl/channel/CommonCouponProxy$subscribe$2$$special$$inlined$apply$lambda$1", "<anonymous>"}, k = 3, mv = {1, 4, 0})
        /* loaded from: classes14.dex */
        public static final class b extends Lambda implements Function0<Unit> {
            final /* synthetic */ List $availableUseCouponList$inlined;
            final /* synthetic */ Payment $currentPayment$inlined;
            final /* synthetic */ List $disableUseCouponList$inlined;
            final /* synthetic */ a $reactionToClick$1$inlined;
            final /* synthetic */ com.jd.lib.cashier.sdk.pay.dialog.b $this_apply;
            final /* synthetic */ c this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            b(com.jd.lib.cashier.sdk.pay.dialog.b bVar, a aVar, List list, List list2, c cVar, Payment payment) {
                super(0);
                this.$this_apply = bVar;
                this.$reactionToClick$1$inlined = aVar;
                this.$availableUseCouponList$inlined = list;
                this.$disableUseCouponList$inlined = list2;
                this.this$0 = cVar;
                this.$currentPayment$inlined = payment;
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2() {
                com.jd.lib.cashier.sdk.h.e.a d = com.jd.lib.cashier.sdk.h.e.a.d();
                FragmentActivity fragmentActivity = this.this$0.f3728h;
                Payment payment = this.$currentPayment$inlined;
                d.y(fragmentActivity, payment.code, payment.uniqueChannelId);
                this.$this_apply.e();
                com.jd.lib.cashier.sdk.pay.dialog.b bVar = this.$this_apply;
                a aVar = o.f3725f;
                bVar.n(aVar.a());
                this.$reactionToClick$1$inlined.invoke2(aVar.a());
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\u0010\u0006\u001a\u00020\u00022\b\u0010\u0001\u001a\u0004\u0018\u00010\u0000H\n\u00a2\u0006\u0004\b\u0003\u0010\u0004\u00a8\u0006\u0005"}, d2 = {"Lcom/jd/lib/cashier/sdk/pay/dialog/e;", "currentCouponItemEntity", "", "invoke", "(Lcom/jd/lib/cashier/sdk/pay/dialog/e;)V", "com/jd/lib/cashier/sdk/pay/aac/impl/channel/CommonCouponProxy$subscribe$2$$special$$inlined$apply$lambda$2", "<anonymous>"}, k = 3, mv = {1, 4, 0})
        /* renamed from: com.jd.lib.cashier.sdk.pay.aac.impl.channel.o$c$c  reason: collision with other inner class name */
        /* loaded from: classes14.dex */
        public static final class C0130c extends Lambda implements Function1<com.jd.lib.cashier.sdk.pay.dialog.e, Unit> {
            final /* synthetic */ List $availableUseCouponList$inlined;
            final /* synthetic */ Payment $currentPayment$inlined;
            final /* synthetic */ List $disableUseCouponList$inlined;
            final /* synthetic */ a $reactionToClick$1$inlined;
            final /* synthetic */ com.jd.lib.cashier.sdk.pay.dialog.b $this_apply;
            final /* synthetic */ c this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C0130c(com.jd.lib.cashier.sdk.pay.dialog.b bVar, a aVar, List list, List list2, c cVar, Payment payment) {
                super(1);
                this.$this_apply = bVar;
                this.$reactionToClick$1$inlined = aVar;
                this.$availableUseCouponList$inlined = list;
                this.$disableUseCouponList$inlined = list2;
                this.this$0 = cVar;
                this.$currentPayment$inlined = payment;
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(com.jd.lib.cashier.sdk.pay.dialog.e eVar) {
                invoke2(eVar);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(@Nullable com.jd.lib.cashier.sdk.pay.dialog.e eVar) {
                if (!(eVar instanceof CommonCouponEntity)) {
                    eVar = null;
                }
                CommonCouponEntity commonCouponEntity = (CommonCouponEntity) eVar;
                if (commonCouponEntity != null) {
                    this.$this_apply.n(commonCouponEntity);
                    this.$reactionToClick$1$inlined.invoke2(commonCouponEntity);
                }
            }
        }

        c(FragmentActivity fragmentActivity) {
            this.f3728h = fragmentActivity;
        }

        @Override // androidx.lifecycle.Observer
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public final void onChanged(com.jd.lib.cashier.sdk.pay.aac.livedata.a.d dVar) {
            CashierPayViewModel x;
            com.jd.lib.cashier.sdk.h.c.a b2;
            BankCouponResponse a2;
            String str = o.d;
            StringBuilder sb = new StringBuilder();
            sb.append("cantUseCouponList = ");
            sb.append((dVar == null || (a2 = dVar.a()) == null) ? null : a2.getCantUseCouponList());
            com.jd.lib.cashier.sdk.core.utils.r.b(str, sb.toString());
            Payment b3 = dVar.b();
            FragmentActivity fragmentActivity = this.f3728h;
            if (!(fragmentActivity instanceof CashierPayActivity)) {
                fragmentActivity = null;
            }
            CashierPayActivity cashierPayActivity = (CashierPayActivity) fragmentActivity;
            if (Intrinsics.areEqual(b3, (cashierPayActivity == null || (x = cashierPayActivity.x()) == null || (b2 = x.b()) == null) ? null : b2.O)) {
                BankCouponResponse a3 = dVar != null ? dVar.a() : null;
                List<CommonCouponEntity> canUseCouponList = a3 != null ? a3.getCanUseCouponList() : null;
                List<CommonCouponEntity> cantUseCouponList = a3 != null ? a3.getCantUseCouponList() : null;
                a aVar = new a(b3);
                com.jd.lib.cashier.sdk.pay.dialog.b bVar = new com.jd.lib.cashier.sdk.pay.dialog.b(this.f3728h);
                List<CommonCouponEntity> list = canUseCouponList;
                List<CommonCouponEntity> list2 = cantUseCouponList;
                bVar.o(new b(bVar, aVar, list, list2, this, b3));
                bVar.s(bVar.j(canUseCouponList, cantUseCouponList, o.this.f(b3)), new C0130c(bVar, aVar, list, list2, this, b3));
            }
        }
    }

    static {
        CommonCouponEntity commonCouponEntity = new CommonCouponEntity();
        commonCouponEntity.setPromotionDesc("\u6682\u4e0d\u4f7f\u7528");
        commonCouponEntity.setPayMarketingUUID("doNotUse");
        f3724e = commonCouponEntity;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public o(@NotNull Function1<? super Payment, Unit> function1) {
        this.f3726c = function1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String f(Payment payment) {
        String sb;
        if (payment == null || (sb = payment.code) == null) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("");
            sb2.append(payment != null ? payment.channelId : null);
            sb = sb2.toString();
        }
        if (sb == null) {
            StringBuilder sb3 = new StringBuilder();
            sb3.append("");
            sb3.append(payment != null ? payment.uniqueChannelId : null);
            sb = sb3.toString();
        }
        if (sb == null) {
            StringBuilder sb4 = new StringBuilder();
            sb4.append("");
            sb4.append(payment != null ? payment.channelName : null);
            sb = sb4.toString();
        }
        if (sb == null) {
            sb = "";
        }
        String str = this.b.get(sb);
        return str != null ? str : "";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void i(Payment payment, String str) {
        String sb;
        if (payment == null || (sb = payment.code) == null) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("");
            sb2.append(payment != null ? payment.channelId : null);
            sb = sb2.toString();
        }
        if (sb == null) {
            StringBuilder sb3 = new StringBuilder();
            sb3.append("");
            sb3.append(payment != null ? payment.uniqueChannelId : null);
            sb = sb3.toString();
        }
        if (sb == null) {
            StringBuilder sb4 = new StringBuilder();
            sb4.append("");
            sb4.append(payment != null ? payment.channelName : null);
            sb = sb4.toString();
        }
        this.b.put(sb != null ? sb : "", str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void k(com.jd.lib.cashier.sdk.pay.dialog.e eVar, Payment payment) {
        Collection data;
        ArrayList arrayList = new ArrayList();
        CashierPayAdapter cashierPayAdapter = this.a;
        if (cashierPayAdapter != null && (data = cashierPayAdapter.getData()) != null) {
            arrayList.addAll(data);
        }
        Iterator it = arrayList.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            com.jd.lib.cashier.sdk.d.a.e.a aVar = (com.jd.lib.cashier.sdk.d.a.e.a) it.next();
            if (aVar instanceof x) {
                x xVar = (x) aVar;
                if (Intrinsics.areEqual(xVar.a(), payment)) {
                    xVar.a().selectedCouponEntity = eVar;
                    break;
                }
            }
        }
        CashierPayAdapter cashierPayAdapter2 = this.a;
        if (cashierPayAdapter2 != null) {
            cashierPayAdapter2.y(arrayList);
        }
    }

    @NotNull
    public final Function1<Payment, Unit> g() {
        return this.f3726c;
    }

    public final void h(@Nullable CashierPayAdapter cashierPayAdapter) {
        this.a = cashierPayAdapter;
    }

    public final void j(@Nullable FragmentActivity fragmentActivity) {
        CashierPayViewModel x;
        BankCouponLiveData s;
        com.jd.lib.cashier.sdk.b.i.e.c("CLICK_BANK_COUPON_ON_PAYMENT_ITEM", new b(fragmentActivity));
        CashierPayActivity cashierPayActivity = (CashierPayActivity) (!(fragmentActivity instanceof CashierPayActivity) ? null : fragmentActivity);
        if (cashierPayActivity == null || (x = cashierPayActivity.x()) == null || (s = x.s()) == null) {
            return;
        }
        s.observe(fragmentActivity, new c(fragmentActivity));
    }
}

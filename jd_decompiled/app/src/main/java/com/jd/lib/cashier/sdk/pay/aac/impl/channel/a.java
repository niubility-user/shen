package com.jd.lib.cashier.sdk.pay.aac.impl.channel;

import androidx.fragment.app.FragmentActivity;
import com.jd.lib.cashier.sdk.h.g.x;
import com.jd.lib.cashier.sdk.pay.aac.viewmodel.CashierPayViewModel;
import com.jd.lib.cashier.sdk.pay.adapter.CashierPayAdapter;
import com.jd.lib.cashier.sdk.pay.adapter.WholePaymentAdapter;
import com.jd.lib.cashier.sdk.pay.bean.CashierPayEntity;
import com.jd.lib.cashier.sdk.pay.bean.GradualPayInfo;
import com.jd.lib.cashier.sdk.pay.bean.Payment;
import com.jd.lib.cashier.sdk.pay.bean.TopFloor;
import com.jd.lib.cashier.sdk.pay.bean.syncevent.ClickOtherJDPayItemEvent;
import com.jd.lib.cashier.sdk.pay.bean.syncevent.SendWholePaymentEvent;
import com.jd.lib.cashier.sdk.pay.view.CashierPayActivity;
import com.meizu.cloud.pushsdk.notification.model.AdvanceSetting;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* loaded from: classes14.dex */
public abstract class a implements com.jd.lib.cashier.sdk.core.aac.d, com.jd.lib.cashier.sdk.d.d.a {

    /* renamed from: l  reason: collision with root package name */
    private static final String f3680l = "a";

    /* renamed from: m  reason: collision with root package name */
    public static final C0125a f3681m = new C0125a(null);
    @Nullable

    /* renamed from: g  reason: collision with root package name */
    private CashierPayAdapter f3682g;
    @Nullable

    /* renamed from: h  reason: collision with root package name */
    private List<? extends com.jd.lib.cashier.sdk.d.a.e.a> f3683h;
    @Nullable

    /* renamed from: i  reason: collision with root package name */
    private Payment f3684i;

    /* renamed from: j  reason: collision with root package name */
    private int f3685j = 1;

    /* renamed from: k  reason: collision with root package name */
    private final Function2<List<? extends com.jd.lib.cashier.sdk.d.a.e.a>, Payment, Unit> f3686k;

    /* renamed from: com.jd.lib.cashier.sdk.pay.aac.impl.channel.a$a  reason: collision with other inner class name */
    /* loaded from: classes14.dex */
    public static final class C0125a {
        private C0125a() {
        }

        public final String a() {
            return a.f3680l;
        }

        public /* synthetic */ C0125a(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\u001f\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0002H\u0016\u00a2\u0006\u0004\b\u0007\u0010\b\u00a8\u0006\t"}, d2 = {"com/jd/lib/cashier/sdk/pay/aac/impl/channel/a$b", "Lcom/jd/lib/cashier/sdk/b/i/g;", "Lcom/jd/lib/cashier/sdk/pay/bean/syncevent/ClickOtherJDPayItemEvent;", "", "action", "eventData", "", "onEvent", "(Ljava/lang/String;Lcom/jd/lib/cashier/sdk/pay/bean/syncevent/ClickOtherJDPayItemEvent;)V", "cashier_release"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes14.dex */
    public static final class b implements com.jd.lib.cashier.sdk.b.i.g<ClickOtherJDPayItemEvent> {
        final /* synthetic */ FragmentActivity b;

        /* JADX INFO: Access modifiers changed from: package-private */
        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\u0010\b\u001a\u00020\u00042\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0002H\n\u00a2\u0006\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/jd/lib/cashier/sdk/h/g/x;", "currentChannelTemplate", "Lcom/jd/lib/cashier/sdk/pay/adapter/WholePaymentAdapter;", "wholePaymentAdapter", "", "invoke", "(Lcom/jd/lib/cashier/sdk/h/g/x;Lcom/jd/lib/cashier/sdk/pay/adapter/WholePaymentAdapter;)V", "com/jd/lib/cashier/sdk/pay/aac/impl/channel/AbsWholeJDPaymentProxy$subscribe$1$onEvent$2$2", "<anonymous>"}, k = 3, mv = {1, 4, 0})
        /* renamed from: com.jd.lib.cashier.sdk.pay.aac.impl.channel.a$b$a  reason: collision with other inner class name */
        /* loaded from: classes14.dex */
        public static final class C0126a extends Lambda implements Function2<x, WholePaymentAdapter, Unit> {
            final /* synthetic */ List $jdWholeTemplateList;
            final /* synthetic */ b this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C0126a(List list, b bVar) {
                super(2);
                this.$jdWholeTemplateList = list;
                this.this$0 = bVar;
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(x xVar, WholePaymentAdapter wholePaymentAdapter) {
                invoke2(xVar, wholePaymentAdapter);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull x xVar, @NotNull WholePaymentAdapter wholePaymentAdapter) {
                CashierPayViewModel x;
                com.jd.lib.cashier.sdk.h.c.a b;
                CashierPayEntity cashierPayEntity;
                TopFloor topFloor;
                CashierPayViewModel x2;
                com.jd.lib.cashier.sdk.h.c.a b2;
                CashierPayEntity cashierPayEntity2;
                Payment a = xVar.a();
                for (x xVar2 : this.$jdWholeTemplateList) {
                    xVar2.a().selected = Intrinsics.areEqual(xVar2.a(), a);
                }
                wholePaymentAdapter.notifyDataSetChanged();
                if (Intrinsics.areEqual("GRADUALLY_PAY", a.code)) {
                    FragmentActivity fragmentActivity = this.this$0.b;
                    String str = null;
                    if (!(fragmentActivity instanceof CashierPayActivity)) {
                        fragmentActivity = null;
                    }
                    CashierPayActivity cashierPayActivity = (CashierPayActivity) fragmentActivity;
                    GradualPayInfo gradualPayInfo = (cashierPayActivity == null || (x2 = cashierPayActivity.x()) == null || (b2 = x2.b()) == null || (cashierPayEntity2 = b2.K) == null) ? null : cashierPayEntity2.graduallyPayInfo;
                    FragmentActivity fragmentActivity2 = this.this$0.b;
                    if (!(fragmentActivity2 instanceof CashierPayActivity)) {
                        fragmentActivity2 = null;
                    }
                    CashierPayActivity cashierPayActivity2 = (CashierPayActivity) fragmentActivity2;
                    if (cashierPayActivity2 != null && (x = cashierPayActivity2.x()) != null && (b = x.b()) != null && (cashierPayEntity = b.K) != null && (topFloor = cashierPayEntity.topFloor) != null) {
                        str = topFloor.payprice;
                    }
                    com.jd.lib.cashier.sdk.h.e.a d = com.jd.lib.cashier.sdk.h.e.a.d();
                    FragmentActivity fragmentActivity3 = this.this$0.b;
                    if (str == null) {
                        str = "";
                    }
                    d.a0(fragmentActivity3, str);
                    com.jd.lib.cashier.sdk.pay.dialog.g.f(this.this$0.b, gradualPayInfo);
                    return;
                }
                try {
                    ArrayList<com.jd.lib.cashier.sdk.d.a.e.a> h2 = a.this.h(a);
                    b bVar = this.this$0;
                    a.this.p(bVar.b, h2);
                    a.this.f3686k.invoke(h2, a);
                } catch (Exception e2) {
                    com.jd.lib.cashier.sdk.core.utils.r.b(a.f3681m.a(), e2.toString());
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n\u00a2\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lcom/jd/lib/cashier/sdk/h/h/h;", AdvanceSetting.NETWORK_TYPE, "", "invoke", "(Lcom/jd/lib/cashier/sdk/h/h/h;)V", "<anonymous>"}, k = 3, mv = {1, 4, 0})
        /* renamed from: com.jd.lib.cashier.sdk.pay.aac.impl.channel.a$b$b  reason: collision with other inner class name */
        /* loaded from: classes14.dex */
        public static final class C0127b extends Lambda implements Function1<com.jd.lib.cashier.sdk.h.h.h, Unit> {
            C0127b() {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(com.jd.lib.cashier.sdk.h.h.h hVar) {
                invoke2(hVar);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull com.jd.lib.cashier.sdk.h.h.h hVar) {
                com.jd.lib.cashier.sdk.h.e.a.d().H(b.this.b);
            }
        }

        b(FragmentActivity fragmentActivity) {
            this.b = fragmentActivity;
        }

        @Override // com.jd.lib.cashier.sdk.b.i.g
        public void onEvent(@NotNull String action, @NotNull ClickOtherJDPayItemEvent eventData) {
            Payment a;
            CashierPayViewModel x;
            com.jd.lib.cashier.sdk.h.c.a b;
            CashierPayEntity cashierPayEntity;
            com.jd.lib.cashier.sdk.h.h.c.b(this.b, new C0127b());
            List<com.jd.lib.cashier.sdk.d.a.e.a> n2 = a.this.n();
            if (n2 != null) {
                ArrayList<x> arrayList = new ArrayList();
                for (Object obj : n2) {
                    if (obj instanceof x) {
                        arrayList.add(obj);
                    }
                }
                com.jd.lib.cashier.sdk.core.utils.r.b(a.f3681m.a(), "currentSelectedPayment = " + a.this.m());
                for (x xVar : arrayList) {
                    if (Intrinsics.areEqual(xVar.a(), a.this.m())) {
                        Payment m2 = a.this.m();
                        if (m2 != null) {
                            m2.selected = true;
                        }
                        Payment m3 = a.this.m();
                        if (m3 == null) {
                            m3 = new Payment();
                        }
                        xVar.b(m3);
                    } else {
                        xVar.a().selected = false;
                    }
                }
                if ((!arrayList.isEmpty()) != false && (a = ((x) arrayList.get(arrayList.size() - 1)).a()) != null) {
                    FragmentActivity fragmentActivity = this.b;
                    String str = null;
                    if (!(fragmentActivity instanceof CashierPayActivity)) {
                        fragmentActivity = null;
                    }
                    CashierPayActivity cashierPayActivity = (CashierPayActivity) fragmentActivity;
                    if (cashierPayActivity != null && (x = cashierPayActivity.x()) != null && (b = x.b()) != null && (cashierPayEntity = b.K) != null) {
                        str = cashierPayEntity.jdServiceProviderTip;
                    }
                    a.jdServiceProviderTip = str;
                }
                com.jd.lib.cashier.sdk.pay.dialog.j.a.b(this.b, arrayList, new C0126a(arrayList, this));
            }
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\u001f\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0002H\u0016\u00a2\u0006\u0004\b\u0007\u0010\b\u00a8\u0006\t"}, d2 = {"com/jd/lib/cashier/sdk/pay/aac/impl/channel/a$c", "Lcom/jd/lib/cashier/sdk/b/i/g;", "Lcom/jd/lib/cashier/sdk/pay/bean/syncevent/SendWholePaymentEvent;", "", "action", "eventData", "", "onEvent", "(Ljava/lang/String;Lcom/jd/lib/cashier/sdk/pay/bean/syncevent/SendWholePaymentEvent;)V", "cashier_release"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes14.dex */
    public static final class c implements com.jd.lib.cashier.sdk.b.i.g<SendWholePaymentEvent> {
        final /* synthetic */ FragmentActivity b;

        c(FragmentActivity fragmentActivity) {
            this.b = fragmentActivity;
        }

        @Override // com.jd.lib.cashier.sdk.b.i.g
        public void onEvent(@NotNull String action, @NotNull SendWholePaymentEvent eventData) {
            Payment payment = eventData.getPayment();
            try {
                ArrayList<com.jd.lib.cashier.sdk.d.a.e.a> h2 = a.this.h(payment);
                a.this.p(this.b, h2);
                a.this.f3686k.invoke(h2, payment);
            } catch (Exception e2) {
                com.jd.lib.cashier.sdk.core.utils.r.b(a.f3681m.a(), e2.toString());
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public a(@NotNull Function2<? super List<? extends com.jd.lib.cashier.sdk.d.a.e.a>, ? super Payment, Unit> function2) {
        this.f3686k = function2;
    }

    @Override // com.jd.lib.cashier.sdk.core.aac.d
    public void f(@NotNull FragmentActivity fragmentActivity) {
        com.jd.lib.cashier.sdk.b.i.e.c("CLICK_OTHER_JD_PAYMENT_CHANNEL_ITEM", new b(fragmentActivity));
        com.jd.lib.cashier.sdk.b.i.e.c("SEND_UPDATE_WHOLE_PAYMENT", new c(fragmentActivity));
    }

    @NotNull
    public abstract ArrayList<com.jd.lib.cashier.sdk.d.a.e.a> h(@NotNull Payment payment);

    @Nullable
    public final CashierPayAdapter l() {
        return this.f3682g;
    }

    @Nullable
    public final Payment m() {
        return this.f3684i;
    }

    @Nullable
    public final List<com.jd.lib.cashier.sdk.d.a.e.a> n() {
        return this.f3683h;
    }

    public final int o() {
        return this.f3685j;
    }

    public abstract void p(@NotNull FragmentActivity fragmentActivity, @NotNull List<? extends com.jd.lib.cashier.sdk.d.a.e.a> list);

    public abstract void q(@NotNull FragmentActivity fragmentActivity);

    public final void r(@Nullable CashierPayAdapter cashierPayAdapter) {
        this.f3682g = cashierPayAdapter;
    }

    public final void s(@Nullable Payment payment) {
        this.f3684i = payment;
    }

    public final void t(@Nullable List<? extends com.jd.lib.cashier.sdk.d.a.e.a> list) {
        this.f3683h = list;
    }

    public final void u(int i2) {
        this.f3685j = i2;
    }

    public final void v() {
        com.jd.lib.cashier.sdk.b.i.e.h("CLICK_OTHER_JD_PAYMENT_CHANNEL_ITEM");
        com.jd.lib.cashier.sdk.b.i.e.h("SEND_UPDATE_WHOLE_PAYMENT");
    }
}

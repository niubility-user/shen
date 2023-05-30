package com.jd.lib.cashier.sdk.pay.aac.impl.channel;

import android.view.ViewGroup;
import android.view.Window;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;
import com.jd.lib.cashier.sdk.R;
import com.jd.lib.cashier.sdk.core.utils.JDDarkUtil;
import com.jd.lib.cashier.sdk.core.utils.g0;
import com.jd.lib.cashier.sdk.h.g.x;
import com.jd.lib.cashier.sdk.pay.aac.impl.topper.CashierPayHeaderImpl;
import com.jd.lib.cashier.sdk.pay.aac.viewmodel.CashierPayViewModel;
import com.jd.lib.cashier.sdk.pay.adapter.CashierPayAdapter;
import com.jd.lib.cashier.sdk.pay.bean.Payment;
import com.jd.lib.cashier.sdk.pay.bean.syncevent.ClickPayChannelItemEvent;
import com.jd.lib.cashier.sdk.pay.bean.syncevent.UpdatePaymentListEvent;
import com.jd.lib.cashier.sdk.pay.recyclerview.CustomDrawOrderRecyclerView;
import com.jd.lib.cashier.sdk.pay.recyclerview.WrapContentGridLayoutManager;
import com.jd.lib.cashier.sdk.pay.view.CashierPayActivity;
import com.jingdong.common.cashiernative.CashierPayChannelCode;
import com.jingdong.common.unification.title.theme.ThemeTitleDataController;
import com.meizu.cloud.pushsdk.notification.model.AdvanceSetting;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* loaded from: classes14.dex */
public final class m implements com.jd.lib.cashier.sdk.pay.aac.impl.e.b {
    private static final String z = "m";

    /* renamed from: g  reason: collision with root package name */
    private ViewGroup f3712g;

    /* renamed from: h  reason: collision with root package name */
    private CashierPayAdapter f3713h;

    /* renamed from: i  reason: collision with root package name */
    private CustomDrawOrderRecyclerView f3714i;

    /* renamed from: j  reason: collision with root package name */
    private CashierPayHeaderImpl f3715j;

    /* renamed from: k  reason: collision with root package name */
    private com.jd.lib.cashier.sdk.pay.aac.impl.channel.a f3716k;

    /* renamed from: l  reason: collision with root package name */
    private final Lazy f3717l;

    /* renamed from: m  reason: collision with root package name */
    private final Lazy f3718m;

    /* renamed from: n  reason: collision with root package name */
    private final Lazy f3719n;
    private final Lazy o;
    private final Lazy p;
    private final Lazy q;
    private final Lazy r;
    private final Lazy s;
    private final Lazy t;
    private final Lazy u;
    private final HashMap<String, com.jd.lib.cashier.sdk.pay.aac.impl.channel.b> v;
    private final Lazy w;
    private CashierPayActivity x;
    private final com.jd.lib.cashier.sdk.h.d.a y;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\n\u00a2\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lcom/jd/lib/cashier/sdk/pay/aac/impl/channel/c;", "invoke", "()Lcom/jd/lib/cashier/sdk/pay/aac/impl/channel/c;", "<anonymous>"}, k = 3, mv = {1, 4, 0})
    /* loaded from: classes14.dex */
    static final class a extends Lambda implements Function0<com.jd.lib.cashier.sdk.pay.aac.impl.channel.c> {
        public static final a INSTANCE = new a();

        a() {
            super(0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        @NotNull
        public final com.jd.lib.cashier.sdk.pay.aac.impl.channel.c invoke() {
            return new com.jd.lib.cashier.sdk.pay.aac.impl.channel.c();
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\n\u00a2\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lcom/jd/lib/cashier/sdk/pay/aac/impl/channel/f;", "invoke", "()Lcom/jd/lib/cashier/sdk/pay/aac/impl/channel/f;", "<anonymous>"}, k = 3, mv = {1, 4, 0})
    /* loaded from: classes14.dex */
    static final class b extends Lambda implements Function0<com.jd.lib.cashier.sdk.pay.aac.impl.channel.f> {
        public static final b INSTANCE = new b();

        b() {
            super(0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        @NotNull
        public final com.jd.lib.cashier.sdk.pay.aac.impl.channel.f invoke() {
            return new com.jd.lib.cashier.sdk.pay.aac.impl.channel.f();
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\n\u00a2\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lcom/jd/lib/cashier/sdk/pay/aac/impl/channel/g;", "invoke", "()Lcom/jd/lib/cashier/sdk/pay/aac/impl/channel/g;", "<anonymous>"}, k = 3, mv = {1, 4, 0})
    /* loaded from: classes14.dex */
    static final class c extends Lambda implements Function0<com.jd.lib.cashier.sdk.pay.aac.impl.channel.g> {
        public static final c INSTANCE = new c();

        c() {
            super(0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        @NotNull
        public final com.jd.lib.cashier.sdk.pay.aac.impl.channel.g invoke() {
            return new com.jd.lib.cashier.sdk.pay.aac.impl.channel.g();
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\n\u00a2\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lcom/jd/lib/cashier/sdk/pay/aac/impl/channel/i;", "invoke", "()Lcom/jd/lib/cashier/sdk/pay/aac/impl/channel/i;", "<anonymous>"}, k = 3, mv = {1, 4, 0})
    /* loaded from: classes14.dex */
    static final class d extends Lambda implements Function0<com.jd.lib.cashier.sdk.pay.aac.impl.channel.i> {
        public static final d INSTANCE = new d();

        d() {
            super(0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        @NotNull
        public final com.jd.lib.cashier.sdk.pay.aac.impl.channel.i invoke() {
            return new com.jd.lib.cashier.sdk.pay.aac.impl.channel.i();
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\n\u00a2\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lcom/jd/lib/cashier/sdk/pay/aac/impl/channel/l;", "invoke", "()Lcom/jd/lib/cashier/sdk/pay/aac/impl/channel/l;", "<anonymous>"}, k = 3, mv = {1, 4, 0})
    /* loaded from: classes14.dex */
    static final class e extends Lambda implements Function0<com.jd.lib.cashier.sdk.pay.aac.impl.channel.l> {
        public static final e INSTANCE = new e();

        e() {
            super(0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        @NotNull
        public final com.jd.lib.cashier.sdk.pay.aac.impl.channel.l invoke() {
            return new com.jd.lib.cashier.sdk.pay.aac.impl.channel.l();
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\n\u00a2\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lcom/jd/lib/cashier/sdk/pay/aac/impl/b;", "invoke", "()Lcom/jd/lib/cashier/sdk/pay/aac/impl/b;", "<anonymous>"}, k = 3, mv = {1, 4, 0})
    /* loaded from: classes14.dex */
    static final class f extends Lambda implements Function0<com.jd.lib.cashier.sdk.pay.aac.impl.b> {
        f() {
            super(0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        @NotNull
        public final com.jd.lib.cashier.sdk.pay.aac.impl.b invoke() {
            return new com.jd.lib.cashier.sdk.pay.aac.impl.b(m.this.x);
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\n\u00a2\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lcom/jd/lib/cashier/sdk/pay/aac/impl/e/f;", "invoke", "()Lcom/jd/lib/cashier/sdk/pay/aac/impl/e/f;", "<anonymous>"}, k = 3, mv = {1, 4, 0})
    /* loaded from: classes14.dex */
    static final class g extends Lambda implements Function0<com.jd.lib.cashier.sdk.pay.aac.impl.e.f> {
        g() {
            super(0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        @NotNull
        public final com.jd.lib.cashier.sdk.pay.aac.impl.e.f invoke() {
            CashierPayViewModel x;
            com.jd.lib.cashier.sdk.h.c.a b;
            CashierPayActivity cashierPayActivity = m.this.x;
            if (!(cashierPayActivity instanceof CashierPayActivity)) {
                cashierPayActivity = null;
            }
            if (cashierPayActivity != null && (x = cashierPayActivity.x()) != null && (b = x.b()) != null && !b.X) {
                return new com.jd.lib.cashier.sdk.pay.aac.impl.f.d(m.this.x);
            }
            return new com.jd.lib.cashier.sdk.pay.aac.impl.f.c(m.this.x);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\n\u00a2\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lcom/jd/lib/cashier/sdk/pay/aac/impl/channel/o;", "invoke", "()Lcom/jd/lib/cashier/sdk/pay/aac/impl/channel/o;", "<anonymous>"}, k = 3, mv = {1, 4, 0})
    /* loaded from: classes14.dex */
    public static final class h extends Lambda implements Function0<com.jd.lib.cashier.sdk.pay.aac.impl.channel.o> {

        /* JADX INFO: Access modifiers changed from: package-private */
        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\b\u0010\u0001\u001a\u0004\u0018\u00010\u0000H\n\u00a2\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lcom/jd/lib/cashier/sdk/pay/bean/Payment;", AdvanceSetting.NETWORK_TYPE, "", "invoke", "(Lcom/jd/lib/cashier/sdk/pay/bean/Payment;)V", "<anonymous>"}, k = 3, mv = {1, 4, 0})
        /* loaded from: classes14.dex */
        public static final class a extends Lambda implements Function1<Payment, Unit> {
            a() {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Payment payment) {
                invoke2(payment);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(@Nullable Payment payment) {
                m mVar = m.this;
                mVar.f0(mVar.x, payment);
                m.this.a0(payment);
            }
        }

        h() {
            super(0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        @NotNull
        public final com.jd.lib.cashier.sdk.pay.aac.impl.channel.o invoke() {
            return new com.jd.lib.cashier.sdk.pay.aac.impl.channel.o(new a());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\u0010\b\u001a\u00020\u00052\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00002\u0006\u0010\u0004\u001a\u00020\u0003H\n\u00a2\u0006\u0004\b\u0006\u0010\u0007"}, d2 = {"", "Lcom/jd/lib/cashier/sdk/d/a/e/a;", "templateEntityList", "Lcom/jd/lib/cashier/sdk/pay/bean/Payment;", "payment", "", "invoke", "(Ljava/util/List;Lcom/jd/lib/cashier/sdk/pay/bean/Payment;)V", "<anonymous>"}, k = 3, mv = {1, 4, 0})
    /* loaded from: classes14.dex */
    public static final class i extends Lambda implements Function2<List<? extends com.jd.lib.cashier.sdk.d.a.e.a>, Payment, Unit> {
        i() {
            super(2);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(List<? extends com.jd.lib.cashier.sdk.d.a.e.a> list, Payment payment) {
            invoke2(list, payment);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(@NotNull List<? extends com.jd.lib.cashier.sdk.d.a.e.a> list, @NotNull Payment payment) {
            m.this.Z(list, payment);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\u0010\b\u001a\u00020\u00052\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00002\u0006\u0010\u0004\u001a\u00020\u0003H\n\u00a2\u0006\u0004\b\u0006\u0010\u0007"}, d2 = {"", "Lcom/jd/lib/cashier/sdk/d/a/e/a;", "templateEntityList", "Lcom/jd/lib/cashier/sdk/pay/bean/Payment;", "payment", "", "invoke", "(Ljava/util/List;Lcom/jd/lib/cashier/sdk/pay/bean/Payment;)V", "<anonymous>"}, k = 3, mv = {1, 4, 0})
    /* loaded from: classes14.dex */
    public static final class j extends Lambda implements Function2<List<? extends com.jd.lib.cashier.sdk.d.a.e.a>, Payment, Unit> {
        j() {
            super(2);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(List<? extends com.jd.lib.cashier.sdk.d.a.e.a> list, Payment payment) {
            invoke2(list, payment);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(@NotNull List<? extends com.jd.lib.cashier.sdk.d.a.e.a> list, @NotNull Payment payment) {
            m.this.Z(list, payment);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\u0010\b\u001a\u00020\u00052\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00002\u0006\u0010\u0004\u001a\u00020\u0003H\n\u00a2\u0006\u0004\b\u0006\u0010\u0007"}, d2 = {"", "Lcom/jd/lib/cashier/sdk/d/a/e/a;", "templateEntityList", "Lcom/jd/lib/cashier/sdk/pay/bean/Payment;", "payment", "", "invoke", "(Ljava/util/List;Lcom/jd/lib/cashier/sdk/pay/bean/Payment;)V", "<anonymous>"}, k = 3, mv = {1, 4, 0})
    /* loaded from: classes14.dex */
    public static final class k extends Lambda implements Function2<List<? extends com.jd.lib.cashier.sdk.d.a.e.a>, Payment, Unit> {
        k() {
            super(2);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(List<? extends com.jd.lib.cashier.sdk.d.a.e.a> list, Payment payment) {
            invoke2(list, payment);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(@NotNull List<? extends com.jd.lib.cashier.sdk.d.a.e.a> list, @NotNull Payment payment) {
            m.this.Z(list, payment);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\u0010\b\u001a\u00020\u00052\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00002\u0006\u0010\u0004\u001a\u00020\u0003H\n\u00a2\u0006\u0004\b\u0006\u0010\u0007"}, d2 = {"", "Lcom/jd/lib/cashier/sdk/d/a/e/a;", "templateEntityList", "Lcom/jd/lib/cashier/sdk/pay/bean/Payment;", "payment", "", "invoke", "(Ljava/util/List;Lcom/jd/lib/cashier/sdk/pay/bean/Payment;)V", "<anonymous>"}, k = 3, mv = {1, 4, 0})
    /* loaded from: classes14.dex */
    public static final class l extends Lambda implements Function2<List<? extends com.jd.lib.cashier.sdk.d.a.e.a>, Payment, Unit> {
        l() {
            super(2);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(List<? extends com.jd.lib.cashier.sdk.d.a.e.a> list, Payment payment) {
            invoke2(list, payment);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(@NotNull List<? extends com.jd.lib.cashier.sdk.d.a.e.a> list, @NotNull Payment payment) {
            m.this.Z(list, payment);
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\n\u00a2\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lcom/jd/lib/cashier/sdk/pay/aac/impl/channel/p;", "invoke", "()Lcom/jd/lib/cashier/sdk/pay/aac/impl/channel/p;", "<anonymous>"}, k = 3, mv = {1, 4, 0})
    /* renamed from: com.jd.lib.cashier.sdk.pay.aac.impl.channel.m$m  reason: collision with other inner class name */
    /* loaded from: classes14.dex */
    static final class C0129m extends Lambda implements Function0<com.jd.lib.cashier.sdk.pay.aac.impl.channel.p> {
        C0129m() {
            super(0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        @NotNull
        public final com.jd.lib.cashier.sdk.pay.aac.impl.channel.p invoke() {
            return new com.jd.lib.cashier.sdk.pay.aac.impl.channel.p(m.this.x);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n\u00a2\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lcom/jd/lib/cashier/sdk/pay/bean/Payment;", AdvanceSetting.NETWORK_TYPE, "", "invoke", "(Lcom/jd/lib/cashier/sdk/pay/bean/Payment;)V", "<anonymous>"}, k = 3, mv = {1, 4, 0})
    /* loaded from: classes14.dex */
    public static final class n extends Lambda implements Function1<Payment, Unit> {
        n() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Payment payment) {
            invoke2(payment);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(@NotNull Payment payment) {
            m mVar = m.this;
            mVar.f0(mVar.x, payment);
            m.this.S(payment, false);
            m.this.h0(payment);
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\n\u00a2\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lcom/jd/lib/cashier/sdk/pay/aac/impl/channel/q;", "invoke", "()Lcom/jd/lib/cashier/sdk/pay/aac/impl/channel/q;", "<anonymous>"}, k = 3, mv = {1, 4, 0})
    /* loaded from: classes14.dex */
    static final class o extends Lambda implements Function0<com.jd.lib.cashier.sdk.pay.aac.impl.channel.q> {
        public static final o INSTANCE = new o();

        o() {
            super(0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        @NotNull
        public final com.jd.lib.cashier.sdk.pay.aac.impl.channel.q invoke() {
            return new com.jd.lib.cashier.sdk.pay.aac.impl.channel.q();
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\n\u00a2\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lcom/jd/lib/cashier/sdk/pay/aac/impl/channel/r;", "invoke", "()Lcom/jd/lib/cashier/sdk/pay/aac/impl/channel/r;", "<anonymous>"}, k = 3, mv = {1, 4, 0})
    /* loaded from: classes14.dex */
    static final class p extends Lambda implements Function0<com.jd.lib.cashier.sdk.pay.aac.impl.channel.r> {
        public static final p INSTANCE = new p();

        p() {
            super(0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        @NotNull
        public final com.jd.lib.cashier.sdk.pay.aac.impl.channel.r invoke() {
            return new com.jd.lib.cashier.sdk.pay.aac.impl.channel.r();
        }
    }

    /* loaded from: classes14.dex */
    static final class q<T> implements Observer<com.jd.lib.cashier.sdk.pay.aac.livedata.a.i> {

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ FragmentActivity f3721h;

        /* renamed from: i  reason: collision with root package name */
        final /* synthetic */ CashierPayViewModel f3722i;

        q(FragmentActivity fragmentActivity, CashierPayViewModel cashierPayViewModel) {
            this.f3721h = fragmentActivity;
            this.f3722i = cashierPayViewModel;
        }

        @Override // androidx.lifecycle.Observer
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public final void onChanged(@Nullable com.jd.lib.cashier.sdk.pay.aac.livedata.a.i iVar) {
            if (iVar != null) {
                m.this.Y();
                m.this.T(this.f3721h);
                m.this.y.d = iVar.c();
                m.this.y.b = iVar.b();
                m.this.y.f3522c = iVar.d();
                m.this.g0(iVar.a());
                m.this.X(iVar.a());
                m.this.L().d();
                m.this.d0(iVar.d(), m.this.y);
                m.this.U(iVar.d());
                m.this.e0(iVar.d());
                m.this.b0(iVar.a());
                this.f3722i.b().N = iVar.b();
                this.f3722i.b().M = iVar.d();
                com.jd.lib.cashier.sdk.pay.aac.impl.channel.a aVar = m.this.f3716k;
                if (aVar != null) {
                    aVar.t(iVar.b());
                }
                com.jd.lib.cashier.sdk.pay.aac.impl.channel.a aVar2 = m.this.f3716k;
                if (aVar2 != null) {
                    aVar2.q(this.f3721h);
                }
                m.this.V();
            }
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\u001f\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0002H\u0016\u00a2\u0006\u0004\b\u0007\u0010\b\u00a8\u0006\t"}, d2 = {"com/jd/lib/cashier/sdk/pay/aac/impl/channel/m$r", "Lcom/jd/lib/cashier/sdk/b/i/g;", "Lcom/jd/lib/cashier/sdk/pay/bean/syncevent/ClickPayChannelItemEvent;", "", "action", "eventData", "", "onEvent", "(Ljava/lang/String;Lcom/jd/lib/cashier/sdk/pay/bean/syncevent/ClickPayChannelItemEvent;)V", "cashier_release"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes14.dex */
    public static final class r implements com.jd.lib.cashier.sdk.b.i.g<ClickPayChannelItemEvent> {
        final /* synthetic */ FragmentActivity b;

        r(FragmentActivity fragmentActivity) {
            this.b = fragmentActivity;
        }

        @Override // com.jd.lib.cashier.sdk.b.i.g
        public void onEvent(@NotNull String action, @NotNull ClickPayChannelItemEvent eventData) {
            Payment payment = eventData.getPayment();
            if (payment != null) {
                payment.clickEvent = true;
            }
            m.this.f0(this.b, eventData.getPayment());
            m.this.X(eventData.getPayment());
            m.this.a0(eventData.getPayment());
        }
    }

    public m(@NotNull CashierPayActivity cashierPayActivity, @NotNull com.jd.lib.cashier.sdk.h.d.a aVar) {
        Lazy lazy;
        Lazy lazy2;
        Lazy lazy3;
        Lazy lazy4;
        Lazy lazy5;
        Lazy lazy6;
        Lazy lazy7;
        Lazy lazy8;
        Lazy lazy9;
        Lazy lazy10;
        Lazy lazy11;
        this.x = cashierPayActivity;
        this.y = aVar;
        lazy = LazyKt__LazyJVMKt.lazy(new g());
        this.f3717l = lazy;
        lazy2 = LazyKt__LazyJVMKt.lazy(new f());
        this.f3718m = lazy2;
        lazy3 = LazyKt__LazyJVMKt.lazy(new C0129m());
        this.f3719n = lazy3;
        lazy4 = LazyKt__LazyJVMKt.lazy(p.INSTANCE);
        this.o = lazy4;
        lazy5 = LazyKt__LazyJVMKt.lazy(o.INSTANCE);
        this.p = lazy5;
        lazy6 = LazyKt__LazyJVMKt.lazy(b.INSTANCE);
        this.q = lazy6;
        lazy7 = LazyKt__LazyJVMKt.lazy(e.INSTANCE);
        this.r = lazy7;
        lazy8 = LazyKt__LazyJVMKt.lazy(a.INSTANCE);
        this.s = lazy8;
        lazy9 = LazyKt__LazyJVMKt.lazy(d.INSTANCE);
        this.t = lazy9;
        lazy10 = LazyKt__LazyJVMKt.lazy(c.INSTANCE);
        this.u = lazy10;
        this.v = new HashMap<>();
        lazy11 = LazyKt__LazyJVMKt.lazy(new h());
        this.w = lazy11;
    }

    private final com.jd.lib.cashier.sdk.pay.aac.impl.channel.a D() {
        CashierPayActivity cashierPayActivity;
        CashierPayViewModel x;
        com.jd.lib.cashier.sdk.h.c.a b2;
        CashierPayViewModel x2;
        com.jd.lib.cashier.sdk.h.c.a b3;
        CashierPayViewModel x3;
        com.jd.lib.cashier.sdk.h.c.a b4;
        CashierPayActivity cashierPayActivity2 = this.x;
        if (!(cashierPayActivity2 instanceof CashierPayActivity)) {
            cashierPayActivity2 = null;
        }
        if (cashierPayActivity2 != null && (x2 = cashierPayActivity2.x()) != null && (b3 = x2.b()) != null && b3.E) {
            CashierPayActivity cashierPayActivity3 = this.x;
            cashierPayActivity = cashierPayActivity3 instanceof CashierPayActivity ? cashierPayActivity3 : null;
            if (cashierPayActivity != null && (x3 = cashierPayActivity.x()) != null && (b4 = x3.b()) != null && b4.I) {
                return new com.jd.lib.cashier.sdk.pay.aac.impl.channel.h(new i());
            }
            return new com.jd.lib.cashier.sdk.pay.aac.impl.channel.k(new j());
        }
        CashierPayActivity cashierPayActivity4 = this.x;
        cashierPayActivity = cashierPayActivity4 instanceof CashierPayActivity ? cashierPayActivity4 : null;
        if (cashierPayActivity != null && (x = cashierPayActivity.x()) != null && (b2 = x.b()) != null && b2.F) {
            return new com.jd.lib.cashier.sdk.pay.aac.impl.channel.e(new k());
        }
        return new t(new l());
    }

    private final com.jd.lib.cashier.sdk.pay.aac.impl.channel.b E(String str, String str2) {
        com.jd.lib.cashier.sdk.pay.aac.impl.channel.b bVar = this.v.get(str + str2);
        if (bVar == null) {
            com.jd.lib.cashier.sdk.pay.aac.impl.channel.b bVar2 = new com.jd.lib.cashier.sdk.pay.aac.impl.channel.b(str, str2, this.x, new n());
            bVar2.f(this.x);
            this.v.put(str + str2, bVar2);
            return bVar2;
        }
        return bVar;
    }

    private final com.jd.lib.cashier.sdk.pay.aac.impl.channel.c F() {
        return (com.jd.lib.cashier.sdk.pay.aac.impl.channel.c) this.s.getValue();
    }

    private final com.jd.lib.cashier.sdk.pay.aac.impl.channel.f G() {
        return (com.jd.lib.cashier.sdk.pay.aac.impl.channel.f) this.q.getValue();
    }

    private final com.jd.lib.cashier.sdk.pay.aac.impl.channel.g H() {
        return (com.jd.lib.cashier.sdk.pay.aac.impl.channel.g) this.u.getValue();
    }

    private final com.jd.lib.cashier.sdk.pay.aac.impl.channel.i I() {
        return (com.jd.lib.cashier.sdk.pay.aac.impl.channel.i) this.t.getValue();
    }

    private final com.jd.lib.cashier.sdk.pay.aac.impl.channel.l J() {
        return (com.jd.lib.cashier.sdk.pay.aac.impl.channel.l) this.r.getValue();
    }

    private final com.jd.lib.cashier.sdk.pay.aac.impl.b K() {
        return (com.jd.lib.cashier.sdk.pay.aac.impl.b) this.f3718m.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final com.jd.lib.cashier.sdk.pay.aac.impl.e.f L() {
        return (com.jd.lib.cashier.sdk.pay.aac.impl.e.f) this.f3717l.getValue();
    }

    private final com.jd.lib.cashier.sdk.pay.aac.impl.channel.o M() {
        return (com.jd.lib.cashier.sdk.pay.aac.impl.channel.o) this.w.getValue();
    }

    private final com.jd.lib.cashier.sdk.pay.aac.impl.channel.p N() {
        return (com.jd.lib.cashier.sdk.pay.aac.impl.channel.p) this.f3719n.getValue();
    }

    private final com.jd.lib.cashier.sdk.pay.aac.impl.channel.q O() {
        return (com.jd.lib.cashier.sdk.pay.aac.impl.channel.q) this.p.getValue();
    }

    private final com.jd.lib.cashier.sdk.pay.aac.impl.channel.r P() {
        return (com.jd.lib.cashier.sdk.pay.aac.impl.channel.r) this.o.getValue();
    }

    private final void Q(Payment payment) {
        if (payment != null) {
            if (com.jd.lib.cashier.sdk.h.h.g.a(payment.code)) {
                if ((!Intrinsics.areEqual("5", payment.status)) == true) {
                    String str = payment.code;
                    Intrinsics.checkExpressionValueIsNotNull(str, "currentSelectedPayment.code");
                    com.jd.lib.cashier.sdk.pay.aac.impl.channel.b.w(E(str, com.jd.lib.cashier.sdk.h.h.e.a.d(payment)), payment, false, 2, null);
                    payment.baitiaoPlanInfo = null;
                }
            } else if (com.jd.lib.cashier.sdk.h.h.g.d(payment.code)) {
                N().B(payment);
            }
        }
    }

    private final void R(Payment payment) {
        if (payment != null) {
            if (com.jd.lib.cashier.sdk.h.h.g.a(payment.code)) {
                String str = payment.code;
                Intrinsics.checkExpressionValueIsNotNull(str, "currentSelectedPayment.code");
                E(str, com.jd.lib.cashier.sdk.h.h.e.a.d(payment)).F(payment);
                payment.baitiaoPlanInfo = null;
            } else if (com.jd.lib.cashier.sdk.h.h.g.d(payment.code)) {
                N().C(payment);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void S(Payment payment, boolean z2) {
        CashierPayViewModel x;
        com.jd.lib.cashier.sdk.h.c.a b2;
        if (!z2 && payment != null) {
            payment.baitiaoPlanInfo = null;
        }
        CashierPayActivity cashierPayActivity = this.x;
        CashierPayActivity cashierPayActivity2 = cashierPayActivity instanceof CashierPayActivity ? cashierPayActivity : null;
        if (cashierPayActivity2 != null && (x = cashierPayActivity2.x()) != null && (b2 = x.b()) != null) {
            b2.O = payment;
        }
        K().A(payment);
        com.jd.lib.cashier.sdk.pay.aac.impl.channel.a aVar = this.f3716k;
        if (aVar != null) {
            aVar.s(payment);
        }
        com.jd.lib.cashier.sdk.h.h.m.a(this.x, payment);
        K().E(payment, this.x);
        K().D(this.x, this.f3713h, z2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void T(FragmentActivity fragmentActivity) {
        com.jd.lib.cashier.sdk.pay.aac.impl.channel.a D = D();
        this.f3716k = D;
        if (D != null) {
            D.v();
        }
        com.jd.lib.cashier.sdk.pay.aac.impl.channel.a aVar = this.f3716k;
        if (aVar != null) {
            aVar.f(fragmentActivity);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void U(List<? extends com.jd.lib.cashier.sdk.d.a.e.a> list) {
        CashierPayAdapter cashierPayAdapter;
        com.jd.lib.cashier.sdk.pay.aac.impl.channel.a aVar;
        if (this.f3713h == null) {
            CashierPayAdapter cashierPayAdapter2 = new CashierPayAdapter(this.x, this.f3714i, this.y, list);
            this.f3713h = cashierPayAdapter2;
            CustomDrawOrderRecyclerView customDrawOrderRecyclerView = this.f3714i;
            if (customDrawOrderRecyclerView != null) {
                customDrawOrderRecyclerView.setAdapter(cashierPayAdapter2);
            }
            M().h(this.f3713h);
            N().O(this.f3713h);
            G().l(this.f3713h);
            J().h(this.f3713h);
            F().h(this.f3713h);
            I().h(this.f3713h);
            H().l(this.f3713h);
        }
        com.jd.lib.cashier.sdk.pay.aac.impl.channel.a aVar2 = this.f3716k;
        if ((aVar2 != null ? aVar2.l() : null) == null && (aVar = this.f3716k) != null) {
            aVar.r(this.f3713h);
        }
        if ((list == null || list.isEmpty()) || (cashierPayAdapter = this.f3713h) == null || cashierPayAdapter == null) {
            return;
        }
        cashierPayAdapter.B(list);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void V() {
        com.jd.lib.cashier.sdk.pay.aac.impl.e.f L = L();
        if (L != null) {
            L.onChangeSkin();
        }
        K().onChangeSkin();
        CashierPayHeaderImpl cashierPayHeaderImpl = this.f3715j;
        if (cashierPayHeaderImpl != null) {
            cashierPayHeaderImpl.onChangeSkin();
        }
        ViewGroup viewGroup = this.f3712g;
        if (viewGroup != null) {
            viewGroup.setBackgroundColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_F6F6F6, JDDarkUtil.COLOR_141212));
        }
    }

    private final void W() {
        List arrayList;
        CashierPayAdapter cashierPayAdapter = this.f3713h;
        if (cashierPayAdapter == null || (arrayList = cashierPayAdapter.getData()) == null) {
            arrayList = new ArrayList();
        }
        ArrayList arrayList2 = new ArrayList();
        arrayList2.addAll(arrayList);
        CashierPayAdapter cashierPayAdapter2 = this.f3713h;
        if (cashierPayAdapter2 != null) {
            cashierPayAdapter2.D(arrayList2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void X(Payment payment) {
        if (payment != null) {
            com.jd.lib.cashier.sdk.h.e.a.d().L(this.x, payment.code, com.jd.lib.cashier.sdk.h.h.e.a.f(payment), payment.defaultSelected, payment.changetag, payment.hasCouponExpo ? "1" : "0");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void Y() {
        Iterator<Map.Entry<String, com.jd.lib.cashier.sdk.pay.aac.impl.channel.b>> it = this.v.entrySet().iterator();
        while (it.hasNext()) {
            it.next().getValue().M();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void Z(List<? extends com.jd.lib.cashier.sdk.d.a.e.a> list, Payment payment) {
        if (payment != null) {
            payment.clickEvent = true;
        }
        f0(this.x, payment);
        c0(list, payment);
        if (Intrinsics.areEqual("5", payment != null ? payment.status : null)) {
            K().C(this.x);
            return;
        }
        if (Intrinsics.areEqual(CashierPayChannelCode.JD_PAY_BANKCARD, payment != null ? payment.code : null)) {
            if (Intrinsics.areEqual("JDP_ADD_NEWCARD", payment != null ? payment.channelId : null)) {
                K().C(this.x);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void a0(Payment payment) {
        S(payment, false);
        h0(payment);
        Q(payment);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void b0(Payment payment) {
        if (payment != null) {
            payment.clickEvent = true;
        }
        S(payment, true);
        R(payment);
    }

    private final void c0(List<? extends com.jd.lib.cashier.sdk.d.a.e.a> list, Payment payment) {
        S(payment, false);
        CashierPayAdapter cashierPayAdapter = this.f3713h;
        if (cashierPayAdapter != null) {
            cashierPayAdapter.C(list, false);
        }
        Q(payment);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void d0(List<? extends com.jd.lib.cashier.sdk.d.a.e.a> list, com.jd.lib.cashier.sdk.h.d.a aVar) {
        if (list != null) {
            for (com.jd.lib.cashier.sdk.d.a.e.a aVar2 : list) {
                if ((aVar2 instanceof com.jd.lib.cashier.sdk.h.g.t) && com.jd.lib.cashier.sdk.h.h.g.a(((com.jd.lib.cashier.sdk.h.g.t) aVar2).a().code)) {
                    if (aVar != null) {
                        aVar.a = (x) aVar2;
                        return;
                    }
                    return;
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void e0(List<? extends com.jd.lib.cashier.sdk.d.a.e.a> list) {
        if ((!list.isEmpty()) == true) {
            for (com.jd.lib.cashier.sdk.d.a.e.a aVar : list) {
                if (aVar instanceof x) {
                    x xVar = (x) aVar;
                    if (!xVar.a().selected && xVar.a().canUse() && !xVar.a().isCombine() && xVar.a().baitiaoPlanInfo != null) {
                        String str = xVar.a().code;
                        Intrinsics.checkExpressionValueIsNotNull(str, "template.payment.code");
                        E(str, com.jd.lib.cashier.sdk.h.h.e.a.d(xVar.a())).N(xVar.a());
                    }
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void f0(FragmentActivity fragmentActivity, Payment payment) {
        CashierPayViewModel x;
        com.jd.lib.cashier.sdk.h.c.a b2;
        if (!(fragmentActivity instanceof CashierPayActivity)) {
            fragmentActivity = null;
        }
        CashierPayActivity cashierPayActivity = (CashierPayActivity) fragmentActivity;
        if (cashierPayActivity == null || (x = cashierPayActivity.x()) == null || (b2 = x.b()) == null) {
            return;
        }
        b2.Q.f(b2.O);
        b2.O = payment;
        g0(payment);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void g0(Payment payment) {
        com.jd.lib.cashier.sdk.h.d.a aVar = this.y;
        if (com.jd.lib.cashier.sdk.h.h.g.j(aVar.f3523e, aVar.b)) {
            com.jd.lib.cashier.sdk.h.d.a aVar2 = this.y;
            aVar2.f3524f = aVar2.f3523e;
        } else if (com.jd.lib.cashier.sdk.h.h.g.j(payment, this.y.b)) {
            this.y.f3524f = payment;
        }
        this.y.f3523e = payment;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void h0(Payment payment) {
        com.jd.lib.cashier.sdk.b.i.e.g("SEND_UPDATE_PAYMENT_LIST", "UPDATE_PAYMENT", new UpdatePaymentListEvent(payment, false));
    }

    @Override // com.jd.lib.cashier.sdk.core.aac.f
    public void a() {
        com.jd.lib.cashier.sdk.pay.aac.impl.e.f L = L();
        if (!(L instanceof com.jd.lib.cashier.sdk.core.aac.f)) {
            L = null;
        }
        com.jd.lib.cashier.sdk.core.aac.f fVar = (com.jd.lib.cashier.sdk.core.aac.f) L;
        if (fVar != null) {
            fVar.a();
        }
    }

    @Override // com.jd.lib.cashier.sdk.core.aac.f
    public void c() {
        com.jd.lib.cashier.sdk.pay.aac.impl.e.f L = L();
        if (!(L instanceof com.jd.lib.cashier.sdk.core.aac.f)) {
            L = null;
        }
        com.jd.lib.cashier.sdk.core.aac.f fVar = (com.jd.lib.cashier.sdk.core.aac.f) L;
        if (fVar != null) {
            fVar.c();
        }
    }

    @Override // com.jd.lib.cashier.sdk.core.aac.d
    public void f(@NotNull FragmentActivity fragmentActivity) {
        if (g0.a(fragmentActivity)) {
            CashierPayActivity cashierPayActivity = (CashierPayActivity) (!(fragmentActivity instanceof CashierPayActivity) ? null : fragmentActivity);
            if (cashierPayActivity != null) {
                CashierPayHeaderImpl cashierPayHeaderImpl = new CashierPayHeaderImpl(cashierPayActivity);
                this.f3715j = cashierPayHeaderImpl;
                if (cashierPayHeaderImpl != null) {
                    cashierPayHeaderImpl.f(cashierPayActivity);
                }
                CashierPayHeaderImpl cashierPayHeaderImpl2 = this.f3715j;
                if (cashierPayHeaderImpl2 != null) {
                    cashierPayHeaderImpl2.h(cashierPayActivity.getWindow());
                }
            }
            ViewModel viewModel = ViewModelProviders.of(fragmentActivity).get(CashierPayViewModel.class);
            Intrinsics.checkExpressionValueIsNotNull(viewModel, "ViewModelProviders.of(ac\u2026PayViewModel::class.java)");
            CashierPayViewModel cashierPayViewModel = (CashierPayViewModel) viewModel;
            cashierPayViewModel.H().observe(fragmentActivity, new q(fragmentActivity, cashierPayViewModel));
            com.jd.lib.cashier.sdk.b.i.e.c("cashier_item_click", new r(fragmentActivity));
            M().j(fragmentActivity);
            P().f(fragmentActivity);
            O().f(fragmentActivity);
            K().f(fragmentActivity);
            G().f(fragmentActivity);
            N().f(fragmentActivity);
            J().f(fragmentActivity);
            F().f(fragmentActivity);
            I().f(fragmentActivity);
            H().f(fragmentActivity);
        }
    }

    @Override // com.jd.lib.cashier.sdk.core.aac.c
    public void h(@NotNull Window window) {
        RecyclerView.RecycledViewPool recycledViewPool;
        RecyclerView.RecycledViewPool recycledViewPool2;
        RecyclerView.RecycledViewPool recycledViewPool3;
        RecyclerView.RecycledViewPool recycledViewPool4;
        L().h(window);
        K().w(window);
        this.f3712g = (ViewGroup) window.findViewById(R.id.lib_cashier_home_payment_root);
        this.f3714i = (CustomDrawOrderRecyclerView) window.findViewById(R.id.lib_cashier_pay_recycler_view);
        WrapContentGridLayoutManager wrapContentGridLayoutManager = new WrapContentGridLayoutManager(this.x, 34);
        CustomDrawOrderRecyclerView customDrawOrderRecyclerView = this.f3714i;
        if (customDrawOrderRecyclerView != null) {
            customDrawOrderRecyclerView.setLayoutManager(wrapContentGridLayoutManager);
        }
        CustomDrawOrderRecyclerView customDrawOrderRecyclerView2 = this.f3714i;
        if (customDrawOrderRecyclerView2 != null && (recycledViewPool4 = customDrawOrderRecyclerView2.getRecycledViewPool()) != null) {
            recycledViewPool4.setMaxRecycledViews(200001, 15);
        }
        CustomDrawOrderRecyclerView customDrawOrderRecyclerView3 = this.f3714i;
        if (customDrawOrderRecyclerView3 != null && (recycledViewPool3 = customDrawOrderRecyclerView3.getRecycledViewPool()) != null) {
            recycledViewPool3.setMaxRecycledViews(400003, 15);
        }
        CustomDrawOrderRecyclerView customDrawOrderRecyclerView4 = this.f3714i;
        if (customDrawOrderRecyclerView4 != null && (recycledViewPool2 = customDrawOrderRecyclerView4.getRecycledViewPool()) != null) {
            recycledViewPool2.setMaxRecycledViews(ThemeTitleDataController.DELAY_TIME, 15);
        }
        CustomDrawOrderRecyclerView customDrawOrderRecyclerView5 = this.f3714i;
        if (customDrawOrderRecyclerView5 != null && (recycledViewPool = customDrawOrderRecyclerView5.getRecycledViewPool()) != null) {
            recycledViewPool.setMaxRecycledViews(500000, 15);
        }
        ViewGroup viewGroup = this.f3712g;
        if (viewGroup != null) {
            viewGroup.setBackgroundColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_F6F6F6, JDDarkUtil.COLOR_141212));
        }
    }

    @Override // com.jd.lib.cashier.sdk.core.aac.e
    public void onChangeSkin() {
        try {
            W();
            V();
        } catch (Exception e2) {
            com.jd.lib.cashier.sdk.core.utils.r.d(z, e2.getMessage());
        }
    }

    @Override // com.jd.lib.cashier.sdk.d.d.a
    public void onDestroy() {
        CashierPayAdapter cashierPayAdapter = this.f3713h;
        if (cashierPayAdapter != null) {
            cashierPayAdapter.onDestroy();
        }
        com.jd.lib.cashier.sdk.pay.aac.impl.e.f L = L();
        if (L != null) {
            L.onDestroy();
        }
        CashierPayHeaderImpl cashierPayHeaderImpl = this.f3715j;
        if (cashierPayHeaderImpl != null) {
            cashierPayHeaderImpl.onDestroy();
        }
        com.jd.lib.cashier.sdk.pay.aac.impl.b K = K();
        if (K != null) {
            K.onDestroy();
        }
        com.jd.lib.cashier.sdk.h.h.a.b();
        this.v.clear();
    }

    @Override // com.jd.lib.cashier.sdk.pay.aac.impl.e.b
    public void onPause() {
        K().onPause();
    }

    @Override // com.jd.lib.cashier.sdk.pay.aac.impl.e.b
    public void onResume() {
        K().onResume();
    }
}

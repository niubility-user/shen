package com.jd.lib.cashier.sdk.pay.aac.impl.d;

import android.text.TextUtils;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import com.jd.lib.cashier.sdk.b.i.e;
import com.jd.lib.cashier.sdk.core.ui.widget.CashierNoticeView;
import com.jd.lib.cashier.sdk.core.utils.d0;
import com.jd.lib.cashier.sdk.core.utils.g0;
import com.jd.lib.cashier.sdk.core.utils.j0;
import com.jd.lib.cashier.sdk.h.g.x;
import com.jd.lib.cashier.sdk.h.h.g;
import com.jd.lib.cashier.sdk.pay.aac.viewmodel.CashierPayViewModel;
import com.jd.lib.cashier.sdk.pay.adapter.CashierPayAdapter;
import com.jd.lib.cashier.sdk.pay.bean.BottomRecommendInfo;
import com.jd.lib.cashier.sdk.pay.bean.CashierPayEntity;
import com.jd.lib.cashier.sdk.pay.bean.Payment;
import com.jd.lib.cashier.sdk.pay.bean.RecChannel;
import com.jd.lib.cashier.sdk.pay.bean.syncevent.ClickPayChannelItemEvent;
import com.jd.lib.cashier.sdk.pay.bean.syncevent.SendWholePaymentEvent;
import com.jd.lib.cashier.sdk.pay.view.CashierPayActivity;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Pair;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* loaded from: classes14.dex */
public final class c implements com.jd.lib.cashier.sdk.d.d.a {
    @Nullable

    /* renamed from: g  reason: collision with root package name */
    private CashierPayAdapter f3744g;

    /* renamed from: h  reason: collision with root package name */
    private CashierNoticeView f3745h;

    /* loaded from: classes14.dex */
    public static final class a implements com.jd.lib.cashier.sdk.core.ui.a.a {
        final /* synthetic */ FragmentActivity b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ RecChannel f3746c;
        final /* synthetic */ CashierPayViewModel d;

        a(FragmentActivity fragmentActivity, RecChannel recChannel, CashierPayViewModel cashierPayViewModel) {
            this.b = fragmentActivity;
            this.f3746c = recChannel;
            this.d = cashierPayViewModel;
        }

        @Override // com.jd.lib.cashier.sdk.core.ui.a.a
        public void a() {
            com.jd.lib.cashier.sdk.h.e.a d = com.jd.lib.cashier.sdk.h.e.a.d();
            FragmentActivity fragmentActivity = this.b;
            RecChannel recChannel = this.f3746c;
            d.p(fragmentActivity, recChannel.code, recChannel.closeButton);
            Pair h2 = c.this.h(this.b, this.f3746c);
            String str = h2 != null ? (String) h2.getFirst() : null;
            x xVar = h2 != null ? (x) h2.getSecond() : null;
            if (xVar != null) {
                if (this.f3746c.recommendBankCoupon != null) {
                    xVar.a().selectedCouponEntity = this.f3746c.recommendBankCoupon;
                }
                if (!TextUtils.isEmpty(this.f3746c.plan) && this.f3746c.recommendCoupon != null) {
                    xVar.a().recommendPlan = this.f3746c.plan;
                    xVar.a().recommendCoupon = this.f3746c.recommendCoupon;
                }
                if (TextUtils.equals(str, "1")) {
                    e.f("cashier_item_click", new ClickPayChannelItemEvent(xVar.a()));
                } else if (TextUtils.equals(str, "2")) {
                    e.f("SEND_UPDATE_WHOLE_PAYMENT", new SendWholePaymentEvent(xVar.a()));
                }
            }
        }

        @Override // com.jd.lib.cashier.sdk.core.ui.a.a
        public void b() {
            com.jd.lib.cashier.sdk.h.c.a b;
            CashierPayEntity cashierPayEntity;
            com.jd.lib.cashier.sdk.h.e.a.d().q(this.b, this.f3746c.code);
            CashierPayViewModel cashierPayViewModel = this.d;
            if (cashierPayViewModel != null && (b = cashierPayViewModel.b()) != null && (cashierPayEntity = b.K) != null) {
                cashierPayEntity.bottomRecommendInfo = null;
            }
            CashierPayViewModel cashierPayViewModel2 = this.d;
            if (cashierPayViewModel2 != null) {
                cashierPayViewModel2.n(this.f3746c);
            }
        }
    }

    public c(@Nullable CashierNoticeView cashierNoticeView) {
        this.f3745h = cashierNoticeView;
    }

    private final void c(FragmentActivity fragmentActivity) {
        CashierPayViewModel cashierPayViewModel;
        com.jd.lib.cashier.sdk.h.c.a b;
        j0.b(this.f3745h);
        if (!g0.a(fragmentActivity) || (cashierPayViewModel = (CashierPayViewModel) ViewModelProviders.of(fragmentActivity).get(CashierPayViewModel.class)) == null || (b = cashierPayViewModel.b()) == null) {
            return;
        }
        b.P = null;
    }

    private final RecChannel f(Payment payment, List<? extends RecChannel> list) {
        if (list == null || list.isEmpty()) {
            return null;
        }
        for (RecChannel recChannel : list) {
            if (p(recChannel, payment)) {
                return recChannel;
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Pair<String, x> h(FragmentActivity fragmentActivity, RecChannel recChannel) {
        List<com.jd.lib.cashier.sdk.d.a.e.a> arrayList;
        CashierPayAdapter cashierPayAdapter = this.f3744g;
        if (cashierPayAdapter != null) {
            if (cashierPayAdapter == null || (arrayList = cashierPayAdapter.getData()) == null) {
                arrayList = new ArrayList();
            }
            for (com.jd.lib.cashier.sdk.d.a.e.a aVar : arrayList) {
                if (aVar instanceof x) {
                    x xVar = (x) aVar;
                    if (p(recChannel, xVar.a())) {
                        Payment a2 = xVar.a();
                        if (g.g(a2 != null ? a2.status : null)) {
                            return new Pair<>("1", aVar);
                        }
                    } else {
                        continue;
                    }
                }
            }
        }
        if (g0.a(fragmentActivity)) {
            ViewModel viewModel = ViewModelProviders.of(fragmentActivity).get(CashierPayViewModel.class);
            Intrinsics.checkExpressionValueIsNotNull(viewModel, "ViewModelProviders.of(ac\u2026PayViewModel::class.java)");
            List<com.jd.lib.cashier.sdk.d.a.e.a> list = ((CashierPayViewModel) viewModel).b().N;
            if (list == null) {
                list = new ArrayList();
            }
            for (com.jd.lib.cashier.sdk.d.a.e.a aVar2 : list) {
                if (aVar2 instanceof x) {
                    x xVar2 = (x) aVar2;
                    if (p(recChannel, xVar2.a())) {
                        Payment a3 = xVar2.a();
                        if (g.g(a3 != null ? a3.status : null)) {
                            return new Pair<>("2", aVar2);
                        }
                    } else {
                        continue;
                    }
                }
            }
        }
        return new Pair<>("", null);
    }

    private final void m(FragmentActivity fragmentActivity) {
        com.jd.lib.cashier.sdk.h.c.a b;
        CashierPayEntity cashierPayEntity;
        com.jd.lib.cashier.sdk.h.c.a b2;
        com.jd.lib.cashier.sdk.h.c.b bVar;
        com.jd.lib.cashier.sdk.h.c.a b3;
        CashierPayActivity cashierPayActivity = (CashierPayActivity) (!(fragmentActivity instanceof CashierPayActivity) ? null : fragmentActivity);
        CashierPayViewModel x = cashierPayActivity != null ? cashierPayActivity.x() : null;
        Payment payment = (x == null || (b3 = x.b()) == null) ? null : b3.O;
        Payment c2 = (x == null || (b2 = x.b()) == null || (bVar = b2.Q) == null) ? null : bVar.c();
        BottomRecommendInfo bottomRecommendInfo = (x == null || (b = x.b()) == null || (cashierPayEntity = b.K) == null) ? null : cashierPayEntity.bottomRecommendInfo;
        if ((!Intrinsics.areEqual(payment, c2)) == true) {
            RecChannel f2 = f(c2, bottomRecommendInfo != null ? bottomRecommendInfo.channelList : null);
            boolean o = o(c2, bottomRecommendInfo != null ? bottomRecommendInfo.channelList : null);
            if (c2 != null && c2.jdPay) {
                if (payment != null && payment.jdPay) {
                    c(fragmentActivity);
                } else if (o) {
                    if (TextUtils.isEmpty(f2 != null ? f2.recommendDesc : null)) {
                        return;
                    }
                    r(fragmentActivity, f2);
                }
            } else if (payment == null || !payment.jdPay) {
            } else {
                c(fragmentActivity);
            }
        }
    }

    private final void n(FragmentActivity fragmentActivity, RecChannel recChannel, Payment payment) {
        com.jd.lib.cashier.sdk.h.c.a b;
        CashierPayActivity cashierPayActivity = (CashierPayActivity) (!(fragmentActivity instanceof CashierPayActivity) ? null : fragmentActivity);
        CashierPayViewModel x = cashierPayActivity != null ? cashierPayActivity.x() : null;
        if (x != null && (b = x.b()) != null) {
            b.P = recChannel;
        }
        if (!TextUtils.isEmpty(recChannel != null ? recChannel.recommendDesc : null) && ((payment == null || !payment.jdPay) && !p(recChannel, payment))) {
            r(fragmentActivity, recChannel);
        } else {
            j0.b(this.f3745h);
        }
    }

    private final boolean o(Payment payment, List<? extends RecChannel> list) {
        if (list == null || list.isEmpty()) {
            return false;
        }
        Iterator<? extends RecChannel> it = list.iterator();
        while (it.hasNext()) {
            if (p(it.next(), payment)) {
                return true;
            }
        }
        return false;
    }

    private final boolean p(RecChannel recChannel, Payment payment) {
        if (d0.a(recChannel != null ? recChannel.code : null, payment != null ? payment.code : null)) {
            if (d0.a(recChannel != null ? recChannel.channelId : null, payment != null ? payment.channelId : null)) {
                if (d0.a(recChannel != null ? recChannel.uniqueChannelId : null, payment != null ? payment.uniqueChannelId : null)) {
                    return true;
                }
            }
        }
        return false;
    }

    private final void r(FragmentActivity fragmentActivity, RecChannel recChannel) {
        com.jd.lib.cashier.sdk.h.c.a b;
        if (!g0.a(fragmentActivity) || recChannel == null) {
            return;
        }
        j0.d(this.f3745h);
        com.jd.lib.cashier.sdk.h.e.a.d().o0(fragmentActivity, recChannel.code, recChannel.closeButton);
        CashierPayViewModel cashierPayViewModel = (CashierPayViewModel) ViewModelProviders.of(fragmentActivity).get(CashierPayViewModel.class);
        if (cashierPayViewModel != null && (b = cashierPayViewModel.b()) != null) {
            b.P = recChannel;
        }
        CashierNoticeView cashierNoticeView = this.f3745h;
        if (cashierNoticeView != null) {
            String str = recChannel.recommendDesc;
            Intrinsics.checkExpressionValueIsNotNull(str, "currentSelectRecChannel.recommendDesc");
            cashierNoticeView.c(str, recChannel.btnText, recChannel.closeButton);
        }
        CashierNoticeView cashierNoticeView2 = this.f3745h;
        if (cashierNoticeView2 != null) {
            cashierNoticeView2.h(new a(fragmentActivity, recChannel, cashierPayViewModel));
        }
    }

    public boolean l(@NotNull FragmentActivity fragmentActivity, boolean z) {
        com.jd.lib.cashier.sdk.h.c.a b;
        CashierPayEntity cashierPayEntity;
        com.jd.lib.cashier.sdk.h.c.a b2;
        CashierPayActivity cashierPayActivity = (CashierPayActivity) (!(fragmentActivity instanceof CashierPayActivity) ? null : fragmentActivity);
        CashierPayViewModel x = cashierPayActivity != null ? cashierPayActivity.x() : null;
        Payment payment = (x == null || (b2 = x.b()) == null) ? null : b2.O;
        BottomRecommendInfo bottomRecommendInfo = (x == null || (b = x.b()) == null || (cashierPayEntity = b.K) == null) ? null : cashierPayEntity.bottomRecommendInfo;
        RecChannel recChannel = bottomRecommendInfo != null ? bottomRecommendInfo.otherRecChannel : null;
        if (recChannel != null && z) {
            n(fragmentActivity, recChannel, payment);
            return true;
        } else if (bottomRecommendInfo != null && payment != null) {
            m(fragmentActivity);
            return true;
        } else {
            c(fragmentActivity);
            return true;
        }
    }

    public final void q(@Nullable CashierPayAdapter cashierPayAdapter) {
        this.f3744g = cashierPayAdapter;
    }
}

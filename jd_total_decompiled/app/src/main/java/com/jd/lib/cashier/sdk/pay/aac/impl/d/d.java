package com.jd.lib.cashier.sdk.pay.aac.impl.d;

import android.text.TextUtils;
import androidx.fragment.app.FragmentActivity;
import com.jd.lib.cashier.sdk.core.ui.widget.CashierNoticeView;
import com.jd.lib.cashier.sdk.core.utils.d0;
import com.jd.lib.cashier.sdk.core.utils.g0;
import com.jd.lib.cashier.sdk.pay.aac.viewmodel.CashierPayViewModel;
import com.jd.lib.cashier.sdk.pay.bean.BottomMarketActivity;
import com.jd.lib.cashier.sdk.pay.bean.CashierPayEntity;
import com.jd.lib.cashier.sdk.pay.bean.MarketActivityInfo;
import com.jd.lib.cashier.sdk.pay.bean.Payment;
import com.jd.lib.cashier.sdk.pay.view.CashierPayActivity;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* loaded from: classes14.dex */
public final class d implements com.jd.lib.cashier.sdk.d.d.a {

    /* renamed from: g  reason: collision with root package name */
    private CashierNoticeView f3747g;

    public d(@Nullable CashierNoticeView cashierNoticeView) {
        this.f3747g = cashierNoticeView;
    }

    private final String a(Payment payment, List<? extends MarketActivityInfo> list) {
        if (payment != null) {
            if (list == null || list.isEmpty()) {
                return "";
            }
            for (MarketActivityInfo marketActivityInfo : list) {
                if (d0.a(marketActivityInfo.code, payment.code) && d0.a(marketActivityInfo.channelId, payment.channelId) && d0.a(marketActivityInfo.uniqueChannelId, payment.uniqueChannelId)) {
                    String str = marketActivityInfo.marketDesc;
                    Intrinsics.checkExpressionValueIsNotNull(str, "activity.marketDesc");
                    return str;
                }
            }
            return "";
        }
        return "";
    }

    public boolean c(@NotNull FragmentActivity fragmentActivity, boolean z) {
        CashierPayViewModel x;
        com.jd.lib.cashier.sdk.h.c.a b;
        CashierPayEntity cashierPayEntity;
        BottomMarketActivity bottomMarketActivity;
        CashierPayViewModel x2;
        com.jd.lib.cashier.sdk.h.c.a b2;
        if (g0.a(fragmentActivity)) {
            boolean z2 = fragmentActivity instanceof CashierPayActivity;
            CashierPayActivity cashierPayActivity = (CashierPayActivity) (!z2 ? null : fragmentActivity);
            Payment payment = (cashierPayActivity == null || (x2 = cashierPayActivity.x()) == null || (b2 = x2.b()) == null) ? null : b2.O;
            CashierPayActivity cashierPayActivity2 = (CashierPayActivity) (!z2 ? null : fragmentActivity);
            String a = a(payment, (cashierPayActivity2 == null || (x = cashierPayActivity2.x()) == null || (b = x.b()) == null || (cashierPayEntity = b.K) == null || (bottomMarketActivity = cashierPayEntity.bottomMarketInfo) == null) ? null : bottomMarketActivity.channelList);
            if (TextUtils.isEmpty(a)) {
                CashierNoticeView cashierNoticeView = this.f3747g;
                if (cashierNoticeView != null) {
                    cashierNoticeView.setVisibility(8);
                }
                return false;
            }
            CashierNoticeView cashierNoticeView2 = this.f3747g;
            if (cashierNoticeView2 != null) {
                cashierNoticeView2.setVisibility(0);
            }
            CashierNoticeView cashierNoticeView3 = this.f3747g;
            if (cashierNoticeView3 != null) {
                cashierNoticeView3.b(a);
            }
            com.jd.lib.cashier.sdk.h.e.a.d().n0(fragmentActivity, payment != null ? payment.code : null);
            return true;
        }
        return false;
    }
}

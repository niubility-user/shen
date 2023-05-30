package com.jd.lib.cashier.sdk.pay.aac.impl.d;

import android.text.TextUtils;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import com.jd.lib.cashier.sdk.core.ui.widget.CashierNoticeView;
import com.jd.lib.cashier.sdk.core.utils.g0;
import com.jd.lib.cashier.sdk.pay.aac.viewmodel.CashierPayViewModel;
import com.jd.lib.cashier.sdk.pay.adapter.CashierPayAdapter;
import com.jd.lib.cashier.sdk.pay.bean.BottomMarketActivity;
import com.jd.lib.cashier.sdk.pay.bean.CashierPayEntity;
import com.jd.lib.cashier.sdk.pay.bean.MarketActivityInfo;
import java.util.List;

/* loaded from: classes14.dex */
public class a {
    private b a;
    private d b;

    /* renamed from: c  reason: collision with root package name */
    private c f3742c;

    public void a(FragmentActivity fragmentActivity, CashierPayAdapter cashierPayAdapter, boolean z) {
        CashierPayEntity cashierPayEntity;
        List<MarketActivityInfo> list;
        d dVar;
        b bVar;
        if (!g0.a(fragmentActivity) || (cashierPayEntity = ((CashierPayViewModel) ViewModelProviders.of(fragmentActivity).get(CashierPayViewModel.class)).b().K) == null) {
            return;
        }
        if (!TextUtils.isEmpty(cashierPayEntity.btnDesc) && (bVar = this.a) != null) {
            bVar.a(fragmentActivity, z);
            return;
        }
        BottomMarketActivity bottomMarketActivity = cashierPayEntity.bottomMarketInfo;
        if (bottomMarketActivity != null && (list = bottomMarketActivity.channelList) != null && !list.isEmpty() && (dVar = this.b) != null) {
            dVar.c(fragmentActivity, z);
            return;
        }
        c cVar = this.f3742c;
        if (cVar != null) {
            cVar.q(cashierPayAdapter);
            this.f3742c.l(fragmentActivity, z);
        }
    }

    public void b(CashierNoticeView cashierNoticeView) {
        this.a = new b(cashierNoticeView);
        this.b = new d(cashierNoticeView);
        this.f3742c = new c(cashierNoticeView);
    }
}

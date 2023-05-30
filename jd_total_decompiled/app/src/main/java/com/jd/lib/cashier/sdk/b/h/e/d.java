package com.jd.lib.cashier.sdk.b.h.e;

import androidx.fragment.app.FragmentActivity;
import com.jd.lib.cashier.sdk.core.utils.g0;
import com.jd.lib.cashier.sdk.pay.aac.viewmodel.CashierPayViewModel;
import com.jd.lib.cashier.sdk.pay.view.CashierPayActivity;
import org.jetbrains.annotations.NotNull;

/* loaded from: classes14.dex */
public final class d extends com.jd.lib.cashier.sdk.core.utils.a {
    @Override // com.jd.lib.cashier.sdk.core.utils.a
    public <T extends FragmentActivity> void a(@NotNull T t) {
        CashierPayViewModel x;
        if (g0.a(t)) {
            CashierPayActivity cashierPayActivity = (CashierPayActivity) (!(t instanceof CashierPayActivity) ? null : t);
            if (cashierPayActivity == null || (x = cashierPayActivity.x()) == null) {
                return;
            }
            CashierPayViewModel.m(x, t, null, null, 6, null);
        }
    }
}

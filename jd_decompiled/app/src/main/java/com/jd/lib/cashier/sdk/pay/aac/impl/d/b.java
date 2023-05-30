package com.jd.lib.cashier.sdk.pay.aac.impl.d;

import android.text.TextUtils;
import androidx.fragment.app.FragmentActivity;
import com.jd.lib.cashier.sdk.core.ui.widget.CashierNoticeView;
import com.jd.lib.cashier.sdk.core.utils.g0;
import com.jd.lib.cashier.sdk.pay.aac.viewmodel.CashierPayViewModel;
import com.jd.lib.cashier.sdk.pay.bean.CashierPayEntity;
import com.jd.lib.cashier.sdk.pay.view.CashierPayActivity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* loaded from: classes14.dex */
public final class b implements com.jd.lib.cashier.sdk.d.d.a {

    /* renamed from: g  reason: collision with root package name */
    private CashierNoticeView f3743g;

    public b(@Nullable CashierNoticeView cashierNoticeView) {
        this.f3743g = cashierNoticeView;
    }

    public boolean a(@NotNull FragmentActivity fragmentActivity, boolean z) {
        String str;
        CashierPayViewModel x;
        com.jd.lib.cashier.sdk.h.c.a b;
        CashierPayEntity cashierPayEntity;
        if (g0.a(fragmentActivity)) {
            if (!(fragmentActivity instanceof CashierPayActivity)) {
                fragmentActivity = null;
            }
            CashierPayActivity cashierPayActivity = (CashierPayActivity) fragmentActivity;
            if (cashierPayActivity == null || (x = cashierPayActivity.x()) == null || (b = x.b()) == null || (cashierPayEntity = b.K) == null || (str = cashierPayEntity.btnDesc) == null) {
                str = "";
            }
            if (TextUtils.isEmpty(str)) {
                CashierNoticeView cashierNoticeView = this.f3743g;
                if (cashierNoticeView != null) {
                    cashierNoticeView.setVisibility(8);
                }
                return false;
            }
            CashierNoticeView cashierNoticeView2 = this.f3743g;
            if (cashierNoticeView2 != null) {
                cashierNoticeView2.setVisibility(0);
            }
            CashierNoticeView cashierNoticeView3 = this.f3743g;
            if (cashierNoticeView3 != null) {
                cashierNoticeView3.b(str);
                return true;
            }
            return true;
        }
        return false;
    }
}

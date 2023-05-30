package com.jd.lib.cashier.sdk.h.h;

import androidx.fragment.app.FragmentActivity;
import com.jd.lib.cashier.sdk.pay.aac.viewmodel.CashierPayViewModel;
import com.jd.lib.cashier.sdk.pay.bean.convert.PaymentChoseHolder;
import com.jd.lib.cashier.sdk.pay.view.CashierPayActivity;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* loaded from: classes14.dex */
public final class n {
    private static final String a = "n";
    public static final n b = new n();

    private n() {
    }

    @JvmStatic
    @Nullable
    public static final PaymentChoseHolder a(@NotNull FragmentActivity fragmentActivity) {
        com.jd.lib.cashier.sdk.h.c.b bVar;
        CashierPayViewModel x = ((CashierPayActivity) fragmentActivity).x();
        Intrinsics.checkExpressionValueIsNotNull(x, "(fragmentActivity as CashierPayActivity).viewModel");
        com.jd.lib.cashier.sdk.h.c.a b2 = x.b();
        if (b2 == null || (bVar = b2.Q) == null) {
            return null;
        }
        return bVar.b();
    }

    public final String b() {
        return a;
    }
}

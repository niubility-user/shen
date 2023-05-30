package com.jd.lib.cashier.sdk.b.h.e;

import com.jd.lib.cashier.sdk.pay.aac.viewmodel.CashierPayViewModel;
import com.jd.lib.cashier.sdk.pay.view.CashierPayActivity;
import java.util.concurrent.LinkedBlockingQueue;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* loaded from: classes14.dex */
public final class e extends a<CashierPayActivity> {
    @NotNull

    /* renamed from: c  reason: collision with root package name */
    private final CashierPayActivity f2877c;

    public e(@NotNull CashierPayActivity cashierPayActivity) {
        super(cashierPayActivity);
        this.f2877c = cashierPayActivity;
    }

    @Override // com.jd.lib.cashier.sdk.b.h.e.a
    @NotNull
    public LinkedBlockingQueue<com.jd.lib.cashier.sdk.core.utils.a> e() {
        CashierPayViewModel x = this.f2877c.x();
        Intrinsics.checkExpressionValueIsNotNull(x, "activity.viewModel");
        com.jd.lib.cashier.sdk.h.c.a b = x.b();
        Intrinsics.checkExpressionValueIsNotNull(b, "activity.viewModel.state");
        LinkedBlockingQueue<com.jd.lib.cashier.sdk.core.utils.a> a = b.a();
        Intrinsics.checkExpressionValueIsNotNull(a, "activity.viewModel.state.cashierTaskQueue");
        return a;
    }
}

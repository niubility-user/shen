package com.jd.lib.cashier.sdk.pay.aac.livedata.a;

import com.jd.lib.cashier.sdk.pay.bean.creditcard.CreditCardPayPlanResponse;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* loaded from: classes14.dex */
public final class k {
    @Nullable
    private final l a;
    @NotNull
    private final CreditCardPayPlanResponse b;

    public k(@Nullable l lVar, @NotNull CreditCardPayPlanResponse creditCardPayPlanResponse) {
        this.a = lVar;
        this.b = creditCardPayPlanResponse;
    }

    @NotNull
    public final CreditCardPayPlanResponse a() {
        return this.b;
    }

    @Nullable
    public final l b() {
        return this.a;
    }
}

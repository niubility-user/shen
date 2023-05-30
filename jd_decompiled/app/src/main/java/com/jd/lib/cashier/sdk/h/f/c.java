package com.jd.lib.cashier.sdk.h.f;

import com.jd.lib.cashier.sdk.pay.view.CashierPayActivity;
import java.lang.ref.WeakReference;
import org.jetbrains.annotations.NotNull;

/* loaded from: classes14.dex */
public final class c extends com.jd.lib.cashier.sdk.d.f.c {
    @NotNull
    private final String a;

    public c(@NotNull String str, @NotNull CashierPayActivity cashierPayActivity) {
        super(new WeakReference(cashierPayActivity));
        this.a = str;
    }

    @NotNull
    public final String a() {
        return this.a;
    }

    @Override // com.jd.lib.cashier.sdk.d.f.c
    @NotNull
    public String toString() {
        return "BankCouponRequestParam(channelId='" + this.a + "')";
    }
}

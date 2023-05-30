package com.jd.lib.cashier.sdk.h.g;

import com.jd.lib.cashier.sdk.pay.bean.Payment;
import org.jetbrains.annotations.NotNull;

/* loaded from: classes14.dex */
public abstract class x extends com.jd.lib.cashier.sdk.d.a.e.a {
    @NotNull
    private Payment a;

    public x(@NotNull Payment payment) {
        this.a = payment;
    }

    @NotNull
    public final Payment a() {
        return this.a;
    }

    public final void b(@NotNull Payment payment) {
        this.a = payment;
    }
}

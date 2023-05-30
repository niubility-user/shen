package com.jd.lib.cashier.sdk.pay.aac.livedata.a;

import com.jd.lib.cashier.sdk.pay.bean.creditcard.CreditCard;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* loaded from: classes14.dex */
public final class l {
    @NotNull
    private final String a;
    @Nullable
    private final CreditCard b;

    public l(@NotNull String str, @NotNull String str2, @Nullable CreditCard creditCard) {
        this.a = str2;
        this.b = creditCard;
    }

    @NotNull
    public final String a() {
        return this.a;
    }

    @Nullable
    public final CreditCard b() {
        return this.b;
    }
}

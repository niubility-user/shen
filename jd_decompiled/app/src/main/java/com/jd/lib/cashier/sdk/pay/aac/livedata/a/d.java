package com.jd.lib.cashier.sdk.pay.aac.livedata.a;

import com.jd.lib.cashier.sdk.pay.bean.BankCouponResponse;
import com.jd.lib.cashier.sdk.pay.bean.Payment;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* loaded from: classes14.dex */
public final class d {
    @NotNull
    private final BankCouponResponse a;
    @NotNull
    private final Payment b;

    public d(@NotNull BankCouponResponse bankCouponResponse, @NotNull Payment payment) {
        this.a = bankCouponResponse;
        this.b = payment;
    }

    @NotNull
    public final BankCouponResponse a() {
        return this.a;
    }

    @NotNull
    public final Payment b() {
        return this.b;
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (obj instanceof d) {
                d dVar = (d) obj;
                return Intrinsics.areEqual(this.a, dVar.a) && Intrinsics.areEqual(this.b, dVar.b);
            }
            return false;
        }
        return true;
    }

    public int hashCode() {
        BankCouponResponse bankCouponResponse = this.a;
        int hashCode = (bankCouponResponse != null ? bankCouponResponse.hashCode() : 0) * 31;
        Payment payment = this.b;
        return hashCode + (payment != null ? payment.hashCode() : 0);
    }

    @NotNull
    public String toString() {
        return "BankCouponHttpEvent(bankCouponResponse=" + this.a + ", currentPayment=" + this.b + ")";
    }
}

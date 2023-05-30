package com.jd.lib.cashier.sdk.h.c;

import com.jd.lib.cashier.sdk.pay.bean.Payment;
import com.jd.lib.cashier.sdk.pay.bean.convert.PaymentChoseHolder;
import java.util.UUID;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* loaded from: classes14.dex */
public final class b {
    @NotNull
    private PaymentChoseHolder a;
    @Nullable
    private Payment b;
    @Nullable

    /* renamed from: c */
    private UUID f3521c;

    public b() {
        this(null, null, null, 7, null);
    }

    public b(@NotNull PaymentChoseHolder paymentChoseHolder, @Nullable Payment payment, @Nullable UUID uuid) {
        this.a = paymentChoseHolder;
        this.b = payment;
        this.f3521c = uuid;
    }

    @Nullable
    public final UUID a() {
        return this.f3521c;
    }

    @NotNull
    public final PaymentChoseHolder b() {
        return this.a;
    }

    @Nullable
    public final Payment c() {
        return this.b;
    }

    public final void d(@Nullable UUID uuid) {
        this.f3521c = uuid;
    }

    public final void e(@NotNull PaymentChoseHolder paymentChoseHolder) {
        this.a = paymentChoseHolder;
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (obj instanceof b) {
                b bVar = (b) obj;
                return Intrinsics.areEqual(this.a, bVar.a) && Intrinsics.areEqual(this.b, bVar.b) && Intrinsics.areEqual(this.f3521c, bVar.f3521c);
            }
            return false;
        }
        return true;
    }

    public final void f(@Nullable Payment payment) {
        this.b = payment;
    }

    public int hashCode() {
        PaymentChoseHolder paymentChoseHolder = this.a;
        int hashCode = (paymentChoseHolder != null ? paymentChoseHolder.hashCode() : 0) * 31;
        Payment payment = this.b;
        int hashCode2 = (hashCode + (payment != null ? payment.hashCode() : 0)) * 31;
        UUID uuid = this.f3521c;
        return hashCode2 + (uuid != null ? uuid.hashCode() : 0);
    }

    @NotNull
    public String toString() {
        return "CashierPayUserInteractionNode(currentPaymentChoseHolder=" + this.a + ", lastClickPayment=" + this.b + ", creditCardPlanRequestUUID=" + this.f3521c + ")";
    }

    public /* synthetic */ b(PaymentChoseHolder paymentChoseHolder, Payment payment, UUID uuid, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? new PaymentChoseHolder() : paymentChoseHolder, (i2 & 2) != 0 ? null : payment, (i2 & 4) != 0 ? null : uuid);
    }
}

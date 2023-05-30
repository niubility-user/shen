package com.jd.lib.cashier.sdk.pay.bean.syncevent;

import com.jd.lib.cashier.sdk.pay.bean.Payment;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0007\u0010\bR\u0019\u0010\u0003\u001a\u00020\u00028\u0006@\u0006\u00a2\u0006\f\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\t"}, d2 = {"Lcom/jd/lib/cashier/sdk/pay/bean/syncevent/SendWholePaymentEvent;", "", "Lcom/jd/lib/cashier/sdk/pay/bean/Payment;", "payment", "Lcom/jd/lib/cashier/sdk/pay/bean/Payment;", "getPayment", "()Lcom/jd/lib/cashier/sdk/pay/bean/Payment;", "<init>", "(Lcom/jd/lib/cashier/sdk/pay/bean/Payment;)V", "cashier_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes14.dex */
public final class SendWholePaymentEvent {
    @NotNull
    private final Payment payment;

    public SendWholePaymentEvent(@NotNull Payment payment) {
        this.payment = payment;
    }

    @NotNull
    public final Payment getPayment() {
        return this.payment;
    }
}

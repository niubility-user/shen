package com.jd.lib.cashier.sdk.pay.bean.syncevent;

import com.jd.lib.cashier.sdk.pay.bean.Payment;
import com.jd.lib.cashier.sdk.pay.bean.creditcard.CreditCardPlan;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\f\u001a\u00020\u0002\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0007\u00a2\u0006\u0004\b\u000e\u0010\u000fR\u0019\u0010\u0003\u001a\u00020\u00028\u0006@\u0006\u00a2\u0006\f\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006R\u001b\u0010\b\u001a\u0004\u0018\u00010\u00078\u0006@\u0006\u00a2\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\n\u0010\u000bR\u0019\u0010\f\u001a\u00020\u00028\u0006@\u0006\u00a2\u0006\f\n\u0004\b\f\u0010\u0004\u001a\u0004\b\r\u0010\u0006\u00a8\u0006\u0010"}, d2 = {"Lcom/jd/lib/cashier/sdk/pay/bean/syncevent/ClickCreditCardPlanItemEvent;", "", "Lcom/jd/lib/cashier/sdk/pay/bean/creditcard/CreditCardPlan;", "targetCreditCardPlan", "Lcom/jd/lib/cashier/sdk/pay/bean/creditcard/CreditCardPlan;", "getTargetCreditCardPlan", "()Lcom/jd/lib/cashier/sdk/pay/bean/creditcard/CreditCardPlan;", "Lcom/jd/lib/cashier/sdk/pay/bean/Payment;", "currentPayment", "Lcom/jd/lib/cashier/sdk/pay/bean/Payment;", "getCurrentPayment", "()Lcom/jd/lib/cashier/sdk/pay/bean/Payment;", "lastCreditCardPlan", "getLastCreditCardPlan", "<init>", "(Lcom/jd/lib/cashier/sdk/pay/bean/creditcard/CreditCardPlan;Lcom/jd/lib/cashier/sdk/pay/bean/creditcard/CreditCardPlan;Lcom/jd/lib/cashier/sdk/pay/bean/Payment;)V", "cashier_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes14.dex */
public final class ClickCreditCardPlanItemEvent {
    @Nullable
    private final Payment currentPayment;
    @NotNull
    private final CreditCardPlan lastCreditCardPlan;
    @NotNull
    private final CreditCardPlan targetCreditCardPlan;

    public ClickCreditCardPlanItemEvent(@NotNull CreditCardPlan creditCardPlan, @NotNull CreditCardPlan creditCardPlan2, @Nullable Payment payment) {
        this.targetCreditCardPlan = creditCardPlan;
        this.lastCreditCardPlan = creditCardPlan2;
        this.currentPayment = payment;
    }

    @Nullable
    public final Payment getCurrentPayment() {
        return this.currentPayment;
    }

    @NotNull
    public final CreditCardPlan getLastCreditCardPlan() {
        return this.lastCreditCardPlan;
    }

    @NotNull
    public final CreditCardPlan getTargetCreditCardPlan() {
        return this.targetCreditCardPlan;
    }
}

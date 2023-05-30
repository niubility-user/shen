package com.jd.lib.cashier.sdk.pay.bean.syncevent;

import com.jd.lib.cashier.sdk.pay.bean.creditcard.CreditCardPlan;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0013\u0012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u00a2\u0006\u0004\b\u0007\u0010\bR\u001b\u0010\u0003\u001a\u0004\u0018\u00010\u00028\u0006@\u0006\u00a2\u0006\f\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\t"}, d2 = {"Lcom/jd/lib/cashier/sdk/pay/bean/syncevent/ClickCreditCardPlanToFooterEvent;", "", "Lcom/jd/lib/cashier/sdk/pay/bean/creditcard/CreditCardPlan;", "selectedCreditCardPlan", "Lcom/jd/lib/cashier/sdk/pay/bean/creditcard/CreditCardPlan;", "getSelectedCreditCardPlan", "()Lcom/jd/lib/cashier/sdk/pay/bean/creditcard/CreditCardPlan;", "<init>", "(Lcom/jd/lib/cashier/sdk/pay/bean/creditcard/CreditCardPlan;)V", "cashier_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes14.dex */
public final class ClickCreditCardPlanToFooterEvent {
    @Nullable
    private final CreditCardPlan selectedCreditCardPlan;

    /* JADX WARN: Illegal instructions before constructor call */
    /* JADX WARN: Type inference failed for: r0v0, types: [kotlin.jvm.internal.DefaultConstructorMarker, com.jd.lib.cashier.sdk.pay.bean.creditcard.CreditCardPlan] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public ClickCreditCardPlanToFooterEvent() {
        /*
            r2 = this;
            r0 = 0
            r1 = 1
            r2.<init>(r0, r1, r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.lib.cashier.sdk.pay.bean.syncevent.ClickCreditCardPlanToFooterEvent.<init>():void");
    }

    public ClickCreditCardPlanToFooterEvent(@Nullable CreditCardPlan creditCardPlan) {
        this.selectedCreditCardPlan = creditCardPlan;
    }

    @Nullable
    public final CreditCardPlan getSelectedCreditCardPlan() {
        return this.selectedCreditCardPlan;
    }

    public /* synthetic */ ClickCreditCardPlanToFooterEvent(CreditCardPlan creditCardPlan, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? null : creditCardPlan);
    }
}

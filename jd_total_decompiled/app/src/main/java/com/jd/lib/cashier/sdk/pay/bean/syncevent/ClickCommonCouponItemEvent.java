package com.jd.lib.cashier.sdk.pay.bean.syncevent;

import com.jd.lib.cashier.sdk.pay.bean.Payment;
import com.jd.lib.cashier.sdk.pay.dialog.e;
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u001b\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u00a2\u0006\u0004\b\f\u0010\rR\u001b\u0010\u0003\u001a\u0004\u0018\u00010\u00028\u0006@\u0006\u00a2\u0006\f\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006R\u001b\u0010\b\u001a\u0004\u0018\u00010\u00078\u0006@\u0006\u00a2\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\n\u0010\u000b\u00a8\u0006\u000e"}, d2 = {"Lcom/jd/lib/cashier/sdk/pay/bean/syncevent/ClickCommonCouponItemEvent;", "", "Lcom/jd/lib/cashier/sdk/pay/dialog/e;", "currentCommonCoupon", "Lcom/jd/lib/cashier/sdk/pay/dialog/e;", "getCurrentCommonCoupon", "()Lcom/jd/lib/cashier/sdk/pay/dialog/e;", "Lcom/jd/lib/cashier/sdk/pay/bean/Payment;", "currentPayment", "Lcom/jd/lib/cashier/sdk/pay/bean/Payment;", "getCurrentPayment", "()Lcom/jd/lib/cashier/sdk/pay/bean/Payment;", "<init>", "(Lcom/jd/lib/cashier/sdk/pay/bean/Payment;Lcom/jd/lib/cashier/sdk/pay/dialog/e;)V", "cashier_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes14.dex */
public final class ClickCommonCouponItemEvent {
    @Nullable
    private final e currentCommonCoupon;
    @Nullable
    private final Payment currentPayment;

    public ClickCommonCouponItemEvent(@Nullable Payment payment, @Nullable e eVar) {
        this.currentPayment = payment;
        this.currentCommonCoupon = eVar;
    }

    @Nullable
    public final e getCurrentCommonCoupon() {
        return this.currentCommonCoupon;
    }

    @Nullable
    public final Payment getCurrentPayment() {
        return this.currentPayment;
    }
}

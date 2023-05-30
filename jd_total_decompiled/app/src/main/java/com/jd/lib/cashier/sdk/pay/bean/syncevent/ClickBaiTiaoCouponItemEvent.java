package com.jd.lib.cashier.sdk.pay.bean.syncevent;

import com.jd.lib.cashier.sdk.pay.bean.Payment;
import com.jd.viewkit.templates.view.helper.JDViewKitEventHelper;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0007\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0005\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0014\u0010\u0015J\u0010\u0010\u0003\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u0003\u0010\u0004J\u001a\u0010\u0006\u001a\u00020\u00002\b\b\u0002\u0010\u0005\u001a\u00020\u0002H\u00c6\u0001\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u0010\u0010\t\u001a\u00020\bH\u00d6\u0001\u00a2\u0006\u0004\b\t\u0010\nJ\u0010\u0010\f\u001a\u00020\u000bH\u00d6\u0001\u00a2\u0006\u0004\b\f\u0010\rJ\u001a\u0010\u0010\u001a\u00020\u000f2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003\u00a2\u0006\u0004\b\u0010\u0010\u0011R\u0019\u0010\u0005\u001a\u00020\u00028\u0006@\u0006\u00a2\u0006\f\n\u0004\b\u0005\u0010\u0012\u001a\u0004\b\u0013\u0010\u0004\u00a8\u0006\u0016"}, d2 = {"Lcom/jd/lib/cashier/sdk/pay/bean/syncevent/ClickBaiTiaoCouponItemEvent;", "", "Lcom/jd/lib/cashier/sdk/pay/bean/Payment;", "component1", "()Lcom/jd/lib/cashier/sdk/pay/bean/Payment;", "payment", JDViewKitEventHelper.ActionType_Copy, "(Lcom/jd/lib/cashier/sdk/pay/bean/Payment;)Lcom/jd/lib/cashier/sdk/pay/bean/syncevent/ClickBaiTiaoCouponItemEvent;", "", "toString", "()Ljava/lang/String;", "", "hashCode", "()I", "other", "", "equals", "(Ljava/lang/Object;)Z", "Lcom/jd/lib/cashier/sdk/pay/bean/Payment;", "getPayment", "<init>", "(Lcom/jd/lib/cashier/sdk/pay/bean/Payment;)V", "cashier_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes14.dex */
public final /* data */ class ClickBaiTiaoCouponItemEvent {
    @NotNull
    private final Payment payment;

    public ClickBaiTiaoCouponItemEvent(@NotNull Payment payment) {
        this.payment = payment;
    }

    public static /* synthetic */ ClickBaiTiaoCouponItemEvent copy$default(ClickBaiTiaoCouponItemEvent clickBaiTiaoCouponItemEvent, Payment payment, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            payment = clickBaiTiaoCouponItemEvent.payment;
        }
        return clickBaiTiaoCouponItemEvent.copy(payment);
    }

    @NotNull
    /* renamed from: component1  reason: from getter */
    public final Payment getPayment() {
        return this.payment;
    }

    @NotNull
    public final ClickBaiTiaoCouponItemEvent copy(@NotNull Payment payment) {
        return new ClickBaiTiaoCouponItemEvent(payment);
    }

    public boolean equals(@Nullable Object other) {
        if (this != other) {
            return (other instanceof ClickBaiTiaoCouponItemEvent) && Intrinsics.areEqual(this.payment, ((ClickBaiTiaoCouponItemEvent) other).payment);
        }
        return true;
    }

    @NotNull
    public final Payment getPayment() {
        return this.payment;
    }

    public int hashCode() {
        Payment payment = this.payment;
        if (payment != null) {
            return payment.hashCode();
        }
        return 0;
    }

    @NotNull
    public String toString() {
        return "ClickBaiTiaoCouponItemEvent(payment=" + this.payment + ")";
    }
}

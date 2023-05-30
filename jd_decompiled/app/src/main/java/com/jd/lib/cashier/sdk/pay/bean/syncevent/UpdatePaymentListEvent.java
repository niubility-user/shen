package com.jd.lib.cashier.sdk.pay.bean.syncevent;

import com.jd.lib.cashier.sdk.pay.bean.Payment;
import com.jd.viewkit.templates.view.helper.JDViewKitEventHelper;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\f\b\u0086\b\u0018\u00002\u00020\u0001B\u0019\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0002\u0012\u0006\u0010\t\u001a\u00020\u0005\u00a2\u0006\u0004\b\u0019\u0010\u001aJ\u0012\u0010\u0003\u001a\u0004\u0018\u00010\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u0003\u0010\u0004J\u0010\u0010\u0006\u001a\u00020\u0005H\u00c6\u0003\u00a2\u0006\u0004\b\u0006\u0010\u0007J&\u0010\n\u001a\u00020\u00002\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00022\b\b\u0002\u0010\t\u001a\u00020\u0005H\u00c6\u0001\u00a2\u0006\u0004\b\n\u0010\u000bJ\u0010\u0010\r\u001a\u00020\fH\u00d6\u0001\u00a2\u0006\u0004\b\r\u0010\u000eJ\u0010\u0010\u0010\u001a\u00020\u000fH\u00d6\u0001\u00a2\u0006\u0004\b\u0010\u0010\u0011J\u001a\u0010\u0013\u001a\u00020\u00052\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003\u00a2\u0006\u0004\b\u0013\u0010\u0014R\u0019\u0010\t\u001a\u00020\u00058\u0006@\u0006\u00a2\u0006\f\n\u0004\b\t\u0010\u0015\u001a\u0004\b\u0016\u0010\u0007R\u001b\u0010\b\u001a\u0004\u0018\u00010\u00028\u0006@\u0006\u00a2\u0006\f\n\u0004\b\b\u0010\u0017\u001a\u0004\b\u0018\u0010\u0004\u00a8\u0006\u001b"}, d2 = {"Lcom/jd/lib/cashier/sdk/pay/bean/syncevent/UpdatePaymentListEvent;", "", "Lcom/jd/lib/cashier/sdk/pay/bean/Payment;", "component1", "()Lcom/jd/lib/cashier/sdk/pay/bean/Payment;", "", "component2", "()Z", "payment", "defaultInit", JDViewKitEventHelper.ActionType_Copy, "(Lcom/jd/lib/cashier/sdk/pay/bean/Payment;Z)Lcom/jd/lib/cashier/sdk/pay/bean/syncevent/UpdatePaymentListEvent;", "", "toString", "()Ljava/lang/String;", "", "hashCode", "()I", "other", "equals", "(Ljava/lang/Object;)Z", "Z", "getDefaultInit", "Lcom/jd/lib/cashier/sdk/pay/bean/Payment;", "getPayment", "<init>", "(Lcom/jd/lib/cashier/sdk/pay/bean/Payment;Z)V", "cashier_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes14.dex */
public final /* data */ class UpdatePaymentListEvent {
    private final boolean defaultInit;
    @Nullable
    private final Payment payment;

    public UpdatePaymentListEvent(@Nullable Payment payment, boolean z) {
        this.payment = payment;
        this.defaultInit = z;
    }

    public static /* synthetic */ UpdatePaymentListEvent copy$default(UpdatePaymentListEvent updatePaymentListEvent, Payment payment, boolean z, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            payment = updatePaymentListEvent.payment;
        }
        if ((i2 & 2) != 0) {
            z = updatePaymentListEvent.defaultInit;
        }
        return updatePaymentListEvent.copy(payment, z);
    }

    @Nullable
    /* renamed from: component1  reason: from getter */
    public final Payment getPayment() {
        return this.payment;
    }

    /* renamed from: component2  reason: from getter */
    public final boolean getDefaultInit() {
        return this.defaultInit;
    }

    @NotNull
    public final UpdatePaymentListEvent copy(@Nullable Payment payment, boolean defaultInit) {
        return new UpdatePaymentListEvent(payment, defaultInit);
    }

    public boolean equals(@Nullable Object other) {
        if (this != other) {
            if (other instanceof UpdatePaymentListEvent) {
                UpdatePaymentListEvent updatePaymentListEvent = (UpdatePaymentListEvent) other;
                return Intrinsics.areEqual(this.payment, updatePaymentListEvent.payment) && this.defaultInit == updatePaymentListEvent.defaultInit;
            }
            return false;
        }
        return true;
    }

    public final boolean getDefaultInit() {
        return this.defaultInit;
    }

    @Nullable
    public final Payment getPayment() {
        return this.payment;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int hashCode() {
        Payment payment = this.payment;
        int hashCode = (payment != null ? payment.hashCode() : 0) * 31;
        boolean z = this.defaultInit;
        int i2 = z;
        if (z != 0) {
            i2 = 1;
        }
        return hashCode + i2;
    }

    @NotNull
    public String toString() {
        return "UpdatePaymentListEvent(payment=" + this.payment + ", defaultInit=" + this.defaultInit + ")";
    }
}

package com.jd.lib.cashier.sdk.pay.bean.syncevent;

import com.jd.lib.cashier.sdk.pay.bean.Payment;
import com.jd.lib.cashier.sdk.pay.bean.PlanFeeEntity;
import com.jd.viewkit.templates.view.helper.JDViewKitEventHelper;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\n\b\u0086\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\t\u001a\u00020\u0002\u0012\u0006\u0010\n\u001a\u00020\u0002\u0012\u0006\u0010\u000b\u001a\u00020\u0006\u00a2\u0006\u0004\b\u001d\u0010\u001eJ\u0010\u0010\u0003\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u0003\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u0005\u0010\u0004J\u0010\u0010\u0007\u001a\u00020\u0006H\u00c6\u0003\u00a2\u0006\u0004\b\u0007\u0010\bJ.\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\t\u001a\u00020\u00022\b\b\u0002\u0010\n\u001a\u00020\u00022\b\b\u0002\u0010\u000b\u001a\u00020\u0006H\u00c6\u0001\u00a2\u0006\u0004\b\f\u0010\rJ\u0010\u0010\u000f\u001a\u00020\u000eH\u00d6\u0001\u00a2\u0006\u0004\b\u000f\u0010\u0010J\u0010\u0010\u0012\u001a\u00020\u0011H\u00d6\u0001\u00a2\u0006\u0004\b\u0012\u0010\u0013J\u001a\u0010\u0016\u001a\u00020\u00152\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003\u00a2\u0006\u0004\b\u0016\u0010\u0017R\u0019\u0010\t\u001a\u00020\u00028\u0006@\u0006\u00a2\u0006\f\n\u0004\b\t\u0010\u0018\u001a\u0004\b\u0019\u0010\u0004R\u0019\u0010\n\u001a\u00020\u00028\u0006@\u0006\u00a2\u0006\f\n\u0004\b\n\u0010\u0018\u001a\u0004\b\u001a\u0010\u0004R\u0019\u0010\u000b\u001a\u00020\u00068\u0006@\u0006\u00a2\u0006\f\n\u0004\b\u000b\u0010\u001b\u001a\u0004\b\u001c\u0010\b\u00a8\u0006\u001f"}, d2 = {"Lcom/jd/lib/cashier/sdk/pay/bean/syncevent/ClickBaiTiaoPayPlanItemEvent;", "", "Lcom/jd/lib/cashier/sdk/pay/bean/PlanFeeEntity;", "component1", "()Lcom/jd/lib/cashier/sdk/pay/bean/PlanFeeEntity;", "component2", "Lcom/jd/lib/cashier/sdk/pay/bean/Payment;", "component3", "()Lcom/jd/lib/cashier/sdk/pay/bean/Payment;", "planFeeEntity", "lastPlanFeeEntity", "currentPayment", JDViewKitEventHelper.ActionType_Copy, "(Lcom/jd/lib/cashier/sdk/pay/bean/PlanFeeEntity;Lcom/jd/lib/cashier/sdk/pay/bean/PlanFeeEntity;Lcom/jd/lib/cashier/sdk/pay/bean/Payment;)Lcom/jd/lib/cashier/sdk/pay/bean/syncevent/ClickBaiTiaoPayPlanItemEvent;", "", "toString", "()Ljava/lang/String;", "", "hashCode", "()I", "other", "", "equals", "(Ljava/lang/Object;)Z", "Lcom/jd/lib/cashier/sdk/pay/bean/PlanFeeEntity;", "getPlanFeeEntity", "getLastPlanFeeEntity", "Lcom/jd/lib/cashier/sdk/pay/bean/Payment;", "getCurrentPayment", "<init>", "(Lcom/jd/lib/cashier/sdk/pay/bean/PlanFeeEntity;Lcom/jd/lib/cashier/sdk/pay/bean/PlanFeeEntity;Lcom/jd/lib/cashier/sdk/pay/bean/Payment;)V", "cashier_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes14.dex */
public final /* data */ class ClickBaiTiaoPayPlanItemEvent {
    @NotNull
    private final Payment currentPayment;
    @NotNull
    private final PlanFeeEntity lastPlanFeeEntity;
    @NotNull
    private final PlanFeeEntity planFeeEntity;

    public ClickBaiTiaoPayPlanItemEvent(@NotNull PlanFeeEntity planFeeEntity, @NotNull PlanFeeEntity planFeeEntity2, @NotNull Payment payment) {
        this.planFeeEntity = planFeeEntity;
        this.lastPlanFeeEntity = planFeeEntity2;
        this.currentPayment = payment;
    }

    public static /* synthetic */ ClickBaiTiaoPayPlanItemEvent copy$default(ClickBaiTiaoPayPlanItemEvent clickBaiTiaoPayPlanItemEvent, PlanFeeEntity planFeeEntity, PlanFeeEntity planFeeEntity2, Payment payment, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            planFeeEntity = clickBaiTiaoPayPlanItemEvent.planFeeEntity;
        }
        if ((i2 & 2) != 0) {
            planFeeEntity2 = clickBaiTiaoPayPlanItemEvent.lastPlanFeeEntity;
        }
        if ((i2 & 4) != 0) {
            payment = clickBaiTiaoPayPlanItemEvent.currentPayment;
        }
        return clickBaiTiaoPayPlanItemEvent.copy(planFeeEntity, planFeeEntity2, payment);
    }

    @NotNull
    /* renamed from: component1  reason: from getter */
    public final PlanFeeEntity getPlanFeeEntity() {
        return this.planFeeEntity;
    }

    @NotNull
    /* renamed from: component2  reason: from getter */
    public final PlanFeeEntity getLastPlanFeeEntity() {
        return this.lastPlanFeeEntity;
    }

    @NotNull
    /* renamed from: component3  reason: from getter */
    public final Payment getCurrentPayment() {
        return this.currentPayment;
    }

    @NotNull
    public final ClickBaiTiaoPayPlanItemEvent copy(@NotNull PlanFeeEntity planFeeEntity, @NotNull PlanFeeEntity lastPlanFeeEntity, @NotNull Payment currentPayment) {
        return new ClickBaiTiaoPayPlanItemEvent(planFeeEntity, lastPlanFeeEntity, currentPayment);
    }

    public boolean equals(@Nullable Object other) {
        if (this != other) {
            if (other instanceof ClickBaiTiaoPayPlanItemEvent) {
                ClickBaiTiaoPayPlanItemEvent clickBaiTiaoPayPlanItemEvent = (ClickBaiTiaoPayPlanItemEvent) other;
                return Intrinsics.areEqual(this.planFeeEntity, clickBaiTiaoPayPlanItemEvent.planFeeEntity) && Intrinsics.areEqual(this.lastPlanFeeEntity, clickBaiTiaoPayPlanItemEvent.lastPlanFeeEntity) && Intrinsics.areEqual(this.currentPayment, clickBaiTiaoPayPlanItemEvent.currentPayment);
            }
            return false;
        }
        return true;
    }

    @NotNull
    public final Payment getCurrentPayment() {
        return this.currentPayment;
    }

    @NotNull
    public final PlanFeeEntity getLastPlanFeeEntity() {
        return this.lastPlanFeeEntity;
    }

    @NotNull
    public final PlanFeeEntity getPlanFeeEntity() {
        return this.planFeeEntity;
    }

    public int hashCode() {
        PlanFeeEntity planFeeEntity = this.planFeeEntity;
        int hashCode = (planFeeEntity != null ? planFeeEntity.hashCode() : 0) * 31;
        PlanFeeEntity planFeeEntity2 = this.lastPlanFeeEntity;
        int hashCode2 = (hashCode + (planFeeEntity2 != null ? planFeeEntity2.hashCode() : 0)) * 31;
        Payment payment = this.currentPayment;
        return hashCode2 + (payment != null ? payment.hashCode() : 0);
    }

    @NotNull
    public String toString() {
        return "ClickBaiTiaoPayPlanItemEvent(planFeeEntity=" + this.planFeeEntity + ", lastPlanFeeEntity=" + this.lastPlanFeeEntity + ", currentPayment=" + this.currentPayment + ")";
    }
}

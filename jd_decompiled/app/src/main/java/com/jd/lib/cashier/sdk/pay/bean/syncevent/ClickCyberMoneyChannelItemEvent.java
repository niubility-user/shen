package com.jd.lib.cashier.sdk.pay.bean.syncevent;

import com.jd.lib.cashier.sdk.pay.dialog.e;
import com.jd.viewkit.templates.view.helper.JDViewKitEventHelper;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0007\b\u0086\b\u0018\u00002\u00020\u0001B\u0011\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0002\u00a2\u0006\u0004\b\u0014\u0010\u0015J\u0012\u0010\u0003\u001a\u0004\u0018\u00010\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u0003\u0010\u0004J\u001c\u0010\u0006\u001a\u00020\u00002\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0002H\u00c6\u0001\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u0010\u0010\t\u001a\u00020\bH\u00d6\u0001\u00a2\u0006\u0004\b\t\u0010\nJ\u0010\u0010\f\u001a\u00020\u000bH\u00d6\u0001\u00a2\u0006\u0004\b\f\u0010\rJ\u001a\u0010\u0010\u001a\u00020\u000f2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003\u00a2\u0006\u0004\b\u0010\u0010\u0011R\u001b\u0010\u0005\u001a\u0004\u0018\u00010\u00028\u0006@\u0006\u00a2\u0006\f\n\u0004\b\u0005\u0010\u0012\u001a\u0004\b\u0013\u0010\u0004\u00a8\u0006\u0016"}, d2 = {"Lcom/jd/lib/cashier/sdk/pay/bean/syncevent/ClickCyberMoneyChannelItemEvent;", "", "Lcom/jd/lib/cashier/sdk/pay/dialog/e;", "component1", "()Lcom/jd/lib/cashier/sdk/pay/dialog/e;", "couponEntity", JDViewKitEventHelper.ActionType_Copy, "(Lcom/jd/lib/cashier/sdk/pay/dialog/e;)Lcom/jd/lib/cashier/sdk/pay/bean/syncevent/ClickCyberMoneyChannelItemEvent;", "", "toString", "()Ljava/lang/String;", "", "hashCode", "()I", "other", "", "equals", "(Ljava/lang/Object;)Z", "Lcom/jd/lib/cashier/sdk/pay/dialog/e;", "getCouponEntity", "<init>", "(Lcom/jd/lib/cashier/sdk/pay/dialog/e;)V", "cashier_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes14.dex */
public final /* data */ class ClickCyberMoneyChannelItemEvent {
    @Nullable
    private final e couponEntity;

    public ClickCyberMoneyChannelItemEvent(@Nullable e eVar) {
        this.couponEntity = eVar;
    }

    public static /* synthetic */ ClickCyberMoneyChannelItemEvent copy$default(ClickCyberMoneyChannelItemEvent clickCyberMoneyChannelItemEvent, e eVar, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            eVar = clickCyberMoneyChannelItemEvent.couponEntity;
        }
        return clickCyberMoneyChannelItemEvent.copy(eVar);
    }

    @Nullable
    /* renamed from: component1  reason: from getter */
    public final e getCouponEntity() {
        return this.couponEntity;
    }

    @NotNull
    public final ClickCyberMoneyChannelItemEvent copy(@Nullable e couponEntity) {
        return new ClickCyberMoneyChannelItemEvent(couponEntity);
    }

    public boolean equals(@Nullable Object other) {
        if (this != other) {
            return (other instanceof ClickCyberMoneyChannelItemEvent) && Intrinsics.areEqual(this.couponEntity, ((ClickCyberMoneyChannelItemEvent) other).couponEntity);
        }
        return true;
    }

    @Nullable
    public final e getCouponEntity() {
        return this.couponEntity;
    }

    public int hashCode() {
        e eVar = this.couponEntity;
        if (eVar != null) {
            return eVar.hashCode();
        }
        return 0;
    }

    @NotNull
    public String toString() {
        return "ClickCyberMoneyChannelItemEvent(couponEntity=" + this.couponEntity + ")";
    }
}

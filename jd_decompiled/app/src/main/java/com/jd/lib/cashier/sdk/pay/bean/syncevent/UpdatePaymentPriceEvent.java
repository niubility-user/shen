package com.jd.lib.cashier.sdk.pay.bean.syncevent;

import com.jd.viewkit.templates.view.helper.JDViewKitEventHelper;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\b\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0006\u001a\u00020\u0002\u0012\u0006\u0010\u0007\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0015\u0010\u0016J\u0010\u0010\u0003\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u0003\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u0005\u0010\u0004J$\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0006\u001a\u00020\u00022\b\b\u0002\u0010\u0007\u001a\u00020\u0002H\u00c6\u0001\u00a2\u0006\u0004\b\b\u0010\tJ\u0010\u0010\n\u001a\u00020\u0002H\u00d6\u0001\u00a2\u0006\u0004\b\n\u0010\u0004J\u0010\u0010\f\u001a\u00020\u000bH\u00d6\u0001\u00a2\u0006\u0004\b\f\u0010\rJ\u001a\u0010\u0010\u001a\u00020\u000f2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003\u00a2\u0006\u0004\b\u0010\u0010\u0011R\u0019\u0010\u0007\u001a\u00020\u00028\u0006@\u0006\u00a2\u0006\f\n\u0004\b\u0007\u0010\u0012\u001a\u0004\b\u0013\u0010\u0004R\u0019\u0010\u0006\u001a\u00020\u00028\u0006@\u0006\u00a2\u0006\f\n\u0004\b\u0006\u0010\u0012\u001a\u0004\b\u0014\u0010\u0004\u00a8\u0006\u0017"}, d2 = {"Lcom/jd/lib/cashier/sdk/pay/bean/syncevent/UpdatePaymentPriceEvent;", "", "", "component1", "()Ljava/lang/String;", "component2", "unit", "price", JDViewKitEventHelper.ActionType_Copy, "(Ljava/lang/String;Ljava/lang/String;)Lcom/jd/lib/cashier/sdk/pay/bean/syncevent/UpdatePaymentPriceEvent;", "toString", "", "hashCode", "()I", "other", "", "equals", "(Ljava/lang/Object;)Z", "Ljava/lang/String;", "getPrice", "getUnit", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "cashier_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes14.dex */
public final /* data */ class UpdatePaymentPriceEvent {
    @NotNull
    private final String price;
    @NotNull
    private final String unit;

    public UpdatePaymentPriceEvent(@NotNull String str, @NotNull String str2) {
        this.unit = str;
        this.price = str2;
    }

    public static /* synthetic */ UpdatePaymentPriceEvent copy$default(UpdatePaymentPriceEvent updatePaymentPriceEvent, String str, String str2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = updatePaymentPriceEvent.unit;
        }
        if ((i2 & 2) != 0) {
            str2 = updatePaymentPriceEvent.price;
        }
        return updatePaymentPriceEvent.copy(str, str2);
    }

    @NotNull
    /* renamed from: component1  reason: from getter */
    public final String getUnit() {
        return this.unit;
    }

    @NotNull
    /* renamed from: component2  reason: from getter */
    public final String getPrice() {
        return this.price;
    }

    @NotNull
    public final UpdatePaymentPriceEvent copy(@NotNull String unit, @NotNull String price) {
        return new UpdatePaymentPriceEvent(unit, price);
    }

    public boolean equals(@Nullable Object other) {
        if (this != other) {
            if (other instanceof UpdatePaymentPriceEvent) {
                UpdatePaymentPriceEvent updatePaymentPriceEvent = (UpdatePaymentPriceEvent) other;
                return Intrinsics.areEqual(this.unit, updatePaymentPriceEvent.unit) && Intrinsics.areEqual(this.price, updatePaymentPriceEvent.price);
            }
            return false;
        }
        return true;
    }

    @NotNull
    public final String getPrice() {
        return this.price;
    }

    @NotNull
    public final String getUnit() {
        return this.unit;
    }

    public int hashCode() {
        String str = this.unit;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.price;
        return hashCode + (str2 != null ? str2.hashCode() : 0);
    }

    @NotNull
    public String toString() {
        return "UpdatePaymentPriceEvent(unit=" + this.unit + ", price=" + this.price + ")";
    }
}

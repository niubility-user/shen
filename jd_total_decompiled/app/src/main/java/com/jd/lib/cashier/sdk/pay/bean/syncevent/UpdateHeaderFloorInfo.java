package com.jd.lib.cashier.sdk.pay.bean.syncevent;

import com.jd.lib.cashier.sdk.pay.bean.TopPriceAnimationInfo;
import com.jd.lib.cashier.sdk.pay.bean.TopPriceMtaObject;
import com.jd.viewkit.templates.view.helper.JDViewKitEventHelper;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\t\b\u0086\b\u0018\u00002\u00020\u0001B\u001b\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\u0004\b\u001a\u0010\u001bJ\u0012\u0010\u0003\u001a\u0004\u0018\u00010\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u0003\u0010\u0004J\u0012\u0010\u0006\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003\u00a2\u0006\u0004\b\u0006\u0010\u0007J(\u0010\n\u001a\u00020\u00002\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0005H\u00c6\u0001\u00a2\u0006\u0004\b\n\u0010\u000bJ\u0010\u0010\r\u001a\u00020\fH\u00d6\u0001\u00a2\u0006\u0004\b\r\u0010\u000eJ\u0010\u0010\u0010\u001a\u00020\u000fH\u00d6\u0001\u00a2\u0006\u0004\b\u0010\u0010\u0011J\u001a\u0010\u0014\u001a\u00020\u00132\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003\u00a2\u0006\u0004\b\u0014\u0010\u0015R\u001b\u0010\t\u001a\u0004\u0018\u00010\u00058\u0006@\u0006\u00a2\u0006\f\n\u0004\b\t\u0010\u0016\u001a\u0004\b\u0017\u0010\u0007R\u001b\u0010\b\u001a\u0004\u0018\u00010\u00028\u0006@\u0006\u00a2\u0006\f\n\u0004\b\b\u0010\u0018\u001a\u0004\b\u0019\u0010\u0004\u00a8\u0006\u001c"}, d2 = {"Lcom/jd/lib/cashier/sdk/pay/bean/syncevent/UpdateHeaderFloorInfo;", "", "Lcom/jd/lib/cashier/sdk/pay/bean/TopPriceAnimationInfo;", "component1", "()Lcom/jd/lib/cashier/sdk/pay/bean/TopPriceAnimationInfo;", "Lcom/jd/lib/cashier/sdk/pay/bean/TopPriceMtaObject;", "component2", "()Lcom/jd/lib/cashier/sdk/pay/bean/TopPriceMtaObject;", "topPriceAnimationInfo", "topPriceMtaObject", JDViewKitEventHelper.ActionType_Copy, "(Lcom/jd/lib/cashier/sdk/pay/bean/TopPriceAnimationInfo;Lcom/jd/lib/cashier/sdk/pay/bean/TopPriceMtaObject;)Lcom/jd/lib/cashier/sdk/pay/bean/syncevent/UpdateHeaderFloorInfo;", "", "toString", "()Ljava/lang/String;", "", "hashCode", "()I", "other", "", "equals", "(Ljava/lang/Object;)Z", "Lcom/jd/lib/cashier/sdk/pay/bean/TopPriceMtaObject;", "getTopPriceMtaObject", "Lcom/jd/lib/cashier/sdk/pay/bean/TopPriceAnimationInfo;", "getTopPriceAnimationInfo", "<init>", "(Lcom/jd/lib/cashier/sdk/pay/bean/TopPriceAnimationInfo;Lcom/jd/lib/cashier/sdk/pay/bean/TopPriceMtaObject;)V", "cashier_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes14.dex */
public final /* data */ class UpdateHeaderFloorInfo {
    @Nullable
    private final TopPriceAnimationInfo topPriceAnimationInfo;
    @Nullable
    private final TopPriceMtaObject topPriceMtaObject;

    public UpdateHeaderFloorInfo(@Nullable TopPriceAnimationInfo topPriceAnimationInfo, @Nullable TopPriceMtaObject topPriceMtaObject) {
        this.topPriceAnimationInfo = topPriceAnimationInfo;
        this.topPriceMtaObject = topPriceMtaObject;
    }

    public static /* synthetic */ UpdateHeaderFloorInfo copy$default(UpdateHeaderFloorInfo updateHeaderFloorInfo, TopPriceAnimationInfo topPriceAnimationInfo, TopPriceMtaObject topPriceMtaObject, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            topPriceAnimationInfo = updateHeaderFloorInfo.topPriceAnimationInfo;
        }
        if ((i2 & 2) != 0) {
            topPriceMtaObject = updateHeaderFloorInfo.topPriceMtaObject;
        }
        return updateHeaderFloorInfo.copy(topPriceAnimationInfo, topPriceMtaObject);
    }

    @Nullable
    /* renamed from: component1  reason: from getter */
    public final TopPriceAnimationInfo getTopPriceAnimationInfo() {
        return this.topPriceAnimationInfo;
    }

    @Nullable
    /* renamed from: component2  reason: from getter */
    public final TopPriceMtaObject getTopPriceMtaObject() {
        return this.topPriceMtaObject;
    }

    @NotNull
    public final UpdateHeaderFloorInfo copy(@Nullable TopPriceAnimationInfo topPriceAnimationInfo, @Nullable TopPriceMtaObject topPriceMtaObject) {
        return new UpdateHeaderFloorInfo(topPriceAnimationInfo, topPriceMtaObject);
    }

    public boolean equals(@Nullable Object other) {
        if (this != other) {
            if (other instanceof UpdateHeaderFloorInfo) {
                UpdateHeaderFloorInfo updateHeaderFloorInfo = (UpdateHeaderFloorInfo) other;
                return Intrinsics.areEqual(this.topPriceAnimationInfo, updateHeaderFloorInfo.topPriceAnimationInfo) && Intrinsics.areEqual(this.topPriceMtaObject, updateHeaderFloorInfo.topPriceMtaObject);
            }
            return false;
        }
        return true;
    }

    @Nullable
    public final TopPriceAnimationInfo getTopPriceAnimationInfo() {
        return this.topPriceAnimationInfo;
    }

    @Nullable
    public final TopPriceMtaObject getTopPriceMtaObject() {
        return this.topPriceMtaObject;
    }

    public int hashCode() {
        TopPriceAnimationInfo topPriceAnimationInfo = this.topPriceAnimationInfo;
        int hashCode = (topPriceAnimationInfo != null ? topPriceAnimationInfo.hashCode() : 0) * 31;
        TopPriceMtaObject topPriceMtaObject = this.topPriceMtaObject;
        return hashCode + (topPriceMtaObject != null ? topPriceMtaObject.hashCode() : 0);
    }

    @NotNull
    public String toString() {
        return "UpdateHeaderFloorInfo(topPriceAnimationInfo=" + this.topPriceAnimationInfo + ", topPriceMtaObject=" + this.topPriceMtaObject + ")";
    }
}

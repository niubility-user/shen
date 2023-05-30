package com.jd.lib.cashier.sdk.pay.bean.syncevent;

import com.jd.lib.cashier.sdk.h.g.x;
import com.jd.lib.cashier.sdk.pay.bean.coupon.CommonCouponEntity;
import com.jd.viewkit.templates.view.helper.JDViewKitEventHelper;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u000b\b\u0086\b\u0018\u00002\u00020\u0001B\u001b\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\u0004\b\u001c\u0010\u001dJ\u0012\u0010\u0003\u001a\u0004\u0018\u00010\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u0003\u0010\u0004J\u0012\u0010\u0006\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003\u00a2\u0006\u0004\b\u0006\u0010\u0007J(\u0010\n\u001a\u00020\u00002\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0005H\u00c6\u0001\u00a2\u0006\u0004\b\n\u0010\u000bJ\u0010\u0010\r\u001a\u00020\fH\u00d6\u0001\u00a2\u0006\u0004\b\r\u0010\u000eJ\u0010\u0010\u0010\u001a\u00020\u000fH\u00d6\u0001\u00a2\u0006\u0004\b\u0010\u0010\u0011J\u001a\u0010\u0014\u001a\u00020\u00132\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003\u00a2\u0006\u0004\b\u0014\u0010\u0015R\u001b\u0010\b\u001a\u0004\u0018\u00010\u00028\u0006@\u0006\u00a2\u0006\f\n\u0004\b\b\u0010\u0016\u001a\u0004\b\u0017\u0010\u0004R$\u0010\t\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\t\u0010\u0018\u001a\u0004\b\u0019\u0010\u0007\"\u0004\b\u001a\u0010\u001b\u00a8\u0006\u001e"}, d2 = {"Lcom/jd/lib/cashier/sdk/pay/bean/syncevent/SendBankCouponDataEvent;", "", "Lcom/jd/lib/cashier/sdk/h/g/x;", "component1", "()Lcom/jd/lib/cashier/sdk/h/g/x;", "Lcom/jd/lib/cashier/sdk/pay/bean/coupon/CommonCouponEntity;", "component2", "()Lcom/jd/lib/cashier/sdk/pay/bean/coupon/CommonCouponEntity;", "channelTemplate", "recommendBankCoupon", JDViewKitEventHelper.ActionType_Copy, "(Lcom/jd/lib/cashier/sdk/h/g/x;Lcom/jd/lib/cashier/sdk/pay/bean/coupon/CommonCouponEntity;)Lcom/jd/lib/cashier/sdk/pay/bean/syncevent/SendBankCouponDataEvent;", "", "toString", "()Ljava/lang/String;", "", "hashCode", "()I", "other", "", "equals", "(Ljava/lang/Object;)Z", "Lcom/jd/lib/cashier/sdk/h/g/x;", "getChannelTemplate", "Lcom/jd/lib/cashier/sdk/pay/bean/coupon/CommonCouponEntity;", "getRecommendBankCoupon", "setRecommendBankCoupon", "(Lcom/jd/lib/cashier/sdk/pay/bean/coupon/CommonCouponEntity;)V", "<init>", "(Lcom/jd/lib/cashier/sdk/h/g/x;Lcom/jd/lib/cashier/sdk/pay/bean/coupon/CommonCouponEntity;)V", "cashier_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes14.dex */
public final /* data */ class SendBankCouponDataEvent {
    @Nullable
    private final x channelTemplate;
    @Nullable
    private CommonCouponEntity recommendBankCoupon;

    public SendBankCouponDataEvent(@Nullable x xVar, @Nullable CommonCouponEntity commonCouponEntity) {
        this.channelTemplate = xVar;
        this.recommendBankCoupon = commonCouponEntity;
    }

    public static /* synthetic */ SendBankCouponDataEvent copy$default(SendBankCouponDataEvent sendBankCouponDataEvent, x xVar, CommonCouponEntity commonCouponEntity, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            xVar = sendBankCouponDataEvent.channelTemplate;
        }
        if ((i2 & 2) != 0) {
            commonCouponEntity = sendBankCouponDataEvent.recommendBankCoupon;
        }
        return sendBankCouponDataEvent.copy(xVar, commonCouponEntity);
    }

    @Nullable
    /* renamed from: component1  reason: from getter */
    public final x getChannelTemplate() {
        return this.channelTemplate;
    }

    @Nullable
    /* renamed from: component2  reason: from getter */
    public final CommonCouponEntity getRecommendBankCoupon() {
        return this.recommendBankCoupon;
    }

    @NotNull
    public final SendBankCouponDataEvent copy(@Nullable x channelTemplate, @Nullable CommonCouponEntity recommendBankCoupon) {
        return new SendBankCouponDataEvent(channelTemplate, recommendBankCoupon);
    }

    public boolean equals(@Nullable Object other) {
        if (this != other) {
            if (other instanceof SendBankCouponDataEvent) {
                SendBankCouponDataEvent sendBankCouponDataEvent = (SendBankCouponDataEvent) other;
                return Intrinsics.areEqual(this.channelTemplate, sendBankCouponDataEvent.channelTemplate) && Intrinsics.areEqual(this.recommendBankCoupon, sendBankCouponDataEvent.recommendBankCoupon);
            }
            return false;
        }
        return true;
    }

    @Nullable
    public final x getChannelTemplate() {
        return this.channelTemplate;
    }

    @Nullable
    public final CommonCouponEntity getRecommendBankCoupon() {
        return this.recommendBankCoupon;
    }

    public int hashCode() {
        x xVar = this.channelTemplate;
        int hashCode = (xVar != null ? xVar.hashCode() : 0) * 31;
        CommonCouponEntity commonCouponEntity = this.recommendBankCoupon;
        return hashCode + (commonCouponEntity != null ? commonCouponEntity.hashCode() : 0);
    }

    public final void setRecommendBankCoupon(@Nullable CommonCouponEntity commonCouponEntity) {
        this.recommendBankCoupon = commonCouponEntity;
    }

    @NotNull
    public String toString() {
        return "SendBankCouponDataEvent(channelTemplate=" + this.channelTemplate + ", recommendBankCoupon=" + this.recommendBankCoupon + ")";
    }
}

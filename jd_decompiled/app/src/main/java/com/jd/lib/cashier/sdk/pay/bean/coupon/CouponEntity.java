package com.jd.lib.cashier.sdk.pay.bean.coupon;

import android.text.TextUtils;
import com.jd.lib.cashier.sdk.pay.bean.TopPriceAnimationInfo;
import com.jd.lib.cashier.sdk.pay.dialog.e;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0015\n\u0002\u0018\u0002\n\u0002\b\u0018\u0018\u00002\u00020\u00012\u00020\u0002B\t\b\u0016\u00a2\u0006\u0004\b7\u00108J\u000f\u0010\u0004\u001a\u00020\u0003H\u0016\u00a2\u0006\u0004\b\u0004\u0010\u0005J\u001a\u0010\t\u001a\u00020\b2\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006H\u0096\u0002\u00a2\u0006\u0004\b\t\u0010\nJ\u000f\u0010\f\u001a\u00020\u000bH\u0016\u00a2\u0006\u0004\b\f\u0010\rJ\r\u0010\u000e\u001a\u00020\u0001\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\r\u0010\u0010\u001a\u00020\u0001\u00a2\u0006\u0004\b\u0010\u0010\u000fR\u0016\u0010\u0012\u001a\u00020\u00038V@\u0016X\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0011\u0010\u0005R\u0016\u0010\u0014\u001a\u00020\u00038V@\u0016X\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0013\u0010\u0005R\"\u0010\u0015\u001a\u00020\b8\u0016@\u0016X\u0096\u000e\u00a2\u0006\u0012\n\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR*\u0010\u001c\u001a\u00020\b2\u0006\u0010\u001b\u001a\u00020\b8\u0016@VX\u0096\u000e\u00a2\u0006\u0012\n\u0004\b\u001c\u0010\u0016\u001a\u0004\b\u001d\u0010\u0018\"\u0004\b\u001e\u0010\u001aR\u0016\u0010 \u001a\u00020\u00038V@\u0016X\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b\u001f\u0010\u0005R\u0018\u0010$\u001a\u0004\u0018\u00010!8V@\u0016X\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b\"\u0010#R\u0016\u0010&\u001a\u00020\u00038V@\u0016X\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b%\u0010\u0005R\u0016\u0010(\u001a\u00020\u00038V@\u0016X\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b'\u0010\u0005R\u0016\u0010*\u001a\u00020\u00038V@\u0016X\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b)\u0010\u0005R\"\u0010+\u001a\u00020\u000b8\u0016@\u0016X\u0096\u000e\u00a2\u0006\u0012\n\u0004\b+\u0010,\u001a\u0004\b-\u0010\r\"\u0004\b.\u0010/R$\u00100\u001a\u0004\u0018\u00010!8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b0\u00101\u001a\u0004\b2\u0010#\"\u0004\b3\u00104R\u0016\u00106\u001a\u00020\u00038V@\u0016X\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b5\u0010\u0005\u00a8\u00069"}, d2 = {"Lcom/jd/lib/cashier/sdk/pay/bean/coupon/CouponEntity;", "Lcom/jd/lib/cashier/sdk/pay/bean/coupon/CouponEntityRequest;", "Lcom/jd/lib/cashier/sdk/pay/dialog/e;", "", "toString", "()Ljava/lang/String;", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "convertToCreditPayCouponEntityRequest", "()Lcom/jd/lib/cashier/sdk/pay/bean/coupon/CouponEntityRequest;", "convertToCouponEntityRequest", "getExtraInfo", "extraInfo", "getSubtitle", "subtitle", "checked", "Z", "getChecked", "()Z", "setChecked", "(Z)V", "value", "canUse", "getCanUse", "setCanUse", "getTitleName", "titleName", "Lcom/jd/lib/cashier/sdk/pay/bean/TopPriceAnimationInfo;", "getPriceAnimationInfo", "()Lcom/jd/lib/cashier/sdk/pay/bean/TopPriceAnimationInfo;", "priceAnimationInfo", "getCouponAmount", "couponAmount", "getCouponTypeName", "couponTypeName", "getCouponEntityId", "couponEntityId", "viewType", "I", "getViewType", "setViewType", "(I)V", "topPriceAnimationInfo", "Lcom/jd/lib/cashier/sdk/pay/bean/TopPriceAnimationInfo;", "getTopPriceAnimationInfo", "setTopPriceAnimationInfo", "(Lcom/jd/lib/cashier/sdk/pay/bean/TopPriceAnimationInfo;)V", "getCouponTypeForMta", "couponTypeForMta", "<init>", "()V", "cashier_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes14.dex */
public final class CouponEntity extends CouponEntityRequest implements e {
    private boolean canUse;
    private boolean checked;
    @Nullable
    private TopPriceAnimationInfo topPriceAnimationInfo;
    private int viewType;

    public CouponEntity() {
        this.viewType = getCanUse() ? 1 : 2;
    }

    @NotNull
    public final CouponEntityRequest convertToCouponEntityRequest() {
        CouponEntityRequest couponEntityRequest = new CouponEntityRequest();
        couponEntityRequest.setCouponId(getCouponId());
        couponEntityRequest.setActivityId(getActivityId());
        couponEntityRequest.setCouponType(getCouponType());
        couponEntityRequest.setPlans(getPlans());
        couponEntityRequest.setExtInfo(getExtInfo());
        couponEntityRequest.setDefaultPlan(getDefaultPlan());
        couponEntityRequest.setCanUse(getCanUse());
        couponEntityRequest.setCouponDesc(getCouponDesc());
        couponEntityRequest.setTermOfValidity(getTermOfValidity());
        couponEntityRequest.setCouponInfo(getCouponInfo());
        couponEntityRequest.setCouponTypeDesc(getCouponTypeDesc());
        couponEntityRequest.setCutOffType(getCutOffType());
        if (TextUtils.equals("combination", getCouponId())) {
            couponEntityRequest.setCombinationCouponList(getCombinationCouponList());
        }
        couponEntityRequest.setActUuId(getActUuId());
        return couponEntityRequest;
    }

    @NotNull
    public final CouponEntityRequest convertToCreditPayCouponEntityRequest() {
        CouponEntityRequest couponEntityRequest = new CouponEntityRequest();
        couponEntityRequest.setCouponId(getCouponId());
        couponEntityRequest.setActivityId(getActivityId());
        couponEntityRequest.setCouponType(getCouponType());
        couponEntityRequest.setPlans(getPlans());
        couponEntityRequest.setExtInfo(getExtInfo());
        couponEntityRequest.setDefaultPlan(getDefaultPlan());
        couponEntityRequest.setCanUse(getCanUse());
        couponEntityRequest.setCouponDesc(getCouponDesc());
        couponEntityRequest.setTermOfValidity(getTermOfValidity());
        couponEntityRequest.setCouponInfo(getCouponInfo());
        couponEntityRequest.setCouponTypeDesc(getCouponTypeDesc());
        couponEntityRequest.setCutOffType(getCutOffType());
        return couponEntityRequest;
    }

    public boolean equals(@Nullable Object other) {
        boolean startsWith$default;
        if (this == other) {
            return true;
        }
        if (other instanceof CouponEntity) {
            String couponId = getCouponId();
            if (couponId != null) {
                startsWith$default = StringsKt__StringsJVMKt.startsWith$default(couponId, "nothing", false, 2, null);
                if (startsWith$default) {
                    return Intrinsics.areEqual(getActivityId(), ((CouponEntity) other).getActivityId());
                }
            }
            return Intrinsics.areEqual(getCouponId(), ((CouponEntity) other).getCouponId());
        }
        return false;
    }

    @Override // com.jd.lib.cashier.sdk.pay.bean.coupon.CouponEntityRequest
    public boolean getCanUse() {
        return this.canUse;
    }

    @Override // com.jd.lib.cashier.sdk.pay.dialog.e
    public boolean getChecked() {
        return this.checked;
    }

    @Override // com.jd.lib.cashier.sdk.pay.dialog.e
    @NotNull
    public String getCouponAmount() {
        return "";
    }

    @Override // com.jd.lib.cashier.sdk.pay.dialog.e
    @NotNull
    /* renamed from: getCouponEntityId */
    public String getPayMarketingUUID() {
        String couponId = getCouponId();
        return couponId != null ? couponId : "";
    }

    @Override // com.jd.lib.cashier.sdk.pay.dialog.e
    @NotNull
    /* renamed from: getCouponTypeForMta */
    public String getCutOffType() {
        String cutOffType = getCutOffType();
        return cutOffType != null ? cutOffType : "0";
    }

    @Override // com.jd.lib.cashier.sdk.pay.dialog.e
    @NotNull
    public String getCouponTypeName() {
        String couponTypeDesc = getCouponTypeDesc();
        return couponTypeDesc != null ? couponTypeDesc : "";
    }

    @Override // com.jd.lib.cashier.sdk.pay.dialog.e
    @NotNull
    /* renamed from: getExtraInfo */
    public String getUsingScene() {
        String couponDesc = getCouponDesc();
        return couponDesc != null ? couponDesc : "";
    }

    @Override // com.jd.lib.cashier.sdk.pay.dialog.e
    @Nullable
    /* renamed from: getPriceAnimationInfo  reason: from getter */
    public TopPriceAnimationInfo getTopPriceAnimationInfo() {
        return this.topPriceAnimationInfo;
    }

    @Override // com.jd.lib.cashier.sdk.pay.dialog.e
    @NotNull
    public String getSubtitle() {
        String termOfValidity = getTermOfValidity();
        return termOfValidity != null ? termOfValidity : "";
    }

    @Override // com.jd.lib.cashier.sdk.pay.dialog.e
    @NotNull
    public String getTitleName() {
        String couponInfo = getCouponInfo();
        return couponInfo != null ? couponInfo : "";
    }

    @Nullable
    public final TopPriceAnimationInfo getTopPriceAnimationInfo() {
        return this.topPriceAnimationInfo;
    }

    @Override // com.jd.lib.cashier.sdk.pay.dialog.e
    public int getViewType() {
        return this.viewType;
    }

    public int hashCode() {
        boolean startsWith$default;
        String couponId = getCouponId();
        if (couponId != null) {
            startsWith$default = StringsKt__StringsJVMKt.startsWith$default(couponId, "nothing", false, 2, null);
            if (startsWith$default) {
                String activityId = getActivityId();
                if (activityId != null) {
                    return activityId.hashCode();
                }
                return 0;
            }
        }
        String couponId2 = getCouponId();
        if (couponId2 != null) {
            return couponId2.hashCode();
        }
        return 0;
    }

    @Override // com.jd.lib.cashier.sdk.pay.bean.coupon.CouponEntityRequest
    public void setCanUse(boolean z) {
        setViewType(z ? 1 : 2);
        this.canUse = z;
    }

    @Override // com.jd.lib.cashier.sdk.pay.dialog.e
    public void setChecked(boolean z) {
        this.checked = z;
    }

    public final void setTopPriceAnimationInfo(@Nullable TopPriceAnimationInfo topPriceAnimationInfo) {
        this.topPriceAnimationInfo = topPriceAnimationInfo;
    }

    public void setViewType(int i2) {
        this.viewType = i2;
    }

    @NotNull
    public String toString() {
        return "CouponEntity(couponId='" + getCouponId() + "', activityId='" + getActivityId() + "', couponType='" + getCouponType() + "', plans=" + getPlans() + ", defaultPlan='" + getDefaultPlan() + "', canUse=" + getCanUse() + ", termOfValidity='" + getTermOfValidity() + "', couponInfo='" + getCouponInfo() + "', couponDesc='" + getCouponDesc() + "', couponTypeDesc='" + getCouponTypeDesc() + "', viewType=" + getViewType() + ", selected=" + getChecked() + ')';
    }
}

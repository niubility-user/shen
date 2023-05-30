package com.jd.lib.cashier.sdk.pay.bean.coupon;

import com.jd.cashier.app.jdlibcutter.protocol.pair.PairKey;
import com.jd.lib.cashier.sdk.core.model.ICheckNullObj;
import com.jd.lib.cashier.sdk.pay.bean.TopPriceAnimationInfo;
import com.jd.lib.cashier.sdk.pay.dialog.e;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b>\u0018\u00002\u00020\u00012\u00020\u0002B\t\b\u0016\u00a2\u0006\u0004\bV\u0010\nJ\u001a\u0010\u0006\u001a\u00020\u00052\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003H\u0096\u0002\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u000f\u0010\t\u001a\u00020\bH\u0016\u00a2\u0006\u0004\b\t\u0010\nJ\u000f\u0010\f\u001a\u00020\u000bH\u0016\u00a2\u0006\u0004\b\f\u0010\rJ\u000f\u0010\u000f\u001a\u00020\u000eH\u0016\u00a2\u0006\u0004\b\u000f\u0010\u0010R$\u0010\u0011\u001a\u0004\u0018\u00010\u000e8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u0013\u0010\u0010\"\u0004\b\u0014\u0010\u0015R\"\u0010\u0016\u001a\u00020\u000e8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0016\u0010\u0012\u001a\u0004\b\u0017\u0010\u0010\"\u0004\b\u0018\u0010\u0015R$\u0010\u001a\u001a\u0004\u0018\u00010\u00198\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u001a\u0010\u001b\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR\u0016\u0010!\u001a\u00020\u000e8V@\u0016X\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b \u0010\u0010R$\u0010\"\u001a\u0004\u0018\u00010\u000e8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\"\u0010\u0012\u001a\u0004\b#\u0010\u0010\"\u0004\b$\u0010\u0015R\"\u0010%\u001a\u00020\u000e8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b%\u0010\u0012\u001a\u0004\b&\u0010\u0010\"\u0004\b'\u0010\u0015R\"\u0010(\u001a\u00020\u000e8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b(\u0010\u0012\u001a\u0004\b)\u0010\u0010\"\u0004\b*\u0010\u0015R\u0016\u0010,\u001a\u00020\u000e8V@\u0016X\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b+\u0010\u0010R\"\u0010-\u001a\u00020\u000e8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b-\u0010\u0012\u001a\u0004\b.\u0010\u0010\"\u0004\b/\u0010\u0015R\"\u00100\u001a\u00020\u000e8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b0\u0010\u0012\u001a\u0004\b1\u0010\u0010\"\u0004\b2\u0010\u0015R\u0018\u00104\u001a\u0004\u0018\u00010\u00198V@\u0016X\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b3\u0010\u001dR\u0016\u00106\u001a\u00020\u000e8V@\u0016X\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b5\u0010\u0010R\"\u00107\u001a\u00020\u000e8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b7\u0010\u0012\u001a\u0004\b8\u0010\u0010\"\u0004\b9\u0010\u0015R$\u0010:\u001a\u0004\u0018\u00010\u000e8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b:\u0010\u0012\u001a\u0004\b;\u0010\u0010\"\u0004\b<\u0010\u0015R\"\u0010=\u001a\u00020\u000e8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b=\u0010\u0012\u001a\u0004\b>\u0010\u0010\"\u0004\b?\u0010\u0015R\"\u0010@\u001a\u00020\u000e8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b@\u0010\u0012\u001a\u0004\bA\u0010\u0010\"\u0004\bB\u0010\u0015R\"\u0010C\u001a\u00020\u000b8\u0016@\u0016X\u0096\u000e\u00a2\u0006\u0012\n\u0004\bC\u0010D\u001a\u0004\bE\u0010\r\"\u0004\bF\u0010GR\"\u0010H\u001a\u00020\u00058\u0016@\u0016X\u0096\u000e\u00a2\u0006\u0012\n\u0004\bH\u0010I\u001a\u0004\bJ\u0010K\"\u0004\bL\u0010MR\u0016\u0010O\u001a\u00020\u000e8V@\u0016X\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\bN\u0010\u0010R\u0016\u0010Q\u001a\u00020\u000e8V@\u0016X\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\bP\u0010\u0010R\u0016\u0010S\u001a\u00020\u000e8V@\u0016X\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\bR\u0010\u0010R\u0016\u0010U\u001a\u00020\u000e8V@\u0016X\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\bT\u0010\u0010\u00a8\u0006W"}, d2 = {"Lcom/jd/lib/cashier/sdk/pay/bean/coupon/CommonCouponEntity;", "Lcom/jd/lib/cashier/sdk/pay/dialog/e;", "Lcom/jd/lib/cashier/sdk/core/model/ICheckNullObj;", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "", "checkNullObjAndInit", "()V", "", "hashCode", "()I", "", "toString", "()Ljava/lang/String;", "showAmountStr", "Ljava/lang/String;", "getShowAmountStr", "setShowAmountStr", "(Ljava/lang/String;)V", "promotionDesc", "getPromotionDesc", "setPromotionDesc", "Lcom/jd/lib/cashier/sdk/pay/bean/TopPriceAnimationInfo;", "topPriceAnimationInfo", "Lcom/jd/lib/cashier/sdk/pay/bean/TopPriceAnimationInfo;", "getTopPriceAnimationInfo", "()Lcom/jd/lib/cashier/sdk/pay/bean/TopPriceAnimationInfo;", "setTopPriceAnimationInfo", "(Lcom/jd/lib/cashier/sdk/pay/bean/TopPriceAnimationInfo;)V", "getCouponEntityId", "couponEntityId", "termOfValidity", "getTermOfValidity", "setTermOfValidity", "endDate", "getEndDate", "setEndDate", "beginDate", "getBeginDate", "setBeginDate", "getCouponAmount", "couponAmount", PairKey.PAY_MARKETING_UUID, "getPayMarketingUUID", "setPayMarketingUUID", "usingScene", "getUsingScene", "setUsingScene", "getPriceAnimationInfo", "priceAnimationInfo", "getCouponTypeName", "couponTypeName", "couponTypeDesc", "getCouponTypeDesc", "setCouponTypeDesc", "showAmount", "getShowAmount", "setShowAmount", "cutOffType", "getCutOffType", "setCutOffType", PairKey.PRIZE_ID, "getPrizeId", "setPrizeId", "viewType", "I", "getViewType", "setViewType", "(I)V", "checked", "Z", "getChecked", "()Z", "setChecked", "(Z)V", "getSubtitle", "subtitle", "getCouponTypeForMta", "couponTypeForMta", "getExtraInfo", "extraInfo", "getTitleName", "titleName", "<init>", "cashier_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes14.dex */
public final class CommonCouponEntity implements e, ICheckNullObj {
    private boolean checked;
    @Nullable
    private TopPriceAnimationInfo topPriceAnimationInfo;
    private int viewType;
    @NotNull
    private String payMarketingUUID = "";
    @NotNull
    private String prizeId = "";
    @Nullable
    private String termOfValidity = "";
    @Nullable
    private String showAmountStr = "";
    @Nullable
    private String showAmount = "";
    @NotNull
    private String couponTypeDesc = "";
    @NotNull
    private String promotionDesc = "";
    @NotNull
    private String beginDate = "";
    @NotNull
    private String endDate = "";
    @NotNull
    private String usingScene = "";
    @NotNull
    private String cutOffType = "0";

    @Override // com.jd.lib.cashier.sdk.core.model.ICheckNullObj
    public void checkNullObjAndInit() {
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof CommonCouponEntity) && !(Intrinsics.areEqual(this.payMarketingUUID, ((CommonCouponEntity) other).payMarketingUUID) ^ true);
    }

    @NotNull
    public final String getBeginDate() {
        return this.beginDate;
    }

    @Override // com.jd.lib.cashier.sdk.pay.dialog.e
    public boolean getChecked() {
        return this.checked;
    }

    @Override // com.jd.lib.cashier.sdk.pay.dialog.e
    @NotNull
    public String getCouponAmount() {
        String str = this.showAmountStr;
        if (str == null) {
            str = this.showAmount;
        }
        return str != null ? str : "";
    }

    @Override // com.jd.lib.cashier.sdk.pay.dialog.e
    @NotNull
    /* renamed from: getCouponEntityId  reason: from getter */
    public String getPayMarketingUUID() {
        return this.payMarketingUUID;
    }

    @NotNull
    public final String getCouponTypeDesc() {
        return this.couponTypeDesc;
    }

    @Override // com.jd.lib.cashier.sdk.pay.dialog.e
    @NotNull
    /* renamed from: getCouponTypeForMta  reason: from getter */
    public String getCutOffType() {
        return this.cutOffType;
    }

    @Override // com.jd.lib.cashier.sdk.pay.dialog.e
    @NotNull
    public String getCouponTypeName() {
        return this.couponTypeDesc;
    }

    @NotNull
    public final String getCutOffType() {
        return this.cutOffType;
    }

    @NotNull
    public final String getEndDate() {
        return this.endDate;
    }

    @Override // com.jd.lib.cashier.sdk.pay.dialog.e
    @NotNull
    /* renamed from: getExtraInfo  reason: from getter */
    public String getUsingScene() {
        return this.usingScene;
    }

    @NotNull
    public final String getPayMarketingUUID() {
        return this.payMarketingUUID;
    }

    @Override // com.jd.lib.cashier.sdk.pay.dialog.e
    @Nullable
    /* renamed from: getPriceAnimationInfo  reason: from getter */
    public TopPriceAnimationInfo getTopPriceAnimationInfo() {
        return this.topPriceAnimationInfo;
    }

    @NotNull
    public final String getPrizeId() {
        return this.prizeId;
    }

    @NotNull
    public final String getPromotionDesc() {
        return this.promotionDesc;
    }

    @Nullable
    public final String getShowAmount() {
        return this.showAmount;
    }

    @Nullable
    public final String getShowAmountStr() {
        return this.showAmountStr;
    }

    @Override // com.jd.lib.cashier.sdk.pay.dialog.e
    @NotNull
    public String getSubtitle() {
        String str = this.termOfValidity;
        return str != null ? str : "";
    }

    @Nullable
    public final String getTermOfValidity() {
        return this.termOfValidity;
    }

    @Override // com.jd.lib.cashier.sdk.pay.dialog.e
    @NotNull
    public String getTitleName() {
        return this.promotionDesc;
    }

    @Nullable
    public final TopPriceAnimationInfo getTopPriceAnimationInfo() {
        return this.topPriceAnimationInfo;
    }

    @NotNull
    public final String getUsingScene() {
        return this.usingScene;
    }

    @Override // com.jd.lib.cashier.sdk.pay.dialog.e
    public int getViewType() {
        return this.viewType;
    }

    public int hashCode() {
        return this.payMarketingUUID.hashCode();
    }

    public final void setBeginDate(@NotNull String str) {
        this.beginDate = str;
    }

    @Override // com.jd.lib.cashier.sdk.pay.dialog.e
    public void setChecked(boolean z) {
        this.checked = z;
    }

    public final void setCouponTypeDesc(@NotNull String str) {
        this.couponTypeDesc = str;
    }

    public final void setCutOffType(@NotNull String str) {
        this.cutOffType = str;
    }

    public final void setEndDate(@NotNull String str) {
        this.endDate = str;
    }

    public final void setPayMarketingUUID(@NotNull String str) {
        this.payMarketingUUID = str;
    }

    public final void setPrizeId(@NotNull String str) {
        this.prizeId = str;
    }

    public final void setPromotionDesc(@NotNull String str) {
        this.promotionDesc = str;
    }

    public final void setShowAmount(@Nullable String str) {
        this.showAmount = str;
    }

    public final void setShowAmountStr(@Nullable String str) {
        this.showAmountStr = str;
    }

    public final void setTermOfValidity(@Nullable String str) {
        this.termOfValidity = str;
    }

    public final void setTopPriceAnimationInfo(@Nullable TopPriceAnimationInfo topPriceAnimationInfo) {
        this.topPriceAnimationInfo = topPriceAnimationInfo;
    }

    public final void setUsingScene(@NotNull String str) {
        this.usingScene = str;
    }

    public void setViewType(int i2) {
        this.viewType = i2;
    }

    @NotNull
    public String toString() {
        return "CommonCouponEntity(payMarketingUUID='" + this.payMarketingUUID + "', prizeId='" + this.prizeId + "', termOfValidity=" + this.termOfValidity + ", showAmountStr=" + this.showAmountStr + ", showAmount=" + this.showAmount + ", couponTypeDesc='" + this.couponTypeDesc + "', promotionDesc='" + this.promotionDesc + "', beginDate='" + this.beginDate + "', endDate='" + this.endDate + "', usingScene='" + this.usingScene + "', cutOffType='" + this.cutOffType + "', topPriceAnimationInfo=" + this.topPriceAnimationInfo + ", viewType=" + getViewType() + ", checked=" + getChecked() + ')';
    }
}

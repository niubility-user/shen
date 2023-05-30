package com.jd.lib.cashier.sdk.pay.bean;

import android.text.TextUtils;
import com.jd.cashier.app.jdlibcutter.protocol.pair.PairKey;
import com.jd.dynamic.DYConstants;
import com.jd.lib.cashier.sdk.core.model.ICheckNullObj;
import com.jd.lib.cashier.sdk.core.ui.widget.IPlanItemViewEntity;
import com.jd.lib.cashier.sdk.pay.bean.coupon.CouponEntity;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\n\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\f\n\u0002\u0010$\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\u001d\u0018\u00002\u00020\u00012\u00020\u0002B\t\b\u0016\u00a2\u0006\u0004\bP\u0010\bJ\u000f\u0010\u0004\u001a\u00020\u0003H\u0016\u00a2\u0006\u0004\b\u0004\u0010\u0005J\u000f\u0010\u0007\u001a\u00020\u0006H\u0016\u00a2\u0006\u0004\b\u0007\u0010\bR\"\u0010\t\u001a\u00020\u00038\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\u0005\"\u0004\b\f\u0010\rR\"\u0010\u000e\u001a\u00020\u00038\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u000e\u0010\n\u001a\u0004\b\u000f\u0010\u0005\"\u0004\b\u0010\u0010\rR\u0016\u0010\u0014\u001a\u00020\u00118V@\u0016X\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013R\"\u0010\u0016\u001a\u00020\u00158\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0016\u0010\u0017\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\"\u0010\u001c\u001a\u00020\u00158\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u001c\u0010\u0017\u001a\u0004\b\u001d\u0010\u0019\"\u0004\b\u001e\u0010\u001bR\"\u0010\u001f\u001a\u00020\u00158\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u001f\u0010\u0017\u001a\u0004\b \u0010\u0019\"\u0004\b!\u0010\u001bR0\u0010#\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\"8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b#\u0010$\u001a\u0004\b%\u0010&\"\u0004\b'\u0010(R\u0016\u0010)\u001a\u00020\u00158V@\u0016X\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b)\u0010\u0019R\u0016\u0010+\u001a\u00020\u00038V@\u0016X\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b*\u0010\u0005R\"\u0010,\u001a\u00020\u00158V@\u0016X\u0096\u000e\u00a2\u0006\u0012\n\u0004\b,\u0010\u0017\u001a\u0004\b,\u0010\u0019\"\u0004\b-\u0010\u001bR\"\u0010.\u001a\u00020\u00038\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b.\u0010\n\u001a\u0004\b/\u0010\u0005\"\u0004\b0\u0010\rR\"\u00101\u001a\u00020\u00158\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b1\u0010\u0017\u001a\u0004\b2\u0010\u0019\"\u0004\b3\u0010\u001bR$\u00105\u001a\u0004\u0018\u0001048\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b5\u00106\u001a\u0004\b7\u00108\"\u0004\b9\u0010:R\"\u0010;\u001a\u00020\u00038\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b;\u0010\n\u001a\u0004\b<\u0010\u0005\"\u0004\b=\u0010\rR\"\u0010>\u001a\u00020\u00038\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b>\u0010\n\u001a\u0004\b?\u0010\u0005\"\u0004\b@\u0010\rR\u0016\u0010B\u001a\u00020\u00038V@\u0016X\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\bA\u0010\u0005R\"\u0010C\u001a\u00020\u00038\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\bC\u0010\n\u001a\u0004\bD\u0010\u0005\"\u0004\bE\u0010\rR\"\u0010F\u001a\u00020\u00038\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\bF\u0010\n\u001a\u0004\bG\u0010\u0005\"\u0004\bH\u0010\rR\"\u0010I\u001a\u00020\u00038\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\bI\u0010\n\u001a\u0004\bJ\u0010\u0005\"\u0004\bK\u0010\rR\u0016\u0010M\u001a\u00020\u00038V@\u0016X\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\bL\u0010\u0005R\u0016\u0010O\u001a\u00020\u00038V@\u0016X\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\bN\u0010\u0005\u00a8\u0006Q"}, d2 = {"Lcom/jd/lib/cashier/sdk/pay/bean/PlanFeeEntity;", "Lcom/jd/lib/cashier/sdk/core/ui/widget/IPlanItemViewEntity;", "Lcom/jd/lib/cashier/sdk/core/model/ICheckNullObj;", "", "toString", "()Ljava/lang/String;", "", "checkNullObjAndInit", "()V", "discountLogoText", "Ljava/lang/String;", "getDiscountLogoText", "setDiscountLogoText", "(Ljava/lang/String;)V", PairKey.PLAN_INFO, "getPlanInfo", "setPlanInfo", "", "getSelectorType", "()I", "selectorType", "", "hiddenFrozenPriceFlag", "Z", "getHiddenFrozenPriceFlag", "()Z", "setHiddenFrozenPriceFlag", "(Z)V", "skuSplitFlag", "getSkuSplitFlag", "setSkuSplitFlag", "useOrder", "getUseOrder", "setUseOrder", "", PairKey.TRADE_MAP, "Ljava/util/Map;", "getTradeMap", "()Ljava/util/Map;", "setTradeMap", "(Ljava/util/Map;)V", "isCheckable", "getTopText", "topText", "isChecked", "setChecked", "plan", "getPlan", "setPlan", DYConstants.DY_SELECTED, "getSelected", "setSelected", "Lcom/jd/lib/cashier/sdk/pay/bean/coupon/CouponEntity;", "recommendCoupon", "Lcom/jd/lib/cashier/sdk/pay/bean/coupon/CouponEntity;", "getRecommendCoupon", "()Lcom/jd/lib/cashier/sdk/pay/bean/coupon/CouponEntity;", "setRecommendCoupon", "(Lcom/jd/lib/cashier/sdk/pay/bean/coupon/CouponEntity;)V", "planFeeInfo", "getPlanFeeInfo", "setPlanFeeInfo", "realPayAmount", "getRealPayAmount", "setRealPayAmount", "getBottomText", "bottomText", "priceAndPlan", "getPriceAndPlan", "setPriceAndPlan", "total", "getTotal", "setTotal", "discountLogoType", "getDiscountLogoType", "setDiscountLogoType", "getFlagText", "flagText", "getPlanNum", "planNum", "<init>", "cashier_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes14.dex */
public final class PlanFeeEntity implements IPlanItemViewEntity, ICheckNullObj {
    private boolean hiddenFrozenPriceFlag;
    private boolean isChecked;
    @Nullable
    private CouponEntity recommendCoupon;
    private boolean selected;
    private boolean skuSplitFlag;
    @Nullable
    private Map<String, String> tradeMap;
    private boolean useOrder;
    @NotNull
    private String plan = "";
    @NotNull
    private String priceAndPlan = "";
    @NotNull
    private String planFeeInfo = "";
    @NotNull
    private String total = "";
    @NotNull
    private String realPayAmount = "";
    @NotNull
    private String discountLogoType = "";
    @NotNull
    private String discountLogoText = "";
    @NotNull
    private String planInfo = "";

    @Override // com.jd.lib.cashier.sdk.core.model.ICheckNullObj
    public void checkNullObjAndInit() {
        if (this.recommendCoupon == null) {
            this.recommendCoupon = new CouponEntity();
        }
        CouponEntity couponEntity = this.recommendCoupon;
        if (couponEntity == null) {
            Intrinsics.throwNpe();
        }
        couponEntity.checkNullObjAndInit();
    }

    @Override // com.jd.lib.cashier.sdk.core.ui.widget.IPlanItemViewEntity
    @NotNull
    /* renamed from: getBottomText  reason: from getter */
    public String getPlanFeeInfo() {
        return this.planFeeInfo;
    }

    @NotNull
    public final String getDiscountLogoText() {
        return this.discountLogoText;
    }

    @NotNull
    public final String getDiscountLogoType() {
        return this.discountLogoType;
    }

    @Override // com.jd.lib.cashier.sdk.core.ui.widget.IPlanItemViewEntity
    @NotNull
    public String getFlagText() {
        return this.discountLogoText;
    }

    public final boolean getHiddenFrozenPriceFlag() {
        return this.hiddenFrozenPriceFlag;
    }

    @NotNull
    public final String getPlan() {
        return this.plan;
    }

    @NotNull
    public final String getPlanFeeInfo() {
        return this.planFeeInfo;
    }

    @NotNull
    public final String getPlanInfo() {
        return this.planInfo;
    }

    @Override // com.jd.lib.cashier.sdk.core.ui.widget.IPlanItemViewEntity
    @NotNull
    public String getPlanNum() {
        return this.plan;
    }

    @NotNull
    public final String getPriceAndPlan() {
        return this.priceAndPlan;
    }

    @NotNull
    public final String getRealPayAmount() {
        return this.realPayAmount;
    }

    @Nullable
    public final CouponEntity getRecommendCoupon() {
        return this.recommendCoupon;
    }

    public final boolean getSelected() {
        return this.selected;
    }

    @Override // com.jd.lib.cashier.sdk.core.ui.widget.IPlanItemViewEntity
    public int getSelectorType() {
        if (TextUtils.equals(this.discountLogoType, "plusMianXi")) {
            return 20000;
        }
        return TextUtils.equals(this.discountLogoType, "mianxi") ? 30000 : 10000;
    }

    public final boolean getSkuSplitFlag() {
        return this.skuSplitFlag;
    }

    @Override // com.jd.lib.cashier.sdk.core.ui.widget.IPlanItemViewEntity
    @NotNull
    public String getTopText() {
        return this.priceAndPlan;
    }

    @NotNull
    public final String getTotal() {
        return this.total;
    }

    @Nullable
    public final Map<String, String> getTradeMap() {
        return this.tradeMap;
    }

    public final boolean getUseOrder() {
        return this.useOrder;
    }

    @Override // com.jd.lib.cashier.sdk.core.ui.widget.IPlanItemViewEntity
    public boolean isCheckable() {
        return this.useOrder;
    }

    @Override // com.jd.lib.cashier.sdk.core.ui.widget.IPlanItemViewEntity
    public boolean isChecked() {
        return this.selected;
    }

    @Override // com.jd.lib.cashier.sdk.core.ui.widget.IPlanItemViewEntity
    public void setChecked(boolean z) {
        this.isChecked = z;
    }

    public final void setDiscountLogoText(@NotNull String str) {
        this.discountLogoText = str;
    }

    public final void setDiscountLogoType(@NotNull String str) {
        this.discountLogoType = str;
    }

    public final void setHiddenFrozenPriceFlag(boolean z) {
        this.hiddenFrozenPriceFlag = z;
    }

    public final void setPlan(@NotNull String str) {
        this.plan = str;
    }

    public final void setPlanFeeInfo(@NotNull String str) {
        this.planFeeInfo = str;
    }

    public final void setPlanInfo(@NotNull String str) {
        this.planInfo = str;
    }

    public final void setPriceAndPlan(@NotNull String str) {
        this.priceAndPlan = str;
    }

    public final void setRealPayAmount(@NotNull String str) {
        this.realPayAmount = str;
    }

    public final void setRecommendCoupon(@Nullable CouponEntity couponEntity) {
        this.recommendCoupon = couponEntity;
    }

    public final void setSelected(boolean z) {
        this.selected = z;
    }

    public final void setSkuSplitFlag(boolean z) {
        this.skuSplitFlag = z;
    }

    public final void setTotal(@NotNull String str) {
        this.total = str;
    }

    public final void setTradeMap(@Nullable Map<String, String> map) {
        this.tradeMap = map;
    }

    public final void setUseOrder(boolean z) {
        this.useOrder = z;
    }

    @NotNull
    public String toString() {
        return "PlanFeeEntity(plan='" + this.plan + "', priceAndPlan='" + this.priceAndPlan + "', planFeeInfo='" + this.planFeeInfo + "', total='" + this.total + "', realPayAmount='" + this.realPayAmount + "', useOrder=" + this.useOrder + ", discountLogoType='" + this.discountLogoType + "', discountLogoText='" + this.discountLogoText + "', selected=" + this.selected + ", planInfo='" + this.planInfo + "', recommendCoupon=" + this.recommendCoupon + ')';
    }
}

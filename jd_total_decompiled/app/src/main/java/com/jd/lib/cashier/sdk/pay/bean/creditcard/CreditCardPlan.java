package com.jd.lib.cashier.sdk.pay.bean.creditcard;

import com.jd.lib.cashier.sdk.core.model.ICheckNullObj;
import com.jd.lib.cashier.sdk.core.ui.widget.IPlanItemViewEntity;
import com.jd.lib.cashier.sdk.h.h.l;
import com.jd.lib.cashier.sdk.pay.bean.coupon.CouponEntity;
import java.util.Map;
import org.jetbrains.annotations.NotNull;

/* loaded from: classes14.dex */
public class CreditCardPlan implements IPlanItemViewEntity, ICheckNullObj {
    public String bankPlanRate;
    public String discountLogoText;
    public String discountLogoType;
    public String merchantFeeSubSideBy;
    public String planInfo;
    public CouponEntity recommendCoupon;
    public Boolean selected;
    public Map<String, String> tradeMap;
    public Boolean useOrder;
    public String plan = "";
    public String priceAndPlan = "";
    public String planFeeInfo = "";
    public String total = "";
    public String realPayAmount = "";

    public CreditCardPlan() {
        Boolean bool = Boolean.FALSE;
        this.useOrder = bool;
        this.discountLogoType = "";
        this.discountLogoText = "";
        this.selected = bool;
        this.planInfo = "";
        this.recommendCoupon = null;
        this.tradeMap = null;
        this.bankPlanRate = "";
        this.merchantFeeSubSideBy = "";
    }

    @Override // com.jd.lib.cashier.sdk.core.model.ICheckNullObj
    public void checkNullObjAndInit() {
        if (this.recommendCoupon == null) {
            this.recommendCoupon = new CouponEntity();
        }
        this.recommendCoupon.checkNullObjAndInit();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof CreditCardPlan) {
            CreditCardPlan creditCardPlan = (CreditCardPlan) obj;
            return l.a(this.merchantFeeSubSideBy, creditCardPlan.merchantFeeSubSideBy) && l.a(this.plan, creditCardPlan.plan) && l.a(this.planFeeInfo, creditCardPlan.planFeeInfo) && l.a(this.priceAndPlan, creditCardPlan.priceAndPlan);
        }
        return false;
    }

    @Override // com.jd.lib.cashier.sdk.core.ui.widget.IPlanItemViewEntity
    @NotNull
    public String getBottomText() {
        return this.planFeeInfo;
    }

    @Override // com.jd.lib.cashier.sdk.core.ui.widget.IPlanItemViewEntity
    @NotNull
    public String getFlagText() {
        return this.discountLogoText;
    }

    @Override // com.jd.lib.cashier.sdk.core.ui.widget.IPlanItemViewEntity
    @NotNull
    public String getPlanNum() {
        return this.plan;
    }

    @Override // com.jd.lib.cashier.sdk.core.ui.widget.IPlanItemViewEntity
    public int getSelectorType() {
        return 10000;
    }

    @Override // com.jd.lib.cashier.sdk.core.ui.widget.IPlanItemViewEntity
    @NotNull
    public String getTopText() {
        return this.priceAndPlan;
    }

    public int hashCode() {
        return l.b(this.merchantFeeSubSideBy, this.plan, this.planFeeInfo, this.priceAndPlan);
    }

    @Override // com.jd.lib.cashier.sdk.core.ui.widget.IPlanItemViewEntity
    public boolean isCheckable() {
        return this.useOrder.booleanValue();
    }

    @Override // com.jd.lib.cashier.sdk.core.ui.widget.IPlanItemViewEntity
    public boolean isChecked() {
        return this.selected.booleanValue();
    }

    @Override // com.jd.lib.cashier.sdk.core.ui.widget.IPlanItemViewEntity
    public void setChecked(boolean z) {
        this.selected = Boolean.valueOf(z);
    }

    public String toString() {
        return "CreditCardPlan{plan='" + this.plan + "', priceAndPlan='" + this.priceAndPlan + "', planFeeInfo='" + this.planFeeInfo + "', total='" + this.total + "', realPayAmount='" + this.realPayAmount + "', useOrder=" + this.useOrder + ", discountLogoType='" + this.discountLogoType + "', discountLogoText='" + this.discountLogoText + "', selected=" + this.selected + ", planInfo='" + this.planInfo + "', recommendCoupon=" + this.recommendCoupon + ", bankPlanRate='" + this.bankPlanRate + "', merchantFeeSubSideBy='" + this.merchantFeeSubSideBy + "'}";
    }
}

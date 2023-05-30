package com.jd.lib.cashier.sdk.pay.bean.coupon;

import android.text.TextUtils;
import com.jd.lib.cashier.sdk.core.model.ICheckNullObj;
import com.jd.lib.cashier.sdk.pay.bean.TopPriceAnimationInfo;
import com.jd.lib.cashier.sdk.pay.dialog.e;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* loaded from: classes14.dex */
public class CouponAndCutOffs implements e, ICheckNullObj {
    public TopPriceAnimationInfo topPriceAnimationInfo;
    public String couponTypeDesc = "";
    public String usingScene = "";
    public String payMarketingUUID = "";
    public String promotionDesc = "";
    public String remark = "";
    public String useDesc = "";
    public String prizeId = "";
    private String realAmount = "";
    public String showAmount = "";
    public String beginDate = "";
    public String endDate = "";
    public String cutOffType = "";

    @Override // com.jd.lib.cashier.sdk.core.model.ICheckNullObj
    public void checkNullObjAndInit() {
    }

    @Override // com.jd.lib.cashier.sdk.pay.dialog.e
    public boolean getChecked() {
        return false;
    }

    @Override // com.jd.lib.cashier.sdk.pay.dialog.e
    @NotNull
    public String getCouponAmount() {
        return this.showAmount;
    }

    @Override // com.jd.lib.cashier.sdk.pay.dialog.e
    @NotNull
    public String getCouponEntityId() {
        return this.payMarketingUUID;
    }

    @Override // com.jd.lib.cashier.sdk.pay.dialog.e
    @NotNull
    public String getCouponTypeForMta() {
        return !TextUtils.isEmpty(this.cutOffType) ? this.cutOffType : "0";
    }

    @Override // com.jd.lib.cashier.sdk.pay.dialog.e
    @NotNull
    public String getCouponTypeName() {
        return this.couponTypeDesc;
    }

    @Override // com.jd.lib.cashier.sdk.pay.dialog.e
    @NotNull
    public String getExtraInfo() {
        return this.useDesc;
    }

    @Override // com.jd.lib.cashier.sdk.pay.dialog.e
    @Nullable
    public TopPriceAnimationInfo getPriceAnimationInfo() {
        return this.topPriceAnimationInfo;
    }

    @Override // com.jd.lib.cashier.sdk.pay.dialog.e
    @NotNull
    public String getSubtitle() {
        return this.remark;
    }

    @Override // com.jd.lib.cashier.sdk.pay.dialog.e
    @NotNull
    public String getTitleName() {
        return this.promotionDesc;
    }

    @Override // com.jd.lib.cashier.sdk.pay.dialog.e
    public int getViewType() {
        return 0;
    }

    @Override // com.jd.lib.cashier.sdk.pay.dialog.e
    public void setChecked(boolean z) {
    }

    public void setViewType(int i2) {
    }
}

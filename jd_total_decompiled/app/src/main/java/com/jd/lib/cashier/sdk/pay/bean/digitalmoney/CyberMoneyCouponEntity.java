package com.jd.lib.cashier.sdk.pay.bean.digitalmoney;

import android.text.TextUtils;
import androidx.annotation.Nullable;
import com.jd.lib.cashier.sdk.pay.bean.TopPriceAnimationInfo;
import com.jd.lib.cashier.sdk.pay.dialog.e;
import org.jetbrains.annotations.NotNull;

/* loaded from: classes14.dex */
public class CyberMoneyCouponEntity implements e {
    private boolean checked;
    public TopPriceAnimationInfo topPriceAnimationInfo;
    private int viewType;
    public String payMarketingUUID = "";
    public String remark = "";
    public String prizeId = "";
    public String useDesc = "";
    public String promotionDesc = "";
    public String couponTypeDesc = "";
    public String realAmount = "";
    public String showAmountStr = "";
    public String showAmount = "";
    public String beginDate = "";
    public String endDate = "";
    public String cutOffType = "";
    public String usingScene = "";
    public String termOfValidity = "";

    public boolean canUse() {
        return this.viewType == 1;
    }

    public boolean equals(@Nullable Object obj) {
        if (obj instanceof CyberMoneyCouponEntity) {
            return TextUtils.equals(this.payMarketingUUID, ((CyberMoneyCouponEntity) obj).payMarketingUUID);
        }
        return false;
    }

    @Override // com.jd.lib.cashier.sdk.pay.dialog.e
    public boolean getChecked() {
        return this.checked;
    }

    @Override // com.jd.lib.cashier.sdk.pay.dialog.e
    @NotNull
    public String getCouponAmount() {
        if (TextUtils.isEmpty(this.showAmountStr)) {
            return !TextUtils.isEmpty(this.showAmount) ? this.showAmount : "";
        }
        return this.showAmountStr;
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
        return this.usingScene;
    }

    @Override // com.jd.lib.cashier.sdk.pay.dialog.e
    @Nullable
    public TopPriceAnimationInfo getPriceAnimationInfo() {
        return this.topPriceAnimationInfo;
    }

    @Override // com.jd.lib.cashier.sdk.pay.dialog.e
    @NotNull
    public String getSubtitle() {
        return this.termOfValidity;
    }

    @Override // com.jd.lib.cashier.sdk.pay.dialog.e
    @NotNull
    public String getTitleName() {
        return this.promotionDesc;
    }

    @Override // com.jd.lib.cashier.sdk.pay.dialog.e
    public int getViewType() {
        return this.viewType;
    }

    public int hashCode() {
        String str = this.payMarketingUUID;
        if (str == null) {
            return -1;
        }
        return str.hashCode();
    }

    @Override // com.jd.lib.cashier.sdk.pay.dialog.e
    public void setChecked(boolean z) {
        this.checked = z;
    }

    public void setViewType(int i2) {
        this.viewType = i2;
    }
}

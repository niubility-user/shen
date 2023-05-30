package com.jingdong.common.entity.cart;

import com.jd.framework.json.JDJSONObject;

/* loaded from: classes5.dex */
public class CartPreferentialSummary {
    public String balanceAmount;
    public String couponSavedPrice;
    public String couponStatus;
    public String discountReprice;
    public String platformPerDiscountPrice;
    public String promotionPrice;
    public String totalReprice;

    public CartPreferentialSummary(JDJSONObject jDJSONObject) {
        if (jDJSONObject == null) {
            return;
        }
        this.balanceAmount = jDJSONObject.optString("balanceAmount");
        this.discountReprice = jDJSONObject.optString("discountReprice");
        this.promotionPrice = jDJSONObject.optString("promotionPrice");
        this.totalReprice = jDJSONObject.optString("totalReprice");
        this.couponSavedPrice = jDJSONObject.optString("couponSavedPrice");
        this.couponStatus = jDJSONObject.optString("couponStatus", "0");
        this.platformPerDiscountPrice = jDJSONObject.optString("platformPerDiscountPrice", "0");
    }
}

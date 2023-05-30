package com.jingdong.common.entity.cart;

import com.jd.framework.json.JDJSONObject;

/* loaded from: classes5.dex */
public class CartCheckOutDetail {
    private String discountText;
    private String discountTextAccessibility;

    public CartCheckOutDetail(JDJSONObject jDJSONObject) {
        if (jDJSONObject == null) {
            return;
        }
        this.discountTextAccessibility = jDJSONObject.optString("discountTextAccessibility");
        this.discountText = jDJSONObject.optString("discountText");
    }

    public String getDiscountText() {
        return this.discountText;
    }

    public String getDiscountTextAccessibility() {
        return this.discountTextAccessibility;
    }

    public void setDiscountText(String str) {
        this.discountText = str;
    }

    public void setDiscountTextAccessibility(String str) {
        this.discountTextAccessibility = str;
    }

    public String toString() {
        return "CartCheckOutDetail{discountTextAccessibility='" + this.discountTextAccessibility + "', discountText='" + this.discountText + "'}";
    }
}

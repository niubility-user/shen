package com.jingdong.common.entity.cart;

import com.jd.framework.json.JDJSONObject;

/* loaded from: classes5.dex */
public class CartEconomicalCardCoupon {
    public String bottomTipString;
    public String couponThreshold;
    public String promotionPrice;

    public static CartEconomicalCardCoupon parseJson(JDJSONObject jDJSONObject) {
        if (jDJSONObject == null || jDJSONObject.size() <= 0) {
            return null;
        }
        CartEconomicalCardCoupon cartEconomicalCardCoupon = new CartEconomicalCardCoupon();
        cartEconomicalCardCoupon.bottomTipString = jDJSONObject.optString("bottomTipString", "");
        cartEconomicalCardCoupon.promotionPrice = jDJSONObject.optString("promotionPrice", "");
        cartEconomicalCardCoupon.couponThreshold = jDJSONObject.optString("couponThreshold", "");
        return cartEconomicalCardCoupon;
    }
}

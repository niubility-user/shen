package com.jingdong.common.entity.cart;

import com.jd.framework.json.JDJSONObject;
import com.jingdong.jdsdk.constant.CartConstant;

/* loaded from: classes5.dex */
public class CartSuperimposePromotion {
    public String needMoney;
    public String promotionEntranceText;
    public String promotionId;
    public String promotionName;
    public String promotionText;

    public CartSuperimposePromotion(JDJSONObject jDJSONObject) {
        if (jDJSONObject == null) {
            return;
        }
        this.promotionId = jDJSONObject.optString("promotionId");
        this.promotionName = jDJSONObject.optString("promotionName");
        this.promotionText = jDJSONObject.optString("promotionText");
        this.promotionEntranceText = jDJSONObject.optString("promotionEntranceText");
        this.needMoney = jDJSONObject.optString(CartConstant.KEY_GLOBAL_NEED_MONEY);
    }
}

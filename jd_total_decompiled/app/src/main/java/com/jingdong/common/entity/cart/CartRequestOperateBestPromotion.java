package com.jingdong.common.entity.cart;

import com.jd.framework.json.JDJSONObject;
import com.jingdong.jdsdk.constant.CartConstant;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class CartRequestOperateBestPromotion extends CartRequestOperate {
    private JDJSONObject promotionPriceInfo;

    public CartRequestOperateBestPromotion(JDJSONObject jDJSONObject) {
        super("");
        this.promotionPriceInfo = jDJSONObject;
    }

    @Override // com.jingdong.common.entity.cart.CartRequestOperate, com.jingdong.common.entity.cart.CartRequestBase
    public JSONObject toParams() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        if (this.promotionPriceInfo != null) {
            jSONObject.put(CartConstant.KEY_SKU_PROMOTIONPRICE, new JSONObject(this.promotionPriceInfo.toJSONString()));
        }
        return jSONObject;
    }
}

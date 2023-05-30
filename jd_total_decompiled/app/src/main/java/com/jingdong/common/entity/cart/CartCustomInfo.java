package com.jingdong.common.entity.cart;

import com.jd.framework.json.JDJSONObject;
import com.jingdong.jdsdk.constant.CartConstant;
import java.util.ArrayList;

/* loaded from: classes5.dex */
public class CartCustomInfo {
    public ArrayList<CartCustomDigestInfo> cartCustomDigestInfos;
    public String customInfoId;
    public int num;
    public String skuAttrTotalPrice;
    public boolean validFlag;

    public CartCustomInfo(JDJSONObject jDJSONObject) {
        if (jDJSONObject == null) {
            return;
        }
        this.validFlag = jDJSONObject.optBoolean("validFlag");
        this.customInfoId = jDJSONObject.optString(CartConstant.KEY_CART_SOLDOFFSKU_CUSTOMINFOID);
        this.cartCustomDigestInfos = CartCustomDigestInfo.toList(jDJSONObject.optJSONArray("customDigestInfoList"));
        this.skuAttrTotalPrice = jDJSONObject.optString("skuAttrTotalPrice");
        this.num = jDJSONObject.optInt("num");
    }
}

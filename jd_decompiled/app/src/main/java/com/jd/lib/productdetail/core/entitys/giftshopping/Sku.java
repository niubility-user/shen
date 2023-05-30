package com.jd.lib.productdetail.core.entitys.giftshopping;

import com.jd.framework.json.JDJSONObject;
import com.jingdong.jdsdk.constant.CartConstant;

/* loaded from: classes15.dex */
public class Sku {
    private static final String TYPE = "type";
    private String imgUrl;
    public String name;
    private int num;
    private String price;
    public long skuId;
    public String stockState;
    public int type;

    public Sku(JDJSONObject jDJSONObject) {
        this.imgUrl = "";
        this.price = "";
        this.name = "";
        this.stockState = "";
        this.type = 2;
        if (jDJSONObject != null) {
            this.skuId = jDJSONObject.optLong("id");
            this.imgUrl = jDJSONObject.optString("imgUrl");
            this.price = jDJSONObject.optString("price");
            this.name = jDJSONObject.optString("name");
            this.num = jDJSONObject.optInt("num");
            this.stockState = jDJSONObject.optString(CartConstant.KEY_SKU_STOCKSTATE);
            jDJSONObject.optString(CartConstant.KEY_SKU_REMAINNUM);
            if (jDJSONObject.isNull("type")) {
                return;
            }
            this.type = jDJSONObject.optInt("type");
        }
    }
}

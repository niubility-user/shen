package com.jingdong.common.entity.cart;

import com.jd.framework.json.JDJSONObject;
import com.jingdong.common.database.table.DB_PacksTable;
import com.jingdong.jdsdk.constant.CartConstant;

/* loaded from: classes5.dex */
public class CartEasyCombineEntity {
    public String anyonePrice;
    public String cateMatchPid;
    public String cateMatchSaleId;
    public String containerHeight;
    public String dynamicData;
    public String gradient;
    public String isEmptyTab;
    public boolean isShowShoppingBag;
    public String mainSkuId;
    public String preferentialTag;
    public String productRow;
    public long promotionId;
    public String promotionTitle;
    public String sessionId;
    public String stillNeed;

    public CartEasyCombineEntity(JDJSONObject jDJSONObject) {
        if (jDJSONObject != null) {
            this.gradient = jDJSONObject.optString("gradient");
            this.isEmptyTab = jDJSONObject.optString("isEmptyTab");
            this.mainSkuId = jDJSONObject.optString(DB_PacksTable.TB_COLOUMN_MAIN_SKU_ID);
            this.cateMatchPid = jDJSONObject.optString("cateMatchPid");
            this.cateMatchSaleId = jDJSONObject.optString("cateMatchSaleId");
            this.anyonePrice = jDJSONObject.optString("anyonePrice");
            this.productRow = jDJSONObject.optString("productRow");
            this.promotionId = jDJSONObject.optLong("promotionId");
            this.promotionTitle = jDJSONObject.optString("promotionTitle");
            this.isShowShoppingBag = jDJSONObject.optBoolean("isShowShoppingBag");
            this.stillNeed = jDJSONObject.optString(CartConstant.KEY_CART_STILL_NEED);
            this.sessionId = jDJSONObject.optString("sessionId");
            this.dynamicData = jDJSONObject.optString("dynamicData");
            this.containerHeight = jDJSONObject.optString("containerHeight");
            this.preferentialTag = jDJSONObject.optString("preferentialTag");
        }
    }
}

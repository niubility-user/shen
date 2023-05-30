package com.jingdong.common.entity.cart;

import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.jdsdk.constant.CartConstant;
import java.util.ArrayList;

/* loaded from: classes5.dex */
public class CartSkuOrderInfo {
    public int businessType;
    public boolean hasNational;
    public int itemType;
    public String jumpUrl;
    public int num;
    public String orderSubTitle;
    public String orderTitle;
    public String storeId;
    public int urlType;

    public CartSkuOrderInfo(JDJSONObject jDJSONObject) {
        if (jDJSONObject == null) {
            return;
        }
        this.orderTitle = jDJSONObject.optString("orderTitle");
        this.orderSubTitle = jDJSONObject.optString("orderSubTitle");
        this.itemType = jDJSONObject.optInt(CartConstant.KEY_VENDOR_ITEM_TYPE);
        this.businessType = jDJSONObject.optInt("businessType");
        this.num = jDJSONObject.optInt("num");
        this.storeId = jDJSONObject.optString("storeId");
        this.jumpUrl = jDJSONObject.optString(CartConstant.KEY_JUMPURL);
        this.urlType = jDJSONObject.optInt("urlType");
        this.hasNational = jDJSONObject.optBoolean("hasNational");
    }

    public static ArrayList<CartSkuOrderInfo> toList(JDJSONArray jDJSONArray) {
        if (jDJSONArray == null || jDJSONArray.size() <= 0) {
            return null;
        }
        int size = jDJSONArray.size();
        ArrayList<CartSkuOrderInfo> arrayList = new ArrayList<>(size);
        for (int i2 = 0; i2 < size; i2++) {
            JDJSONObject jSONObject = jDJSONArray.getJSONObject(i2);
            if (jSONObject != null) {
                arrayList.add(new CartSkuOrderInfo(jSONObject));
            }
        }
        return arrayList;
    }
}

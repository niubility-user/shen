package com.jingdong.common.entity.cart.yanbao;

import com.jingdong.jdsdk.constant.CartConstant;
import java.io.Serializable;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class CartResonseNewYB implements Serializable {
    private static final long serialVersionUID = -7321916218563868596L;
    private ArrayList<CartResponseNewYBCategory> newYBCategories;
    private String skuId;
    public String suitId;

    public CartResonseNewYB() {
    }

    public ArrayList<CartResponseNewYBCategory> getCategories() {
        return this.newYBCategories;
    }

    public String getSkuId() {
        String str = this.skuId;
        return str == null ? "" : str;
    }

    public void setCategories(ArrayList<CartResponseNewYBCategory> arrayList) {
        this.newYBCategories = arrayList;
    }

    public String toString() {
        return "CartResonseYB [skuId=" + this.skuId + ",suitId=" + this.suitId + ", newYBCategories=" + this.newYBCategories + "]";
    }

    public CartResonseNewYB(JSONObject jSONObject) {
        if (jSONObject != null) {
            this.skuId = jSONObject.optString(CartConstant.KEY_YB_SKU_ID);
            this.suitId = jSONObject.optString(CartConstant.KEY_YB_SUIT_ID);
            JSONArray optJSONArray = jSONObject.optJSONArray(CartConstant.KEY_YB_YBBRANDS);
            if (optJSONArray == null || optJSONArray.length() <= 0) {
                return;
            }
            this.newYBCategories = new ArrayList<>();
            for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                JSONObject optJSONObject = optJSONArray.optJSONObject(i2);
                if (optJSONObject != null) {
                    this.newYBCategories.add(new CartResponseNewYBCategory(optJSONObject));
                }
            }
        }
    }
}

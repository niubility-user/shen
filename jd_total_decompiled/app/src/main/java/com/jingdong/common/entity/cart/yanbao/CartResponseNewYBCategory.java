package com.jingdong.common.entity.cart.yanbao;

import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.jdsdk.utils.JSONArrayPoxy;
import com.jingdong.jdsdk.utils.JSONObjectProxy;
import java.io.Serializable;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class CartResponseNewYBCategory implements Serializable {
    private static final long serialVersionUID = 8933319978503940647L;
    private String brandId;
    private String brandName;
    private String imageUrl;
    private String rSuitId;
    private String skuId;
    private Integer sortId;
    private ArrayList<CartResponseNewYBDetail> ybDetails;

    public CartResponseNewYBCategory(JSONObject jSONObject) {
        if (jSONObject != null) {
            setSortId(Integer.valueOf(jSONObject.optInt(CartConstant.KEY_YB_SORTID)));
            setBrandId(jSONObject.optString(CartConstant.KEY_YB_BRAND_ID));
            setSkuId(jSONObject.optString("skuId"));
            setrSuitId(jSONObject.optString(CartConstant.KEY_YB_RSUITID));
            setBrandName(jSONObject.optString("brandName"));
            JSONArray optJSONArray = jSONObject.optJSONArray(CartConstant.KEY_YB_CONFIGVOS);
            if (optJSONArray == null || optJSONArray.length() <= 0) {
                return;
            }
            this.ybDetails = new ArrayList<>();
            for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                JSONObject optJSONObject = optJSONArray.optJSONObject(i2);
                if (optJSONObject != null) {
                    this.ybDetails.add(new CartResponseNewYBDetail(optJSONObject));
                }
            }
        }
    }

    public String getBrandId() {
        return this.brandId;
    }

    public String getBrandName() {
        return this.brandName;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public String getSkuId() {
        return this.skuId;
    }

    public Integer getSortId() {
        return this.sortId;
    }

    public ArrayList<CartResponseNewYBDetail> getYbDetails() {
        return this.ybDetails;
    }

    public String getrSuitId() {
        return this.rSuitId;
    }

    public void setBrandId(String str) {
        this.brandId = str;
    }

    public void setBrandName(String str) {
        this.brandName = str;
    }

    public void setImageUrl(String str) {
        this.imageUrl = str;
    }

    public void setSkuId(String str) {
        this.skuId = str;
    }

    public void setSortId(Integer num) {
        this.sortId = num;
    }

    public void setYbDetails(ArrayList<CartResponseNewYBDetail> arrayList) {
        this.ybDetails = arrayList;
    }

    public void setrSuitId(String str) {
        this.rSuitId = str;
    }

    public int sortId() {
        Integer num = this.sortId;
        if (num == null) {
            return -1;
        }
        return num.intValue();
    }

    public String toString() {
        return "CartResponseYBCategory [sortId=" + this.sortId + ", brandId=" + this.brandId + ", skuId=" + this.skuId + ", rSuitId=" + this.rSuitId + ", brandName=" + this.brandName + ", ybDetails=" + this.ybDetails + "]";
    }

    public CartResponseNewYBCategory(JSONObjectProxy jSONObjectProxy, int i2) {
        setBrandId(jSONObjectProxy.getStringOrNull(CartConstant.KEY_YB_PRODUCT_ID));
        setBrandName(jSONObjectProxy.getStringOrNull(CartConstant.KEY_YB_SORT_NAME));
        setImageUrl(jSONObjectProxy.getStringOrNull("imgurl"));
        setSortId(Integer.valueOf(i2));
        JSONArrayPoxy jSONArrayOrNull = jSONObjectProxy.getJSONArrayOrNull("products");
        if (jSONArrayOrNull == null || jSONArrayOrNull.length() <= 0) {
            return;
        }
        this.ybDetails = new ArrayList<>();
        for (int i3 = 0; i3 < jSONArrayOrNull.length(); i3++) {
            JSONObjectProxy jSONObjectOrNull = jSONArrayOrNull.getJSONObjectOrNull(i3);
            if (jSONObjectOrNull != null) {
                CartResponseNewYBDetail cartResponseNewYBDetail = new CartResponseNewYBDetail(jSONObjectOrNull, 1);
                cartResponseNewYBDetail.mSortIndex = i2;
                this.ybDetails.add(cartResponseNewYBDetail);
            }
        }
    }
}

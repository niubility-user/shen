package com.jingdong.common.entity.cart.yanbao;

import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.jdsdk.utils.JSONObjectProxy;
import java.io.Serializable;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class CartResponseNewYBDetail implements Serializable {
    private static final long serialVersionUID = -5504915557567400579L;
    private String imgUrl;
    public int index;
    private Boolean isSelected;
    public int mSortIndex;
    private String platformId;
    private String platformName;
    private String platformNum;
    private String price;
    private String rSuitId;
    private String skuId;
    private String sortId;

    public CartResponseNewYBDetail(JSONObject jSONObject) {
        if (jSONObject != null) {
            setSkuId(jSONObject.optString("skuId"));
            setrSuitId(jSONObject.optString(CartConstant.KEY_YB_RSUITID));
            setPlatformId(jSONObject.optString("platformId"));
            setPlatformName(jSONObject.optString(CartConstant.KEY_YB_PLATFORMNAME));
            setIsSelected(Boolean.valueOf(jSONObject.optBoolean("isSelected")));
            setPlatformNum(jSONObject.optString(CartConstant.KEY_YB_PLATFORMNUM));
            setSortId(jSONObject.optString(CartConstant.KEY_YB_SORTID));
            setPrice(jSONObject.optString("price"));
            setImgUrl(jSONObject.optString("imgUrl"));
        }
    }

    public String getImgUrl() {
        return this.imgUrl;
    }

    public Boolean getIsSelected() {
        return this.isSelected;
    }

    public String getPlatformId() {
        return this.platformId;
    }

    public String getPlatformName() {
        return this.platformName;
    }

    public String getPlatformNum() {
        return this.platformNum;
    }

    public String getPrice() {
        return this.price;
    }

    public String getSkuId() {
        return this.skuId;
    }

    public String getSortId() {
        return this.sortId;
    }

    public String getrSuitId() {
        return this.rSuitId;
    }

    public boolean isSelected() {
        Boolean bool = this.isSelected;
        if (bool == null) {
            return false;
        }
        return bool.booleanValue();
    }

    public void setImgUrl(String str) {
        this.imgUrl = str;
    }

    public void setIsSelected(Boolean bool) {
        this.isSelected = bool;
    }

    public void setPlatformId(String str) {
        this.platformId = str;
    }

    public void setPlatformName(String str) {
        this.platformName = str;
    }

    public void setPlatformNum(String str) {
        this.platformNum = str;
    }

    public void setPrice(String str) {
        this.price = str;
    }

    public void setSkuId(String str) {
        this.skuId = str;
    }

    public void setSortId(String str) {
        this.sortId = str;
    }

    public void setrSuitId(String str) {
        this.rSuitId = str;
    }

    public String toString() {
        return "CartResponseYBDetail [skuId=" + this.skuId + ", rSuitId=" + this.rSuitId + ", platformId=" + this.platformId + ", platformName=" + this.platformName + ", isSelected=" + this.isSelected + ", platformNum=" + this.platformNum + ", sortId=" + this.sortId + ", price=" + this.price + "]";
    }

    public CartResponseNewYBDetail(JSONObjectProxy jSONObjectProxy, int i2) {
        setPlatformId(jSONObjectProxy.getStringOrNull(CartConstant.KEY_YB_PLATFORMPID));
        setPlatformName(jSONObjectProxy.getStringOrNull(CartConstant.KEY_YB_SORT_NAME));
        setImgUrl(jSONObjectProxy.getStringOrNull("imgurl"));
        setPrice(jSONObjectProxy.getStringOrNull("price"));
    }
}

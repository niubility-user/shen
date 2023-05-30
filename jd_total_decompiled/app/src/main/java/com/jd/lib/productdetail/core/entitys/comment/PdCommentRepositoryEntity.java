package com.jd.lib.productdetail.core.entitys.comment;

import com.jd.framework.json.JDJSONObject;

/* loaded from: classes15.dex */
public class PdCommentRepositoryEntity {
    private String category;
    public int commentNum = 2;
    private String isXinpin;
    private JDJSONObject jdjsonObject;
    private String shadowMainSku;
    private String shieldCurrentComment;
    private String shopId;
    private String shopType;
    private String sku;
    public String venderId;
    private String wareType;
    private String xinPinTitle;

    public String getCategory() {
        return this.category;
    }

    public JDJSONObject getExtInfo() {
        return this.jdjsonObject;
    }

    public String getShadowMainSku() {
        return this.shadowMainSku;
    }

    public String getShieldCurrentComment() {
        return this.shieldCurrentComment;
    }

    public String getShopId() {
        return this.shopId;
    }

    public String getShopType() {
        return this.shopType;
    }

    public String getSku() {
        return this.sku;
    }

    public String getVenderId() {
        return this.venderId;
    }

    public String getWareType() {
        return this.wareType;
    }

    public String getXinPinTitle() {
        return this.xinPinTitle;
    }

    public String isXinpin() {
        return this.isXinpin;
    }

    public void setCategory(String str) {
        this.category = str;
    }

    public void setExtInfo(JDJSONObject jDJSONObject) {
        this.jdjsonObject = jDJSONObject;
    }

    public void setIsXinpin(String str) {
        this.isXinpin = str;
    }

    public void setShadowMainSku(String str) {
        this.shadowMainSku = str;
    }

    public void setShieldCurrentComment(String str) {
        this.shieldCurrentComment = str;
    }

    public void setShopId(String str) {
        this.shopId = str;
    }

    public void setShopType(String str) {
        this.shopType = str;
    }

    public void setSku(String str) {
        this.sku = str;
    }

    public void setVenderId(String str) {
        this.venderId = str;
    }

    public void setWareType(String str) {
        this.wareType = str;
    }

    public void setXinPinTitle(String str) {
        this.xinPinTitle = str;
    }
}

package com.jingdong.common.sample.jshop.Entity;

import com.jd.framework.json.JDJSONObject;
import com.jingdong.jdsdk.constant.JshopConst;

/* loaded from: classes6.dex */
public class ProductEntity {
    public String imgPath;
    public int isUnderCarriage;
    public String jdPrice;
    public String mPrice;
    public int stock;
    public String wareId;
    public String wareName;

    public ProductEntity() {
    }

    public ProductEntity(JDJSONObject jDJSONObject) {
        this.wareId = jDJSONObject.optString("wareId");
        this.wareName = jDJSONObject.optString(JshopConst.JSKEY_PRODUCT_WARENAME);
        this.imgPath = jDJSONObject.optString(JshopConst.JSKEY_PRODUCT_IMGPATH);
        this.mPrice = jDJSONObject.optString(JshopConst.JSKEY_PRODUCT_MPRICE);
        this.jdPrice = jDJSONObject.optString(JshopConst.JSKEY_PRODUCT_JDPRICE);
        this.isUnderCarriage = jDJSONObject.optInt("isUnderCarriage", 0);
        this.stock = jDJSONObject.optInt("stock", -1);
    }

    /* renamed from: clone  reason: merged with bridge method [inline-methods] */
    public ProductEntity m22clone() {
        ProductEntity productEntity = new ProductEntity();
        productEntity.wareId = this.wareId;
        productEntity.wareName = this.wareName;
        productEntity.imgPath = this.imgPath;
        productEntity.mPrice = this.mPrice;
        productEntity.jdPrice = this.jdPrice;
        productEntity.isUnderCarriage = this.isUnderCarriage;
        productEntity.stock = this.stock;
        return productEntity;
    }
}

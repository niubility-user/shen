package com.jd.lib.productdetail.core.floor;

import androidx.annotation.NonNull;
import com.jd.lib.productdetail.core.entitys.ProductDetailEntity;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBasicInfo;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessABTestInfo;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessData;
import com.jd.lib.productdetail.core.entitys.warebusiness.WarePropertyInfo;
import com.jingdong.sdk.platform.base.ICloneableData;

/* loaded from: classes15.dex */
public class PdBaseConfig implements ICloneableData, Cloneable {
    public WareBusinessABTestInfo mAbTestInfo;
    public Object mExtData;
    public boolean mIsDarkTheme;
    public String manageKey;
    public WarePropertyInfo property;
    public String shopId;
    public String sku;
    public String skuTag;
    public boolean tenthRevision;
    public int themeStyle;
    public WareBasicInfo wareBasicInfo;

    public static PdBaseConfig parseToConfig(ProductDetailEntity productDetailEntity) {
        if (productDetailEntity != null) {
            PdBaseConfig pdBaseConfig = new PdBaseConfig();
            pdBaseConfig.sku = productDetailEntity.skuId;
            pdBaseConfig.manageKey = productDetailEntity.mManageKey;
            pdBaseConfig.skuTag = productDetailEntity.getSkuTag();
            pdBaseConfig.tenthRevision = productDetailEntity.isTenthRevision();
            pdBaseConfig.mIsDarkTheme = productDetailEntity.isDarkTheme();
            pdBaseConfig.shopId = productDetailEntity.getShopId();
            pdBaseConfig.themeStyle = productDetailEntity.themeStyle;
            WareBusinessData wareBusinessData = productDetailEntity.mWareBusinessData;
            if (wareBusinessData != null) {
                WareBusinessABTestInfo wareBusinessABTestInfo = wareBusinessData.abTestInfo;
                if (wareBusinessABTestInfo != null) {
                    pdBaseConfig.mAbTestInfo = (WareBusinessABTestInfo) wareBusinessABTestInfo.clone();
                }
                WarePropertyInfo warePropertyInfo = productDetailEntity.mWareBusinessData.property;
                if (warePropertyInfo != null) {
                    pdBaseConfig.property = (WarePropertyInfo) warePropertyInfo.clone();
                }
                WareBasicInfo wareBasicInfo = productDetailEntity.mWareBusinessData.wareInfo;
                if (wareBasicInfo != null) {
                    pdBaseConfig.wareBasicInfo = (WareBasicInfo) wareBasicInfo.clone();
                }
            }
            return pdBaseConfig;
        }
        return null;
    }

    @NonNull
    protected Object clone() throws CloneNotSupportedException {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    @Override // com.jingdong.sdk.platform.base.ICloneableData
    public Object cloneData() {
        try {
            return clone();
        } catch (CloneNotSupportedException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public boolean isDarkTheme() {
        WareBusinessABTestInfo wareBusinessABTestInfo = this.mAbTestInfo;
        return wareBusinessABTestInfo != null && wareBusinessABTestInfo.darkModel;
    }
}

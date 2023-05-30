package com.jingdong.app.mall.bundle.styleinfoview.entitys;

import android.text.TextUtils;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.warebusiness.WareBusinessData;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.warebusiness.WareBusinessPaiPaiReplacement;
import com.jingdong.app.mall.bundle.styleinfoview.utils.PDUtils;
import com.jingdong.common.entity.AddressGlobal;
import com.jingdong.common.lbs.jdlocation.JDLocation;
import com.jingdong.common.lbs.jdlocation.JDLocationCache;
import com.jingdong.common.utils.AddressUtil;
import com.jingdong.sdk.baseinfo.BaseInfo;

/* loaded from: classes3.dex */
public class PdPaiPaiRequestParams {
    private JDLocation location;

    public PdPaiPaiReplaceInquiryBody getPaiPaiReplaceRequestParams(ProductDetailEntity productDetailEntity) {
        WareBusinessData wareBusinessData;
        WareBusinessPaiPaiReplacement wareBusinessPaiPaiReplacement;
        if (productDetailEntity == null || (wareBusinessData = productDetailEntity.mWareBusinessData) == null || (wareBusinessPaiPaiReplacement = wareBusinessData.paiPaiOld4New) == null || wareBusinessPaiPaiReplacement.body == null) {
            return null;
        }
        PdPaiPaiReplaceInquiryBody pdPaiPaiReplaceInquiryBody = new PdPaiPaiReplaceInquiryBody();
        WareBusinessPaiPaiReplacement.WareBusinessPaiPaiReplaceBody wareBusinessPaiPaiReplaceBody = productDetailEntity.mWareBusinessData.paiPaiOld4New.body;
        pdPaiPaiReplaceInquiryBody.type = wareBusinessPaiPaiReplaceBody.type;
        pdPaiPaiReplaceInquiryBody.cache = wareBusinessPaiPaiReplaceBody.cache;
        pdPaiPaiReplaceInquiryBody.jp = wareBusinessPaiPaiReplaceBody.jp;
        pdPaiPaiReplaceInquiryBody.skuId = wareBusinessPaiPaiReplaceBody.skuId;
        pdPaiPaiReplaceInquiryBody.brand = BaseInfo.getDeviceBrand();
        pdPaiPaiReplaceInquiryBody.model = BaseInfo.getDeviceModel();
        AddressGlobal addressGlobal = AddressUtil.getAddressGlobal();
        if (addressGlobal != null) {
            pdPaiPaiReplaceInquiryBody.uAddrId = String.valueOf(addressGlobal.getId());
        }
        boolean z = addressGlobal != null && (addressGlobal.getIsUserAddress().booleanValue() || !TextUtils.isEmpty(productDetailEntity.storeId));
        if (z && !TextUtils.isEmpty(addressGlobal.getLatitude())) {
            pdPaiPaiReplaceInquiryBody.gcLat = addressGlobal.getLatitude();
        }
        if (z && !TextUtils.isEmpty(addressGlobal.getLongitude())) {
            pdPaiPaiReplaceInquiryBody.gcLng = addressGlobal.getLongitude();
        }
        JDLocation location = JDLocationCache.getInstance().getLocation(PDUtils.getLocationCacheOption());
        this.location = location;
        if (location != null) {
            pdPaiPaiReplaceInquiryBody.latitude = String.valueOf(location.getLat());
            pdPaiPaiReplaceInquiryBody.longitude = String.valueOf(this.location.getLng());
        }
        pdPaiPaiReplaceInquiryBody.venderId = "200101";
        if (pdPaiPaiReplaceInquiryBody.type == 1) {
            pdPaiPaiReplaceInquiryBody.pageNo = 1;
            pdPaiPaiReplaceInquiryBody.pageSize = 20;
        }
        return pdPaiPaiReplaceInquiryBody;
    }
}

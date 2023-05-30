package com.jingdong.app.mall.bundle.styleinfoview.engine;

import android.os.Bundle;
import android.text.TextUtils;
import com.jd.framework.json.JDJSONObject;
import com.jd.lib.productdetail.core.protocol.PdLVBody;
import com.jingdong.app.mall.bundle.jdweather.action.JDWeatherActionKey;
import com.jingdong.app.mall.bundle.styleinfoview.LibPdStyleInfoViewUtils;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.ProductDetailEntity;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.loc.LocShopInfo;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.warebusiness.WareBusinessData;
import com.jingdong.app.mall.bundle.styleinfoview.utils.PDDataUtil;
import com.jingdong.app.mall.bundle.styleinfoview.utils.PDUtils;
import com.jingdong.common.entity.AddressGlobal;
import com.jingdong.common.entity.personal.PersonalConstants;
import com.jingdong.common.jdmiaosha.utils.cache.JDNetCacheManager;
import com.jingdong.common.lbs.jdlocation.JDLocation;
import com.jingdong.common.lbs.jdlocation.JDLocationCache;
import com.jingdong.common.login.LoginUserBase;
import com.jingdong.common.unification.customtheme.CustomThemeConstance;
import com.jingdong.common.utils.AddressUtil;
import com.jingdong.common.utils.AdvertUtils;
import com.jingdong.common.utils.CommonBase;
import com.jingdong.common.web.entity.WebEntity;
import com.jingdong.common.widget.custom.livewidget.bean.VideoPerfEntity;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.jdsdk.utils.SharedPreferencesUtil;
import com.jingdong.sdk.baseinfo.BaseInfo;

/* loaded from: classes3.dex */
public class PdMainRequestParam {
    private static final String DESKEY = "yc2JffcREheFQlYFIAY5f9sY7uflgBTo";
    private static final String TAG = "PdMainRequestParam";
    public static final int TYPE_CHANGE_ADDRESS = 1;
    public static final int TYPE_NORMAL = 0;
    private JDLocation location = JDLocationCache.getInstance().getLocation(PDUtils.getLocationCacheOption());
    private ProductDetailEntity mProduct;

    public PdMainRequestParam(ProductDetailEntity productDetailEntity) {
        this.mProduct = productDetailEntity;
    }

    private JDJSONObject buildDefaultSkuParameters(String str) {
        JDJSONObject jDJSONObject = new JDJSONObject();
        try {
            jDJSONObject.put("skuId", (Object) str);
            ProductDetailEntity productDetailEntity = this.mProduct;
            if (productDetailEntity != null && !TextUtils.isEmpty(productDetailEntity.inspectSkuId)) {
                jDJSONObject.put("inspectSkuId", (Object) this.mProduct.inspectSkuId);
            }
            ProductDetailEntity productDetailEntity2 = this.mProduct;
            if (productDetailEntity2 != null && !TextUtils.isEmpty(productDetailEntity2.storeId)) {
                jDJSONObject.put("storeId", (Object) this.mProduct.storeId);
            }
            AddressGlobal addressGlobal = AddressUtil.getAddressGlobal();
            if (addressGlobal != null) {
                jDJSONObject.put("uAddrId", (Object) String.valueOf(addressGlobal.getId()));
            }
            boolean z = addressGlobal != null && (addressGlobal.getIsUserAddress().booleanValue() || !TextUtils.isEmpty(this.mProduct.storeId));
            if (z && !TextUtils.isEmpty(addressGlobal.getLatitude())) {
                jDJSONObject.put(PdLVBody.GCLAT, (Object) addressGlobal.getLatitude());
            }
            if (z && !TextUtils.isEmpty(addressGlobal.getLongitude())) {
                jDJSONObject.put(PdLVBody.GCLNG, (Object) addressGlobal.getLongitude());
            }
        } catch (Exception e2) {
            if (Log.D) {
                Log.d(TAG, e2.getMessage());
            }
        }
        return jDJSONObject;
    }

    public JDJSONObject buildWareBusinessParam(String str, String str2, Bundle bundle) {
        LocShopInfo locShopInfo;
        AddressGlobal addressGlobal;
        JDJSONObject buildDefaultSkuParameters = buildDefaultSkuParameters(str);
        buildDefaultSkuParameters.put(CartConstant.KEY_SOURCE_TYPE, (Object) str2);
        buildDefaultSkuParameters.put("eventId", (Object) JDMtaUtils.getLastEventId());
        buildDefaultSkuParameters.put("pluginVersion", (Object) Integer.valueOf((int) PDUtils.pluginVersion));
        Boolean bool = Boolean.TRUE;
        buildDefaultSkuParameters.put("lego", (Object) bool);
        if (bundle != null) {
            String bbtf = PDUtils.getBbtf(bundle);
            if (!TextUtils.isEmpty(bbtf)) {
                buildDefaultSkuParameters.put(LibPdStyleInfoViewUtils.BBTF, (Object) bbtf);
            }
            if (!TextUtils.isEmpty(bundle.getString(LibPdStyleInfoViewUtils.EXTRA_ROOMID))) {
                Log.d("yueliang", "buildWareBusinessParam EXTRA_ROOMID = " + bundle.getString(LibPdStyleInfoViewUtils.EXTRA_ROOMID));
                buildDefaultSkuParameters.put(VideoPerfEntity.FIELD_ROOM_ID, (Object) bundle.getString(LibPdStyleInfoViewUtils.EXTRA_ROOMID));
            }
            if (!TextUtils.isEmpty(bundle.getString(LibPdStyleInfoViewUtils.EXTRA_BUSINESS_ORIGIN))) {
                buildDefaultSkuParameters.put("businessOrigin", (Object) bundle.getString(LibPdStyleInfoViewUtils.EXTRA_BUSINESS_ORIGIN));
                Log.d("yueliang", "buildWareBusinessParam EXTRA_BUSINESS_ORIGIN = " + bundle.getString(LibPdStyleInfoViewUtils.EXTRA_BUSINESS_ORIGIN));
            }
        }
        String authCodeTokenParam = PDDataUtil.getAuthCodeTokenParam();
        if (!TextUtils.isEmpty(authCodeTokenParam)) {
            buildDefaultSkuParameters.put("token", (Object) authCodeTokenParam);
            buildDefaultSkuParameters.put("pdbp", (Object) "0");
        }
        String businessId = AdvertUtils.getBusinessId();
        if (!TextUtils.isEmpty(businessId)) {
            buildDefaultSkuParameters.put("bizType", (Object) WebEntity.VALUE_ONEKEYLOGIN_KEPLER);
            buildDefaultSkuParameters.put("bizId", (Object) businessId);
        }
        boolean z = SharedPreferencesUtil.getBoolean("isDesCbc", false);
        buildDefaultSkuParameters.put("isDesCbc", (Object) Boolean.valueOf(z));
        if (LoginUserBase.hasLogin() && (addressGlobal = AddressUtil.getAddressGlobal()) != null && addressGlobal.getIsUserAddress().booleanValue() && !TextUtils.isEmpty(addressGlobal.getWhere())) {
            if (Log.D) {
                Log.d(TAG, "global.getAddressDetail() = " + addressGlobal.getWhere());
            }
            String encryptThreeDESECB = PDUtils.encryptThreeDESECB(addressGlobal.getWhere(), z, "yc2JffcREheFQlYFIAY5f9sY7uflgBTo");
            if (!TextUtils.isEmpty(encryptThreeDESECB)) {
                if (Log.D) {
                    Log.d(TAG, "address = " + encryptThreeDESECB);
                    Log.d(TAG, "decryptThreeDESECB address = " + PDUtils.decryptThreeDESECB(encryptThreeDESECB, z, "yc2JffcREheFQlYFIAY5f9sY7uflgBTo"));
                }
                buildDefaultSkuParameters.put("fullAddress", (Object) encryptThreeDESECB);
            }
        }
        if (this.location == null) {
            this.location = JDLocationCache.getInstance().getLocation(PDUtils.getLocationCacheOption());
        }
        JDLocation jDLocation = this.location;
        if (jDLocation != null) {
            buildDefaultSkuParameters.put(PdLVBody.LATITUDE, (Object) String.valueOf(jDLocation.getLat()));
            buildDefaultSkuParameters.put(PdLVBody.LONGITUDE, (Object) String.valueOf(this.location.getLng()));
        }
        WareBusinessData wareBusinessData = this.mProduct.mWareBusinessData;
        if (wareBusinessData != null && (locShopInfo = wareBusinessData.locShopInfo) != null && !TextUtils.isEmpty(locShopInfo.locShopId)) {
            LocShopInfo locShopInfo2 = this.mProduct.mWareBusinessData.locShopInfo;
            if (locShopInfo2.isNeedLocShopId) {
                buildDefaultSkuParameters.put("locShopId", (Object) locShopInfo2.locShopId);
            }
        }
        if (!TextUtils.isEmpty(this.mProduct.mLocShopId)) {
            buildDefaultSkuParameters.put("locShopId", (Object) this.mProduct.mLocShopId);
        }
        buildDefaultSkuParameters.put("avoidLive", (Object) Boolean.valueOf(this.mProduct.avoidLive));
        buildDefaultSkuParameters.put("abTest800", (Object) bool);
        buildDefaultSkuParameters.put("plusClickCount", (Object) Integer.valueOf(SharedPreferencesUtil.getInt("plusClickCount", 0)));
        JDLocation jDLocation2 = this.location;
        if (jDLocation2 != null) {
            buildDefaultSkuParameters.put(JDWeatherActionKey.PROVINCE_ID, (Object) String.valueOf(jDLocation2.getProvinceId()));
            buildDefaultSkuParameters.put(JDWeatherActionKey.CITY_ID, (Object) Integer.valueOf(this.location.getCityId()));
            buildDefaultSkuParameters.put("districtId", (Object) Integer.valueOf(this.location.getDistrictId()));
            buildDefaultSkuParameters.put(JDWeatherActionKey.TOWN_ID, (Object) Integer.valueOf(this.location.getTownId()));
        }
        buildDefaultSkuParameters.put(JDNetCacheManager.BRAND_BIZKEY, (Object) BaseInfo.getDeviceBrand());
        buildDefaultSkuParameters.put(CustomThemeConstance.NAVI_MODEL, (Object) BaseInfo.getDeviceModel());
        buildDefaultSkuParameters.put("plusLandedFatigue", (Object) Integer.valueOf(PDUtils.isToday(SharedPreferencesUtil.getString("pd_plus_weary_day", "0")) ? SharedPreferencesUtil.getInt("pd_plus_weary_times", 0) : 0));
        buildDefaultSkuParameters.put("ocrFlag", (Object) CommonBase.getBooleanFromPreference(PersonalConstants.SP_SETTING_OCR, Boolean.FALSE));
        if (!TextUtils.isEmpty(this.mProduct.businessOrigin)) {
            buildDefaultSkuParameters.put("channelId", (Object) this.mProduct.businessOrigin);
        }
        if (!TextUtils.isEmpty(this.mProduct.roomId)) {
            buildDefaultSkuParameters.put("authorId", (Object) this.mProduct.roomId);
        }
        if (!TextUtils.isEmpty(this.mProduct.supperRoomPromo)) {
            buildDefaultSkuParameters.put(LibPdStyleInfoViewUtils.EXTRA_SUPPER_ROOM_PROMO, (Object) this.mProduct.supperRoomPromo);
        }
        return buildDefaultSkuParameters;
    }
}

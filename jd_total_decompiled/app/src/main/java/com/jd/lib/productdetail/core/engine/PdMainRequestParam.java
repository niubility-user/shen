package com.jd.lib.productdetail.core.engine;

import android.os.Bundle;
import android.text.TextUtils;
import com.jd.dynamic.DYConstants;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONObject;
import com.jd.lib.productdetail.core.entitys.ProductDetailEntity;
import com.jd.lib.productdetail.core.entitys.loc.LocShopInfo;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessData;
import com.jd.lib.productdetail.core.protocol.PdLVBody;
import com.jd.lib.productdetail.core.utils.PDDataUtil;
import com.jd.lib.productdetail.core.utils.PDUtils;
import com.jingdong.app.mall.bundle.jdweather.action.JDWeatherActionKey;
import com.jingdong.app.mall.bundle.mobileConfig.JDMobileConfig;
import com.jingdong.app.mall.bundle.styleinfoview.LibPdStyleInfoViewUtils;
import com.jingdong.app.mall.recommend.PerRecRouterImpl;
import com.jingdong.common.entity.AddressGlobal;
import com.jingdong.common.entity.SourceEntity;
import com.jingdong.common.entity.personal.PersonalConstants;
import com.jingdong.common.jdmiaosha.utils.cache.JDNetCacheManager;
import com.jingdong.common.lbs.jdlocation.JDLocation;
import com.jingdong.common.lbs.jdlocation.JDLocationCache;
import com.jingdong.common.login.LoginUserBase;
import com.jingdong.common.unification.customtheme.CustomThemeConstance;
import com.jingdong.common.utils.AddressUtil;
import com.jingdong.common.utils.AdvertUtils;
import com.jingdong.common.utils.CommonBase;
import com.jingdong.common.utils.DeepDarkChangeManager;
import com.jingdong.common.web.entity.WebEntity;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdma.JDMaInterface;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.jdsdk.constant.PDConstant;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.jdsdk.utils.SharedPreferencesUtil;
import com.jingdong.sdk.baseinfo.BaseInfo;
import java.io.Serializable;
import java.util.HashMap;
import tv.danmaku.ijk.media.ext.report.ReportConstant;

/* loaded from: classes15.dex */
public class PdMainRequestParam {
    private static final String DESKEY = "yc2JffcREheFQlYFIAY5f9sY7uflgBTo";
    private static final String TAG = "PdMainRequestParam";
    public static final int TYPE_CHANGE_ADDRESS = 1;
    public static final int TYPE_NORMAL = 0;
    private JDLocation location = JDLocationCache.getInstance().getLocation(PDUtils.getLocationCacheOption(false));
    private final ProductDetailEntity mProduct;

    public PdMainRequestParam(ProductDetailEntity productDetailEntity) {
        this.mProduct = productDetailEntity;
    }

    private JDJSONObject buildDefaultSkuParameters(String str, Bundle bundle) {
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
            ProductDetailEntity productDetailEntity3 = this.mProduct;
            if (productDetailEntity3 != null && !TextUtils.isEmpty(productDetailEntity3.daoDianStoreId)) {
                jDJSONObject.put("daoDianStoreId", (Object) this.mProduct.daoDianStoreId);
            }
            if (bundle != null) {
                if (bundle.getString("jxzs") != null) {
                    jDJSONObject.put("jxzs", (Object) bundle.getString("jxzs"));
                }
                if (bundle.getString(PDConstant.EXTRA_CSKU) != null) {
                    jDJSONObject.put(PDConstant.EXTRA_CSKU, (Object) bundle.getString(PDConstant.EXTRA_CSKU));
                }
                if (bundle.getString("index") != null) {
                    jDJSONObject.put("index", (Object) bundle.getString("index"));
                }
                if (bundle.getString(PDConstant.EXTRA_RID) != null) {
                    jDJSONObject.put(PDConstant.EXTRA_RID, (Object) bundle.getString(PDConstant.EXTRA_RID));
                }
                if (bundle.getString(PDConstant.EXTRA_EXPID) != null) {
                    jDJSONObject.put(PDConstant.EXTRA_EXPID, (Object) bundle.getString(PDConstant.EXTRA_EXPID));
                }
                if (!TextUtils.isEmpty(bundle.getString("from"))) {
                    jDJSONObject.put("from", (Object) bundle.getString("from"));
                }
                if (bundle.getString("wareInnerSourceHoutai") != null) {
                    jDJSONObject.put("wareInnerSourceHoutai", (Object) bundle.getString("wareInnerSourceHoutai"));
                }
                jDJSONObject.put("selfDelivery", (Object) String.valueOf(bundle.getInt("selfDelivery")));
                if (bundle.getString("targetUrl") != null) {
                    jDJSONObject.put("adClickUrl", (Object) bundle.getString("targetUrl"));
                }
                if (TextUtils.isEmpty(bundle.getString(PDConstant.EXTRA_PERSONAS))) {
                    jDJSONObject.put(PDConstant.EXTRA_PERSONAS, (Object) bundle.getString(PDConstant.EXTRA_PERSONAS));
                }
                Object obj = bundle.get("source");
                if (obj instanceof SourceEntity) {
                    SourceEntity sourceEntity = (SourceEntity) obj;
                    jDJSONObject.put(CartConstant.KEY_SOURCE_TYPE, (Object) sourceEntity.getSourceType());
                    jDJSONObject.put(CartConstant.KEY_SOURCE_VALUE, (Object) sourceEntity.getSourceValue());
                }
                Object obj2 = bundle.get("sourceType");
                if (obj2 instanceof String) {
                    jDJSONObject.put(CartConstant.KEY_SOURCE_TYPE, obj2);
                }
                Object obj3 = bundle.get("sourceValue");
                if (obj2 instanceof String) {
                    jDJSONObject.put(CartConstant.KEY_SOURCE_VALUE, obj3);
                }
                jDJSONObject.put("overseas", (Object) Integer.valueOf(bundle.getInt("overseas")));
                String jdv = JDMaInterface.getJdv();
                if (!TextUtils.isEmpty(jdv)) {
                    jDJSONObject.put("productJdv", (Object) jdv);
                }
                Object obj4 = bundle.get("carModelId");
                if (obj4 instanceof String) {
                    jDJSONObject.put("carModelId", obj4);
                }
            }
            AddressGlobal addressGlobal = AddressUtil.getAddressGlobal();
            if (addressGlobal != null) {
                jDJSONObject.put("uAddrId", (Object) String.valueOf(addressGlobal.getId()));
                jDJSONObject.put("cityCode", (Object) Integer.valueOf(addressGlobal.getIdCity()));
            }
            boolean z = addressGlobal != null && (addressGlobal.getIsUserAddress().booleanValue() || !TextUtils.isEmpty(this.mProduct.storeId));
            if (z && !TextUtils.isEmpty(addressGlobal.getLatitude())) {
                jDJSONObject.put(PdLVBody.GCLAT, (Object) addressGlobal.getLatitude());
            }
            if (z && !TextUtils.isEmpty(addressGlobal.getLongitude())) {
                jDJSONObject.put(PdLVBody.GCLNG, (Object) addressGlobal.getLongitude());
            }
            if (TextUtils.equals(JDMobileConfig.getInstance().getConfig("JDProductdetail", "EuropeAddress", "enable"), DYConstants.DY_TRUE)) {
                jDJSONObject.put("euaf", (Object) Boolean.TRUE);
            } else if (addressGlobal != null) {
                jDJSONObject.put("euaf", (Object) Boolean.valueOf(addressGlobal.isEUCountry()));
            }
        } catch (Exception e2) {
            if (Log.D) {
                Log.d(TAG, e2.getMessage());
            }
        }
        return jDJSONObject;
    }

    public JDJSONObject buildWareBusinessParam(String str, Bundle bundle, int i2, boolean z, String str2, boolean z2) {
        LocShopInfo locShopInfo;
        AddressGlobal addressGlobal;
        JDJSONObject buildDefaultSkuParameters = buildDefaultSkuParameters(str, bundle);
        buildDefaultSkuParameters.put("eventId", (Object) JDMtaUtils.getLastEventId());
        buildDefaultSkuParameters.put("pluginVersion", (Object) Integer.valueOf((int) PDUtils.pluginVersion));
        buildDefaultSkuParameters.put("fromType", (Object) Integer.valueOf(i2));
        buildDefaultSkuParameters.put("yrqNew", "1");
        buildDefaultSkuParameters.put("pdVersion", "1");
        buildDefaultSkuParameters.put("acceptPrivacy", (Object) Boolean.valueOf(z2));
        if (!TextUtils.isEmpty(str2)) {
            buildDefaultSkuParameters.put("wareInnerSource", (Object) str2);
        }
        Boolean bool = Boolean.TRUE;
        buildDefaultSkuParameters.put("lego", (Object) bool);
        if (z) {
            buildDefaultSkuParameters.put("changeUsual", (Object) "1");
        }
        String authCodeTokenParam = PDDataUtil.getAuthCodeTokenParam();
        if (!TextUtils.isEmpty(authCodeTokenParam)) {
            buildDefaultSkuParameters.put("token", (Object) authCodeTokenParam);
            buildDefaultSkuParameters.put("pdbp", (Object) "0");
        }
        if (bundle != null) {
            buildDefaultSkuParameters.put("searchWareflag", (Object) bundle.getString("searchWareflag", ""));
            buildDefaultSkuParameters.put("oneboxChannel", (Object) Boolean.valueOf(bundle.getBoolean("oneboxChannel", false)));
            buildDefaultSkuParameters.put("oneboxSource", (Object) bundle.getString("oneboxSource", ""));
            buildDefaultSkuParameters.put("oneboxKeyword", (Object) bundle.getString("oneboxKeyword", ""));
            if (bundle.containsKey("secKillParams")) {
                buildDefaultSkuParameters.put("secKillParams", (Object) bundle.getString("secKillParams", ""));
                if (bundle.containsKey("addCartTime")) {
                    buildDefaultSkuParameters.put("addCartTime", (Object) bundle.getString("addCartTime", ""));
                }
            }
            Serializable serializable = bundle.getSerializable("passThroughMap");
            if (serializable != null && (serializable instanceof HashMap)) {
                buildDefaultSkuParameters.put("passThroughMap", (Object) JDJSON.parseObject(JDJSON.toJSONString(bundle.getSerializable("passThroughMap"))));
            }
        }
        String businessId = AdvertUtils.getBusinessId();
        if (!TextUtils.isEmpty(businessId)) {
            buildDefaultSkuParameters.put("bizType", (Object) WebEntity.VALUE_ONEKEYLOGIN_KEPLER);
            buildDefaultSkuParameters.put("bizId", (Object) businessId);
        }
        boolean z3 = SharedPreferencesUtil.getBoolean("isDesCbc", false);
        buildDefaultSkuParameters.put("isDesCbc", (Object) Boolean.valueOf(z3));
        if (LoginUserBase.hasLogin() && (addressGlobal = AddressUtil.getAddressGlobal()) != null && addressGlobal.getIsUserAddress().booleanValue() && !TextUtils.isEmpty(addressGlobal.getWhere())) {
            if (Log.D) {
                Log.d(TAG, "global.getAddressDetail() = " + addressGlobal.getWhere());
            }
            String encryptThreeDESECB = PDUtils.encryptThreeDESECB(addressGlobal.getWhere(), z3, "yc2JffcREheFQlYFIAY5f9sY7uflgBTo");
            if (!TextUtils.isEmpty(encryptThreeDESECB)) {
                if (Log.D) {
                    Log.d(TAG, "address = " + encryptThreeDESECB);
                    Log.d(TAG, "decryptThreeDESECB address = " + PDUtils.decryptThreeDESECB(encryptThreeDESECB, z3, "yc2JffcREheFQlYFIAY5f9sY7uflgBTo"));
                }
                buildDefaultSkuParameters.put("fullAddress", (Object) encryptThreeDESECB);
            }
        }
        if (this.location == null) {
            this.location = JDLocationCache.getInstance().getLocation(PDUtils.getLocationCacheOption(false));
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
        ProductDetailEntity productDetailEntity = this.mProduct;
        if (productDetailEntity != null && !productDetailEntity.isForeground) {
            buildDefaultSkuParameters.put("wareInnerSourceHoutai", "houtai");
        }
        if (!TextUtils.isEmpty(this.mProduct.mLocShopId)) {
            buildDefaultSkuParameters.put("locShopId", (Object) this.mProduct.mLocShopId);
        }
        if (!TextUtils.isEmpty(this.mProduct.floor3cTabType)) {
            buildDefaultSkuParameters.put("yjhxtabtype", (Object) this.mProduct.floor3cTabType);
        }
        ProductDetailEntity productDetailEntity2 = this.mProduct;
        if (productDetailEntity2 != null && productDetailEntity2.isPaiPaiSecondOpen() && this.mProduct.isPaiPaiSecond() && !TextUtils.isEmpty(this.mProduct.mPaiPaiInspectIds)) {
            buildDefaultSkuParameters.put("ppInspectParams", (Object) this.mProduct.mPaiPaiInspectIds);
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
        int i3 = this.mProduct.themeStyle;
        if (i3 == 0) {
            buildDefaultSkuParameters.put("darkModelEnum", (Object) 1);
        } else if (i3 == 1) {
            buildDefaultSkuParameters.put("darkModelEnum", (Object) 2);
        } else if (DeepDarkChangeManager.getInstance().getUIMode() == 0) {
            buildDefaultSkuParameters.put("darkModelEnum", (Object) 3);
        } else if (DeepDarkChangeManager.getInstance().getUIMode() == 1) {
            buildDefaultSkuParameters.put("darkModelEnum", (Object) 4);
        }
        buildDefaultSkuParameters.put("plusLandedFatigue", (Object) Integer.valueOf(PDUtils.isToday(SharedPreferencesUtil.getString("pd_plus_weary_day", "0")) ? SharedPreferencesUtil.getInt("pd_plus_weary_times", 0) : 0));
        buildDefaultSkuParameters.put("ocrFlag", (Object) CommonBase.getBooleanFromPreference(PersonalConstants.SP_SETTING_OCR, Boolean.FALSE));
        if (bundle != null) {
            buildDefaultSkuParameters.put("utmMedium", (Object) bundle.getString("utm_medium"));
            buildDefaultSkuParameters.put("cpsNoTuan", (Object) bundle.getString("cpsNoTuan"));
        }
        buildDefaultSkuParameters.put("prstate", (Object) (PerRecRouterImpl.getPerRecStatusValue() ? "0" : "1"));
        buildDefaultSkuParameters.put("isFromOpenApp", (Object) Boolean.valueOf(this.mProduct.isFromOpenApp));
        if (!TextUtils.isEmpty(this.mProduct.liveId)) {
            buildDefaultSkuParameters.put(ReportConstant.CommonInfo.LIVE_ID, (Object) this.mProduct.liveId);
        }
        if (!TextUtils.isEmpty(this.mProduct.channelId)) {
            buildDefaultSkuParameters.put("channelId", (Object) this.mProduct.channelId);
        }
        if (!TextUtils.isEmpty(this.mProduct.authorId)) {
            buildDefaultSkuParameters.put("authorId", (Object) this.mProduct.authorId);
        }
        if (!TextUtils.isEmpty(this.mProduct.supperRoomPromo)) {
            buildDefaultSkuParameters.put(LibPdStyleInfoViewUtils.EXTRA_SUPPER_ROOM_PROMO, (Object) this.mProduct.supperRoomPromo);
        }
        if (!TextUtils.isEmpty(this.mProduct.adBackUrl4Activity)) {
            buildDefaultSkuParameters.put("adback", (Object) this.mProduct.adBackUrl4Activity);
        }
        buildDefaultSkuParameters.put("bbtf", (Object) this.mProduct.bbtf);
        buildDefaultSkuParameters.put("bybt", (Object) this.mProduct.bybt);
        return buildDefaultSkuParameters;
    }
}

package com.jingdong.common.ui.address;

import android.text.TextUtils;
import com.jd.framework.json.JDJSON;
import com.jd.libs.hybrid.HybridSDK;
import com.jingdong.app.mall.bundle.jdweather.action.JDWeatherActionKey;
import com.jingdong.common.UnLog;
import com.jingdong.common.entity.AddressGlobal;
import com.jingdong.common.ui.UnAddressSelectUtils;
import com.jingdong.common.ui.address.entity.UnAddressInfo;
import com.jingdong.common.unification.router.CallBackWithReturnListener;
import com.jingdong.common.utils.AddressUtil;
import com.jingdong.common.web.IRouterParams;
import com.jingdong.jdsdk.constant.CartConstant;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class JDAddressRouterH5 {
    public static final String TAG = "JDAddressRouterH5";

    public static void getCacheAddress(IRouterParams iRouterParams) {
        String str;
        if (iRouterParams == null) {
            if (UnLog.D) {
                UnLog.d(TAG, "IRouterParams is null");
                return;
            }
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(iRouterParams.getRouterParam());
            if (UnLog.D) {
                UnLog.d(TAG, "jsonParam = " + jSONObject.toString());
            }
            str = jSONObject.getString("sceneId");
        } catch (Exception unused) {
            str = "";
        }
        if (TextUtils.isEmpty(str)) {
            str = "marketingActivities";
        }
        UnAddressInfo addressCacheAddressInfo = UnAddressSelectUtils.getAddressCacheAddressInfo(str);
        if (addressCacheAddressInfo != null) {
            try {
                iRouterParams.onCallBack(new JSONObject(JDJSON.toJSONString(addressCacheAddressInfo)));
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
        } else {
            iRouterParams.onCallBack("");
        }
        if (UnLog.D) {
            UnLog.d(TAG, JDJSON.toJSONString(addressCacheAddressInfo));
        }
    }

    public static boolean saveAddress(AddressGlobal addressGlobal) {
        if (addressGlobal == null) {
            return false;
        }
        boolean updateAddressGlobal = AddressUtil.updateAddressGlobal(addressGlobal);
        AddressUtil.onAddressChanged();
        UnAddressInfo addressCacheAddressInfo = UnAddressSelectUtils.getAddressCacheAddressInfo();
        if (UnLog.D) {
            UnLog.d(TAG, "getSaveAddress = " + JDJSON.toJSONString(addressCacheAddressInfo));
        }
        return updateAddressGlobal;
    }

    public static void updateCacheAddress(IRouterParams iRouterParams) {
        if (iRouterParams == null) {
            if (UnLog.D) {
                UnLog.d(TAG, "IRouterParams is null");
                return;
            }
            return;
        }
        boolean z = false;
        try {
            JSONObject jSONObject = new JSONObject(iRouterParams.getRouterParam());
            if (UnLog.D) {
                UnLog.d(TAG, "jsonParam = " + jSONObject.toString());
            }
            AddressGlobal addressGlobal = new AddressGlobal();
            addressGlobal.setProvinceName(jSONObject.optString("provinceName"));
            addressGlobal.setCityName(jSONObject.optString("cityName"));
            addressGlobal.setAreaName(jSONObject.optString("countyName"));
            addressGlobal.setTownName(jSONObject.optString("townName"));
            addressGlobal.setIdProvince(jSONObject.optInt(JDWeatherActionKey.PROVINCE_ID));
            addressGlobal.setIdCity(jSONObject.optInt(JDWeatherActionKey.CITY_ID));
            addressGlobal.setIdArea(jSONObject.optInt("countyId"));
            addressGlobal.setIdTown(jSONObject.optInt(JDWeatherActionKey.TOWN_ID));
            long optLong = jSONObject.optLong(CartConstant.KEY_ADDRESS_ID);
            addressGlobal.setId(optLong);
            if (optLong > 0) {
                addressGlobal.setIsUserAddress(Boolean.TRUE);
            }
            String optString = jSONObject.optString("isUserAddress");
            if (!TextUtils.isEmpty(optString)) {
                addressGlobal.setIsUserAddress(Boolean.valueOf(Boolean.parseBoolean(optString)));
            }
            addressGlobal.setAddressDetail(jSONObject.optString("detailAddress"));
            addressGlobal.setWhere(jSONObject.optString("fullAddress"));
            addressGlobal.setLatitude(jSONObject.optString("lat"));
            addressGlobal.setLongitude(jSONObject.optString(HybridSDK.LNG));
            z = saveAddress(addressGlobal);
            iRouterParams.onCallBack(Boolean.valueOf(z));
        } catch (JSONException e2) {
            e2.printStackTrace();
            iRouterParams.onCallBack(Boolean.valueOf(z));
        }
    }

    public void jumpToMap(final IRouterParams iRouterParams) {
        String str;
        if (iRouterParams == null) {
            return;
        }
        CallBackWithReturnListener callBackWithReturnListener = new CallBackWithReturnListener() { // from class: com.jingdong.common.ui.address.JDAddressRouterH5.1
            @Override // com.jingdong.common.unification.router.CallBackWithReturnListener
            public void onComplete(Object obj) {
                if (obj != null) {
                    iRouterParams.onCallBack(obj);
                }
            }

            @Override // com.jingdong.common.unification.router.CallBackListener
            public void onError(int i2) {
            }

            @Override // com.jingdong.common.unification.router.CallBackListener
            public void onComplete() {
                iRouterParams.onCallBack(null);
            }
        };
        try {
            JSONObject jSONObject = new JSONObject(iRouterParams.getRouterParam());
            if (UnLog.D) {
                UnLog.d(TAG, "jsonParam = " + jSONObject.toString());
            }
            str = jSONObject.getString("sceneId");
        } catch (Exception unused) {
            str = "";
        }
        if (TextUtils.isEmpty(str)) {
            str = "marketingActivities";
        }
        UnAddressSelectUtils.jumpToMap(iRouterParams.getContext(), str, callBackWithReturnListener);
    }
}

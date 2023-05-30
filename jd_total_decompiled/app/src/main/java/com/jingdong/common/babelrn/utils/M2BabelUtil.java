package com.jingdong.common.babelrn.utils;

import android.text.TextUtils;
import android.util.Base64;
import com.jd.framework.json.JDJSONObject;
import com.jd.libs.hybrid.HybridSDK;
import com.jingdong.app.mall.recommend.PerRecRouterImpl;
import com.jingdong.common.entity.AddressGlobal;
import com.jingdong.common.lbs.LocManager;
import com.jingdong.common.lbs.businessAddress.JDBusinessAddressManager;
import com.jingdong.common.lbs.businessAddress.JDGlobalAddressManager;
import com.jingdong.common.lbs.businessAddress.JDUserCityAddress;
import com.jingdong.common.lbs.http.JDLbsHttpOption;
import com.jingdong.common.lbs.jdlocation.JDLocation;
import com.jingdong.common.lbs.jdlocation.JDLocationCache;
import com.jingdong.common.lbs.jdlocation.JDLocationCacheOption;
import com.jingdong.common.unification.customtheme.CustomThemeConstance;
import com.jingdong.common.utils.AddressUtil;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.sdk.baseinfo.BaseInfo;
import com.jingdong.sdk.eldermode.util.JDElderModeUtils;
import java.util.Random;

/* loaded from: classes5.dex */
public class M2BabelUtil {
    private static final String LBS_ID = "2b8ad271e577175adc9f0e7b93e76592";
    private static final String TAG = "M2BabelUtil";

    public static String encryptAddr(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        try {
            String trim = Base64.encodeToString(str.getBytes("utf-8"), 2).trim();
            int length = trim.length() - 2;
            if (length <= 0) {
                if (Log.E) {
                    Log.e(TAG, "encrypt str error: encode to Base64 Error, after encoded: " + trim);
                }
                return "";
            }
            int nextInt = new Random().nextInt(5) + 5;
            return (getRandomString(nextInt) + trim.substring(0, length) + nextInt + trim.substring(length)).trim();
        } catch (Exception e2) {
            if (Log.E) {
                Log.e(TAG, e2.getMessage(), e2);
            }
            return "";
        }
    }

    public static String getBabelParam() {
        JDJSONObject jDJSONObject = new JDJSONObject();
        JDLbsHttpOption jDLbsHttpOption = new JDLbsHttpOption(LBS_ID);
        jDLbsHttpOption.setSceneId("marketingActivities");
        AddressGlobal addressGlobal = JDGlobalAddressManager.getAddressGlobal(jDLbsHttpOption);
        jDJSONObject.put("gLng", (Object) (addressGlobal == null ? "" : addressGlobal.getLongitude()));
        jDJSONObject.put("gLat", (Object) (addressGlobal == null ? "" : addressGlobal.getLatitude()));
        JDUserCityAddress cachedUserCityAddress = JDBusinessAddressManager.getInstance().getCachedUserCityAddress();
        jDJSONObject.put("dLng", (Object) (cachedUserCityAddress == null ? "" : Double.valueOf(cachedUserCityAddress.getLng())));
        jDJSONObject.put("dLat", (Object) (cachedUserCityAddress == null ? "" : Double.valueOf(cachedUserCityAddress.getLat())));
        JDLocationCacheOption jDLocationCacheOption = new JDLocationCacheOption();
        jDLocationCacheOption.setBusinessId(LBS_ID);
        jDLocationCacheOption.setSceneId("marketingActivities");
        JDLocation location = JDLocationCache.getInstance().getLocation(jDLocationCacheOption);
        jDJSONObject.put(HybridSDK.LNG, (Object) Double.valueOf(location != null ? location.getLng() : 0.0d));
        jDJSONObject.put("lat", (Object) Double.valueOf(location != null ? location.getLat() : 0.0d));
        AddressGlobal addressGlobal2 = AddressUtil.getAddressGlobal("marketingActivities");
        jDJSONObject.put(CartConstant.KEY_ADDRESS_ID, (Object) (addressGlobal2 == null ? "" : Long.valueOf(addressGlobal2.getId())));
        jDJSONObject.put("posLng", (Object) (addressGlobal2 == null ? "" : addressGlobal2.getLongitude()));
        jDJSONObject.put("posLat", (Object) (addressGlobal2 != null ? addressGlobal2.getLatitude() : ""));
        jDJSONObject.put("gps_area", (Object) getCommonLbsParameter(location));
        jDJSONObject.put("un_area", (Object) LocManager.getCommonLbsParameter());
        jDJSONObject.put(CustomThemeConstance.NAVI_MODEL, (Object) BaseInfo.getDeviceModel());
        jDJSONObject.put("prstate", (Object) (PerRecRouterImpl.getPerRecStatusValue() ? "0" : "1"));
        if (!TextUtils.isEmpty(JDElderModeUtils.getUemps())) {
            jDJSONObject.put("uemps", (Object) JDElderModeUtils.getUemps());
        }
        return encryptAddr(jDJSONObject.toJSONString());
    }

    public static String getCommonLbsParameter(JDLocation jDLocation) {
        if (jDLocation == null) {
            return null;
        }
        return jDLocation.getProvinceId() + CartConstant.KEY_YB_INFO_LINK + jDLocation.getCityId() + CartConstant.KEY_YB_INFO_LINK + jDLocation.getDistrictId() + CartConstant.KEY_YB_INFO_LINK + jDLocation.getTownId();
    }

    private static String getRandomString(int i2) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i3 = 0; i3 < i2; i3++) {
            int nextInt = random.nextInt(3);
            if (nextInt == 0) {
                sb.append((char) Math.round((Math.random() * 25.0d) + 65.0d));
            } else if (nextInt == 1) {
                sb.append((char) Math.round((Math.random() * 25.0d) + 97.0d));
            } else if (nextInt == 2) {
                sb.append(new Random().nextInt(10));
            }
        }
        return sb.toString();
    }
}

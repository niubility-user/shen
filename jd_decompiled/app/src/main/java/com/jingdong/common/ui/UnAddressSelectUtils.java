package com.jingdong.common.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.text.TextUtils;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONObject;
import com.jd.libs.hybrid.HybridSDK;
import com.jingdong.app.mall.bundle.mobileConfig.JDMobileConfig;
import com.jingdong.common.entity.AddressGlobal;
import com.jingdong.common.lbs.jdlocation.JDLocation;
import com.jingdong.common.lbs.jdlocation.JDLocationCache;
import com.jingdong.common.lbs.jdlocation.JDLocationCacheOption;
import com.jingdong.common.lbs.jdlocation.JDLocationError;
import com.jingdong.common.lbs.jdlocation.JDLocationListener;
import com.jingdong.common.lbs.jdlocation.JDLocationManager;
import com.jingdong.common.lbs.jdlocation.JDLocationOption;
import com.jingdong.common.network.HttpGroupUtils;
import com.jingdong.common.permission.LBSSceneSwitchHelper;
import com.jingdong.common.permission.PermissionHelper;
import com.jingdong.common.runTimeConfig.ConfigUtil;
import com.jingdong.common.ui.address.UnAddressConstants;
import com.jingdong.common.ui.address.UnAddressEnableHelper;
import com.jingdong.common.ui.address.UnAddressMiddleActivity;
import com.jingdong.common.ui.address.entity.AddressPageParams;
import com.jingdong.common.ui.address.entity.UnAddressInfo;
import com.jingdong.common.ui.address.listener.OnAddressCoverageListener;
import com.jingdong.common.ui.address.listener.OnAddressInfoListener;
import com.jingdong.common.ui.address.listener.OnShortAddressListener;
import com.jingdong.common.ui.address.listener.OnSingleAddressCoverageListener;
import com.jingdong.common.ui.homemix.HomeMixHelper;
import com.jingdong.common.ui.homemix.OnRequestCoverageListener;
import com.jingdong.common.ui.homemix.entity.Coverage;
import com.jingdong.common.ui.homemix.entity.ShopParam;
import com.jingdong.common.unification.router.CallBackListener;
import com.jingdong.common.unification.router.CallBackWithReturnListener;
import com.jingdong.common.unification.title.theme.ThemeTitleConstant;
import com.jingdong.common.utils.AddressUtil;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import com.jingdong.jdsdk.utils.JSONObjectProxy;
import com.jingdong.sdk.log.Log;
import com.jingdong.sdk.platform.lib.utils.HostUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes6.dex */
public class UnAddressSelectUtils {
    private static final int CLICK_TIME_SPACE = 300;
    public static final int DEFAULT_DISTANCE = 700;
    public static final String LBS_SCENE_ID_BASE = "basicShoppingProcess";
    public static final String LBS_SCENE_ID_BUSINESS = "marketingActivities";
    public static final String LBS_SCENE_ID_NEARBY = "locService";
    public static final String LBS_SCENE_ID_RECEIVE_ADDRESS = "receiveAddress";
    private static final double RADIUS = 6378.137d;
    private static long lastClickTime;
    public static CallBackWithReturnListener listener;
    public static CallBackWithReturnListener mapListener;

    public static UnAddressInfo addressGlobalToAddressInfo(AddressGlobal addressGlobal) {
        if (addressGlobal == null) {
            return null;
        }
        UnAddressInfo unAddressInfo = new UnAddressInfo();
        unAddressInfo.addressId = addressGlobal.getId();
        unAddressInfo.detailAddress = addressGlobal.getAddressDetail();
        try {
            String str = "0";
            unAddressInfo.lat = Double.valueOf(TextUtils.isEmpty(addressGlobal.getLatitude()) ? "0" : addressGlobal.getLatitude()).doubleValue();
            if (!TextUtils.isEmpty(addressGlobal.getLongitude())) {
                str = addressGlobal.getLongitude();
            }
            unAddressInfo.lng = Double.valueOf(str).doubleValue();
        } catch (Exception e2) {
            if (Log.D) {
                e2.printStackTrace();
            }
        }
        unAddressInfo.provinceId = addressGlobal.getIdProvince();
        unAddressInfo.provinceName = addressGlobal.getProvinceName();
        unAddressInfo.cityName = addressGlobal.getCityName();
        unAddressInfo.cityId = addressGlobal.getIdCity();
        unAddressInfo.countyId = addressGlobal.getIdArea();
        unAddressInfo.countyName = addressGlobal.getAreaName();
        unAddressInfo.townName = addressGlobal.getTownName();
        unAddressInfo.townId = addressGlobal.getIdTown();
        unAddressInfo.isUserAddress = addressGlobal.getIsUserAddress().booleanValue();
        unAddressInfo.isOverSea = addressGlobal.isForeignOverSea;
        unAddressInfo.isGangAoTai = addressGlobal.isGangAoTai;
        unAddressInfo.saveBusiness = addressGlobal.getSaveBusiness();
        unAddressInfo.source = addressGlobal.getSource();
        unAddressInfo.addressTitle = addressGlobal.getAddressTitle();
        if (!TextUtils.isEmpty(addressGlobal.getWhere()) && TextUtils.isEmpty(unAddressInfo.provinceName)) {
            if (!TextUtils.isEmpty(unAddressInfo.provinceName)) {
                unAddressInfo.fourAddress = unAddressInfo.fullAddress.replace(unAddressInfo.detailAddress, "");
            }
        } else {
            unAddressInfo.fourAddress = unAddressInfo.provinceName + unAddressInfo.cityName + unAddressInfo.countyName + unAddressInfo.townName;
        }
        if (TextUtils.isEmpty(addressGlobal.getWhere()) && !TextUtils.isEmpty(unAddressInfo.detailAddress)) {
            unAddressInfo.fullAddress = unAddressInfo.fourAddress + unAddressInfo.detailAddress;
        } else {
            unAddressInfo.fullAddress = addressGlobal.getWhere();
        }
        unAddressInfo.subDetailAddress = addressGlobal.getSubAddressDetail();
        return unAddressInfo;
    }

    public static AddressGlobal addressInfoToAddressGlobal(UnAddressInfo unAddressInfo) {
        if (unAddressInfo == null) {
            return null;
        }
        AddressGlobal addressGlobal = new AddressGlobal();
        addressGlobal.setId(unAddressInfo.addressId);
        addressGlobal.setAddressDetail(unAddressInfo.detailAddress);
        addressGlobal.setWhere(unAddressInfo.fullAddress);
        addressGlobal.setLatitude(String.valueOf(unAddressInfo.lat));
        addressGlobal.setLongitude(String.valueOf(unAddressInfo.lng));
        addressGlobal.setIdProvince(unAddressInfo.provinceId);
        addressGlobal.setProvinceName(unAddressInfo.provinceName);
        addressGlobal.setCityName(unAddressInfo.cityName);
        addressGlobal.setIdCity(unAddressInfo.cityId);
        addressGlobal.setIdArea(unAddressInfo.countyId);
        addressGlobal.setAreaName(unAddressInfo.countyName);
        addressGlobal.setTownName(unAddressInfo.townName);
        addressGlobal.setIdTown(unAddressInfo.townId);
        addressGlobal.setIsUserAddress(Boolean.valueOf(unAddressInfo.isUserAddress));
        addressGlobal.isForeignOverSea = unAddressInfo.isOverSea;
        addressGlobal.isGangAoTai = unAddressInfo.isGangAoTai;
        addressGlobal.setSaveBusiness(unAddressInfo.saveBusiness);
        addressGlobal.setSource(unAddressInfo.source);
        addressGlobal.setAddressTitle(unAddressInfo.addressTitle);
        addressGlobal.setAddressType(unAddressInfo.addressType);
        addressGlobal.setSubAddressDetail(unAddressInfo.subDetailAddress);
        return addressGlobal;
    }

    public static double calculateDistance(double d, double d2, double d3, double d4) {
        double rad = rad(d);
        double rad2 = rad(d3);
        double round = Math.round(Math.asin(Math.sqrt(Math.pow(Math.sin((rad - rad2) / 2.0d), 2.0d) + (Math.cos(rad) * Math.cos(rad2) * Math.pow(Math.sin((rad(d2) - rad(d4)) / 2.0d), 2.0d)))) * 2.0d * RADIUS * 10000.0d);
        Double.isNaN(round);
        return round / 10000.0d;
    }

    public static boolean canOrderShowLocWidget() {
        try {
            String config = JDMobileConfig.getInstance().getConfig("unification", "addressWidgetConfig", "openLocOrder");
            if (TextUtils.isEmpty(config)) {
                return true;
            }
            return TextUtils.equals("1", config);
        } catch (Exception unused) {
            return true;
        }
    }

    public static boolean canReqeustCoverage() {
        try {
            String config = JDMobileConfig.getInstance().getConfig("unification", "addressWidgetConfig", "coverage");
            if (TextUtils.isEmpty(config)) {
                return true;
            }
            return TextUtils.equals("1", config);
        } catch (Exception unused) {
            return true;
        }
    }

    public static boolean canShowLocWidget() {
        try {
            String config = JDMobileConfig.getInstance().getConfig("unification", "addressWidgetConfig", "openLoc");
            if (TextUtils.isEmpty(config)) {
                return true;
            }
            return TextUtils.equals("1", config);
        } catch (Exception unused) {
            return true;
        }
    }

    public static boolean canShowLocWidgetBySource(String str, boolean z) {
        JDJSONObject parseObject;
        try {
            String config = JDMobileConfig.getInstance().getConfig("unification", "addressWidgetConfig", "openLocModule");
            return (TextUtils.isEmpty(config) || (parseObject = JDJSON.parseObject(config)) == null || !parseObject.containsKey(str)) ? z : TextUtils.equals("1", parseObject.getString(str));
        } catch (Exception unused) {
            return z;
        }
    }

    public static Map<String, Double> createMapByInfo(UnAddressInfo unAddressInfo) {
        if (unAddressInfo == null) {
            return null;
        }
        HashMap hashMap = new HashMap();
        hashMap.put(HybridSDK.LNG, Double.valueOf(unAddressInfo.lng));
        hashMap.put("lat", Double.valueOf(unAddressInfo.lat));
        return hashMap;
    }

    public static List<Map<String, Double>> createMapsByInfos(List<UnAddressInfo> list) {
        if (list == null || list.size() <= 0) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        Iterator<UnAddressInfo> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(createMapByInfo(it.next()));
        }
        return arrayList;
    }

    public static List<UnAddressInfo> createOverageList(List<UnAddressInfo> list, List<Coverage> list2) {
        if (list == null) {
            return null;
        }
        if (list2 != null && list2.size() > 0) {
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            for (UnAddressInfo unAddressInfo : list) {
                Iterator<Coverage> it = list2.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    Coverage next = it.next();
                    if (isSamePoint(unAddressInfo.lng, unAddressInfo.lat, next.lng, next.lat)) {
                        unAddressInfo.isCoverage = next.coverageStatus == 1;
                        unAddressInfo.distance = next.distance;
                    }
                }
                if (unAddressInfo.isCoverage) {
                    arrayList.add(unAddressInfo);
                } else {
                    arrayList2.add(unAddressInfo);
                }
            }
            list.clear();
            list.addAll(sort(arrayList));
            list.addAll(sort(arrayList2));
        }
        return list;
    }

    public static AddressGlobal getAddressCacheAddressGlobal(String str) {
        return AddressUtil.getAddressGlobal(str);
    }

    public static UnAddressInfo getAddressCacheAddressInfo(String str) {
        UnAddressInfo addressGlobalToAddressInfo = addressGlobalToAddressInfo(getAddressCacheAddressGlobal(str));
        if (addressGlobalToAddressInfo != null) {
            addressGlobalToAddressInfo.addressType = 1;
        }
        return addressGlobalToAddressInfo;
    }

    public static void getAddressInfo(String str, String str2, String str3, String str4, String str5, OnAddressInfoListener onAddressInfoListener) {
        if (onAddressInfoListener == null) {
            return;
        }
        if (TextUtils.isEmpty(str2) || TextUtils.isEmpty(str3) || TextUtils.isEmpty(str4)) {
            if (onAddressInfoListener != null) {
                onAddressInfoListener.onResult(null);
                return;
            }
            return;
        }
        ShopParam shopParam = new ShopParam();
        shopParam.venderId = str2;
        shopParam.shopId = str3;
        shopParam.shopType = str4;
        getAddressInfo(str, shopParam, str5, onAddressInfoListener);
    }

    public static JDLocation getDimLocation() {
        return new JDLocation();
    }

    public static int getDistanceConfig() {
        try {
            String config = JDMobileConfig.getInstance().getConfig("distance", "addressWidgetConfig", "coverage");
            if (TextUtils.isEmpty(config)) {
                return 700;
            }
            return Integer.valueOf(config).intValue();
        } catch (Exception e2) {
            e2.printStackTrace();
            return 700;
        }
    }

    public static void getLocAddress(final String str, final OnAddressInfoListener onAddressInfoListener) {
        if (onAddressInfoListener == null) {
            return;
        }
        if (!isSceneSwitch(str)) {
            onAddressInfoListener.onResult(getLocAddressInfo(str));
        }
        if (!TextUtils.isEmpty(getLocAddressInfo(str).detailAddress)) {
            onAddressInfoListener.onResult(getLocAddressInfo(str));
            return;
        }
        JDLocationOption jDLocationOption = new JDLocationOption();
        jDLocationOption.setBusinessId("12936d26e791c7059d9c8682182be45a");
        jDLocationOption.setSceneId(str);
        jDLocationOption.setNeedDetail(true);
        JDLocationManager.getInstance().getAddress(jDLocationOption, new JDLocationListener() { // from class: com.jingdong.common.ui.UnAddressSelectUtils.1
            @Override // com.jingdong.common.lbs.jdlocation.JDLocationListener
            public void onFail(JDLocationError jDLocationError) {
                OnAddressInfoListener onAddressInfoListener2 = onAddressInfoListener;
                if (onAddressInfoListener2 != null) {
                    onAddressInfoListener2.onResult(UnAddressSelectUtils.getLocAddressInfo(str));
                }
            }

            @Override // com.jingdong.common.lbs.jdlocation.JDLocationListener
            public void onSuccess(JDLocation jDLocation) {
                OnAddressInfoListener onAddressInfoListener2 = onAddressInfoListener;
                if (onAddressInfoListener2 != null) {
                    onAddressInfoListener2.onResult(UnAddressSelectUtils.locationToAddressInfo(jDLocation));
                }
            }
        });
    }

    public static AddressGlobal getLocAddressGlobal(String str) {
        AddressGlobal addressGlobal = new AddressGlobal();
        JDLocation location = getLocation(str);
        addressGlobal.setAddressDetail(location.getDetailAddress());
        addressGlobal.setProvinceName(location.getProvinceName());
        addressGlobal.setIdProvince(location.getProvinceId());
        addressGlobal.setCityName(location.getCityName());
        addressGlobal.setIdCity(location.getCityId());
        addressGlobal.setAreaName(location.getDistrictName());
        addressGlobal.setIdArea(location.getDistrictId());
        addressGlobal.setTownName(location.getTownName());
        addressGlobal.setIdTown(location.getTownId());
        addressGlobal.setLatitude(String.valueOf(location.getLat()));
        addressGlobal.setLongitude(String.valueOf(location.getLng()));
        addressGlobal.isForeignOverSea = TextUtils.equals(location.getOversea(), "2");
        addressGlobal.isGangAoTai = TextUtils.equals(location.getOversea(), "1");
        return addressGlobal;
    }

    public static UnAddressInfo getLocAddressInfo(String str) {
        UnAddressInfo addressGlobalToAddressInfo = addressGlobalToAddressInfo(getLocAddressGlobal(str));
        if (addressGlobalToAddressInfo != null) {
            addressGlobalToAddressInfo.addressType = 2;
        }
        return addressGlobalToAddressInfo;
    }

    private static JDLocation getLocation(String str) {
        JDLocationCacheOption jDLocationCacheOption = new JDLocationCacheOption();
        jDLocationCacheOption.setBusinessId("12936d26e791c7059d9c8682182be45a");
        jDLocationCacheOption.setSceneId(str);
        return JDLocationCache.getInstance().getLocation(jDLocationCacheOption);
    }

    public static boolean hasLocationPermission() {
        return PermissionHelper.hasGrantedLocation(PermissionHelper.generateBundle(ThemeTitleConstant.TITLE_LOCATION_DRAWABLE_ID, UnAddressSelectUtils.class.getName(), "startPermissionSetting"));
    }

    private static boolean isCanStartActivity() {
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - lastClickTime > 300) {
            lastClickTime = currentTimeMillis;
            return true;
        }
        return false;
    }

    public static boolean isOpenAddressDetail() {
        JDLocation dimLocation = getDimLocation();
        if (dimLocation == null) {
            return true;
        }
        return dimLocation.getRegionId() == 0 && isUseNewLbs() && !isOverseas(dimLocation.getProvinceId());
    }

    public static boolean isOpenGps() {
        LocationManager locationManager = (LocationManager) JdSdk.getInstance().getApplicationContext().getSystemService(ThemeTitleConstant.TITLE_LOCATION_DRAWABLE_ID);
        return locationManager.isProviderEnabled("gps") || locationManager.isProviderEnabled("network");
    }

    public static boolean isOverseas(int i2) {
        return i2 == 32 || i2 == 52993 || i2 == 53283;
    }

    private static boolean isSamePoint(double d, double d2, double d3, double d4) {
        return TextUtils.equals(String.valueOf(d), String.valueOf(d3)) && TextUtils.equals(String.valueOf(d2), String.valueOf(d4));
    }

    public static boolean isSceneSwitch(String str) {
        return LBSSceneSwitchHelper.getLbsSceneSwitch(str);
    }

    public static boolean isUseNewLbs() {
        return ConfigUtil.getKeySwitchState("Newlbs");
    }

    public static synchronized void jumpToMap(Context context, String str, CallBackListener callBackListener) {
        synchronized (UnAddressSelectUtils.class) {
            if (callBackListener instanceof CallBackWithReturnListener) {
                mapListener = (CallBackWithReturnListener) callBackListener;
            }
            if (context == null) {
                CallBackWithReturnListener callBackWithReturnListener = mapListener;
                if (callBackWithReturnListener != null) {
                    callBackWithReturnListener.onError(-1);
                }
                return;
            }
            Intent intent = new Intent();
            intent.putExtra("sceneId", str);
            intent.setClass(context, UnAddressMiddleActivity.class);
            context.startActivity(intent);
        }
    }

    public static boolean lngLatIsEnable(double d, double d2) {
        return d2 != 0.0d && d2 >= -180.0d && d2 <= 180.0d && d != 0.0d && d >= -180.0d && d <= 180.0d;
    }

    public static boolean lngLatIsEnable(String str, String str2) {
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            try {
                return lngLatIsEnable(Double.valueOf(str).doubleValue(), Double.valueOf(str2).doubleValue());
            } catch (Exception e2) {
                if (Log.D) {
                    e2.printStackTrace();
                }
            }
        }
        return false;
    }

    public static UnAddressInfo locationToAddressInfo(JDLocation jDLocation) {
        if (jDLocation == null) {
            return null;
        }
        UnAddressInfo unAddressInfo = new UnAddressInfo();
        unAddressInfo.detailAddress = jDLocation.getDetailAddress();
        unAddressInfo.lat = jDLocation.getLat();
        unAddressInfo.lng = jDLocation.getLng();
        unAddressInfo.provinceId = jDLocation.getProvinceId();
        unAddressInfo.provinceName = jDLocation.getProvinceName();
        unAddressInfo.cityName = jDLocation.getCityName();
        unAddressInfo.cityId = jDLocation.getCityId();
        unAddressInfo.countyId = jDLocation.getDistrictId();
        unAddressInfo.countyName = jDLocation.getDistrictName();
        unAddressInfo.townName = jDLocation.getTownName();
        unAddressInfo.townId = jDLocation.getTownId();
        unAddressInfo.isUserAddress = false;
        unAddressInfo.isOverSea = TextUtils.equals(jDLocation.getOversea(), "2");
        unAddressInfo.isGangAoTai = TextUtils.equals(jDLocation.getOversea(), "1");
        unAddressInfo.fourAddress = unAddressInfo.provinceName + unAddressInfo.cityName + unAddressInfo.countyName + unAddressInfo.townName;
        StringBuilder sb = new StringBuilder();
        sb.append(unAddressInfo.fourAddress);
        sb.append(unAddressInfo.detailAddress);
        unAddressInfo.fullAddress = sb.toString();
        return unAddressInfo;
    }

    public static void multAddreessDeliveryArea(final List<UnAddressInfo> list, ShopParam shopParam, final OnAddressCoverageListener onAddressCoverageListener) {
        if (list == null || list.size() <= 0 || shopParam == null || onAddressCoverageListener == null) {
            return;
        }
        new HomeMixHelper().requestAddressFence(createMapsByInfos(list), shopParam, new OnRequestCoverageListener() { // from class: com.jingdong.common.ui.UnAddressSelectUtils.3
            @Override // com.jingdong.common.ui.homemix.OnRequestCoverageListener
            public void onError() {
                OnAddressCoverageListener onAddressCoverageListener2 = onAddressCoverageListener;
                if (onAddressCoverageListener2 != null) {
                    onAddressCoverageListener2.onResult(list);
                }
            }

            @Override // com.jingdong.common.ui.homemix.OnRequestCoverageListener
            public void onResult(List<Coverage> list2) {
                if (list2 != null && list2.size() > 0) {
                    OnAddressCoverageListener onAddressCoverageListener2 = onAddressCoverageListener;
                    if (onAddressCoverageListener2 != null) {
                        onAddressCoverageListener2.onResult(UnAddressSelectUtils.createOverageList(list, list2));
                        return;
                    }
                    return;
                }
                OnAddressCoverageListener onAddressCoverageListener3 = onAddressCoverageListener;
                if (onAddressCoverageListener3 != null) {
                    onAddressCoverageListener3.onResult(null);
                }
            }
        });
    }

    private static double rad(double d) {
        return (d * 3.141592653589793d) / 180.0d;
    }

    public static void requestShortAddress(final AddressGlobal addressGlobal, final OnShortAddressListener onShortAddressListener) {
        if (addressGlobal == null || TextUtils.isEmpty(addressGlobal.getWhere()) || addressGlobal.getId() <= 0) {
            return;
        }
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setFunctionId("publicThirdAddress");
        httpSetting.putJsonParam("channel", "1");
        httpSetting.putJsonParam("action", "shortAddress");
        httpSetting.putJsonParam(ThemeTitleConstant.TITLE_ADDRESS_DRAWABLE_ID, addressGlobal.getWhere());
        httpSetting.setHost(HostUtils.getWareHost());
        httpSetting.setListener(new HttpGroup.OnAllListener() { // from class: com.jingdong.common.ui.UnAddressSelectUtils.5
            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
            public void onEnd(HttpResponse httpResponse) {
                JSONObjectProxy jSONObject = httpResponse.getJSONObject();
                if (jSONObject != null) {
                    String optString = jSONObject.optString("shortAddress");
                    if (TextUtils.isEmpty(optString)) {
                        OnShortAddressListener onShortAddressListener2 = onShortAddressListener;
                        if (onShortAddressListener2 != null) {
                            onShortAddressListener2.shortAddress(addressGlobal);
                            return;
                        }
                        return;
                    }
                    AddressGlobal addressGlobal2 = AddressUtil.getAddressGlobal();
                    if (addressGlobal2 == null) {
                        OnShortAddressListener onShortAddressListener3 = onShortAddressListener;
                        if (onShortAddressListener3 != null) {
                            onShortAddressListener3.shortAddress(null);
                            return;
                        }
                        return;
                    } else if (addressGlobal.getId() == addressGlobal2.getId()) {
                        addressGlobal.setSubAddressDetail(optString);
                        AddressUtil.updateAddressGlobal(addressGlobal, false);
                    }
                }
                OnShortAddressListener onShortAddressListener4 = onShortAddressListener;
                if (onShortAddressListener4 != null) {
                    onShortAddressListener4.shortAddress(addressGlobal);
                }
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
            public void onError(HttpError httpError) {
                OnShortAddressListener onShortAddressListener2 = onShortAddressListener;
                if (onShortAddressListener2 != null) {
                    onShortAddressListener2.shortAddress(addressGlobal);
                }
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnProgressListener
            public void onProgress(int i2, int i3) {
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnStartListener
            public void onStart() {
            }
        });
        HttpGroupUtils.getHttpGroupaAsynPool().add(httpSetting);
    }

    public static void saveAddress(UnAddressInfo unAddressInfo) {
        if (unAddressInfo == null) {
            return;
        }
        AddressUtil.updateAddressGlobal(addressInfoToAddressGlobal(unAddressInfo));
        AddressUtil.onAddressChanged();
    }

    public static void singleAddreessDeliveryArea(final UnAddressInfo unAddressInfo, ShopParam shopParam, final OnSingleAddressCoverageListener onSingleAddressCoverageListener) {
        if (unAddressInfo == null || shopParam == null || onSingleAddressCoverageListener == null) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(createMapByInfo(unAddressInfo));
        new HomeMixHelper().requestAddressFence(arrayList, shopParam, new OnRequestCoverageListener() { // from class: com.jingdong.common.ui.UnAddressSelectUtils.2
            @Override // com.jingdong.common.ui.homemix.OnRequestCoverageListener
            public void onError() {
                OnSingleAddressCoverageListener onSingleAddressCoverageListener2 = onSingleAddressCoverageListener;
                if (onSingleAddressCoverageListener2 != null) {
                    onSingleAddressCoverageListener2.onResult(unAddressInfo);
                }
            }

            @Override // com.jingdong.common.ui.homemix.OnRequestCoverageListener
            public void onResult(List<Coverage> list) {
                OnSingleAddressCoverageListener onSingleAddressCoverageListener2;
                if (list != null && list.size() > 0) {
                    Coverage coverage = list.get(0);
                    if (coverage == null) {
                        OnSingleAddressCoverageListener onSingleAddressCoverageListener3 = onSingleAddressCoverageListener;
                        if (onSingleAddressCoverageListener3 != null) {
                            onSingleAddressCoverageListener3.onResult(unAddressInfo);
                            return;
                        }
                        return;
                    } else if (coverage == null && (onSingleAddressCoverageListener2 = onSingleAddressCoverageListener) != null) {
                        onSingleAddressCoverageListener2.onResult(unAddressInfo);
                        return;
                    } else {
                        UnAddressInfo unAddressInfo2 = unAddressInfo;
                        unAddressInfo2.distance = coverage.distance;
                        unAddressInfo2.isCoverage = coverage.coverageStatus == 1;
                        OnSingleAddressCoverageListener onSingleAddressCoverageListener4 = onSingleAddressCoverageListener;
                        if (onSingleAddressCoverageListener4 != null) {
                            onSingleAddressCoverageListener4.onResult(unAddressInfo2);
                            return;
                        }
                        return;
                    }
                }
                OnSingleAddressCoverageListener onSingleAddressCoverageListener5 = onSingleAddressCoverageListener;
                if (onSingleAddressCoverageListener5 != null) {
                    onSingleAddressCoverageListener5.onResult(unAddressInfo);
                }
            }
        });
    }

    private static List<UnAddressInfo> sort(List<UnAddressInfo> list) {
        if (list != null && list.size() > 0) {
            Collections.sort(list, new Comparator<UnAddressInfo>() { // from class: com.jingdong.common.ui.UnAddressSelectUtils.4
                @Override // java.util.Comparator
                public int compare(UnAddressInfo unAddressInfo, UnAddressInfo unAddressInfo2) {
                    return unAddressInfo.distance > unAddressInfo2.distance ? 0 : 1;
                }
            });
        }
        return list;
    }

    public static synchronized void startSelectActivity(Context context, int i2, AddressPageParams addressPageParams) {
        synchronized (UnAddressSelectUtils.class) {
            startSelectActivity(context, i2, addressPageParams, (CallBackListener) null);
        }
    }

    public static AddressGlobal getAddressCacheAddressGlobal() {
        return getAddressCacheAddressGlobal(null);
    }

    public static UnAddressInfo getAddressCacheAddressInfo() {
        UnAddressInfo addressGlobalToAddressInfo = addressGlobalToAddressInfo(getAddressCacheAddressGlobal(null));
        if (addressGlobalToAddressInfo != null) {
            addressGlobalToAddressInfo.addressType = 1;
        }
        return addressGlobalToAddressInfo;
    }

    @Deprecated
    public static UnAddressInfo getLocAddressInfo() {
        UnAddressInfo addressGlobalToAddressInfo = addressGlobalToAddressInfo(getLocAddressGlobal(""));
        if (addressGlobalToAddressInfo != null) {
            addressGlobalToAddressInfo.addressType = 2;
        }
        return addressGlobalToAddressInfo;
    }

    public static void saveAddress(AddressGlobal addressGlobal) {
        if (addressGlobal == null) {
            return;
        }
        AddressUtil.updateAddressGlobal(addressGlobal);
        AddressUtil.onAddressChanged();
    }

    public static synchronized void startSelectActivity(Context context, int i2, AddressPageParams addressPageParams, CallBackListener callBackListener) {
        synchronized (UnAddressSelectUtils.class) {
            if (context == null) {
                return;
            }
            if (!isCanStartActivity()) {
                if (callBackListener != null) {
                    callBackListener.onError(-1);
                }
                return;
            }
            Intent intent = new Intent(context, JdAddressSelectActivity.class);
            intent.putExtra(UnAddressConstants.INTENT_PAGE_PARAMS, addressPageParams);
            if (context instanceof Activity) {
                ((Activity) context).startActivityForResult(intent, i2);
            } else {
                intent.addFlags(268435456);
                context.startActivity(intent);
            }
        }
    }

    public static void getAddressInfo(String str, ShopParam shopParam, String str2, OnAddressInfoListener onAddressInfoListener) {
        if (onAddressInfoListener == null) {
            return;
        }
        if (shopParam != null) {
            new UnAddressEnableHelper(shopParam, str2, str, onAddressInfoListener).getAddress();
        } else if (onAddressInfoListener != null) {
            onAddressInfoListener.onResult(null);
        }
    }

    public static synchronized void jumpToMap(Context context, CallBackListener callBackListener) {
        synchronized (UnAddressSelectUtils.class) {
            if (callBackListener instanceof CallBackWithReturnListener) {
                mapListener = (CallBackWithReturnListener) callBackListener;
            }
            if (context == null) {
                CallBackWithReturnListener callBackWithReturnListener = mapListener;
                if (callBackWithReturnListener != null) {
                    callBackWithReturnListener.onError(-1);
                }
                return;
            }
            Intent intent = new Intent();
            intent.setClass(context, UnAddressMiddleActivity.class);
            context.startActivity(intent);
        }
    }

    public static synchronized void startSelectActivity(Context context, int i2, ShopParam shopParam, String str) {
        synchronized (UnAddressSelectUtils.class) {
            startSelectActivity(context, i2, shopParam, str, "", "");
        }
    }

    public static synchronized void startSelectActivity(Context context, int i2, ShopParam shopParam, String str, String str2) {
        synchronized (UnAddressSelectUtils.class) {
            startSelectActivity(context, i2, shopParam, str, str2, "");
        }
    }

    public static synchronized void startSelectActivity(Context context, int i2, ShopParam shopParam, String str, String str2, String str3) {
        synchronized (UnAddressSelectUtils.class) {
            if (context == null) {
                return;
            }
            if (isCanStartActivity()) {
                Intent intent = new Intent(context, JdAddressSelectActivity.class);
                intent.putExtra(UnAddressConstants.INTENT_SHOP_PARAM, shopParam);
                intent.putExtra("sku", str);
                intent.putExtra(UnAddressConstants.INTENT_SAVE_BUSINESS, str2);
                intent.putExtra("source", str3);
                if (context instanceof Activity) {
                    ((Activity) context).startActivityForResult(intent, i2);
                } else {
                    intent.addFlags(268435456);
                    context.startActivity(intent);
                }
            }
        }
    }

    public static synchronized void startSelectActivity(Activity activity, int i2, String str, String str2, String str3, String str4) {
        synchronized (UnAddressSelectUtils.class) {
            startSelectActivity(activity, i2, str, str2, str3, str4, "");
        }
    }

    public static synchronized void startSelectActivity(Activity activity, int i2, String str, String str2, String str3, String str4, String str5) {
        synchronized (UnAddressSelectUtils.class) {
            startSelectActivity(activity, i2, str, str2, str3, str4, str5, "");
        }
    }

    public static synchronized void startSelectActivity(Activity activity, int i2, String str, String str2, String str3, String str4, String str5, String str6) {
        synchronized (UnAddressSelectUtils.class) {
            if (activity == null) {
                return;
            }
            if (isCanStartActivity()) {
                Intent intent = new Intent(activity, JdAddressSelectActivity.class);
                intent.putExtra(UnAddressConstants.INTENT_SHOP_ID, str);
                intent.putExtra("vender_id", str2);
                intent.putExtra(UnAddressConstants.INTENT_SHOP_TYPE, str3);
                intent.putExtra("sku", str4);
                intent.putExtra(UnAddressConstants.INTENT_SAVE_BUSINESS, str5);
                intent.putExtra("source", str6);
                activity.startActivityForResult(intent, i2);
            }
        }
    }

    public static synchronized void startSelectActivity(Context context, int i2, String str, String str2, String str3, String str4, CallBackListener callBackListener) {
        synchronized (UnAddressSelectUtils.class) {
            startSelectActivity(context, i2, str, str2, str3, str4, "", callBackListener);
        }
    }

    public static synchronized void startSelectActivity(Context context, int i2, String str, String str2, String str3, String str4, String str5, CallBackListener callBackListener) {
        synchronized (UnAddressSelectUtils.class) {
            startSelectActivity(context, i2, str, str2, str3, str4, str5, "", callBackListener);
        }
    }

    public static synchronized void startSelectActivity(Context context, int i2, String str, String str2, String str3, String str4, String str5, String str6, CallBackListener callBackListener) {
        synchronized (UnAddressSelectUtils.class) {
            if (context == null) {
                return;
            }
            if (!isCanStartActivity()) {
                if (callBackListener != null) {
                    callBackListener.onError(-1);
                }
                return;
            }
            Intent intent = new Intent(context, JdAddressSelectActivity.class);
            intent.putExtra(UnAddressConstants.INTENT_SHOP_ID, str);
            intent.putExtra("vender_id", str2);
            intent.putExtra(UnAddressConstants.INTENT_SHOP_TYPE, str3);
            intent.putExtra("sku", str4);
            intent.putExtra(UnAddressConstants.INTENT_SAVE_BUSINESS, str5);
            intent.putExtra("source", str6);
            if (context instanceof Activity) {
                ((Activity) context).startActivityForResult(intent, i2);
            } else {
                intent.addFlags(268435456);
                context.startActivity(intent);
            }
        }
    }
}

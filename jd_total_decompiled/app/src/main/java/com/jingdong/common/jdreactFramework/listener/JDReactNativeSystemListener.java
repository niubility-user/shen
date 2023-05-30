package com.jingdong.common.jdreactFramework.listener;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Window;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.jd.lib.cashier.sdk.core.utils.JDDarkUtil;
import com.jd.lib.productdetail.core.protocol.PdLVBody;
import com.jingdong.app.mall.bundle.jdweather.action.JDWeatherActionKey;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.deeplinkhelper.DeeplinkJDpaySdkHelper;
import com.jingdong.common.entity.AddressGlobal;
import com.jingdong.common.entity.settlement.AddressTagInfo;
import com.jingdong.common.jdflutter.JDFlutterCall;
import com.jingdong.common.jdflutter.JDFlutterCallResult;
import com.jingdong.common.jdmiaosha.utils.cache.JDNetCacheManager;
import com.jingdong.common.jdreactFramework.JDCallback;
import com.jingdong.common.jdreactFramework.activities.JDReactNativeBaseActivity;
import com.jingdong.common.jdreactFramework.utils.AbstractJDReactInitialHelper;
import com.jingdong.common.jdreactFramework.utils.AresCommonUtils;
import com.jingdong.common.jdreactFramework.utils.JLog;
import com.jingdong.common.jdreactFramework.utils.RNSoftHideKeyBoardUtil;
import com.jingdong.common.lbs.LocManager;
import com.jingdong.common.lbs.jdlocation.JDLocation;
import com.jingdong.common.lbs.jdlocation.JDLocationCache;
import com.jingdong.common.lbs.jdlocation.JDLocationCacheOption;
import com.jingdong.common.unification.customtheme.CustomThemeConstance;
import com.jingdong.common.unification.statusbar.UnStatusBarTintUtil;
import com.jingdong.common.utils.AddressUtil;
import com.jingdong.common.utils.JDSettingUtils;
import com.jingdong.common.utils.StatisticsReportUtil;
import com.jingdong.common.utils.SwitchQueryFetcher;
import com.jingdong.common.utils.pay.JumpUtils;
import com.jingdong.jdsdk.config.Configuration;
import com.jingdong.sdk.baseinfo.BaseInfo;
import com.jingdong.sdk.oklog.OKLog;
import java.util.HashMap;
import java.util.Locale;

/* loaded from: classes5.dex */
public class JDReactNativeSystemListener implements NativeSystemListener, JDFlutterCall {
    public static final String SYSTEMCHANNEL = "com.jd.jdflutter/system";
    private static final String TAG = "JDReactNativeSystemListener";

    /* loaded from: classes5.dex */
    public class InputRunable implements Runnable {
        BaseActivity activity;
        final JDCallback callback;
        final JDCallback callback1;
        String ss;

        public InputRunable(BaseActivity baseActivity, String str, JDCallback jDCallback, JDCallback jDCallback2) {
            this.activity = baseActivity;
            this.ss = str;
            this.callback = jDCallback;
            this.callback1 = jDCallback2;
        }

        @Override // java.lang.Runnable
        public void run() {
            BaseActivity baseActivity = this.activity;
            if (baseActivity instanceof BaseActivity) {
                baseActivity.post(new Runnable() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeSystemListener.InputRunable.1
                    @Override // java.lang.Runnable
                    public void run() {
                        Window window = InputRunable.this.activity.getWindow();
                        if (window == null) {
                            JDCallback jDCallback = InputRunable.this.callback1;
                            if (jDCallback != null) {
                                jDCallback.invoke(new Object[0]);
                                return;
                            }
                            return;
                        }
                        if ("adjustPan".equals(InputRunable.this.ss)) {
                            window.setSoftInputMode(32);
                        } else if ("adjustNothing".equals(InputRunable.this.ss)) {
                            window.setSoftInputMode(48);
                        } else if ("adjustResize".equals(InputRunable.this.ss)) {
                            window.setSoftInputMode(16);
                        } else if ("adjustUnspecified".equals(InputRunable.this.ss)) {
                            window.setSoftInputMode(0);
                        } else if ("stateAlwaysHidden".equals(InputRunable.this.ss)) {
                            window.setSoftInputMode(3);
                        } else if ("stateAlwaysVisible".equals(InputRunable.this.ss)) {
                            window.setSoftInputMode(5);
                        } else if ("stateHidden".equals(InputRunable.this.ss)) {
                            window.setSoftInputMode(2);
                        } else if ("stateVisible".equals(InputRunable.this.ss)) {
                            window.setSoftInputMode(4);
                        } else if ("stateUnchanged".equals(InputRunable.this.ss)) {
                            window.setSoftInputMode(1);
                        } else if ("stateUnspecified".equals(InputRunable.this.ss)) {
                            window.setSoftInputMode(0);
                        }
                        JDCallback jDCallback2 = InputRunable.this.callback;
                        if (jDCallback2 != null) {
                            jDCallback2.invoke(new Object[0]);
                        }
                    }
                });
            }
        }
    }

    private AddressGlobal assembleAddressGlobal(HashMap hashMap) {
        Object obj;
        Object obj2;
        HashMap hashMap2;
        if (hashMap == null) {
            return null;
        }
        AddressGlobal addressGlobal = new AddressGlobal();
        try {
            if (hashMap.containsKey("id")) {
                obj = "addressDetail";
                obj2 = "mobile";
                addressGlobal.setId((int) ((Double) hashMap.get("id")).doubleValue());
            } else {
                obj = "addressDetail";
                obj2 = "mobile";
            }
            if (hashMap.containsKey("idProvince")) {
                addressGlobal.setIdProvince((int) ((Double) hashMap.get("idProvince")).doubleValue());
            }
            if (hashMap.containsKey("idCity")) {
                addressGlobal.setIdCity((int) ((Double) hashMap.get("idCity")).doubleValue());
            }
            if (hashMap.containsKey("idTown")) {
                addressGlobal.setIdTown((int) ((Double) hashMap.get("idTown")).doubleValue());
            }
            if (hashMap.containsKey("idArea")) {
                addressGlobal.setIdArea((int) ((Double) hashMap.get("idArea")).doubleValue());
            }
            if (hashMap.containsKey("where")) {
                addressGlobal.setWhere((String) hashMap.get("where"));
            }
            if (hashMap.containsKey("provinceName")) {
                addressGlobal.setProvinceName((String) hashMap.get("provinceName"));
            }
            if (hashMap.containsKey("cityName")) {
                addressGlobal.setCityName((String) hashMap.get("cityName"));
            }
            if (hashMap.containsKey("townName")) {
                addressGlobal.setTownName((String) hashMap.get("townName"));
            }
            if (hashMap.containsKey("areaName")) {
                addressGlobal.setAreaName((String) hashMap.get("areaName"));
            }
            if (hashMap.containsKey("isUserAddress")) {
                addressGlobal.setIsUserAddress(Boolean.valueOf(((Boolean) hashMap.get("isUserAddress")).booleanValue()));
            }
            if (hashMap.containsKey("name")) {
                addressGlobal.setName((String) hashMap.get("name"));
            }
            Object obj3 = obj2;
            if (hashMap.containsKey(obj3)) {
                addressGlobal.setMobile((String) hashMap.get(obj3));
            }
            Object obj4 = obj;
            if (hashMap.containsKey(obj4)) {
                addressGlobal.setAddressDetail((String) hashMap.get(obj4));
            }
            if (hashMap.containsKey(PdLVBody.LONGITUDE)) {
                addressGlobal.setLongitude((String) hashMap.get(PdLVBody.LONGITUDE));
            }
            if (hashMap.containsKey(PdLVBody.LATITUDE)) {
                addressGlobal.setLatitude((String) hashMap.get(PdLVBody.LATITUDE));
            }
            if (hashMap.containsKey("CoordType")) {
                addressGlobal.setCoordType((String) hashMap.get("CoordType"));
            }
            if (hashMap.containsKey("timeStamp")) {
                addressGlobal.setTimeStamp((int) ((Double) hashMap.get("timeStamp")).doubleValue());
            }
            if (hashMap.containsKey("addressDefault")) {
                addressGlobal.setAddressDefault(Boolean.valueOf(((Boolean) hashMap.get("addressDefault")).booleanValue()));
            }
            if (hashMap.containsKey("addressTagMap") && (hashMap2 = (HashMap) hashMap.get("addressTagMap")) != null) {
                AddressTagInfo addressTagInfo = new AddressTagInfo();
                if (hashMap2.containsKey("addressTagType")) {
                    addressTagInfo.addressTagType = (int) ((Double) hashMap2.get("addressTagType")).doubleValue();
                }
                if (hashMap2.containsKey("addressTagName")) {
                    addressTagInfo.addressTagName = (String) hashMap2.get("addressTagName");
                }
                if (hashMap2.containsKey("addressTagId")) {
                    addressTagInfo.addressTagId = (int) ((Double) hashMap2.get("addressTagId")).doubleValue();
                }
                addressGlobal.addressTagMap = addressTagInfo;
            }
            if (hashMap.containsKey("isForeignOverSea")) {
                addressGlobal.isForeignOverSea = ((Boolean) hashMap.get("isForeignOverSea")).booleanValue();
            }
            if (hashMap.containsKey("isGangAoTai")) {
                addressGlobal.isGangAoTai = ((Boolean) hashMap.get("isGangAoTai")).booleanValue();
            }
            if (hashMap.containsKey("areaCode")) {
                addressGlobal.areaCode = (String) hashMap.get("areaCode");
            }
            if (hashMap.containsKey("postCode")) {
                addressGlobal.postCode = (String) hashMap.get("postCode");
            }
            if (hashMap.containsKey("email")) {
                addressGlobal.email = (String) hashMap.get("email");
            }
            if (hashMap.containsKey("nameCode")) {
                addressGlobal.nameCode = (String) hashMap.get("nameCode");
            }
        } catch (Exception e2) {
            OKLog.e(TAG, e2);
        }
        return addressGlobal;
    }

    private void invokeCallback(JDCallback jDCallback, Object... objArr) {
        if (jDCallback != null) {
            jDCallback.invoke(objArr);
        }
    }

    @Override // com.jingdong.common.jdreactFramework.listener.NativeSystemListener
    public void changeStausBarColor(String str, JDCallback jDCallback, JDCallback jDCallback2) {
        try {
            Activity currentMyActivity = AbstractJDReactInitialHelper.getCurrentMyActivity();
            if (currentMyActivity != null && (currentMyActivity instanceof JDReactNativeBaseActivity) && str != null) {
                ((JDReactNativeBaseActivity) currentMyActivity).changeStatusBarColor(Color.parseColor(str));
                StringBuilder sb = new StringBuilder();
                String str2 = TAG;
                sb.append(str2);
                sb.append("-changeStausBarColor StatusBarHeight");
                JLog.e(sb.toString(), UnStatusBarTintUtil.getStatusBarHeight(currentMyActivity) + "");
                JLog.e(str2 + "-changeStausBarColor NavigationBarHeight", UnStatusBarTintUtil.getNavigationBarHeight(currentMyActivity) + "");
            }
            if (jDCallback != null) {
                jDCallback.invoke(new Object[0]);
            }
        } catch (Exception e2) {
            OKLog.e(TAG, e2);
            if (jDCallback2 != null) {
                jDCallback2.invoke(new Object[0]);
            }
        }
    }

    @Override // com.jingdong.common.jdreactFramework.listener.NativeSystemListener
    public String desDecode(HashMap hashMap) {
        return "";
    }

    @Override // com.jingdong.common.jdreactFramework.listener.NativeSystemListener
    public String desEncode(HashMap hashMap) {
        return "";
    }

    @Override // com.jingdong.common.jdreactFramework.listener.NativeSystemListener
    public boolean doPay(HashMap hashMap) {
        return false;
    }

    @Override // com.jingdong.common.jdreactFramework.listener.NativeSystemListener
    public boolean doWeiXinLogin(HashMap hashMap) {
        return false;
    }

    @Override // com.jingdong.common.jdreactFramework.listener.NativeSystemListener
    public void getAndroidID(JDCallback jDCallback, JDCallback jDCallback2) {
        try {
            String androidId = BaseInfo.getAndroidId();
            JLog.d("privacy:", "AndroidId:" + androidId);
            if (jDCallback != null) {
                jDCallback.invoke(androidId);
            }
        } catch (Exception unused) {
            if (jDCallback2 != null) {
                jDCallback2.invoke(new Object[0]);
            }
        }
    }

    @Override // com.jingdong.common.jdreactFramework.listener.NativeSystemListener
    public void getCacheAddress(JDCallback jDCallback, JDCallback jDCallback2) {
        getCacheAddressScene("", jDCallback, jDCallback2);
    }

    @Override // com.jingdong.common.jdreactFramework.listener.NativeSystemListener
    public void getCacheAddressScene(String str, JDCallback jDCallback, JDCallback jDCallback2) {
        try {
            AddressGlobal addressGlobal = AddressUtil.getAddressGlobal(str);
            if (addressGlobal == null) {
                jDCallback2.invoke(new Object[0]);
                return;
            }
            WritableMap createMap = Arguments.createMap();
            createMap.putDouble(JDWeatherActionKey.CITY_ID, addressGlobal.getIdCity());
            createMap.putString("cityName", addressGlobal.getCityName());
            createMap.putString("provinceName", addressGlobal.getProvinceName());
            createMap.putDouble(JDWeatherActionKey.PROVINCE_ID, addressGlobal.getIdProvince());
            createMap.putDouble("areaId", addressGlobal.getIdArea());
            createMap.putString("areaName", addressGlobal.getAreaName());
            createMap.putDouble(JDWeatherActionKey.TOWN_ID, addressGlobal.getIdTown());
            createMap.putString("townName", addressGlobal.getTownName());
            createMap.putString(LocManager.LAT_KEY, addressGlobal.getLatitude());
            createMap.putString(LocManager.LNG_KEY, addressGlobal.getLongitude());
            createMap.putString("addressDetail", addressGlobal.getAddressDetail());
            createMap.putDouble("addressID", addressGlobal.getId());
            createMap.putString("where", addressGlobal.getWhere());
            jDCallback.invoke(createMap);
        } catch (Exception unused) {
            jDCallback2.invoke(new Object[0]);
        }
    }

    @Override // com.jingdong.common.jdreactFramework.listener.NativeSystemListener
    public void getCartUUID(JDCallback jDCallback, JDCallback jDCallback2) {
        try {
            String readCartUUID = StatisticsReportUtil.readCartUUID();
            JLog.d("JDReactNativeSystemModule", "getCartUUID result =" + readCartUUID);
            if (jDCallback != null) {
                jDCallback.invoke(readCartUUID);
            }
        } catch (Exception e2) {
            JLog.e(TAG, e2.toString());
            if (jDCallback2 != null) {
                jDCallback2.invoke(new Object[0]);
            }
        }
    }

    @Override // com.jingdong.common.jdreactFramework.listener.NativeSystemListener
    public void getCurrentAddress(JDCallback jDCallback, JDCallback jDCallback2) {
        getCurrentAddressScene("", jDCallback, jDCallback2);
    }

    @Override // com.jingdong.common.jdreactFramework.listener.NativeSystemListener
    public void getCurrentAddressScene(String str, JDCallback jDCallback, JDCallback jDCallback2) {
        try {
            JDLocationCacheOption jDLocationCacheOption = new JDLocationCacheOption();
            jDLocationCacheOption.setBusinessId(JDReactNativeLBSListener.BUSINESSID);
            jDLocationCacheOption.setSceneId(str);
            WritableMap createMap = Arguments.createMap();
            JDLocation location = JDLocationCache.getInstance().getLocation(jDLocationCacheOption);
            createMap.putDouble(JDWeatherActionKey.CITY_ID, location.getCityId());
            createMap.putString("cityName", location.getCityName());
            createMap.putString("provinceName", location.getProvinceName());
            createMap.putDouble(JDWeatherActionKey.PROVINCE_ID, location.getProvinceId());
            createMap.putDouble(LocManager.LAT_KEY, location.getLat());
            createMap.putDouble(LocManager.LNG_KEY, location.getLng());
            createMap.putDouble("districtId", location.getDistrictId());
            createMap.putString("districtName", location.getDistrictName());
            jDCallback.invoke(createMap);
        } catch (Exception unused) {
            jDCallback2.invoke(new Object[0]);
        }
    }

    @Override // com.jingdong.common.jdreactFramework.listener.NativeSystemListener
    public void getDeviceID(JDCallback jDCallback, JDCallback jDCallback2) {
        try {
            String readDeviceUUID = StatisticsReportUtil.readDeviceUUID();
            if (jDCallback != null) {
                jDCallback.invoke(readDeviceUUID);
            }
        } catch (Exception e2) {
            JLog.e(TAG, e2.toString());
            if (jDCallback2 != null) {
                jDCallback2.invoke(new Object[0]);
            }
        }
    }

    @Override // com.jingdong.common.jdreactFramework.listener.NativeSystemListener
    public void getDeviceInfo(JDCallback jDCallback, JDCallback jDCallback2) {
        try {
            Activity currentMyActivity = AbstractJDReactInitialHelper.getCurrentMyActivity();
            WritableMap createMap = Arguments.createMap();
            createMap.putString(JDNetCacheManager.BRAND_BIZKEY, BaseInfo.getDeviceBrand());
            createMap.putString(CustomThemeConstance.NAVI_MODEL, BaseInfo.getDeviceModel());
            createMap.putString("androidDeviceVersion", BaseInfo.getOSName());
            createMap.putString("sysVersion", Build.VERSION.RELEASE);
            createMap.putString("androidSDK", Build.VERSION.SDK);
            createMap.putInt("statusbar", UnStatusBarTintUtil.getStatusBarHeight(currentMyActivity));
            createMap.putInt("navigationBar", UnStatusBarTintUtil.getNavigationBarHeight(currentMyActivity));
            createMap.putString("imei", "");
            createMap.putString("currentLanguage", Locale.getDefault().toString());
            Locale[] availableLocales = Locale.getAvailableLocales();
            WritableArray createArray = Arguments.createArray();
            if (availableLocales != null && availableLocales.length > 0) {
                for (Locale locale : availableLocales) {
                    createArray.pushString(locale.toString());
                }
            }
            createMap.putArray("allLanguage", createArray);
            AresCommonUtils.invokeCallback(jDCallback, createMap);
        } catch (Exception e2) {
            OKLog.e(TAG, e2);
            AresCommonUtils.invokeCallback(jDCallback2, new Object[0]);
        }
    }

    @Override // com.jingdong.common.jdreactFramework.listener.NativeSystemListener
    public void isAppDebug(JDCallback jDCallback, JDCallback jDCallback2) {
        if (jDCallback != null) {
            try {
                jDCallback.invoke(Boolean.valueOf(Configuration.isBeta()));
            } catch (Exception e2) {
                OKLog.e(TAG, e2);
                if (jDCallback2 != null) {
                    jDCallback2.invoke(new Object[0]);
                }
            }
        }
    }

    @Override // com.jingdong.common.jdreactFramework.listener.NativeSystemListener
    public void isDebugMode(JDCallback jDCallback, JDCallback jDCallback2) {
        if (jDCallback != null) {
            try {
                jDCallback.invoke(Boolean.FALSE);
            } catch (Exception unused) {
                if (jDCallback2 != null) {
                    jDCallback2.invoke(new Object[0]);
                }
            }
        }
    }

    @Override // com.jingdong.common.jdreactFramework.listener.NativeSystemListener
    public void isWifiVideoAutoPlay(JDCallback jDCallback, JDCallback jDCallback2) {
        try {
            boolean isWifiVideoAutoPlay = JDSettingUtils.isWifiVideoAutoPlay();
            if (jDCallback != null) {
                jDCallback.invoke(Boolean.valueOf(isWifiVideoAutoPlay));
            }
        } catch (Exception unused) {
            if (jDCallback2 != null) {
                jDCallback2.invoke(new Object[0]);
            }
        }
    }

    @Override // com.jingdong.common.jdreactFramework.listener.NativeSystemListener
    public boolean jumpPay(HashMap hashMap) {
        return false;
    }

    public void onMethodCall(String str, HashMap hashMap, JDFlutterCallResult jDFlutterCallResult, Activity activity) {
    }

    @Override // com.jingdong.common.jdreactFramework.listener.NativeSystemListener
    public void payOutOrder(HashMap hashMap, JDCallback jDCallback, JDCallback jDCallback2) {
        if (hashMap == null) {
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putString("JDPAY_ENTRANCE_VERIFY", "JDPAY_OPEN_PAY_VISITCONTROL");
        if (hashMap.containsKey("orderId")) {
            bundle.putString("ORDERID", (String) hashMap.get("orderId"));
        }
        if (hashMap.containsKey("merchatId")) {
            bundle.putString("MERCHANT", (String) hashMap.get("merchatId"));
        }
        bundle.putString(JumpUtils.JDPAY_CODE_SOURCE, "0");
        BaseActivity baseActivity = (BaseActivity) AbstractJDReactInitialHelper.getCurrentMyActivity();
        if (baseActivity instanceof JDReactNativeBaseActivity) {
            JDReactNativeBaseActivity jDReactNativeBaseActivity = (JDReactNativeBaseActivity) baseActivity;
            jDReactNativeBaseActivity.setSuccessCB(jDCallback);
            jDReactNativeBaseActivity.setErrorCB(jDCallback2);
        }
        DeeplinkJDpaySdkHelper.startJDPayActivityForResult(baseActivity, bundle, 1004);
    }

    @Override // com.jingdong.common.jdreactFramework.listener.NativeSystemListener
    public void requestPermission(Activity activity, HashMap hashMap, JDCallback jDCallback, JDCallback jDCallback2) {
        if (jDCallback2 != null) {
            try {
                jDCallback2.invoke("deprecated function");
            } catch (Exception e2) {
                String str = "requestPermission Exception" + e2.getMessage();
                if (jDCallback2 != null) {
                    jDCallback2.invoke(new Object[0]);
                }
            }
        }
    }

    @Override // com.jingdong.common.jdreactFramework.listener.NativeSystemListener
    public void setBarMode(final Activity activity, final boolean z, final JDCallback jDCallback, final JDCallback jDCallback2) {
        if (activity != null) {
            activity.runOnUiThread(new Runnable() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeSystemListener.1
                @Override // java.lang.Runnable
                public void run() {
                    boolean statusBarDarkMode;
                    if (UnStatusBarTintUtil.setLightOrDarkEnable(activity)) {
                        JLog.e(JDReactNativeSystemListener.TAG, UnStatusBarTintUtil.getStatusBarHeight(activity) + "");
                        JLog.e(JDReactNativeSystemListener.TAG, UnStatusBarTintUtil.getNavigationBarHeight(activity) + "");
                        if (z) {
                            statusBarDarkMode = UnStatusBarTintUtil.setStatusBarLightMode(activity);
                        } else {
                            statusBarDarkMode = UnStatusBarTintUtil.setStatusBarDarkMode(activity);
                        }
                        if (statusBarDarkMode) {
                            jDCallback.invoke(new Object[0]);
                            return;
                        } else {
                            jDCallback2.invoke(new Object[0]);
                            return;
                        }
                    }
                    UnStatusBarTintUtil.setBackgroundColor(activity, Color.parseColor(JDDarkUtil.COLOR_1D1B1B));
                    jDCallback2.invoke(new Object[0]);
                    JLog.e(JDReactNativeSystemListener.TAG + "-setBarMode StatusBarHeight", UnStatusBarTintUtil.getStatusBarHeight(activity) + "");
                    JLog.e(JDReactNativeSystemListener.TAG + "-setBarMode NavigationBarHeight", UnStatusBarTintUtil.getNavigationBarHeight(activity) + "");
                }
            });
        } else if (jDCallback2 != null) {
            jDCallback2.invoke(new Object[0]);
        }
    }

    @Override // com.jingdong.common.jdreactFramework.listener.NativeSystemListener
    public void setCacheAddress(HashMap hashMap, JDCallback jDCallback, JDCallback jDCallback2) {
        try {
            if (AddressUtil.updateAddressGlobal(assembleAddressGlobal(hashMap))) {
                invokeCallback(jDCallback, new Object[0]);
            } else {
                invokeCallback(jDCallback2, new Object[0]);
            }
        } catch (Exception unused) {
            invokeCallback(jDCallback2, new Object[0]);
        }
    }

    @Override // com.jingdong.common.jdreactFramework.listener.NativeSystemListener
    public void setInputMode(Activity activity, String str, JDCallback jDCallback, JDCallback jDCallback2) {
        if (TextUtils.isEmpty(str) || activity == null) {
            if (jDCallback2 != null) {
                jDCallback2.invoke(new Object[0]);
            }
        } else if (!(activity instanceof BaseActivity)) {
            if (jDCallback2 != null) {
                jDCallback2.invoke(new Object[0]);
            }
        } else {
            boolean isSoftHideKeyBoard = RNSoftHideKeyBoardUtil.isSoftHideKeyBoard(activity);
            if ("adjustResize".equals(str) || !isSoftHideKeyBoard) {
                BaseActivity baseActivity = (BaseActivity) activity;
                baseActivity.post(new InputRunable(baseActivity, str, jDCallback, jDCallback2));
            }
        }
    }

    @Override // com.jingdong.common.jdreactFramework.listener.NativeSystemListener
    public void xSwitch(JDCallback jDCallback, JDCallback jDCallback2) {
        try {
            boolean isXTime = SwitchQueryFetcher.isXTime();
            JLog.d(TAG, "xSwitch :" + isXTime);
            if (jDCallback != null) {
                jDCallback.invoke(Boolean.valueOf(isXTime));
            } else if (jDCallback2 != null) {
                jDCallback2.invoke(new Object[0]);
            }
        } catch (Exception unused) {
            if (jDCallback2 != null) {
                jDCallback2.invoke(new Object[0]);
            }
        }
    }
}

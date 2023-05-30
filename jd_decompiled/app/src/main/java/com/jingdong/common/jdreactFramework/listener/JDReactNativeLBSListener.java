package com.jingdong.common.jdreactFramework.listener;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.jingdong.app.mall.bundle.jdweather.action.JDWeatherActionKey;
import com.jingdong.common.jdreactFramework.JDCallback;
import com.jingdong.common.jdreactFramework.JDReactHelper;
import com.jingdong.common.jdreactFramework.activities.JDReactExtendHelperCallback;
import com.jingdong.common.lbs.LocManager;
import com.jingdong.common.lbs.jdlocation.JDLocation;
import com.jingdong.common.lbs.jdlocation.JDLocationCache;
import com.jingdong.common.lbs.jdlocation.JDLocationCacheOption;
import com.jingdong.common.lbs.jdlocation.JDLocationError;
import com.jingdong.common.lbs.jdlocation.JDLocationListener;
import com.jingdong.common.lbs.jdlocation.JDLocationManager;
import com.jingdong.common.lbs.jdlocation.JDLocationOption;
import com.jingdong.common.permission.PermissionHelper;
import com.jingdong.common.permission.entity.SceneStatus;
import java.util.HashMap;

/* loaded from: classes5.dex */
public class JDReactNativeLBSListener implements NativeLBSListener {
    public static final String BUSINESSID = "ccd2064f21b33ff71d80e1db19b14ba1";
    private HashMap<String, Object> stringObjectHashMap;

    /* renamed from: com.jingdong.common.jdreactFramework.listener.JDReactNativeLBSListener$8 */
    /* loaded from: classes5.dex */
    static /* synthetic */ class AnonymousClass8 {
        static final /* synthetic */ int[] $SwitchMap$com$jingdong$common$permission$entity$SceneStatus;

        static {
            int[] iArr = new int[SceneStatus.values().length];
            $SwitchMap$com$jingdong$common$permission$entity$SceneStatus = iArr;
            try {
                iArr[SceneStatus.HAS_ALL_PERMISSION.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$jingdong$common$permission$entity$SceneStatus[SceneStatus.NO_SYSTEM_PERMISSION.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$jingdong$common$permission$entity$SceneStatus[SceneStatus.NO_SCENE_PERMISSION.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public WritableMap convertJDLocation(JDLocation jDLocation) {
        WritableMap createMap = Arguments.createMap();
        if (jDLocation == null) {
            return createMap;
        }
        createMap.putDouble(LocManager.LAT_KEY, jDLocation.getLat());
        createMap.putDouble(LocManager.LNG_KEY, jDLocation.getLng());
        createMap.putDouble(JDWeatherActionKey.CITY_ID, jDLocation.getCityId());
        createMap.putString("cityName", jDLocation.getCityName());
        createMap.putString("provinceName", jDLocation.getProvinceName());
        createMap.putDouble(JDWeatherActionKey.PROVINCE_ID, jDLocation.getProvinceId());
        createMap.putDouble("districtId", jDLocation.getDistrictId());
        createMap.putString("districtName", jDLocation.getDistrictName());
        createMap.putString("townName", jDLocation.getTownName());
        createMap.putDouble(JDWeatherActionKey.TOWN_ID, jDLocation.getTownId());
        createMap.putDouble("altitude", jDLocation.getAltitude());
        createMap.putString("detailAddress", jDLocation.getDetailAddress());
        return createMap;
    }

    public WritableMap convertJDLocationError(JDLocationError jDLocationError) {
        WritableMap createMap = Arguments.createMap();
        if (jDLocationError == null) {
            return createMap;
        }
        createMap.putInt("code", jDLocationError.getCode());
        createMap.putString("msg", jDLocationError.getMsg());
        return createMap;
    }

    @Override // com.jingdong.common.jdreactFramework.listener.NativeLBSListener
    public void getAddress(HashMap hashMap, final JDCallback jDCallback, final JDCallback jDCallback2) {
        JDLocationOption jDLocationOption = new JDLocationOption();
        if (hashMap.containsKey("businessId")) {
            jDLocationOption.setBusinessId((String) hashMap.get("businessId"));
        }
        if (hashMap.containsKey("sceneId")) {
            jDLocationOption.setSceneId((String) hashMap.get("sceneId"));
        }
        if (hashMap.containsKey("isNeedDetail") && ((String) hashMap.get("isNeedDetail")).equals("1")) {
            jDLocationOption.setNeedDetail(true);
        }
        JDLocationManager.getInstance().getAddress(jDLocationOption, new JDLocationListener() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeLBSListener.1
            {
                JDReactNativeLBSListener.this = this;
            }

            @Override // com.jingdong.common.lbs.jdlocation.JDLocationListener
            public void onFail(JDLocationError jDLocationError) {
                jDCallback2.invoke(JDReactNativeLBSListener.this.convertJDLocationError(jDLocationError));
            }

            @Override // com.jingdong.common.lbs.jdlocation.JDLocationListener
            public void onSuccess(JDLocation jDLocation) {
                if (jDLocation != null) {
                    jDCallback.invoke(JDReactNativeLBSListener.this.convertJDLocation(jDLocation));
                }
            }
        });
    }

    @Override // com.jingdong.common.jdreactFramework.listener.NativeLBSListener
    public void getAddressID(HashMap hashMap, JDCallback jDCallback, JDCallback jDCallback2) {
        JDLocationCacheOption jDLocationCacheOption = new JDLocationCacheOption();
        if (hashMap.containsKey("businessId")) {
            jDLocationCacheOption.setBusinessId((String) hashMap.get("businessId"));
            if (hashMap.containsKey("sceneId")) {
                jDLocationCacheOption.setSceneId((String) hashMap.get("sceneId"));
            }
            jDCallback.invoke(JDLocationCache.getInstance().getAddressID(jDLocationCacheOption));
            return;
        }
        jDCallback2.invoke(new Object[0]);
    }

    @Override // com.jingdong.common.jdreactFramework.listener.NativeLBSListener
    public void getAddressList(HashMap hashMap, JDCallback jDCallback, JDCallback jDCallback2) {
    }

    @Override // com.jingdong.common.jdreactFramework.listener.NativeLBSListener
    public void getLastLocation(HashMap hashMap, JDCallback jDCallback, JDCallback jDCallback2) {
        JDLocationCacheOption jDLocationCacheOption = new JDLocationCacheOption();
        if (hashMap.containsKey("businessId")) {
            jDLocationCacheOption.setBusinessId((String) hashMap.get("businessId"));
            if (hashMap.containsKey("sceneId")) {
                jDLocationCacheOption.setSceneId((String) hashMap.get("sceneId"));
            }
            jDCallback.invoke(convertJDLocation(JDLocationCache.getInstance().getLocation(jDLocationCacheOption)));
            return;
        }
        jDCallback2.invoke(new Object[0]);
    }

    @Override // com.jingdong.common.jdreactFramework.listener.NativeLBSListener
    public void getLatLng(HashMap hashMap, final JDCallback jDCallback, final JDCallback jDCallback2) {
        JDLocationOption jDLocationOption = new JDLocationOption();
        if (hashMap.containsKey("businessId")) {
            jDLocationOption.setBusinessId((String) hashMap.get("businessId"));
        }
        if (hashMap.containsKey("sceneId")) {
            jDLocationOption.setSceneId((String) hashMap.get("sceneId"));
        }
        JDLocationManager.getInstance().getLatLng(jDLocationOption, new JDLocationListener() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeLBSListener.2
            {
                JDReactNativeLBSListener.this = this;
            }

            @Override // com.jingdong.common.lbs.jdlocation.JDLocationListener
            public void onFail(JDLocationError jDLocationError) {
                jDCallback2.invoke(JDReactNativeLBSListener.this.convertJDLocationError(jDLocationError));
            }

            @Override // com.jingdong.common.lbs.jdlocation.JDLocationListener
            public void onSuccess(JDLocation jDLocation) {
                if (jDLocation != null) {
                    jDLocation.getLng();
                    WritableMap createMap = Arguments.createMap();
                    createMap.putDouble(LocManager.LAT_KEY, jDLocation.getLat());
                    createMap.putDouble(LocManager.LNG_KEY, jDLocation.getLng());
                    jDCallback.invoke(createMap);
                }
            }
        });
    }

    public void getLocationMsg(final JDReactExtendHelperCallback.LocationCallBack locationCallBack) {
        JDLocationOption jDLocationOption = new JDLocationOption();
        jDLocationOption.setBusinessId(BUSINESSID);
        jDLocationOption.setNeedDetail(true);
        JDLocationManager.getInstance().getAddress(jDLocationOption, new JDLocationListener() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeLBSListener.5
            {
                JDReactNativeLBSListener.this = this;
            }

            @Override // com.jingdong.common.lbs.jdlocation.JDLocationListener
            public void onFail(JDLocationError jDLocationError) {
                locationCallBack.onSuc(null);
                JDReactNativeLBSListener.this.stringObjectHashMap = null;
            }

            @Override // com.jingdong.common.lbs.jdlocation.JDLocationListener
            public void onSuccess(JDLocation jDLocation) {
                WritableMap convertJDLocation = JDReactNativeLBSListener.this.convertJDLocation(jDLocation);
                JDReactNativeLBSListener.this.stringObjectHashMap = convertJDLocation.toHashMap();
                locationCallBack.onSuc(JDReactNativeLBSListener.this.stringObjectHashMap);
            }
        });
    }

    public void getLocationMsgNew(String str, String str2, final JDReactExtendHelperCallback.LocationCallBack locationCallBack) {
        if (TextUtils.isEmpty(str)) {
            str = "basicShoppingProcess";
        }
        if (TextUtils.isEmpty(str2)) {
            str2 = BUSINESSID;
        }
        try {
            if (PermissionHelper.hasLocationPermissionWithScene(str, str2)) {
                realGetLocationMsg(str, str2, locationCallBack);
            } else {
                final boolean[] zArr = {false};
                final String str3 = str;
                final String str4 = str2;
                if (TextUtils.equals(PermissionHelper.requestLocationPermissionWithScene(JDReactHelper.newInstance().getCurrentMyActivity(), new PermissionHelper.PermissionSceneCallback() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeLBSListener.7
                    {
                        JDReactNativeLBSListener.this = this;
                    }

                    private void emptyResult() {
                        JDReactExtendHelperCallback.LocationCallBack locationCallBack2 = locationCallBack;
                        if (locationCallBack2 != null) {
                            locationCallBack2.onSuc(null);
                        }
                    }

                    @Override // com.jingdong.common.permission.PermissionHelper.PermissionSceneCallback
                    public void onAgree() {
                        zArr[0] = true;
                        JDReactNativeLBSListener.this.realGetLocationMsg(str3, str4, locationCallBack);
                    }

                    @Override // com.jingdong.common.permission.PermissionHelper.PermissionResultCallBack
                    public void onCanceled() {
                        emptyResult();
                    }

                    @Override // com.jingdong.common.permission.PermissionHelper.PermissionResultCallBack
                    public void onDenied() {
                        emptyResult();
                    }

                    @Override // com.jingdong.common.permission.PermissionHelper.PermissionSceneCallback
                    public void onDisagree() {
                        emptyResult();
                    }

                    @Override // com.jingdong.common.permission.PermissionHelper.PermissionResultCallBack
                    public void onGranted() {
                        zArr[0] = true;
                        JDReactNativeLBSListener.this.realGetLocationMsg(str3, str4, locationCallBack);
                    }

                    @Override // com.jingdong.common.permission.PermissionHelper.PermissionResultCallBack
                    public void onIgnored() {
                        emptyResult();
                    }

                    @Override // com.jingdong.common.permission.PermissionHelper.PermissionResultCallBack
                    public void onOpenSetting() {
                        emptyResult();
                    }
                }, str, str2, null), "0") && !zArr[0] && locationCallBack != null) {
                    locationCallBack.onSuc(null);
                }
            }
        } catch (Exception unused) {
            locationCallBack.onSuc(null);
        }
    }

    @Override // com.jingdong.common.jdreactFramework.listener.NativeLBSListener
    public void hasLocationPermissionWithScene(HashMap hashMap, JDCallback jDCallback, JDCallback jDCallback2) {
        String str = hashMap.containsKey("businessId") ? (String) hashMap.get("businessId") : "";
        String str2 = hashMap.containsKey("sceneId") ? (String) hashMap.get("sceneId") : "";
        if (jDCallback == null) {
            if (jDCallback2 != null) {
                jDCallback2.invoke(new Object[0]);
                return;
            }
            return;
        }
        try {
            if (PermissionHelper.hasLocationPermissionWithScene(str2, str)) {
                jDCallback.invoke(new Object[0]);
            } else if (jDCallback2 != null) {
                jDCallback2.invoke("-1");
            }
        } catch (Exception e2) {
            if (jDCallback2 != null) {
                jDCallback2.invoke(e2.toString());
            }
        }
    }

    @Override // com.jingdong.common.jdreactFramework.listener.NativeLBSListener
    public void hasLocationPermissionWithSceneV2(HashMap hashMap, JDCallback jDCallback, JDCallback jDCallback2) {
        String str = hashMap.containsKey("businessId") ? (String) hashMap.get("businessId") : "";
        String str2 = hashMap.containsKey("sceneId") ? (String) hashMap.get("sceneId") : "";
        if (jDCallback == null) {
            if (jDCallback2 != null) {
                jDCallback2.invoke(new Object[0]);
                return;
            }
            return;
        }
        try {
            SceneStatus hasLocationPermissionWithSceneV2 = PermissionHelper.hasLocationPermissionWithSceneV2(str2, str);
            if (hasLocationPermissionWithSceneV2 != null) {
                int i2 = AnonymousClass8.$SwitchMap$com$jingdong$common$permission$entity$SceneStatus[hasLocationPermissionWithSceneV2.ordinal()];
                if (i2 == 1) {
                    jDCallback.invoke(new Object[0]);
                } else if (i2 != 2) {
                    if (i2 != 3) {
                        if (jDCallback2 != null) {
                            jDCallback2.invoke("0");
                        }
                    } else if (jDCallback2 != null) {
                        jDCallback2.invoke("-2");
                    }
                } else if (jDCallback2 != null) {
                    jDCallback2.invoke("-1");
                }
            } else if (jDCallback2 != null) {
                jDCallback2.invoke("0");
            }
        } catch (Exception e2) {
            if (jDCallback2 != null) {
                jDCallback2.invoke(e2.toString());
            }
        }
    }

    @Override // com.jingdong.common.jdreactFramework.listener.NativeLBSListener
    public void manualRequestLocationPermissionWithScene(Activity activity, HashMap hashMap, final JDCallback jDCallback, final JDCallback jDCallback2) {
        final String str = hashMap.containsKey("businessId") ? (String) hashMap.get("businessId") : "";
        final String str2 = hashMap.containsKey("sceneId") ? (String) hashMap.get("sceneId") : "";
        final String str3 = hashMap.containsKey("sceneContent") ? (String) hashMap.get("sceneContent") : "";
        if (jDCallback == null) {
            if (jDCallback2 != null) {
                jDCallback2.invoke(new Object[0]);
                return;
            }
            return;
        }
        if (activity == null) {
            activity = JDReactHelper.newInstance().getCurrentMyActivity();
        }
        final Activity activity2 = activity;
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeLBSListener.4
            {
                JDReactNativeLBSListener.this = this;
            }

            @Override // java.lang.Runnable
            public void run() {
                JDCallback jDCallback3;
                try {
                    final boolean[] zArr = {false};
                    if (!TextUtils.equals(PermissionHelper.manualRequestLocationPermissionWithScene(activity2, new PermissionHelper.PermissionSceneCallback() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeLBSListener.4.1
                        {
                            AnonymousClass4.this = this;
                        }

                        @Override // com.jingdong.common.permission.PermissionHelper.PermissionSceneCallback
                        public void onAgree() {
                            jDCallback.invoke(new Object[0]);
                        }

                        @Override // com.jingdong.common.permission.PermissionHelper.PermissionResultCallBack
                        public void onCanceled() {
                            super.onCanceled();
                            JDCallback jDCallback4 = jDCallback2;
                            if (jDCallback4 != null) {
                                jDCallback4.invoke("-2");
                            }
                        }

                        @Override // com.jingdong.common.permission.PermissionHelper.PermissionResultCallBack
                        public void onDenied() {
                            super.onDenied();
                            JDCallback jDCallback4 = jDCallback2;
                            if (jDCallback4 != null) {
                                jDCallback4.invoke("-1");
                            }
                        }

                        @Override // com.jingdong.common.permission.PermissionHelper.PermissionSceneCallback
                        public void onDisagree() {
                            JDCallback jDCallback4 = jDCallback2;
                            if (jDCallback4 != null) {
                                jDCallback4.invoke("-4");
                            }
                        }

                        @Override // com.jingdong.common.permission.PermissionHelper.PermissionResultCallBack
                        public void onGranted() {
                            JDCallback jDCallback4 = jDCallback;
                            if (jDCallback4 != null) {
                                jDCallback4.invoke(new Object[0]);
                                zArr[0] = true;
                            }
                        }

                        @Override // com.jingdong.common.permission.PermissionHelper.PermissionResultCallBack
                        public void onIgnored() {
                            super.onIgnored();
                            JDCallback jDCallback4 = jDCallback2;
                            if (jDCallback4 != null) {
                                jDCallback4.invoke("-3");
                            }
                        }

                        @Override // com.jingdong.common.permission.PermissionHelper.PermissionResultCallBack
                        public void onOpenSetting() {
                            super.onOpenSetting();
                            JDCallback jDCallback4 = jDCallback2;
                            if (jDCallback4 != null) {
                                jDCallback4.invoke("0");
                            }
                        }
                    }, str2, str, str3), "0") || zArr[0] || (jDCallback3 = jDCallback2) == null) {
                        return;
                    }
                    jDCallback3.invoke("-5");
                } catch (Throwable th) {
                    JDCallback jDCallback4 = jDCallback2;
                    if (jDCallback4 != null) {
                        jDCallback4.invoke(th.toString());
                    }
                }
            }
        });
    }

    public void realGetLocationMsg(String str, String str2, final JDReactExtendHelperCallback.LocationCallBack locationCallBack) {
        JDLocationOption jDLocationOption = new JDLocationOption();
        jDLocationOption.setBusinessId(str2);
        jDLocationOption.setNeedDetail(true);
        jDLocationOption.setSceneId(str);
        JDLocationManager.getInstance().getAddress(jDLocationOption, new JDLocationListener() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeLBSListener.6
            {
                JDReactNativeLBSListener.this = this;
            }

            @Override // com.jingdong.common.lbs.jdlocation.JDLocationListener
            public void onFail(JDLocationError jDLocationError) {
                JDReactExtendHelperCallback.LocationCallBack locationCallBack2 = locationCallBack;
                if (locationCallBack2 != null) {
                    locationCallBack2.onSuc(null);
                }
                JDReactNativeLBSListener.this.stringObjectHashMap = null;
            }

            @Override // com.jingdong.common.lbs.jdlocation.JDLocationListener
            public void onSuccess(JDLocation jDLocation) {
                WritableMap convertJDLocation = JDReactNativeLBSListener.this.convertJDLocation(jDLocation);
                JDReactNativeLBSListener.this.stringObjectHashMap = convertJDLocation.toHashMap();
                JDReactExtendHelperCallback.LocationCallBack locationCallBack2 = locationCallBack;
                if (locationCallBack2 != null) {
                    locationCallBack2.onSuc(JDReactNativeLBSListener.this.stringObjectHashMap);
                }
            }
        });
    }

    @Override // com.jingdong.common.jdreactFramework.listener.NativeLBSListener
    public void requestLocationPermissionWithScene(Activity activity, HashMap hashMap, final JDCallback jDCallback, final JDCallback jDCallback2) {
        final String str = hashMap.containsKey("businessId") ? (String) hashMap.get("businessId") : "";
        final String str2 = hashMap.containsKey("sceneId") ? (String) hashMap.get("sceneId") : "";
        final String str3 = hashMap.containsKey("sceneContent") ? (String) hashMap.get("sceneContent") : "";
        if (jDCallback == null) {
            if (jDCallback2 != null) {
                jDCallback2.invoke(new Object[0]);
                return;
            }
            return;
        }
        if (activity == null) {
            activity = JDReactHelper.newInstance().getCurrentMyActivity();
        }
        final Activity activity2 = activity;
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeLBSListener.3
            {
                JDReactNativeLBSListener.this = this;
            }

            @Override // java.lang.Runnable
            public void run() {
                JDCallback jDCallback3;
                try {
                    final boolean[] zArr = {false};
                    if (!TextUtils.equals(PermissionHelper.requestLocationPermissionWithScene(activity2, new PermissionHelper.PermissionSceneCallback() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeLBSListener.3.1
                        {
                            AnonymousClass3.this = this;
                        }

                        @Override // com.jingdong.common.permission.PermissionHelper.PermissionSceneCallback
                        public void onAgree() {
                            jDCallback.invoke(new Object[0]);
                        }

                        @Override // com.jingdong.common.permission.PermissionHelper.PermissionResultCallBack
                        public void onCanceled() {
                            super.onCanceled();
                            JDCallback jDCallback4 = jDCallback2;
                            if (jDCallback4 != null) {
                                jDCallback4.invoke("-2");
                            }
                        }

                        @Override // com.jingdong.common.permission.PermissionHelper.PermissionResultCallBack
                        public void onDenied() {
                            super.onDenied();
                            JDCallback jDCallback4 = jDCallback2;
                            if (jDCallback4 != null) {
                                jDCallback4.invoke("-1");
                            }
                        }

                        @Override // com.jingdong.common.permission.PermissionHelper.PermissionSceneCallback
                        public void onDisagree() {
                            JDCallback jDCallback4 = jDCallback2;
                            if (jDCallback4 != null) {
                                jDCallback4.invoke("-4");
                            }
                        }

                        @Override // com.jingdong.common.permission.PermissionHelper.PermissionResultCallBack
                        public void onGranted() {
                            JDCallback jDCallback4 = jDCallback;
                            if (jDCallback4 != null) {
                                jDCallback4.invoke(new Object[0]);
                                zArr[0] = true;
                            }
                        }

                        @Override // com.jingdong.common.permission.PermissionHelper.PermissionResultCallBack
                        public void onIgnored() {
                            super.onIgnored();
                            JDCallback jDCallback4 = jDCallback2;
                            if (jDCallback4 != null) {
                                jDCallback4.invoke("-3");
                            }
                        }

                        @Override // com.jingdong.common.permission.PermissionHelper.PermissionResultCallBack
                        public void onOpenSetting() {
                            super.onOpenSetting();
                            JDCallback jDCallback4 = jDCallback2;
                            if (jDCallback4 != null) {
                                jDCallback4.invoke("0");
                            }
                        }
                    }, str2, str, str3), "0") || zArr[0] || (jDCallback3 = jDCallback2) == null) {
                        return;
                    }
                    jDCallback3.invoke("-5");
                } catch (Throwable th) {
                    JDCallback jDCallback4 = jDCallback2;
                    if (jDCallback4 != null) {
                        jDCallback4.invoke(th.toString());
                    }
                }
            }
        });
    }
}

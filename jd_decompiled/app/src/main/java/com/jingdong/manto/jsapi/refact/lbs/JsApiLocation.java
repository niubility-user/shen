package com.jingdong.manto.jsapi.refact.lbs;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.jd.dynamic.DYConstants;
import com.jd.lib.productdetail.core.protocol.PdLVBody;
import com.jingdong.a;
import com.jingdong.common.unification.title.theme.ThemeTitleConstant;
import com.jingdong.manto.AppLifeCycle;
import com.jingdong.manto.MantoCore;
import com.jingdong.manto.c;
import com.jingdong.manto.jsapi.openmodule.AbstractMantoModule;
import com.jingdong.manto.jsapi.openmodule.IMantoBaseModule;
import com.jingdong.manto.jsapi.openmodule.JsApiMethod;
import com.jingdong.manto.jsapi.openmodule.MantoResultCallBack;
import com.jingdong.manto.utils.MantoLog;
import com.jingdong.manto.utils.MantoTrack;
import com.jingdong.manto.utils.MantoUtils;
import java.util.HashSet;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public abstract class JsApiLocation extends AbstractMantoModule {
    public static final String CHOOSELOCATION_NAME = "chooseLocation";
    public static final String GETLOCATION_INNER_NAME = "getLocationInner";
    public static final String GETLOCATION_NAME = "getLocation";
    public static final String LISTEN_BG_LOC_UPDATE = "enableLocationUpdateBackground";
    public static final String LISTEN_LOC_UPDATE = "enableLocationUpdate";
    public static final String LOC_CHANGE_EVNET_NAME = "onLocationChange";
    static final String MODULE_NAME = "LocationNew";
    public static final String OPENLOCATION_NAME = "openLocation";
    public static final int REQUEST_CODE_CHOOSE = 1102;
    static final String TAG = "LocationNew";
    private static final HashSet<String> types;
    private String appId;
    private LocationChangeListener listener;
    private int hashCode = 0;
    private boolean isBGLocationChange = false;
    private final Bundle passBundle = new Bundle(1);
    AppLifeCycle.Listener lifeCycle = new AppLifeCycle.Listener() { // from class: com.jingdong.manto.jsapi.refact.lbs.JsApiLocation.4
        @Override // com.jingdong.manto.AppLifeCycle.Listener
        public void onAppDestroy() {
            super.onAppDestroy();
            MantoLog.d("betterLoc", "onAppDestroy");
            JsApiLocation.this.requestLocationInterval(a.g(), JsApiLocation.this.passBundle, JsApiLocation.this.listener, false);
            AppLifeCycle.remove(JsApiLocation.this.appId, JsApiLocation.this.lifeCycle);
        }

        @Override // com.jingdong.manto.AppLifeCycle.Listener
        public void onAppPause() {
            super.onAppPause();
            MantoLog.d("betterLoc", "onAppPause");
            JsApiLocation.this.passBundle.putInt("interval", 2000);
            if (JsApiLocation.this.isBGLocationChange) {
                return;
            }
            JsApiLocation.this.requestLocationInterval(a.g(), JsApiLocation.this.passBundle, JsApiLocation.this.listener, false);
        }

        @Override // com.jingdong.manto.AppLifeCycle.Listener
        public void onAppResume() {
            super.onAppResume();
            MantoLog.d("betterLoc", "onAppResume");
            JsApiLocation.this.passBundle.putInt("interval", 2000);
            if (JsApiLocation.this.isBGLocationChange) {
                return;
            }
            JsApiLocation.this.requestLocationInterval(a.g(), JsApiLocation.this.passBundle, JsApiLocation.this.listener, true);
        }
    };

    /* loaded from: classes15.dex */
    public abstract class Permission {
        public Permission() {
        }

        public abstract void onDenied();

        public abstract void onGranted();
    }

    static {
        HashSet<String> hashSet = new HashSet<>();
        types = hashSet;
        hashSet.add("gcj02");
        hashSet.add("wgs84");
    }

    private void locationUpdate(final MantoCore mantoCore, final Activity activity, JSONObject jSONObject, final MantoResultCallBack mantoResultCallBack, boolean z) {
        final boolean optBoolean = jSONObject.optBoolean("enable", z);
        this.isBGLocationChange = z;
        if (this.listener == null) {
            this.listener = new LocationChangeListener() { // from class: com.jingdong.manto.jsapi.refact.lbs.JsApiLocation.2
                @Override // com.jingdong.manto.jsapi.refact.lbs.LocationChangeListener
                public void onLocationChange(Bundle bundle) {
                    JSONObject jSONObject2 = new JSONObject();
                    try {
                        jSONObject2.put(PdLVBody.LATITUDE, bundle.getString(PdLVBody.LATITUDE));
                        jSONObject2.put(PdLVBody.LONGITUDE, bundle.getString(PdLVBody.LONGITUDE));
                        jSONObject2.put("speed", bundle.getString("speed"));
                        jSONObject2.put("accuracy", bundle.getString("accuracy"));
                        jSONObject2.put("altitude", bundle.getString("altitude"));
                        jSONObject2.put("verticalAccuracy", bundle.getString("altitude"));
                        jSONObject2.put("horizontalAccuracy", bundle.getString("horizontalAccuracy"));
                        jSONObject2.put("provider", bundle.getString("provider"));
                        jSONObject2.put("indoorLocationType", bundle.getString("indoorLocationType"));
                        JsApiLocation jsApiLocation = JsApiLocation.this;
                        jsApiLocation.dispatchEvent(mantoCore, JsApiLocation.LOC_CHANGE_EVNET_NAME, jSONObject2, jsApiLocation.hashCode);
                        MantoLog.d("betterLoc", "onLocationChange invoked. data: " + jSONObject2);
                    } catch (Exception unused) {
                    }
                }

                @Override // com.jingdong.manto.jsapi.refact.lbs.LocationChangeListener
                public void onLocationFailed(int i2, String str) {
                    JSONObject jSONObject2 = new JSONObject();
                    try {
                        jSONObject2.put("code", i2);
                        jSONObject2.put("msg", str);
                        JsApiLocation jsApiLocation = JsApiLocation.this;
                        jsApiLocation.dispatchEvent(mantoCore, JsApiLocation.LOC_CHANGE_EVNET_NAME, jSONObject2, jsApiLocation.hashCode);
                        MantoLog.d("betterLoc", "onLocationChangefailed invoked. data: " + jSONObject2);
                    } catch (Exception unused) {
                    }
                }
            };
        }
        final Bundle bundle = new Bundle();
        final Bundle bundle2 = new Bundle(3);
        bundle2.putInt("interval", 2000);
        if (!hasLocationPermission()) {
            requireLocationPermission(activity, new Permission() { // from class: com.jingdong.manto.jsapi.refact.lbs.JsApiLocation.3
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super();
                }

                @Override // com.jingdong.manto.jsapi.refact.lbs.JsApiLocation.Permission
                public void onDenied() {
                    bundle.putString("message", "system permission denied");
                    mantoResultCallBack.onFailed(bundle);
                }

                @Override // com.jingdong.manto.jsapi.refact.lbs.JsApiLocation.Permission
                public void onGranted() {
                    JsApiLocation jsApiLocation = JsApiLocation.this;
                    jsApiLocation.requestLocationInterval(activity, bundle2, jsApiLocation.listener, optBoolean);
                    mantoResultCallBack.onSuccess(bundle);
                }
            });
            return;
        }
        requestLocationInterval(activity, bundle2, this.listener, optBoolean);
        mantoResultCallBack.onSuccess(bundle);
    }

    private void parseGetLocation(Activity activity, JSONObject jSONObject, MantoResultCallBack mantoResultCallBack) {
        new Bundle();
        String optString = jSONObject.optString("type", "wgs84");
        MantoLog.v("LocationNew", String.format(optString, new Object[0]));
        TextUtils.isEmpty(optString);
        boolean optBoolean = jSONObject.optBoolean("altitude");
        boolean optBoolean2 = jSONObject.optBoolean("isHighAccuracy", false);
        int optInt = jSONObject.optInt("highAccuracyExpireTime", 3000);
        Bundle bundle = new Bundle(3);
        bundle.putBoolean("altitude", optBoolean);
        bundle.putBoolean("isHighAccuracy", optBoolean2);
        bundle.putInt("highAccuracyExpireTime", optInt);
        requestLocation(activity, bundle, mantoResultCallBack);
    }

    private void parseGetLocationPermission(Activity activity, JSONObject jSONObject, final MantoResultCallBack mantoResultCallBack) {
        final Bundle bundle = new Bundle();
        String optString = jSONObject.optString("type", "wgs84");
        MantoLog.v("LocationNew", String.format(optString, new Object[0]));
        String str = TextUtils.isEmpty(optString) ? "wgs84" : optString;
        boolean optBoolean = jSONObject.optBoolean("altitude");
        boolean optBoolean2 = jSONObject.optBoolean("isHighAccuracy", false);
        int optInt = jSONObject.optInt("highAccuracyExpireTime", 3000);
        final Bundle bundle2 = new Bundle(3);
        bundle2.putBoolean("altitude", optBoolean);
        bundle2.putBoolean("isHighAccuracy", optBoolean2);
        bundle2.putInt("highAccuracyExpireTime", optInt);
        if (!types.contains(str)) {
            MantoLog.e("LocationNew", String.format("doGeoLocation fail, unsupported type = %s", str));
            bundle.putString("message", "unsupported type");
            mantoResultCallBack.onFailed(bundle);
        } else if (!hasLocationPermission()) {
            requireLocationPermission(activity, new Permission() { // from class: com.jingdong.manto.jsapi.refact.lbs.JsApiLocation.1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super();
                }

                @Override // com.jingdong.manto.jsapi.refact.lbs.JsApiLocation.Permission
                public void onDenied() {
                    bundle.putString("message", "system permission denied");
                    mantoResultCallBack.onFailed(bundle);
                }

                @Override // com.jingdong.manto.jsapi.refact.lbs.JsApiLocation.Permission
                public void onGranted() {
                    bundle2.putString(IMantoBaseModule.REQUEST_JSAPI_KEY, JsApiLocation.GETLOCATION_INNER_NAME);
                    mantoResultCallBack.onSuccess(bundle2);
                }
            });
        } else {
            bundle2.putString(IMantoBaseModule.REQUEST_JSAPI_KEY, GETLOCATION_INNER_NAME);
            mantoResultCallBack.onSuccess(bundle2);
        }
    }

    private void reportMta(String str, String str2, String str3) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("vapp_type", str3);
            jSONObject.put("api", str);
            MantoTrack.sendCommonDataWithExt(c.a(), "\u63a5\u53e3\u8c03\u7528", "applets_api", str2, "", "", jSONObject.toString(), "", null);
        } catch (Throwable th) {
            MantoLog.e(DYConstants.DY_TRACK, th);
        }
    }

    @Override // com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
    public final String getModuleName() {
        return "LocationNew";
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoModule, com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
    public final void handleMethod(String str, MantoCore mantoCore, Bundle bundle, MantoResultCallBack mantoResultCallBack) {
        JSONObject jSONObject;
        Bundle bundle2;
        this.hashCode = bundle.getInt(IMantoBaseModule.COMPONENT_HASHCODE);
        this.appId = bundle.getString("appid");
        bundle.getString("type");
        try {
            jSONObject = new JSONObject(bundle.getString("json"));
        } catch (JSONException e2) {
            e2.printStackTrace();
            jSONObject = null;
        }
        bundle.getString(IMantoBaseModule.APP_UNIQUEID_ID_KEY, "");
        Bundle bundle3 = new Bundle();
        Activity activity = mantoCore.getActivity();
        if (GETLOCATION_NAME.equals(str)) {
            parseGetLocationPermission(activity, jSONObject, mantoResultCallBack);
        } else if (GETLOCATION_INNER_NAME.equals(str)) {
            parseGetLocation(activity, jSONObject, mantoResultCallBack);
        } else {
            if (LISTEN_LOC_UPDATE.equals(str)) {
                bundle2 = new Bundle(1);
            } else if (!LISTEN_BG_LOC_UPDATE.equals(str)) {
                if (mantoCore == null) {
                    mantoResultCallBack.onFailed(bundle3);
                    return;
                } else if (CHOOSELOCATION_NAME.equals(str)) {
                    startChooseLocation(activity, 1102);
                    return;
                } else {
                    try {
                        startOpenLocation(activity, MantoUtils.getFloat(jSONObject.optString(PdLVBody.LONGITUDE), 0.0f), MantoUtils.getFloat(jSONObject.optString(PdLVBody.LATITUDE), 0.0f), MantoUtils.transferHtmlCharacters(jSONObject.optString("name")), MantoUtils.transferHtmlCharacters(jSONObject.optString(ThemeTitleConstant.TITLE_ADDRESS_DRAWABLE_ID)), MantoUtils.getInt(jSONObject.optString("scale"), 0));
                        mantoResultCallBack.onSuccess(bundle3);
                        return;
                    } catch (Exception unused) {
                        bundle3.putString("message", "invalid_coordinate");
                        mantoResultCallBack.onFailed(bundle3);
                        return;
                    }
                }
            } else {
                bundle2 = new Bundle(1);
            }
            bundle2.putString("errormsg", "api is forbidden");
            mantoResultCallBack.onFailed(bundle2);
        }
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoModule, com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
    public final Bundle handleResult(String str, MantoCore mantoCore, Intent intent, int i2, int i3) {
        Bundle bundle = new Bundle();
        if (!CHOOSELOCATION_NAME.equals(str)) {
            bundle.putString(IMantoBaseModule.ERROR_CODE, "0");
            return bundle;
        } else if (i3 != 1102) {
            bundle.putString(IMantoBaseModule.ERROR_CODE, "0");
            return bundle;
        } else if (i2 != -1) {
            if (i2 != 0) {
                bundle.putString(IMantoBaseModule.ERROR_CODE, "0");
                return bundle;
            }
            bundle.putString(IMantoBaseModule.ERROR_CODE, "2");
            bundle.putString("result", "cancel");
            return bundle;
        } else if (intent == null) {
            bundle.putString(IMantoBaseModule.ERROR_CODE, "0");
            return bundle;
        } else {
            MapAddress mapAddress = (MapAddress) intent.getParcelableExtra("key_pick_addr");
            if (mapAddress == null) {
                bundle.putString(IMantoBaseModule.ERROR_CODE, "0");
                return bundle;
            }
            MantoLog.i("LocationNew", "addr: " + mapAddress.toString());
            StringBuilder sb = new StringBuilder();
            sb.append(TextUtils.isEmpty(mapAddress.address) ? mapAddress.Kx() : mapAddress.address);
            bundle.putString(ThemeTitleConstant.TITLE_ADDRESS_DRAWABLE_ID, sb.toString());
            bundle.putString("name", TextUtils.isEmpty(mapAddress.name) ? sb.toString() : mapAddress.name);
            bundle.putString(PdLVBody.LATITUDE, String.valueOf(mapAddress.latitude));
            bundle.putString(PdLVBody.LONGITUDE, String.valueOf(mapAddress.longitude));
            bundle.putString(IMantoBaseModule.ERROR_CODE, "1");
            return bundle;
        }
    }

    public abstract boolean hasLocationPermission();

    @Override // com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
    public final Bundle initData(String str, MantoCore mantoCore, JSONObject jSONObject) {
        Bundle bundle = new Bundle();
        bundle.putString("json", jSONObject.toString());
        if (CHOOSELOCATION_NAME.equals(str)) {
            bundle.putInt("requestCode", 1102);
        }
        return bundle;
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoModule
    protected final void injectJsApiMethod(List<JsApiMethod> list) {
        list.add(new JsApiMethod(CHOOSELOCATION_NAME, 2));
        list.add(new JsApiMethod(OPENLOCATION_NAME, 1));
        list.add(new JsApiMethod(GETLOCATION_NAME, 1));
        list.add(new JsApiMethod(GETLOCATION_INNER_NAME, 1));
        list.add(new JsApiMethod(LISTEN_LOC_UPDATE, 1));
        list.add(new JsApiMethod(LISTEN_BG_LOC_UPDATE, 1));
    }

    public abstract void requestLocation(Context context, Bundle bundle, MantoResultCallBack mantoResultCallBack);

    public abstract void requestLocationInterval(Context context, Bundle bundle, LocationChangeListener locationChangeListener, boolean z);

    public abstract void requireLocationPermission(Activity activity, Permission permission);

    public abstract void startChooseLocation(Activity activity, int i2);

    public abstract void startOpenLocation(Activity activity, float f2, float f3, String str, String str2, int i2);
}

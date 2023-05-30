package com.jingdong.manto.jsapi.refact.wifi;

import android.app.Activity;
import android.os.Bundle;
import com.jingdong.common.deeplinkhelper.DeepLink3DProductHelper;
import com.jingdong.manto.MantoCore;
import com.jingdong.manto.jsapi.openmodule.AbstractMantoModule;
import com.jingdong.manto.jsapi.openmodule.IMantoBaseModule;
import com.jingdong.manto.jsapi.openmodule.JsApiMethod;
import com.jingdong.manto.jsapi.openmodule.MantoResultCallBack;
import com.jingdong.manto.utils.MantoLog;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public abstract class JsApiWifi extends AbstractMantoModule {
    static final String CONNECTWIFI_NAME = "connectWifi";
    static final String EVENT_ONGETWIFILIST_NAME = "onGetWifiList";
    static final String EVENT_ONWIFICONNECTED_NAME = "onWifiConnected";
    static final String GETCONNECTEDWIFI_NAME = "getConnectedWifi";
    static final String GETWIFILIST_NAME = "getWifiList";
    static final String MODULE_NAME = "JsApiWifi";
    static final String STARTWIFI_NAME = "startWifi";
    static final String STOPWIFI_NAME = "stopWifi";
    static final String TAG = "JsApiWifi";
    static final int WIFI_BASEINDEX = 1220;
    private MantoCore core;
    private int mHashCode = 0;

    protected abstract void connectWifi(String str, String str2, String str3, boolean z, MantoResultCallBack mantoResultCallBack);

    public Activity getActivity() {
        return this.core.getActivity();
    }

    protected abstract void getConnectedWifi(MantoResultCallBack mantoResultCallBack);

    @Override // com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
    public String getModuleName() {
        return "JsApiWifi";
    }

    protected abstract void getWifiList(MantoResultCallBack mantoResultCallBack);

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoModule, com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
    public void handleMethod(String str, MantoCore mantoCore, Bundle bundle, MantoResultCallBack mantoResultCallBack) {
        JSONObject jSONObject;
        try {
            jSONObject = new JSONObject(bundle.getString("json"));
        } catch (JSONException e2) {
            e2.printStackTrace();
            jSONObject = null;
        }
        bundle.getString(IMantoBaseModule.APP_UNIQUEID_ID_KEY, "");
        this.core = mantoCore;
        this.mHashCode = bundle.getInt(IMantoBaseModule.COMPONENT_HASHCODE);
        if (STARTWIFI_NAME.equals(str)) {
            startWifi(mantoResultCallBack);
        } else if (STOPWIFI_NAME.equals(str)) {
            stopWifi(mantoResultCallBack);
        } else if (GETWIFILIST_NAME.equals(str)) {
            getWifiList(mantoResultCallBack);
        } else if (GETCONNECTEDWIFI_NAME.equals(str)) {
            getConnectedWifi(mantoResultCallBack);
        } else if (CONNECTWIFI_NAME.equals(str)) {
            connectWifi(jSONObject.optString("SSID"), jSONObject.optString("BSSID"), jSONObject.optString(DeepLink3DProductHelper.EXTRA_PASSWORD), jSONObject.optBoolean("manual", false), mantoResultCallBack);
        }
    }

    @Override // com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
    public Bundle initData(String str, MantoCore mantoCore, JSONObject jSONObject) {
        Bundle bundle = new Bundle();
        bundle.putString("json", jSONObject.toString());
        return bundle;
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoModule
    protected void injectJsApiMethod(List<JsApiMethod> list) {
        list.add(new JsApiMethod(STARTWIFI_NAME, 1));
        list.add(new JsApiMethod(STOPWIFI_NAME, 1));
        list.add(new JsApiMethod(GETWIFILIST_NAME, 1));
        list.add(new JsApiMethod(GETCONNECTEDWIFI_NAME, 1));
        list.add(new JsApiMethod(CONNECTWIFI_NAME, 1));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onGetWifiList(List<WifiInfo> list) {
        MantoCore mantoCore = this.core;
        if (mantoCore == null || this.mHashCode == 0) {
            MantoLog.e("JsApiWifi", mantoCore == null ? "Activity null" : "HashCode error");
            return;
        }
        JSONArray jSONArray = new JSONArray();
        JSONObject jSONObject = new JSONObject();
        try {
            Iterator<WifiInfo> it = list.iterator();
            while (it.hasNext()) {
                jSONArray.put(it.next().toJsonObject());
            }
            jSONObject.put("wifiList", jSONArray);
        } catch (JSONException unused) {
        }
        dispatchEvent(this.core, EVENT_ONGETWIFILIST_NAME, jSONObject, this.mHashCode);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onWifiConnected(WifiInfo wifiInfo) {
        MantoCore mantoCore = this.core;
        if (mantoCore == null || this.mHashCode == 0) {
            MantoLog.e("JsApiWifi", mantoCore == null ? "Activity null" : "HashCode error");
            return;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("wifi", wifiInfo.toJsonObject());
        } catch (JSONException unused) {
        }
        dispatchEvent(this.core, EVENT_ONWIFICONNECTED_NAME, jSONObject, this.mHashCode);
    }

    protected abstract void startWifi(MantoResultCallBack mantoResultCallBack);

    protected abstract void stopWifi(MantoResultCallBack mantoResultCallBack);
}

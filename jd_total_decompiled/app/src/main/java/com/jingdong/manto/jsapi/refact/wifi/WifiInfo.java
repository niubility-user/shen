package com.jingdong.manto.jsapi.refact.wifi;

import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class WifiInfo {
    public int frequency;
    public String mBSSID;
    public String mSsid;
    public boolean secure;
    public int signalStrength;

    public JSONObject toJsonObject() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("BSSID", this.mBSSID);
            jSONObject.put("SSID", this.mSsid);
            jSONObject.put("secure", this.secure);
            jSONObject.put("signalStrength", this.signalStrength);
            jSONObject.put("frequency", this.frequency);
        } catch (JSONException unused) {
        }
        return jSONObject;
    }
}

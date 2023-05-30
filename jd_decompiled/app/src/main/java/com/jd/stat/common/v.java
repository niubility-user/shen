package com.jd.stat.common;

import android.content.Context;
import android.text.TextUtils;
import com.jingdong.sdk.baseinfo.BaseInfo;
import com.unionpay.tsmservice.mi.data.Constant;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes18.dex */
public class v {
    public static JSONObject a(Context context) {
        if (!l.a(context, "android.permission.ACCESS_WIFI_STATE") && !l.a(context, "android.permission.ACCESS_FINE_LOCATION")) {
            com.jd.stat.common.b.b.a("====> ", "wifi no permission");
            return new JSONObject();
        } else if (!com.jd.stat.security.d.a().J()) {
            com.jd.stat.common.b.b.a("====> ", "wifi switch close");
            return new JSONObject();
        } else {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("scaned", b(context.getApplicationContext()));
                jSONObject.put("saved", com.jingdong.jdsdk.a.a.a);
                jSONObject.put("using", c(context.getApplicationContext()));
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
            return jSONObject;
        }
    }

    private static JSONObject b(Context context) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        for (Map.Entry<String, String> entry : BaseInfo.getWifiBssidAndSsidMap(context).entrySet()) {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("joinable", true).put(Constant.KEY_MAC, entry.getKey()).put("SSID", entry.getValue());
            jSONObject.put(entry.getKey(), jSONObject2);
        }
        return jSONObject;
    }

    private static JSONObject c(Context context) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        if (context != null) {
            String wifiSSID = BaseInfo.getWifiSSID(context);
            JSONObject jSONObject2 = new JSONObject();
            if (wifiSSID != null && wifiSSID.length() > 1 && wifiSSID.charAt(0) == '\"' && wifiSSID.charAt(wifiSSID.length() - 1) == '\"') {
                String substring = wifiSSID.substring(1, wifiSSID.length() - 1);
                jSONObject2.put("SSID", substring);
                jSONObject2.put(Constant.KEY_MAC, BaseInfo.getWifiBSSID(context));
                jSONObject.put(substring, jSONObject2);
            } else if (!TextUtils.isEmpty(wifiSSID)) {
                jSONObject2.put("SSID", wifiSSID);
                jSONObject2.put(Constant.KEY_MAC, BaseInfo.getWifiBSSID(context));
                jSONObject.put(wifiSSID, jSONObject2);
            }
        }
        return jSONObject;
    }
}

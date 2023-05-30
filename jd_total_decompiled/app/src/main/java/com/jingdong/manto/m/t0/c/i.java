package com.jingdong.manto.m.t0.c;

import com.jingdong.manto.jsapi.bluetooth.sdk.util.BTHelper;
import com.jingdong.manto.m.d0;
import com.jingdong.manto.utils.MantoLog;
import com.tencent.mapsdk.internal.i2;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class i extends d0 {
    @Override // com.jingdong.manto.m.d0
    public void exec(com.jingdong.manto.h hVar, JSONObject jSONObject, int i2, String str) {
        ArrayList arrayList;
        String jSONObject2;
        HashMap hashMap;
        String str2;
        super.exec(hVar, jSONObject, i2, str);
        com.jingdong.manto.m.t0.b a = com.jingdong.manto.m.t0.a.a(hVar.a());
        if (a == null) {
            hashMap = new HashMap();
            hashMap.put("errCode", 10000);
            str2 = "fail:not init";
        } else if (BTHelper.btEnabled()) {
            JSONArray optJSONArray = jSONObject.optJSONArray(i2.d);
            if (optJSONArray != null) {
                int length = optJSONArray.length();
                arrayList = new ArrayList(length);
                for (int i3 = 0; i3 < length; i3++) {
                    String optString = optJSONArray.optString(i3);
                    if (optString != null) {
                        arrayList.add(optString);
                    }
                }
            } else {
                arrayList = null;
            }
            List<com.jingdong.manto.m.t0.d.d.h> a2 = a.a(arrayList);
            if (a2 == null) {
                hVar.a(i2, putErrMsg("fail:internal error", null));
                return;
            }
            JSONArray jSONArray = new JSONArray();
            for (com.jingdong.manto.m.t0.d.d.h hVar2 : a2) {
                String str3 = hVar2.a;
                String str4 = hVar2.f13690h;
                JSONObject jSONObject3 = new JSONObject();
                try {
                    jSONObject3.put("deviceId", str3);
                    jSONObject3.put("name", str4);
                    jSONObject3.put("localName", str4);
                    jSONArray.put(jSONObject3);
                } catch (Throwable th) {
                    MantoLog.e("BT.GetConnectedDevice", "put JSON data error ", th);
                }
            }
            JSONObject jSONObject4 = new JSONObject();
            try {
                jSONObject4.put("errMsg", getJsApiName() + ":ok");
                jSONObject4.put("devices", jSONArray);
            } catch (JSONException unused) {
            }
            jSONObject2 = jSONObject4.toString();
            hVar.a(i2, jSONObject2);
        } else {
            hashMap = new HashMap();
            hashMap.put("errCode", 10001);
            str2 = "fail:not available";
        }
        jSONObject2 = putErrMsg(str2, hashMap);
        hVar.a(i2, jSONObject2);
    }

    @Override // com.jingdong.manto.m.a
    public String getJsApiName() {
        return "getConnectedBluetoothDevices";
    }
}

package com.jingdong.manto.m.t0.c;

import android.text.TextUtils;
import com.jingdong.manto.jsapi.bluetooth.sdk.util.BTHelper;
import com.jingdong.manto.m.d0;
import com.tencent.mapsdk.internal.i2;
import java.util.HashMap;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class f extends d0 {
    @Override // com.jingdong.manto.m.d0
    public void exec(com.jingdong.manto.h hVar, JSONObject jSONObject, int i2, String str) {
        String putErrMsg;
        String jSONObject2;
        HashMap hashMap;
        String str2;
        super.exec(hVar, jSONObject, i2, str);
        if (jSONObject != null) {
            com.jingdong.manto.m.t0.b a = com.jingdong.manto.m.t0.a.a(hVar.a());
            if (a == null) {
                hashMap = new HashMap();
                hashMap.put("errCode", 10000);
                str2 = "fail:not init";
            } else if (BTHelper.btEnabled()) {
                List<com.jingdong.manto.m.t0.d.c.b> a2 = a.a(jSONObject.optString("deviceId"));
                HashMap hashMap2 = new HashMap();
                if (a2 != null && a2.size() > 0) {
                    JSONArray jSONArray = new JSONArray();
                    for (com.jingdong.manto.m.t0.d.c.b bVar : a2) {
                        if (!TextUtils.isEmpty(bVar.b)) {
                            try {
                                if (bVar.f13633c == null) {
                                    JSONObject jSONObject3 = new JSONObject();
                                    bVar.f13633c = jSONObject3;
                                    jSONObject3.put("uuid", bVar.b);
                                    bVar.f13633c.put("isPrimary", bVar.a);
                                }
                                jSONArray.put(bVar.f13633c);
                            } catch (JSONException unused) {
                            }
                        }
                    }
                    JSONObject jSONObject4 = new JSONObject();
                    try {
                        jSONObject4.put("errMsg", getJsApiName() + ":ok");
                        jSONObject4.put(i2.d, jSONArray);
                        jSONObject4.put("errCode", 0);
                    } catch (JSONException unused2) {
                    }
                    jSONObject2 = jSONObject4.toString();
                    hVar.a(i2, jSONObject2);
                    return;
                }
                hashMap2.put("errCode", 10004);
                putErrMsg = putErrMsg("fail:no service", hashMap2);
            } else {
                hashMap = new HashMap();
                hashMap.put("errCode", 10001);
                str2 = "fail:not available";
            }
            jSONObject2 = putErrMsg(str2, hashMap);
            hVar.a(i2, jSONObject2);
            return;
        }
        putErrMsg = putErrMsg("fail:invalid data", null);
        hVar.a(i2, putErrMsg);
    }

    @Override // com.jingdong.manto.m.a
    public String getJsApiName() {
        return "getBLEDeviceServices";
    }
}

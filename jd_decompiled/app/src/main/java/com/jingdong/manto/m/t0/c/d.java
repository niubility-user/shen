package com.jingdong.manto.m.t0.c;

import com.jingdong.manto.jsapi.bluetooth.sdk.util.BTHelper;
import com.jingdong.manto.m.d0;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class d extends d0 {
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
                List<com.jingdong.manto.m.t0.d.d.b> a2 = a.a(jSONObject.optString("deviceId"), jSONObject.optString("serviceId"));
                HashMap hashMap2 = new HashMap();
                if (a2 != null && a2.size() > 0) {
                    JSONArray jSONArray = new JSONArray();
                    Iterator<com.jingdong.manto.m.t0.d.d.b> it = a2.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        com.jingdong.manto.m.t0.d.d.b next = it.next();
                        try {
                            if (next.f13653g == null) {
                                JSONObject jSONObject3 = new JSONObject();
                                next.f13653g = jSONObject3;
                                jSONObject3.put("uuid", next.a);
                                JSONObject jSONObject4 = new JSONObject();
                                jSONObject4.put("read", next.f13650c);
                                jSONObject4.put("write", next.d || next.f13651e);
                                jSONObject4.put("notify", next.b);
                                jSONObject4.put("indicate", next.f13652f);
                                next.f13653g.put("properties", jSONObject4);
                            }
                            jSONArray.put(next.f13653g);
                        } catch (Throwable unused) {
                        }
                    }
                    JSONObject jSONObject5 = new JSONObject();
                    try {
                        jSONObject5.put("errMsg", getJsApiName() + ":ok");
                        jSONObject5.put("characteristics", jSONArray);
                        jSONObject5.put("errCode", 0);
                    } catch (JSONException e2) {
                        e2.printStackTrace();
                    }
                    jSONObject2 = jSONObject5.toString();
                    hVar.a(i2, jSONObject2);
                    return;
                }
                hashMap2.put("errCode", 10005);
                putErrMsg = putErrMsg("fail:no characteristic", hashMap2);
            } else {
                hashMap = new HashMap();
                hashMap.put("errCode", 10001);
                str2 = "fail:not available";
            }
            jSONObject2 = putErrMsg(str2, hashMap);
            hVar.a(i2, jSONObject2);
            return;
        }
        HashMap hashMap3 = new HashMap();
        hashMap3.put("errCode", 10013);
        putErrMsg = putErrMsg("fail:invalid data", hashMap3);
        hVar.a(i2, putErrMsg);
    }

    @Override // com.jingdong.manto.m.a
    public String getJsApiName() {
        return "getBLEDeviceCharacteristics";
    }
}

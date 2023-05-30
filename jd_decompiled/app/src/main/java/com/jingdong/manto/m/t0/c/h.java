package com.jingdong.manto.m.t0.c;

import com.jingdong.manto.jsapi.bluetooth.sdk.util.BTHelper;
import com.jingdong.manto.m.d0;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class h extends d0 {
    @Override // com.jingdong.manto.m.d0
    public void exec(com.jingdong.manto.h hVar, JSONObject jSONObject, int i2, String str) {
        String jSONObject2;
        com.jingdong.manto.m.t0.d.e.f fVar;
        Map<String, com.jingdong.manto.m.t0.d.d.h> map;
        Collection<com.jingdong.manto.m.t0.d.d.h> values;
        HashMap hashMap;
        String str2;
        super.exec(hVar, jSONObject, i2, str);
        try {
            CopyOnWriteArrayList copyOnWriteArrayList = new CopyOnWriteArrayList();
            com.jingdong.manto.m.t0.b a = com.jingdong.manto.m.t0.a.a(hVar.a());
            if (a == null) {
                hashMap = new HashMap();
                hashMap.put("errCode", 10000);
                str2 = "fail:not init";
            } else if (BTHelper.btEnabled()) {
                com.jingdong.manto.m.t0.d.b bVar = a.b;
                if (bVar != null && bVar != null && (fVar = bVar.a) != null && (map = fVar.f13716j) != null && (values = map.values()) != null && !values.isEmpty()) {
                    copyOnWriteArrayList.addAll(values);
                }
                JSONObject jSONObject3 = new JSONObject();
                JSONArray jSONArray = new JSONArray();
                List<com.jingdong.manto.m.t0.d.d.h> a2 = a.a();
                if (a2 != null && !a2.isEmpty()) {
                    copyOnWriteArrayList.addAll(a2);
                }
                if (!copyOnWriteArrayList.isEmpty()) {
                    for (com.jingdong.manto.m.t0.d.d.h hVar2 : new ArrayList(copyOnWriteArrayList)) {
                        if (hVar2 != null) {
                            jSONArray.put(hVar2.a());
                        }
                    }
                }
                try {
                    jSONObject3.put("errMsg", getJsApiName() + ":ok");
                    jSONObject3.put("devices", jSONArray);
                } catch (Throwable unused) {
                }
                jSONObject2 = jSONObject3.toString();
                hVar.a(i2, jSONObject2);
            } else {
                hashMap = new HashMap();
                hashMap.put("errCode", 10001);
                str2 = "fail:not available";
            }
            jSONObject2 = putErrMsg(str2, hashMap);
            hVar.a(i2, jSONObject2);
        } catch (Throwable th) {
            hVar.a(i2, putErrMsg("fail:" + th.getMessage()));
        }
    }

    @Override // com.jingdong.manto.m.a
    public String getJsApiName() {
        return "getBluetoothDevices";
    }
}

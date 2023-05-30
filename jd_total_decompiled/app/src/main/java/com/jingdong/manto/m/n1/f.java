package com.jingdong.manto.m.n1;

import com.jingdong.manto.jsapi.openmodule.IMantoBaseModule;
import com.jingdong.manto.m.l0;
import com.jingdong.manto.utils.MantoStringUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class f extends l0 {
    @Override // com.jingdong.manto.m.l0
    public String a(com.jingdong.manto.h hVar, JSONObject jSONObject) {
        g gVar = new g();
        gVar.f13462e = MantoStringUtils.optional(hVar.h().f13016h == null ? "" : hVar.h().f13016h.type, "");
        gVar.d = hVar.a();
        gVar.f();
        HashMap hashMap = new HashMap();
        ArrayList<String> arrayList = gVar.f13464g;
        if (arrayList != null && arrayList.size() > 0) {
            JSONArray jSONArray = new JSONArray();
            Iterator<String> it = gVar.f13464g.iterator();
            while (it.hasNext()) {
                jSONArray.put(it.next());
            }
            hashMap.put("keys", jSONArray);
        }
        hashMap.put("currentSize", Integer.valueOf(gVar.f13465h));
        hashMap.put("limitSize", Integer.valueOf(gVar.f13461c));
        return putErrMsg(IMantoBaseModule.SUCCESS, hashMap);
    }

    @Override // com.jingdong.manto.m.a
    public String getJsApiName() {
        return "getStorageInfoSync";
    }
}

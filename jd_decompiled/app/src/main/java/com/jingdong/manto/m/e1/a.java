package com.jingdong.manto.m.e1;

import com.jingdong.manto.h;
import com.jingdong.manto.m.d0;
import com.jingdong.manto.m.q1.f;
import com.jingdong.manto.utils.MantoLog;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class a extends d0 {
    @Override // com.jingdong.manto.m.d0
    public final void exec(h hVar, JSONObject jSONObject, int i2, String str) {
        if (!hVar.f() || jSONObject == null) {
            hVar.a(i2, putErrMsg("fail", null, str));
            return;
        }
        f fVar = new f();
        fVar.f13576c = 2;
        fVar.f13578f = i2;
        fVar.d = hVar;
        fVar.f13577e = this;
        JSONObject jSONObject2 = new JSONObject();
        try {
            String optString = jSONObject.optString("eventId");
            String optString2 = jSONObject.optString("api");
            jSONObject2.put("eventId", optString);
            jSONObject2.put("eventName", "\u63a5\u53e3\u8c03\u7528");
            jSONObject2.put("eventParam", hVar.a());
            jSONObject2.put("pageID", "");
            jSONObject2.put("page_name", "");
            jSONObject2.put("pageParam", "");
            JSONObject jSONObject3 = new JSONObject();
            jSONObject3.put("host_id", com.jingdong.a.b);
            jSONObject3.put("api", optString2);
            if (hVar.h() != null && hVar.h().f13016h != null) {
                jSONObject3.put("vapp_type", hVar.h().f13016h.type);
            }
            jSONObject2.put("jsonParam", jSONObject3.toString());
        } catch (Throwable th) {
            MantoLog.d("tag", th);
        }
        fVar.f13579g = jSONObject2.toString();
        fVar.f13582j = str;
        fVar.d();
    }

    @Override // com.jingdong.manto.m.a
    public String getJsApiName() {
        return "jsapiRealtimeReport";
    }
}

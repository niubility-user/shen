package com.jingdong.manto.m.q1;

import com.jingdong.manto.h;
import com.jingdong.manto.m.d0;
import com.jingdong.manto.utils.MantoLog;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class e extends d0 {
    @Override // com.jingdong.manto.m.d0
    public void exec(h hVar, JSONObject jSONObject, int i2, String str) {
        super.exec(hVar, jSONObject, i2, str);
        if (!hVar.f() || jSONObject == null) {
            hVar.a(i2, putErrMsg("fail", null, str));
            return;
        }
        f fVar = new f();
        fVar.f13576c = 1;
        fVar.f13578f = i2;
        fVar.d = hVar;
        fVar.f13577e = this;
        try {
            jSONObject.put("page_param", (jSONObject.has("page_param") ? new JSONObject(jSONObject.getString("page_param")) : new JSONObject()).toString());
        } catch (Exception e2) {
            MantoLog.d("tag", e2);
        }
        fVar.f13579g = jSONObject.toString();
        fVar.f13582j = str;
        fVar.d();
    }

    @Override // com.jingdong.manto.m.a
    public String getJsApiName() {
        return "sendPvData";
    }
}

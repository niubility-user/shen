package com.jingdong.manto.m.e1;

import com.huawei.hms.actions.SearchIntents;
import com.jingdong.jdma.minterface.BaseEvent;
import com.jingdong.manto.h;
import com.jingdong.manto.m.d0;
import com.jingdong.manto.m.q1.f;
import com.jingdong.manto.utils.MantoLog;
import com.jingdong.manto.utils.MantoStringUtils;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class b extends d0 {
    @Override // com.jingdong.manto.m.d0
    public final void exec(h hVar, JSONObject jSONObject, int i2, String str) {
        JSONObject optJSONObject;
        if (!hVar.f() || jSONObject == null) {
            hVar.a(i2, putErrMsg("fail", null, str));
            return;
        }
        f fVar = new f();
        fVar.f13576c = 1;
        fVar.f13578f = i2;
        fVar.d = hVar;
        fVar.f13577e = this;
        JSONObject jSONObject2 = new JSONObject();
        try {
            optJSONObject = jSONObject.optJSONObject("actionData");
        } catch (Throwable th) {
            MantoLog.d("tag", th);
        }
        if (optJSONObject == null) {
            hVar.a(i2, putErrMsg("fail:no data", null, str));
            return;
        }
        jSONObject2.put("page_id", "applets_pagesend");
        jSONObject2.put("page_name", optJSONObject.optString("page", ""));
        JSONObject jSONObject3 = new JSONObject();
        jSONObject3.put("appId", hVar.a());
        if (hVar.h() != null && hVar.h().f13016h != null) {
            jSONObject3.put("vapp_type", hVar.h().f13016h.type);
            jSONObject3.put("version", String.valueOf(hVar.h().f13016h.build));
        }
        jSONObject3.put("host_id", com.jingdong.manto.b.e());
        jSONObject3.put("enter_flag", optJSONObject.optInt("enter_flag", 0));
        jSONObject3.put("time_type", optJSONObject.optString("time_type", "page"));
        jSONObject3.put("cost", optJSONObject.optInt("cost", 0));
        try {
            jSONObject3.put(SearchIntents.EXTRA_QUERY, new JSONObject(optJSONObject.optString(SearchIntents.EXTRA_QUERY)));
            if (hVar.h().r != null) {
                String str2 = hVar.h().r.f13091n;
                if (MantoStringUtils.isEmpty(str2)) {
                    str2 = "0";
                }
                jSONObject3.put(BaseEvent.SCENE, str2);
            }
        } catch (Exception unused) {
        }
        jSONObject2.put("page_param", jSONObject3.toString());
        fVar.f13579g = jSONObject2.toString();
        fVar.f13582j = str;
        fVar.d();
    }

    @Override // com.jingdong.manto.m.a
    public String getJsApiName() {
        return "reportCycleAction";
    }
}

package com.jingdong.manto.m;

import com.jingdong.manto.jsapi.openmodule.IMantoBaseModule;
import com.jingdong.manto.utils.MantoLog;
import java.util.HashMap;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class f extends d0 {
    private static final String a = "f";

    @Override // com.jingdong.manto.m.d0
    public final void exec(com.jingdong.manto.h hVar, JSONObject jSONObject, int i2, String str) {
        if (jSONObject == null) {
            hVar.a(i2, putErrMsg("fail", null, str));
            MantoLog.e(a, "data is null");
        } else if (jSONObject.optInt("type", 0) <= 0) {
            hVar.a(i2, putErrMsg("fail", null, str));
        } else {
            HashMap hashMap = new HashMap();
            hashMap.put("data", IMantoBaseModule.SUCCESS);
            hVar.a(i2, putErrMsg(IMantoBaseModule.SUCCESS, hashMap, str));
        }
    }

    @Override // com.jingdong.manto.m.a
    public String getJsApiName() {
        return "getAppConfig";
    }
}

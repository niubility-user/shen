package com.jingdong.manto.m;

import com.jingdong.manto.jsapi.openmodule.IMantoBaseModule;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class c extends d0 {
    @Override // com.jingdong.manto.m.d0
    public final void exec(com.jingdong.manto.q.n nVar, JSONObject jSONObject, int i2, String str) {
        if (jSONObject.has("disable")) {
            if (jSONObject.optBoolean("disable", false)) {
                nVar.a(false);
            } else {
                nVar.a(nVar.h().t.b(nVar.r()).f13065f);
            }
        }
        nVar.a(i2, putErrMsg(IMantoBaseModule.SUCCESS, null, str));
    }

    @Override // com.jingdong.manto.m.a
    public String getJsApiName() {
        return "disableScrollBounce";
    }
}

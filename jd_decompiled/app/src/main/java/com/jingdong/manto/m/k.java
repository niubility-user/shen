package com.jingdong.manto.m;

import com.jingdong.manto.jsapi.openmodule.IMantoBaseModule;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class k extends d0 {
    @Override // com.jingdong.manto.m.d0
    public final void exec(com.jingdong.manto.q.n nVar, JSONObject jSONObject, int i2, String str) {
        if (jSONObject.optBoolean("listen")) {
            nVar.y = true;
        }
        nVar.a(i2, putErrMsg(IMantoBaseModule.SUCCESS, null, str));
    }

    @Override // com.jingdong.manto.m.a
    public String getJsApiName() {
        return "listenTapStatusBar";
    }
}

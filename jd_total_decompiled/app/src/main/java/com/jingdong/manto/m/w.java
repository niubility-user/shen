package com.jingdong.manto.m;

import com.jingdong.manto.jsapi.openmodule.IMantoBaseModule;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class w extends d0 {
    @Override // com.jingdong.manto.m.d0
    public void exec(com.jingdong.manto.h hVar, JSONObject jSONObject, int i2, String str) {
        boolean optBoolean = jSONObject.optBoolean("allow");
        com.jingdong.manto.q.n pageView = c0.getPageView(hVar);
        if (pageView != null) {
            pageView.d(!optBoolean);
        }
        hVar.a(i2, putErrMsg(IMantoBaseModule.SUCCESS, null, str));
    }

    @Override // com.jingdong.manto.m.a
    public String getJsApiName() {
        return "setPopGesture";
    }
}

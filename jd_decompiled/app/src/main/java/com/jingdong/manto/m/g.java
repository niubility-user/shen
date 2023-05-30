package com.jingdong.manto.m;

import org.json.JSONObject;

/* loaded from: classes15.dex */
public class g extends d0 {
    @Override // com.jingdong.manto.m.d0
    public void exec(com.jingdong.manto.h hVar, JSONObject jSONObject, int i2, String str) {
        hVar.a(i2, putErrMsg("fail:api is forbidden", null, str));
    }

    @Override // com.jingdong.manto.m.a
    public String getJsApiName() {
        return "getClipboardData";
    }
}

package com.jingdong.manto.m.u0;

import org.json.JSONObject;

/* loaded from: classes15.dex */
public class k extends com.jingdong.manto.jsapi.base.b {
    @Override // com.jingdong.manto.jsapi.base.d
    public final int a(JSONObject jSONObject) {
        return jSONObject.optInt("canvasId");
    }

    @Override // com.jingdong.manto.jsapi.base.d, com.jingdong.manto.m.a
    public String getJsApiName() {
        return "removeCanvas";
    }
}

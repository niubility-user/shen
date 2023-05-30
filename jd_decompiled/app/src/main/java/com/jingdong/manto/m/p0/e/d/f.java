package com.jingdong.manto.m.p0.e.d;

import com.jingdong.manto.h;
import com.jingdong.manto.jsapi.openmodule.IMantoBaseModule;
import com.jingdong.manto.m.d0;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class f extends d0 {
    @Override // com.jingdong.manto.m.d0
    public void exec(h hVar, JSONObject jSONObject, int i2, String str) {
        jSONObject.optBoolean("mixWithOther", true);
        hVar.a(i2, putErrMsg(IMantoBaseModule.SUCCESS));
    }

    @Override // com.jingdong.manto.m.a
    public String getJsApiName() {
        return "setInnerAudioOption";
    }
}

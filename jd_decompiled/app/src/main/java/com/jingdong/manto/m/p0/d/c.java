package com.jingdong.manto.m.p0.d;

import com.jingdong.manto.h;
import com.jingdong.manto.jsapi.openmodule.IMantoBaseModule;
import com.jingdong.manto.m.l0;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class c extends l0 {
    @Override // com.jingdong.manto.m.l0
    public String a(h hVar, JSONObject jSONObject) {
        try {
            return putErrMsg(IMantoBaseModule.SUCCESS, a.a(hVar));
        } catch (Throwable unused) {
            return putErrMsg("fail:system error");
        }
    }

    @Override // com.jingdong.manto.m.a
    public String getJsApiName() {
        return "getBackgroundAudioState";
    }
}

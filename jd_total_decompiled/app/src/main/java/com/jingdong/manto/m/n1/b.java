package com.jingdong.manto.m.n1;

import com.jingdong.manto.jsapi.openmodule.IMantoBaseModule;
import com.jingdong.manto.m.l0;
import com.jingdong.manto.utils.MantoStringUtils;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class b extends l0 {
    @Override // com.jingdong.manto.m.l0
    public final String a(com.jingdong.manto.h hVar, JSONObject jSONObject) {
        c cVar = new c();
        cVar.d = MantoStringUtils.optional(hVar.h().f13016h == null ? "" : hVar.h().f13016h.type, "");
        cVar.f13456c = hVar.a();
        cVar.f();
        return putErrMsg(IMantoBaseModule.SUCCESS, null);
    }

    @Override // com.jingdong.manto.m.a
    public String getJsApiName() {
        return "clearStorageSync";
    }
}

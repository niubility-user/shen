package com.jingdong.manto.m.t0.c;

import com.jingdong.manto.jsapi.openmodule.IMantoBaseModule;
import com.jingdong.manto.m.d0;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class b extends d0 {
    @Override // com.jingdong.manto.m.d0
    public void exec(com.jingdong.manto.h hVar, JSONObject jSONObject, int i2, String str) {
        super.exec(hVar, jSONObject, i2, str);
        com.jingdong.manto.m.t0.d.d.e a = com.jingdong.manto.m.t0.a.a(hVar.a(), true);
        hVar.a(i2, putErrMsg(a.a != 0 ? a.b : IMantoBaseModule.SUCCESS));
    }

    @Override // com.jingdong.manto.m.a
    public String getJsApiName() {
        return "closeBluetoothAdapter";
    }
}

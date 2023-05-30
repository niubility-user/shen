package com.jingdong.manto.m;

import org.json.JSONObject;

/* loaded from: classes15.dex */
public class g0 extends d0 {
    @Override // com.jingdong.manto.m.d0
    public void exec(com.jingdong.manto.q.n nVar, JSONObject jSONObject, int i2, String str) {
        nVar.H();
        if (nVar.h() != null) {
            nVar.h().L();
        }
        com.jingdong.manto.r.d.a(nVar.h());
    }

    @Override // com.jingdong.manto.m.a
    public String getJsApiName() {
        return "initReady";
    }
}

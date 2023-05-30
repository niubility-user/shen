package com.jingdong.manto.m;

import com.jingdong.manto.utils.MantoLog;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class o extends d0 {
    @Override // com.jingdong.manto.m.d0
    public final void exec(com.jingdong.manto.h hVar, JSONObject jSONObject, int i2, String str) {
        MantoLog.d("pageNotFoundCallback", "not found callback");
    }

    @Override // com.jingdong.manto.m.a
    public String getJsApiName() {
        return "pageNotFoundCallback";
    }
}

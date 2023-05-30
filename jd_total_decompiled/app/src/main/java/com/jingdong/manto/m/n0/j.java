package com.jingdong.manto.m.n0;

import com.jingdong.manto.jsapi.openmodule.IMantoBaseModule;
import com.jingdong.manto.m.c0;
import com.jingdong.manto.m.d0;
import com.jingdong.manto.q.n;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class j extends d0 {
    @Override // com.jingdong.manto.m.d0
    public final void exec(com.jingdong.manto.h hVar, JSONObject jSONObject, int i2, String str) {
        String str2;
        String optString = jSONObject.optString("title");
        n pageView = c0.getPageView(hVar);
        if (pageView == null) {
            str2 = "fail";
        } else {
            pageView.h(optString);
            str2 = IMantoBaseModule.SUCCESS;
        }
        hVar.a(i2, putErrMsg(str2, null, str));
    }

    @Override // com.jingdong.manto.m.a
    public String getJsApiName() {
        return "setNavigationBarTitle";
    }
}

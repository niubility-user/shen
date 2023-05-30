package com.jingdong.manto.m;

import com.jingdong.manto.jsapi.openmodule.IMantoBaseModule;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class y extends d0 {
    @Override // com.jingdong.manto.m.d0
    public final void exec(com.jingdong.manto.h hVar, JSONObject jSONObject, int i2, String str) {
        String str2;
        com.jingdong.manto.q.n pageView = c0.getPageView(hVar);
        if (pageView == null) {
            str2 = "fail";
        } else {
            pageView.M();
            str2 = IMantoBaseModule.SUCCESS;
        }
        hVar.a(i2, putErrMsg(str2, null, str));
    }

    @Override // com.jingdong.manto.m.a
    public String getJsApiName() {
        return "startPullDownRefresh";
    }
}

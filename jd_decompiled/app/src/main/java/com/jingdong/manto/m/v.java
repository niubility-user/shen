package com.jingdong.manto.m;

import com.jingdong.manto.jsapi.openmodule.IMantoBaseModule;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class v extends d0 {

    /* loaded from: classes15.dex */
    public static class a extends d {
        @Override // com.jingdong.manto.m.a
        public String getJsApiName() {
            return "onNavigateBackConfirmEvent";
        }
    }

    @Override // com.jingdong.manto.m.d0
    public final void exec(com.jingdong.manto.h hVar, JSONObject jSONObject, int i2, String str) {
        com.jingdong.manto.q.l lVar;
        com.jingdong.manto.q.j firstPage;
        com.jingdong.manto.q.n i3;
        String putErrMsg;
        if (hVar == null) {
            return;
        }
        boolean optBoolean = jSONObject.optBoolean("enable", false);
        String optString = jSONObject.optString("content", "");
        String optString2 = jSONObject.optString("mode", "");
        com.jingdong.manto.f h2 = hVar.h();
        if (h2 == null || (lVar = h2.f13014f) == null || (firstPage = lVar.getFirstPage()) == null || (i3 = firstPage.i()) == null) {
            putErrMsg = putErrMsg("fail", null, str);
        } else {
            i3.a(optBoolean, optString, optString2);
            putErrMsg = putErrMsg(IMantoBaseModule.SUCCESS, null, str);
        }
        hVar.a(i2, putErrMsg);
    }

    @Override // com.jingdong.manto.m.a
    public String getJsApiName() {
        return "setNavigateBackConfirmDialog";
    }
}

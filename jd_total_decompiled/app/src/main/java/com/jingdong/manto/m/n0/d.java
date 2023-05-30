package com.jingdong.manto.m.n0;

import com.jingdong.manto.jsapi.openmodule.IMantoBaseModule;
import com.jingdong.manto.m.c0;
import com.jingdong.manto.m.d0;
import com.jingdong.manto.q.n;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class d extends d0 {
    /* JADX WARN: Code restructure failed: missing block: B:11:0x002b, code lost:
        if (r6.equals("black") != false) goto L9;
     */
    @Override // com.jingdong.manto.m.d0
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void exec(com.jingdong.manto.h hVar, JSONObject jSONObject, int i2, String str) {
        String str2;
        String optString = jSONObject.optString("color", "");
        n pageView = c0.getPageView(hVar);
        if (pageView == null) {
            str2 = "fail";
        } else {
            String str3 = optString.equals("white") ? "white" : "black";
            pageView.a(str3);
            str2 = IMantoBaseModule.SUCCESS;
        }
        hVar.a(i2, putErrMsg(str2, null, str));
    }

    @Override // com.jingdong.manto.m.a
    public String getJsApiName() {
        return "setStatusBarStyle";
    }
}

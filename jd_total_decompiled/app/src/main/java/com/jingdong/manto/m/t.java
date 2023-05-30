package com.jingdong.manto.m;

import android.text.TextUtils;
import com.jingdong.manto.jsapi.openmodule.IMantoBaseModule;
import com.jingdong.manto.utils.MantoStringUtils;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class t extends d0 {
    @Override // com.jingdong.manto.m.d0
    public final void exec(com.jingdong.manto.h hVar, JSONObject jSONObject, int i2, String str) {
        boolean optBoolean = jSONObject.optBoolean("enableDebug", false);
        if (hVar.h().s.f13094c != optBoolean) {
            String optional = MantoStringUtils.optional(hVar.h().f13016h == null ? "" : hVar.h().f13016h.type, "");
            if (!TextUtils.equals(optional, "1") || !hVar.h().u()) {
                com.jingdong.manto.m.w0.b.a(hVar.p(), hVar.a(), optBoolean, optional);
            }
        }
        hVar.a(i2, putErrMsg(IMantoBaseModule.SUCCESS, null, str));
    }

    @Override // com.jingdong.manto.m.a
    public String getJsApiName() {
        return "setEnableDebug";
    }
}

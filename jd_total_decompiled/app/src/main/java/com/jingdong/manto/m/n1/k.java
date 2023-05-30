package com.jingdong.manto.m.n1;

import android.text.TextUtils;
import com.jingdong.manto.jsapi.openmodule.IMantoBaseModule;
import com.jingdong.manto.m.l0;
import com.jingdong.manto.utils.MantoStringUtils;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class k extends l0 {
    @Override // com.jingdong.manto.m.l0
    public final String a(com.jingdong.manto.h hVar, JSONObject jSONObject) {
        String str;
        String optString = jSONObject.optString("key");
        if (TextUtils.isEmpty(optString)) {
            str = "fail";
        } else {
            l lVar = new l();
            lVar.f13475e = MantoStringUtils.optional(hVar.h().f13016h == null ? "" : hVar.h().f13016h.type, "");
            lVar.d = hVar.a();
            lVar.f13474c = optString;
            lVar.f();
            str = IMantoBaseModule.SUCCESS;
        }
        return putErrMsg(str, null);
    }

    @Override // com.jingdong.manto.m.a
    public String getJsApiName() {
        return "removeStorageSync";
    }
}

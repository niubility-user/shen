package com.jingdong.manto.m.n1;

import android.text.TextUtils;
import com.jingdong.manto.jsapi.openmodule.IMantoBaseModule;
import com.jingdong.manto.m.d0;
import com.jingdong.manto.utils.MantoStringUtils;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class j extends d0 {
    @Override // com.jingdong.manto.m.d0
    public final void exec(com.jingdong.manto.h hVar, JSONObject jSONObject, int i2, String str) {
        String str2;
        String optString = jSONObject.optString("key");
        if (TextUtils.isEmpty(optString)) {
            str2 = "fail";
        } else {
            l lVar = new l();
            lVar.f13475e = MantoStringUtils.optional(hVar.h().f13016h == null ? "" : hVar.h().f13016h.type, "");
            lVar.d = hVar.a();
            lVar.f13474c = optString;
            lVar.e();
            str2 = IMantoBaseModule.SUCCESS;
        }
        hVar.a(i2, putErrMsg(str2, null, str));
    }

    @Override // com.jingdong.manto.m.a
    public String getJsApiName() {
        return "removeStorage";
    }
}

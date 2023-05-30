package com.jingdong.manto.m.n1;

import android.text.TextUtils;
import com.jingdong.manto.jsapi.openmodule.IMantoBaseModule;
import com.jingdong.manto.m.l0;
import com.jingdong.manto.t.b;
import com.jingdong.manto.utils.MantoStringUtils;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class n extends l0 {
    @Override // com.jingdong.manto.m.l0
    public final String a(com.jingdong.manto.h hVar, JSONObject jSONObject) {
        String optString = jSONObject.optString("key");
        String optString2 = jSONObject.optString("data");
        String optString3 = jSONObject.optString("dataType");
        String str = "fail";
        if (TextUtils.isEmpty(optString)) {
            return putErrMsg("fail", null);
        }
        if ((optString2 == null ? 0 : optString2.length()) + optString.length() > hVar.h().s.p.b) {
            return putErrMsg("fail:entry size limit reached", null);
        }
        try {
            b.a a = com.jingdong.manto.t.b.a(hVar.a(), optString, optString2, optString3, MantoStringUtils.optional(hVar.h().f13016h == null ? "" : hVar.h().f13016h.type, ""));
            if (a == b.a.NONE) {
                str = IMantoBaseModule.SUCCESS;
            } else if (a == b.a.QUOTA_REACHED) {
                str = "fail:quota reached";
            }
        } catch (Throwable unused) {
        }
        return putErrMsg(str, null);
    }

    @Override // com.jingdong.manto.m.a
    public String getJsApiName() {
        return "setStorageSync";
    }
}

package com.jingdong.manto.m.n1;

import android.text.TextUtils;
import com.jingdong.manto.jsapi.openmodule.IMantoBaseModule;
import com.jingdong.manto.m.l0;
import com.jingdong.manto.utils.MantoStringUtils;
import java.util.HashMap;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class h extends l0 {
    @Override // com.jingdong.manto.m.l0
    public final String a(com.jingdong.manto.h hVar, JSONObject jSONObject) {
        String str;
        HashMap hashMap;
        String optString = jSONObject.optString("key");
        str = "fail";
        if (TextUtils.isEmpty(optString)) {
            hashMap = null;
        } else {
            i iVar = new i();
            iVar.f13467e = MantoStringUtils.optional(hVar.h().f13016h == null ? "" : hVar.h().f13016h.type, "");
            iVar.d = hVar.a();
            iVar.f13466c = optString;
            iVar.f();
            str = iVar.f13473k != null ? IMantoBaseModule.SUCCESS : "fail";
            hashMap = new HashMap();
            String str2 = iVar.f13473k;
            if (str2 == null) {
                str2 = "";
            }
            hashMap.put("data", str2);
            String str3 = iVar.f13472j;
            hashMap.put("dataType", str3 != null ? str3 : "");
        }
        return putErrMsg(str, hashMap);
    }

    @Override // com.jingdong.manto.m.a
    public String getJsApiName() {
        return "getStorageSync";
    }
}

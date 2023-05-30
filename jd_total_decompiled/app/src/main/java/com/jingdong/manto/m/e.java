package com.jingdong.manto.m;

import android.text.TextUtils;
import com.jingdong.manto.jsapi.openmodule.IMantoBaseModule;
import com.tencent.open.miniapp.MiniApp;
import java.util.HashMap;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class e extends l0 {
    @Override // com.jingdong.manto.m.l0
    public String a(com.jingdong.manto.h hVar, JSONObject jSONObject) {
        HashMap hashMap = new HashMap(2);
        if (hVar == null || hVar.h().f13016h == null) {
            return putErrMsg("fail", null);
        }
        String str = hVar.h().f13016h.appId;
        HashMap hashMap2 = new HashMap();
        hashMap2.put("appId", str);
        hashMap2.put("version", hVar.h().f13016h.versionName);
        String str2 = hVar.h().f13016h.type;
        hashMap2.put("envVersion", TextUtils.equals(str2, "2") ? MiniApp.MINIAPP_VERSION_TRIAL : TextUtils.equals(str2, "5") ? MiniApp.MINIAPP_VERSION_DEVELOP : "release");
        hashMap.put("miniProgram", hashMap2);
        return putErrMsg(IMantoBaseModule.SUCCESS, hashMap);
    }

    @Override // com.jingdong.manto.m.a
    public String getJsApiName() {
        return "getAccountInfoSync";
    }
}

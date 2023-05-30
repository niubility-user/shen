package com.jingdong.manto.m.s0;

import com.jingdong.manto.h;
import com.jingdong.manto.jsapi.openmodule.IMantoBaseModule;
import com.jingdong.manto.m.l0;
import com.jingdong.manto.utils.k;
import java.util.HashMap;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class a extends l0 {
    @Override // com.jingdong.manto.m.l0
    public String a(h hVar, JSONObject jSONObject) {
        HashMap hashMap;
        String str;
        if (hVar == null) {
            hashMap = null;
            str = "fail";
        } else {
            hashMap = new HashMap();
            hashMap.put("level", Integer.valueOf(k.c().a()));
            hashMap.put("isCharging", Boolean.valueOf(k.c().b()));
            str = IMantoBaseModule.SUCCESS;
        }
        return putErrMsg(str, hashMap);
    }

    @Override // com.jingdong.manto.m.a
    public String getJsApiName() {
        return "getBatteryInfo";
    }
}

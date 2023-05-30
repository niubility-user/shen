package com.jingdong.manto.m;

import com.jingdong.manto.jsapi.openmodule.IMantoBaseModule;
import com.jingdong.manto.utils.MantoUtils;
import java.util.HashMap;
import org.json.JSONObject;
import tv.danmaku.ijk.media.pha.JDHPlayerJSEvent;

/* loaded from: classes15.dex */
public class h extends d0 {
    @Override // com.jingdong.manto.m.d0
    public void exec(com.jingdong.manto.h hVar, JSONObject jSONObject, int i2, String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("networkType", MantoUtils.getNetworkType(hVar.p()));
        hVar.a(i2, putErrMsg(IMantoBaseModule.SUCCESS, hashMap, str));
    }

    @Override // com.jingdong.manto.m.a
    public String getJsApiName() {
        return JDHPlayerJSEvent.GET_NETTYPE;
    }
}

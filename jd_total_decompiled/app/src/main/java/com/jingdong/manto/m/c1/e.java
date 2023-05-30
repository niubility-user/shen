package com.jingdong.manto.m.c1;

import android.text.TextUtils;
import com.jingdong.common.XView2.common.XView2Constants;
import com.jingdong.manto.jsapi.openmodule.IMantoBaseModule;
import com.jingdong.manto.m.l0;
import com.jingdong.manto.utils.MantoLog;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class e extends l0 {

    /* loaded from: classes15.dex */
    public static class a extends com.jingdong.manto.m.d {
        @Override // com.jingdong.manto.m.a
        public String getJsApiName() {
            return "onSocketTaskStateChange";
        }
    }

    @Override // com.jingdong.manto.m.l0
    public String a(com.jingdong.manto.h hVar, JSONObject jSONObject) {
        m mVar;
        String c2 = c();
        String optString = jSONObject.optString("url");
        if (TextUtils.isEmpty(optString)) {
            MantoLog.i("JsApiCreateSocketTask", "url is null");
            HashMap hashMap = new HashMap();
            hashMap.put("socketTaskId", c2);
            hashMap.put(XView2Constants.STATE, "error");
            m.a(hVar, c2, "url is null");
            return putErrMsg("fail: url is null", hashMap);
        }
        MantoLog.i("JsApiCreateSocketTask", "url is " + optString);
        com.jingdong.manto.i.e eVar = hVar.h().s;
        Map<String, String> a2 = com.jingdong.manto.p.c.a(jSONObject, eVar);
        if (com.jingdong.manto.p.c.a(eVar, jSONObject.optBoolean("__skipDomainCheck__")) && !com.jingdong.manto.p.c.a(eVar.f13102l, optString, eVar.o)) {
            MantoLog.i("JsApiCreateSocketTask", "not in domain url : " + optString);
            HashMap hashMap2 = new HashMap();
            hashMap2.put("socketTaskId", c2);
            hashMap2.put(XView2Constants.STATE, "error");
            m.a(hVar, c2, "url not in domain list");
            return putErrMsg("fail: url not in domain list", hashMap2);
        }
        int optInt = jSONObject.optInt("timeout", 0);
        if (optInt <= 0) {
            optInt = com.jingdong.manto.p.c.a(eVar, hVar, com.jingdong.manto.p.c.SOCKET);
        }
        int i2 = optInt <= 0 ? 60000 : optInt;
        int i3 = eVar.f13098h;
        if (i3 < 1) {
            i3 = 10;
        }
        if (l.c().a.get(hVar.c()) == null) {
            mVar = new m(hVar.c(), i3);
            l.c().a.put(hVar.c(), mVar);
        } else {
            mVar = l.c().a.get(hVar.c());
        }
        String str = "size:" + mVar.a.size();
        synchronized (mVar.a) {
            if (mVar.a.size() < i3) {
                mVar.a(hVar, c2, i2, jSONObject, a2);
                MantoLog.i("JsApiCreateSocketTask", String.format("connectSocket, url is : %s ,appid: %s", optString, eVar.a));
                HashMap hashMap3 = new HashMap();
                hashMap3.put(b(), c2);
                return putErrMsg(IMantoBaseModule.SUCCESS, hashMap3);
            }
            HashMap hashMap4 = new HashMap();
            hashMap4.put("socketTaskId", c2);
            hashMap4.put(XView2Constants.STATE, "error");
            m.a(hVar, c2, "max connected");
            return putErrMsg("fail: max connected is " + i3, hashMap4);
        }
    }

    protected String b() {
        return "socketTaskId";
    }

    protected String c() {
        StringBuilder sb = new StringBuilder();
        sb.append(l.a());
        return sb.toString();
    }

    @Override // com.jingdong.manto.m.a
    public String getJsApiName() {
        return "createSocketTask";
    }
}

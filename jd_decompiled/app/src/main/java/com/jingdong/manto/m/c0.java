package com.jingdong.manto.m;

import com.jingdong.manto.MantoCore;
import com.jingdong.manto.utils.MantoUtils;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public abstract class c0 implements a {
    public String msg;
    public boolean webAPI = false;

    public static com.jingdong.manto.q.n getPageView(e0 e0Var) {
        com.jingdong.manto.q.j firstPage;
        com.jingdong.manto.q.l lVar = e0Var.h().f13014f;
        if (lVar == null || (firstPage = lVar.getFirstPage()) == null) {
            return null;
        }
        return firstPage.i();
    }

    public final String a(com.jingdong.manto.h hVar, String str, Map<String, Object> map) {
        return com.jingdong.manto.utils.u.a(hVar, map, this) ? putErrMsg(str, map, null) : putErrMsg(this.msg, null, getJsApiName());
    }

    public final String a(com.jingdong.manto.h hVar, String str, Map<String, Object> map, String str2) {
        return com.jingdong.manto.utils.u.a(hVar, map, this) ? putErrMsg(str, map, str2) : putErrMsg(this.msg, null, str2);
    }

    public MantoCore getCore(e0 e0Var) {
        com.jingdong.manto.f h2 = e0Var.h();
        if (h2 == null) {
            return null;
        }
        return h2.k();
    }

    public boolean isAppForeground(com.jingdong.manto.h hVar) {
        com.jingdong.manto.f h2;
        return (hVar == null || (h2 = hVar.h()) == null || !h2.t()) ? false : true;
    }

    public final String putErrMsg(String str) {
        return putErrMsg(str, null, getJsApiName());
    }

    public final String putErrMsg(String str, Map<String, ? extends Object> map) {
        return putErrMsg(str, map, getJsApiName());
    }

    public final String putErrMsg(String str, Map<String, ? extends Object> map, String str2) {
        HashMap hashMap = new HashMap();
        hashMap.put("errMsg", str2 + ":" + str);
        if (map != null) {
            if (map.containsKey("errMsg") && com.jingdong.a.a) {
                throw new RuntimeException("api " + getJsApiName() + ": Cant put errMsg in res!!!");
            }
            hashMap.putAll(map);
        }
        MantoUtils.mapToJson(hashMap);
        return new JSONObject(hashMap).toString();
    }
}

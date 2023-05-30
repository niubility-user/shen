package com.jd.stat.security.jma.b;

import com.jd.stat.common.t;
import com.jingdong.jdsdk.config.Configuration;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes18.dex */
public abstract class a implements f {
    private String a;
    private String b;

    /* JADX INFO: Access modifiers changed from: protected */
    public JSONObject a(ArrayList<JSONObject> arrayList) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("appname", com.jd.stat.common.c.a(com.jd.stat.security.c.a));
        jSONObject.put("appid", com.jd.stat.security.c.g());
        jSONObject.put("client", "android");
        JSONArray jSONArray = new JSONArray();
        if (arrayList != null && !arrayList.isEmpty()) {
            Iterator<JSONObject> it = arrayList.iterator();
            while (it.hasNext()) {
                JSONObject next = it.next();
                if (next != null) {
                    JSONObject a = com.jd.stat.common.b.a.a(com.jd.stat.security.c.a, a(next));
                    a.put("functionId", next.optString("eventid"));
                    a.put("sdkversion", "2.5.8");
                    jSONArray.put(a);
                }
            }
        }
        jSONObject.put("jdkey", t.b());
        jSONObject.put("body", jSONArray);
        jSONObject.put("whwswswws", a());
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put("content", jSONObject);
        return jSONObject2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void b(JSONObject jSONObject) throws JSONException {
        jSONObject.put("whwswswws", a());
        jSONObject.put("sid", com.jd.stat.common.d.a(com.jd.stat.security.c.a).a());
        jSONObject.put("seq", com.jd.stat.common.d.a(com.jd.stat.security.c.a).b());
        jSONObject.put("clienttime", com.jd.stat.common.b.g.a());
        jSONObject.put("jdkey", t.a(com.jd.stat.security.c.a));
        jSONObject.put("extkey", com.jd.stat.security.c.a());
        jSONObject.put(Configuration.UNION_ID, com.jd.stat.security.c.b());
        jSONObject.put(Configuration.SUB_UNION_ID, com.jd.stat.security.c.c());
        jSONObject.put(Configuration.PARTNER, com.jd.stat.security.c.d());
        jSONObject.put("installtionid", com.jd.stat.security.c.h());
    }

    public String c() {
        return this.a;
    }

    public String b() {
        return this.b;
    }

    public void b(String str) {
        this.a = str;
    }

    public JSONObject a(JSONObject jSONObject) throws JSONException {
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put("sid", com.jd.stat.common.d.a(com.jd.stat.security.c.a).a());
        jSONObject2.put("seq", com.jd.stat.common.d.a(com.jd.stat.security.c.a).b());
        jSONObject2.put("extkey", com.jd.stat.security.c.a());
        jSONObject2.put("clienttime", com.jd.stat.common.b.g.a());
        jSONObject2.put("jdkey", t.a(com.jd.stat.security.c.a));
        jSONObject2.put("clientversion", com.jd.stat.common.c.c(com.jd.stat.security.c.a));
        jSONObject2.put("client", "android");
        jSONObject2.put("sdkversion", "2.5.8");
        jSONObject2.put("whwswswws", a());
        jSONObject2.put("installtionid", com.jd.stat.security.c.h());
        jSONObject2.put("eventid", jSONObject.optString("eventid"));
        jSONObject2.put("uid", jSONObject.optString("uid"));
        jSONObject2.put("eventparam", jSONObject);
        return jSONObject2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String a() {
        return com.jd.stat.security.e.a(com.jd.stat.security.c.a).a();
    }

    public void a(String str) {
        this.b = str;
    }
}

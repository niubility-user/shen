package com.jd.stat.security.jma.b;

import com.jd.stat.common.t;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes18.dex */
public class e extends a implements f {
    /* JADX INFO: Access modifiers changed from: private */
    public JSONObject d() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        com.jd.stat.common.b.c.a(jSONObject, com.jd.stat.security.jma.a.e.b(c(), b()), com.jd.stat.security.jma.a.e.c(), com.jd.stat.security.jma.a.e.d());
        b(jSONObject);
        JSONObject a = com.jd.stat.common.b.a.a(com.jd.stat.security.c.a, jSONObject);
        a.put("functionId", "fixedinfo");
        a.put("sdkversion", "2.5.8");
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put("appname", com.jd.stat.common.c.a(com.jd.stat.security.c.a));
        jSONObject2.put("jdkey", t.b());
        jSONObject2.put("whwswswws", a());
        jSONObject2.put("body", a);
        jSONObject2.put("appid", com.jd.stat.security.c.g());
        jSONObject2.put("client", "android");
        JSONObject jSONObject3 = new JSONObject();
        jSONObject3.put("content", jSONObject2);
        return jSONObject3;
    }

    @Override // com.jd.stat.security.jma.b.f
    public void c(JSONObject jSONObject) {
        final boolean K = com.jd.stat.security.d.a().K();
        com.jd.stat.network.d dVar = new com.jd.stat.network.d(j.c()) { // from class: com.jd.stat.security.jma.b.e.1
            @Override // com.jd.stat.network.d
            protected String e() {
                try {
                    return "content=" + URLEncoder.encode(e.this.d().getString("content"), "UTF-8");
                } catch (IOException e2) {
                    e2.printStackTrace();
                    return null;
                } catch (JSONException e3) {
                    e3.printStackTrace();
                    return null;
                }
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.jd.stat.network.d
            public Map<String, String> f() {
                HashMap hashMap = new HashMap();
                if (K) {
                    hashMap.put("content-encoding", "gzip");
                }
                return hashMap;
            }
        };
        dVar.a(K);
        dVar.a((Object) ("DeviceFixInfoSender." + System.currentTimeMillis()));
        dVar.h();
    }
}

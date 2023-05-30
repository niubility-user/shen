package com.jd.stat.security.jma.b;

import com.jd.stat.common.t;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes18.dex */
public class d extends a implements f {
    /* JADX INFO: Access modifiers changed from: private */
    public JSONObject d(JSONObject jSONObject) throws JSONException {
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put(JDMtaUtils.LON, jSONObject.optString(JDMtaUtils.LON, "0.0"));
        jSONObject2.put("lat", jSONObject.optString("lat", "0.0"));
        com.jd.stat.common.b.c.a(jSONObject2, com.jd.stat.security.jma.a.e.d(), com.jd.stat.security.jma.a.e.a(c(), b()));
        b(jSONObject2);
        JSONObject a = com.jd.stat.common.b.a.a(com.jd.stat.security.c.a, jSONObject2);
        a.put("functionId", "alterationinfo");
        a.put("sdkversion", "2.5.8");
        JSONObject jSONObject3 = new JSONObject();
        jSONObject3.put("appname", com.jd.stat.common.c.a(com.jd.stat.security.c.a));
        jSONObject3.put("jdkey", t.b());
        jSONObject3.put("appid", com.jd.stat.security.c.g());
        jSONObject3.put("client", "android");
        jSONObject3.put("whwswswws", a());
        jSONObject3.put("body", a);
        JSONObject jSONObject4 = new JSONObject();
        jSONObject4.put("content", jSONObject3);
        return jSONObject4;
    }

    @Override // com.jd.stat.security.jma.b.f
    public void c(final JSONObject jSONObject) {
        final boolean K = com.jd.stat.security.d.a().K();
        com.jd.stat.network.d dVar = new com.jd.stat.network.d(j.c()) { // from class: com.jd.stat.security.jma.b.d.1
            @Override // com.jd.stat.network.d
            protected String e() {
                try {
                    return "content=" + URLEncoder.encode(d.this.d(jSONObject).getString("content"), "UTF-8");
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
        dVar.a((Object) ("DeviceDynamicInfoSender." + System.currentTimeMillis()));
        dVar.h();
    }
}

package com.jd.jdsec.c.h.c;

import android.util.Base64;
import java.net.URLEncoder;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes13.dex */
public class f implements g {

    /* loaded from: classes13.dex */
    class a extends com.jd.jdsec.b.e {
        final /* synthetic */ JSONObject r;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        a(f fVar, String str, JSONObject jSONObject) {
            super(str);
            this.r = jSONObject;
        }

        @Override // com.jd.jdsec.b.e
        protected String b() {
            try {
                JSONObject jSONObject = new JSONObject();
                com.jd.jdsec.a.l.c.a(jSONObject, this.r);
                jSONObject.put("extkey", com.jd.jdsec.c.g.a());
                jSONObject.put("client", "android");
                jSONObject.put("clientversion", com.jd.jdsec.a.c.a(com.jd.jdsec.c.g.a));
                com.jd.jdsec.a.l.b.e("JDSec.Security.EventTrackSender", String.format("event info\u76ee\u524d\u5305\u542b\u4fe1\u606f: %s", jSONObject));
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("event_info", "jidwhzqal" + Base64.encodeToString(jSONObject.toString().getBytes("UTF-8"), 2));
                return "body=" + URLEncoder.encode(jSONObject2.toString(), "UTF-8");
            } catch (JSONException e2) {
                e2.printStackTrace();
                return null;
            } catch (Exception e3) {
                e3.printStackTrace();
                return null;
            }
        }
    }

    /* loaded from: classes13.dex */
    class b extends com.jd.jdsec.b.g {
        b(f fVar) {
        }

        @Override // com.jd.jdsec.b.g
        public void a(com.jd.jdsec.b.a aVar) {
            com.jd.jdsec.a.l.b.b("JDSec.Security.EventTrackSender", "\u8bf7\u6c42\u540e\u7684\u6d88\u606f\u8fd4\u56de\uff0c\u51fa\u9519\u4f53: " + aVar.getLocalizedMessage());
        }

        @Override // com.jd.jdsec.b.g
        public void b(com.jd.jdsec.b.f fVar) {
            if (fVar == null || !fVar.h()) {
                return;
            }
            com.jd.jdsec.a.l.b.e("JDSec.Security.EventTrackSender", "\u8bf7\u6c42\u540e\u7684\u6d88\u606f\u8fd4\u56de\u4f53: " + fVar.a());
        }
    }

    @Override // com.jd.jdsec.c.h.c.g
    public void a(JSONObject jSONObject) {
        a aVar = new a(this, l.d(), jSONObject);
        aVar.g("BusinessEventTrackSender." + System.currentTimeMillis());
        aVar.f(new b(this));
        aVar.w();
    }
}

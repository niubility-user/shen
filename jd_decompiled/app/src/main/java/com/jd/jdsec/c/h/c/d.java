package com.jd.jdsec.c.h.c;

import android.util.Base64;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes13.dex */
public class d implements h {
    private final com.jd.jdsec.c.h.b.a a = new com.jd.jdsec.c.h.b.a();

    /* loaded from: classes13.dex */
    class a extends com.jd.jdsec.b.e {
        final /* synthetic */ boolean r;
        final /* synthetic */ HashMap s;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        a(String str, boolean z, HashMap hashMap) {
            super(str);
            d.this = r1;
            this.r = z;
            this.s = hashMap;
        }

        @Override // com.jd.jdsec.b.e
        protected String b() {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("fix_info", "jidwhzqal" + new String(Base64.encode(d.this.a.c(com.jd.jdsec.c.g.a, this.s).toString().getBytes(), 2)));
                return "body=" + URLEncoder.encode(jSONObject.toString(), "UTF-8");
            } catch (JSONException e2) {
                com.jd.jdsec.a.l.b.b("JDSec.Security.DeviceFixInfoSender", e2.getMessage());
                return null;
            } catch (Exception e3) {
                com.jd.jdsec.a.l.b.b("JDSec.Security.DeviceFixInfoSender", e3.getMessage());
                return null;
            }
        }

        @Override // com.jd.jdsec.b.e
        protected Map<String, String> l() {
            HashMap hashMap = new HashMap();
            if (this.r) {
                hashMap.put("content-encoding", "gzip");
            }
            return hashMap;
        }
    }

    /* loaded from: classes13.dex */
    class b extends com.jd.jdsec.b.g {
        b(d dVar) {
        }

        @Override // com.jd.jdsec.b.g
        public void a(com.jd.jdsec.b.a aVar) {
            com.jd.jdsec.a.l.b.b("JDSec.Security.DeviceFixInfoSender", "\u8bf7\u6c42\u540e\u7684\u6d88\u606f\u8fd4\u56de\uff0c\u51fa\u9519\u4f53: " + aVar.getLocalizedMessage());
        }

        @Override // com.jd.jdsec.b.g
        public void b(com.jd.jdsec.b.f fVar) {
            com.jd.jdsec.a.l.b.e("JDSec.Security.DeviceFixInfoSender", "\u8bf7\u6c42\u540e\u7684\u6d88\u606f\u8fd4\u56de\u4f53: " + fVar.a());
        }
    }

    @Override // com.jd.jdsec.c.h.c.h
    public void a(JSONObject jSONObject, HashMap<String, String> hashMap) {
        boolean a2 = com.jd.jdsec.c.c.c().a();
        a aVar = new a(l.e(), a2, hashMap);
        aVar.i(a2);
        aVar.g("DeviceFixInfoSender." + System.currentTimeMillis());
        aVar.f(new b(this));
        aVar.w();
    }
}

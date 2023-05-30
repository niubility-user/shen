package com.jd.jdsec.c.h.c;

import android.util.Base64;
import com.jdpay.net.http.HTTP;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes13.dex */
public class e implements i {

    /* loaded from: classes13.dex */
    class a extends com.jd.jdsec.b.e {
        final /* synthetic */ boolean r;
        final /* synthetic */ JSONObject s;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        a(e eVar, String str, boolean z, JSONObject jSONObject) {
            super(str);
            this.r = z;
            this.s = jSONObject;
        }

        @Override // com.jd.jdsec.b.e
        protected String b() {
            try {
                com.jd.jdsec.a.l.b.e("JDSec.Security.EdgeComputedSender", "\u7aef\u8ba1\u7b97 send body: ");
                com.jd.jdsec.a.l.e.i(this.s.toString());
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("data", com.jd.jdsec.a.l.e.a() + Base64.encodeToString(this.s.toString().getBytes(), 2));
                return jSONObject.toString();
            } catch (JSONException e2) {
                com.jd.jdsec.a.l.b.b("JDSec.Security.EdgeComputedSender", e2.getMessage());
                return null;
            } catch (Exception e3) {
                com.jd.jdsec.a.l.b.b("JDSec.Security.EdgeComputedSender", e3.getMessage());
                return null;
            }
        }

        @Override // com.jd.jdsec.b.e
        protected Map<String, String> l() {
            HashMap hashMap = new HashMap();
            if (this.r) {
                hashMap.put("content-encoding", "gzip");
            }
            hashMap.put("content-type", HTTP.CONTENT_TYPE_JSON);
            return hashMap;
        }
    }

    /* loaded from: classes13.dex */
    class b extends com.jd.jdsec.b.g {
        b(e eVar) {
        }

        @Override // com.jd.jdsec.b.g
        public void a(com.jd.jdsec.b.a aVar) {
            com.jd.jdsec.a.l.b.b("JDSec.Security.EdgeComputedSender", "\u7aef\u8ba1\u7b97\u8bf7\u6c42\u7ed3\u679c\u5931\u8d25: " + aVar.getLocalizedMessage());
        }

        @Override // com.jd.jdsec.b.g
        public void b(com.jd.jdsec.b.f fVar) {
            if (fVar == null) {
                return;
            }
            com.jd.jdsec.a.l.b.e("JDSec.Security.EdgeComputedSender", "\u7aef\u8ba1\u7b97\u7684\u8bf7\u6c42\u7ed3\u679c\uff1a" + fVar.a());
        }
    }

    @Override // com.jd.jdsec.c.h.c.i
    public void a(JSONObject jSONObject) {
        com.jd.jdsec.a.l.b.e("JDSec.Security.EdgeComputedSender", "sendEdgeComputed invoked");
        boolean a2 = com.jd.jdsec.c.c.c().a();
        a aVar = new a(this, l.c(), a2, jSONObject);
        aVar.i(a2);
        aVar.g("DeviceFixInfoSender." + System.currentTimeMillis());
        aVar.f(new b(this));
        aVar.w();
    }
}

package com.jingdong.manto.m.c1;

import android.text.TextUtils;
import com.huawei.hms.support.hianalytics.HiAnalyticsConstant;
import com.jingdong.common.XView2.common.XView2Constants;
import com.jingdong.manto.jsapi.openmodule.IMantoBaseModule;
import com.jingdong.manto.p.g.c;
import com.jingdong.manto.utils.MantoLog;
import com.jingdong.manto.utils.u;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class d extends com.jingdong.manto.m.c1.a {

    /* loaded from: classes15.dex */
    class a implements c.b {
        final /* synthetic */ long a;
        final /* synthetic */ com.jingdong.manto.h b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ String f13310c;

        a(d dVar, long j2, com.jingdong.manto.h hVar, String str) {
            this.a = j2;
            this.b = hVar;
            this.f13310c = str;
        }

        @Override // com.jingdong.manto.p.g.c.b
        public final void a(String str) {
            Object[] objArr = new Object[2];
            objArr[0] = Long.valueOf(System.currentTimeMillis() - this.a);
            objArr[1] = Integer.valueOf(str == null ? 0 : str.length());
            MantoLog.d("JsApiCreateRequestTask", String.format("onRequestFail, time: %d, data size: %d", objArr));
            com.jingdong.manto.p.g.c a = com.jingdong.manto.p.g.a.b().a(this.b.c());
            if (a != null && a.b(this.f13310c)) {
                MantoLog.d("JsApiCreateRequestTask", String.format("request abort %s", this.f13310c));
            }
            d.b(this.b, this.f13310c, str);
        }

        @Override // com.jingdong.manto.p.g.c.b
        public final void a(String str, Object obj, int i2, JSONObject jSONObject) {
            MantoLog.d("JsApiCreateRequestTask", String.format("onRequestResult, time: %d, data size: %d, code %s,reqrestId %s", Long.valueOf(System.currentTimeMillis() - this.a), Integer.valueOf((obj == null || !(obj instanceof ByteBuffer)) ? (obj == null || !(obj instanceof String)) ? 0 : ((String) obj).length() : ((ByteBuffer) obj).array().length), Integer.valueOf(i2), this.f13310c));
            HashMap hashMap = new HashMap();
            hashMap.put("requestTaskId", this.f13310c);
            if (str.equalsIgnoreCase(IMantoBaseModule.SUCCESS)) {
                hashMap.put(XView2Constants.STATE, "success");
                hashMap.put("data", obj);
            } else {
                hashMap.put(XView2Constants.STATE, "fail");
            }
            hashMap.put(HiAnalyticsConstant.HaKey.BI_KEY_RESULT, String.valueOf(i2));
            if (jSONObject != null) {
                hashMap.put("header", jSONObject);
            }
            b bVar = new b();
            if ((obj == null || !(obj instanceof String)) && !u.a(this.b, hashMap, bVar)) {
                return;
            }
            String jSONObject2 = new JSONObject(hashMap).toString();
            com.jingdong.manto.m.d a = bVar.a(this.b);
            a.f13315c = jSONObject2;
            a.a();
        }
    }

    /* loaded from: classes15.dex */
    public static class b extends com.jingdong.manto.m.d {
        @Override // com.jingdong.manto.m.a
        public String getJsApiName() {
            return "onRequestTaskStateChange";
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(com.jingdong.manto.h hVar, String str, String str2) {
        HashMap hashMap = new HashMap();
        hashMap.put("requestTaskId", str);
        hashMap.put(XView2Constants.STATE, "fail");
        hashMap.put("errMsg", str2);
        if (TextUtils.equals("request timeout", str2)) {
            hashMap.put(HiAnalyticsConstant.HaKey.BI_KEY_RESULT, "-1001");
        }
        String jSONObject = new JSONObject(hashMap).toString();
        com.jingdong.manto.m.d a2 = new b().a(hVar);
        a2.f13315c = jSONObject;
        a2.a();
    }

    @Override // com.jingdong.manto.m.c1.a
    public final void a(com.jingdong.manto.h hVar, JSONObject jSONObject, String str) {
        com.jingdong.manto.p.g.c a2;
        ArrayList<String> arrayList;
        String str2;
        MantoLog.w("JsApiCreateRequestTask", String.format("exec: %s", jSONObject.toString()));
        long currentTimeMillis = System.currentTimeMillis();
        String optString = jSONObject.optString("url");
        if (!com.jingdong.manto.p.c.d(optString)) {
            b(hVar, str, "url invalid, url:" + optString);
            return;
        }
        com.jingdong.manto.i.e eVar = hVar.h().s;
        int a3 = com.jingdong.manto.p.c.a(eVar, hVar, com.jingdong.manto.p.c.REQUEST);
        int i2 = a3 <= 0 ? 15000 : a3;
        boolean a4 = com.jingdong.manto.p.c.a(eVar, jSONObject.optBoolean("__skipDomainCheck__", false));
        if (!a4 || com.jingdong.manto.p.c.a(eVar.f13101k, optString)) {
            if (com.jingdong.manto.p.g.a.b().a(hVar.c()) == null) {
                a2 = new com.jingdong.manto.p.g.c(hVar.c(), optString, hVar.h().s);
                com.jingdong.manto.p.g.a.b().a.put(hVar.c(), a2);
            } else {
                a2 = com.jingdong.manto.p.g.a.b().a(hVar.c());
            }
            com.jingdong.manto.p.g.c cVar = a2;
            Map<String, String> a5 = com.jingdong.manto.p.c.a(jSONObject, eVar);
            a aVar = new a(this, currentTimeMillis, hVar, str);
            if (cVar != null) {
                if (a4) {
                    arrayList = eVar.f13101k;
                } else {
                    MantoLog.i("JsApiCreateRequestTask", "debug type, do not verify domains");
                    arrayList = null;
                }
                cVar.a(hVar, this, i2, jSONObject, a5, arrayList, aVar, str, "createRequestTask");
                return;
            }
            str2 = "create request error";
        } else {
            MantoLog.i("JsApiCreateRequestTask", String.format("not in domain url %s", optString));
            str2 = "url not in domain list";
        }
        b(hVar, str, str2);
    }

    @Override // com.jingdong.manto.m.c1.a
    protected final String b() {
        return "requestTaskId";
    }

    @Override // com.jingdong.manto.m.c1.a
    protected final String c() {
        return String.valueOf(com.jingdong.manto.p.g.a.a());
    }

    @Override // com.jingdong.manto.m.a
    public String getJsApiName() {
        return "createRequestTask";
    }
}

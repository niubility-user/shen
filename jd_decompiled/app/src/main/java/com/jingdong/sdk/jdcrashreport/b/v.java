package com.jingdong.sdk.jdcrashreport.b;

import com.google.common.net.HttpHeaders;
import com.jd.libs.hybrid.HybridSDK;
import com.jingdong.jdsdk.config.Configuration;
import com.jingdong.sdk.jdcrashreport.b.m;
import com.tencent.mapsdk.internal.l4;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: classes7.dex */
public class v {

    /* loaded from: classes7.dex */
    public interface b {
        void a(String str);

        void a(JSONObject jSONObject);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes7.dex */
    public static class c implements Runnable {

        /* renamed from: g  reason: collision with root package name */
        private volatile m f14888g;

        /* renamed from: h  reason: collision with root package name */
        private b f14889h;

        private Map<String, String> b() {
            HashMap hashMap = new HashMap();
            hashMap.put("connection", "close");
            hashMap.put(HttpHeaders.ACCEPT_ENCODING, "gzip,deflate");
            hashMap.put(HttpHeaders.CONTENT_TYPE, "application/x-www-form-urlencoded;charset=UTF-8");
            com.jingdong.sdk.jdcrashreport.c.e(hashMap);
            return hashMap;
        }

        private JSONObject c() {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("appId", com.jingdong.sdk.jdcrashreport.d.N());
                jSONObject.put("appArch", n.b(com.jingdong.sdk.jdcrashreport.d.I()));
                jSONObject.put("appTheme", com.jingdong.sdk.jdcrashreport.d.U());
                return jSONObject;
            } catch (Exception unused) {
                return new JSONObject();
            }
        }

        private Map<String, String> d() {
            HashMap hashMap = new HashMap();
            hashMap.put("networkType", n.k());
            hashMap.put(HybridSDK.APP_VERSION, com.jingdong.sdk.jdcrashreport.d.L());
            hashMap.put(HybridSDK.APP_VERSION_CODE, String.valueOf(com.jingdong.sdk.jdcrashreport.d.M()));
            hashMap.put("client", "android");
            hashMap.put(HybridSDK.D_BRAND, n.c(false));
            hashMap.put(HybridSDK.D_MODEL, n.e(false));
            hashMap.put(HybridSDK.OS_VERSION, n.d());
            hashMap.put("screen", n.f());
            hashMap.put(Configuration.PARTNER, com.jingdong.sdk.jdcrashreport.d.K());
            hashMap.put(l4.f16791e, "E1.1");
            String O = com.jingdong.sdk.jdcrashreport.d.O();
            r.b("JDCrashReport", "deviceId: " + O);
            if (O != null && O.trim().length() > 0) {
                hashMap.put("uuid", O);
            } else {
                hashMap.put("uuid", n.a());
            }
            return hashMap;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (com.jingdong.sdk.jdcrashreport.d.g()) {
                r.b("JDCrashReport.Strategy", "downgrade is enabled, not pull config");
                return;
            }
            try {
                try {
                    JSONObject jSONObject = new JSONObject(this.f14888g.a());
                    String optString = jSONObject.optString("code");
                    String optString2 = jSONObject.optString("message");
                    r.b("JDCrashReport.Strategy", "configPull code: " + optString + ", msg: " + optString2);
                    if ("0".equals(optString)) {
                        b bVar = this.f14889h;
                        if (bVar != null) {
                            bVar.a(jSONObject.optJSONObject("data"));
                        }
                    } else {
                        b bVar2 = this.f14889h;
                        if (bVar2 != null) {
                            bVar2.a(optString2);
                        }
                    }
                } catch (Exception e2) {
                    r.c("JDCrashReport.Strategy", "configPull failed: " + e2.getMessage(), e2);
                    b bVar3 = this.f14889h;
                    if (bVar3 != null) {
                        bVar3.a("configPull failed: " + e2.getMessage());
                    }
                }
            } finally {
                this.f14888g.c();
                this.f14888g = null;
            }
        }

        private c(b bVar) {
            this.f14889h = bVar;
            m.b bVar2 = new m.b();
            bVar2.c(com.jingdong.sdk.jdcrashreport.c.b("configPull"));
            bVar2.j("configPull");
            bVar2.b(m.c.POST);
            bVar2.a(15000);
            bVar2.i(10000);
            bVar2.k(b());
            bVar2.d(d());
            bVar2.e(c());
            this.f14888g = bVar2.f();
        }
    }

    public static synchronized void a(b bVar) {
        synchronized (v.class) {
            try {
                d.c(new c(bVar));
            } catch (Exception e2) {
                r.c("JDCrashReport.Strategy", "Pull config failed", e2);
            }
        }
    }
}

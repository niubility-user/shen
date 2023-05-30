package com.jingdong.sdk.jdcrashreport.b;

import com.google.common.net.HttpHeaders;
import com.jd.libs.hybrid.HybridSDK;
import com.jingdong.jdsdk.config.Configuration;
import com.jingdong.sdk.jdcrashreport.b.m;
import com.tencent.mapsdk.internal.l4;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes7.dex */
public class y {
    private static ScheduledFuture<?> a;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes7.dex */
    public static class b implements Runnable {

        /* renamed from: g  reason: collision with root package name */
        private volatile m f14898g;

        private Map<String, String> b() {
            HashMap hashMap = new HashMap();
            hashMap.put("connection", "close");
            hashMap.put(HttpHeaders.ACCEPT_ENCODING, "gzip,deflate");
            hashMap.put(HttpHeaders.CONTENT_TYPE, "application/x-www-form-urlencoded;charset=UTF-8");
            com.jingdong.sdk.jdcrashreport.c.e(hashMap);
            return hashMap;
        }

        private Map<String, String> c() {
            HashMap hashMap = new HashMap();
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

        private JSONObject d() {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("appId", com.jingdong.sdk.jdcrashreport.d.N());
                jSONObject.put(HybridSDK.APP_VERSION, com.jingdong.sdk.jdcrashreport.d.L());
                jSONObject.put("buildCode", String.valueOf(com.jingdong.sdk.jdcrashreport.d.M()));
                jSONObject.put("appArch", n.b(com.jingdong.sdk.jdcrashreport.d.I()));
                jSONObject.put("appTheme", com.jingdong.sdk.jdcrashreport.d.U());
                JSONArray jSONArray = new JSONArray();
                jSONArray.put(jSONObject);
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("msg", jSONArray);
                return jSONObject2;
            } catch (Exception unused) {
                return new JSONObject();
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            if (com.jingdong.sdk.jdcrashreport.d.g()) {
                r.b("JDCrashReport.UVTask", "downgrade is enabled, not report uv");
                return;
            }
            try {
                try {
                    m.b bVar = new m.b();
                    bVar.c(com.jingdong.sdk.jdcrashreport.c.b("uvReport"));
                    bVar.j("uvReport");
                    bVar.b(m.c.POST);
                    bVar.a(15000);
                    bVar.i(10000);
                    bVar.k(b());
                    bVar.d(c());
                    bVar.e(d());
                    this.f14898g = bVar.f();
                    JSONObject jSONObject = new JSONObject(this.f14898g.a());
                    r.b("JDCrashReport.UVTask", "uvReport code: " + jSONObject.optString("code") + " msg: " + jSONObject.optString("message") + " Time: " + x.c(System.currentTimeMillis()));
                } catch (Exception e2) {
                    r.c("JDCrashReport.UVTask", "uvReport failed: " + e2.getMessage(), e2);
                }
            } finally {
                this.f14898g.c();
                this.f14898g = null;
            }
        }

        private b() {
        }
    }

    public static synchronized void a() {
        synchronized (y.class) {
            d.c(new b());
        }
    }

    public static synchronized void b(long j2) {
        synchronized (y.class) {
            try {
                c();
                r.b("JDCrashReport.UV", "set uvReport rate: " + j2 + "ms");
                a = d.b(new b(), j2, j2);
            } catch (Exception e2) {
                r.c("JDCrashReport.UV", "uvReport failed", e2);
            }
        }
    }

    public static synchronized void c() {
        synchronized (y.class) {
            if (a != null) {
                r.b("JDCrashReport.UV", "cancel uvReport");
                a.cancel(false);
                a = null;
            }
        }
    }
}

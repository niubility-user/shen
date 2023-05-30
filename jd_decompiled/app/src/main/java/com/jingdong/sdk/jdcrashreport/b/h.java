package com.jingdong.sdk.jdcrashreport.b;

import com.google.common.net.HttpHeaders;
import com.jd.libs.hybrid.HybridSDK;
import com.jingdong.jdsdk.config.Configuration;
import com.jingdong.sdk.jdcrashreport.JDCrashReportListener;
import com.jingdong.sdk.jdcrashreport.b.m;
import com.jingdong.sdk.jdcrashreport.common.CrashInfo;
import com.tencent.mapsdk.internal.l4;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes7.dex */
public class h implements Runnable {

    /* renamed from: g  reason: collision with root package name */
    private volatile CrashInfo f14861g;

    /* renamed from: h  reason: collision with root package name */
    private volatile List<CrashInfo> f14862h;

    /* renamed from: i  reason: collision with root package name */
    private volatile JDCrashReportListener f14863i;

    /* renamed from: j  reason: collision with root package name */
    private volatile m f14864j;

    public h(CrashInfo crashInfo, JDCrashReportListener jDCrashReportListener) {
        this.f14861g = crashInfo;
        this.f14863i = jDCrashReportListener;
        m.b bVar = new m.b();
        bVar.c(com.jingdong.sdk.jdcrashreport.c.b("crashReport"));
        bVar.j("crashReport");
        bVar.b(m.c.POST);
        bVar.a(15000);
        bVar.i(10000);
        bVar.k(b());
        bVar.d(d());
        bVar.e(c());
        this.f14864j = bVar.f();
    }

    private Map<String, String> b() {
        HashMap hashMap = new HashMap();
        hashMap.put("connection", "close");
        hashMap.put(HttpHeaders.ACCEPT_ENCODING, "gzip,deflate");
        hashMap.put(HttpHeaders.CONTENT_TYPE, "application/x-www-form-urlencoded;charset=UTF-8");
        com.jingdong.sdk.jdcrashreport.c.e(hashMap);
        return hashMap;
    }

    private JSONObject c() {
        if (this.f14861g != null) {
            try {
                JSONArray jSONArray = new JSONArray();
                jSONArray.put(this.f14861g.toUploadJsonObject());
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("msg", jSONArray);
                return jSONObject;
            } catch (Exception e2) {
                r.c("JDCrashReport", "setupBody failed", e2);
            }
        } else if (this.f14862h != null) {
            try {
                JSONArray jSONArray2 = new JSONArray();
                Iterator<CrashInfo> it = this.f14862h.iterator();
                while (it.hasNext()) {
                    jSONArray2.put(it.next().toUploadJsonObject());
                }
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("msg", jSONArray2);
                return jSONObject2;
            } catch (Exception e3) {
                r.c("JDCrashReport", "setupBody failed", e3);
            }
        }
        r.h("JDCrashReport", "setupBody: {}");
        return new JSONObject();
    }

    private Map<String, String> d() {
        HashMap hashMap = new HashMap();
        if (this.f14861g != null) {
            boolean z = "java".equals(this.f14861g.busiType) || "native".equals(this.f14861g.busiType);
            hashMap.put("networkType", this.f14861g.feedback == null ? "unknown" : this.f14861g.feedback.get("networkType"));
            hashMap.put(HybridSDK.APP_VERSION, this.f14861g.clientVersion == null ? "unknown" : this.f14861g.clientVersion);
            hashMap.put(HybridSDK.APP_VERSION_CODE, this.f14861g.buildCode == null ? "-1" : this.f14861g.buildCode);
            hashMap.put("client", "android");
            hashMap.put(HybridSDK.D_BRAND, n.c(z));
            hashMap.put(HybridSDK.D_MODEL, n.e(z));
            hashMap.put(HybridSDK.OS_VERSION, this.f14861g.feedback != null ? this.f14861g.feedback.get(HybridSDK.OS_VERSION) : "unknown");
            hashMap.put("screen", n.f());
            hashMap.put(Configuration.PARTNER, this.f14861g.partner);
            hashMap.put(l4.f16791e, "E1.1");
            hashMap.put("uts", this.f14861g.uts);
            String O = com.jingdong.sdk.jdcrashreport.d.O();
            r.b("JDCrashReport", "deviceId: " + O);
            if (O != null && O.trim().length() > 0) {
                hashMap.put("uuid", O);
            } else {
                hashMap.put("uuid", n.a());
            }
        } else if (this.f14862h != null) {
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
            hashMap.put("uts", com.jingdong.sdk.jdcrashreport.d.Q());
            String O2 = com.jingdong.sdk.jdcrashreport.d.O();
            r.b("JDCrashReport", "deviceId: " + O2);
            if (O2 != null && O2.trim().length() > 0) {
                hashMap.put("uuid", O2);
            } else {
                hashMap.put("uuid", n.a());
            }
        }
        return hashMap;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            try {
                JSONObject jSONObject = new JSONObject(this.f14864j.a());
                String str = "";
                String str2 = "no message";
                try {
                    str = jSONObject.optString("code");
                    str2 = jSONObject.optString("message");
                } catch (Throwable unused) {
                }
                r.b("JDCrashReport", "CrashReporterTask ----> code: " + str + " msg: " + str2 + " Time: " + x.c(System.currentTimeMillis()));
                if ("0".equals(str)) {
                    if (this.f14863i != null) {
                        this.f14863i.onEnd(0, str2, this.f14861g);
                    }
                } else if (this.f14863i != null) {
                    this.f14863i.onError(-1, str2, this.f14861g);
                }
            } catch (Exception e2) {
                r.c("JDCrashReport", "CrashReporterTask run failed: " + e2.getMessage(), e2);
                if (this.f14863i != null) {
                    this.f14863i.onError(-1, e2.getMessage(), this.f14861g);
                }
            }
        } finally {
            this.f14864j.c();
        }
    }

    public h(List<CrashInfo> list) {
        this.f14862h = list;
        m.b bVar = new m.b();
        bVar.c(com.jingdong.sdk.jdcrashreport.c.b("crashReport"));
        bVar.j("crashReport");
        bVar.b(m.c.POST);
        bVar.a(15000);
        bVar.i(10000);
        bVar.k(b());
        bVar.d(d());
        bVar.e(c());
        this.f14864j = bVar.f();
    }
}

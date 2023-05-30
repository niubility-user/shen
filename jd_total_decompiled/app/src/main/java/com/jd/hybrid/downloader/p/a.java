package com.jd.hybrid.downloader.p;

import com.jd.libs.hybrid.base.HybridSettings;
import com.jd.libs.hybrid.base.util.ExceptionUtils;
import com.jd.libs.hybrid.base.util.Log;
import com.jd.libs.hybrid.base.util.MtaUtils;
import com.jd.libs.hybrid.base.util.PerfMtaUtils;
import com.jingdong.common.web.managers.PerformanceManager;
import com.jingdong.common.web.managers.WebPerfManager;
import java.util.HashMap;
import jpbury.t;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes13.dex */
public class a {

    /* renamed from: com.jd.hybrid.downloader.p.a$a  reason: collision with other inner class name */
    /* loaded from: classes13.dex */
    public static class C0087a {
        public String a;
        public float b;
        public int d;

        /* renamed from: e  reason: collision with root package name */
        public boolean f2700e;

        /* renamed from: g  reason: collision with root package name */
        public int f2702g;

        /* renamed from: h  reason: collision with root package name */
        public String f2703h;

        /* renamed from: i  reason: collision with root package name */
        public long f2704i;

        /* renamed from: j  reason: collision with root package name */
        public int f2705j;

        /* renamed from: k  reason: collision with root package name */
        public int f2706k;

        /* renamed from: l  reason: collision with root package name */
        public String f2707l;

        /* renamed from: c  reason: collision with root package name */
        public String f2699c = "0";

        /* renamed from: f  reason: collision with root package name */
        public String f2701f = "0";
    }

    /* loaded from: classes13.dex */
    public static class b {
        public String a;
        public String b;

        /* renamed from: c  reason: collision with root package name */
        public String f2708c;
    }

    /* loaded from: classes13.dex */
    public static class c {
        public String a;
        public String b;

        /* renamed from: c  reason: collision with root package name */
        public String f2709c;
    }

    public static void a(b bVar) {
        HashMap hashMap = new HashMap();
        hashMap.put(t.f20145j, bVar.a);
        hashMap.put("errCode", bVar.b);
        hashMap.put("reserved1", bVar.f2708c);
        double currentTimeMillis = System.currentTimeMillis();
        Double.isNaN(currentTimeMillis);
        hashMap.put("occurTime", String.format("%.6f", Double.valueOf(currentTimeMillis / 1000.0d)));
        hashMap.put(PerformanceManager.ERR_TYPE, "2");
        ExceptionUtils.report(hashMap);
    }

    public static void b(C0087a c0087a) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(WebPerfManager.HYBRID_ID, c0087a.a);
            jSONObject.put("appsize", String.valueOf(c0087a.b));
            jSONObject.put("downloadstatus", c0087a.f2699c);
            jSONObject.put("code", String.valueOf(c0087a.d));
            jSONObject.put("splitpack", c0087a.f2700e ? "1" : "0");
            jSONObject.put("unpackstatus", c0087a.f2701f);
            jSONObject.put("type", String.valueOf(c0087a.f2702g));
            jSONObject.put("url", c0087a.f2703h);
            jSONObject.put(WebPerfManager.HYBRID_CONFIG_VERSION, String.valueOf(c0087a.f2705j));
            jSONObject.put(WebPerfManager.HYBRID_FILE_VERSION, String.valueOf(c0087a.f2706k));
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("loadtime", String.valueOf(c0087a.f2704i));
            jSONObject.put("performance", jSONObject2);
            String str = c0087a.f2707l;
            if (str != null) {
                jSONObject.put("extra", str);
            }
            MtaUtils.sendExposureData(HybridSettings.getAppContext(), "hybrid_download", "", "", "", "", jSONObject.toString(), null);
            HashMap hashMap = new HashMap();
            hashMap.put(WebPerfManager.HYBRID_ID, c0087a.a);
            hashMap.put("appsize", String.valueOf(c0087a.b));
            hashMap.put("downloadstatus", c0087a.f2699c);
            hashMap.put("code", String.valueOf(c0087a.d));
            hashMap.put("splitpack", c0087a.f2700e ? "1" : "0");
            hashMap.put("unpackstatus", c0087a.f2701f);
            hashMap.put("type", String.valueOf(c0087a.f2702g));
            hashMap.put("url", c0087a.f2703h);
            hashMap.put(WebPerfManager.HYBRID_CONFIG_VERSION, String.valueOf(c0087a.f2705j));
            hashMap.put(WebPerfManager.HYBRID_FILE_VERSION, String.valueOf(c0087a.f2706k));
            hashMap.put("performance", jSONObject2.toString());
            String str2 = c0087a.f2707l;
            if (str2 != null) {
                hashMap.put("extra", str2);
            }
            PerfMtaUtils.onDownloaded(hashMap);
        } catch (JSONException e2) {
            Log.e("OfflineMtaUtils", e2);
        }
    }

    public static void c(c cVar) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("nameSpace", cVar.a);
            jSONObject.put("fileId", cVar.b);
            jSONObject.put("result", cVar.f2709c);
            MtaUtils.sendExposureData(HybridSettings.getAppContext(), "xcache_get_resource", "", "", "", "", jSONObject.toString(), null);
        } catch (JSONException e2) {
            Log.e("OfflineMtaUtils", e2);
        }
    }
}

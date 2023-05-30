package com.jingdong.app.mall.performance;

import android.text.TextUtils;
import com.jingdong.jdsdk.utils.PackageInfoUtil;
import java.util.HashMap;

/* loaded from: classes.dex */
public final class a {
    public String a;
    public String b;

    /* renamed from: c  reason: collision with root package name */
    public String f11512c;
    public String d;

    /* renamed from: e  reason: collision with root package name */
    public String f11513e;

    /* renamed from: f  reason: collision with root package name */
    public String f11514f;

    /* renamed from: g  reason: collision with root package name */
    public String f11515g;

    /* renamed from: h  reason: collision with root package name */
    public String f11516h;

    /* renamed from: i  reason: collision with root package name */
    public String f11517i;

    /* renamed from: j  reason: collision with root package name */
    public String f11518j;

    /* renamed from: k  reason: collision with root package name */
    public String f11519k;

    /* renamed from: l  reason: collision with root package name */
    public String f11520l;

    /* renamed from: m  reason: collision with root package name */
    public String f11521m;

    /* renamed from: n  reason: collision with root package name */
    public String f11522n;
    public String p;
    public boolean o = false;
    public String q = "0";

    private static String a() {
        StringBuilder sb = new StringBuilder();
        sb.append("");
        double currentTimeMillis = System.currentTimeMillis();
        Double.isNaN(currentTimeMillis);
        sb.append(String.format("%.6f", Double.valueOf((currentTimeMillis + 0.0d) / 1000.0d)));
        return sb.toString();
    }

    public static a b(HashMap<String, Object> hashMap) {
        if (hashMap == null || hashMap.isEmpty()) {
            return null;
        }
        a aVar = new a();
        aVar.a = c(hashMap, "moduleName");
        aVar.b = c(hashMap, "moduleVersion");
        aVar.f11512c = c(hashMap, "rnVersion");
        aVar.d = c(hashMap, "memBefore");
        aVar.f11513e = c(hashMap, "memAfter");
        aVar.f11514f = c(hashMap, "startTime");
        aVar.f11515g = c(hashMap, "preLoadEnd");
        aVar.f11516h = c(hashMap, "jsLoadEnd");
        aVar.f11517i = c(hashMap, "mountEnd");
        aVar.f11518j = c(hashMap, "requestsInfo");
        aVar.f11519k = c(hashMap, "updateEnd");
        aVar.f11520l = c(hashMap, "extdata");
        aVar.f11521m = c(hashMap, "rtype");
        aVar.f11522n = c(hashMap, "sessionId");
        aVar.o = "1".equals(c(hashMap, "isRN"));
        aVar.p = c(hashMap, "errMsg");
        aVar.q = c(hashMap, "errCode");
        return aVar;
    }

    private static String c(HashMap<String, Object> hashMap, String str) {
        Object obj;
        String o = b.m().o();
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(o)) {
            return "";
        }
        String q = b.m().q(o, str);
        return ((TextUtils.isEmpty(q) || "rtype".equals(str)) && hashMap != null && hashMap.containsKey(str) && (obj = hashMap.get(str)) != null) ? obj.toString() : q;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public HashMap<String, String> d() {
        HashMap<String, String> hashMap = new HashMap<>(18);
        hashMap.put("moduleName", this.a);
        hashMap.put("moduleVersion", TextUtils.isEmpty(this.b) ? PackageInfoUtil.getVersionName() : this.b);
        hashMap.put("RNVersion", this.f11512c);
        hashMap.put("memBefore", this.d);
        hashMap.put("memAfter", "auto".equals(this.f11521m) ? this.f11513e : b.m().n());
        hashMap.put("startTime", this.f11514f);
        hashMap.put("preLoadEnd", this.f11515g);
        hashMap.put("jsLoadEnd", this.f11516h);
        hashMap.put("mountEnd", this.f11517i);
        hashMap.put("requestsInfo", this.f11518j);
        hashMap.put("updateEnd", this.f11519k);
        hashMap.put("extdata", this.f11520l);
        hashMap.put("rtype", this.f11521m);
        hashMap.put("sessionId", this.f11522n);
        hashMap.put("isRN", this.o ? "1" : "0");
        hashMap.put("errMsg", this.p);
        hashMap.put("errCode", this.q);
        hashMap.put("occurTime", a());
        return hashMap;
    }

    public String toString() {
        return "ChannelPerformanceInfo{moduleName='" + this.a + "', moduleVersion='" + this.b + "', rnVersion='" + this.f11512c + "', memBefore='" + this.d + "', memAfter='" + this.f11513e + "', startTime='" + this.f11514f + "', preLoadEnd='" + this.f11515g + "', jsLoadEnd='" + this.f11516h + "', mountEnd='" + this.f11517i + "', requestsInfo='" + this.f11518j + "', updateEnd='" + this.f11519k + "', extData='" + this.f11520l + "', rtype='" + this.f11521m + "', sessionId='" + this.f11522n + "', isRn=" + this.o + ", errMsg='" + this.p + "', errCode='" + this.q + "'}";
    }
}

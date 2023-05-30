package com.jingdong.sdk.dialingtest.e.a;

import android.text.TextUtils;
import com.huawei.hms.framework.common.hianalytics.WiseOpenHianalyticsData;
import com.huawei.hms.support.hianalytics.HiAnalyticsConstant;
import com.jingdong.jdsdk.constant.JshopConst;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import jpbury.t;
import org.json.JSONObject;

/* loaded from: classes7.dex */
public class c {
    private static a a;
    private static b b;

    private static String a() {
        StringBuilder sb = new StringBuilder();
        sb.append("");
        Locale locale = Locale.ENGLISH;
        double currentTimeMillis = System.currentTimeMillis();
        Double.isNaN(currentTimeMillis);
        sb.append(String.format(locale, "%.6f", Double.valueOf((currentTimeMillis + 0.0d) / 1000.0d)));
        return sb.toString();
    }

    private static String b(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return "";
        }
        a aVar = a;
        if (aVar == null) {
            com.jingdong.sdk.dialingtest.c.e.a.a("DialingReporter", " reporter not init");
            return "";
        } else if (!aVar.c(com.jingdong.sdk.dialingtest.b.k().j(), str, str2)) {
            com.jingdong.sdk.dialingtest.c.e.a.a("DialingReporter", "not need report");
            return "";
        } else {
            String a2 = a.a(com.jingdong.sdk.dialingtest.b.k().j(), str, str2);
            if (TextUtils.isEmpty(a2)) {
                return a2;
            }
            try {
                return new String(d.e(d.c(a2)));
            } catch (Exception e2) {
                e2.printStackTrace();
                return a2;
            }
        }
    }

    public static void c(com.jingdong.sdk.dialingtest.d.a.a aVar) {
        if (aVar == null) {
            return;
        }
        HashMap hashMap = new HashMap(10);
        hashMap.put("typeId", "9");
        if (aVar.v) {
            hashMap.put("chId", "5");
        } else {
            hashMap.put("chId", "3");
        }
        hashMap.put("occurTime", a());
        if (!TextUtils.isEmpty(aVar.b)) {
            hashMap.put("clientIP", aVar.b);
        }
        if (!TextUtils.isEmpty(aVar.f14750c)) {
            hashMap.put("host", aVar.f14750c);
        }
        if (!TextUtils.isEmpty(aVar.d)) {
            hashMap.put("hostIP", aVar.d);
        }
        if (!TextUtils.isEmpty(aVar.f14751e)) {
            hashMap.put("nameLookup", aVar.f14751e);
        }
        if (!TextUtils.isEmpty(aVar.f14753g)) {
            hashMap.put("ldnsIP", aVar.f14753g);
        }
        if (!TextUtils.isEmpty(aVar.f14754h)) {
            hashMap.put("opldnsIP", aVar.f14754h);
        }
        if (!TextUtils.isEmpty(aVar.f14752f)) {
            hashMap.put(JshopConst.JSHOP_PROMOTIO_URL, aVar.f14752f);
        }
        if (!TextUtils.isEmpty(aVar.f14757k)) {
            hashMap.put("errMsg", aVar.f14757k);
        }
        if (!TextUtils.isEmpty(aVar.f14755i)) {
            hashMap.put("errCode", aVar.f14755i);
        }
        if (!TextUtils.isEmpty(aVar.f14756j)) {
            hashMap.put(t.f20145j, aVar.f14756j);
        }
        if (!TextUtils.isEmpty(aVar.f14758l)) {
            hashMap.put(HiAnalyticsConstant.HaKey.BI_KEY_RESULT, aVar.f14758l);
        }
        if (!TextUtils.isEmpty(aVar.f14759m)) {
            hashMap.put("respHead", aVar.f14759m);
        }
        if (!TextUtils.isEmpty(aVar.f14760n)) {
            hashMap.put("respBody", aVar.f14760n);
        }
        if (!TextUtils.isEmpty(aVar.o)) {
            hashMap.put(WiseOpenHianalyticsData.UNION_COSTTIME, aVar.o);
        }
        if (!TextUtils.isEmpty(aVar.p)) {
            hashMap.put("certificateInfo", aVar.p);
        }
        if (!TextUtils.isEmpty(aVar.a)) {
            hashMap.put("sessionId", aVar.a);
        }
        if (!TextUtils.isEmpty(aVar.q)) {
            hashMap.put("httprtt", aVar.q);
        }
        if (!TextUtils.isEmpty(aVar.r)) {
            hashMap.put("tcprtt", aVar.r);
        }
        if (!TextUtils.isEmpty(aVar.s)) {
            hashMap.put("throughput", aVar.s);
        }
        if (!TextUtils.isEmpty(aVar.t)) {
            hashMap.put("signal", aVar.t);
        }
        if (aVar.v) {
            if (!TextUtils.isEmpty(aVar.u)) {
                hashMap.put("diagId", aVar.u);
            }
            h(hashMap);
        }
        com.jingdong.sdk.dialingtest.c.e.a.a("DialingReporter", "http test report data: " + hashMap.toString());
        j(hashMap);
    }

    public static void d(com.jingdong.sdk.dialingtest.d.b.a aVar) {
        if (aVar == null) {
            return;
        }
        HashMap hashMap = new HashMap(10);
        hashMap.put("typeId", "9");
        if (aVar.r) {
            hashMap.put("chId", "4");
        } else {
            hashMap.put("chId", "1");
        }
        hashMap.put("occurTime", a());
        if (!TextUtils.isEmpty(aVar.b)) {
            hashMap.put("clientIP", aVar.b);
        }
        if (!TextUtils.isEmpty(aVar.f14772c)) {
            hashMap.put("host", aVar.f14772c);
        }
        if (!TextUtils.isEmpty(aVar.d)) {
            hashMap.put("hostIP", aVar.d);
        }
        if (!TextUtils.isEmpty(aVar.f14773e)) {
            hashMap.put("nameLookup", aVar.f14773e);
        }
        if (!TextUtils.isEmpty(aVar.f14774f)) {
            hashMap.put("ldnsIP", aVar.f14774f);
        }
        if (!TextUtils.isEmpty(aVar.f14775g)) {
            hashMap.put("opldnsIP", aVar.f14775g);
        }
        if (!TextUtils.isEmpty(aVar.f14776h)) {
            hashMap.put("errCode", aVar.f14776h);
        }
        if (!TextUtils.isEmpty(aVar.f14777i)) {
            hashMap.put("errMsg", aVar.f14777i);
        }
        if (!TextUtils.isEmpty(aVar.f14778j)) {
            hashMap.put(t.f20145j, aVar.f14778j);
        }
        if (!TextUtils.isEmpty(aVar.f14779k)) {
            hashMap.put("pingResult", aVar.f14779k);
        }
        if (!TextUtils.isEmpty(aVar.f14780l)) {
            hashMap.put("pingMin", aVar.f14780l);
        }
        if (!TextUtils.isEmpty(aVar.f14781m)) {
            hashMap.put("pingMax", aVar.f14781m);
        }
        if (!TextUtils.isEmpty(aVar.f14782n)) {
            hashMap.put("pingAvg", aVar.f14782n);
        }
        if (!TextUtils.isEmpty(aVar.o)) {
            hashMap.put("pktLoss", aVar.o);
        }
        if (!TextUtils.isEmpty(aVar.a)) {
            hashMap.put("sessionId", aVar.a);
        }
        if (aVar.r) {
            if (!TextUtils.isEmpty(aVar.p)) {
                hashMap.put("diagId", aVar.p);
            }
            h(hashMap);
        }
        com.jingdong.sdk.dialingtest.c.e.a.c("DialingReporter", "ping test report data: " + hashMap.toString());
        j(hashMap);
    }

    public static void e(com.jingdong.sdk.dialingtest.d.c.a aVar) {
        if (aVar == null) {
            return;
        }
        HashMap hashMap = new HashMap(10);
        hashMap.put("typeId", "9");
        hashMap.put("chId", "2");
        hashMap.put("occurTime", a());
        if (!TextUtils.isEmpty(aVar.b)) {
            hashMap.put("clientIP", aVar.b);
        }
        if (!TextUtils.isEmpty(aVar.f14791c)) {
            hashMap.put("host", aVar.f14791c);
        }
        if (!TextUtils.isEmpty(aVar.d)) {
            hashMap.put("hostIP", aVar.d);
        }
        if (!TextUtils.isEmpty(aVar.f14792e)) {
            hashMap.put("nameLookup", aVar.f14792e);
        }
        if (!TextUtils.isEmpty(aVar.f14793f)) {
            hashMap.put("ldnsIP", aVar.f14793f);
        }
        if (!TextUtils.isEmpty(aVar.f14794g)) {
            hashMap.put("opldnsIP", aVar.f14794g);
        }
        if (!TextUtils.isEmpty(aVar.f14795h)) {
            hashMap.put("errCode", aVar.f14795h);
        }
        if (!TextUtils.isEmpty(aVar.f14796i)) {
            hashMap.put("errMsg", aVar.f14796i);
        }
        if (!TextUtils.isEmpty(aVar.f14797j)) {
            hashMap.put(t.f20145j, aVar.f14797j);
        }
        if (!TextUtils.isEmpty(aVar.f14798k)) {
            hashMap.put("traceResult", aVar.f14798k);
        }
        if (!TextUtils.isEmpty(aVar.a)) {
            hashMap.put("sessionId", aVar.a);
        }
        com.jingdong.sdk.dialingtest.c.e.a.c("DialingReporter", "trace route test report data: " + hashMap.toString());
        j(hashMap);
    }

    public static void f(a aVar) {
        a = aVar;
    }

    public static void g(b bVar) {
        b = bVar;
    }

    private static void h(HashMap<String, String> hashMap) {
        if (hashMap == null) {
            return;
        }
        String str = com.jingdong.sdk.dialingtest.b.k().f14710f;
        if (!TextUtils.isEmpty(str) && str.equals(hashMap.get("diagId"))) {
            if ("5".equals(hashMap.get("chId"))) {
                com.jingdong.sdk.dialingtest.b.k().f14709e.decrementAndGet();
                b bVar = b;
                if (bVar != null) {
                    bVar.b(l(hashMap));
                }
            } else {
                com.jingdong.sdk.dialingtest.b.k().d.decrementAndGet();
                b bVar2 = b;
                if (bVar2 != null) {
                    bVar2.c(l(hashMap));
                }
            }
            if (com.jingdong.sdk.dialingtest.b.k().f14709e.get() == 0 && com.jingdong.sdk.dialingtest.b.k().d.get() == 0) {
                b bVar3 = b;
                if (bVar3 != null) {
                    bVar3.a();
                }
                b = null;
                return;
            }
            return;
        }
        com.jingdong.sdk.dialingtest.c.e.a.a("DialingReporter", "diagnose id changed");
    }

    public static String i() {
        return b("9", "3");
    }

    private static void j(HashMap<String, String> hashMap) {
        a aVar = a;
        if (aVar == null) {
            com.jingdong.sdk.dialingtest.c.e.a.a("DialingReporter", "reporter not init");
        } else {
            aVar.b(hashMap);
        }
    }

    public static String k() {
        String b2 = b("9", "5");
        if (!TextUtils.isEmpty(b2)) {
            try {
                com.jingdong.sdk.dialingtest.c.e.b.f("dialing_local_http_strategy_key", b2);
            } catch (Throwable th) {
                th.printStackTrace();
            }
            return b2;
        }
        try {
            b2 = com.jingdong.sdk.dialingtest.c.e.b.c("dialing_local_http_strategy_key", "");
        } catch (Throwable th2) {
            th2.printStackTrace();
        }
        return !TextUtils.isEmpty(b2) ? b2 : "{\"repeat\":\"1\",\"ldnsSwitch\":\"0\",\"timeout\":\"5\",\"hosts\":[{\"host\":\"https://api.m.jd.com\",\"method\":\"head\",\"body\":\"\",\"isSetHeaders\":0,\"headers\":{},\"isReportBody\":0,\"isReportCertificate\":1},{\"host\":\"http://api.m.jd.com\",\"method\":\"head\",\"body\":\"\",\"isSetHeaders\":0,\"headers\":{},\"isReportBody\":0,\"isReportCertificate\":0},{\"host\":\"https://m.360buyimg.com/mobilecms/s357x357_jfs/t3244/133/1862505358/77665/8338e400/57d50f21Naabeb513.jpg\",\"method\":\"head\",\"body\":\"\",\"isSetHeaders\":0,\"headers\":{},\"isReportBody\":0,\"isReportCertificate\":1},{\"host\":\"https://www.jd.com\",\"method\":\"head\",\"body\":\"\",\"isSetHeaders\":0,\"headers\":{},\"isReportBody\":0,\"isReportCertificate\":0},{\"host\":\"https://pro.m.jd.com/mall/active/4P9a2T9osR9JvtzHVaYTPvsecRtg/index.html\",\"method\":\"head\",\"body\":\"\",\"isSetHeaders\":0,\"headers\":{},\"isReportBody\":0,\"isReportCertificate\":0}]}";
    }

    private static String l(HashMap<String, String> hashMap) {
        if (hashMap == null) {
            return "";
        }
        JSONObject jSONObject = new JSONObject();
        try {
            for (Map.Entry<String, String> entry : hashMap.entrySet()) {
                jSONObject.put(entry.getKey(), entry.getValue());
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return jSONObject.toString();
    }

    public static String m() {
        String b2 = b("9", "4");
        if (!TextUtils.isEmpty(b2)) {
            try {
                com.jingdong.sdk.dialingtest.c.e.b.f("dialing_local_ping_strategy_key", b2);
            } catch (Throwable th) {
                th.printStackTrace();
            }
            return b2;
        }
        try {
            b2 = com.jingdong.sdk.dialingtest.c.e.b.c("dialing_local_ping_strategy_key", "");
        } catch (Throwable th2) {
            th2.printStackTrace();
        }
        return !TextUtils.isEmpty(b2) ? b2 : "{\"repeat\":1,\"ldnsSwitch\":0,\"packetNum\":4,\"timeout\":3,\"hosts\":[{\"type\":\"domain\",\"host\":\"api.m.jd.com\"},{\"type\":\"domain\",\"host\":\"www.jd.com\"},{\"type\":\"domain\",\"host\":\"m.360buyimg.com\"},{\"type\":\"domain\",\"host\":\"m.taobao.com\"},{\"type\":\"domain\",\"host\":\"api.yangkeduo.com\"},{\"type\":\"domain\",\"host\":\"wx.qq.com\"}]}";
    }

    public static String n() {
        return b("9", "1");
    }

    public static String o() {
        return b("9", "2");
    }
}

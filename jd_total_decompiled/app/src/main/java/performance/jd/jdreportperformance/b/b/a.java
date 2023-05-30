package performance.jd.jdreportperformance.b.b;

import android.text.TextUtils;
import com.jd.dynamic.DYConstants;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONObject;
import performance.jd.jdreportperformance.c.b;
import performance.jd.jdreportperformance.minterface.InitInformation;

/* loaded from: classes.dex */
public class a {
    private static boolean a(String str) {
        return Pattern.compile("[0-9]*").matcher(str.trim()).matches();
    }

    public static String b() {
        StringBuilder sb = new StringBuilder();
        sb.append("");
        Locale locale = Locale.CHINESE;
        double currentTimeMillis = System.currentTimeMillis();
        Double.isNaN(currentTimeMillis);
        sb.append(String.format(locale, "%.6f", Double.valueOf((currentTimeMillis + 0.0d) / 1000.0d)));
        return sb.toString();
    }

    private static String c() {
        InitInformation.IPerformanceController e2 = performance.jd.jdreportperformance.d.b.d().e();
        if (e2 != null) {
            String queryIpByHost = e2.queryIpByHost("perf.m.jd.com");
            StringBuilder sb = new StringBuilder();
            sb.append("httpdns ip: ");
            sb.append(TextUtils.isEmpty(queryIpByHost) ? "empty" : queryIpByHost);
            b.a("CommonUtil", sb.toString());
            if (TextUtils.isEmpty(queryIpByHost) || queryIpByHost.contains("[") || !queryIpByHost.contains(":")) {
                return queryIpByHost;
            }
            return "[" + queryIpByHost + "]";
        }
        return "";
    }

    public static boolean d(List<performance.jd.jdreportperformance.e.b> list) {
        String b = b(list);
        String c2 = c();
        boolean z = !TextUtils.isEmpty(c2);
        return a(b, z ? a(c2, "/app_monitor/v2/report") : "https://perf.m.jd.com/app_monitor/v2/report", "https://perf.m.jd.com/app_monitor/v2/report", z);
    }

    private static String b(List<performance.jd.jdreportperformance.e.b> list) {
        JSONArray jSONArray = new JSONArray();
        for (performance.jd.jdreportperformance.e.b bVar : list) {
            if (bVar != null) {
                try {
                    jSONArray.put(new JSONObject(bVar.b()));
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }
        return a(jSONArray);
    }

    public static Long a(String str, long j2) {
        long j3;
        if (a(str)) {
            try {
                j3 = Long.parseLong(str);
            } catch (Exception e2) {
                e2.printStackTrace();
                j3 = j2;
            }
            if (j3 >= 0) {
                j2 = j3;
            }
        }
        return Long.valueOf(j2);
    }

    public static boolean c(List<HashMap<String, String>> list) {
        String a = a(list);
        String c2 = c();
        boolean z = !TextUtils.isEmpty(c2);
        return a(a, z ? a(c2, "/app_monitor/v2/report") : "https://perf.m.jd.com/app_monitor/v2/report", "https://perf.m.jd.com/app_monitor/v2/report", z);
    }

    public static JSONObject a(HashMap<String, String> hashMap) {
        if (hashMap != null && !hashMap.isEmpty()) {
            try {
                JSONObject jSONObject = new JSONObject();
                for (Map.Entry<String, String> entry : hashMap.entrySet()) {
                    if (entry != null && !TextUtils.isEmpty(entry.getKey())) {
                        try {
                            jSONObject.put(entry.getKey(), entry.getValue() + "");
                        } catch (Exception unused) {
                        }
                    }
                }
                return jSONObject;
            } catch (Throwable unused2) {
            }
        }
        return null;
    }

    public static boolean a() {
        String a = a((JSONArray) null);
        String c2 = c();
        boolean z = !TextUtils.isEmpty(c2);
        return a(a, z ? a(c2, "/app_monitor/v2/getRule") : "https://perf.m.jd.com/app_monitor/v2/getRule", "https://perf.m.jd.com/app_monitor/v2/getRule", z);
    }

    private static String a(String str, String str2) {
        return "https://" + str + str2;
    }

    private static String a(List<HashMap<String, String>> list) {
        JSONArray jSONArray = new JSONArray();
        Iterator<HashMap<String, String>> it = list.iterator();
        while (it.hasNext()) {
            JSONObject a = a(it.next());
            if (a != null) {
                try {
                    jSONArray.put(a);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }
        return a(jSONArray);
    }

    private static String a(JSONArray jSONArray) {
        String str;
        JSONObject a = performance.jd.jdreportperformance.e.a.c().a();
        if (jSONArray != null) {
            try {
                a.put("data", jSONArray);
            } catch (Throwable th) {
                th.printStackTrace();
                str = "";
            }
        }
        str = a.toString();
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        try {
            return performance.jd.jdreportperformance.b.a.a.a(performance.jd.jdreportperformance.b.a.a.b(str.getBytes()));
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }

    private static boolean a(String str, String str2, String str3, boolean z) {
        boolean z2;
        if (TextUtils.isEmpty(str)) {
            b.a("CommonUtil", "data after assemble is null or empty");
            return false;
        }
        b.a("CommonUtil", "http url: " + str2);
        performance.jd.jdreportperformance.c.b bVar = new performance.jd.jdreportperformance.c.b();
        bVar.a(str2);
        bVar.a(str.getBytes());
        b.a a = bVar.a();
        if (a != null && a.a == 0) {
            if (a.b == 200) {
                String a2 = a.a();
                StringBuilder sb = new StringBuilder();
                sb.append("response strategy: ");
                sb.append(TextUtils.isEmpty(a2) ? "" : a2);
                b.b("CommonUtil", sb.toString());
                z2 = performance.jd.jdreportperformance.e.c.a().a(a2);
                if (!z2) {
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("update strategy failed, strategy: ");
                    if (TextUtils.isEmpty(a2)) {
                        a2 = DYConstants.DY_NULL_STR;
                    }
                    sb2.append(a2);
                    b.a("CommonUtil", sb2.toString());
                }
                b.a("CommonUtil", "postStatus: " + z2);
                return (z2 && z) ? a(str, str3, str3, false) : z2;
            }
            b.b("CommonUtil", "response code error: " + a.b);
        }
        z2 = false;
        b.a("CommonUtil", "postStatus: " + z2);
        if (z2) {
        }
    }
}

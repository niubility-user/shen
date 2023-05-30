package performance.jd.jdreportperformance.e;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import performance.jd.jdreportperformance.b.b.d;
import performance.jd.jdreportperformance.entity.StategyEntity;

/* loaded from: classes.dex */
public class c {

    /* renamed from: i  reason: collision with root package name */
    private static volatile c f20460i;
    private final HashMap<String, Long> a;
    private final HashMap<String, Long> b;

    /* renamed from: c  reason: collision with root package name */
    private final List<String> f20461c;
    private long d;

    /* renamed from: e  reason: collision with root package name */
    private long f20462e;

    /* renamed from: f  reason: collision with root package name */
    String f20463f = "";

    /* renamed from: g  reason: collision with root package name */
    private JSONObject f20464g = null;

    /* renamed from: h  reason: collision with root package name */
    private volatile int f20465h = 0;

    /* loaded from: classes.dex */
    class a extends ArrayList<String> {
        a(c cVar) {
            add("wifi");
        }
    }

    public c() {
        this.d = 0L;
        this.f20462e = 0L;
        HashMap<String, Long> hashMap = new HashMap<>();
        this.a = hashMap;
        HashMap<String, Long> hashMap2 = new HashMap<>();
        this.b = hashMap2;
        hashMap.put("wifi", 30L);
        hashMap.put("mobile", 1800L);
        hashMap2.put("wifi", 20L);
        hashMap2.put("mobile", 20L);
        this.d = 1800L;
        this.f20462e = 20L;
        this.f20461c = new a(this);
    }

    public static c a() {
        if (f20460i == null) {
            synchronized (c.class) {
                if (f20460i == null) {
                    f20460i = new c();
                }
            }
        }
        return f20460i;
    }

    private String e() {
        Context a2 = performance.jd.jdreportperformance.a.b().a();
        return a2 == null ? "" : a2.getSharedPreferences("strategyInfo", 0).getString("strategy", "");
    }

    public long b() {
        Long l2;
        Context a2 = performance.jd.jdreportperformance.a.b().a();
        if (a2 != null) {
            String c2 = d.c(a2);
            if (this.b.containsKey(c2) && (l2 = this.b.get(c2)) != null) {
                return l2.longValue();
            }
        }
        return this.f20462e;
    }

    public long c() {
        Long l2;
        Context a2 = performance.jd.jdreportperformance.a.b().a();
        if (a2 != null) {
            String c2 = d.c(a2);
            if (this.a.containsKey(c2) && (l2 = this.a.get(c2)) != null) {
                return l2.longValue();
            }
        }
        return this.d;
    }

    public boolean d() {
        Context a2 = performance.jd.jdreportperformance.a.b().a();
        if (a2 == null) {
            return false;
        }
        String c2 = d.c(a2);
        List<String> list = this.f20461c;
        return list != null && list.contains(c2);
    }

    private void b(String str) {
        Context a2 = performance.jd.jdreportperformance.a.b().a();
        if (a2 == null) {
            return;
        }
        SharedPreferences.Editor edit = a2.getSharedPreferences("strategyInfo", 0).edit();
        edit.putString("strategy", str);
        edit.apply();
    }

    public StategyEntity a(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return null;
        }
        if (this.f20464g == null && this.f20465h < 5) {
            synchronized (this) {
                if (this.f20464g == null) {
                    performance.jd.jdreportperformance.b.b.b.a("StrategyModel", "load strategy from sp");
                    a(e());
                    this.f20465h++;
                }
            }
        }
        JSONObject jSONObject = this.f20464g;
        if (jSONObject == null) {
            performance.jd.jdreportperformance.b.b.b.a("StrategyModel", "strategyJsonObj is null");
            return null;
        }
        JSONObject optJSONObject = jSONObject.optJSONObject(str);
        if (optJSONObject == null) {
            performance.jd.jdreportperformance.b.b.b.a("StrategyModel", "typeId: " + str + ", chId: " + str2 + " empty data");
            return null;
        }
        JSONObject optJSONObject2 = optJSONObject.optJSONObject(str2);
        if (optJSONObject2 == null) {
            performance.jd.jdreportperformance.b.b.b.a("StrategyModel", "typeId: " + str + ", chId: " + str2 + " empty data");
            return null;
        }
        StategyEntity stategyEntity = new StategyEntity();
        stategyEntity.rt = optJSONObject2.optString("rt");
        stategyEntity.ret = optJSONObject2.optString("ret");
        stategyEntity.param = optJSONObject2.optString("param");
        performance.jd.jdreportperformance.b.b.b.b("StrategyModel", "typeId: " + str + ", chId: " + str2 + "  get strategy entity success");
        return stategyEntity;
    }

    public synchronized boolean a(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            String optString = jSONObject.optString("errCode", "");
            String optString2 = jSONObject.optString("errMsg", "");
            if (!"0".equals(optString)) {
                performance.jd.jdreportperformance.b.b.b.a("StrategyModel", "errCode: " + optString + "errMsg: " + optString2);
                return false;
            }
            String optString3 = jSONObject.optString("strategyId", "");
            if (TextUtils.isEmpty(optString3)) {
                performance.jd.jdreportperformance.b.b.b.b("StrategyModel", "strategyId is empty, not need update");
                return true;
            }
            long longValue = performance.jd.jdreportperformance.b.b.a.a("".equals(jSONObject.optString("wifiInt")) ? "0" : jSONObject.optString("wifiInt"), 0L).longValue();
            if (longValue != 0) {
                this.a.put("wifi", Long.valueOf(longValue));
                this.d = longValue;
            } else if (!this.a.containsKey("wifi")) {
                this.a.put("wifi", 30L);
            }
            long longValue2 = performance.jd.jdreportperformance.b.b.a.a("".equals(jSONObject.optString("mobileInt")) ? "0" : jSONObject.optString("mobileInt"), 0L).longValue();
            if (longValue2 != 0) {
                this.a.put("mobile", Long.valueOf(longValue2));
                if (longValue2 > this.d) {
                    this.d = longValue2;
                }
            } else if (!this.a.containsKey("mobile")) {
                this.a.put("mobile", 1800L);
            }
            long longValue3 = performance.jd.jdreportperformance.b.b.a.a("".equals(jSONObject.optString("wifiSz")) ? "0" : jSONObject.optString("wifiSz"), 0L).longValue();
            if (longValue3 != 0) {
                this.b.put("wifi", Long.valueOf(longValue3));
                this.f20462e = longValue3;
            } else if (!this.b.containsKey("wifi")) {
                this.b.put("wifi", 20L);
            }
            long longValue4 = performance.jd.jdreportperformance.b.b.a.a("".equals(jSONObject.optString("mobileSz")) ? "0" : jSONObject.optString("mobileSz"), 0L).longValue();
            if (longValue4 != 0) {
                this.b.put("mobile", Long.valueOf(longValue4));
                if (longValue4 < this.f20462e) {
                    this.f20462e = longValue4;
                }
            } else if (!this.b.containsKey("mobile")) {
                this.b.put("mobile", 20L);
            }
            if (jSONObject.has("reportNet")) {
                try {
                    JSONArray optJSONArray = jSONObject.optJSONArray("reportNet");
                    if (optJSONArray != null) {
                        this.f20461c.clear();
                        for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                            String string = optJSONArray.getString(i2);
                            if (!TextUtils.isEmpty(string)) {
                                this.f20461c.add(string);
                            }
                        }
                    }
                } catch (JSONException e2) {
                    e2.printStackTrace();
                }
            }
            this.f20464g = jSONObject.optJSONObject("strategy");
            this.f20463f = optString3;
            b(str);
            performance.jd.jdreportperformance.b.b.b.a("StrategyModel", "update strategy success");
            return true;
        } catch (JSONException e3) {
            e3.printStackTrace();
            return false;
        }
    }
}

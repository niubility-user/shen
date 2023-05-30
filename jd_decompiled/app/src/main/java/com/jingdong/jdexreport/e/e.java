package com.jingdong.jdexreport.e;

import android.content.Context;
import android.text.TextUtils;
import com.jingdong.common.jdmiaosha.utils.cache.Final;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class e {

    /* renamed from: k  reason: collision with root package name */
    public static e f12607k;
    private boolean b;

    /* renamed from: c  reason: collision with root package name */
    private HashMap<String, Long> f12608c;
    private HashMap<String, Long> d;

    /* renamed from: j  reason: collision with root package name */
    private Context f12614j;
    public String a = "";

    /* renamed from: e  reason: collision with root package name */
    private long f12609e = 20;

    /* renamed from: f  reason: collision with root package name */
    private long f12610f = 60;

    /* renamed from: g  reason: collision with root package name */
    private long f12611g = 0;

    /* renamed from: h  reason: collision with root package name */
    public long f12612h = 0;

    /* renamed from: i  reason: collision with root package name */
    public long f12613i = 0;

    public e(Context context) {
        if (context.getApplicationContext() != null) {
            this.f12614j = context.getApplicationContext();
        } else {
            this.f12614j = context;
        }
        f();
    }

    public static synchronized e a(Context context) {
        e eVar;
        synchronized (e.class) {
            if (f12607k == null) {
                f12607k = new e(context);
            }
            eVar = f12607k;
        }
        return eVar;
    }

    public static synchronized void b() {
        synchronized (e.class) {
            e eVar = f12607k;
            if (eVar != null) {
                eVar.a();
            }
            f12607k = null;
        }
    }

    public synchronized long c(String str) {
        long j2;
        Long l2;
        j2 = this.f12612h;
        if (this.f12608c.containsKey(str) && (l2 = this.f12608c.get(str)) != null) {
            j2 = 10;
            if (l2.longValue() > 10) {
                j2 = l2.longValue();
            }
        }
        return j2;
    }

    public synchronized boolean d(String str) {
        if (!a(str)) {
            f();
            return false;
        } else if (this.a.equals(str)) {
            return true;
        } else {
            JSONObject jSONObject = null;
            try {
                jSONObject = new JSONObject(str);
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
            if (jSONObject == null) {
                f();
                return false;
            } else if (jSONObject.optInt("errCode", 0) != 0) {
                f();
                return false;
            } else if (jSONObject.optInt("ret") == 1) {
                this.b = true;
                long longValue = com.jingdong.jdexreport.a.a.a.a("".equals(jSONObject.optString("wifiInt")) ? "0" : jSONObject.optString("wifiInt"), 0L).longValue();
                if (longValue != 0) {
                    this.f12608c.put("wifi", Long.valueOf(longValue));
                    this.f12612h = longValue;
                } else if (!this.f12608c.containsKey("wifi")) {
                    this.f12608c.put("wifi", 300L);
                }
                long longValue2 = com.jingdong.jdexreport.a.a.a.a("".equals(jSONObject.optString("mobileInt")) ? "0" : jSONObject.optString("mobileInt"), 0L).longValue();
                if (longValue2 != 0) {
                    this.f12608c.put("mobile", Long.valueOf(longValue2));
                    if (longValue2 > this.f12612h) {
                        this.f12612h = longValue2;
                    }
                } else if (!this.f12608c.containsKey("mobile")) {
                    this.f12608c.put("mobile", 300L);
                }
                long longValue3 = com.jingdong.jdexreport.a.a.a.a("".equals(jSONObject.optString("wifiSz")) ? "0" : jSONObject.optString("wifiSz"), 0L).longValue();
                if (longValue3 != 0) {
                    this.d.put("wifi", Long.valueOf(longValue3));
                    this.f12613i = longValue3;
                } else if (!this.d.containsKey("wifi")) {
                    this.d.put("wifi", 1L);
                }
                long longValue4 = com.jingdong.jdexreport.a.a.a.a("".equals(jSONObject.optString("mobileSz")) ? "0" : jSONObject.optString("mobileSz"), 0L).longValue();
                if (longValue4 != 0) {
                    this.d.put("mobile", Long.valueOf(longValue4));
                    if (longValue4 < this.f12613i) {
                        this.f12613i = longValue4;
                    }
                } else if (!this.d.containsKey("mobile")) {
                    this.d.put("mobile", 1L);
                }
                if (jSONObject.has("limitCnt")) {
                    this.f12609e = jSONObject.optInt("limitCnt");
                } else {
                    this.f12609e = 20L;
                }
                if (jSONObject.has("limitInt")) {
                    this.f12610f = jSONObject.optInt("limitInt");
                } else {
                    this.f12610f = 60L;
                }
                if (jSONObject.has("expireInt")) {
                    int optInt = jSONObject.optInt("expireInt");
                    long j2 = optInt;
                    if (j2 != this.f12611g) {
                        a.a(this.f12614j).a("exceptiondataexpireint", "" + optInt);
                        this.f12611g = j2;
                    }
                }
                this.a = str;
                return true;
            } else {
                this.b = false;
                return true;
            }
        }
    }

    public long e() {
        return this.f12610f;
    }

    public synchronized void f() {
        if (this.f12614j == null) {
            return;
        }
        this.f12608c = new HashMap<>();
        this.d = new HashMap<>();
        this.f12608c.put("wifi", 300L);
        this.f12608c.put("mobile", 300L);
        this.d.put("wifi", 1L);
        this.d.put("mobile", 1L);
        this.f12612h = 300L;
        this.f12613i = 1L;
        this.b = true;
        String b = a.a(this.f12614j).b("exceptiondataexpireint");
        if ("".equals(b)) {
            this.f12611g = Final.SEV_DAY;
        } else {
            this.f12611g = com.jingdong.jdexreport.a.a.a.a(b, (long) Final.SEV_DAY).longValue();
        }
    }

    public boolean g() {
        return this.b;
    }

    public void a() {
        HashMap<String, Long> hashMap = this.f12608c;
        if (hashMap != null) {
            hashMap.clear();
        }
        HashMap<String, Long> hashMap2 = this.d;
        if (hashMap2 != null) {
            hashMap2.clear();
        }
    }

    public synchronized long b(String str) {
        Long l2;
        if (this.d.containsKey(str) && (l2 = this.d.get(str)) != null && l2.longValue() > 0) {
            return l2.longValue();
        }
        return this.f12613i;
    }

    public long c() {
        return this.f12611g;
    }

    public synchronized boolean a(String str, long j2) {
        Long l2;
        if (str == null) {
            return false;
        }
        HashMap<String, Long> hashMap = this.d;
        if (hashMap == null) {
            return false;
        }
        if (!hashMap.containsKey(str) || (l2 = this.d.get(str)) == null) {
            return false;
        }
        return j2 >= l2.longValue();
    }

    private boolean a(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.has("expireInt") && jSONObject.has("mobileInt") && jSONObject.has("mobileSz") && jSONObject.has("limitCnt") && jSONObject.has("limitInt") && jSONObject.has("ret") && jSONObject.has("wifiInt")) {
                if (jSONObject.has("wifiSz")) {
                    return true;
                }
            }
            return false;
        } catch (JSONException e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public long d() {
        return this.f12609e;
    }
}

package com.jd.stat.security.jma.b;

import android.text.TextUtils;
import com.jd.stat.common.t;
import java.util.HashMap;
import java.util.Iterator;
import org.json.JSONObject;

/* loaded from: classes18.dex */
public class h {
    public static long a;
    public static long b;

    /* renamed from: c  reason: collision with root package name */
    private static final HashMap<String, Long> f7085c = new HashMap<>();
    private static final HashMap<String, Long> d = new HashMap<>();

    public static void a() {
        b = com.jd.stat.common.b.f.b("lastfixinfotime", 0L);
        com.jd.stat.common.b.b.b("JDMob.Security.SendController", "lastFixInfoReportTime = " + b);
        f();
        d();
    }

    public static boolean b() {
        if (com.jd.stat.security.c.a(2)) {
            return true;
        }
        boolean z = System.currentTimeMillis() - a >= (((long) com.jd.stat.security.d.a().h()) * 60) * 1000;
        if (z) {
            a = System.currentTimeMillis();
            d.clear();
            g();
            com.jd.stat.common.b.b.b("JDMob.Security.SendController", "shouldReportAlterInfo return true. LastReportTime:" + a);
        } else {
            com.jd.stat.common.b.b.b("JDMob.Security.SendController", "shouldReportAlterInfo return false. LastReportTime:" + a);
        }
        return z;
    }

    public static boolean c() {
        if (com.jd.stat.security.c.a(1)) {
            return true;
        }
        boolean z = System.currentTimeMillis() - b >= (((long) com.jd.stat.security.d.a().g()) * 60) * 1000;
        com.jd.stat.common.b.b.b("JDMob.Security.SendController", "should = " + z + ",lastFixInfoReportTime = " + b);
        if (!z) {
            z = !t.a();
            com.jd.stat.common.b.b.b("JDMob.Security.SendController", "isSameUniqueId. should = " + z);
        }
        if (z) {
            long currentTimeMillis = System.currentTimeMillis();
            b = currentTimeMillis;
            com.jd.stat.common.b.f.a("lastfixinfotime", currentTimeMillis);
            f7085c.clear();
            e();
        }
        return z;
    }

    private static void d() {
        HashMap<String, Long> hashMap = f7085c;
        hashMap.clear();
        String b2 = com.jd.stat.common.b.f.b("lastFixReportSceneMap", "");
        if (TextUtils.isEmpty(b2)) {
            return;
        }
        try {
            hashMap.putAll(a(new JSONObject(b2)));
        } catch (Throwable th) {
            com.jd.stat.common.b.b.a("SendController", th);
        }
    }

    private static void e() {
        com.jd.stat.common.b.f.a("lastFixReportSceneMap", a(f7085c).toString());
    }

    private static void f() {
        HashMap<String, Long> hashMap = d;
        hashMap.clear();
        String b2 = com.jd.stat.common.b.f.b("lastAlterReportSceneMap", "");
        if (TextUtils.isEmpty(b2)) {
            return;
        }
        try {
            hashMap.putAll(a(new JSONObject(b2)));
        } catch (Throwable th) {
            com.jd.stat.common.b.b.a("SendController", th);
        }
    }

    private static void g() {
        com.jd.stat.common.b.f.a("lastAlterReportSceneMap", a(d).toString());
    }

    public static boolean a(String str, String str2) {
        if (b()) {
            return true;
        }
        boolean z = System.currentTimeMillis() - b(str) >= (((long) com.jd.stat.security.d.a().h()) * 60) * 1000;
        if (z) {
            long currentTimeMillis = System.currentTimeMillis();
            a = currentTimeMillis;
            b(str, currentTimeMillis);
            com.jd.stat.common.b.b.b("JDMob.Security.SendController", "shouldReportAlterInfo with scene:" + str + " trigger:" + str2 + " return true");
        } else {
            com.jd.stat.common.b.b.b("JDMob.Security.SendController", "shouldReportAlterInfo with scene:" + str + " trigger:" + str2 + " return false");
        }
        return z;
    }

    public static boolean b(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return c();
        }
        if (c()) {
            return true;
        }
        boolean z = System.currentTimeMillis() - a(str) >= (((long) com.jd.stat.security.d.a().g()) * 60) * 1000;
        if (z) {
            long currentTimeMillis = System.currentTimeMillis();
            b = currentTimeMillis;
            com.jd.stat.common.b.f.a("lastfixinfotime", currentTimeMillis);
            a(str, b);
            com.jd.stat.common.b.b.b("JDMob.Security.SendController", "shouldReportFixInfo scene:" + str + " trigger:" + str2 + " true");
        } else {
            com.jd.stat.common.b.b.b("JDMob.Security.SendController", "shouldReportFixInfo scene:" + str + " trigger:" + str2 + " false");
        }
        return z;
    }

    private static void a(String str, long j2) {
        f7085c.put(str, Long.valueOf(j2));
        e();
    }

    private static long a(String str) {
        Long l2 = f7085c.get(str);
        if (l2 == null) {
            return 0L;
        }
        return l2.longValue();
    }

    private static JSONObject a(HashMap<String, Long> hashMap) {
        try {
            JSONObject jSONObject = new JSONObject();
            for (String str : hashMap.keySet()) {
                jSONObject.put(str, hashMap.get(str));
            }
            return jSONObject;
        } catch (Throwable unused) {
            return new JSONObject();
        }
    }

    private static void b(String str, long j2) {
        d.put(str, Long.valueOf(j2));
        g();
    }

    private static long b(String str) {
        Long l2 = d.get(str);
        if (l2 == null) {
            return 0L;
        }
        return l2.longValue();
    }

    private static HashMap<String, Long> a(JSONObject jSONObject) {
        try {
            HashMap<String, Long> hashMap = new HashMap<>();
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                hashMap.put(next, Long.valueOf(Long.parseLong(jSONObject.getString(next))));
            }
            return hashMap;
        } catch (Throwable unused) {
            return new HashMap<>();
        }
    }
}

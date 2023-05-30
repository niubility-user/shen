package com.xiaomi.push.service;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.NetworkInfo;
import android.text.TextUtils;
import com.huawei.hms.support.hianalytics.HiAnalyticsConstant;
import com.jd.aips.verify.tracker.VerifyTracker;
import com.jd.dynamic.DYConstants;
import com.jingdong.common.database.table.NavigationBarTable;
import com.jingdong.common.unification.customtheme.CustomThemeConstance;
import com.jingdong.sdk.baseinfo.BaseInfo;
import com.xiaomi.push.a7;
import com.xiaomi.push.a8;
import com.xiaomi.push.g7;
import com.xiaomi.push.h7;
import com.xiaomi.push.m9;
import com.xiaomi.push.u5;
import com.xiaomi.push.y4;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import org.json.JSONObject;

/* loaded from: classes11.dex */
public class n2 {
    @SuppressLint({"StaticFieldLeak"})

    /* renamed from: n */
    private static volatile n2 f19143n;
    private final SharedPreferences a;

    /* renamed from: h */
    private long f19148h;

    /* renamed from: i */
    private final boolean f19149i;

    /* renamed from: j */
    private final boolean f19150j;

    /* renamed from: l */
    private final Context f19152l;
    private final AtomicInteger b = new AtomicInteger(0);

    /* renamed from: c */
    private String f19144c = null;
    private volatile boolean d = false;

    /* renamed from: e */
    private String f19145e = null;

    /* renamed from: f */
    private final AtomicInteger f19146f = new AtomicInteger(0);

    /* renamed from: g */
    private final AtomicInteger f19147g = new AtomicInteger(0);

    /* renamed from: k */
    private int f19151k = -1;

    /* renamed from: m */
    private long f19153m = -1;

    /* loaded from: classes11.dex */
    public static class a {
        public static String a() {
            return "support_wifi_digest";
        }

        public static String b(String str) {
            return String.format("HB_%s", str);
        }

        public static String c() {
            return "record_support_wifi_digest_reported";
        }

        public static String d(String str) {
            return String.format("HB_dead_time_%s", str);
        }

        public static String e() {
            return "record_hb_count_start";
        }

        public static String f() {
            return "record_short_hb_count";
        }

        public static String g() {
            return "record_long_hb_count";
        }

        public static String h() {
            return "record_hb_change";
        }

        public static String i() {
            return "record_mobile_ptc";
        }

        public static String j() {
            return "record_wifi_ptc";
        }

        public static String k() {
            return "record_ptc_start";
        }

        public static String l() {
            return "keep_short_hb_effective_time";
        }
    }

    private n2(Context context) {
        this.f19152l = context;
        this.f19150j = a8.j(context);
        this.f19149i = b0.d(context).m(h7.IntelligentHeartbeatSwitchBoolean.a(), true);
        SharedPreferences sharedPreferences = context.getSharedPreferences("hb_record", 0);
        this.a = sharedPreferences;
        long currentTimeMillis = System.currentTimeMillis();
        if (sharedPreferences.getLong(a.e(), -1L) == -1) {
            sharedPreferences.edit().putLong(a.e(), currentTimeMillis).apply();
        }
        long j2 = sharedPreferences.getLong(a.k(), -1L);
        this.f19148h = j2;
        if (j2 == -1) {
            this.f19148h = currentTimeMillis;
            sharedPreferences.edit().putLong(a.k(), currentTimeMillis).apply();
        }
    }

    private boolean A() {
        if (this.f19148h == -1) {
            return false;
        }
        long currentTimeMillis = System.currentTimeMillis();
        long j2 = this.f19148h;
        return j2 > currentTimeMillis || currentTimeMillis - j2 >= 259200000;
    }

    private void B() {
        int i2;
        String[] split;
        String[] split2;
        if (s()) {
            String string = this.a.getString(a.h(), null);
            char c2 = 1;
            char c3 = 0;
            if (!TextUtils.isEmpty(string) && (split = string.split("###")) != null) {
                int i3 = 0;
                while (i3 < split.length) {
                    if (!TextUtils.isEmpty(split[i3]) && (split2 = split[i3].split(":::")) != null && split2.length >= 4) {
                        String str = split2[c3];
                        String str2 = split2[c2];
                        String str3 = split2[2];
                        String str4 = split2[3];
                        HashMap hashMap = new HashMap();
                        hashMap.put("event", "change");
                        hashMap.put(CustomThemeConstance.NAVI_MODEL, BaseInfo.getDeviceModel());
                        hashMap.put(HiAnalyticsConstant.BI_KEY_NET_TYPE, str2);
                        hashMap.put("net_name", str);
                        hashMap.put("interval", str3);
                        hashMap.put(VerifyTracker.KEY_TIMESTAMP, str4);
                        h("category_hb_change", null, hashMap);
                        g.j.a.a.a.c.o("[HB] report hb changed events.");
                    }
                    i3++;
                    c2 = 1;
                    c3 = 0;
                }
                this.a.edit().remove(a.h()).apply();
            }
            if (this.a.getBoolean(a.a(), false) && !this.a.getBoolean(a.c(), false)) {
                HashMap hashMap2 = new HashMap();
                hashMap2.put("event", "support");
                hashMap2.put(CustomThemeConstance.NAVI_MODEL, BaseInfo.getDeviceModel());
                hashMap2.put(VerifyTracker.KEY_TIMESTAMP, String.valueOf(System.currentTimeMillis() / 1000));
                h("category_hb_change", null, hashMap2);
                g.j.a.a.a.c.o("[HB] report support wifi digest events.");
                this.a.edit().putBoolean(a.c(), true).apply();
            }
            if (y()) {
                int i4 = this.a.getInt(a.f(), 0);
                int i5 = this.a.getInt(a.g(), 0);
                if (i4 > 0 || i5 > 0) {
                    long j2 = this.a.getLong(a.e(), -1L);
                    String valueOf = String.valueOf(235000);
                    String valueOf2 = String.valueOf(j2);
                    String valueOf3 = String.valueOf(System.currentTimeMillis());
                    try {
                        JSONObject jSONObject = new JSONObject();
                        jSONObject.put("interval", valueOf);
                        jSONObject.put("c_short", String.valueOf(i4));
                        jSONObject.put("c_long", String.valueOf(i5));
                        jSONObject.put("count", String.valueOf(i4 + i5));
                        jSONObject.put(NavigationBarTable.FIELD_START_TIME, valueOf2);
                        jSONObject.put(NavigationBarTable.FIELD_END_TIME, valueOf3);
                        String jSONObject2 = jSONObject.toString();
                        HashMap hashMap3 = new HashMap();
                        hashMap3.put("event", "long_and_short_hb_count");
                        h("category_hb_count", jSONObject2, hashMap3);
                        g.j.a.a.a.c.o("[HB] report short/long hb count events.");
                    } catch (Throwable unused) {
                    }
                }
                this.a.edit().putInt(a.f(), 0).putInt(a.g(), 0).putLong(a.e(), System.currentTimeMillis()).apply();
            }
            if (A()) {
                String valueOf4 = String.valueOf(this.f19148h);
                String valueOf5 = String.valueOf(System.currentTimeMillis());
                int i6 = this.a.getInt(a.i(), 0);
                if (i6 > 0) {
                    try {
                        JSONObject jSONObject3 = new JSONObject();
                        jSONObject3.put(HiAnalyticsConstant.BI_KEY_NET_TYPE, "M");
                        jSONObject3.put("ptc", i6);
                        jSONObject3.put(NavigationBarTable.FIELD_START_TIME, valueOf4);
                        jSONObject3.put(NavigationBarTable.FIELD_END_TIME, valueOf5);
                        String jSONObject4 = jSONObject3.toString();
                        HashMap hashMap4 = new HashMap();
                        hashMap4.put("event", "ptc_event");
                        h("category_lc_ptc", jSONObject4, hashMap4);
                        g.j.a.a.a.c.o("[HB] report ping timeout count events of mobile network.");
                        this.a.edit().putInt(a.i(), 0).apply();
                    } catch (Throwable unused2) {
                        i2 = 0;
                        this.a.edit().putInt(a.i(), 0).apply();
                    }
                }
                i2 = 0;
                int i7 = this.a.getInt(a.j(), i2);
                if (i7 > 0) {
                    try {
                        JSONObject jSONObject5 = new JSONObject();
                        jSONObject5.put(HiAnalyticsConstant.BI_KEY_NET_TYPE, "W");
                        jSONObject5.put("ptc", i7);
                        jSONObject5.put(NavigationBarTable.FIELD_START_TIME, valueOf4);
                        jSONObject5.put(NavigationBarTable.FIELD_END_TIME, valueOf5);
                        String jSONObject6 = jSONObject5.toString();
                        HashMap hashMap5 = new HashMap();
                        hashMap5.put("event", "ptc_event");
                        h("category_lc_ptc", jSONObject6, hashMap5);
                        g.j.a.a.a.c.o("[HB] report ping timeout count events of wifi network.");
                    } catch (Throwable unused3) {
                    }
                    this.a.edit().putInt(a.j(), 0).apply();
                }
                this.f19148h = System.currentTimeMillis();
                this.a.edit().putLong(a.k(), this.f19148h).apply();
            }
        }
    }

    private int a() {
        if (TextUtils.isEmpty(this.f19144c)) {
            return -1;
        }
        try {
            return this.a.getInt(a.b(this.f19144c), -1);
        } catch (Throwable unused) {
            return -1;
        }
    }

    public static n2 c(Context context) {
        if (f19143n == null) {
            synchronized (n2.class) {
                if (f19143n == null) {
                    f19143n = new n2(context);
                }
            }
        }
        return f19143n;
    }

    private void h(String str, String str2, Map<String, String> map) {
        g7 g7Var = new g7();
        g7Var.d(str);
        g7Var.c("hb_name");
        g7Var.a("hb_channel");
        g7Var.a(1L);
        g7Var.b(str2);
        g7Var.a(false);
        g7Var.b(System.currentTimeMillis());
        g7Var.g(this.f19152l.getPackageName());
        g7Var.e("com.xiaomi.xmsf");
        if (map == null) {
            map = new HashMap<>();
        }
        String str3 = null;
        s2 b = t2.b(this.f19152l);
        if (b != null && !TextUtils.isEmpty(b.a)) {
            String[] split = b.a.split(DYConstants.DY_REGEX_AT);
            if (split.length > 0) {
                str3 = split[0];
            }
        }
        map.put("uuid", str3);
        map.put(CustomThemeConstance.NAVI_MODEL, BaseInfo.getDeviceModel());
        Context context = this.f19152l;
        map.put("avc", String.valueOf(y4.b(context, context.getPackageName())));
        map.put("pvc", String.valueOf(50300));
        map.put("cvc", String.valueOf(48));
        g7Var.a(map);
        a7 a2 = a7.a(this.f19152l);
        if (a2 != null) {
            a2.e(g7Var, this.f19152l.getPackageName());
        }
    }

    private void i(boolean z) {
        if (s()) {
            int incrementAndGet = (z ? this.f19146f : this.f19147g).incrementAndGet();
            Object[] objArr = new Object[2];
            objArr[0] = z ? "short" : "long";
            objArr[1] = Integer.valueOf(incrementAndGet);
            g.j.a.a.a.c.y(String.format("[HB] %s ping interval count: %s", objArr));
            if (incrementAndGet >= 5) {
                String f2 = z ? a.f() : a.g();
                int i2 = this.a.getInt(f2, 0) + incrementAndGet;
                this.a.edit().putInt(f2, i2).apply();
                Object[] objArr2 = new Object[2];
                objArr2[0] = z ? "short" : "long";
                objArr2[1] = Integer.valueOf(i2);
                g.j.a.a.a.c.o(String.format("[HB] accumulate %s hb count(%s) and write to file. ", objArr2));
                (z ? this.f19146f : this.f19147g).set(0);
            }
        }
    }

    private boolean j() {
        return this.b.get() >= Math.max(b0.d(this.f19152l).a(h7.IntelligentHeartbeatNATCountInt.a(), 5), 3);
    }

    private boolean k(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return str.startsWith("W-") || str.startsWith("M-");
    }

    /* JADX WARN: Removed duplicated region for block: B:42:0x0039  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void n(java.lang.String r10) {
        /*
            r9 = this;
            java.lang.String r0 = "WIFI-ID-UNKNOWN"
            boolean r0 = r0.equals(r10)
            if (r0 == 0) goto L16
            java.lang.String r10 = r9.f19144c
            if (r10 == 0) goto L15
            java.lang.String r0 = "W-"
            boolean r10 = r10.startsWith(r0)
            if (r10 == 0) goto L15
            goto L18
        L15:
            r10 = 0
        L16:
            r9.f19144c = r10
        L18:
            android.content.SharedPreferences r10 = r9.a
            java.lang.String r0 = r9.f19144c
            java.lang.String r0 = com.xiaomi.push.service.n2.a.b(r0)
            r1 = -1
            int r10 = r10.getInt(r0, r1)
            android.content.SharedPreferences r0 = r9.a
            java.lang.String r2 = r9.f19144c
            java.lang.String r2 = com.xiaomi.push.service.n2.a.d(r2)
            r3 = -1
            long r5 = r0.getLong(r2, r3)
            long r7 = java.lang.System.currentTimeMillis()
            if (r10 == r1) goto L75
            int r10 = (r5 > r3 ? 1 : (r5 == r3 ? 0 : -1))
            if (r10 != 0) goto L56
            android.content.SharedPreferences r10 = r9.a
            android.content.SharedPreferences$Editor r10 = r10.edit()
            java.lang.String r0 = r9.f19144c
            java.lang.String r0 = com.xiaomi.push.service.n2.a.d(r0)
            long r2 = r9.t()
            long r7 = r7 + r2
            android.content.SharedPreferences$Editor r10 = r10.putLong(r0, r7)
        L52:
            r10.apply()
            goto L75
        L56:
            int r10 = (r7 > r5 ? 1 : (r7 == r5 ? 0 : -1))
            if (r10 <= 0) goto L75
            android.content.SharedPreferences r10 = r9.a
            android.content.SharedPreferences$Editor r10 = r10.edit()
            java.lang.String r0 = r9.f19144c
            java.lang.String r0 = com.xiaomi.push.service.n2.a.b(r0)
            android.content.SharedPreferences$Editor r10 = r10.remove(r0)
            java.lang.String r0 = r9.f19144c
            java.lang.String r0 = com.xiaomi.push.service.n2.a.d(r0)
            android.content.SharedPreferences$Editor r10 = r10.remove(r0)
            goto L52
        L75:
            java.util.concurrent.atomic.AtomicInteger r10 = r9.b
            r0 = 0
            r10.getAndSet(r0)
            java.lang.String r10 = r9.f19144c
            boolean r10 = android.text.TextUtils.isEmpty(r10)
            r2 = 1
            if (r10 != 0) goto L8e
            int r10 = r9.a()
            if (r10 == r1) goto L8b
            goto L8e
        L8b:
            r9.d = r2
            goto L90
        L8e:
            r9.d = r0
        L90:
            r10 = 2
            java.lang.Object[] r10 = new java.lang.Object[r10]
            java.lang.String r1 = r9.f19144c
            r10[r0] = r1
            boolean r0 = r9.d
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r0)
            r10[r2] = r0
            java.lang.String r0 = "[HB] network changed, netid:%s, %s"
            java.lang.String r10 = java.lang.String.format(r0, r10)
            g.j.a.a.a.c.o(r10)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.service.n2.n(java.lang.String):void");
    }

    private boolean o() {
        return (TextUtils.isEmpty(this.f19144c) || !this.f19144c.startsWith("M-") || b0.d(this.f19152l).m(h7.IntelligentHeartbeatUseInMobileNetworkBoolean.a(), false)) ? false : true;
    }

    private long p() {
        return this.a.getLong(a.l(), -1L);
    }

    private void r(String str) {
        if (k(str)) {
            this.a.edit().putInt(a.b(str), 235000).apply();
            this.a.edit().putLong(a.d(this.f19144c), System.currentTimeMillis() + t()).apply();
        }
    }

    private boolean s() {
        return w() && b0.d(this.f19152l).m(h7.IntelligentHeartbeatDataCollectSwitchBoolean.a(), true) && m9.China.name().equals(b.a(this.f19152l).b());
    }

    private long t() {
        return b0.d(this.f19152l).c(h7.ShortHeartbeatEffectivePeriodMsLong.a(), 777600000L);
    }

    private void v(String str) {
        String str2;
        String str3;
        if (s() && !TextUtils.isEmpty(str)) {
            if (str.startsWith("W-")) {
                str2 = "W";
            } else if (!str.startsWith("M-")) {
                return;
            } else {
                str2 = "M";
            }
            String valueOf = String.valueOf(235000);
            String valueOf2 = String.valueOf(System.currentTimeMillis() / 1000);
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            sb.append(":::");
            sb.append(str2);
            sb.append(":::");
            sb.append(valueOf);
            sb.append(":::");
            sb.append(valueOf2);
            String string = this.a.getString(a.h(), null);
            if (TextUtils.isEmpty(string)) {
                str3 = sb.toString();
            } else {
                str3 = string + "###" + sb.toString();
            }
            this.a.edit().putString(a.h(), str3).apply();
        }
    }

    private boolean w() {
        return this.f19150j && (this.f19149i || ((p() > System.currentTimeMillis() ? 1 : (p() == System.currentTimeMillis() ? 0 : -1)) >= 0));
    }

    private void x() {
        if (this.a.getBoolean(a.a(), false)) {
            return;
        }
        this.a.edit().putBoolean(a.a(), true).apply();
    }

    private boolean y() {
        long j2 = this.a.getLong(a.e(), -1L);
        if (j2 == -1) {
            return false;
        }
        long currentTimeMillis = System.currentTimeMillis();
        return j2 > currentTimeMillis || currentTimeMillis - j2 >= 259200000;
    }

    private void z() {
        int i2 = this.f19151k;
        String j2 = i2 != 0 ? i2 != 1 ? null : a.j() : a.i();
        if (TextUtils.isEmpty(j2)) {
            return;
        }
        if (this.a.getLong(a.k(), -1L) == -1) {
            this.f19148h = System.currentTimeMillis();
            this.a.edit().putLong(a.k(), this.f19148h).apply();
        }
        this.a.edit().putInt(j2, this.a.getInt(j2, 0) + 1).apply();
    }

    public long b() {
        int a2;
        long f2 = u5.f();
        if (this.f19150j && !o() && ((b0.d(this.f19152l).m(h7.IntelligentHeartbeatSwitchBoolean.a(), true) || p() >= System.currentTimeMillis()) && (a2 = a()) != -1)) {
            f2 = a2;
        }
        if (!TextUtils.isEmpty(this.f19144c) && !"WIFI-ID-UNKNOWN".equals(this.f19144c) && this.f19151k == 1) {
            i(f2 < 300000);
        }
        this.f19153m = f2;
        g.j.a.a.a.c.o("[HB] ping interval:" + f2);
        return f2;
    }

    public void d() {
    }

    public void e(int i2) {
        this.a.edit().putLong(a.l(), System.currentTimeMillis() + (i2 * 1000)).apply();
    }

    public synchronized void f(NetworkInfo networkInfo) {
        if (w()) {
            String str = null;
            if (networkInfo == null) {
                n(null);
            } else if (networkInfo.getType() == 0) {
                String subtypeName = networkInfo.getSubtypeName();
                if (!TextUtils.isEmpty(subtypeName) && !"UNKNOWN".equalsIgnoreCase(subtypeName)) {
                    str = "M-" + subtypeName;
                }
                n(str);
                this.f19151k = 0;
            } else {
                if (networkInfo.getType() != 1 && networkInfo.getType() != 6) {
                    n(null);
                }
                n("WIFI-ID-UNKNOWN");
                this.f19151k = 1;
            }
            this.f19151k = -1;
        }
    }

    public synchronized void g(String str) {
        if (!TextUtils.isEmpty(str)) {
            x();
        }
        if (w() && !TextUtils.isEmpty(str)) {
            n("W-" + str);
        }
    }

    public long l() {
        return this.f19153m;
    }

    public void m() {
        if (w()) {
            z();
            if (this.d && !TextUtils.isEmpty(this.f19144c) && this.f19144c.equals(this.f19145e)) {
                this.b.getAndIncrement();
                g.j.a.a.a.c.o("[HB] ping timeout count:" + this.b);
                if (j()) {
                    g.j.a.a.a.c.o("[HB] change hb interval for net:" + this.f19144c);
                    r(this.f19144c);
                    this.d = false;
                    this.b.getAndSet(0);
                    v(this.f19144c);
                }
            }
        }
    }

    public void q() {
        if (w()) {
            this.f19145e = this.f19144c;
        }
    }

    public void u() {
        if (w()) {
            B();
            if (this.d) {
                this.b.getAndSet(0);
            }
        }
    }
}

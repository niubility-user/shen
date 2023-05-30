package com.jingdong.sdk.jdcrashreport;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Process;
import android.text.TextUtils;
import com.jingdong.sdk.jdcrashreport.JDCrashReportConfig;
import com.jingdong.sdk.jdcrashreport.b.c;
import com.jingdong.sdk.jdcrashreport.b.e;
import com.jingdong.sdk.jdcrashreport.b.f;
import com.jingdong.sdk.jdcrashreport.b.g;
import com.jingdong.sdk.jdcrashreport.b.i;
import com.jingdong.sdk.jdcrashreport.b.q;
import com.jingdong.sdk.jdcrashreport.b.r;
import com.jingdong.sdk.jdcrashreport.b.t;
import com.jingdong.sdk.jdcrashreport.b.v;
import com.jingdong.sdk.jdcrashreport.b.w;
import com.jingdong.sdk.jdcrashreport.b.y;
import com.jingdong.sdk.jdcrashreport.crash.jni.NativeMonitor;
import com.jingdong.sdk.jdcrashreport.recover.b;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import org.json.JSONObject;

/* loaded from: classes7.dex */
public class d {
    private static JDCrashReportConfig a;
    private static com.jingdong.sdk.jdcrashreport.common.a b;

    /* renamed from: c */
    private static com.jingdong.sdk.jdcrashreport.e.c.a f14902c;
    private static g d;

    /* renamed from: e */
    private static com.jingdong.sdk.jdcrashreport.a f14903e;

    /* renamed from: f */
    private static boolean f14904f;

    /* renamed from: g */
    private static HashMap<String, Boolean> f14905g = new HashMap<>();

    public static JDCrashReportConfig A() {
        return a;
    }

    public static void B(String str) {
        JDCrashReportConfig jDCrashReportConfig = a;
        if (jDCrashReportConfig != null) {
            jDCrashReportConfig.b(str);
            return;
        }
        throw new NullPointerException("JDCrashReportConfig is null, please check init code!");
    }

    public static void C(String str) {
        t.g(str);
    }

    public static boolean D() {
        StringBuilder sb = new StringBuilder();
        sb.append(I().getPackageName());
        sb.append(":jdcrashreport");
        return sb.toString().equals(com.jingdong.sdk.jdcrashreport.b.c.a(Process.myPid()));
    }

    public static boolean E() {
        return a.m();
    }

    public static boolean F() {
        return a.l();
    }

    public static com.jingdong.sdk.jdcrashreport.common.a G() {
        if (b == null) {
            try {
                b = com.jingdong.sdk.jdcrashreport.common.a.a(new JSONObject(i.f("STRATEGY", "")));
            } catch (Throwable unused) {
                b = new com.jingdong.sdk.jdcrashreport.common.a();
            }
        }
        return b;
    }

    static void H() {
        if (!f14904f) {
            r.h("JDCrashReport", "Not Call init Yet!");
        } else if (com.jingdong.sdk.jdcrashreport.b.c.m(I())) {
            w.c(e.a(), J()).b(com.jingdong.sdk.jdcrashreport.crash.jni.a.a()).b(f.a()).h();
        }
    }

    public static Context I() {
        JDCrashReportConfig jDCrashReportConfig = a;
        if (jDCrashReportConfig != null) {
            return jDCrashReportConfig.a();
        }
        throw new NullPointerException("JDCrashReportConfig is null, please check init code!");
    }

    public static long J() {
        JDCrashReportConfig jDCrashReportConfig = a;
        if (jDCrashReportConfig == null) {
            return 60000L;
        }
        return jDCrashReportConfig.n();
    }

    public static String K() {
        JDCrashReportConfig jDCrashReportConfig = a;
        if (jDCrashReportConfig == null) {
            return null;
        }
        return TextUtils.isEmpty(jDCrashReportConfig.e()) ? "" : a.e();
    }

    public static String L() {
        JDCrashReportConfig jDCrashReportConfig = a;
        return (jDCrashReportConfig == null || TextUtils.isEmpty(jDCrashReportConfig.f())) ? "" : a.f();
    }

    public static int M() {
        JDCrashReportConfig jDCrashReportConfig = a;
        if (jDCrashReportConfig == null) {
            return -1;
        }
        return jDCrashReportConfig.g();
    }

    public static String N() {
        JDCrashReportConfig jDCrashReportConfig = a;
        return (jDCrashReportConfig == null || TextUtils.isEmpty(jDCrashReportConfig.h())) ? "" : a.h();
    }

    public static String O() {
        JDCrashReportConfig jDCrashReportConfig = a;
        return (jDCrashReportConfig == null || TextUtils.isEmpty(jDCrashReportConfig.getDeviceUniqueId())) ? "" : a.getDeviceUniqueId();
    }

    public static String P() {
        JDCrashReportConfig jDCrashReportConfig = a;
        return (jDCrashReportConfig == null || TextUtils.isEmpty(jDCrashReportConfig.getUserId())) ? "" : a.getUserId();
    }

    public static String Q() {
        JDCrashReportConfig jDCrashReportConfig = a;
        return (jDCrashReportConfig == null || TextUtils.isEmpty(jDCrashReportConfig.getUts())) ? "" : a.getUts();
    }

    public static ArrayList<String> R() {
        JDCrashReportConfig jDCrashReportConfig = a;
        if (jDCrashReportConfig == null) {
            return new ArrayList<>();
        }
        return jDCrashReportConfig.i();
    }

    public static boolean S() {
        JDCrashReportConfig jDCrashReportConfig = a;
        if (jDCrashReportConfig != null) {
            return jDCrashReportConfig.b();
        }
        return false;
    }

    public static boolean T() {
        JDCrashReportConfig jDCrashReportConfig = a;
        if (jDCrashReportConfig != null) {
            return jDCrashReportConfig.c();
        }
        return true;
    }

    public static String U() {
        JDCrashReportConfig.c x;
        JDCrashReportConfig jDCrashReportConfig = a;
        return (jDCrashReportConfig == null || (x = jDCrashReportConfig.x()) == null) ? "1" : x.a();
    }

    public static List<Pattern> V() {
        JDCrashReportConfig jDCrashReportConfig = a;
        if (jDCrashReportConfig != null) {
            return jDCrashReportConfig.o();
        }
        throw new NullPointerException("JDCrashReportConfig is null");
    }

    public static long W() {
        JDCrashReportConfig jDCrashReportConfig = a;
        if (jDCrashReportConfig == null) {
            return 0L;
        }
        return jDCrashReportConfig.k();
    }

    public static long X() {
        JDCrashReportConfig jDCrashReportConfig = a;
        if (jDCrashReportConfig == null) {
            return 0L;
        }
        return jDCrashReportConfig.j();
    }

    public static JDCrashReportListener Y() {
        return d;
    }

    public static com.jingdong.sdk.jdcrashreport.a Z() {
        return f14903e;
    }

    public static boolean a() {
        JDCrashReportConfig jDCrashReportConfig = a;
        return jDCrashReportConfig != null && jDCrashReportConfig.p();
    }

    public static boolean b() {
        JDCrashReportConfig jDCrashReportConfig = a;
        return jDCrashReportConfig != null && jDCrashReportConfig.u();
    }

    public static Class<? extends Activity> c() {
        JDCrashReportConfig jDCrashReportConfig = a;
        if (jDCrashReportConfig == null) {
            return null;
        }
        return jDCrashReportConfig.r();
    }

    public static List<Class<? extends Activity>> d() {
        JDCrashReportConfig jDCrashReportConfig = a;
        if (jDCrashReportConfig == null) {
            return new ArrayList();
        }
        return jDCrashReportConfig.t();
    }

    public static JDCrashReportConfig.e.a e() {
        JDCrashReportConfig jDCrashReportConfig = a;
        if (jDCrashReportConfig == null) {
            return null;
        }
        return jDCrashReportConfig.s();
    }

    public static b f() {
        JDCrashReportConfig jDCrashReportConfig = a;
        if (jDCrashReportConfig != null && jDCrashReportConfig.q() != null) {
            return a.q();
        }
        r.h("JDCrashReport", "Get Custom Recovery Error! Use Default Recovery Instead.");
        return new com.jingdong.sdk.jdcrashreport.recover.d();
    }

    public static boolean g() {
        JDCrashReportConfig jDCrashReportConfig = a;
        if (jDCrashReportConfig != null && jDCrashReportConfig.v() != null) {
            try {
                return a.v().a();
            } catch (Throwable unused) {
            }
        }
        return false;
    }

    private static void h() {
        HashMap<String, Boolean> hashMap = f14905g;
        Boolean bool = Boolean.FALSE;
        hashMap.put("native", bool);
        f14905g.put("java", bool);
        f14905g.put("anr", bool);
    }

    private static void i() {
        com.jingdong.sdk.jdcrashreport.e.a.b.a().b(I());
    }

    private static void j() {
        if (f14902c == null) {
            f14902c = new com.jingdong.sdk.jdcrashreport.e.c.a();
        }
        f14902c.b();
    }

    public static JDCrashReportConfig.f k(String str, String str2) {
        JDCrashReportConfig jDCrashReportConfig = a;
        if (jDCrashReportConfig != null && jDCrashReportConfig.w() != null) {
            try {
                JDCrashReportConfig.f b2 = a.w().b(str, str2);
                return b2 != null ? b2 : JDCrashReportConfig.f.DEFAULT;
            } catch (Throwable unused) {
                return JDCrashReportConfig.f.DEFAULT;
            }
        }
        return JDCrashReportConfig.f.DEFAULT;
    }

    public static void m(com.jingdong.sdk.jdcrashreport.a aVar) {
        f14903e = aVar;
    }

    public static synchronized void n(JDCrashReportConfig jDCrashReportConfig) {
        synchronized (d.class) {
            if (f14904f) {
                return;
            }
            a = jDCrashReportConfig;
            r.b("JDCrashReport", "config.deviceId: " + jDCrashReportConfig.getDeviceUniqueId());
            d = new g();
            c.d(a);
            h();
            if (D()) {
                f14904f = true;
                return;
            }
            i.c();
            t.c();
            q.b(jDCrashReportConfig.a());
            j();
            if (F()) {
                i();
            }
            if (Build.VERSION.SDK_INT >= 21) {
                v(E(), F());
            } else {
                v(E(), false);
            }
            f14904f = true;
            r.b("JDCrashReport", "UV enable: " + T());
            if (T()) {
                y.b(G().a);
            }
            com.jingdong.sdk.jdcrashreport.b.c.h(new a());
            H();
            if (i.b("APP_VERSION_CODE", 0L) != a.g()) {
                i.e().putLong("APP_VERSION_CODE", jDCrashReportConfig.g()).putInt("crash_times", 0).apply();
            }
        }
    }

    @Deprecated
    public static void o(JDCrashReportListener jDCrashReportListener) {
    }

    public static void p(Class<? extends Activity> cls) {
        JDCrashReportConfig jDCrashReportConfig = a;
        if (jDCrashReportConfig != null) {
            jDCrashReportConfig.a(cls);
            return;
        }
        throw new NullPointerException("JDCrashReportConfig is null, please check init code!");
    }

    public static void q(String str) {
        JDCrashReportConfig jDCrashReportConfig = a;
        if (jDCrashReportConfig != null) {
            jDCrashReportConfig.a(str);
            return;
        }
        throw new NullPointerException("JDCrashReportConfig is null, please check init code!");
    }

    public static void r(String str, String str2, String str3, Throwable th) {
        if (G().f14900c <= 0) {
            return;
        }
        com.jingdong.sdk.jdcrashreport.e.b.e.c().d(str, str2, str3, th);
    }

    public static void s(String str, boolean z) {
        if (f14905g.containsKey(str)) {
            f14905g.put(str, Boolean.valueOf(z));
        }
    }

    public static void t(Throwable th, String str) {
        if (G().b <= 0) {
            return;
        }
        com.jingdong.sdk.jdcrashreport.e.b.c.c().d(th, str);
    }

    public static void u(Throwable th, String str, String str2, Map<String, String> map) {
        if (G().d <= 0) {
            return;
        }
        com.jingdong.sdk.jdcrashreport.e.b.d.c().d(th, str, str2, map);
    }

    private static void v(boolean z, boolean z2) {
        NativeMonitor.a().a(I(), L(), z, z2);
        String str = "init native crash handler end: nativeEnable = " + z + ", anrEnable = " + z2;
    }

    public static boolean w() {
        return f14904f;
    }

    public static String x() {
        return (f14905g.get("native") == null || !f14905g.get("native").booleanValue()) ? (f14905g.get("java") == null || !f14905g.get("java").booleanValue()) ? (f14905g.get("anr") == null || !f14905g.get("anr").booleanValue()) ? "" : "anr" : "java" : "native";
    }

    public static void y(String str) {
        JDCrashReportConfig jDCrashReportConfig = a;
        if (jDCrashReportConfig != null) {
            jDCrashReportConfig.c(str);
            return;
        }
        throw new NullPointerException("JDCrashReportConfig is null, please check init code!");
    }

    public static void z(String str, String str2) {
        JDCrashReportConfig jDCrashReportConfig = a;
        if (jDCrashReportConfig == null || jDCrashReportConfig.w() == null) {
            return;
        }
        try {
            a.w().a(str, str2);
        } catch (Throwable unused) {
        }
    }

    /* loaded from: classes7.dex */
    public class a implements c.b {
        a() {
        }

        @Override // com.jingdong.sdk.jdcrashreport.b.c.b
        public void a() {
            r.b("JDCrashReport", "onAppSwitch2Foreground");
            if (d.T()) {
                y.a();
            }
            v.a(new C0717a(this));
        }

        @Override // com.jingdong.sdk.jdcrashreport.b.c.b
        public void b() {
            r.b("JDCrashReport", "onAppSwitch2Background");
            if (d.T()) {
                y.c();
            }
        }

        /* renamed from: com.jingdong.sdk.jdcrashreport.d$a$a */
        /* loaded from: classes7.dex */
        class C0717a implements v.b {
            C0717a(a aVar) {
            }

            @Override // com.jingdong.sdk.jdcrashreport.b.v.b
            public void a(JSONObject jSONObject) {
                r.b("JDCrashReport", "Strategy onResult:" + jSONObject);
                if (jSONObject != null) {
                    i.d("STRATEGY", jSONObject.toString());
                    com.jingdong.sdk.jdcrashreport.common.a unused = d.b = com.jingdong.sdk.jdcrashreport.common.a.a(jSONObject);
                    com.jingdong.sdk.jdcrashreport.e.b.c.c().a(d.G().b);
                    com.jingdong.sdk.jdcrashreport.e.b.e.c().a(d.G().f14900c);
                    com.jingdong.sdk.jdcrashreport.e.b.d.c().a(d.G().d);
                }
                if (d.T()) {
                    y.b(d.G().a);
                }
            }

            @Override // com.jingdong.sdk.jdcrashreport.b.v.b
            public void a(String str) {
                r.b("JDCrashReport", "Strategy onFailed:" + str);
                if (d.T()) {
                    y.b(d.G().a);
                }
            }
        }
    }
}

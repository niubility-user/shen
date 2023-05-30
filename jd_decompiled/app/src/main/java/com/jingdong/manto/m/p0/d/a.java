package com.jingdong.manto.m.p0.d;

import android.util.Pair;
import com.jingdong.manto.AppLifeCycle;
import com.jingdong.manto.h;
import com.jingdong.manto.utils.MantoProcessUtil;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes15.dex */
public class a {
    private static int a = 180000;
    private static int b = 181000;

    /* renamed from: c */
    private static int f13504c = 182000;
    private static int d = 183000;

    /* renamed from: e */
    private static int f13505e = 184000;

    /* renamed from: f */
    private static int f13506f = 185000;

    /* renamed from: g */
    private static int f13507g = 186000;

    /* renamed from: h */
    private static Map<String, b> f13508h = new HashMap();

    /* renamed from: i */
    private static AtomicInteger f13509i = new AtomicInteger();

    /* renamed from: com.jingdong.manto.m.p0.d.a$a */
    /* loaded from: classes15.dex */
    public class C0591a extends AppLifeCycle.Listener {
        final /* synthetic */ String a;

        C0591a(String str) {
            this.a = str;
        }

        @Override // com.jingdong.manto.AppLifeCycle.Listener
        public void onAppDestroy() {
            b bVar = (b) a.f13508h.get(this.a);
            if (bVar != null) {
                bVar.l();
            }
            a.f13508h.remove(this.a);
            AppLifeCycle.remove(this.a, this);
        }
    }

    public static Pair<Boolean, String> a(h hVar, String str, String str2, String str3, String str4, String str5, String str6, String str7, int i2) {
        return a(hVar.a()).a(hVar, str, str2, str3, str4, str5, str6, str7, i2);
    }

    private static synchronized b a(String str) {
        b bVar;
        synchronized (a.class) {
            bVar = f13508h.get(str);
            if (bVar == null) {
                String processName = MantoProcessUtil.getProcessName();
                int i2 = f13507g;
                if (processName.contains(":manto0")) {
                    i2 = a;
                } else if (processName.contains(":manto1")) {
                    i2 = b;
                } else if (processName.contains(":manto2")) {
                    i2 = f13504c;
                } else if (processName.contains(":manto3")) {
                    i2 = d;
                } else if (processName.contains(":manto4")) {
                    i2 = f13505e;
                } else if (processName.contains(":mantoProcess")) {
                    i2 = f13506f;
                }
                bVar = new b(i2 + f13509i.incrementAndGet());
                f13508h.put(str, bVar);
                AppLifeCycle.add(str, new C0591a(str));
            }
        }
        return bVar;
    }

    public static Map a(h hVar) {
        b a2 = a(hVar.a());
        a2.a(hVar);
        return a2.b();
    }

    public static void a(h hVar, double d2) {
        b a2 = a(hVar.a());
        a2.a(hVar);
        a2.a(d2);
    }

    public static void b() {
        for (b bVar : f13508h.values()) {
            if (bVar != null) {
                bVar.c();
            }
        }
    }

    public static void b(h hVar) {
        b a2 = a(hVar.a());
        a2.a(hVar);
        a2.i();
    }

    public static void b(String str) {
        a(str).e();
    }

    public static void c() {
        for (b bVar : f13508h.values()) {
            if (bVar != null) {
                bVar.d();
            }
        }
    }

    public static void c(h hVar) {
        b a2 = a(hVar.a());
        a2.a(hVar);
        a2.j();
    }

    public static void c(String str) {
        a(str).f();
    }

    public static void d(h hVar) {
        b a2 = a(hVar.a());
        a2.a(hVar);
        a2.l();
    }

    public static void d(String str) {
        a(str).g();
    }

    public static void e(String str) {
        a(str).h();
    }
}

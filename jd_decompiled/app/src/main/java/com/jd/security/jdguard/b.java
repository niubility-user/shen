package com.jd.security.jdguard;

import com.jd.security.jdguard.core.e;
import com.jd.security.jdguard.e.d;
import java.util.Locale;

/* loaded from: classes.dex */
public class b {
    private static boolean a = h();
    private static e b;

    /* renamed from: c */
    private static com.jd.security.jdguard.e.c f6878c;

    private static void a(c cVar) {
        if (b == null) {
            synchronized (b.class) {
                if (b == null) {
                    f6878c = new d(cVar.f());
                    e x = e.x(cVar);
                    b = x;
                    x.z(f6878c);
                }
            }
        }
    }

    public static byte[] b(byte[] bArr) throws Exception {
        if (e() != null && (e() == null || !e().i())) {
            if (i()) {
                return b.r(bArr, 0);
            }
            return null;
        }
        throw new Exception("JDGuard is disabled");
    }

    public static String c() {
        if (e() == null || ((e() != null && e().i()) || !i())) {
            return null;
        }
        return b.v();
    }

    public static e d() {
        return b;
    }

    public static c e() {
        if (d() == null) {
            return null;
        }
        return d().e();
    }

    private static void f() {
        if (e() != null) {
            if (e() == null || !e().i()) {
                synchronized (b.class) {
                    if (i()) {
                        b.k();
                    }
                }
            }
        }
    }

    public static void g(c cVar) {
        long currentTimeMillis = System.currentTimeMillis();
        if (cVar == null) {
            return;
        }
        if (!a) {
            h();
        }
        a(cVar);
        f();
        com.jd.security.jdguard.e.c cVar2 = f6878c;
        if (cVar2 != null) {
            cVar2.d(-1103, System.currentTimeMillis() - currentTimeMillis);
        }
    }

    private static boolean h() {
        try {
            System.loadLibrary("jdg".toLowerCase(Locale.getDefault()));
            return true;
        } catch (Throwable th) {
            com.jd.security.jdguard.f.d.e(th);
            return false;
        }
    }

    private static boolean i() {
        if (!a) {
            boolean h2 = h();
            a = h2;
            if (!h2) {
                com.jd.security.jdguard.f.d.e(new RuntimeException("JDGuard not init, load library failed."));
                return false;
            }
        }
        if (b == null) {
            com.jd.security.jdguard.f.d.e(new RuntimeException("JDGuard not init, call JDGuard.init first."));
            return false;
        }
        return true;
    }
}

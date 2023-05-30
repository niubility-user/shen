package g.j.a.a.a;

import android.content.Context;
import android.os.Process;
import com.xiaomi.push.a8;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes11.dex */
public abstract class c {
    private static int a = 2;
    private static boolean b;

    /* renamed from: c */
    private static boolean f19656c;
    private static String d = "XMPush-" + Process.myPid();

    /* renamed from: e */
    private static g.j.a.a.a.a f19657e = new a();

    /* renamed from: f */
    private static final HashMap<Integer, Long> f19658f = new HashMap<>();

    /* renamed from: g */
    private static final HashMap<Integer, String> f19659g = new HashMap<>();

    /* renamed from: h */
    private static final Integer f19660h = -1;

    /* renamed from: i */
    private static AtomicInteger f19661i = new AtomicInteger(1);

    /* loaded from: classes11.dex */
    static class a implements g.j.a.a.a.a {
        private String a = c.d;

        a() {
        }

        @Override // g.j.a.a.a.a
        public void a(String str, Throwable th) {
        }

        @Override // g.j.a.a.a.a
        public void log(String str) {
        }
    }

    public static void A(String str, Object... objArr) {
        if (v(1)) {
            i(1, f(str, objArr));
        }
    }

    public static void B(String str) {
        if (v(0)) {
            i(1, d(str));
        }
    }

    public static void C(String str, String str2) {
        if (!b) {
            x(str, str2);
            if (f19656c) {
                return;
            }
        }
        p(str, str2);
    }

    public static void D(String str) {
        if (v(4)) {
            i(4, d(str));
        }
    }

    public static void E(String str) {
        if (!b) {
            d(str);
            if (f19656c) {
                return;
            }
        }
        o(str);
    }

    public static int a() {
        return a;
    }

    public static Integer b(String str) {
        if (a <= 1) {
            Integer valueOf = Integer.valueOf(f19661i.incrementAndGet());
            f19658f.put(valueOf, Long.valueOf(System.currentTimeMillis()));
            f19659g.put(valueOf, str);
            f19657e.log(str + " starts");
            return valueOf;
        }
        return f19660h;
    }

    private static String d(String str) {
        return w() + str;
    }

    public static String e(String str, String str2) {
        return "[" + str + "] " + str2;
    }

    private static String f(String str, Object... objArr) {
        StringBuilder sb = new StringBuilder();
        sb.append("[Tid:");
        sb.append(Thread.currentThread().getId());
        sb.append("] ");
        sb.append("[");
        sb.append(str);
        sb.append("] ");
        if (objArr != null && objArr.length > 0) {
            for (Object obj : objArr) {
                sb.append(obj);
            }
        }
        return sb.toString();
    }

    private static String g(Object... objArr) {
        StringBuilder sb = new StringBuilder();
        sb.append("[Tid:");
        sb.append(Thread.currentThread().getId());
        sb.append("] ");
        if (objArr != null && objArr.length > 0) {
            for (Object obj : objArr) {
                sb.append(obj);
            }
        }
        return sb.toString();
    }

    public static void h(int i2) {
        if (i2 < 0 || i2 > 5) {
            i(2, "set log level as " + i2);
        }
        a = i2;
    }

    public static void i(int i2, String str) {
        if (i2 >= a) {
            f19657e.log(str);
        } else if (u()) {
            String str2 = "-->log(" + i2 + "): " + str;
        }
    }

    public static void j(int i2, String str, Throwable th) {
        if (i2 >= a) {
            f19657e.a(str, th);
        } else if (u()) {
            String str2 = "-->log(" + i2 + "): " + str;
        }
    }

    public static void k(int i2, Throwable th) {
        if (i2 >= a) {
            f19657e.a("", th);
        } else if (u()) {
            String str = "-->log(" + i2 + "): ";
        }
    }

    public static void l(Context context) {
        if (a8.j(context)) {
            b = true;
        }
        if (a8.i()) {
            f19656c = true;
        }
    }

    public static void m(g.j.a.a.a.a aVar) {
        f19657e = aVar;
    }

    public static void n(Integer num) {
        if (a <= 1) {
            HashMap<Integer, Long> hashMap = f19658f;
            if (hashMap.containsKey(num)) {
                long currentTimeMillis = System.currentTimeMillis() - hashMap.remove(num).longValue();
                f19657e.log(f19659g.remove(num) + " ends in " + currentTimeMillis + " ms");
            }
        }
    }

    public static void o(String str) {
        if (v(2)) {
            i(2, d(str));
        }
    }

    public static void p(String str, String str2) {
        if (v(2)) {
            i(2, x(str, str2));
        }
    }

    public static void q(String str, Throwable th) {
        if (v(4)) {
            j(4, d(str), th);
        }
    }

    public static void r(String str, Object... objArr) {
        if (v(2)) {
            i(2, f(str, objArr));
        }
    }

    public static void s(Throwable th) {
        if (v(4)) {
            k(4, th);
        }
    }

    public static void t(Object... objArr) {
        if (v(4)) {
            i(4, g(objArr));
        }
    }

    private static boolean u() {
        return false;
    }

    private static boolean v(int i2) {
        return i2 >= a || u();
    }

    private static String w() {
        return "[Tid:" + Thread.currentThread().getId() + "] ";
    }

    private static String x(String str, String str2) {
        return w() + e(str, str2);
    }

    public static void y(String str) {
        if (v(0)) {
            i(0, d(str));
        }
    }

    public static void z(String str, String str2) {
        if (v(1)) {
            i(1, x(str, str2));
        }
    }
}

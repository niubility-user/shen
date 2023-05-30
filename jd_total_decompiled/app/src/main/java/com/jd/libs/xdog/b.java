package com.jd.libs.xdog;

import android.content.Context;
import android.view.ViewGroup;
import java.util.Map;

/* loaded from: classes16.dex */
public class b {
    public static void a() {
        f.b().h();
    }

    public static void b(Class<? extends e> cls) {
        f.b().i(cls);
    }

    public static void c(ViewGroup viewGroup, Context context, String str) {
        f.b().j(viewGroup, context, str);
    }

    public static void d(String str, Integer num, String str2, Integer num2) {
        f.b().m(str, num, str2, num2);
    }

    public static void e(String str, String str2, String str3) {
        f.b().n(str, str2, str3);
    }

    public static void f(String str, String str2) {
        f.b().o(str, str2);
    }

    public static synchronized void g(String str) {
        synchronized (b.class) {
            f.b().p(str);
        }
    }

    public static synchronized void h(String str) {
        synchronized (b.class) {
            f.b().r(str);
        }
    }

    public static void i(String str, Integer num, String str2) {
        f.b().t(str, num, str2);
    }

    public static synchronized void j(String str, String str2, String str3, String str4) {
        synchronized (b.class) {
            f.b().u(str, str2, str3, str4);
        }
    }

    public static void k(String str, String str2, String str3) {
        f.b().v(str, str2, str3);
    }

    public static void l(String str, String str2) {
        f.b().w(str, str2);
    }

    public static void m(String str, Map<String, String> map) {
        f.b().x(str, map);
    }
}

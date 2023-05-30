package com.jd.jdsec.c;

import android.app.Application;
import android.content.Context;

/* loaded from: classes13.dex */
public class g {
    public static Context a = null;
    public static Application b = null;

    /* renamed from: c */
    private static String f2744c = null;
    private static String d = null;

    /* renamed from: e */
    private static String f2745e = null;

    /* renamed from: f */
    private static f f2746f = null;

    /* renamed from: g */
    private static a f2747g = null;

    /* renamed from: h */
    private static boolean f2748h = true;

    /* renamed from: i */
    private static b f2749i;

    public static String a() {
        String str = f2744c;
        return str == null ? "" : str;
    }

    public static void b(Context context) {
        if (a == null) {
            if (context instanceof Application) {
                b = (Application) context;
                a = context;
            } else if (context != null) {
                Context applicationContext = context.getApplicationContext();
                a = applicationContext;
                if (applicationContext instanceof Application) {
                    b = (Application) applicationContext;
                }
            }
            Application application = b;
            if (application != null) {
                com.jd.jdsec.a.d.b(application);
            }
        }
        if (com.jd.jdsec.b.c.b != null || context == null) {
            return;
        }
        com.jd.jdsec.b.c.b = context.getApplicationContext();
    }

    public static void c(e eVar) {
        if (eVar != null) {
            f2744c = eVar.a();
            d = eVar.e();
            f2745e = eVar.d();
            f2749i = eVar.c();
            f2746f = eVar.f();
            f2747g = eVar.b();
        }
    }

    public static void d(boolean z) {
        f2748h = z;
    }

    public static a e() {
        a aVar = f2747g;
        if (aVar == null) {
            return null;
        }
        return aVar;
    }

    public static b f() {
        return f2749i;
    }

    public static String g() {
        String str = f2745e;
        return str == null ? "" : str;
    }

    public static f h() {
        f fVar = f2746f;
        if (fVar == null) {
            return null;
        }
        return fVar;
    }

    public static String i() {
        String str = d;
        return str == null ? "" : str;
    }

    public static boolean j() {
        return f2748h;
    }

    public static boolean k() {
        try {
            boolean z = f() != null && f().isOpen();
            String str = "isOpen = " + z;
            return true ^ z;
        } catch (Exception e2) {
            e2.printStackTrace();
            return true;
        }
    }
}

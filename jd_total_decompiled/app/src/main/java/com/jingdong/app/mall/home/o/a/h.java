package com.jingdong.app.mall.home.o.a;

import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes4.dex */
public class h {
    private static final AtomicBoolean a = new AtomicBoolean(false);
    private static final AtomicBoolean b = new AtomicBoolean(false);

    /* renamed from: c  reason: collision with root package name */
    private static String f10476c;

    public static String a() {
        return f10476c;
    }

    public static boolean b() {
        return b.get() || com.jingdong.app.mall.home.floor.common.i.g.f9303m;
    }

    public static boolean c() {
        return a.get() || com.jingdong.app.mall.home.floor.common.i.g.f9303m;
    }

    public static boolean d() {
        return a.get() && com.jingdong.app.mall.home.floor.common.i.g.c();
    }

    private static void e(String str) {
        f10476c = str;
        b.set(true);
        a.set(true);
        com.jingdong.app.mall.home.r.c.d b2 = com.jingdong.app.mall.home.r.c.d.b("Home_AutoXVIEWEffect");
        b2.a("type", str);
        b2.d();
        k.e("Check Dialog >>> showDialog Tag is" + str);
    }

    public static void f() {
        b.set(true);
    }

    public static void g() {
        e("6");
    }

    public static void h() {
        e("3");
    }

    public static void i() {
        e("2");
    }

    public static void j() {
        a.set(true);
        k.e("Check Dialog >>> showLaunchXView");
    }

    public static void k() {
        a.set(true);
        k.e("Check Dialog >>> showLaunchXView2");
    }

    public static void l() {
        e("1");
    }

    public static void m() {
        e("0");
    }

    public static void n() {
        e("5");
    }

    public static void o() {
        e("4");
    }

    public static void p() {
        if (com.jingdong.app.mall.home.floor.common.i.g.f9302l) {
            return;
        }
        e("7");
    }

    public static void q() {
    }
}

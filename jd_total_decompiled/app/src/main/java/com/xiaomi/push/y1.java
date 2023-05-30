package com.xiaomi.push;

import android.content.Context;

/* loaded from: classes11.dex */
public class y1 {
    private static m1 a;
    private static o1 b;

    public static void a(Context context) {
        c("onSendMsg");
        if (g(context)) {
            b2.g(context, System.currentTimeMillis(), d(context));
        }
    }

    public static void b(Context context, o5 o5Var) {
        if (g(context)) {
            if (a == null) {
                a = new m1(context);
            }
            if (b == null) {
                b = new o1(context);
            }
            m1 m1Var = a;
            o5Var.k(m1Var, m1Var);
            o1 o1Var = b;
            o5Var.z(o1Var, o1Var);
            c("startStats");
        }
    }

    public static void c(String str) {
        l1.b("Push-PowerStats", str);
    }

    public static boolean d(Context context) {
        return z6.p(context);
    }

    public static void e(Context context) {
        c("onReceiveMsg");
        if (g(context)) {
            b2.k(context, System.currentTimeMillis(), d(context));
        }
    }

    public static void f(Context context, o5 o5Var) {
        m1 m1Var = a;
        if (m1Var != null) {
            o5Var.j(m1Var);
            a = null;
        }
        o1 o1Var = b;
        if (o1Var != null) {
            o5Var.y(o1Var);
            b = null;
        }
        c("stopStats");
    }

    private static boolean g(Context context) {
        return l1.c(context);
    }

    public static void h(Context context) {
        c("onPing");
        if (g(context)) {
            b2.l(context, System.currentTimeMillis(), d(context));
        }
    }

    public static void i(Context context) {
        c("onPong");
        if (g(context)) {
            b2.m(context, System.currentTimeMillis(), d(context));
        }
    }
}

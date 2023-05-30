package com.xiaomi.push;

/* loaded from: classes11.dex */
public class c9 {
    private static int a = Integer.MAX_VALUE;

    public static void a(y8 y8Var, byte b) {
        b(y8Var, b, a);
    }

    public static void b(y8 y8Var, byte b, int i2) {
        if (i2 <= 0) {
            throw new s8("Maximum skip depth exceeded");
        }
        int i3 = 0;
        switch (b) {
            case 2:
                y8Var.y();
                return;
            case 3:
                y8Var.a();
                return;
            case 4:
                y8Var.b();
                return;
            case 5:
            case 7:
            case 9:
            default:
                return;
            case 6:
                y8Var.l();
                return;
            case 8:
                y8Var.c();
                return;
            case 10:
                y8Var.d();
                return;
            case 11:
                y8Var.k();
                return;
            case 12:
                y8Var.i();
                while (true) {
                    byte b2 = y8Var.e().b;
                    if (b2 == 0) {
                        y8Var.D();
                        return;
                    } else {
                        b(y8Var, b2, i2 - 1);
                        y8Var.E();
                    }
                }
            case 13:
                x8 g2 = y8Var.g();
                while (i3 < g2.f19321c) {
                    int i4 = i2 - 1;
                    b(y8Var, g2.a, i4);
                    b(y8Var, g2.b, i4);
                    i3++;
                }
                y8Var.F();
                return;
            case 14:
                d9 h2 = y8Var.h();
                while (i3 < h2.b) {
                    b(y8Var, h2.a, i2 - 1);
                    i3++;
                }
                y8Var.H();
                return;
            case 15:
                w8 f2 = y8Var.f();
                while (i3 < f2.b) {
                    b(y8Var, f2.a, i2 - 1);
                    i3++;
                }
                y8Var.G();
                return;
        }
    }
}

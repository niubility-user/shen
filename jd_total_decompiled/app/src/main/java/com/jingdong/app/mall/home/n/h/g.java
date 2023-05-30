package com.jingdong.app.mall.home.n.h;

import android.graphics.Path;

/* loaded from: classes4.dex */
public class g {
    public static void a(float f2, float f3, float f4, float f5, Path path) {
        float f6 = f2 - f4;
        path.cubicTo((f3 + f4) - f5, f2, f3, f6 + f5, f3, f6);
    }

    public static void b(float f2, float f3, float f4, float f5, Path path) {
        float f6 = f2 + f4;
        path.cubicTo(f2, (f3 - f4) + f5, f6 - f5, f3, f6, f3);
    }

    public static void c(float f2, float f3, float f4, float f5, Path path) {
        float f6 = f2 + f4;
        path.cubicTo(f2, (f3 + f4) - f5, f6 - f5, f3, f6, f3);
    }

    public static void d(float f2, float f3, float f4, float f5, Path path) {
        float f6 = f2 - f4;
        path.cubicTo(f2, (f3 - f4) + f5, f6 + f5, f3, f6, f3);
    }

    public static void e(float f2, float f3, float f4, float f5, Path path) {
        float f6 = f2 + f4;
        path.cubicTo((f3 + f4) - f5, f2, f3, f6 - f5, f3, f6);
    }

    public static void f(float f2, float f3, float f4, float f5, Path path) {
        float f6 = f2 + f4;
        path.cubicTo((f3 - f4) + f5, f2, f3, f6 - f5, f3, f6);
    }

    public static void g(float f2, float f3, float f4, float f5, float f6, Path path) {
        float f7 = 0.5522848f * f6;
        float f8 = f3 + f6;
        path.moveTo(f2, f8);
        c(f2, f3, f6, f7, path);
        path.lineTo(f4 - f6, f3);
        f(f3, f4, f6, f7, path);
        path.lineTo(f4, f5 - f6);
        d(f4, f5, f6, f7, path);
        path.lineTo(f2 + f6, f5);
        a(f5, f2, f6, f7, path);
        path.lineTo(f2, f8);
    }

    public static float h(float f2) {
        return f2 * 0.5522848f;
    }
}

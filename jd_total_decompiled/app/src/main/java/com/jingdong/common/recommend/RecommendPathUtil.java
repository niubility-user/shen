package com.jingdong.common.recommend;

import android.graphics.Path;

/* loaded from: classes6.dex */
public class RecommendPathUtil {
    public static final float C = 0.5522848f;

    public static void addCircleB2L(float f2, float f3, float f4, float f5, Path path) {
        float f6 = f2 - f4;
        path.cubicTo((f3 + f4) - f5, f2, f3, f6 + f5, f3, f6);
    }

    public static void addCircleL2B(float f2, float f3, float f4, float f5, Path path) {
        float f6 = f2 + f4;
        path.cubicTo(f2, (f3 - f4) + f5, f6 - f5, f3, f6, f3);
    }

    public static void addCircleL2T(float f2, float f3, float f4, float f5, Path path) {
        float f6 = f2 + f4;
        path.cubicTo(f2, (f3 + f4) - f5, f6 - f5, f3, f6, f3);
    }

    public static void addCircleR2B(float f2, float f3, float f4, float f5, Path path) {
        float f6 = f2 - f4;
        path.cubicTo(f2, (f3 - f4) + f5, f6 + f5, f3, f6, f3);
    }

    public static void addCircleT2L(float f2, float f3, float f4, float f5, Path path) {
        float f6 = f2 + f4;
        path.cubicTo((f3 + f4) - f5, f2, f3, f6 - f5, f3, f6);
    }

    public static void addCircleT2R(float f2, float f3, float f4, float f5, Path path) {
        float f6 = f2 + f4;
        path.cubicTo((f3 - f4) + f5, f2, f3, f6 - f5, f3, f6);
    }

    public static void addRoundRect(float f2, float f3, float f4, float f5, float f6, Path path) {
        float f7 = 0.5522848f * f6;
        float f8 = f3 + f6;
        path.moveTo(f2, f8);
        addCircleL2T(f2, f3, f6, f7, path);
        path.lineTo(f4 - f6, f3);
        addCircleT2R(f3, f4, f6, f7, path);
        path.lineTo(f4, f5 - f6);
        addCircleR2B(f4, f5, f6, f7, path);
        path.lineTo(f2 + f6, f5);
        addCircleB2L(f5, f2, f6, f7, path);
        path.lineTo(f2, f8);
    }

    public static float getCube(float f2) {
        return f2 * 0.5522848f;
    }
}

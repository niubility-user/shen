package com.jingdong.common.Interpolator;

import android.graphics.PointF;
import android.view.animation.Interpolator;

/* loaded from: classes5.dex */
public class CubicBezierInterpolator implements Interpolator {
    private static final float STEP_SIZE = 2.4414062E-4f;
    private int mLastI = 0;
    private final PointF point1;
    private final PointF point2;

    public CubicBezierInterpolator(float f2, float f3, float f4, float f5) {
        PointF pointF = new PointF();
        this.point1 = pointF;
        PointF pointF2 = new PointF();
        this.point2 = pointF2;
        pointF.x = f2;
        pointF.y = f3;
        pointF2.x = f4;
        pointF2.y = f5;
    }

    public static double cubicEquation(double d, double d2, double d3) {
        double d4 = 1.0d - d;
        double d5 = d * d;
        return (d4 * d4 * 3.0d * d * d2) + (d4 * 3.0d * d5 * d3) + (d5 * d);
    }

    @Override // android.animation.TimeInterpolator
    public float getInterpolation(float f2) {
        if (f2 == 0.0f) {
            this.mLastI = 0;
        }
        int i2 = this.mLastI;
        float f3 = f2;
        while (true) {
            if (i2 >= 4096) {
                break;
            }
            f3 = i2 * STEP_SIZE;
            if (cubicEquation(f3, this.point1.x, this.point2.x) >= f2) {
                this.mLastI = i2;
                break;
            }
            i2++;
        }
        double cubicEquation = cubicEquation(f3, this.point1.y, this.point2.y);
        if (f2 == 1.0f) {
            this.mLastI = 0;
        }
        return (float) cubicEquation;
    }
}

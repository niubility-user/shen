package com.jingdong.common.utils;

import android.animation.TypeEvaluator;
import android.graphics.PointF;

/* loaded from: classes6.dex */
public class BezierEvaluator implements TypeEvaluator<PointF> {
    private PointF point1;

    public BezierEvaluator(PointF pointF) {
        this.point1 = pointF;
    }

    @Override // android.animation.TypeEvaluator
    public PointF evaluate(float f2, PointF pointF, PointF pointF2) {
        PointF pointF3 = new PointF();
        float f3 = 1.0f - f2;
        PointF pointF4 = this.point1;
        float f4 = f2 * f2;
        pointF3.x = (pointF.x * f3 * f3) + (pointF4.x * 2.0f * f2 * f3) + (pointF2.x * f4);
        pointF3.y = (pointF.y * f3 * f3) + (pointF4.y * 2.0f * f2 * f3) + (f4 * pointF2.y);
        return pointF3;
    }
}

package com.jingdong.common.model.smarttablayout;

import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;

/* loaded from: classes5.dex */
public abstract class SmartTabIndicationInterpolator {
    static final int ID_LINEAR = 1;
    static final int ID_SMART = 0;
    public static final SmartTabIndicationInterpolator SMART = new SmartIndicationInterpolator();
    public static final SmartTabIndicationInterpolator LINEAR = new LinearIndicationInterpolator();

    /* loaded from: classes5.dex */
    public static class LinearIndicationInterpolator extends SmartTabIndicationInterpolator {
        @Override // com.jingdong.common.model.smarttablayout.SmartTabIndicationInterpolator
        public float getLeftEdge(float f2) {
            return f2;
        }

        @Override // com.jingdong.common.model.smarttablayout.SmartTabIndicationInterpolator
        public float getRightEdge(float f2) {
            return f2;
        }
    }

    /* loaded from: classes5.dex */
    public static class SmartIndicationInterpolator extends SmartTabIndicationInterpolator {
        private static final float DEFAULT_INDICATOR_INTERPOLATION_FACTOR = 3.0f;
        private final Interpolator leftEdgeInterpolator;
        private final Interpolator rightEdgeInterpolator;

        public SmartIndicationInterpolator() {
            this(3.0f);
        }

        @Override // com.jingdong.common.model.smarttablayout.SmartTabIndicationInterpolator
        public float getLeftEdge(float f2) {
            return this.leftEdgeInterpolator.getInterpolation(f2);
        }

        @Override // com.jingdong.common.model.smarttablayout.SmartTabIndicationInterpolator
        public float getRightEdge(float f2) {
            return this.rightEdgeInterpolator.getInterpolation(f2);
        }

        @Override // com.jingdong.common.model.smarttablayout.SmartTabIndicationInterpolator
        public float getThickness(float f2) {
            return 1.0f / ((1.0f - getLeftEdge(f2)) + getRightEdge(f2));
        }

        public SmartIndicationInterpolator(float f2) {
            this.leftEdgeInterpolator = new AccelerateInterpolator(f2);
            this.rightEdgeInterpolator = new DecelerateInterpolator(f2);
        }
    }

    public static SmartTabIndicationInterpolator of(int i2) {
        if (i2 != 0) {
            if (i2 == 1) {
                return LINEAR;
            }
            throw new IllegalArgumentException("Unknown id: " + i2);
        }
        return SMART;
    }

    public abstract float getLeftEdge(float f2);

    public abstract float getRightEdge(float f2);

    public float getThickness(float f2) {
        return 1.0f;
    }
}

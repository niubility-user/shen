package com.jd.manto.lbs;

import android.view.animation.Interpolator;

/* loaded from: classes17.dex */
public class MantoBounceInterpolator implements Interpolator {
    private double mAmplitude;
    private double mFrequency;

    /* JADX INFO: Access modifiers changed from: package-private */
    public MantoBounceInterpolator(double d, double d2) {
        this.mAmplitude = 1.0d;
        this.mFrequency = 10.0d;
        this.mAmplitude = d;
        this.mFrequency = d2;
    }

    @Override // android.animation.TimeInterpolator
    public float getInterpolation(float f2) {
        double d = -f2;
        double d2 = this.mAmplitude;
        Double.isNaN(d);
        double d3 = this.mFrequency;
        double d4 = f2;
        Double.isNaN(d4);
        return (float) ((Math.pow(2.718281828459045d, d / d2) * (-1.0d) * Math.cos(d3 * d4)) + 1.0d);
    }
}

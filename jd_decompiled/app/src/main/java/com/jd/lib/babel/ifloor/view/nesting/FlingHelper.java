package com.jd.lib.babel.ifloor.view.nesting;

import android.content.Context;
import android.view.ViewConfiguration;
import com.jd.lib.babel.servicekit.iservice.IBaseInfo;
import com.jd.lib.babel.servicekit.util.BabelServiceUtils;

/* loaded from: classes13.dex */
public class FlingHelper {
    private static final float INFLEXION = 0.35f;
    private static float mPhysicalCoeff;
    private static float mFlingFriction = ViewConfiguration.getScrollFriction();
    private static float DECELERATION_RATE = (float) (Math.log(0.78d) / Math.log(0.9d));

    public FlingHelper(Context context) {
        IBaseInfo iBaseInfo = (IBaseInfo) BabelServiceUtils.getService(IBaseInfo.class);
        mPhysicalCoeff = (iBaseInfo != null ? 160.0f * iBaseInfo.getDensity() : 160.0f) * 386.0878f * 0.84f;
    }

    private double getSplineDeceleration(int i2) {
        return Math.log((Math.abs(i2) * 0.35f) / (mFlingFriction * mPhysicalCoeff));
    }

    private double getSplineDecelerationByDistance(double d) {
        double d2 = DECELERATION_RATE;
        Double.isNaN(d2);
        double d3 = mFlingFriction * mPhysicalCoeff;
        Double.isNaN(d3);
        double log = (d2 - 1.0d) * Math.log(d / d3);
        double d4 = DECELERATION_RATE;
        Double.isNaN(d4);
        return log / d4;
    }

    public double getSplineFlingDistance(int i2) {
        double splineDeceleration = getSplineDeceleration(i2);
        float f2 = DECELERATION_RATE;
        double d = f2;
        Double.isNaN(d);
        double d2 = mFlingFriction * mPhysicalCoeff;
        double d3 = f2;
        Double.isNaN(d3);
        double exp = Math.exp((d3 / (d - 1.0d)) * splineDeceleration);
        Double.isNaN(d2);
        return d2 * exp;
    }

    public int getSplineFlingDuration(int i2) {
        double splineDeceleration = getSplineDeceleration(i2);
        double d = DECELERATION_RATE;
        Double.isNaN(d);
        return (int) (Math.exp(splineDeceleration / (d - 1.0d)) * 1000.0d);
    }

    public int getVelocityByDistance(double d) {
        double exp = Math.exp(getSplineDecelerationByDistance(d));
        double d2 = mFlingFriction;
        Double.isNaN(d2);
        double d3 = exp * d2;
        double d4 = mPhysicalCoeff;
        Double.isNaN(d4);
        return Math.abs((int) ((d3 * d4) / 0.3499999940395355d));
    }
}

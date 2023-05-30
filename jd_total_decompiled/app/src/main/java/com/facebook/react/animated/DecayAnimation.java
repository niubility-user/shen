package com.facebook.react.animated;

import com.facebook.react.bridge.ReadableMap;

/* loaded from: classes.dex */
public class DecayAnimation extends AnimationDriver {
    private int mCurrentLoop;
    private double mDeceleration;
    private double mFromValue;
    private int mIterations;
    private double mLastValue;
    private long mStartFrameTimeMillis;
    private final double mVelocity;

    public DecayAnimation(ReadableMap readableMap) {
        this.mVelocity = readableMap.getDouble("velocity");
        resetConfig(readableMap);
    }

    @Override // com.facebook.react.animated.AnimationDriver
    public void resetConfig(ReadableMap readableMap) {
        this.mDeceleration = readableMap.getDouble("deceleration");
        int i2 = readableMap.hasKey("iterations") ? readableMap.getInt("iterations") : 1;
        this.mIterations = i2;
        this.mCurrentLoop = 1;
        this.mHasFinished = i2 == 0;
        this.mStartFrameTimeMillis = -1L;
        this.mFromValue = 0.0d;
        this.mLastValue = 0.0d;
    }

    @Override // com.facebook.react.animated.AnimationDriver
    public void runAnimationStep(long j2) {
        long j3 = j2 / 1000000;
        if (this.mStartFrameTimeMillis == -1) {
            this.mStartFrameTimeMillis = j3 - 16;
            double d = this.mFromValue;
            if (d == this.mLastValue) {
                this.mFromValue = this.mAnimatedValue.mValue;
            } else {
                this.mAnimatedValue.mValue = d;
            }
            this.mLastValue = this.mAnimatedValue.mValue;
        }
        double d2 = this.mFromValue;
        double d3 = this.mVelocity;
        double d4 = this.mDeceleration;
        double d5 = j3 - this.mStartFrameTimeMillis;
        Double.isNaN(d5);
        double exp = d2 + ((d3 / (1.0d - d4)) * (1.0d - Math.exp((-(1.0d - d4)) * d5)));
        if (Math.abs(this.mLastValue - exp) < 0.1d) {
            int i2 = this.mIterations;
            if (i2 != -1 && this.mCurrentLoop >= i2) {
                this.mHasFinished = true;
                return;
            } else {
                this.mStartFrameTimeMillis = -1L;
                this.mCurrentLoop++;
            }
        }
        this.mLastValue = exp;
        this.mAnimatedValue.mValue = exp;
    }
}

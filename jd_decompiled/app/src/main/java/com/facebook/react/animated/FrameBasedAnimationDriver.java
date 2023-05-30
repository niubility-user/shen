package com.facebook.react.animated;

import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;

/* loaded from: classes.dex */
class FrameBasedAnimationDriver extends AnimationDriver {
    private static final double FRAME_TIME_MILLIS = 16.666666666666668d;
    private int mCurrentLoop;
    private double[] mFrames;
    private double mFromValue;
    private int mIterations;
    private long mStartFrameTimeNanos;
    private double mToValue;

    /* JADX INFO: Access modifiers changed from: package-private */
    public FrameBasedAnimationDriver(ReadableMap readableMap) {
        resetConfig(readableMap);
    }

    @Override // com.facebook.react.animated.AnimationDriver
    public void resetConfig(ReadableMap readableMap) {
        ReadableArray array = readableMap.getArray("frames");
        int size = array.size();
        double[] dArr = this.mFrames;
        if (dArr == null || dArr.length != size) {
            this.mFrames = new double[size];
        }
        for (int i2 = 0; i2 < size; i2++) {
            this.mFrames[i2] = array.getDouble(i2);
        }
        this.mToValue = readableMap.hasKey("toValue") ? readableMap.getDouble("toValue") : 0.0d;
        int i3 = readableMap.hasKey("iterations") ? readableMap.getInt("iterations") : 1;
        this.mIterations = i3;
        this.mCurrentLoop = 1;
        this.mHasFinished = i3 == 0;
        this.mStartFrameTimeNanos = -1L;
    }

    @Override // com.facebook.react.animated.AnimationDriver
    public void runAnimationStep(long j2) {
        double d;
        if (this.mStartFrameTimeNanos < 0) {
            this.mStartFrameTimeNanos = j2;
            if (this.mCurrentLoop == 1) {
                this.mFromValue = this.mAnimatedValue.mValue;
            }
        }
        double d2 = (j2 - this.mStartFrameTimeNanos) / 1000000;
        Double.isNaN(d2);
        int round = (int) Math.round(d2 / FRAME_TIME_MILLIS);
        if (round >= 0) {
            if (this.mHasFinished) {
                return;
            }
            double[] dArr = this.mFrames;
            if (round >= dArr.length - 1) {
                d = this.mToValue;
                int i2 = this.mIterations;
                if (i2 != -1 && this.mCurrentLoop >= i2) {
                    this.mHasFinished = true;
                } else {
                    this.mStartFrameTimeNanos = -1L;
                    this.mCurrentLoop++;
                }
            } else {
                double d3 = this.mFromValue;
                d = d3 + (dArr[round] * (this.mToValue - d3));
            }
            this.mAnimatedValue.mValue = d;
            return;
        }
        throw new IllegalStateException("Calculated frame index should never be lower than 0");
    }
}

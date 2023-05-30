package com.jingdong.app.mall.utils.ui;

import android.content.Context;
import android.view.ViewConfiguration;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import com.jingdong.sdk.baseinfo.BaseInfo;

/* loaded from: classes4.dex */
public class Scroller {
    private static final int DEFAULT_DURATION = 250;
    private static final int FLING_MODE = 1;
    private static final int NB_SAMPLES = 100;
    private static final int SCROLL_MODE = 0;
    private static float sViscousFluidNormalize;
    private static float sViscousFluidScale;
    private int mCurrX;
    private int mCurrY;
    private float mDeceleration;
    private float mDeltaX;
    private float mDeltaY;
    private int mDuration;
    private float mDurationReciprocal;
    private int mFinalX;
    private int mFinalY;
    private boolean mFinished;
    private boolean mFlywheel;
    private Interpolator mInterpolator;
    private int mMaxX;
    private int mMaxY;
    private int mMinX;
    private int mMinY;
    private int mMode;
    private final float mPpi;
    private long mStartTime;
    private int mStartX;
    private int mStartY;
    private float mVelocity;
    private static float DECELERATION_RATE = (float) (Math.log(0.75d) / Math.log(0.9d));
    private static float ALPHA = 800.0f;
    private static float START_TENSION = 0.4f;
    private static float END_TENSION = 1.0f - 0.4f;
    private static final float[] SPLINE = new float[101];

    static {
        float f2;
        float f3;
        float f4 = 0.0f;
        for (int i2 = 0; i2 <= 100; i2++) {
            float f5 = i2 / 100.0f;
            float f6 = 1.0f;
            while (true) {
                float f7 = ((f6 - f4) / 2.0f) + f4;
                float f8 = 1.0f - f7;
                f2 = 3.0f * f7 * f8;
                f3 = f7 * f7 * f7;
                float f9 = (((f8 * START_TENSION) + (END_TENSION * f7)) * f2) + f3;
                if (Math.abs(f9 - f5) < 1.0E-5d) {
                    break;
                } else if (f9 > f5) {
                    f6 = f7;
                } else {
                    f4 = f7;
                }
            }
            SPLINE[i2] = f2 + f3;
        }
        SPLINE[100] = 1.0f;
        sViscousFluidScale = 8.0f;
        sViscousFluidNormalize = 1.0f;
        sViscousFluidNormalize = 1.0f / viscousFluid(1.0f);
    }

    public Scroller(Context context) {
        this(context, null);
    }

    private float computeDeceleration(float f2) {
        return this.mPpi * 386.0878f * f2;
    }

    static float viscousFluid(float f2) {
        float exp;
        float f3 = f2 * sViscousFluidScale;
        if (f3 < 1.0f) {
            exp = f3 - (1.0f - ((float) Math.exp(-f3)));
        } else {
            exp = ((1.0f - ((float) Math.exp(1.0f - f3))) * 0.63212055f) + 0.36787945f;
        }
        return exp * sViscousFluidNormalize;
    }

    public void abortAnimation() {
        this.mCurrX = this.mFinalX;
        this.mCurrY = this.mFinalY;
        this.mFinished = true;
    }

    public boolean computeScrollOffset() {
        float interpolation;
        if (this.mFinished) {
            return false;
        }
        int currentAnimationTimeMillis = (int) (AnimationUtils.currentAnimationTimeMillis() - this.mStartTime);
        int i2 = this.mDuration;
        if (currentAnimationTimeMillis < i2) {
            int i3 = this.mMode;
            if (i3 == 0) {
                float f2 = currentAnimationTimeMillis * this.mDurationReciprocal;
                Interpolator interpolator = this.mInterpolator;
                if (interpolator == null) {
                    interpolation = viscousFluid(f2);
                } else {
                    interpolation = interpolator.getInterpolation(f2);
                }
                this.mCurrX = this.mStartX + Math.round(this.mDeltaX * interpolation);
                this.mCurrY = this.mStartY + Math.round(interpolation * this.mDeltaY);
            } else if (i3 == 1) {
                float f3 = currentAnimationTimeMillis / i2;
                int i4 = (int) (f3 * 100.0f);
                float f4 = i4 / 100.0f;
                int i5 = i4 + 1;
                float[] fArr = SPLINE;
                float f5 = fArr[i4];
                float f6 = f5 + (((f3 - f4) / ((i5 / 100.0f) - f4)) * (fArr[i5] - f5));
                int round = this.mStartX + Math.round((this.mFinalX - r0) * f6);
                this.mCurrX = round;
                int min = Math.min(round, this.mMaxX);
                this.mCurrX = min;
                this.mCurrX = Math.max(min, this.mMinX);
                int round2 = this.mStartY + Math.round(f6 * (this.mFinalY - r0));
                this.mCurrY = round2;
                int min2 = Math.min(round2, this.mMaxY);
                this.mCurrY = min2;
                int max = Math.max(min2, this.mMinY);
                this.mCurrY = max;
                if (this.mCurrX == this.mFinalX && max == this.mFinalY) {
                    this.mFinished = true;
                }
            }
        } else {
            this.mCurrX = this.mFinalX;
            this.mCurrY = this.mFinalY;
            this.mFinished = true;
        }
        return true;
    }

    public void extendDuration(int i2) {
        int timePassed = timePassed() + i2;
        this.mDuration = timePassed;
        this.mDurationReciprocal = 1.0f / timePassed;
        this.mFinished = false;
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x00a0  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x00a3  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x00aa  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void fling(int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9) {
        int i10;
        int i11;
        if (!this.mFlywheel || this.mFinished) {
            i10 = i4;
        } else {
            float currVelocity = getCurrVelocity();
            float f2 = this.mFinalX - this.mStartX;
            float f3 = this.mFinalY - this.mStartY;
            float sqrt = (float) Math.sqrt((f2 * f2) + (f3 * f3));
            float f4 = (f2 / sqrt) * currVelocity;
            float f5 = (f3 / sqrt) * currVelocity;
            i10 = i4;
            float f6 = i10;
            if (Math.signum(f6) == Math.signum(f4)) {
                i11 = i5;
                float f7 = i11;
                if (Math.signum(f7) == Math.signum(f5)) {
                    i10 = (int) (f6 + f4);
                    i11 = (int) (f7 + f5);
                }
                this.mMode = 1;
                this.mFinished = false;
                float sqrt2 = (float) Math.sqrt((i10 * i10) + (i11 * i11));
                this.mVelocity = sqrt2;
                double log = Math.log((START_TENSION * sqrt2) / ALPHA);
                double d = DECELERATION_RATE;
                Double.isNaN(d);
                this.mDuration = (int) (Math.exp(log / (d - 1.0d)) * 1000.0d);
                this.mStartTime = AnimationUtils.currentAnimationTimeMillis();
                this.mStartX = i2;
                this.mStartY = i3;
                float f8 = sqrt2 != 0.0f ? 1.0f : i10 / sqrt2;
                float f9 = sqrt2 != 0.0f ? i11 / sqrt2 : 1.0f;
                double d2 = ALPHA;
                float f10 = DECELERATION_RATE;
                double d3 = f10;
                double d4 = f10;
                Double.isNaN(d4);
                Double.isNaN(d3);
                double exp = Math.exp((d3 / (d4 - 1.0d)) * log);
                Double.isNaN(d2);
                this.mMinX = i6;
                this.mMaxX = i7;
                this.mMinY = i8;
                this.mMaxY = i9;
                float f11 = (int) (d2 * exp);
                int round = i2 + Math.round(f8 * f11);
                this.mFinalX = round;
                int min = Math.min(round, this.mMaxX);
                this.mFinalX = min;
                this.mFinalX = Math.max(min, this.mMinX);
                int round2 = Math.round(f11 * f9) + i3;
                this.mFinalY = round2;
                int min2 = Math.min(round2, this.mMaxY);
                this.mFinalY = min2;
                this.mFinalY = Math.max(min2, this.mMinY);
            }
        }
        i11 = i5;
        this.mMode = 1;
        this.mFinished = false;
        float sqrt22 = (float) Math.sqrt((i10 * i10) + (i11 * i11));
        this.mVelocity = sqrt22;
        double log2 = Math.log((START_TENSION * sqrt22) / ALPHA);
        double d5 = DECELERATION_RATE;
        Double.isNaN(d5);
        this.mDuration = (int) (Math.exp(log2 / (d5 - 1.0d)) * 1000.0d);
        this.mStartTime = AnimationUtils.currentAnimationTimeMillis();
        this.mStartX = i2;
        this.mStartY = i3;
        if (sqrt22 != 0.0f) {
        }
        if (sqrt22 != 0.0f) {
        }
        double d22 = ALPHA;
        float f102 = DECELERATION_RATE;
        double d32 = f102;
        double d42 = f102;
        Double.isNaN(d42);
        Double.isNaN(d32);
        double exp2 = Math.exp((d32 / (d42 - 1.0d)) * log2);
        Double.isNaN(d22);
        this.mMinX = i6;
        this.mMaxX = i7;
        this.mMinY = i8;
        this.mMaxY = i9;
        float f112 = (int) (d22 * exp2);
        int round3 = i2 + Math.round(f8 * f112);
        this.mFinalX = round3;
        int min3 = Math.min(round3, this.mMaxX);
        this.mFinalX = min3;
        this.mFinalX = Math.max(min3, this.mMinX);
        int round22 = Math.round(f112 * f9) + i3;
        this.mFinalY = round22;
        int min22 = Math.min(round22, this.mMaxY);
        this.mFinalY = min22;
        this.mFinalY = Math.max(min22, this.mMinY);
    }

    public final void forceFinished(boolean z) {
        this.mFinished = z;
    }

    public float getCurrVelocity() {
        return this.mVelocity - ((this.mDeceleration * timePassed()) / 2000.0f);
    }

    public final int getCurrX() {
        return this.mCurrX;
    }

    public final int getCurrY() {
        return this.mCurrY;
    }

    public final int getDuration() {
        return this.mDuration;
    }

    public final int getFinalX() {
        return this.mFinalX;
    }

    public final int getFinalY() {
        return this.mFinalY;
    }

    public final int getStartX() {
        return this.mStartX;
    }

    public final int getStartY() {
        return this.mStartY;
    }

    public final boolean isFinished() {
        return this.mFinished;
    }

    public boolean isScrollingInDirection(float f2, float f3) {
        return !this.mFinished && Math.signum(f2) == Math.signum((float) (this.mFinalX - this.mStartX)) && Math.signum(f3) == Math.signum((float) (this.mFinalY - this.mStartY));
    }

    public void setFinalX(int i2) {
        this.mFinalX = i2;
        this.mDeltaX = i2 - this.mStartX;
        this.mFinished = false;
    }

    public void setFinalY(int i2) {
        this.mFinalY = i2;
        this.mDeltaY = i2 - this.mStartY;
        this.mFinished = false;
    }

    public final void setFriction(float f2) {
        this.mDeceleration = computeDeceleration(f2);
    }

    public void startScroll(int i2, int i3, int i4, int i5) {
        startScroll(i2, i3, i4, i5, 250);
    }

    public int timePassed() {
        return (int) (AnimationUtils.currentAnimationTimeMillis() - this.mStartTime);
    }

    public Scroller(Context context, Interpolator interpolator) {
        this(context, interpolator, true);
    }

    public void startScroll(int i2, int i3, int i4, int i5, int i6) {
        this.mMode = 0;
        this.mFinished = false;
        this.mDuration = i6;
        this.mStartTime = AnimationUtils.currentAnimationTimeMillis();
        this.mStartX = i2;
        this.mStartY = i3;
        this.mFinalX = i2 + i4;
        this.mFinalY = i3 + i5;
        this.mDeltaX = i4;
        this.mDeltaY = i5;
        this.mDurationReciprocal = 1.0f / this.mDuration;
    }

    public Scroller(Context context, Interpolator interpolator, boolean z) {
        this.mFinished = true;
        this.mInterpolator = interpolator;
        this.mPpi = BaseInfo.getDensity() * 160.0f;
        this.mDeceleration = computeDeceleration(ViewConfiguration.getScrollFriction());
        this.mFlywheel = z;
    }
}

package com.facebook.react.views.slider;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.SeekBar;
import javax.annotation.Nullable;

/* loaded from: classes12.dex */
public class ReactSlider extends SeekBar {
    private static int DEFAULT_TOTAL_STEPS = 128;
    private double mMaxValue;
    private double mMinValue;
    private double mStep;
    private double mStepCalculated;
    private double mValue;

    public ReactSlider(Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mMinValue = 0.0d;
        this.mMaxValue = 0.0d;
        this.mValue = 0.0d;
        this.mStep = 0.0d;
        this.mStepCalculated = 0.0d;
        disableStateListAnimatorIfNeeded();
    }

    private void disableStateListAnimatorIfNeeded() {
        int i2 = Build.VERSION.SDK_INT;
        if (i2 < 23 || i2 >= 26) {
            return;
        }
        super.setStateListAnimator(null);
    }

    private double getStepValue() {
        double d = this.mStep;
        return d > 0.0d ? d : this.mStepCalculated;
    }

    private int getTotalSteps() {
        return (int) Math.ceil((this.mMaxValue - this.mMinValue) / getStepValue());
    }

    private void updateAll() {
        if (this.mStep == 0.0d) {
            double d = this.mMaxValue - this.mMinValue;
            double d2 = DEFAULT_TOTAL_STEPS;
            Double.isNaN(d2);
            this.mStepCalculated = d / d2;
        }
        setMax(getTotalSteps());
        updateValue();
    }

    private void updateValue() {
        double d = this.mValue;
        double d2 = this.mMinValue;
        double totalSteps = getTotalSteps();
        Double.isNaN(totalSteps);
        setProgress((int) Math.round(((d - d2) / (this.mMaxValue - d2)) * totalSteps));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setMaxValue(double d) {
        this.mMaxValue = d;
        updateAll();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setMinValue(double d) {
        this.mMinValue = d;
        updateAll();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setStep(double d) {
        this.mStep = d;
        updateAll();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setValue(double d) {
        this.mValue = d;
        updateValue();
    }

    public double toRealProgress(int i2) {
        if (i2 == getMax()) {
            return this.mMaxValue;
        }
        double d = i2;
        double stepValue = getStepValue();
        Double.isNaN(d);
        return (d * stepValue) + this.mMinValue;
    }
}

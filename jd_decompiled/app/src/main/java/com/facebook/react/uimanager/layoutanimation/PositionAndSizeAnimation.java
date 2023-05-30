package com.facebook.react.uimanager.layoutanimation;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;

/* loaded from: classes12.dex */
class PositionAndSizeAnimation extends Animation implements LayoutHandlingAnimation {
    private int mDeltaHeight;
    private int mDeltaWidth;
    private float mDeltaX;
    private float mDeltaY;
    private int mStartHeight;
    private int mStartWidth;
    private float mStartX;
    private float mStartY;
    private final View mView;

    public PositionAndSizeAnimation(View view, int i2, int i3, int i4, int i5) {
        this.mView = view;
        calculateAnimation(i2, i3, i4, i5);
    }

    private void calculateAnimation(int i2, int i3, int i4, int i5) {
        this.mStartX = this.mView.getX() - this.mView.getTranslationX();
        this.mStartY = this.mView.getY() - this.mView.getTranslationY();
        this.mStartWidth = this.mView.getWidth();
        int height = this.mView.getHeight();
        this.mStartHeight = height;
        this.mDeltaX = i2 - this.mStartX;
        this.mDeltaY = i3 - this.mStartY;
        this.mDeltaWidth = i4 - this.mStartWidth;
        this.mDeltaHeight = i5 - height;
    }

    @Override // android.view.animation.Animation
    protected void applyTransformation(float f2, Transformation transformation) {
        float f3 = this.mStartX + (this.mDeltaX * f2);
        float f4 = this.mStartY + (this.mDeltaY * f2);
        this.mView.layout(Math.round(f3), Math.round(f4), Math.round(f3 + this.mStartWidth + (this.mDeltaWidth * f2)), Math.round(f4 + this.mStartHeight + (this.mDeltaHeight * f2)));
    }

    @Override // com.facebook.react.uimanager.layoutanimation.LayoutHandlingAnimation
    public void onLayoutUpdate(int i2, int i3, int i4, int i5) {
        calculateAnimation(i2, i3, i4, i5);
    }

    @Override // android.view.animation.Animation
    public boolean willChangeBounds() {
        return true;
    }
}

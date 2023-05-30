package com.jd.viewkit.templates.container.jdviewkitswipecard.swipecard;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;

/* loaded from: classes18.dex */
public class SwipeFlingListener implements View.OnTouchListener {
    private long aDownTouchTime;
    private float aDownTouchX;
    private float aDownTouchY;
    private float aPosX;
    private float aPosY;
    private float aTouchUpX;
    private float aTouchUpY;
    private final Object dataObject;
    private View frame;
    private final FlingListener mFlingListener;
    private int mTouchSlop;
    private final int objectH;
    private final int objectW;
    private final float objectX;
    private final float objectY;
    private final int parentWidth;
    private boolean isAnimationRunning = false;
    private float MAX_COS = (float) Math.cos(Math.toRadians(45.0d));
    private int animDuration = 300;

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes18.dex */
    public interface FlingListener {
        void leftExit(Object obj);

        void onCardExited(boolean z);

        void onClick(MotionEvent motionEvent, View view, Object obj);

        void onScroll(float f2, float f3);

        void rightExit(Object obj);

        void swipe(boolean z);
    }

    public SwipeFlingListener(View view, Object obj, FlingListener flingListener) {
        this.frame = null;
        this.frame = view;
        this.objectX = view.getX();
        this.objectY = view.getY();
        this.objectW = view.getWidth();
        this.objectH = view.getHeight();
        this.dataObject = obj;
        this.parentWidth = ((ViewGroup) view.getParent()).getWidth();
        this.mFlingListener = flingListener;
        this.mTouchSlop = ViewConfiguration.get(view.getContext()).getScaledTouchSlop();
    }

    private float getRotationWidthOffset() {
        int i2 = this.objectW;
        return (i2 / this.MAX_COS) - i2;
    }

    private float getScrollProgress() {
        return Math.min(Math.abs(this.aPosX - this.objectX) + Math.abs(this.aPosY - this.objectY), 400.0f) / 400.0f;
    }

    private boolean resetCardViewOnStack(MotionEvent motionEvent) {
        float abs = Math.abs(this.aTouchUpX - this.aDownTouchX);
        float abs2 = Math.abs(this.aTouchUpY - this.aDownTouchY);
        int i2 = this.mTouchSlop;
        if (abs <= i2 && abs2 <= i2 && abs - abs2 >= -2.0f && System.currentTimeMillis() - this.aDownTouchTime < ViewConfiguration.getJumpTapTimeout()) {
            if (this.aDownTouchX < this.objectX + this.objectW) {
                this.mFlingListener.onClick(motionEvent, this.frame, this.dataObject);
            }
        } else if (abs > this.mTouchSlop && abs >= abs2) {
            this.mFlingListener.swipe(this.aPosX < 0.0f);
        }
        return false;
    }

    public void onSelected(final boolean z, float f2, long j2) {
        this.isAnimationRunning = true;
        this.frame.animate().setDuration(j2).setInterpolator(new LinearInterpolator()).translationX(z ? (-this.objectW) - getRotationWidthOffset() : 0.0f).translationY(this.objectY).setListener(new AnimatorListenerAdapter() { // from class: com.jd.viewkit.templates.container.jdviewkitswipecard.swipecard.SwipeFlingListener.1
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                if (z) {
                    SwipeFlingListener.this.mFlingListener.onCardExited(true);
                    SwipeFlingListener.this.mFlingListener.leftExit(SwipeFlingListener.this.dataObject);
                } else {
                    SwipeFlingListener.this.mFlingListener.onCardExited(false);
                    SwipeFlingListener.this.mFlingListener.rightExit(SwipeFlingListener.this.dataObject);
                }
                SwipeFlingListener.this.isAnimationRunning = false;
            }
        }).start();
    }

    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View view, MotionEvent motionEvent) {
        try {
            int action = motionEvent.getAction();
            if (action != 0) {
                if (action != 1) {
                    if (action == 2) {
                        float rawX = motionEvent.getRawX();
                        float rawY = motionEvent.getRawY();
                        if (this.aDownTouchTime <= 0) {
                            this.aDownTouchTime = System.currentTimeMillis();
                            this.aDownTouchX = rawX;
                            this.aDownTouchY = rawY;
                        }
                        this.aPosX = rawX - this.aDownTouchX;
                        this.aPosY = rawY - this.aDownTouchY;
                    } else if (action != 3) {
                    }
                }
                this.aTouchUpX = motionEvent.getRawX();
                this.aTouchUpY = motionEvent.getRawY();
                resetCardViewOnStack(motionEvent);
                this.aDownTouchTime = 0L;
            } else {
                float rawX2 = motionEvent.getRawX();
                float rawY2 = motionEvent.getRawY();
                this.aDownTouchX = rawX2;
                this.aDownTouchY = rawY2;
                this.aDownTouchTime = System.currentTimeMillis();
                this.aPosX = 0.0f;
                this.aPosY = 0.0f;
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return true;
    }

    public void selectLeft() {
        if (this.isAnimationRunning) {
            return;
        }
        selectLeft(this.animDuration);
    }

    public void selectRight() {
        if (this.isAnimationRunning) {
            return;
        }
        selectRight(this.animDuration);
    }

    public void selectLeft(long j2) {
        if (this.isAnimationRunning) {
            return;
        }
        onSelected(true, this.objectY, j2);
    }

    public void selectRight(long j2) {
        if (this.isAnimationRunning) {
            return;
        }
        onSelected(false, this.objectY, j2);
    }
}

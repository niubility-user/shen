package com.jd.lib.babel.task.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/* loaded from: classes14.dex */
public class DragContainer extends FrameLayout {
    private static final int MIN_MOVE_SLOP = 10;
    private boolean isDraging;
    private FrameLayout layout;
    private float mLastMotionX;
    private float mLastMotionY;
    private int moveX;
    private int moveY;
    private FrameLayout.LayoutParams params;

    public DragContainer(@NonNull Context context) {
        this(context, null);
    }

    private void startDragConfig() {
        try {
            this.layout = (FrameLayout) getParent();
            this.params = (FrameLayout.LayoutParams) getLayoutParams();
        } catch (Exception unused) {
            this.layout = null;
            this.params = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateLayoutParams(int i2, int i3) {
        FrameLayout.LayoutParams layoutParams = this.params;
        if (layoutParams != null) {
            layoutParams.leftMargin = i2;
            layoutParams.topMargin = i3;
            setLayoutParams(layoutParams);
        }
    }

    public void onDraging() {
        FrameLayout frameLayout = this.layout;
        if (frameLayout == null || this.params == null) {
            return;
        }
        if (this.moveX > frameLayout.getWidth() - getWidth()) {
            this.moveX = this.layout.getWidth() - getWidth();
        } else if (this.moveX < 0) {
            this.moveX = 0;
        }
        if (this.moveY > this.layout.getHeight() - getHeight()) {
            this.moveY = this.layout.getHeight() - getHeight();
        } else if (this.moveY < 0) {
            this.moveY = 0;
        }
        updateLayoutParams(this.moveX, this.moveY);
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 0) {
            this.mLastMotionX = motionEvent.getX();
            this.mLastMotionY = motionEvent.getY();
        } else if (action != 1 && action == 2) {
            float abs = Math.abs(motionEvent.getX() - this.mLastMotionX);
            float abs2 = Math.abs(motionEvent.getY() - this.mLastMotionY);
            if (abs > 10.0f || abs2 > 10.0f) {
                return true;
            }
        }
        return false;
    }

    public void onStopDrag() {
        if (this.isDraging) {
            boolean z = this.layout != null && this.moveX + (getWidth() / 2) >= this.layout.getWidth() / 2;
            int[] iArr = new int[2];
            iArr[0] = this.moveX;
            iArr[1] = z ? this.layout.getWidth() - getWidth() : 0;
            ValueAnimator ofInt = ValueAnimator.ofInt(iArr);
            ofInt.setDuration(100L);
            ofInt.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.jd.lib.babel.task.view.DragContainer.1
                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    int intValue = ((Integer) valueAnimator.getAnimatedValue()).intValue();
                    DragContainer dragContainer = DragContainer.this;
                    dragContainer.updateLayoutParams(intValue, dragContainer.moveY);
                }
            });
            ofInt.start();
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:7:0x000b, code lost:
        if (r0 != 3) goto L14;
     */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action != 1) {
            if (action == 2) {
                if (!this.isDraging) {
                    this.isDraging = true;
                    startDragConfig();
                }
                this.moveX = (int) ((getLeft() + motionEvent.getX()) - this.mLastMotionX);
                this.moveY = (int) ((getTop() + motionEvent.getY()) - this.mLastMotionY);
                onDraging();
            }
            return true;
        }
        onStopDrag();
        this.isDraging = false;
        return true;
    }

    public DragContainer(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public DragContainer(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.isDraging = false;
    }
}

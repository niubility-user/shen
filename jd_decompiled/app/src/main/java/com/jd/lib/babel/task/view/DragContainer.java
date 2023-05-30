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
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean onTouchEvent(android.view.MotionEvent r4) {
        /*
            r3 = this;
            int r0 = r4.getAction()
            r1 = 1
            if (r0 == r1) goto L3b
            r2 = 2
            if (r0 == r2) goto Le
            r4 = 3
            if (r0 == r4) goto L3b
            goto L41
        Le:
            boolean r0 = r3.isDraging
            if (r0 != 0) goto L17
            r3.isDraging = r1
            r3.startDragConfig()
        L17:
            int r0 = r3.getLeft()
            float r0 = (float) r0
            float r2 = r4.getX()
            float r0 = r0 + r2
            float r2 = r3.mLastMotionX
            float r0 = r0 - r2
            int r0 = (int) r0
            r3.moveX = r0
            int r0 = r3.getTop()
            float r0 = (float) r0
            float r4 = r4.getY()
            float r0 = r0 + r4
            float r4 = r3.mLastMotionY
            float r0 = r0 - r4
            int r4 = (int) r0
            r3.moveY = r4
            r3.onDraging()
            goto L41
        L3b:
            r3.onStopDrag()
            r4 = 0
            r3.isDraging = r4
        L41:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.lib.babel.task.view.DragContainer.onTouchEvent(android.view.MotionEvent):boolean");
    }

    public DragContainer(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public DragContainer(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.isDraging = false;
    }
}

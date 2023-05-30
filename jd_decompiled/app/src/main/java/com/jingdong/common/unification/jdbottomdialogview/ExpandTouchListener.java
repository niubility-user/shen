package com.jingdong.common.unification.jdbottomdialogview;

import android.animation.Animator;
import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;

/* loaded from: classes6.dex */
public class ExpandTouchListener implements View.OnTouchListener {
    private final AbsListView absListView;
    private final View contentContainer;
    private final int contentHeight;
    private final int defaultTranY;
    private final int expandTranY;
    private boolean fullScreen;
    private final GestureDetector gestureDetector;
    private IDismiss iDismiss;
    private boolean scrollUp;
    private boolean touchUp;
    private final String TAG = getClass().getSimpleName();
    private float lastY = 0.0f;
    private int scrollOffset1 = 50;
    private int scrollOffset2 = 150;

    /* loaded from: classes6.dex */
    public interface IDismiss {
        void accessDismiss();
    }

    private ExpandTouchListener(Context context, AbsListView absListView, View view, int i2, int i3, int i4, IDismiss iDismiss) {
        this.absListView = absListView;
        this.contentContainer = view;
        this.expandTranY = i2;
        this.defaultTranY = i3;
        this.contentHeight = i4;
        this.iDismiss = iDismiss;
        this.gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() { // from class: com.jingdong.common.unification.jdbottomdialogview.ExpandTouchListener.1
            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
            public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f2, float f3) {
                ExpandTouchListener.this.scrollUp = f3 > 0.0f;
                return false;
            }

            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
            public boolean onSingleTapUp(MotionEvent motionEvent) {
                return true;
            }
        });
    }

    public static ExpandTouchListener newListener(Context context, AbsListView absListView, View view, int i2, int i3, int i4, IDismiss iDismiss) {
        return new ExpandTouchListener(context, absListView, view, i2, i3, i4, iDismiss);
    }

    private void onTouchMove(float f2) {
        float f3 = this.lastY;
        float f4 = f3 != 0.0f ? f3 - f2 : 0.0f;
        this.lastY = f2;
        this.touchUp = f4 > 0.0f;
        int translationY = (int) (this.contentContainer.getTranslationY() - f4);
        int i2 = this.expandTranY;
        if (translationY < i2) {
            translationY = i2;
        }
        int i3 = this.contentHeight;
        if (translationY > i3) {
            translationY = i3;
        }
        this.contentContainer.setTranslationY(translationY);
        this.fullScreen = this.contentContainer.getTranslationY() == ((float) this.expandTranY);
    }

    private void onTouchUp() {
        IDismiss iDismiss;
        int i2;
        this.lastY = 0.0f;
        int translationY = (int) this.contentContainer.getTranslationY();
        boolean z = this.touchUp;
        boolean z2 = !z && translationY > (i2 = this.expandTranY) && translationY < i2 + this.scrollOffset1;
        boolean z3 = z && translationY < this.defaultTranY - this.scrollOffset1;
        if (!z2 && !z3) {
            boolean z4 = !z && translationY > this.expandTranY + this.scrollOffset1 && translationY < this.defaultTranY + this.scrollOffset2;
            boolean z5 = z && translationY >= this.defaultTranY - this.scrollOffset1;
            if (!z4 && !z5) {
                if (z || translationY < this.defaultTranY + this.scrollOffset2 || (iDismiss = this.iDismiss) == null) {
                    return;
                }
                iDismiss.accessDismiss();
                return;
            }
            JDBottomDialogViewUtil.startTransAnim(this.contentContainer, translationY, this.defaultTranY, new Animator.AnimatorListener() { // from class: com.jingdong.common.unification.jdbottomdialogview.ExpandTouchListener.3
                @Override // android.animation.Animator.AnimatorListener
                public void onAnimationCancel(Animator animator) {
                }

                @Override // android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator animator) {
                    ExpandTouchListener.this.fullScreen = false;
                }

                @Override // android.animation.Animator.AnimatorListener
                public void onAnimationRepeat(Animator animator) {
                }

                @Override // android.animation.Animator.AnimatorListener
                public void onAnimationStart(Animator animator) {
                }
            });
            return;
        }
        JDBottomDialogViewUtil.startTransAnim(this.contentContainer, translationY, this.expandTranY, new Animator.AnimatorListener() { // from class: com.jingdong.common.unification.jdbottomdialogview.ExpandTouchListener.2
            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                ExpandTouchListener.this.fullScreen = true;
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationRepeat(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
            }
        });
    }

    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (this.gestureDetector.onTouchEvent(motionEvent)) {
            return false;
        }
        if ((!this.scrollUp && JDBottomDialogViewUtil.listIsAtTop(this.absListView)) || !this.fullScreen) {
            float rawY = motionEvent.getRawY();
            int action = motionEvent.getAction();
            if (action == 0) {
                this.lastY = rawY;
                return true;
            }
            if (action == 1) {
                onTouchUp();
            } else if (action == 2) {
                if (this.contentContainer.getTranslationY() == this.expandTranY) {
                    this.contentContainer.setTranslationY(r1 + 1);
                    this.lastY = rawY;
                    return false;
                }
                onTouchMove(rawY);
            }
            return true;
        }
        return false;
    }

    public void reset() {
        this.fullScreen = false;
        this.scrollUp = false;
        this.touchUp = false;
        this.lastY = 0.0f;
    }
}

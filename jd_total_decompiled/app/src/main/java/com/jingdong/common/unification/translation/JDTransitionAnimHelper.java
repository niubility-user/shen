package com.jingdong.common.unification.translation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.ViewPropertyAnimator;

/* loaded from: classes6.dex */
public class JDTransitionAnimHelper {
    private Animator.AnimatorListener animatorListener;
    private int size = 0;
    private boolean isClear = false;

    static /* synthetic */ int access$110(JDTransitionAnimHelper jDTransitionAnimHelper) {
        int i2 = jDTransitionAnimHelper.size;
        jDTransitionAnimHelper.size = i2 - 1;
        return i2;
    }

    public void clear() {
        this.animatorListener = null;
        this.size = 0;
        this.isClear = true;
    }

    public void playTogether(Object... objArr) {
        for (Object obj : objArr) {
            if (obj instanceof Animator) {
                Animator animator = (Animator) obj;
                animator.addListener(this.animatorListener);
                animator.start();
                this.size++;
            } else if (obj instanceof ViewPropertyAnimator) {
                ViewPropertyAnimator viewPropertyAnimator = (ViewPropertyAnimator) obj;
                viewPropertyAnimator.setListener(this.animatorListener);
                viewPropertyAnimator.start();
                this.size++;
            }
        }
        if (this.size > 0) {
            this.isClear = false;
        }
    }

    public void setAnimatorListener(final Animator.AnimatorListener animatorListener) {
        this.animatorListener = new AnimatorListenerAdapter() { // from class: com.jingdong.common.unification.translation.JDTransitionAnimHelper.1
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                super.onAnimationEnd(animator);
                if (JDTransitionAnimHelper.this.isClear) {
                    return;
                }
                JDTransitionAnimHelper.access$110(JDTransitionAnimHelper.this);
                if (JDTransitionAnimHelper.this.size == 0) {
                    animatorListener.onAnimationEnd(animator);
                }
            }
        };
    }
}

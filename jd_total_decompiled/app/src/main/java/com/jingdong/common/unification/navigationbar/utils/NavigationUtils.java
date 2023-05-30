package com.jingdong.common.unification.navigationbar.utils;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.animation.LinearInterpolator;
import com.jingdong.common.unification.navigationbar.AnimationEndListener;
import com.jingdong.common.unification.navigationbar.NavigationConstants;

/* loaded from: classes6.dex */
public class NavigationUtils {
    public static ObjectAnimator startRotationAnimation(View view, final AnimationEndListener animationEndListener, ValueAnimator.AnimatorUpdateListener animatorUpdateListener) {
        try {
            new AnimatorSet();
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, "rotationY", -180.0f, 0.0f);
            ofFloat.setDuration(NavigationConstants.ANIMATION_TIME_SKU_ROTATION);
            ofFloat.setInterpolator(new LinearInterpolator());
            ofFloat.addListener(new Animator.AnimatorListener() { // from class: com.jingdong.common.unification.navigationbar.utils.NavigationUtils.1
                @Override // android.animation.Animator.AnimatorListener
                public void onAnimationCancel(Animator animator) {
                }

                @Override // android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator animator) {
                    AnimationEndListener animationEndListener2 = AnimationEndListener.this;
                    if (animationEndListener2 != null) {
                        animationEndListener2.onAnimationEnd();
                    }
                }

                @Override // android.animation.Animator.AnimatorListener
                public void onAnimationRepeat(Animator animator) {
                }

                @Override // android.animation.Animator.AnimatorListener
                public void onAnimationStart(Animator animator) {
                }
            });
            if (animatorUpdateListener != null) {
                ofFloat.addUpdateListener(animatorUpdateListener);
            }
            ofFloat.start();
            return ofFloat;
        } catch (Exception unused) {
            return null;
        }
    }
}

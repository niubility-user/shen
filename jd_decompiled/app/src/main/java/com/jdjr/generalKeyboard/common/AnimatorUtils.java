package com.jdjr.generalKeyboard.common;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;
import androidx.viewpager.widget.ViewPager;

/* loaded from: classes18.dex */
public class AnimatorUtils {
    public static void initDownAnimator(final View view, final View view2, float f2) {
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, "translationY", 0.0f, f2);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(300L);
        animatorSet.addListener(new AnimatorListenerAdapter() { // from class: com.jdjr.generalKeyboard.common.AnimatorUtils.2
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                View view3 = view;
                if (view3 instanceof ViewPager) {
                    ViewsUtils.removeAllFromParent((View) view3.getParent());
                } else {
                    ViewsUtils.removeFromParent(view2);
                }
                view.setLayerType(0, null);
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
                view.setLayerType(2, null);
            }
        });
        animatorSet.playTogether(ofFloat);
        animatorSet.start();
    }

    public static void initUpAnimator(final View view, float f2) {
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, "translationY", f2, 0.0f);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(300L);
        animatorSet.addListener(new AnimatorListenerAdapter() { // from class: com.jdjr.generalKeyboard.common.AnimatorUtils.1
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                view.setLayerType(0, null);
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
                view.setLayerType(2, null);
            }
        });
        animatorSet.playTogether(ofFloat);
        animatorSet.start();
    }
}

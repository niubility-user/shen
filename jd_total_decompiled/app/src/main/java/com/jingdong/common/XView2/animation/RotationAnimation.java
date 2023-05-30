package com.jingdong.common.XView2.animation;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.view.View;
import com.jingdong.common.XView2.utils.BaseRunnable;
import com.jingdong.common.XView2.utils.XView2Utils;

/* loaded from: classes5.dex */
public class RotationAnimation {
    private Activity mActivity;
    public ObjectAnimator mAnimRotation;
    private AnimatorSet mRotationAnimationSet;
    private View mTargetView;

    public RotationAnimation(View view) {
        this.mTargetView = view;
    }

    public void destroyAnimation() {
        AnimatorSet animatorSet = this.mRotationAnimationSet;
        if (animatorSet != null) {
            animatorSet.cancel();
            this.mRotationAnimationSet = null;
        }
        ObjectAnimator objectAnimator = this.mAnimRotation;
        if (objectAnimator != null) {
            objectAnimator.removeAllListeners();
            this.mAnimRotation = null;
        }
    }

    public void play(final Activity activity) {
        if (this.mAnimRotation == null) {
            this.mAnimRotation = ObjectAnimator.ofFloat(this.mTargetView, "rotationY", 0.0f, 360.0f);
        }
        this.mAnimRotation.setDuration(1000L);
        AnimatorSet animatorSet = this.mRotationAnimationSet;
        if (animatorSet != null) {
            animatorSet.cancel();
            this.mRotationAnimationSet.play(this.mAnimRotation);
        }
        this.mAnimRotation.removeAllListeners();
        this.mAnimRotation.addListener(new Animator.AnimatorListener() { // from class: com.jingdong.common.XView2.animation.RotationAnimation.1
            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                XView2Utils.runOnUiThread(new BaseRunnable() { // from class: com.jingdong.common.XView2.animation.RotationAnimation.1.1
                    @Override // com.jingdong.common.XView2.utils.BaseRunnable
                    protected void safeRun() {
                        AnonymousClass1 anonymousClass1 = AnonymousClass1.this;
                        RotationAnimation.this.startRotationAnimation(activity, null);
                    }
                }, 2000L);
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationRepeat(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
            }
        });
        AnimatorSet animatorSet2 = this.mRotationAnimationSet;
        if (animatorSet2 != null) {
            animatorSet2.start();
        }
    }

    public void startRotationAnimation(Activity activity, AnimateListener animateListener) {
        this.mActivity = activity;
        if (activity == null || activity.isFinishing() || this.mTargetView == null) {
            return;
        }
        if (this.mRotationAnimationSet == null) {
            this.mRotationAnimationSet = new AnimatorSet();
        }
        play(activity);
    }
}

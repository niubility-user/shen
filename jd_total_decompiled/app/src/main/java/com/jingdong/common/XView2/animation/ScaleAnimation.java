package com.jingdong.common.XView2.animation;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import com.jingdong.common.XView2.common.XView2Constants;
import com.jingdong.common.XView2.container.XView2Container;
import com.jingdong.common.XView2.utils.BaseRunnable;
import com.jingdong.common.XView2.utils.XView2Utils;
import com.jingdong.common.XView2.utils.log.XViewLogPrint;

/* loaded from: classes5.dex */
public class ScaleAnimation {
    ObjectAnimator animScaleX;
    ObjectAnimator animScaleY;
    public Activity mActivity;
    private ObjectAnimator mBackAnim;
    private boolean mIsNeedBackAnim;
    public long mScaleInDuration;
    private AnimatorSet mScaleInSet;
    public long mScaleOutDuration;
    private AnimatorSet mScaleOutSet;
    private View mTargetView;

    public ScaleAnimation(View view) {
        this.mScaleOutDuration = 320L;
        this.mScaleInDuration = 460L;
        this.mIsNeedBackAnim = false;
        this.mTargetView = view;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startBackFadeInAnimation(final View view, final long j2) {
        if (view == null) {
            return;
        }
        XView2Utils.runOnUiThread(new BaseRunnable() { // from class: com.jingdong.common.XView2.animation.ScaleAnimation.2
            @Override // com.jingdong.common.XView2.utils.BaseRunnable
            protected void safeRun() {
                if (ScaleAnimation.this.mBackAnim != null) {
                    ScaleAnimation.this.mBackAnim.cancel();
                }
                ScaleAnimation.this.mBackAnim = ObjectAnimator.ofFloat(view, "alpha", 0.0f, 1.0f);
                ScaleAnimation.this.mBackAnim.setInterpolator(new AccelerateInterpolator());
                ScaleAnimation.this.mBackAnim.setDuration(j2);
                ScaleAnimation.this.mBackAnim.addListener(new Animator.AnimatorListener() { // from class: com.jingdong.common.XView2.animation.ScaleAnimation.2.1
                    @Override // android.animation.Animator.AnimatorListener
                    public void onAnimationCancel(Animator animator) {
                    }

                    @Override // android.animation.Animator.AnimatorListener
                    public void onAnimationEnd(Animator animator) {
                        view.setAlpha(1.0f);
                    }

                    @Override // android.animation.Animator.AnimatorListener
                    public void onAnimationRepeat(Animator animator) {
                    }

                    @Override // android.animation.Animator.AnimatorListener
                    public void onAnimationStart(Animator animator) {
                    }
                });
                ScaleAnimation.this.mBackAnim.start();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startBackFadeOutAnimation(final View view, final long j2) {
        if (view == null) {
            return;
        }
        XView2Utils.runOnUiThread(new BaseRunnable() { // from class: com.jingdong.common.XView2.animation.ScaleAnimation.4
            @Override // com.jingdong.common.XView2.utils.BaseRunnable
            protected void safeRun() {
                if (ScaleAnimation.this.mBackAnim != null) {
                    ScaleAnimation.this.mBackAnim.cancel();
                }
                ScaleAnimation.this.mBackAnim = ObjectAnimator.ofFloat(view, "alpha", 1.0f, 0.0f);
                ScaleAnimation.this.mBackAnim.setInterpolator(new DecelerateInterpolator());
                ScaleAnimation.this.mBackAnim.setDuration(j2);
                ScaleAnimation.this.mBackAnim.start();
            }
        });
    }

    public void destroyAnimation() {
        AnimatorSet animatorSet = this.mScaleOutSet;
        if (animatorSet != null) {
            animatorSet.cancel();
            this.mScaleOutSet = null;
        }
        AnimatorSet animatorSet2 = this.mScaleInSet;
        if (animatorSet2 != null) {
            animatorSet2.cancel();
            this.mScaleInSet = null;
        }
        ObjectAnimator objectAnimator = this.mBackAnim;
        if (objectAnimator != null) {
            objectAnimator.cancel();
            this.mBackAnim = null;
        }
    }

    public void startScaleInAnimation(Activity activity, AnimateListener animateListener) {
        startScaleInAnimation(activity, this.mTargetView.getWidth() >> 1, this.mTargetView.getHeight(), animateListener);
    }

    public void startScaleInAnimationInCenter(Activity activity, AnimateListener animateListener) {
        startScaleInAnimation(activity, this.mTargetView.getWidth() >> 1, this.mTargetView.getHeight() >> 1, animateListener);
    }

    public void startScaleOutAnimation(Activity activity, AnimateListener animateListener) {
        startScaleOutAnimation(activity, this.mTargetView.getWidth() >> 1, this.mTargetView.getHeight(), animateListener);
    }

    public void startScaleOutAnimationInCenter(Activity activity, AnimateListener animateListener) {
        startScaleOutAnimation(activity, this.mTargetView.getWidth() >> 1, this.mTargetView.getHeight() >> 1, animateListener);
    }

    public void startScaleInAnimation(Activity activity, final float f2, final float f3, final AnimateListener animateListener) {
        this.mActivity = activity;
        if (activity == null || activity.isFinishing() || this.mTargetView == null) {
            return;
        }
        if (this.mScaleInSet == null) {
            this.mScaleInSet = new AnimatorSet();
        }
        this.mScaleInSet.cancel();
        XView2Utils.runOnUiThread(new BaseRunnable() { // from class: com.jingdong.common.XView2.animation.ScaleAnimation.3
            @Override // com.jingdong.common.XView2.utils.BaseRunnable
            protected void safeRun() {
                if (ScaleAnimation.this.mTargetView != null && f2 >= 0.0f && f3 >= 0.0f) {
                    ScaleAnimation.this.mTargetView.setPivotX(f2);
                    ScaleAnimation.this.mTargetView.setPivotY(f3);
                }
                XViewLogPrint.JDXLog.e(XView2Constants.TAG, "starScaleInAnimation");
                ObjectAnimator ofFloat = ObjectAnimator.ofFloat(ScaleAnimation.this.mTargetView, "ScaleX", 1.0f, 0.0f);
                ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(ScaleAnimation.this.mTargetView, "ScaleY", 1.0f, 0.0f);
                ObjectAnimator ofFloat3 = ObjectAnimator.ofFloat(ScaleAnimation.this.mTargetView, "alpha", 1.0f, 0.0f);
                if (ScaleAnimation.this.mScaleInSet != null) {
                    ScaleAnimation.this.mScaleInSet.playTogether(ofFloat, ofFloat2, ofFloat3);
                    ScaleAnimation.this.mScaleInSet.setDuration(ScaleAnimation.this.mScaleInDuration);
                }
                ofFloat2.addListener(new Animator.AnimatorListener() { // from class: com.jingdong.common.XView2.animation.ScaleAnimation.3.1
                    @Override // android.animation.Animator.AnimatorListener
                    public void onAnimationCancel(Animator animator) {
                    }

                    @Override // android.animation.Animator.AnimatorListener
                    public void onAnimationEnd(Animator animator) {
                        AnimateListener animateListener2 = animateListener;
                        if (animateListener2 != null) {
                            animateListener2.onAnimateEnd();
                        }
                        ScaleAnimation.this.mTargetView.clearAnimation();
                    }

                    @Override // android.animation.Animator.AnimatorListener
                    public void onAnimationRepeat(Animator animator) {
                    }

                    @Override // android.animation.Animator.AnimatorListener
                    public void onAnimationStart(Animator animator) {
                        AnonymousClass3 anonymousClass3 = AnonymousClass3.this;
                        AnimateListener animateListener2 = animateListener;
                        if (animateListener2 != null) {
                            animateListener2.onAnimateStart(ScaleAnimation.this.mScaleInDuration);
                        }
                    }
                });
                if (ScaleAnimation.this.mScaleInSet != null) {
                    ScaleAnimation.this.mScaleInSet.start();
                }
                if (ScaleAnimation.this.mIsNeedBackAnim && (ScaleAnimation.this.mTargetView.getParent() instanceof XView2Container)) {
                    ScaleAnimation scaleAnimation = ScaleAnimation.this;
                    scaleAnimation.startBackFadeOutAnimation((View) scaleAnimation.mTargetView.getParent(), ScaleAnimation.this.mScaleInDuration);
                }
            }
        });
    }

    public void startScaleOutAnimation(Activity activity, final float f2, final float f3, final AnimateListener animateListener) {
        this.mActivity = activity;
        if (activity == null || activity.isFinishing() || this.mTargetView == null) {
            return;
        }
        if (this.mScaleOutSet == null) {
            this.mScaleOutSet = new AnimatorSet();
        }
        this.mScaleOutSet.cancel();
        this.mTargetView.setAlpha(0.0f);
        XView2Utils.runOnUiThread(new BaseRunnable() { // from class: com.jingdong.common.XView2.animation.ScaleAnimation.1
            @Override // com.jingdong.common.XView2.utils.BaseRunnable
            protected void safeRun() {
                if (ScaleAnimation.this.mTargetView != null && f2 >= 0.0f && f3 >= 0.0f) {
                    ScaleAnimation.this.mTargetView.setPivotX(f2);
                    ScaleAnimation.this.mTargetView.setPivotY(f3);
                }
                ScaleAnimation scaleAnimation = ScaleAnimation.this;
                scaleAnimation.animScaleX = ObjectAnimator.ofFloat(scaleAnimation.mTargetView, "ScaleX", 0.0f, 1.0f);
                ScaleAnimation scaleAnimation2 = ScaleAnimation.this;
                scaleAnimation2.animScaleY = ObjectAnimator.ofFloat(scaleAnimation2.mTargetView, "ScaleY", 0.0f, 1.0f);
                ObjectAnimator ofFloat = ObjectAnimator.ofFloat(ScaleAnimation.this.mTargetView, "alpha", 0.0f, 1.0f);
                if (ScaleAnimation.this.mScaleOutSet != null) {
                    AnimatorSet animatorSet = ScaleAnimation.this.mScaleOutSet;
                    ScaleAnimation scaleAnimation3 = ScaleAnimation.this;
                    animatorSet.playTogether(scaleAnimation3.animScaleX, scaleAnimation3.animScaleY, ofFloat);
                    ScaleAnimation.this.mScaleOutSet.setDuration(ScaleAnimation.this.mScaleOutDuration);
                }
                ScaleAnimation.this.animScaleY.addListener(new Animator.AnimatorListener() { // from class: com.jingdong.common.XView2.animation.ScaleAnimation.1.1
                    @Override // android.animation.Animator.AnimatorListener
                    public void onAnimationCancel(Animator animator) {
                    }

                    @Override // android.animation.Animator.AnimatorListener
                    public void onAnimationEnd(Animator animator) {
                        XViewLogPrint.JDXLog.e(XView2Constants.TAG, "onAnimationEnd");
                        ScaleAnimation.this.mTargetView.setAlpha(1.0f);
                        AnimateListener animateListener2 = animateListener;
                        if (animateListener2 != null) {
                            animateListener2.onAnimateEnd();
                        }
                        ScaleAnimation.this.mTargetView.clearAnimation();
                    }

                    @Override // android.animation.Animator.AnimatorListener
                    public void onAnimationRepeat(Animator animator) {
                    }

                    @Override // android.animation.Animator.AnimatorListener
                    public void onAnimationStart(Animator animator) {
                        XViewLogPrint.JDXLog.e(XView2Constants.TAG, "onAnimationStart");
                        AnonymousClass1 anonymousClass1 = AnonymousClass1.this;
                        AnimateListener animateListener2 = animateListener;
                        if (animateListener2 != null) {
                            animateListener2.onAnimateStart(ScaleAnimation.this.mScaleOutDuration);
                        }
                    }
                });
                if (ScaleAnimation.this.mScaleOutSet != null) {
                    ScaleAnimation.this.mScaleOutSet.start();
                }
                if (ScaleAnimation.this.mIsNeedBackAnim && (ScaleAnimation.this.mTargetView.getParent() instanceof XView2Container)) {
                    ScaleAnimation scaleAnimation4 = ScaleAnimation.this;
                    scaleAnimation4.startBackFadeInAnimation((View) scaleAnimation4.mTargetView.getParent(), ScaleAnimation.this.mScaleOutDuration);
                }
            }
        });
    }

    public ScaleAnimation(View view, boolean z) {
        this.mScaleOutDuration = 320L;
        this.mScaleInDuration = 460L;
        this.mIsNeedBackAnim = false;
        this.mTargetView = view;
        this.mIsNeedBackAnim = z;
    }
}

package com.jingdong.common.XView2.animation;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.view.View;
import com.airbnb.lottie.LottieComposition;
import com.jingdong.app.mall.bundle.xanimation.d;
import com.jingdong.app.mall.bundle.xanimation.e;
import com.jingdong.app.mall.bundle.xanimation.interfaces.IXAnimationListener;
import com.jingdong.app.mall.bundle.xanimation.interfaces.b;
import com.jingdong.common.XView2.utils.BaseRunnable;
import com.jingdong.common.XView2.utils.XView2Utils;
import com.jingdong.common.utils.SwitchQueryFetcher;

/* loaded from: classes5.dex */
public class FadeAnimation {
    private static final String X_ANIMATION_SWITCH = "XAnimationSwitch";
    private Activity mActivity;
    private ObjectAnimator mAlphaAni;
    private View mTargetView;
    private d mXAnimation;

    public FadeAnimation(View view) {
        this.mTargetView = view;
    }

    public void destroyAnimation() {
        ObjectAnimator objectAnimator = this.mAlphaAni;
        if (objectAnimator != null) {
            objectAnimator.cancel();
            this.mAlphaAni = null;
        }
        d dVar = this.mXAnimation;
        if (dVar != null) {
            dVar.h();
            this.mXAnimation = null;
        }
    }

    public void startFadeInAnimation(Activity activity, final AnimateListener animateListener) {
        View view;
        this.mActivity = activity;
        if (activity == null || activity.isFinishing() || (view = this.mTargetView) == null) {
            return;
        }
        view.setAlpha(0.0f);
        XView2Utils.runOnUiThread(new BaseRunnable() { // from class: com.jingdong.common.XView2.animation.FadeAnimation.1
            @Override // com.jingdong.common.XView2.utils.BaseRunnable
            protected void safeRun() {
                if (SwitchQueryFetcher.getSwitchBooleanValue(FadeAnimation.X_ANIMATION_SWITCH, false)) {
                    if (FadeAnimation.this.mXAnimation == null) {
                        FadeAnimation.this.mXAnimation = new d();
                    }
                    FadeAnimation.this.mXAnimation.k(new b() { // from class: com.jingdong.common.XView2.animation.FadeAnimation.1.1
                        @Override // com.jingdong.app.mall.bundle.xanimation.interfaces.b
                        public /* bridge */ /* synthetic */ void onAnimationCancel(Animator animator) {
                            IXAnimationListener.a(this, animator);
                        }

                        @Override // com.jingdong.app.mall.bundle.xanimation.interfaces.b
                        public void onAnimationEnd(Animator animator) {
                            FadeAnimation.this.mTargetView.setAlpha(1.0f);
                            AnimateListener animateListener2 = animateListener;
                            if (animateListener2 != null) {
                                animateListener2.onAnimateEnd();
                            }
                        }

                        @Override // com.jingdong.app.mall.bundle.xanimation.interfaces.b
                        public void onAnimationError(Throwable th) {
                            AnimateListener animateListener2 = animateListener;
                            if (animateListener2 != null) {
                                animateListener2.onAnimateError();
                            }
                        }

                        @Override // com.jingdong.app.mall.bundle.xanimation.interfaces.b
                        public /* bridge */ /* synthetic */ void onAnimationRepeat(Animator animator) {
                            IXAnimationListener.b(this, animator);
                        }

                        @Override // com.jingdong.app.mall.bundle.xanimation.interfaces.b
                        public void onAnimationStart(Animator animator) {
                            AnimateListener animateListener2 = animateListener;
                            if (animateListener2 == null || animator == null) {
                                return;
                            }
                            animateListener2.onAnimateStart(animator.getDuration());
                        }

                        @Override // com.jingdong.app.mall.bundle.xanimation.interfaces.b
                        public /* bridge */ /* synthetic */ void onAnimationUpdate(float f2) {
                            IXAnimationListener.c(this, f2);
                        }

                        @Override // com.jingdong.app.mall.bundle.xanimation.interfaces.b
                        public /* bridge */ /* synthetic */ void onLayerStatusListener(String str, int i2, int i3) {
                            IXAnimationListener.d(this, str, i2, i3);
                        }

                        @Override // com.jingdong.app.mall.bundle.xanimation.interfaces.b
                        public /* bridge */ /* synthetic */ void onLottieLoadResult(LottieComposition lottieComposition) {
                            IXAnimationListener.e(this, lottieComposition);
                        }
                    });
                    e eVar = new e();
                    eVar.b("fade_in_alpha.json");
                    eVar.d(FadeAnimation.this.mTargetView);
                    FadeAnimation.this.mXAnimation.l(FadeAnimation.this.mActivity, eVar);
                    return;
                }
                if (FadeAnimation.this.mAlphaAni == null) {
                    FadeAnimation fadeAnimation = FadeAnimation.this;
                    fadeAnimation.mAlphaAni = ObjectAnimator.ofFloat(fadeAnimation.mTargetView, "alpha", 0.0f, 1.0f);
                }
                FadeAnimation.this.mAlphaAni.setDuration(500L);
                FadeAnimation.this.mAlphaAni.addListener(new Animator.AnimatorListener() { // from class: com.jingdong.common.XView2.animation.FadeAnimation.1.2
                    @Override // android.animation.Animator.AnimatorListener
                    public void onAnimationCancel(Animator animator) {
                    }

                    @Override // android.animation.Animator.AnimatorListener
                    public void onAnimationEnd(Animator animator) {
                        FadeAnimation.this.mTargetView.setAlpha(1.0f);
                        AnimateListener animateListener2 = animateListener;
                        if (animateListener2 != null) {
                            animateListener2.onAnimateEnd();
                        }
                    }

                    @Override // android.animation.Animator.AnimatorListener
                    public void onAnimationRepeat(Animator animator) {
                    }

                    @Override // android.animation.Animator.AnimatorListener
                    public void onAnimationStart(Animator animator) {
                        AnimateListener animateListener2 = animateListener;
                        if (animateListener2 == null || animator == null) {
                            return;
                        }
                        animateListener2.onAnimateStart(animator.getDuration());
                    }
                });
                FadeAnimation.this.mAlphaAni.start();
            }
        });
    }
}

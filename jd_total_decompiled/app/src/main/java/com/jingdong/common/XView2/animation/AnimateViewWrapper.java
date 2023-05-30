package com.jingdong.common.XView2.animation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.graphics.RectF;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;
import com.airbnb.lottie.LottieAnimationView;
import com.jingdong.common.XView2.common.XView2Constants;
import com.jingdong.common.XView2.container.XView2Container;
import com.jingdong.common.XView2.utils.XView2Utils;
import com.jingdong.common.XView2.utils.log.XViewLogPrint;
import com.jingdong.sdk.oklog.OKLog;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes5.dex */
public class AnimateViewWrapper {
    private static final String TAG = "AnimateViewWrapper";
    public Activity mActivity;
    private AnimatorSet mAnimatorSet;
    private AtomicBoolean mHasShow = new AtomicBoolean(false);
    private LottieAnimationView mLottieView;
    private RectF mTargetRectF;
    private ViewGroup mTargetView;
    int[] position;

    public AnimateViewWrapper(ViewGroup viewGroup, RectF rectF) {
        this.mTargetView = viewGroup;
        this.mTargetRectF = rectF;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showLottieView(final AnimateListener animateListener) {
        if (this.mTargetRectF == null || this.mTargetView == null || this.mActivity == null) {
            return;
        }
        try {
            this.mLottieView = new LottieAnimationView(this.mActivity);
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(XView2Utils.getSizeBy750(250), XView2Utils.getSizeBy750(250));
            layoutParams.leftMargin = ((int) this.mTargetRectF.centerX()) - (XView2Utils.getSizeBy750(250) / 2);
            layoutParams.topMargin = ((int) this.mTargetRectF.centerY()) - (XView2Utils.getSizeBy750(250) / 2);
            ViewGroup viewGroup = this.mTargetView;
            if (viewGroup instanceof XView2Container) {
                ((XView2Container) viewGroup).addView(this.mLottieView, layoutParams);
            } else {
                ((XView2Container) viewGroup.getParent()).addView(this.mLottieView, layoutParams);
            }
            XViewLogPrint.JDXLog.e(XView2Constants.TAG, "showLottieView");
            String assetsString = XView2Utils.getAssetsString("local/xviewLight.json");
            if (!TextUtils.isEmpty(assetsString) && XView2Utils.isValid(assetsString)) {
                this.mLottieView.setAnimation("local/xviewLight.json");
                this.mLottieView.playAnimation();
            } else if (animateListener != null) {
                animateListener.onAnimateError();
            }
            this.mLottieView.addAnimatorListener(new AnimatorListenerAdapter() { // from class: com.jingdong.common.XView2.animation.AnimateViewWrapper.2
                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator animator) {
                    Activity activity;
                    super.onAnimationEnd(animator);
                    if (animateListener == null || (activity = AnimateViewWrapper.this.mActivity) == null || activity.isFinishing()) {
                        return;
                    }
                    animateListener.onAnimateEnd();
                }
            });
        } catch (Exception e2) {
            if (OKLog.D) {
                OKLog.d(TAG, "show lottieView  from local error : " + e2.getMessage());
            }
            if (animateListener != null) {
                animateListener.onAnimateError();
            }
        }
    }

    public void destroyAnimation() {
        AnimatorSet animatorSet = this.mAnimatorSet;
        if (animatorSet != null) {
            animatorSet.cancel();
            this.mAnimatorSet = null;
        }
        LottieAnimationView lottieAnimationView = this.mLottieView;
        if (lottieAnimationView != null) {
            lottieAnimationView.cancelAnimation();
            this.mLottieView.destroyDrawingCache();
            this.mLottieView = null;
        }
    }

    public void startAnimation(Activity activity, final AnimateListener animateListener) {
        final ViewGroup viewGroup;
        this.mActivity = activity;
        if (activity == null || activity.isFinishing() || this.mTargetRectF == null || (viewGroup = this.mTargetView) == null) {
            return;
        }
        viewGroup.post(new Runnable() { // from class: com.jingdong.common.XView2.animation.AnimateViewWrapper.1
            @Override // java.lang.Runnable
            public void run() {
                Activity activity2 = AnimateViewWrapper.this.mActivity;
                if (activity2 == null || activity2.isFinishing()) {
                    return;
                }
                int[] iArr = new int[2];
                AnimateViewWrapper.this.position = iArr;
                viewGroup.getLocationOnScreen(iArr);
                ObjectAnimator ofFloat = ObjectAnimator.ofFloat(viewGroup, View.TRANSLATION_X, 0.0f, AnimateViewWrapper.this.mTargetRectF.centerX() - (AnimateViewWrapper.this.position[0] + (viewGroup.getWidth() / 2)));
                long j2 = 400;
                ofFloat.setDuration(j2);
                ofFloat.setInterpolator(new LinearInterpolator());
                XViewLogPrint.JDXLog.e(XView2Constants.TAG, "position[0] " + AnimateViewWrapper.this.position[0] + "position[1] " + AnimateViewWrapper.this.position[1]);
                ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(viewGroup, View.TRANSLATION_Y, 0.0f, AnimateViewWrapper.this.mTargetRectF.centerY() - ((float) (AnimateViewWrapper.this.position[1] + (viewGroup.getHeight() / 2))));
                ofFloat2.setDuration(j2);
                ofFloat2.setInterpolator(new LinearInterpolator());
                ObjectAnimator ofFloat3 = ObjectAnimator.ofFloat(viewGroup, View.SCALE_X, 1.0f, 1.1f, 0.0f);
                long j3 = (long) 800;
                ofFloat3.setDuration(j3);
                ofFloat3.setInterpolator(new DecelerateInterpolator());
                ObjectAnimator ofFloat4 = ObjectAnimator.ofFloat(viewGroup, View.SCALE_Y, 1.0f, 1.1f, 0.0f);
                ofFloat4.setDuration(j3);
                ofFloat4.setInterpolator(new DecelerateInterpolator());
                ObjectAnimator ofFloat5 = ObjectAnimator.ofFloat(viewGroup, "alpha", 1.0f, 0.0f);
                ofFloat5.setDuration(j3);
                ObjectAnimator ofFloat6 = AnimateViewWrapper.this.mTargetView instanceof XView2Container ? ObjectAnimator.ofFloat(AnimateViewWrapper.this.mTargetView, "alpha", 1.0f, 0.0f) : ObjectAnimator.ofFloat((ViewGroup) AnimateViewWrapper.this.mTargetView.getParent(), "alpha", 1.0f, 0.0f);
                ofFloat6.setDuration(j3);
                AnimateViewWrapper.this.mAnimatorSet = new AnimatorSet();
                AnimateViewWrapper.this.mAnimatorSet.play(ofFloat3).with(ofFloat4);
                AnimateViewWrapper.this.mAnimatorSet.play(ofFloat).with(ofFloat2);
                AnimateViewWrapper.this.mAnimatorSet.play(ofFloat5);
                AnimateViewWrapper.this.mAnimatorSet.play(ofFloat6);
                AnimateViewWrapper.this.mAnimatorSet.setDuration(1100L);
                ofFloat3.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.jingdong.common.XView2.animation.AnimateViewWrapper.1.1
                    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
                        AnimateListener animateListener2;
                        Activity activity3 = AnimateViewWrapper.this.mActivity;
                        if (activity3 == null || activity3.isFinishing()) {
                            return;
                        }
                        long currentPlayTime = valueAnimator.getCurrentPlayTime();
                        if (currentPlayTime == 0 && (animateListener2 = animateListener) != null) {
                            animateListener2.onAnimateStart(1100L);
                        }
                        if (currentPlayTime < 600 || AnimateViewWrapper.this.mHasShow.get()) {
                            return;
                        }
                        AnonymousClass1 anonymousClass1 = AnonymousClass1.this;
                        AnimateViewWrapper.this.showLottieView(animateListener);
                        AnimateViewWrapper.this.mHasShow.set(true);
                    }
                });
                if (AnimateViewWrapper.this.mAnimatorSet != null) {
                    AnimateViewWrapper.this.mAnimatorSet.start();
                }
            }
        });
    }
}

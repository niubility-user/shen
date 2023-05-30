package com.jingdong.sdk.jshopsdk.common.favo;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import com.jingdong.sdk.jshopsdk.R;
import com.jingdong.sdk.oklog.OKLog;
import com.jingdong.sdk.utils.DPIUtil;

/* loaded from: classes7.dex */
public abstract class JShopAnimationToast {
    private static final String TAG = "JShopAnimationToast";
    private Activity activity;
    private AnimationDrawable animatinoDrawable;
    private int mAnimationDrawableDuration;
    protected View mPushUpView;
    private View mView;
    private WindowManager mWindowManager;
    private WindowManager.LayoutParams mWindowManagerParams;
    private int mPushUpDuration = 300;
    private Handler handler = new Handler();

    public JShopAnimationToast(Activity activity) {
        this.activity = activity;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dismiss() {
        this.mWindowManager.removeViewImmediate(this.mView);
        this.handler.postDelayed(new Runnable() { // from class: com.jingdong.sdk.jshopsdk.common.favo.JShopAnimationToast.3
            @Override // java.lang.Runnable
            public void run() {
                JShopAnimationToastManager.getInstance().onEnd(JShopAnimationToast.this);
            }
        }, 300L);
    }

    private void displayAnimation() {
        this.animatinoDrawable.stop();
        this.animatinoDrawable.start();
        this.mPushUpView.setVisibility(4);
        this.handler.postDelayed(new Runnable() { // from class: com.jingdong.sdk.jshopsdk.common.favo.JShopAnimationToast.2
            @Override // java.lang.Runnable
            public void run() {
                JShopAnimationToast.this.mPushUpView.setVisibility(0);
                JShopAnimationToast.this.displayPushUpAnimation();
            }
        }, this.mAnimationDrawableDuration + 10);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void displayPushUpAnimation() {
        pushUpView(this.mPushUpView);
    }

    private int getTotalDurationOfAnimation(AnimationDrawable animationDrawable) {
        int i2 = 0;
        for (int i3 = 0; i3 < animationDrawable.getNumberOfFrames(); i3++) {
            i2 += animationDrawable.getDuration(i3);
        }
        return i2;
    }

    private void init() {
        if (this.mView == null) {
            View createContentView = createContentView(this.activity.getLayoutInflater(), null);
            this.mView = createContentView;
            this.animatinoDrawable = (AnimationDrawable) createContentView.getBackground();
            this.mWindowManager = (WindowManager) this.mView.getContext().getApplicationContext().getSystemService("window");
            WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
            this.mWindowManagerParams = layoutParams;
            layoutParams.height = -2;
            layoutParams.width = -2;
            layoutParams.flags = 152;
            layoutParams.format = -3;
            layoutParams.windowAnimations = getWindowAnimatino();
            WindowManager.LayoutParams layoutParams2 = this.mWindowManagerParams;
            layoutParams2.type = 2005;
            layoutParams2.gravity = 17;
        }
    }

    private void pushUpView(View view) {
        view.clearAnimation();
        TranslateAnimation translateAnimation = new TranslateAnimation(0.0f, 0.0f, DPIUtil.dip2px(120.0f), 0.0f);
        view.setAnimation(translateAnimation);
        translateAnimation.setDuration(this.mPushUpDuration);
        translateAnimation.setAnimationListener(new Animation.AnimationListener() { // from class: com.jingdong.sdk.jshopsdk.common.favo.JShopAnimationToast.4
            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationEnd(Animation animation) {
                JShopAnimationToast.this.handler.postDelayed(new Runnable() { // from class: com.jingdong.sdk.jshopsdk.common.favo.JShopAnimationToast.4.1
                    @Override // java.lang.Runnable
                    public void run() {
                        JShopAnimationToast.this.dismiss();
                    }
                }, JShopAnimationToast.this.getPushUpStayDuration());
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationRepeat(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationStart(Animation animation) {
            }
        });
        translateAnimation.startNow();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void addShowTask(Object obj) {
        JShopAnimationToastManager.getInstance().add(this, obj);
    }

    protected abstract View createContentView(LayoutInflater layoutInflater, ViewGroup viewGroup);

    protected int getPushUpStayDuration() {
        return 1000;
    }

    protected int getWindowAnimatino() {
        return R.style.jshop_windows_anim;
    }

    public void showData(final Object obj) {
        this.handler.post(new Runnable() { // from class: com.jingdong.sdk.jshopsdk.common.favo.JShopAnimationToast.1
            @Override // java.lang.Runnable
            public void run() {
                JShopAnimationToast.this.showTask(obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void showTask(Object obj) {
        try {
            init();
            this.mView.clearAnimation();
            this.mView.setBackgroundDrawable(this.animatinoDrawable);
            this.mWindowManager.addView(this.mView, this.mWindowManagerParams);
            this.mAnimationDrawableDuration = getTotalDurationOfAnimation(this.animatinoDrawable);
            displayAnimation();
        } catch (Exception e2) {
            OKLog.e(TAG, e2);
        }
    }
}

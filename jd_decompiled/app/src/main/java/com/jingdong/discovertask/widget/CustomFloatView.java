package com.jingdong.discovertask.widget;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.GravityCompat;
import com.jingdong.corelib.utils.Log;
import com.jingdong.discovertask.model.inter.OnTimeDownFinishListener;
import com.jingdong.discovertask.model.inter.OnTimeStatusChangedListener;
import com.jingdong.sdk.utils.DPIUtil;

/* loaded from: classes12.dex */
public class CustomFloatView extends FrameLayout {
    public static boolean HAS_PLAY_ROTATE = false;
    public static final String TAG = "CustomFloatView";
    private int[] floatViewPos;
    private boolean hasReplaceIconCoor;
    private AnimatorSet mAnimatorSet;
    private Context mContext;
    private OnTimeDownFinishListener mFinishListener;
    private TaskIconView mFloatView;
    private long mFurlDuring;
    private String mIconUrl;
    private View.OnClickListener mOnClickListener;
    private long mRotateDuring;
    private TimeDownView mTimeDownView;
    private OnTimeStatusChangedListener mTimeStatusChangedListener;
    private long mTranslateDuring;

    public CustomFloatView(@NonNull Context context) {
        this(context, null);
    }

    private ObjectAnimator createRotateAnim() {
        TaskIconView taskIconView = this.mFloatView;
        if (taskIconView == null) {
            return null;
        }
        if (taskIconView.getAnimation() != null) {
            this.mFloatView.getAnimation().cancel();
        }
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.mFloatView, "rotation", -10.0f, 10.0f);
        ofFloat.setRepeatMode(2);
        ofFloat.setRepeatCount(5);
        ofFloat.setDuration(this.mRotateDuring);
        ofFloat.addListener(new Animator.AnimatorListener() { // from class: com.jingdong.discovertask.widget.CustomFloatView.5
            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                if (CustomFloatView.this.mFloatView != null) {
                    CustomFloatView.this.mFloatView.setRotation(0.0f);
                }
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationRepeat(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
            }
        });
        return ofFloat;
    }

    private void init() {
        this.floatViewPos = new int[2];
    }

    private void initDragFloatIcon() {
    }

    private void initTaskIconFloatView() {
        this.mFloatView = new TaskIconView(this.mContext).initWithUrl(this.mIconUrl);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(DPIUtil.dip2px(72.5f), DPIUtil.dip2px(53.5f));
        layoutParams.gravity = GravityCompat.END;
        this.mFloatView.setLayoutParams(layoutParams);
        this.mFloatView.setVisibility(0);
        this.mFloatView.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.discovertask.widget.CustomFloatView.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (CustomFloatView.this.mOnClickListener != null) {
                    CustomFloatView.this.mOnClickListener.onClick(view);
                }
            }
        });
        addView(this.mFloatView);
        playRotateAnim();
    }

    private void initTimeDownView(int i2, int i3) {
        TimeDownView initWithStyle = new TimeDownView(this.mContext).finishListener(this.mFinishListener).iconUrl(this.mIconUrl).setTimeStatusChangedListener(this.mTimeStatusChangedListener).gravity(i3).setOnFloatViewClickListener(this.mOnClickListener).initWithStyle(i2);
        this.mTimeDownView = initWithStyle;
        addView(initWithStyle);
    }

    private void setTaskIconCoord() {
        if (this.mFloatView == null || this.hasReplaceIconCoor || !(getContext() instanceof Activity)) {
            return;
        }
        int appHeight = DPIUtil.getAppHeight((Activity) getContext()) / 2;
        getTop();
        Rect rect = new Rect();
        getGlobalVisibleRect(rect);
        int i2 = rect.top;
        if (Log.D) {
            Log.e(TAG, "setTaskIconCoord: appMid = " + appHeight + ", viewTop = " + i2 + ", rect = " + getTop());
        }
        if (this.mFloatView.getLayoutParams() instanceof FrameLayout.LayoutParams) {
            ((FrameLayout.LayoutParams) this.mFloatView.getLayoutParams()).topMargin = appHeight - i2;
            this.mFloatView.requestLayout();
            this.hasReplaceIconCoor = true;
        }
    }

    public TaskIconView getFloatView() {
        return this.mFloatView;
    }

    public TimeDownView getTimeDownView() {
        return this.mTimeDownView;
    }

    public void hideHalf() {
        TaskIconView taskIconView = this.mFloatView;
        if (taskIconView == null) {
            return;
        }
        if (taskIconView.getAnimation() != null) {
            this.mFloatView.getAnimation().cancel();
        }
        ValueAnimator ofFloat = ValueAnimator.ofFloat(this.mFloatView.getTranslationX(), (this.mFloatView.getWidth() / 2) + ((ViewGroup.MarginLayoutParams) this.mFloatView.getLayoutParams()).rightMargin);
        ofFloat.setDuration(this.mTranslateDuring);
        ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.jingdong.discovertask.widget.CustomFloatView.3
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                if (CustomFloatView.this.mFloatView != null) {
                    CustomFloatView.this.mFloatView.setTranslationX(floatValue);
                }
            }
        });
        ofFloat.start();
    }

    public void iconAnimToRightBottom(final int i2, final int i3) {
        if (this.mFloatView == null) {
            return;
        }
        setVisibility(0);
        this.mFloatView.setVisibility(0);
        Rect rect = new Rect();
        this.mFloatView.getGlobalVisibleRect(rect);
        int[] iArr = this.floatViewPos;
        iArr[0] = rect.left;
        iArr[1] = rect.top;
        final int i4 = iArr[0];
        this.mFloatView.setTranslationX(iArr[0] + i2);
        this.mFloatView.setTranslationY(iArr[1] + i3);
        ValueAnimator ofPropertyValuesHolder = ValueAnimator.ofPropertyValuesHolder(PropertyValuesHolder.ofFloat("alpha", 0.0f, 1.0f), PropertyValuesHolder.ofFloat("scaleX", 0.2f, 1.0f), PropertyValuesHolder.ofFloat("scaleY", 0.2f, 1.0f));
        ofPropertyValuesHolder.setInterpolator(new LinearInterpolator());
        ofPropertyValuesHolder.setDuration(this.mFurlDuring);
        ofPropertyValuesHolder.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.jingdong.discovertask.widget.CustomFloatView.4
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float floatValue = ((Float) valueAnimator.getAnimatedValue("alpha")).floatValue();
                float floatValue2 = ((Float) valueAnimator.getAnimatedValue("scaleX")).floatValue();
                float floatValue3 = ((Float) valueAnimator.getAnimatedValue("scaleY")).floatValue();
                float animatedFraction = valueAnimator.getAnimatedFraction();
                float f2 = 1.0f - animatedFraction;
                double d = f2;
                double pow = Math.pow(d, 2.0d);
                double d2 = i2;
                Double.isNaN(d2);
                double d3 = pow * d2;
                float f3 = 2.0f * animatedFraction * f2;
                double d4 = i4 * f3;
                Double.isNaN(d4);
                double d5 = d3 + d4;
                double d6 = animatedFraction;
                double pow2 = Math.pow(d6, 2.0d);
                double d7 = CustomFloatView.this.floatViewPos[0];
                Double.isNaN(d7);
                float f4 = (float) (d5 + (pow2 * d7));
                double pow3 = Math.pow(d, 2.0d);
                double d8 = i3;
                Double.isNaN(d8);
                double d9 = pow3 * d8;
                double d10 = f3 * i3;
                Double.isNaN(d10);
                double pow4 = Math.pow(d6, 2.0d);
                double d11 = CustomFloatView.this.floatViewPos[1];
                Double.isNaN(d11);
                CustomFloatView.this.mFloatView.setTranslationX(f4 - CustomFloatView.this.floatViewPos[0]);
                CustomFloatView.this.mFloatView.setTranslationY(((float) ((d9 + d10) + (pow4 * d11))) - CustomFloatView.this.floatViewPos[1]);
                CustomFloatView.this.mFloatView.setAlpha(floatValue);
                CustomFloatView.this.mFloatView.setScaleX(floatValue2);
                CustomFloatView.this.mFloatView.setScaleY(floatValue3);
            }
        });
        AnimatorSet animatorSet = new AnimatorSet();
        this.mAnimatorSet = animatorSet;
        animatorSet.playSequentially(ofPropertyValuesHolder, createRotateAnim());
        this.mAnimatorSet.start();
    }

    public CustomFloatView iconUrl(String str) {
        this.mIconUrl = str;
        return this;
    }

    public CustomFloatView initWithStyle(int i2) {
        if (i2 == 1) {
            initTimeDownView(i2, 83);
        } else if (i2 == 2) {
            initTaskIconFloatView();
        } else if (i2 == 3) {
            initTimeDownView(i2, 8388661);
        }
        return this;
    }

    public CustomFloatView onClickListener(View.OnClickListener onClickListener) {
        this.mOnClickListener = onClickListener;
        return this;
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        super.onLayout(z, i2, i3, i4, i5);
        setTaskIconCoord();
    }

    public void pauseTimeDown() {
        TimeDownView timeDownView = this.mTimeDownView;
        if (timeDownView != null) {
            timeDownView.pauseTimeDown();
        }
    }

    public void playRotateAnim() {
        ObjectAnimator createRotateAnim;
        if (HAS_PLAY_ROTATE || (createRotateAnim = createRotateAnim()) == null) {
            return;
        }
        createRotateAnim.start();
        HAS_PLAY_ROTATE = true;
    }

    public void releaseResource() {
        TimeDownView timeDownView = this.mTimeDownView;
        if (timeDownView != null) {
            timeDownView.releaseResource();
            this.mTimeDownView.clearAnimation();
        }
        if (this.mContext != null) {
            this.mContext = null;
        }
        AnimatorSet animatorSet = this.mAnimatorSet;
        if (animatorSet != null) {
            animatorSet.cancel();
            this.mAnimatorSet = null;
        }
        TaskIconView taskIconView = this.mFloatView;
        if (taskIconView != null) {
            taskIconView.clearAnimation();
            this.mFloatView = null;
        }
        removeAllViews();
        clearAnimation();
    }

    public void resumeTimeDown() {
        TimeDownView timeDownView = this.mTimeDownView;
        if (timeDownView != null) {
            timeDownView.resumeTimeDown();
        }
    }

    public void setTimeStatusChangedListener(OnTimeStatusChangedListener onTimeStatusChangedListener) {
        this.mTimeStatusChangedListener = onTimeStatusChangedListener;
    }

    public void showComplete() {
        TaskIconView taskIconView = this.mFloatView;
        if (taskIconView == null) {
            return;
        }
        if (taskIconView.getAnimation() != null) {
            this.mFloatView.getAnimation().cancel();
        }
        this.mFloatView.setVisibility(0);
        ValueAnimator ofFloat = ValueAnimator.ofFloat(this.mFloatView.getTranslationX(), 0.0f);
        ofFloat.setDuration(this.mTranslateDuring);
        ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.jingdong.discovertask.widget.CustomFloatView.2
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                if (CustomFloatView.this.mFloatView != null) {
                    CustomFloatView.this.mFloatView.setTranslationX(floatValue);
                }
            }
        });
        ofFloat.start();
    }

    public void startTimeDown(long j2) {
        TimeDownView timeDownView = this.mTimeDownView;
        if (timeDownView != null) {
            timeDownView.setVisibility(0);
            this.mTimeDownView.setTime(j2);
            this.mTimeDownView.startTimeDown();
        }
    }

    public void switchScreen(boolean z) {
        TimeDownView timeDownView = this.mTimeDownView;
        if (timeDownView != null) {
            timeDownView.switchScreen(z);
        }
    }

    public CustomFloatView timeDownFinishListener(OnTimeDownFinishListener onTimeDownFinishListener) {
        this.mFinishListener = onTimeDownFinishListener;
        return this;
    }

    public CustomFloatView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public CustomFloatView(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mFurlDuring = 500L;
        this.mRotateDuring = 100L;
        this.mTranslateDuring = 300L;
        this.mContext = context;
        init();
    }
}

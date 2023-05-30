package com.jingdong.common.ui;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jingdong.common.cart.CubicBezierInterpolator;
import com.jingdong.sdk.platform.lib.R;

/* loaded from: classes6.dex */
public class CommonNotifyView extends FrameLayout {
    private Animator.AnimatorListener animatorListener;
    private SimpleDraweeView bellIcon;
    private CommonNotifyBgView bgView;
    private View.OnClickListener closeClickListener;
    private SimpleDraweeView closeImageView;
    private TextView commonNotifyTv;
    private boolean isAnimEnd;
    private boolean isAnimStart;
    private View.OnClickListener notifyOnclickListener;
    private View.OnClickListener onCloseClickListener;
    private View.OnClickListener onRootClickListener;
    private View rootView;

    public CommonNotifyView(@NonNull Context context) {
        super(context);
        this.isAnimEnd = false;
        this.isAnimStart = false;
        this.notifyOnclickListener = new View.OnClickListener() { // from class: com.jingdong.common.ui.CommonNotifyView.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (CommonNotifyView.this.onRootClickListener != null) {
                    CommonNotifyView.this.onRootClickListener.onClick(view);
                }
            }
        };
        this.closeClickListener = new View.OnClickListener() { // from class: com.jingdong.common.ui.CommonNotifyView.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                CommonNotifyView.this.StartAnimation();
                if (CommonNotifyView.this.onCloseClickListener != null) {
                    CommonNotifyView.this.onCloseClickListener.onClick(view);
                }
            }
        };
        initView();
    }

    public void StartAnimation() {
        this.isAnimEnd = false;
        this.isAnimStart = false;
        CubicBezierInterpolator cubicBezierInterpolator = new CubicBezierInterpolator(0.5f, 0.0f, 0.3f, 1.0f);
        CubicBezierInterpolator cubicBezierInterpolator2 = new CubicBezierInterpolator(1.0f, 0.0f, 0.75f, 1.0f);
        CommonNotifyBgView commonNotifyBgView = this.bgView;
        ObjectAnimator duration = ObjectAnimator.ofFloat(commonNotifyBgView, "translationX", commonNotifyBgView.getTranslationX(), -this.bgView.getMeasuredWidth()).setDuration(500L);
        duration.setInterpolator(cubicBezierInterpolator);
        duration.addListener(new Animator.AnimatorListener() { // from class: com.jingdong.common.ui.CommonNotifyView.1
            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animator) {
                if (CommonNotifyView.this.animatorListener != null) {
                    CommonNotifyView.this.animatorListener.onAnimationCancel(animator);
                }
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                if (CommonNotifyView.this.isAnimEnd) {
                    return;
                }
                if (CommonNotifyView.this.animatorListener != null) {
                    CommonNotifyView.this.animatorListener.onAnimationEnd(animator);
                }
                CommonNotifyView.this.reset();
                CommonNotifyView.this.isAnimEnd = true;
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationRepeat(Animator animator) {
                if (CommonNotifyView.this.animatorListener != null) {
                    CommonNotifyView.this.animatorListener.onAnimationRepeat(animator);
                }
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
                CommonNotifyView.this.isAnimStart = true;
                if (CommonNotifyView.this.animatorListener != null) {
                    CommonNotifyView.this.animatorListener.onAnimationStart(animator);
                }
            }
        });
        duration.start();
        ObjectAnimator.ofFloat(this.closeImageView, "alpha", 1.0f, 0.0f).setDuration(83L).start();
        ObjectAnimator duration2 = ObjectAnimator.ofFloat(this.commonNotifyTv, "alpha", 1.0f, 0.0f).setDuration(83L);
        duration2.setStartDelay(166L);
        duration2.setInterpolator(cubicBezierInterpolator2);
        duration2.start();
        ObjectAnimator duration3 = ObjectAnimator.ofFloat(this.bellIcon, "alpha", 1.0f, 0.0f).setDuration(83L);
        duration3.setStartDelay(333L);
        duration3.start();
    }

    public CommonNotifyBgView getBgView() {
        return this.bgView;
    }

    public SimpleDraweeView getLeftDraweeView() {
        return this.bellIcon;
    }

    public SimpleDraweeView getRightDraweeView() {
        return this.closeImageView;
    }

    public TextView getTextView() {
        return this.commonNotifyTv;
    }

    public void initView() {
        LayoutInflater.from(getContext()).inflate(R.layout.common_notify_header, (ViewGroup) this, true);
        this.commonNotifyTv = (TextView) findViewById(R.id.common_notify_text);
        this.bgView = (CommonNotifyBgView) findViewById(R.id.common_notify_bg);
        this.closeImageView = (SimpleDraweeView) findViewById(R.id.common_notify_close);
        this.bellIcon = (SimpleDraweeView) findViewById(R.id.common_notify_bell_icon);
        View findViewById = findViewById(R.id.common_notify_layout);
        this.rootView = findViewById;
        findViewById.setOnClickListener(this.notifyOnclickListener);
        this.closeImageView.setOnClickListener(this.closeClickListener);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (!this.isAnimStart || this.isAnimEnd) {
            return;
        }
        Animator.AnimatorListener animatorListener = this.animatorListener;
        if (animatorListener != null) {
            animatorListener.onAnimationEnd(new ObjectAnimator());
        }
        reset();
        this.isAnimEnd = false;
        this.isAnimStart = false;
    }

    public void reset() {
        CommonNotifyBgView commonNotifyBgView = this.bgView;
        if (commonNotifyBgView != null) {
            commonNotifyBgView.setTranslationX(0.0f);
        }
        SimpleDraweeView simpleDraweeView = this.closeImageView;
        if (simpleDraweeView != null) {
            simpleDraweeView.setAlpha(1.0f);
        }
        SimpleDraweeView simpleDraweeView2 = this.bellIcon;
        if (simpleDraweeView2 != null) {
            simpleDraweeView2.setAlpha(1.0f);
        }
        TextView textView = this.commonNotifyTv;
        if (textView != null) {
            textView.setAlpha(1.0f);
        }
    }

    public void setAnimatorListener(Animator.AnimatorListener animatorListener) {
        this.animatorListener = animatorListener;
    }

    public void setOnCloseClickListener(View.OnClickListener onClickListener) {
        this.onCloseClickListener = onClickListener;
    }

    public void setOnRootClickListener(View.OnClickListener onClickListener) {
        this.onRootClickListener = onClickListener;
    }

    public void setText(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.commonNotifyTv.setText(str);
    }

    public CommonNotifyView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.isAnimEnd = false;
        this.isAnimStart = false;
        this.notifyOnclickListener = new View.OnClickListener() { // from class: com.jingdong.common.ui.CommonNotifyView.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (CommonNotifyView.this.onRootClickListener != null) {
                    CommonNotifyView.this.onRootClickListener.onClick(view);
                }
            }
        };
        this.closeClickListener = new View.OnClickListener() { // from class: com.jingdong.common.ui.CommonNotifyView.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                CommonNotifyView.this.StartAnimation();
                if (CommonNotifyView.this.onCloseClickListener != null) {
                    CommonNotifyView.this.onCloseClickListener.onClick(view);
                }
            }
        };
        initView();
    }

    public CommonNotifyView(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.isAnimEnd = false;
        this.isAnimStart = false;
        this.notifyOnclickListener = new View.OnClickListener() { // from class: com.jingdong.common.ui.CommonNotifyView.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (CommonNotifyView.this.onRootClickListener != null) {
                    CommonNotifyView.this.onRootClickListener.onClick(view);
                }
            }
        };
        this.closeClickListener = new View.OnClickListener() { // from class: com.jingdong.common.ui.CommonNotifyView.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                CommonNotifyView.this.StartAnimation();
                if (CommonNotifyView.this.onCloseClickListener != null) {
                    CommonNotifyView.this.onCloseClickListener.onClick(view);
                }
            }
        };
        initView();
    }
}

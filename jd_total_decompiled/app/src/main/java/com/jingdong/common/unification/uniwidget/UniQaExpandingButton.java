package com.jingdong.common.unification.uniwidget;

import android.animation.Animator;
import android.animation.IntEvaluator;
import android.animation.LayoutTransition;
import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.jd.lib.un.basewidget.R;
import com.jingdong.common.DpiUtil;

/* loaded from: classes6.dex */
public class UniQaExpandingButton extends RelativeLayout implements View.OnClickListener {
    private int autoExpandTime;
    private boolean clickAble;
    private boolean clickIconPackup;
    private int defalutWidth;
    public ImageView endIcon;
    private RelativeLayout.LayoutParams endIconParams;
    private boolean isAutoPackUp;
    private boolean isExpand;
    private LayoutTransition layoutTransition;
    private Handler mHandler;
    public ImageView mainIcon;
    public TextView message;
    private RelativeLayout.LayoutParams messageParams;
    private View.OnClickListener onUnExpandButtonClick;

    public UniQaExpandingButton(Context context) {
        super(context);
        this.isAutoPackUp = true;
        this.clickAble = true;
        this.autoExpandTime = 5000;
        initView(context);
    }

    private void expandAnimator(final int i2, final int i3, int i4) {
        ValueAnimator ofInt = ValueAnimator.ofInt(0, 100);
        ofInt.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.jingdong.common.unification.uniwidget.UniQaExpandingButton.2
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                UniQaExpandingButton.this.getLayoutParams().width = new IntEvaluator().evaluate(((Integer) valueAnimator.getAnimatedValue()).intValue() / 100.0f, Integer.valueOf(i2), Integer.valueOf(i3)).intValue();
                UniQaExpandingButton.this.requestLayout();
            }
        });
        ofInt.addListener(new Animator.AnimatorListener() { // from class: com.jingdong.common.unification.uniwidget.UniQaExpandingButton.3
            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                UniQaExpandingButton.this.clickAble = true;
                if (UniQaExpandingButton.this.isExpand) {
                    UniQaExpandingButton.this.message.setVisibility(0);
                    UniQaExpandingButton.this.endIcon.setVisibility(0);
                    if (!UniQaExpandingButton.this.isAutoPackUp || UniQaExpandingButton.this.mHandler == null) {
                        return;
                    }
                    UniQaExpandingButton.this.mHandler.sendEmptyMessageDelayed(1, UniQaExpandingButton.this.autoExpandTime);
                    return;
                }
                UniQaExpandingButton.this.message.setVisibility(4);
                UniQaExpandingButton.this.endIcon.setVisibility(4);
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationRepeat(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
                if (UniQaExpandingButton.this.isExpand) {
                    UniQaExpandingButton.this.message.setVisibility(4);
                    UniQaExpandingButton.this.endIcon.setVisibility(4);
                }
                UniQaExpandingButton.this.isExpand = !r2.isExpand;
            }
        });
        ofInt.setInterpolator(new AccelerateInterpolator());
        ofInt.setDuration(i4);
        ofInt.setTarget(this);
        ofInt.start();
    }

    private int expandWidth() {
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
        int makeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(0, 0);
        this.message.measure(makeMeasureSpec, makeMeasureSpec2);
        int measuredWidth = this.message.getMeasuredWidth();
        this.endIcon.measure(makeMeasureSpec, makeMeasureSpec2);
        return this.defalutWidth + this.endIcon.getMeasuredWidth() + measuredWidth + DpiUtil.dip2px(getContext(), 10.0f) + this.messageParams.leftMargin;
    }

    private void initView(Context context) {
        RelativeLayout.inflate(getContext(), R.layout.un_expand_button, this);
        this.defalutWidth = DpiUtil.dip2px(context, 40.0f);
        setBackgroundResource(R.drawable.un_expand_layout_bg);
        setPadding(DpiUtil.dip2px(context, 10.0f), DpiUtil.dip2px(context, 10.0f), DpiUtil.dip2px(context, 10.0f), DpiUtil.dip2px(context, 10.0f));
        this.mainIcon = (ImageView) findViewById(R.id.mainIcon);
        this.message = (TextView) findViewById(R.id.message);
        this.endIcon = (ImageView) findViewById(R.id.endIcon);
        if (this.message.getLayoutParams() instanceof RelativeLayout.LayoutParams) {
            this.messageParams = (RelativeLayout.LayoutParams) this.message.getLayoutParams();
        }
        if (this.endIcon.getLayoutParams() instanceof RelativeLayout.LayoutParams) {
            this.endIconParams = (RelativeLayout.LayoutParams) this.endIcon.getLayoutParams();
        }
        LayoutTransition layoutTransition = new LayoutTransition();
        this.layoutTransition = layoutTransition;
        layoutTransition.setDuration(100L);
        setLayoutTransition(this.layoutTransition);
        setOnClickListener(this);
        if (this.clickIconPackup) {
            this.mainIcon.setOnClickListener(this);
        }
        setAutoPackUp(true);
    }

    public void expand(boolean z) {
        if (z) {
            int i2 = getLayoutParams() == null ? this.defalutWidth : getLayoutParams().width;
            this.defalutWidth = i2;
            expandAnimator(i2, expandWidth(), 500);
            return;
        }
        expandAnimator(expandWidth(), this.defalutWidth, 250);
    }

    public boolean isExpand() {
        return this.isExpand;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        View.OnClickListener onClickListener;
        if (this.clickAble) {
            this.clickAble = false;
            if (this.isExpand) {
                if (this.isAutoPackUp && (onClickListener = this.onUnExpandButtonClick) != null) {
                    onClickListener.onClick(view);
                    this.clickAble = true;
                    return;
                } else if (this.clickIconPackup) {
                    Handler handler = this.mHandler;
                    if (handler != null) {
                        handler.removeMessages(1);
                    }
                    expandAnimator(expandWidth(), this.defalutWidth, 250);
                    return;
                } else {
                    return;
                }
            }
            int i2 = getLayoutParams() == null ? this.defalutWidth : getLayoutParams().width;
            this.defalutWidth = i2;
            expandAnimator(i2, expandWidth(), 500);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Handler handler = this.mHandler;
        if (handler != null) {
            handler.removeMessages(1);
        }
    }

    public void setAutoExpandTime(int i2) {
        this.autoExpandTime = i2;
    }

    public void setAutoPackUp(boolean z) {
        this.isAutoPackUp = z;
        this.mHandler = new Handler() { // from class: com.jingdong.common.unification.uniwidget.UniQaExpandingButton.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                super.handleMessage(message);
                if (UniQaExpandingButton.this.isExpand) {
                    UniQaExpandingButton.this.expand(false);
                }
            }
        };
    }

    public void setJump(View.OnClickListener onClickListener) {
        this.onUnExpandButtonClick = onClickListener;
    }

    public UniQaExpandingButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.isAutoPackUp = true;
        this.clickAble = true;
        this.autoExpandTime = 5000;
        initView(context);
    }

    public UniQaExpandingButton(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.isAutoPackUp = true;
        this.clickAble = true;
        this.autoExpandTime = 5000;
        initView(context);
    }

    @TargetApi(21)
    public UniQaExpandingButton(Context context, AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
        this.isAutoPackUp = true;
        this.clickAble = true;
        this.autoExpandTime = 5000;
        initView(context);
    }
}

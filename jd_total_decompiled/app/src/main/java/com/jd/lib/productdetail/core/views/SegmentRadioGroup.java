package com.jd.lib.productdetail.core.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Transformation;
import android.view.animation.TranslateAnimation;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import com.jd.lib.productdetail.core.R;

/* loaded from: classes15.dex */
public class SegmentRadioGroup extends RadioGroup implements RadioGroup.OnCheckedChangeListener {
    private Animation mAnimation;
    private boolean mAnimationDoing;
    private Drawable mDrawableChecked;
    private Drawable mDrawableNormal;
    private Drawable mDummy;
    private int mLastCheckedId;
    private RadioGroup.OnCheckedChangeListener mOnCheckedChangeListener;
    private final Transformation mTransformation;

    public SegmentRadioGroup(Context context) {
        super(context);
        this.mTransformation = new Transformation();
        this.mLastCheckedId = -1;
        this.mAnimationDoing = false;
        init();
    }

    private void doAnimation(int i2) {
        Animation animation;
        RadioButton radioButton = (RadioButton) findViewById(i2);
        RadioButton radioButton2 = (RadioButton) findViewById(this.mLastCheckedId);
        if (radioButton != null && radioButton2 != null) {
            if (this.mDrawableChecked == null) {
                this.mDrawableChecked = radioButton.getBackground();
                this.mDummy.setBounds(0, 0, radioButton.getWidth(), radioButton.getHeight());
            }
            radioButton.setBackgroundDrawable(this.mDrawableNormal);
            if (this.mAnimationDoing && (animation = this.mAnimation) != null) {
                animation.reset();
            }
            TranslateAnimation translateAnimation = new TranslateAnimation(radioButton2.getLeft(), radioButton.getLeft(), 0.0f, 0.0f);
            this.mAnimation = translateAnimation;
            translateAnimation.setDuration(300L);
            this.mAnimation.initialize(0, 0, 0, 0);
            this.mAnimationDoing = true;
            this.mAnimation.startNow();
            invalidate();
            this.mAnimation.setAnimationListener(new Animation.AnimationListener() { // from class: com.jd.lib.productdetail.core.views.SegmentRadioGroup.1
                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationEnd(Animation animation2) {
                    SegmentRadioGroup.this.setReallyCheck();
                }

                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationRepeat(Animation animation2) {
                }

                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationStart(Animation animation2) {
                }
            });
            return;
        }
        this.mLastCheckedId = i2;
    }

    private void init() {
        super.setOnCheckedChangeListener(this);
        this.mLastCheckedId = getCheckedRadioButtonId();
        this.mDummy = getResources().getDrawable(R.drawable.lib_pd_core_segment_item_background);
        this.mDrawableNormal = getResources().getDrawable(17170445);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setReallyCheck() {
        int i2 = this.mLastCheckedId;
        if (i2 != -1) {
            super.findViewById(i2).setBackgroundDrawable(this.mDrawableNormal);
        }
        int checkedRadioButtonId = super.getCheckedRadioButtonId();
        this.mLastCheckedId = checkedRadioButtonId;
        if (checkedRadioButtonId != -1) {
            super.findViewById(checkedRadioButtonId).setBackgroundDrawable(this.mDrawableChecked);
        }
    }

    @Override // android.widget.RadioGroup.OnCheckedChangeListener
    public void onCheckedChanged(RadioGroup radioGroup, int i2) {
        if (this.mLastCheckedId != -1) {
            doAnimation(i2);
        } else {
            this.mLastCheckedId = i2;
        }
        RadioGroup.OnCheckedChangeListener onCheckedChangeListener = this.mOnCheckedChangeListener;
        if (onCheckedChangeListener != null) {
            onCheckedChangeListener.onCheckedChanged(radioGroup, i2);
        }
    }

    @Override // android.widget.LinearLayout, android.view.View
    protected void onDraw(Canvas canvas) {
        Animation animation = this.mAnimation;
        if (animation == null || !this.mAnimationDoing) {
            return;
        }
        if (!animation.hasEnded()) {
            int save = canvas.save();
            this.mAnimation.getTransformation(AnimationUtils.currentAnimationTimeMillis(), this.mTransformation);
            canvas.concat(this.mTransformation.getMatrix());
            this.mDummy.draw(canvas);
            canvas.restoreToCount(save);
            invalidate();
            return;
        }
        this.mAnimationDoing = false;
    }

    @Override // android.widget.RadioGroup
    public void setOnCheckedChangeListener(RadioGroup.OnCheckedChangeListener onCheckedChangeListener) {
        this.mOnCheckedChangeListener = onCheckedChangeListener;
    }

    public SegmentRadioGroup(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mTransformation = new Transformation();
        this.mLastCheckedId = -1;
        this.mAnimationDoing = false;
        init();
    }
}

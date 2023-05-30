package com.jingdong.common.sample.jshop.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.jingdong.app.mall.R;

/* loaded from: classes6.dex */
public class XListViewHeader extends LinearLayout {
    private static final int ROTATE_ANIM_DURATION = 180;
    public static final int STATE_NORMAL = 0;
    public static final int STATE_READY = 1;
    public static final int STATE_REFRESHING = 2;
    private ImageView mArrowImageView;
    private LinearLayout mContainer;
    private TextView mHintTextView;
    private ProgressBar mProgressBar;
    private Animation mRotateDownAnim;
    private Animation mRotateUpAnim;
    private int mState;

    public XListViewHeader(Context context) {
        super(context);
        this.mState = 0;
        initView(context);
    }

    private void initView(Context context) {
        ViewGroup.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, 0);
        LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.xlistview_header, (ViewGroup) null);
        this.mContainer = linearLayout;
        addView(linearLayout, layoutParams);
        setGravity(80);
        this.mArrowImageView = (ImageView) findViewById(R.id.xlistview_header_arrow);
        this.mHintTextView = (TextView) findViewById(R.id.xlistview_header_hint_textview);
        this.mProgressBar = (ProgressBar) findViewById(R.id.xlistview_header_progressbar);
        RotateAnimation rotateAnimation = new RotateAnimation(0.0f, -180.0f, 1, 0.5f, 1, 0.5f);
        this.mRotateUpAnim = rotateAnimation;
        rotateAnimation.setDuration(180L);
        this.mRotateUpAnim.setFillAfter(true);
        RotateAnimation rotateAnimation2 = new RotateAnimation(-180.0f, 0.0f, 1, 0.5f, 1, 0.5f);
        this.mRotateDownAnim = rotateAnimation2;
        rotateAnimation2.setDuration(180L);
        this.mRotateDownAnim.setFillAfter(true);
    }

    public int getVisiableHeight() {
        return this.mContainer.getHeight();
    }

    public void setState(int i2) {
        if (i2 == this.mState) {
            return;
        }
        if (i2 == 2) {
            this.mArrowImageView.clearAnimation();
            this.mArrowImageView.setVisibility(4);
            this.mProgressBar.setVisibility(0);
        } else {
            this.mArrowImageView.setVisibility(0);
            this.mProgressBar.setVisibility(4);
        }
        if (i2 == 0) {
            if (this.mState == 1) {
                this.mArrowImageView.startAnimation(this.mRotateDownAnim);
            }
            if (this.mState == 2) {
                this.mArrowImageView.clearAnimation();
            }
            this.mHintTextView.setText(R.string.xlistview_header_hint_normal);
        } else if (i2 != 1) {
            if (i2 == 2) {
                this.mHintTextView.setText(R.string.xlistview_header_hint_loading);
            }
        } else if (this.mState != 1) {
            this.mArrowImageView.clearAnimation();
            this.mArrowImageView.startAnimation(this.mRotateUpAnim);
            this.mHintTextView.setText(R.string.xlistview_header_hint_ready);
        }
        this.mState = i2;
    }

    public void setVisiableHeight(int i2) {
        if (i2 < 0) {
            i2 = 0;
        }
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.mContainer.getLayoutParams();
        layoutParams.height = i2;
        this.mContainer.setLayoutParams(layoutParams);
    }

    public XListViewHeader(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mState = 0;
        initView(context);
    }
}

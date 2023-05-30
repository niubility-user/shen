package com.jingdong.common.listui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.jingdong.listui.R;

/* loaded from: classes5.dex */
public class TitleView extends RelativeLayout {
    private boolean checkNeedRelayout;
    private RelativeLayout leftContainer;
    private TextView leftTv;
    private RelativeLayout middleContainer;
    private RelativeLayout rightContainer;
    private TextView rightTv;
    private RelativeLayout rootContaniner;
    private TextView titleTv;

    public TitleView(Context context) {
        this(context, null);
    }

    private void init(Context context, AttributeSet attributeSet, int i2) {
        LayoutInflater.from(context).inflate(R.layout.liui_view_commontitle, (ViewGroup) this, true);
        this.rootContaniner = (RelativeLayout) findViewById(R.id.title_bar_root_view);
        this.leftContainer = (RelativeLayout) findViewById(R.id.title_bar_left_container);
        this.rightContainer = (RelativeLayout) findViewById(R.id.title_bar_right_container);
        this.middleContainer = (RelativeLayout) findViewById(R.id.title_bar_middle_container);
        this.leftTv = (TextView) findViewById(R.id.title_bar_left_tv);
        this.rightTv = (TextView) findViewById(R.id.title_bar_right_tv);
        this.titleTv = (TextView) findViewById(R.id.title_bar_title_tv);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.LiuiTitleView, i2, 0);
        String string = obtainStyledAttributes.getString(R.styleable.LiuiTitleView_j_titleText);
        int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(R.styleable.LiuiTitleView_j_titleTextSize, 0);
        int color = obtainStyledAttributes.getColor(R.styleable.LiuiTitleView_j_titleTextColor, -1);
        int resourceId = obtainStyledAttributes.getResourceId(R.styleable.LiuiTitleView_j_titleTextStyle, -1);
        setTitle(string);
        if (dimensionPixelSize == 0) {
            setTitleTextSize(2, 18.0f);
        } else {
            setTitleTextSize(0, dimensionPixelSize);
        }
        setTitleTextColor(color);
        setTitleTextStyle(context, resourceId);
        String string2 = obtainStyledAttributes.getString(R.styleable.LiuiTitleView_j_leftText);
        if (!TextUtils.isEmpty(string2)) {
            setLeftText(string2);
        }
        int resourceId2 = obtainStyledAttributes.getResourceId(R.styleable.LiuiTitleView_j_leftDrawable, 0);
        if (resourceId2 != 0) {
            setLeftResource(resourceId2);
        }
        String string3 = obtainStyledAttributes.getString(R.styleable.LiuiTitleView_j_rightText);
        int dimensionPixelSize2 = obtainStyledAttributes.getDimensionPixelSize(R.styleable.LiuiTitleView_j_rightTextSize, 0);
        int color2 = obtainStyledAttributes.getColor(R.styleable.LiuiTitleView_j_rightTextColor, -1);
        int resourceId3 = obtainStyledAttributes.getResourceId(R.styleable.LiuiTitleView_j_rightTextStyle, -1);
        setRightText(string3);
        setRightTextColor(color2);
        setRightTextStyle(context, resourceId3);
        if (dimensionPixelSize2 == 0) {
            setRightTextSize(2, 14.0f);
        } else {
            setRightTextSize(0, dimensionPixelSize2);
        }
        int resourceId4 = obtainStyledAttributes.getResourceId(R.styleable.LiuiTitleView_j_rightDrawable, 0);
        if (resourceId4 != 0) {
            setRightResource(resourceId4);
        }
        obtainStyledAttributes.recycle();
    }

    public RelativeLayout getLeftContainer() {
        return this.leftContainer;
    }

    public TextView getLeftTextView() {
        return this.leftTv;
    }

    public RelativeLayout getRightContainer() {
        return this.rightContainer;
    }

    public TextView getRightTextView() {
        return this.rightTv;
    }

    public RelativeLayout getTitleContainer() {
        return this.middleContainer;
    }

    public boolean isMiddleCoverLeft() {
        return this.leftContainer.getVisibility() == 0 && this.leftContainer.getRight() > this.middleContainer.getLeft();
    }

    public boolean isMiddleCoverRight() {
        return this.rightContainer.getVisibility() == 0 && this.middleContainer.getRight() > this.rightContainer.getLeft();
    }

    @Override // android.widget.RelativeLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        super.onLayout(z, i2, i3, i4, i5);
        int width = this.leftContainer.getWidth();
        int width2 = this.rightContainer.getWidth();
        if (this.checkNeedRelayout) {
            this.checkNeedRelayout = false;
            if (isMiddleCoverLeft() || isMiddleCoverRight()) {
                int width3 = getWidth() - (Math.max(width, width2) * 2);
                ViewGroup.LayoutParams layoutParams = this.middleContainer.getLayoutParams();
                layoutParams.width = width3;
                this.middleContainer.setLayoutParams(layoutParams);
            }
        }
    }

    public void setCustomTitle(View view) {
        RelativeLayout relativeLayout = this.rootContaniner;
        if (relativeLayout != null) {
            relativeLayout.removeAllViews();
            this.rootContaniner.addView(view);
        }
    }

    public void setLeftOnClickListener(View.OnClickListener onClickListener) {
        RelativeLayout relativeLayout = this.leftContainer;
        if (relativeLayout != null) {
            relativeLayout.setOnClickListener(onClickListener);
        }
    }

    public void setLeftResource(int i2) {
        TextView textView = this.leftTv;
        if (textView != null) {
            textView.setVisibility(0);
            this.leftTv.setBackgroundResource(i2);
        }
    }

    public void setLeftText(String str) {
        TextView textView = this.leftTv;
        if (textView != null) {
            textView.setVisibility(0);
            this.leftTv.setText(str);
        }
    }

    public void setLeftTextColor(int i2) {
        TextView textView = this.leftTv;
        if (textView != null) {
            textView.setTextColor(i2);
        }
    }

    public void setLeftTextSize(float f2) {
        TextView textView = this.leftTv;
        if (textView != null) {
            textView.setTextSize(f2);
        }
    }

    public void setLeftTextTypeface(Typeface typeface) {
        TextView textView = this.leftTv;
        if (textView != null) {
            textView.setTypeface(typeface);
        }
    }

    public void setLeftVisible(boolean z) {
        RelativeLayout relativeLayout = this.leftContainer;
        if (relativeLayout != null) {
            relativeLayout.setVisibility(z ? 0 : 8);
        }
    }

    public void setRightOnClickListener(View.OnClickListener onClickListener) {
        RelativeLayout relativeLayout = this.rightContainer;
        if (relativeLayout != null) {
            relativeLayout.setOnClickListener(onClickListener);
        }
    }

    public void setRightOnTouchListener(View.OnTouchListener onTouchListener) {
        RelativeLayout relativeLayout = this.rightContainer;
        if (relativeLayout != null) {
            relativeLayout.setOnTouchListener(onTouchListener);
        }
    }

    public void setRightResource(int i2) {
        TextView textView = this.rightTv;
        if (textView != null) {
            textView.setBackgroundResource(i2);
        }
    }

    public void setRightText(String str) {
        TextView textView = this.rightTv;
        if (textView != null) {
            textView.setVisibility(0);
            this.rightTv.setText(str);
        }
    }

    public void setRightTextColor(int i2) {
        TextView textView = this.rightTv;
        if (textView != null) {
            textView.setTextColor(i2);
        }
    }

    public void setRightTextSize(int i2, float f2) {
        TextView textView = this.rightTv;
        if (textView != null) {
            textView.setTextSize(i2, f2);
        }
    }

    public void setRightTextStyle(Context context, int i2) {
        TextView textView = this.rightTv;
        if (textView != null) {
            textView.setTextAppearance(context, i2);
        }
    }

    public void setRightVisible(boolean z) {
        RelativeLayout relativeLayout = this.rightContainer;
        if (relativeLayout != null) {
            relativeLayout.setVisibility(z ? 0 : 8);
        }
    }

    public void setTitle(String str) {
        TextView textView = this.titleTv;
        if (textView != null) {
            textView.setVisibility(0);
            this.titleTv.setText(str);
        }
    }

    public void setTitleBackground(int i2) {
        TextView textView = this.titleTv;
        if (textView != null) {
            textView.setBackgroundResource(i2);
        }
    }

    public void setTitleOnClickListener(View.OnClickListener onClickListener) {
        RelativeLayout relativeLayout = this.middleContainer;
        if (relativeLayout != null) {
            relativeLayout.setOnClickListener(onClickListener);
        }
    }

    public void setTitleTextColor(int i2) {
        TextView textView = this.titleTv;
        if (textView != null) {
            textView.setTextColor(i2);
        }
    }

    public void setTitleTextSize(float f2) {
        TextView textView = this.titleTv;
        if (textView != null) {
            textView.setTextSize(f2);
        }
    }

    public void setTitleTextStyle(Context context, int i2) {
        TextView textView = this.titleTv;
        if (textView != null) {
            textView.setTextAppearance(context, i2);
        }
    }

    public TitleView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public TitleView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.checkNeedRelayout = true;
        init(context, attributeSet, i2);
    }

    public void setTitleTextSize(int i2, float f2) {
        TextView textView = this.titleTv;
        if (textView != null) {
            textView.setTextSize(i2, f2);
        }
    }
}

package com.jingdong.common.sample.jshop.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.jingdong.app.mall.R;

/* loaded from: classes6.dex */
public class JshopTitle extends RelativeLayout {
    int leftDrawableId;
    private ImageView leftImageView;
    private ImageView leftSplitLine;
    int rightBtnTextResId;
    private TextView rightButton;
    int rightDrawableId;
    private ImageView rightImageView;
    private TitleClickListener titleClickListener;
    int titleDrawableId;
    private ImageView titleImageView;
    int titleTextResId;
    private TextView titleTextView;

    /* loaded from: classes6.dex */
    public interface TitleClickListener {
        void onLeftClicked();

        void onRightClicked();
    }

    public JshopTitle(Context context) {
        this(context, null);
    }

    public void setLeftSplitLineVisibility(int i2) {
        this.leftSplitLine.setVisibility(i2);
    }

    public void setOnTitleClickListener(TitleClickListener titleClickListener) {
        this.titleClickListener = titleClickListener;
    }

    public void setRightImageViewBackgroundResource(int i2) {
        this.rightImageView.setBackgroundResource(i2);
    }

    public void setRightVisibility(int i2) {
        if (this.rightDrawableId != -1) {
            this.rightImageView.setVisibility(i2);
        } else if (this.rightBtnTextResId != -1) {
            this.rightButton.setVisibility(i2);
        }
    }

    public void setTitleImageResource(int i2) {
        this.titleTextView.setVisibility(8);
        this.titleImageView.setVisibility(0);
        this.titleImageView.setImageResource(i2);
    }

    public void setTitleText(int i2) {
        this.titleImageView.setVisibility(8);
        this.titleTextView.setVisibility(0);
        this.titleTextView.setText(i2);
    }

    public void setnRightClickable(boolean z) {
        if (this.rightDrawableId != -1) {
            this.rightImageView.setClickable(z);
        } else if (this.rightBtnTextResId != -1) {
            this.rightButton.setClickable(z);
        }
    }

    public JshopTitle(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        LayoutInflater.from(context).inflate(R.layout.jshop_title, (ViewGroup) this, true);
        this.leftImageView = (ImageView) findViewById(R.id.jshop_title_left_img);
        this.rightImageView = (ImageView) findViewById(R.id.jshop_title_right_img);
        this.rightButton = (TextView) findViewById(R.id.jshop_title_right_btn);
        this.titleTextView = (TextView) findViewById(R.id.jshop_title_title_text);
        this.titleImageView = (ImageView) findViewById(R.id.jshop_title_title_icon);
        this.leftSplitLine = (ImageView) findViewById(R.id.jshop_title_left_split_line);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.jshop_title);
        this.leftDrawableId = obtainStyledAttributes.getResourceId(0, -1);
        this.rightDrawableId = obtainStyledAttributes.getResourceId(1, -1);
        this.rightBtnTextResId = obtainStyledAttributes.getResourceId(3, -1);
        this.titleTextResId = obtainStyledAttributes.getResourceId(4, -1);
        this.titleDrawableId = obtainStyledAttributes.getResourceId(2, -1);
        int i2 = this.leftDrawableId;
        if (i2 != -1) {
            this.leftImageView.setImageResource(i2);
            this.leftImageView.setVisibility(0);
            this.leftSplitLine.setVisibility(8);
            this.leftImageView.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.sample.jshop.ui.JshopTitle.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (JshopTitle.this.titleClickListener != null) {
                        JshopTitle.this.titleClickListener.onLeftClicked();
                    }
                }
            });
        }
        int i3 = this.rightDrawableId;
        if (i3 != -1) {
            this.rightImageView.setImageResource(i3);
            this.rightImageView.setVisibility(0);
            this.rightImageView.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.sample.jshop.ui.JshopTitle.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (JshopTitle.this.titleClickListener != null) {
                        JshopTitle.this.titleClickListener.onRightClicked();
                    }
                }
            });
        }
        int i4 = this.rightBtnTextResId;
        if (i4 != -1) {
            this.rightButton.setText(i4);
            this.rightButton.setVisibility(0);
            this.rightButton.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.sample.jshop.ui.JshopTitle.3
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (JshopTitle.this.titleClickListener != null) {
                        JshopTitle.this.titleClickListener.onRightClicked();
                    }
                }
            });
        }
        int i5 = this.titleTextResId;
        if (i5 != -1) {
            this.titleTextView.setText(i5);
            this.titleTextView.setVisibility(0);
        }
        int i6 = this.titleDrawableId;
        if (i6 != -1) {
            this.titleImageView.setImageResource(i6);
            this.titleImageView.setVisibility(0);
        }
        obtainStyledAttributes.recycle();
    }

    public void setTitleText(String str) {
        this.titleImageView.setVisibility(8);
        this.titleTextView.setVisibility(0);
        this.titleTextView.setText(str);
    }
}

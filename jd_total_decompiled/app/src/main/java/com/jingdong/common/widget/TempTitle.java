package com.jingdong.common.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.jd.lib.un.business.R;

/* loaded from: classes12.dex */
public class TempTitle extends RelativeLayout {
    private static final long INTERVAL = 500;
    private static final int MESSAGE_NUM = 100;
    private long lastClickTime;
    int leftDrawableId;
    public ImageView leftImageView;
    public ImageView leftImageViewPoint;
    private TitleClickListenerEx mTitleClickListenerEx;
    int rightBtnTextColorResId;
    int rightBtnTextResId;
    public TextView rightButton;
    int rightDrawableId;
    int rightDrawableId2;
    public ImageView rightImageView;
    public ImageView rightImageView2;
    public ImageView rightImageViewPoint;
    public TextView rightNumberPoint;
    public View titleBg;
    int titleBgId;
    private TitleClickListener titleClickListener;
    int titleDrawableId;
    public ImageView titleImageView;
    int titleTextColorId;
    int titleTextResId;
    public TextView titleTextView;

    /* loaded from: classes12.dex */
    public interface TitleClickListener {
        void onLeftClicked();

        void onRightClicked();
    }

    /* loaded from: classes12.dex */
    public interface TitleClickListenerEx {
        void onRight2Clicked();
    }

    public TempTitle(Context context) {
        this(context, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isClickEffective() {
        long currentTimeMillis = System.currentTimeMillis();
        boolean z = currentTimeMillis - this.lastClickTime > 500;
        this.lastClickTime = currentTimeMillis;
        return z;
    }

    public void setLeftEnabled(boolean z) {
        if (this.leftDrawableId != -1) {
            this.leftImageView.setEnabled(z);
        }
    }

    public void setLeftPointVisibility(int i2) {
        this.leftImageViewPoint.setVisibility(i2);
    }

    public void setOnTitleClickListener(TitleClickListener titleClickListener) {
        this.titleClickListener = titleClickListener;
    }

    public void setOnTitleClickListenerEx(TitleClickListenerEx titleClickListenerEx) {
        this.mTitleClickListenerEx = titleClickListenerEx;
    }

    public void setRight2Visibility(int i2) {
        if (this.rightDrawableId2 != -1) {
            this.rightImageView2.setVisibility(i2);
        }
    }

    public void setRightClickable(boolean z) {
        if (this.rightDrawableId != -1) {
            this.rightImageView.setClickable(z);
        } else if (this.rightBtnTextResId != -1) {
            this.rightButton.setClickable(z);
        }
    }

    public void setRightEnabled(boolean z) {
        if (this.rightDrawableId != -1) {
            this.rightImageView.setEnabled(z);
        } else if (this.rightBtnTextResId != -1) {
            this.rightButton.setEnabled(z);
        }
    }

    public void setRightMessageNumber(int i2) {
        this.rightImageViewPoint.setVisibility(8);
        if (i2 <= 0) {
            this.rightNumberPoint.setVisibility(8);
            return;
        }
        this.rightNumberPoint.setVisibility(0);
        if (i2 < 100) {
            this.rightNumberPoint.setText(String.valueOf(i2));
        } else {
            this.rightNumberPoint.setText("99+");
        }
    }

    public void setRightPointVisibility(int i2) {
        this.rightImageViewPoint.setVisibility(i2);
        this.rightNumberPoint.setVisibility(8);
    }

    public void setRightText(String str) {
        if (this.rightBtnTextResId != -1) {
            this.rightButton.setText(str);
        }
    }

    public void setRightTextColor(int i2) {
        if (this.rightBtnTextResId != -1) {
            this.rightButton.setTextColor(i2);
        }
    }

    public void setRightVisibility(int i2) {
        if (this.rightDrawableId != -1) {
            this.rightImageView.setVisibility(i2);
        } else if (this.rightBtnTextResId != -1) {
            this.rightButton.setVisibility(i2);
        }
    }

    public void setTitleBackGroundResource(int i2) {
        this.titleBg.setBackgroundResource(i2);
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

    public TempTitle(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.lastClickTime = 0L;
        LayoutInflater.from(context).inflate(R.layout.temp_title, (ViewGroup) this, true);
        if (isInEditMode()) {
            return;
        }
        this.titleBg = findViewById(R.id.title_bg);
        this.leftImageView = (ImageView) findViewById(R.id.temp_title_left_img);
        this.rightImageView = (ImageView) findViewById(R.id.temp_title_right_img);
        this.rightImageView2 = (ImageView) findViewById(R.id.temp_title_right_img2);
        this.rightButton = (TextView) findViewById(R.id.temp_title_right_btn);
        this.titleTextView = (TextView) findViewById(R.id.temp_title_title_text);
        this.titleImageView = (ImageView) findViewById(R.id.temp_title_title_icon);
        this.leftImageViewPoint = (ImageView) findViewById(R.id.temp_title_left_img_point);
        this.rightImageViewPoint = (ImageView) findViewById(R.id.temp_title_right_img_point);
        this.rightNumberPoint = (TextView) findViewById(R.id.temp_title_right_number_point);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.tempTitle);
        this.titleBgId = obtainStyledAttributes.getResourceId(R.styleable.tempTitle_bg, -1);
        this.leftDrawableId = obtainStyledAttributes.getResourceId(R.styleable.tempTitle_drawableLeft, -1);
        this.rightDrawableId = obtainStyledAttributes.getResourceId(R.styleable.tempTitle_drawableRight, -1);
        this.rightDrawableId2 = obtainStyledAttributes.getResourceId(R.styleable.tempTitle_drawableRight2, -1);
        this.rightBtnTextResId = obtainStyledAttributes.getResourceId(R.styleable.tempTitle_textRight, -1);
        this.titleTextResId = obtainStyledAttributes.getResourceId(R.styleable.tempTitle_textTitle, -1);
        this.titleTextColorId = obtainStyledAttributes.getResourceId(R.styleable.tempTitle_textTitleColor, -1);
        this.titleDrawableId = obtainStyledAttributes.getResourceId(R.styleable.tempTitle_drawableTitle, -1);
        this.rightBtnTextColorResId = obtainStyledAttributes.getResourceId(R.styleable.tempTitle_textRightColor, -1);
        int i2 = this.titleBgId;
        if (i2 != -1) {
            this.titleBg.setBackgroundResource(i2);
        }
        int i3 = this.leftDrawableId;
        if (i3 != -1) {
            this.leftImageView.setImageResource(i3);
            this.leftImageView.setVisibility(0);
            this.leftImageView.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.widget.TempTitle.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (TempTitle.this.isClickEffective() && TempTitle.this.titleClickListener != null) {
                        TempTitle.this.titleClickListener.onLeftClicked();
                    }
                }
            });
        }
        int i4 = this.rightDrawableId;
        if (i4 != -1) {
            this.rightImageView.setImageResource(i4);
            this.rightImageView.setVisibility(0);
            this.rightImageView.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.widget.TempTitle.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (TempTitle.this.isClickEffective() && TempTitle.this.titleClickListener != null) {
                        TempTitle.this.titleClickListener.onRightClicked();
                    }
                }
            });
        }
        int i5 = this.rightDrawableId2;
        if (i5 != -1) {
            this.rightImageView2.setImageResource(i5);
            this.rightImageView2.setVisibility(0);
            this.rightImageView2.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.widget.TempTitle.3
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (TempTitle.this.isClickEffective() && TempTitle.this.mTitleClickListenerEx != null) {
                        TempTitle.this.mTitleClickListenerEx.onRight2Clicked();
                    }
                }
            });
        }
        int i6 = this.rightBtnTextResId;
        if (i6 != -1) {
            this.rightButton.setText(i6);
            this.rightButton.setVisibility(0);
            this.rightButton.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.widget.TempTitle.4
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (TempTitle.this.isClickEffective() && TempTitle.this.titleClickListener != null) {
                        TempTitle.this.titleClickListener.onRightClicked();
                    }
                }
            });
        }
        if (this.rightBtnTextColorResId != -1) {
            this.rightButton.setTextColor(getResources().getColor(this.rightBtnTextColorResId));
        }
        int i7 = this.titleTextResId;
        if (i7 != -1) {
            this.titleTextView.setText(i7);
            this.titleTextView.setVisibility(0);
        }
        if (this.titleTextColorId != -1) {
            this.titleTextView.setTextColor(getResources().getColor(this.titleTextColorId));
        }
        int i8 = this.titleDrawableId;
        if (i8 != -1) {
            this.titleImageView.setImageResource(i8);
            this.titleImageView.setVisibility(0);
        }
        obtainStyledAttributes.recycle();
    }

    public void setRightTextColor(ColorStateList colorStateList) {
        if (this.rightBtnTextResId != -1) {
            this.rightButton.setTextColor(colorStateList);
        }
    }

    public void setTitleText(String str) {
        this.titleImageView.setVisibility(8);
        this.titleTextView.setVisibility(0);
        this.titleTextView.setText(str);
    }
}

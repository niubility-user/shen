package com.jingdong.common.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.jd.lib.un.business.R;

/* loaded from: classes12.dex */
public class JDCommonTitle extends RelativeLayout {
    private int backButtonSrc;
    private OnCommonClickListener commonClickListener;
    private int leftButtonSrc;
    private int leftImageButtonSrc;
    private ImageView mBackButton;
    private ImageView mLeftButton;
    private ImageView mLeftImageButton;
    private ImageView mRedPointIcon;
    private ImageView mRedPointImage;
    private FrameLayout mRedPointLayout;
    private Button mRightButton;
    private ImageView mRightImageButton;
    private TextView mRightTextView;
    private TextView mTitleTextView;
    private OnRedPointClickListener redPointClickListener;
    private int redPointImgSrc;
    private int rightButtonText;
    private int rightImageButtonSrc;
    private int rightTextViewBackground;
    private int rightTextViewText;
    private OnRightTwoImageViewsClickListener rightTwoImageViewsClickListener;
    private int titleBg;
    private OnTitleClickListener titleClickListener;
    private int titleText;

    /* loaded from: classes12.dex */
    public interface OnCommonClickListener extends OnTitleClickListener {
        void onButtonClicked(View view);

        void onLeftButtonClicked(View view);

        void onTextViewClicked(View view);
    }

    /* loaded from: classes12.dex */
    public interface OnRedPointClickListener extends OnTitleClickListener {
        void onRedPointClicked(View view);
    }

    /* loaded from: classes12.dex */
    public interface OnRightTwoImageViewsClickListener extends OnTitleClickListener {
        void onLeftImageViewClicked(View view);

        void onRightImageViewClicked(View view);
    }

    /* loaded from: classes12.dex */
    public interface OnTitleClickListener {
        void onBackClicked(View view);
    }

    public JDCommonTitle(Context context) {
        this(context, null);
    }

    public Button getTitleRightButton() {
        return this.mRightButton;
    }

    public TextView getTitleRightTextView() {
        return this.mRightTextView;
    }

    public TextView getTitleTextView() {
        return this.mTitleTextView;
    }

    public void initTitleRightTextView(int i2, Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4, int i3) {
        if (getResources().getText(i2) instanceof String) {
            initTitleRightTextView((String) getResources().getText(i2), drawable, drawable2, drawable3, drawable4, i3);
        }
    }

    public void reSetBackButtonSrc(int i2) {
        this.mBackButton.setImageResource(i2);
        this.mBackButton.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.widget.JDCommonTitle.11
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (JDCommonTitle.this.titleClickListener != null) {
                    JDCommonTitle.this.titleClickListener.onBackClicked(view);
                }
            }
        });
        this.mBackButton.setVisibility(0);
    }

    public void reSetLeftButtonSrc(int i2) {
        this.mLeftButton.setImageResource(i2);
        this.mLeftButton.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.widget.JDCommonTitle.16
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (JDCommonTitle.this.commonClickListener != null) {
                    JDCommonTitle.this.commonClickListener.onLeftButtonClicked(view);
                }
            }
        });
        this.mLeftButton.setVisibility(0);
    }

    public void reSetLeftImageViewSrc(int i2) {
        this.mLeftImageButton.setImageResource(i2);
        this.mLeftImageButton.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.widget.JDCommonTitle.14
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (JDCommonTitle.this.rightTwoImageViewsClickListener != null) {
                    JDCommonTitle.this.rightTwoImageViewsClickListener.onLeftImageViewClicked(view);
                }
            }
        });
        this.mLeftImageButton.setVisibility(0);
    }

    public void reSetRightImageViewSrc(int i2) {
        this.mRightImageButton.setImageResource(i2);
        this.mRightImageButton.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.widget.JDCommonTitle.15
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (JDCommonTitle.this.rightTwoImageViewsClickListener != null) {
                    JDCommonTitle.this.rightTwoImageViewsClickListener.onRightImageViewClicked(view);
                }
            }
        });
        this.mRightImageButton.setVisibility(0);
    }

    public void reSetRightTextView(int i2) {
        this.mRightTextView.setText(i2);
        this.mRightTextView.setVisibility(0);
        this.mRightTextView.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.widget.JDCommonTitle.8
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (JDCommonTitle.this.commonClickListener != null) {
                    JDCommonTitle.this.commonClickListener.onTextViewClicked(view);
                }
            }
        });
    }

    public void reSetTitle(int i2) {
        this.mTitleTextView.setText(i2);
    }

    public void reSetTitleBg(int i2) {
        this.mTitleTextView.setBackgroundResource(i2);
    }

    public void setBackButtonVisible(int i2) {
        if (this.backButtonSrc != -1) {
            this.mBackButton.setVisibility(i2);
        }
    }

    public void setLeftButtonVisible(int i2) {
        if (this.leftButtonSrc != -1) {
            this.mLeftButton.setVisibility(i2);
        }
    }

    public void setLeftImageButtonVisible(int i2) {
        if (this.leftImageButtonSrc != -1) {
            this.mLeftImageButton.setVisibility(i2);
        }
    }

    public void setOnTitleClickListener(OnTitleClickListener onTitleClickListener) {
        this.titleClickListener = onTitleClickListener;
        if (onTitleClickListener instanceof OnCommonClickListener) {
            this.commonClickListener = (OnCommonClickListener) onTitleClickListener;
        }
        if (onTitleClickListener instanceof OnRightTwoImageViewsClickListener) {
            this.rightTwoImageViewsClickListener = (OnRightTwoImageViewsClickListener) onTitleClickListener;
        }
        if (onTitleClickListener instanceof OnRedPointClickListener) {
            this.redPointClickListener = (OnRedPointClickListener) onTitleClickListener;
        }
    }

    public void setRedPointImage(int i2, int i3) {
        if (i2 > 0) {
            this.mRedPointLayout.setVisibility(0);
            this.mRedPointImage.setImageResource(i2);
            setRedPointVisible(i3);
        }
    }

    public void setRedPointVisible(int i2) {
        this.mRedPointIcon.setVisibility(i2);
    }

    public void setRightImageButtonVisible(int i2) {
        if (this.rightImageButtonSrc != -1) {
            this.mRightImageButton.setVisibility(i2);
        }
    }

    public void setRightTextViewVisible(int i2) {
        if (this.rightTextViewText == -1 && this.rightTextViewBackground == -1) {
            return;
        }
        this.mRightTextView.setVisibility(i2);
    }

    public JDCommonTitle(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        LayoutInflater.from(context).inflate(R.layout.common_title, (ViewGroup) this, true);
        this.mBackButton = (ImageView) findViewById(R.id.title_back);
        this.mLeftButton = (ImageView) findViewById(R.id.title_left_imageView);
        this.mLeftImageButton = (ImageView) findViewById(R.id.common_title_imageView_left);
        this.mRightImageButton = (ImageView) findViewById(R.id.common_title_imageView_right);
        this.mTitleTextView = (TextView) findViewById(R.id.titleText);
        this.mRightTextView = (TextView) findViewById(R.id.common_title_tight_textView);
        this.mRightButton = (Button) findViewById(R.id.titleRightButton);
        this.mRedPointLayout = (FrameLayout) findViewById(R.id.common_title_redpoint_layout);
        this.mRedPointImage = (ImageView) findViewById(R.id.common_title_redpoint_img);
        this.mRedPointIcon = (ImageView) findViewById(R.id.common_title_redpoint_icon);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.commonTitle);
        this.backButtonSrc = obtainStyledAttributes.getResourceId(R.styleable.commonTitle_title_back_button, -1);
        this.leftButtonSrc = obtainStyledAttributes.getResourceId(R.styleable.commonTitle_title_left_button, -1);
        this.rightButtonText = obtainStyledAttributes.getResourceId(R.styleable.commonTitle_title_right_button_text, -1);
        this.rightTextViewText = obtainStyledAttributes.getResourceId(R.styleable.commonTitle_title_right_textView_text, -1);
        this.rightTextViewBackground = obtainStyledAttributes.getResourceId(R.styleable.commonTitle_title_right_textView_background, -1);
        this.titleText = obtainStyledAttributes.getResourceId(R.styleable.commonTitle_title_text, -1);
        this.titleBg = obtainStyledAttributes.getResourceId(R.styleable.commonTitle_title_bg, -1);
        this.rightImageButtonSrc = obtainStyledAttributes.getResourceId(R.styleable.commonTitle_title_two_right_image_src, -1);
        this.leftImageButtonSrc = obtainStyledAttributes.getResourceId(R.styleable.commonTitle_title_two_left_image_src, -1);
        this.redPointImgSrc = obtainStyledAttributes.getResourceId(R.styleable.commonTitle_title_redpoint_image, -1);
        int i2 = this.backButtonSrc;
        if (i2 != -1) {
            this.mBackButton.setImageResource(i2);
            this.mBackButton.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.widget.JDCommonTitle.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (JDCommonTitle.this.titleClickListener != null) {
                        JDCommonTitle.this.titleClickListener.onBackClicked(view);
                    }
                }
            });
            this.mBackButton.setVisibility(0);
        }
        int i3 = this.leftButtonSrc;
        if (i3 != -1) {
            this.mLeftButton.setImageResource(i3);
            this.mLeftButton.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.widget.JDCommonTitle.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (JDCommonTitle.this.commonClickListener != null) {
                        JDCommonTitle.this.commonClickListener.onLeftButtonClicked(view);
                    }
                }
            });
            this.mLeftButton.setVisibility(0);
        }
        int i4 = this.titleText;
        if (i4 != -1) {
            this.mTitleTextView.setText(i4);
        }
        int i5 = this.titleBg;
        if (i5 != -1) {
            this.mTitleTextView.setBackgroundResource(i5);
        }
        int i6 = this.rightTextViewText;
        if (i6 != -1 || this.rightTextViewBackground != -1) {
            if (i6 != -1) {
                this.mRightTextView.setText(i6);
            }
            int i7 = this.rightTextViewBackground;
            if (i7 != -1) {
                this.mRightTextView.setBackgroundResource(i7);
            }
            this.mRightTextView.setVisibility(0);
            this.mRightTextView.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.widget.JDCommonTitle.3
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (JDCommonTitle.this.commonClickListener != null) {
                        JDCommonTitle.this.commonClickListener.onTextViewClicked(view);
                    }
                }
            });
        }
        int i8 = this.rightButtonText;
        if (i8 != -1) {
            this.mRightButton.setText(i8);
            this.mRightButton.setVisibility(0);
            this.mRightButton.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.widget.JDCommonTitle.4
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (JDCommonTitle.this.commonClickListener != null) {
                        JDCommonTitle.this.commonClickListener.onButtonClicked(view);
                    }
                }
            });
        }
        int i9 = this.rightImageButtonSrc;
        if (i9 != -1) {
            this.mRightImageButton.setImageResource(i9);
            this.mRightImageButton.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.widget.JDCommonTitle.5
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (JDCommonTitle.this.rightTwoImageViewsClickListener != null) {
                        JDCommonTitle.this.rightTwoImageViewsClickListener.onRightImageViewClicked(view);
                    }
                }
            });
            this.mRightImageButton.setVisibility(0);
        }
        int i10 = this.leftImageButtonSrc;
        if (i10 != -1) {
            this.mLeftImageButton.setImageResource(i10);
            this.mLeftImageButton.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.widget.JDCommonTitle.6
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (JDCommonTitle.this.rightTwoImageViewsClickListener != null) {
                        JDCommonTitle.this.rightTwoImageViewsClickListener.onLeftImageViewClicked(view);
                    }
                }
            });
            this.mLeftImageButton.setVisibility(0);
        }
        if (this.redPointImgSrc != -1) {
            this.mRedPointLayout.setVisibility(0);
            this.mRedPointImage.setImageResource(this.redPointImgSrc);
            this.mRedPointLayout.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.widget.JDCommonTitle.7
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (JDCommonTitle.this.redPointClickListener != null) {
                        JDCommonTitle.this.redPointClickListener.onRedPointClicked(view);
                    }
                }
            });
        }
    }

    public void reSetTitle(String str) {
        this.mTitleTextView.setText(str);
    }

    public void reSetTitleBg(Drawable drawable) {
        this.mTitleTextView.setBackgroundDrawable(drawable);
    }

    public void initTitleRightTextView(String str, Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4, int i2) {
        if (!TextUtils.isEmpty(str)) {
            this.mRightTextView.setText(str);
        }
        this.mRightTextView.setCompoundDrawablesWithIntrinsicBounds(drawable, drawable2, drawable3, drawable4);
        if (i2 > 0) {
            this.mRightTextView.setCompoundDrawablePadding(i2);
        }
        this.mRightTextView.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.widget.JDCommonTitle.10
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (JDCommonTitle.this.commonClickListener != null) {
                    JDCommonTitle.this.commonClickListener.onTextViewClicked(view);
                }
            }
        });
        this.mRightTextView.setBackgroundDrawable(null);
        this.mRightTextView.setVisibility(0);
    }

    public void reSetBackButtonSrc(Bitmap bitmap) {
        this.mBackButton.setImageBitmap(bitmap);
        this.mBackButton.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.widget.JDCommonTitle.12
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (JDCommonTitle.this.titleClickListener != null) {
                    JDCommonTitle.this.titleClickListener.onBackClicked(view);
                }
            }
        });
        this.mBackButton.setVisibility(0);
    }

    public void reSetRightTextView(String str) {
        this.mRightTextView.setText(str);
        this.mRightTextView.setVisibility(0);
        this.mRightTextView.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.widget.JDCommonTitle.9
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (JDCommonTitle.this.commonClickListener != null) {
                    JDCommonTitle.this.commonClickListener.onTextViewClicked(view);
                }
            }
        });
    }

    public void setRedPointImage(int i2) {
        this.mRedPointLayout.setVisibility(i2);
    }

    public void reSetBackButtonSrc(Drawable drawable) {
        this.mBackButton.setImageDrawable(drawable);
        this.mBackButton.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.widget.JDCommonTitle.13
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (JDCommonTitle.this.titleClickListener != null) {
                    JDCommonTitle.this.titleClickListener.onBackClicked(view);
                }
            }
        });
        this.mBackButton.setVisibility(0);
    }
}

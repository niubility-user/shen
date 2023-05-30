package com.jingdong.common.ui;

import android.content.Context;
import android.content.res.ColorStateList;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewStub;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jd.mobile.image.ExtendedScaleTypes;
import com.jingdong.app.util.image.JDDisplayImageOptions;
import com.jingdong.app.util.image.display.JDRoundedBitmapDisplayer;
import com.jingdong.common.PdColorSizeUtil;
import com.jingdong.common.entity.productdetail.PDStylePropertyEntity;
import com.jingdong.common.utils.JDImageUtils;
import com.jingdong.sdk.lib.stylecolorsize.R;

/* loaded from: classes6.dex */
public class PDStyleBubbleItemView extends FrameLayout {
    public static final int ELDER = 2;
    public static final int IMG = 1;
    public static final String IN_STOCK = "1";
    public static final String OUT_STOCK = "0";
    public static final int TEXT = 0;
    private int mBgDrawable;
    private int mBubbleBgDrawable;
    private Context mContext;
    private SimpleDraweeView mCornerImg;
    private JDDisplayImageOptions mCornerImgOptions;
    private int mDisableBg;
    private int mDisableBgSelect;
    private int mDisableText;
    private SimpleDraweeView mElderIcon;
    private TextView mElderText;
    private View mImageCoverBg;
    private ImageView mImgColorsize;
    private TextView mImgNoStock;
    private ImageView mImgOpenDetail;
    private String mImgUrl;
    private boolean mIsElder;
    private LinearLayout mItemContentLayout;
    private View mItemLineView;
    private PdAutoChangeTextSize mItemPriceText;
    private JDDisplayImageOptions mJdDisplayImageOptions;
    private PdAutoChangeTextSize mServiceItemContent;
    private TextView mServiceItemContentNor;
    private PdAutoChangeTextSize mServiceItemDiscount;
    private ColorStateList mTextColor;
    private ColorStateList mTextColorBubble;
    private int mType;
    private View mVsElderRootView;
    private View mVsImgRootView;
    private View mVsTextRootView;

    public PDStyleBubbleItemView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mContext = context;
    }

    private void initStyle() {
        int i2 = this.mType;
        if (i2 != 0) {
            if (i2 == 2) {
                ColorStateList colorStateList = getResources().getColorStateList(R.color.lib_style_elder_text_selector);
                this.mTextColor = colorStateList;
                this.mElderText.setTextColor(colorStateList);
                return;
            }
            ColorStateList colorStateList2 = getResources().getColorStateList(R.color.lib_style_text_selector);
            this.mTextColor = colorStateList2;
            this.mServiceItemContentNor.setTextColor(colorStateList2);
            return;
        }
        int i3 = R.color.lib_style_text_selector;
        int i4 = R.drawable.lib_style_line_selector;
        this.mBgDrawable = R.drawable.lib_pd_style_button_g;
        this.mBubbleBgDrawable = R.drawable.lib_pd_style_buystyle_selector;
        if (this.mIsElder) {
            i3 = R.color.lib_style_elder_text_selector;
            this.mBgDrawable = R.drawable.lib_pd_item_elder_selector;
        }
        this.mTextColor = getResources().getColorStateList(i3);
        this.mTextColorBubble = getResources().getColorStateList(R.color.lib_style_text_bubble_selector);
        this.mServiceItemContent.setTextColor(this.mTextColor);
        this.mItemPriceText.setTextColor(this.mTextColor);
        this.mItemLineView.setBackgroundResource(i4);
        this.mItemContentLayout.setBackgroundResource(this.mBgDrawable);
        this.mServiceItemDiscount.setBackgroundResource(this.mBubbleBgDrawable);
        this.mServiceItemDiscount.setTextColor(this.mTextColorBubble);
    }

    private void setImageStock(boolean z) {
        TextView textView = this.mImgNoStock;
        if (textView != null) {
            textView.setVisibility(z ? 0 : 8);
        }
    }

    public void initView(int i2, boolean z) {
        this.mIsElder = z;
        setItemType(i2);
        int i3 = this.mType;
        if (i3 == 0) {
            if (this.mVsTextRootView == null) {
                View findViewById = findViewById(R.id.item_text_layout);
                if (findViewById instanceof ViewStub) {
                    View inflate = ((ViewStub) findViewById).inflate();
                    this.mVsTextRootView = inflate;
                    this.mItemContentLayout = (LinearLayout) inflate.findViewById(R.id.detail_style_bubble_content_layout);
                    this.mItemLineView = this.mVsTextRootView.findViewById(R.id.detail_style_bubble_line);
                    this.mItemPriceText = (PdAutoChangeTextSize) this.mVsTextRootView.findViewById(R.id.detail_style_bubble_price);
                    this.mServiceItemContent = (PdAutoChangeTextSize) this.mVsTextRootView.findViewById(R.id.detail_style_bubble_content);
                    this.mServiceItemDiscount = (PdAutoChangeTextSize) this.mVsTextRootView.findViewById(R.id.detail_style_bubble_text);
                    initStyle();
                    if (z) {
                        this.mServiceItemContent.setTextSize(2, 14.0f);
                        this.mItemPriceText.setTextSize(2, 14.0f);
                        return;
                    }
                    this.mServiceItemContent.setTextSize(2, 12.0f);
                    this.mItemPriceText.setTextSize(2, 12.0f);
                }
            }
        } else if (i3 == 2) {
            if (this.mVsElderRootView == null) {
                View findViewById2 = findViewById(R.id.item_elder_layout);
                if (findViewById2 instanceof ViewStub) {
                    View inflate2 = ((ViewStub) findViewById2).inflate();
                    this.mVsElderRootView = inflate2;
                    this.mElderIcon = (SimpleDraweeView) inflate2.findViewById(R.id.detail_style_elder_img);
                    this.mElderText = (TextView) this.mVsElderRootView.findViewById(R.id.detail_style_elder_text);
                    initStyle();
                }
            }
        } else if (this.mVsImgRootView == null) {
            View findViewById3 = findViewById(R.id.item_img_layout);
            if (findViewById3 instanceof ViewStub) {
                View inflate3 = ((ViewStub) findViewById3).inflate();
                this.mVsImgRootView = inflate3;
                this.mServiceItemContentNor = (TextView) inflate3.findViewById(R.id.detail_style_bubble_content_img);
                this.mImageCoverBg = this.mVsImgRootView.findViewById(R.id.detail_style_img_cover_bg);
                this.mCornerImg = (SimpleDraweeView) this.mVsImgRootView.findViewById(R.id.detail_style_bubble_corner_icon);
                this.mImgColorsize = (ImageView) this.mVsImgRootView.findViewById(R.id.img_colorsize);
                this.mImgNoStock = (TextView) this.mVsImgRootView.findViewById(R.id.img_color_size_stock);
                this.mImgOpenDetail = (ImageView) this.mVsImgRootView.findViewById(R.id.img_open_detail);
                initStyle();
            }
        }
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
    }

    public void setContentTextColor(int i2) {
        PdAutoChangeTextSize pdAutoChangeTextSize = this.mServiceItemContent;
        if (pdAutoChangeTextSize != null) {
            pdAutoChangeTextSize.setTextColor(this.mContext.getResources().getColor(i2));
        }
        TextView textView = this.mServiceItemContentNor;
        if (textView != null) {
            textView.setTextColor(this.mContext.getResources().getColor(i2));
        }
    }

    public void setDarkTheme(boolean z) {
        int i2 = this.mType;
        if (i2 == 2 && z) {
            return;
        }
        if (i2 == 0) {
            if (this.mIsElder) {
                this.mJdDisplayImageOptions = JDDisplayImageOptions.createSimple();
                float dip2px = PdColorSizeUtil.dip2px(4);
                this.mJdDisplayImageOptions.displayer(new JDRoundedBitmapDisplayer(dip2px, dip2px, 0.0f, 0.0f));
                this.mDisableBg = R.drawable.lib_pd_style_elder_button_disable;
                this.mDisableText = ContextCompat.getColor(this.mContext, R.color.pd_color_BFBFBF);
                return;
            }
            this.mBgDrawable = z ? R.drawable.lib_pd_style_button_new_dark : R.drawable.lib_pd_style_button_new;
            this.mDisableBg = z ? R.drawable.lib_pd_style_button_disable_dark : R.drawable.lib_pd_style_button_disable;
            this.mDisableText = ContextCompat.getColor(this.mContext, z ? R.color.pd_color_555353 : R.color.pd_color_BFBFBF);
            this.mTextColor = getResources().getColorStateList(z ? R.color.lib_style_text_dark_selector : R.color.lib_style_text_selector);
            this.mBubbleBgDrawable = z ? R.drawable.lib_pd_style_buystyle_dark_selector : R.drawable.lib_pd_style_buystyle_selector;
            this.mTextColorBubble = getResources().getColorStateList(z ? R.color.lib_style_text_bubble_dark_selector : R.color.lib_style_text_bubble_selector);
            this.mServiceItemContent.setTextColor(this.mTextColor);
            this.mItemContentLayout.setBackgroundResource(this.mBgDrawable);
            this.mItemPriceText.setTextColor(this.mTextColor);
            this.mServiceItemDiscount.setBackgroundResource(this.mBubbleBgDrawable);
            this.mServiceItemDiscount.setTextColor(this.mTextColorBubble);
        } else if (i2 == 2) {
            this.mJdDisplayImageOptions = JDDisplayImageOptions.createSimple();
            float dip2px2 = PdColorSizeUtil.dip2px(4);
            this.mJdDisplayImageOptions.displayer(new JDRoundedBitmapDisplayer(dip2px2, dip2px2, 0.0f, 0.0f));
            this.mDisableBg = R.drawable.lib_pd_style_elder_button_disable;
            this.mDisableText = ContextCompat.getColor(this.mContext, R.color.pd_color_BFBFBF);
        } else {
            int i3 = z ? R.color.lib_style_text_dark_selector : R.color.lib_style_text_selector;
            this.mDisableBg = z ? R.drawable.lib_pd_style_button_img_disable_dark : R.drawable.lib_pd_style_button_img_disable;
            this.mDisableText = ContextCompat.getColor(this.mContext, z ? R.color.pd_color_555353 : R.color.pd_color_BFBFBF);
            int i4 = z ? R.drawable.lib_pd_item_img_dark_selector : R.drawable.lib_pd_item_img_selector;
            this.mDisableBgSelect = i4;
            this.mVsImgRootView.setBackgroundDrawable(ContextCompat.getDrawable(this.mContext, i4));
            this.mImageCoverBg.setBackgroundResource(this.mDisableBgSelect);
            ColorStateList colorStateList = getResources().getColorStateList(i3);
            this.mTextColor = colorStateList;
            this.mBgDrawable = z ? R.drawable.lib_pd_style_button_img_dark : R.drawable.lib_pd_style_button_img;
            this.mServiceItemContentNor.setTextColor(colorStateList);
            this.mServiceItemContentNor.setBackgroundResource(this.mBgDrawable);
            TextView textView = this.mImgNoStock;
            if (textView != null && textView.getBackground() != null) {
                this.mImgNoStock.getBackground().setLevel(z ? 2 : 1);
                this.mImgNoStock.setTextColor(getResources().getColorStateList(z ? R.color.pd_color_1d1b1b : R.color.pd_white));
            }
            this.mJdDisplayImageOptions = JDDisplayImageOptions.createSimple();
            RoundingParams roundingParams = new RoundingParams();
            float dip2px3 = PdColorSizeUtil.dip2px(6);
            roundingParams.setCornersRadii(dip2px3, dip2px3, 0.0f, 0.0f);
            roundingParams.setRoundingMethod(RoundingParams.RoundingMethod.OVERLAY_COLOR);
            roundingParams.setOverlayColor(ContextCompat.getColor(this.mContext, z ? R.color.pd_color_0A0909 : R.color.pd_white));
            this.mJdDisplayImageOptions.setRoundingParams(roundingParams);
            JDDisplayImageOptions createSimple = JDDisplayImageOptions.createSimple();
            this.mCornerImgOptions = createSimple;
            createSimple.setActualImageScaleType(ExtendedScaleTypes.FIT_LEFT_Y);
        }
    }

    public void setItemBubble(String str) {
        if (this.mServiceItemDiscount != null) {
            if (!TextUtils.isEmpty(str)) {
                this.mServiceItemDiscount.setText(str);
            } else {
                this.mServiceItemDiscount.setVisibility(8);
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void setItemContent(PDStylePropertyEntity pDStylePropertyEntity) {
        int i2;
        int i3;
        if (this.mType == 2) {
            if (this.mElderText == null || pDStylePropertyEntity == null || TextUtils.isEmpty(pDStylePropertyEntity.text)) {
                return;
            }
            if (TextUtils.equals(pDStylePropertyEntity.stock, "0")) {
                String concat = pDStylePropertyEntity.text.concat(this.mContext.getString(R.string.pd_style_no_stock));
                SpannableString spannableString = new SpannableString(concat);
                if (pDStylePropertyEntity.isSelect) {
                    i3 = R.color.pd_color_F57665;
                } else {
                    i3 = R.color.gray_8c8c8c;
                }
                spannableString.setSpan(new ForegroundColorSpan(getResources().getColor(i3)), pDStylePropertyEntity.text.length(), concat.length(), 33);
                this.mElderText.setText(spannableString);
                return;
            }
            this.mElderText.setText(pDStylePropertyEntity.text);
        } else if (pDStylePropertyEntity != null) {
            if (!TextUtils.isEmpty(pDStylePropertyEntity.text)) {
                String str = pDStylePropertyEntity.text;
                if (this.mType == 0 && !TextUtils.isEmpty(pDStylePropertyEntity.iconInfo)) {
                    str = pDStylePropertyEntity.iconInfo + pDStylePropertyEntity.text;
                }
                boolean equals = TextUtils.equals(pDStylePropertyEntity.stock, "0");
                if (equals) {
                    String concat2 = str.concat(this.mContext.getString(R.string.pd_style_no_stock));
                    SpannableString spannableString2 = new SpannableString(concat2);
                    if (pDStylePropertyEntity.isSelect) {
                        i2 = R.color.pd_color_F57665;
                    } else {
                        i2 = R.color.gray_8c8c8c;
                    }
                    spannableString2.setSpan(new ForegroundColorSpan(getResources().getColor(i2)), str.length(), concat2.length(), 33);
                    str = spannableString2;
                }
                PdAutoChangeTextSize pdAutoChangeTextSize = this.mServiceItemContent;
                if (pdAutoChangeTextSize != null) {
                    pdAutoChangeTextSize.setText(str);
                }
                TextView textView = this.mServiceItemContentNor;
                if (textView != null) {
                    textView.setText(pDStylePropertyEntity.text);
                    this.mServiceItemContentNor.setEnabled(!equals);
                }
            }
            if (this.mItemPriceText == null || this.mItemLineView == null) {
                return;
            }
            if (!TextUtils.isEmpty(pDStylePropertyEntity.saleAttrValuePrice)) {
                this.mItemPriceText.setText(pDStylePropertyEntity.saleAttrValuePrice);
                this.mItemPriceText.setVisibility(0);
                this.mItemLineView.setVisibility(0);
                return;
            }
            this.mItemPriceText.setVisibility(8);
            this.mItemLineView.setVisibility(8);
        }
    }

    public void setItemDash(PDStylePropertyEntity pDStylePropertyEntity) {
        if (pDStylePropertyEntity == null) {
            return;
        }
        int i2 = this.mType;
        int i3 = 8;
        if (i2 == 0) {
            if (pDStylePropertyEntity.isDash) {
                this.mItemContentLayout.setBackgroundResource(this.mDisableBg);
                PdAutoChangeTextSize pdAutoChangeTextSize = this.mServiceItemContent;
                pdAutoChangeTextSize.setPaintFlags(pdAutoChangeTextSize.getPaintFlags() | 16);
                this.mServiceItemContent.setTextColor(this.mDisableText);
                PdAutoChangeTextSize pdAutoChangeTextSize2 = this.mItemPriceText;
                pdAutoChangeTextSize2.setPaintFlags(pdAutoChangeTextSize2.getPaintFlags() | 16);
                this.mItemPriceText.setTextColor(this.mDisableText);
                setEnabled(false);
            } else {
                this.mItemContentLayout.setBackgroundResource(this.mBgDrawable);
                PdAutoChangeTextSize pdAutoChangeTextSize3 = this.mServiceItemContent;
                pdAutoChangeTextSize3.setPaintFlags(pdAutoChangeTextSize3.getPaintFlags() & (-17));
                this.mServiceItemContent.setTextColor(this.mTextColor);
                PdAutoChangeTextSize pdAutoChangeTextSize4 = this.mItemPriceText;
                pdAutoChangeTextSize4.setPaintFlags(pdAutoChangeTextSize4.getPaintFlags() & (-17));
                this.mItemPriceText.setTextColor(this.mTextColor);
                setEnabled(true);
            }
            if (pDStylePropertyEntity.hasService) {
                PdAutoChangeTextSize pdAutoChangeTextSize5 = this.mServiceItemDiscount;
                if (pDStylePropertyEntity.hasBubble && !TextUtils.isEmpty(pDStylePropertyEntity.ktyf)) {
                    i3 = 0;
                }
                pdAutoChangeTextSize5.setVisibility(i3);
                ((FrameLayout.LayoutParams) this.mItemContentLayout.getLayoutParams()).topMargin = (int) getResources().getDimension(R.dimen.pd_space_width_7);
                return;
            }
            this.mServiceItemDiscount.setVisibility(8);
            ((FrameLayout.LayoutParams) this.mItemContentLayout.getLayoutParams()).topMargin = 0;
        } else if (i2 == 2) {
            if (pDStylePropertyEntity.isDash) {
                TextView textView = this.mElderText;
                textView.setPaintFlags(textView.getPaintFlags() | 16);
                this.mElderText.setTextColor(this.mDisableText);
                setEnabled(false);
                return;
            }
            TextView textView2 = this.mElderText;
            textView2.setPaintFlags(textView2.getPaintFlags() & (-17));
            this.mElderText.setTextColor(this.mTextColor);
            setEnabled(true);
        } else {
            if (pDStylePropertyEntity.isDash) {
                ImageView imageView = this.mImgOpenDetail;
                if (imageView != null) {
                    imageView.setVisibility(8);
                }
                this.mImgColorsize.setAlpha(0.6f);
                this.mServiceItemContentNor.setBackgroundResource(this.mDisableBg);
                TextView textView3 = this.mServiceItemContentNor;
                textView3.setPaintFlags(textView3.getPaintFlags() | 16);
                this.mServiceItemContentNor.setTextColor(this.mDisableText);
                setEnabled(false);
            } else {
                ImageView imageView2 = this.mImgOpenDetail;
                if (imageView2 != null) {
                    imageView2.setVisibility(0);
                }
                this.mServiceItemContentNor.setBackgroundResource(this.mBgDrawable);
                TextView textView4 = this.mServiceItemContentNor;
                textView4.setPaintFlags(textView4.getPaintFlags() & (-17));
                this.mServiceItemContentNor.setTextColor(this.mTextColor);
                setEnabled(true);
            }
            if (pDStylePropertyEntity.hasService) {
                ((LinearLayout.LayoutParams) this.mServiceItemContentNor.getLayoutParams()).topMargin = (int) getResources().getDimension(R.dimen.pd_space_width_7);
                return;
            }
            ((LinearLayout.LayoutParams) this.mServiceItemContentNor.getLayoutParams()).topMargin = 0;
        }
    }

    public void setItemImage(String str, String str2, boolean z) {
        this.mImgUrl = str;
        int i2 = this.mType;
        if (i2 == 2) {
            if (this.mElderIcon != null) {
                if (!TextUtils.isEmpty(str)) {
                    this.mElderIcon.setVisibility(0);
                    JDImageUtils.displayImage(str, (ImageView) this.mElderIcon, this.mJdDisplayImageOptions, true);
                } else {
                    this.mElderIcon.setVisibility(8);
                }
            }
        } else if (i2 == 1) {
            if (TextUtils.isEmpty(str)) {
                this.mImgOpenDetail.setVisibility(8);
            } else {
                this.mImgOpenDetail.setVisibility(0);
            }
            if (this.mImgColorsize != null) {
                ImageView imageView = this.mImgOpenDetail;
                if (imageView != null) {
                    imageView.setTag(this.mImgUrl);
                }
                JDImageUtils.displayImage(str, this.mImgColorsize, this.mJdDisplayImageOptions, true);
            }
            if (this.mCornerImg != null) {
                if (TextUtils.isEmpty(str2)) {
                    this.mCornerImg.setVisibility(8);
                } else {
                    this.mCornerImg.setVisibility(0);
                    JDImageUtils.displayImage(str2, (ImageView) this.mCornerImg, this.mCornerImgOptions, false);
                }
            }
        }
        setImageStock(z);
    }

    public void setItemSelected(boolean z) {
        int i2 = this.mType;
        if (i2 == 0) {
            this.mServiceItemContent.setSelected(z);
            this.mItemContentLayout.setSelected(z);
            this.mItemPriceText.setSelected(z);
            this.mItemLineView.setSelected(z);
            this.mServiceItemDiscount.setSelected(z);
            setSelected(z);
        } else if (i2 == 2) {
            this.mElderIcon.setSelected(z);
            this.mElderText.setSelected(z);
            setSelected(z);
        } else {
            this.mServiceItemContentNor.setSelected(z);
            this.mVsImgRootView.setSelected(z);
            this.mImageCoverBg.setSelected(z);
            setSelected(z);
        }
    }

    public void setItemType(int i2) {
        this.mType = i2;
    }

    public void setOnOpenImgClickListener(View.OnClickListener onClickListener) {
        ImageView imageView;
        if (onClickListener == null || (imageView = this.mImgOpenDetail) == null) {
            return;
        }
        imageView.setTag(this.mImgUrl);
        this.mImgOpenDetail.setOnClickListener(onClickListener);
    }
}

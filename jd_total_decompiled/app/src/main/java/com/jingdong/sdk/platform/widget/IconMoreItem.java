package com.jingdong.sdk.platform.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jd.lib.cashier.sdk.core.utils.JDDarkUtil;
import com.jingdong.app.util.image.JDDisplayImageOptions;
import com.jingdong.app.util.image.listener.JDSimpleImageLoadingListener;
import com.jingdong.common.utils.DeepDarkChangeManager;
import com.jingdong.common.utils.JDImageUtils;
import com.jingdong.common.utils.PersonalPreferenceUtil;
import com.jingdong.common.utils.text.TextScaleModeHelper;
import com.jingdong.sdk.oklog.OKLog;
import com.jingdong.sdk.platform.business.personal.R;
import com.jingdong.sdk.platform.business.personal.R2;
import com.jingdong.sdk.platform.business.personal.common.ImageTools;
import com.jingdong.sdk.platform.business.personal.common.PersonalCommonUtil;
import com.jingdong.sdk.platform.business.personal.entity.CommonMultiIconEntity;

/* loaded from: classes10.dex */
public class IconMoreItem extends RelativeLayout {
    private static final String COLOR_STARTS_WITH_RULE = "#";
    private static final String CONTENT_TYPE_LOTTIE = "2";
    private static final String CONTENT_TYPE_NUMBER = "1";
    private static final String NOTICE_TYPE_RED_POINT = "1";
    private static final String TAG = "IconMoreItem";
    @BindView(R2.id.content)
    TextView content;
    @BindView(R2.id.content_red_point)
    ImageView contentRedPoint;
    @BindView(R2.id.icon)
    SimpleDraweeView icon;
    @BindView(R2.id.icon_red_point)
    ImageView iconRedPoint;
    private boolean isNumberShow;
    private boolean isRedPointShow;
    @BindView(R2.id.lottie_view)
    ImageView lottieView;
    private JDDisplayImageOptions options;
    @BindView(R2.id.sub_title)
    TextView subTitle;
    @BindView(R2.id.title)
    TextView title;

    public IconMoreItem(Context context) {
        this(context, null);
    }

    private void bindContent(CommonMultiIconEntity.IconElement iconElement) {
        if (TextUtils.equals(iconElement.contentType, "1") && !TextUtils.isEmpty(iconElement.iconContent)) {
            this.icon.setVisibility(8);
            this.lottieView.setVisibility(8);
            this.content.setVisibility(0);
            this.content.setText(iconElement.iconContent);
            PersonalCommonUtil.setNumberTextFont(this.content, iconElement.iconContent);
            this.isNumberShow = true;
        } else if (TextUtils.equals(iconElement.contentType, "2") && !TextUtils.isEmpty(iconElement.lottieContent)) {
            this.icon.setVisibility(8);
            this.content.setVisibility(8);
            this.lottieView.setVisibility(0);
            fitLottieView(this.lottieView, iconElement);
        } else {
            this.content.setVisibility(8);
            this.lottieView.setVisibility(8);
            this.icon.setVisibility(0);
            ImageTools.displayImage(iconElement.safeImage, this.icon, this.options, true, null);
            this.isNumberShow = false;
        }
        CommonMultiIconEntity.IconElement.UpdateNotice updateNotice = iconElement.updateNotice;
        if (updateNotice == null || !TextUtils.equals(updateNotice.noticeType, "1")) {
            resetRedPoint();
        }
    }

    private void bindRedPoint(CommonMultiIconEntity.IconElement iconElement) {
        if (iconElement.updateTimeStamp > PersonalPreferenceUtil.getRedDotLastVersion(iconElement.bizId)) {
            setRedPointVisibility(0);
            this.isRedPointShow = true;
            return;
        }
        this.isRedPointShow = false;
    }

    private void bindSubTitle(CommonMultiIconEntity.IconElement.SubTitle subTitle) {
        if (subTitle == null) {
            return;
        }
        setTitleAttrs(this.subTitle, true, subTitle);
    }

    private void bindTitle(CommonMultiIconEntity.IconElement.Title title) {
        if (title == null) {
            return;
        }
        setTitleAttrs(this.title, false, title);
    }

    private void fitLottieView(final ImageView imageView, CommonMultiIconEntity.IconElement iconElement) {
        if (iconElement == null) {
            return;
        }
        if (PersonalCommonUtil.isShowLottie(imageView)) {
            ((PersonalLottieView) imageView).display(iconElement);
        } else {
            JDImageUtils.loadImage(iconElement.safeImage, new JDSimpleImageLoadingListener() { // from class: com.jingdong.sdk.platform.widget.IconMoreItem.1
                @Override // com.jingdong.app.util.image.listener.JDSimpleImageLoadingListener, com.jingdong.app.util.image.listener.JDImageLoadingListener
                public void onLoadingComplete(String str, View view, Bitmap bitmap) {
                    ImageView imageView2 = imageView;
                    if (!(imageView2 instanceof PersonalLottieView) || bitmap == null) {
                        return;
                    }
                    imageView2.setBackgroundResource(0);
                    imageView.setImageBitmap(bitmap);
                }
            });
        }
    }

    private void handleDarkMode() {
        if (this.content != null) {
            if (DeepDarkChangeManager.getInstance().getUIMode() == 1) {
                this.content.setTextColor(Color.parseColor(JDDarkUtil.COLOR_ECECEC));
            } else {
                this.content.setTextColor(Color.parseColor(JDDarkUtil.COLOR_2E2D2D));
            }
        }
    }

    private void resetRedPoint() {
        this.iconRedPoint.setVisibility(8);
        this.contentRedPoint.setVisibility(8);
    }

    private void setTextViewText(TextView textView, String str) {
        if (textView == null) {
            return;
        }
        textView.setText(PersonalCommonUtil.getSafetyString(str));
    }

    private void setTitleAttrs(TextView textView, boolean z, CommonMultiIconEntity.IconElement.BaseTitle baseTitle) {
        if (textView == null || baseTitle == null) {
            return;
        }
        setTextViewText(textView, baseTitle.value);
        if (DeepDarkChangeManager.getInstance().getUIMode() == 1) {
            textView.setTextColor(Color.parseColor(z ? JDDarkUtil.COLOR_848383 : JDDarkUtil.COLOR_ECECEC));
            return;
        }
        String str = baseTitle.color;
        int parseColor = Color.parseColor(z ? "#848484" : JDDarkUtil.COLOR_2E2D2D);
        if (!TextUtils.isEmpty(str)) {
            try {
                if (!str.startsWith("#")) {
                    str = "#" + str;
                }
                parseColor = Color.parseColor(str);
            } catch (Exception e2) {
                if (OKLog.D) {
                    OKLog.d(TAG, String.format("parse title color attr exception:%s", e2));
                }
            }
        }
        textView.setTextColor(parseColor);
    }

    public void bindData(CommonMultiIconEntity.IconElement iconElement) {
        if (iconElement == null) {
            return;
        }
        bindContent(iconElement);
        bindTitle(iconElement.title);
        bindSubTitle(iconElement.subTitle);
        setTextViewText(this.content, iconElement.iconContent);
        handleTextScaleModeChanged();
        handleDarkMode();
    }

    public boolean getRedPointShow() {
        return this.isRedPointShow;
    }

    public void handleTextScaleModeChanged() {
        TextView textView = this.subTitle;
        if (textView != null) {
            textView.setTextSize(TextScaleModeHelper.INSTANCE.getInstance().getScaleSize(getContext(), 9.0f));
        }
        TextView textView2 = this.title;
        if (textView2 != null) {
            textView2.setTextSize(TextScaleModeHelper.INSTANCE.getInstance().getScaleSize(getContext(), 11.0f));
        }
    }

    public void setRedPointVisibility(int i2) {
        resetRedPoint();
        if (this.isNumberShow) {
            this.contentRedPoint.setVisibility(i2);
        } else {
            this.iconRedPoint.setVisibility(i2);
        }
    }

    public IconMoreItem(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public IconMoreItem(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        LayoutInflater.from(context.getApplicationContext()).inflate(R.layout.layout_multi_icon_view, (ViewGroup) this, true);
        ButterKnife.bind(this);
        this.options = new JDDisplayImageOptions().setPlaceholder(18);
        handleTextScaleModeChanged();
        handleDarkMode();
    }
}

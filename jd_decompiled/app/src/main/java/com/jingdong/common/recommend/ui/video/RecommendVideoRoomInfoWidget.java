package com.jingdong.common.recommend.ui.video;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.airbnb.lottie.LottieAnimationView;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jd.lib.cashier.sdk.core.utils.JDDarkUtil;
import com.jd.mobile.image.JDImageLoader;
import com.jingdong.app.util.image.JDDisplayImageOptions;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.recommend.RecommendTextScaleUtils;
import com.jingdong.sdk.platform.business.personal.R2;
import com.jingdong.sdk.utils.DPIUtil;

/* loaded from: classes6.dex */
public class RecommendVideoRoomInfoWidget extends ConstraintLayout {
    private final int PAGEVIEW_MAX_WIDTH;
    private final int STREAMER_ICON_ID;
    private final int TITLE_MAX_WIDTH;
    private Info info;
    private LottieAnimationView lottieLiveIcon;
    private TextView pageView;
    private SimpleDraweeView streamerIcon;
    private TextView title;

    /* loaded from: classes6.dex */
    public static class Info {
        String pageView;
        String streamerIcon;
        String streamerName;

        private Info() {
        }

        public static Info builder() {
            return new Info();
        }

        public Info setPageView(String str) {
            this.pageView = str;
            return this;
        }

        public Info setStreamerIcon(String str) {
            this.streamerIcon = str;
            return this;
        }

        public Info setStreamerName(String str) {
            this.streamerName = str;
            return this;
        }
    }

    public RecommendVideoRoomInfoWidget(Context context) {
        super(context);
        this.STREAMER_ICON_ID = 7672367;
        this.TITLE_MAX_WIDTH = R2.anim.pop_left_top_out;
        this.PAGEVIEW_MAX_WIDTH = 160;
        initView(context);
    }

    private GradientDrawable getDefaultBg(BaseActivity baseActivity) {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setCornerRadius(DPIUtil.getWidthByDesignValue750((Activity) baseActivity, 36));
        gradientDrawable.setColor(Color.parseColor(JDDarkUtil.COLOR_7F000000));
        return gradientDrawable;
    }

    private void initView(Context context) {
        SimpleDraweeView simpleDraweeView = new SimpleDraweeView(context);
        this.streamerIcon = simpleDraweeView;
        simpleDraweeView.setId(7672367);
        addView(this.streamerIcon);
        LottieAnimationView lottieAnimationView = new LottieAnimationView(context);
        this.lottieLiveIcon = lottieAnimationView;
        addView(lottieAnimationView);
        TextView textView = new TextView(context);
        this.title = textView;
        addView(textView);
        TextView textView2 = new TextView(context);
        this.pageView = textView2;
        addView(textView2);
    }

    private void realBind(BaseActivity baseActivity, Info info) {
        if (!TextUtils.isEmpty(info.streamerIcon)) {
            JDDisplayImageOptions jDDisplayImageOptions = new JDDisplayImageOptions();
            jDDisplayImageOptions.setRoundingParams(new RoundingParams().setCornersRadius(DPIUtil.getWidthByDesignValue750((Activity) baseActivity, 28)));
            JDImageLoader.display(info.streamerIcon, this.streamerIcon, jDDisplayImageOptions);
        }
        this.lottieLiveIcon.setAnimation("live_head.json");
        this.lottieLiveIcon.setSpeed(1.0f);
        this.lottieLiveIcon.setRepeatCount(-1);
        this.lottieLiveIcon.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() { // from class: com.jingdong.common.recommend.ui.video.RecommendVideoRoomInfoWidget.1
            @Override // android.view.View.OnAttachStateChangeListener
            public void onViewAttachedToWindow(View view) {
                if (RecommendVideoRoomInfoWidget.this.lottieLiveIcon.isAnimating()) {
                    return;
                }
                RecommendVideoRoomInfoWidget.this.lottieLiveIcon.playAnimation();
            }

            @Override // android.view.View.OnAttachStateChangeListener
            public void onViewDetachedFromWindow(View view) {
            }
        });
        if (!TextUtils.isEmpty(info.streamerName)) {
            this.title.setText(RecommendTextScaleUtils.subStringForTextViewToDraw(info.streamerName, 7, DPIUtil.getWidthByDesignValue750((Activity) baseActivity, (int) R2.anim.pop_left_top_out), this.title));
        } else {
            this.title.setText("");
        }
        if (!TextUtils.isEmpty(info.pageView)) {
            this.pageView.setText(RecommendTextScaleUtils.subStringForTextViewToDraw(info.pageView, 8, DPIUtil.getWidthByDesignValue750((Activity) baseActivity, 160), this.pageView));
        } else {
            this.pageView.setText("\u6b63\u5728\u76f4\u64ad\u4e2d");
        }
    }

    private void setViewLayout(BaseActivity baseActivity) {
        ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) this.streamerIcon.getLayoutParams();
        ((ViewGroup.MarginLayoutParams) layoutParams).width = DPIUtil.getWidthByDesignValue750((Activity) baseActivity, 56);
        ((ViewGroup.MarginLayoutParams) layoutParams).height = DPIUtil.getWidthByDesignValue750((Activity) baseActivity, 56);
        ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin = DPIUtil.getWidthByDesignValue750((Activity) baseActivity, 8);
        layoutParams.leftToLeft = 0;
        layoutParams.topToTop = 0;
        layoutParams.bottomToBottom = 0;
        this.streamerIcon.setLayoutParams(layoutParams);
        ConstraintLayout.LayoutParams layoutParams2 = (ConstraintLayout.LayoutParams) this.lottieLiveIcon.getLayoutParams();
        ((ViewGroup.MarginLayoutParams) layoutParams2).width = DPIUtil.getWidthByDesignValue750((Activity) baseActivity, 24);
        ((ViewGroup.MarginLayoutParams) layoutParams2).height = DPIUtil.getWidthByDesignValue750((Activity) baseActivity, 24);
        layoutParams2.rightToRight = 7672367;
        layoutParams2.bottomToBottom = 7672367;
        this.lottieLiveIcon.setLayoutParams(layoutParams2);
        ConstraintLayout.LayoutParams layoutParams3 = (ConstraintLayout.LayoutParams) this.title.getLayoutParams();
        ((ViewGroup.MarginLayoutParams) layoutParams3).width = DPIUtil.getWidthByDesignValue750((Activity) baseActivity, 170);
        ((ViewGroup.MarginLayoutParams) layoutParams3).height = DPIUtil.getWidthByDesignValue750((Activity) baseActivity, 40);
        layoutParams3.leftToRight = 7672367;
        layoutParams3.topToTop = 0;
        ((ViewGroup.MarginLayoutParams) layoutParams3).leftMargin = DPIUtil.getWidthByDesignValue750((Activity) baseActivity, 8);
        ((ViewGroup.MarginLayoutParams) layoutParams3).topMargin = DPIUtil.getWidthByDesignValue750((Activity) baseActivity, 3);
        this.title.setLayoutParams(layoutParams3);
        this.title.setGravity(19);
        this.title.setTextSize(0, DPIUtil.getWidthByDesignValue750((Activity) baseActivity, 24));
        this.title.setTextColor(-1);
        this.title.getPaint().setFakeBoldText(true);
        this.title.setSingleLine();
        ConstraintLayout.LayoutParams layoutParams4 = (ConstraintLayout.LayoutParams) this.pageView.getLayoutParams();
        ((ViewGroup.MarginLayoutParams) layoutParams4).width = DPIUtil.getWidthByDesignValue750((Activity) baseActivity, 162);
        ((ViewGroup.MarginLayoutParams) layoutParams4).height = DPIUtil.getWidthByDesignValue750((Activity) baseActivity, 36);
        layoutParams4.leftToRight = 7672367;
        layoutParams4.bottomToBottom = 0;
        ((ViewGroup.MarginLayoutParams) layoutParams4).leftMargin = DPIUtil.getWidthByDesignValue750((Activity) baseActivity, 8);
        ((ViewGroup.MarginLayoutParams) layoutParams4).bottomMargin = DPIUtil.getWidthByDesignValue750((Activity) baseActivity, 3);
        this.pageView.setLayoutParams(layoutParams4);
        this.pageView.setGravity(19);
        this.pageView.setTextSize(0, DPIUtil.getWidthByDesignValue750((Activity) baseActivity, 20));
        this.pageView.setTextColor(-1);
        this.pageView.setSingleLine();
        this.pageView.setAlpha(0.7f);
        setBackground(getDefaultBg(baseActivity));
    }

    public void bind(BaseActivity baseActivity, Info info) {
        if (info == null || baseActivity == null) {
            return;
        }
        this.info = info;
        try {
            setViewLayout(baseActivity);
            realBind(baseActivity, info);
        } catch (Exception unused) {
        }
    }

    public RecommendVideoRoomInfoWidget(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.STREAMER_ICON_ID = 7672367;
        this.TITLE_MAX_WIDTH = R2.anim.pop_left_top_out;
        this.PAGEVIEW_MAX_WIDTH = 160;
        initView(context);
    }

    public RecommendVideoRoomInfoWidget(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.STREAMER_ICON_ID = 7672367;
        this.TITLE_MAX_WIDTH = R2.anim.pop_left_top_out;
        this.PAGEVIEW_MAX_WIDTH = 160;
        initView(context);
    }
}

package com.jingdong.common.recommend.ui.homerecommend;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.text.InputFilter;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.Nullable;
import com.airbnb.lottie.LottieAnimationView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jingdong.common.network.HttpGroupUtils;
import com.jingdong.common.recommend.R;
import com.jingdong.common.recommend.RecommendUtils;
import com.jingdong.common.recommend.entity.RecommendTab;
import com.jingdong.common.recommend.ui.TabItemViewInterface;
import com.jingdong.common.utils.ABTestUtils;
import com.jingdong.common.utils.text.ScaleModeConstants;
import com.jingdong.common.utils.text.TextScaleModeHelper;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import com.jingdong.jdsdk.utils.DPIUtil;
import com.jingdong.jdsdk.utils.SharedPreferencesUtil;
import com.jingdong.sdk.oklog.OKLog;
import com.jingdong.sdk.platform.business.personal.R2;
import java.io.File;

/* loaded from: classes6.dex */
public class RecommendTabBView extends RelativeLayout implements TabItemViewInterface {
    private ImageView bottomImg;
    private String contentDescription;
    public boolean isDeepDark;
    private boolean isSelected;
    private boolean mHasSubtitle;
    private RecommendTab mRecommendTab;
    private int mSubTitleTextSize;
    private int mTitleSelectedTextSize;
    private boolean mTitleSelectedUseText;
    private int mTitleUnSelectedTextSize;
    private boolean mTitleUnSelectedUseText;
    private float progress;
    private TextView subTitleTV;
    private ValueAnimator tabValueAni;
    private SimpleDraweeView tipDV;
    private FrameLayout titleImgContainer;
    private SimpleDraweeView titleSelectedIV;
    private TextView titleTV;
    private SimpleDraweeView titleUnSelectedIV;
    private ViewGroup topContainer;
    private ImageView topImg;
    private int[] wh;

    public RecommendTabBView(Context context) {
        this(context, null);
    }

    private void changeSubTitleSize() {
        this.subTitleTV.setTextSize(0, getRightSize(this.mSubTitleTextSize));
    }

    private void changeTextColor() {
        if (this.isSelected) {
            if (this.isDeepDark) {
                if (this.mTitleSelectedUseText) {
                    TextView textView = this.titleTV;
                    RecommendTab recommendTab = this.mRecommendTab;
                    setTextColor(textView, (recommendTab == null || TextUtils.isEmpty(recommendTab.seletedTitleDarkColor)) ? "#ffFF3826" : this.mRecommendTab.seletedTitleDarkColor);
                }
                TextView textView2 = this.subTitleTV;
                RecommendTab recommendTab2 = this.mRecommendTab;
                setTextColor(textView2, (recommendTab2 == null || TextUtils.isEmpty(recommendTab2.selectedSubTitleDarkColor)) ? "#FFFF3826" : this.mRecommendTab.selectedSubTitleDarkColor);
                return;
            }
            if (this.mTitleSelectedUseText) {
                TextView textView3 = this.titleTV;
                RecommendTab recommendTab3 = this.mRecommendTab;
                setTextColor(textView3, (recommendTab3 == null || TextUtils.isEmpty(recommendTab3.seletedTitleColor)) ? "#ffE2231A" : this.mRecommendTab.seletedTitleColor);
            }
            TextView textView4 = this.subTitleTV;
            RecommendTab recommendTab4 = this.mRecommendTab;
            setTextColor(textView4, (recommendTab4 == null || TextUtils.isEmpty(recommendTab4.selectedSubTitleColor)) ? "#FFEB7873" : this.mRecommendTab.selectedSubTitleColor);
        } else if (this.isDeepDark) {
            if (this.mTitleSelectedUseText) {
                TextView textView5 = this.titleTV;
                RecommendTab recommendTab5 = this.mRecommendTab;
                setTextColor(textView5, (recommendTab5 == null || TextUtils.isEmpty(recommendTab5.unSelectedTitleDarkColor)) ? "#ffECECEC" : this.mRecommendTab.unSelectedTitleDarkColor);
            }
            TextView textView6 = this.subTitleTV;
            RecommendTab recommendTab6 = this.mRecommendTab;
            setTextColor(textView6, (recommendTab6 == null || TextUtils.isEmpty(recommendTab6.unSelectedSubTitleDarkColor)) ? "#FF848383" : this.mRecommendTab.unSelectedSubTitleDarkColor);
        } else {
            if (this.mTitleSelectedUseText) {
                TextView textView7 = this.titleTV;
                RecommendTab recommendTab7 = this.mRecommendTab;
                setTextColor(textView7, (recommendTab7 == null || TextUtils.isEmpty(recommendTab7.unSelectedTitleColor)) ? "#ff222222" : this.mRecommendTab.unSelectedTitleColor);
            }
            TextView textView8 = this.subTitleTV;
            RecommendTab recommendTab8 = this.mRecommendTab;
            setTextColor(textView8, (recommendTab8 == null || TextUtils.isEmpty(recommendTab8.unSelectedSubTitleColor)) ? "#FFA2A2A2" : this.mRecommendTab.unSelectedSubTitleColor);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void correctedView() {
        if (isLottieEnable()) {
            if (this.isSelected) {
                ((LottieAnimationView) this.topImg).setProgress(1.0f);
                ((LottieAnimationView) this.bottomImg).setProgress(1.0f);
                return;
            }
            ((LottieAnimationView) this.topImg).setProgress(0.0f);
            ((LottieAnimationView) this.bottomImg).setProgress(0.0f);
        } else if (this.isSelected) {
            this.topImg.setVisibility(0);
            this.bottomImg.setVisibility(0);
        } else {
            this.topImg.setVisibility(4);
            this.bottomImg.setVisibility(4);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Bitmap getBitmapFromResponse(HttpResponse httpResponse) {
        Bitmap decodeByteArray;
        if (httpResponse == null) {
            return null;
        }
        try {
            File saveFile = httpResponse.getSaveFile();
            if (saveFile != null) {
                decodeByteArray = BitmapFactory.decodeFile(saveFile.getPath());
            } else {
                byte[] inputData = httpResponse.getInputData();
                decodeByteArray = inputData != null ? BitmapFactory.decodeByteArray(inputData, 0, inputData.length) : null;
            }
            if (decodeByteArray != null) {
                if (decodeByteArray.getByteCount() >= 1) {
                    return decodeByteArray;
                }
            }
        } catch (Exception unused) {
        }
        return null;
    }

    private int getRightSize(int i2) {
        int i3 = RecommendUtils.windowWidthSize;
        if (i3 == 0) {
            return DPIUtil.getWidthByDesignValue750(i2);
        }
        return RecommendUtils.getWidthByDesignValue(i3, i2, R2.attr.decimalNumber);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isLottieEnable() {
        return this.topImg instanceof LottieAnimationView;
    }

    private void loadImageUrl(final ImageView imageView, String str) {
        if (imageView == null || TextUtils.isEmpty(str)) {
            return;
        }
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setUrl(str);
        httpSetting.setReferer(RecommendUtils.HTTP_REFER);
        httpSetting.setConnectTimeout(5000);
        httpSetting.setAttempts(1);
        httpSetting.setCacheMode(0);
        httpSetting.setType(5000);
        httpSetting.setListener(new HttpGroup.OnAllListener() { // from class: com.jingdong.common.recommend.ui.homerecommend.RecommendTabBView.5
            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
            public void onEnd(HttpResponse httpResponse) {
                try {
                    final Bitmap bitmapFromResponse = RecommendTabBView.this.getBitmapFromResponse(httpResponse);
                    if (bitmapFromResponse != null) {
                        RecommendTabBView.this.post(new Runnable() { // from class: com.jingdong.common.recommend.ui.homerecommend.RecommendTabBView.5.1
                            @Override // java.lang.Runnable
                            public void run() {
                                try {
                                    imageView.setImageBitmap(bitmapFromResponse);
                                    AnonymousClass5 anonymousClass5 = AnonymousClass5.this;
                                    RecommendTabBView.this.onImageLoadSuccess(imageView);
                                } catch (Exception unused) {
                                }
                            }
                        });
                    }
                } catch (Exception e2) {
                    if (OKLog.D) {
                        throw e2;
                    }
                }
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
            public void onError(HttpError httpError) {
                try {
                    if (imageView == RecommendTabBView.this.tipDV) {
                        RecommendTabBView.this.post(new Runnable() { // from class: com.jingdong.common.recommend.ui.homerecommend.RecommendTabBView.5.2
                            @Override // java.lang.Runnable
                            public void run() {
                                try {
                                    imageView.setImageResource(R.drawable.recommend_home_tab_tip_icon);
                                } catch (Exception unused) {
                                }
                            }
                        });
                    }
                } catch (Exception e2) {
                    if (OKLog.D) {
                        throw e2;
                    }
                }
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnProgressListener
            public void onProgress(int i2, int i3) {
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnStartListener
            public void onStart() {
            }
        });
        httpSetting.setNeedShareImage(false);
        HttpGroupUtils.getHttpGroupaAsynPool(5000).add(httpSetting);
    }

    private void loadImageView() {
        RecommendTab recommendTab = this.mRecommendTab;
        if (recommendTab == null) {
            return;
        }
        loadImageUrl(this.titleSelectedIV, recommendTab.selectedTitleImg);
        loadImageUrl(this.titleUnSelectedIV, this.mRecommendTab.unselectedTitleImg);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onImageLoadSuccess(View view) {
        SimpleDraweeView simpleDraweeView = this.titleSelectedIV;
        if (view == simpleDraweeView) {
            this.mTitleSelectedUseText = false;
        } else if (view == this.titleUnSelectedIV) {
            this.mTitleUnSelectedUseText = false;
        }
        if (view == simpleDraweeView || view == this.titleUnSelectedIV) {
            updateView();
        }
    }

    private void ratioLayout() {
        if (this.subTitleTV.getLayoutParams() != null && (this.subTitleTV.getLayoutParams() instanceof RelativeLayout.LayoutParams)) {
            ((RelativeLayout.LayoutParams) this.subTitleTV.getLayoutParams()).bottomMargin = getRightSize(27);
        }
        if (this.titleSelectedIV.getLayoutParams() != null) {
            this.titleSelectedIV.getLayoutParams().width = getRightSize(122);
            this.titleSelectedIV.getLayoutParams().height = getRightSize(46);
        }
        if (this.titleUnSelectedIV.getLayoutParams() != null) {
            this.titleUnSelectedIV.getLayoutParams().width = getRightSize(94);
            this.titleUnSelectedIV.getLayoutParams().height = getRightSize(36);
        }
        if (this.topImg.getLayoutParams() instanceof RelativeLayout.LayoutParams) {
            this.topImg.getLayoutParams().width = getRightSize(22);
            this.topImg.getLayoutParams().height = getRightSize(22);
            ((RelativeLayout.LayoutParams) this.topImg.getLayoutParams()).rightMargin = -getRightSize(11);
            ((RelativeLayout.LayoutParams) this.topImg.getLayoutParams()).bottomMargin = -getRightSize(7);
        }
        if (this.bottomImg.getLayoutParams() instanceof RelativeLayout.LayoutParams) {
            this.bottomImg.getLayoutParams().width = getRightSize(22);
            this.bottomImg.getLayoutParams().height = getRightSize(22);
            ((RelativeLayout.LayoutParams) this.bottomImg.getLayoutParams()).rightMargin = -getRightSize(11);
            ((RelativeLayout.LayoutParams) this.bottomImg.getLayoutParams()).bottomMargin = -getRightSize(8);
        }
        changeSubTitleSize();
    }

    private void setTextColor(TextView textView, String str) {
        if (textView != null) {
            try {
                if (TextUtils.isEmpty(str)) {
                    return;
                }
                textView.setTextColor(Color.parseColor(str));
            } catch (Exception e2) {
                if (OKLog.D) {
                    e2.printStackTrace();
                }
            }
        }
    }

    private void updateView() {
        if (this.isSelected) {
            if (this.mTitleSelectedUseText) {
                this.titleTV.setVisibility(0);
                if (this.topContainer.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
                    ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.topContainer.getLayoutParams();
                    if (ScaleModeConstants.TEXT_SCALE_MODE_LARGE.equals(TextScaleModeHelper.INSTANCE.getInstance().getTextSizeScaleMode())) {
                        marginLayoutParams.topMargin = getRightSize(13);
                    } else {
                        marginLayoutParams.topMargin = getRightSize(16);
                    }
                }
                this.titleTV.setTextSize(0, getRightSize(this.mTitleSelectedTextSize));
                this.titleImgContainer.setVisibility(8);
            } else {
                if (this.topContainer.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
                    ((ViewGroup.MarginLayoutParams) this.topContainer.getLayoutParams()).topMargin = getRightSize(16);
                }
                this.titleImgContainer.setVisibility(0);
                this.titleTV.setVisibility(4);
                this.titleSelectedIV.setVisibility(0);
                this.titleUnSelectedIV.setVisibility(4);
            }
            if (this.mRecommendTab.isShowDot) {
                this.tipDV.setVisibility(8);
                this.mRecommendTab.isShowDot = false;
                SharedPreferencesUtil.putString(this.mRecommendTab.tabId + "", this.mRecommendTab.redDotId);
            }
            ImageView imageView = this.topImg;
            if (imageView != null) {
                imageView.setAlpha(1.0f - this.progress);
            }
            ImageView imageView2 = this.bottomImg;
            if (imageView2 != null) {
                imageView2.setAlpha(this.progress);
            }
        } else if (this.mTitleUnSelectedUseText) {
            if (this.topContainer.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
                ViewGroup.MarginLayoutParams marginLayoutParams2 = (ViewGroup.MarginLayoutParams) this.topContainer.getLayoutParams();
                if (ScaleModeConstants.TEXT_SCALE_MODE_LARGE.equals(TextScaleModeHelper.INSTANCE.getInstance().getTextSizeScaleMode())) {
                    marginLayoutParams2.topMargin = getRightSize(17);
                } else {
                    marginLayoutParams2.topMargin = getRightSize(19);
                }
            }
            this.titleTV.setTextSize(0, getRightSize(this.mTitleUnSelectedTextSize));
            this.titleImgContainer.setVisibility(8);
            this.titleTV.setVisibility(0);
        } else {
            if (this.topContainer.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
                ((ViewGroup.MarginLayoutParams) this.topContainer.getLayoutParams()).topMargin = getRightSize(20);
            }
            this.titleImgContainer.setVisibility(0);
            this.titleTV.setVisibility(4);
            this.titleSelectedIV.setVisibility(4);
            this.titleUnSelectedIV.setVisibility(0);
        }
        changeTextColor();
        setSelected(this.isSelected);
    }

    @Override // com.jingdong.common.recommend.ui.TabItemViewInterface
    public void onDeepDarkChanged(boolean z) {
        this.isDeepDark = z;
        changeTextColor();
    }

    @Override // com.jingdong.common.recommend.ui.TabItemViewInterface
    public void onTextScaleModeChanged() {
        if (ScaleModeConstants.TEXT_SCALE_MODE_LARGE.equals(TextScaleModeHelper.INSTANCE.getInstance().getTextSizeScaleMode())) {
            this.mTitleSelectedTextSize = 40;
            this.mTitleUnSelectedTextSize = 34;
            this.mSubTitleTextSize = 28;
        } else {
            this.mTitleSelectedTextSize = 36;
            this.mTitleUnSelectedTextSize = 32;
            this.mSubTitleTextSize = 26;
        }
        changeSubTitleSize();
        if (this.isSelected) {
            this.titleTV.setTextSize(0, getRightSize(this.mTitleSelectedTextSize));
        } else {
            this.titleTV.setTextSize(0, getRightSize(this.mTitleUnSelectedTextSize));
        }
    }

    @Override // com.jingdong.common.recommend.ui.TabItemViewInterface
    public void onWidthSizeChange() {
        try {
            if (getLayoutParams() != null) {
                getLayoutParams().height = getRightSize(this.wh[1]);
                getLayoutParams().width = getRightSize(this.wh[0]);
            }
            ratioLayout();
            updateView();
        } catch (Exception e2) {
            if (OKLog.E) {
                e2.printStackTrace();
            }
        }
    }

    @Override // com.jingdong.common.recommend.ui.TabItemViewInterface
    public void setChangeProgress(float f2) {
        this.progress = f2;
        if (this.mHasSubtitle) {
            TextView textView = this.subTitleTV;
            if (textView != null) {
                textView.setAlpha(f2);
            }
            ImageView imageView = this.topImg;
            if (imageView != null) {
                imageView.setAlpha(1.0f - f2);
            }
            ImageView imageView2 = this.bottomImg;
            if (imageView2 != null) {
                imageView2.setAlpha(f2);
            }
        }
    }

    @Override // com.jingdong.common.recommend.ui.TabItemViewInterface
    public void setHasSplitLine(boolean z) {
    }

    @Override // com.jingdong.common.recommend.ui.TabItemViewInterface
    public void setHasSubTitle(boolean z) {
        this.mHasSubtitle = z;
        if (z) {
            this.progress = 1.0f;
            this.subTitleTV.setVisibility(0);
            this.bottomImg.setVisibility(0);
        } else {
            this.subTitleTV.setVisibility(8);
            this.bottomImg.setVisibility(8);
            this.progress = 0.0f;
        }
        this.bottomImg.setAlpha(this.progress);
        this.topImg.setAlpha(1.0f - this.progress);
        correctedView();
    }

    @Override // com.jingdong.common.recommend.ui.TabItemViewInterface
    public void setRecommendTab(RecommendTab recommendTab) {
        this.mRecommendTab = recommendTab;
        if (recommendTab != null) {
            this.titleTV.setText(recommendTab.title);
            this.subTitleTV.setText(recommendTab.subtitle);
            if (this.mRecommendTab.isShowDot) {
                this.tipDV.setVisibility(0);
                if (!TextUtils.isEmpty(this.mRecommendTab.redDotImg)) {
                    loadImageUrl(this.tipDV, this.mRecommendTab.redDotImg);
                }
            } else {
                this.tipDV.setVisibility(8);
            }
            if (!TextUtils.isEmpty(this.mRecommendTab.unselectedTitleImg) || !TextUtils.isEmpty(this.mRecommendTab.selectedTitleImg)) {
                loadImageView();
            }
            if (!TextUtils.isEmpty(recommendTab.title)) {
                this.contentDescription += recommendTab.title;
            }
            if (!TextUtils.isEmpty(recommendTab.subtitle)) {
                this.contentDescription += recommendTab.subtitle;
            }
        }
        setContentDescription(this.contentDescription);
    }

    @Override // com.jingdong.common.recommend.ui.TabItemViewInterface
    public void setTabSelect(boolean z) {
        this.isSelected = z;
        updateView();
    }

    @Override // com.jingdong.common.recommend.ui.TabItemViewInterface
    public void setWH(int i2, int i3) {
        int[] iArr = this.wh;
        iArr[0] = i2;
        iArr[1] = i3;
        setLayoutParams(new LinearLayout.LayoutParams(getRightSize(i2), getRightSize(i3)));
    }

    @Override // com.jingdong.common.recommend.ui.TabItemViewInterface
    public void startTabAni(boolean z, boolean z2) {
        if (isLottieEnable() && z2) {
            if (z) {
                this.tabValueAni.setFloatValues(0.0f, 1.0f);
                this.tabValueAni.setDuration(500L);
            } else {
                this.tabValueAni.setFloatValues(1.0f, 0.0f);
                this.tabValueAni.setDuration(250L);
            }
            this.tabValueAni.start();
            return;
        }
        correctedView();
    }

    public RecommendTabBView(Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public RecommendTabBView(Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mTitleSelectedUseText = true;
        this.mTitleUnSelectedUseText = true;
        this.mTitleSelectedTextSize = 36;
        this.mTitleUnSelectedTextSize = 32;
        this.mSubTitleTextSize = 26;
        this.isSelected = false;
        this.contentDescription = "";
        this.mHasSubtitle = true;
        this.wh = new int[]{0, 0};
        this.progress = 1.0f;
        this.isDeepDark = false;
        if (ScaleModeConstants.TEXT_SCALE_MODE_LARGE.equals(TextScaleModeHelper.INSTANCE.getInstance().getTextSizeScaleMode())) {
            this.mTitleSelectedTextSize = 40;
            this.mTitleUnSelectedTextSize = 34;
            this.mSubTitleTextSize = 28;
        } else {
            this.mTitleSelectedTextSize = 36;
            this.mTitleUnSelectedTextSize = 32;
            this.mSubTitleTextSize = 26;
        }
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        this.topContainer = new FrameLayout(context);
        layoutParams.addRule(14);
        layoutParams.topMargin = getRightSize(13);
        ViewGroup viewGroup = this.topContainer;
        int i3 = R.id.recommend_tab_top;
        viewGroup.setId(i3);
        addView(this.topContainer, layoutParams);
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams2.addRule(14);
        TextView textView = new TextView(context);
        this.titleTV = textView;
        textView.setTypeface(Typeface.defaultFromStyle(1));
        this.titleTV.setTextSize(0, getRightSize(this.mTitleUnSelectedTextSize));
        this.titleTV.setFilters(new InputFilter[]{new InputFilter.LengthFilter(2) { // from class: com.jingdong.common.recommend.ui.homerecommend.RecommendTabBView.1
        }});
        this.titleTV.setGravity(1);
        this.topContainer.addView(this.titleTV, layoutParams2);
        this.titleImgContainer = new FrameLayout(context);
        FrameLayout.LayoutParams layoutParams3 = new FrameLayout.LayoutParams(-2, -2);
        layoutParams3.gravity = 17;
        SimpleDraweeView simpleDraweeView = new SimpleDraweeView(context);
        this.titleSelectedIV = simpleDraweeView;
        simpleDraweeView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        this.titleSelectedIV.setLayoutParams(layoutParams3);
        this.titleImgContainer.addView(this.titleSelectedIV);
        FrameLayout.LayoutParams layoutParams4 = new FrameLayout.LayoutParams(-2, -2);
        layoutParams4.gravity = 17;
        SimpleDraweeView simpleDraweeView2 = new SimpleDraweeView(context);
        this.titleUnSelectedIV = simpleDraweeView2;
        simpleDraweeView2.setScaleType(ImageView.ScaleType.FIT_CENTER);
        this.titleImgContainer.addView(this.titleUnSelectedIV, layoutParams4);
        this.titleImgContainer.setVisibility(8);
        this.topContainer.addView(this.titleImgContainer);
        RelativeLayout.LayoutParams layoutParams5 = new RelativeLayout.LayoutParams(DPIUtil.dip2px(10.0f), DPIUtil.dip2px(10.0f));
        layoutParams5.rightMargin = -DPIUtil.dip2px(5.0f);
        layoutParams5.bottomMargin = -DPIUtil.dip2px(4.0f);
        layoutParams5.addRule(8, i3);
        layoutParams5.addRule(7, i3);
        if (Build.VERSION.SDK_INT >= 16 && ABTestUtils.isLottieEnable()) {
            LottieAnimationView lottieAnimationView = new LottieAnimationView(getContext());
            this.topImg = lottieAnimationView;
            lottieAnimationView.setAnimation("recommend_tab_b_smile.json");
            LottieAnimationView lottieAnimationView2 = new LottieAnimationView(getContext());
            this.bottomImg = lottieAnimationView2;
            lottieAnimationView2.setAnimation("recommend_tab_b_smile.json");
        } else {
            ImageView imageView = new ImageView(getContext());
            this.topImg = imageView;
            Resources resources = getResources();
            int i4 = R.drawable.recommend_tab_b_smile;
            imageView.setImageDrawable(resources.getDrawable(i4));
            ImageView imageView2 = new ImageView(context);
            this.bottomImg = imageView2;
            imageView2.setImageDrawable(getResources().getDrawable(i4));
        }
        this.topImg.setScaleType(ImageView.ScaleType.CENTER_CROP);
        this.bottomImg.setScaleType(ImageView.ScaleType.CENTER_CROP);
        addView(this.topImg, layoutParams5);
        this.subTitleTV = new TextView(context);
        RelativeLayout.LayoutParams layoutParams6 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams6.addRule(14);
        layoutParams6.addRule(12);
        this.subTitleTV.setTextSize(0, getRightSize(this.mSubTitleTextSize));
        this.subTitleTV.setFilters(new InputFilter[]{new InputFilter.LengthFilter(6) { // from class: com.jingdong.common.recommend.ui.homerecommend.RecommendTabBView.2
        }});
        TextView textView2 = this.subTitleTV;
        int i5 = R.id.recommend_tab_subtitle;
        textView2.setId(i5);
        addView(this.subTitleTV, layoutParams6);
        RelativeLayout.LayoutParams layoutParams7 = new RelativeLayout.LayoutParams(DPIUtil.dip2px(10.0f), DPIUtil.dip2px(10.0f));
        layoutParams7.rightMargin = -DPIUtil.dip2px(5.0f);
        layoutParams7.bottomMargin = -DPIUtil.dip2px(4.0f);
        layoutParams7.addRule(8, i5);
        layoutParams7.addRule(7, i5);
        addView(this.bottomImg, layoutParams7);
        RelativeLayout.LayoutParams layoutParams8 = new RelativeLayout.LayoutParams(DPIUtil.dip2px(10.0f), DPIUtil.dip2px(10.0f));
        layoutParams8.addRule(1, i3);
        layoutParams8.addRule(6, i3);
        layoutParams8.width = -1;
        layoutParams8.height = DPIUtil.dip2px(9.0f);
        layoutParams8.bottomMargin = DPIUtil.dip2px(9.0f);
        layoutParams8.leftMargin = DPIUtil.dip2px(2.0f);
        SimpleDraweeView simpleDraweeView3 = new SimpleDraweeView(context);
        this.tipDV = simpleDraweeView3;
        simpleDraweeView3.setScaleType(ImageView.ScaleType.FIT_START);
        this.tipDV.setVisibility(8);
        addView(this.tipDV, layoutParams8);
        ratioLayout();
        updateView();
        ValueAnimator valueAnimator = new ValueAnimator();
        this.tabValueAni = valueAnimator;
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.jingdong.common.recommend.ui.homerecommend.RecommendTabBView.3
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator2) {
                if (RecommendTabBView.this.isLottieEnable()) {
                    if (RecommendTabBView.this.isSelected) {
                        ((LottieAnimationView) RecommendTabBView.this.bottomImg).setProgress(((Float) valueAnimator2.getAnimatedValue()).floatValue());
                        ((LottieAnimationView) RecommendTabBView.this.topImg).setProgress(((Float) valueAnimator2.getAnimatedValue()).floatValue());
                        return;
                    }
                    ((LottieAnimationView) RecommendTabBView.this.topImg).setProgress(((Float) valueAnimator2.getAnimatedValue()).floatValue() / 2.0f);
                    ((LottieAnimationView) RecommendTabBView.this.bottomImg).setProgress(((Float) valueAnimator2.getAnimatedValue()).floatValue() / 2.0f);
                }
            }
        });
        this.tabValueAni.addListener(new Animator.AnimatorListener() { // from class: com.jingdong.common.recommend.ui.homerecommend.RecommendTabBView.4
            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animator) {
                RecommendTabBView.this.correctedView();
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                RecommendTabBView.this.correctedView();
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationRepeat(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
            }
        });
    }
}

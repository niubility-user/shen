package com.jingdong.common.recommend.ui.personal;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
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
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import com.jingdong.jdsdk.utils.DPIUtil;
import com.jingdong.sdk.oklog.OKLog;
import com.jingdong.sdk.platform.business.personal.R2;
import java.io.File;

/* loaded from: classes6.dex */
public class PersonalTabBView extends RelativeLayout implements TabItemViewInterface {
    private String contentDescription;
    public boolean isDeepDark;
    private boolean isSelected;
    private boolean mHasSubtitle;
    private RecommendTab mRecommendTab;
    private int mTitleSelectedTextSize;
    private boolean mTitleSelectedUseText;
    private int mTitleUnSelectedTextSize;
    private boolean mTitleUnSelectedUseText;
    private ValueAnimator tabValueAni;
    private FrameLayout titleImgContainer;
    private SimpleDraweeView titleSelectedIV;
    private TextView titleTV;
    private SimpleDraweeView titleUnSelectedIV;
    private ViewGroup topContainer;
    private ImageView topImg;
    private int[] wh;

    public PersonalTabBView(Context context) {
        this(context, null);
    }

    private void changeTextColor() {
        if (this.isSelected) {
            if (this.isDeepDark) {
                if (this.mTitleSelectedUseText) {
                    TextView textView = this.titleTV;
                    RecommendTab recommendTab = this.mRecommendTab;
                    setTextColor(textView, (recommendTab == null || TextUtils.isEmpty(recommendTab.seletedTitleDarkColor)) ? "#ffFF3826" : this.mRecommendTab.seletedTitleDarkColor);
                }
            } else if (this.mTitleSelectedUseText) {
                TextView textView2 = this.titleTV;
                RecommendTab recommendTab2 = this.mRecommendTab;
                setTextColor(textView2, (recommendTab2 == null || TextUtils.isEmpty(recommendTab2.seletedTitleColor)) ? "#ffE2231A" : this.mRecommendTab.seletedTitleColor);
            }
        } else if (this.isDeepDark) {
            if (this.mTitleSelectedUseText) {
                TextView textView3 = this.titleTV;
                RecommendTab recommendTab3 = this.mRecommendTab;
                setTextColor(textView3, (recommendTab3 == null || TextUtils.isEmpty(recommendTab3.unSelectedTitleDarkColor)) ? "#ffECECEC" : this.mRecommendTab.unSelectedTitleDarkColor);
            }
        } else if (this.mTitleSelectedUseText) {
            TextView textView4 = this.titleTV;
            RecommendTab recommendTab4 = this.mRecommendTab;
            setTextColor(textView4, (recommendTab4 == null || TextUtils.isEmpty(recommendTab4.unSelectedTitleColor)) ? "#ff222222" : this.mRecommendTab.unSelectedTitleColor);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void correctedView() {
        if (isLottieEnable()) {
            if (this.isSelected) {
                ((LottieAnimationView) this.topImg).setProgress(1.0f);
            } else {
                ((LottieAnimationView) this.topImg).setProgress(0.0f);
            }
        } else if (this.isSelected) {
            this.topImg.setVisibility(0);
        } else {
            this.topImg.setVisibility(4);
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
        httpSetting.setListener(new HttpGroup.OnAllListener() { // from class: com.jingdong.common.recommend.ui.personal.PersonalTabBView.4
            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
            public void onEnd(HttpResponse httpResponse) {
                try {
                    final Bitmap bitmapFromResponse = PersonalTabBView.this.getBitmapFromResponse(httpResponse);
                    if (bitmapFromResponse != null) {
                        PersonalTabBView.this.post(new Runnable() { // from class: com.jingdong.common.recommend.ui.personal.PersonalTabBView.4.1
                            @Override // java.lang.Runnable
                            public void run() {
                                try {
                                    imageView.setImageBitmap(bitmapFromResponse);
                                    AnonymousClass4 anonymousClass4 = AnonymousClass4.this;
                                    PersonalTabBView.this.onImageLoadSuccess(imageView);
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
                    ((ViewGroup.MarginLayoutParams) this.topContainer.getLayoutParams()).topMargin = getRightSize(16);
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
        } else if (this.mTitleUnSelectedUseText) {
            if (this.topContainer.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
                ((ViewGroup.MarginLayoutParams) this.topContainer.getLayoutParams()).topMargin = getRightSize(19);
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
    }

    @Override // com.jingdong.common.recommend.ui.TabItemViewInterface
    public void onWidthSizeChange() {
        try {
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
    }

    @Override // com.jingdong.common.recommend.ui.TabItemViewInterface
    public void setHasSplitLine(boolean z) {
    }

    @Override // com.jingdong.common.recommend.ui.TabItemViewInterface
    public void setHasSubTitle(boolean z) {
    }

    @Override // com.jingdong.common.recommend.ui.TabItemViewInterface
    public void setRecommendTab(RecommendTab recommendTab) {
        this.mRecommendTab = recommendTab;
        if (recommendTab != null) {
            this.titleTV.setText(recommendTab.title);
            if (!TextUtils.isEmpty(this.mRecommendTab.unselectedTitleImg) || !TextUtils.isEmpty(this.mRecommendTab.selectedTitleImg)) {
                loadImageView();
            }
            if (!TextUtils.isEmpty(recommendTab.title)) {
                this.contentDescription += recommendTab.title;
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

    public PersonalTabBView(Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public PersonalTabBView(Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mTitleSelectedUseText = true;
        this.mTitleUnSelectedUseText = true;
        this.mTitleSelectedTextSize = 36;
        this.mTitleUnSelectedTextSize = 32;
        this.isSelected = false;
        this.contentDescription = "";
        this.mHasSubtitle = true;
        this.wh = new int[]{0, 0};
        this.isDeepDark = false;
        setLayoutParams(new RelativeLayout.LayoutParams(-2, -2));
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        this.topContainer = new FrameLayout(context);
        layoutParams.addRule(14);
        layoutParams.topMargin = getRightSize(16);
        layoutParams.rightMargin = getRightSize(16);
        layoutParams.bottomMargin = getRightSize(16);
        layoutParams.leftMargin = getRightSize(16);
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
        this.titleTV.setFilters(new InputFilter[]{new InputFilter.LengthFilter(6) { // from class: com.jingdong.common.recommend.ui.personal.PersonalTabBView.1
        }});
        this.titleTV.setGravity(1);
        this.topContainer.addView(this.titleTV, layoutParams2);
        this.titleImgContainer = new FrameLayout(context);
        FrameLayout.LayoutParams layoutParams3 = new FrameLayout.LayoutParams(-2, -2);
        layoutParams3.gravity = 17;
        SimpleDraweeView simpleDraweeView = new SimpleDraweeView(context);
        this.titleSelectedIV = simpleDraweeView;
        simpleDraweeView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        this.titleImgContainer.addView(this.titleSelectedIV, layoutParams3);
        this.titleUnSelectedIV = new SimpleDraweeView(context);
        FrameLayout.LayoutParams layoutParams4 = new FrameLayout.LayoutParams(-2, -2);
        layoutParams4.gravity = 17;
        this.titleUnSelectedIV.setScaleType(ImageView.ScaleType.FIT_CENTER);
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
        } else {
            ImageView imageView = new ImageView(getContext());
            this.topImg = imageView;
            imageView.setImageDrawable(getResources().getDrawable(R.drawable.recommend_tab_b_smile));
            this.topImg.setScaleType(ImageView.ScaleType.CENTER);
        }
        addView(this.topImg, layoutParams5);
        correctedView();
        ratioLayout();
        updateView();
        ValueAnimator valueAnimator = new ValueAnimator();
        this.tabValueAni = valueAnimator;
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.jingdong.common.recommend.ui.personal.PersonalTabBView.2
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator2) {
                if (PersonalTabBView.this.isLottieEnable()) {
                    if (PersonalTabBView.this.isSelected) {
                        ((LottieAnimationView) PersonalTabBView.this.topImg).setProgress(((Float) valueAnimator2.getAnimatedValue()).floatValue());
                    } else {
                        ((LottieAnimationView) PersonalTabBView.this.topImg).setProgress(((Float) valueAnimator2.getAnimatedValue()).floatValue() / 2.0f);
                    }
                }
            }
        });
        this.tabValueAni.addListener(new Animator.AnimatorListener() { // from class: com.jingdong.common.recommend.ui.personal.PersonalTabBView.3
            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animator) {
                PersonalTabBView.this.correctedView();
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                PersonalTabBView.this.correctedView();
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

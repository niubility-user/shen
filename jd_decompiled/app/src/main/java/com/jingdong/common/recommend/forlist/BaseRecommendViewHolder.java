package com.jingdong.common.recommend.forlist;

import android.graphics.Paint;
import android.text.SpannableString;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.jingdong.common.recommend.R;
import com.jingdong.common.recommend.RecommendConfig;
import com.jingdong.common.recommend.RecommendFontUtils;
import com.jingdong.common.recommend.RecommendUtils;
import com.jingdong.common.recommend.RecommendViewUtil;
import com.jingdong.common.recommend.entity.RecommendVideo;
import com.jingdong.common.recommend.forlist.RecommendUtil;
import com.jingdong.common.recommend.ui.video.RecommendVideoWidget;
import com.jingdong.common.unification.uniconfig.UnIconConfigHelper;
import com.jingdong.common.utils.DeepDarkUtils;
import com.jingdong.common.widget.custom.livewidget.bean.LiveVideoEntity;
import com.jingdong.sdk.oklog.OKLog;
import java.util.HashSet;
import java.util.List;

/* loaded from: classes6.dex */
public class BaseRecommendViewHolder extends RecyclerView.ViewHolder {
    public float bgAspectRatio;
    protected RecommendUtil.OnRecommendClickedListener clickedListener;
    private View darkCoverView;
    protected RecommendUtil.IRecommendHomePageTestPlanLoader homePageTestPlanLoader;
    private boolean isAdRealExpo;
    RecommendConfig mRecommendConfig;
    protected int pageFrom;
    protected HashSet<String> realExpoHashSet;
    protected int recommendUIMode;
    public RecommendVideoWidget recommendVideoWidget;

    public BaseRecommendViewHolder(View view) {
        super(view);
        this.realExpoHashSet = null;
        this.bgAspectRatio = 0.642f;
        this.isAdRealExpo = false;
        this.darkCoverView = view.findViewById(R.id.deep_dark_cover);
    }

    private boolean isProductRecommend() {
        int i2 = this.pageFrom;
        return i2 == 35 || i2 == 24 || i2 == 48 || i2 == 10035;
    }

    private void refreshBaseView() {
        View view = this.darkCoverView;
        if (view != null) {
            if (this.pageFrom == 9) {
                RecommendViewUtil.gone(view);
            } else if (isDeepDark()) {
                RecommendViewUtil.visible(this.darkCoverView);
            } else {
                RecommendViewUtil.gone(this.darkCoverView);
            }
        }
    }

    public void changeVideoViewHeight(float f2) {
        RecommendVideoWidget recommendVideoWidget;
        ViewGroup.LayoutParams layoutParams;
        if (f2 <= 0.0f || (recommendVideoWidget = this.recommendVideoWidget) == null || (layoutParams = recommendVideoWidget.getLayoutParams()) == null) {
            return;
        }
        layoutParams.height = (int) ((this.recommendVideoWidget.getWidth() > 0 ? this.recommendVideoWidget.getWidth() : RecommendViewUtil.getLineTwoItemWidth(this.itemView.getContext())) / f2);
        this.recommendVideoWidget.setLayoutParams(layoutParams);
        this.recommendVideoWidget.requestLayout();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean checkHomePageTestPlanIsA() {
        RecommendUtil.IRecommendHomePageTestPlanLoader iRecommendHomePageTestPlanLoader = this.homePageTestPlanLoader;
        if (iRecommendHomePageTestPlanLoader != null) {
            return iRecommendHomePageTestPlanLoader.checkHomePageTestPlanIsA();
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void exposurePercent(double d) {
    }

    public int getColor_262626() {
        if (isDeepDark()) {
            return DeepDarkUtils.getDarkColor_262626();
        }
        return RecommendUtils.normalColor_262626;
    }

    public int getColor_2E2D2D() {
        if (isDeepDark()) {
            return DeepDarkUtils.getDarkColor_262626();
        }
        return RecommendUtils.normalColor_2E2D2D;
    }

    public int getColor_FFFFFF() {
        if (isDeepDark()) {
            return DeepDarkUtils.getDarkColor_FFFFFF();
        }
        return -1;
    }

    protected float getFitTextSize(TextView textView, String str, float f2, float f3) {
        if (textView == null) {
            return 0.0f;
        }
        float textSize = textView.getTextSize();
        if (TextUtils.isEmpty(str) || f2 <= 0.0f) {
            return textSize;
        }
        float f4 = f3 == 0.0f ? textSize : f3;
        try {
            Paint paint = new Paint();
            paint.set(textView.getPaint());
            paint.setTextSize(f4);
            float measureText = paint.measureText(str);
            if (OKLog.D) {
                StringBuilder sb = new StringBuilder();
                sb.append("---");
                sb.append(f3 > f2);
                OKLog.d("RECOMMEND_MEASURE_TEXTSIZE", sb.toString());
            }
            while (true) {
                float f5 = 6;
                if (f4 <= f5 || measureText <= f2) {
                    return textSize;
                }
                f4 -= 1.0f;
                textSize -= 1.0f;
                if (f4 <= f5) {
                    return f5;
                }
                measureText = paint.measureText(str);
                paint.setTextSize(f4);
            }
        } catch (Exception e2) {
            if (OKLog.D) {
                e2.printStackTrace();
                return textSize;
            }
            return textSize;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int getRecommendUIMode() {
        int recommendUIMode = RecommendFontUtils.getRecommendUIMode(this.mRecommendConfig);
        this.recommendUIMode = recommendUIMode;
        return recommendUIMode;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public SpannableString getSpannableString(List<String> list, String str, TextView textView) {
        return UnIconConfigHelper.getSpanableString(list, str, textView, RecommendFontUtils.enableFontRule(this.recommendUIMode));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public TextView getTextView(String str, String str2) {
        return UnIconConfigHelper.getTextView(str, str2, RecommendFontUtils.enableFontRule(this.recommendUIMode));
    }

    public void inflateRecommendVideo() {
        RecommendVideoWidget recommendVideoWidget = (RecommendVideoWidget) this.itemView.findViewById(R.id.recommend_widget_new);
        this.recommendVideoWidget = recommendVideoWidget;
        recommendVideoWidget.setVisibility(0);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean isDeepDark() {
        if (this.mRecommendConfig != null) {
            if (isProductRecommend()) {
                return this.mRecommendConfig.isDarkTheme();
            }
            return this.mRecommendConfig.isDarkEnable();
        }
        return false;
    }

    public boolean isElder() {
        return RecommendFontUtils.RECOMMEND_ELDER_MODE == RecommendFontUtils.getRecommendUIMode(this.mRecommendConfig);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void realExpo(String str, String str2) {
        if (this.isAdRealExpo || this.clickedListener == null || TextUtils.isEmpty(str)) {
            return;
        }
        if (TextUtils.isEmpty(str2)) {
            this.clickedListener.onRecommendMoneyExpo(str);
            return;
        }
        HashSet<String> hashSet = this.realExpoHashSet;
        if (hashSet == null || hashSet.contains(str2)) {
            return;
        }
        this.clickedListener.onRecommendMoneyExpo(str);
        OKLog.d("RecommendAd", "==\u5e7f\u544abind\u4e0a\u62a5====");
        this.realExpoHashSet.add(str2);
    }

    public void setClickedListener(RecommendUtil.OnRecommendClickedListener onRecommendClickedListener) {
        this.clickedListener = onRecommendClickedListener;
    }

    public void setFrom(int i2) {
        this.pageFrom = i2;
        refreshBaseView();
    }

    public void setHomePageTestPlanLoader(RecommendUtil.IRecommendHomePageTestPlanLoader iRecommendHomePageTestPlanLoader) {
        this.homePageTestPlanLoader = iRecommendHomePageTestPlanLoader;
    }

    public void setIsAdRealExpo(boolean z) {
        this.isAdRealExpo = z;
    }

    public void setLiveData(LiveVideoEntity liveVideoEntity) {
        RecommendVideoWidget recommendVideoWidget = this.recommendVideoWidget;
        if (recommendVideoWidget == null) {
            return;
        }
        recommendVideoWidget.setLiVeData(liveVideoEntity);
    }

    public void setRealExpoHashSet(HashSet<String> hashSet) {
        this.realExpoHashSet = hashSet;
    }

    public void setVideoData(RecommendVideo recommendVideo, String str) {
        RecommendVideoWidget recommendVideoWidget = this.recommendVideoWidget;
        if (recommendVideoWidget == null) {
            return;
        }
        recommendVideoWidget.setVideoData(recommendVideo, str);
        this.recommendVideoWidget.setTag(recommendVideo);
    }

    public void setmRecommendConfig(RecommendConfig recommendConfig) {
        this.mRecommendConfig = recommendConfig;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void realExpo(String str) {
        RecommendUtil.OnRecommendClickedListener onRecommendClickedListener;
        if (this.isAdRealExpo || (onRecommendClickedListener = this.clickedListener) == null) {
            return;
        }
        onRecommendClickedListener.onRecommendMoneyExpo(str);
        OKLog.d("RecommendAd", "==\u5e7f\u544abind\u4e0a\u62a5====");
    }
}

package com.jingdong.common.recommend.forlist;

import android.graphics.Bitmap;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jingdong.app.util.image.JDDisplayImageOptions;
import com.jingdong.app.util.image.assist.JDFailReason;
import com.jingdong.app.util.image.listener.JDImageLoadingListener;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.recommend.ExpoDataStore;
import com.jingdong.common.recommend.R;
import com.jingdong.common.recommend.RecommendFontUtils;
import com.jingdong.common.recommend.RecommendMtaUtils;
import com.jingdong.common.recommend.RecommendViewUtil;
import com.jingdong.common.recommend.entity.RecommendTemplate;
import com.jingdong.common.recommend.entity.RecommendVideo;
import com.jingdong.common.recommend.forlist.RecommendUtil;
import com.jingdong.common.utils.JDImageUtils;
import com.jingdong.common.utils.StringUtil;

/* loaded from: classes6.dex */
public class RecommendTemplateEighteenViewHolder extends BaseRecommendMaterialViewHolder {
    private SimpleDraweeView background;
    private SimpleDraweeView firstTitleIcon;
    private TextView firstTitleView;
    private int from;
    private String localImg;
    private View.OnClickListener onClickListener;
    private View playView;
    private SimpleDraweeView sdView;
    private TextView subTitleView;
    private TextView titleView;

    public RecommendTemplateEighteenViewHolder(BaseActivity baseActivity, View view) {
        super(baseActivity, view);
        this.onClickListener = new View.OnClickListener() { // from class: com.jingdong.common.recommend.forlist.RecommendTemplateEighteenViewHolder.3
            {
                RecommendTemplateEighteenViewHolder.this = this;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                RecommendUtil.OnRecommendClickedListener onRecommendClickedListener;
                RecommendTemplate recommendTemplate = (RecommendTemplate) view2.getTag();
                if (recommendTemplate == null || (onRecommendClickedListener = RecommendTemplateEighteenViewHolder.this.clickedListener) == null) {
                    return;
                }
                onRecommendClickedListener.onRecommendJump(recommendTemplate.jump, recommendTemplate.isOpenApp);
                if (recommendTemplate.isFromCache) {
                    return;
                }
                RecommendMtaUtils.aggregationClickMtaRealize(RecommendTemplateEighteenViewHolder.this.itemView.getContext(), recommendTemplate.sourceValue, RecommendTemplateEighteenViewHolder.this.from, recommendTemplate.extension_id);
                RecommendTemplateEighteenViewHolder.this.onAdClick(recommendTemplate.client_click_url);
            }
        };
        SimpleDraweeView simpleDraweeView = (SimpleDraweeView) view.findViewById(R.id.recom_eighteen_img);
        this.sdView = simpleDraweeView;
        simpleDraweeView.setAspectRatio(0.75f);
        this.firstTitleIcon = (SimpleDraweeView) view.findViewById(R.id.recom_eighteen_first_title_icon);
        this.firstTitleView = (TextView) view.findViewById(R.id.recom_eighteen_first_title);
        this.titleView = (TextView) view.findViewById(R.id.recom_eighteen_title);
        this.subTitleView = (TextView) view.findViewById(R.id.recom_eighteen_sub);
        SimpleDraweeView simpleDraweeView2 = (SimpleDraweeView) view.findViewById(R.id.recom_eight_background);
        this.background = simpleDraweeView2;
        simpleDraweeView2.setAspectRatio(2.42f);
        this.playView = view.findViewById(R.id.recom_eighteen_play);
        inflateRecommendVideo();
    }

    public void setData(RecommendTemplate recommendTemplate, JDDisplayImageOptions jDDisplayImageOptions, ExpoDataStore expoDataStore, int i2, int i3) {
        if (recommendTemplate != null) {
            this.from = i2;
            setFrom(i2);
            if (!StringUtil.isEmpty(recommendTemplate.firstTitle)) {
                this.firstTitleView.setText(recommendTemplate.firstTitle);
                this.firstTitleView.setTextColor(RecommendViewUtil.generateColor(recommendTemplate.firstTitleFont, "#FFFA2C19"));
                RecommendViewUtil.setRightTextSize(this.firstTitleView, 13, "", RecommendFontUtils.getRecommendUIMode(this.mRecommendConfig));
            } else {
                this.firstTitleView.setText("");
            }
            if (!StringUtil.isEmpty(recommendTemplate.mainTitle)) {
                this.titleView.setText(recommendTemplate.mainTitle);
                this.titleView.setTextColor(RecommendViewUtil.generateColor(recommendTemplate.mainTitleFont, "#FF1A1A1A"));
                RecommendViewUtil.setRightTextSize(this.titleView, 14, "", RecommendFontUtils.getRecommendUIMode(this.mRecommendConfig));
            } else {
                this.titleView.setText("");
            }
            if (recommendTemplate.lastTitle.isEmpty()) {
                this.subTitleView.setText("");
            } else {
                this.subTitleView.setText(recommendTemplate.lastTitle.get(0));
                this.subTitleView.setTextColor(RecommendViewUtil.generateColor(recommendTemplate.subTitleFont, "#FFFA2C19"));
                RecommendViewUtil.setRightTextSize(this.subTitleView, 12, "", RecommendFontUtils.getRecommendUIMode(this.mRecommendConfig));
            }
            if (!StringUtil.isEmpty(recommendTemplate.firstTitleIcon)) {
                JDImageUtils.displayImage(recommendTemplate.firstTitleIcon, this.firstTitleIcon, jDDisplayImageOptions, new JDImageLoadingListener() { // from class: com.jingdong.common.recommend.forlist.RecommendTemplateEighteenViewHolder.1
                    {
                        RecommendTemplateEighteenViewHolder.this = this;
                    }

                    @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                    public void onLoadingCancelled(String str, View view) {
                    }

                    @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                    public void onLoadingComplete(String str, View view, Bitmap bitmap) {
                    }

                    @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                    public void onLoadingFailed(String str, View view, JDFailReason jDFailReason) {
                        RecommendTemplateEighteenViewHolder.this.firstTitleIcon.setImageResource(R.drawable.recommend_eighteen_title_icon);
                    }

                    @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                    public void onLoadingStarted(String str, View view) {
                    }
                });
            } else {
                this.firstTitleIcon.setImageResource(R.drawable.recommend_eighteen_title_icon);
            }
            if (!StringUtil.isEmpty(recommendTemplate.background)) {
                JDImageUtils.displayImage(recommendTemplate.background, this.background, jDDisplayImageOptions, new JDImageLoadingListener() { // from class: com.jingdong.common.recommend.forlist.RecommendTemplateEighteenViewHolder.2
                    {
                        RecommendTemplateEighteenViewHolder.this = this;
                    }

                    @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                    public void onLoadingCancelled(String str, View view) {
                    }

                    @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                    public void onLoadingComplete(String str, View view, Bitmap bitmap) {
                    }

                    @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                    public void onLoadingFailed(String str, View view, JDFailReason jDFailReason) {
                        RecommendTemplateEighteenViewHolder.this.background.setImageResource(R.drawable.recommend_eighteen_background);
                    }

                    @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                    public void onLoadingStarted(String str, View view) {
                    }
                });
            } else {
                this.background.setImageResource(R.drawable.recommend_eighteen_background);
            }
            if (TextUtils.isEmpty(this.localImg) || !this.localImg.equals(recommendTemplate.img) || this.sdView.getTopLevelDrawable() == null) {
                JDImageUtils.displayImage(recommendTemplate.img, this.sdView, jDDisplayImageOptions);
                this.localImg = recommendTemplate.img;
            }
            RecommendViewUtil.visible(this.playView);
            RecommendVideo recommendVideo = null;
            if (!TextUtils.isEmpty(recommendTemplate.videoId)) {
                recommendVideo = recommendTemplate.getVideoData();
                recommendVideo.isMeasureParent = true;
            }
            setVideoData(recommendVideo, recommendTemplate.img);
            this.itemView.setTag(recommendTemplate);
            this.itemView.setOnClickListener(this.onClickListener);
            if (expoDataStore != null && !recommendTemplate.isFromCache) {
                if (!TextUtils.isEmpty(recommendTemplate.exposureJsonSourceValue)) {
                    expoDataStore.putExpoJsonDada(recommendTemplate.exposureJsonSourceValue);
                } else if (!TextUtils.isEmpty(recommendTemplate.exposureSourceValue)) {
                    expoDataStore.putExpoData(recommendTemplate.exposureSourceValue);
                }
            }
            setAdData(recommendTemplate);
            setMaterialData(recommendTemplate, i3);
        }
    }

    public void showVideoPlayView(boolean z) {
        try {
            if (!z) {
                this.playView.setVisibility(8);
                RecommendViewUtil.visible(this.recommendVideoWidget);
            } else {
                this.playView.setVisibility(0);
                RecommendViewUtil.gone(this.recommendVideoWidget);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}

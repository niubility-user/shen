package com.jingdong.common.recommend.forlist;

import android.graphics.Bitmap;
import android.os.Build;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.airbnb.lottie.LottieAnimationView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jingdong.app.mall.bundle.mobileConfig.JDMobileConfig;
import com.jingdong.app.util.image.JDDisplayImageOptions;
import com.jingdong.app.util.image.assist.JDFailReason;
import com.jingdong.app.util.image.listener.JDImageLoadingListener;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.recommend.ExpoDataStore;
import com.jingdong.common.recommend.R;
import com.jingdong.common.recommend.RecommendMtaUtils;
import com.jingdong.common.recommend.RecommendViewUtil;
import com.jingdong.common.recommend.entity.RecommendVideo;
import com.jingdong.common.recommend.entity.SubSkuData;
import com.jingdong.common.recommend.forlist.RecommendUtil;
import com.jingdong.common.utils.ABTestUtils;
import com.jingdong.common.utils.JDImageUtils;
import com.jingdong.sdk.utils.DPIUtil;
import java.util.ArrayList;

/* loaded from: classes6.dex */
public class RecommendTemplateFifteenViewHolder extends BaseRecommendMaterialViewHolder {
    private SimpleDraweeView authorImg;
    private TextView authorName;
    private SimpleDraweeView background;
    private int from;
    private boolean isLottiePlay;
    private LinearLayout liveInfoLayout;
    private TextView livePeopleNum;
    private LinearLayout liveStateContainer;
    private ImageView liveStateView;
    private ImageView liveTrailerView;
    private View.OnClickListener onClickListener;
    private SimpleDraweeView productImg;
    private LinearLayout thumbUpLayout;
    private SimpleDraweeView thumbsUpIcon;
    private TextView thumbsUpNum;
    private TextView title;

    public RecommendTemplateFifteenViewHolder(BaseActivity baseActivity, View view) {
        super(baseActivity, view);
        this.isLottiePlay = false;
        this.onClickListener = new View.OnClickListener() { // from class: com.jingdong.common.recommend.forlist.RecommendTemplateFifteenViewHolder.3
            {
                RecommendTemplateFifteenViewHolder.this = this;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                RecommendUtil.OnRecommendClickedListener onRecommendClickedListener;
                RecommendVideo recommendVideo = (RecommendVideo) view2.getTag();
                if (recommendVideo == null || (onRecommendClickedListener = RecommendTemplateFifteenViewHolder.this.clickedListener) == null) {
                    return;
                }
                onRecommendClickedListener.onRecommendJump(recommendVideo.jump, recommendVideo.isOpenApp);
                if (recommendVideo.isFromCache) {
                    return;
                }
                RecommendTemplateFifteenViewHolder.this.onAdClick(recommendVideo.client_click_url);
                RecommendMtaUtils.aggregationClickMtaRealize(RecommendTemplateFifteenViewHolder.this.itemView.getContext(), recommendVideo.sourceValue, RecommendTemplateFifteenViewHolder.this.from, recommendVideo.extension_id);
            }
        };
        this.background = (SimpleDraweeView) view.findViewById(R.id.recommend_template_fifteen_background);
        this.liveInfoLayout = (LinearLayout) view.findViewById(R.id.recommend_fifteen_live_info_layout);
        this.liveStateContainer = (LinearLayout) view.findViewById(R.id.recommend_live_state_container);
        this.livePeopleNum = (TextView) view.findViewById(R.id.recommend_live_people_num);
        this.liveTrailerView = (ImageView) view.findViewById(R.id.recommend_live_status_trailer);
        this.productImg = (SimpleDraweeView) view.findViewById(R.id.recommend_template_product_img);
        this.title = (TextView) view.findViewById(R.id.recommend_template_product_title);
        this.authorImg = (SimpleDraweeView) view.findViewById(R.id.recommend_template_author_image);
        this.authorName = (TextView) view.findViewById(R.id.recommend_template_author_name);
        this.thumbUpLayout = (LinearLayout) view.findViewById(R.id.recommend_template_thumb_up_layout);
        this.thumbsUpIcon = (SimpleDraweeView) view.findViewById(R.id.recommend_template_thumbs_up_icon);
        this.thumbsUpNum = (TextView) view.findViewById(R.id.recommend_template_thumbs_up_number);
        initLiveStateInfo();
    }

    private void initLiveStateInfo() {
        boolean equals = "1".equals(JDMobileConfig.getInstance().getConfig("jdRecommend", "liveLottie", "enable", "1"));
        if (Build.VERSION.SDK_INT >= 16 && ABTestUtils.isLottieEnable() && equals) {
            LottieAnimationView lottieAnimationView = new LottieAnimationView(this.itemView.getContext());
            this.liveStateView = lottieAnimationView;
            if (lottieAnimationView != null) {
                lottieAnimationView.setAnimation("live.json");
                ((LottieAnimationView) this.liveStateView).setSpeed(1.5f);
                ((LottieAnimationView) this.liveStateView).setRepeatCount(-1);
                this.liveStateView.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() { // from class: com.jingdong.common.recommend.forlist.RecommendTemplateFifteenViewHolder.1
                    {
                        RecommendTemplateFifteenViewHolder.this = this;
                    }

                    @Override // android.view.View.OnAttachStateChangeListener
                    public void onViewAttachedToWindow(View view) {
                        if (!RecommendTemplateFifteenViewHolder.this.isLottiePlay || ((LottieAnimationView) RecommendTemplateFifteenViewHolder.this.liveStateView).isAnimating()) {
                            return;
                        }
                        ((LottieAnimationView) RecommendTemplateFifteenViewHolder.this.liveStateView).playAnimation();
                    }

                    @Override // android.view.View.OnAttachStateChangeListener
                    public void onViewDetachedFromWindow(View view) {
                    }
                });
            }
        } else {
            ImageView imageView = new ImageView(this.itemView.getContext());
            this.liveStateView = imageView;
            RecommendViewUtil.setImageResource(imageView, R.drawable.live_state);
        }
        RecommendViewUtil.addView(this.liveStateContainer, this.liveStateView, 0, new LinearLayout.LayoutParams(DPIUtil.dip2px(10.0f), DPIUtil.dip2px(9.0f)));
    }

    private void setLiveStateInfo(RecommendVideo recommendVideo) {
        int i2 = recommendVideo.liveStatus;
        if (i2 == 0) {
            stopLiveAnimation();
            RecommendViewUtil.gone(this.liveInfoLayout);
            RecommendViewUtil.visible(this.liveTrailerView);
            RecommendViewUtil.setImageResource(this.liveTrailerView, R.drawable.recommend_status_trailer);
        } else if (i2 == 1) {
            RecommendViewUtil.visible(this.liveInfoLayout, this.liveStateContainer);
            this.isLottiePlay = true;
            this.livePeopleNum.setCompoundDrawables(null, null, null, null);
        } else if (i2 == 3) {
            stopLiveAnimation();
            RecommendViewUtil.visible(this.liveInfoLayout);
            RecommendViewUtil.gone(this.liveStateContainer);
            this.livePeopleNum.setCompoundDrawablesWithIntrinsicBounds(R.drawable.recommend_status_replay, 0, 0, 0);
        } else {
            stopLiveAnimation();
            RecommendViewUtil.gone(this.liveInfoLayout);
        }
    }

    private void stopLiveAnimation() {
        this.isLottiePlay = false;
        ImageView imageView = this.liveStateView;
        if (imageView instanceof LottieAnimationView) {
            ((LottieAnimationView) imageView).cancelAnimation();
        }
    }

    public void setData(RecommendVideo recommendVideo, JDDisplayImageOptions jDDisplayImageOptions, ExpoDataStore expoDataStore, int i2, int i3) {
        if (recommendVideo != null) {
            this.from = i2;
            setFrom(i2);
            RecommendViewUtil.gone(this.liveTrailerView);
            RecommendViewUtil.gone(this.thumbsUpIcon);
            RecommendViewUtil.gone(this.thumbsUpNum);
            JDImageUtils.displayImage(recommendVideo.authorPic, this.authorImg, jDDisplayImageOptions);
            setLiveStateInfo(recommendVideo);
            this.livePeopleNum.setText(recommendVideo.pageView);
            ArrayList<SubSkuData> arrayList = recommendVideo.subsku;
            if (arrayList != null && arrayList.size() > 0) {
                SubSkuData subSkuData = recommendVideo.subsku.get(0);
                JDImageUtils.displayImage(subSkuData.skuImg, (ImageView) this.productImg, jDDisplayImageOptions, true);
                this.title.setText(subSkuData.hit);
            } else {
                this.title.setText("");
                JDImageUtils.displayImage("", (ImageView) this.productImg, jDDisplayImageOptions, true);
            }
            this.authorName.setText(recommendVideo.authorName);
            if (!TextUtils.isEmpty(recommendVideo.thumbsUpIcon)) {
                this.thumbsUpIcon.setVisibility(0);
                JDImageUtils.displayImage(recommendVideo.thumbsUpIcon, this.thumbsUpIcon, jDDisplayImageOptions, new JDImageLoadingListener() { // from class: com.jingdong.common.recommend.forlist.RecommendTemplateFifteenViewHolder.2
                    {
                        RecommendTemplateFifteenViewHolder.this = this;
                    }

                    @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                    public void onLoadingCancelled(String str, View view) {
                    }

                    @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                    public void onLoadingComplete(String str, View view, Bitmap bitmap) {
                    }

                    @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                    public void onLoadingFailed(String str, View view, JDFailReason jDFailReason) {
                        RecommendTemplateFifteenViewHolder.this.thumbsUpIcon.setImageResource(R.drawable.recommend_template_thumbs_up_icon);
                    }

                    @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                    public void onLoadingStarted(String str, View view) {
                    }
                });
                if (!TextUtils.isEmpty(recommendVideo.thumbsUpNumber)) {
                    this.thumbsUpNum.setVisibility(0);
                    this.thumbsUpNum.setText(recommendVideo.thumbsUpNumber);
                }
            }
            this.itemView.setTag(recommendVideo);
            this.itemView.setOnClickListener(this.onClickListener);
            if (expoDataStore != null && !recommendVideo.isFromCache) {
                if (!TextUtils.isEmpty(recommendVideo.exposureJsonSourceValue)) {
                    expoDataStore.putExpoJsonDada(recommendVideo.exposureJsonSourceValue);
                } else if (!TextUtils.isEmpty(recommendVideo.exposureSourceValue)) {
                    expoDataStore.putExpoData(recommendVideo.exposureSourceValue);
                }
            }
            setAdData(recommendVideo);
            setMaterialData(recommendVideo, i3);
        }
    }
}

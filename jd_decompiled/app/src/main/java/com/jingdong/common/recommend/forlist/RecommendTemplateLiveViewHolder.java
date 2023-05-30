package com.jingdong.common.recommend.forlist;

import android.graphics.Bitmap;
import android.os.Build;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
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
import com.jingdong.common.recommend.RecommendFontUtils;
import com.jingdong.common.recommend.RecommendMtaUtils;
import com.jingdong.common.recommend.RecommendViewUtil;
import com.jingdong.common.recommend.entity.RecommendVideo;
import com.jingdong.common.recommend.entity.SubSkuData;
import com.jingdong.common.recommend.forlist.RecommendUtil;
import com.jingdong.common.utils.ABTestUtils;
import com.jingdong.common.utils.JDImageUtils;
import com.jingdong.common.utils.StringUtil;
import com.jingdong.sdk.utils.DPIUtil;
import java.util.ArrayList;

/* loaded from: classes6.dex */
public class RecommendTemplateLiveViewHolder extends BaseRecommendMaterialViewHolder {
    private int from;
    private boolean isLottiePlay;
    private SimpleDraweeView leftTopIconView;
    private SimpleDraweeView liveAuthorImage;
    private LinearLayout liveAuthorInfoLayout;
    private TextView liveAuthorNameTv;
    private SimpleDraweeView liveBgImage;
    private TextView livePeopleTv;
    private ViewGroup liveStateContainer;
    private ImageView liveStateView;
    private TextView liveTitleTv;
    private SimpleDraweeView liveTopIcon1;
    private ImageView liveTrailerView;
    private View liveViewContainer;
    private TextView nameTV;
    private View.OnClickListener onClickListener;
    private View productView;
    private TextView profitTV;
    private SimpleDraweeView skuImg;

    public RecommendTemplateLiveViewHolder(BaseActivity baseActivity, View view) {
        super(baseActivity, view);
        this.isLottiePlay = false;
        this.onClickListener = new View.OnClickListener() { // from class: com.jingdong.common.recommend.forlist.RecommendTemplateLiveViewHolder.3
            {
                RecommendTemplateLiveViewHolder.this = this;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                RecommendUtil.OnRecommendClickedListener onRecommendClickedListener;
                RecommendVideo recommendVideo = (RecommendVideo) view2.getTag();
                if (recommendVideo == null || (onRecommendClickedListener = RecommendTemplateLiveViewHolder.this.clickedListener) == null) {
                    return;
                }
                onRecommendClickedListener.onRecommendJump(recommendVideo.jump, recommendVideo.isOpenApp);
                if (recommendVideo.isFromCache) {
                    return;
                }
                RecommendTemplateLiveViewHolder.this.onAdClick(recommendVideo.client_click_url);
                RecommendMtaUtils.aggregationClickMtaRealize(RecommendTemplateLiveViewHolder.this.itemView.getContext(), recommendVideo.sourceValue, RecommendTemplateLiveViewHolder.this.from, recommendVideo.extension_id);
            }
        };
        this.liveViewContainer = view.findViewById(R.id.recommend_live_container);
        this.liveStateContainer = (ViewGroup) view.findViewById(R.id.recommend_live_state_container);
        this.liveBgImage = (SimpleDraweeView) view.findViewById(R.id.recommend_live_Image);
        this.liveAuthorNameTv = (TextView) view.findViewById(R.id.recommend_live_author_name);
        this.livePeopleTv = (TextView) view.findViewById(R.id.recommend_live_people_text);
        this.liveAuthorImage = (SimpleDraweeView) view.findViewById(R.id.recommend_live_author_image);
        this.liveTrailerView = (ImageView) view.findViewById(R.id.recommend_live_status_trailer);
        this.liveAuthorInfoLayout = (LinearLayout) view.findViewById(R.id.recommend_live_author_info);
        this.liveTitleTv = (TextView) view.findViewById(R.id.recommend_live_title);
        this.liveTopIcon1 = (SimpleDraweeView) view.findViewById(R.id.recommend_live_top_icon1);
        this.leftTopIconView = (SimpleDraweeView) view.findViewById(R.id.recommend_live_lefttop_icon);
        boolean equals = "1".equals(JDMobileConfig.getInstance().getConfig("jdRecommend", "liveLottie", "enable", "1"));
        if (Build.VERSION.SDK_INT >= 16 && ABTestUtils.isLottieEnable() && equals) {
            LottieAnimationView lottieAnimationView = new LottieAnimationView(view.getContext());
            this.liveStateView = lottieAnimationView;
            if (lottieAnimationView != null) {
                lottieAnimationView.setAnimation("live.json");
                ((LottieAnimationView) this.liveStateView).setSpeed(1.5f);
                ((LottieAnimationView) this.liveStateView).setRepeatCount(-1);
                this.liveStateView.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() { // from class: com.jingdong.common.recommend.forlist.RecommendTemplateLiveViewHolder.1
                    {
                        RecommendTemplateLiveViewHolder.this = this;
                    }

                    @Override // android.view.View.OnAttachStateChangeListener
                    public void onViewAttachedToWindow(View view2) {
                        if (!RecommendTemplateLiveViewHolder.this.isLottiePlay || ((LottieAnimationView) RecommendTemplateLiveViewHolder.this.liveStateView).isAnimating()) {
                            return;
                        }
                        ((LottieAnimationView) RecommendTemplateLiveViewHolder.this.liveStateView).playAnimation();
                    }

                    @Override // android.view.View.OnAttachStateChangeListener
                    public void onViewDetachedFromWindow(View view2) {
                    }
                });
            }
        } else {
            ImageView imageView = new ImageView(view.getContext());
            this.liveStateView = imageView;
            RecommendViewUtil.setImageResource(imageView, R.drawable.live_state);
        }
        RecommendViewUtil.addView(this.liveStateContainer, this.liveStateView, 0, new LinearLayout.LayoutParams(DPIUtil.dip2px(10.0f), DPIUtil.dip2px(9.0f)));
    }

    private boolean isShowProductInfo(SubSkuData subSkuData) {
        return (StringUtil.isEmpty(subSkuData.sku) || StringUtil.isEmpty(subSkuData.hit) || StringUtil.isEmpty(subSkuData.skuImg)) ? false : true;
    }

    private void showProduct(SubSkuData subSkuData, RecommendVideo recommendVideo, JDDisplayImageOptions jDDisplayImageOptions) {
        if (this.productView == null) {
            View inflate = ((ViewStub) this.itemView.findViewById(R.id.recommend_template_live_product)).inflate();
            this.productView = inflate;
            this.skuImg = (SimpleDraweeView) inflate.findViewById(R.id.recommend_template_live_product_img);
            this.nameTV = (TextView) this.productView.findViewById(R.id.recommend_template_live_product_hit);
            this.profitTV = (TextView) this.productView.findViewById(R.id.recommend_template_live_product_profit);
        }
        this.productView.setTag(recommendVideo);
        this.productView.setOnClickListener(this.onClickListener);
        RecommendViewUtil.visible(this.productView);
        this.nameTV.setLines(StringUtil.isEmpty(subSkuData.profit) ? 2 : 1);
        this.nameTV.setText(subSkuData.hit);
        JDImageUtils.displayImage(subSkuData.skuImg, this.skuImg, jDDisplayImageOptions);
        if (!StringUtil.isEmpty(subSkuData.profit)) {
            RecommendViewUtil.visible(this.profitTV);
            this.profitTV.setText(subSkuData.profit);
            return;
        }
        RecommendViewUtil.gone(this.profitTV);
    }

    private void stopLiveAnimation() {
        this.isLottiePlay = false;
        ImageView imageView = this.liveStateView;
        if (imageView instanceof LottieAnimationView) {
            ((LottieAnimationView) imageView).cancelAnimation();
        }
    }

    public void setData(RecommendVideo recommendVideo, ExpoDataStore expoDataStore, int i2, int i3, JDDisplayImageOptions jDDisplayImageOptions) {
        if (recommendVideo == null) {
            return;
        }
        this.from = i2;
        RecommendViewUtil.visible(this.liveAuthorInfoLayout);
        RecommendViewUtil.gone(this.liveTrailerView);
        RecommendViewUtil.gone(this.productView);
        ArrayList<SubSkuData> arrayList = recommendVideo.subsku;
        if (arrayList != null && arrayList.size() > 0) {
            SubSkuData subSkuData = recommendVideo.subsku.get(0);
            if (isShowProductInfo(subSkuData)) {
                RecommendViewUtil.gone(this.liveAuthorInfoLayout);
                showProduct(subSkuData, recommendVideo, jDDisplayImageOptions);
            }
        }
        JDImageUtils.displayImage(recommendVideo.imgUrlLocal, this.liveBgImage, jDDisplayImageOptions);
        this.livePeopleTv.setText(recommendVideo.pageView);
        if (TextUtils.isEmpty(recommendVideo.authorPic)) {
            this.liveAuthorImage.setImageResource(R.drawable.recommend_live_author_failed);
        } else {
            JDImageUtils.displayImage(recommendVideo.authorPic, this.liveAuthorImage, jDDisplayImageOptions, new JDImageLoadingListener() { // from class: com.jingdong.common.recommend.forlist.RecommendTemplateLiveViewHolder.2
                {
                    RecommendTemplateLiveViewHolder.this = this;
                }

                @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                public void onLoadingCancelled(String str, View view) {
                }

                @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                public void onLoadingComplete(String str, View view, Bitmap bitmap) {
                }

                @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                public void onLoadingFailed(String str, View view, JDFailReason jDFailReason) {
                    RecommendTemplateLiveViewHolder.this.liveAuthorImage.setImageResource(R.drawable.recommend_live_author_failed);
                }

                @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                public void onLoadingStarted(String str, View view) {
                }
            });
        }
        if (TextUtils.isEmpty(recommendVideo.leftTopIcon)) {
            this.leftTopIconView.setVisibility(8);
        } else {
            this.leftTopIconView.setVisibility(0);
            JDImageUtils.displayImage(recommendVideo.leftTopIcon, this.leftTopIconView, jDDisplayImageOptions);
        }
        this.liveTitleTv.setText(recommendVideo.mainTitle);
        this.liveAuthorNameTv.setTextSize(RecommendFontUtils.convertFontSize(12, RecommendFontUtils.getRecommendUIMode(this.mRecommendConfig)));
        this.liveAuthorNameTv.setText(recommendVideo.authorName);
        this.itemView.setTag(recommendVideo);
        this.itemView.setOnClickListener(this.onClickListener);
        int i4 = recommendVideo.liveStatus;
        if (i4 == 0) {
            stopLiveAnimation();
            RecommendViewUtil.gone(this.liveViewContainer);
            RecommendViewUtil.visible(this.liveTrailerView);
            RecommendViewUtil.setImageResource(this.liveTrailerView, R.drawable.recommend_status_trailer);
        } else if (i4 == 1) {
            RecommendViewUtil.visible(this.liveViewContainer, this.liveStateContainer);
            this.isLottiePlay = true;
            this.livePeopleTv.setCompoundDrawables(null, null, null, null);
        } else if (i4 == 3) {
            stopLiveAnimation();
            RecommendViewUtil.visible(this.liveViewContainer);
            RecommendViewUtil.gone(this.liveStateContainer);
            this.livePeopleTv.setCompoundDrawablesWithIntrinsicBounds(R.drawable.recommend_status_replay, 0, 0, 0);
        } else {
            stopLiveAnimation();
            RecommendViewUtil.gone(this.liveViewContainer);
        }
        ArrayList<String> arrayList2 = recommendVideo.benefitIcons;
        if (arrayList2 != null && !arrayList2.isEmpty()) {
            String str = arrayList2.get(0);
            if (!TextUtils.isEmpty(str)) {
                this.liveTopIcon1.setVisibility(0);
                JDImageUtils.displayImage(str, this.liveTopIcon1, jDDisplayImageOptions);
            } else {
                this.liveTopIcon1.setVisibility(8);
            }
        } else {
            this.liveTopIcon1.setVisibility(8);
        }
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

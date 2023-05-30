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
import com.jingdong.app.util.image.assist.JDFailReason;
import com.jingdong.app.util.image.listener.JDImageLoadingListener;
import com.jingdong.common.recommend.ExpoDataStore;
import com.jingdong.common.recommend.R;
import com.jingdong.common.recommend.RecommendConfig;
import com.jingdong.common.recommend.RecommendLiveProduct;
import com.jingdong.common.recommend.RecommendMtaUtils;
import com.jingdong.common.recommend.RecommendSPUtils;
import com.jingdong.common.recommend.RecommendUtils;
import com.jingdong.common.recommend.RecommendViewUtil;
import com.jingdong.common.recommend.entity.RecommendVideo;
import com.jingdong.common.recommend.ui.RecommendLiveProductsView;
import com.jingdong.common.utils.ABTestUtils;
import com.jingdong.common.utils.JDImageUtils;
import com.jingdong.sdk.utils.DPIUtil;
import java.util.ArrayList;

/* loaded from: classes6.dex */
public class RecommendLiveViewHolder extends BaseRecommendAdViewHolder {
    private int from;
    private boolean isLottiePlay;
    private SimpleDraweeView leftTopIconView;
    private SimpleDraweeView liveAuthorImage;
    private TextView liveAuthorNameTv;
    private SimpleDraweeView liveBgImage;
    private TextView livePeopleTv;
    private ViewGroup liveStateContainer;
    private ImageView liveStateView;
    private TextView liveTitleTv;
    private SimpleDraweeView liveTopIcon1;
    private View liveViewContainer;
    private View.OnClickListener onClickListener;
    private RecommendLiveProductsView recomLiveProductsView;
    private SimpleDraweeView thumbsUpIcon;
    private TextView thumbsUpNumber;

    public RecommendLiveViewHolder(View view) {
        super(view);
        this.isLottiePlay = false;
        this.onClickListener = new View.OnClickListener() { // from class: com.jingdong.common.recommend.forlist.RecommendLiveViewHolder.5
            {
                RecommendLiveViewHolder.this = this;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                RecommendVideo recommendVideo = (RecommendVideo) view2.getTag();
                if (recommendVideo != null) {
                    RecommendLiveViewHolder recommendLiveViewHolder = RecommendLiveViewHolder.this;
                    if (recommendLiveViewHolder.clickedListener != null) {
                        RecommendMtaUtils.aggregationClickMtaRealize(recommendLiveViewHolder.itemView.getContext(), recommendVideo.sourceValue, RecommendLiveViewHolder.this.from, recommendVideo.extension_id);
                        RecommendLiveViewHolder.this.clickedListener.onRecommendJump(recommendVideo.channelJumpUrl, recommendVideo.isOpenApp);
                        RecommendLiveViewHolder.this.onAdClick(recommendVideo.client_click_url);
                    }
                }
            }
        };
        this.liveViewContainer = view.findViewById(R.id.recommend_live_container);
        this.liveStateContainer = (ViewGroup) view.findViewById(R.id.recommend_live_state_container);
        this.liveBgImage = (SimpleDraweeView) view.findViewById(R.id.recommend_live_Image);
        this.liveAuthorNameTv = (TextView) view.findViewById(R.id.recommend_live_author_name);
        this.livePeopleTv = (TextView) view.findViewById(R.id.recommend_live_people_text);
        this.liveAuthorImage = (SimpleDraweeView) view.findViewById(R.id.recommend_live_author_image);
        this.liveTitleTv = (TextView) view.findViewById(R.id.recommend_live_title);
        this.liveTopIcon1 = (SimpleDraweeView) view.findViewById(R.id.recommend_live_top_icon1);
        this.leftTopIconView = (SimpleDraweeView) view.findViewById(R.id.recommend_live_lefttop_icon);
        this.thumbsUpIcon = (SimpleDraweeView) view.findViewById(R.id.recommend_thumbs_up_icon);
        this.thumbsUpNumber = (TextView) view.findViewById(R.id.recommend_thumbs_up_number);
        boolean equals = "1".equals(JDMobileConfig.getInstance().getConfig("jdRecommend", "liveLottie", "enable", "1"));
        if (Build.VERSION.SDK_INT >= 16 && ABTestUtils.isLottieEnable() && equals) {
            LottieAnimationView lottieAnimationView = new LottieAnimationView(view.getContext());
            this.liveStateView = lottieAnimationView;
            if (lottieAnimationView != null) {
                lottieAnimationView.setAnimation("live.json");
                ((LottieAnimationView) this.liveStateView).setSpeed(1.5f);
                ((LottieAnimationView) this.liveStateView).setRepeatCount(-1);
                this.liveStateView.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() { // from class: com.jingdong.common.recommend.forlist.RecommendLiveViewHolder.1
                    {
                        RecommendLiveViewHolder.this = this;
                    }

                    @Override // android.view.View.OnAttachStateChangeListener
                    public void onViewAttachedToWindow(View view2) {
                        if (!RecommendLiveViewHolder.this.isLottiePlay || ((LottieAnimationView) RecommendLiveViewHolder.this.liveStateView).isAnimating()) {
                            return;
                        }
                        ((LottieAnimationView) RecommendLiveViewHolder.this.liveStateView).playAnimation();
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

    private void resetInit() {
        RecommendViewUtil.gone(this.thumbsUpIcon);
        RecommendViewUtil.gone(this.thumbsUpNumber);
        RecommendViewUtil.gone(this.recomLiveProductsView);
    }

    public void showProductsView(RecommendVideo recommendVideo) {
        if (this.recomLiveProductsView == null) {
            RecommendLiveProductsView recommendLiveProductsView = (RecommendLiveProductsView) ((ViewStub) this.itemView.findViewById(R.id.recom_live_product)).inflate();
            this.recomLiveProductsView = recommendLiveProductsView;
            recommendLiveProductsView.setOnRecommendClickListener(this.clickedListener);
        }
        RecommendLiveProductsView recommendLiveProductsView2 = this.recomLiveProductsView;
        if (recommendLiveProductsView2 != null) {
            RecommendViewUtil.visible(recommendLiveProductsView2);
            this.recomLiveProductsView.setRecommendLiveProductData(recommendVideo, this.from);
        }
    }

    private void stopLiveAnimation() {
        this.isLottiePlay = false;
        ImageView imageView = this.liveStateView;
        if (imageView instanceof LottieAnimationView) {
            ((LottieAnimationView) imageView).cancelAnimation();
        }
    }

    public void setData(final RecommendVideo recommendVideo, int i2, ExpoDataStore expoDataStore, int i3) {
        ArrayList<RecommendLiveProduct> arrayList;
        if (recommendVideo == null) {
            return;
        }
        resetInit();
        this.from = i3;
        JDImageUtils.displayImage(recommendVideo.imageurl, this.liveBgImage);
        this.livePeopleTv.setText(recommendVideo.pageView);
        if (TextUtils.isEmpty(recommendVideo.authorPic)) {
            this.liveAuthorImage.setImageResource(R.drawable.recommend_live_author_failed);
        } else {
            JDImageUtils.displayImage(recommendVideo.authorPic, this.liveAuthorImage, new JDImageLoadingListener() { // from class: com.jingdong.common.recommend.forlist.RecommendLiveViewHolder.2
                {
                    RecommendLiveViewHolder.this = this;
                }

                @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                public void onLoadingCancelled(String str, View view) {
                }

                @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                public void onLoadingComplete(String str, View view, Bitmap bitmap) {
                }

                @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                public void onLoadingFailed(String str, View view, JDFailReason jDFailReason) {
                    RecommendLiveViewHolder.this.liveAuthorImage.setImageResource(R.drawable.recommend_live_author_failed);
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
            JDImageUtils.displayImage(recommendVideo.leftTopIcon, this.leftTopIconView);
        }
        this.liveTitleTv.setText(recommendVideo.wname);
        this.liveAuthorNameTv.setText(recommendVideo.authorName);
        this.itemView.setTag(recommendVideo);
        this.itemView.setOnClickListener(this.onClickListener);
        int i4 = recommendVideo.liveStatus;
        if (i4 == 1) {
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
                JDImageUtils.displayImage(str, this.liveTopIcon1);
            } else {
                this.liveTopIcon1.setVisibility(8);
            }
        } else {
            this.liveTopIcon1.setVisibility(8);
        }
        if (!TextUtils.isEmpty(recommendVideo.thumbsUpIcon)) {
            this.thumbsUpIcon.setVisibility(0);
            JDImageUtils.displayImage(recommendVideo.thumbsUpIcon, this.thumbsUpIcon, new JDImageLoadingListener() { // from class: com.jingdong.common.recommend.forlist.RecommendLiveViewHolder.3
                {
                    RecommendLiveViewHolder.this = this;
                }

                @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                public void onLoadingCancelled(String str2, View view) {
                }

                @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                public void onLoadingComplete(String str2, View view, Bitmap bitmap) {
                }

                @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                public void onLoadingFailed(String str2, View view, JDFailReason jDFailReason) {
                    RecommendLiveViewHolder.this.thumbsUpIcon.setImageResource(R.drawable.recommend_live_thumbs_up_icon);
                }

                @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                public void onLoadingStarted(String str2, View view) {
                }
            });
            if (!TextUtils.isEmpty(recommendVideo.thumbsUpNumber)) {
                this.thumbsUpNumber.setVisibility(0);
                this.thumbsUpNumber.setText(recommendVideo.thumbsUpNumber);
            }
        }
        RecommendConfig recommendConfig = this.mRecommendConfig;
        if (recommendConfig != null && recommendConfig.isEnableLiveProductCover() && (arrayList = recommendVideo.liveSkus) != null && arrayList.size() > 0) {
            if (i2 == 0 && RecommendUtils.RECOMMEND_LIVE_GUIDE_SHOW) {
                RecommendSPUtils.putBoolean(RecommendSPUtils.SP_RECOMMEND_LIVE_GUIDE, true);
                RecommendUtils.RECOMMEND_LIVE_GUIDE_SHOW = false;
                recommendVideo.playGuidAni = true;
                showProductsView(recommendVideo);
            }
            this.itemView.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.jingdong.common.recommend.forlist.RecommendLiveViewHolder.4
                {
                    RecommendLiveViewHolder.this = this;
                }

                @Override // android.view.View.OnLongClickListener
                public boolean onLongClick(View view) {
                    ArrayList<RecommendLiveProduct> arrayList3;
                    if ((RecommendLiveViewHolder.this.recomLiveProductsView == null || RecommendLiveViewHolder.this.recomLiveProductsView.getVisibility() == 8) && (arrayList3 = recommendVideo.liveSkus) != null && arrayList3.size() > 0) {
                        RecommendLiveViewHolder.this.showProductsView(recommendVideo);
                        return true;
                    }
                    return true;
                }
            });
        } else {
            this.itemView.setOnLongClickListener(null);
        }
        if (expoDataStore != null) {
            if (!TextUtils.isEmpty(recommendVideo.exposureJsonSourceValue)) {
                expoDataStore.putExpoJsonDada(recommendVideo.exposureJsonSourceValue);
            } else if (!TextUtils.isEmpty(recommendVideo.exposureSourceValue)) {
                expoDataStore.putExpoData(recommendVideo.exposureSourceValue);
            }
        }
        setAdData(recommendVideo);
    }
}

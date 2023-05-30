package com.jingdong.common.recommend.forlist;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.LinearLayout;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.airbnb.lottie.LottieAnimationView;
import com.jingdong.app.util.image.JDDisplayImageOptions;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.R;
import com.jingdong.common.jump.JumpUtil;
import com.jingdong.common.recommend.RecommendConstant;
import com.jingdong.common.recommend.RecommendMtaUtils;
import com.jingdong.common.recommend.RecommendNetUtil;
import com.jingdong.common.recommend.entity.RecommendHomeCardBean;
import com.jingdong.common.recommend.entity.RecommendVideo;
import com.jingdong.common.recommend.ui.homerecommend.HomeCardMainTitleLayout;
import com.jingdong.common.recommend.ui.homerecommend.HomeCardSubTitleLayout;
import com.jingdong.common.recommend.ui.video.RecommendVideoRoomInfoWidget;
import com.jingdong.sdk.platform.business.personal.R2;
import com.jingdong.sdk.utils.DPIUtil;
import java.util.List;

/* loaded from: classes6.dex */
public class RecommendHomeLiveVideoViewHolder extends BaseRecommendViewHolder {
    private BaseActivity baseActivity;
    private int bigCardH;
    private LinearLayout header;
    private RecommendVideoRoomInfoWidget roomInfoWidget;
    private int smallCardH;
    private HomeCardSubTitleLayout subTitle;
    private LottieAnimationView thumbsUpLottieView;
    private HomeCardMainTitleLayout title;
    private RecommendHomeCardBean.SubWareList videoSubWareInfo;

    public RecommendHomeLiveVideoViewHolder(BaseActivity baseActivity, View view) {
        super(view);
        this.baseActivity = baseActivity;
        findViews(view);
        inflateRecommendVideo();
        this.recommendVideoWidget.setBackgroundResource(R.color.transparent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b  reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void c(View view) {
        JumpUtil.execJump(this.itemView.getContext(), this.videoSubWareInfo.jump, 1);
        Context context = this.itemView.getContext();
        RecommendHomeCardBean.SubWareList subWareList = this.videoSubWareInfo;
        RecommendMtaUtils.doHomeLiveVideoCardClick(context, subWareList.jump, subWareList);
    }

    private void findViews(View view) {
        this.header = (LinearLayout) view.findViewById(com.jingdong.common.recommend.R.id.header);
        this.title = (HomeCardMainTitleLayout) view.findViewById(com.jingdong.common.recommend.R.id.title);
        this.subTitle = (HomeCardSubTitleLayout) view.findViewById(com.jingdong.common.recommend.R.id.subtitle);
        this.roomInfoWidget = (RecommendVideoRoomInfoWidget) view.findViewById(com.jingdong.common.recommend.R.id.room_info);
        this.thumbsUpLottieView = (LottieAnimationView) view.findViewById(com.jingdong.common.recommend.R.id.thumbs_up);
    }

    private void setViewLayout(RecommendHomeCardBean.SubWareList subWareList) {
        this.bigCardH = DPIUtil.getWidthByDesignValue750((Activity) this.baseActivity, (int) R2.attr.backgroundInsetBottom);
        this.smallCardH = DPIUtil.getWidthByDesignValue750((Activity) this.baseActivity, 204);
        ViewGroup.LayoutParams layoutParams = this.itemView.getLayoutParams();
        if (RecommendConstant.scaleH) {
            layoutParams.height = this.smallCardH;
        } else {
            layoutParams.height = this.bigCardH;
        }
        this.itemView.setLayoutParams(layoutParams);
        this.recommendVideoWidget.getLayoutParams().height = this.bigCardH;
        ConstraintLayout.LayoutParams layoutParams2 = (ConstraintLayout.LayoutParams) this.header.getLayoutParams();
        ((ViewGroup.MarginLayoutParams) layoutParams2).width = DPIUtil.getWidthByDesignValue750((Activity) this.baseActivity, 316);
        ((ViewGroup.MarginLayoutParams) layoutParams2).height = DPIUtil.getWidthByDesignValue750((Activity) this.baseActivity, 52);
        ((ViewGroup.MarginLayoutParams) layoutParams2).leftMargin = DPIUtil.getWidthByDesignValue750((Activity) this.baseActivity, 16);
        ((ViewGroup.MarginLayoutParams) layoutParams2).topMargin = DPIUtil.getWidthByDesignValue750((Activity) this.baseActivity, 0);
        this.header.setLayoutParams(layoutParams2);
        LinearLayout.LayoutParams layoutParams3 = (LinearLayout.LayoutParams) this.title.getLayoutParams();
        layoutParams3.width = -2;
        this.title.setLayoutParams(layoutParams3);
        LinearLayout.LayoutParams layoutParams4 = (LinearLayout.LayoutParams) this.subTitle.getLayoutParams();
        layoutParams4.height = DPIUtil.getWidthByDesignValue750((Activity) this.baseActivity, 28);
        layoutParams4.leftMargin = DPIUtil.getWidthByDesignValue750((Activity) this.baseActivity, 8);
        this.subTitle.setLayoutParams(layoutParams4);
        ConstraintLayout.LayoutParams layoutParams5 = (ConstraintLayout.LayoutParams) this.roomInfoWidget.getLayoutParams();
        ((ViewGroup.MarginLayoutParams) layoutParams5).width = DPIUtil.getWidthByDesignValue750((Activity) this.baseActivity, 260);
        ((ViewGroup.MarginLayoutParams) layoutParams5).height = DPIUtil.getWidthByDesignValue750((Activity) this.baseActivity, 72);
        ((ViewGroup.MarginLayoutParams) layoutParams5).leftMargin = DPIUtil.getWidthByDesignValue750((Activity) this.baseActivity, 12);
        ((ViewGroup.MarginLayoutParams) layoutParams5).bottomMargin = DPIUtil.getWidthByDesignValue750((Activity) this.baseActivity, 12);
        this.roomInfoWidget.setLayoutParams(layoutParams5);
        ConstraintLayout.LayoutParams layoutParams6 = (ConstraintLayout.LayoutParams) this.thumbsUpLottieView.getLayoutParams();
        ((ViewGroup.MarginLayoutParams) layoutParams6).width = DPIUtil.getWidthByDesignValue750((Activity) this.baseActivity, 56);
        ((ViewGroup.MarginLayoutParams) layoutParams6).height = DPIUtil.getWidthByDesignValue750((Activity) this.baseActivity, (int) R2.anim.out_to_bottom_slow);
        ((ViewGroup.MarginLayoutParams) layoutParams6).rightMargin = DPIUtil.getWidthByDesignValue750((Activity) this.baseActivity, 12);
        ((ViewGroup.MarginLayoutParams) layoutParams6).bottomMargin = DPIUtil.getWidthByDesignValue750((Activity) this.baseActivity, 12);
        this.thumbsUpLottieView.setLayoutParams(layoutParams6);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.common.recommend.forlist.BaseRecommendViewHolder
    public void exposurePercent(double d) {
        super.exposurePercent(d);
        if (d > 0.0d) {
            try {
                StringBuilder sb = new StringBuilder();
                RecommendHomeCardBean.SubWareList subWareList = this.videoSubWareInfo;
                if (subWareList != null && !subWareList.hasExpoView) {
                    RecommendNetUtil.recommendFeedBack(subWareList.expoLog);
                    if (!TextUtils.isEmpty(this.videoSubWareInfo.exposureJsonSourceValue)) {
                        this.videoSubWareInfo.hasExpoView = true;
                        sb.append("[");
                        sb.append(this.videoSubWareInfo.exposureJsonSourceValue);
                        sb.append("]");
                    }
                }
                if (sb.length() > 0) {
                    RecommendMtaUtils.sendHomeChannelCardExpo(this.itemView.getContext(), sb.toString());
                }
            } catch (Exception unused) {
            }
        }
    }

    public void setData(RecommendHomeCardBean recommendHomeCardBean, JDDisplayImageOptions jDDisplayImageOptions) {
        List<RecommendHomeCardBean.SubWareList> list;
        if (recommendHomeCardBean == null || (list = recommendHomeCardBean.subWareList) == null || list.size() == 0) {
            return;
        }
        RecommendHomeCardBean.SubWareList subWareList = recommendHomeCardBean.subWareList.get(0);
        this.videoSubWareInfo = subWareList;
        if (subWareList != null) {
            setViewLayout(subWareList);
            this.title.bind(this.baseActivity, HomeCardMainTitleLayout.Info.builder().setShowNameImg(this.videoSubWareInfo.showNameImg).setShowName(this.videoSubWareInfo.showName).setMaintitleColor(this.videoSubWareInfo.maintitleColor).setShowNameImgWidth(this.videoSubWareInfo.showNameImgWidth));
            HomeCardSubTitleLayout.Info sloganTagImg = HomeCardSubTitleLayout.Info.builder().setSlogan(this.videoSubWareInfo.slogan).setSloganColor(this.videoSubWareInfo.sloganColor).setSloganTagImg(this.videoSubWareInfo.sloganTagImg);
            if (!TextUtils.isEmpty(this.videoSubWareInfo.showNameImgWidth)) {
                try {
                    sloganTagImg.setMaxWidth(308 - Integer.parseInt(this.videoSubWareInfo.showNameImgWidth));
                } catch (Exception unused) {
                }
            }
            this.subTitle.bind(this.baseActivity, sloganTagImg);
            this.roomInfoWidget.bind(this.baseActivity, RecommendVideoRoomInfoWidget.Info.builder().setPageView(this.videoSubWareInfo.pageView).setStreamerIcon(this.videoSubWareInfo.streamerIcon).setStreamerName(this.videoSubWareInfo.streamerName));
            setVideoData(this.videoSubWareInfo.getVideoData(), this.videoSubWareInfo.liveRoomImg);
            if (RecommendConstant.scaleH) {
                this.roomInfoWidget.setVisibility(4);
                this.thumbsUpLottieView.setVisibility(4);
            } else {
                this.roomInfoWidget.setVisibility(0);
                this.thumbsUpLottieView.setVisibility(0);
                startThumbsUpLottieAnimation();
                try {
                    if (!RecommendConstant.scaleH) {
                        startHeightScaleAnimation(this.videoSubWareInfo, this.bigCardH, this.smallCardH, 800L);
                    }
                } catch (Exception unused2) {
                }
            }
            this.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.recommend.forlist.l
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    RecommendHomeLiveVideoViewHolder.this.c(view);
                }
            });
        }
    }

    public void setPlayUiVisible(boolean z) {
        RecommendVideo videoData;
        RecommendHomeCardBean.SubWareList subWareList = this.videoSubWareInfo;
        if (subWareList == null || (videoData = subWareList.getVideoData()) == null) {
            return;
        }
        videoData.canPlay = false;
    }

    public void startHeightScaleAnimation(RecommendHomeCardBean.SubWareList subWareList, final int i2, final int i3, final long j2) {
        if (subWareList == null) {
            return;
        }
        int i4 = 0;
        try {
            i4 = Integer.parseInt(subWareList.showSeconds);
        } catch (Exception unused) {
        }
        this.baseActivity.post(new Runnable() { // from class: com.jingdong.common.recommend.forlist.RecommendHomeLiveVideoViewHolder.1
            @Override // java.lang.Runnable
            public void run() {
                ValueAnimator ofInt = ValueAnimator.ofInt(i2, i3);
                ofInt.setDuration(j2);
                ofInt.setInterpolator(new LinearInterpolator());
                ofInt.addListener(new Animator.AnimatorListener() { // from class: com.jingdong.common.recommend.forlist.RecommendHomeLiveVideoViewHolder.1.1
                    @Override // android.animation.Animator.AnimatorListener
                    public void onAnimationCancel(Animator animator) {
                    }

                    @Override // android.animation.Animator.AnimatorListener
                    public void onAnimationEnd(Animator animator) {
                    }

                    @Override // android.animation.Animator.AnimatorListener
                    public void onAnimationRepeat(Animator animator) {
                    }

                    @Override // android.animation.Animator.AnimatorListener
                    public void onAnimationStart(Animator animator) {
                        try {
                            RecommendHomeLiveVideoViewHolder.this.roomInfoWidget.setVisibility(4);
                            RecommendHomeLiveVideoViewHolder.this.thumbsUpLottieView.setVisibility(4);
                        } catch (Exception unused2) {
                        }
                    }
                });
                ofInt.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.jingdong.common.recommend.forlist.RecommendHomeLiveVideoViewHolder.1.2
                    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
                        try {
                            ViewGroup.LayoutParams layoutParams = RecommendHomeLiveVideoViewHolder.this.itemView.getLayoutParams();
                            layoutParams.height = ((Integer) valueAnimator.getAnimatedValue()).intValue();
                            RecommendHomeLiveVideoViewHolder.this.itemView.setLayoutParams(layoutParams);
                        } catch (Exception unused2) {
                        }
                    }
                });
                ofInt.start();
            }
        }, i4 * 1000);
        RecommendConstant.scaleH = true;
    }

    public void startThumbsUpLottieAnimation() {
        if (this.thumbsUpLottieView.isAnimating()) {
            return;
        }
        this.thumbsUpLottieView.setAnimation("thumbs_up.json");
        this.thumbsUpLottieView.setSpeed(1.0f);
        this.thumbsUpLottieView.setRepeatCount(-1);
        this.thumbsUpLottieView.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() { // from class: com.jingdong.common.recommend.forlist.RecommendHomeLiveVideoViewHolder.2
            @Override // android.view.View.OnAttachStateChangeListener
            public void onViewAttachedToWindow(View view) {
                if (RecommendHomeLiveVideoViewHolder.this.thumbsUpLottieView.isAnimating()) {
                    return;
                }
                RecommendHomeLiveVideoViewHolder.this.thumbsUpLottieView.playAnimation();
            }

            @Override // android.view.View.OnAttachStateChangeListener
            public void onViewDetachedFromWindow(View view) {
            }
        });
    }
}

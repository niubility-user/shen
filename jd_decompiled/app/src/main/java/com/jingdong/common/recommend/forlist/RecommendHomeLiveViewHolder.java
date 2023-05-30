package com.jingdong.common.recommend.forlist;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.airbnb.lottie.LottieAnimationView;
import com.jingdong.app.util.image.JDDisplayImageOptions;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.jump.JumpUtil;
import com.jingdong.common.recommend.R;
import com.jingdong.common.recommend.RecommendImageUtils;
import com.jingdong.common.recommend.RecommendMtaUtils;
import com.jingdong.common.recommend.RecommendNetUtil;
import com.jingdong.common.recommend.entity.RecommendHomeCardBean;
import com.jingdong.common.recommend.ui.homerecommend.HomeCardMainTitleLayout;
import com.jingdong.common.recommend.ui.homerecommend.HomeCardSubTitleLayout;
import com.jingdong.common.recommend.ui.video.RecommendVideoRoomInfoWidget;
import com.jingdong.common.recommend.ui.video.RecommendVideoWidget;
import com.jingdong.common.widget.custom.livewidget.bean.LiveVideoEntity;
import com.jingdong.common.widget.custom.livewidget.playerview.VideoViewLayout;
import com.jingdong.sdk.platform.business.personal.R2;
import com.jingdong.sdk.utils.DPIUtil;
import java.util.List;

/* loaded from: classes6.dex */
public class RecommendHomeLiveViewHolder extends BaseRecommendViewHolder {
    private BaseActivity baseActivity;
    private LinearLayout header;
    private ConstraintLayout layout_container;
    private RecommendHomeCardBean.SubWareList liveSubWareInfo;
    private RecommendVideoRoomInfoWidget roomInfoWidget;
    private HomeCardSubTitleLayout subTitle;
    private LottieAnimationView thumbsUpLottieView;
    private HomeCardMainTitleLayout title;
    private CardView video_layout_container;

    public RecommendHomeLiveViewHolder(BaseActivity baseActivity, View view) {
        super(view);
        this.baseActivity = baseActivity;
        findViews(view);
        inflateRecommendVideo();
    }

    /* renamed from: b */
    public /* synthetic */ void c(View view) {
        VideoViewLayout videoViewLayout;
        RecommendVideoWidget recommendVideoWidget = this.recommendVideoWidget;
        if (recommendVideoWidget != null && (videoViewLayout = recommendVideoWidget.liveVideoLayout) != null) {
            videoViewLayout.jump(this.liveSubWareInfo.jump);
        } else {
            JumpUtil.execJump(this.itemView.getContext(), this.liveSubWareInfo.jump, 1);
        }
        Context context = this.itemView.getContext();
        RecommendHomeCardBean.SubWareList subWareList = this.liveSubWareInfo;
        RecommendMtaUtils.doHomeLiveVideoCardClick(context, subWareList.jump, subWareList);
    }

    private void findViews(View view) {
        this.layout_container = (ConstraintLayout) view.findViewById(R.id.layout_container);
        this.video_layout_container = (CardView) view.findViewById(R.id.video_layout_container);
        this.header = (LinearLayout) view.findViewById(R.id.header);
        this.title = (HomeCardMainTitleLayout) view.findViewById(R.id.title);
        this.subTitle = (HomeCardSubTitleLayout) view.findViewById(R.id.subtitle);
        this.roomInfoWidget = (RecommendVideoRoomInfoWidget) view.findViewById(R.id.room_info);
        this.thumbsUpLottieView = (LottieAnimationView) view.findViewById(R.id.thumbs_up);
    }

    private LiveVideoEntity getLiveLayoutData(RecommendHomeCardBean.SubWareList subWareList) {
        if (subWareList == null) {
            return null;
        }
        LiveVideoEntity liveVideoEntity = new LiveVideoEntity("3", subWareList.jdFlvPull, subWareList.itemid, TextUtils.isEmpty(subWareList.screen) ? "0" : subWareList.screen, subWareList.liveRoomImg, 1);
        liveVideoEntity.mNeedDefaultImg = true;
        return liveVideoEntity;
    }

    private void setViewLayout() {
        ViewGroup.LayoutParams layoutParams = this.itemView.getLayoutParams();
        layoutParams.height = DPIUtil.getWidthByDesignValue750((Activity) this.baseActivity, (int) R2.attr.arrowWidth);
        this.itemView.setLayoutParams(layoutParams);
        ConstraintLayout.LayoutParams layoutParams2 = (ConstraintLayout.LayoutParams) this.video_layout_container.getLayoutParams();
        ((ViewGroup.MarginLayoutParams) layoutParams2).width = -1;
        ((ViewGroup.MarginLayoutParams) layoutParams2).height = DPIUtil.getWidthByDesignValue750((Activity) this.baseActivity, (int) R2.attr.actionModeStyle);
        this.video_layout_container.setLayoutParams(layoutParams2);
        ConstraintLayout.LayoutParams layoutParams3 = (ConstraintLayout.LayoutParams) this.header.getLayoutParams();
        ((ViewGroup.MarginLayoutParams) layoutParams3).width = DPIUtil.getWidthByDesignValue750((Activity) this.baseActivity, 316);
        ((ViewGroup.MarginLayoutParams) layoutParams3).height = DPIUtil.getWidthByDesignValue750((Activity) this.baseActivity, 52);
        ((ViewGroup.MarginLayoutParams) layoutParams3).leftMargin = DPIUtil.getWidthByDesignValue750((Activity) this.baseActivity, 16);
        ((ViewGroup.MarginLayoutParams) layoutParams3).topMargin = DPIUtil.getWidthByDesignValue750((Activity) this.baseActivity, 0);
        this.header.setLayoutParams(layoutParams3);
        LinearLayout.LayoutParams layoutParams4 = (LinearLayout.LayoutParams) this.title.getLayoutParams();
        layoutParams4.width = -2;
        this.title.setLayoutParams(layoutParams4);
        LinearLayout.LayoutParams layoutParams5 = (LinearLayout.LayoutParams) this.subTitle.getLayoutParams();
        layoutParams5.height = DPIUtil.getWidthByDesignValue750((Activity) this.baseActivity, 28);
        layoutParams5.leftMargin = DPIUtil.getWidthByDesignValue750((Activity) this.baseActivity, 8);
        this.subTitle.setLayoutParams(layoutParams5);
        ConstraintLayout.LayoutParams layoutParams6 = (ConstraintLayout.LayoutParams) this.roomInfoWidget.getLayoutParams();
        ((ViewGroup.MarginLayoutParams) layoutParams6).width = DPIUtil.getWidthByDesignValue750((Activity) this.baseActivity, 260);
        ((ViewGroup.MarginLayoutParams) layoutParams6).height = DPIUtil.getWidthByDesignValue750((Activity) this.baseActivity, 72);
        ((ViewGroup.MarginLayoutParams) layoutParams6).leftMargin = DPIUtil.getWidthByDesignValue750((Activity) this.baseActivity, 12);
        ((ViewGroup.MarginLayoutParams) layoutParams6).bottomMargin = DPIUtil.getWidthByDesignValue750((Activity) this.baseActivity, 12);
        this.roomInfoWidget.setLayoutParams(layoutParams6);
        ConstraintLayout.LayoutParams layoutParams7 = (ConstraintLayout.LayoutParams) this.thumbsUpLottieView.getLayoutParams();
        ((ViewGroup.MarginLayoutParams) layoutParams7).width = DPIUtil.getWidthByDesignValue750((Activity) this.baseActivity, 56);
        ((ViewGroup.MarginLayoutParams) layoutParams7).height = DPIUtil.getWidthByDesignValue750((Activity) this.baseActivity, (int) R2.anim.out_to_bottom_slow);
        ((ViewGroup.MarginLayoutParams) layoutParams7).rightMargin = DPIUtil.getWidthByDesignValue750((Activity) this.baseActivity, 12);
        ((ViewGroup.MarginLayoutParams) layoutParams7).bottomMargin = DPIUtil.getWidthByDesignValue750((Activity) this.baseActivity, 12);
        this.thumbsUpLottieView.setLayoutParams(layoutParams7);
    }

    @Override // com.jingdong.common.recommend.forlist.BaseRecommendViewHolder
    public void exposurePercent(double d) {
        super.exposurePercent(d);
        if (d > 0.0d) {
            try {
                StringBuilder sb = new StringBuilder();
                RecommendHomeCardBean.SubWareList subWareList = this.liveSubWareInfo;
                if (subWareList != null && !subWareList.hasExpoView) {
                    RecommendNetUtil.recommendFeedBack(subWareList.expoLog);
                    if (!TextUtils.isEmpty(this.liveSubWareInfo.exposureJsonSourceValue)) {
                        this.liveSubWareInfo.hasExpoView = true;
                        sb.append("[");
                        sb.append(this.liveSubWareInfo.exposureJsonSourceValue);
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
        setViewLayout();
        RecommendHomeCardBean.SubWareList subWareList = recommendHomeCardBean.subWareList.get(0);
        this.liveSubWareInfo = subWareList;
        if (subWareList != null) {
            if (!TextUtils.isEmpty(subWareList.bgImg)) {
                RecommendImageUtils.downloadAnddisplay(this.liveSubWareInfo.bgImg, this.layout_container);
            }
            this.title.bind(this.baseActivity, HomeCardMainTitleLayout.Info.builder().setShowNameImg(this.liveSubWareInfo.showNameImg).setShowName(this.liveSubWareInfo.showName).setMaintitleColor(this.liveSubWareInfo.maintitleColor).setShowNameImgWidth(this.liveSubWareInfo.showNameImgWidth));
            HomeCardSubTitleLayout.Info sloganTagImg = HomeCardSubTitleLayout.Info.builder().setSlogan(this.liveSubWareInfo.slogan).setSloganColor(this.liveSubWareInfo.sloganColor).setSloganTagImg(this.liveSubWareInfo.sloganTagImg);
            if (!TextUtils.isEmpty(this.liveSubWareInfo.showNameImgWidth)) {
                try {
                    sloganTagImg.setMaxWidth(308 - Integer.parseInt(this.liveSubWareInfo.showNameImgWidth));
                } catch (Exception unused) {
                }
            }
            this.subTitle.bind(this.baseActivity, sloganTagImg);
            this.roomInfoWidget.bind(this.baseActivity, RecommendVideoRoomInfoWidget.Info.builder().setPageView(this.liveSubWareInfo.pageView).setStreamerIcon(this.liveSubWareInfo.streamerIcon).setStreamerName(this.liveSubWareInfo.streamerName));
            startThumbsUpLottieAnimation();
            setLiveData(getLiveLayoutData(this.liveSubWareInfo));
            RecommendVideoWidget recommendVideoWidget = this.recommendVideoWidget;
            if (recommendVideoWidget != null) {
                recommendVideoWidget.setCanPlayWithNoWifi(true);
            }
            this.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.recommend.forlist.m
                {
                    RecommendHomeLiveViewHolder.this = this;
                }

                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    RecommendHomeLiveViewHolder.this.c(view);
                }
            });
        }
    }

    public void startThumbsUpLottieAnimation() {
        if (this.thumbsUpLottieView.isAnimating()) {
            return;
        }
        this.thumbsUpLottieView.setAnimation("thumbs_up.json");
        this.thumbsUpLottieView.setSpeed(1.0f);
        this.thumbsUpLottieView.setRepeatCount(-1);
        this.thumbsUpLottieView.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() { // from class: com.jingdong.common.recommend.forlist.RecommendHomeLiveViewHolder.1
            {
                RecommendHomeLiveViewHolder.this = this;
            }

            @Override // android.view.View.OnAttachStateChangeListener
            public void onViewAttachedToWindow(View view) {
                if (RecommendHomeLiveViewHolder.this.thumbsUpLottieView.isAnimating()) {
                    return;
                }
                RecommendHomeLiveViewHolder.this.thumbsUpLottieView.playAnimation();
            }

            @Override // android.view.View.OnAttachStateChangeListener
            public void onViewDetachedFromWindow(View view) {
            }
        });
    }
}

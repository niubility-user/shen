package com.jingdong.common.recommend.ui.homerecommend;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import com.jingdong.app.mall.bundle.mobileConfig.JDMobileConfig;
import com.jingdong.cleanmvp.common.BaseEvent;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.gray.GrayModelListener;
import com.jingdong.common.gray.JDGrayModelUtils;
import com.jingdong.common.recommend.RecommendConstant;
import com.jingdong.common.recommend.RecommendSPUtils;
import com.jingdong.common.recommend.RecommendUtils;
import com.jingdong.common.recommend.RecommendVideoManagerNew;
import com.jingdong.common.recommend.RecommendViewUtil;
import com.jingdong.common.recommend.entity.RecommendHeaderData;
import com.jingdong.common.recommend.entity.RecommendHomeTabs;
import com.jingdong.common.recommend.forlist.BaseRecommendMaterialViewHolder;
import com.jingdong.common.recommend.forlist.RecommendBannerBViewHolder;
import com.jingdong.common.recommend.forlist.RecommendProductManager;
import com.jingdong.common.recommend.forlist.RecommendUtil;
import com.jingdong.common.recommend.forlist.RecommendViewHolder;
import com.jingdong.common.recommend.ui.RecommendChildRecyclerView;
import com.jingdong.sdk.oklog.OKLog;
import de.greenrobot.event.EventBus;

/* loaded from: classes6.dex */
public class HomeChildRecyclerView extends RecommendChildRecyclerView {
    int currentScrollState;
    private boolean hasShowFeedBackAni;
    boolean isAdAllPositionExpo;
    int preScrollState;
    private RecyclerView.OnScrollListener scrollReport;

    public HomeChildRecyclerView(Context context) {
        super(context);
        this.hasShowFeedBackAni = false;
        this.currentScrollState = 0;
        this.preScrollState = 0;
        this.isAdAllPositionExpo = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void exeBannerExpo(boolean z) {
        RecommendViewHolder bannerViewHolder;
        RecommendBannerBViewHolder recommendBannerBViewHolder;
        if (!isAttachedToWindow() || (bannerViewHolder = getBannerViewHolder()) == null || (recommendBannerBViewHolder = bannerViewHolder.bannerBViewHolder) == null) {
            return;
        }
        if (z) {
            recommendBannerBViewHolder.expoProduct();
        } else if (HomeRecommendContentLayout.isHomeRecommendFistVisible) {
            recommendBannerBViewHolder.expoProduct();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void execRealExpo() {
        RecommendUtil recommendUtil;
        try {
            if (isAttachedToWindow()) {
                for (int i2 = 0; i2 < getChildCount(); i2++) {
                    View childAt = getChildAt(i2);
                    RecyclerView.ViewHolder childViewHolder = getChildViewHolder(childAt);
                    if (childViewHolder instanceof RecommendViewHolder) {
                        RecommendViewHolder recommendViewHolder = (RecommendViewHolder) childViewHolder;
                        double currentExpoPercent = RecommendViewUtil.getCurrentExpoPercent(childAt);
                        ((RecommendViewHolder) childViewHolder).doRealRightExpoForHomeFloor(currentExpoPercent);
                        if ((this.isAdAllPositionExpo || recommendViewHolder.positionInDatas < 7) && recommendViewHolder.needAdRealExpo() && (recommendUtil = this.mRecommendUtil) != null && recommendUtil.isAdRealExpo && currentExpoPercent > 0.0d) {
                            recommendViewHolder.doAdRealExpo();
                        }
                        if (recommendViewHolder.needRealExpo() && currentExpoPercent > 0.5d) {
                            recommendViewHolder.doRealExpo();
                        }
                    }
                }
            }
        } catch (Exception e2) {
            if (OKLog.D) {
                e2.printStackTrace();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.common.recommend.ui.RecommendChildRecyclerView
    public void afterNotifyDataChange(int i2, int i3) {
        super.afterNotifyDataChange(i2, i3);
        if (i2 != 1 || i3 <= 0) {
            return;
        }
        post(new Runnable() { // from class: com.jingdong.common.recommend.ui.homerecommend.HomeChildRecyclerView.3
            @Override // java.lang.Runnable
            public void run() {
                HomeChildRecyclerView.this.execRealExpo();
                HomeChildRecyclerView.this.exeBannerExpo(false);
                HomeChildRecyclerView.this.changeBannerPlayState(HomeRecommendContentLayout.isFirstHomeRecyclerViewShow);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.common.recommend.ui.RecommendChildRecyclerView
    public void changViewPadding() {
        super.changViewPadding();
        if (this.mRecommendUtil.getIsSingleRow()) {
            setPadding(0, 0, 0, 0);
            return;
        }
        int i2 = RecommendUtils.RECYCLER_VIEW_PADDING;
        setPadding(i2, 0, i2, 0);
    }

    public void changeBannerPlayState(boolean z) {
        RecommendBannerBViewHolder bannerBView;
        if (HomeRecommendContentLayout.needWaitSplash || (bannerBView = getBannerBView()) == null) {
            return;
        }
        bannerBView.switchPlayState(z, this.currentScrollState);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.common.recommend.ui.RecommendChildRecyclerView
    public void changeItemViewPadding(RecyclerView.ViewHolder viewHolder) {
        super.changeItemViewPadding(viewHolder);
    }

    @Override // com.jingdong.common.recommend.ui.RecommendChildRecyclerView
    public void controlFullSpan(RecyclerView.ViewHolder viewHolder) {
        super.controlFullSpan(viewHolder);
        if (!(viewHolder instanceof RecommendViewHolder) || ((RecommendViewHolder) viewHolder).recommendProductListViewHolder == null) {
            return;
        }
        ViewGroup.LayoutParams layoutParams = viewHolder.itemView.getLayoutParams();
        if (layoutParams instanceof StaggeredGridLayoutManager.LayoutParams) {
            ((StaggeredGridLayoutManager.LayoutParams) layoutParams).setFullSpan(true);
        }
    }

    public RecommendBannerBViewHolder getBannerBView() {
        RecommendViewHolder bannerViewHolder = getBannerViewHolder();
        if (bannerViewHolder != null) {
            return bannerViewHolder.bannerBViewHolder;
        }
        return null;
    }

    public RecommendViewHolder getBannerViewHolder() {
        try {
            View childAt = getChildAt(0);
            if (childAt == null || childAt.getParent() == null) {
                return null;
            }
            RecyclerView.ViewHolder childViewHolder = getChildViewHolder(childAt);
            if (childViewHolder instanceof RecommendViewHolder) {
                return (RecommendViewHolder) childViewHolder;
            }
            return null;
        } catch (Exception e2) {
            if (OKLog.D) {
                e2.printStackTrace();
                return null;
            }
            return null;
        }
    }

    public void onEventMainThread(BaseEvent baseEvent) {
        if (baseEvent != null) {
            if (RecommendUtils.HOME_FIST_RECOMMEND_VIEW_SHOW.equals(baseEvent.getType())) {
                OKLog.d(RecommendBannerBViewHolder.BANNERB, "\u9875\u9762\u5207\u6362\u5230\u53ef\u89c1");
                post(new Runnable() { // from class: com.jingdong.common.recommend.ui.homerecommend.HomeChildRecyclerView.4
                    @Override // java.lang.Runnable
                    public void run() {
                        if (HomeChildRecyclerView.this.isAttachedToWindow()) {
                            HomeChildRecyclerView homeChildRecyclerView = HomeChildRecyclerView.this;
                            homeChildRecyclerView.preScrollState = 0;
                            homeChildRecyclerView.currentScrollState = 0;
                            homeChildRecyclerView.exeBannerExpo(false);
                            HomeChildRecyclerView.this.changeBannerPlayState(true);
                        }
                    }
                });
            } else if (RecommendUtils.HOME_FIST_RECOMMEND_VIEW_HIDE.equals(baseEvent.getType())) {
                OKLog.d(RecommendBannerBViewHolder.BANNERB, "\u9875\u9762\u5207\u6362\u5230\u4e0d\u53ef\u89c1");
                changeBannerPlayState(false);
            }
        }
    }

    @Override // com.jingdong.common.recommend.ui.RecommendChildRecyclerView, com.jingdong.common.recommend.ScrollDispatchHelper.ScrollDispatchChild
    public void onParentScroll(int i2) {
        super.onParentScroll(i2);
        exeBannerExpo(true);
    }

    @Override // com.jingdong.common.recommend.ui.RecommendChildRecyclerView, com.jingdong.common.recommend.ScrollDispatchHelper.ScrollDispatchChild
    public void onSelfScroll(int i2) {
        super.onSelfScroll(i2);
        exeBannerExpo(true);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.common.recommend.ui.RecommendChildRecyclerView
    public void onSuccess(int i2, RecommendHomeTabs recommendHomeTabs, RecommendHeaderData recommendHeaderData) {
        RecommendUtil recommendUtil = this.mRecommendUtil;
        if (recommendUtil != null) {
            this.isAdAllPositionExpo = recommendHeaderData != null && recommendHeaderData.isAdAllPositionExpo;
            recommendUtil.setAdRealExpo("1".equals(JDMobileConfig.getInstance().getConfig("JDUniformRecommend", "realExpo", "realExpo")) && "1".equals(JDMobileConfig.getInstance().getConfig("JDUniformRecommend", "uniformRecommendAccurateADExpo", "uniformRecommendAccurateADExpo")), this.isAdAllPositionExpo);
        }
    }

    @Override // com.jingdong.common.recommend.ui.RecommendChildRecyclerView
    protected void resetData() {
        RecommendVideoManagerNew recommendVideoManagerNew = this.videoManagerNew;
        if (recommendVideoManagerNew != null) {
            recommendVideoManagerNew.reset();
        }
        RecommendProductManager recommendProductManager = this.mRecommendProductManager;
        if (recommendProductManager != null) {
            recommendProductManager.reSet();
            this.isPullToRefresh = true;
        }
    }

    @Override // com.jingdong.common.recommend.ui.RecommendChildRecyclerView, com.jingdong.common.recommend.ScrollDispatchHelper.ScrollDispatchChild
    public void scrollStateChange(int i2) {
        super.scrollStateChange(i2);
        if (!this.hasShowFeedBackAni && i2 == 0 && "1".equals(JDMobileConfig.getInstance().getConfig("JDUniformRecommend", "materialFeedbackAni", "materialFeedbackAni"))) {
            try {
                if (isAttachedToWindow()) {
                    int childCount = getChildCount();
                    for (int i3 = 0; i3 < childCount; i3++) {
                        View childAt = getChildAt(i3);
                        RecyclerView.ViewHolder childViewHolder = getChildViewHolder(childAt);
                        if ((childViewHolder instanceof RecommendViewHolder) && ((RecommendViewHolder) childViewHolder).positionInDatas > 9 && (((RecommendViewHolder) childViewHolder).inUseViewHolder instanceof BaseRecommendMaterialViewHolder) && RecommendViewUtil.getCurrentExpoPercent(childAt) > 0.95d) {
                            this.hasShowFeedBackAni = true;
                            RecommendSPUtils.putBoolean(RecommendUtils.MATERIAL_FEEDBACK_ANI, true);
                            ((BaseRecommendMaterialViewHolder) ((RecommendViewHolder) childViewHolder).inUseViewHolder).showFeedBackAni();
                            return;
                        }
                    }
                }
            } catch (Exception e2) {
                if (OKLog.D) {
                    e2.printStackTrace();
                }
            }
        }
    }

    @Override // com.jingdong.common.recommend.ui.RecommendChildRecyclerView
    public void scrollToTop() {
        super.scrollToTop();
        changeBannerPlayState(HomeRecommendContentLayout.isFirstHomeRecyclerViewShow);
    }

    public void setHomeJsonData(boolean z, int i2) {
        String str = RecommendUtils.isAppB(i2) ? RecommendConstant.HOME_B_SOURCE : "";
        boolean z2 = (this.mRecommendProductManager.getSourceExt() == null || str.equals(this.mRecommendProductManager.getSourceExt())) ? false : true;
        this.mRecommendProductManager.setSourceExt(str);
        if (z2) {
            viewReset();
            onPageSelected();
        }
    }

    public HomeChildRecyclerView(final RecyclerView recyclerView, @NonNull BaseActivity baseActivity, int i2) {
        super(recyclerView, baseActivity, i2);
        this.hasShowFeedBackAni = false;
        this.currentScrollState = 0;
        this.preScrollState = 0;
        this.isAdAllPositionExpo = false;
        if (this.scrollReport == null) {
            this.scrollReport = new RecyclerView.OnScrollListener() { // from class: com.jingdong.common.recommend.ui.homerecommend.HomeChildRecyclerView.1
                @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
                public void onScrollStateChanged(@NonNull RecyclerView recyclerView2, int i3) {
                    super.onScrollStateChanged(recyclerView2, i3);
                    if (HomeRecommendContentLayout.isFirstHomeRecyclerViewShow) {
                        if (recyclerView.getScrollState() == 0 && HomeChildRecyclerView.this.getScrollState() == 0) {
                            HomeChildRecyclerView.this.currentScrollState = 0;
                        } else {
                            HomeChildRecyclerView.this.currentScrollState = 1;
                        }
                        HomeChildRecyclerView homeChildRecyclerView = HomeChildRecyclerView.this;
                        if (homeChildRecyclerView.preScrollState != homeChildRecyclerView.currentScrollState) {
                            homeChildRecyclerView.changeBannerPlayState(HomeRecommendContentLayout.isFirstHomeRecyclerViewShow);
                        }
                        HomeChildRecyclerView homeChildRecyclerView2 = HomeChildRecyclerView.this;
                        homeChildRecyclerView2.preScrollState = homeChildRecyclerView2.currentScrollState;
                    }
                }

                @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
                public void onScrolled(@NonNull RecyclerView recyclerView2, int i3, int i4) {
                    super.onScrolled(recyclerView2, i3, i4);
                    if (i4 != 0) {
                        HomeChildRecyclerView.this.execRealExpo();
                    }
                }
            };
        }
        addOnScrollListener(this.scrollReport);
        if (recyclerView != null) {
            recyclerView.addOnScrollListener(this.scrollReport);
        }
        this.hasShowFeedBackAni = RecommendSPUtils.getBoolean(RecommendUtils.MATERIAL_FEEDBACK_ANI, false);
        RecommendProductManager recommendProductManager = this.mRecommendProductManager;
        if (recommendProductManager != null) {
            recommendProductManager.setEnableCache(3);
        }
        EventBus.getDefault().register(this);
        JDGrayModelUtils.getInstance().addListener(new GrayModelListener() { // from class: com.jingdong.common.recommend.ui.homerecommend.HomeChildRecyclerView.2
            @Override // com.jingdong.common.gray.GrayModelListener
            public void onModelChanged() {
                try {
                    if (!HomeChildRecyclerView.this.hasRecommendData() || ((RecommendChildRecyclerView) HomeChildRecyclerView.this).mRecommendUtil == null || ((RecommendChildRecyclerView) HomeChildRecyclerView.this).mRecommendUtil.getRecommendOtherData() == null) {
                        return;
                    }
                    HomeChildRecyclerView homeChildRecyclerView = HomeChildRecyclerView.this;
                    homeChildRecyclerView.notifyItemRangeChanged(0, ((RecommendChildRecyclerView) homeChildRecyclerView).mRecommendUtil.getRecommendOtherData().recommendGrayNumber);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        });
    }
}

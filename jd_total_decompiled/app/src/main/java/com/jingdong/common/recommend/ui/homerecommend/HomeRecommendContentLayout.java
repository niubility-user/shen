package com.jingdong.common.recommend.ui.homerecommend;

import android.app.Activity;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.app.mall.bundle.mobileConfig.JDMobileConfig;
import com.jingdong.cleanmvp.common.BaseEvent;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.login.LoginEvent;
import com.jingdong.common.recommend.RecommendConstant;
import com.jingdong.common.recommend.RecommendMtaUtils;
import com.jingdong.common.recommend.RecommendSPUtils;
import com.jingdong.common.recommend.RecommendUtils;
import com.jingdong.common.recommend.RecommendViewUtil;
import com.jingdong.common.recommend.entity.RecommendBannerPosition;
import com.jingdong.common.recommend.entity.RecommendHomeTabs;
import com.jingdong.common.recommend.entity.RecommendTab;
import com.jingdong.common.recommend.entity.RecommendTipsEvent;
import com.jingdong.common.recommend.forlist.RecommendBannerBViewHolder;
import com.jingdong.common.recommend.ui.PagerSlidingTabStrip;
import com.jingdong.common.recommend.ui.RecommendChildRecyclerView;
import com.jingdong.common.recommend.ui.RecommendContentLayout;
import com.jingdong.common.recommend.ui.TabItemViewInterface;
import com.jingdong.common.unification.statusbar.UnStatusBarTintUtil;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import com.jingdong.jdsdk.utils.DPIUtil;
import com.jingdong.sdk.oklog.OKLog;
import de.greenrobot.event.EventBus;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class HomeRecommendContentLayout extends RecommendContentLayout {
    private static final String MTA_TAB_CLICK_FORMAT = "%d_%s_%d_%d";
    public static boolean isFirstHomeRecyclerViewShow = true;
    public static boolean isHomePageVisible = true;
    public static boolean isHomeRecommendFistVisible = true;
    public static boolean isUseDynamicZip = true;
    public static boolean isUseNewDynApi = true;
    public static boolean needWaitSplash;
    private final int TITLEBARLAYOUTHEIGHT;
    private int defaultTopSpace;
    boolean isUploadScrollY;
    protected AtomicBoolean isViewBind;
    private int mCurrentVisibleIndex;
    private boolean mIsAttach;
    private boolean mIsNeedUploadTabExpo;
    private AtomicBoolean needSendRecommendExpo;
    private String tabAB;
    private int viewWidth;

    /* loaded from: classes6.dex */
    public class HomeRecommendPageAdapter extends RecommendContentLayout.RecommendPageAdapter implements PagerSlidingTabStrip.CustomTabProvider {
        public boolean mHasSubtitle;
        private int mTabItemWidth;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        HomeRecommendPageAdapter() {
            super();
            HomeRecommendContentLayout.this = r1;
            this.mHasSubtitle = false;
        }

        private void calculateTabItemWidth() {
            List<RecommendTab> list = this.mRecommendTabs;
            if (list != null) {
                int size = list.size();
                if (size < 6) {
                    this.mTabItemWidth = (750 - ((size - 1) * 2)) / size;
                } else {
                    this.mTabItemWidth = 150;
                }
            }
        }

        private void checkHasSubtitle() {
            List<RecommendTab> list = this.mRecommendTabs;
            if (list != null) {
                this.mHasSubtitle = true;
                Iterator<RecommendTab> it = list.iterator();
                while (it.hasNext()) {
                    if (TextUtils.isEmpty(it.next().subtitle)) {
                        this.mHasSubtitle = false;
                        return;
                    }
                }
            }
        }

        private void initTabInfoByRecommendTab() {
            checkHasSubtitle();
            calculateTabItemWidth();
        }

        @Override // com.jingdong.common.recommend.ui.PagerSlidingTabStrip.CustomTabProvider
        public View getCustomTabView(ViewGroup viewGroup, int i2) {
            if (i2 >= 0) {
                try {
                    if (i2 < ((RecommendContentLayout) HomeRecommendContentLayout.this).mChildViews.size() && ((RecommendContentLayout) HomeRecommendContentLayout.this).mChildViews.get(i2) != null && ((RecommendChildRecyclerView) ((RecommendContentLayout) HomeRecommendContentLayout.this).mChildViews.get(i2)).getmRecommendTab() != null) {
                        if (HomeRecommendContentLayout.this.isNTabSliding()) {
                            RecommendTabNView recommendTabNView = new RecommendTabNView(HomeRecommendContentLayout.this.getContext());
                            recommendTabNView.setRecommendTab(((RecommendChildRecyclerView) ((RecommendContentLayout) HomeRecommendContentLayout.this).mChildViews.get(i2)).getmRecommendTab());
                            recommendTabNView.setWH(this.mTabItemWidth, 82);
                            return recommendTabNView;
                        }
                        RecommendTabBView recommendTabBView = new RecommendTabBView(HomeRecommendContentLayout.this.getContext());
                        recommendTabBView.setRecommendTab(((RecommendChildRecyclerView) ((RecommendContentLayout) HomeRecommendContentLayout.this).mChildViews.get(i2)).getmRecommendTab());
                        recommendTabBView.setHasSubTitle(this.mHasSubtitle);
                        recommendTabBView.setWH(this.mTabItemWidth, this.mHasSubtitle ? 125 : 90);
                        return recommendTabBView;
                    }
                } catch (Exception e2) {
                    if (OKLog.D) {
                        e2.printStackTrace();
                    }
                }
            }
            return new View(HomeRecommendContentLayout.this.getContext());
        }

        @Override // com.jingdong.common.recommend.ui.RecommendContentLayout.RecommendPageAdapter
        public void setRecommendTabs(List<RecommendTab> list) {
            super.setRecommendTabs(list);
            initTabInfoByRecommendTab();
        }

        @Override // com.jingdong.common.recommend.ui.PagerSlidingTabStrip.CustomTabProvider
        public void tabSelected(View view) {
            if (view instanceof TabItemViewInterface) {
                ((TabItemViewInterface) view).setTabSelect(true);
            }
        }

        @Override // com.jingdong.common.recommend.ui.PagerSlidingTabStrip.CustomTabProvider
        public void tabUnselected(View view) {
            if (view instanceof TabItemViewInterface) {
                ((TabItemViewInterface) view).setTabSelect(false);
            }
        }
    }

    public HomeRecommendContentLayout(RecyclerView recyclerView, BaseActivity baseActivity) {
        super(recyclerView, baseActivity);
        this.isViewBind = new AtomicBoolean(false);
        this.needSendRecommendExpo = new AtomicBoolean(false);
        this.mCurrentVisibleIndex = 0;
        this.tabAB = "B";
        this.isUploadScrollY = true;
        this.TITLEBARLAYOUTHEIGHT = DPIUtil.getWidthByDesignValue750(96);
        this.defaultTopSpace = -1;
        RecommendUtils.recommendTestSwitch = RecommendSPUtils.getBoolean(RecommendSPUtils.SP_USER_DEBUG, false);
        recommendInitConfig();
        RecommendUtils.RECOMMEND_LIVE_GUIDE_SHOW = true ^ RecommendSPUtils.getBoolean(RecommendSPUtils.SP_RECOMMEND_LIVE_GUIDE, false);
        EventBus.getDefault().register(this);
    }

    private void createNTabSliding() {
        HomeNTabSliding homeNTabSliding = new HomeNTabSliding(getContext());
        this.mSlidingTabStrip = homeNTabSliding;
        homeNTabSliding.setTabBackground(0);
        this.mSlidingTabStrip.setViewPagerSmoothScroll(false);
        initSlidingTab();
    }

    private void createOSlidingTab() {
        HomeBTabSliding homeBTabSliding = new HomeBTabSliding(getContext());
        this.mSlidingTabStrip = homeBTabSliding;
        homeBTabSliding.setTabBackground(0);
        this.mSlidingTabStrip.setViewPagerSmoothScroll(false);
        initSlidingTab();
    }

    private void dealCreateSlidingTab(RecommendHomeTabs recommendHomeTabs) {
        if (recommendHomeTabs != null) {
            if ("B".equals(recommendHomeTabs.getLocalTabAB())) {
                if (isNTabSliding()) {
                    return;
                }
                this.mSlidingTabStrip = null;
                createNTabSliding();
            } else if ("A".equals(recommendHomeTabs.getLocalTabAB())) {
                if ("0".equals(JDMobileConfig.getInstance().getConfig("jdRecommend", "tab100B", "enable", "1"))) {
                    this.tabAB = "A";
                } else {
                    this.tabAB = "B";
                }
                if (isOTabSliding()) {
                    return;
                }
                this.mSlidingTabStrip = null;
                createOSlidingTab();
            }
        }
    }

    public boolean isNTabSliding() {
        PagerSlidingTabStrip pagerSlidingTabStrip = this.mSlidingTabStrip;
        return pagerSlidingTabStrip != null && (pagerSlidingTabStrip instanceof HomeNTabSliding);
    }

    private boolean isOTabSliding() {
        PagerSlidingTabStrip pagerSlidingTabStrip = this.mSlidingTabStrip;
        return pagerSlidingTabStrip != null && (pagerSlidingTabStrip instanceof HomeBTabSliding);
    }

    private void recommendInitConfig() {
        RecommendUtils.requestDynamic();
        RecommendConstant.loadingBuxFix = "1".equals(JDMobileConfig.getInstance().getConfig("JDUniformRecommend", "loadingBug", "loadingBug", "0"));
        RecommendConstant.errorReport = "1".equals(JDMobileConfig.getInstance().getConfig("JDUniformRecommend", "errorReport", "errorReport", "0"));
        RecommendMtaUtils.enableRightExpo = "1".equals(JDMobileConfig.getInstance().getConfig("JDUniformRecommend", "enableRightExpo", "enableRightExpo", "0"));
    }

    private void sendFirstViewState() {
        int i2 = this.mCurrentVisibleIndex;
        if (i2 == 0) {
            isHomeRecommendFistVisible = true;
        } else {
            isHomeRecommendFistVisible = false;
        }
        if (i2 == 0 && isHomePageVisible) {
            isFirstHomeRecyclerViewShow = true;
            EventBus.getDefault().post(new BaseEvent(RecommendUtils.HOME_FIST_RECOMMEND_VIEW_SHOW));
            return;
        }
        isFirstHomeRecyclerViewShow = false;
        EventBus.getDefault().post(new BaseEvent(RecommendUtils.HOME_FIST_RECOMMEND_VIEW_HIDE));
    }

    @Override // com.jingdong.common.recommend.ui.RecommendContentLayout
    public void createOneRecyclerView() {
        HomeChildRecyclerView homeChildRecyclerView = new HomeChildRecyclerView(getParentRecycleView(), this.mActivity, this.fromType);
        this.oneRCView = homeChildRecyclerView;
        homeChildRecyclerView.setRecommendConfig(this.recommendConfig);
    }

    @Override // com.jingdong.common.recommend.ui.RecommendContentLayout
    protected void createViewPagerAdapter() {
        this.pageAdapter = new HomeRecommendPageAdapter();
    }

    public RecommendBannerPosition getBannerLocationOnScreen() {
        return null;
    }

    @Override // com.jingdong.common.recommend.ui.RecommendContentLayout, com.jingdong.common.recommend.ScrollDispatchHelper.ScrollDispatchChild
    public ViewParent getChildParent() {
        if (getParent() == null || getParent().getParent() == null) {
            return null;
        }
        return getParent().getParent();
    }

    @Override // com.jingdong.common.recommend.ui.RecommendContentLayout, com.jingdong.common.recommend.ScrollDispatchHelper.ScrollDispatchChild
    public int getChildTop() {
        if (getParent() == null || !(getParent() instanceof View)) {
            return 0;
        }
        return ((View) getParent()).getTop();
    }

    @Override // com.jingdong.common.recommend.ui.RecommendContentLayout
    public int getContentHeight() {
        int height;
        RecyclerView recyclerView = this.mParentRecyclerView;
        if (recyclerView != null && recyclerView.getHeight() > 0) {
            height = this.mParentRecyclerView.getHeight();
        } else {
            height = DPIUtil.getHeight() - DPIUtil.dip2px(50.0f);
        }
        return height - getTopSpace();
    }

    @Override // com.jingdong.common.recommend.ui.RecommendContentLayout
    public void getRecommendSuccess(RecommendHomeTabs recommendHomeTabs) {
        dealCreateSlidingTab(recommendHomeTabs);
        super.getRecommendSuccess(recommendHomeTabs);
        RecommendUtils.homeScreenHeight = this.mParentRecyclerView.getHeight();
        if (getChildTop() > 0) {
            if (((RecommendUtils.screenHeight - RecommendUtils.statusBarHeight) - getChildTop()) - RecommendUtils.bottomHeight > (hasTab() ? RecommendViewUtil.getRightSize(120) : 0)) {
                RecommendViewUtil.isHomeRecommendInScreen = true;
                return;
            }
        }
        RecommendViewUtil.isHomeRecommendInScreen = false;
    }

    @Override // com.jingdong.common.recommend.ui.RecommendContentLayout, com.jingdong.common.recommend.ScrollDispatchHelper.ScrollDispatchChild
    public int getTopSpace() {
        int i2 = this.defaultTopSpace;
        if (i2 >= 0) {
            return i2;
        }
        int i3 = 0;
        BaseActivity baseActivity = this.mActivity;
        if (baseActivity != null && baseActivity.isStatusBarTintEnable()) {
            i3 = UnStatusBarTintUtil.getStatusBarHeight((Activity) this.mActivity);
        }
        int i4 = i3 + this.TITLEBARLAYOUTHEIGHT;
        this.defaultTopSpace = i4;
        return i4;
    }

    @Override // com.jingdong.common.recommend.ui.RecommendContentLayout
    public boolean hasRecommendData() {
        RecommendChildRecyclerView recommendChildRecyclerView = this.oneRCView;
        if (recommendChildRecyclerView != null) {
            return recommendChildRecyclerView.hasRecommendData();
        }
        return false;
    }

    @Override // com.jingdong.common.recommend.ui.RecommendContentLayout
    protected void initOneRecyclerView() {
        RecommendChildRecyclerView recommendChildRecyclerView = this.oneRCView;
        if (recommendChildRecyclerView != null) {
            recommendChildRecyclerView.setAutoPlayEnable(true);
            this.oneRCView.setBitmapConfig(Bitmap.Config.ARGB_8888);
            RecommendChildRecyclerView recommendChildRecyclerView2 = this.oneRCView;
            recommendChildRecyclerView2.isLoadExpo = true;
            recommendChildRecyclerView2.setCurrentPlan("B");
        }
    }

    @Override // com.jingdong.common.recommend.ui.RecommendContentLayout
    protected void initOtherRecyclerView(RecommendChildRecyclerView recommendChildRecyclerView) {
        if (recommendChildRecyclerView != null) {
            recommendChildRecyclerView.isShowEmptyView.set(true);
            recommendChildRecyclerView.setAutoPlayEnable(true);
        }
    }

    public boolean isSlidingWithAnimal() {
        return hasTab();
    }

    public boolean isToPlayHomeVideo(String str) {
        return false;
    }

    @Override // com.jingdong.common.recommend.ui.RecommendContentLayout
    public void onActivityDestroy() {
        super.onActivityDestroy();
        EventBus.getDefault().unregister(this);
    }

    public void onBackToHome() {
        if (this.mCurrentView == null || !this.isViewBind.get()) {
            return;
        }
        this.mCurrentView.onBackToHome();
    }

    public void onEventMainThread(BaseEvent baseEvent) {
        if (baseEvent != null && "MallFloorEvent".equals(baseEvent.getClass().getSimpleName())) {
            if ("home_resume".equals(baseEvent.getType())) {
                isHomePageVisible = true;
                sendFirstViewState();
            } else if ("home_pause".equals(baseEvent.getType())) {
                isHomePageVisible = false;
                sendFirstViewState();
            }
        }
        if (baseEvent instanceof LoginEvent) {
            if (LoginEvent.TYPE_LOGOUT.equals(baseEvent.getType()) || LoginEvent.TYPE_READY_LOGOUT.equals(baseEvent.getType())) {
                RecommendUtils.setRecommendTestSwitch(false);
            }
        }
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        super.onLayout(z, i2, i3, i4, i5);
        if (!this.isUploadScrollY || getChildTop() <= 0) {
            return;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("height", (this.parentScrollY + getChildTop()) + "");
        } catch (JSONException e2) {
            if (OKLog.D) {
                e2.printStackTrace();
            }
        }
        JDMtaUtils.sendExposureDataWithExt(getContext(), "Home_RecomHeight", "", RecommendMtaUtils.Home_PageId, "JDHomeFragment", "", jSONObject.toString(), "", "", "", null);
        this.isUploadScrollY = false;
    }

    @Override // android.widget.LinearLayout, android.view.View
    protected void onMeasure(int i2, int i3) {
        int size = View.MeasureSpec.getSize(i2);
        RecommendUtils.windowWidthSize = size;
        if (this.viewWidth != size) {
            this.viewWidth = size;
            if (size != 0) {
                PagerSlidingTabStrip pagerSlidingTabStrip = this.mSlidingTabStrip;
                if (pagerSlidingTabStrip instanceof HomeTabInterface) {
                    ((HomeTabInterface) pagerSlidingTabStrip).onWidthSizeChange();
                }
            }
        }
        super.onMeasure(i2, i3);
    }

    @Override // com.jingdong.common.recommend.ui.RecommendContentLayout
    public void onPageChange(int i2) {
        super.onPageChange(i2);
        this.mCurrentVisibleIndex = i2;
        sendFirstViewState();
    }

    @Override // com.jingdong.common.recommend.ui.RecommendContentLayout, com.jingdong.common.utils.text.OnTextScaleModeChangeListener
    public void onTextScaleModeChanged() {
        super.onTextScaleModeChanged();
        for (int i2 = 0; i2 < this.mChildViews.size(); i2++) {
            RecommendChildRecyclerView recommendChildRecyclerView = this.mChildViews.get(i2);
            if (recommendChildRecyclerView != null && recommendChildRecyclerView.getAdapter() != null) {
                recommendChildRecyclerView.getAdapter().notifyDataSetChanged();
            }
        }
    }

    public void onViewAttached() {
    }

    public void onViewBind() {
        this.mIsAttach = true;
        this.isViewBind.set(true);
        this.mIsNeedUploadTabExpo = true;
        this.needSendRecommendExpo.set(true);
        RecommendChildRecyclerView recommendChildRecyclerView = this.mCurrentView;
        if (recommendChildRecyclerView != null) {
            recommendChildRecyclerView.onViewBind();
        }
    }

    public void onViewDetached() {
        this.mIsAttach = false;
    }

    public void onViewRecycle() {
        this.isViewBind.set(false);
        this.needSendRecommendExpo.set(true);
        RecommendChildRecyclerView recommendChildRecyclerView = this.mCurrentView;
        if (recommendChildRecyclerView != null) {
            recommendChildRecyclerView.onViewRecycle();
        }
    }

    @Override // com.jingdong.common.recommend.ui.RecommendContentLayout
    public void resetContent() {
        this.isUploadScrollY = true;
        this.mCurrentVisibleIndex = 0;
        isHomeRecommendFistVisible = true;
        if (hasRecommendData()) {
            this.parentScrollY = 0;
        }
        if ("1".equals(JDMobileConfig.getInstance().getConfig("JDUniformRecommend", "refreshCache", "refreshCache", "1"))) {
            resetHomeContent();
        } else {
            super.resetContent();
        }
    }

    public void resetHomeContent() {
        this.mMaxVisitPosition = 0;
        ViewPager viewPager = this.mViewPager;
        if (viewPager != null) {
            viewPager.setCurrentItem(0);
        }
        PagerSlidingTabStrip pagerSlidingTabStrip = this.mSlidingTabStrip;
        if (pagerSlidingTabStrip != null) {
            pagerSlidingTabStrip.releaseView();
        }
        RecommendChildRecyclerView recommendChildRecyclerView = this.oneRCView;
        if (recommendChildRecyclerView == null) {
            release();
            resetView();
        } else {
            if (recommendChildRecyclerView != null) {
                recommendChildRecyclerView.removeOnScrollListener(this.childScrollListener);
                this.oneRCView.viewReset();
            }
            for (int i2 = 1; i2 < this.mChildViews.size(); i2++) {
                if (this.mChildViews.get(i2) != null) {
                    this.mChildViews.get(i2).removeOnScrollListener(this.childScrollListener);
                    this.mChildViews.get(i2).viewReset();
                }
            }
        }
        this.mCurrentView = this.oneRCView;
    }

    public void setFistViewShowType(String str) {
        RecommendChildRecyclerView recommendChildRecyclerView = this.oneRCView;
        if (recommendChildRecyclerView != null) {
            recommendChildRecyclerView.setCurrentPlan("B");
        }
    }

    public void setHomeJsonData(JDJSONObject jDJSONObject, boolean z) {
        setHomeJsonData(jDJSONObject, z, 1);
    }

    public void setNeedWaitSplash(boolean z) {
        OKLog.d(RecommendBannerBViewHolder.BANNERB, "\u542f\u52a8\u56fe==>" + z);
        needWaitSplash = z;
        RecommendChildRecyclerView recommendChildRecyclerView = this.oneRCView;
        if (recommendChildRecyclerView instanceof HomeChildRecyclerView) {
            ((HomeChildRecyclerView) recommendChildRecyclerView).changeBannerPlayState(isFirstHomeRecyclerViewShow);
        }
    }

    public void setOnGetRecommendDataListener(RecommendChildRecyclerView.onRecommendContentListener onrecommendcontentlistener) {
        RecommendChildRecyclerView recommendChildRecyclerView = this.oneRCView;
        if (recommendChildRecyclerView != null) {
            recommendChildRecyclerView.setOnRecommendContentListener(onrecommendcontentlistener);
        }
    }

    @Override // com.jingdong.common.recommend.ui.RecommendContentLayout, com.jingdong.common.recommend.ScrollDispatchHelper.ScrollDispatchChild
    public void setTabSpreadState(boolean z) {
        if (z) {
            spreadSlidingTab(true, true);
        } else {
            spreadSlidingTab(false, true);
        }
    }

    public void setTipsEvent(RecommendTipsEvent recommendTipsEvent) {
        RecommendChildRecyclerView recommendChildRecyclerView = this.oneRCView;
        if (recommendChildRecyclerView != null) {
            recommendChildRecyclerView.setTipsEvent(recommendTipsEvent);
        }
    }

    @Override // com.jingdong.common.recommend.ui.RecommendContentLayout, com.jingdong.common.recommend.ScrollDispatchHelper.ScrollDispatchChild
    public void setTopSpace(int i2) {
        this.defaultTopSpace = i2;
        RecommendViewUtil.homeTopSpace = i2;
    }

    @Override // com.jingdong.common.recommend.ui.RecommendContentLayout
    public void settingSlidingTab(RecommendHomeTabs recommendHomeTabs) {
        super.settingSlidingTab(recommendHomeTabs);
        PagerSlidingTabStrip pagerSlidingTabStrip = this.mSlidingTabStrip;
        if (pagerSlidingTabStrip instanceof HomeTabInterface) {
            ((HomeTabInterface) pagerSlidingTabStrip).setHomeTab(recommendHomeTabs);
        }
    }

    public void spreadSlidingTab(boolean z, boolean z2) {
        if (hasTab()) {
            PagerSlidingTabStrip pagerSlidingTabStrip = this.mSlidingTabStrip;
            if (pagerSlidingTabStrip instanceof HomeBTabSliding) {
                ((HomeTabInterface) pagerSlidingTabStrip).dealTabDynamicHeight(z, z2);
            }
        }
    }

    @Override // com.jingdong.common.recommend.ui.RecommendContentLayout
    public void uploadClickMta(RecommendTab recommendTab, int i2) {
        if (recommendTab != null) {
            int i3 = !this.mIsTabClick ? 1 : 0;
            String format = String.format(MTA_TAB_CLICK_FORMAT, Integer.valueOf(recommendTab.tabId), recommendTab.title, Integer.valueOf(i3), Integer.valueOf(i2));
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("tabid", recommendTab.tabId + "");
                jSONObject.put("tabswitch", i3 + "");
                jSONObject.put("tabname", recommendTab.title);
                StringBuilder sb = new StringBuilder();
                try {
                    sb.append(i2);
                    sb.append("");
                    jSONObject.put("tabindex", sb.toString());
                    jSONObject.put("broker_info", TextUtils.isEmpty(recommendTab.broker_info) ? "-100" : recommendTab.broker_info);
                } catch (JSONException e2) {
                    e = e2;
                    if (OKLog.D) {
                        e.printStackTrace();
                    }
                    JDMtaUtils.sendClickDataWithExt(getContext(), "Home_RecommendTab", format, "", RecommendMtaUtils.Home_PageId, "JDHomeFragmen", "", "", jSONObject.toString(), null);
                    super.uploadClickMta(recommendTab, i2);
                }
            } catch (JSONException e3) {
                e = e3;
            }
            JDMtaUtils.sendClickDataWithExt(getContext(), "Home_RecommendTab", format, "", RecommendMtaUtils.Home_PageId, "JDHomeFragmen", "", "", jSONObject.toString(), null);
        }
        super.uploadClickMta(recommendTab, i2);
    }

    @Override // com.jingdong.common.recommend.ui.RecommendContentLayout
    public void uploadExoTabMta() {
        PagerSlidingTabStrip pagerSlidingTabStrip = this.mSlidingTabStrip;
        if (pagerSlidingTabStrip == null || this.mRecommendTabList == null || !this.mIsNeedUploadTabExpo) {
            return;
        }
        int maxExpoPosition = pagerSlidingTabStrip.getMaxExpoPosition();
        if (maxExpoPosition > this.mRecommendTabList.size() - 1) {
            maxExpoPosition = this.mRecommendTabList.size() - 1;
        }
        int max = Math.max(maxExpoPosition, this.mMaxVisitPosition);
        if (max > this.mRecommendTabList.size() - 1) {
            max = this.mRecommendTabList.size() - 1;
        }
        StringBuilder sb = new StringBuilder();
        JSONArray jSONArray = new JSONArray();
        for (int i2 = 0; i2 <= max; i2++) {
            RecommendTab recommendTab = this.mRecommendTabList.get(i2);
            sb.append(recommendTab.tabId);
            sb.append(CartConstant.KEY_YB_INFO_LINK);
            sb.append(recommendTab.title);
            sb.append(CartConstant.KEY_YB_INFO_LINK);
            sb.append(i2);
            if (i2 < max) {
                sb.append("&&");
            }
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("tabid", recommendTab.tabId + "");
                jSONObject.put("tabname", recommendTab.title);
                jSONObject.put("tabindex", i2 + "");
                jSONObject.put("broker_info", TextUtils.isEmpty(recommendTab.broker_info) ? "-100" : recommendTab.broker_info);
            } catch (JSONException e2) {
                if (OKLog.D) {
                    e2.printStackTrace();
                }
            }
            jSONArray.put(jSONObject);
        }
        String jSONArray2 = jSONArray.toString();
        if (!TextUtils.isEmpty(jSONArray2)) {
            JDMtaUtils.sendExposureDataWithExt(getContext(), "Home_RecommendTabExpo", "", RecommendMtaUtils.Home_PageId, "JDHomeFragmen", "", jSONArray2, null);
        } else if (!TextUtils.isEmpty(sb.toString())) {
            JDMtaUtils.sendExposureDataWithExt(getContext(), "Home_RecommendTabExpo", sb.toString(), RecommendMtaUtils.Home_PageId, "JDHomeFragmen", "", "", null);
        }
        if (this.mIsAttach) {
            return;
        }
        this.mIsNeedUploadTabExpo = false;
        this.mSlidingTabStrip.resetScroll();
    }

    @Override // com.jingdong.common.recommend.ui.RecommendContentLayout
    public void uploadExpoData() {
        if (this.mCurrentView != null) {
            if (this.isViewBind.get() || this.needSendRecommendExpo.getAndSet(false)) {
                this.mCurrentView.sendExposureMta();
            }
        }
    }

    @Override // com.jingdong.common.recommend.ui.RecommendContentLayout
    public void viewToTop() {
        super.viewToTop();
        spreadSlidingTab(true, false);
    }

    public void setHomeJsonData(final JDJSONObject jDJSONObject, final boolean z, final int i2) {
        this.mActivity.runOnUiThread(new Runnable() { // from class: com.jingdong.common.recommend.ui.homerecommend.HomeRecommendContentLayout.1
            {
                HomeRecommendContentLayout.this = this;
            }

            @Override // java.lang.Runnable
            public void run() {
                if (((RecommendContentLayout) HomeRecommendContentLayout.this).oneRCView instanceof HomeChildRecyclerView) {
                    try {
                        ((HomeChildRecyclerView) ((RecommendContentLayout) HomeRecommendContentLayout.this).oneRCView).setHomeJsonData(z, i2);
                        if (((RecommendContentLayout) HomeRecommendContentLayout.this).oneRCView.mRecommendProductManager != null) {
                            ((RecommendContentLayout) HomeRecommendContentLayout.this).oneRCView.mRecommendProductManager.setHomeJsonData(jDJSONObject, z);
                        }
                    } catch (Exception e2) {
                        ExceptionReporter.reportExceptionToBugly(e2);
                    }
                }
            }
        });
    }
}

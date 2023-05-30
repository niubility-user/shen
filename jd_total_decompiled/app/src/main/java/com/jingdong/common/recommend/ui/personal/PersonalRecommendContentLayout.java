package com.jingdong.common.recommend.ui.personal;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.recommend.R;
import com.jingdong.common.recommend.RecommendConstant;
import com.jingdong.common.recommend.RecommendExpoHelper;
import com.jingdong.common.recommend.RecommendJumpUtils;
import com.jingdong.common.recommend.RecommendMtaUtils;
import com.jingdong.common.recommend.RecommendUtils;
import com.jingdong.common.recommend.entity.RecommendHeaderData;
import com.jingdong.common.recommend.entity.RecommendHomeTabs;
import com.jingdong.common.recommend.entity.RecommendTab;
import com.jingdong.common.recommend.forlist.RecommendProductManager;
import com.jingdong.common.recommend.ui.PagerSlidingTabStrip;
import com.jingdong.common.recommend.ui.RecommendChildRecyclerView;
import com.jingdong.common.recommend.ui.RecommendContentLayout;
import com.jingdong.common.recommend.ui.RecommendHeaderViewHolder;
import com.jingdong.common.recommend.ui.TabItemViewInterface;
import com.jingdong.common.unification.statusbar.UnStatusBarTintUtil;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.jdsdk.utils.DPIUtil;
import com.jingdong.sdk.oklog.OKLog;
import com.jingdong.sdk.platform.business.personal.R2;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class PersonalRecommendContentLayout extends RecommendContentLayout {
    private int defaultTopSpace;
    private RecommendHeaderViewHolder headerViewHolder;
    boolean isUploadScrollY;
    public UploadHeightCallback uploadHeightCallback;
    private int viewWidth;

    /* loaded from: classes6.dex */
    class PersonalRecommendPageAdapter extends RecommendContentLayout.RecommendPageAdapter implements PagerSlidingTabStrip.CustomTabProvider {
        private int mTabItemWidth;

        PersonalRecommendPageAdapter() {
            super();
        }

        private void calculateTabItemWidth() {
            List<RecommendTab> list = this.mRecommendTabs;
            if (list == null || list.size() != 2) {
                return;
            }
            this.mTabItemWidth = R2.attr.alignContent;
        }

        @Override // com.jingdong.common.recommend.ui.PagerSlidingTabStrip.CustomTabProvider
        public View getCustomTabView(ViewGroup viewGroup, int i2) {
            if (i2 >= 0) {
                try {
                    if (i2 < ((RecommendContentLayout) PersonalRecommendContentLayout.this).mChildViews.size() && ((RecommendContentLayout) PersonalRecommendContentLayout.this).mChildViews.get(i2) != null && ((RecommendChildRecyclerView) ((RecommendContentLayout) PersonalRecommendContentLayout.this).mChildViews.get(i2)).getmRecommendTab() != null) {
                        if (((RecommendContentLayout) PersonalRecommendContentLayout.this).mChildViews.size() == 2) {
                            PersonalTabNewView personalTabNewView = new PersonalTabNewView(PersonalRecommendContentLayout.this.getContext(), i2 == 0);
                            personalTabNewView.setRecommendTab(((RecommendChildRecyclerView) ((RecommendContentLayout) PersonalRecommendContentLayout.this).mChildViews.get(i2)).getmRecommendTab());
                            personalTabNewView.setWH(this.mTabItemWidth, 80);
                            return personalTabNewView;
                        }
                        PersonalTabBView personalTabBView = new PersonalTabBView(PersonalRecommendContentLayout.this.getContext());
                        personalTabBView.setRecommendTab(((RecommendChildRecyclerView) ((RecommendContentLayout) PersonalRecommendContentLayout.this).mChildViews.get(i2)).getmRecommendTab());
                        personalTabBView.setHasSplitLine(i2 != ((RecommendContentLayout) PersonalRecommendContentLayout.this).mChildViews.size() - 1);
                        return personalTabBView;
                    }
                } catch (Exception e2) {
                    if (OKLog.D) {
                        e2.printStackTrace();
                    }
                }
            }
            return new View(PersonalRecommendContentLayout.this.getContext());
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.jingdong.common.recommend.ui.RecommendContentLayout.RecommendPageAdapter
        public void setRecommendTabs(List<RecommendTab> list) {
            super.setRecommendTabs(list);
            calculateTabItemWidth();
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

    /* loaded from: classes6.dex */
    public interface UploadHeightCallback {
        void uploadHeight();
    }

    public PersonalRecommendContentLayout(RecyclerView recyclerView, BaseActivity baseActivity) {
        super(recyclerView, baseActivity);
        this.isUploadScrollY = true;
        this.defaultTopSpace = -1;
        recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() { // from class: com.jingdong.common.recommend.ui.personal.PersonalRecommendContentLayout.1
            @Override // androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
            public boolean onInterceptTouchEvent(@NonNull RecyclerView recyclerView2, @NonNull MotionEvent motionEvent) {
                RecommendExpoHelper currentExpoHelper;
                if (motionEvent == null || motionEvent.getAction() != 0 || (currentExpoHelper = PersonalRecommendContentLayout.this.getCurrentExpoHelper()) == null) {
                    return false;
                }
                currentExpoHelper.onViewTouch(motionEvent);
                return false;
            }

            @Override // androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
            public void onRequestDisallowInterceptTouchEvent(boolean z) {
            }

            @Override // androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
            public void onTouchEvent(@NonNull RecyclerView recyclerView2, @NonNull MotionEvent motionEvent) {
            }
        });
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() { // from class: com.jingdong.common.recommend.ui.personal.PersonalRecommendContentLayout.2
            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView2, int i2) {
                super.onScrollStateChanged(recyclerView2, i2);
                RecommendExpoHelper currentExpoHelper = PersonalRecommendContentLayout.this.getCurrentExpoHelper();
                if (currentExpoHelper != null) {
                    currentExpoHelper.onViewScrollStateChange();
                }
            }

            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrolled(@NonNull RecyclerView recyclerView2, int i2, int i3) {
                super.onScrolled(recyclerView2, i2, i3);
                RecommendExpoHelper currentExpoHelper = PersonalRecommendContentLayout.this.getCurrentExpoHelper();
                if (currentExpoHelper != null) {
                    currentExpoHelper.onViewScroll();
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.common.recommend.ui.RecommendContentLayout
    public void createOneRecyclerView() {
        super.createOneRecyclerView();
        this.oneRCView.needRealExpoHelper();
    }

    @Override // com.jingdong.common.recommend.ui.RecommendContentLayout
    protected void createSlidingTab() {
        PersonalTabSliding personalTabSliding = new PersonalTabSliding(getContext());
        this.mSlidingTabStrip = personalTabSliding;
        personalTabSliding.setTabBackground(0);
        this.mSlidingTabStrip.setViewPagerSmoothScroll(false);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.common.recommend.ui.RecommendContentLayout
    public void createTabRecyclerView(RecommendChildRecyclerView recommendChildRecyclerView) {
        super.createTabRecyclerView(recommendChildRecyclerView);
        recommendChildRecyclerView.needRealExpoHelper();
    }

    @Override // com.jingdong.common.recommend.ui.RecommendContentLayout
    protected void createViewPagerAdapter() {
        this.pageAdapter = new PersonalRecommendPageAdapter();
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

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.common.recommend.ui.RecommendContentLayout
    public int getCorrectFromType(RecommendTab recommendTab) {
        if (recommendTab == null || recommendTab.pos <= 0) {
            return super.getCorrectFromType(recommendTab);
        }
        return 18;
    }

    public RecommendExpoHelper getCurrentExpoHelper() {
        RecommendChildRecyclerView recommendChildRecyclerView = this.mCurrentView;
        if (recommendChildRecyclerView != null) {
            return recommendChildRecyclerView.getChildExpoHelper();
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.common.recommend.ui.RecommendContentLayout
    public void getRecommendHeaderData(RecommendHeaderData recommendHeaderData) {
        super.getRecommendHeaderData(recommendHeaderData);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.common.recommend.ui.RecommendContentLayout
    public void getRecommendSuccess(RecommendHomeTabs recommendHomeTabs) {
        super.getRecommendSuccess(recommendHomeTabs);
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
        int dip2px = i3 + com.jingdong.sdk.utils.DPIUtil.dip2px(49.0f);
        this.defaultTopSpace = dip2px;
        return dip2px;
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
        this.oneRCView.setAutoPlayEnable(true);
        RecommendProductManager recommendProductManager = this.oneRCView.mRecommendProductManager;
        if (recommendProductManager != null) {
            recommendProductManager.setEnableCache(2);
        }
    }

    @Override // com.jingdong.common.recommend.ui.RecommendContentLayout
    protected void initOtherRecyclerView(RecommendChildRecyclerView recommendChildRecyclerView) {
        if (recommendChildRecyclerView != null) {
            recommendChildRecyclerView.isShowEmptyView.set(true);
            recommendChildRecyclerView.setIsEnableAutoLoad(true);
        }
    }

    @Override // com.jingdong.common.recommend.ui.RecommendContentLayout
    public void loadRecommendData() {
        RecommendChildRecyclerView recommendChildRecyclerView = this.mCurrentView;
        if (recommendChildRecyclerView == this.oneRCView && !recommendChildRecyclerView.hasRecommendData()) {
            this.mCurrentView.mRecommendProductManager.setSourceExt(RecommendUtils.isBAppType() ? RecommendConstant.PERSONAL_B_SOURCE : "");
        }
        super.loadRecommendData();
    }

    @Override // com.jingdong.common.recommend.ui.RecommendContentLayout
    public void onBindRecommendHeaderViewHolder(RecyclerView.ViewHolder viewHolder) {
        if (viewHolder instanceof RecommendHeaderViewHolder) {
            RecommendHeaderViewHolder recommendHeaderViewHolder = (RecommendHeaderViewHolder) viewHolder;
            RecommendHeaderData recommendHeaderData = this.headerData;
            recommendHeaderViewHolder.onBindRecommendHeaderViewHolder(recommendHeaderData == null ? "" : recommendHeaderData.headerTitleUrl, recommendHeaderData == null ? "" : recommendHeaderData.darkHeaderTitleUrl, recommendHeaderData != null ? recommendHeaderData.publicTest : "", this.recommendConfig);
            recommendHeaderViewHolder.setOnTestClickListener(new View.OnClickListener() { // from class: com.jingdong.common.recommend.ui.personal.PersonalRecommendContentLayout.3
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (((RecommendContentLayout) PersonalRecommendContentLayout.this).headerData != null) {
                        RecommendJumpUtils.gotoMWithUrl(((RecommendContentLayout) PersonalRecommendContentLayout.this).mActivity.getThisActivity(), null, ((RecommendContentLayout) PersonalRecommendContentLayout.this).headerData.publicTestTopUrl);
                        RecommendMtaUtils.jumpPublicTestClickMta(((RecommendContentLayout) PersonalRecommendContentLayout.this).mActivity.getThisActivity(), ((RecommendContentLayout) PersonalRecommendContentLayout.this).fromType, ((RecommendContentLayout) PersonalRecommendContentLayout.this).headerData.publicTest);
                    }
                }
            });
        }
    }

    @Override // com.jingdong.common.recommend.ui.RecommendContentLayout
    public RecyclerView.ViewHolder onCreateRecommendHeader(ViewGroup viewGroup) {
        View inflate;
        if (viewGroup != null) {
            inflate = LayoutInflater.from(getContext()).inflate(R.layout.recommend_head, viewGroup, false);
        } else {
            inflate = LayoutInflater.from(getContext()).inflate(R.layout.recommend_head, (ViewGroup) null);
        }
        RecommendHeaderViewHolder recommendHeaderViewHolder = new RecommendHeaderViewHolder(inflate, this.fromType);
        this.headerViewHolder = recommendHeaderViewHolder;
        return recommendHeaderViewHolder;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.common.recommend.ui.RecommendContentLayout
    public void onDeepDarkChange() {
        super.onDeepDarkChange();
        RecommendHeaderViewHolder recommendHeaderViewHolder = this.headerViewHolder;
        if (recommendHeaderViewHolder != null) {
            RecommendHeaderData recommendHeaderData = this.headerData;
            recommendHeaderViewHolder.onBindRecommendHeaderViewHolder(recommendHeaderData == null ? "" : recommendHeaderData.headerTitleUrl, recommendHeaderData == null ? "" : recommendHeaderData.darkHeaderTitleUrl, recommendHeaderData != null ? recommendHeaderData.publicTest : "", this.recommendConfig);
        }
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        super.onLayout(z, i2, i3, i4, i5);
        if (!this.isUploadScrollY || i3 == 0) {
            return;
        }
        UploadHeightCallback uploadHeightCallback = this.uploadHeightCallback;
        if (uploadHeightCallback != null) {
            uploadHeightCallback.uploadHeight();
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("height", (this.parentScrollY + getTop()) + "");
        } catch (JSONException e2) {
            if (OKLog.D) {
                e2.printStackTrace();
            }
        }
        JDMtaUtils.sendExposureDataWithExt(getContext(), "MyJD_RecomHeight", "", RecommendMtaUtils.MyJD_PageId, "JDHomeFragment", "", jSONObject.toString(), "", "", "", null);
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
                if (pagerSlidingTabStrip instanceof PersonalTabSliding) {
                    ((PersonalTabSliding) pagerSlidingTabStrip).onWidthSizeChange();
                }
            }
        }
        super.onMeasure(i2, i3);
    }

    @Override // com.jingdong.common.recommend.ui.RecommendContentLayout
    public void resetContent() {
        this.isUploadScrollY = true;
        if (hasRecommendData()) {
            this.parentScrollY = 0;
        }
        super.resetContent();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.common.recommend.ui.RecommendContentLayout
    public void resetRecyclerView(RecommendChildRecyclerView recommendChildRecyclerView) {
        super.resetRecyclerView(recommendChildRecyclerView);
        recommendChildRecyclerView.setCurrentPlan("B");
    }

    public void setOnGetRecommendDataListener(RecommendChildRecyclerView.onRecommendContentListener onrecommendcontentlistener) {
        RecommendChildRecyclerView recommendChildRecyclerView = this.oneRCView;
        if (recommendChildRecyclerView != null) {
            recommendChildRecyclerView.setOnRecommendContentListener(onrecommendcontentlistener);
        }
    }

    public void setOnUploadHeightCallback(UploadHeightCallback uploadHeightCallback) {
        this.uploadHeightCallback = uploadHeightCallback;
    }

    @Override // com.jingdong.common.recommend.ui.RecommendContentLayout, com.jingdong.common.recommend.ScrollDispatchHelper.ScrollDispatchChild
    public void setTabSpreadState(boolean z) {
        PagerSlidingTabStrip pagerSlidingTabStrip = this.mSlidingTabStrip;
        if (pagerSlidingTabStrip instanceof PersonalTabSliding) {
            ((PersonalTabSliding) pagerSlidingTabStrip).setSpread(z);
        }
    }

    @Override // com.jingdong.common.recommend.ui.RecommendContentLayout, com.jingdong.common.recommend.ScrollDispatchHelper.ScrollDispatchChild
    public void setTopSpace(int i2) {
        this.defaultTopSpace = i2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.common.recommend.ui.RecommendContentLayout
    public void settingSlidingTab(RecommendHomeTabs recommendHomeTabs) {
        super.settingSlidingTab(recommendHomeTabs);
        PagerSlidingTabStrip pagerSlidingTabStrip = this.mSlidingTabStrip;
        if (pagerSlidingTabStrip instanceof PersonalTabSliding) {
            ((PersonalTabSliding) pagerSlidingTabStrip).setHomeTab(recommendHomeTabs);
        }
        if (recommendHomeTabs != null) {
            try {
                ArrayList<RecommendTab> arrayList = recommendHomeTabs.recommendTabList;
                if (arrayList == null || arrayList.isEmpty()) {
                    return;
                }
                StringBuilder sb = new StringBuilder();
                JSONArray jSONArray = new JSONArray();
                int i2 = 0;
                while (i2 < recommendHomeTabs.recommendTabList.size()) {
                    int i3 = recommendHomeTabs.recommendTabList.get(i2) != null ? recommendHomeTabs.recommendTabList.get(i2).tabId : -100;
                    sb.append(i3);
                    sb.append("#");
                    int i4 = i2 + 1;
                    sb.append(i4);
                    sb.append(CartConstant.KEY_YB_INFO_LINK);
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("geneid", i3 + "");
                    jSONObject.put("geneindex", i2 + "");
                    jSONArray.put(jSONObject);
                    i2 = i4;
                }
                sb.deleteCharAt(sb.length() - 1);
                JDMtaUtils.sendExposureDataWithExt(getContext(), "MyJD_GeneTagExpo", sb.toString(), RecommendMtaUtils.MyJD_PageId, RecommendMtaUtils.MyJD_Page_Name, "", jSONArray.toString(), null);
            } catch (Exception e2) {
                if (OKLog.D) {
                    e2.printStackTrace();
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.common.recommend.ui.RecommendContentLayout
    public void uploadClickMta(RecommendTab recommendTab, int i2) {
        if (recommendTab != null) {
            if (this.mIsTabClick) {
                BaseActivity baseActivity = this.mActivity;
                if (baseActivity != null) {
                    RecommendMtaUtils.GeneTabClickMta(baseActivity, recommendTab.tabId + CartConstant.KEY_YB_INFO_LINK + i2);
                }
            } else {
                JDMtaUtils.onClickWithPageId(getContext(), "MyJD_GeneTagSlide", RecommendMtaUtils.MyJD_Page_Name, recommendTab.tabId + CartConstant.KEY_YB_INFO_LINK + i2, RecommendMtaUtils.MyJD_PageId);
            }
        }
        super.uploadClickMta(recommendTab, i2);
    }

    @Override // com.jingdong.common.recommend.ui.RecommendContentLayout
    public void uploadExpoData() {
        RecommendChildRecyclerView recommendChildRecyclerView = this.mCurrentView;
        if (recommendChildRecyclerView != null) {
            recommendChildRecyclerView.sendExposureMta();
        }
    }

    @Override // com.jingdong.common.recommend.ui.RecommendContentLayout
    public void viewToTop() {
        super.viewToTop();
    }
}

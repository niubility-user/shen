package com.jingdong.common.recommend.ui;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.LinearLayout;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.frame.IDestroyListener;
import com.jingdong.common.recommend.R;
import com.jingdong.common.recommend.RecommendConfig;
import com.jingdong.common.recommend.RecommendJumpUtils;
import com.jingdong.common.recommend.RecommendMtaUtils;
import com.jingdong.common.recommend.ScrollDispatchHelper;
import com.jingdong.common.recommend.entity.RecommendHeaderData;
import com.jingdong.common.recommend.entity.RecommendHomeTabs;
import com.jingdong.common.recommend.entity.RecommendTab;
import com.jingdong.common.recommend.ui.PagerSlidingTabStrip;
import com.jingdong.common.recommend.ui.RecommendChildRecyclerView;
import com.jingdong.common.recommend.ui.homerecommend.HomeTabInterface;
import com.jingdong.common.utils.DeepDarkChangeManager;
import com.jingdong.common.utils.text.OnTextScaleModeChangeListener;
import com.jingdong.common.utils.text.TextScaleModeHelper;
import com.jingdong.corelib.utils.Log;
import com.jingdong.sdk.eldermode.util.JDElderModeUtils;
import com.jingdong.sdk.eldermode.util.OnElderModeChangeListener;
import com.jingdong.sdk.oklog.OKLog;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes6.dex */
public class RecommendContentLayout extends LinearLayout implements ScrollDispatchHelper.ScrollDispatchChild, OnTextScaleModeChangeListener, OnElderModeChangeListener {
    protected RecyclerView.OnScrollListener childScrollListener;
    protected int fromType;
    protected RecommendHeaderData headerData;
    protected BaseActivity mActivity;
    private Map<String, RecommendChildRecyclerView> mCatchViews;
    protected ArrayList<RecommendChildRecyclerView> mChildViews;
    protected RecommendChildRecyclerView mCurrentView;
    private RecommendFooterView mFooterView;
    protected boolean mIsTabClick;
    protected int mMaxVisitPosition;
    public RecyclerView.OnScrollListener mOnScrollListener;
    protected RecyclerView mParentRecyclerView;
    protected List<RecommendTab> mRecommendTabList;
    protected PagerSlidingTabStrip mSlidingTabStrip;
    protected ViewPager mViewPager;
    private OnRecommendChangeListener onPageChangeListener;
    private RecommendChildRecyclerView.OnRequestResultListener onRequestResultListener;
    protected RecommendChildRecyclerView oneRCView;
    protected RecommendPageAdapter pageAdapter;
    protected int parentScrollY;
    protected RecommendConfig recommendConfig;
    int[] recyclerViewPadding;
    private int topSpace;

    /* loaded from: classes6.dex */
    public interface OnRecommendChangeListener extends ViewPager.OnPageChangeListener {
        void onPageSelected(RecommendChildRecyclerView recommendChildRecyclerView);
    }

    /* loaded from: classes6.dex */
    public class RecommendPageAdapter extends PagerAdapter {
        protected List<RecommendTab> mRecommendTabs;

        public RecommendPageAdapter() {
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public void destroyItem(ViewGroup viewGroup, int i2, Object obj) {
            if (obj instanceof View) {
                viewGroup.removeView((View) obj);
            }
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public int getCount() {
            ArrayList<RecommendChildRecyclerView> arrayList = RecommendContentLayout.this.mChildViews;
            if (arrayList != null) {
                return arrayList.size();
            }
            return 0;
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public int getItemPosition(Object obj) {
            return -2;
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public CharSequence getPageTitle(int i2) {
            return (i2 < 0 || i2 >= RecommendContentLayout.this.mChildViews.size() || RecommendContentLayout.this.mChildViews.get(i2) == null || RecommendContentLayout.this.mChildViews.get(i2).getmRecommendTab() == null) ? "" : RecommendContentLayout.this.mChildViews.get(i2).getmRecommendTab().title;
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public Object instantiateItem(ViewGroup viewGroup, int i2) {
            viewGroup.addView(RecommendContentLayout.this.mChildViews.get(i2));
            return RecommendContentLayout.this.mChildViews.get(i2);
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public void setRecommendTabs(List<RecommendTab> list) {
            this.mRecommendTabs = list;
        }
    }

    public RecommendContentLayout(Context context) {
        super(context);
        this.mChildViews = new ArrayList<>();
        this.mCatchViews = new ConcurrentHashMap();
        this.mRecommendTabList = new ArrayList();
        this.recyclerViewPadding = new int[4];
        this.topSpace = 0;
    }

    private void initViews() {
        if (getLayoutParams() == null) {
            setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
        }
        setOrientation(1);
        RecommendFooterView recommendFooterView = new RecommendFooterView(getContext());
        this.mFooterView = recommendFooterView;
        recommendFooterView.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
        this.mFooterView.setOnErrorLayoutClickLinstener(new View.OnClickListener() { // from class: com.jingdong.common.recommend.ui.RecommendContentLayout.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                RecommendContentLayout.this.loadRecommendData();
            }
        });
        ViewPager viewPager = new ViewPager(getContext());
        this.mViewPager = viewPager;
        viewPager.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
        createViewPagerAdapter();
        this.mViewPager.setAdapter(this.pageAdapter);
        this.mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() { // from class: com.jingdong.common.recommend.ui.RecommendContentLayout.4
            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageScrollStateChanged(int i2) {
            }

            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageScrolled(int i2, float f2, int i3) {
            }

            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageSelected(int i2) {
                int size = RecommendContentLayout.this.mChildViews.size();
                if (size < 1) {
                    return;
                }
                RecommendContentLayout.this.onPageChange(i2);
                if (i2 > size) {
                    i2 = size;
                }
                RecommendContentLayout recommendContentLayout = RecommendContentLayout.this;
                if (recommendContentLayout.mCurrentView != recommendContentLayout.mChildViews.get(i2)) {
                    RecommendChildRecyclerView recommendChildRecyclerView = RecommendContentLayout.this.mCurrentView;
                    if (recommendChildRecyclerView != null) {
                        recommendChildRecyclerView.onSelectBefore();
                    }
                    RecommendContentLayout recommendContentLayout2 = RecommendContentLayout.this;
                    recommendContentLayout2.mCurrentView = recommendContentLayout2.mChildViews.get(i2);
                    RecommendChildRecyclerView recommendChildRecyclerView2 = RecommendContentLayout.this.mCurrentView;
                    if (recommendChildRecyclerView2 != null) {
                        recommendChildRecyclerView2.onPageSelected();
                    }
                }
                if (RecommendContentLayout.this.onPageChangeListener != null) {
                    RecommendContentLayout.this.onPageChangeListener.onPageSelected(i2);
                    RecommendContentLayout recommendContentLayout3 = RecommendContentLayout.this;
                    if (recommendContentLayout3.mCurrentView != null) {
                        recommendContentLayout3.onPageChangeListener.onPageSelected(RecommendContentLayout.this.mCurrentView);
                    }
                }
                RecommendContentLayout recommendContentLayout4 = RecommendContentLayout.this;
                if (i2 > recommendContentLayout4.mMaxVisitPosition) {
                    recommendContentLayout4.mMaxVisitPosition = i2;
                }
                RecommendChildRecyclerView recommendChildRecyclerView3 = recommendContentLayout4.mCurrentView;
                if (recommendChildRecyclerView3 == null || recommendChildRecyclerView3.getmRecommendTab() == null) {
                    return;
                }
                RecommendContentLayout recommendContentLayout5 = RecommendContentLayout.this;
                recommendContentLayout5.uploadClickMta(recommendContentLayout5.mCurrentView.getmRecommendTab(), i2);
            }
        });
        this.childScrollListener = new RecyclerView.OnScrollListener() { // from class: com.jingdong.common.recommend.ui.RecommendContentLayout.5
            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrollStateChanged(RecyclerView recyclerView, int i2) {
                super.onScrollStateChanged(recyclerView, i2);
                RecyclerView.OnScrollListener onScrollListener = RecommendContentLayout.this.mOnScrollListener;
                if (onScrollListener != null) {
                    onScrollListener.onScrollStateChanged(recyclerView, i2);
                }
            }

            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrolled(RecyclerView recyclerView, int i2, int i3) {
                super.onScrolled(recyclerView, i2, i3);
                RecyclerView.OnScrollListener onScrollListener = RecommendContentLayout.this.mOnScrollListener;
                if (onScrollListener != null) {
                    onScrollListener.onScrolled(recyclerView, i2, i3);
                }
            }
        };
    }

    private void resetViewLayout() {
        RecommendChildRecyclerView recommendChildRecyclerView;
        removeAllViews();
        this.mChildViews.clear();
        viewPagerNotify();
        resetRecyclerView(this.oneRCView);
        if (getLayoutParams() == null) {
            setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
        } else {
            getLayoutParams().height = getContentHeight();
        }
        if (this.mCatchViews.size() > this.mRecommendTabList.size()) {
            this.mCatchViews.clear();
        }
        if (this.mRecommendTabList.size() > 1) {
            this.oneRCView.setRecommendTab(this.mRecommendTabList.get(0));
            this.mChildViews.add(this.oneRCView);
            viewPagerNotify();
            for (int i2 = 1; i2 < this.mRecommendTabList.size(); i2++) {
                RecommendTab recommendTab = this.mRecommendTabList.get(i2);
                if (recommendTab == null) {
                    recommendChildRecyclerView = new RecommendChildRecyclerView(getParentRecycleView(), this.mActivity, this.fromType);
                    createTabRecyclerView(recommendChildRecyclerView);
                    this.mCatchViews.put("emptyView", recommendChildRecyclerView);
                } else {
                    String valueOf = String.valueOf(recommendTab.tabId);
                    RecommendChildRecyclerView recommendChildRecyclerView2 = this.mCatchViews.get(valueOf);
                    if (recommendChildRecyclerView2 == null || this.mChildViews.contains(recommendChildRecyclerView2)) {
                        recommendChildRecyclerView2 = new RecommendChildRecyclerView(getParentRecycleView(), this.mActivity, getCorrectFromType(recommendTab));
                        createTabRecyclerView(recommendChildRecyclerView2);
                        this.mCatchViews.put(valueOf, recommendChildRecyclerView2);
                    }
                    recommendChildRecyclerView2.setRecommendTab(recommendTab);
                    resetRecyclerView(recommendChildRecyclerView2);
                    initOtherRecyclerView(recommendChildRecyclerView2);
                    this.mChildViews.add(recommendChildRecyclerView2);
                    recommendChildRecyclerView = recommendChildRecyclerView2;
                }
                recommendChildRecyclerView.setRecommendConfig(this.recommendConfig);
            }
            PagerSlidingTabStrip pagerSlidingTabStrip = this.mSlidingTabStrip;
            if (pagerSlidingTabStrip != null) {
                if (pagerSlidingTabStrip.getLayoutParams() == null) {
                    this.mSlidingTabStrip.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
                } else {
                    this.mSlidingTabStrip.getLayoutParams().height = -2;
                }
                addView(this.mSlidingTabStrip);
            }
            addView(this.mViewPager);
            this.pageAdapter.setRecommendTabs(this.mRecommendTabList);
            viewPagerNotify();
            return;
        }
        addView(this.mViewPager);
        this.mChildViews.add(this.oneRCView);
        viewPagerNotify();
    }

    private void viewPagerNotify() {
        RecommendPageAdapter recommendPageAdapter = this.pageAdapter;
        if (recommendPageAdapter != null) {
            recommendPageAdapter.notifyDataSetChanged();
        }
    }

    @Override // com.jingdong.common.recommend.ScrollDispatchHelper.ScrollDispatchChild
    public void allChildToTop() {
        for (int i2 = 0; i2 < this.mChildViews.size(); i2++) {
            try {
                if (this.mChildViews.get(i2) != null) {
                    this.mChildViews.get(i2).scrollToTop();
                }
            } catch (Exception e2) {
                if (Log.D) {
                    e2.printStackTrace();
                    return;
                }
                return;
            }
        }
    }

    @Override // com.jingdong.common.recommend.ScrollDispatchHelper.ScrollDispatchChild
    public boolean canChildScrollVertically(int i2) {
        RecommendChildRecyclerView recommendChildRecyclerView;
        if (indexOfChild(this.mViewPager) == -1 || (recommendChildRecyclerView = this.mCurrentView) == null) {
            return false;
        }
        return recommendChildRecyclerView.canScrollVertically(i2);
    }

    @Override // com.jingdong.common.recommend.ScrollDispatchHelper.ScrollDispatchChild
    public void childScrollBy(int i2, int i3) {
        try {
            RecommendChildRecyclerView recommendChildRecyclerView = this.mCurrentView;
            if (recommendChildRecyclerView != null) {
                recommendChildRecyclerView.scrollBy(i2, i3);
            }
        } catch (Exception e2) {
            if (OKLog.D) {
                throw e2;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void createOneRecyclerView() {
        RecommendChildRecyclerView recommendChildRecyclerView = new RecommendChildRecyclerView(getParentRecycleView(), this.mActivity, this.fromType);
        this.oneRCView = recommendChildRecyclerView;
        recommendChildRecyclerView.setRecommendConfig(this.recommendConfig);
    }

    protected void createSlidingTab() {
        PagerSlidingTabStrip pagerSlidingTabStrip = new PagerSlidingTabStrip(getContext());
        this.mSlidingTabStrip = pagerSlidingTabStrip;
        pagerSlidingTabStrip.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void createTabRecyclerView(RecommendChildRecyclerView recommendChildRecyclerView) {
    }

    protected void createViewPagerAdapter() {
        this.pageAdapter = new RecommendPageAdapter();
    }

    public void fling(int i2, int i3) {
        RecommendChildRecyclerView recommendChildRecyclerView = this.mCurrentView;
        if (recommendChildRecyclerView != null) {
            recommendChildRecyclerView.fling(i2, i3);
        }
    }

    @Override // com.jingdong.common.recommend.ScrollDispatchHelper.ScrollDispatchChild
    public ViewParent getChildParent() {
        return getParent();
    }

    @Override // com.jingdong.common.recommend.ScrollDispatchHelper.ScrollDispatchChild
    public int getChildTop() {
        return getTop();
    }

    protected int getContentHeight() {
        RecyclerView recyclerView = this.mParentRecyclerView;
        if (recyclerView != null) {
            return recyclerView.getHeight();
        }
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int getCorrectFromType(RecommendTab recommendTab) {
        return this.fromType;
    }

    public RecyclerView getParentRecycleView() {
        return this.mParentRecyclerView;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void getRecommendHeaderData(RecommendHeaderData recommendHeaderData) {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void getRecommendSuccess(RecommendHomeTabs recommendHomeTabs) {
        ArrayList<RecommendTab> arrayList;
        if (recommendHomeTabs == null) {
            this.mSlidingTabStrip = null;
        } else if (this.mSlidingTabStrip == null) {
            createSlidingTab();
            initSlidingTab();
        }
        PagerSlidingTabStrip pagerSlidingTabStrip = this.mSlidingTabStrip;
        if (pagerSlidingTabStrip != null) {
            pagerSlidingTabStrip.setRecommendConfig(this.recommendConfig);
        }
        settingSlidingTab(recommendHomeTabs);
        this.mRecommendTabList.clear();
        if (recommendHomeTabs != null && (arrayList = recommendHomeTabs.recommendTabList) != null) {
            this.mRecommendTabList.addAll(arrayList);
        }
        resetViewLayout();
        RecommendChildRecyclerView.OnRequestResultListener onRequestResultListener = this.onRequestResultListener;
        if (onRequestResultListener != null) {
            onRequestResultListener.onSuccess(recommendHomeTabs);
        }
    }

    @Override // com.jingdong.common.recommend.ScrollDispatchHelper.ScrollDispatchChild
    public int getTopSpace() {
        return this.topSpace;
    }

    public boolean hasRecommendData() {
        RecommendChildRecyclerView recommendChildRecyclerView = this.mCurrentView;
        if (recommendChildRecyclerView != null) {
            return recommendChildRecyclerView.hasRecommendData();
        }
        return false;
    }

    public boolean hasTab() {
        PagerSlidingTabStrip pagerSlidingTabStrip = this.mSlidingTabStrip;
        return pagerSlidingTabStrip != null && indexOfChild(pagerSlidingTabStrip) > -1;
    }

    protected void initOneRecyclerView() {
    }

    protected void initOtherRecyclerView(RecommendChildRecyclerView recommendChildRecyclerView) {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void initSlidingTab() {
        this.mSlidingTabStrip.setTabPaddingLeftRight(0);
        this.mSlidingTabStrip.setOnTabReselectedListener(new PagerSlidingTabStrip.OnTabReselectedListener() { // from class: com.jingdong.common.recommend.ui.RecommendContentLayout.8
            @Override // com.jingdong.common.recommend.ui.PagerSlidingTabStrip.OnTabReselectedListener
            public void onTabReselected(int i2) {
                RecommendContentLayout.this.mIsTabClick = false;
            }

            @Override // com.jingdong.common.recommend.ui.PagerSlidingTabStrip.OnTabReselectedListener
            public void onTabSelected(int i2) {
                RecommendContentLayout.this.mIsTabClick = true;
            }
        });
        this.mSlidingTabStrip.setIndicatorHeight(0);
        this.mSlidingTabStrip.setViewPager(this.mViewPager);
    }

    public boolean isTop() {
        RecommendChildRecyclerView recommendChildRecyclerView;
        return indexOfChild(this.mViewPager) == -1 || ((recommendChildRecyclerView = this.mCurrentView) != null && recommendChildRecyclerView.isTop());
    }

    public void loadRecommendData() {
        if (this.mCurrentView == null) {
            resetContent();
        }
        this.mFooterView.setFooterState(0);
        RecommendChildRecyclerView recommendChildRecyclerView = this.mCurrentView;
        if (recommendChildRecyclerView != null) {
            recommendChildRecyclerView.onPageSelected();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onActivityDestroy() {
    }

    public void onBindRecommendHeaderViewHolder(RecyclerView.ViewHolder viewHolder) {
        if (viewHolder instanceof RecommendHeaderViewHolder) {
            RecommendHeaderData recommendHeaderData = this.headerData;
            if (recommendHeaderData == null) {
                viewHolder.itemView.setVisibility(8);
                return;
            }
            RecommendHeaderViewHolder recommendHeaderViewHolder = (RecommendHeaderViewHolder) viewHolder;
            recommendHeaderViewHolder.onBindRecommendHeaderViewHolder(recommendHeaderData.headerTitleUrl, recommendHeaderData.darkHeaderTitleUrl, recommendHeaderData.publicTest, this.recommendConfig);
            recommendHeaderViewHolder.setOnTestClickListener(new View.OnClickListener() { // from class: com.jingdong.common.recommend.ui.RecommendContentLayout.7
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    RecommendJumpUtils.gotoMWithUrl(RecommendContentLayout.this.mActivity.getThisActivity(), null, RecommendContentLayout.this.headerData.publicTestTopUrl);
                    Activity thisActivity = RecommendContentLayout.this.mActivity.getThisActivity();
                    RecommendContentLayout recommendContentLayout = RecommendContentLayout.this;
                    RecommendMtaUtils.jumpPublicTestClickMta(thisActivity, recommendContentLayout.fromType, recommendContentLayout.headerData.publicTest);
                }
            });
        }
    }

    public RecyclerView.ViewHolder onCreateRecommendHeader(ViewGroup viewGroup) {
        View inflate;
        if (viewGroup != null) {
            inflate = LayoutInflater.from(getContext()).inflate(R.layout.recommend_head, viewGroup, false);
        } else {
            inflate = LayoutInflater.from(getContext()).inflate(R.layout.recommend_head, (ViewGroup) null);
        }
        return new RecommendHeaderViewHolder(inflate, this.fromType);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onDeepDarkChange() {
    }

    @Override // com.jingdong.sdk.eldermode.util.OnElderModeChangeListener
    public void onElderModeChanged(int i2) {
        for (int i3 = 0; i3 < this.mChildViews.size(); i3++) {
            RecommendChildRecyclerView recommendChildRecyclerView = this.mChildViews.get(i3);
            if (recommendChildRecyclerView != null && recommendChildRecyclerView.getAdapter() != null) {
                recommendChildRecyclerView.getAdapter().notifyDataSetChanged();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onPageChange(int i2) {
    }

    @Override // com.jingdong.common.recommend.ScrollDispatchHelper.ScrollDispatchChild
    public void onParentScroll(int i2) {
        this.parentScrollY += i2;
        RecommendChildRecyclerView recommendChildRecyclerView = this.mCurrentView;
        if (recommendChildRecyclerView != null) {
            recommendChildRecyclerView.onParentScroll(i2);
        }
    }

    public void onScroll(int i2) {
        RecommendChildRecyclerView recommendChildRecyclerView = this.mCurrentView;
        if (recommendChildRecyclerView != null) {
            recommendChildRecyclerView.scrollBy(0, i2);
        }
    }

    public void onScrollChanged(int i2) {
        RecommendChildRecyclerView recommendChildRecyclerView = this.mCurrentView;
        if (recommendChildRecyclerView != null) {
            recommendChildRecyclerView.onScrollChanged(i2);
        }
    }

    @Override // com.jingdong.common.recommend.ScrollDispatchHelper.ScrollDispatchChild
    public void onSelfScroll(int i2) {
        RecommendChildRecyclerView recommendChildRecyclerView = this.mCurrentView;
        if (recommendChildRecyclerView != null) {
            recommendChildRecyclerView.onSelfScroll(i2);
        }
    }

    @Override // com.jingdong.common.utils.text.OnTextScaleModeChangeListener
    public void onTextScaleModeChanged() {
        PagerSlidingTabStrip pagerSlidingTabStrip = this.mSlidingTabStrip;
        if (pagerSlidingTabStrip instanceof HomeTabInterface) {
            ((HomeTabInterface) pagerSlidingTabStrip).onTextScaleModeChanged();
        }
        for (int i2 = 0; i2 < this.mChildViews.size(); i2++) {
            RecommendChildRecyclerView recommendChildRecyclerView = this.mChildViews.get(i2);
            if (recommendChildRecyclerView != null && recommendChildRecyclerView.getAdapter() != null) {
                recommendChildRecyclerView.getAdapter().notifyDataSetChanged();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void release() {
        this.mMaxVisitPosition = 0;
        this.mRecommendTabList.clear();
        this.headerData = null;
        ViewPager viewPager = this.mViewPager;
        if (viewPager != null) {
            viewPager.setCurrentItem(0);
        }
        PagerSlidingTabStrip pagerSlidingTabStrip = this.mSlidingTabStrip;
        if (pagerSlidingTabStrip != null) {
            pagerSlidingTabStrip.releaseView();
        }
        RecommendChildRecyclerView recommendChildRecyclerView = this.oneRCView;
        if (recommendChildRecyclerView != null) {
            recommendChildRecyclerView.removeOnScrollListener(this.childScrollListener);
            this.oneRCView.viewReset();
        }
        for (int i2 = 0; i2 < this.mChildViews.size(); i2++) {
            if (this.mChildViews.get(i2) != null) {
                this.mChildViews.get(i2).removeOnScrollListener(this.childScrollListener);
                this.mChildViews.get(i2).viewReset();
            }
        }
        this.mChildViews.clear();
        viewPagerNotify();
        this.mCurrentView = null;
    }

    public void resetContent() {
        release();
        resetView();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void resetRecyclerView(RecommendChildRecyclerView recommendChildRecyclerView) {
        if (recommendChildRecyclerView != null) {
            recommendChildRecyclerView.addOnScrollListener(this.childScrollListener);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void resetView() {
        removeAllViews();
        if (getLayoutParams() == null) {
            setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
        } else {
            getLayoutParams().height = -2;
        }
        this.mFooterView.setFooterState(0);
        addView(this.mFooterView);
        if (this.oneRCView == null) {
            createOneRecyclerView();
            initOneRecyclerView();
        }
        this.oneRCView.setOnRequestResultListener(new RecommendChildRecyclerView.OnNewRequestResultListener() { // from class: com.jingdong.common.recommend.ui.RecommendContentLayout.6
            @Override // com.jingdong.common.recommend.ui.RecommendChildRecyclerView.OnNewRequestResultListener
            public void onFailed(int i2) {
                if (RecommendContentLayout.this.onRequestResultListener != null && i2 == 1) {
                    RecommendContentLayout.this.onRequestResultListener.onFailed();
                }
                if (RecommendContentLayout.this.mFooterView.getParent() != null) {
                    RecommendContentLayout.this.mFooterView.setFooterState(1);
                }
            }

            @Override // com.jingdong.common.recommend.ui.RecommendChildRecyclerView.OnNewRequestResultListener
            public void onSuccess(int i2, RecommendHomeTabs recommendHomeTabs, RecommendHeaderData recommendHeaderData) {
                if (i2 == 1) {
                    RecommendContentLayout recommendContentLayout = RecommendContentLayout.this;
                    recommendContentLayout.headerData = recommendHeaderData;
                    recommendContentLayout.getRecommendHeaderData(recommendHeaderData);
                    RecommendContentLayout.this.getRecommendSuccess(recommendHomeTabs);
                }
            }
        });
        this.mCurrentView = this.oneRCView;
    }

    @Override // com.jingdong.common.recommend.ScrollDispatchHelper.ScrollDispatchChild
    public void scrollStateChange(int i2) {
        RecommendChildRecyclerView recommendChildRecyclerView = this.mCurrentView;
        if (recommendChildRecyclerView != null) {
            recommendChildRecyclerView.scrollStateChange(i2);
        }
    }

    public void setFromType(int i2) {
        this.fromType = i2;
        resetView();
    }

    @Override // android.view.View
    public void setLayoutParams(ViewGroup.LayoutParams layoutParams) {
        if (layoutParams != null && layoutParams.height != -2) {
            layoutParams.height = getContentHeight();
        }
        super.setLayoutParams(layoutParams);
    }

    public void setOnPageChangeListener(OnRecommendChangeListener onRecommendChangeListener) {
        this.onPageChangeListener = onRecommendChangeListener;
    }

    public void setOnRequestResultListener(RecommendChildRecyclerView.OnRequestResultListener onRequestResultListener) {
        this.onRequestResultListener = onRequestResultListener;
    }

    public void setOnScrollListener(RecyclerView.OnScrollListener onScrollListener) {
        this.mOnScrollListener = onScrollListener;
    }

    public void setRecyclerViewPadding(int i2, int i3, int i4, int i5) {
        int[] iArr = this.recyclerViewPadding;
        iArr[0] = i2;
        iArr[1] = i3;
        iArr[2] = i4;
        iArr[3] = i5;
    }

    @Override // com.jingdong.common.recommend.ScrollDispatchHelper.ScrollDispatchChild
    public void setTabSpreadState(boolean z) {
    }

    @Override // com.jingdong.common.recommend.ScrollDispatchHelper.ScrollDispatchChild
    public void setTopSpace(int i2) {
        this.topSpace = i2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void settingSlidingTab(RecommendHomeTabs recommendHomeTabs) {
    }

    @Override // com.jingdong.common.recommend.ScrollDispatchHelper.ScrollDispatchChild
    public void stopScroll() {
        RecommendChildRecyclerView recommendChildRecyclerView = this.mCurrentView;
        if (recommendChildRecyclerView != null) {
            recommendChildRecyclerView.stopScroll();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void uploadClickMta(RecommendTab recommendTab, int i2) {
        this.mIsTabClick = false;
    }

    public void uploadExoTabMta() {
    }

    public void uploadExpoData() {
    }

    public void viewToTop() {
        allChildToTop();
    }

    public RecommendContentLayout(RecyclerView recyclerView, BaseActivity baseActivity) {
        super(baseActivity);
        this.mChildViews = new ArrayList<>();
        this.mCatchViews = new ConcurrentHashMap();
        this.mRecommendTabList = new ArrayList();
        this.recyclerViewPadding = new int[4];
        this.topSpace = 0;
        this.mActivity = baseActivity;
        TextScaleModeHelper.INSTANCE.getInstance().addOnTextSizeChangeListener(this);
        JDElderModeUtils.addElderModeChangeListener(this);
        this.mActivity.addDestroyListener(new IDestroyListener() { // from class: com.jingdong.common.recommend.ui.RecommendContentLayout.1
            @Override // com.jingdong.common.frame.IDestroyListener
            public void onDestroy() {
                TextScaleModeHelper.INSTANCE.getInstance().removeOnTextSizeChangeListener(RecommendContentLayout.this);
                JDElderModeUtils.removeElderModeChangeListener(RecommendContentLayout.this);
                RecommendContentLayout.this.onActivityDestroy();
            }
        });
        this.mParentRecyclerView = recyclerView;
        DeepDarkChangeManager.getInstance().addDeepDarkChangeListener(baseActivity, new Observer<Integer>() { // from class: com.jingdong.common.recommend.ui.RecommendContentLayout.2
            @Override // androidx.lifecycle.Observer
            public void onChanged(@Nullable Integer num) {
                for (int i2 = 0; i2 < RecommendContentLayout.this.mChildViews.size(); i2++) {
                    RecommendContentLayout.this.mChildViews.get(i2).onDeepDarkChanged();
                }
                PagerSlidingTabStrip pagerSlidingTabStrip = RecommendContentLayout.this.mSlidingTabStrip;
                if (pagerSlidingTabStrip instanceof HomeTabInterface) {
                    ((HomeTabInterface) pagerSlidingTabStrip).onDeepDarkChanged();
                }
                RecommendContentLayout.this.onDeepDarkChange();
            }
        });
        RecommendConfig recommendConfig = new RecommendConfig();
        this.recommendConfig = recommendConfig;
        recommendConfig.setEnableDeepDark(true);
        this.recommendConfig.setFollowService(true);
        initViews();
    }
}

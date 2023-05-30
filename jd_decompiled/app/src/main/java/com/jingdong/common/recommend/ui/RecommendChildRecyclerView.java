package com.jingdong.common.recommend.ui;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Parcelable;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.recommend.RecommendAnimator;
import com.jingdong.common.recommend.RecommendConfig;
import com.jingdong.common.recommend.RecommendConstant;
import com.jingdong.common.recommend.RecommendExpoHelper;
import com.jingdong.common.recommend.RecommendMtaUtils;
import com.jingdong.common.recommend.RecommendUtils;
import com.jingdong.common.recommend.RecommendVideoManagerNew;
import com.jingdong.common.recommend.ScrollDispatchHelper;
import com.jingdong.common.recommend.entity.RecomPerformanceData;
import com.jingdong.common.recommend.entity.RecommendHeaderData;
import com.jingdong.common.recommend.entity.RecommendHomeTabs;
import com.jingdong.common.recommend.entity.RecommendItem;
import com.jingdong.common.recommend.entity.RecommendProduct;
import com.jingdong.common.recommend.entity.RecommendTab;
import com.jingdong.common.recommend.entity.RecommendTipsEvent;
import com.jingdong.common.recommend.forlist.RecommendProductManager;
import com.jingdong.common.recommend.forlist.RecommendUtil;
import com.jingdong.common.recommend.forlist.RecommendViewHolder;
import com.jingdong.common.recommend.ui.RecommendEmptyView;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.jdsdk.utils.DPIUtil;
import com.jingdong.sdk.oklog.OKLog;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes6.dex */
public class RecommendChildRecyclerView extends RecyclerView implements ScrollDispatchHelper.ScrollDispatchChild {
    public static final String HOME_PAGE_TEST_PLAN_A = "A";
    public static final String HOME_PAGE_TEST_PLAN_B = "B";
    protected final int MIN_COUNT;
    private Bitmap.Config bitmapConfig;
    protected boolean canPreLoadNext;
    private String currentPlan;
    public RecommendExpoHelper expoHelper;
    private int fromType;
    private AtomicBoolean isAnimationEnable;
    private boolean isAutoPlayEnable;
    protected AtomicBoolean isDestroy;
    protected boolean isEnableAutoLoad;
    public boolean isLoadExpo;
    protected boolean isPreLoadEnable;
    protected boolean isPullToRefresh;
    private AtomicBoolean isScrollTop;
    public AtomicBoolean isShowEmptyView;
    protected BaseActivity mActivity;
    protected final ColorDrawable mDefaultBg;
    private FlingHelperUtil mFlingHelper;
    private int mMaxDistance;
    private onRecommendContentListener mOnRecommendContentListener;
    protected int mPreCount;
    private RecommendAdapter mRecommendAdapter;
    protected RecommendEmptyView mRecommendEmptyView;
    private RecommendHeaderData mRecommendHeaderData;
    public RecommendProductManager mRecommendProductManager;
    private RecommendTab mRecommendTab;
    private RecommendHomeTabs mRecommendTabs;
    protected RecommendUtil mRecommendUtil;
    private Parcelable normal;
    protected OnNewRequestResultListener onRequestResultListener;
    private RecyclerView parentRecyclerView;
    private RecommendAnimator recommendAnimator;
    protected AtomicBoolean recommendLoading;
    private boolean startFling;
    private int topSpace;
    private int totalDy;
    private int velocityY;
    protected RecommendVideoManagerNew videoManagerNew;

    /* loaded from: classes6.dex */
    public interface OnNewRequestResultListener {
        void onFailed(int i2);

        void onSuccess(int i2, RecommendHomeTabs recommendHomeTabs, RecommendHeaderData recommendHeaderData);
    }

    /* loaded from: classes6.dex */
    public interface OnRequestResultListener {
        void onFailed();

        void onSuccess(RecommendHomeTabs recommendHomeTabs);
    }

    /* loaded from: classes6.dex */
    public class RecommendAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        public static final int PARENT_TYPE_NUM = 2;
        public static final int TYPE_EMPTY = 1;
        public static final int TYPE_RECOMMEND_FOOTER = 0;
        public int TYPE_NUM;
        public BaseActivity activity;
        protected RecommendUtil recommendUtil;

        public RecommendAdapter() {
            RecommendChildRecyclerView.this = r1;
        }

        protected int getDataCorrectPosition(int i2) {
            return i2;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            RecommendUtil recommendUtil = this.recommendUtil;
            if (recommendUtil == null) {
                return 0;
            }
            return recommendUtil.getNewRecommendItemCount() + 1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemViewType(int i2) {
            if (RecommendChildRecyclerView.this.isShowEmptyView.get() && this.recommendUtil.getNewRecommendItemCount() == 0) {
                return 1;
            }
            if (this.recommendUtil.getNewRecommendItemCount() != i2 && this.recommendUtil.getNewRecommendItemCount() > 0) {
                return this.recommendUtil.getNewRecommendItemType(i2, this.TYPE_NUM);
            }
            return 0;
        }

        protected int getTypeNum() {
            return 2;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i2) {
            if (viewHolder == null) {
                return;
            }
            try {
                if (this.recommendUtil.isNewRecommendItemType(getItemViewType(i2), this.TYPE_NUM)) {
                    RecommendChildRecyclerView.this.changeItemViewPadding(viewHolder);
                    this.recommendUtil.onBindNewRecommendViewHolder(viewHolder, getDataCorrectPosition(i2), this.activity);
                    RecommendChildRecyclerView.this.callAfterBinder(viewHolder, getDataCorrectPosition(i2));
                }
            } catch (Exception e2) {
                if (Log.E) {
                    e2.printStackTrace();
                }
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i2) {
            try {
                if (this.recommendUtil.isNewRecommendItemType(i2, this.TYPE_NUM)) {
                    RecyclerView.ViewHolder onCreateNewRecommedViewHolder = this.recommendUtil.onCreateNewRecommedViewHolder(this.activity, i2, this.TYPE_NUM);
                    RecommendChildRecyclerView.this.changeItemViewPadding(onCreateNewRecommedViewHolder);
                    return onCreateNewRecommedViewHolder;
                } else if (i2 != 0) {
                    if (i2 != 1) {
                        TextView textView = new TextView(this.activity);
                        textView.setTextSize(1.0f);
                        return new SimpleViewHolder(textView);
                    }
                    RecommendChildRecyclerView recommendChildRecyclerView = RecommendChildRecyclerView.this;
                    if (recommendChildRecyclerView.mRecommendEmptyView == null) {
                        recommendChildRecyclerView.mRecommendEmptyView = new RecommendEmptyView(RecommendChildRecyclerView.this.getContext());
                        RecommendChildRecyclerView.this.mRecommendEmptyView.setFooterState(1002);
                        RecommendChildRecyclerView.this.mRecommendEmptyView.setRetryListener(new RecommendEmptyView.RetryListener() { // from class: com.jingdong.common.recommend.ui.RecommendChildRecyclerView.RecommendAdapter.1
                            {
                                RecommendAdapter.this = this;
                            }

                            @Override // com.jingdong.common.recommend.ui.RecommendEmptyView.RetryListener
                            public void emptyRetry() {
                                RecommendChildRecyclerView.this.setEmptyViewState(1002);
                                RecommendChildRecyclerView.this.loadRecommendData();
                            }
                        });
                    }
                    return new SimpleViewHolder(RecommendChildRecyclerView.this.mRecommendEmptyView);
                } else {
                    return this.recommendUtil.onCreateRecommedFooterViewHolder(viewGroup);
                }
            } catch (Exception e2) {
                if (OKLog.D) {
                    e2.printStackTrace();
                    return null;
                }
                return null;
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onViewAttachedToWindow(RecyclerView.ViewHolder viewHolder) {
            super.onViewAttachedToWindow(viewHolder);
            if (!(viewHolder instanceof RecommendViewHolder)) {
                ViewGroup.LayoutParams layoutParams = viewHolder.itemView.getLayoutParams();
                if (layoutParams instanceof StaggeredGridLayoutManager.LayoutParams) {
                    ((StaggeredGridLayoutManager.LayoutParams) layoutParams).setFullSpan(true);
                }
            }
            RecommendChildRecyclerView.this.controlFullSpan(viewHolder);
        }

        public RecommendAdapter(BaseActivity baseActivity, RecommendUtil recommendUtil) {
            RecommendChildRecyclerView.this = r1;
            this.activity = baseActivity;
            this.recommendUtil = recommendUtil;
            this.TYPE_NUM = getTypeNum();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i2, List<Object> list) {
            if (viewHolder == null) {
                return;
            }
            try {
                if (this.recommendUtil.isNewRecommendItemType(getItemViewType(i2), this.TYPE_NUM)) {
                    RecommendChildRecyclerView.this.changeItemViewPadding(viewHolder);
                    this.recommendUtil.onBindNewRecommendViewHolder(viewHolder, getDataCorrectPosition(i2), this.activity, list);
                    RecommendChildRecyclerView.this.callAfterBinder(viewHolder, getDataCorrectPosition(i2));
                }
            } catch (Exception e2) {
                if (Log.D) {
                    e2.printStackTrace();
                }
            }
        }
    }

    /* loaded from: classes6.dex */
    public static class SimpleViewHolder extends RecyclerView.ViewHolder {
        public SimpleViewHolder(View view) {
            super(view);
        }
    }

    /* loaded from: classes6.dex */
    public interface onRecommendContentListener {
        void onRecommendTips(RecommendProduct recommendProduct);
    }

    public RecommendChildRecyclerView(Context context) {
        super(context);
        this.isShowEmptyView = new AtomicBoolean(false);
        this.currentPlan = "B";
        this.MIN_COUNT = 6;
        this.mPreCount = 0;
        this.mDefaultBg = new ColorDrawable(-592138);
        this.isScrollTop = new AtomicBoolean(false);
        this.isDestroy = new AtomicBoolean(false);
        this.recommendLoading = new AtomicBoolean(false);
        this.isLoadExpo = false;
        this.isAnimationEnable = new AtomicBoolean(true);
        this.canPreLoadNext = true;
        this.isPullToRefresh = false;
        this.isEnableAutoLoad = true;
        this.isPreLoadEnable = false;
        this.topSpace = 0;
    }

    private void changeBgColor() {
        RecommendUtil recommendUtil = this.mRecommendUtil;
        if (recommendUtil == null) {
            setBackGround(-592138);
        } else if (recommendUtil.getNewRecommendItemCount() <= 0) {
            setBackGround(-592138);
        } else if (TextUtils.isEmpty(this.mRecommendUtil.getBgColor())) {
            setBackGround(-592138);
        } else {
            try {
                setBackGround(Color.parseColor(this.mRecommendUtil.getBgColor()));
            } catch (Exception unused) {
                setBackGround(-592138);
            }
        }
    }

    public void dateImmediateRemove(RecommendProduct recommendProduct, int i2, ArrayList<Integer> arrayList) {
        RecommendItem recommendItem;
        RecommendProduct recommendProduct2;
        if (recommendProduct == null || TextUtils.isEmpty(recommendProduct.category3)) {
            return;
        }
        int size = this.mRecommendUtil.getRecommendItemList().size() - 1;
        while (true) {
            if (size < (i2 > 0 ? i2 - 1 : 0)) {
                return;
            }
            if (size < this.mRecommendUtil.getRecommendItemList().size() && (recommendItem = this.mRecommendUtil.getRecommendItemList().get(size)) != null && (recommendProduct2 = recommendItem.product) != null && recommendProduct.category3.equals(recommendProduct2.category3) && !isComputingLayout() && this.mRecommendAdapter != null) {
                this.mRecommendUtil.getRecommendItemList().remove(size);
                this.mRecommendAdapter.notifyItemRangeRemoved(size, 1);
            }
            size--;
        }
    }

    private void dispatchParentFling() {
        int i2;
        if (this.parentRecyclerView != null) {
            if (isTop() && (i2 = this.velocityY) != 0) {
                double splineFlingDistance = this.mFlingHelper.getSplineFlingDistance(i2);
                if (splineFlingDistance > Math.abs(this.totalDy)) {
                    FlingHelperUtil flingHelperUtil = this.mFlingHelper;
                    double d = this.totalDy;
                    Double.isNaN(d);
                    this.parentRecyclerView.fling(0, -flingHelperUtil.getVelocityByDistance(splineFlingDistance + d));
                }
            }
            this.totalDy = 0;
            this.velocityY = 0;
        }
    }

    public void forceLinearManager() {
        RecyclerView.LayoutManager layoutManager = getLayoutManager();
        if ((layoutManager instanceof GridLayoutManager) || (layoutManager instanceof StaggeredGridLayoutManager)) {
            setLayoutManager(new LinearLayoutManager(getContext(), 1, false) { // from class: com.jingdong.common.recommend.ui.RecommendChildRecyclerView.2
                {
                    RecommendChildRecyclerView.this = this;
                }

                @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
                public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
                    try {
                        super.onLayoutChildren(recycler, state);
                    } catch (Exception e2) {
                        if (OKLog.D) {
                            e2.printStackTrace();
                        }
                    }
                }

                @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
                public int scrollVerticallyBy(int i2, RecyclerView.Recycler recycler, RecyclerView.State state) {
                    try {
                        return super.scrollVerticallyBy(i2, recycler, state);
                    } catch (Exception e2) {
                        if (OKLog.D) {
                            e2.printStackTrace();
                            return 0;
                        }
                        return 0;
                    }
                }
            });
            notifyChange();
        }
    }

    public int getTotalItemCount() {
        RecommendAdapter recommendAdapter = this.mRecommendAdapter;
        if (recommendAdapter != null) {
            return recommendAdapter.getItemCount();
        }
        return -1;
    }

    private int getVisibleItemCount() {
        RecyclerView.LayoutManager layoutManager = getLayoutManager();
        if (layoutManager != null) {
            return layoutManager.getChildCount();
        }
        return -1;
    }

    public boolean isForceLinearManager() {
        RecommendUtil recommendUtil = this.mRecommendUtil;
        return recommendUtil != null && "1".equals(recommendUtil.getDisplayMode());
    }

    private boolean isGridLayoutManager() {
        RecyclerView.LayoutManager layoutManager = getLayoutManager();
        return layoutManager != null && (layoutManager instanceof GridLayoutManager);
    }

    private void notifyLayoutChange() {
        RecyclerView.LayoutManager layoutManager = getLayoutManager();
        if (layoutManager == null || !(layoutManager instanceof StaggeredGridLayoutManager)) {
            return;
        }
        ((StaggeredGridLayoutManager) layoutManager).invalidateSpanAssignments();
    }

    private void notifyLayoutManagerB() {
        if (isStaggeredGridLayoutManager()) {
            return;
        }
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, 1) { // from class: com.jingdong.common.recommend.ui.RecommendChildRecyclerView.11
            Parcelable mPreState = null;

            {
                RecommendChildRecyclerView.this = this;
            }

            @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
            public void onAttachedToWindow(RecyclerView recyclerView) {
                if (RecommendChildRecyclerView.this.isScrollTop.get() || (RecommendChildRecyclerView.this.parentRecyclerView != null && !RecommendChildRecyclerView.this.isLastCompleteVisible())) {
                    this.mPreState = RecommendChildRecyclerView.this.normal;
                    RecommendChildRecyclerView.this.isScrollTop.set(false);
                }
                Parcelable parcelable = this.mPreState;
                if (parcelable != null) {
                    onRestoreInstanceState(parcelable);
                }
                RecommendChildRecyclerView.this.notifyChange();
                super.onAttachedToWindow(recyclerView);
            }

            @Override // androidx.recyclerview.widget.StaggeredGridLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
            public void onDetachedFromWindow(RecyclerView recyclerView, RecyclerView.Recycler recycler) {
                this.mPreState = onSaveInstanceState();
                super.onDetachedFromWindow(recyclerView, recycler);
            }

            @Override // androidx.recyclerview.widget.StaggeredGridLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
            public void onItemsAdded(RecyclerView recyclerView, int i2, int i3) {
                try {
                    super.onItemsAdded(recyclerView, i2, i3);
                } catch (Exception unused) {
                }
            }

            @Override // androidx.recyclerview.widget.StaggeredGridLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
            public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
                try {
                    super.onLayoutChildren(recycler, state);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }

            @Override // androidx.recyclerview.widget.StaggeredGridLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
            public int scrollVerticallyBy(int i2, RecyclerView.Recycler recycler, RecyclerView.State state) {
                try {
                    return super.scrollVerticallyBy(i2, recycler, state);
                } catch (Exception e2) {
                    e2.printStackTrace();
                    return 0;
                }
            }
        };
        staggeredGridLayoutManager.setGapStrategy(0);
        this.normal = staggeredGridLayoutManager.onSaveInstanceState();
        setLayoutManager(staggeredGridLayoutManager);
        notifyChange();
    }

    public void onDataSetChanged() {
        RecommendAdapter recommendAdapter;
        if (isComputingLayout() || (recommendAdapter = this.mRecommendAdapter) == null) {
            return;
        }
        recommendAdapter.notifyDataSetChanged();
        onDataChange();
    }

    public void onRangeChanged(int i2, int i3) {
        RecommendAdapter recommendAdapter;
        if (isComputingLayout() || (recommendAdapter = this.mRecommendAdapter) == null) {
            return;
        }
        recommendAdapter.notifyItemRangeChanged(i2, i3);
        onDataChange();
    }

    public void onRangeRemoved(int i2, int i3) {
        RecommendAdapter recommendAdapter;
        if (isComputingLayout() || (recommendAdapter = this.mRecommendAdapter) == null) {
            return;
        }
        recommendAdapter.notifyItemRangeRemoved(i2, i3);
        onDataChange();
    }

    public void resetAnimation() {
        if (this.isAnimationEnable.get()) {
            useAnimator(0);
        } else {
            unUseAnimator();
        }
    }

    private void setRecommendShowType() {
        RecommendProductManager recommendProductManager = this.mRecommendProductManager;
        if (recommendProductManager != null) {
            recommendProductManager.setHomePageTestPlan(this.currentPlan);
        }
        if (isForceLinearManager()) {
            forceLinearManager();
        } else if ("B".equals(this.currentPlan)) {
            notifyLayoutManagerB();
        } else {
            notifyLayoutManagerA();
        }
    }

    private void unUseAnimator() {
        setLayoutTransition(null);
        RecyclerView.ItemAnimator itemAnimator = getItemAnimator();
        if (itemAnimator != null) {
            itemAnimator.setAddDuration(0L);
            itemAnimator.setChangeDuration(0L);
            itemAnimator.setMoveDuration(0L);
            itemAnimator.setRemoveDuration(0L);
            if (itemAnimator instanceof SimpleItemAnimator) {
                ((SimpleItemAnimator) itemAnimator).setSupportsChangeAnimations(false);
            }
        }
    }

    public void afterNotifyDataChange(int i2, int i3) {
        if (i3 <= 0 || i2 != 1) {
            return;
        }
        post(new Runnable() { // from class: com.jingdong.common.recommend.ui.RecommendChildRecyclerView.4
            {
                RecommendChildRecyclerView.this = this;
            }

            @Override // java.lang.Runnable
            public void run() {
                RecommendChildRecyclerView recommendChildRecyclerView = RecommendChildRecyclerView.this;
                RecommendVideoManagerNew recommendVideoManagerNew = recommendChildRecyclerView.videoManagerNew;
                if (recommendVideoManagerNew != null) {
                    recommendVideoManagerNew.onScrollStateChange(recommendChildRecyclerView.getScrollState());
                }
                RecommendChildRecyclerView recommendChildRecyclerView2 = RecommendChildRecyclerView.this;
                RecommendExpoHelper recommendExpoHelper = recommendChildRecyclerView2.expoHelper;
                if (recommendExpoHelper != null) {
                    recommendExpoHelper.dealExpoView(recommendChildRecyclerView2);
                }
            }
        });
    }

    @Override // com.jingdong.common.recommend.ScrollDispatchHelper.ScrollDispatchChild
    public void allChildToTop() {
        scrollToTop();
    }

    public void beforeRefreshView(int i2) {
        RecommendUtil recommendUtil;
        RecommendConfig recommendConfig;
        if (i2 == 1) {
            changViewPadding();
            if (this.onRequestResultListener == null || (recommendUtil = this.mRecommendUtil) == null || (recommendConfig = recommendUtil.getRecommendConfig()) == null) {
                return;
            }
            recommendConfig.serviceDarkSwitch(this.mRecommendUtil.serviceDarkModeEnable);
        }
    }

    protected void callAfterBinder(RecyclerView.ViewHolder viewHolder, int i2) {
    }

    @Override // com.jingdong.common.recommend.ScrollDispatchHelper.ScrollDispatchChild
    public boolean canChildScrollVertically(int i2) {
        return canScrollVertically(i2);
    }

    public void changViewPadding() {
        int i2 = RecommendUtils.RECYCLER_VIEW_PADDING_NEW;
        setPadding(i2, 0, i2, 0);
    }

    public void changeItemViewPadding(RecyclerView.ViewHolder viewHolder) {
    }

    @Override // com.jingdong.common.recommend.ScrollDispatchHelper.ScrollDispatchChild
    public void childScrollBy(int i2, int i3) {
        scrollBy(i2, i3);
    }

    public void controlFullSpan(RecyclerView.ViewHolder viewHolder) {
    }

    protected RecommendAdapter createAdapter() {
        return new RecommendAdapter(this.mActivity, this.mRecommendUtil);
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (motionEvent != null && motionEvent.getAction() == 0) {
            this.velocityY = 0;
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    @Override // androidx.recyclerview.widget.RecyclerView
    public boolean fling(int i2, int i3) {
        if (isAttachedToWindow()) {
            int abs = Math.abs(i3);
            int i4 = this.mMaxDistance;
            if (i4 > 8888 && abs > i4) {
                i3 = (i4 * abs) / i3;
            }
            boolean fling = super.fling(i2, i3);
            if (fling && i3 < 0) {
                this.startFling = true;
                this.velocityY = i3;
            } else {
                this.velocityY = 0;
            }
            return fling;
        }
        return false;
    }

    public RecommendExpoHelper getChildExpoHelper() {
        return this.expoHelper;
    }

    @Override // com.jingdong.common.recommend.ScrollDispatchHelper.ScrollDispatchChild
    public ViewParent getChildParent() {
        return getParent();
    }

    @Override // com.jingdong.common.recommend.ScrollDispatchHelper.ScrollDispatchChild
    public int getChildTop() {
        return getTop();
    }

    public int getFirstVisibleItem() {
        RecyclerView.LayoutManager layoutManager = getLayoutManager();
        if (layoutManager != null && (layoutManager instanceof StaggeredGridLayoutManager)) {
            int[] iArr = new int[2];
            ((StaggeredGridLayoutManager) layoutManager).findFirstVisibleItemPositions(iArr);
            return iArr[0] > iArr[1] ? iArr[1] : iArr[0];
        } else if (layoutManager != null && (layoutManager instanceof GridLayoutManager)) {
            return ((GridLayoutManager) layoutManager).findFirstVisibleItemPosition();
        } else {
            if (layoutManager instanceof LinearLayoutManager) {
                return ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition();
            }
            return -1;
        }
    }

    public int getLastVisibleItem() {
        RecyclerView.LayoutManager layoutManager = getLayoutManager();
        if (layoutManager != null && (layoutManager instanceof StaggeredGridLayoutManager)) {
            int[] iArr = new int[2];
            ((StaggeredGridLayoutManager) layoutManager).findLastVisibleItemPositions(iArr);
            return iArr[0] > iArr[1] ? iArr[0] : iArr[1];
        } else if (layoutManager != null && (layoutManager instanceof GridLayoutManager)) {
            return ((GridLayoutManager) layoutManager).findLastVisibleItemPosition();
        } else {
            if (layoutManager instanceof LinearLayoutManager) {
                return ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
            }
            return -1;
        }
    }

    protected int getSpanSize(int i2) {
        RecommendUtil recommendUtil = this.mRecommendUtil;
        return (recommendUtil == null ? 0 : recommendUtil.getNewRecommendItemCount()) <= i2 ? 2 : 1;
    }

    @Override // com.jingdong.common.recommend.ScrollDispatchHelper.ScrollDispatchChild
    public int getTopSpace() {
        return this.topSpace;
    }

    public int getTotalDy() {
        return this.totalDy;
    }

    public RecommendTab getmRecommendTab() {
        return this.mRecommendTab;
    }

    public boolean hasRecommendData() {
        RecommendUtil recommendUtil = this.mRecommendUtil;
        return recommendUtil != null && recommendUtil.getNewRecommendItemCount() > 0;
    }

    protected void initRecommendManager(int i2) {
        RecommendProductManager recommendProductManager = new RecommendProductManager(this.mActivity, i2, null) { // from class: com.jingdong.common.recommend.ui.RecommendChildRecyclerView.3
            {
                RecommendChildRecyclerView.this = this;
            }

            @Override // com.jingdong.common.recommend.forlist.RecommendProductManager
            public void enableAnimator(boolean z, int i3) {
                super.enableAnimator(z, i3);
                if (!z) {
                    RecommendChildRecyclerView.this.resetAnimation();
                } else {
                    RecommendChildRecyclerView.this.useAnimator(i3);
                }
            }

            @Override // com.jingdong.common.recommend.forlist.RecommendProductManager
            public RecyclerView.ViewHolder findHolderForAdapterPostion(int i3) {
                return RecommendChildRecyclerView.this.findViewHolderForAdapterPosition(i3);
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.jingdong.common.recommend.forlist.RecommendProductManager
            public void notifyDataChanged(int i3, int i4, int i5) {
                RecommendChildRecyclerView.this.beforeRefreshView(i3);
                super.notifyDataChanged(i3, i4, i5);
                RecommendChildRecyclerView.this.afterNotifyDataChange(i3, i5);
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.jingdong.common.recommend.forlist.RecommendProductManager
            public void onAnimationEnable(boolean z) {
                super.onAnimationEnable(z);
                if (RecommendChildRecyclerView.this.isDestroy.get()) {
                    return;
                }
                RecommendChildRecyclerView.this.isAnimationEnable.set(z);
                RecommendChildRecyclerView.this.resetAnimation();
            }

            @Override // com.jingdong.common.recommend.forlist.RecommendProductManager
            protected void onErrorRefreshView() {
                if (RecommendChildRecyclerView.this.isDestroy.get()) {
                    return;
                }
                RecommendChildRecyclerView.this.recommendLoading.set(false);
                RecommendUtil recommendUtil = RecommendChildRecyclerView.this.mRecommendUtil;
                if (recommendUtil == null || recommendUtil.isHasRecommend()) {
                    return;
                }
                RecommendChildRecyclerView.this.safeNotifyDataSetChanged();
                RecommendChildRecyclerView.this.notifyEmptyViewState();
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.jingdong.common.recommend.forlist.RecommendProductManager
            public void onRecommendDataError() {
                super.onRecommendDataError();
                if (RecommendChildRecyclerView.this.isDestroy.get()) {
                    return;
                }
                RecommendChildRecyclerView.this.recommendLoading.set(false);
                RecommendChildRecyclerView.this.safeNotifyDataSetChanged();
                RecommendChildRecyclerView.this.notifyEmptyViewState();
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.jingdong.common.recommend.forlist.RecommendProductManager
            public void onRecommendHeader(RecommendHeaderData recommendHeaderData) {
                super.onRecommendHeader(recommendHeaderData);
                if (RecommendChildRecyclerView.this.isDestroy.get()) {
                    return;
                }
                RecommendChildRecyclerView.this.mRecommendHeaderData = recommendHeaderData;
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.jingdong.common.recommend.forlist.RecommendProductManager
            public void onRecommendTabs(RecommendHomeTabs recommendHomeTabs) {
                super.onRecommendTabs(recommendHomeTabs);
                if (RecommendChildRecyclerView.this.isDestroy.get()) {
                    return;
                }
                RecommendChildRecyclerView.this.mRecommendTabs = recommendHomeTabs;
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.jingdong.common.recommend.forlist.RecommendProductManager
            public void onRecommendTips(RecommendProduct recommendProduct) {
                super.onRecommendTips(recommendProduct);
                if (RecommendChildRecyclerView.this.isDestroy.get() || RecommendChildRecyclerView.this.mOnRecommendContentListener == null) {
                    return;
                }
                RecommendChildRecyclerView.this.mOnRecommendContentListener.onRecommendTips(recommendProduct);
            }

            @Override // com.jingdong.common.recommend.forlist.RecommendProductManager
            protected void onRefershListDataRangeChange(int i3, int i4) {
                if (RecommendChildRecyclerView.this.isDestroy.get()) {
                    return;
                }
                RecommendChildRecyclerView.this.recommendLoading.set(false);
                RecommendChildRecyclerView.this.notifyItemRangeChanged(i3, i4);
                RecommendChildRecyclerView.this.notifyEmptyViewState();
            }

            @Override // com.jingdong.common.recommend.forlist.RecommendProductManager
            protected void onRefershListDataRangeRemove(int i3, int i4) {
                if (RecommendChildRecyclerView.this.isDestroy.get()) {
                    return;
                }
                RecommendChildRecyclerView.this.notifyItemRangeRemoved(i3, i4);
                RecommendChildRecyclerView.this.notifyEmptyViewState();
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.jingdong.common.recommend.forlist.RecommendProductManager
            public void onRefreshListData() {
                if (RecommendChildRecyclerView.this.isDestroy.get()) {
                    return;
                }
                RecommendChildRecyclerView.this.recommendLoading.set(false);
                RecommendChildRecyclerView.this.safeNotifyDataSetChanged();
                RecommendChildRecyclerView.this.notifyEmptyViewState();
            }

            @Override // com.jingdong.common.recommend.forlist.RecommendProductManager
            protected void onRefreshListDataChanged(int i3, int i4, Object obj) {
                RecommendChildRecyclerView.this.notifyItemRangeChanged(i3, i4, obj);
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.jingdong.common.recommend.forlist.RecommendProductManager
            public void onRefreshListDataImmediateRemove(RecommendProduct recommendProduct, int i3, ArrayList<Integer> arrayList) {
                super.onRefreshListDataImmediateRemove(recommendProduct, i3, arrayList);
                if (RecommendChildRecyclerView.this.isDestroy.get()) {
                    return;
                }
                RecommendChildRecyclerView.this.dateImmediateRemove(recommendProduct, i3, arrayList);
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.jingdong.common.recommend.forlist.RecommendProductManager
            public void onRefreshListDataRangeInsert(int i3, int i4) {
                super.onRefreshListDataRangeInsert(i3, i4);
                if (RecommendChildRecyclerView.this.isDestroy.get()) {
                    return;
                }
                RecommendChildRecyclerView.this.recommendLoading.set(false);
                RecommendChildRecyclerView.this.notifyItemRangeInserted(i3, i4);
                RecommendChildRecyclerView.this.notifyEmptyViewState();
            }

            @Override // com.jingdong.common.recommend.forlist.RecommendProductManager
            protected void onRequestFail(int i3) {
                if (RecommendChildRecyclerView.this.isDestroy.get()) {
                    return;
                }
                RecommendChildRecyclerView recommendChildRecyclerView = RecommendChildRecyclerView.this;
                recommendChildRecyclerView.canPreLoadNext = true;
                recommendChildRecyclerView.recommendLoading.set(false);
                OnNewRequestResultListener onNewRequestResultListener = RecommendChildRecyclerView.this.onRequestResultListener;
                if (onNewRequestResultListener != null) {
                    onNewRequestResultListener.onFailed(i3);
                }
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.jingdong.common.recommend.forlist.RecommendProductManager
            public void onRequestSuccess(final int i3) {
                RecommendUtil recommendUtil;
                if (RecommendChildRecyclerView.this.isDestroy.get()) {
                    return;
                }
                RecommendChildRecyclerView recommendChildRecyclerView = RecommendChildRecyclerView.this;
                recommendChildRecyclerView.canPreLoadNext = true;
                recommendChildRecyclerView.recommendLoading.set(false);
                super.onRequestSuccess(i3);
                RecommendChildRecyclerView recommendChildRecyclerView2 = RecommendChildRecyclerView.this;
                OnNewRequestResultListener onNewRequestResultListener = recommendChildRecyclerView2.onRequestResultListener;
                if (onNewRequestResultListener != null) {
                    onNewRequestResultListener.onSuccess(i3, recommendChildRecyclerView2.mRecommendTabs, RecommendChildRecyclerView.this.mRecommendHeaderData);
                }
                RecommendChildRecyclerView recommendChildRecyclerView3 = RecommendChildRecyclerView.this;
                if (!recommendChildRecyclerView3.isEnableAutoLoad && (recommendUtil = recommendChildRecyclerView3.mRecommendUtil) != null) {
                    recommendUtil.setFootState(2);
                }
                RecommendChildRecyclerView recommendChildRecyclerView4 = RecommendChildRecyclerView.this;
                recommendChildRecyclerView4.onSuccess(i3, recommendChildRecyclerView4.mRecommendTabs, RecommendChildRecyclerView.this.mRecommendHeaderData);
                if (RecommendChildRecyclerView.this.isForceLinearManager()) {
                    RecommendChildRecyclerView.this.forceLinearManager();
                }
                RecommendChildRecyclerView.this.post(new Runnable() { // from class: com.jingdong.common.recommend.ui.RecommendChildRecyclerView.3.1
                    {
                        AnonymousClass3.this = this;
                    }

                    @Override // java.lang.Runnable
                    public void run() {
                        RecomPerformanceData performanceData;
                        if (RecommendChildRecyclerView.this.getParent() == null || (performanceData = RecommendChildRecyclerView.this.mRecommendUtil.getPerformanceData(i3)) == null || performanceData.requestEndTime <= 0) {
                            return;
                        }
                        performanceData.renderEndTime = System.currentTimeMillis();
                    }
                });
            }

            @Override // com.jingdong.common.recommend.forlist.RecommendProductManager
            protected void onSetPreload(boolean z) {
                RecommendChildRecyclerView.this.setIsPreLoadEnable(z);
            }
        };
        this.mRecommendProductManager = recommendProductManager;
        Bitmap.Config config = this.bitmapConfig;
        if (config != null) {
            recommendProductManager.setBitmapConfig(config);
        }
        this.mRecommendUtil = this.mRecommendProductManager.getRecommendUtil();
    }

    protected boolean isErrorRange(int i2, int i3) {
        RecommendUtil recommendUtil = this.mRecommendUtil;
        if (recommendUtil == null) {
            return false;
        }
        return i2 < 0 || i3 < 1 || i2 + i3 > recommendUtil.getNewRecommendItemCount() + 1;
    }

    public boolean isLastCompleteVisible() {
        try {
            RecyclerView recyclerView = this.parentRecyclerView;
            if (recyclerView != null && (recyclerView.getLayoutManager() instanceof LinearLayoutManager)) {
                return ((LinearLayoutManager) this.parentRecyclerView.getLayoutManager()).findLastCompletelyVisibleItemPosition() >= this.parentRecyclerView.getLayoutManager().getItemCount() - 1;
            }
        } catch (Exception e2) {
            if (Log.D) {
                e2.printStackTrace();
            }
        }
        return false;
    }

    protected boolean isStaggeredGridLayoutManager() {
        RecyclerView.LayoutManager layoutManager = getLayoutManager();
        return layoutManager != null && (layoutManager instanceof StaggeredGridLayoutManager);
    }

    public boolean isTop() {
        return !canScrollVertically(-1);
    }

    public void loadRecommendData() {
        if (this.recommendLoading.get() || this.mRecommendProductManager == null) {
            return;
        }
        if (!RecommendConstant.loadingBuxFix) {
            this.recommendLoading.set(true);
        }
        this.mRecommendProductManager.loadRecommendData();
        if (this.isLoadExpo && this.fromType == 9) {
            JDMtaUtils.sendExposureData(getContext(), "JDHomeFragment", RecommendMtaUtils.Home_PageId, "", "Home_ProductList", "", "", "", "");
        }
    }

    public void needRealExpoHelper() {
        RecommendExpoHelper recommendExpoHelper = new RecommendExpoHelper(this);
        this.expoHelper = recommendExpoHelper;
        recommendExpoHelper.setNestedParentView(this.parentRecyclerView);
    }

    protected void notifyChange() {
        if (getChildCount() == 0) {
            safeNotifyDataSetChanged();
        }
    }

    protected void notifyEmptyViewState() {
        RecommendAdapter recommendAdapter;
        if (!this.isShowEmptyView.get() || this.mRecommendEmptyView == null || (recommendAdapter = this.mRecommendAdapter) == null) {
            return;
        }
        if (recommendAdapter.getItemViewType(0) == 1) {
            setEmptyViewState(1003);
        } else {
            setEmptyViewState(1001);
        }
    }

    public void notifyItemRangeChanged(final int i2, final int i3) {
        if (isErrorRange(i2, i3)) {
            safeNotifyDataSetChanged();
        } else if (!isComputingLayout()) {
            onRangeChanged(i2, i3);
        } else {
            BaseActivity baseActivity = this.mActivity;
            if (baseActivity == null) {
                return;
            }
            baseActivity.post(new Runnable() { // from class: com.jingdong.common.recommend.ui.RecommendChildRecyclerView.7
                {
                    RecommendChildRecyclerView.this = this;
                }

                @Override // java.lang.Runnable
                public void run() {
                    RecommendChildRecyclerView.this.onRangeChanged(i2, i3);
                }
            });
        }
    }

    public void notifyItemRangeInserted(final int i2, final int i3) {
        if (isErrorRange(i2, i3)) {
            safeNotifyDataSetChanged();
        } else if (!isComputingLayout()) {
            onRangeInserted(i2, i3);
        } else {
            BaseActivity baseActivity = this.mActivity;
            if (baseActivity == null) {
                return;
            }
            baseActivity.post(new Runnable() { // from class: com.jingdong.common.recommend.ui.RecommendChildRecyclerView.5
                {
                    RecommendChildRecyclerView.this = this;
                }

                @Override // java.lang.Runnable
                public void run() {
                    RecommendChildRecyclerView.this.onRangeInserted(i2, i3);
                }
            });
        }
    }

    public void notifyItemRangeRemoved(final int i2, final int i3) {
        if (isErrorRange(i2, i3)) {
            safeNotifyDataSetChanged();
        } else if (!isComputingLayout()) {
            onRangeRemoved(i2, i3);
        } else {
            BaseActivity baseActivity = this.mActivity;
            if (baseActivity == null) {
                return;
            }
            baseActivity.post(new Runnable() { // from class: com.jingdong.common.recommend.ui.RecommendChildRecyclerView.6
                {
                    RecommendChildRecyclerView.this = this;
                }

                @Override // java.lang.Runnable
                public void run() {
                    RecommendChildRecyclerView.this.onRangeRemoved(i2, i3);
                }
            });
        }
    }

    protected void notifyLayoutManagerA() {
        if (isGridLayoutManager()) {
            return;
        }
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2) { // from class: com.jingdong.common.recommend.ui.RecommendChildRecyclerView.9
            {
                RecommendChildRecyclerView.this = this;
            }

            @Override // androidx.recyclerview.widget.GridLayoutManager, androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
            public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
                try {
                    super.onLayoutChildren(recycler, state);
                } catch (Exception e2) {
                    if (OKLog.D) {
                        e2.printStackTrace();
                    }
                }
            }

            @Override // androidx.recyclerview.widget.GridLayoutManager, androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
            public int scrollVerticallyBy(int i2, RecyclerView.Recycler recycler, RecyclerView.State state) {
                try {
                    return super.scrollVerticallyBy(i2, recycler, state);
                } catch (Exception e2) {
                    if (OKLog.D) {
                        e2.printStackTrace();
                        return 0;
                    }
                    return 0;
                }
            }
        };
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() { // from class: com.jingdong.common.recommend.ui.RecommendChildRecyclerView.10
            {
                RecommendChildRecyclerView.this = this;
            }

            @Override // androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
            public int getSpanSize(int i2) {
                return RecommendChildRecyclerView.this.getSpanSize(i2);
            }
        });
        setLayoutManager(gridLayoutManager);
        notifyChange();
    }

    public void onBackToHome() {
        RecommendProductManager recommendProductManager = this.mRecommendProductManager;
        if (recommendProductManager != null) {
            recommendProductManager.onBackToHome();
        }
    }

    @Override // android.view.View
    protected void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        RecommendAdapter recommendAdapter = this.mRecommendAdapter;
        if (recommendAdapter != null) {
            recommendAdapter.notifyDataSetChanged();
        }
    }

    protected void onDataChange() {
        if (this.mRecommendUtil == null || this.mRecommendProductManager == null) {
            return;
        }
        changeBgColor();
        int newRecommendItemCount = this.mRecommendUtil.getNewRecommendItemCount();
        if (newRecommendItemCount > 0 && newRecommendItemCount < 6) {
            if (newRecommendItemCount != this.mPreCount) {
                loadRecommendData();
                this.mPreCount = newRecommendItemCount;
                return;
            }
            return;
        }
        this.mPreCount = 0;
    }

    public void onDeepDarkChanged() {
        safeNotifyDataSetChanged();
    }

    @Override // androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        try {
            super.onDetachedFromWindow();
        } catch (Exception e2) {
            if (Log.D) {
                e2.printStackTrace();
            }
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView, android.view.View
    public void onMeasure(int i2, int i3) {
        int size = View.MeasureSpec.getSize(i2);
        if (RecommendUtils.windowWidthSize != size) {
            RecommendUtils.windowWidthSize = size;
        }
        super.onMeasure(i2, i3);
    }

    public void onPageSelected() {
        this.isDestroy.set(false);
        RecommendTab recommendTab = this.mRecommendTab;
        if (recommendTab != null) {
            this.mRecommendProductManager.setTabId(recommendTab.tabId);
            this.mRecommendProductManager.setSelectedRecommendTab(this.mRecommendTab);
        }
        setRecommendShowType();
        if (this.mRecommendAdapter == null) {
            RecommendAdapter createAdapter = createAdapter();
            this.mRecommendAdapter = createAdapter;
            setAdapter(createAdapter);
        }
        RecommendUtil recommendUtil = this.mRecommendUtil;
        if ((recommendUtil == null || recommendUtil.getNewRecommendItemCount() > 0) && !this.isPullToRefresh) {
            return;
        }
        this.isPullToRefresh = false;
        setEmptyViewState(1002);
        loadRecommendData();
    }

    @Override // com.jingdong.common.recommend.ScrollDispatchHelper.ScrollDispatchChild
    public void onParentScroll(int i2) {
    }

    public void onRangeInserted(int i2, int i3) {
        RecommendAdapter recommendAdapter;
        if (isComputingLayout() || (recommendAdapter = this.mRecommendAdapter) == null) {
            return;
        }
        if (i2 == 0) {
            recommendAdapter.notifyDataSetChanged();
            onDataChange();
            return;
        }
        recommendAdapter.notifyItemRangeInserted(i2, i3);
        onDataChange();
    }

    public void onScrollChanged(int i2) {
        RecommendProductManager recommendProductManager;
        if (this.isAutoPlayEnable && (recommendProductManager = this.mRecommendProductManager) != null && this.videoManagerNew == null) {
            recommendProductManager.onScroll(this, getFirstVisibleItem(), getVisibleItemCount(), getTotalItemCount(), 0);
            this.mRecommendProductManager.onScrollStateChanged(this, i2);
        }
    }

    public void onSelectBefore() {
        sendExposureMta();
        RecommendVideoManagerNew recommendVideoManagerNew = this.videoManagerNew;
        if (recommendVideoManagerNew != null) {
            recommendVideoManagerNew.onViewHidden();
        }
    }

    @Override // com.jingdong.common.recommend.ScrollDispatchHelper.ScrollDispatchChild
    public void onSelfScroll(int i2) {
        if (this.isDestroy.get()) {
            return;
        }
        if (this.startFling) {
            this.totalDy = 0;
            this.startFling = false;
        }
        this.totalDy += i2;
    }

    @Override // androidx.recyclerview.widget.RecyclerView, android.view.View
    public void onSizeChanged(int i2, int i3, int i4, int i5) {
        try {
            super.onSizeChanged(i2, i3, i4, i5);
        } catch (Exception unused) {
        }
    }

    public void onSuccess(int i2, RecommendHomeTabs recommendHomeTabs, RecommendHeaderData recommendHeaderData) {
        RecommendUtil recommendUtil;
        int i3 = this.fromType;
        if ((i3 == 6 || i3 == 24 || i3 == 0) && (recommendUtil = this.mRecommendUtil) != null) {
            boolean z = recommendHeaderData != null && recommendHeaderData.isAdAllPositionExpo;
            recommendUtil.setAdRealExpo(z, z);
            RecommendExpoHelper recommendExpoHelper = this.expoHelper;
            if (recommendExpoHelper != null) {
                recommendExpoHelper.setAdRealExpo(z);
            }
        }
    }

    public void onViewBind() {
        notifyChange();
        BaseActivity baseActivity = this.mActivity;
        if (baseActivity == null) {
            return;
        }
        baseActivity.post(new Runnable() { // from class: com.jingdong.common.recommend.ui.RecommendChildRecyclerView.13
            {
                RecommendChildRecyclerView.this = this;
            }

            @Override // java.lang.Runnable
            public void run() {
                if (RecommendChildRecyclerView.this.isLastCompleteVisible()) {
                    RecommendChildRecyclerView.this.scrollToTop();
                }
            }
        });
    }

    public void onViewRecycle() {
    }

    protected void resetData() {
        RecommendProductManager recommendProductManager = this.mRecommendProductManager;
        if (recommendProductManager != null) {
            recommendProductManager.reSet();
            RecommendUtil recommendUtil = this.mRecommendUtil;
            if (recommendUtil != null) {
                recommendUtil.clearRecommendData();
            }
            safeNotifyDataSetChanged();
        }
        RecommendVideoManagerNew recommendVideoManagerNew = this.videoManagerNew;
        if (recommendVideoManagerNew != null) {
            recommendVideoManagerNew.reset();
        }
    }

    public void safeNotifyDataSetChanged() {
        if (!isComputingLayout()) {
            onDataSetChanged();
            return;
        }
        BaseActivity baseActivity = this.mActivity;
        if (baseActivity == null) {
            return;
        }
        baseActivity.post(new Runnable() { // from class: com.jingdong.common.recommend.ui.RecommendChildRecyclerView.12
            {
                RecommendChildRecyclerView.this = this;
            }

            @Override // java.lang.Runnable
            public void run() {
                RecommendChildRecyclerView.this.onDataSetChanged();
            }
        });
    }

    @Override // com.jingdong.common.recommend.ScrollDispatchHelper.ScrollDispatchChild
    public void scrollStateChange(int i2) {
        try {
            if (this.isDestroy.get()) {
                return;
            }
            if (getFirstVisibleItem() == 0) {
                notifyLayoutChange();
            }
            if (i2 == 0) {
                dispatchParentFling();
            }
            RecommendVideoManagerNew recommendVideoManagerNew = this.videoManagerNew;
            if (recommendVideoManagerNew != null) {
                recommendVideoManagerNew.onScrollStateChange(i2);
            }
            onScrollChanged(i2);
            boolean z = getLastVisibleItem() >= getTotalItemCount() + (-4);
            if (this.isEnableAutoLoad && i2 == 0 && z) {
                loadRecommendData();
            }
        } catch (Exception e2) {
            if (OKLog.D) {
                e2.printStackTrace();
            }
        }
    }

    public void scrollToTop() {
        this.isScrollTop.set(true);
        if (isTop()) {
            return;
        }
        try {
            scrollToPosition(0);
        } catch (Exception e2) {
            if (OKLog.D) {
                e2.printStackTrace();
            }
        }
    }

    public void sendExposureMta() {
        RecommendProductManager recommendProductManager = this.mRecommendProductManager;
        if (recommendProductManager != null) {
            recommendProductManager.sendExposureMta();
        }
    }

    public void setAutoPlayEnable(boolean z) {
        this.isAutoPlayEnable = z;
    }

    protected void setBackGround(int i2) {
        if (this.mDefaultBg != null) {
            RecommendUtil recommendUtil = this.mRecommendUtil;
            if (recommendUtil != null && recommendUtil.isDarkEnable()) {
                this.mDefaultBg.setColor(this.mRecommendUtil.getDarkBgColor());
            } else {
                this.mDefaultBg.setColor(i2);
            }
            setBackgroundDrawable(this.mDefaultBg);
        }
    }

    public void setBitmapConfig(Bitmap.Config config) {
        this.bitmapConfig = config;
    }

    public void setCurrentPlan(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        if (("A".equals(str) || "B".equals(str)) && !this.currentPlan.equals(str)) {
            this.currentPlan = str;
            if (this.mRecommendProductManager != null) {
                setRecommendShowType();
                safeNotifyDataSetChanged();
            }
        }
    }

    public void setEmptyViewState(int i2) {
        RecommendEmptyView recommendEmptyView = this.mRecommendEmptyView;
        if (recommendEmptyView != null) {
            recommendEmptyView.setFooterState(i2);
        }
    }

    public void setExtentMap(HashMap<String, String> hashMap) {
        RecommendProductManager recommendProductManager = this.mRecommendProductManager;
        if (recommendProductManager != null) {
            recommendProductManager.setExtentParam(hashMap);
        }
    }

    public void setIsEnableAutoLoad(boolean z) {
        this.isEnableAutoLoad = z;
    }

    public void setIsPreLoadEnable(boolean z) {
        this.isPreLoadEnable = z;
    }

    public void setOnRecommendContentListener(onRecommendContentListener onrecommendcontentlistener) {
        this.mOnRecommendContentListener = onrecommendcontentlistener;
    }

    public void setOnRequestResultListener(OnNewRequestResultListener onNewRequestResultListener) {
        this.onRequestResultListener = onNewRequestResultListener;
    }

    public void setRecommendConfig(RecommendConfig recommendConfig) {
        RecommendProductManager recommendProductManager = this.mRecommendProductManager;
        if (recommendProductManager != null) {
            recommendProductManager.setRecommendConfig(recommendConfig);
        }
        changeBgColor();
    }

    public void setRecommendTab(RecommendTab recommendTab) {
        RecommendProductManager recommendProductManager;
        this.mRecommendTab = recommendTab;
        if (recommendTab == null || (recommendProductManager = this.mRecommendProductManager) == null) {
            return;
        }
        recommendProductManager.setTabId(recommendTab.tabId);
        this.mRecommendProductManager.setSelectedRecommendTab(this.mRecommendTab);
    }

    public void setShowEmptyView(boolean z) {
        this.isShowEmptyView.set(z);
    }

    public void setSkus(String[] strArr) {
        RecommendProductManager recommendProductManager = this.mRecommendProductManager;
        if (recommendProductManager != null) {
            recommendProductManager.setSkus(strArr);
        }
    }

    @Override // com.jingdong.common.recommend.ScrollDispatchHelper.ScrollDispatchChild
    public void setTabSpreadState(boolean z) {
    }

    public void setTipsEvent(RecommendTipsEvent recommendTipsEvent) {
        RecommendProductManager recommendProductManager = this.mRecommendProductManager;
        if (recommendProductManager != null) {
            recommendProductManager.setTipsEvent(recommendTipsEvent);
        }
    }

    @Override // com.jingdong.common.recommend.ScrollDispatchHelper.ScrollDispatchChild
    public void setTopSpace(int i2) {
        this.topSpace = i2;
    }

    protected void useAnimator(int i2) {
        setLayoutTransition(null);
        if (this.recommendAnimator == null) {
            RecommendAnimator recommendAnimator = new RecommendAnimator();
            this.recommendAnimator = recommendAnimator;
            setItemAnimator(recommendAnimator);
        }
        this.recommendAnimator.setAnimationFrom(i2);
        RecyclerView.ItemAnimator itemAnimator = getItemAnimator();
        if (itemAnimator != null) {
            itemAnimator.setAddDuration(i2 == 1 ? 350L : 250L);
            itemAnimator.setChangeDuration(250L);
            itemAnimator.setMoveDuration(250L);
            itemAnimator.setRemoveDuration(i2 != 1 ? 250L : 350L);
            if (itemAnimator instanceof SimpleItemAnimator) {
                ((SimpleItemAnimator) itemAnimator).setSupportsChangeAnimations(true);
            }
        }
    }

    public void viewReset() {
        unUseAnimator();
        this.recommendLoading.set(false);
        this.isDestroy.set(true);
        this.mRecommendTabs = null;
        this.mRecommendHeaderData = null;
        this.mRecommendTab = null;
        scrollToTop();
        setEmptyViewState(1001);
        resetData();
        setBackGround(-592138);
    }

    public void onRangeChanged(int i2, int i3, Object obj) {
        RecommendAdapter recommendAdapter;
        if (isComputingLayout() || (recommendAdapter = this.mRecommendAdapter) == null) {
            return;
        }
        recommendAdapter.notifyItemRangeChanged(i2, i3, obj);
        onDataChange();
    }

    public void notifyItemRangeChanged(final int i2, final int i3, final Object obj) {
        if (isErrorRange(i2, i3)) {
            safeNotifyDataSetChanged();
        } else if (!isComputingLayout()) {
            onRangeChanged(i2, i3, obj);
        } else {
            BaseActivity baseActivity = this.mActivity;
            if (baseActivity == null) {
                return;
            }
            baseActivity.post(new Runnable() { // from class: com.jingdong.common.recommend.ui.RecommendChildRecyclerView.8
                {
                    RecommendChildRecyclerView.this = this;
                }

                @Override // java.lang.Runnable
                public void run() {
                    RecommendChildRecyclerView.this.onRangeChanged(i2, i3, obj);
                }
            });
        }
    }

    public RecommendChildRecyclerView(RecyclerView recyclerView, @NonNull BaseActivity baseActivity, int i2) {
        super(baseActivity);
        this.isShowEmptyView = new AtomicBoolean(false);
        this.currentPlan = "B";
        this.MIN_COUNT = 6;
        this.mPreCount = 0;
        this.mDefaultBg = new ColorDrawable(-592138);
        this.isScrollTop = new AtomicBoolean(false);
        this.isDestroy = new AtomicBoolean(false);
        this.recommendLoading = new AtomicBoolean(false);
        this.isLoadExpo = false;
        this.isAnimationEnable = new AtomicBoolean(true);
        this.canPreLoadNext = true;
        this.isPullToRefresh = false;
        this.isEnableAutoLoad = true;
        this.isPreLoadEnable = false;
        this.topSpace = 0;
        setAccessibilityDelegateCompat(new RecommendHRecyclerViewAccessibilityDelegate(this));
        this.mActivity = baseActivity;
        setOverScrollMode(2);
        if (recyclerView != null) {
            this.parentRecyclerView = recyclerView;
        }
        this.fromType = i2;
        if (this.mRecommendProductManager == null) {
            initRecommendManager(i2);
        }
        this.videoManagerNew = new RecommendVideoManagerNew(this, baseActivity, i2, this.mRecommendProductManager);
        FlingHelperUtil flingHelperUtil = new FlingHelperUtil(getContext());
        this.mFlingHelper = flingHelperUtil;
        this.mMaxDistance = flingHelperUtil.getVelocityByDistance(DPIUtil.getHeight() * 4);
        addOnScrollListener(new RecyclerView.OnScrollListener() { // from class: com.jingdong.common.recommend.ui.RecommendChildRecyclerView.1
            {
                RecommendChildRecyclerView.this = this;
            }

            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrollStateChanged(RecyclerView recyclerView2, int i3) {
                RecommendChildRecyclerView.this.scrollStateChange(i3);
            }

            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrolled(RecyclerView recyclerView2, int i3, int i4) {
                if (RecommendChildRecyclerView.this.isDestroy.get()) {
                    return;
                }
                RecommendChildRecyclerView recommendChildRecyclerView = RecommendChildRecyclerView.this;
                if (recommendChildRecyclerView.isEnableAutoLoad && recommendChildRecyclerView.isPreLoadEnable && recommendChildRecyclerView.canPreLoadNext) {
                    if (recommendChildRecyclerView.getLastVisibleItem() >= RecommendChildRecyclerView.this.getTotalItemCount() + (-5)) {
                        RecommendChildRecyclerView.this.loadRecommendData();
                        RecommendChildRecyclerView.this.canPreLoadNext = false;
                    }
                }
                if (RecommendChildRecyclerView.this.getFirstVisibleItem() > 0) {
                    RecommendChildRecyclerView.this.isScrollTop.set(false);
                }
            }
        });
        unUseAnimator();
    }
}

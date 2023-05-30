package com.jingdong.common.baseRecycleAdapter;

import android.animation.Animator;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import com.jingdong.common.baseRecycleAdapter.animation.AlphaInAnimation;
import com.jingdong.common.baseRecycleAdapter.animation.BaseAnimation;
import com.jingdong.common.baseRecycleAdapter.animation.ScaleInAnimation;
import com.jingdong.common.baseRecycleAdapter.animation.SlideInBottomAnimation;
import com.jingdong.common.baseRecycleAdapter.animation.SlideInLeftAnimation;
import com.jingdong.common.baseRecycleAdapter.animation.SlideInRightAnimation;
import com.jingdong.common.baseRecycleAdapter.entity.IExpandable;
import com.jingdong.common.baseRecycleAdapter.loadmore.JDSimpleLoadingMoreView;
import com.jingdong.common.baseRecycleAdapter.loadmore.LoadMoreView;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes5.dex */
public abstract class BaseQuickAdapter<T, K extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<K> {
    public static final int ALPHAIN = 1;
    public static final int EMPTY_VIEW = 1365;
    public static final int FOOTER_VIEW = 819;
    public static final int HEADER_VIEW = 273;
    public static final int LOADING_VIEW = 546;
    public static final int SCALEIN = 2;
    public static final int SLIDEIN_BOTTOM = 3;
    public static final int SLIDEIN_LEFT = 4;
    public static final int SLIDEIN_RIGHT = 5;
    protected static final String TAG = "BaseQuickAdapter";
    private boolean flag;
    private int mAutoLoadMoreSize;
    protected Context mContext;
    private BaseAnimation mCustomAnimation;
    protected List<T> mData;
    private int mDuration;
    private FrameLayout mEmptyLayout;
    private boolean mFirstOnlyEnable;
    private boolean mFootAndEmptyEnable;
    private LinearLayout mFooterLayout;
    private boolean mHeadAndEmptyEnable;
    private LinearLayout mHeaderLayout;
    private Interpolator mInterpolator;
    private boolean mIsUseEmpty;
    private int mLastPosition;
    protected LayoutInflater mLayoutInflater;
    protected int mLayoutResId;
    private boolean mLoadMoreEnable;
    private LoadMoreView mLoadMoreView;
    private boolean mLoading;
    private boolean mNextLoadEnable;
    private boolean mOpenAnimationEnable;
    private RequestLoadMoreListener mRequestLoadMoreListener;
    private BaseAnimation mSelectAnimation;
    private SpanSizeLookup mSpanSizeLookup;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface AnimationType {
    }

    /* loaded from: classes5.dex */
    public interface RequestLoadMoreListener {
        void onLoadMoreRequested();
    }

    /* loaded from: classes5.dex */
    public interface SpanSizeLookup {
        int getSpanSize(GridLayoutManager gridLayoutManager, int i2);
    }

    public BaseQuickAdapter(int i2, List<T> list) {
        this.mNextLoadEnable = false;
        this.mLoadMoreEnable = false;
        this.mLoading = false;
        this.mLoadMoreView = new JDSimpleLoadingMoreView();
        this.mFirstOnlyEnable = true;
        this.mOpenAnimationEnable = false;
        this.mInterpolator = new LinearInterpolator();
        this.mDuration = 300;
        this.mLastPosition = -1;
        this.mSelectAnimation = new AlphaInAnimation();
        this.mIsUseEmpty = true;
        this.flag = true;
        this.mAutoLoadMoreSize = 1;
        this.mData = list == null ? new ArrayList<>() : list;
        if (i2 != 0) {
            this.mLayoutResId = i2;
        }
    }

    private void addAnimation(RecyclerView.ViewHolder viewHolder) {
        if (this.mOpenAnimationEnable) {
            if (!this.mFirstOnlyEnable || viewHolder.getLayoutPosition() > this.mLastPosition) {
                BaseAnimation baseAnimation = this.mCustomAnimation;
                if (baseAnimation == null) {
                    baseAnimation = this.mSelectAnimation;
                }
                for (Animator animator : baseAnimation.getAnimators(viewHolder.itemView)) {
                    startAnim(animator, viewHolder.getLayoutPosition());
                }
                this.mLastPosition = viewHolder.getLayoutPosition();
            }
        }
    }

    private void compatibilityDataSizeChanged(int i2) {
        List<T> list = this.mData;
        if ((list == null ? 0 : list.size()) == i2) {
            notifyDataSetChanged();
        }
    }

    private IExpandable getExpandableItem(int i2) {
        T item = getItem(i2);
        if (isExpandable(item)) {
            return (IExpandable) item;
        }
        return null;
    }

    private int getFooterViewPosition() {
        int i2 = 1;
        if (getEmptyViewCount() == 1) {
            if (this.mHeadAndEmptyEnable && getHeaderLayoutCount() != 0) {
                i2 = 2;
            }
            if (this.mFootAndEmptyEnable) {
                return i2;
            }
            return -1;
        }
        return getHeaderLayoutCount() + this.mData.size();
    }

    private int getHeaderViewPosition() {
        return (getEmptyViewCount() != 1 || this.mHeadAndEmptyEnable) ? 0 : -1;
    }

    private int getItemPosition(T t) {
        List<T> list;
        if (t == null || (list = this.mData) == null || list.isEmpty()) {
            return -1;
        }
        return this.mData.indexOf(t);
    }

    private K getLoadingView(ViewGroup viewGroup) {
        K createBaseViewHolder = createBaseViewHolder(getItemView(this.mLoadMoreView.getLayoutId(), viewGroup));
        this.mLoadMoreView.setLoadingViewNew();
        createBaseViewHolder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.baseRecycleAdapter.BaseQuickAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (BaseQuickAdapter.this.mLoadMoreView.getLoadMoreStatus() == 3) {
                    BaseQuickAdapter.this.mLoadMoreView.setLoadMoreStatus(1);
                    BaseQuickAdapter baseQuickAdapter = BaseQuickAdapter.this;
                    baseQuickAdapter.notifyItemChanged(baseQuickAdapter.getHeaderLayoutCount() + BaseQuickAdapter.this.mData.size() + BaseQuickAdapter.this.getFooterLayoutCount());
                }
            }
        });
        return createBaseViewHolder;
    }

    private boolean hasSubItems(IExpandable iExpandable) {
        List<T> subItems;
        return (iExpandable == null || (subItems = iExpandable.getSubItems()) == null || subItems.size() <= 0) ? false : true;
    }

    private int recursiveCollapse(@IntRange(from = 0) int i2) {
        T item = getItem(i2);
        int i3 = 0;
        if (isExpandable(item)) {
            IExpandable iExpandable = (IExpandable) item;
            if (iExpandable.isExpanded()) {
                List<T> subItems = iExpandable.getSubItems();
                for (int size = subItems.size() - 1; size >= 0; size--) {
                    T t = subItems.get(size);
                    int itemPosition = getItemPosition(t);
                    if (itemPosition >= 0) {
                        if (t instanceof IExpandable) {
                            i3 += recursiveCollapse(itemPosition);
                        }
                        this.mData.remove(itemPosition);
                        i3++;
                    }
                }
            }
            return i3;
        }
        return 0;
    }

    private int recursiveExpand(int i2, @NonNull List list) {
        int size = (i2 + list.size()) - 1;
        int size2 = list.size() - 1;
        int i3 = 0;
        while (size2 >= 0) {
            if (list.get(size2) instanceof IExpandable) {
                IExpandable iExpandable = (IExpandable) list.get(size2);
                if (iExpandable.isExpanded() && hasSubItems(iExpandable)) {
                    List<T> subItems = iExpandable.getSubItems();
                    int i4 = size + 1;
                    this.mData.addAll(i4, subItems);
                    i3 += recursiveExpand(i4, subItems);
                }
            }
            size2--;
            size--;
        }
        return i3;
    }

    @Deprecated
    public void add(int i2, T t) {
        addData(i2, (int) t);
    }

    public void addData(int i2, T t) {
        this.mData.add(i2, t);
        notifyItemInserted(i2 + getHeaderLayoutCount());
        compatibilityDataSizeChanged(1);
    }

    public void addFooterView(View view) {
        addFooterView(view, -1, 1);
    }

    public void addHeaderView(View view) {
        addHeaderView(view, -1);
    }

    public void autoLoadMore(int i2) {
        if (getLoadMoreViewCount() != 0 && i2 >= getItemCount() - this.mAutoLoadMoreSize && this.mLoadMoreView.getLoadMoreStatus() == 1) {
            this.mLoadMoreView.setLoadMoreStatus(2);
            if (this.mLoading) {
                return;
            }
            this.mLoading = true;
            this.mRequestLoadMoreListener.onLoadMoreRequested();
        }
    }

    public int collapse(@IntRange(from = 0) int i2, boolean z, boolean z2) {
        int headerLayoutCount = i2 - getHeaderLayoutCount();
        IExpandable expandableItem = getExpandableItem(headerLayoutCount);
        if (expandableItem == null) {
            return 0;
        }
        int recursiveCollapse = recursiveCollapse(headerLayoutCount);
        expandableItem.setExpanded(false);
        int headerLayoutCount2 = headerLayoutCount + getHeaderLayoutCount();
        if (z2) {
            if (z) {
                notifyItemChanged(headerLayoutCount2);
                notifyItemRangeRemoved(headerLayoutCount2 + 1, recursiveCollapse);
            } else {
                notifyDataSetChanged();
            }
        }
        return recursiveCollapse;
    }

    protected abstract void convert(K k2, T t);

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract K createBaseViewHolder(View view);

    /* JADX INFO: Access modifiers changed from: protected */
    public K createBaseViewHolder(ViewGroup viewGroup, int i2) {
        return createBaseViewHolder(getItemView(i2, viewGroup));
    }

    public int expand(@IntRange(from = 0) int i2, boolean z, boolean z2) {
        int headerLayoutCount = i2 - getHeaderLayoutCount();
        IExpandable expandableItem = getExpandableItem(headerLayoutCount);
        int i3 = 0;
        if (expandableItem == null) {
            return 0;
        }
        if (!hasSubItems(expandableItem)) {
            expandableItem.setExpanded(false);
            return 0;
        }
        if (!expandableItem.isExpanded()) {
            List<T> subItems = expandableItem.getSubItems();
            int i4 = headerLayoutCount + 1;
            this.mData.addAll(i4, subItems);
            expandableItem.setExpanded(true);
            i3 = recursiveExpand(i4, subItems) + 0 + subItems.size();
        }
        int headerLayoutCount2 = headerLayoutCount + getHeaderLayoutCount();
        if (z2) {
            if (z) {
                notifyItemChanged(headerLayoutCount2);
                notifyItemRangeInserted(headerLayoutCount2 + 1, i3);
            } else {
                notifyDataSetChanged();
            }
        }
        return i3;
    }

    public int expandAll(int i2, boolean z, boolean z2) {
        T item;
        int headerLayoutCount = i2 - getHeaderLayoutCount();
        int i3 = headerLayoutCount + 1;
        T item2 = i3 < this.mData.size() ? getItem(i3) : null;
        if (hasSubItems(getExpandableItem(headerLayoutCount))) {
            int expand = expand(getHeaderLayoutCount() + headerLayoutCount, false, false);
            while (i3 < this.mData.size() && (item = getItem(i3)) != item2) {
                if (isExpandable(item)) {
                    expand += expand(getHeaderLayoutCount() + i3, false, false);
                }
                i3++;
            }
            if (z2) {
                if (z) {
                    notifyItemRangeInserted(headerLayoutCount + getHeaderLayoutCount() + 1, expand);
                } else {
                    notifyDataSetChanged();
                }
            }
            return expand;
        }
        return 0;
    }

    public List<T> getData() {
        return this.mData;
    }

    protected int getDefItemViewType(int i2) {
        return super.getItemViewType(i2);
    }

    public View getEmptyView() {
        return this.mEmptyLayout;
    }

    public int getEmptyViewCount() {
        FrameLayout frameLayout = this.mEmptyLayout;
        return (frameLayout == null || frameLayout.getChildCount() == 0 || !this.mIsUseEmpty || this.mData.size() != 0) ? 0 : 1;
    }

    public LinearLayout getFooterLayout() {
        return this.mFooterLayout;
    }

    public int getFooterLayoutCount() {
        LinearLayout linearLayout = this.mFooterLayout;
        return (linearLayout == null || linearLayout.getChildCount() == 0) ? 0 : 1;
    }

    @Deprecated
    public int getFooterViewsCount() {
        return getFooterLayoutCount();
    }

    public LinearLayout getHeaderLayout() {
        return this.mHeaderLayout;
    }

    public int getHeaderLayoutCount() {
        LinearLayout linearLayout = this.mHeaderLayout;
        return (linearLayout == null || linearLayout.getChildCount() == 0) ? 0 : 1;
    }

    @Deprecated
    public int getHeaderViewsCount() {
        return getHeaderLayoutCount();
    }

    public T getItem(int i2) {
        if (i2 != -1) {
            return this.mData.get(i2);
        }
        return null;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        int i2 = 1;
        if (getEmptyViewCount() != 1) {
            return getLoadMoreViewCount() + getHeaderLayoutCount() + this.mData.size() + getFooterLayoutCount();
        }
        if (this.mHeadAndEmptyEnable && getHeaderLayoutCount() != 0) {
            i2 = 2;
        }
        return (!this.mFootAndEmptyEnable || getFooterLayoutCount() == 0) ? i2 : i2 + 1;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public long getItemId(int i2) {
        return i2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public View getItemView(int i2, ViewGroup viewGroup) {
        return this.mLayoutInflater.inflate(i2, viewGroup, false);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i2) {
        if (getEmptyViewCount() == 1) {
            boolean z = this.mHeadAndEmptyEnable && getHeaderLayoutCount() != 0;
            return i2 != 0 ? i2 != 1 ? i2 != 2 ? 1365 : 819 : z ? 1365 : 819 : z ? 273 : 1365;
        }
        autoLoadMore(i2);
        int headerLayoutCount = getHeaderLayoutCount();
        if (i2 < headerLayoutCount) {
            return 273;
        }
        int i3 = i2 - headerLayoutCount;
        int size = this.mData.size();
        if (i3 < size) {
            return getDefItemViewType(i3);
        }
        return i3 - size < getFooterLayoutCount() ? 819 : 546;
    }

    public LoadMoreView getLoadMoreView() {
        return this.mLoadMoreView;
    }

    public int getLoadMoreViewCount() {
        if (this.mRequestLoadMoreListener == null || !this.mLoadMoreEnable) {
            return 0;
        }
        return ((this.mNextLoadEnable || !this.mLoadMoreView.isLoadEndMoreGone()) && this.mData.size() != 0) ? 1 : 0;
    }

    public int getParentPosition(@NonNull T t) {
        int itemPosition = getItemPosition(t);
        if (itemPosition == -1) {
            return -1;
        }
        int level = t instanceof IExpandable ? ((IExpandable) t).getLevel() : Integer.MAX_VALUE;
        if (level == 0) {
            return itemPosition;
        }
        if (level == -1) {
            return -1;
        }
        while (itemPosition >= 0) {
            T t2 = this.mData.get(itemPosition);
            if (t2 instanceof IExpandable) {
                IExpandable iExpandable = (IExpandable) t2;
                if (iExpandable.getLevel() >= 0 && iExpandable.getLevel() < level) {
                    return itemPosition;
                }
            }
            itemPosition--;
        }
        return -1;
    }

    public void isAutoDark(boolean z) {
        LoadMoreView loadMoreView = this.mLoadMoreView;
        if (loadMoreView != null) {
            loadMoreView.setAutoDark(z);
        }
    }

    public boolean isExpandable(T t) {
        return t != null && (t instanceof IExpandable);
    }

    public void isFirstOnly(boolean z) {
        this.mFirstOnlyEnable = z;
    }

    public boolean isLoadMoreEnable() {
        return this.mLoadMoreEnable;
    }

    public boolean isLoading() {
        return this.mLoading;
    }

    public void isUseEmpty(boolean z) {
        this.mIsUseEmpty = z;
    }

    public void loadMoreComplete() {
        if (getLoadMoreViewCount() == 0) {
            return;
        }
        this.mLoading = false;
        this.mLoadMoreView.setLoadMoreStatus(1);
        notifyItemChanged(getHeaderLayoutCount() + this.mData.size() + getFooterLayoutCount());
    }

    public void loadMoreEnd() {
        loadMoreEnd(false);
    }

    public void loadMoreFail() {
        if (getLoadMoreViewCount() == 0) {
            return;
        }
        this.mLoading = false;
        this.mLoadMoreView.setLoadMoreStatus(3);
        notifyItemChanged(getHeaderLayoutCount() + this.mData.size() + getFooterLayoutCount());
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            final GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() { // from class: com.jingdong.common.baseRecycleAdapter.BaseQuickAdapter.2
                @Override // androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
                public int getSpanSize(int i2) {
                    int itemViewType = BaseQuickAdapter.this.getItemViewType(i2);
                    if (BaseQuickAdapter.this.mSpanSizeLookup != null) {
                        return (itemViewType == 1365 || itemViewType == 273 || itemViewType == 819 || itemViewType == 546) ? gridLayoutManager.getSpanCount() : BaseQuickAdapter.this.mSpanSizeLookup.getSpanSize(gridLayoutManager, i2 - BaseQuickAdapter.this.getHeaderLayoutCount());
                    } else if (itemViewType == 1365 || itemViewType == 273 || itemViewType == 819 || itemViewType == 546) {
                        return gridLayoutManager.getSpanCount();
                    } else {
                        return 1;
                    }
                }
            });
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(K k2, int i2) {
        int itemViewType = k2.getItemViewType();
        if (itemViewType == 0) {
            convert(k2, this.mData.get(k2.getLayoutPosition() - getHeaderLayoutCount()));
        } else if (itemViewType != 273) {
            if (itemViewType == 546) {
                this.mLoadMoreView.convert(k2);
            } else if (itemViewType == 819 || itemViewType == 1365) {
            } else {
                convert(k2, this.mData.get(k2.getLayoutPosition() - getHeaderLayoutCount()));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public K onCreateDefViewHolder(ViewGroup viewGroup, int i2) {
        return createBaseViewHolder(viewGroup, this.mLayoutResId);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public K onCreateViewHolder(ViewGroup viewGroup, int i2) {
        Context context = viewGroup.getContext();
        this.mContext = context;
        this.mLayoutInflater = LayoutInflater.from(context);
        if (i2 != 273) {
            if (i2 != 546) {
                if (i2 != 819) {
                    if (i2 != 1365) {
                        return onCreateDefViewHolder(viewGroup, i2);
                    }
                    return createBaseViewHolder(this.mEmptyLayout);
                }
                return createBaseViewHolder(this.mFooterLayout);
            }
            return getLoadingView(viewGroup);
        }
        return createBaseViewHolder(this.mHeaderLayout);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onViewAttachedToWindow(K k2) {
        super.onViewAttachedToWindow(k2);
        int itemViewType = k2.getItemViewType();
        if (itemViewType != 1365 && itemViewType != 273 && itemViewType != 819 && itemViewType != 546) {
            addAnimation(k2);
        } else {
            setFullSpan(k2);
        }
    }

    public void openLoadAnimation(int i2) {
        this.mOpenAnimationEnable = true;
        this.mCustomAnimation = null;
        if (i2 == 1) {
            this.mSelectAnimation = new AlphaInAnimation();
        } else if (i2 == 2) {
            this.mSelectAnimation = new ScaleInAnimation();
        } else if (i2 == 3) {
            this.mSelectAnimation = new SlideInBottomAnimation();
        } else if (i2 == 4) {
            this.mSelectAnimation = new SlideInLeftAnimation();
        } else if (i2 != 5) {
        } else {
            this.mSelectAnimation = new SlideInRightAnimation();
        }
    }

    public void remove(int i2) {
        this.mData.remove(i2);
        notifyItemRemoved(i2 + getHeaderLayoutCount());
        compatibilityDataSizeChanged(0);
    }

    public void removeAllFooterView() {
        if (getFooterLayoutCount() == 0) {
            return;
        }
        this.mFooterLayout.removeAllViews();
        int footerViewPosition = getFooterViewPosition();
        if (footerViewPosition != -1) {
            notifyItemRemoved(footerViewPosition);
        }
    }

    public void removeAllHeaderView() {
        if (getHeaderLayoutCount() == 0) {
            return;
        }
        this.mHeaderLayout.removeAllViews();
        int headerViewPosition = getHeaderViewPosition();
        if (headerViewPosition != -1) {
            notifyItemRemoved(headerViewPosition);
        }
    }

    public void removeFooterView(View view) {
        int footerViewPosition;
        if (getFooterLayoutCount() == 0) {
            return;
        }
        this.mFooterLayout.removeView(view);
        if (this.mFooterLayout.getChildCount() != 0 || (footerViewPosition = getFooterViewPosition()) == -1) {
            return;
        }
        notifyItemRemoved(footerViewPosition);
    }

    public void removeHeaderView(View view) {
        int headerViewPosition;
        if (getHeaderLayoutCount() == 0) {
            return;
        }
        this.mHeaderLayout.removeView(view);
        if (this.mHeaderLayout.getChildCount() != 0 || (headerViewPosition = getHeaderViewPosition()) == -1) {
            return;
        }
        notifyItemRemoved(headerViewPosition);
    }

    public void setAutoLoadMoreSize(int i2) {
        if (i2 > 1) {
            this.mAutoLoadMoreSize = i2;
        }
    }

    public void setData(int i2, T t) {
        this.mData.set(i2, t);
        notifyItemChanged(i2 + getHeaderLayoutCount());
    }

    public void setDuration(int i2) {
        this.mDuration = i2;
    }

    public void setEmptyView(int i2, ViewGroup viewGroup) {
        setEmptyView(LayoutInflater.from(viewGroup.getContext()).inflate(i2, viewGroup, false));
    }

    public void setEnableLoadMore(boolean z) {
        int loadMoreViewCount = getLoadMoreViewCount();
        this.mLoadMoreEnable = z;
        int loadMoreViewCount2 = getLoadMoreViewCount();
        if (loadMoreViewCount == 1) {
            if (loadMoreViewCount2 == 0) {
                notifyItemRemoved(getHeaderLayoutCount() + this.mData.size() + getFooterLayoutCount());
            }
        } else if (loadMoreViewCount2 == 1) {
            this.mLoadMoreView.setLoadMoreStatus(1);
            notifyItemInserted(getHeaderLayoutCount() + this.mData.size() + getFooterLayoutCount());
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setFullSpan(RecyclerView.ViewHolder viewHolder) {
        if (viewHolder.itemView.getLayoutParams() instanceof StaggeredGridLayoutManager.LayoutParams) {
            ((StaggeredGridLayoutManager.LayoutParams) viewHolder.itemView.getLayoutParams()).setFullSpan(true);
        }
    }

    public void setHeaderAndEmpty(boolean z) {
        setHeaderFooterEmpty(z, false);
    }

    public void setHeaderFooterEmpty(boolean z, boolean z2) {
        this.mHeadAndEmptyEnable = z;
        this.mFootAndEmptyEnable = z2;
    }

    public void setHeaderView(View view) {
        setHeaderView(view, 0, 1);
    }

    public void setLoadMoreView(LoadMoreView loadMoreView) {
        this.mLoadMoreView = loadMoreView;
    }

    public void setNewData(List<T> list) {
        if (list == null) {
            list = new ArrayList<>();
        }
        this.mData = list;
        if (this.mRequestLoadMoreListener != null) {
            this.mNextLoadEnable = true;
            this.mLoadMoreEnable = true;
            this.mLoading = false;
            this.mLoadMoreView.setLoadMoreStatus(1);
        }
        this.mLastPosition = -1;
        notifyDataSetChanged();
    }

    public void setNotDoAnimationCount(int i2) {
        this.mLastPosition = i2;
    }

    public void setOnLoadMoreListener(RequestLoadMoreListener requestLoadMoreListener) {
        this.mRequestLoadMoreListener = requestLoadMoreListener;
        this.mNextLoadEnable = true;
        this.mLoadMoreEnable = true;
        this.mLoading = false;
    }

    public void setSpanSizeLookup(SpanSizeLookup spanSizeLookup) {
        this.mSpanSizeLookup = spanSizeLookup;
    }

    public void setUIMode(int i2) {
    }

    protected void startAnim(Animator animator, int i2) {
        animator.setDuration(this.mDuration).start();
        animator.setInterpolator(this.mInterpolator);
    }

    public void addFooterView(View view, int i2) {
        addFooterView(view, i2, 1);
    }

    public void addHeaderView(View view, int i2) {
        addHeaderView(view, i2, 1);
    }

    public void loadMoreEnd(boolean z) {
        if (getLoadMoreViewCount() == 0) {
            return;
        }
        this.mLoading = false;
        this.mNextLoadEnable = false;
        this.mLoadMoreView.setLoadMoreEndGone(z);
        if (z) {
            notifyItemRemoved(getHeaderLayoutCount() + this.mData.size() + getFooterLayoutCount());
            return;
        }
        this.mLoadMoreView.setLoadMoreStatus(4);
        notifyItemChanged(getHeaderLayoutCount() + this.mData.size() + getFooterLayoutCount());
    }

    public void setHeaderView(View view, int i2) {
        setHeaderView(view, i2, 1);
    }

    public void addFooterView(View view, int i2, int i3) {
        int footerViewPosition;
        if (this.mFooterLayout == null) {
            LinearLayout linearLayout = new LinearLayout(view.getContext());
            this.mFooterLayout = linearLayout;
            if (i3 == 1) {
                linearLayout.setOrientation(1);
                this.mFooterLayout.setLayoutParams(new RecyclerView.LayoutParams(-1, -2));
            } else {
                linearLayout.setOrientation(0);
                this.mFooterLayout.setLayoutParams(new RecyclerView.LayoutParams(-2, -1));
            }
        }
        if (i2 >= this.mFooterLayout.getChildCount()) {
            i2 = -1;
        }
        this.mFooterLayout.addView(view, i2);
        if (this.mFooterLayout.getChildCount() != 1 || (footerViewPosition = getFooterViewPosition()) == -1) {
            return;
        }
        notifyItemInserted(footerViewPosition);
    }

    public void addHeaderView(View view, int i2, int i3) {
        int headerViewPosition;
        if (this.mHeaderLayout == null) {
            LinearLayout linearLayout = new LinearLayout(view.getContext());
            this.mHeaderLayout = linearLayout;
            if (i3 == 1) {
                linearLayout.setOrientation(1);
                this.mHeaderLayout.setLayoutParams(new RecyclerView.LayoutParams(-1, -2));
            } else {
                linearLayout.setOrientation(0);
                this.mHeaderLayout.setLayoutParams(new RecyclerView.LayoutParams(-2, -1));
            }
        }
        if (i2 >= this.mHeaderLayout.getChildCount()) {
            i2 = -1;
        }
        this.mHeaderLayout.addView(view, i2);
        if (this.mHeaderLayout.getChildCount() != 1 || (headerViewPosition = getHeaderViewPosition()) == -1) {
            return;
        }
        notifyItemInserted(headerViewPosition);
    }

    public void setEmptyView(View view) {
        boolean z;
        int i2 = 0;
        if (this.mEmptyLayout == null) {
            this.mEmptyLayout = new FrameLayout(view.getContext());
            RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(-1, -1);
            ViewGroup.LayoutParams layoutParams2 = view.getLayoutParams();
            if (layoutParams2 != null) {
                ((ViewGroup.MarginLayoutParams) layoutParams).width = layoutParams2.width;
                ((ViewGroup.MarginLayoutParams) layoutParams).height = layoutParams2.height;
            }
            this.mEmptyLayout.setLayoutParams(layoutParams);
            z = true;
        } else {
            z = false;
        }
        this.mEmptyLayout.removeAllViews();
        this.mEmptyLayout.addView(view);
        this.mIsUseEmpty = true;
        if (z && getEmptyViewCount() == 1) {
            if (this.mHeadAndEmptyEnable && getHeaderLayoutCount() != 0) {
                i2 = 1;
            }
            notifyItemInserted(i2);
        }
    }

    public void setHeaderView(View view, int i2, int i3) {
        LinearLayout linearLayout = this.mHeaderLayout;
        if (linearLayout != null && linearLayout.getChildCount() > i2) {
            this.mHeaderLayout.removeViewAt(i2);
            this.mHeaderLayout.addView(view, i2);
            return;
        }
        addHeaderView(view, i2, i3);
    }

    public void addData(T t) {
        this.mData.add(t);
        notifyItemInserted(this.mData.size() + getHeaderLayoutCount());
        compatibilityDataSizeChanged(1);
    }

    public void addData(int i2, List<T> list) {
        this.mData.addAll(i2, list);
        notifyItemRangeInserted(i2 + getHeaderLayoutCount(), list.size());
        compatibilityDataSizeChanged(list.size());
    }

    public void openLoadAnimation(BaseAnimation baseAnimation) {
        this.mOpenAnimationEnable = true;
        this.mCustomAnimation = baseAnimation;
    }

    public int collapse(@IntRange(from = 0) int i2) {
        return collapse(i2, true, true);
    }

    public void addData(List<T> list) {
        this.mData.addAll(list);
        notifyItemRangeInserted((this.mData.size() - list.size()) + getHeaderLayoutCount(), list.size());
        compatibilityDataSizeChanged(list.size());
    }

    public int collapse(@IntRange(from = 0) int i2, boolean z) {
        return collapse(i2, z, true);
    }

    public void openLoadAnimation() {
        this.mOpenAnimationEnable = true;
    }

    public int expandAll(int i2, boolean z) {
        return expandAll(i2, true, !z);
    }

    public int expand(@IntRange(from = 0) int i2, boolean z) {
        return expand(i2, z, true);
    }

    public int expand(@IntRange(from = 0) int i2) {
        return expand(i2, true, true);
    }

    public BaseQuickAdapter(List<T> list) {
        this(0, list);
    }

    public BaseQuickAdapter(int i2) {
        this(i2, null);
    }
}

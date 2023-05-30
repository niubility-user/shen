package com.jingdong.common.ui;

import android.content.Context;
import android.database.DataSetObservable;
import android.database.DataSetObserver;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.WrapperListAdapter;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes6.dex */
public class GridViewWithHeaderAndFooter extends GridView {
    private static final String LOG_TAG = "grid-view-with-header-and-footer";
    private ArrayList<FixedViewInfo> mFooterViewInfos;
    private ArrayList<FixedViewInfo> mHeaderViewInfos;
    private int mNumColumns;
    private int mRowHeight;
    private View mViewForMeasureRowHeight;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes6.dex */
    public static class FixedViewInfo {
        public Object data;
        public boolean isSelectable;
        public View view;
        public ViewGroup viewContainer;

        private FixedViewInfo() {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes6.dex */
    public class FullWidthFixedViewLayout extends FrameLayout {
        public FullWidthFixedViewLayout(Context context) {
            super(context);
        }

        @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
        protected void onLayout(boolean z, int i2, int i3, int i4, int i5) {
            int paddingLeft = getPaddingLeft();
            if (paddingLeft != i2) {
                offsetLeftAndRight(paddingLeft - i2);
            }
            super.onLayout(z, i2, i3, i4, i5);
        }

        @Override // android.widget.FrameLayout, android.view.View
        protected void onMeasure(int i2, int i3) {
            super.onMeasure(View.MeasureSpec.makeMeasureSpec((GridViewWithHeaderAndFooter.this.getMeasuredWidth() - GridViewWithHeaderAndFooter.this.getPaddingLeft()) - GridViewWithHeaderAndFooter.this.getPaddingRight(), View.MeasureSpec.getMode(i2)), i3);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes6.dex */
    public static class HeaderViewGridAdapter implements WrapperListAdapter, Filterable {
        static final ArrayList<FixedViewInfo> EMPTY_INFO_LIST = new ArrayList<>();
        private final ListAdapter mAdapter;
        boolean mAreAllFixedViewsSelectable;
        ArrayList<FixedViewInfo> mFooterViewInfos;
        ArrayList<FixedViewInfo> mHeaderViewInfos;
        private final boolean mIsFilterable;
        private final DataSetObservable mDataSetObservable = new DataSetObservable();
        private int mNumColumns = 1;
        private int mRowHeight = -1;
        private boolean mCachePlaceHoldView = true;
        private boolean mCacheFirstHeaderView = false;

        public HeaderViewGridAdapter(ArrayList<FixedViewInfo> arrayList, ArrayList<FixedViewInfo> arrayList2, ListAdapter listAdapter) {
            this.mAdapter = listAdapter;
            this.mIsFilterable = listAdapter instanceof Filterable;
            if (arrayList == null) {
                this.mHeaderViewInfos = EMPTY_INFO_LIST;
            } else {
                this.mHeaderViewInfos = arrayList;
            }
            if (arrayList2 == null) {
                this.mFooterViewInfos = EMPTY_INFO_LIST;
            } else {
                this.mFooterViewInfos = arrayList2;
            }
            this.mAreAllFixedViewsSelectable = areAllListInfosSelectable(this.mHeaderViewInfos) && areAllListInfosSelectable(this.mFooterViewInfos);
        }

        private boolean areAllListInfosSelectable(ArrayList<FixedViewInfo> arrayList) {
            if (arrayList != null) {
                Iterator<FixedViewInfo> it = arrayList.iterator();
                while (it.hasNext()) {
                    if (!it.next().isSelectable) {
                        return false;
                    }
                }
                return true;
            }
            return true;
        }

        private int getAdapterAndPlaceHolderCount() {
            double ceil = Math.ceil((this.mAdapter.getCount() * 1.0f) / this.mNumColumns);
            double d = this.mNumColumns;
            Double.isNaN(d);
            return (int) (ceil * d);
        }

        @Override // android.widget.ListAdapter
        public boolean areAllItemsEnabled() {
            ListAdapter listAdapter = this.mAdapter;
            if (listAdapter != null) {
                return this.mAreAllFixedViewsSelectable && listAdapter.areAllItemsEnabled();
            }
            return true;
        }

        @Override // android.widget.Adapter
        public int getCount() {
            if (this.mAdapter != null) {
                return ((getFootersCount() + getHeadersCount()) * this.mNumColumns) + getAdapterAndPlaceHolderCount();
            }
            return (getFootersCount() + getHeadersCount()) * this.mNumColumns;
        }

        @Override // android.widget.Filterable
        public Filter getFilter() {
            if (this.mIsFilterable) {
                ListAdapter listAdapter = this.mAdapter;
                if (listAdapter instanceof Filterable) {
                    return ((Filterable) listAdapter).getFilter();
                }
            }
            return null;
        }

        public int getFootersCount() {
            return this.mFooterViewInfos.size();
        }

        public int getHeadersCount() {
            return this.mHeaderViewInfos.size();
        }

        @Override // android.widget.Adapter
        public Object getItem(int i2) {
            int headersCount = getHeadersCount();
            int i3 = this.mNumColumns;
            int i4 = headersCount * i3;
            if (i2 < i4) {
                if (i2 % i3 == 0) {
                    return this.mHeaderViewInfos.get(i2 / i3).data;
                }
                return null;
            }
            int i5 = i2 - i4;
            int i6 = 0;
            if (this.mAdapter != null && i5 < (i6 = getAdapterAndPlaceHolderCount())) {
                if (i5 < this.mAdapter.getCount()) {
                    return this.mAdapter.getItem(i5);
                }
                return null;
            }
            int i7 = i5 - i6;
            if (i7 % this.mNumColumns == 0) {
                return this.mFooterViewInfos.get(i7).data;
            }
            return null;
        }

        @Override // android.widget.Adapter
        public long getItemId(int i2) {
            int i3;
            int headersCount = getHeadersCount() * this.mNumColumns;
            ListAdapter listAdapter = this.mAdapter;
            if (listAdapter == null || i2 < headersCount || (i3 = i2 - headersCount) >= listAdapter.getCount()) {
                return -1L;
            }
            return this.mAdapter.getItemId(i3);
        }

        @Override // android.widget.Adapter
        public int getItemViewType(int i2) {
            int i3;
            int headersCount = getHeadersCount() * this.mNumColumns;
            int i4 = 0;
            int viewTypeCount = this.mAdapter == null ? 0 : r1.getViewTypeCount() - 1;
            int i5 = -2;
            if (this.mCachePlaceHoldView && i2 < headersCount) {
                if (i2 == 0 && this.mCacheFirstHeaderView) {
                    i5 = this.mHeaderViewInfos.size() + viewTypeCount + this.mFooterViewInfos.size() + 1 + 1;
                }
                int i6 = this.mNumColumns;
                if (i2 % i6 != 0) {
                    i5 = (i2 / i6) + 1 + viewTypeCount;
                }
            }
            int i7 = i2 - headersCount;
            if (this.mAdapter != null) {
                i4 = getAdapterAndPlaceHolderCount();
                if (i7 >= 0 && i7 < i4) {
                    if (i7 < this.mAdapter.getCount()) {
                        i5 = this.mAdapter.getItemViewType(i7);
                    } else if (this.mCachePlaceHoldView) {
                        i5 = this.mHeaderViewInfos.size() + viewTypeCount + 1;
                    }
                }
            }
            return (!this.mCachePlaceHoldView || (i3 = i7 - i4) < 0 || i3 >= getCount() || i3 % this.mNumColumns == 0) ? i5 : viewTypeCount + this.mHeaderViewInfos.size() + 1 + (i3 / this.mNumColumns) + 1;
        }

        @Override // android.widget.Adapter
        public View getView(int i2, View view, ViewGroup viewGroup) {
            int headersCount = getHeadersCount();
            int i3 = this.mNumColumns;
            int i4 = headersCount * i3;
            if (i2 < i4) {
                ViewGroup viewGroup2 = this.mHeaderViewInfos.get(i2 / i3).viewContainer;
                if (i2 % this.mNumColumns == 0) {
                    return viewGroup2;
                }
                if (view == null) {
                    view = new View(viewGroup.getContext());
                }
                view.setVisibility(4);
                view.setMinimumHeight(viewGroup2.getHeight());
                return view;
            }
            int i5 = i2 - i4;
            int i6 = 0;
            if (this.mAdapter != null && i5 < (i6 = getAdapterAndPlaceHolderCount())) {
                if (i5 < this.mAdapter.getCount()) {
                    try {
                        return this.mAdapter.getView(i5, view, viewGroup);
                    } catch (Exception unused) {
                        return new View(viewGroup.getContext());
                    }
                }
                if (view == null) {
                    view = new View(viewGroup.getContext());
                }
                view.setVisibility(4);
                view.setMinimumHeight(this.mRowHeight);
                return view;
            }
            int i7 = i5 - i6;
            if (i7 < getCount()) {
                ViewGroup viewGroup3 = this.mFooterViewInfos.get(i7 / this.mNumColumns).viewContainer;
                if (i2 % this.mNumColumns == 0) {
                    return viewGroup3;
                }
                if (view == null) {
                    view = new View(viewGroup.getContext());
                }
                view.setVisibility(4);
                view.setMinimumHeight(viewGroup3.getHeight());
                return view;
            }
            throw new ArrayIndexOutOfBoundsException(i2);
        }

        @Override // android.widget.Adapter
        public int getViewTypeCount() {
            ListAdapter listAdapter = this.mAdapter;
            int viewTypeCount = listAdapter == null ? 1 : listAdapter.getViewTypeCount();
            if (this.mCachePlaceHoldView) {
                int size = this.mHeaderViewInfos.size() + 1 + this.mFooterViewInfos.size();
                if (this.mCacheFirstHeaderView) {
                    size++;
                }
                return viewTypeCount + size;
            }
            return viewTypeCount;
        }

        @Override // android.widget.WrapperListAdapter
        public ListAdapter getWrappedAdapter() {
            return this.mAdapter;
        }

        @Override // android.widget.Adapter
        public boolean hasStableIds() {
            ListAdapter listAdapter = this.mAdapter;
            if (listAdapter != null) {
                return listAdapter.hasStableIds();
            }
            return false;
        }

        @Override // android.widget.Adapter
        public boolean isEmpty() {
            ListAdapter listAdapter = this.mAdapter;
            return (listAdapter == null || listAdapter.isEmpty()) && getHeadersCount() == 0 && getFootersCount() == 0;
        }

        @Override // android.widget.ListAdapter
        public boolean isEnabled(int i2) {
            int i3;
            int headersCount = getHeadersCount();
            int i4 = this.mNumColumns;
            int i5 = headersCount * i4;
            if (i2 < i5) {
                return i2 % i4 == 0 && this.mHeaderViewInfos.get(i2 / i4).isSelectable;
            }
            int i6 = i2 - i5;
            if (this.mAdapter != null) {
                i3 = getAdapterAndPlaceHolderCount();
                if (i6 < i3) {
                    return i6 < this.mAdapter.getCount() && this.mAdapter.isEnabled(i6);
                }
            } else {
                i3 = 0;
            }
            int i7 = i6 - i3;
            int i8 = this.mNumColumns;
            return i7 % i8 == 0 && this.mFooterViewInfos.get(i7 / i8).isSelectable;
        }

        public void notifyDataSetChanged() {
            this.mDataSetObservable.notifyChanged();
        }

        @Override // android.widget.Adapter
        public void registerDataSetObserver(DataSetObserver dataSetObserver) {
            this.mDataSetObservable.registerObserver(dataSetObserver);
            ListAdapter listAdapter = this.mAdapter;
            if (listAdapter != null) {
                listAdapter.registerDataSetObserver(dataSetObserver);
            }
        }

        public boolean removeFooter(View view) {
            boolean z = false;
            for (int i2 = 0; i2 < this.mFooterViewInfos.size(); i2++) {
                if (this.mFooterViewInfos.get(i2).view == view) {
                    this.mFooterViewInfos.remove(i2);
                    if (areAllListInfosSelectable(this.mHeaderViewInfos) && areAllListInfosSelectable(this.mFooterViewInfos)) {
                        z = true;
                    }
                    this.mAreAllFixedViewsSelectable = z;
                    this.mDataSetObservable.notifyChanged();
                    return true;
                }
            }
            return false;
        }

        public boolean removeHeader(View view) {
            boolean z = false;
            for (int i2 = 0; i2 < this.mHeaderViewInfos.size(); i2++) {
                if (this.mHeaderViewInfos.get(i2).view == view) {
                    this.mHeaderViewInfos.remove(i2);
                    if (areAllListInfosSelectable(this.mHeaderViewInfos) && areAllListInfosSelectable(this.mFooterViewInfos)) {
                        z = true;
                    }
                    this.mAreAllFixedViewsSelectable = z;
                    this.mDataSetObservable.notifyChanged();
                    return true;
                }
            }
            return false;
        }

        public void setNumColumns(int i2) {
            if (i2 >= 1 && this.mNumColumns != i2) {
                this.mNumColumns = i2;
                notifyDataSetChanged();
            }
        }

        public void setRowHeight(int i2) {
            this.mRowHeight = i2;
        }

        @Override // android.widget.Adapter
        public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {
            this.mDataSetObservable.unregisterObserver(dataSetObserver);
            ListAdapter listAdapter = this.mAdapter;
            if (listAdapter != null) {
                listAdapter.unregisterDataSetObserver(dataSetObserver);
            }
        }
    }

    public GridViewWithHeaderAndFooter(Context context) {
        super(context);
        this.mNumColumns = -1;
        this.mViewForMeasureRowHeight = null;
        this.mRowHeight = -1;
        this.mHeaderViewInfos = new ArrayList<>();
        this.mFooterViewInfos = new ArrayList<>();
        initHeaderGridView();
    }

    private int getNumColumnsCompatible() {
        if (Build.VERSION.SDK_INT >= 11) {
            return super.getNumColumns();
        }
        try {
            Field declaredField = getClass().getSuperclass().getDeclaredField("mNumColumns");
            declaredField.setAccessible(true);
            return declaredField.getInt(this);
        } catch (Exception unused) {
            int i2 = this.mNumColumns;
            if (i2 != -1) {
                return i2;
            }
            throw new RuntimeException("Can not determine the mNumColumns for this API platform, please call setNumColumns to set it.");
        }
    }

    private void initHeaderGridView() {
    }

    private void removeFixedViewInfo(View view, ArrayList<FixedViewInfo> arrayList) {
        int size = arrayList.size();
        for (int i2 = 0; i2 < size; i2++) {
            if (arrayList.get(i2).view == view) {
                arrayList.remove(i2);
                return;
            }
        }
    }

    public void addFooterView(View view) {
        addFooterView(view, null, true);
    }

    public void addHeaderView(View view) {
        addHeaderView(view, null, true);
    }

    protected int getColumnWidthCompatible() {
        if (Build.VERSION.SDK_INT >= 16) {
            return super.getColumnWidth();
        }
        try {
            Field declaredField = getClass().getSuperclass().getDeclaredField("mColumnWidth");
            declaredField.setAccessible(true);
            return declaredField.getInt(this);
        } catch (IllegalAccessException e2) {
            throw new RuntimeException(e2);
        } catch (NoSuchFieldException e3) {
            throw new RuntimeException(e3);
        }
    }

    public int getFooterViewCount() {
        return this.mFooterViewInfos.size();
    }

    public int getHeaderViewCount() {
        return this.mHeaderViewInfos.size();
    }

    public int getRowHeight() {
        int i2 = this.mRowHeight;
        if (i2 > 0) {
            return i2;
        }
        ListAdapter adapter = getAdapter();
        int numColumnsCompatible = getNumColumnsCompatible();
        if (adapter == null || adapter.getCount() <= (this.mHeaderViewInfos.size() + this.mFooterViewInfos.size()) * numColumnsCompatible) {
            return -1;
        }
        int columnWidthCompatible = getColumnWidthCompatible();
        View view = getAdapter().getView(numColumnsCompatible * this.mHeaderViewInfos.size(), this.mViewForMeasureRowHeight, this);
        if (view.getLayoutParams() instanceof AbsListView.LayoutParams) {
            AbsListView.LayoutParams layoutParams = (AbsListView.LayoutParams) view.getLayoutParams();
            if (layoutParams == null) {
                layoutParams = new AbsListView.LayoutParams(-1, -2, 0);
                view.setLayoutParams(layoutParams);
            }
            view.measure(GridView.getChildMeasureSpec(View.MeasureSpec.makeMeasureSpec(columnWidthCompatible, 1073741824), 0, layoutParams.width), GridView.getChildMeasureSpec(View.MeasureSpec.makeMeasureSpec(0, 0), 0, layoutParams.height));
            this.mViewForMeasureRowHeight = view;
            int measuredHeight = view.getMeasuredHeight();
            this.mRowHeight = measuredHeight;
            return measuredHeight;
        }
        return -1;
    }

    public void invalidateRowHeight() {
        this.mRowHeight = -1;
    }

    @Override // android.widget.AbsListView, android.widget.AdapterView, android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.mViewForMeasureRowHeight = null;
    }

    @Override // android.widget.GridView, android.widget.AbsListView, android.view.View
    protected void onMeasure(int i2, int i3) {
        super.onMeasure(i2, i3);
        ListAdapter adapter = getAdapter();
        if (adapter == null || !(adapter instanceof HeaderViewGridAdapter)) {
            return;
        }
        HeaderViewGridAdapter headerViewGridAdapter = (HeaderViewGridAdapter) adapter;
        headerViewGridAdapter.setNumColumns(getNumColumnsCompatible());
        headerViewGridAdapter.setRowHeight(getRowHeight());
    }

    public boolean removeFooterView(View view) {
        boolean z = false;
        if (this.mFooterViewInfos.size() > 0) {
            ListAdapter adapter = getAdapter();
            if (adapter != null && ((HeaderViewGridAdapter) adapter).removeFooter(view)) {
                z = true;
            }
            removeFixedViewInfo(view, this.mFooterViewInfos);
        }
        return z;
    }

    public boolean removeHeaderView(View view) {
        boolean z = false;
        if (this.mHeaderViewInfos.size() > 0) {
            ListAdapter adapter = getAdapter();
            if (adapter != null && ((HeaderViewGridAdapter) adapter).removeHeader(view)) {
                z = true;
            }
            removeFixedViewInfo(view, this.mHeaderViewInfos);
        }
        return z;
    }

    @Override // android.view.ViewGroup
    public void setClipChildren(boolean z) {
    }

    public void setClipChildrenSupper(boolean z) {
        super.setClipChildren(false);
    }

    @Override // android.widget.GridView
    public void setNumColumns(int i2) {
        super.setNumColumns(i2);
        this.mNumColumns = i2;
        ListAdapter adapter = getAdapter();
        if (adapter == null || !(adapter instanceof HeaderViewGridAdapter)) {
            return;
        }
        ((HeaderViewGridAdapter) adapter).setNumColumns(i2);
    }

    public void tryToScrollToBottomSmoothly() {
        int count = getAdapter().getCount() - 1;
        if (Build.VERSION.SDK_INT >= 11) {
            smoothScrollToPositionFromTop(count, 0);
        } else {
            setSelection(count);
        }
    }

    public void addFooterView(View view, Object obj, boolean z) {
        ListAdapter adapter = getAdapter();
        if (adapter != null && !(adapter instanceof HeaderViewGridAdapter)) {
            throw new IllegalStateException("Cannot add header view to grid -- setAdapter has already been called.");
        }
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        FixedViewInfo fixedViewInfo = new FixedViewInfo();
        FullWidthFixedViewLayout fullWidthFixedViewLayout = new FullWidthFixedViewLayout(getContext());
        if (layoutParams != null) {
            view.setLayoutParams(new FrameLayout.LayoutParams(layoutParams.width, layoutParams.height));
            fullWidthFixedViewLayout.setLayoutParams(new AbsListView.LayoutParams(layoutParams.width, layoutParams.height));
        }
        fullWidthFixedViewLayout.addView(view);
        fixedViewInfo.view = view;
        fixedViewInfo.viewContainer = fullWidthFixedViewLayout;
        fixedViewInfo.data = obj;
        fixedViewInfo.isSelectable = z;
        this.mFooterViewInfos.add(fixedViewInfo);
        if (adapter != null) {
            ((HeaderViewGridAdapter) adapter).notifyDataSetChanged();
        }
    }

    public void addHeaderView(View view, Object obj, boolean z) {
        ListAdapter adapter = getAdapter();
        if (adapter != null && !(adapter instanceof HeaderViewGridAdapter)) {
            throw new IllegalStateException("Cannot add header view to grid -- setAdapter has already been called.");
        }
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        FixedViewInfo fixedViewInfo = new FixedViewInfo();
        FullWidthFixedViewLayout fullWidthFixedViewLayout = new FullWidthFixedViewLayout(getContext());
        if (layoutParams != null) {
            view.setLayoutParams(new FrameLayout.LayoutParams(layoutParams.width, layoutParams.height));
            fullWidthFixedViewLayout.setLayoutParams(new AbsListView.LayoutParams(layoutParams.width, layoutParams.height));
        }
        fullWidthFixedViewLayout.addView(view);
        fixedViewInfo.view = view;
        fixedViewInfo.viewContainer = fullWidthFixedViewLayout;
        fixedViewInfo.data = obj;
        fixedViewInfo.isSelectable = z;
        this.mHeaderViewInfos.add(fixedViewInfo);
        if (adapter != null) {
            ((HeaderViewGridAdapter) adapter).notifyDataSetChanged();
        }
    }

    @Override // android.widget.AdapterView
    public void setAdapter(ListAdapter listAdapter) {
        if (this.mHeaderViewInfos.size() <= 0 && this.mFooterViewInfos.size() <= 0) {
            super.setAdapter(listAdapter);
            return;
        }
        HeaderViewGridAdapter headerViewGridAdapter = new HeaderViewGridAdapter(this.mHeaderViewInfos, this.mFooterViewInfos, listAdapter);
        int numColumnsCompatible = getNumColumnsCompatible();
        if (numColumnsCompatible > 1) {
            headerViewGridAdapter.setNumColumns(numColumnsCompatible);
        }
        headerViewGridAdapter.setRowHeight(getRowHeight());
        super.setAdapter((ListAdapter) headerViewGridAdapter);
    }

    public void tryToScrollToBottomSmoothly(int i2) {
        int count = getAdapter().getCount() - 1;
        if (Build.VERSION.SDK_INT >= 11) {
            smoothScrollToPositionFromTop(count, 0, i2);
        } else {
            setSelection(count);
        }
    }

    public GridViewWithHeaderAndFooter(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mNumColumns = -1;
        this.mViewForMeasureRowHeight = null;
        this.mRowHeight = -1;
        this.mHeaderViewInfos = new ArrayList<>();
        this.mFooterViewInfos = new ArrayList<>();
        initHeaderGridView();
    }

    public GridViewWithHeaderAndFooter(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mNumColumns = -1;
        this.mViewForMeasureRowHeight = null;
        this.mRowHeight = -1;
        this.mHeaderViewInfos = new ArrayList<>();
        this.mFooterViewInfos = new ArrayList<>();
        initHeaderGridView();
    }
}

package com.jingdong.common.stickyListView;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.WrapperListAdapter;
import java.util.List;

/* loaded from: classes6.dex */
public class StickyHeaderListView extends ListView implements AbsListView.OnScrollListener {
    private SparseArray<View> cacheView;
    private StickyHeaderAdapter mAdapter;
    private View mCurrentHeader;
    private float mHeaderOffset;
    private int mHeightMode;
    private AbsListView.OnScrollListener mOnScrollListener;
    private boolean mShouldPin;
    private int mWidthMode;
    private SparseIntArray posToHeaderPos;
    private int stickyHeaderHeight;

    /* loaded from: classes6.dex */
    public interface IStickyHeaderAdapter {
        void changeHeaderStatus(boolean z);

        int changeToDataPosition(int i2);

        int changeToRealPosition(int i2);

        View getHeaderViewOrNull(int i2, View view, ViewGroup viewGroup);

        boolean isHeaderView(int i2);
    }

    public StickyHeaderListView(Context context) {
        super(context);
        this.stickyHeaderHeight = -1;
        this.posToHeaderPos = new SparseIntArray();
        this.mShouldPin = true;
        this.cacheView = new SparseArray<>();
        super.setOnScrollListener(this);
    }

    private int changeToHeaderIndex(int i2) {
        return this.posToHeaderPos.get(i2, -1);
    }

    private void ensureStickyHeaderLayout(View view) {
        int makeMeasureSpec;
        int i2;
        if (view == null || !view.isLayoutRequested()) {
            return;
        }
        int makeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), this.mWidthMode);
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams != null && (i2 = layoutParams.height) > 0) {
            makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(i2, 1073741824);
        } else {
            makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
        }
        view.measure(makeMeasureSpec2, makeMeasureSpec);
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
    }

    private int getStickyHeaderHeight() {
        View view;
        if (this.stickyHeaderHeight == -1 && (view = this.mCurrentHeader) != null) {
            this.stickyHeaderHeight = view.getMeasuredHeight();
        }
        return this.stickyHeaderHeight;
    }

    private void refreshCache() {
        this.posToHeaderPos.clear();
        this.cacheView.clear();
        updateCache();
    }

    private void updateCache() {
        int count;
        int size;
        ListAdapter listAdapter = this.mAdapter;
        if (listAdapter instanceof WrapperListAdapter) {
            listAdapter = ((WrapperListAdapter) listAdapter).getWrappedAdapter();
        }
        List<Integer> headers = this.mAdapter.getHeaders();
        if (listAdapter == null || headers == null || headers.isEmpty() || (count = listAdapter.getCount()) < (size = headers.size())) {
            return;
        }
        int i2 = 0;
        while (i2 < headers.size()) {
            int intValue = headers.get(i2).intValue();
            i2++;
            int intValue2 = i2 > size + (-1) ? count : headers.get(i2).intValue();
            for (int i3 = intValue; i3 < intValue2; i3++) {
                this.posToHeaderPos.put(i3, intValue);
            }
        }
    }

    @Override // android.widget.ListView, android.widget.AbsListView, android.view.ViewGroup, android.view.View
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        if (this.mAdapter == null || !this.mShouldPin || this.mCurrentHeader == null) {
            return;
        }
        int save = canvas.save();
        canvas.translate(0.0f, this.mHeaderOffset);
        canvas.clipRect(0, 0, getWidth(), this.mCurrentHeader.getMeasuredHeight());
        this.mCurrentHeader.draw(canvas);
        canvas.restoreToCount(save);
    }

    public View getSectionHeaderView(int i2, View view) {
        int changeToHeaderIndex = changeToHeaderIndex(i2);
        View view2 = this.cacheView.get(changeToHeaderIndex);
        if (view2 == null) {
            view2 = this.mAdapter.getHeaderViewOrNull(changeToHeaderIndex, view, this);
        }
        if (view2 != null) {
            view = view2;
        }
        this.cacheView.put(changeToHeaderIndex, view);
        return view;
    }

    @Override // android.widget.ListView, android.widget.AbsListView, android.view.View
    protected void onMeasure(int i2, int i3) {
        super.onMeasure(i2, i3);
        this.mWidthMode = View.MeasureSpec.getMode(i2);
        this.mHeightMode = View.MeasureSpec.getMode(i3);
    }

    @Override // android.widget.AbsListView.OnScrollListener
    public void onScroll(AbsListView absListView, int i2, int i3, int i4) {
        AbsListView.OnScrollListener onScrollListener = this.mOnScrollListener;
        if (onScrollListener != null) {
            onScrollListener.onScroll(absListView, i2, i3, i4);
        }
        if (this.mAdapter != null && this.mShouldPin && i2 >= getHeaderViewsCount()) {
            int headerViewsCount = i2 - getHeaderViewsCount();
            View sectionHeaderView = getSectionHeaderView(headerViewsCount, this.mCurrentHeader);
            this.mCurrentHeader = sectionHeaderView;
            ensureStickyHeaderLayout(sectionHeaderView);
            this.mHeaderOffset = 0.0f;
            for (int i5 = headerViewsCount; i5 < headerViewsCount + i3; i5++) {
                if (this.mAdapter.isHeaderView(i5)) {
                    View childAt = getChildAt(i5 - headerViewsCount);
                    float top = childAt.getTop();
                    childAt.setVisibility(0);
                    if (getStickyHeaderHeight() >= top && top >= -1.0f) {
                        this.mHeaderOffset = top - childAt.getHeight();
                    } else if (top < -1.0f) {
                        childAt.setVisibility(4);
                    }
                }
            }
            invalidate();
            return;
        }
        this.mCurrentHeader = null;
        this.mHeaderOffset = 0.0f;
        for (int i6 = i2; i6 < i2 + i3; i6++) {
            View childAt2 = getChildAt(i6);
            if (childAt2 != null) {
                childAt2.setVisibility(0);
            }
        }
    }

    @Override // android.widget.AbsListView.OnScrollListener
    public void onScrollStateChanged(AbsListView absListView, int i2) {
        AbsListView.OnScrollListener onScrollListener = this.mOnScrollListener;
        if (onScrollListener != null) {
            onScrollListener.onScrollStateChanged(absListView, i2);
        }
    }

    @Override // android.widget.AbsListView
    public void setOnScrollListener(AbsListView.OnScrollListener onScrollListener) {
        this.mOnScrollListener = onScrollListener;
    }

    public void setStickyHeaders(boolean z) {
        this.mShouldPin = z;
    }

    @Override // android.widget.AdapterView
    public void setAdapter(ListAdapter listAdapter) {
        this.mCurrentHeader = null;
        if (listAdapter instanceof StickyHeaderAdapter) {
            this.mAdapter = (StickyHeaderAdapter) listAdapter;
            refreshCache();
        }
        super.setAdapter(listAdapter);
    }

    public StickyHeaderListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.stickyHeaderHeight = -1;
        this.posToHeaderPos = new SparseIntArray();
        this.mShouldPin = true;
        this.cacheView = new SparseArray<>();
        super.setOnScrollListener(this);
    }

    public StickyHeaderListView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.stickyHeaderHeight = -1;
        this.posToHeaderPos = new SparseIntArray();
        this.mShouldPin = true;
        this.cacheView = new SparseArray<>();
        super.setOnScrollListener(this);
    }
}

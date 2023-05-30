package com.jingdong.common.tagcloud;

import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/* loaded from: classes6.dex */
public class CloudTagLayout extends ViewGroup {
    private boolean isRight;
    private BaseAdapter mAdapter;
    private int mColumnSize;
    private int mLineSpacing;
    private TagItemClickListener mListener;
    private TagItemLongClickListener mLongListener;
    private DataChangeObserver mObserver;
    private int mTagSpacing;
    private int offLeft;

    /* loaded from: classes6.dex */
    class DataChangeObserver extends DataSetObserver {
        DataChangeObserver() {
        }

        @Override // android.database.DataSetObserver
        public void onChanged() {
            CloudTagLayout.this.drawLayout();
        }

        @Override // android.database.DataSetObserver
        public void onInvalidated() {
            super.onInvalidated();
        }
    }

    /* loaded from: classes6.dex */
    public interface TagItemClickListener {
        void itemClick(int i2);
    }

    /* loaded from: classes6.dex */
    public interface TagItemLongClickListener {
        void itemLongClick(int i2);
    }

    public CloudTagLayout(Context context) {
        super(context);
        init(context, null, 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void drawLayout() {
        BaseAdapter baseAdapter = this.mAdapter;
        if (baseAdapter == null || baseAdapter.getCount() == 0) {
            return;
        }
        removeAllViews();
        for (final int i2 = 0; i2 < this.mAdapter.getCount(); i2++) {
            View view = this.mAdapter.getView(i2, null, null);
            view.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.tagcloud.CloudTagLayout.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    if (CloudTagLayout.this.mListener != null) {
                        CloudTagLayout.this.mListener.itemClick(i2);
                    }
                }
            });
            view.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.jingdong.common.tagcloud.CloudTagLayout.2
                @Override // android.view.View.OnLongClickListener
                public boolean onLongClick(View view2) {
                    if (CloudTagLayout.this.mLongListener != null) {
                        CloudTagLayout.this.mLongListener.itemLongClick(i2);
                        return true;
                    }
                    return false;
                }
            });
            addView(view);
        }
    }

    private void init(Context context, AttributeSet attributeSet, int i2) {
        CloudTagConfiguration cloudTagConfiguration = new CloudTagConfiguration(context, attributeSet);
        this.mLineSpacing = cloudTagConfiguration.getLineSpacing();
        this.mTagSpacing = cloudTagConfiguration.getTagSpacing();
        this.mColumnSize = cloudTagConfiguration.getColumnSize();
        this.isRight = cloudTagConfiguration.isRight();
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new ViewGroup.LayoutParams(getContext(), attributeSet);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        int i6 = i4 - i2;
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight = getPaddingRight();
        int i7 = this.offLeft + paddingLeft;
        int i8 = 0;
        for (int i9 = 0; i9 < getChildCount(); i9++) {
            View childAt = getChildAt(i9);
            if (childAt.getVisibility() != 8) {
                int measuredWidth = childAt.getMeasuredWidth();
                int measuredHeight = childAt.getMeasuredHeight();
                i8 = Math.max(measuredHeight, i8);
                if (i7 + measuredWidth + paddingRight > i6) {
                    paddingTop += this.mLineSpacing + i8;
                    i7 = paddingLeft;
                    i8 = measuredHeight;
                }
                childAt.layout(i7, paddingTop, i7 + measuredWidth, measuredHeight + paddingTop);
                i7 += measuredWidth + this.mTagSpacing;
            }
        }
    }

    @Override // android.view.View
    protected void onMeasure(int i2, int i3) {
        int resolveSize = ViewGroup.resolveSize(0, i2);
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();
        int i4 = paddingLeft;
        int i5 = paddingTop;
        int i6 = 0;
        int i7 = 0;
        int i8 = 0;
        while (i6 < getChildCount()) {
            View childAt = getChildAt(i6);
            ViewGroup.LayoutParams layoutParams = childAt.getLayoutParams();
            int i9 = paddingLeft;
            childAt.measure(ViewGroup.getChildMeasureSpec(i2, paddingLeft + paddingRight, layoutParams.width), ViewGroup.getChildMeasureSpec(i3, paddingTop + paddingBottom, layoutParams.height));
            int measuredHeight = childAt.getMeasuredHeight();
            int measuredWidth = childAt.getMeasuredWidth();
            i7 = Math.max(measuredHeight, i7);
            if (i4 + measuredWidth + paddingRight > resolveSize) {
                i8++;
                int i10 = this.mColumnSize;
                if (i10 > 0 && i8 == i10) {
                    break;
                }
                i5 += this.mLineSpacing + measuredHeight;
                i7 = measuredHeight;
                i4 = i9;
            }
            i4 += measuredWidth + this.mTagSpacing;
            i6++;
            paddingLeft = i9;
        }
        if (this.mColumnSize == 1 && this.isRight) {
            this.offLeft = resolveSize - i4;
        }
        setMeasuredDimension(resolveSize, ViewGroup.resolveSize(0 + i5 + i7 + paddingBottom, i3));
    }

    public void setAdapter(BaseAdapter baseAdapter) {
        if (this.mAdapter == null) {
            this.mAdapter = baseAdapter;
            if (this.mObserver == null) {
                DataChangeObserver dataChangeObserver = new DataChangeObserver();
                this.mObserver = dataChangeObserver;
                this.mAdapter.registerDataSetObserver(dataChangeObserver);
            }
            drawLayout();
        }
    }

    public void setItemClickListener(TagItemClickListener tagItemClickListener) {
        this.mListener = tagItemClickListener;
    }

    public void setItemLongClickListener(TagItemLongClickListener tagItemLongClickListener) {
        this.mLongListener = tagItemLongClickListener;
    }

    public CloudTagLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context, attributeSet, 0);
    }

    public CloudTagLayout(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        init(context, attributeSet, i2);
    }
}

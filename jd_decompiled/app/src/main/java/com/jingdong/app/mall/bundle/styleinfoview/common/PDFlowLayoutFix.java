package com.jingdong.app.mall.bundle.styleinfoview.common;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import com.jingdong.app.mall.bundle.styleinfoview.utils.PDUtils;
import java.util.ArrayList;

/* loaded from: classes3.dex */
public class PDFlowLayoutFix extends ViewGroup {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private int curRowNum;
    private int mHeight;
    private int maxRows;
    private int padH;
    private int padV;
    private ArrayList<Integer> rowHeight;
    private RowNumListener rowNumListener;

    /* loaded from: classes3.dex */
    public interface RowNumListener {
        void overflow(int i2);
    }

    public PDFlowLayoutFix(Context context) {
        super(context);
        this.maxRows = -1;
        this.padH = PDUtils.dip2px(15.0f);
        this.padV = PDUtils.dip2px(15.0f);
        this.curRowNum = 0;
        this.rowHeight = new ArrayList<>();
    }

    public int getCurRowNum() {
        return this.curRowNum;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        RowNumListener rowNumListener;
        int i6;
        int i7;
        this.rowHeight.clear();
        int i8 = i4 - i2;
        int paddingLeft = (i8 - getPaddingLeft()) - getPaddingRight();
        int paddingTop = getPaddingTop();
        int childCount = getChildCount();
        int i9 = 0;
        int i10 = 0;
        for (int i11 = 0; i11 < childCount; i11++) {
            View childAt = getChildAt(i11);
            if (childAt.getVisibility() != 8) {
                int measuredWidth = childAt.getMeasuredWidth();
                int measuredHeight = childAt.getMeasuredHeight();
                if (i10 + measuredWidth > paddingLeft) {
                    this.rowHeight.add(Integer.valueOf(i9));
                    i9 = measuredHeight;
                    i10 = 0;
                } else {
                    i9 = Math.max(measuredHeight, i9);
                }
                i10 += measuredWidth + this.padH;
            }
        }
        this.rowHeight.add(Integer.valueOf(i9));
        int paddingLeft2 = getPaddingLeft();
        int i12 = 0;
        for (int i13 = 0; i13 < childCount; i13++) {
            View childAt2 = getChildAt(i13);
            if (childAt2.getVisibility() != 8) {
                int measuredWidth2 = childAt2.getMeasuredWidth();
                int measuredHeight2 = childAt2.getMeasuredHeight();
                if (paddingLeft2 + measuredWidth2 > i8 - getPaddingRight()) {
                    try {
                        i6 = this.rowHeight.get(i12).intValue();
                    } catch (Exception unused) {
                        i6 = measuredHeight2;
                    }
                    paddingTop += i6 + this.padV;
                    i12++;
                    paddingLeft2 = getPaddingLeft();
                }
                int i14 = this.maxRows;
                if (i14 != -1 && i12 >= i14) {
                    return;
                }
                try {
                    i7 = this.rowHeight.get(i12).intValue() - measuredHeight2;
                } catch (Exception unused2) {
                    i7 = 0;
                }
                childAt2.layout(paddingLeft2, paddingTop + i7, paddingLeft2 + measuredWidth2, measuredHeight2 + paddingTop + i7);
                paddingLeft2 += measuredWidth2 + this.padH;
            }
        }
        if (i12 < this.maxRows || (rowNumListener = this.rowNumListener) == null) {
            return;
        }
        rowNumListener.overflow(i12);
    }

    @Override // android.view.View
    protected void onMeasure(int i2, int i3) {
        int i4;
        int size = (View.MeasureSpec.getSize(i2) - getPaddingLeft()) - getPaddingRight();
        int size2 = (View.MeasureSpec.getSize(i3) - getPaddingTop()) - getPaddingBottom();
        int childCount = getChildCount();
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        this.mHeight = 0;
        this.curRowNum = 0;
        for (int i5 = 0; i5 < childCount; i5++) {
            View childAt = getChildAt(i5);
            if (childAt.getVisibility() != 8) {
                measureChild(childAt, i2, i3);
                int measuredWidth = childAt.getMeasuredWidth();
                if (paddingLeft + measuredWidth > getPaddingLeft() + size) {
                    int i6 = this.curRowNum + 1;
                    this.curRowNum = i6;
                    int i7 = this.maxRows;
                    if (i7 == -1 || (i7 != -1 && i6 < i7)) {
                        paddingLeft = getPaddingLeft();
                        paddingTop += this.mHeight + this.padV;
                        this.mHeight = childAt.getMeasuredHeight();
                    }
                } else {
                    this.mHeight = Math.max(this.mHeight, childAt.getMeasuredHeight());
                }
                paddingLeft += measuredWidth + this.padH;
            }
        }
        if (View.MeasureSpec.getMode(i3) == 0) {
            size2 = paddingTop + this.mHeight;
        } else if (View.MeasureSpec.getMode(i3) == Integer.MIN_VALUE && (i4 = paddingTop + this.mHeight) < size2) {
            size2 = i4;
        }
        setMeasuredDimension(View.MeasureSpec.getSize(i2), size2 + getPaddingBottom());
    }

    public void setCurRowNum(int i2) {
        this.curRowNum = i2;
    }

    public void setMaxRows(int i2) {
        this.maxRows = i2;
    }

    public void setRowNumListener(RowNumListener rowNumListener) {
        this.rowNumListener = rowNumListener;
    }

    public void setSpace(int i2, int i3) {
        this.padH = i2;
        this.padV = i3;
    }

    public PDFlowLayoutFix(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.maxRows = -1;
        this.padH = PDUtils.dip2px(15.0f);
        this.padV = PDUtils.dip2px(15.0f);
        this.curRowNum = 0;
        this.rowHeight = new ArrayList<>();
    }
}
